package be.fgov.ehealth.recipe.protocol.v4;

import be.fgov.ehealth.recipe.core.v4.SecuredContentType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "MarkAsDeliveredRequestType",
   propOrder = {"securedMarkAsDeliveredRequest"}
)
@XmlRootElement(
   name = "MarkAsDeliveredRequest"
)
public class MarkAsDeliveredRequest extends RequestType {
   @XmlElement(
      name = "SecuredMarkAsDeliveredRequest",
      required = true
   )
   protected SecuredContentType securedMarkAsDeliveredRequest;

   public SecuredContentType getSecuredMarkAsDeliveredRequest() {
      return this.securedMarkAsDeliveredRequest;
   }

   public void setSecuredMarkAsDeliveredRequest(SecuredContentType value) {
      this.securedMarkAsDeliveredRequest = value;
   }
}
