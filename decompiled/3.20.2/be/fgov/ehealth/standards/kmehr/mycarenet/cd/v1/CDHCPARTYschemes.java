package be.fgov.ehealth.standards.kmehr.mycarenet.cd.v1;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(
   name = "CD-HCPARTYschemes"
)
@XmlEnum
public enum CDHCPARTYschemes {
   @XmlEnumValue("CD-HCPARTY")
   CD_HCPARTY("CD-HCPARTY"),
   @XmlEnumValue("CD-APPLICATION")
   CD_APPLICATION("CD-APPLICATION"),
   @XmlEnumValue("CD-ENCRYPTION-ACTOR")
   CD_ENCRYPTION_ACTOR("CD-ENCRYPTION-ACTOR"),
   @XmlEnumValue("CD-ROLE")
   CD_ROLE("CD-ROLE"),
   LOCAL("LOCAL");

   private final String value;

   private CDHCPARTYschemes(String v) {
      this.value = v;
   }

   public String value() {
      return this.value;
   }

   public static CDHCPARTYschemes fromValue(String v) {
      CDHCPARTYschemes[] arr$ = values();
      int len$ = arr$.length;

      for(int i$ = 0; i$ < len$; ++i$) {
         CDHCPARTYschemes c = arr$[i$];
         if (c.value.equals(v)) {
            return c;
         }
      }

      throw new IllegalArgumentException(v);
   }
}
