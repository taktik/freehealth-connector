package be.recipe.services;

import javax.xml.ws.WebFault;

@WebFault(
   name = "RecipeException",
   targetNamespace = "http://services.recipe.be"
)
public class RecipeException extends Exception {
   private RecipeExceptionDetails recipeException;

   public RecipeException() {
   }

   public RecipeException(String message) {
      super(message);
   }

   public RecipeException(String message, Throwable cause) {
      super(message, cause);
   }

   public RecipeException(String message, RecipeExceptionDetails recipeException) {
      super(message);
      this.recipeException = recipeException;
   }

   public RecipeException(String message, RecipeExceptionDetails recipeException, Throwable cause) {
      super(message, cause);
      this.recipeException = recipeException;
   }

   public RecipeExceptionDetails getFaultInfo() {
      return this.recipeException;
   }
}
