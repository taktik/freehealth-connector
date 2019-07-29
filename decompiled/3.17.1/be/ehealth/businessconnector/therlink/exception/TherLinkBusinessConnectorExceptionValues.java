package be.ehealth.businessconnector.therlink.exception;

public enum TherLinkBusinessConnectorExceptionValues {
   MALFORMED_XML("malformed.xml", "Malformed XML"),
   VALIDATION_ERROR("validation.hub.intra.acknowledge", "Could not validate incoming message: {0}"),
   PROPERTY_UNKNOWN("property.unknown", "Property not defined : {0}"),
   OBJECTBUILDER_INSTANCIATION_ERROR("objectbuilder.instanciation.error", "Object builder could not be instanciated : {0}"),
   REQUIRED_FIELD_NULL("required.field.null", "A required field is missing : {1}"),
   SETTINGS_NOT_FOUND("settings.not.found", "Could not find the settings file : {0}"),
   HCP_NOT_VALID("hcp.not.valid", "HcParty not valid : {0}"),
   MAXROWS_INCORRECT("maxrows.incorrect", "MaxRows should be smaller than {0}"),
   ERROR_CREATEPROOF("error.createproof", "Error while creating proof : {0}"),
   ERROR_PROOF_VALIDATION("error.proof.validation", "Error while validating signing proof : {0}");

   private String errorCode;
   private String message;

   private TherLinkBusinessConnectorExceptionValues(String errorCode, String errorMessage) {
      this.errorCode = errorCode;
      this.message = errorMessage;
   }

   public String getErrorCode() {
      return this.errorCode;
   }

   public String getMessage() {
      return this.message;
   }
}
