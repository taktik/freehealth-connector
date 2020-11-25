package be.fgov.ehealth.recipe.protocol.v3;

import be.fgov.ehealth.commons.protocol.v1.ResponseType;
import be.fgov.ehealth.recipe.core.v3.SecuredContentType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "ListFeedbacksResponseType",
   propOrder = {"securedListFeedbacksResponse"}
)
@XmlRootElement(
   name = "ListFeedbacksResponse"
)
public class ListFeedbacksResponse extends ResponseType {
   @XmlElement(
      name = "SecuredListFeedbacksResponse"
   )
   protected SecuredContentType securedListFeedbacksResponse;

   public SecuredContentType getSecuredListFeedbacksResponse() {
      return this.securedListFeedbacksResponse;
   }

   public void setSecuredListFeedbacksResponse(SecuredContentType value) {
      this.securedListFeedbacksResponse = value;
   }
}
