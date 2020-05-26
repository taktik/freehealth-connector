package be.recipe.common.exceptions;

import javax.xml.ws.WebFault;

@WebFault(
   name = "RecipeException",
   targetNamespace = "http://services.recipe.be"
)
public class RecipeException extends Exception {
   private static final long serialVersionUID = 1L;
   private RecipeExceptionDetails faultInfo;

   public RecipeException(String message, RecipeExceptionDetails faultInfo) {
      super(message);
      this.faultInfo = faultInfo;
   }

   public RecipeException(String message, RecipeExceptionDetails faultInfo, Throwable cause) {
      super(message, cause);
      this.faultInfo = faultInfo;
   }

   public RecipeExceptionDetails getFaultInfo() {
      return this.faultInfo;
   }
}
