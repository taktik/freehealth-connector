package be.fgov.ehealth.recipe.protocol.v1;

import be.fgov.ehealth.commons.protocol.v1.ResponseType;
import be.fgov.ehealth.recipe.core.v1.SecuredContentType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "GetPrescriptionForPrescriberResponseType",
   propOrder = {"securedGetPrescriptionForPrescriberResponse"}
)
@XmlRootElement(
   name = "GetPrescriptionForPrescriberResponse"
)
public class GetPrescriptionForPrescriberResponse extends ResponseType {
   @XmlElement(
      name = "SecuredGetPrescriptionForPrescriberResponse"
   )
   protected SecuredContentType securedGetPrescriptionForPrescriberResponse;

   public SecuredContentType getSecuredGetPrescriptionForPrescriberResponse() {
      return this.securedGetPrescriptionForPrescriberResponse;
   }

   public void setSecuredGetPrescriptionForPrescriberResponse(SecuredContentType value) {
      this.securedGetPrescriptionForPrescriberResponse = value;
   }
}
