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
   name = "PutRidsInProcessResponseType",
   propOrder = {"securedPutRidsInProcessResponse"}
)
@XmlRootElement(
   name = "PutRidsInProcessResponse"
)
public class PutRidsInProcessResponse extends StatusResponseType {
   @XmlElement(
      name = "SecuredPutRidsInProcessResponse"
   )
   protected SecuredContentType securedPutRidsInProcessResponse;

   public SecuredContentType getSecuredPutRidsInProcessResponse() {
      return this.securedPutRidsInProcessResponse;
   }

   public void setSecuredPutRidsInProcessResponse(SecuredContentType value) {
      this.securedPutRidsInProcessResponse = value;
   }
}
