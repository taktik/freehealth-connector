package be.fgov.ehealth.standards.kmehr.cd.v1;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(
   name = "CD-BVT-PATIENTOPPOSITIONvalues"
)
@XmlEnum
public enum CDBVTPATIENTOPPOSITIONvalues {
   @XmlEnumValue("nopatientopposition")
   NOPATIENTOPPOSITION("nopatientopposition");

   private final String value;

   private CDBVTPATIENTOPPOSITIONvalues(String v) {
      this.value = v;
   }

   public String value() {
      return this.value;
   }

   public static CDBVTPATIENTOPPOSITIONvalues fromValue(String v) {
      CDBVTPATIENTOPPOSITIONvalues[] var1 = values();
      int var2 = var1.length;

      for(int var3 = 0; var3 < var2; ++var3) {
         CDBVTPATIENTOPPOSITIONvalues c = var1[var3];
         if (c.value.equals(v)) {
            return c;
         }
      }

      throw new IllegalArgumentException(v);
   }
}
