package be.fgov.ehealth.technicalconnector.ra.builders;

import be.ehealth.technicalconnector.service.sts.security.Credential;

public class BuilderFactory {
   public static ContractBuilder.ContractBuilderStep newContractBuilder() {
      return new ContractBuilder.ContractSteps();
   }

   public static EncryptionTokenBuilder.EncryptionTokenBuilderStep newEncryptionTokenBuilder(Credential cred) {
      return new EncryptionTokenBuilder.EncryptionTokenBuilderSteps(cred);
   }

   public static InformationBuilder.InformationBuilderStep newInformationBuilder() {
      return new InformationBuilder.InformationBuilderSteps();
   }
}
