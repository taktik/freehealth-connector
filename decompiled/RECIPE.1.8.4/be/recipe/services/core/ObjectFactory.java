package be.recipe.services.core;

import javax.xml.bind.annotation.XmlRegistry;

@XmlRegistry
public class ObjectFactory {
   public RecipeExceptionDetails createRecipeExceptionDetails() {
      return new RecipeExceptionDetails();
   }

   public RecipeExceptionDetails.ErrorMap createRecipeExceptionDetailsErrorMap() {
      return new RecipeExceptionDetails.ErrorMap();
   }

   public PartyIdentification createPartyIdentification() {
      return new PartyIdentification();
   }

   public ResponseType createResponseType() {
      return new ResponseType();
   }

   public StatusType createStatusType() {
      return new StatusType();
   }

   public Page createPage() {
      return new Page();
   }

   public RecipeError createRecipeError() {
      return new RecipeError();
   }

   public LocalisedString createLocalisedString() {
      return new LocalisedString();
   }

   public RecipeExceptionDetails.ErrorMap.Entry createRecipeExceptionDetailsErrorMapEntry() {
      return new RecipeExceptionDetails.ErrorMap.Entry();
   }
}
