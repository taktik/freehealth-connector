package be.fgov.ehealth.standards.kmehr.cd.v1;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(
   name = "CD-MICROORGANISMvalues"
)
@XmlEnum
public enum CDMICROORGANISMvalues {
   @XmlEnumValue("legionella")
   LEGIONELLA("legionella"),
   @XmlEnumValue("salmonella")
   SALMONELLA("salmonella");

   private final String value;

   private CDMICROORGANISMvalues(String v) {
      this.value = v;
   }

   public String value() {
      return this.value;
   }

   public static CDMICROORGANISMvalues fromValue(String v) {
      CDMICROORGANISMvalues[] var1 = values();
      int var2 = var1.length;

      for(int var3 = 0; var3 < var2; ++var3) {
         CDMICROORGANISMvalues c = var1[var3];
         if (c.value.equals(v)) {
            return c;
         }
      }

      throw new IllegalArgumentException(v);
   }
}
