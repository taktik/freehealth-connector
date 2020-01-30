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
   name = "PutFeedbackFlagResponseType",
   propOrder = {"securedPutFeedbackFlagResponse"}
)
@XmlRootElement(
   name = "PutFeedbackFlagResponse"
)
public class PutFeedbackFlagResponse extends StatusResponseType {
   @XmlElement(
      name = "SecuredPutFeedbackFlagResponse"
   )
   protected SecuredContentType securedPutFeedbackFlagResponse;

   public SecuredContentType getSecuredPutFeedbackFlagResponse() {
      return this.securedPutFeedbackFlagResponse;
   }

   public void setSecuredPutFeedbackFlagResponse(SecuredContentType value) {
      this.securedPutFeedbackFlagResponse = value;
   }
}
