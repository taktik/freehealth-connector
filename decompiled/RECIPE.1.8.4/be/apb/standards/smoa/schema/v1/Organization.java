package be.apb.standards.smoa.schema.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "Organization",
   propOrder = {"name", "organizationId", "organizationIdType", "role"}
)
public class Organization extends AbstractAuthor {
   protected String name;
   @XmlElement(
      required = true
   )
   protected String organizationId;
   @XmlElement(
      required = true
   )
   @XmlSchemaType(
      name = "string"
   )
   protected OrganizationIdType organizationIdType;
   @XmlElement(
      required = true
   )
   @XmlSchemaType(
      name = "string"
   )
   protected OrganizationRoleType role;

   public String getName() {
      return this.name;
   }

   public void setName(String value) {
      this.name = value;
   }

   public String getOrganizationId() {
      return this.organizationId;
   }

   public void setOrganizationId(String value) {
      this.organizationId = value;
   }

   public OrganizationIdType getOrganizationIdType() {
      return this.organizationIdType;
   }

   public void setOrganizationIdType(OrganizationIdType value) {
      this.organizationIdType = value;
   }

   public OrganizationRoleType getRole() {
      return this.role;
   }

   public void setRole(OrganizationRoleType value) {
      this.role = value;
   }
}
