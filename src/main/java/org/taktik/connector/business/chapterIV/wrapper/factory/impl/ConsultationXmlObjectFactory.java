package org.taktik.connector.business.chapterIV.wrapper.factory.impl;

import be.cin.io.sealed.medicaladvisoragreement.consult.v1.Request;
import org.taktik.connector.business.chapterIV.common.ConversationType;
import org.taktik.connector.business.chapterIV.wrapper.Chap4MedicalAdvisorAgreementRequestWrapper;
import org.taktik.connector.business.chapterIV.wrapper.SealedRequestWrapper;
import org.taktik.connector.business.chapterIV.wrapper.UnsealedRequestWrapper;
import org.taktik.connector.business.chapterIV.wrapper.factory.XmlObjectFactory;
import org.taktik.connector.business.chapterIV.wrapper.impl.ConsultChap4MedicalAdvisorAgreementRequestWrapperImpl;
import org.taktik.connector.business.chapterIV.wrapper.impl.ConsultSealedRequestWrapperImpl;
import org.taktik.connector.business.chapterIV.wrapper.impl.ConsultUnsealedRequestWrapperImpl;
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
