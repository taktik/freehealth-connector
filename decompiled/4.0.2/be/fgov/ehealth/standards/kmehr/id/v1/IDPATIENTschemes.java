package be.fgov.ehealth.standards.kmehr.id.v1;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(
   name = "ID-PATIENTschemes"
)
@XmlEnum
public enum IDPATIENTschemes {
   @XmlEnumValue("ID-PATIENT")
   ID_PATIENT("ID-PATIENT"),
   INSS("INSS"),
   @XmlEnumValue("EID-CARDNO")
   EID_CARDNO("EID-CARDNO"),
   @XmlEnumValue("SIS-CARDNO")
   SIS_CARDNO("SIS-CARDNO"),
   @XmlEnumValue("ISI-CARDNO")
   ISI_CARDNO("ISI-CARDNO"),
   LOCAL("LOCAL");

   private final String value;

   private IDPATIENTschemes(String v) {
      this.value = v;
   }

   public String value() {
      return this.value;
   }

   public static IDPATIENTschemes fromValue(String v) {
      IDPATIENTschemes[] var1 = values();
      int var2 = var1.length;

      for(int var3 = 0; var3 < var2; ++var3) {
         IDPATIENTschemes c = var1[var3];
         if (c.value.equals(v)) {
            return c;
         }
      }

      throw new IllegalArgumentException(v);
   }
}
