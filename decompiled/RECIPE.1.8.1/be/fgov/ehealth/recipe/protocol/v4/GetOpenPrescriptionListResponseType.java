package be.fgov.ehealth.recipe.protocol.v4;

import be.fgov.ehealth.commons.protocol.v2.StatusResponseType;
import be.fgov.ehealth.recipe.core.v4.SecuredContentType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "GetOpenPrescriptionListResponseType",
   propOrder = {"securedGetOpenPrescriptionListResponse"}
)
public class GetOpenPrescriptionListResponseType extends StatusResponseType {
   @XmlElement(
      name = "SecuredGetOpenPrescriptionListResponse"
   )
   protected SecuredContentType securedGetOpenPrescriptionListResponse;

   public SecuredContentType getSecuredGetOpenPrescriptionListResponse() {
      return this.securedGetOpenPrescriptionListResponse;
   }

   public void setSecuredGetOpenPrescriptionListResponse(SecuredContentType value) {
      this.securedGetOpenPrescriptionListResponse = value;
   }
}
