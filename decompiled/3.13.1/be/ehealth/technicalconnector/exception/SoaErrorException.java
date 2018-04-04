package be.ehealth.technicalconnector.exception;

import be.fgov.ehealth.commons.protocol.v1.ResponseType;
import be.fgov.ehealth.errors.core.v1.ErrorType;

public class SoaErrorException extends TechnicalConnectorException {
   private static final long serialVersionUID = 1L;
   private String errorCode;
   private ResponseType responseTypeV1;
   private ErrorType errorType;
   private be.fgov.ehealth.commons.protocol.v2.ResponseType responseTypeV2;

   public SoaErrorException(String errorCode, ResponseType responseType) {
      super(TechnicalConnectorExceptionValues.ERROR_WS, errorCode);
      this.errorCode = errorCode;
      this.responseTypeV1 = responseType;
   }

   public SoaErrorException(String errorCode, be.fgov.ehealth.commons.protocol.v2.ResponseType responseType) {
      super(TechnicalConnectorExceptionValues.ERROR_WS, errorCode);
      this.errorCode = errorCode;
      this.responseTypeV2 = responseType;
   }

   public SoaErrorException(String errorCode, ErrorType errorType) {
      super(TechnicalConnectorExceptionValues.ERROR_WS, errorCode);
      this.errorCode = errorCode;
      this.errorType = errorType;
   }

   public String getErrorCode() {
      return this.errorCode;
   }

   public ResponseType getResponseType() {
      return this.responseTypeV1;
   }

   public be.fgov.ehealth.commons.protocol.v2.ResponseType getResponseTypeV2() {
      return this.responseTypeV2;
   }

   public ErrorType getErrorType() {
      return this.errorType;
   }
}
