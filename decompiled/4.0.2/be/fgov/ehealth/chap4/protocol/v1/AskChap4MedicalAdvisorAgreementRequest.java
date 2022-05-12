package be.fgov.ehealth.chap4.protocol.v1;

import be.fgov.ehealth.chap4.core.v1.CareReceiverIdType;
import be.fgov.ehealth.chap4.core.v1.CommonInputType;
import be.fgov.ehealth.chap4.core.v1.RecordCommonInputType;
import be.fgov.ehealth.chap4.core.v1.SecuredContentType;
import be.fgov.ehealth.commons.protocol.v1.RequestType;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "AskChap4MedicalAdvisorAgreementRequestType",
   propOrder = {"commonInput", "recordCommonInput", "careReceiver", "request"}
)
@XmlRootElement(
   name = "AskChap4MedicalAdvisorAgreementRequest"
)
public class AskChap4MedicalAdvisorAgreementRequest extends RequestType implements Serializable {
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
      name = "CareReceiver",
      required = true
   )
   protected CareReceiverIdType careReceiver;
   @XmlElement(
      name = "Request",
      required = true
   )
   protected SecuredContentType request;

   public AskChap4MedicalAdvisorAgreementRequest() {
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

   public CareReceiverIdType getCareReceiver() {
      return this.careReceiver;
   }

   public void setCareReceiver(CareReceiverIdType value) {
      this.careReceiver = value;
   }

   public SecuredContentType getRequest() {
      return this.request;
   }

   public void setRequest(SecuredContentType value) {
      this.request = value;
   }
}
