package be.fgov.ehealth.recipe.protocol.v4;

import be.fgov.ehealth.recipe.core.v4.SecuredContentType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "ListRidsHistoryRequestType",
   propOrder = {"securedListRidsHistoryRequest"}
)
@XmlRootElement(
   name = "ListRidsHistoryRequest"
)
public class ListRidsHistoryRequest extends RequestType {
   @XmlElement(
      name = "SecuredListRidsHistoryRequest",
      required = true
   )
   protected SecuredContentType securedListRidsHistoryRequest;

   public SecuredContentType getSecuredListRidsHistoryRequest() {
      return this.securedListRidsHistoryRequest;
   }

   public void setSecuredListRidsHistoryRequest(SecuredContentType value) {
      this.securedListRidsHistoryRequest = value;
   }
}
