package be.fgov.ehealth.recipe.core.v2;

import javax.xml.bind.annotation.XmlRegistry;

@XmlRegistry
public class ObjectFactory {
   public SecuredContentType createSecuredContentType() {
      return new SecuredContentType();
   }

   public CreatePrescriptionAdministrativeInformationType createCreatePrescriptionAdministrativeInformationType() {
      return new CreatePrescriptionAdministrativeInformationType();
   }

   public SendNotificationAdministrativeInformationType createSendNotificationAdministrativeInformationType() {
      return new SendNotificationAdministrativeInformationType();
   }

   public PrescriberServiceAdministrativeInformationType createPrescriberServiceAdministrativeInformationType() {
      return new PrescriberServiceAdministrativeInformationType();
   }

   public ExecutorServiceAdministrativeInformationType createExecutorServiceAdministrativeInformationType() {
      return new ExecutorServiceAdministrativeInformationType();
   }
}
