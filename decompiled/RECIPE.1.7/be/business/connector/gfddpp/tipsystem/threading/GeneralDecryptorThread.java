package be.business.connector.gfddpp.tipsystem.threading;

import org.taktik.connector.business.recipeprojects.core.exceptions.IntegrationModuleException;
import org.taktik.connector.business.recipeprojects.core.technical.connector.utils.Crypto;
import be.ehealth.technicalconnector.service.kgss.domain.KeyResult;
import java.io.Serializable;
import java.util.concurrent.Semaphore;
import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

public class GeneralDecryptorThread extends Thread {
   private static final Logger LOG = Logger.getLogger(GeneralDecryptorThread.class);
   private byte[] encoded;
   private byte[] decoded;
   private String encryptionKeyId;
   private Semaphore available;
   private Cache kgssCache;
   private IntegrationModuleException error;

   public GeneralDecryptorThread(Semaphore available, byte[] encoded, String encryptionKeyId, Cache kgssCache) {
      this.available = available;
      this.encoded = encoded;
      this.encryptionKeyId = encryptionKeyId;
      this.kgssCache = kgssCache;
      this.decoded = null;
      this.error = null;
   }

   public void run() {
      if (this.encoded.length > 0 && StringUtils.isNotBlank(this.encryptionKeyId)) {
         try {
            KeyResult key = this.getKey(this.encryptionKeyId);
            this.decoded = Crypto.unsealForUnknown(key, this.encoded);
         } catch (IntegrationModuleException var2) {
            this.error = var2;
         }
      }

      this.available.release();
   }

   private KeyResult getKey(String encryptionKeyId) {
      LOG.info("GET KEY FROM CACHE FOR ID: " + encryptionKeyId);
      Element tmpKey = this.kgssCache.get((Serializable)encryptionKeyId);
      return tmpKey != null ? (KeyResult)tmpKey.getObjectValue() : null;
   }

   public byte[] getDecoded() throws IntegrationModuleException {
      if (this.error != null) {
         throw this.error;
      } else {
         return this.decoded;
      }
   }
}
