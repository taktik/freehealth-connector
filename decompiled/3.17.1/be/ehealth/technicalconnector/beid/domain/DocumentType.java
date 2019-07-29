package be.ehealth.technicalconnector.beid.domain;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public enum DocumentType implements Serializable {
   BELGIAN_CITIZEN("1"),
   KIDS_CARD("6"),
   BOOTSTRAP_CARD("7"),
   HABILITATION_CARD("8"),
   FOREIGNER_A("11"),
   FOREIGNER_B("12"),
   FOREIGNER_C("13"),
   FOREIGNER_D("14"),
   FOREIGNER_E("15"),
   FOREIGNER_E_PLUS("16"),
   FOREIGNER_F("17"),
   FOREIGNER_F_PLUS("18"),
   EUROPEAN_BLUE_CARD_H("19");

   private final int key;
   private static Map<Integer, DocumentType> documentTypes = new HashMap();

   private DocumentType(String value) {
      this.key = toKey(value);
   }

   private static int toKey(String value) {
      char c1 = value.charAt(0);
      int key1 = c1 - 48;
      if (2 == value.length()) {
         key1 *= 10;
         char c2 = value.charAt(1);
         key1 += c2 - 48;
      }

      return key1;
   }

   private static int toKey(byte[] value) {
      int key = value[0] - 48;
      if (2 == value.length) {
         key *= 10;
         key += value[1] - 48;
      }

      return key;
   }

   public int getKey() {
      return this.key;
   }

   public static DocumentType toDocumentType(byte[] value) {
      int key = toKey(value);
      return (DocumentType)documentTypes.get(key);
   }

   public static String toString(byte[] documentTypeValue) {
      return Integer.toString(toKey(documentTypeValue));
   }

   static {
      DocumentType[] arr$ = values();
      int len$ = arr$.length;

      for(int i$ = 0; i$ < len$; ++i$) {
         DocumentType documentType = arr$[i$];
         int encodedValue = documentType.key;
         if (documentTypes.containsKey(encodedValue)) {
            throw new IllegalArgumentException("duplicate document type enum: " + encodedValue);
         }

         documentTypes.put(encodedValue, documentType);
      }

   }
}
