package be.fgov.ehealth.recipe.protocol.v4;

import be.fgov.ehealth.commons.protocol.v2.StatusResponseType;
import be.fgov.ehealth.recipe.core.v4.SecuredContentType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "MarkAsUnDeliveredResponseType",
   propOrder = {"securedMarkAsUnDeliveredResponse"}
)
@XmlRootElement(
   name = "MarkAsUnDeliveredResponse"
)
public class MarkAsUnDeliveredResponse extends StatusResponseType {
   @XmlElement(
      name = "SecuredMarkAsUnDeliveredResponse"
   )
   protected SecuredContentType securedMarkAsUnDeliveredResponse;

   public SecuredContentType getSecuredMarkAsUnDeliveredResponse() {
      return this.securedMarkAsUnDeliveredResponse;
   }

   public void setSecuredMarkAsUnDeliveredResponse(SecuredContentType value) {
      this.securedMarkAsUnDeliveredResponse = value;
   }
}
