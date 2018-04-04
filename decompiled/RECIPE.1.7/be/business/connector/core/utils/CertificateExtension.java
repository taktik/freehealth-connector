package org.taktik.connector.business.recipeprojects.core.utils;

import java.io.File;

public enum CertificateExtension {
   P12(".p12"),
   ACC_P12(".acc-p12");

   private final String name;

   private CertificateExtension(String name) {
      this.name = name;
   }

   public String getName() {
      return this.name;
   }

   public static CertificateExtension lookupType(File file) {
      CertificateExtension[] var4;
      int var3 = (var4 = values()).length;

      for(int var2 = 0; var2 < var3; ++var2) {
         CertificateExtension extension = var4[var2];
         if (file.getName().endsWith(extension.getName())) {
            return extension;
         }
      }

      throw new IllegalArgumentException();
   }

   public static boolean isSupported(File file) {
      if (file == null) {
         return false;
      } else {
         try {
            lookupType(file);
            return true;
         } catch (IllegalArgumentException var1) {
            return false;
         }
      }
   }

   public String removeExtension(String fileName) {
      return fileName == null ? null : fileName.substring(0, fileName.length() - this.name.length());
   }
}
