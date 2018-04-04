package be.ehealth.technicalconnector.beid;

import be.ehealth.technicalconnector.beid.domain.Address;
import be.ehealth.technicalconnector.beid.domain.Identity;
import be.ehealth.technicalconnector.cache.Cache;
import be.ehealth.technicalconnector.cache.CacheFactory;
import be.ehealth.technicalconnector.config.ConfigFactory;
import be.ehealth.technicalconnector.config.ConfigValidator;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.exception.TechnicalConnectorExceptionValues;
import be.ehealth.technicalconnector.mapper.Mapper;
import be.ehealth.technicalconnector.mapper.MapperFactory;
import be.ehealth.technicalconnector.session.Session;
import be.ehealth.technicalconnector.session.SessionServiceWithCache;
import be.fedict.commons.eid.client.BeIDCard;
import be.fedict.commons.eid.client.FileType;
import be.fedict.commons.eid.consumer.tlv.TlvParser;
import org.apache.commons.lang.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class BeIDInfo implements SessionServiceWithCache {
   public static final String PROP_USE_CACHE = "be.ehealth.technicalconnector.beid.beidinfo.cache";
   private static final boolean PROP_USE_CACHE_DEFAULT_VALUE = false;
   private static final Logger LOG = LoggerFactory.getLogger(BeIDInfo.class);
   private static Cache<String, BeIDInfo> cache;
   private Address address;
   private Identity identity;
   private byte[] photo;
   private static Mapper mapper;
   private static ConfigValidator config;

   private BeIDInfo() throws TechnicalConnectorException {
      BeIDCard beIDCard = BeIDCardFactory.getBeIDCard();

      try {
         LOG.debug("processing identity file");
         byte[] identityFile = beIDCard.readFile(FileType.Identity);
         be.fedict.commons.eid.consumer.Identity identityFedict = (be.fedict.commons.eid.consumer.Identity)TlvParser.parse(identityFile, be.fedict.commons.eid.consumer.Identity.class);
         this.identity = (Identity)mapper.map(identityFedict, Identity.class);
         LOG.debug("processing address file");
         byte[] addressFile = beIDCard.readFile(FileType.Address);
         be.fedict.commons.eid.consumer.Address addressFedict = (be.fedict.commons.eid.consumer.Address)TlvParser.parse(addressFile, be.fedict.commons.eid.consumer.Address.class);
         this.address = (Address)mapper.map(addressFedict, Address.class);
         LOG.debug("processing photo file");
         this.photo = beIDCard.readFile(FileType.Photo);
      } catch (Exception var6) {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.BEID_ERROR, var6, new Object[]{var6.getMessage()});
      }

      beIDCard.close();
      Session.getInstance().registerSessionService(this);
   }

   public static BeIDInfo getInstance() throws TechnicalConnectorException {
      return getInstance("DEFAULT", false);
   }

   public static BeIDInfo getInstance(String scope) throws TechnicalConnectorException {
      boolean useCache = config.getBooleanProperty("be.ehealth.technicalconnector.beid.beidinfo.cache", false).booleanValue();
      return getInstance(scope, useCache);
   }

   public static BeIDInfo getInstance(String scope, boolean useCache) throws TechnicalConnectorException {
      if (useCache && cache.containsKey(scope)) {
         LOG.debug("Returning cached instance.");
         return (BeIDInfo)cache.get(scope);
      } else {
         BeIDInfo result = new BeIDInfo();
         if (useCache) {
            cache.put(scope, result);
         }

         return result;
      }
   }

   public Identity getIdentity() {
      return this.identity;
   }

   public Address getAddress() {
      return this.address;
   }

   public byte[] getPhoto() throws TechnicalConnectorException {
      return ArrayUtils.clone(this.photo);
   }

   public void flushCache() {
      cache.clear();
   }

   static {
      cache = CacheFactory.newInstance(CacheFactory.CacheType.MEMORY);
      mapper = MapperFactory.getMapper("dozer/commonseid.xml");
      config = ConfigFactory.getConfigValidator();
   }
}
