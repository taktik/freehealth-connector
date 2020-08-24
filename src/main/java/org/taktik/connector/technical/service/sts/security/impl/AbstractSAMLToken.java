package org.taktik.connector.technical.service.sts.security.impl;

import org.taktik.connector.technical.exception.TechnicalConnectorException;
import org.taktik.connector.technical.exception.TechnicalConnectorExceptionValues;
import org.taktik.connector.technical.service.sts.security.Credential;
import org.taktik.connector.technical.service.sts.security.SAMLToken;
import org.taktik.connector.technical.service.sts.utils.SAMLHelper;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import org.apache.commons.lang.Validate;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Element;

public abstract class AbstractSAMLToken extends AbstractExtendedCredential implements SAMLToken {
   private static final Logger LOG = LoggerFactory.getLogger(SAMLHolderOfKeyToken.class);
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

   @Override
   public String getQuality() {
      return credential instanceof AbstractExtendedCredential ? ((AbstractExtendedCredential) credential).getQuality() : super.getQuality();
   }
}
