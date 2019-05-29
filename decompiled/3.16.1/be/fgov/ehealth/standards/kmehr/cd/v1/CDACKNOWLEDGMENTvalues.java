package be.fgov.ehealth.standards.kmehr.cd.v1;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(
   name = "CD-ACKNOWLEDGMENTvalues"
)
@XmlEnum
public enum CDACKNOWLEDGMENTvalues {
   @XmlEnumValue("always")
   ALWAYS("always"),
   @XmlEnumValue("never")
   NEVER("never"),
   @XmlEnumValue("onerror")
   ONERROR("onerror"),
   @XmlEnumValue("onsuccess")
   ONSUCCESS("onsuccess");

   private final String value;

   private CDACKNOWLEDGMENTvalues(String v) {
      this.value = v;
   }

   public String value() {
      return this.value;
   }

   public static CDACKNOWLEDGMENTvalues fromValue(String v) {
      CDACKNOWLEDGMENTvalues[] arr$ = values();
      int len$ = arr$.length;

      for(int i$ = 0; i$ < len$; ++i$) {
         CDACKNOWLEDGMENTvalues c = arr$[i$];
         if (c.value.equals(v)) {
            return c;
         }
      }

      throw new IllegalArgumentException(v);
   }
}
