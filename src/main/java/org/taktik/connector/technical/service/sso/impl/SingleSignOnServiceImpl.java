package org.taktik.connector.technical.service.sso.impl;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.Validate;
import org.bouncycastle.util.encoders.Base64;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.taktik.connector.technical.config.ConfigFactory;
import org.taktik.connector.technical.config.ConfigValidator;
import org.taktik.connector.technical.enumeration.Charset;
import org.taktik.connector.technical.enumeration.SsoProfile;
import org.taktik.connector.technical.exception.TechnicalConnectorException;
import org.taktik.connector.technical.exception.TechnicalConnectorExceptionValues;
import org.taktik.connector.technical.idgenerator.IdGenerator;
import org.taktik.connector.technical.idgenerator.IdGeneratorFactory;
import org.taktik.connector.technical.service.sso.SingleSignOnService;
import org.taktik.connector.technical.service.sts.security.SAMLToken;
import org.taktik.connector.technical.utils.ConnectorIOUtils;
import org.taktik.connector.technical.utils.ConnectorXmlUtils;
import org.taktik.connector.technical.ws.ServiceFactory;
import org.taktik.connector.technical.ws.domain.GenericRequest;
import org.taktik.connector.technical.ws.domain.TokenType;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.soap.SOAPException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.FileWriter;
import java.io.StringWriter;
import java.net.URI;
import java.net.URISyntaxException;

public class SingleSignOnServiceImpl implements SingleSignOnService {
   private static final String PROP_SSO_AUTODISCOVERY_ENABLED = "org.taktik.connector.technical.service.sso.autodiscovery.enabled";
   public static final String PROP_ENDPOINT_STS_SSO = "endpoint.sts.sso";
   public static final String PROP_ENDPOINT_IDP_SAML2_POST = "endpoint.idp.saml2.post";
   public static final String PROP_ENDPOINT_IDP_SAML2_ARTIFACT = "endpoint.idp.saml2.artifact";
   public static final String PROP_DEFAULT_BROWSER_HANDLER = "org.taktik.connector.technical.service.sso.browserhandler.default";
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

   public String signin(SsoProfile profile, SAMLToken samlToken) throws TechnicalConnectorException {
      switch(profile) {
      case SAML2_ARTIFACT:
         return this.signinWithSAML2Artifact(samlToken);
      case SAML2_POST:
         return this.signinWithSAML2POST(samlToken);
      default:
         throw new IllegalArgumentException("Unsupported SSO profile [" + profile + "]");
      }

   }

   private String signinWithSAML2Artifact(SAMLToken samlToken) throws TechnicalConnectorException {
      try {
         String template = ConnectorIOUtils.getResourceAsString("/sso/SSORequestSTSSAML2Artifact.xml");
         template = StringUtils.replaceEach(template, new String[]{"${reqId}", "${endpoint.idp.saml2.artifact}"}, new String[]{this.idGenerator.generateId(), this.getSAML2Artifact()});
         NodeList references = this.invokeSecureTokenService(ConnectorXmlUtils.flatten(template), samlToken).getElementsByTagNameNS("http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd", "Reference");
         Validate.notNull(references);
         Validate.isTrue(references.getLength() == 1);
         Element reference = (Element)references.item(0);
         String uri = reference.getAttribute("URI");

         LOG.debug("Launching browser with url [" + uri + "]");
         return new URI(uri).toASCIIString();
      } catch (URISyntaxException var6) {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.CORE_TECHNICAL, var6, new Object[]{var6.getMessage()});
      }
   }

   private String getSAML2Post() {
      String hostname = this.config.getURLProperty("endpoint.sts.sso").getHost();
      if (Boolean.TRUE.toString().equalsIgnoreCase(this.config.getProperty("org.taktik.connector.technical.service.sso.autodiscovery.enabled", Boolean.TRUE.toString()))) {
         if ("services-acpt.ehealth.fgov.be".equals(hostname)) {
            return "https://wwwacc.ehealth.fgov.be/idp/profile/SAML2/Bearer/POST";
         }

         if ("services.ehealth.fgov.be".equals(hostname)) {
            return "https://www.ehealth.fgov.be/idp/profile/SAML2/Bearer/POST";
         }
      }

      return this.config.getProperty("endpoint.idp.saml2.post");
   }

   private String getSAML2Artifact() {
      String hostname = this.config.getURLProperty("endpoint.sts.sso").getHost();
      if (Boolean.TRUE.toString().equalsIgnoreCase(this.config.getProperty("org.taktik.connector.technical.service.sso.autodiscovery.enabled", Boolean.TRUE.toString()))) {
         if ("services-acpt.ehealth.fgov.be".equals(hostname)) {
            return "https://wwwacc.ehealth.fgov.be/idp/profile/SAML2/Bearer/Artifact";
         }

         if ("services.ehealth.fgov.be".equals(hostname)) {
            return "https://www.ehealth.fgov.be/idp/profile/SAML2/Bearer/Artifact";
         }
      }

      return this.config.getProperty("endpoint.idp.saml2.artifact");
   }

   private String signinWithSAML2POST(SAMLToken samlToken) throws TechnicalConnectorException {
      FileWriter fw = null;

      try {
         String template = ConnectorIOUtils.getResourceAsString("/sso/SSORequestSTSSAML2POST.xml");
         template = StringUtils.replaceEach(template, new String[]{"${reqId}", "${endpoint.idp.saml2.post}"}, new String[]{this.idGenerator.generateId(), this.getSAML2Post()});
         NodeList assertions = this.invokeSecureTokenService(ConnectorXmlUtils.flatten(template), samlToken).getElementsByTagNameNS("urn:oasis:names:tc:SAML:2.0:assertion", "Assertion");
         Validate.notNull(assertions);
         Validate.isTrue(assertions.getLength() == 1);
         Element assertion = (Element)assertions.item(0);
         String samlResponse = ConnectorIOUtils.getResourceAsString("/sso/bindingTemplate-SAMLResponse.xml");
         samlResponse = StringUtils.replaceEachRepeatedly(samlResponse, new String[]{"${SAMLResponseID}", "${SAMLResponseIssueInstant}", "${SAMLAssertion}"}, new String[]{IdGeneratorFactory.getIdGenerator("xsid").generateId(), (new DateTime()).toString(), this.toXMLString(assertion)});
         return new String(Base64.encode(ConnectorIOUtils.toBytes(ConnectorXmlUtils.flatten(samlResponse), Charset.UTF_8)));
      } finally {
         ConnectorIOUtils.closeQuietly(fw);
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

   private Element invokeSecureTokenService(String template, SAMLToken samlToken) throws TechnicalConnectorException {
      try {
         GenericRequest request = new GenericRequest();
         request.setEndpoint(this.config.getProperty("endpoint.sts.sso"));
         request.setCredential(samlToken, TokenType.SAML);
         request.setSoapAction("urn:be:fgov:ehealth:sts:protocol:v1:RequestSecurityToken");
         request.setPayload(template);
         return (Element)ServiceFactory.getGenericWsSender().send(request).asNode();
      } catch (SOAPException var3) {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_WS, var3, new Object[]{var3.getMessage()});
      }
   }
}
