package be.fgov.ehealth.insurability.protocol.v1;

import be.fgov.ehealth.commons.protocol.v1.ResponseType;
import be.fgov.ehealth.insurability.core.v1.CommonOutputType;
import be.fgov.ehealth.insurability.core.v1.InsurabilityForPharmacistResponseType;
import be.fgov.ehealth.insurability.core.v1.RecordCommonOutputType;
import be.fgov.ehealth.insurability.core.v1.ReturnInfoType;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "GetInsurabilityForPharmacistResponseType",
   propOrder = {"commonOutput", "recordCommonOutput", "returnInfo", "insurabilityResponse"}
)
@XmlRootElement(
   name = "GetInsurabilityForPharmacistResponse"
)
public class GetInsurabilityForPharmacistResponse extends ResponseType implements Serializable {
   private static final long serialVersionUID = 1L;
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
}
