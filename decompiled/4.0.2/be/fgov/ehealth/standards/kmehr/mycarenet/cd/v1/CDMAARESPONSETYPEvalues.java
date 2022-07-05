package be.fgov.ehealth.standards.kmehr.mycarenet.cd.v1;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(
   name = "CD-MAA-RESPONSETYPEvalues"
)
@XmlEnum
public enum CDMAARESPONSETYPEvalues {
   @XmlEnumValue("agreement")
   AGREEMENT("agreement"),
   @XmlEnumValue("refusal")
   REFUSAL("refusal"),
   @XmlEnumValue("intreatment")
   INTREATMENT("intreatment");

   private final String value;

   private CDMAARESPONSETYPEvalues(String v) {
      this.value = v;
   }

   public String value() {
      return this.value;
   }

   public static CDMAARESPONSETYPEvalues fromValue(String v) {
      CDMAARESPONSETYPEvalues[] var1 = values();
      int var2 = var1.length;

      for(int var3 = 0; var3 < var2; ++var3) {
         CDMAARESPONSETYPEvalues c = var1[var3];
         if (c.value.equals(v)) {
            return c;
         }
      }

      throw new IllegalArgumentException(v);
   }
}
