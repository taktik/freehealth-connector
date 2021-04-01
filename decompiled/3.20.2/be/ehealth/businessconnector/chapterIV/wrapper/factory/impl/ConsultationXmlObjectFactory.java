package be.ehealth.businessconnector.chapterIV.wrapper.factory.impl;

import be.cin.io.sealed.medicaladvisoragreement.consult.v1.Request;
import be.ehealth.businessconnector.chapterIV.common.ConversationType;
import be.ehealth.businessconnector.chapterIV.wrapper.Chap4MedicalAdvisorAgreementRequestWrapper;
import be.ehealth.businessconnector.chapterIV.wrapper.SealedRequestWrapper;
import be.ehealth.businessconnector.chapterIV.wrapper.UnsealedRequestWrapper;
import be.ehealth.businessconnector.chapterIV.wrapper.factory.XmlObjectFactory;
import be.ehealth.businessconnector.chapterIV.wrapper.impl.ConsultChap4MedicalAdvisorAgreementRequestWrapperImpl;
import be.ehealth.businessconnector.chapterIV.wrapper.impl.ConsultSealedRequestWrapperImpl;
import be.ehealth.businessconnector.chapterIV.wrapper.impl.ConsultUnsealedRequestWrapperImpl;
import be.fgov.ehealth.chap4.protocol.v1.ConsultChap4MedicalAdvisorAgreementRequest;

public class ConsultationXmlObjectFactory implements XmlObjectFactory {
   public SealedRequestWrapper<Request> createSealedRequest() {
      return new ConsultSealedRequestWrapperImpl();
   }

   public UnsealedRequestWrapper<be.cin.io.unsealed.medicaladvisoragreement.consult.v1.Request> createUnsealedRequest() {
      return new ConsultUnsealedRequestWrapperImpl();
   }

   public Chap4MedicalAdvisorAgreementRequestWrapper<ConsultChap4MedicalAdvisorAgreementRequest> createChap4MedicalAdvisorAgreementRequest() {
      return new ConsultChap4MedicalAdvisorAgreementRequestWrapperImpl();
   }

   public String getSubtypeNameToRetrieveCredentialTypeProperties() {
      return ConversationType.CONSULT.getPropertyString();
   }
}
