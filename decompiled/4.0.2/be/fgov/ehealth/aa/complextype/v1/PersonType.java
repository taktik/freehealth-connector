package be.fgov.ehealth.aa.complextype.v1;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "PersonType",
   propOrder = {"inss", "firstName", "lastName"}
)
@XmlSeeAlso({Responsible.class, PersonWithFunction.class})
public class PersonType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Inss",
      required = true
   )
   protected String inss;
   @XmlElement(
      name = "FirstName",
      required = true
   )
   protected String firstName;
   @XmlElement(
      name = "LastName",
      required = true
   )
   protected String lastName;

   public PersonType() {
   }

   public String getInss() {
      return this.inss;
   }

   public void setInss(String value) {
      this.inss = value;
   }

   public String getFirstName() {
      return this.firstName;
   }

   public void setFirstName(String value) {
      this.firstName = value;
   }

   public String getLastName() {
      return this.lastName;
   }

   public void setLastName(String value) {
      this.lastName = value;
   }
}
