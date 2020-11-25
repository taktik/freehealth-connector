package sun.security.smartcardio;

import java.io.File;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class PlatformPCSC {
   public static final String CUSTOM_J2PCSCS_LOCATION = "platformpcsc.custom.j2pcscs.location";
   public static final Logger LOG = LoggerFactory.getLogger(PlatformPCSC.class);
   static final Throwable initException = loadLibrary();
   static final int SCARD_PROTOCOL_T0 = 1;
   static final int SCARD_PROTOCOL_T1 = 2;
   static final int SCARD_PROTOCOL_RAW = 65536;
   static final int SCARD_UNKNOWN = 0;
   static final int SCARD_ABSENT = 1;
   static final int SCARD_PRESENT = 2;
   static final int SCARD_SWALLOWED = 3;
   static final int SCARD_POWERED = 4;
   static final int SCARD_NEGOTIABLE = 5;
   static final int SCARD_SPECIFIC = 6;

   private static Throwable loadLibrary() {
      try {
         String resource = null;
         if (System.getProperty("platformpcsc.custom.j2pcscs.location") != null) {
            resource = System.getProperty("platformpcsc.custom.j2pcscs.location");
         } else {
            resource = getResourceFilePath(System.getProperty("user.dir") + System.getProperty("file.separator") + "j2pcsc.dll");
         }

         if (resource != null) {
            LOG.info("Loading library from [" + resource + "]");
            System.load(resource);
         } else {
            LOG.info("Loading j2pcsc libary");
            System.loadLibrary("j2pcsc");
         }

         return null;
      } catch (Throwable var1) {
         LOG.error("Unable to loadLibrary", var1);
         return var1;
      }
   }

   public static String getResourceFilePath(String location) {
      String filePath = null;
      LOG.debug("Loading " + location + " as ResourceAsString");
      InputStream stream = PlatformPCSC.class.getResourceAsStream(location);
      if (stream != null) {
         filePath = PlatformPCSC.class.getResource(location).getFile();
      } else {
         File file = new File(location);
         if (!file.exists()) {
            try {
               URL resource = new URL(location);
               filePath = resource.getFile();
            } catch (MalformedURLException var5) {
               LOG.error("location [" + location + "] could not be retrieved as URL, classpath resource or file.");
            }
         } else {
            filePath = location;
         }
      }

      return filePath;
   }
}
