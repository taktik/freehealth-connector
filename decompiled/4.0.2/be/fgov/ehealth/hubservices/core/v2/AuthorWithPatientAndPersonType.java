package be.fgov.ehealth.hubservices.core.v2;

import be.fgov.ehealth.standards.kmehr.schema.v1.AuthorType;
import be.fgov.ehealth.standards.kmehr.schema.v1.PersonTypeLight;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "AuthorWithPatientAndPersonType",
   propOrder = {"patient", "person"}
)
public class AuthorWithPatientAndPersonType extends AuthorType implements Serializable {
   private static final long serialVersionUID = 1L;
   protected PatientIdType patient;
   protected PersonTypeLight person;

   public AuthorWithPatientAndPersonType() {
   }

   public PatientIdType getPatient() {
      return this.patient;
   }

   public void setPatient(PatientIdType value) {
      this.patient = value;
   }

   public PersonTypeLight getPerson() {
      return this.person;
   }

   public void setPerson(PersonTypeLight value) {
      this.person = value;
   }
}
