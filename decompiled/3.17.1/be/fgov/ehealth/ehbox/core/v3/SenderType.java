package be.fgov.ehealth.ehbox.core.v3;

import be.fgov.ehealth.commons.core.v1.IdentifierType;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "SenderType",
   propOrder = {"quality", "name", "firstName", "personInOrganisation"}
)
public class SenderType extends IdentifierType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Quality",
      required = true
   )
   protected String quality;
   @XmlElement(
      name = "Name",
      required = true
   )
   protected String name;
   @XmlElement(
      name = "FirstName"
   )
   protected String firstName;
   @XmlElement(
      name = "PersonInOrganisation"
   )
   protected String personInOrganisation;

   public String getQuality() {
      return this.quality;
   }

   public void setQuality(String value) {
      this.quality = value;
   }

   public String getName() {
      return this.name;
   }

   public void setName(String value) {
      this.name = value;
   }

   public String getFirstName() {
      return this.firstName;
   }

   public void setFirstName(String value) {
      this.firstName = value;
   }

   public String getPersonInOrganisation() {
      return this.personInOrganisation;
   }

   public void setPersonInOrganisation(String value) {
      this.personInOrganisation = value;
   }
}
