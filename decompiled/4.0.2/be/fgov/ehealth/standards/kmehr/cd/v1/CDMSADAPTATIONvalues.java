package be.fgov.ehealth.standards.kmehr.cd.v1;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(
   name = "CD-MS-ADAPTATIONvalues"
)
@XmlEnum
public enum CDMSADAPTATIONvalues {
   @XmlEnumValue("nochanges")
   NOCHANGES("nochanges"),
   @XmlEnumValue("medication")
   MEDICATION("medication"),
   @XmlEnumValue("posology")
   POSOLOGY("posology"),
   @XmlEnumValue("treatmentsuspension")
   TREATMENTSUSPENSION("treatmentsuspension");

   private final String value;

   private CDMSADAPTATIONvalues(String v) {
      this.value = v;
   }

   public String value() {
      return this.value;
   }

   public static CDMSADAPTATIONvalues fromValue(String v) {
      CDMSADAPTATIONvalues[] var1 = values();
      int var2 = var1.length;

      for(int var3 = 0; var3 < var2; ++var3) {
         CDMSADAPTATIONvalues c = var1[var3];
         if (c.value.equals(v)) {
            return c;
         }
      }

      throw new IllegalArgumentException(v);
   }
}
