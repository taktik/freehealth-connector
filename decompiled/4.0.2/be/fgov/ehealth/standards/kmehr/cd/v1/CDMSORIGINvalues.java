package be.fgov.ehealth.standards.kmehr.cd.v1;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(
   name = "CD-MS-ORIGINvalues"
)
@XmlEnum
public enum CDMSORIGINvalues {
   @XmlEnumValue("regularprocess")
   REGULARPROCESS("regularprocess"),
   @XmlEnumValue("recorded")
   RECORDED("recorded");

   private final String value;

   private CDMSORIGINvalues(String v) {
      this.value = v;
   }

   public String value() {
      return this.value;
   }

   public static CDMSORIGINvalues fromValue(String v) {
      CDMSORIGINvalues[] var1 = values();
      int var2 = var1.length;

      for(int var3 = 0; var3 < var2; ++var3) {
         CDMSORIGINvalues c = var1[var3];
         if (c.value.equals(v)) {
            return c;
         }
      }

      throw new IllegalArgumentException(v);
   }
}
