package be.fgov.ehealth.recipe.protocol.v4;

import be.fgov.ehealth.recipe.core.v4.SecuredContentType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "CreateFeedbackRequestType",
   propOrder = {"securedCreateFeedbackRequest"}
)
@XmlRootElement(
   name = "CreateFeedbackRequest"
)
public class CreateFeedbackRequest extends RequestType {
   @XmlElement(
      name = "SecuredCreateFeedbackRequest",
      required = true
   )
   protected SecuredContentType securedCreateFeedbackRequest;

   public SecuredContentType getSecuredCreateFeedbackRequest() {
      return this.securedCreateFeedbackRequest;
   }

   public void setSecuredCreateFeedbackRequest(SecuredContentType value) {
      this.securedCreateFeedbackRequest = value;
   }
}
