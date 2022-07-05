package be.cin.mycarenet.esb.medicaladvisoragreement.chap4.consult.v1;

import javax.xml.bind.annotation.XmlRegistry;

@XmlRegistry
public class ObjectFactory {
   public ObjectFactory() {
   }

   public ConsultChap4MedicalAdvisorAgreementRequest createConsultChap4MedicalAdvisorAgreementRequest() {
      return new ConsultChap4MedicalAdvisorAgreementRequest();
   }

   public RequestType createRequestType() {
      return new RequestType();
   }

   public ConsultChap4MedicalAdvisorAgreementResponse createConsultChap4MedicalAdvisorAgreementResponse() {
      return new ConsultChap4MedicalAdvisorAgreementResponse();
   }

   public ResponseType createResponseType() {
      return new ResponseType();
   }
}
