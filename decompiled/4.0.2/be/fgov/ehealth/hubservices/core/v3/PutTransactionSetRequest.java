package be.fgov.ehealth.hubservices.core.v3;

import be.fgov.ehealth.standards.kmehr.schema.v1.Kmehrmessage;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "PutTransactionSetRequestType",
   propOrder = {"request", "kmehrmessage"}
)
@XmlRootElement(
   name = "PutTransactionSetRequest",
   namespace = "http://www.ehealth.fgov.be/hubservices/protocol/v3"
)
public class PutTransactionSetRequest implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      required = true
   )
   protected RequestType request;
   @XmlElement(
      required = true
   )
   protected Kmehrmessage kmehrmessage;

   public PutTransactionSetRequest() {
   }

   public RequestType getRequest() {
      return this.request;
   }

   public void setRequest(RequestType value) {
      this.request = value;
   }

   public Kmehrmessage getKmehrmessage() {
      return this.kmehrmessage;
   }

   public void setKmehrmessage(Kmehrmessage value) {
      this.kmehrmessage = value;
   }
}
