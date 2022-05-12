package be.fgov.ehealth.hubservices.core.v3;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(
   name = "LocalSearchType"
)
@XmlEnum
public enum LocalSearchType {
   @XmlEnumValue("local")
   LOCAL("local"),
   @XmlEnumValue("global")
   GLOBAL("global"),
   @XmlEnumValue("external")
   EXTERNAL("external");

   private final String value;

   private LocalSearchType(String v) {
      this.value = v;
   }

   public String value() {
      return this.value;
   }

   public static LocalSearchType fromValue(String v) {
      LocalSearchType[] var1 = values();
      int var2 = var1.length;

      for(int var3 = 0; var3 < var2; ++var3) {
         LocalSearchType c = var1[var3];
         if (c.value.equals(v)) {
            return c;
         }
      }

      throw new IllegalArgumentException(v);
   }
}
