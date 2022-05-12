package be.fgov.ehealth.standards.kmehr.id.v1;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(
   name = "ID-PROFESSIONschemes"
)
@XmlEnum
public enum IDPROFESSIONschemes {
   @XmlEnumValue("ID-MEDEX")
   ID_MEDEX("ID-MEDEX"),
   @XmlEnumValue("ID-CBE")
   ID_CBE("ID-CBE");

   private final String value;

   private IDPROFESSIONschemes(String v) {
      this.value = v;
   }

   public String value() {
      return this.value;
   }

   public static IDPROFESSIONschemes fromValue(String v) {
      IDPROFESSIONschemes[] var1 = values();
      int var2 = var1.length;

      for(int var3 = 0; var3 < var2; ++var3) {
         IDPROFESSIONschemes c = var1[var3];
         if (c.value.equals(v)) {
            return c;
         }
      }

      throw new IllegalArgumentException(v);
   }
}
