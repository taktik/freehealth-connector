package be.fgov.ehealth.recipe.protocol.v3;

import be.fgov.ehealth.commons.protocol.v1.ResponseType;
import be.fgov.ehealth.recipe.core.v3.SecuredContentType;
import java.io.Serializable;
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
public class CreatePrescriptionResponse extends ResponseType implements Serializable {
   private static final long serialVersionUID = 1L;
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
