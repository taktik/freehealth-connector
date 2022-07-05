package be.fgov.ehealth.standards.kmehr.mycarenet.cd.v1;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(
   name = "CD-ERROR-MYCARENETschemes"
)
@XmlEnum
public enum CDERRORMYCARENETschemes {
   @XmlEnumValue("CD-ERROR")
   CD_ERROR("CD-ERROR"),
   @XmlEnumValue("CD-REFUSAL-MYCARENET")
   CD_REFUSAL_MYCARENET("CD-REFUSAL-MYCARENET");

   private final String value;

   private CDERRORMYCARENETschemes(String v) {
      this.value = v;
   }

   public String value() {
      return this.value;
   }

   public static CDERRORMYCARENETschemes fromValue(String v) {
      CDERRORMYCARENETschemes[] var1 = values();
      int var2 = var1.length;

      for(int var3 = 0; var3 < var2; ++var3) {
         CDERRORMYCARENETschemes c = var1[var3];
         if (c.value.equals(v)) {
            return c;
         }
      }

      throw new IllegalArgumentException(v);
   }
}
