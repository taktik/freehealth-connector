package be.ehealth.technicalconnector.config.impl;

import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.exception.TechnicalConnectorExceptionValues;
import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;
import org.apache.commons.lang3.StringUtils;

public class ConfigUtils {
   public ConfigUtils() {
   }

   public static boolean isNet() throws TechnicalConnectorException {
      try {
         Enumeration resEnum = Thread.currentThread().getContextClassLoader().getResources("META-INF/MANIFEST.MF");
         if (resEnum.hasMoreElements()) {
            URL url = (URL)resEnum.nextElement();
            String path = url.getPath().toLowerCase();
            return StringUtils.contains(path, "ikvm");
         } else {
            return false;
         }
      } catch (IOException var3) {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_GENERAL, var3, new Object[]{var3.getMessage()});
      }
   }
}
