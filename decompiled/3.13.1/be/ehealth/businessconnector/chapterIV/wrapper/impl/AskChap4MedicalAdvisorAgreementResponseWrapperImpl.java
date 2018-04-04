package be.ehealth.businessconnector.chapterIV.wrapper.impl;

import be.ehealth.businessconnector.chapterIV.wrapper.Chap4MedicalAdvisorAgreementResponseWrapper;
import be.fgov.ehealth.chap4.core.v1.CommonOutputType;
import be.fgov.ehealth.chap4.core.v1.FaultType;
import be.fgov.ehealth.chap4.core.v1.RecordCommonOutputType;
import be.fgov.ehealth.chap4.core.v1.SecuredContentType;
import be.fgov.ehealth.chap4.protocol.v1.AskChap4MedicalAdvisorAgreementResponse;
import be.fgov.ehealth.commons.core.v1.StatusType;

public class AskChap4MedicalAdvisorAgreementResponseWrapperImpl extends AbstractWrapper<AskChap4MedicalAdvisorAgreementResponse> implements Chap4MedicalAdvisorAgreementResponseWrapper<AskChap4MedicalAdvisorAgreementResponse> {
   private static final long serialVersionUID = -1172891362437088360L;

   public AskChap4MedicalAdvisorAgreementResponseWrapperImpl(AskChap4MedicalAdvisorAgreementResponse response) {
      super(response);
   }

   public StatusType getStatus() {
      return ((AskChap4MedicalAdvisorAgreementResponse)this.getXmlObject()).getStatus();
   }

   public void setStatus(StatusType value) {
      ((AskChap4MedicalAdvisorAgreementResponse)this.getXmlObject()).setStatus(value);
   }

   public String getId() {
      return ((AskChap4MedicalAdvisorAgreementResponse)this.getXmlObject()).getId();
   }

   public void setId(String value) {
      ((AskChap4MedicalAdvisorAgreementResponse)this.getXmlObject()).setId(value);
   }

   public CommonOutputType getCommonOutput() {
      return ((AskChap4MedicalAdvisorAgreementResponse)this.getXmlObject()).getCommonOutput();
   }

   public void setCommonOutput(CommonOutputType value) {
      ((AskChap4MedicalAdvisorAgreementResponse)this.getXmlObject()).setCommonOutput(value);
   }

   public RecordCommonOutputType getRecordCommonOutput() {
      return ((AskChap4MedicalAdvisorAgreementResponse)this.getXmlObject()).getRecordCommonOutput();
   }

   public void setRecordCommonOutput(RecordCommonOutputType value) {
      ((AskChap4MedicalAdvisorAgreementResponse)this.getXmlObject()).setRecordCommonOutput(value);
   }

   public FaultType getReturnInfo() {
      return ((AskChap4MedicalAdvisorAgreementResponse)this.getXmlObject()).getReturnInfo();
   }

   public void setReturnInfo(FaultType value) {
      ((AskChap4MedicalAdvisorAgreementResponse)this.getXmlObject()).setReturnInfo(value);
   }

   public SecuredContentType getResponse() {
      return ((AskChap4MedicalAdvisorAgreementResponse)this.getXmlObject()).getResponse();
   }

   public void setResponse(SecuredContentType value) {
      ((AskChap4MedicalAdvisorAgreementResponse)this.getXmlObject()).setResponse(value);
   }
}
