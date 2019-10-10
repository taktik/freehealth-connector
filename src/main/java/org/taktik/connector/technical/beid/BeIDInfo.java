package org.taktik.connector.technical.beid;

import org.taktik.connector.technical.beid.domain.Address;
import org.taktik.connector.technical.beid.domain.DocumentType;
import org.taktik.connector.technical.beid.domain.Gender;
import org.taktik.connector.technical.beid.domain.Identity;
import org.taktik.connector.technical.beid.domain.SpecialOrganisation;
import org.taktik.connector.technical.beid.domain.SpecialStatus;
import org.taktik.connector.technical.cache.Cache;
import org.taktik.connector.technical.cache.CacheFactory;
import org.taktik.connector.technical.config.ConfigFactory;
import org.taktik.connector.technical.config.ConfigValidator;
import org.taktik.connector.technical.exception.TechnicalConnectorException;
import org.taktik.connector.technical.exception.TechnicalConnectorExceptionValues;
import be.fedict.commons.eid.client.BeIDCard;
import be.fedict.commons.eid.client.FileType;
import be.fedict.commons.eid.consumer.tlv.TlvParser;
import be.fgov.ehealth.technicalconnector.bootstrap.bcp.domain.CacheInformation;
import org.apache.commons.lang.ArrayUtils;
import org.joda.time.Duration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class BeIDInfo {
   public static final String PROP_USE_CACHE = "org.taktik.connector.technical.beid.beidinfo.cache";
   private static final boolean PROP_USE_CACHE_DEFAULT_VALUE = false;
   private static final Logger LOG = LoggerFactory.getLogger(BeIDInfo.class);
   private static Cache<String, BeIDInfo> cache;
   private Address address;
   private Identity identity;
   private byte[] photo;
   private static ConfigValidator config;

   private BeIDInfo() throws TechnicalConnectorException {
      BeIDCard beIDCard = BeIDCardFactory.getBeIDCard();

      try {
         LOG.debug("processing identity file");
         byte[] identityFile = beIDCard.readFile(FileType.Identity);
         be.fedict.commons.eid.consumer.Identity identityFedict = (be.fedict.commons.eid.consumer.Identity)TlvParser.parse(identityFile, be.fedict.commons.eid.consumer.Identity.class);
         this.identity = this.mapIdentityFedict(identityFedict);
         LOG.debug("processing address file");
         byte[] addressFile = beIDCard.readFile(FileType.Address);
         be.fedict.commons.eid.consumer.Address addressFedict = (be.fedict.commons.eid.consumer.Address)TlvParser.parse(addressFile, be.fedict.commons.eid.consumer.Address.class);
         this.address = this.mapAddressFedict(addressFedict);
         LOG.debug("processing photo file");
         this.photo = beIDCard.readFile(FileType.Photo);
      } catch (Exception ex) {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.BEID_ERROR, ex, ex.getMessage());
      }

      beIDCard.close();
   }

   public static BeIDInfo getInstance() throws TechnicalConnectorException {
      return getInstance("DEFAULT", false);
   }

   public static BeIDInfo getInstance(String scope) throws TechnicalConnectorException {
      boolean useCache = config.getBooleanProperty("org.taktik.connector.technical.beid.beidinfo.cache", false);
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

   private Address mapAddressFedict(be.fedict.commons.eid.consumer.Address addressFedict) {
      Address addr = new Address();

      addr.setStreetAndNumber(addressFedict.getStreetAndNumber());
      addr.setZip(addressFedict.getZip());
      addr.setMunicipality(addressFedict.getMunicipality());
      addr.setData(addressFedict.getData());

      return addr;
   }

   private Identity mapIdentityFedict(be.fedict.commons.eid.consumer.Identity identityFedict) {
      Identity id = new Identity();

      id.setCardNumber(identityFedict.getCardNumber());
      id.setChipNumber(identityFedict.getChipNumber());
      id.setCardValidityDateBegin(identityFedict.getCardValidityDateBegin());
      id.setCardValidityDateEnd(identityFedict.getCardValidityDateEnd());
      id.setCardDeliveryMunicipality(identityFedict.getCardDeliveryMunicipality());
      id.setNationalNumber(identityFedict.getNationalNumber());
      id.setName(identityFedict.getName());
      id.setFirstName(identityFedict.getFirstName());
      id.setMiddleName(identityFedict.getMiddleName());
      id.setNationality(identityFedict.getNationality());
      id.setPlaceOfBirth(identityFedict.getPlaceOfBirth());
      id.setDateOfBirth(identityFedict.getDateOfBirth());
      id.setGender(Gender.valueOf(identityFedict.getGender().name()));
      id.setNobleCondition(identityFedict.getNobleCondition());
      id.setDocumentType(DocumentType.valueOf(identityFedict.getDocumentType().name()));
      id.setSpecialStatus(SpecialStatus.valueOf(identityFedict.getSpecialStatus().name()));
      id.setPhotoDigest(identityFedict.getPhotoDigest());
      id.setDuplicate(identityFedict.getDuplicate());
      id.setSpecialOrganisation(SpecialOrganisation.valueOf(identityFedict.getSpecialOrganisation().name()));
      id.setIsMemberOfFamily(identityFedict.isMemberOfFamily());
      id.setData(identityFedict.getData());

      return id;
   }

   static {
      cache = CacheFactory.newInstance(CacheFactory.CacheType.MEMORY, "beid-info", CacheInformation.ExpiryType.NONE, (Duration)null);
      config = ConfigFactory.getConfigValidator();
   }
}
