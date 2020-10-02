package be.fgov.ehealth.technicalconnector.bootstrap.bcp.domain;

import be.ehealth.technicalconnector.cache.Cache;
import be.ehealth.technicalconnector.cache.CacheFactory;
import org.joda.time.Duration;

public class CacheInformation {
   private CacheInformation.CacheType cacheType;
   private final CacheInformation.KeyTransformType keyTransformType;
   private final String keyTransfromLocation;
   private Cache<String, String> cache;

   public CacheInformation(String serviceName, CacheInformation.CacheType cacheType, CacheInformation.KeyTransformType keyTransformType, String keyTransfromLocation, CacheInformation.ExpiryType expiryType, Duration expiryDuration) {
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

   public CacheInformation.KeyTransformType getKeyTransformType() {
      return this.keyTransformType;
   }

   public boolean isKeyTransformType(CacheInformation.KeyTransformType keyTransformType) {
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
