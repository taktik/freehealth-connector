package be.ehealth.technicalconnector.ws.domain;

import be.ehealth.technicalconnector.exception.InstantiationException;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.handler.CertificateCallback;
import be.ehealth.technicalconnector.handler.SAMLHolderOfKeyHandler;
import be.ehealth.technicalconnector.handler.SAMLSenderVouchesHandler;
import be.ehealth.technicalconnector.handler.domain.WsAddressingHeader;
import be.ehealth.technicalconnector.service.sts.SAMLTokenFactory;
import be.ehealth.technicalconnector.service.sts.security.Credential;
import be.ehealth.technicalconnector.service.sts.security.SAMLToken;
import be.ehealth.technicalconnector.service.sts.security.impl.KeyPairCredential;
import be.ehealth.technicalconnector.service.sts.security.impl.SAMLHolderOfKeyToken;
import be.ehealth.technicalconnector.service.sts.security.impl.SAMLSenderVouchesCredential;
import be.ehealth.technicalconnector.utils.ByteArrayDatasource;
import be.ehealth.technicalconnector.utils.ConfigurableFactoryHelper;
import be.ehealth.technicalconnector.utils.ConnectorXmlUtils;
import be.ehealth.technicalconnector.utils.MarshallerHelper;
import be.ehealth.technicalconnector.utils.impl.JaxbContextFactory;
import be.ehealth.technicalconnector.ws.feature.EndpointFeature;
import be.ehealth.technicalconnector.ws.feature.GenericFeature;
import be.ehealth.technicalconnector.ws.feature.SOAPActionFeature;
import be.ehealth.technicalconnector.ws.feature.WSAddressingV200508Feature;
import be.ehealth.technicalconnector.ws.feature.XOPFeature;
import java.security.PrivateKey;
import java.security.cert.X509Certificate;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.activation.DataHandler;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.ws.handler.Handler;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
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
   private FeatureLoader featureLoader = new FeatureLoader();
   private HandlerChain handlerChain = new HandlerChain();

   public GenericRequest() {
   }

   public GenericRequest setEndpoint(String endpoint) {
      this.featureLoader.register(new EndpointFeature(endpoint));
      return this;
   }

   public GenericRequest setPayload(Document payload) {
      this.payload = payload;
      return this;
   }

   public GenericRequest setPayload(Document payload, GenericFeature... features) {
      this.payload = payload;
      this.featureLoader.register(features);
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

   public GenericRequest setPayload(Object payload, GenericFeature... features) {
      this.featureLoader.register(features);
      Class<?> payloadClazz = payload.getClass();
      if (payloadClazz.isAnnotationPresent(XmlRootElement.class)) {
         MarshallerHelper helper = getHelper(payloadClazz, (XOPFeature)this.featureLoader.getFeature(XOPFeature.class));
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
         } catch (JAXBException var7) {
            throw new IllegalArgumentException("PayLoadclass [" + payloadClazz + "] is not annotated with @XMLRootElement or is not a JAXBElement class.", var7);
         }
      }

      return this;
   }

   private static MarshallerHelper getHelper(Class<?> payloadClazz, XOPFeature feature) {
      return feature == null ? new MarshallerHelper(payloadClazz, payloadClazz, false, false) : new MarshallerHelper(payloadClazz, payloadClazz, false, feature.isEnabled(), feature.getThreshold());
   }

   public GenericRequest setSoapAction(String soapAction) {
      this.setSoapAction(soapAction, true);
      return this;
   }

   public GenericRequest setSoapAction(String soapAction, boolean wsiCompliant) {
      this.featureLoader.register(new SOAPActionFeature(soapAction, wsiCompliant));
      return this;
   }

   public Map<String, Object> getRequestMap() {
      return this.featureLoader.getRequestMap();
   }

   public GenericRequest setWSAddressing(WsAddressingHeader header) throws TechnicalConnectorException {
      this.featureLoader.register(new WSAddressingV200508Feature(header));
      return this;
   }

   public GenericRequest setCertificateSecured(X509Certificate certificate, PrivateKey privateKey) throws TechnicalConnectorException {
      Credential cred = new KeyPairCredential(privateKey, certificate);
      this.setCredential(cred, TokenType.X509);
      return this;
   }

   public GenericRequest setSamlSecured(Element assertion, Credential hok) throws TechnicalConnectorException {
      SAMLToken token = SAMLTokenFactory.getInstance().createSamlToken(assertion, hok);
      this.setCredential(token, TokenType.SAML);
      return this;
   }

   public GenericRequest setCredential(Credential cred, TokenType sec) throws TechnicalConnectorException {
      switch (sec) {
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
      this.setCredential((Credential)null, sec);
      return this;
   }

   private GenericRequest processAsX509(Credential cred) throws TechnicalConnectorException {
      LOG.debug("Using X509 Security");
      this.handlerChain.register(HandlerPosition.SECURITY, new CertificateCallback(cred));
      return this;
   }

   private GenericRequest processAsSAML(Credential cred) {
      if (cred == null) {
         LOG.debug("Using HolderOfKey Credential with lazy init");
         this.handlerChain.register(HandlerPosition.SECURITY, new SAMLHolderOfKeyHandler());
      } else if (cred instanceof SAMLHolderOfKeyToken) {
         LOG.debug("Using HolderOfKey Credential");
         this.handlerChain.register(HandlerPosition.SECURITY, new SAMLHolderOfKeyHandler((SAMLToken)cred));
      } else {
         if (!(cred instanceof SAMLSenderVouchesCredential)) {
            throw new IllegalArgumentException("Unsupported credential of type [" + cred.getClass().getName() + "]");
         }

         LOG.debug("Using SenderVouches Credential");
         this.handlerChain.register(HandlerPosition.SECURITY, new SAMLSenderVouchesHandler((SAMLSenderVouchesCredential)cred));
      }

      return this;
   }

   public GenericRequest addDefaulHandlerChain() throws TechnicalConnectorException {
      List<SOAPHandler> beforeSecurityList = (new ConfigurableFactoryHelper("connector.defaulthandlerchain.beforesecurity", (String)null)).getImplementations();
      Iterator var2 = beforeSecurityList.iterator();

      while(var2.hasNext()) {
         SOAPHandler handler = (SOAPHandler)var2.next();
         this.handlerChain.register(HandlerPosition.BEFORE, handler);
      }

      List<SOAPHandler> afterSecurityList = (new ConfigurableFactoryHelper("connector.defaulthandlerchain.aftersecurity", (String)null)).getImplementations();
      Iterator var6 = afterSecurityList.iterator();

      while(var6.hasNext()) {
         SOAPHandler handler = (SOAPHandler)var6.next();
         this.handlerChain.register(HandlerPosition.AFTER, handler);
      }

      return this;
   }

   public GenericRequest addHandlerChain(HandlerChain handlers) {
      this.handlerChain.add(handlers);
      return this;
   }

   public Handler<? extends MessageContext>[] getHandlerchain() {
      this.handlerChain.add(this.featureLoader.getHandlerChain());
      Handler<?>[] result = this.handlerChain.getHandlers();
      result = HandlersLoader.addingDefaultHandlers(result);
      return result;
   }

   public GenericRequest addDataHandler(String id, DataHandler dataHandler) {
      this.handlers.put(id, dataHandler);
      return this;
   }

   public boolean isXopEnabled() {
      return this.featureLoader.hasFeature(XOPFeature.class);
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
}
