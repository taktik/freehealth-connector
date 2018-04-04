package org.taktik.connector.business.genericasync.exception;

import be.fgov.ehealth.technicalconnector.signature.domain.SignatureVerificationResult;
import java.util.Map;

public class GenAsyncSignatureValidationConnectorException extends GenAsyncBusinessConnectorException {
   private static final long serialVersionUID = -5527698631504638L;
   private Map<Object, SignatureVerificationResult> validationResult;

   public GenAsyncSignatureValidationConnectorException(GenAsyncBusinessConnectorExceptionValues errorCodeValue, Map<Object, SignatureVerificationResult> validationResult) {
      super(errorCodeValue);
      this.validationResult = validationResult;
   }

   public Map<Object, SignatureVerificationResult> getSignatureValidationResult() {
      return this.validationResult;
   }
}
