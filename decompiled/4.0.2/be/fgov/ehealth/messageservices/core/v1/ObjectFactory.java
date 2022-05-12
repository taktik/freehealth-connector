package be.fgov.ehealth.messageservices.core.v1;

import javax.xml.bind.annotation.XmlRegistry;

@XmlRegistry
public class ObjectFactory {
   public ObjectFactory() {
   }

   public RequestType createRequestType() {
      return new RequestType();
   }

   public AcknowledgeType createAcknowledgeType() {
      return new AcknowledgeType();
   }

   public ResponseType createResponseType() {
      return new ResponseType();
   }

   public PatientType createPatientType() {
      return new PatientType();
   }

   public TransactionType createTransactionType() {
      return new TransactionType();
   }

   public SelectRetrieveTransactionType createSelectRetrieveTransactionType() {
      return new SelectRetrieveTransactionType();
   }

   public SendTransactionRequest createSendTransactionRequest() {
      return new SendTransactionRequest();
   }

   public SendTransactionResponse createSendTransactionResponse() {
      return new SendTransactionResponse();
   }

   public RetrieveTransactionRequest createRetrieveTransactionRequest() {
      return new RetrieveTransactionRequest();
   }

   public RetrieveTransactionResponse createRetrieveTransactionResponse() {
      return new RetrieveTransactionResponse();
   }

   public Nationality createNationality() {
      return new Nationality();
   }
}
