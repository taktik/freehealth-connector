package be.apb.standards.smoa.schema.v1;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;

@XmlType(
   name = "PersonIdType"
)
@XmlEnum
public enum PersonIdType {
   INSS,
   NIHII;

   public String value() {
      return this.name();
   }

   public static PersonIdType fromValue(String v) {
      return valueOf(v);
   }
}
