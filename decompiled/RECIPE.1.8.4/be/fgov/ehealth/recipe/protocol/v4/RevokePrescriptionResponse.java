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
   name = "RevokePrescriptionResponseType",
   propOrder = {"securedRevokePrescriptionResponse"}
)
@XmlRootElement(
   name = "RevokePrescriptionResponse"
)
public class RevokePrescriptionResponse extends StatusResponseType {
   @XmlElement(
      name = "SecuredRevokePrescriptionResponse"
   )
   protected SecuredContentType securedRevokePrescriptionResponse;

   public SecuredContentType getSecuredRevokePrescriptionResponse() {
      return this.securedRevokePrescriptionResponse;
   }

   public void setSecuredRevokePrescriptionResponse(SecuredContentType value) {
      this.securedRevokePrescriptionResponse = value;
   }
}
