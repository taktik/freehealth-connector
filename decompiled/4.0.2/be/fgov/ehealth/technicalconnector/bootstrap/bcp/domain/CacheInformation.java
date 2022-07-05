package be.fgov.ehealth.technicalconnector.bootstrap.bcp.domain;

import be.ehealth.technicalconnector.cache.Cache;
import be.ehealth.technicalconnector.cache.CacheFactory;
import org.joda.time.Duration;

public class CacheInformation {
   private CacheType cacheType;
   private final KeyTransformType keyTransformType;
   private final String keyTransfromLocation;
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

      private ExpiryType() {
      }
   }

   public static enum KeyTransformType {
      XSLT;

      private KeyTransformType() {
      }
   }

   public static enum CacheType {
      ENDPOINT_FIRST,
      CACHE_FIRST;

      private CacheType() {
      }
   }
}
