package be.fgov.ehealth.recipe.core.v1;

import javax.xml.bind.annotation.XmlRegistry;

@XmlRegistry
public class ObjectFactory {
   public SecuredContentType createSecuredContentType() {
      return new SecuredContentType();
   }

   public ExecutorServiceAdministrativeInformationType createExecutorServiceAdministrativeInformationType() {
      return new ExecutorServiceAdministrativeInformationType();
   }

   public CreatePrescriptionAdministrativeInformationType createCreatePrescriptionAdministrativeInformationType() {
      return new CreatePrescriptionAdministrativeInformationType();
   }

   public PrescriberServiceAdministrativeInformationType createPrescriberServiceAdministrativeInformationType() {
      return new PrescriberServiceAdministrativeInformationType();
   }

   public SendNotificationAdministrativeInformationType createSendNotificationAdministrativeInformationType() {
      return new SendNotificationAdministrativeInformationType();
   }
}
