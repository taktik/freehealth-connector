package be.ehealth.businessconnector.chapterIV.builders;

import be.cin.io.unsealed.medicaladvisoragreement.consult.v1.Response;
import be.ehealth.businessconnector.chapterIV.wrapper.Chap4MedicalAdvisorAgreementResponseWrapper;
import be.ehealth.businessconnector.chapterIV.wrapper.UnsealedResponseWrapper;
import be.ehealth.businessconnector.chapterIV.wrapper.impl.AskChap4MedicalAdvisorAgreementResponseWrapperImpl;
import be.ehealth.businessconnector.chapterIV.wrapper.impl.AskUnsealedResponseWrapperImpl;
import be.ehealth.businessconnector.chapterIV.wrapper.impl.ConsultChap4MedicalAdvisorAgreementResponseWrapperImpl;
import be.ehealth.businessconnector.chapterIV.wrapper.impl.ConsultUnsealedResponseWrapperImpl;
import be.fgov.ehealth.chap4.protocol.v1.AskChap4MedicalAdvisorAgreementResponse;
import be.fgov.ehealth.chap4.protocol.v1.ConsultChap4MedicalAdvisorAgreementResponse;

public final class WrappedResponseBuilder {
   public static Chap4MedicalAdvisorAgreementResponseWrapper<ConsultChap4MedicalAdvisorAgreementResponse> wrap(ConsultChap4MedicalAdvisorAgreementResponse agreementResponse) {
      return new ConsultChap4MedicalAdvisorAgreementResponseWrapperImpl(agreementResponse);
   }

   public static Chap4MedicalAdvisorAgreementResponseWrapper<AskChap4MedicalAdvisorAgreementResponse> wrap(AskChap4MedicalAdvisorAgreementResponse response) {
      return new AskChap4MedicalAdvisorAgreementResponseWrapperImpl(response);
   }

   public static UnsealedResponseWrapper<Response> wrap(Response response) {
      return new ConsultUnsealedResponseWrapperImpl(response);
   }

   public static UnsealedResponseWrapper<be.cin.io.unsealed.medicaladvisoragreement.ask.v1.Response> wrap(be.cin.io.unsealed.medicaladvisoragreement.ask.v1.Response response) {
      return new AskUnsealedResponseWrapperImpl(response);
   }
}
