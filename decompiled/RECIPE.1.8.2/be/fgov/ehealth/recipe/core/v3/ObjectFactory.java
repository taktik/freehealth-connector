package be.fgov.ehealth.recipe.core.v3;

import javax.xml.bind.annotation.XmlRegistry;

@XmlRegistry
public class ObjectFactory {
   public CreatePrescriptionAdministrativeInformationType createCreatePrescriptionAdministrativeInformationType() {
      return new CreatePrescriptionAdministrativeInformationType();
   }

   public PrescriberServiceAdministrativeInformationType createPrescriberServiceAdministrativeInformationType() {
      return new PrescriberServiceAdministrativeInformationType();
   }

   public ExecutorServiceAdministrativeInformationType createExecutorServiceAdministrativeInformationType() {
      return new ExecutorServiceAdministrativeInformationType();
   }

   public SendNotificationAdministrativeInformationType createSendNotificationAdministrativeInformationType() {
      return new SendNotificationAdministrativeInformationType();
   }

   public SecuredContentType createSecuredContentType() {
      return new SecuredContentType();
   }
}
