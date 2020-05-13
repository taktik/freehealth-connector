package be.fgov.ehealth.chap4.protocol.v1;

import javax.xml.bind.annotation.XmlRegistry;

@XmlRegistry
public class ObjectFactory {
   public AskChap4MedicalAdvisorAgreementRequest createAskChap4MedicalAdvisorAgreementRequest() {
      return new AskChap4MedicalAdvisorAgreementRequest();
   }

   public AskChap4MedicalAdvisorAgreementResponse createAskChap4MedicalAdvisorAgreementResponse() {
      return new AskChap4MedicalAdvisorAgreementResponse();
   }

   public ConsultChap4MedicalAdvisorAgreementRequest createConsultChap4MedicalAdvisorAgreementRequest() {
      return new ConsultChap4MedicalAdvisorAgreementRequest();
   }

   public ConsultChap4MedicalAdvisorAgreementResponse createConsultChap4MedicalAdvisorAgreementResponse() {
      return new ConsultChap4MedicalAdvisorAgreementResponse();
   }
}
