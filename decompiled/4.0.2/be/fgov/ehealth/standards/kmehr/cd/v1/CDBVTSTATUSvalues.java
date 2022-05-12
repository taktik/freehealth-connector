package be.fgov.ehealth.standards.kmehr.cd.v1;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(
   name = "CD-BVT-STATUSvalues"
)
@XmlEnum
public enum CDBVTSTATUSvalues {
   @XmlEnumValue("saved")
   SAVED("saved"),
   @XmlEnumValue("submitted")
   SUBMITTED("submitted"),
   @XmlEnumValue("rejected")
   REJECTED("rejected"),
   @XmlEnumValue("inactive")
   INACTIVE("inactive"),
   @XmlEnumValue("published")
   PUBLISHED("published");

   private final String value;

   private CDBVTSTATUSvalues(String v) {
      this.value = v;
   }

   public String value() {
      return this.value;
   }

   public static CDBVTSTATUSvalues fromValue(String v) {
      CDBVTSTATUSvalues[] var1 = values();
      int var2 = var1.length;

      for(int var3 = 0; var3 < var2; ++var3) {
         CDBVTSTATUSvalues c = var1[var3];
         if (c.value.equals(v)) {
            return c;
         }
      }

      throw new IllegalArgumentException(v);
   }
}
