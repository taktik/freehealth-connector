package be.fgov.ehealth.consultrn.protocol.v2;

import be.fgov.ehealth.consultrn.commons.core.v3.PersonRequestType;
import be.fgov.ehealth.consultrn.commons.protocol.v3.ConsultRnRequestType;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "RegisterPersonRequestType",
   propOrder = {"person"}
)
@XmlRootElement(
   name = "RegisterPersonRequest"
)
public class RegisterPersonRequest extends ConsultRnRequestType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Person",
      required = true
   )
   protected PersonRequestType person;

   public RegisterPersonRequest() {
   }

   public PersonRequestType getPerson() {
      return this.person;
   }

   public void setPerson(PersonRequestType value) {
      this.person = value;
   }
}
