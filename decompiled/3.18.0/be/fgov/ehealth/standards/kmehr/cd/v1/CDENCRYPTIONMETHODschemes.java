package be.fgov.ehealth.standards.kmehr.cd.v1;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(
   name = "CD-ENCRYPTION-METHODschemes"
)
@XmlEnum
public enum CDENCRYPTIONMETHODschemes {
   @XmlEnumValue("CD-ENCRYPTION-METHOD")
   CD_ENCRYPTION_METHOD("CD-ENCRYPTION-METHOD");

   private final String value;

   private CDENCRYPTIONMETHODschemes(String v) {
      this.value = v;
   }

   public String value() {
      return this.value;
   }

   public static CDENCRYPTIONMETHODschemes fromValue(String v) {
      CDENCRYPTIONMETHODschemes[] arr$ = values();
      int len$ = arr$.length;

      for(int i$ = 0; i$ < len$; ++i$) {
         CDENCRYPTIONMETHODschemes c = arr$[i$];
         if (c.value.equals(v)) {
            return c;
         }
      }

      throw new IllegalArgumentException(v);
   }
}
