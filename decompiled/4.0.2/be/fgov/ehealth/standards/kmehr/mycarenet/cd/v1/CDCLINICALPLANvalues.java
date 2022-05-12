package be.fgov.ehealth.standards.kmehr.mycarenet.cd.v1;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(
   name = "CD-CLINICALPLANvalues"
)
@XmlEnum
public enum CDCLINICALPLANvalues {
   @XmlEnumValue("breastcancerprevention")
   BREASTCANCERPREVENTION("breastcancerprevention"),
   @XmlEnumValue("cervixutericancer")
   CERVIXUTERICANCER("cervixutericancer"),
   @XmlEnumValue("colrectalcancerprevention")
   COLRECTALCANCERPREVENTION("colrectalcancerprevention"),
   @XmlEnumValue("primaryprevention")
   PRIMARYPREVENTION("primaryprevention"),
   @XmlEnumValue("prostatecancerprevention")
   PROSTATECANCERPREVENTION("prostatecancerprevention"),
   @XmlEnumValue("gmdplus")
   GMDPLUS("gmdplus");

   private final String value;

   private CDCLINICALPLANvalues(String v) {
      this.value = v;
   }

   public String value() {
      return this.value;
   }

   public static CDCLINICALPLANvalues fromValue(String v) {
      CDCLINICALPLANvalues[] var1 = values();
      int var2 = var1.length;

      for(int var3 = 0; var3 < var2; ++var3) {
         CDCLINICALPLANvalues c = var1[var3];
         if (c.value.equals(v)) {
            return c;
         }
      }

      throw new IllegalArgumentException(v);
   }
}
