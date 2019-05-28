package be.fgov.ehealth.genericinsurability.core.v1;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(
   name = "SexType"
)
@XmlEnum
public enum SexType {
   @XmlEnumValue("male")
   MALE("male"),
   @XmlEnumValue("female")
   FEMALE("female");

   private final String value;

   private SexType(String v) {
      this.value = v;
   }

   public String value() {
      return this.value;
   }

   public static SexType fromValue(String v) {
      SexType[] arr$ = values();
      int len$ = arr$.length;

      for(int i$ = 0; i$ < len$; ++i$) {
         SexType c = arr$[i$];
         if (c.value.equals(v)) {
            return c;
         }
      }

      throw new IllegalArgumentException(v);
   }
}
