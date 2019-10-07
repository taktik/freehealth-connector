package org.taktik.connector.technical.service.keydepot.impl;

import be.fgov.ehealth.technicalconnector.bootstrap.bcp.domain.CacheInformation;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.taktik.connector.technical.cache.Cache;
import org.taktik.connector.technical.cache.CacheFactory;
import org.taktik.connector.technical.exception.TechnicalConnectorException;
import org.taktik.connector.technical.exception.TechnicalConnectorExceptionValues;
import org.taktik.connector.technical.service.etee.domain.EncryptionToken;
import org.taktik.connector.technical.service.keydepot.KeyDepotManager;
import org.taktik.connector.technical.service.keydepot.KeyDepotService;
import org.taktik.connector.technical.service.sts.security.Credential;
import org.taktik.connector.technical.utils.CertificateParser;
import org.taktik.connector.technical.utils.IdentifierType;

import java.security.cert.X509Certificate;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public final class KeyDepotManagerImpl implements KeyDepotManager {
   private static final Logger LOG = LoggerFactory.getLogger(KeyDepotManagerImpl.class);
   private static KeyDepotManagerImpl instance = null;

   private KeyDepotService keyDepotService;
   private Cache<X509Certificate, EncryptionToken> cache;

   private KeyDepotManagerImpl(KeyDepotService keyDepotService) {
      this.cache = CacheFactory.newInstance(CacheFactory.CacheType.MEMORY, "etkdepot-manager", CacheInformation.ExpiryType.NONE, null);
      this.keyDepotService = keyDepotService;
   }

   public static synchronized KeyDepotManager getInstance(KeyDepotService keyDepotService) {
      return (instance == null) ? (instance = new KeyDepotManagerImpl(keyDepotService)) : instance;
   }

   @Override
   public EncryptionToken getETK(Credential cred, UUID keystoreId) throws TechnicalConnectorException {
      if (cred != null) {
         X509Certificate cert = cred.getCertificate();
         if (!this.cache.containsKey(cert)) {
            EncryptionToken result;
            CertificateParser parser = new CertificateParser(cert);
            IdentifierType identifierType = parser.getIdentifier();
            String identifierValue = parser.getId();
            String application = parser.getApplication();
            if (identifierType != null && !StringUtils.isEmpty(identifierValue) && StringUtils.isNumeric(identifierValue)) {
               try {
                  result = this.getEtk(identifierType, Long.parseLong(identifierValue), application, keystoreId, true);
               } catch (NumberFormatException numberFormatException) {
                  LOG.error(TechnicalConnectorExceptionValues.ERROR_ETK_NOTFOUND.getMessage());
                  throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_ETK_NOTFOUND, numberFormatException);
               }
            } else {
               LOG.error(TechnicalConnectorExceptionValues.ERROR_ETK_NOTFOUND.getMessage());
               throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_ETK_NOTFOUND);
            }
            this.cache.put(cert, result);
         }

         return this.cache.get(cert);
      } else {
         LOG.error(TechnicalConnectorExceptionValues.NO_VALID_SESSION_WITH_ENCRYPTION.getMessage());
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.NO_VALID_SESSION_WITH_ENCRYPTION);
      }
   }

   @Override
   public EncryptionToken getEtk(IdentifierType identifierType, Long identifierValue, String application, UUID keystoreId, boolean isOwnEtk) throws TechnicalConnectorException {
      Set<EncryptionToken> etkSet = this.getEtkSet(identifierType, identifierValue, application, keystoreId, isOwnEtk);
      if (etkSet.size() != 1) {
         LOG.error(TechnicalConnectorExceptionValues.ERROR_ETK_NOTFOUND.getMessage());
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_ETK_NOTFOUND);
      } else {
         return etkSet.iterator().next();
      }
   }

   @Override
   public Set<EncryptionToken> getEtkSet(IdentifierType identifierType, Long identifierValue, String application, UUID keystoreId, boolean isOwnEtk) throws TechnicalConnectorException {
      String identifier = identifierType.formatIdentifierValue(identifierValue);
      Set<EncryptionToken> result = new HashSet<>(this.keyDepotService.getETKSet(identifierType, identifier, application, keystoreId, isOwnEtk));

      if (LOG.isDebugEnabled()) {
         StringBuilder keyBuilder = new StringBuilder();
         keyBuilder.append(identifierType).append("/").append(identifierValue).append("/").append(application).append(" size [").append(result.size()).append("] with serialnr [");
         String delim = ",";

         for (EncryptionToken etk : result) {
            keyBuilder.append(delim).append(etk.getCertificate().getSerialNumber().toString(10));
         }

         keyBuilder.append("]");
         LOG.debug("Retrieved ETK from eHealth Key Depot Web Service: " + keyBuilder.toString());
      }

      return result;
   }

   public void setKeyDepotService(KeyDepotService service) {
      this.keyDepotService = service;
      this.flushCache();
   }

   @Override
   public void flushCache() {
      this.cache.clear();
   }
}
