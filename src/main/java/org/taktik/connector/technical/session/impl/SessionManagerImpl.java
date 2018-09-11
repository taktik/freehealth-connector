package org.taktik.connector.technical.session.impl;

import org.taktik.connector.technical.beid.BeIDCardFactory;
import org.taktik.connector.technical.cache.Cache;
import org.taktik.connector.technical.cache.CacheFactory;
import org.taktik.connector.technical.config.ConfigFactory;
import org.taktik.connector.technical.config.ConfigValidator;
import org.taktik.connector.technical.config.Configuration;
import org.taktik.connector.technical.exception.SessionManagementException;
import org.taktik.connector.technical.exception.SessionManagementExceptionValues;
import org.taktik.connector.technical.exception.TechnicalConnectorException;
import org.taktik.connector.technical.exception.TechnicalConnectorExceptionValues;
import org.taktik.connector.technical.service.etee.domain.EncryptionToken;
import org.taktik.connector.technical.service.keydepot.KeyDepotManager;
import org.taktik.connector.technical.service.keydepot.KeyDepotManagerFactory;
import org.taktik.connector.technical.service.sts.SAMLTokenFactory;
import org.taktik.connector.technical.service.sts.STSService;
import org.taktik.connector.technical.service.sts.STSServiceFactory;
import org.taktik.connector.technical.service.sts.domain.SAMLAttribute;
import org.taktik.connector.technical.service.sts.domain.SAMLAttributeDesignator;
import org.taktik.connector.technical.service.sts.security.Credential;
import org.taktik.connector.technical.service.sts.security.KeyStoreInfo;
import org.taktik.connector.technical.service.sts.security.SAMLToken;
import org.taktik.connector.technical.service.sts.security.impl.BeIDCredential;
import org.taktik.connector.technical.service.sts.security.impl.KeyStoreCredential;
import org.taktik.connector.technical.service.sts.utils.SAMLConfigHelper;
import org.taktik.connector.technical.service.sts.utils.SAMLHelper;
import org.taktik.connector.technical.session.SessionItem;
import org.taktik.connector.technical.session.SessionManager;
import org.taktik.connector.technical.session.SessionServiceWithCache;
import org.taktik.connector.technical.utils.CertificateParser;
import org.taktik.connector.technical.utils.DateUtils;
import org.taktik.connector.technical.utils.KeyStoreManager;
import be.fgov.ehealth.etee.crypto.utils.KeyManager;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.Validate;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Element;

public final class SessionManagerImpl implements SessionManager {
   private static final String EID_SESSION = "session";
   private static final Logger LOG = LoggerFactory.getLogger(SessionManagerImpl.class);
   private static final String PROP_SESSIONMNG_SAMLATTRIBUTEDESIGNATOR = "sessionmanager.samlattributedesignator";
   private static final String PROP_SESSIONMNG_SAMLATTRIBUTE = "sessionmanager.samlattribute";
   protected static final String AUTHENTICATION_ALIAS = "authentication";
   private static final String PROP_KEYSTORE_IDNT_NAME = "sessionmanager.identification.keystore";
   private static final String PROP_KEYSTORE_IDNT_ALIAS = "sessionmanager.identification.alias";
   private static final String PROP_KEYSTORE_HOK_NAME = "sessionmanager.holderofkey.keystore";
   private static final String PROP_KEYSTORE_HOK_ALIAS = "sessionmanager.holderofkey.alias";
   private static final String PROP_KEYSTORE_ENC_NAME = "sessionmanager.encryption.keystore";
   private static final String PROP_KEYSTORE_ENC_ALIAS = "sessionmanager.encryption.alias";
   private static final String PROP_VALIDITY_TOKEN = "sessionmanager.validity.token";
   private static final String PROP_AUTO_RENEW = "sessionmanager.activate.autorenew";
   private static final String PROP_DISABLE_EID_DISCOVERY = "sessionmanager.disable.eiddiscovery";
   private static final String PROP_EMPTY_PASSWORD_HOK = "sessionmanager.holderofkey.emptypassword";
   private static final String PROP_EMPTY_PASSWORD_ENCRYPTION = "sessionmanager.encryption.emptypassword";
   public static final String PROP_FETCH_ETK = "sessionmanager.fetch.etk";
   private static final int DEFAULT_VALIDITY_TOKEN = 24;
   private static Object mutex = new Object();
   private List<SessionServiceWithCache> cacheService;
   private final ConfigValidator config;
   private final Cache<String, KeyStore> cache;
   private SessionItem session;

