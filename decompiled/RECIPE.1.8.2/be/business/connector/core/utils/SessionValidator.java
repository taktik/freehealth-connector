package be.business.connector.core.utils;

import be.business.connector.core.exceptions.IntegrationModuleException;
import be.ehealth.technicalconnector.session.SessionItem;

public class SessionValidator {
   public static void assertValidSession(SessionItem sessionItem) throws IntegrationModuleException {
      if (!isValidSession(sessionItem)) {
         throw new IntegrationModuleException(I18nHelper.getLabel("error.invalid.session"));
      }
   }

   public static void assertValidPharmacySession(SessionItem sessionItem) throws IntegrationModuleException {
      if (!isValidPharmacySession(sessionItem)) {
         throw new IntegrationModuleException(I18nHelper.getLabel("error.invalid.session"));
      }
   }

   public static boolean isValidSession(SessionItem sessionItem) {
      return sessionItem != null && sessionItem.getSAMLToken() != null && SAMLTokenValidator.isValid(sessionItem.getSAMLToken().getAssertion());
   }

   public static boolean isValidPharmacySession(SessionItem sessionItem) {
      return sessionItem != null && sessionItem.getSAMLToken() != null && SAMLTokenValidator.isValidPharmacySession(sessionItem.getSAMLToken().getAssertion());
   }
}
