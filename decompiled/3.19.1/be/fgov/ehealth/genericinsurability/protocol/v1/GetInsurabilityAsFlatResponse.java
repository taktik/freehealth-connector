package be.fgov.ehealth.genericinsurability.protocol.v1;

import be.fgov.ehealth.commons.protocol.v1.ResponseType;
import be.fgov.ehealth.genericinsurability.core.v1.CommonOutputType;
import be.fgov.ehealth.genericinsurability.core.v1.RecordCommonOutputType;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "GetInsurabilityAsFlatResponseType",
   propOrder = {"commonOutput", "recordCommonOutput", "response"}
)
@XmlRootElement(
   name = "GetInsurabilityAsFlatResponse"
)
public class GetInsurabilityAsFlatResponse extends ResponseType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "CommonOutput",
      required = true
   )
   protected CommonOutputType commonOutput;
   @XmlElement(
      name = "RecordCommonOutput",
      required = true
   )
   protected RecordCommonOutputType recordCommonOutput;
   @XmlElement(
      name = "Response",
      required = true
   )
   protected String response;

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

   public String getResponse() {
      return this.response;
   }

   public void setResponse(String value) {
      this.response = value;
   }
}
