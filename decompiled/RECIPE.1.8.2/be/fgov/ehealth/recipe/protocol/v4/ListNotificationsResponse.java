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
   name = "ListNotificationsResponseType",
   propOrder = {"securedListNotificationsResponse"}
)
@XmlRootElement(
   name = "ListNotificationsResponse"
)
public class ListNotificationsResponse extends StatusResponseType {
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
