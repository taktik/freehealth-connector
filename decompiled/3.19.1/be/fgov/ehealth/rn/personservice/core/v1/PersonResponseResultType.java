package be.fgov.ehealth.rn.personservice.core.v1;

import be.fgov.ehealth.rn.personlegaldata.v1.PersonResponseType;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "PersonResponseResultType",
   propOrder = {"person"}
)
public class PersonResponseResultType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Person",
      required = true
   )
   protected PersonResponseType person;

   public PersonResponseType getPerson() {
      return this.person;
   }

   public void setPerson(PersonResponseType value) {
      this.person = value;
   }
}
