package be.cin.mycarenet.esb.medicaladvisoragreement.chap4.ask.v1;

import be.cin.mycarenet.esb.common.v2.CommonOutputType;
import be.cin.mycarenet.esb.common.v2.RecordCommonOutputType;
import be.cin.types.v1.FaultType;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "AskChap4MedicalAdvisorAgreementResponseType",
   propOrder = {"commonOutput", "recordCommonOutput", "returnInfo", "response"}
)
@XmlRootElement(
   name = "AskChap4MedicalAdvisorAgreementResponse"
)
public class AskChap4MedicalAdvisorAgreementResponse implements Serializable {
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
      name = "ReturnInfo"
   )
   protected FaultType returnInfo;
   @XmlElement(
      name = "Response"
   )
   protected ResponseType response;

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

   public ResponseType getResponse() {
      return this.response;
   }

   public void setResponse(ResponseType value) {
      this.response = value;
   }
}
