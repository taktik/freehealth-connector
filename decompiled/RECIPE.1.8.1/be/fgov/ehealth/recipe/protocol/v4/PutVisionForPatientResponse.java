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
   name = "PutVisionForPatientResponseType",
   propOrder = {"securedPutVisionForPatientResponse"}
)
@XmlRootElement(
   name = "PutVisionForPatientResponse"
)
public class PutVisionForPatientResponse extends StatusResponseType {
   @XmlElement(
      name = "SecuredPutVisionForPatientResponse"
   )
   protected SecuredContentType securedPutVisionForPatientResponse;

   public SecuredContentType getSecuredPutVisionForPatientResponse() {
      return this.securedPutVisionForPatientResponse;
   }

   public void setSecuredPutVisionForPatientResponse(SecuredContentType value) {
      this.securedPutVisionForPatientResponse = value;
   }
}
