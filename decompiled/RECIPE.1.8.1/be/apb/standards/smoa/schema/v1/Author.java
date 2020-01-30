package be.apb.standards.smoa.schema.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "Author",
   propOrder = {"organization", "person"}
)
public class Author {
   protected Organization organization;
   protected Person person;

   public Organization getOrganization() {
      return this.organization;
   }

   public void setOrganization(Organization value) {
      this.organization = value;
   }

   public Person getPerson() {
      return this.person;
   }

   public void setPerson(Person value) {
      this.person = value;
   }
}
