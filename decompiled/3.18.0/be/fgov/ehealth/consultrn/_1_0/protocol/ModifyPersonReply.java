package be.fgov.ehealth.consultrn._1_0.protocol;

import be.fgov.ehealth.consultrn._1_0.core.PersonType;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "ModifyPersonReplyType",
   propOrder = {"person"}
)
@XmlRootElement(
   name = "ModifyPersonReply"
)
public class ModifyPersonReply extends ConsultRnReplyType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Person"
   )
   protected PersonType person;

   public PersonType getPerson() {
      return this.person;
   }

   public void setPerson(PersonType value) {
      this.person = value;
   }
}
