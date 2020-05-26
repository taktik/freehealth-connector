package be.fgov.ehealth.standards.kmehr.mycarenet.cd.v1;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(
   name = "CD-MEDICATIONschemes"
)
@XmlEnum
public enum CDMEDICATIONschemes {
   @XmlEnumValue("CD-DRUG-CNK")
   CD_DRUG_CNK("CD-DRUG-CNK"),
   @XmlEnumValue("CD-VACCINE")
   CD_VACCINE("CD-VACCINE"),
   LOCAL("LOCAL");

   private final String value;

   private CDMEDICATIONschemes(String v) {
      this.value = v;
   }

   public String value() {
      return this.value;
   }

   public static CDMEDICATIONschemes fromValue(String v) {
      CDMEDICATIONschemes[] arr$ = values();
      int len$ = arr$.length;

      for(int i$ = 0; i$ < len$; ++i$) {
         CDMEDICATIONschemes c = arr$[i$];
         if (c.value.equals(v)) {
            return c;
         }
      }

      throw new IllegalArgumentException(v);
   }
}
