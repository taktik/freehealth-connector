package be.fgov.ehealth.consultrn._1_0.protocol;

import be.fgov.ehealth.consultrn._1_0.core.PersonDataType;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "PersonCreatedType",
   propOrder = {"ssin", "personData"}
)
public class PersonCreatedType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "SSIN",
      required = true
   )
   protected String ssin;
   @XmlElement(
      name = "PersonData"
   )
   protected PersonDataType personData;

   public String getSSIN() {
      return this.ssin;
   }

   public void setSSIN(String value) {
      this.ssin = value;
   }

   public PersonDataType getPersonData() {
      return this.personData;
   }

   public void setPersonData(PersonDataType value) {
      this.personData = value;
   }
}