   private SessionManagerImpl() {
      this.cacheService = new ArrayList();
      this.config = ConfigFactory.getConfigValidatorFor("sessionmanager.samlattribute", "sessionmanager.samlattributedesignator");
      this.cache = CacheFactory.newInstance(CacheFactory.CacheType.MEMORY);
      this.session = new SessionItemImpl();
   }

   public static SessionManagerImpl getInstance() {
      return SessionManagerImpl.SessionManagerImplSingleton.INSTANCE.getManagerImpl();
   }

   public void loadSession(SAMLToken token, String hokPwd) throws TechnicalConnectorException {
      this.loadSession(token, hokPwd, (String)null);
   }

   public void loadSession(SAMLToken token, String hokPwd, String encryptionPwd) throws TechnicalConnectorException {
      this.session.setHolderOfKeyCredential(token);
      Map<String, PrivateKey> hokPrivateKeys = new HashMap();
      hokPrivateKeys.put("authentication", token.getPrivateKey());

      try {
         if (token.getKeyStore() != null && hokPwd != null) {
            LOG.debug("Trying to add all the private keys of the HOK keystore.");
            Map<String, PrivateKey> tempKeys = KeyManager.getDecryptionKeys(token.getKeyStore(), hokPwd.toCharArray());
            hokPrivateKeys.putAll(tempKeys);
         }
      } catch (Exception var6) {
         LOG.warn(var6.getClass().getSimpleName() + ":" + var6.getMessage(), var6);
      }

      this.session.setHolderOfKeyPrivateKeys(hokPrivateKeys);
      this.loadEncryptionKeys(encryptionPwd);
      this.session.setSAMLToken(token);
   }

   public SessionItem createSessionEidOnly() throws TechnicalConnectorException {
      return this.createSession((String)null, (String)null);
   }

   public SessionItem createSession(String hokPwd) throws TechnicalConnectorException {
      return this.createSession(hokPwd, (String)null);
   }

   public SessionItem createSession(String hokPwd, String encryptionPwd) throws TechnicalConnectorException {
      isEidPresent();
      this.populateConfigWithEidFields();
      this.loadIdentificationKeys((String)null, true);
      this.loadHolderOfKeyKeys(hokPwd, true);
      this.loadEncryptionKeys(encryptionPwd, true);
      return this.initSession();
   }

   public SessionItem createFallbackSession(String hokPwd) throws TechnicalConnectorException {
      return this.createFallbackSession(hokPwd, (String)null);
   }

   public SessionItem createFallbackSession(String hokPwd, String encryptionPwd) throws TechnicalConnectorException {
      return this.createFallbackSession(hokPwd, hokPwd, encryptionPwd);
   }

   public SessionItem createFallbackSession(String idnetPwd, String hokPwd, String encryptionPwd) throws TechnicalConnectorException {
      this.loadIdentificationKeys(idnetPwd, false);
      this.loadHolderOfKeyKeys(hokPwd, false);
      this.loadEncryptionKeys(encryptionPwd, false);
      return this.initSession();
   }

   public SessionItem getSession() {
      if (this.session == null) {
         this.session = new SessionItemImpl();
      }

      return this.session;
   }

   public void unloadSession() {
      this.session = new SessionItemImpl();
      Iterator i$ = this.cacheService.iterator();

      while(i$.hasNext()) {
         SessionServiceWithCache serviceWithCache = (SessionServiceWithCache)i$.next();
         serviceWithCache.flushCache();
      }

   }

