package be.fgov.ehealth.etkra.protocol.v1;

import javax.xml.bind.annotation.XmlRegistry;

@XmlRegistry
public class ObjectFactory {
   public StartEtkRegistrationRequest createStartEtkRegistrationRequest() {
      return new StartEtkRegistrationRequest();
   }

   public StartEtkRegistrationResponse createStartEtkRegistrationResponse() {
      return new StartEtkRegistrationResponse();
   }

   public RaResponseType createRaResponseType() {
      return new RaResponseType();
   }

   public ErrorType createErrorType() {
      return new ErrorType();
   }

   public CompleteEtkRegistrationRequest createCompleteEtkRegistrationRequest() {
      return new CompleteEtkRegistrationRequest();
   }

   public SignedCredentialsType createSignedCredentialsType() {
      return new SignedCredentialsType();
   }

   public CompleteEtkRegistrationResponse createCompleteEtkRegistrationResponse() {
      return new CompleteEtkRegistrationResponse();
   }

   public GetValidAuthCertsRequest createGetValidAuthCertsRequest() {
      return new GetValidAuthCertsRequest();
   }

   public EhealthDistinguishedNameType createEhealthDistinguishedNameType() {
      return new EhealthDistinguishedNameType();
   }

   public GetValidAuthCertsResponse createGetValidAuthCertsResponse() {
      return new GetValidAuthCertsResponse();
   }

   public ProcessCsrRequest createProcessCsrRequest() {
      return new ProcessCsrRequest();
   }

   public ContactDataType createContactDataType() {
      return new ContactDataType();
   }

   public ProcessCsrResponse createProcessCsrResponse() {
      return new ProcessCsrResponse();
   }

   public ActivateETKRequest createActivateETKRequest() {
      return new ActivateETKRequest();
   }

   public ActivateETKResponse createActivateETKResponse() {
      return new ActivateETKResponse();
   }

   public RevokeAndActivateRequest createRevokeAndActivateRequest() {
      return new RevokeAndActivateRequest();
   }

   public RevokeAndActivateResponse createRevokeAndActivateResponse() {
      return new RevokeAndActivateResponse();
   }

   public RevokeRequest createRevokeRequest() {
      return new RevokeRequest();
   }

   public RevokeResponse createRevokeResponse() {
      return new RevokeResponse();
   }
}
