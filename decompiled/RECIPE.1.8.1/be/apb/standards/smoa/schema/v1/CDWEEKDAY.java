package be.apb.standards.smoa.schema.v1;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;

@XmlType(
   name = "CD-WEEKDAY"
)
@XmlEnum
public enum CDWEEKDAY {
   MONDAY,
   TUESDAY,
   WEDNESDAY,
   THURSDAY,
   FRIDAY,
   SATURDAY,
   SUNDAY;

   public String value() {
      return this.name();
   }

   public static CDWEEKDAY fromValue(String v) {
      return valueOf(v);
   }
}
