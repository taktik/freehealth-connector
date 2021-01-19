package be.ehealth.technicalconnector.service.sts.impl;

import be.ehealth.technicalconnector.config.domain.Duration;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.idgenerator.IdGeneratorFactory;
import be.ehealth.technicalconnector.service.sts.STSService;
import be.ehealth.technicalconnector.service.sts.domain.SAMLAttribute;
import be.ehealth.technicalconnector.service.sts.domain.SAMLAttributeDesignator;
import be.ehealth.technicalconnector.service.sts.domain.SAMLNameIdentifier;
import be.ehealth.technicalconnector.service.sts.security.Credential;
import be.ehealth.technicalconnector.utils.DateUtils;
import java.security.cert.X509Certificate;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.Validate;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.w3c.dom.Element;

public abstract class AbstractSTSService implements STSService {
   public static final String HOK_METHOD = "urn:oasis:names:tc:SAML:1.0:cm:holder-of-key";
   public static final String SV_METHOD = "urn:oasis:names:tc:SAML:1.0:cm:sender-vouches";
   protected static final String NAMEID_UNSPECIFIED = "urn:oasis:names:tc:SAML:1.1:nameid-format:unspecified";
   protected static final String NAMEID_X509SUBJECTNAME = "urn:oasis:names:tc:SAML:1.1:nameid-format:X509SubjectName";
   protected static final String XMLNS_WSSE = "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd";
   protected static final String XMLNS_DS = "http://www.w3.org/2000/09/xmldsig#";
   protected static final String XMLNS_WSU = "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd";
   protected static final String XMLNS_AUTH = "http://docs.oasis-open.org/wsfed/authorization/200706";
   protected static final String XMLNS_WST = "http://docs.oasis-open.org/ws-sx/ws-trust/200512";
   protected static final String XMLNS_SAML = "urn:oasis:names:tc:SAML:1.0:assertion";
   protected static final String XMLNS_SAMLP = "urn:oasis:names:tc:SAML:1.0:protocol";

   private SAMLNameIdentifier generateNameIdentifier(X509Certificate authnCertificate) {
      Validate.notNull(authnCertificate, "Parameter authnCertificate is not nullable.");
      String cn = authnCertificate.getSubjectX500Principal().getName("RFC1779");
      String ca = authnCertificate.getIssuerX500Principal().getName("RFC1779");
      return new SAMLNameIdentifier(StringEscapeUtils.escapeXml(cn), "urn:oasis:names:tc:SAML:1.1:nameid-format:X509SubjectName", StringEscapeUtils.escapeXml(ca), StringEscapeUtils.escapeXml(cn));
   }

   protected String processDefaultFields(String requestTemplate, Duration validity, SAMLNameIdentifier nameIdentifier) throws TechnicalConnectorException {
      DateTime now = new DateTime();
      String uuid = IdGeneratorFactory.getIdGenerator("uuid").generateId();
      String notBefore = DateUtils.printDateTime(now.toDateTime(DateTimeZone.UTC));
      String notAfter = DateUtils.printDateTime(now.plusSeconds((int)validity.convert(TimeUnit.SECONDS)).toDateTime(DateTimeZone.UTC));
      String result = StringUtils.replace(requestTemplate, "${uuid}", uuid);
      result = StringUtils.replace(result, "${NotBefore}", notBefore);
      result = StringUtils.replace(result, "${NotOnOrAfter}", notAfter);
      result = StringUtils.replace(result, "${issuer}", nameIdentifier.getAssertionIssuer());
      result = StringUtils.replace(result, "${nameid.format}", nameIdentifier.getFormat());
      result = StringUtils.replace(result, "${nameid.qualifier}", nameIdentifier.getNameQualifier());
      result = StringUtils.replace(result, "${nameid.value}", nameIdentifier.getValue());
      return result;
   }

   protected SAMLNameIdentifier generateNameIdentifier(Credential headerCredentials, String nameQualifier, String value) throws TechnicalConnectorException {
      SAMLNameIdentifier nameId = null;
      if (StringUtils.isEmpty(nameQualifier) && StringUtils.isEmpty(value)) {
         nameId = this.generateNameIdentifier(headerCredentials.getCertificate());
      } else {
         nameId = new SAMLNameIdentifier(nameQualifier, "urn:oasis:names:tc:SAML:1.1:nameid-format:unspecified", nameQualifier, value);
      }

      return nameId;
   }

   public Element getToken(Credential headerCredentials, Credential bodyCredentials, List<SAMLAttribute> attributes, List<SAMLAttributeDesignator> designators, String subjectConfirmationMethod, int validity) throws TechnicalConnectorException {
      return this.getToken(headerCredentials, bodyCredentials, attributes, designators, subjectConfirmationMethod, new Duration((long)validity, TimeUnit.HOURS));
   }

   public Element getToken(Credential headerCredentials, Credential bodyCredentials, List<SAMLAttribute> attributes, List<SAMLAttributeDesignator> designators, String authenticationMethod, String nameQualifier, String value, String subjectConfirmationMethod, int validity) throws TechnicalConnectorException {
      return this.getToken(headerCredentials, bodyCredentials, attributes, designators, authenticationMethod, nameQualifier, value, subjectConfirmationMethod, new Duration((long)validity, TimeUnit.HOURS));
   }

   public Element renewToken(Credential headerCredentials, Credential bodyCredentials, Element samlToken, int validity) throws TechnicalConnectorException {
      return this.renewToken(headerCredentials, bodyCredentials, samlToken, new Duration((long)validity, TimeUnit.HOURS));
   }
}
