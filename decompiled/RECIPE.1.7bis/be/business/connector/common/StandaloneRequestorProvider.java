package be.business.connector.common;

import be.apb.gfddpp.domain.PharmacyIdType;
import be.business.connector.core.exceptions.IntegrationModuleException;
import be.business.connector.core.utils.I18nHelper;
import be.business.connector.core.utils.PropertyHandler;
import be.business.connector.core.utils.STSHelper;
import be.business.connector.core.utils.SessionValidator;
import be.ehealth.technicalconnector.session.Session;
import be.ehealth.technicalconnector.session.SessionItem;
import org.apache.commons.lang3.StringUtils;

public class StandaloneRequestorProvider {
   public static String getRequestorTypeInformation() throws IntegrationModuleException {
      ApplicationConfig.getInstance().assertInitialized();
      return getRequestorTypeInformation(Session.getInstance().getSession(), PropertyHandler.getInstance());
   }

   private static String getRequestorTypeInformation(SessionItem sessionItem, PropertyHandler propertyHandler) throws IntegrationModuleException {
      SessionValidator.assertValidSession(sessionItem);
      String type;
      if (propertyHandler != null && propertyHandler.hasProperty("requesttype")) {
         type = propertyHandler.getProperty("requesttype");
      } else {
         type = STSHelper.getType(sessionItem.getSAMLToken().getAssertion());
      }

      try {
         PharmacyIdType.valueOf(type);
         return type;
      } catch (IllegalArgumentException var4) {
         throw new IntegrationModuleException(I18nHelper.getLabel("error.validation.saml.type.not.found"), var4);
      }
   }

   private static String getRequestorIdInformation(SessionItem sessionItem, PropertyHandler propertyHandler) throws IntegrationModuleException {
      String nihii = null;
      if (propertyHandler != null && propertyHandler.hasProperty("requestid")) {
         nihii = propertyHandler.getProperty("requestid");
      } else {
         SessionValidator.assertValidSession(sessionItem);
         nihii = STSHelper.getNihii(sessionItem.getSAMLToken().getAssertion());
      }

      if (StringUtils.isBlank(nihii)) {
         throw new IntegrationModuleException(I18nHelper.getLabel("error.validation.saml.nihii.not.found"));
      } else {
         return nihii;
      }
   }

   public static String getRequestorIdInformation() throws IntegrationModuleException {
      ApplicationConfig.getInstance().assertInitialized();
      return getRequestorIdInformation(Session.getInstance().getSession(), PropertyHandler.getInstance());
   }
}
