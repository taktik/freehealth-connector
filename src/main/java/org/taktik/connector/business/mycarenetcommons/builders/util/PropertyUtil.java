package org.taktik.connector.business.mycarenetcommons.builders.util;

import org.taktik.connector.technical.config.ConfigFactory;

public class PropertyUtil {
   public static String retrieveProjectNameToUse(String projectName, String prefix) {
      return retrieveProjectNameToUse(projectName, prefix, "");
   }

   public static String retrieveProjectNameToUse(String projectName, String prefix, String suffix) {
      if (prefix != null && suffix != null) {
         if (projectName != null && !projectName.isEmpty()) {
            prefix = endPrefixWithDotIfNeeded(prefix);
            suffix = startSuffixWithDotIfNeeded(suffix);
            String key = prefix + projectName + suffix + ".usedefaultproperties";
            String useDefaultPropertiesString = ConfigFactory.getConfigValidator().getProperty(key, "true");
            return useDefaultPropertiesString != null && !"true".equals(useDefaultPropertiesString) ? projectName : "default";
         } else {
            return "default";
         }
      } else {
         throw new IllegalArgumentException("parameters prefix and suffix cannot be null");
      }
   }

   private static String startSuffixWithDotIfNeeded(String suffix) {
      if (!suffix.isEmpty() && !suffix.startsWith(".")) {
         suffix = "." + suffix;
      }

      return suffix;
   }

   private static String endPrefixWithDotIfNeeded(String prefix) {
      if (!prefix.endsWith(".")) {
         prefix = prefix + ".";
      }

      return prefix;
   }
}
