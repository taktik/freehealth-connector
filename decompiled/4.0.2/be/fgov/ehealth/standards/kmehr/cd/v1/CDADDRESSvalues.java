package be.fgov.ehealth.standards.kmehr.cd.v1;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(
   name = "CD-ADDRESSvalues"
)
@XmlEnum
public enum CDADDRESSvalues {
   @XmlEnumValue("home")
   HOME("home"),
   @XmlEnumValue("other")
   OTHER("other"),
   @XmlEnumValue("vacation")
   VACATION("vacation"),
   @XmlEnumValue("work")
   WORK("work"),
   @XmlEnumValue("careaddress")
   CAREADDRESS("careaddress");

   private final String value;

   private CDADDRESSvalues(String v) {
      this.value = v;
   }

   public String value() {
      return this.value;
   }

   public static CDADDRESSvalues fromValue(String v) {
      CDADDRESSvalues[] var1 = values();
      int var2 = var1.length;

      for(int var3 = 0; var3 < var2; ++var3) {
         CDADDRESSvalues c = var1[var3];
         if (c.value.equals(v)) {
            return c;
         }
      }

      throw new IllegalArgumentException(v);
   }
}
