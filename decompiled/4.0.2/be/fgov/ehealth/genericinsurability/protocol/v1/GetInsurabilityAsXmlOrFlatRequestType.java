package be.fgov.ehealth.genericinsurability.protocol.v1;

import be.fgov.ehealth.commons.protocol.v1.RequestType;
import be.fgov.ehealth.genericinsurability.core.v1.CommonInputType;
import be.fgov.ehealth.genericinsurability.core.v1.RecordCommonInputType;
import be.fgov.ehealth.genericinsurability.core.v1.SingleInsurabilityRequestType;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "GetInsurabilityAsXmlOrFlatRequestType",
   propOrder = {"commonInput", "recordCommonInput", "request"}
)
public class GetInsurabilityAsXmlOrFlatRequestType extends RequestType implements Serializable {
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
      name = "Request",
      required = true
   )
   protected SingleInsurabilityRequestType request;

   public GetInsurabilityAsXmlOrFlatRequestType() {
   }

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

   public SingleInsurabilityRequestType getRequest() {
      return this.request;
   }

   public void setRequest(SingleInsurabilityRequestType value) {
      this.request = value;
   }
}
