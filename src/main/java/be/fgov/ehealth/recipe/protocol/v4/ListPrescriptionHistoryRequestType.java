package be.fgov.ehealth.recipe.protocol.v4;

import be.fgov.ehealth.recipe.core.v4.SecuredContentType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "ListPrescriptionHistoryRequestType",
   propOrder = {"securedListPrescriptionHistoryRequest"}
)
public class ListPrescriptionHistoryRequestType extends RequestType {
   @XmlElement(
      name = "SecuredListPrescriptionHistoryRequest",
      required = true
   )
   protected SecuredContentType securedListPrescriptionHistoryRequest;

   public SecuredContentType getSecuredListPrescriptionHistoryRequest() {
      return this.securedListPrescriptionHistoryRequest;
   }

   public void setSecuredListPrescriptionHistoryRequest(SecuredContentType value) {
      this.securedListPrescriptionHistoryRequest = value;
   }
}
