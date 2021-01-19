package be.fgov.ehealth.standards.kmehr.mycarenet.cd.v1;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(
   name = "CD-TELECOMvalues"
)
@XmlEnum
public enum CDTELECOMvalues {
   @XmlEnumValue("phone")
   PHONE("phone"),
   @XmlEnumValue("mobile")
   MOBILE("mobile"),
   @XmlEnumValue("fax")
   FAX("fax"),
   @XmlEnumValue("email")
   EMAIL("email"),
   @XmlEnumValue("carenet")
   CARENET("carenet");

   private final String value;

   private CDTELECOMvalues(String v) {
      this.value = v;
   }

   public String value() {
      return this.value;
   }

   public static CDTELECOMvalues fromValue(String v) {
      CDTELECOMvalues[] arr$ = values();
      int len$ = arr$.length;

      for(int i$ = 0; i$ < len$; ++i$) {
         CDTELECOMvalues c = arr$[i$];
         if (c.value.equals(v)) {
            return c;
         }
      }

      throw new IllegalArgumentException(v);
   }
}
