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
   name = "CreateFeedbackResponseType",
   propOrder = {"securedCreateFeedbackResponse"}
)
@XmlRootElement(
   name = "CreateFeedbackResponse"
)
public class CreateFeedbackResponse extends StatusResponseType {
   @XmlElement(
      name = "SecuredCreateFeedbackResponse"
   )
   protected SecuredContentType securedCreateFeedbackResponse;

   public SecuredContentType getSecuredCreateFeedbackResponse() {
      return this.securedCreateFeedbackResponse;
   }

   public void setSecuredCreateFeedbackResponse(SecuredContentType value) {
      this.securedCreateFeedbackResponse = value;
   }
}
