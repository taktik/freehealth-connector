package be.fgov.ehealth.standards.kmehr.mycarenet.cd.v1;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(
   name = "CD-DISCHARGETYPEvalues"
)
@XmlEnum
public enum CDDISCHARGETYPEvalues {
   @XmlEnumValue("alive")
   ALIVE("alive"),
   @XmlEnumValue("dead")
   DEAD("dead");

   private final String value;

   private CDDISCHARGETYPEvalues(String v) {
      this.value = v;
   }

   public String value() {
      return this.value;
   }

   public static CDDISCHARGETYPEvalues fromValue(String v) {
      CDDISCHARGETYPEvalues[] var1 = values();
      int var2 = var1.length;

      for(int var3 = 0; var3 < var2; ++var3) {
         CDDISCHARGETYPEvalues c = var1[var3];
         if (c.value.equals(v)) {
            return c;
         }
      }

      throw new IllegalArgumentException(v);
   }
}
