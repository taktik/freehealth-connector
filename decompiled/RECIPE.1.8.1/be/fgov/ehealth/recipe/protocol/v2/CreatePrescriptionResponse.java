package be.fgov.ehealth.recipe.protocol.v2;

import be.fgov.ehealth.commons.protocol.v1.ResponseType;
import be.fgov.ehealth.recipe.core.v2.SecuredContentType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "CreatePrescriptionResponseType",
   propOrder = {"securedCreatePrescriptionResponse"}
)
@XmlRootElement(
   name = "CreatePrescriptionResponse"
)
public class CreatePrescriptionResponse extends ResponseType {
   @XmlElement(
      name = "SecuredCreatePrescriptionResponse"
   )
   protected SecuredContentType securedCreatePrescriptionResponse;

   public SecuredContentType getSecuredCreatePrescriptionResponse() {
      return this.securedCreatePrescriptionResponse;
   }

   public void setSecuredCreatePrescriptionResponse(SecuredContentType value) {
      this.securedCreatePrescriptionResponse = value;
   }
}
