package be.cin.nip.sync.reg.v1;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "registrationsAnswerType",
   propOrder = {"registrant", "registrationAnswer"}
)
@XmlRootElement(
   name = "registrationsAnswer"
)
public class RegistrationsAnswer implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      required = true
   )
   protected RegistrantType registrant;
   @XmlElement(
      required = true
   )
   protected RegistrationAnswerType registrationAnswer;

   public RegistrationsAnswer() {
   }

   public RegistrantType getRegistrant() {
      return this.registrant;
   }

   public void setRegistrant(RegistrantType value) {
      this.registrant = value;
   }

   public RegistrationAnswerType getRegistrationAnswer() {
      return this.registrationAnswer;
   }

   public void setRegistrationAnswer(RegistrationAnswerType value) {
      this.registrationAnswer = value;
   }
}
