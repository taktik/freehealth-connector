package be.apb.standards.smoa.schema.v1;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;

@XmlType(
   name = "OrganizationIdType"
)
@XmlEnum
public enum OrganizationIdType {
   NIHII,
   CBE,
   EHP;

   public String value() {
      return this.name();
   }

   public static OrganizationIdType fromValue(String v) {
      return valueOf(v);
   }
}
