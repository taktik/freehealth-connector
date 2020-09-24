package be.business.connector.gfddpp.tipsystem.threading;

import be.apb.gfddpp.common.exceptions.GFDDPPException;
import be.apb.gfddpp.common.utils.JaxContextCentralizer;
import be.apb.standards.smoa.schema.id.v1.EntityIdType;
import be.apb.standards.smoa.schema.model.v1.EncryptedContentType;
import be.apb.standards.smoa.schema.model.v1.HistoryProductType;
import be.apb.standards.smoa.schema.model.v1.MedicationHistoryType;
import be.business.connector.core.exceptions.IntegrationModuleException;
import be.business.connector.core.technical.connector.utils.Crypto;
import be.business.connector.gfddpp.pcdh.PCDHIntegrationModuleImpl;
import be.ehealth.technicalconnector.service.kgss.domain.KeyResult;
import java.util.concurrent.Semaphore;
import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;
import org.apache.log4j.Logger;

public class ProductDecryptorThread extends Thread {
   private static final Logger LOG = Logger.getLogger(ProductDecryptorThread.class);
   private MedicationHistoryType medicationHistoryType;
   private byte[] encoded;
   private JaxContextCentralizer jaxContextCentralizer = JaxContextCentralizer.getInstance();
   private Semaphore available;
   private Cache kgssCache;

   public ProductDecryptorThread(Semaphore available, MedicationHistoryType medicationHistoryType, byte[] encoded, PCDHIntegrationModuleImpl pcdhModule, Cache kgssCache) {
      this.available = available;
      this.medicationHistoryType = medicationHistoryType;
      this.encoded = encoded;
      this.kgssCache = kgssCache;
   }

   public void run() {
      if (this.medicationHistoryType.getEncryptedContent() != null) {
         EncryptedContentType encryptedContentType = this.medicationHistoryType.getEncryptedContent();
         HistoryProductType product = null;
         if (encryptedContentType != null) {
            EntityIdType entityId;
            try {
               byte[] productsByte = encryptedContentType.getProduct();
               LOG.info("GET KEY START");
               KeyResult key = this.getKey(encryptedContentType);
               LOG.info("GET KEY END");
               byte[] prd = Crypto.unsealForUnknown(key, productsByte);
               LOG.debug("******************* convertEncryptedContentToProduct product unsealed: " + new String(prd) + " ***********************");
               product = (HistoryProductType)this.getJaxContextCentralizer().toObject(HistoryProductType.class, prd);
            } catch (GFDDPPException var6) {
               entityId = (EntityIdType)this.medicationHistoryType.getEntityId();
               LOG.error("can not convert product : " + entityId.getId());
               LOG.error(var6.getMessage(), var6);
            } catch (IntegrationModuleException var7) {
               entityId = (EntityIdType)this.medicationHistoryType.getEntityId();
               LOG.error("can not convert product : " + entityId.getId());
               LOG.error(var7.getMessage(), var7);
            } catch (Throwable var8) {
               LOG.error("Exception occured in KGSSTHREAD:", var8);
            }
         }

         this.medicationHistoryType.setProduct(product);
         this.medicationHistoryType.setEncryptedContent((EncryptedContentType)null);
      }

      this.available.release();
   }

   private KeyResult getKey(EncryptedContentType encryptedContentType) throws IntegrationModuleException {
      KeyResult key = null;
      LOG.info("GET KEY FROM CACHE FOR ID: " + encryptedContentType.getEncryptionKeyId());
      Element tmpKey = this.kgssCache.get(encryptedContentType.getEncryptionKeyId());
      if (tmpKey != null) {
         key = (KeyResult)tmpKey.getObjectValue();
      }

      return key;
   }

   private JaxContextCentralizer getJaxContextCentralizer() {
      return this.jaxContextCentralizer;
   }
}
