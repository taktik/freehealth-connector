package be.fgov.ehealth.medicalagreement.core.v1;

import be.fgov.ehealth.standards.kmehr.schema.v1.Kmehrmessage;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "kmehrresponsetype",
   propOrder = {"request", "acknowledge", "kmehrmessage"}
)
@XmlRootElement(
   name = "kmehrresponse"
)
public class Kmehrresponse implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      required = true
   )
   protected Request request;
   @XmlElement(
      required = true
   )
   protected Acknowledgetype acknowledge;
   protected Kmehrmessage kmehrmessage;

   public Request getRequest() {
      return this.request;
   }

   public void setRequest(Request value) {
      this.request = value;
   }

   public Acknowledgetype getAcknowledge() {
      return this.acknowledge;
   }

   public void setAcknowledge(Acknowledgetype value) {
      this.acknowledge = value;
   }

   public Kmehrmessage getKmehrmessage() {
      return this.kmehrmessage;
   }

   public void setKmehrmessage(Kmehrmessage value) {
      this.kmehrmessage = value;
   }
}
