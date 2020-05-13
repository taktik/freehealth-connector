package be.fgov.ehealth.etee.ra.aqdr._1_0.protocol;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(
   name = "EntityType"
)
@XmlEnum
public enum EntityType {
   @XmlEnumValue("Natural")
   NATURAL("Natural"),
   @XmlEnumValue("Legal")
   LEGAL("Legal");

   private final String value;

   private EntityType(String v) {
      this.value = v;
   }

   public String value() {
      return this.value;
   }

   public static EntityType fromValue(String v) {
      EntityType[] arr$ = values();
      int len$ = arr$.length;

      for(int i$ = 0; i$ < len$; ++i$) {
         EntityType c = arr$[i$];
         if (c.value.equals(v)) {
            return c;
         }
      }

      throw new IllegalArgumentException(v);
   }
}
