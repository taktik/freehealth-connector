package be.ehealth.businessconnector.genins.exception;

public enum GenInsBusinessConnectorExceptionValues {
   TARGET_SERVICE_ERROR("targetservice.error", "TargetService error description: {0}"),
   SETTINGS_NOT_FOUND("settings.not.found", "Could not find the settings file : {0}"),
   OBJECTBUILDER_INSTANCIATION_ERROR("objectbuilder.instanciation.error", "Object builder could not be instanciated : {0}"),
   MALFORMED_URL("malformed.url", "Invalid url to {0} file"),
   ERROR_XML_KMEHRVALIDATOR("error.xml.kmehrvalidator", "XML is not correct: {0}"),
   ERROR_XML_GENINSVALIDATOR("error.xml.genins.validator", "XML is not correct: {0}"),
   ERROR_XML_UNDEFINED_XSD_FOR_XML_CLASS_VALIDATOR("error.xml.genins.undefined.class.validator", "no xsd file location is defined for class: {0}"),
   INVALID_ATTRIBUTES_LENGTH("invalid_attributes_length", "Invalid attributes array length : {0}"),
   UNKNOWN_ERROR("unknown.error", "Unknown error"),
   INPUT_PARAM_NULL("input.param.null", "Input parameter null : {0}"),
   ERROR_RESPONSE_XML("error.xml.responsevalidator", "response is not valid : {0}"),
   TIMESTAMP_NOT_CORRECT("error.xml.invalid.timestamp.in.response", "the timestamp in the response was not valid : {0}");

   private String errorCode;
   private String message;

   private GenInsBusinessConnectorExceptionValues(String errorCode, String errorMessage) {
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
