package be.fgov.ehealth.schematron.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import javax.xml.transform.TransformerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Utils {
   public static final String SCHEMATRON_NAME = "http://purl.oclc.org/dsdl/schematron";
   public static final String SVRL_NAME = "http://purl.oclc.org/dsdl/svrl";
   public static final String PROBATRON_FUNCTION_NAME = "http://www.probatron.org/functions";
   private static final String PROPERTY_TRAX_IMPLEMENTATION = "javax.xml.transform.TransformerFactory";
   private static final String SAXON_TRAX_CLASS = "net.sf.saxon.TransformerFactoryImpl";
   private static final int READ_BUFFER_SIZE = 32768;
   public static final int CLOSE_NONE = 0;
   public static final int CLOSE_IN = 1;
   public static final int CLOSE_OUT = 16;
   static Logger LOG = LoggerFactory.getLogger(Utils.class);

   public Utils() {
   }

   public static TransformerFactory getTransformerFactory() {
      System.setProperty("javax.xml.transform.TransformerFactory", "net.sf.saxon.TransformerFactoryImpl");
      return TransformerFactory.newInstance();
   }

   public static byte[] derefUrl(URL url) {
      try {
         URLConnection conn = url.openConnection();
         conn.connect();
         InputStream is = conn.getInputStream();
         byte[] ba = getBytesToEndOfStream(is, true);
         return ba;
      } catch (IOException var4) {
         LOG.warn(var4.getMessage());
         return null;
      }
   }

   public static byte[] getBytesToEndOfStream(InputStream in, boolean closeSteam) throws IOException {
      ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
      transferBytesToEndOfStream(in, byteStream, closeSteam ? 17 : 16);
      byte[] ba = byteStream.toByteArray();
      return ba;
   }

   public static long transferBytesToEndOfStream(InputStream in, OutputStream out, int closeFlags) throws IOException {
      if (in != null && out != null) {
         byte[] buf = new byte['老'];

         long written;
         int count;
         for(written = 0L; (count = in.read(buf)) != -1; written += (long)count) {
            out.write(buf, 0, count);
         }

         if ((closeFlags & 1) != 0) {
            streamClose(in);
         }

         if ((closeFlags & 16) != 0) {
            streamClose(out);
         }

         return written;
      } else {
         LOG.error("transferBytesToEndOfStream() called with a null parameter");
         throw new IllegalArgumentException();
      }
   }

   public static void streamClose(InputStream is) {
      try {
         if (is != null) {
            is.close();
         }
      } catch (Exception var2) {
         LOG.warn(var2.getMessage());
      }

   }

   public static void streamClose(OutputStream os) {
      try {
         if (os != null) {
            os.close();
         }
      } catch (Exception var2) {
         LOG.warn(var2.getMessage());
      }

   }

   public static void streamToFile(InputStream is, String fn, boolean closeStream) throws IOException {
      File f = new File(fn);
      boolean ret = f.createNewFile();
      if (!ret) {
         LOG.error("File " + fn + " not created.");
      }

      try {
         FileOutputStream fos = new FileOutputStream(f);
         int flags = 16;
         if (closeStream) {
            flags |= 1;
         }

         transferBytesToEndOfStream(is, fos, flags);
      } catch (FileNotFoundException var7) {
         throw new RuntimeException("File not found when writing: ", var7);
      }
   }

   public static void writeBytesToFile(byte[] ba, String fn) throws IOException {
      File f = new File(fn);
      boolean ret = f.createNewFile();
      if (!ret) {
         LOG.error("File " + fn + " was not created.");
      }

      FileOutputStream fos = null;
      ByteArrayInputStream bis = null;

      try {
         fos = new FileOutputStream(f);
         bis = new ByteArrayInputStream(ba);
         transferBytesToEndOfStream(bis, fos, 17);
      } catch (FileNotFoundException var7) {
         throw new RuntimeException("File not found when writing: ", var7);
      }
   }

   static String trimAttributePart(String xpath) {
      String ret = null;
      int n = xpath.indexOf("@");
      if (n != -1) {
         ret = xpath.substring(0, n);
         LOG.trace("Trimmed xpath to: " + ret);
      } else {
         ret = xpath;
      }

      return ret;
   }
}
