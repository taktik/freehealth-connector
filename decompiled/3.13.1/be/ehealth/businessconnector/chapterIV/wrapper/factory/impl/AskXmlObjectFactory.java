package be.ehealth.businessconnector.chapterIV.wrapper.factory.impl;

import be.cin.io.sealed.medicaladvisoragreement.ask.v1.Request;
import be.ehealth.businessconnector.chapterIV.common.ConversationType;
import be.ehealth.businessconnector.chapterIV.wrapper.Chap4MedicalAdvisorAgreementRequestWrapper;
import be.ehealth.businessconnector.chapterIV.wrapper.SealedRequestWrapper;
import be.ehealth.businessconnector.chapterIV.wrapper.UnsealedRequestWrapper;
import be.ehealth.businessconnector.chapterIV.wrapper.factory.XmlObjectFactory;
import be.ehealth.businessconnector.chapterIV.wrapper.impl.AskChap4MedicalAdvisorAgreementRequestWrapperImpl;
import be.ehealth.businessconnector.chapterIV.wrapper.impl.AskSealedRequestWrapperImpl;
import be.ehealth.businessconnector.chapterIV.wrapper.impl.AskUnsealedRequestWrapperImpl;
import be.fgov.ehealth.chap4.protocol.v1.AskChap4MedicalAdvisorAgreementRequest;

public class AskXmlObjectFactory implements XmlObjectFactory {
   public SealedRequestWrapper<Request> createSealedRequest() {
      return new AskSealedRequestWrapperImpl();
   }

   public UnsealedRequestWrapper<be.cin.io.unsealed.medicaladvisoragreement.ask.v1.Request> createUnsealedRequest() {
      return new AskUnsealedRequestWrapperImpl();
   }

   public Chap4MedicalAdvisorAgreementRequestWrapper<AskChap4MedicalAdvisorAgreementRequest> createChap4MedicalAdvisorAgreementRequest() {
      return new AskChap4MedicalAdvisorAgreementRequestWrapperImpl();
   }

   public String getSubtypeNameToRetrieveCredentialTypeProperties() {
      return ConversationType.ADMISSION.getPropertyString();
   }
}
