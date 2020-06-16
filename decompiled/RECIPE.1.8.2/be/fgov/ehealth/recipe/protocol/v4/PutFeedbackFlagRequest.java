package be.fgov.ehealth.recipe.protocol.v4;

import be.fgov.ehealth.recipe.core.v4.SecuredContentType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "PutFeedbackFlagRequestType",
   propOrder = {"securedPutFeedbackFlagRequest"}
)
@XmlRootElement(
   name = "PutFeedbackFlagRequest"
)
public class PutFeedbackFlagRequest extends RequestType {
   @XmlElement(
      name = "SecuredPutFeedbackFlagRequest",
      required = true
   )
   protected SecuredContentType securedPutFeedbackFlagRequest;

   public SecuredContentType getSecuredPutFeedbackFlagRequest() {
      return this.securedPutFeedbackFlagRequest;
   }

   public void setSecuredPutFeedbackFlagRequest(SecuredContentType value) {
      this.securedPutFeedbackFlagRequest = value;
   }
}
