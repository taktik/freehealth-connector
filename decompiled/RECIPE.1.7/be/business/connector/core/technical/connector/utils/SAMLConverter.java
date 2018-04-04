package org.taktik.connector.business.recipeprojects.core.technical.connector.utils;

import org.taktik.connector.business.recipeprojects.core.exceptions.IntegrationModuleException;
import org.taktik.connector.business.recipeprojects.core.utils.I18nHelper;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.w3c.dom.Element;

public class SAMLConverter {
   private static final Logger LOG = Logger.getLogger(SAMLConverter.class);

   public static String toXMLString(Element assertion) throws IntegrationModuleException {
      if (assertion != null) {
         try {
            return be.ehealth.technicalconnector.service.sts.utils.SAMLConverter.toXMLString(assertion);
         } catch (TechnicalConnectorException var3) {
            LOG.error("TechnicalConnectorException SAMLConverter webservice", var3);
            String eHealthMessage = var3.getLocalizedMessage();
            if (var3.getCause() != null && StringUtils.isNotEmpty(var3.getCause().getLocalizedMessage())) {
               eHealthMessage = eHealthMessage + " \nCause is: " + var3.getCause().getLocalizedMessage();
            }

            throw new IntegrationModuleException(I18nHelper.getLabel("technical.connector.error.utils", new Object[]{"SAMLConverter", eHealthMessage}), var3);
         }
      } else {
         return null;
      }
   }
}
