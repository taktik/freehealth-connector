package be.fgov.ehealth.medicalagreement.core.v1;

import be.fgov.ehealth.standards.kmehr.schema.v1.Kmehrmessage;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "",
   propOrder = {"kmehrmessage"}
)
public class Request implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      required = true
   )
   protected Kmehrmessage kmehrmessage;

   public Request() {
   }

   public Kmehrmessage getKmehrmessage() {
      return this.kmehrmessage;
   }

   public void setKmehrmessage(Kmehrmessage value) {
      this.kmehrmessage = value;
   }
}
