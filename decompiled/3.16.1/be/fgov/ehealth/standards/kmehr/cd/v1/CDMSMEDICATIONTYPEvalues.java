package be.fgov.ehealth.standards.kmehr.cd.v1;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(
   name = "CD-MS-MEDICATIONTYPEvalues"
)
@XmlEnum
public enum CDMSMEDICATIONTYPEvalues {
   @XmlEnumValue("onprescription")
   ONPRESCRIPTION("onprescription"),
   @XmlEnumValue("otc")
   OTC("otc"),
   @XmlEnumValue("other")
   OTHER("other");

   private final String value;

   private CDMSMEDICATIONTYPEvalues(String v) {
      this.value = v;
   }

   public String value() {
      return this.value;
   }

   public static CDMSMEDICATIONTYPEvalues fromValue(String v) {
      CDMSMEDICATIONTYPEvalues[] arr$ = values();
      int len$ = arr$.length;

      for(int i$ = 0; i$ < len$; ++i$) {
         CDMSMEDICATIONTYPEvalues c = arr$[i$];
         if (c.value.equals(v)) {
            return c;
         }
      }

      throw new IllegalArgumentException(v);
   }
}
