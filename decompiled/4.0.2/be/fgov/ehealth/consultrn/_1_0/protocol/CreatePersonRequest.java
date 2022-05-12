package be.fgov.ehealth.consultrn._1_0.protocol;

import be.fgov.ehealth.consultrn._1_0.core.PersonDataType;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "CreatePersonRequestType",
   propOrder = {"personData"}
)
@XmlRootElement(
   name = "CreatePersonRequest"
)
public class CreatePersonRequest extends ConsultRnRequestType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "PersonData",
      required = true
   )
   protected PersonDataType personData;

   public CreatePersonRequest() {
   }

   public PersonDataType getPersonData() {
      return this.personData;
   }

   public void setPersonData(PersonDataType value) {
      this.personData = value;
   }
}
