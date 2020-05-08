package be.ehealth.technicalconnector.service.kgss;

import be.ehealth.technicalconnector.cache.Cache;
import be.ehealth.technicalconnector.cache.CacheFactory;
import be.ehealth.technicalconnector.config.ConfigFactory;
import be.ehealth.technicalconnector.config.ConfigValidator;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.service.keydepot.KeyDepotManagerFactory;
import be.ehealth.technicalconnector.service.kgss.domain.KeyResult;
import be.ehealth.technicalconnector.service.kgss.impl.KgssServiceImpl;
import be.ehealth.technicalconnector.session.Session;
import be.ehealth.technicalconnector.session.SessionItem;
import be.ehealth.technicalconnector.session.SessionServiceWithCache;
import be.ehealth.technicalconnector.utils.IdentifierType;
import be.fgov.ehealth.etee.kgss._1_0.protocol.CredentialType;
import be.fgov.ehealth.etee.kgss._1_0.protocol.GetKeyRequestContent;
import be.fgov.ehealth.etee.kgss._1_0.protocol.GetNewKeyRequestContent;
import be.fgov.ehealth.technicalconnector.bootstrap.bcp.domain.CacheInformation;
import java.util.List;
import org.bouncycastle.util.encoders.Base64;
import org.joda.time.Duration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class KgssManager implements SessionServiceWithCache {
   public static final String PROP_KGSS_IDENTIFIER_APPLICATIONID = "be.ehealth.technicalconnector.service.kgss.identifier.applicationid";
   private static final String PROP_KGSS_IDENTIFIER_ID = "be.ehealth.technicalconnector.service.kgss.identifier.value";
   private static final String PROP_KGSS_IDENTIFIER_SUBTYPE = "be.ehealth.technicalconnector.service.kgss.identifier.subtype";
   private static final String PROP_KGSS_IDENTIFIER_TYPE = "be.ehealth.technicalconnector.service.kgss.identifier.type";
   private static final String KGSS_TYPE = "CBE";
   private static final long KGSS_IDENTIFIER_VALUE = 809394427L;
   private static final String KGSS_APPLICATION_ID = "KGSS";
   private static final Logger LOG = LoggerFactory.getLogger(KgssManager.class);
   private Cache<String, KeyResult> cache;
   private static KgssManager instance;
   private KgssService service;

   public static KgssManager getInstance() {
      return instance;
   }

   public static KgssManager getInstance(KgssService kgssService) {
      instance.setKgssService(kgssService);
      return instance;
   }

   private KgssManager(KgssService service) {
      this.cache = CacheFactory.newInstance(CacheFactory.CacheType.MEMORY, "kgss-manager", CacheInformation.ExpiryType.NONE, (Duration)null);
      this.service = service;
      Session.getInstance().registerSessionService(this);
   }

   public KeyResult get(String keyId, byte[] myEtk) throws TechnicalConnectorException {
      if (!this.cache.containsKey(keyId)) {
         LOG.debug("keyCache does not contain key, getting the key from KGSS Web Service: {}", keyId);
         KeyResult key = this.getKeyFromKgss(keyId, myEtk);
         this.cache.put(keyId, key);
         LOG.debug("Added key to cache: {}", keyId);
      }

      LOG.debug("returning key from cache: {}", keyId);
      return (KeyResult)this.cache.get(keyId);
   }

   public void add(List<CredentialType> allowedReaders, byte[] myEtk) throws TechnicalConnectorException {
      LOG.debug("Getting new key from KGSS Web Service");
      KeyResult key = this.getNewKeyFromKgss(allowedReaders, myEtk);
      this.cache.put(key.getKeyId(), key);
      LOG.debug("Added new key to cache: {}", key.getKeyId());
   }

   public void add(String cacheId, List<CredentialType> allowedReaders, byte[] myEtk) throws TechnicalConnectorException {
      LOG.debug("Getting new key from KGSS Web Service: {}", cacheId);
      KeyResult key = this.getNewKeyFromKgss(allowedReaders, myEtk);
      this.cache.put(cacheId, key);
      LOG.debug("Added new key to cache: {}", cacheId);
   }

   public KeyResult remove(String key) {
      LOG.debug("removing key from cache: {}", key);
      KeyResult result = (KeyResult)this.cache.get(key);
      this.cache.remove(key);
      return result;
   }

   public boolean containsKey(String key) {
      return this.cache.containsKey(key);
   }

   protected KeyResult getKeyFromKgss(String keyId, byte[] myEtk) throws TechnicalConnectorException {
      LOG.debug("KeyIdentifier : {}", keyId);
      GetKeyRequestContent req = new GetKeyRequestContent();
      req.setETK(myEtk);
      req.setKeyIdentifier(Base64.decode(keyId.getBytes()));
      SessionItem session = Session.getInstance().getSession();
      return this.service.getKey(req, getETKOfKGSS(), session);
   }

   public KeyResult getNewKeyFromKgss(List<CredentialType> allowedReaders, byte[] myEtk) throws TechnicalConnectorException {
      GetNewKeyRequestContent req = new GetNewKeyRequestContent();
      req.setETK(myEtk);
      req.getAllowedReaders().addAll(allowedReaders);
      return this.service.getNewKey(req, getETKOfKGSS());
   }

   private static byte[] getETKOfKGSS() throws TechnicalConnectorException {
      ConfigValidator config = ConfigFactory.getConfigValidator();
      String identifierType = config.getProperty("be.ehealth.technicalconnector.service.kgss.identifier.type", "CBE");
      String identifierSubType = config.getProperty("be.ehealth.technicalconnector.service.kgss.identifier.subtype");
      IdentifierType idType = IdentifierType.lookup(identifierType, identifierSubType, 48);
      Long id = config.getLongProperty("be.ehealth.technicalconnector.service.kgss.identifier.value", 809394427L);
      String appId = config.getProperty("be.ehealth.technicalconnector.service.kgss.identifier.applicationid", "KGSS");
      return KeyDepotManagerFactory.getKeyDepotManager().getEtk(idType, id, appId).getEncoded();
   }

   public void flushCache() {
      this.cache.clear();
   }

   public void setKgssService(KgssService service) {
      this.service = service;
   }

   // $FF: synthetic method
   KgssManager(KgssService x0, Object x1) {
      this(x0);
   }

   static {
      instance = KgssManager.KgssManagerSingleton.INSTANCE.getKgssManager();
   }

   private static enum KgssManagerSingleton {
      INSTANCE;

      private KgssManager instance = new KgssManager(new KgssServiceImpl());

      public KgssManager getKgssManager() {
         return this.instance;
      }
   }
}
