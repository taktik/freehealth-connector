package org.taktik.connector.business.chapterIV.exception;

public enum ChapterIVBusinessConnectorExceptionValues {
   TARGET_SERVICE_ERROR("targetservice.error", "TargetService error description: {0}"),
   MALFORMED_URL("malformed.url", "Invalid url to {0} file"),
   ERROR_XML_KMEHRVALIDATOR("error.xml.kmehrvalidator", "XML is not correct: {0}"),
   ERROR_XML_CHAPTER4VALIDATOR("error.xml.chapter4.validator", "XML is not correct: {0}"),
   ERROR_XML_UNDEFINED_XSD_FOR_XML_CLASS_VALIDATOR("error.xml.chapter4.undefined.class.validator", "no xsd file location is defined for class: {0}"),
   INVALID_ATTRIBUTES_LENGTH("invalid_attributes_length", "Invalid attributes array length : {0}"),
   UNKNOWN_ERROR("unknown.error", "Unknown error"),
   INPUT_PARAM_NULL("input.param.null", "Input parameter null : {0}"),
   ERROR_RESPONSE_XML("error.xml.responsevalidator", "response is not valid : {0}"),
   TIMESTAMP_NOT_CORRECT("error.xml.invalid.timestamp.in.response", "the timestamp in the response was not valid : {0}");

   private String errorCode;
   private String message;

   private ChapterIVBusinessConnectorExceptionValues(String errorCode, String errorMessage) {
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
