package be.fgov.ehealth.standards.kmehr.mycarenet.cd.v1;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(
   name = "CD-ADDRESSschemes"
)
@XmlEnum
public enum CDADDRESSschemes {
   @XmlEnumValue("CD-ADDRESS")
   CD_ADDRESS("CD-ADDRESS"),
   LOCAL("LOCAL");

   private final String value;

   private CDADDRESSschemes(String v) {
      this.value = v;
   }

   public String value() {
      return this.value;
   }

   public static CDADDRESSschemes fromValue(String v) {
      CDADDRESSschemes[] arr$ = values();
      int len$ = arr$.length;

      for(int i$ = 0; i$ < len$; ++i$) {
         CDADDRESSschemes c = arr$[i$];
         if (c.value.equals(v)) {
            return c;
         }
      }

      throw new IllegalArgumentException(v);
   }
}
