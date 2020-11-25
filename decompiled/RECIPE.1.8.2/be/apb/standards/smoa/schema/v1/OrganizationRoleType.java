package be.apb.standards.smoa.schema.v1;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;

@XmlType(
   name = "OrganizationRoleType"
)
@XmlEnum
public enum OrganizationRoleType {
   ORG_HOSPITAL,
   ORG_NURSING,
   ORG_PHARMACY,
   ORG_HOMECARE,
   ORG_RESIDENTIALCARE;

   public String value() {
      return this.name();
   }

   public static OrganizationRoleType fromValue(String v) {
      return valueOf(v);
   }
}
