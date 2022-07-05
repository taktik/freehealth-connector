package be.fgov.ehealth.rn.cbsspersonservice.core.v1;

import be.fgov.ehealth.rn.cbsspersonlegaldata.v1.CbssPersonResponseType;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "RegisterPersonResultType",
   propOrder = {"existingPersons", "newlyRegisteredPerson"}
)
public class RegisterPersonResultType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "ExistingPersons"
   )
   protected ExistingPersons existingPersons;
   @XmlElement(
      name = "NewlyRegisteredPerson"
   )
   protected CbssPersonResponseType newlyRegisteredPerson;

   public RegisterPersonResultType() {
   }

   public ExistingPersons getExistingPersons() {
      return this.existingPersons;
   }

   public void setExistingPersons(ExistingPersons value) {
      this.existingPersons = value;
   }

   public CbssPersonResponseType getNewlyRegisteredPerson() {
      return this.newlyRegisteredPerson;
   }

   public void setNewlyRegisteredPerson(CbssPersonResponseType value) {
      this.newlyRegisteredPerson = value;
   }
}
