package org.taktik.connector.technical.service.kgss;

import org.taktik.connector.technical.cache.Cache;
import org.taktik.connector.technical.cache.CacheFactory;
import org.taktik.connector.technical.config.ConfigFactory;
import org.taktik.connector.technical.config.ConfigValidator;
import org.taktik.connector.technical.exception.TechnicalConnectorException;
import org.taktik.connector.technical.service.keydepot.KeyDepotManagerFactory;
import org.taktik.connector.technical.service.kgss.domain.KeyResult;
import org.taktik.connector.technical.service.kgss.impl.KgssServiceImpl;
import org.taktik.connector.technical.session.Session;
import org.taktik.connector.technical.session.SessionItem;
import org.taktik.connector.technical.session.SessionServiceWithCache;
import org.taktik.connector.technical.utils.IdentifierType;
import be.fgov.ehealth.etee.kgss._1_0.protocol.CredentialType;
import be.fgov.ehealth.etee.kgss._1_0.protocol.GetKeyRequestContent;
import be.fgov.ehealth.etee.kgss._1_0.protocol.GetNewKeyRequestContent;
import java.util.List;
import org.bouncycastle.util.encoders.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class KgssManager implements SessionServiceWithCache {
   public static final String PROP_KGSS_IDENTIFIER_APPLICATIONID = "org.taktik.connector.technical.service.kgss.identifier.applicationid";
   private static final String PROP_KGSS_IDENTIFIER_ID = "org.taktik.connector.technical.service.kgss.identifier.value";
   private static final String PROP_KGSS_IDENTIFIER_SUBTYPE = "org.taktik.connector.technical.service.kgss.identifier.subtype";
   private static final String PROP_KGSS_IDENTIFIER_TYPE = "org.taktik.connector.technical.service.kgss.identifier.type";
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
      this.cache = CacheFactory.newInstance(CacheFactory.CacheType.MEMORY);
      this.service = service;
      Session.getInstance().registerSessionService(this);
   }

   public KeyResult get(String keyId, byte[] myEtk) throws TechnicalConnectorException {
      if (!this.cache.containsKey(keyId)) {
         LOG.debug("keyCache does not contain key, getting the key from KGSS Web Service: " + keyId);
         KeyResult key = this.getKeyFromKgss(keyId, myEtk);
         this.cache.put(keyId, key);
         LOG.debug("Added key to cache: " + keyId);
      }

      LOG.debug("returning key from cache: " + keyId);
      return (KeyResult)this.cache.get(keyId);
   }

   public void add(List<CredentialType> allowedReaders, byte[] myEtk) throws TechnicalConnectorException {
      LOG.debug("Getting new key from KGSS Web Service");
      KeyResult key = this.getNewKeyFromKgss(allowedReaders, myEtk);
      this.cache.put(key.getKeyId(), key);
      LOG.debug("Added new key to cache: " + key.getKeyId());
   }

   public void add(String cacheId, List<CredentialType> allowedReaders, byte[] myEtk) throws TechnicalConnectorException {
      LOG.debug("Getting new key from KGSS Web Service: " + cacheId);
      KeyResult key = this.getNewKeyFromKgss(allowedReaders, myEtk);
      this.cache.put(cacheId, key);
      LOG.debug("Added new key to cache: " + cacheId);
   }

   public KeyResult remove(String key) {
      LOG.debug("removing key from cache: " + key);
      KeyResult result = (KeyResult)this.cache.get(key);
      this.cache.remove(key);
      return result;
   }

   public boolean containsKey(String key) {
      return this.cache.containsKey(key);
   }

   protected KeyResult getKeyFromKgss(String keyId, byte[] myEtk) throws TechnicalConnectorException {
      LOG.debug("KeyIdentifier : " + keyId);
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
      String identifierType = config.getProperty("org.taktik.connector.technical.service.kgss.identifier.type", "CBE");
      String identifierSubType = config.getProperty("org.taktik.connector.technical.service.kgss.identifier.subtype");
      IdentifierType idType = IdentifierType.lookup(identifierType, identifierSubType, 48);
      Long id = config.getLongProperty("org.taktik.connector.technical.service.kgss.identifier.value", 809394427L);
      String appId = config.getProperty("org.taktik.connector.technical.service.kgss.identifier.applicationid", "KGSS");
      return KeyDepotManagerFactory.getKeyDepotManager().getEtk(idType, id, appId).getEncoded();
   }

   public void flushCache() {
      this.cache.clear();
   }

   public void setKgssService(KgssService service) {
      this.service = service;
   }

   // $FF: synthetic method
   KgssManager(KgssService x0, KgssManager.SyntheticClass_1 x1) {
      this(x0);
   }

   static {
      instance = KgssManager.KgssManagerSingleton.INSTANCE.getKgssManager();
   }

   // $FF: synthetic class
   static class SyntheticClass_1 {
   }

   private static enum KgssManagerSingleton {
      INSTANCE;

      private KgssManager instance = new KgssManager(new KgssServiceImpl());

      public KgssManager getKgssManager() {
         return this.instance;
      }
   }
}
