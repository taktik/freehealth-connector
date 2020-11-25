package be.business.connector.core.exceptions;

import be.recipe.common.exceptions.RecipeException;
import be.recipe.common.exceptions.RecipeExceptionDetails;
import java.util.Iterator;
import java.util.Locale;
import org.apache.log4j.Logger;

public class IntegrationModuleRuntimeException extends RuntimeException {
   private static final Logger LOG = Logger.getLogger(IntegrationModuleRuntimeException.class);
   private static final long serialVersionUID = 1L;

   public IntegrationModuleRuntimeException(String message, Throwable cause) {
      super(message, cause);
      LOG.error(message, this);
   }

   public IntegrationModuleRuntimeException(String message) {
      super(message);
      LOG.error(message, this);
   }

   public IntegrationModuleRuntimeException(Throwable cause) {
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
