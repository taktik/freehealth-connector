package be.fgov.ehealth.standards.kmehr.cd.v1;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(
   name = "CD-TRANSACTION-MAAvalues"
)
@XmlEnum
public enum CDTRANSACTIONMAAvalues {
   @XmlEnumValue("agreementrequest")
   AGREEMENTREQUEST("agreementrequest"),
   @XmlEnumValue("agreementresponse")
   AGREEMENTRESPONSE("agreementresponse"),
   @XmlEnumValue("freeappendix")
   FREEAPPENDIX("freeappendix"),
   @XmlEnumValue("reglementaryappendix")
   REGLEMENTARYAPPENDIX("reglementaryappendix"),
   @XmlEnumValue("consultationrequest")
   CONSULTATIONREQUEST("consultationrequest"),
   @XmlEnumValue("consultationresponse")
   CONSULTATIONRESPONSE("consultationresponse");

   private final String value;

   private CDTRANSACTIONMAAvalues(String v) {
      this.value = v;
   }

   public String value() {
      return this.value;
   }

   public static CDTRANSACTIONMAAvalues fromValue(String v) {
      CDTRANSACTIONMAAvalues[] var1 = values();
      int var2 = var1.length;

      for(int var3 = 0; var3 < var2; ++var3) {
         CDTRANSACTIONMAAvalues c = var1[var3];
         if (c.value.equals(v)) {
            return c;
         }
      }

      throw new IllegalArgumentException(v);
   }
}
