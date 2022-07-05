package be.fgov.ehealth.rn.personservice.core.v1;

import be.fgov.ehealth.rn.baselegaldata.v1.PersonIdentificationsResponseType;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "PersonResponseResultsType",
   propOrder = {"personIdentifications"}
)
public class PersonResponseResultsType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "PersonIdentifications",
      required = true
   )
   protected PersonIdentificationsResponseType personIdentifications;

   public PersonResponseResultsType() {
   }

   public PersonIdentificationsResponseType getPersonIdentifications() {
      return this.personIdentifications;
   }

   public void setPersonIdentifications(PersonIdentificationsResponseType value) {
      this.personIdentifications = value;
   }
}
