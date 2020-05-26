package be.fgov.ehealth.recipe.protocol.v4;

import be.fgov.ehealth.commons.protocol.v2.StatusResponseType;
import be.fgov.ehealth.recipe.core.v4.SecuredContentType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "ListPrescriptionHistoryResponseType",
   propOrder = {"securedListPrescriptionHistoryResponse"}
)
public class ListPrescriptionHistoryResponseType extends StatusResponseType {
   @XmlElement(
      name = "SecuredListPrescriptionHistoryResponse"
   )
   protected SecuredContentType securedListPrescriptionHistoryResponse;

   public SecuredContentType getSecuredListPrescriptionHistoryResponse() {
      return this.securedListPrescriptionHistoryResponse;
   }

   public void setSecuredListPrescriptionHistoryResponse(SecuredContentType value) {
      this.securedListPrescriptionHistoryResponse = value;
   }
}
