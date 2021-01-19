package be.ehealth.businessconnector.chapterIV.wrapper.impl;

import be.ehealth.businessconnector.chapterIV.wrapper.Chap4MedicalAdvisorAgreementRequestWrapper;
import be.fgov.ehealth.chap4.core.v1.CareReceiverIdType;
import be.fgov.ehealth.chap4.core.v1.CommonInputType;
import be.fgov.ehealth.chap4.core.v1.RecordCommonInputType;
import be.fgov.ehealth.chap4.core.v1.SecuredContentType;
import be.fgov.ehealth.chap4.protocol.v1.ConsultChap4MedicalAdvisorAgreementRequest;

public class ConsultChap4MedicalAdvisorAgreementRequestWrapperImpl extends AbstractWrapper<ConsultChap4MedicalAdvisorAgreementRequest> implements Chap4MedicalAdvisorAgreementRequestWrapper<ConsultChap4MedicalAdvisorAgreementRequest> {
   private static final long serialVersionUID = 7272239695414769447L;

   public ConsultChap4MedicalAdvisorAgreementRequestWrapperImpl() {
      super(new ConsultChap4MedicalAdvisorAgreementRequest());
   }

   public ConsultChap4MedicalAdvisorAgreementRequestWrapperImpl(ConsultChap4MedicalAdvisorAgreementRequest request) {
      super(request);
   }

   public CommonInputType getCommonInput() {
      return ((ConsultChap4MedicalAdvisorAgreementRequest)this.getXmlObject()).getCommonInput();
   }

   public void setCommonInput(CommonInputType value) {
      ((ConsultChap4MedicalAdvisorAgreementRequest)this.getXmlObject()).setCommonInput(value);
   }

   public RecordCommonInputType getRecordCommonInput() {
      return ((ConsultChap4MedicalAdvisorAgreementRequest)this.getXmlObject()).getRecordCommonInput();
   }

   public void setRecordCommonInput(RecordCommonInputType value) {
      ((ConsultChap4MedicalAdvisorAgreementRequest)this.getXmlObject()).setRecordCommonInput(value);
   }

   public CareReceiverIdType getCareReceiver() {
      return ((ConsultChap4MedicalAdvisorAgreementRequest)this.getXmlObject()).getCareReceiver();
   }

   public void setCareReceiver(CareReceiverIdType value) {
      ((ConsultChap4MedicalAdvisorAgreementRequest)this.getXmlObject()).setCareReceiver(value);
   }

   public SecuredContentType getRequest() {
      return ((ConsultChap4MedicalAdvisorAgreementRequest)this.getXmlObject()).getRequest();
   }

   public void setRequest(SecuredContentType value) {
      ((ConsultChap4MedicalAdvisorAgreementRequest)this.getXmlObject()).setRequest(value);
   }
}
