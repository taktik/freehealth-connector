package be.apb.standards.smoa.schema.v1;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;

@XmlType(
   name = "AvailabilityStatus"
)
@XmlEnum
public enum AvailabilityStatus {
   ACTIVE,
   ENDED;

   public String value() {
      return this.name();
   }

   public static AvailabilityStatus fromValue(String v) {
      return valueOf(v);
   }
}
