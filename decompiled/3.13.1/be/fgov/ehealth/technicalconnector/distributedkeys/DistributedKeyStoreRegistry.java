package be.fgov.ehealth.technicalconnector.distributedkeys;

import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.exception.TechnicalConnectorExceptionValues;
import be.fgov.ehealth.technicalconnector.distributedkeys.jca.DistributedKeyLoadStoreParam;
import be.fgov.ehealth.technicalconnector.distributedkeys.jca.DistributedKeyProvider;
import java.io.IOException;
import java.io.Serializable;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.Security;
import java.security.KeyStore.LoadStoreParameter;
import java.security.cert.CertificateException;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class DistributedKeyStoreRegistry implements Serializable {
   private static final long serialVersionUID = 1L;
   private Map<String, KeyStore> distributedKeyStores;
   private static final Logger LOG = LoggerFactory.getLogger(DistributedKeyStoreRegistry.class);

   private DistributedKeyStoreRegistry() {
      this.distributedKeyStores = new HashMap();
   }

   public static DistributedKeyStoreRegistry getInstance() {
      return DistributedKeyStoreRegistry.DistributedKeyStoreRegistrySingleTon.INSTANCE.getDistributedKeyStoreRegistry();
   }

   public KeyStore createDistributedKeyStore(String key, DistributedSignerProxy proxy) throws TechnicalConnectorException {
      try {
         KeyStore store = KeyStore.getInstance("DistributedKeyProvider");
         Validate.notNull(store);
         LoadStoreParameter param = new DistributedKeyLoadStoreParam(proxy);
         store.load(param);
         if (this.distributedKeyStores.containsKey(key)) {
            LOG.info("Key [" + key + "] already in cache.");
         }

         this.distributedKeyStores.put(key, store);
         return store;
      } catch (IOException var5) {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_GENERAL, var5, new Object[]{var5.getMessage()});
      } catch (KeyStoreException var6) {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_GENERAL, var6, new Object[]{var6.getMessage()});
      } catch (NoSuchAlgorithmException var7) {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_GENERAL, var7, new Object[]{var7.getMessage()});
      } catch (CertificateException var8) {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_GENERAL, var8, new Object[]{var8.getMessage()});
      }
   }

   public Map<String, KeyStore> getDistributedKeyStores() {
      return this.distributedKeyStores;
   }

   public void flushCache() {
      this.distributedKeyStores.clear();
   }

   // $FF: synthetic method
   DistributedKeyStoreRegistry(DistributedKeyStoreRegistry.SyntheticClass_1 x0) {
      this();
   }

   static {
      Security.addProvider(new DistributedKeyProvider());
   }

   // $FF: synthetic class
   static class SyntheticClass_1 {
   }

   private static enum DistributedKeyStoreRegistrySingleTon {
      INSTANCE;

      private DistributedKeyStoreRegistry manager = new DistributedKeyStoreRegistry();

      public DistributedKeyStoreRegistry getDistributedKeyStoreRegistry() {
         return this.manager;
      }
   }
}
