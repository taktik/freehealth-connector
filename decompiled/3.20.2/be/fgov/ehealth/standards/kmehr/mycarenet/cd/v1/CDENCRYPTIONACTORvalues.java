package be.fgov.ehealth.standards.kmehr.mycarenet.cd.v1;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(
   name = "CD-ENCRYPTION-ACTORvalues"
)
@XmlEnum
public enum CDENCRYPTIONACTORvalues {
   NIHII("NIHII"),
   @XmlEnumValue("NIHII-HOSPITAL")
   NIHII_HOSPITAL("NIHII-HOSPITAL"),
   @XmlEnumValue("NIHII-PHARMACY")
   NIHII_PHARMACY("NIHII-PHARMACY"),
   CBE("CBE"),
   INSS("INSS"),
   EHP("EHP"),
   SSIN("SSIN");

   private final String value;

   private CDENCRYPTIONACTORvalues(String v) {
      this.value = v;
   }

   public String value() {
      return this.value;
   }

   public static CDENCRYPTIONACTORvalues fromValue(String v) {
      CDENCRYPTIONACTORvalues[] arr$ = values();
      int len$ = arr$.length;

      for(int i$ = 0; i$ < len$; ++i$) {
         CDENCRYPTIONACTORvalues c = arr$[i$];
         if (c.value.equals(v)) {
            return c;
         }
      }

      throw new IllegalArgumentException(v);
   }
}
