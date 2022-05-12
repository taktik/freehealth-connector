package be.fgov.ehealth.technicalconnector.ra.builders;

import be.ehealth.technicalconnector.service.sts.security.Credential;

public class BuilderFactory {
   public BuilderFactory() {
   }

   public static ContractRequestBuilder.ContractRequestBuilderStep newContractRequestBuilder() {
      return new ContractRequestBuilder.ContractRequestSteps();
   }

   public static EncryptionTokenBuilder.EncryptionTokenBuilderStep newEncryptionTokenBuilder(Credential cred) {
      return new EncryptionTokenBuilder.EncryptionTokenBuilderSteps(cred);
   }

   public static RevocationRequestBuilder.RevokeRequestBuilderStep newRevokeRequestBuilder() {
      return new RevocationRequestBuilder.RevokeRequestSteps();
   }

   public static ForeignRequestBuilder newForeignRequestBuilder() {
      return new ForeignRequestBuilder();
   }
}
