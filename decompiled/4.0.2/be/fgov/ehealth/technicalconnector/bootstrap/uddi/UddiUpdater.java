package be.fgov.ehealth.technicalconnector.bootstrap.uddi;

import be.ehealth.technicalconnector.config.ConfigFactory;
import java.io.File;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class UddiUpdater {
   public static final String PROP_UDDI_LOCAL_CACHE_DIR = "uddi.local.cache.dir";
   private static final Logger LOG = LoggerFactory.getLogger(UddiUpdater.class);

   private UddiUpdater() {
      throw new UnsupportedOperationException();
   }

   public static void launch() throws Exception {
      String dirLocation = ConfigFactory.getConfigValidator().getProperty("uddi.local.cache.dir", System.getProperty("java.io.tmpdir"));
      File dir = new File(dirLocation);
      if (!dir.exists() && !dir.mkdirs()) {
         throw new IOException("Unable to create directory. [" + dirLocation + "]");
      } else {
         File file = new File(dirLocation, "uddi-local.properties");
         if (!file.exists() && !file.createNewFile()) {
            throw new IOException("Unable to create file. [" + file.getAbsolutePath() + "]");
         } else {
            LOG.warn("UddiUpdater desactivated.");
         }
      }
   }
}