   public boolean hasValidSession() throws SessionManagementException {
      LOG.debug("Checking if session exists and if session is valid...");
      if (this.getSession() != null && this.getSession().getSAMLToken() != null) {
         SAMLToken samlToken = this.getSession().getSAMLToken();
         DateTime end = SAMLHelper.getNotOnOrAfterCondition(samlToken.getAssertion());
         boolean valid = end.isAfterNow();
         if (!valid) {
            valid = this.renewSession(samlToken);
         }

         LOG.debug("Session found, valid: " + valid + ". (Valid until:" + DateUtils.printDateTime(end) + ")");
         return valid;
      } else {
         LOG.debug("No Session found");
         return false;
      }
   }

   private boolean renewSession(SAMLToken samlToken) throws SessionManagementException {
      boolean valid = false;
      Iterator i$ = this.cacheService.iterator();

      while(i$.hasNext()) {
         SessionServiceWithCache serviceWithCache = (SessionServiceWithCache)i$.next();
         serviceWithCache.flushCache();
      }

      try {
         if (!valid && this.session.getHeaderCredential() != null && this.config.getBooleanProperty("sessionmanager.activate.autorenew", false).booleanValue()) {
            Object var10 = mutex;
            synchronized(mutex) {
               if (!valid) {
                  LOG.debug("Trying to renew existing session.");
                  STSService sts = STSServiceFactory.getInstance();
                  Element assertion = sts.renewToken(this.session.getHeaderCredential(), this.session.getHolderOfKeyCredential(), samlToken.getAssertion(), this.config.getIntegerProperty("sessionmanager.validity.token", Integer.valueOf(24)).intValue());
                  SAMLToken token = SAMLTokenFactory.getInstance().createSamlToken(assertion, this.session.getHolderOfKeyCredential());
                  this.getSession().setSAMLToken(token);
                  valid = true;
               }
            }
         }

         return valid;
      } catch (TechnicalConnectorException var9) {
         LOG.error("Autorenew failed: " + var9.getMessage());
         throw new SessionManagementException(SessionManagementExceptionValues.ERROR_GENERAL, var9, new Object[]{var9.getMessage()});
      }
   }

