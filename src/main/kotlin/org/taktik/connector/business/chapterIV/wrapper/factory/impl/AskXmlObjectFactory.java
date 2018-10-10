package org.taktik.connector.business.chapterIV.wrapper.factory.impl;

import be.cin.io.sealed.medicaladvisoragreement.ask.v1.Request;
import org.taktik.connector.business.chapterIV.common.ConversationType;
import org.taktik.connector.business.chapterIV.wrapper.Chap4MedicalAdvisorAgreementRequestWrapper;
import org.taktik.connector.business.chapterIV.wrapper.SealedRequestWrapper;
import org.taktik.connector.business.chapterIV.wrapper.UnsealedRequestWrapper;
import org.taktik.connector.business.chapterIV.wrapper.factory.XmlObjectFactory;
import org.taktik.connector.business.chapterIV.wrapper.impl.AskChap4MedicalAdvisorAgreementRequestWrapperImpl;
import org.taktik.connector.business.chapterIV.wrapper.impl.AskSealedRequestWrapperImpl;
import org.taktik.connector.business.chapterIV.wrapper.impl.AskUnsealedRequestWrapperImpl;
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
