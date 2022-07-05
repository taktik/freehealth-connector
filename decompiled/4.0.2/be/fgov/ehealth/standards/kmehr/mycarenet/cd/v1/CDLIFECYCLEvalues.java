package be.fgov.ehealth.standards.kmehr.mycarenet.cd.v1;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(
   name = "CD-LIFECYCLEvalues"
)
@XmlEnum
public enum CDLIFECYCLEvalues {
   @XmlEnumValue("aborted")
   ABORTED("aborted"),
   @XmlEnumValue("active")
   ACTIVE("active"),
   @XmlEnumValue("added")
   ADDED("added"),
   @XmlEnumValue("administrated")
   ADMINISTRATED("administrated"),
   @XmlEnumValue("cancelled")
   CANCELLED("cancelled"),
   @XmlEnumValue("completed")
   COMPLETED("completed"),
   @XmlEnumValue("corrected")
   CORRECTED("corrected"),
   @XmlEnumValue("delivered")
   DELIVERED("delivered"),
   @XmlEnumValue("substituted")
   SUBSTITUTED("substituted"),
   @XmlEnumValue("inactive")
   INACTIVE("inactive"),
   @XmlEnumValue("planned")
   PLANNED("planned"),
   @XmlEnumValue("prescribed")
   PRESCRIBED("prescribed"),
   @XmlEnumValue("reported")
   REPORTED("reported"),
   @XmlEnumValue("refused")
   REFUSED("refused"),
   @XmlEnumValue("switched")
   SWITCHED("switched"),
   @XmlEnumValue("suspended")
   SUSPENDED("suspended"),
   @XmlEnumValue("stopped")
   STOPPED("stopped"),
   @XmlEnumValue("excluded")
   EXCLUDED("excluded"),
   @XmlEnumValue("notpresent")
   NOTPRESENT("notpresent"),
   @XmlEnumValue("ordered")
   ORDERED("ordered"),
   @XmlEnumValue("scheduled")
   SCHEDULED("scheduled"),
   @XmlEnumValue("todo")
   TODO("todo"),
   @XmlEnumValue("postponed")
   POSTPONED("postponed"),
   @XmlEnumValue("pending")
   PENDING("pending"),
   @XmlEnumValue("expected")
   EXPECTED("expected"),
   @XmlEnumValue("obtained")
   OBTAINED("obtained"),
   @XmlEnumValue("proposed")
   PROPOSED("proposed"),
   @XmlEnumValue("retracted")
   RETRACTED("retracted");

   private final String value;

   private CDLIFECYCLEvalues(String v) {
      this.value = v;
   }

   public String value() {
      return this.value;
   }

   public static CDLIFECYCLEvalues fromValue(String v) {
      CDLIFECYCLEvalues[] var1 = values();
      int var2 = var1.length;

      for(int var3 = 0; var3 < var2; ++var3) {
         CDLIFECYCLEvalues c = var1[var3];
         if (c.value.equals(v)) {
            return c;
         }
      }

      throw new IllegalArgumentException(v);
   }
}
