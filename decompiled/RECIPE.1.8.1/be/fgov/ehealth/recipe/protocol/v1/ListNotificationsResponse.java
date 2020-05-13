package be.fgov.ehealth.recipe.protocol.v1;

import be.fgov.ehealth.commons.protocol.v1.ResponseType;
import be.fgov.ehealth.recipe.core.v1.SecuredContentType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "ListNotificationsResponseType",
   propOrder = {"securedListNotificationsResponse"}
)
@XmlRootElement(
   name = "ListNotificationsResponse"
)
public class ListNotificationsResponse extends ResponseType {
   @XmlElement(
      name = "SecuredListNotificationsResponse"
   )
   protected SecuredContentType securedListNotificationsResponse;

   public SecuredContentType getSecuredListNotificationsResponse() {
      return this.securedListNotificationsResponse;
   }

   public void setSecuredListNotificationsResponse(SecuredContentType value) {
      this.securedListNotificationsResponse = value;
   }
}
