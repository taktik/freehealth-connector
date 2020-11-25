package org.taktik.connector.business.recipeprojects.core.exceptions;

import java.util.Collections;
import java.util.List;

public class IntegrationModuleValidationException extends RuntimeException {
   private List<String> validationErrors;

   public IntegrationModuleValidationException(String message, String validationError) {
      super(message);
      this.validationErrors = Collections.singletonList(validationError);
   }

   public IntegrationModuleValidationException(String message, List<String> validationErrors) {
      super(message);
      this.validationErrors = validationErrors;
   }

   public IntegrationModuleValidationException(String validationError) {
      this.validationErrors = Collections.singletonList(validationError);
   }

   public List<String> getValidationErrors() {
      return this.validationErrors;
   }

   public void setValidationErrors(List<String> validationErrors) {
      this.validationErrors = validationErrors;
   }
}
