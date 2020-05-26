package be.fgov.ehealth.insurability.protocol.v1;

import be.fgov.ehealth.commons.protocol.v1.RequestType;
import be.fgov.ehealth.insurability.core.v1.CommonInputType;
import be.fgov.ehealth.insurability.core.v1.InsurabilityForPharmacistRequestType;
import be.fgov.ehealth.insurability.core.v1.RecordCommonInputType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "GetInsurabilityForPharmacistRequestType",
   propOrder = {"commonInput", "recordCommonInput", "insurabilityRequest"}
)
@XmlRootElement(
   name = "GetInsurabilityForPharmacistRequest"
)
public class GetInsurabilityForPharmacistRequest extends RequestType {
   @XmlElement(
      name = "CommonInput",
      required = true
   )
   protected CommonInputType commonInput;
   @XmlElement(
      name = "RecordCommonInput",
      required = true
   )
   protected RecordCommonInputType recordCommonInput;
   @XmlElement(
      name = "InsurabilityRequest",
      required = true
   )
   protected InsurabilityForPharmacistRequestType insurabilityRequest;

   public CommonInputType getCommonInput() {
      return this.commonInput;
   }

   public void setCommonInput(CommonInputType value) {
      this.commonInput = value;
   }

   public RecordCommonInputType getRecordCommonInput() {
      return this.recordCommonInput;
   }

   public void setRecordCommonInput(RecordCommonInputType value) {
      this.recordCommonInput = value;
   }

   public InsurabilityForPharmacistRequestType getInsurabilityRequest() {
      return this.insurabilityRequest;
   }

   public void setInsurabilityRequest(InsurabilityForPharmacistRequestType value) {
      this.insurabilityRequest = value;
   }
}
