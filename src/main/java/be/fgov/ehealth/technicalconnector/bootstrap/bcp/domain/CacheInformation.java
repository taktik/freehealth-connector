package be.fgov.ehealth.technicalconnector.bootstrap.bcp.domain;

import org.taktik.connector.technical.cache.Cache;
import org.taktik.connector.technical.cache.CacheFactory;
import org.taktik.connector.technical.handler.CacheFeederHandler;
import org.joda.time.Duration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CacheInformation {
   private CacheType cacheType;
   private final KeyTransformType keyTransformType;
   private final String keyTransfromLocation;
   private static final Logger LOG = LoggerFactory.getLogger(CacheFeederHandler.class);
   private Cache<String, String> cache;

   public CacheInformation(String serviceName, CacheType cacheType, KeyTransformType keyTransformType, String keyTransfromLocation, ExpiryType expiryType, Duration expiryDuration) {
      this.cacheType = cacheType;
      this.keyTransformType = keyTransformType;
      this.keyTransfromLocation = keyTransfromLocation;
      this.cache = CacheFactory.newInstance(CacheFactory.CacheType.PERSISTENT, serviceName, expiryType, expiryDuration);
   }

   public boolean isActivated() {
      return true;
   }

   public Cache<String, String> getCache() {
      return this.cache;
   }

   public boolean isXsltKeyTransform() {
      return this.keyTransfromLocation != null;
   }

   public KeyTransformType getKeyTransformType() {
      return this.keyTransformType;
   }

   public boolean isKeyTransformType(KeyTransformType keyTransformType) {
      return this.keyTransformType == keyTransformType;
   }

   public String getKeyTransfromLocation() {
      return this.keyTransfromLocation;
   }

   public String toString() {
      return "CacheInformation{cacheType=" + this.cacheType + ", keyTransformType=" + this.keyTransformType + ", keyTransfromLocation='" + this.keyTransfromLocation + '\'' + ", cache=" + this.cache + '}';
   }

   public static enum ExpiryType {
      NONE,
      TTL;
   }

   public static enum KeyTransformType {
      XSLT;
   }

   public static enum CacheType {
      ENDPOINT_FIRST,
      CACHE_FIRST;
   }
}
