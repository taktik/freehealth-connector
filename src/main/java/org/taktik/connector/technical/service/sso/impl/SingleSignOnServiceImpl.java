package org.taktik.connector.technical.service.sso.impl;

import org.taktik.connector.technical.config.ConfigFactory;
import org.taktik.connector.technical.config.ConfigValidator;
import org.taktik.connector.technical.enumeration.Charset;
import org.taktik.connector.technical.enumeration.SsoProfile;
import org.taktik.connector.technical.exception.TechnicalConnectorException;
import org.taktik.connector.technical.exception.TechnicalConnectorExceptionValues;
import org.taktik.connector.technical.idgenerator.IdGenerator;
import org.taktik.connector.technical.idgenerator.IdGeneratorFactory;
import org.taktik.connector.technical.service.sso.SingleSignOnService;
import org.taktik.connector.technical.session.AbstractSessionServiceWithCache;
import org.taktik.connector.technical.utils.ConnectorIOUtils;
import org.taktik.connector.technical.utils.ConnectorXmlUtils;
import org.taktik.connector.technical.ws.ServiceFactory;
import org.taktik.connector.technical.ws.domain.GenericRequest;
import org.taktik.connector.technical.ws.domain.TokenType;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.net.URI;
import java.net.URISyntaxException;
import javax.xml.soap.SOAPException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.Validate;
import org.bouncycastle.util.encoders.Base64;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class SingleSignOnServiceImpl extends AbstractSessionServiceWithCache implements SingleSignOnService {
   private static final String PROP_SSO_AUTODISCOVERY_ENABLED = "org.taktik.connector.technical.service.sso.autodiscovery.enabled";
   public static final String PROP_ENDPOINT_STS_SSO = "endpoint.sts.sso";
   public static final String PROP_ENDPOINT_IDP_SAML2_POST = "endpoint.idp.saml2.post";
   public static final String PROP_ENDPOINT_IDP_SAML2_ARTIFACT = "endpoint.idp.saml2.artifact";
   private static final String PROP_BROWSER = "browser";
   private static final Logger LOG = LoggerFactory.getLogger(SingleSignOnServiceImpl.class);
   private IdGenerator idGenerator;
   private ConfigValidator config;

   public SingleSignOnServiceImpl() {
      try {
         this.idGenerator = IdGeneratorFactory.getIdGenerator("xsid");
         this.config = ConfigFactory.getConfigValidator();
      } catch (TechnicalConnectorException var2) {
         throw new IllegalArgumentException(var2);
      }
   }

   public void signin(SsoProfile profile) throws TechnicalConnectorException {
      this.signin(profile, (String)null);
   }

   public void signin(SsoProfile profile, String relayState) throws TechnicalConnectorException {
      switch(profile) {
      case SAML2_ARTIFACT:
         this.signinWithSAML2Artifact(relayState);
         break;
      case SAML2_POST:
         this.signinWithSAML2POST(relayState);
         break;
      default:
         throw new IllegalArgumentException("Unsupported SSO profile [" + profile + "]");
      }

   }

   private void signinWithSAML2Artifact(String targetLocation) throws TechnicalConnectorException {
      try {
         String template = ConnectorIOUtils.getResourceAsString("/sso/SSORequestSTSSAML2Artifact.xml");
         template = StringUtils.replaceEach(template, new String[]{"${reqId}", "${endpoint.idp.saml2.artifact}"}, new String[]{this.idGenerator.generateId(), this.getSAML2Artifact()});
         NodeList references = this.invokeSecureTokenService(ConnectorXmlUtils.flatten(template)).getElementsByTagNameNS("http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd", "Reference");
         Validate.notNull(references);
         Validate.isTrue(references.getLength() == 1);
         Element reference = (Element)references.item(0);
         String uri = reference.getAttribute("URI");
         if (StringUtils.isNotBlank(targetLocation)) {
            uri = uri + "&RelayState=" + targetLocation;
         }

         LOG.debug("Launching browser with url [" + uri + "]");
         this.launchBrowser(new URI(uri));
      } catch (IOException var6) {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.CORE_TECHNICAL, var6, new Object[]{var6.getMessage()});
      } catch (URISyntaxException var7) {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.CORE_TECHNICAL, var7, new Object[]{var7.getMessage()});
      }
   }

   private String getSAML2Post() {
      String hostname = this.config.getURLProperty("endpoint.sts.sso").getHost();
      if (Boolean.TRUE.toString().equalsIgnoreCase(this.config.getProperty("org.taktik.connector.technical.service.sso.autodiscovery.enabled", Boolean.TRUE.toString()))) {
         if ("services-acpt.ehealth.fgov.be".equals(hostname)) {
            return "https://wwwacc.ehealth.fgov.be/idp/Authn/SSO/SAML2/POST";
         }

         if ("services.ehealth.fgov.be".equals(hostname)) {
            return "https://www.ehealth.fgov.be/idp/Authn/SSO/SAML2/POST";
         }
      }

      return this.config.getProperty("endpoint.idp.saml2.post");
   }

   private String getSAML2Artifact() {
      String hostname = this.config.getURLProperty("endpoint.sts.sso").getHost();
      if (Boolean.TRUE.toString().equalsIgnoreCase(this.config.getProperty("org.taktik.connector.technical.service.sso.autodiscovery.enabled", Boolean.TRUE.toString()))) {
         if ("services-acpt.ehealth.fgov.be".equals(hostname)) {
            return "https://wwwacc.ehealth.fgov.be/idp/Authn/SSO/SAML2/Artifact";
         }

         if ("services.ehealth.fgov.be".equals(hostname)) {
            return "https://www.ehealth.fgov.be/idp/Authn/SSO/SAML2/Artifact";
         }
      }

      return this.config.getProperty("endpoint.idp.saml2.artifact");
   }

   private void signinWithSAML2POST(String targetLocation) throws TechnicalConnectorException {
      FileOutputStream fos = null;

      try {
         String template = ConnectorIOUtils.getResourceAsString("/sso/SSORequestSTSSAML2POST.xml");
         template = StringUtils.replaceEach(template, new String[]{"${reqId}", "${endpoint.idp.saml2.post}"}, new String[]{this.idGenerator.generateId(), this.getSAML2Post()});
         NodeList assertions = this.invokeSecureTokenService(ConnectorXmlUtils.flatten(template)).getElementsByTagNameNS("urn:oasis:names:tc:SAML:2.0:assertion", "Assertion");
         Validate.notNull(assertions);
         Validate.isTrue(assertions.getLength() == 1);
         Element assertion = (Element)assertions.item(0);
         String samlResponse = ConnectorIOUtils.getResourceAsString("/sso/bindingTemplate-SAMLResponse.xml");
         samlResponse = StringUtils.replaceEachRepeatedly(samlResponse, new String[]{"${SAMLResponseID}", "${SAMLResponseIssueInstant}", "${SAMLAssertion}"}, new String[]{IdGeneratorFactory.getIdGenerator("xsid").generateId(), (new DateTime()).toString(), this.toXMLString(assertion)});
         String templateForm = "";
         if (StringUtils.isNotBlank(targetLocation)) {
            templateForm = ConnectorIOUtils.getResourceAsString("/sso/bindingTemplate-Form.html");
         } else {
            templateForm = ConnectorIOUtils.getResourceAsString("/sso/bindingTemplate-FormNoRelayState.html");
         }

         templateForm = StringUtils.replaceEachRepeatedly(templateForm, new String[]{"${endpoint.idp.saml2.post}", "${relayState}", "${SAMLResponse}"}, new String[]{this.getSAML2Post(), targetLocation, new String(Base64.encode(ConnectorIOUtils.toBytes(ConnectorXmlUtils.flatten(samlResponse), Charset.UTF_8)))});
         File result = File.createTempFile("sso", "post.html");
         result.deleteOnExit();
         URI uri = result.toURI();
         fos = new FileOutputStream(result);
         IOUtils.write(templateForm, fos);
         this.launchBrowser(uri);
      } catch (IOException var13) {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.CORE_TECHNICAL, var13, new Object[]{var13.getMessage()});
      } finally {
         ConnectorIOUtils.closeQuietly((Object)fos);
      }

   }

   private void launchBrowser(URI uri) throws IOException {
      if (this.config.hasProperty("browser")) {
         Runtime.getRuntime().exec(this.config.getProperty("browser") + " " + uri);
      } else {
         LOG.info("Using system default for opening " + uri.toASCIIString());
         Desktop.getDesktop().browse(uri);
      }

   }

   private String toXMLString(Element assertion) throws TechnicalConnectorException {
      try {
         StreamResult sr = new StreamResult(new StringWriter());
         Transformer tf = TransformerFactory.newInstance().newTransformer();
         tf.setOutputProperty("encoding", "utf8");
         tf.setOutputProperty("indent", "no");
         tf.setOutputProperty("media-type", "text/xml");
         tf.setOutputProperty("omit-xml-declaration", "yes");
         tf.transform(new DOMSource(assertion), sr);
         return sr.getWriter().toString();
      } catch (TransformerException var4) {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.CORE_TECHNICAL, var4, new Object[]{var4.getMessage()});
      }
   }

   private Element invokeSecureTokenService(String template) throws TechnicalConnectorException {
      try {
         GenericRequest request = new GenericRequest();
         request.setEndpoint(this.config.getProperty("endpoint.sts.sso"));
         request.setCredential(this.getSamlToken(), TokenType.SAML);
         request.setSoapAction("urn:be:fgov:ehealth:sts:protocol:v1:RequestSecurityToken");
         request.setPayload(template);
         return (Element)ServiceFactory.getGenericWsSender().send(request).asNode();
      } catch (SOAPException var3) {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_WS, var3, new Object[]{var3.getMessage()});
      }
   }

   // $FF: synthetic class
   static class SyntheticClass_1 {
      // $FF: synthetic field
      static final int[] $SwitchMap$be$ehealth$technicalconnector$enumeration$SsoProfile = new int[SsoProfile.values().length];

      static {
         try {
            $SwitchMap$be$ehealth$technicalconnector$enumeration$SsoProfile[SsoProfile.SAML2_ARTIFACT.ordinal()] = 1;
         } catch (NoSuchFieldError var2) {
            ;
         }

         try {
            $SwitchMap$be$ehealth$technicalconnector$enumeration$SsoProfile[SsoProfile.SAML2_POST.ordinal()] = 2;
         } catch (NoSuchFieldError var1) {
            ;
         }

      }
   }
}
