package be.ehealth.business.common.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PomPropertiesUtil {
   private static Properties properties;

   private PomPropertiesUtil() {
      throw new IllegalStateException("Utility class");
   }

   public static String getProperty(String propertyName) {
      return properties.getProperty(propertyName);
   }

   static {
      InputStream is = PomPropertiesUtil.class.getClassLoader().getResourceAsStream("properties-from-pom.properties");
      properties = new Properties();

      try {
         properties.load(is);
      } catch (IOException var2) {
         throw new IllegalStateException(var2);
      }
   }
}
