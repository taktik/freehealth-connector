package be.fgov.ehealth.insurability.protocol.v2;

import be.fgov.ehealth.commons.protocol.v1.ResponseType;
import be.fgov.ehealth.insurability.core.v2.CommonOutputType;
import be.fgov.ehealth.insurability.core.v2.InsurabilityForPharmacistResponseType;
import be.fgov.ehealth.insurability.core.v2.RecordCommonOutputType;
import be.fgov.ehealth.insurability.core.v2.ReturnInfoType;
import be.fgov.ehealth.insurability.core.v2.SamlAttributeType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "GetInsurabilityForPharmacistResponseType",
   propOrder = {"commonOutput", "recordCommonOutput", "returnInfo", "insurabilityResponse", "extendedInformation"}
)
@XmlRootElement(
   name = "GetInsurabilityForPharmacistResponse"
)
public class GetInsurabilityForPharmacistResponse extends ResponseType {
   @XmlElement(
      name = "CommonOutput"
   )
   protected CommonOutputType commonOutput;
   @XmlElement(
      name = "RecordCommonOutput"
   )
   protected RecordCommonOutputType recordCommonOutput;
   @XmlElement(
      name = "ReturnInfo"
   )
   protected ReturnInfoType returnInfo;
   @XmlElement(
      name = "InsurabilityResponse"
   )
   protected InsurabilityForPharmacistResponseType insurabilityResponse;
   @XmlElement(
      name = "ExtendedInformation"
   )
   protected SamlAttributeType extendedInformation;

   public CommonOutputType getCommonOutput() {
      return this.commonOutput;
   }

   public void setCommonOutput(CommonOutputType value) {
      this.commonOutput = value;
   }

   public RecordCommonOutputType getRecordCommonOutput() {
      return this.recordCommonOutput;
   }

   public void setRecordCommonOutput(RecordCommonOutputType value) {
      this.recordCommonOutput = value;
   }

   public ReturnInfoType getReturnInfo() {
      return this.returnInfo;
   }

   public void setReturnInfo(ReturnInfoType value) {
      this.returnInfo = value;
   }

   public InsurabilityForPharmacistResponseType getInsurabilityResponse() {
      return this.insurabilityResponse;
   }

   public void setInsurabilityResponse(InsurabilityForPharmacistResponseType value) {
      this.insurabilityResponse = value;
   }

   public SamlAttributeType getExtendedInformation() {
      return this.extendedInformation;
   }

   public void setExtendedInformation(SamlAttributeType value) {
      this.extendedInformation = value;
   }
}
