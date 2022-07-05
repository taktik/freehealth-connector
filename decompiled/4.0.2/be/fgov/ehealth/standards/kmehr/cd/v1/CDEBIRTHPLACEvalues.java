package be.fgov.ehealth.standards.kmehr.cd.v1;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(
   name = "CD-EBIRTH-PLACEvalues"
)
@XmlEnum
public enum CDEBIRTHPLACEvalues {
   @XmlEnumValue("home")
   HOME("home"),
   @XmlEnumValue("hospital")
   HOSPITAL("hospital"),
   @XmlEnumValue("other")
   OTHER("other");

   private final String value;

   private CDEBIRTHPLACEvalues(String v) {
      this.value = v;
   }

   public String value() {
      return this.value;
   }

   public static CDEBIRTHPLACEvalues fromValue(String v) {
      CDEBIRTHPLACEvalues[] var1 = values();
      int var2 = var1.length;

      for(int var3 = 0; var3 < var2; ++var3) {
         CDEBIRTHPLACEvalues c = var1[var3];
         if (c.value.equals(v)) {
            return c;
         }
      }

      throw new IllegalArgumentException(v);
   }
}
