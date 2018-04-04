package org.taktik.connector.technical.ws.domain;

import org.taktik.connector.technical.exception.InstantiationException;
import org.taktik.connector.technical.exception.TechnicalConnectorException;
import org.taktik.connector.technical.exception.TechnicalConnectorExceptionValues;
import org.taktik.connector.technical.handler.CertificateCallback;
import org.taktik.connector.technical.handler.SAMLHolderOfKeyHandler;
import org.taktik.connector.technical.handler.SAMLSenderVouchesHandler;
import org.taktik.connector.technical.handler.SoapActionHandler;
import org.taktik.connector.technical.handler.WsAddressingHandlerV200508;
import org.taktik.connector.technical.handler.domain.WsAddressingHeader;
import org.taktik.connector.technical.service.sts.SAMLTokenFactory;
import org.taktik.connector.technical.service.sts.security.Credential;
import org.taktik.connector.technical.service.sts.security.SAMLToken;
import org.taktik.connector.technical.service.sts.security.impl.KeyPairCredential;
import org.taktik.connector.technical.service.sts.security.impl.SAMLHolderOfKeyToken;
import org.taktik.connector.technical.service.sts.security.impl.SAMLSenderVouchesCredential;
import org.taktik.connector.technical.utils.ByteArrayDatasource;
import org.taktik.connector.technical.utils.ConfigurableFactoryHelper;
import org.taktik.connector.technical.utils.ConnectorXmlUtils;
import org.taktik.connector.technical.utils.MarshallerHelper;
import org.taktik.connector.technical.utils.impl.JaxbContextFactory;
import org.taktik.connector.technical.ws.feature.GenericFeature;
import org.taktik.connector.technical.ws.feature.XOPFeature;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.PrivateKey;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import javax.activation.DataHandler;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.ws.handler.Handler;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

public final class GenericRequest {
   private static final Logger LOG = LoggerFactory.getLogger(GenericRequest.class);
   private static final DocumentBuilder DOC_BUILDER;
   private Document payload;
   private Map<String, DataHandler> handlers = new HashMap();
   private Map<String, Object> requestMap = new HashMap();
   private List<Handler> beforeSecurity = new ArrayList();
   private List<Handler> afterSecurity = new ArrayList();
   private List<Handler> securityHandler = new ArrayList();
   private Map<Class, Object> activeFeatures = new HashMap();
   private List<Handler> featureHandlers = new ArrayList();

   public GenericRequest setEndpoint(String endpoint) {
      try {
         new URL(endpoint);
         this.requestMap.put("javax.xml.ws.service.endpoint.address", endpoint);
         return this;
      } catch (MalformedURLException var3) {
         throw new IllegalArgumentException(var3.getMessage(), var3);
      }
   }

   public GenericRequest setPayload(Document payload) {
      this.payload = payload;
      return this;
   }

   public GenericRequest setPayload(Document payload, GenericFeature... features) {
      this.payload = payload;
      this.process(features);
      return this;
   }

   public Document getPayload() {
      try {
         Document result = DOC_BUILDER.newDocument();
         result.appendChild(result.importNode(this.payload.getDocumentElement().cloneNode(true), true));
         return result;
      } catch (Exception var2) {
         LOG.warn("Unable to clone payload, returning original one.", var2);
         return this.payload;
      }
   }

   public Map<String, DataHandler> getDataHandlerMap() {
      return Collections.unmodifiableMap(this.handlers);
   }

   public GenericRequest setPayload(String payload) throws TechnicalConnectorException {
      try {
         this.payload = ConnectorXmlUtils.toDocument(payload);
      } catch (TechnicalConnectorException var3) {
         if (var3.getCause() instanceof SAXException) {
            throw new IllegalArgumentException("Payload is not a well-formed xml document.", var3);
         }
      }

      return this;
   }

   public GenericRequest setPayload(Object payload) {
      this.setPayload(payload, (GenericFeature)null);
      return this;
   }

   /** @deprecated */
   @Deprecated
   public GenericRequest setPayload(Object payload, boolean xop) {
      XOPFeature mtomFeature = null;
      if (xop) {
         mtomFeature = new XOPFeature();
      }

      this.setPayload(payload, mtomFeature);
      return this;
   }

   public GenericRequest setPayload(Object payload, GenericFeature... features) {
      this.process(features);
      XOPFeature mtomFeature = this.getFeature(XOPFeature.class);
      Class<?> payloadClazz = payload.getClass();
      if (payloadClazz.isAnnotationPresent(XmlRootElement.class)) {
         MarshallerHelper helper = getHelper(payloadClazz, mtomFeature);
         this.payload = helper.toDocument(payload);
         this.handlers = helper.getDataHandlersMap();
      } else {
         if (!(payload instanceof JAXBElement)) {
            throw new IllegalArgumentException("PayLoadclass [" + payloadClazz + "] is not annotated with @XMLRootElement or is not a JAXBElement class.");
         }

         try {
            Document doc = DOC_BUILDER.newDocument();
            JAXBElement<?> jaxbElement = (JAXBElement)payload;
            Marshaller marshaller = JaxbContextFactory.getJaxbContextForClass(jaxbElement.getDeclaredType()).createMarshaller();
            marshaller.marshal(jaxbElement, doc);
            this.payload = doc;
         } catch (JAXBException var8) {
            throw new IllegalArgumentException("PayLoadclass [" + payloadClazz + "] is not annotated with @XMLRootElement or is not a JAXBElement class.", var8);
         }
      }

      return this;
   }

