package be.fgov.ehealth.recipe.protocol.v4;

import be.fgov.ehealth.recipe.core.v4.SecuredContentType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "GetOpenPrescriptionListRequestType",
   propOrder = {"securedGetOpenPrescriptionListRequest"}
)
public class GetOpenPrescriptionListRequestType extends RequestType {
   @XmlElement(
      name = "SecuredGetOpenPrescriptionListRequest",
      required = true
   )
   protected SecuredContentType securedGetOpenPrescriptionListRequest;

   public SecuredContentType getSecuredGetOpenPrescriptionListRequest() {
      return this.securedGetOpenPrescriptionListRequest;
   }

   public void setSecuredGetOpenPrescriptionListRequest(SecuredContentType value) {
      this.securedGetOpenPrescriptionListRequest = value;
   }
}
