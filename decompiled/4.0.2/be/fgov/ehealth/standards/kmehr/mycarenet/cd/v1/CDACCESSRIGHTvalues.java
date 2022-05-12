package be.fgov.ehealth.standards.kmehr.mycarenet.cd.v1;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(
   name = "CD-ACCESSRIGHTvalues"
)
@XmlEnum
public enum CDACCESSRIGHTvalues {
   @XmlEnumValue("allow")
   ALLOW("allow"),
   @XmlEnumValue("disallow")
   DISALLOW("disallow");

   private final String value;

   private CDACCESSRIGHTvalues(String v) {
      this.value = v;
   }

   public String value() {
      return this.value;
   }

   public static CDACCESSRIGHTvalues fromValue(String v) {
      CDACCESSRIGHTvalues[] var1 = values();
      int var2 = var1.length;

      for(int var3 = 0; var3 < var2; ++var3) {
         CDACCESSRIGHTvalues c = var1[var3];
         if (c.value.equals(v)) {
            return c;
         }
      }

      throw new IllegalArgumentException(v);
   }
}
