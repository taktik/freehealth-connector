package be.ehealth.businessconnector.dmg.exception;

public enum DmgBusinessConnectorExceptionValues {
   TARGET_SERVICE_ERROR("targetservice.error", "TargetService error description: {0}"),
   MALFORMED_URL("malformed.url", "Invalid url to {0} file"),
   SETTINGS_NOT_FOUND("settings.not.found", "Could not find the settings file : {0}"),
   ERROR_XML_DMGVALIDATOR("error.xml.dmg.validator", "XML is not correct: {0}"),
   ERROR_XML_UNDEFINED_XSD_FOR_XML_CLASS_VALIDATOR("error.xml.dmg.undefined.class.validator", "no xsd file location is defined for class: {0}"),
   PARAMETER_NULL("parameters.null", "This parameter is null : {0}"),
   SOAP_EXC("soapexception", "SoapException occured : {0}");

   private String errorCode;
   private String message;

   private DmgBusinessConnectorExceptionValues(String errorCode, String errorMessage) {
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
