package be.business.connector.core.utils;

import be.business.connector.core.exceptions.IntegrationModuleEhealthException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
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

   private static boolean hasValidPharmacyAttributes(Element assertion) {
      NodeList attributes = STSHelper.getAttributes(assertion);
      if (attributes != null && attributes.getLength() != 0) {
         Map<String, String> failedAttributes = new HashMap();

         for(int i = 0; i < attributes.getLength(); ++i) {
            Element attribute = (Element)attributes.item(i);
            LOG.debug("SAML AttributeName : " + attribute.getAttribute("AttributeName") + " : TextContent : " + attribute.getTextContent());
            if ("urn:be:fgov:ehealth:1.0:pharmacy:nihii-number:recognisedpharmacy:boolean".equals(attribute.getAttribute("AttributeName")) && attribute.getTextContent().equals("false")) {
               failedAttributes.put(attribute.getAttribute("AttributeName"), attribute.getTextContent());
            }

            if ("urn:be:fgov:ehealth:1.0:pharmacy:nihii-number:person:ssin:ehealth:1.0:pharmacy-holder:boolean".equals(attribute.getAttribute("AttributeName")) && attribute.getTextContent().equals("false")) {
               failedAttributes.put(attribute.getAttribute("AttributeName"), attribute.getTextContent());
            }

            if (failedAttributes.size() > 0) {
               LOG.error("Not a recognized , eHealth doesn't recognised you... contact eHealth");
               throw new IntegrationModuleEhealthException(I18nHelper.getLabel("error.false.saml.attribute", new Object[]{toString(failedAttributes)}));
            }
         }

         return true;
      } else {
         return false;
      }
   }

   private static boolean hasValidHospitalPharmacyAttributes(Element assertion) {
      NodeList attributes = STSHelper.getAttributes(assertion);
      if (attributes != null && attributes.getLength() != 0) {
         Map<String, String> failedAttributes = new HashMap();

         for(int i = 0; i < attributes.getLength(); ++i) {
            Element attribute = (Element)attributes.item(i);
            LOG.debug("SAML AttributeName : " + attribute.getAttribute("AttributeName") + " : TextContent : " + attribute.getTextContent());
            if ("urn:be:fgov:ehealth:1.0:pharmacy:nihii-number:recognisedhospitalpharmacy:boolean".equals(attribute.getAttribute("AttributeName")) && attribute.getTextContent().equals("false")) {
               failedAttributes.put(attribute.getAttribute("AttributeName"), attribute.getTextContent());
            }

            if ("urn:be:fgov:ehealth:1.0:certificateholder:hospital:nihii-number:recognisedhospital:boolean".equals(attribute.getAttribute("AttributeName")) && attribute.getTextContent().equals("false")) {
               failedAttributes.put(attribute.getAttribute("AttributeName"), attribute.getTextContent());
            }

            if (failedAttributes.size() > 0) {
               LOG.error("Not a recognized , eHealth doesn't recognised you... contact eHealth");
               throw new IntegrationModuleEhealthException(I18nHelper.getLabel("error.false.saml.attribute", new Object[]{toString(failedAttributes)}));
            }
         }

         return true;
      } else {
         return false;
      }
   }

   private static String toString(Map<String, String> failedAttributes) {
      StringBuilder sb = new StringBuilder();
      Iterator var3 = failedAttributes.keySet().iterator();

      while(var3.hasNext()) {
         String key = (String)var3.next();
         String value = (String)failedAttributes.get(key);
         sb.append(key + "[" + value + "];");
      }

      return sb.toString();
   }

   public static boolean isValid(Element assertion) {
      return assertion != null && !hasExpired(assertion) && hasValidAttributes(assertion);
   }

   public static boolean isValidPharmacySession(Element assertion) {
      return assertion != null && !hasExpired(assertion) && hasValidAttributes(assertion) && hasValidPharmacyAttributes(assertion);
   }

   public static boolean isValidHospitalPharmacySession(Element assertion) {
      return assertion != null && !hasExpired(assertion) && hasValidAttributes(assertion) && hasValidHospitalPharmacyAttributes(assertion);
   }
}