   private <T extends GenericFeature> T getFeature(Class<T> clazz) {
      Iterator i$ = this.activeFeatures.entrySet().iterator();

      Entry entry;
      do {
         if (!i$.hasNext()) {
            return (T)this.activeFeatures.get(clazz);
         }

         entry = (Entry)i$.next();
      } while(!clazz.isAssignableFrom((Class)entry.getKey()));

      return (T)entry.getValue();
   }

   private boolean hasFeature(Class<?> clazz) {
      Iterator i$ = this.activeFeatures.keySet().iterator();

      Class key;
      do {
         if (!i$.hasNext()) {
            return this.activeFeatures.containsKey(clazz);
         }

         key = (Class)i$.next();
      } while(!clazz.isAssignableFrom(key));

      return true;
   }

   private void process(GenericFeature... features) {
      GenericFeature[] arr$ = features;
      int len$ = features.length;

      for(int i$ = 0; i$ < len$; ++i$) {
         GenericFeature feature = arr$[i$];
         if (feature != null) {
            this.activeFeatures.put(feature.getClass(), feature);
            this.requestMap.put(feature.getID(), feature.isEnabled());
            this.featureHandlers.addAll(feature.getHandlers());
         }
      }

   }

   private static MarshallerHelper getHelper(Class<?> payloadClazz, XOPFeature feature) {
      return feature == null ? new MarshallerHelper(payloadClazz, payloadClazz, false, false) : new MarshallerHelper(payloadClazz, payloadClazz, false, feature.isEnabled(), feature.getThreshold());
   }

   public GenericRequest setSoapAction(String soapAction) throws TechnicalConnectorException {
      if (StringUtils.isNotBlank(soapAction)) {
         this.requestMap.put("javax.xml.ws.soap.http.soapaction.use", Boolean.TRUE);
         this.requestMap.put("javax.xml.ws.soap.http.soapaction.uri", soapAction);
         this.securityHandler.add(new SoapActionHandler());
      } else {
         LOG.warn("soapAction is Blank [" + soapAction + "]");
      }

      return this;
   }

   public Map<String, Object> getRequestMap() {
      return this.requestMap;
   }

