package be.fgov.ehealth.standards.kmehr.mycarenet.id.v1;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(
   name = "ID-KMEHRschemes"
)
@XmlEnum
public enum IDKMEHRschemes {
   @XmlEnumValue("ID-KMEHR")
   ID_KMEHR("ID-KMEHR"),
   @XmlEnumValue("ID-IBAN")
   ID_IBAN("ID-IBAN"),
   @XmlEnumValue("ID-SERIALNO")
   ID_SERIALNO("ID-SERIALNO"),
   LOCAL("LOCAL"),
   @XmlEnumValue("ID-CBE")
   ID_CBE("ID-CBE");

   private final String value;

   private IDKMEHRschemes(String v) {
      this.value = v;
   }

   public String value() {
      return this.value;
   }

   public static IDKMEHRschemes fromValue(String v) {
      IDKMEHRschemes[] arr$ = values();
      int len$ = arr$.length;

      for(int i$ = 0; i$ < len$; ++i$) {
         IDKMEHRschemes c = arr$[i$];
         if (c.value.equals(v)) {
            return c;
         }
      }

      throw new IllegalArgumentException(v);
   }
}
