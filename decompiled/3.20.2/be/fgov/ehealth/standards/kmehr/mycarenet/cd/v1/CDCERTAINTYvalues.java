package be.fgov.ehealth.standards.kmehr.mycarenet.cd.v1;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(
   name = "CD-CERTAINTYvalues"
)
@XmlEnum
public enum CDCERTAINTYvalues {
   @XmlEnumValue("excluded")
   EXCLUDED("excluded"),
   @XmlEnumValue("probable")
   PROBABLE("probable"),
   @XmlEnumValue("proven")
   PROVEN("proven"),
   @XmlEnumValue("unprobable")
   UNPROBABLE("unprobable"),
   @XmlEnumValue("undefined")
   UNDEFINED("undefined");

   private final String value;

   private CDCERTAINTYvalues(String v) {
      this.value = v;
   }

   public String value() {
      return this.value;
   }

   public static CDCERTAINTYvalues fromValue(String v) {
      CDCERTAINTYvalues[] arr$ = values();
      int len$ = arr$.length;

      for(int i$ = 0; i$ < len$; ++i$) {
         CDCERTAINTYvalues c = arr$[i$];
         if (c.value.equals(v)) {
            return c;
         }
      }

      throw new IllegalArgumentException(v);
   }
}
