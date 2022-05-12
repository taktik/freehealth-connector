package be.ehealth.technicalconnector.session.impl;

import be.ehealth.technicalconnector.beid.BeIDFactory;
import be.ehealth.technicalconnector.cache.Cache;
import be.ehealth.technicalconnector.cache.CacheFactory;
import be.ehealth.technicalconnector.config.ConfigFactory;
import be.ehealth.technicalconnector.config.ConfigValidator;
import be.ehealth.technicalconnector.config.Configuration;
import be.ehealth.technicalconnector.exception.SessionManagementException;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.exception.TechnicalConnectorExceptionValues;
import be.ehealth.technicalconnector.service.etee.domain.EncryptionToken;
import be.ehealth.technicalconnector.service.keydepot.KeyDepotManager;
import be.ehealth.technicalconnector.service.keydepot.KeyDepotManagerFactory;
import be.ehealth.technicalconnector.service.sts.SAMLTokenFactory;
import be.ehealth.technicalconnector.service.sts.STSService;
import be.ehealth.technicalconnector.service.sts.STSServiceFactory;
import be.ehealth.technicalconnector.service.sts.domain.SAMLAttribute;
import be.ehealth.technicalconnector.service.sts.domain.SAMLAttributeDesignator;
import be.ehealth.technicalconnector.service.sts.security.Credential;
import be.ehealth.technicalconnector.service.sts.security.KeyStoreInfo;
import be.ehealth.technicalconnector.service.sts.security.SAMLToken;
import be.ehealth.technicalconnector.service.sts.security.impl.BeIDCredential;
import be.ehealth.technicalconnector.service.sts.security.impl.KeyStoreCredential;
import be.ehealth.technicalconnector.service.sts.utils.SAMLConfigHelper;
import be.ehealth.technicalconnector.service.sts.utils.SAMLHelper;
import be.ehealth.technicalconnector.session.SessionItem;
import be.ehealth.technicalconnector.session.SessionManager;
import be.ehealth.technicalconnector.session.SessionServiceWithCache;
import be.ehealth.technicalconnector.session.renew.RenewStrategy;
import be.ehealth.technicalconnector.session.renew.RenewStrategyFactory;
import be.ehealth.technicalconnector.utils.CertificateParser;
import be.ehealth.technicalconnector.utils.DateUtils;
import be.ehealth.technicalconnector.utils.KeyStoreManager;
import be.fgov.ehealth.etee.crypto.utils.KeyManager;
import be.fgov.ehealth.technicalconnector.bootstrap.bcp.domain.CacheInformation;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Element;

public final class SessionManagerImpl implements SessionManager {
   public static final String PROP_FETCH_ETK = "sessionmanager.fetch.etk";
   protected static final String AUTHENTICATION_ALIAS = "authentication";
   private static final String EID_SESSION = "session";
   private static final Logger LOG = LoggerFactory.getLogger(SessionManagerImpl.class);
   private static final String PROP_SESSIONMNG_SAMLATTRIBUTEDESIGNATOR = "sessionmanager.samlattributedesignator";
   private static final String PROP_SESSIONMNG_SAMLATTRIBUTE = "sessionmanager.samlattribute";
   private static final String PROP_KEYSTORE_IDNT_NAME = "sessionmanager.identification.keystore";
   private static final String PROP_KEYSTORE_IDNT_ALIAS = "sessionmanager.identification.alias";
   private static final String PROP_KEYSTORE_HOK_NAME = "sessionmanager.holderofkey.keystore";
   private static final String PROP_KEYSTORE_HOK_ALIAS = "sessionmanager.holderofkey.alias";
   private static final String PROP_KEYSTORE_ENC_NAME = "sessionmanager.encryption.keystore";
   private static final String PROP_KEYSTORE_ENC_ALIAS = "sessionmanager.encryption.alias";
   private static final String PROP_VALIDITY_TOKEN = "sessionmanager.validity.token";
   private static final String PROP_DISABLE_EID_DISCOVERY = "sessionmanager.disable.eiddiscovery";
   private static final String PROP_EMPTY_PASSWORD_HOK = "sessionmanager.holderofkey.emptypassword";
   private static final String PROP_EMPTY_PASSWORD_ENCRYPTION = "sessionmanager.encryption.emptypassword";
   private static final long DEFAULT_VALIDITY_TOKEN = 24L;
   private List<SessionServiceWithCache> cacheService;
   private ConfigValidator config;
   private Cache<String, KeyStore> cache;
   private Semaphore mutex;
   private SessionItem session;

