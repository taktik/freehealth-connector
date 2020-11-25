package be.fgov.ehealth.insurability.protocol.v2;

import be.fgov.ehealth.commons.protocol.v1.RequestType;
import be.fgov.ehealth.insurability.core.v2.CommonInputType;
import be.fgov.ehealth.insurability.core.v2.InsurabilityForPharmacistRequestType;
import be.fgov.ehealth.insurability.core.v2.RecordCommonInputType;
import be.fgov.ehealth.insurability.core.v2.SamlAttributeType;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "GetInsurabilityForPharmacistRequestType",
   propOrder = {"commonInput", "recordCommonInput", "insurabilityRequest", "extendedInformation"}
)
@XmlRootElement(
   name = "GetInsurabilityForPharmacistRequest"
)
public class GetInsurabilityForPharmacistRequest extends RequestType implements Serializable {
   private static final long serialVersionUID = 1L;
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
   @XmlElement(
      name = "ExtendedInformation"
   )
   protected SamlAttributeType extendedInformation;

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

   public SamlAttributeType getExtendedInformation() {
      return this.extendedInformation;
   }

   public void setExtendedInformation(SamlAttributeType value) {
      this.extendedInformation = value;
   }
}
