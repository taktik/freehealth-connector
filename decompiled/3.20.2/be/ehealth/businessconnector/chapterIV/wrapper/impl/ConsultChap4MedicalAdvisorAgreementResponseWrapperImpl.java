package be.ehealth.businessconnector.chapterIV.wrapper.impl;

import be.ehealth.businessconnector.chapterIV.wrapper.Chap4MedicalAdvisorAgreementResponseWrapper;
import be.fgov.ehealth.chap4.core.v1.CommonOutputType;
import be.fgov.ehealth.chap4.core.v1.FaultType;
import be.fgov.ehealth.chap4.core.v1.RecordCommonOutputType;
import be.fgov.ehealth.chap4.core.v1.SecuredContentType;
import be.fgov.ehealth.chap4.protocol.v1.ConsultChap4MedicalAdvisorAgreementResponse;
import be.fgov.ehealth.commons.core.v1.StatusType;

public class ConsultChap4MedicalAdvisorAgreementResponseWrapperImpl extends AbstractWrapper<ConsultChap4MedicalAdvisorAgreementResponse> implements Chap4MedicalAdvisorAgreementResponseWrapper<ConsultChap4MedicalAdvisorAgreementResponse> {
   private static final long serialVersionUID = -7773747306707839298L;

   public ConsultChap4MedicalAdvisorAgreementResponseWrapperImpl(ConsultChap4MedicalAdvisorAgreementResponse response) {
      super(response);
   }

   public StatusType getStatus() {
      return ((ConsultChap4MedicalAdvisorAgreementResponse)this.getXmlObject()).getStatus();
   }

   public void setStatus(StatusType value) {
      ((ConsultChap4MedicalAdvisorAgreementResponse)this.getXmlObject()).setStatus(value);
   }

   public String getId() {
      return ((ConsultChap4MedicalAdvisorAgreementResponse)this.getXmlObject()).getId();
   }

   public void setId(String value) {
      ((ConsultChap4MedicalAdvisorAgreementResponse)this.getXmlObject()).setId(value);
   }

   public CommonOutputType getCommonOutput() {
      return ((ConsultChap4MedicalAdvisorAgreementResponse)this.getXmlObject()).getCommonOutput();
   }

   public void setCommonOutput(CommonOutputType value) {
      ((ConsultChap4MedicalAdvisorAgreementResponse)this.getXmlObject()).setCommonOutput(value);
   }

   public RecordCommonOutputType getRecordCommonOutput() {
      return ((ConsultChap4MedicalAdvisorAgreementResponse)this.getXmlObject()).getRecordCommonOutput();
   }

   public void setRecordCommonOutput(RecordCommonOutputType value) {
      ((ConsultChap4MedicalAdvisorAgreementResponse)this.getXmlObject()).setRecordCommonOutput(value);
   }

   public FaultType getReturnInfo() {
      return ((ConsultChap4MedicalAdvisorAgreementResponse)this.getXmlObject()).getReturnInfo();
   }

   public void setReturnInfo(FaultType value) {
      ((ConsultChap4MedicalAdvisorAgreementResponse)this.getXmlObject()).setReturnInfo(value);
   }

   public SecuredContentType getResponse() {
      return ((ConsultChap4MedicalAdvisorAgreementResponse)this.getXmlObject()).getResponse();
   }

   public void setResponse(SecuredContentType value) {
      ((ConsultChap4MedicalAdvisorAgreementResponse)this.getXmlObject()).setResponse(value);
   }
}
