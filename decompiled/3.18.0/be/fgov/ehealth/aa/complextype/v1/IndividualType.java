package be.fgov.ehealth.aa.complextype.v1;

import be.fgov.ehealth.addressbook.core.v1.IndividualContactInformationType;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "IndividualType",
   propOrder = {"ssin", "lastName", "firstName", "middleNames", "language", "gender", "birthDate", "deathDate"}
)
@XmlSeeAlso({IndividualContactInformationType.class, HealthCareProfessional.class})
public class IndividualType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "SSIN"
   )
   protected String ssin;
   @XmlElement(
      name = "LastName",
      required = true
   )
   protected String lastName;
   @XmlElement(
      name = "FirstName"
   )
   protected String firstName;
   @XmlElement(
      name = "MiddleNames"
   )
   protected String middleNames;
   @XmlElement(
      name = "Language"
   )
   protected String language;
   @XmlElement(
      name = "Gender"
   )
   protected String gender;
   @XmlElement(
      name = "BirthDate"
   )
   protected String birthDate;
   @XmlElement(
      name = "DeathDate"
   )
   protected String deathDate;

   public String getSSIN() {
      return this.ssin;
   }

   public void setSSIN(String value) {
      this.ssin = value;
   }

   public String getLastName() {
      return this.lastName;
   }

   public void setLastName(String value) {
      this.lastName = value;
   }

   public String getFirstName() {
      return this.firstName;
   }

   public void setFirstName(String value) {
      this.firstName = value;
   }

   public String getMiddleNames() {
      return this.middleNames;
   }

   public void setMiddleNames(String value) {
      this.middleNames = value;
   }

   public String getLanguage() {
      return this.language;
   }

   public void setLanguage(String value) {
      this.language = value;
   }

   public String getGender() {
      return this.gender;
   }

   public void setGender(String value) {
      this.gender = value;
   }

   public String getBirthDate() {
      return this.birthDate;
   }

   public void setBirthDate(String value) {
      this.birthDate = value;
   }

   public String getDeathDate() {
      return this.deathDate;
   }

   public void setDeathDate(String value) {
      this.deathDate = value;
   }
}
