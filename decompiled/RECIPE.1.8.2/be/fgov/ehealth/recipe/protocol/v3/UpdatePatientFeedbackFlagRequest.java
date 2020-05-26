package be.fgov.ehealth.recipe.protocol.v3;

import be.fgov.ehealth.commons.protocol.v1.RequestType;
import be.fgov.ehealth.recipe.core.v3.SecuredContentType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "UpdatePatientFeedbackFlagRequestType",
   propOrder = {"securedUpdatePatientFeedbackFlagRequest"}
)
@XmlRootElement(
   name = "UpdatePatientFeedbackFlagRequest"
)
public class UpdatePatientFeedbackFlagRequest extends RequestType {
   @XmlElement(
      name = "SecuredUpdatePatientFeedbackFlagRequest",
      required = true
   )
   protected SecuredContentType securedUpdatePatientFeedbackFlagRequest;

   public SecuredContentType getSecuredUpdatePatientFeedbackFlagRequest() {
      return this.securedUpdatePatientFeedbackFlagRequest;
   }

   public void setSecuredUpdatePatientFeedbackFlagRequest(SecuredContentType value) {
      this.securedUpdatePatientFeedbackFlagRequest = value;
   }
}
