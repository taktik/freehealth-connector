package org.taktik.connector.business.recipeprojects.core.utils;

import java.util.HashMap;
import java.util.Map;

public class OnlineProperties {
   public Map<String, String> properties = new HashMap();
   private Map<String, byte[]> xsdValidationFiles = new HashMap();
   private static boolean loaded = false;

   public Map<String, String> getProperties() {
      return this.properties;
   }

   public void setProperties(Map<String, String> properties) {
      this.properties = properties;
   }

   public Map<String, byte[]> getXsdValidationFiles() {
      return this.xsdValidationFiles;
   }

   public void setXsdValidationFiles(Map<String, byte[]> xsdValidationFiles) {
      this.xsdValidationFiles = xsdValidationFiles;
   }

   public void addXsdValidationFiles(String key, byte[] xsdValidationFile) {
      if (this.xsdValidationFiles == null) {
         this.xsdValidationFiles = new HashMap<>();
      }

      this.xsdValidationFiles.put(key, xsdValidationFile);
   }

   public static void setLoaded(boolean b) {
      loaded = b;
   }

   public static boolean isLoaded() {
      return loaded;
   }
}
