package be.business.connector.core.exceptions;

import be.recipe.common.exceptions.RecipeException;
import be.recipe.common.exceptions.RecipeExceptionDetails;
import be.recipe.services.core.LangageType;
import be.recipe.services.core.LocalisedString;
import be.recipe.services.core.PrescriptionStatus;
import be.recipe.services.core.ResponseType;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

public class IntegrationModuleException extends RuntimeException {
   private static final long serialVersionUID = 1L;
   private String code;
   private String statusUpdater;
   private PrescriptionStatus prescriptionStatus;
   private String messageCode;
   private List<LocalisedString> messages = new ArrayList();

   public IntegrationModuleException() {
   }

   public IntegrationModuleException(String message, Throwable cause) {
      super(message, cause);
   }

   public IntegrationModuleException(String code, String message) {
      super(message);
      this.code = code;
   }

   public IntegrationModuleException(String message, ResponseType response) {
      super(message);
      this.code = response.getStatus().getCode();
      this.statusUpdater = response.getStatus().getStatusUpdater();
      if (response != null && response.getStatus() != null && response.getStatus().getPrescriptionStatus() != null) {
         this.prescriptionStatus = PrescriptionStatus.valueOf(response.getStatus().getPrescriptionStatus());
      }

      this.messageCode = response.getStatus().getMessageCode();
      this.messages.addAll(response.getStatus().getMessages());
   }

   public IntegrationModuleException(String message) {
      super(message);
   }

   public IntegrationModuleException(Throwable cause) {
      super(cause);
   }

   public String getLocalizedMessage() {
      return this.getMessage();
   }

   public String getMessage() {
      String locale = getUserLocale();
      Throwable cause = this.getCause();
      if (cause instanceof RecipeException) {
         RecipeException re = (RecipeException)cause;
         RecipeExceptionDetails e = re.getFaultInfo();
         RecipeExceptionDetails.ErrorMap list = e.getErrorMap();
         Iterator var7 = list.getEntries().iterator();

         while(var7.hasNext()) {
            RecipeExceptionDetails.ErrorMap.Entry entry = (RecipeExceptionDetails.ErrorMap.Entry)var7.next();
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

   public String getCode() {
      return this.code;
   }

   public void setCode(String code) {
      this.code = code;
   }

   public String getStatusUpdater() {
      return this.statusUpdater;
   }

   public void setStatusUpdater(String statusUpdater) {
      this.statusUpdater = statusUpdater;
   }

   public String getMessageCode() {
      return this.messageCode;
   }

   public void setMessageCode(String messageCode) {
      this.messageCode = messageCode;
   }

   public List<LocalisedString> getMessages() {
      return this.messages;
   }

   public String getMessage(LangageType language) {
      Iterator var3 = this.messages.iterator();

      while(var3.hasNext()) {
         LocalisedString ls = (LocalisedString)var3.next();
         if (ls.getLang() == language) {
            return ls.getValue();
         }
      }

      return null;
   }

   public void setMessages(List<LocalisedString> messages) {
      Iterator var3 = messages.iterator();

      while(var3.hasNext()) {
         LocalisedString s = (LocalisedString)var3.next();
         this.messages.add(s);
      }

   }

   public PrescriptionStatus getPrescriptionStatus() {
      return this.prescriptionStatus;
   }

   public void setPrescriptionStatus(PrescriptionStatus prescriptionStatus) {
      this.prescriptionStatus = prescriptionStatus;
   }
}
