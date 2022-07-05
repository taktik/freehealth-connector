package be.fgov.ehealth.chap4.protocol.v1;

import be.fgov.ehealth.chap4.core.v1.CommonOutputType;
import be.fgov.ehealth.chap4.core.v1.FaultType;
import be.fgov.ehealth.chap4.core.v1.RecordCommonOutputType;
import be.fgov.ehealth.chap4.core.v1.SecuredContentType;
import be.fgov.ehealth.commons.protocol.v1.ResponseType;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "ConsultChap4MedicalAdvisorAgreementResponseType",
   propOrder = {"commonOutput", "recordCommonOutput", "returnInfo", "response"}
)
@XmlRootElement(
   name = "ConsultChap4MedicalAdvisorAgreementResponse"
)
public class ConsultChap4MedicalAdvisorAgreementResponse extends ResponseType implements Serializable {
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
   protected FaultType returnInfo;
   @XmlElement(
      name = "Response"
   )
   protected SecuredContentType response;

   public ConsultChap4MedicalAdvisorAgreementResponse() {
   }

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

   public FaultType getReturnInfo() {
      return this.returnInfo;
   }

   public void setReturnInfo(FaultType value) {
      this.returnInfo = value;
   }

   public SecuredContentType getResponse() {
      return this.response;
   }

   public void setResponse(SecuredContentType value) {
      this.response = value;
   }
}
