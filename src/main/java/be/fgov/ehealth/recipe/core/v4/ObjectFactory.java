package be.fgov.ehealth.recipe.core.v4;

import javax.xml.bind.annotation.XmlRegistry;

@XmlRegistry
public class ObjectFactory {
   public SecuredContentType createSecuredContentType() {
      return new SecuredContentType();
   }

   public CreatePrescriptionAdministrativeInformationType createCreatePrescriptionAdministrativeInformationType() {
      return new CreatePrescriptionAdministrativeInformationType();
   }

   public PrescriberPutVisionInformationType createPrescriberPutVisionInformationType() {
      return new PrescriberPutVisionInformationType();
   }
}
