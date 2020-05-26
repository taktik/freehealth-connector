package be.recipe.common.exceptions;

import java.text.MessageFormat;
import java.util.Iterator;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import org.apache.log4j.Logger;

public class RecipeExceptionFactory {
   public static final String RESOURCE_BUNDLE = "RecipeErrors";
   public static final Locale DUTCH_LOCALE = new Locale("nl", "BE");
   public static final Locale FRENCH_LOCALE = new Locale("fr", "FR");
   public static final Locale ENGLISH_LOCALE;
   private static final Logger LOG;
   public static final String SEPERATOR = ";";
   public static final Locale[] LOCALES;

   public static RecipeException createException(String errorCode, Object[] context) {
      RecipeExceptionDetails recipeDetails = getRecipeDetails(errorCode, context);
      String message = errorCode;

      try {
         RecipeExceptionDetails.ErrorMap errorMap = recipeDetails.getErrorMap();
         Iterator var5 = errorMap.entries.iterator();

         while(var5.hasNext()) {
            RecipeExceptionDetails.ErrorMap.Entry entry = (RecipeExceptionDetails.ErrorMap.Entry)var5.next();
            if (entry.key.equals("en")) {
               message = entry.value.getMessage();
               break;
            }
         }
      } finally {
         LOG.error("Error: " + message);
      }

      return new RecipeException(message, recipeDetails);
   }

   public static RecipeException createException(Object[] context, String errorCode) {
      RecipeExceptionDetails recipeDetails = getRecipeDetails(errorCode, context);
      String message = errorCode;

      try {
         RecipeExceptionDetails.ErrorMap errorMap = recipeDetails.getErrorMap();
         Iterator var5 = errorMap.entries.iterator();

         while(var5.hasNext()) {
            RecipeExceptionDetails.ErrorMap.Entry entry = (RecipeExceptionDetails.ErrorMap.Entry)var5.next();
            if (entry.key.equals("en")) {
               message = entry.value.getMessage();
               break;
            }
         }
      } catch (RuntimeException var10) {
      } finally {
         LOG.error("Erro: " + message);
      }

      return new RecipeException(message, recipeDetails);
   }

   public static RecipeException createException(String errorCode, Object[] context, Throwable cause) {
      if (cause instanceof RecipeException) {
         return (RecipeException)cause;
      } else {
         RecipeExceptionDetails recipeDetails = getRecipeDetails(errorCode, context);
         String message = errorCode;

         try {
            RecipeExceptionDetails.ErrorMap errorMap = recipeDetails.getErrorMap();
            Iterator var6 = errorMap.entries.iterator();

            while(var6.hasNext()) {
               RecipeExceptionDetails.ErrorMap.Entry entry = (RecipeExceptionDetails.ErrorMap.Entry)var6.next();
               if (entry.key.equals("en")) {
                  message = entry.value.getMessage();
                  break;
               }
            }
         } catch (RuntimeException var11) {
         } finally {
            LOG.error("Error: " + message);
         }

         return new RecipeException(message, recipeDetails);
      }
   }

   private static RecipeExceptionDetails getRecipeDetails(String errorCode, Object[] context) {
      RecipeExceptionDetails.ErrorMap errorMap = new RecipeExceptionDetails.ErrorMap();
      Locale[] var3 = LOCALES;
      int var4 = var3.length;

      for(int var5 = 0; var5 < var4; ++var5) {
         Locale loc = var3[var5];
         RecipeExceptionDetails.ErrorMap.Entry entry = new RecipeExceptionDetails.ErrorMap.Entry();
         entry.setKey(loc.getLanguage());
         be.recipe.services.core.RecipeError re = new be.recipe.services.core.RecipeError();
         re.setMessage(getI18nMessage(errorCode, loc, context).getMessage());
         entry.setValue(re);
         errorMap.entries.add(entry);
      }

      RecipeExceptionDetails red = new RecipeExceptionDetails();
      red.setCode(errorCode);
      red.setErrorMap(errorMap);
      return red;
   }

   private static RecipeError getI18nMessage(String errorCode, Locale locale, Object[] context) {
      if (locale == null) {
         locale = ENGLISH_LOCALE;
      }

      Locale.setDefault(locale);

      try {
         ResourceBundle rb = ResourceBundle.getBundle("RecipeErrors", locale);
         if (rb != null && rb.containsKey(errorCode)) {
            String msg = rb.getString(errorCode);
            return new RecipeError(MessageFormat.format(msg, context));
         }
      } catch (MissingResourceException var5) {
         LOG.error(var5.getMessage(), var5);
      }

      return new RecipeError("!!! " + errorCode + " !!!");
   }

   static {
      ENGLISH_LOCALE = Locale.ENGLISH;
      LOG = Logger.getLogger(RecipeExceptionFactory.class);
      LOCALES = new Locale[]{ENGLISH_LOCALE, DUTCH_LOCALE, FRENCH_LOCALE};
   }
}
