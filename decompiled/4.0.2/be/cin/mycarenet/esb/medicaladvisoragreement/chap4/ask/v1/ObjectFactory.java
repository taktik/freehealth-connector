package be.cin.mycarenet.esb.medicaladvisoragreement.chap4.ask.v1;

import javax.xml.bind.annotation.XmlRegistry;

@XmlRegistry
public class ObjectFactory {
   public ObjectFactory() {
   }

   public AskChap4MedicalAdvisorAgreementRequest createAskChap4MedicalAdvisorAgreementRequest() {
      return new AskChap4MedicalAdvisorAgreementRequest();
   }

   public RequestType createRequestType() {
      return new RequestType();
   }

   public AskChap4MedicalAdvisorAgreementResponse createAskChap4MedicalAdvisorAgreementResponse() {
      return new AskChap4MedicalAdvisorAgreementResponse();
   }

   public ResponseType createResponseType() {
      return new ResponseType();
   }
}
