package be.ehealth.technicalconnector.session.impl;

import be.ehealth.technicalconnector.config.ConfigFactory;
import be.ehealth.technicalconnector.config.ConfigValidator;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.service.etee.Crypto;
import be.ehealth.technicalconnector.service.etee.CryptoFactory;
import be.ehealth.technicalconnector.service.sts.security.Credential;
import be.ehealth.technicalconnector.service.sts.security.SAMLToken;
import be.ehealth.technicalconnector.session.SessionItem;
import java.security.PrivateKey;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SessionItemImpl implements SessionItem {
   private static final Logger LOG = LoggerFactory.getLogger(SessionItemImpl.class);
   private static List<String> expectedProps = new ArrayList();
   private static final String PROP_USER_FIRSTNAME = "";
   private static final String PROP_USER_LASTNAME = "";
   private SAMLToken token;
   private Credential hokCredential;
   private Crypto cryptoSystem;
   private Crypto cryptoPersonal;
   private Map<String, PrivateKey> hokPrivateKeys;
   private Credential encryptionCredential;
   private Credential headerCredential;
   private Map<String, PrivateKey> encryptionPrivateKeys;
   private ConfigValidator config;

   private void initIfNeeded() {
      if (this.config == null) {
         this.config = ConfigFactory.getConfigValidator(expectedProps);
      }

   }

   public SessionItemImpl() {
      this.initIfNeeded();
   }

   public void setSAMLToken(SAMLToken token) {
      this.token = token;
   }

   public final SAMLToken getSAMLToken() {
      return this.token;
   }

   public final void setHolderOfKeyCredential(Credential hokCredential) {
      this.hokCredential = hokCredential;
   }

   public final Credential getHolderOfKeyCredential() {
      return this.hokCredential;
   }

   public final void setHolderOfKeyPrivateKeys(Map<String, PrivateKey> hokPrivateKeys) {
      this.hokPrivateKeys = hokPrivateKeys;
   }

   public final Map<String, PrivateKey> getHolderOfKeyPrivateKeys() {
      return this.hokPrivateKeys;
   }

   public final void setEncryptionCredential(Credential encryptionCredential) {
      this.encryptionCredential = encryptionCredential;
   }

   public final Credential getEncryptionCredential() {
      if (this.encryptionCredential == null) {
         LOG.warn("Session Initialized without encryption: return null.");
      }

      return this.encryptionCredential;
   }

   public final void setEncryptionPrivateKeys(Map<String, PrivateKey> encryptionPrivateKeys) {
      this.encryptionPrivateKeys = encryptionPrivateKeys;
   }

   public final Map<String, PrivateKey> getEncryptionPrivateKeys() {
      if (this.encryptionPrivateKeys == null) {
         LOG.warn("Session Initialized without encryption: return null.");
      }

      return this.encryptionPrivateKeys;
   }

   public Crypto getHolderOfKeyCrypto() throws TechnicalConnectorException {
      LOG.debug("Retrieving HOK Crypto... (for sealing and unsealing)");
      if (this.cryptoSystem == null) {
         LOG.debug("No HOK Crypto has been created. User has valid session, creating crypto...");
         this.cryptoSystem = CryptoFactory.getCrypto(this.getHolderOfKeyCredential(), this.getHolderOfKeyPrivateKeys());
         LOG.debug("HOK crypto created: " + this.cryptoSystem);
      }

      return this.cryptoSystem;
   }

   public Crypto getEncryptionCrypto() throws TechnicalConnectorException {
      LOG.debug("Retrieving personal Crypto... (for sealing and unsealing)");
      if (this.cryptoPersonal == null) {
         LOG.debug("No personal Crypto has been created. User has valid session, creating crypto...");
         this.cryptoPersonal = CryptoFactory.getCrypto(this.getEncryptionCredential(), this.getEncryptionPrivateKeys());
         LOG.debug("System crypto created: " + this.cryptoPersonal);
      }

      return this.cryptoPersonal;
   }

   public void setHeaderCredential(Credential headerCredential) throws TechnicalConnectorException {
      this.headerCredential = headerCredential;
   }

   public Credential getHeaderCredential() throws TechnicalConnectorException {
      return this.headerCredential;
   }

   static {
      expectedProps.add("");
      expectedProps.add("");
   }
}
