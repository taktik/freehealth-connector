package org.taktik.connector.business.mycarenetdomaincommons.exception;

public enum InvalidBlobContentConnectorExceptionValues {
   SETTINGS_NOT_FOUND("settings.not.found", "Could not find the settings file : {0}"),
   HASH_VALUES_DIFFERENT("hash.values.different", "Hash value is different : was {0} but expecting {1}"),
   PARAMETER_NULL("parameters.null", "This parameter is null : {0}"),
   HASHVALUE_NULL("hashvalue.null", "The hash value is required but null"),
   XADESVALUES_DIFFERENT("xadesvalues.different", "Xades validation failed with the following error :${0} \n received xades: {1}"),
   XADESVALUE_NULL("xadesvalue.null", "The xades value is required but null");

   private String errorCode;
   private String message;

   private InvalidBlobContentConnectorExceptionValues(String errorCode, String errorMessage) {
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
