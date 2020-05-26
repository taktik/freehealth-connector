package be.fgov.ehealth.recipe.protocol.v4;

import be.fgov.ehealth.recipe.core.v4.SecuredContentType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "MarkAsUnDeliveredRequestType",
   propOrder = {"securedMarkAsUnDeliveredRequest"}
)
@XmlRootElement(
   name = "MarkAsUnDeliveredRequest"
)
public class MarkAsUnDeliveredRequest extends RequestType {
   @XmlElement(
      name = "SecuredMarkAsUnDeliveredRequest",
      required = true
   )
   protected SecuredContentType securedMarkAsUnDeliveredRequest;

   public SecuredContentType getSecuredMarkAsUnDeliveredRequest() {
      return this.securedMarkAsUnDeliveredRequest;
   }

   public void setSecuredMarkAsUnDeliveredRequest(SecuredContentType value) {
      this.securedMarkAsUnDeliveredRequest = value;
   }
}
