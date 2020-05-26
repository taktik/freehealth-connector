package be.business.connector.gfddpp.tipsystem.threading;

import be.business.connector.core.exceptions.IntegrationModuleException;
import be.business.connector.gfddpp.pcdh.PCDHIntegrationModuleImpl;
import be.ehealth.technicalconnector.service.kgss.domain.KeyResult;
import java.util.concurrent.Semaphore;
import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;
import org.apache.log4j.Logger;

public class KGSSThread extends Thread {
   private static final Logger LOG = Logger.getLogger(KGSSThread.class);
   private final byte[] etkKey;
   private final PCDHIntegrationModuleImpl pcdhModule;
   private final Semaphore available;
   private final Cache kgssCache;
   private final String encryptionId;
   private boolean errorOccured = false;

   public KGSSThread(Semaphore available, Cache kgssCache, String encryptionId, byte[] etkKey, PCDHIntegrationModuleImpl pcdhIntegrationModuleImpl) {
      this.available = available;
      this.kgssCache = kgssCache;
      this.encryptionId = encryptionId;
      this.etkKey = etkKey;
      this.pcdhModule = pcdhIntegrationModuleImpl;
   }

   public void run() {
      long start = System.currentTimeMillis();
      LOG.info("GET KEY START");

      try {
         KeyResult key = this.pcdhModule.getKeyFromKgss(this.encryptionId, this.etkKey);
         this.setErrorOccured(true);
         LOG.info("PUT KGSS ID IN ELEMENT: " + this.encryptionId);
         Element element = new Element(this.encryptionId, key);
         LOG.info("PUT KGSS ELEMENT IN CACHE: " + this.encryptionId);
         this.kgssCache.put(element);
      } catch (IntegrationModuleException var5) {
         LOG.error("Exception - Call KGSS - encryptionId: " + this.encryptionId);
         LOG.error("Exception occured in KGSSTHREAD", var5);
      } catch (Throwable var6) {
         LOG.error("Exception occured in KGSSTHREAD:", var6);
      }

      long stop = System.currentTimeMillis();
      LOG.info("GET KEY END: " + (stop - start));
      this.available.release();
   }

   public boolean isErrorOccured() {
      return this.errorOccured;
   }

   public void setErrorOccured(boolean errorOccured) {
      this.errorOccured = errorOccured;
   }
}
