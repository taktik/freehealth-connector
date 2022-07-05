package be.fgov.ehealth.schematron.utils;

import java.io.UnsupportedEncodingException;

public final class XmlUtils {
   private XmlUtils() {
      throw new UnsupportedOperationException();
   }

   public static String flatten(String xml) {
      String result;
      for(result = xml.replaceAll("[\t\n\r]", ""); result.contains(" <"); result = result.replace(" <", "<")) {
      }

      return result;
   }

   public static String removeProcessInstruction(String xml) {
      int firstLineEnd = xml.indexOf("\n");
      return !xml.startsWith("<?xml ") && !xml.startsWith("<?xml ", 1) && !xml.startsWith("<?xml ", 2) && !xml.startsWith("<?xml ", 3) ? xml : xml.substring(firstLineEnd + 1);
   }

   public static String flatten(byte[] xml) {
      try {
         return flatten(new String(xml, "UTF-8"));
      } catch (UnsupportedEncodingException var2) {
         throw new IllegalArgumentException("UTF-8 not supported", var2);
      }
   }
}
