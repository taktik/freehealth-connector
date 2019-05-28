package be.ehealth.business.intrahubcommons.exception;

public enum IntraHubBusinessConnectorExceptionValues {
   MALFORMED_XML("malformed.xml", "Malformed XML"),
   VALIDATION_ERROR("validation.hub.intra.acknowledge", "Could not validate incoming message: {0}"),
   PROPERTY_UNKNOWN("property.unknown", "Property not defined : {0}"),
   ERROR("error", "Error occured: {0}");

   private final String errorCode;
   private final String message;

   private IntraHubBusinessConnectorExceptionValues(String errorCode, String errorMessage) {
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
