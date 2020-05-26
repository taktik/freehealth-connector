package be.fgov.ehealth.standards.kmehr.mycarenet.cd.v1;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(
   name = "CD-CARENET-PERSONAL-PARTvalues"
)
@XmlEnum
public enum CDCARENETPERSONALPARTvalues {
   @XmlEnumValue("code1")
   CODE_1("code1"),
   @XmlEnumValue("code2")
   CODE_2("code2"),
   @XmlEnumValue("future")
   FUTURE("future");

   private final String value;

   private CDCARENETPERSONALPARTvalues(String v) {
      this.value = v;
   }

   public String value() {
      return this.value;
   }

   public static CDCARENETPERSONALPARTvalues fromValue(String v) {
      CDCARENETPERSONALPARTvalues[] arr$ = values();
      int len$ = arr$.length;

      for(int i$ = 0; i$ < len$; ++i$) {
         CDCARENETPERSONALPARTvalues c = arr$[i$];
         if (c.value.equals(v)) {
            return c;
         }
      }

      throw new IllegalArgumentException(v);
   }
}
