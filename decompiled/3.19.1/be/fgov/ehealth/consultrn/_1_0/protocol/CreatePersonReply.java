package be.fgov.ehealth.consultrn._1_0.protocol;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "CreatePersonReplyType",
   propOrder = {"personCreated", "phoneticResult"}
)
@XmlRootElement(
   name = "CreatePersonReply"
)
public class CreatePersonReply extends ConsultRnReplyType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "PersonCreated"
   )
   protected PersonCreatedType personCreated;
   @XmlElement(
      name = "PhoneticResult"
   )
   protected PhoneticResultType phoneticResult;

   public PersonCreatedType getPersonCreated() {
      return this.personCreated;
   }

   public void setPersonCreated(PersonCreatedType value) {
      this.personCreated = value;
   }

   public PhoneticResultType getPhoneticResult() {
      return this.phoneticResult;
   }

   public void setPhoneticResult(PhoneticResultType value) {
      this.phoneticResult = value;
   }
}
