package be.fgov.ehealth.etee.kgss._1_0.protocol;

import javax.xml.bind.annotation.XmlRegistry;

@XmlRegistry
public class ObjectFactory {
   public ObjectFactory() {
   }

   public GetNewKeyRequestContent createGetNewKeyRequestContent() {
      return new GetNewKeyRequestContent();
   }

   public CredentialType createCredentialType() {
      return new CredentialType();
   }

   public DeletionStrategyType createDeletionStrategyType() {
      return new DeletionStrategyType();
   }

   public GetNewKeyResponseContent createGetNewKeyResponseContent() {
      return new GetNewKeyResponseContent();
   }

   public GetKeyRequestContent createGetKeyRequestContent() {
      return new GetKeyRequestContent();
   }

   public GetKeyResponseContent createGetKeyResponseContent() {
      return new GetKeyResponseContent();
   }

   public GetNewKeyRequest createGetNewKeyRequest() {
      return new GetNewKeyRequest();
   }

   public SealedContentType createSealedContentType() {
      return new SealedContentType();
   }

   public GetNewKeyResponse createGetNewKeyResponse() {
      return new GetNewKeyResponse();
   }

   public GetKeyRequest createGetKeyRequest() {
      return new GetKeyRequest();
   }

   public GetKeyResponse createGetKeyResponse() {
      return new GetKeyResponse();
   }
}
