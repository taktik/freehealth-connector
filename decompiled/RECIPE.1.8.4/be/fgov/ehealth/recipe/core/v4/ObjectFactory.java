package be.fgov.ehealth.recipe.core.v4;

import javax.xml.bind.annotation.XmlRegistry;

@XmlRegistry
public class ObjectFactory {
   public CreatePrescriptionAdministrativeInformationType createCreatePrescriptionAdministrativeInformationType() {
      return new CreatePrescriptionAdministrativeInformationType();
   }

   public SecuredContentType createSecuredContentType() {
      return new SecuredContentType();
   }

   public PrescriberPutVisionInformationType createPrescriberPutVisionInformationType() {
      return new PrescriberPutVisionInformationType();
   }
}
