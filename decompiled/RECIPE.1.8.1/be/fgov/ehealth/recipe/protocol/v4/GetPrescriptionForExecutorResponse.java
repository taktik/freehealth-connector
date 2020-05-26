package be.fgov.ehealth.recipe.protocol.v4;

import be.fgov.ehealth.commons.protocol.v2.StatusResponseType;
import be.fgov.ehealth.insurability.protocol.v2.GetInsurabilityForPharmacistResponse;
import be.fgov.ehealth.recipe.core.v4.SecuredContentType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "GetPrescriptionForExecutorResponseType",
   propOrder = {"securedGetPrescriptionForExecutorResponse", "prescriptionType", "getInsurabilityForPharmacistResponse"}
)
@XmlRootElement(
   name = "GetPrescriptionForExecutorResponse"
)
public class GetPrescriptionForExecutorResponse extends StatusResponseType {
   @XmlElement(
      name = "SecuredGetPrescriptionForExecutorResponse"
   )
   protected SecuredContentType securedGetPrescriptionForExecutorResponse;
   @XmlElement(
      name = "PrescriptionType"
   )
   protected String prescriptionType;
   @XmlElement(
      name = "GetInsurabilityForPharmacistResponse"
   )
   protected GetInsurabilityForPharmacistResponse getInsurabilityForPharmacistResponse;

   public SecuredContentType getSecuredGetPrescriptionForExecutorResponse() {
      return this.securedGetPrescriptionForExecutorResponse;
   }

   public void setSecuredGetPrescriptionForExecutorResponse(SecuredContentType value) {
      this.securedGetPrescriptionForExecutorResponse = value;
   }

   public String getPrescriptionType() {
      return this.prescriptionType;
   }

   public void setPrescriptionType(String value) {
      this.prescriptionType = value;
   }

   public GetInsurabilityForPharmacistResponse getGetInsurabilityForPharmacistResponse() {
      return this.getInsurabilityForPharmacistResponse;
   }

   public void setGetInsurabilityForPharmacistResponse(GetInsurabilityForPharmacistResponse value) {
      this.getInsurabilityForPharmacistResponse = value;
   }
}
