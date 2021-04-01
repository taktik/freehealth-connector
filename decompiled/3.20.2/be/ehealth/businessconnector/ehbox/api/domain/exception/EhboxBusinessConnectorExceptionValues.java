package be.ehealth.businessconnector.ehbox.api.domain.exception;

public enum EhboxBusinessConnectorExceptionValues {
   ERROR_EHBOX_DOCUMENT_OUTPUTSTREAM("error.ehbox.document.outputstream", "Error occured while writing file {0}"),
   INVALID_EHBOX_NEWS_NEWSTITLE("invalid.ehbox.news.newstitle", "News title can not be null."),
   ERROR_BUSINESS_CODE_REASON("error.business.code.reason", "Error while processing business call (code={0}): {1}"),
   NO_QUALITY_SET("no.quality.set", "No quality has been set"),
   CRYPTO_NOT_PROPERLY_INITIALIZED("crypto.not.properly.initialized", "Crypto has'nt been properly initialized"),
   PROPERTY_MISSING("property.missing", "The required property [{0}] is missing");

   private String errorCode;
   private String message;

   private EhboxBusinessConnectorExceptionValues(String errorCode, String errorMessage) {
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
