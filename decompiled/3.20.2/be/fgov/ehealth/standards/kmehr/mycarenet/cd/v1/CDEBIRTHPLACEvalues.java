package be.fgov.ehealth.standards.kmehr.mycarenet.cd.v1;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(
   name = "CD-EBIRTH-PLACEvalues"
)
@XmlEnum
public enum CDEBIRTHPLACEvalues {
   @XmlEnumValue("home")
   HOME("home"),
   @XmlEnumValue("hospital")
   HOSPITAL("hospital"),
   @XmlEnumValue("other")
   OTHER("other");

   private final String value;

   private CDEBIRTHPLACEvalues(String v) {
      this.value = v;
   }

   public String value() {
      return this.value;
   }

   public static CDEBIRTHPLACEvalues fromValue(String v) {
      CDEBIRTHPLACEvalues[] arr$ = values();
      int len$ = arr$.length;

      for(int i$ = 0; i$ < len$; ++i$) {
         CDEBIRTHPLACEvalues c = arr$[i$];
         if (c.value.equals(v)) {
            return c;
         }
      }

      throw new IllegalArgumentException(v);
   }
}
