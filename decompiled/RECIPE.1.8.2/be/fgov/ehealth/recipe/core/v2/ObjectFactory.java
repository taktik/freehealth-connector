package be.fgov.ehealth.recipe.core.v2;

import javax.xml.bind.annotation.XmlRegistry;

@XmlRegistry
public class ObjectFactory {
   public CreatePrescriptionAdministrativeInformationType createCreatePrescriptionAdministrativeInformationType() {
      return new CreatePrescriptionAdministrativeInformationType();
   }

   public ExecutorServiceAdministrativeInformationType createExecutorServiceAdministrativeInformationType() {
      return new ExecutorServiceAdministrativeInformationType();
   }

   public PrescriberServiceAdministrativeInformationType createPrescriberServiceAdministrativeInformationType() {
      return new PrescriberServiceAdministrativeInformationType();
   }

   public SecuredContentType createSecuredContentType() {
      return new SecuredContentType();
   }

   public SendNotificationAdministrativeInformationType createSendNotificationAdministrativeInformationType() {
      return new SendNotificationAdministrativeInformationType();
   }
}