   private SessionManagerImpl() {
      this.cacheService = new ArrayList();
      this.mutex = new Semaphore(1);
      this.session = new SessionItemImpl();
   }

   public static SessionManagerImpl getInstance() {
      return SessionManagerImpl.SessionManagerImplSingleton.INSTANCE.getManagerImpl();
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
      if (config.getBooleanProperty("sessionmanager.fetch.etk", Boolean.TRUE)) {
         EncryptionToken etk = null;

         try {
            etk = KeyDepotManagerFactory.getKeyDepotManager().getETK(type);
         } catch (Exception var5) {
            LOG.warn("Unable to prefetch ETK", var5);
         }

         if (etk != null && !privateKeys.containsKey(etk.getCertificate().getSerialNumber().toString(10))) {
            throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_CONFIG, new Object[]{"The certificate from the ETK don't match with the one in the encryption keystore"});
         }
      }

   }

   private static void isEidPresent() throws TechnicalConnectorException {
      BeIDFactory.getBeIDInfo("eid-present", false);
   }

   private void bootstrap() {
      if (this.config == null || this.cache == null) {
         try {
            this.mutex.acquire();
            if (this.cache == null) {
               this.bootstrapCache();
            }

            if (this.config == null) {
               this.bootstrapConfig();
            }
         } catch (InterruptedException var5) {
         } finally {
            this.mutex.release();
         }
      }

   }

   private void bootstrapConfig() {
      this.config = ConfigFactory.getConfigValidatorFor("sessionmanager.samlattribute", "sessionmanager.samlattributedesignator");
   }

   private void bootstrapCache() {
      this.cache = CacheFactory.newInstance(CacheFactory.CacheType.MEMORY, "sessionmanager-keystore", CacheInformation.ExpiryType.NONE, (Duration)null);
   }

   public void loadSession(SAMLToken token, String hokPwd) throws TechnicalConnectorException {
      this.loadSession(token, hokPwd, (String)null);
   }

   public void loadSession(SAMLToken token, String hokPwd, String encryptionPwd) throws TechnicalConnectorException {
      this.bootstrap();
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
      this.bootstrap();
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
      this.bootstrap();
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
      Iterator var1 = this.cacheService.iterator();

      while(var1.hasNext()) {
         SessionServiceWithCache serviceWithCache = (SessionServiceWithCache)var1.next();
         serviceWithCache.flushCache();
      }

   }

   public boolean hasValidSession() throws SessionManagementException {
      LOG.debug("Checking if session exists and if session is valid...");
      if (this.getSession() != null && this.getSession().getSAMLToken() != null) {
         RenewStrategy renewStrategy = RenewStrategyFactory.get();
         renewStrategy.renew(this.session);
         DateTime end = SAMLHelper.getNotOnOrAfterCondition(this.getSession().getSAMLToken().getAssertion());
         boolean valid = end.isAfterNow();
         LOG.debug("Session found, valid: {}. (Valid until:{})", valid, DateUtils.printDateTime(end));
         return valid;
      } else {
         LOG.debug("No Session found");
         return false;
      }
   }

   private void loadIdentificationKeys(String pwd, boolean eidonly) throws TechnicalConnectorException {
      char[] password = pwd == null ? ArrayUtils.EMPTY_CHAR_ARRAY : pwd.toCharArray();
      if (this.cache.containsKey("identification")) {
         this.session.setHeaderCredential(new KeyStoreCredential((KeyStore)this.cache.get("identification"), this.config.getProperty("sessionmanager.identification.alias", "authentication"), pwd));
      } else if (pwd == null && eidonly) {
         this.session.setHeaderCredential(BeIDCredential.getInstance("session", "Authentication"));
      } else {
         if (pwd == null && !this.config.getBooleanProperty("sessionmanager.identification.emptypassword", false)) {
            return;
         }

         try {
            String pathKeystore = this.config.getProperty("sessionmanager.identification.keystore");
            String privateKeyAlias = this.config.getProperty("sessionmanager.identification.alias", "authentication");
            KeyStoreInfo ksInfo = new KeyStoreInfo(pathKeystore, password, privateKeyAlias, password);
            Credential headerCred = new KeyStoreCredential(ksInfo);
            this.session.setHeaderCredential(headerCred);
         } catch (Exception var10) {
            LOG.error("{} : Could not load HolderOfkey keys. Reason:{}", var10.getClass().getSimpleName(), var10.getMessage());
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
         if (pwd == null && !this.config.getBooleanProperty("sessionmanager.holderofkey.emptypassword", false)) {
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
         if (pwd == null && !this.config.getBooleanProperty("sessionmanager.encryption.emptypassword", false)) {
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
      this.cacheService.add(serviceWithCache);
      RenewStrategy strategy = RenewStrategyFactory.get();
      this.cacheService.add(strategy);
      strategy.register(serviceWithCache);
   }

   private SessionItem initSession() throws TechnicalConnectorException {
      be.ehealth.technicalconnector.config.domain.Duration validity = this.config.getDurationProperty("sessionmanager.validity.token", this.config.getLongProperty("sessionmanager.validity.token", 24L), TimeUnit.HOURS);
      LOG.debug("Requesting SAML-Token from STS...");
      SAMLToken token = this.getToken(validity, this.session.getHeaderCredential(), this.session.getHolderOfKeyCredential());
      LOG.debug("SAML Token received");
      LOG.debug("Loading SAML token into session...");
      this.session.setSAMLToken(token);
      LOG.debug("Session created!");
      LOG.debug("Registering session to RenewStrategy");
      RenewStrategyFactory.get().renew(this.session);
      return this.session;
   }

   protected SAMLToken getToken(be.ehealth.technicalconnector.config.domain.Duration validity, Credential headerCredential, Credential bodyCredential) throws TechnicalConnectorException {
      Validate.notNull(headerCredential, "Parameter headerCredential is not nullable", new Object[0]);
      Validate.notNull(bodyCredential, "Parameter bodyCredential is not nullable", new Object[0]);
      List<SAMLAttributeDesignator> designators = SAMLConfigHelper.getSAMLAttributeDesignators("sessionmanager.samlattributedesignator");
      List<SAMLAttribute> attributes = SAMLConfigHelper.getSAMLAttributes("sessionmanager.samlattribute");
      STSService sts = STSServiceFactory.getInstance();
      Element assertion = sts.getToken(headerCredential, bodyCredential, attributes, designators, "urn:oasis:names:tc:SAML:1.0:cm:holder-of-key", validity);
      return SAMLTokenFactory.getInstance().createSamlToken(assertion, bodyCredential);
   }

   private void populateConfigWithEidFields() throws TechnicalConnectorException {
      if (!this.config.getBooleanProperty("sessionmanager.disable.eiddiscovery", false)) {
         String userInss = this.config.getProperty("user.inss");
         if (StringUtils.isEmpty(userInss)) {
            String ssin = (new CertificateParser(BeIDCredential.getInstance("session", "Authentication").getCertificate())).getId();
            this.config.setProperty("user.inss", ssin);
         }
      }

   }

   public void setKeyStore(Map<String, KeyStore> keystores) {
      this.cache.clear();
      this.cache.putAll(keystores);
   }

   private static enum SessionManagerImplSingleton {
      INSTANCE;

      private volatile SessionManagerImpl manager = new SessionManagerImpl();

      private SessionManagerImplSingleton() {
      }

      public SessionManagerImpl getManagerImpl() {
         return this.manager;
      }
   }
}