   public GenericRequest setWSAddressing(WsAddressingHeader header) throws TechnicalConnectorException {
      if (header != null) {
         this.requestMap.put("org.taktik.connector.technical.handler.WsAddressingHandlerV200508.use", Boolean.TRUE);
         this.requestMap.put("org.taktik.connector.technical.handler.WsAddressingHandlerV200508", header);
         this.securityHandler.add(new WsAddressingHandlerV200508());
         return this;
      } else {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_GENERAL, new Object[]{"WsAddressing object is null."});
      }
   }

   /** @deprecated */
   @Deprecated
   public void setCertificateSecured() throws TechnicalConnectorException {
      this.setCredential(null, TokenType.X509);
   }

   public GenericRequest setCertificateSecured(X509Certificate certificate, PrivateKey privateKey) throws TechnicalConnectorException {
      Credential cred = new KeyPairCredential(privateKey, certificate);
      this.setCredential(cred, TokenType.X509);
      return this;
   }

   /** @deprecated */
   @Deprecated
   public void setSamlSecured() throws TechnicalConnectorException {
      this.setCredential(null, TokenType.SAML);
   }

   public GenericRequest setSamlSecured(Element assertion, Credential hok) throws TechnicalConnectorException {
      SAMLToken token = SAMLTokenFactory.getInstance().createSamlToken(assertion, hok);
      this.setCredential(token, TokenType.SAML);
      return this;
   }

   /** @deprecated */
   @Deprecated
   public void setSamlSecured(SAMLToken token) throws TechnicalConnectorException {
      this.setCredential(token, TokenType.SAML);
   }

   public GenericRequest setCredential(Credential cred, TokenType sec) throws TechnicalConnectorException {
      switch(sec) {
      case SAML:
         this.processAsSAML(cred);
         break;
      case X509:
      default:
         this.processAsX509(cred);
      }

      return this;
   }

   public GenericRequest setCredentialFromSession(TokenType sec) throws TechnicalConnectorException {
      this.setCredential(null, sec);
      return this;
   }

   private GenericRequest processAsX509(Credential cred) throws TechnicalConnectorException {
      LOG.debug("Using X509 Security");
      this.securityHandler.add(new CertificateCallback(cred));
      return this;
   }

   private GenericRequest processAsSAML(Credential cred) throws TechnicalConnectorException {
      if (cred == null) {
         LOG.debug("Using HolderOfKey Credential with lazy init");
         this.securityHandler.add(new SAMLHolderOfKeyHandler());
      } else if (cred instanceof SAMLHolderOfKeyToken) {
         LOG.debug("Using HolderOfKey Credential");
         this.securityHandler.add(new SAMLHolderOfKeyHandler((SAMLToken)cred));
      } else {
         if (!(cred instanceof SAMLSenderVouchesCredential)) {
            throw new IllegalArgumentException("Unsupported credential of type [" + cred.getClass().getName() + "]");
         }

         LOG.debug("Using SenderVouches Credential");
         this.securityHandler.add(new SAMLSenderVouchesHandler((SAMLSenderVouchesCredential)cred));
      }

      return this;
   }

   public GenericRequest addDefaulHandlerChain() throws TechnicalConnectorException {
      this.beforeSecurity.addAll((new ConfigurableFactoryHelper("connector.defaulthandlerchain.beforesecurity", null)).getImplementations());
      this.afterSecurity.addAll((new ConfigurableFactoryHelper("connector.defaulthandlerchain.aftersecurity", null)).getImplementations());
      return this;
   }

   /** @deprecated */
   @Deprecated
   public GenericRequest setDefaultHandlerChain() throws TechnicalConnectorException {
      return this.addDefaulHandlerChain();
   }

   public GenericRequest addHandlerChain(HandlerChain handlers) {
      this.beforeSecurity.addAll(handlers.getHandlers(HandlerPosition.BEFORE));
      this.afterSecurity.addAll(handlers.getHandlers(HandlerPosition.AFTER));
      this.afterSecurity.addAll(handlers.getHandlers(HandlerPosition.SECURITY));
      return this;
   }

   /** @deprecated */
   @Deprecated
   public GenericRequest setHandlerChain(HandlerChain handlers) {
      return this.addHandlerChain(handlers);
   }

   /** @deprecated */
   @Deprecated
   public List<Handler> getAfterSecurityHandlerChain() {
      return this.afterSecurity;
   }

   /** @deprecated */
   @Deprecated
   public List<Handler> getBeforeSecurityHandlerChain() {
      return this.beforeSecurity;
   }

   /** @deprecated */
   @Deprecated
   public List<Handler> getSecurityHandlerChain() {
      return this.securityHandler;
   }

   public Handler<?>[] getHandlerchain() {
      Handler<?>[] result = new Handler[0];
      if (this.beforeSecurity != null && !this.beforeSecurity.isEmpty()) {
         result = (Handler[]) ArrayUtils.addAll(result, this.beforeSecurity.toArray(new Handler[0]));
      }

      if (this.securityHandler != null) {
         result = (Handler[]) ArrayUtils.addAll(result, this.securityHandler.toArray(new Handler[0]));
      }

      if (this.afterSecurity != null && !this.afterSecurity.isEmpty()) {
         result = (Handler[]) ArrayUtils.addAll(result, this.afterSecurity.toArray(new Handler[0]));
      }

      if (this.featureHandlers != null && !this.featureHandlers.isEmpty()) {
         result = (Handler[]) ArrayUtils.addAll(result, this.featureHandlers.toArray(new Handler[0]));
      }

      result = HandlersLoader.addingDefaultHandlers(result);
      return result;
   }

   public GenericRequest addDataHandler(String id, DataHandler dataHandler) {
      this.handlers.put(id, dataHandler);
      return this;
   }

   public boolean isXopEnabled() {
      return this.hasFeature(XOPFeature.class);
   }

   public GenericRequest addDataHandler(String id, byte[] byteArray) {
      this.addDataHandler(id, new DataHandler(new ByteArrayDatasource(byteArray)));
      return this;
   }

   static {
      try {
         DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
         dbf.setNamespaceAware(true);
         DOC_BUILDER = dbf.newDocumentBuilder();
      } catch (Exception var1) {
         throw new InstantiationException("Unable to create DocumentBuilder", var1);
      }
   }

   // $FF: synthetic class
   static class SyntheticClass_1 {
      // $FF: synthetic field
      static final int[] $SwitchMap$be$ehealth$technicalconnector$ws$domain$TokenType = new int[TokenType.values().length];

      static {
         try {
            $SwitchMap$be$ehealth$technicalconnector$ws$domain$TokenType[TokenType.SAML.ordinal()] = 1;
         } catch (NoSuchFieldError var2) {
            ;
         }

         try {
            $SwitchMap$be$ehealth$technicalconnector$ws$domain$TokenType[TokenType.X509.ordinal()] = 2;
         } catch (NoSuchFieldError var1) {
            ;
         }

      }
   }
}
