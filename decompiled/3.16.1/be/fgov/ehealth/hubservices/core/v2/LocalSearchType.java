package be.fgov.ehealth.hubservices.core.v2;

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
      LocalSearchType[] arr$ = values();
      int len$ = arr$.length;

      for(int i$ = 0; i$ < len$; ++i$) {
         LocalSearchType c = arr$[i$];
         if (c.value.equals(v)) {
            return c;
         }
      }

      throw new IllegalArgumentException(v);
   }
}
