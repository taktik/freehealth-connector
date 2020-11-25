package be.fgov.ehealth.recipe.protocol.v4;

import be.fgov.ehealth.recipe.core.v4.SecuredContentType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "PutVisionForPatientRequestType",
   propOrder = {"securedPutVisionForPatientRequest"}
)
@XmlRootElement(
   name = "PutVisionForPatientRequest"
)
public class PutVisionForPatientRequest extends RequestType {
   @XmlElement(
      name = "SecuredPutVisionForPatientRequest",
      required = true
   )
   protected SecuredContentType securedPutVisionForPatientRequest;

   public SecuredContentType getSecuredPutVisionForPatientRequest() {
      return this.securedPutVisionForPatientRequest;
   }

   public void setSecuredPutVisionForPatientRequest(SecuredContentType value) {
      this.securedPutVisionForPatientRequest = value;
   }
}
