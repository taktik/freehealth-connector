package be.fgov.ehealth.recipe.protocol.v3;

import be.fgov.ehealth.commons.protocol.v1.ResponseType;
import be.fgov.ehealth.insurability.protocol.v2.GetInsurabilityForPharmacistResponse;
import be.fgov.ehealth.recipe.core.v3.SecuredContentType;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "GetPrescriptionForExecutorResponseType",
   propOrder = {"securedGetPrescriptionForExecutorResponse", "patientId", "prescriptionType", "getInsurabilityForPharmacistResponse"}
)
@XmlRootElement(
   name = "GetPrescriptionForExecutorResponse"
)
public class GetPrescriptionForExecutorResponse extends ResponseType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "SecuredGetPrescriptionForExecutorResponse"
   )
   protected SecuredContentType securedGetPrescriptionForExecutorResponse;
   @XmlElement(
      name = "PatientId"
   )
   protected String patientId;
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

   public String getPatientId() {
      return this.patientId;
   }

   public void setPatientId(String value) {
      this.patientId = value;
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
