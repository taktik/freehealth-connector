package be.fgov.ehealth.standards.kmehr.mycarenet.cd.v1;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(
   name = "CD-MESSAGEvalues"
)
@XmlEnum
public enum CDMESSAGEvalues {
   @XmlEnumValue("gpsoftwaremigration")
   GPSOFTWAREMIGRATION("gpsoftwaremigration"),
   @XmlEnumValue("gppatientmigration")
   GPPATIENTMIGRATION("gppatientmigration"),
   @XmlEnumValue("ptsoftwaremigration")
   PTSOFTWAREMIGRATION("ptsoftwaremigration"),
   @XmlEnumValue("ptpatientmigration")
   PTPATIENTMIGRATION("ptpatientmigration"),
   @XmlEnumValue("nursingsoftwaremigration")
   NURSINGSOFTWAREMIGRATION("nursingsoftwaremigration"),
   @XmlEnumValue("nursingpatientmigration")
   NURSINGPATIENTMIGRATION("nursingpatientmigration");

   private final String value;

   private CDMESSAGEvalues(String v) {
      this.value = v;
   }

   public String value() {
      return this.value;
   }

   public static CDMESSAGEvalues fromValue(String v) {
      CDMESSAGEvalues[] var1 = values();
      int var2 = var1.length;

      for(int var3 = 0; var3 < var2; ++var3) {
         CDMESSAGEvalues c = var1[var3];
         if (c.value.equals(v)) {
            return c;
         }
      }

      throw new IllegalArgumentException(v);
   }
}
