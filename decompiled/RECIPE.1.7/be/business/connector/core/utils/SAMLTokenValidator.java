package org.taktik.connector.business.recipeprojects.core.utils;

import org.taktik.connector.business.recipeprojects.core.exceptions.IntegrationModuleEhealthException;
import java.util.Calendar;
import org.apache.log4j.Logger;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class SAMLTokenValidator {
   private static final Logger LOG = Logger.getLogger(SAMLTokenValidator.class);

   private static boolean hasExpired(Element assertion) {
      if (assertion != null && STSHelper.getConditions(assertion).getLength() > 0) {
         Calendar calNotOnOrAfter = Calendar.getInstance();
         calNotOnOrAfter.setTime(STSHelper.getNotOnOrAfterConditions(assertion).getTime());
         Calendar now = Calendar.getInstance();
         return !now.before(calNotOnOrAfter);
      } else {
         return true;
      }
   }

   private static boolean hasValidAttributes(Element assertion) {
      NodeList attributes = STSHelper.getAttributes(assertion);
      if (attributes != null && attributes.getLength() != 0) {
         for(int i = 0; i < attributes.getLength(); ++i) {
            Element attribute = (Element)attributes.item(i);
            LOG.debug("SAML AttributeName : " + attribute.getAttribute("AttributeName") + " : TextContent : " + attribute.getTextContent());
            if ("".equals(attribute.getTextContent().trim())) {
               LOG.error("Empty SAML attribute designator, eHealth doesn't recognised you... contact eHealth");
               throw new IntegrationModuleEhealthException(I18nHelper.getLabel("error.saml.attribute", new String[]{attribute.getAttribute("AttributeName")}));
            }
         }

         return true;
      } else {
         return false;
      }
   }

   public static boolean isValid(Element assertion) {
      return assertion != null && !hasExpired(assertion) && hasValidAttributes(assertion);
   }
}
