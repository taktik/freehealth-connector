package be.business.connector.core.exceptions;

import be.recipe.services.RecipeException;
import be.recipe.services.RecipeExceptionDetails;
import java.util.Iterator;
import java.util.Locale;
import org.apache.log4j.Logger;

public class IntegrationModuleException extends Exception {
   private static final Logger LOG = Logger.getLogger(IntegrationModuleException.class);
   private static final long serialVersionUID = 1L;

   public IntegrationModuleException(String message, Throwable cause) {
      super(message, cause);
      LOG.error(message, this);
   }

   public IntegrationModuleException(String message) {
      super(message);
      LOG.error(message, this);
   }

   public IntegrationModuleException(Throwable cause) {
      super(cause);
      LOG.error("IntegrationModuleException", cause);
   }

   public String getLocalizedMessage() {
      return this.getMessage();
   }

   public String getMessage() {
      String locale = getUserLocale();
      Throwable cause = this.getCause();
      RecipeExceptionDetails e;
      RecipeExceptionDetails.ErrorMap list;
      RecipeExceptionDetails.ErrorMap.Entry entry;
      Iterator var6;
      if (cause instanceof RecipeException) {
         e = ((RecipeException)cause).getFaultInfo();
         list = e.getErrorMap();
         var6 = list.getEntries().iterator();

         while(var6.hasNext()) {
            entry = (RecipeExceptionDetails.ErrorMap.Entry)var6.next();
            if (entry.getKey().startsWith(locale)) {
               return entry.getValue().getMessage() + "\n" + entry.getValue().getResolution();
            }
         }
      }

      if (cause instanceof RecipeException) {
         e = ((RecipeException)cause).getFaultInfo();
         list = e.getErrorMap();
         var6 = list.getEntries().iterator();

         while(var6.hasNext()) {
            entry = (RecipeExceptionDetails.ErrorMap.Entry)var6.next();
            if (entry.getKey().startsWith(locale)) {
               return entry.getValue().getMessage() + "\n" + entry.getValue().getResolution();
            }
         }
      }

      return super.getMessage();
   }

   public static String getUserLocale() {
      String locale = Locale.getDefault().getLanguage();
      if (!locale.equalsIgnoreCase("nl") && !locale.equalsIgnoreCase("fr") && !locale.equalsIgnoreCase("en")) {
         Locale.setDefault(Locale.ENGLISH);
         locale = Locale.ENGLISH.getLanguage();
      }

      return locale;
   }
}
