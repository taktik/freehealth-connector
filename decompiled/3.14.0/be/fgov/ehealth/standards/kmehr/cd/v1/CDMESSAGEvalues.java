package be.fgov.ehealth.standards.kmehr.cd.v1;

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
      CDMESSAGEvalues[] arr$ = values();
      int len$ = arr$.length;

      for(int i$ = 0; i$ < len$; ++i$) {
         CDMESSAGEvalues c = arr$[i$];
         if (c.value.equals(v)) {
            return c;
         }
      }

      throw new IllegalArgumentException(v);
   }
}
