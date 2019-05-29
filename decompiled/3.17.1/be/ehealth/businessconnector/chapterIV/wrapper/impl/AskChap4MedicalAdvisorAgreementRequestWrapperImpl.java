package be.ehealth.businessconnector.chapterIV.wrapper.impl;

import be.ehealth.businessconnector.chapterIV.wrapper.Chap4MedicalAdvisorAgreementRequestWrapper;
import be.fgov.ehealth.chap4.core.v1.CareReceiverIdType;
import be.fgov.ehealth.chap4.core.v1.CommonInputType;
import be.fgov.ehealth.chap4.core.v1.RecordCommonInputType;
import be.fgov.ehealth.chap4.core.v1.SecuredContentType;
import be.fgov.ehealth.chap4.protocol.v1.AskChap4MedicalAdvisorAgreementRequest;

public class AskChap4MedicalAdvisorAgreementRequestWrapperImpl extends AbstractWrapper<AskChap4MedicalAdvisorAgreementRequest> implements Chap4MedicalAdvisorAgreementRequestWrapper<AskChap4MedicalAdvisorAgreementRequest> {
   private static final long serialVersionUID = 4234644547846425700L;

   public AskChap4MedicalAdvisorAgreementRequestWrapperImpl(AskChap4MedicalAdvisorAgreementRequest request) {
      super(request);
   }

   public AskChap4MedicalAdvisorAgreementRequestWrapperImpl() {
      super(new AskChap4MedicalAdvisorAgreementRequest());
   }

   public CommonInputType getCommonInput() {
      return ((AskChap4MedicalAdvisorAgreementRequest)this.getXmlObject()).getCommonInput();
   }

   public void setCommonInput(CommonInputType value) {
      ((AskChap4MedicalAdvisorAgreementRequest)this.getXmlObject()).setCommonInput(value);
   }

   public RecordCommonInputType getRecordCommonInput() {
      return ((AskChap4MedicalAdvisorAgreementRequest)this.getXmlObject()).getRecordCommonInput();
   }

   public void setRecordCommonInput(RecordCommonInputType value) {
      ((AskChap4MedicalAdvisorAgreementRequest)this.getXmlObject()).setRecordCommonInput(value);
   }

   public CareReceiverIdType getCareReceiver() {
      return ((AskChap4MedicalAdvisorAgreementRequest)this.getXmlObject()).getCareReceiver();
   }

   public void setCareReceiver(CareReceiverIdType value) {
      ((AskChap4MedicalAdvisorAgreementRequest)this.getXmlObject()).setCareReceiver(value);
   }

   public SecuredContentType getRequest() {
      return ((AskChap4MedicalAdvisorAgreementRequest)this.getXmlObject()).getRequest();
   }

   public void setRequest(SecuredContentType value) {
      ((AskChap4MedicalAdvisorAgreementRequest)this.getXmlObject()).setRequest(value);
   }
}
