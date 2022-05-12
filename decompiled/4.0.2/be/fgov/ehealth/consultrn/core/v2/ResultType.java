package be.fgov.ehealth.consultrn.core.v2;

import be.fgov.ehealth.consultrn.commons.core.v3.PersonResponseType;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "ResultType",
   propOrder = {"existingPersons", "newlyRegisteredPerson"}
)
public class ResultType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "ExistingPersons"
   )
   protected ExistingPersonsType existingPersons;
   @XmlElement(
      name = "NewlyRegisteredPerson"
   )
   protected PersonResponseType newlyRegisteredPerson;

   public ResultType() {
   }

   public ExistingPersonsType getExistingPersons() {
      return this.existingPersons;
   }

   public void setExistingPersons(ExistingPersonsType value) {
      this.existingPersons = value;
   }

   public PersonResponseType getNewlyRegisteredPerson() {
      return this.newlyRegisteredPerson;
   }

   public void setNewlyRegisteredPerson(PersonResponseType value) {
      this.newlyRegisteredPerson = value;
   }
}
