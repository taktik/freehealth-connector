package org.taktik.connector.technical.service.keydepot.impl;

import org.taktik.connector.technical.cache.Cache;
import org.taktik.connector.technical.cache.CacheFactory;
import org.taktik.connector.technical.exception.TechnicalConnectorException;
import org.taktik.connector.technical.exception.TechnicalConnectorExceptionValues;
import org.taktik.connector.technical.service.ServiceFactory;
import org.taktik.connector.technical.service.etee.domain.EncryptionToken;
import org.taktik.connector.technical.service.keydepot.KeyDepotManager;
import org.taktik.connector.technical.service.keydepot.KeyDepotService;
import org.taktik.connector.technical.service.sts.security.Credential;
import org.taktik.connector.technical.session.Session;
import org.taktik.connector.technical.session.SessionItem;
import org.taktik.connector.technical.session.SessionServiceWithCache;
import org.taktik.connector.technical.utils.CertificateParser;
import org.taktik.connector.technical.utils.IdentifierType;
import java.security.cert.X509Certificate;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class KeyDepotManagerImpl implements KeyDepotManager, SessionServiceWithCache {
   private static final Logger LOG = LoggerFactory.getLogger(KeyDepotManagerImpl.class);
   private KeyDepotService service;
   private Cache<X509Certificate, EncryptionToken> cache;

   private KeyDepotManagerImpl() {
      this.cache = CacheFactory.newInstance(CacheFactory.CacheType.MEMORY);

      try {
         this.service = ServiceFactory.getKeyDepotService();
      } catch (TechnicalConnectorException var2) {
         LOG.error(var2.getMessage(), var2);
         throw new IllegalArgumentException(var2);
      }

      Session.getInstance().registerSessionService(this);
   }

   public static KeyDepotManager getInstance() {
      return KeyDepotManagerImpl.KeyDepotManagerImplSingleton.INSTANCE.getKeyDepotManager();
   }

   @Override
   public EncryptionToken getETK(Credential cred) throws TechnicalConnectorException {
      return this.getEncryptionToken(cred);
   }

   private EncryptionToken getEncryptionToken(Credential cred) throws TechnicalConnectorException {
      if (cred != null) {
         X509Certificate cert = cred.getCertificate();
         if (!this.cache.containsKey(cert)) {
            this.cache.put(cert, this.getEtkBasedOnX509(cert));
         }

         return this.cache.get(cert);
      } else {
         LOG.error(TechnicalConnectorExceptionValues.NO_VALID_SESSION_WITH_ENCRYPTION.getMessage());
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.NO_VALID_SESSION_WITH_ENCRYPTION);
      }
   }

   private EncryptionToken getEtkBasedOnX509(X509Certificate cert) throws TechnicalConnectorException {
      CertificateParser parser = new CertificateParser(cert);
      IdentifierType identifierType = parser.getIdentifier();
      String identifierValue = parser.getId();
      String application = parser.getApplication();
      if (identifierType != null && !StringUtils.isEmpty(identifierValue) && StringUtils.isNumeric(identifierValue)) {
         try {
            return this.getEtk(identifierType, Long.parseLong(identifierValue), application);
         } catch (NumberFormatException var7) {
            LOG.error(TechnicalConnectorExceptionValues.ERROR_ETK_NOTFOUND.getMessage());
            throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_ETK_NOTFOUND, var7, new Object[0]);
         }
      } else {
         LOG.error(TechnicalConnectorExceptionValues.ERROR_ETK_NOTFOUND.getMessage());
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_ETK_NOTFOUND, new Object[0]);
      }
   }

   public EncryptionToken getEtk(IdentifierType identifierType, Long identifierValue, String application) throws TechnicalConnectorException {
      Set<EncryptionToken> etkSet = this.getEtkSet(identifierType, identifierValue, application);
      if (etkSet.size() != 1) {
         LOG.error(TechnicalConnectorExceptionValues.ERROR_ETK_NOTFOUND.getMessage());
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_ETK_NOTFOUND, new Object[0]);
      } else {
         return etkSet.iterator().next();
      }
   }

   public Set<EncryptionToken> getEtkSet(IdentifierType identifierType, Long identifierValue, String application) throws TechnicalConnectorException {
      String identifier = identifierType.formatIdentifierValue(identifierValue.longValue());
      Set<EncryptionToken> result = new HashSet();
      result.addAll(this.service.getETKSet(identifierType, identifier, application));
      if (LOG.isDebugEnabled()) {
         StringBuilder keyBuilder = new StringBuilder();
         keyBuilder.append(identifierType).append("/").append(identifierValue).append("/").append(application).append(" size [").append(result.size()).append("] with serialnr [");
         String delim = "";

         for(Iterator i$ = result.iterator(); i$.hasNext(); delim = ",") {
            EncryptionToken etk = (EncryptionToken)i$.next();
            keyBuilder.append(delim).append(etk.getCertificate().getSerialNumber().toString(10));
         }

         keyBuilder.append("]");
         LOG.debug("Retrieved ETK from eHealth Key Depot Web Service: " + keyBuilder.toString());
      }

      return result;
   }

   public void flushCache() {
      this.cache.clear();
   }

   // $FF: synthetic method
   KeyDepotManagerImpl(KeyDepotManagerImpl.SyntheticClass_1 x0) {
      this();
   }

   // $FF: synthetic class
   static class SyntheticClass_1 {
      // $FF: synthetic field
      static final int[] $SwitchMap$be$ehealth$technicalconnector$service$keydepot$KeyDepotManager$EncryptionTokenType = new int[KeyDepotManager.EncryptionTokenType.values().length];

      static {
         try {
            $SwitchMap$be$ehealth$technicalconnector$service$keydepot$KeyDepotManager$EncryptionTokenType[KeyDepotManager.EncryptionTokenType.ENCRYPTION.ordinal()] = 1;
         } catch (NoSuchFieldError var2) {
            ;
         }

         try {
            $SwitchMap$be$ehealth$technicalconnector$service$keydepot$KeyDepotManager$EncryptionTokenType[KeyDepotManager.EncryptionTokenType.HOLDER_OF_KEY.ordinal()] = 2;
         } catch (NoSuchFieldError var1) {
            ;
         }

      }
   }

   private static enum KeyDepotManagerImplSingleton {
      INSTANCE;

      private transient KeyDepotManager instance = new KeyDepotManagerImpl((KeyDepotManagerImpl.SyntheticClass_1)null);

      public KeyDepotManager getKeyDepotManager() {
         return this.instance;
      }
   }
}
