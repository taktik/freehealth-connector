package be.ehealth.technicalconnector.service.sts.security.impl;

import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.exception.TechnicalConnectorExceptionValues;
import be.ehealth.technicalconnector.service.sts.security.Credential;
import be.ehealth.technicalconnector.service.sts.security.SAMLToken;
import be.ehealth.technicalconnector.service.sts.utils.SAMLHelper;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import org.apache.commons.lang3.Validate;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Element;

public abstract class AbstractSAMLToken extends AbstractExtendedCredential implements SAMLToken {
   private static final Logger LOG = LoggerFactory.getLogger(AbstractSAMLToken.class);
   private final Credential credential;
   private final Element assertion;

   public AbstractSAMLToken(Element assertion, Credential credential) {
      Validate.notNull(assertion);
      Validate.notNull(credential);
      this.assertion = assertion;
      this.credential = credential;
   }

   public String getIssuer() throws TechnicalConnectorException {
      return this.credential.getIssuer();
   }

   public String getIssuerQualifier() throws TechnicalConnectorException {
      return this.credential.getIssuerQualifier();
   }

   public PublicKey getPublicKey() throws TechnicalConnectorException {
      return this.credential.getPublicKey();
   }

   public PrivateKey getPrivateKey() throws TechnicalConnectorException {
      return this.credential.getPrivateKey();
   }

   public X509Certificate getCertificate() throws TechnicalConnectorException {
      return this.credential.getCertificate();
   }

   public Element getAssertion() {
      return this.assertion;
   }

   public String getProviderName() {
      try {
         return this.credential.getProviderName();
      } catch (TechnicalConnectorException var2) {
         LOG.error(var2.getClass().getSimpleName() + ":" + var2.getMessage(), var2);
         return "";
      }
   }

   public Certificate[] getCertificateChain() throws TechnicalConnectorException {
      return this.credential.getCertificateChain();
   }

   public KeyStore getKeyStore() throws TechnicalConnectorException {
      return this.credential.getKeyStore();
   }

   public String getAssertionID() {
      return this.assertion.getAttribute("AssertionID");
   }

   public void checkValidity() throws TechnicalConnectorException {
      DateTime calendar = SAMLHelper.getNotOnOrAfterCondition(this.assertion);
      if (calendar.isBeforeNow()) {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.INVALID_TOKEN, new Object[]{"token is expired."});
      }
   }
}
