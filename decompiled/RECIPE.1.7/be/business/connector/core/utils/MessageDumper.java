package org.taktik.connector.business.recipeprojects.core.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.xml.ws.handler.soap.SOAPMessageContext;
import org.apache.log4j.Logger;

public class MessageDumper {
   private static final Logger LOG = Logger.getLogger(MessageDumper.class);
   public static final String IN = "IN";
   public static final String OUT = "OUT";
   private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM_dd_HHmmss");
   private static MessageDumper instance;
   private static String path = "";
   public static final String MESSAGE_DUMPER_DIRECTORY = "messageDumper.directory";

   public static MessageDumper getInstance() {
      if (instance == null) {
         instance = new MessageDumper();
      }

      return instance;
   }

   public void init(PropertyHandler propertyHandler) {
      if (propertyHandler.hasProperty("messageDumper.directory")) {
         path = propertyHandler.getProperty("messageDumper.directory");
      }

   }

   public boolean isDumpEnabled() {
      if (path != null && !"".equals(path)) {
         File dir = new File(path);
         if (dir.exists() && dir.isDirectory()) {
            return true;
         }
      }

      return false;
   }

   public void dump(ByteArrayOutputStream bos, String name, String way) {
      try {
         if (path != null && !"".equals(path)) {
            File dir = new File(path);
            if (dir.exists() && dir.isDirectory()) {
               OutputStream fos = new FileOutputStream(this.generateFileName(name, way));
               bos.writeTo(fos);
               fos.close();
            }
         }
      } catch (FileNotFoundException var6) {
         LOG.error("dump error", var6);
      } catch (IOException var7) {
         LOG.error("dump error", var7);
      }

   }

   public void dump(byte[] data, String name, String way) {
      try {
         ByteArrayInputStream bis = new ByteArrayInputStream(data);
         ByteArrayOutputStream buffer = new ByteArrayOutputStream();
         byte[] temp = new byte[1024];

         int read;
         while((read = bis.read(temp)) > 0) {
            buffer.write(temp, 0, read);
         }

         this.dump(buffer, name, way);
         bis.close();
         buffer.close();
      } catch (FileNotFoundException var8) {
         LOG.error("dump error", var8);
      } catch (IOException var9) {
         LOG.error("dump error", var9);
      }

   }

   private File generateFileName(String name, String way) {
      Date now = new Date();
      String direction = "";
      if ("IN".equalsIgnoreCase(way)) {
         direction = "-IN-";
      } else if ("OUT".equalsIgnoreCase(way)) {
         direction = "-OUT-";
      }

      return new File(path, sdf.format(now) + direction + name + ".xml");
   }

   public static String getOperationName(SOAPMessageContext context) {
      try {
         return context.getMessage().getSOAPBody().getFirstChild().getLocalName();
      } catch (Exception var2) {
         throw new RuntimeException("Error while trying to get wsdl operation name", var2);
      }
   }
}
