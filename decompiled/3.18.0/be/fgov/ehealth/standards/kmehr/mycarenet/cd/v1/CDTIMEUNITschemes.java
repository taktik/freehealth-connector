package be.fgov.ehealth.standards.kmehr.mycarenet.cd.v1;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(
   name = "CD-TIMEUNITschemes"
)
@XmlEnum
public enum CDTIMEUNITschemes {
   @XmlEnumValue("CD-TIMEUNIT")
   CD_TIMEUNIT("CD-TIMEUNIT"),
   LOCAL("LOCAL");

   private final String value;

   private CDTIMEUNITschemes(String v) {
      this.value = v;
   }

   public String value() {
      return this.value;
   }

   public static CDTIMEUNITschemes fromValue(String v) {
      CDTIMEUNITschemes[] arr$ = values();
      int len$ = arr$.length;

      for(int i$ = 0; i$ < len$; ++i$) {
         CDTIMEUNITschemes c = arr$[i$];
         if (c.value.equals(v)) {
            return c;
         }
      }

      throw new IllegalArgumentException(v);
   }
}