   private void loadIdentificationKeys(String pwd, boolean eidonly) throws TechnicalConnectorException {
      char[] password = pwd == null ? ArrayUtils.EMPTY_CHAR_ARRAY : pwd.toCharArray();
      if (this.cache.containsKey("identification")) {
         this.session.setHeaderCredential(new KeyStoreCredential((KeyStore)this.cache.get("identification"), this.config.getProperty("sessionmanager.identification.alias", "authentication"), pwd));
      } else if (pwd == null && eidonly) {
         this.session.setHeaderCredential(BeIDCredential.getInstance("session", "Authentication"));
      } else {
         if (pwd == null && !this.config.getBooleanProperty("sessionmanager.identification.emptypassword", false).booleanValue()) {
            return;
         }

         try {
            String pathKeystore = this.config.getProperty("sessionmanager.identification.keystore");
            String privateKeyAlias = this.config.getProperty("sessionmanager.identification.alias", "authentication");
            KeyStoreInfo ksInfo = new KeyStoreInfo(pathKeystore, password, privateKeyAlias, password);
            Credential headerCred = new KeyStoreCredential(ksInfo);
            this.session.setHeaderCredential(headerCred);
         } catch (Exception var10) {
            LOG.error(var10.getClass().getSimpleName() + ": Could not load HolderOfkey keys. Reason:" + var10.getMessage());
            throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_GENERAL, var10, new Object[]{"Could not load decryption keys"});
         }
      }

   }

   private void loadHolderOfKeyKeys(String pwd, boolean eidonly) throws TechnicalConnectorException {
      LOG.debug("Loading HolderOfKeyKeys");
      char[] password = pwd == null ? ArrayUtils.EMPTY_CHAR_ARRAY : pwd.toCharArray();
      if (this.cache.containsKey("holderofkey")) {
         KeyStore hokstore = (KeyStore)this.cache.get("holderofkey");
         this.session.setHolderOfKeyCredential(new KeyStoreCredential(hokstore, this.config.getProperty("sessionmanager.holderofkey.alias", "authentication"), pwd));
         this.session.setHolderOfKeyPrivateKeys(KeyManager.getDecryptionKeys(hokstore, password));
      } else if (pwd == null && eidonly) {
         Credential authCredential = BeIDCredential.getInstance("session", "Authentication");
         Map<String, PrivateKey> authPK = new HashMap();
         authPK.put("authentication", authCredential.getPrivateKey());
         this.session.setHolderOfKeyCredential(authCredential);
         this.session.setHolderOfKeyPrivateKeys(authPK);
      } else {
         if (pwd == null && !this.config.getBooleanProperty("sessionmanager.holderofkey.emptypassword", false).booleanValue()) {
            return;
         }

         try {
            String pathKeystore = this.config.getProperty("sessionmanager.holderofkey.keystore");
            String privateKeyAlias = this.config.getProperty("sessionmanager.holderofkey.alias", "authentication");
            KeyStoreInfo ksInfo = new KeyStoreInfo(pathKeystore, password, privateKeyAlias, password);
            KeyStoreManager encryptionKeystoreManager = new KeyStoreManager(ksInfo);
            Map<String, PrivateKey> hokPrivateKeys = KeyManager.getDecryptionKeys(encryptionKeystoreManager.getKeyStore(), ksInfo.getPrivateKeyPassword());
            this.session.setHolderOfKeyCredential(new KeyStoreCredential(ksInfo));
            this.session.setHolderOfKeyPrivateKeys(hokPrivateKeys);
            fetchEtk(KeyDepotManager.EncryptionTokenType.HOLDER_OF_KEY, hokPrivateKeys, this.config);
         } catch (Exception var11) {
            throw translate(var11, "HolderOfKey");
         }
      }

   }

   private static TechnicalConnectorException translate(Exception e, String msg) throws TechnicalConnectorException {
      if (e instanceof TechnicalConnectorException) {
         return (TechnicalConnectorException)e;
      } else {
         LOG.error(e.getClass().getSimpleName() + ": Could not load " + msg + " keys. Reason:" + e.getMessage());
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_GENERAL, e, new Object[]{"Could not load " + msg + " keys"});
      }
   }

   private static void fetchEtk(KeyDepotManager.EncryptionTokenType type, Map<String, PrivateKey> privateKeys, Configuration config) throws TechnicalConnectorException {
      throw new IllegalStateException("Session use is not alowed");
   }

   public void loadEncryptionKeys(String encryptionPwd) throws TechnicalConnectorException {
      this.loadEncryptionKeys(encryptionPwd, false);
   }

   private void loadEncryptionKeys(String pwd, boolean eidonly) throws TechnicalConnectorException {
      LOG.debug("Loading EncryptionKeys");
      char[] password = pwd == null ? ArrayUtils.EMPTY_CHAR_ARRAY : pwd.toCharArray();
      if (this.cache.containsKey("encryption")) {
         KeyStore hokstore = (KeyStore)this.cache.get("encryption");
         this.session.setHolderOfKeyCredential(new KeyStoreCredential(hokstore, this.config.getProperty("sessionmanager.encryption.alias", "authentication"), pwd));
         this.session.setHolderOfKeyPrivateKeys(KeyManager.getDecryptionKeys(hokstore, password));
      } else if (pwd == null && eidonly) {
         Credential authCredential = BeIDCredential.getInstance("session", "Authentication");
         Map<String, PrivateKey> authPK = new HashMap();
         authPK.put("authentication", authCredential.getPrivateKey());
         this.session.setEncryptionCredential(authCredential);
         this.session.setEncryptionPrivateKeys(authPK);
      } else {
         if (pwd == null && !this.config.getBooleanProperty("sessionmanager.encryption.emptypassword", false).booleanValue()) {
            return;
         }

         try {
            String pathKeystore = this.config.getProperty("sessionmanager.encryption.keystore");
            String privateKeyAlias = this.config.getProperty("sessionmanager.encryption.alias", "authentication");
            KeyStoreInfo ksInfo = new KeyStoreInfo(pathKeystore, password, privateKeyAlias, password);
            KeyStoreManager encryptionKeystoreManager = new KeyStoreManager(ksInfo);
            Map<String, PrivateKey> encryptionPrivateKeys = KeyManager.getDecryptionKeys(encryptionKeystoreManager.getKeyStore(), ksInfo.getPrivateKeyPassword());
            this.session.setEncryptionCredential(new KeyStoreCredential(ksInfo));
            this.session.setEncryptionPrivateKeys(encryptionPrivateKeys);
            fetchEtk(KeyDepotManager.EncryptionTokenType.ENCRYPTION, encryptionPrivateKeys, this.config);
         } catch (Exception var11) {
            throw translate(var11, "EncrytionKeys");
         }
      }

   }

   public void registerSessionService(SessionServiceWithCache serviceWithCache) {
      if (this.cacheService == null) {
         this.cacheService = new ArrayList();
      }

      this.cacheService.add(serviceWithCache);
   }

   private SessionItem initSession() throws TechnicalConnectorException {
      int validity = this.config.getIntegerProperty("sessionmanager.validity.token", Integer.valueOf(24)).intValue();
      LOG.debug("Requesting SAML-Token from STS...");
      SAMLToken token = this.getToken(validity, this.session.getHeaderCredential(), this.session.getHolderOfKeyCredential());
      LOG.debug("SAML Token received");
      LOG.debug("Loading SAML token into session...");
      this.session.setSAMLToken(token);
      LOG.debug("Session created!");
      return this.session;
   }

   protected SAMLToken getToken(int validityHours, Credential headerCredential, Credential bodyCredential) throws TechnicalConnectorException {
      Validate.notNull(headerCredential, "Parameter headerCredential is not nullable");
      Validate.notNull(bodyCredential, "Parameter bodyCredential is not nullable");
      List<SAMLAttributeDesignator> designators = SAMLConfigHelper.getSAMLAttributeDesignators("sessionmanager.samlattributedesignator");
      List<SAMLAttribute> attributes = SAMLConfigHelper.getSAMLAttributes("sessionmanager.samlattribute");
      STSService sts = STSServiceFactory.getInstance();
      Element assertion = sts.getToken(headerCredential, bodyCredential, attributes, designators, "urn:oasis:names:tc:SAML:1.0:cm:holder-of-key", validityHours);
      return SAMLTokenFactory.getInstance().createSamlToken(assertion, bodyCredential);
   }

   private void populateConfigWithEidFields() throws TechnicalConnectorException {
      if (!this.config.getBooleanProperty("sessionmanager.disable.eiddiscovery", false).booleanValue()) {
         String userInss = this.config.getProperty("user.inss");
         if (StringUtils.isEmpty(userInss)) {
            String ssin = (new CertificateParser(BeIDCredential.getInstance("session", "Authentication").getCertificate())).getId();
            this.config.setProperty("user.inss", ssin);
         }
      }

   }

   private static void isEidPresent() throws TechnicalConnectorException {
      BeIDCardFactory.getBeIDCard();
   }

   public void setKeyStore(Map<String, KeyStore> keystores) {
      this.cache.clear();
      this.cache.putAll(keystores);
   }

   // $FF: synthetic method
   SessionManagerImpl(SessionManagerImpl.SyntheticClass_1 x0) {
      this();
   }

   // $FF: synthetic class
   static class SyntheticClass_1 {
   }

   private static enum SessionManagerImplSingleton {
      INSTANCE;

      private volatile SessionManagerImpl manager = new SessionManagerImpl();

      public SessionManagerImpl getManagerImpl() {
         return this.manager;
      }
   }
}
