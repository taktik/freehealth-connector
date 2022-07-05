package be.cin.mycarenet.esb.medicaladvisoragreement.chap4.consult.v1;

import be.cin.mycarenet.esb.common.v2.CommonInput;
import be.cin.mycarenet.esb.common.v2.RecordCommonInputType;
import be.cin.types.v1.CareReceiverIdType;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "ConsultChap4MedicalAdvisorAgreementRequestType",
   propOrder = {"commonInput", "recordCommonInput", "careReceiver", "request"}
)
@XmlRootElement(
   name = "ConsultChap4MedicalAdvisorAgreementRequest"
)
public class ConsultChap4MedicalAdvisorAgreementRequest implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "CommonInput",
      required = true
   )
   protected CommonInput commonInput;
   @XmlElement(
      name = "RecordCommonInput",
      required = true
   )
   protected RecordCommonInputType recordCommonInput;
   @XmlElement(
      name = "CareReceiver",
      required = true
   )
   protected CareReceiverIdType careReceiver;
   @XmlElement(
      name = "Request",
      required = true
   )
   protected RequestType request;

   public ConsultChap4MedicalAdvisorAgreementRequest() {
   }

   public CommonInput getCommonInput() {
      return this.commonInput;
   }

   public void setCommonInput(CommonInput value) {
      this.commonInput = value;
   }

   public RecordCommonInputType getRecordCommonInput() {
      return this.recordCommonInput;
   }

   public void setRecordCommonInput(RecordCommonInputType value) {
      this.recordCommonInput = value;
   }

   public CareReceiverIdType getCareReceiver() {
      return this.careReceiver;
   }

   public void setCareReceiver(CareReceiverIdType value) {
      this.careReceiver = value;
   }

   public RequestType getRequest() {
      return this.request;
   }

   public void setRequest(RequestType value) {
      this.request = value;
   }
}
