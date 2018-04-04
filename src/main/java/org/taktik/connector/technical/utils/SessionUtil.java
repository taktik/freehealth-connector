package org.taktik.connector.technical.utils;

import org.taktik.connector.technical.config.ConfigFactory;
import org.taktik.connector.technical.config.Configuration;
import org.taktik.connector.technical.enumeration.CryptoType;
import org.taktik.connector.technical.exception.TechnicalConnectorException;
import org.taktik.connector.technical.exception.TechnicalConnectorExceptionValues;
import org.taktik.connector.technical.service.etee.Crypto;
import org.taktik.connector.technical.service.sts.security.Credential;
import org.taktik.connector.technical.session.Session;
import org.taktik.connector.technical.session.SessionItem;
import org.taktik.connector.technical.session.SessionManager;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public final class SessionUtil {
   private static final Logger LOG = LoggerFactory.getLogger(SessionUtil.class);
   private static final int NIHII8_LENGTH = 8;
   private static final String ASSERTION_NAMESPACE = "urn:oasis:names:tc:SAML:1.0:assertion";
   private static final String SAML_ATTRIBUTE_NAMESPACE = "AttributeNamespace";
   private static final String SAML_ATTRIBUTE_NAME = "AttributeName";
   private static final String ATTR_NAMESPACE = "urn:be:fgov:certified-namespace:ehealth";
   private static final String SUFFIX_NIHII11 = ".*nihii11$";
   private static final String USER_LASTNAME = "user.lastname";
   private static final String USER_FIRSTNAME = "user.firstname";
   private static final String USER_NIHII = "user.nihii";
   private static final String USER_INSS = "user.inss";
   private static final String SAML_ATTRIBUTE = "Attribute";
   private static Configuration config = ConfigFactory.getConfigValidatorFor("user.inss", "user.nihii", "user.firstname", "user.lastname");

   private SessionUtil() {
      throw new UnsupportedOperationException();
   }

   public static Crypto getCrypto(CryptoType type, Crypto defaultCrypto) throws TechnicalConnectorException {
      if (defaultCrypto == null) {
         switch(type) {
         case HOLDER_OF_KEY:
            return getHolderOfKeyCrypto();
         case ENCRYPTION:
            return getEncryptionCrypto();
         default:
            throw new IllegalArgumentException("Unsupported CryptoType " + type);
         }
      } else {
         return defaultCrypto;
      }
   }

   public static Credential getCredentail(CryptoType type, Credential defaultCredential) throws TechnicalConnectorException {
      if (defaultCredential == null) {
         switch(type) {
         case HOLDER_OF_KEY:
            return getHolderOfKeyCredential();
         case ENCRYPTION:
            return getEncryptionCredential();
         default:
            throw new IllegalArgumentException("Unsupported CryptoType " + type);
         }
      } else {
         return defaultCredential;
      }
   }

   public static Crypto getEncryptionCrypto() throws TechnicalConnectorException {
      SessionItem session = checkAndRetrieveSession();
      if (session.getEncryptionCrypto() == null) {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_CRYPTO, new Object[]{"there was no encryption crypto found in the session"});
      } else {
         return session.getEncryptionCrypto();
      }
   }

   public static Crypto getHolderOfKeyCrypto() throws TechnicalConnectorException {
      SessionItem session = checkAndRetrieveSession();
      if (session.getHolderOfKeyCrypto() == null) {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_CRYPTO, new Object[]{"there was no holder of key crypto found in the session"});
      } else {
         return session.getHolderOfKeyCrypto();
      }
   }

   public static Credential getHolderOfKeyCredential() throws TechnicalConnectorException {
      SessionItem session = checkAndRetrieveSession();
      if (session.getHolderOfKeyCredential() == null) {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_CRYPTO, new Object[]{"there was no holder of key credential found in the session"});
      } else {
         return session.getHolderOfKeyCredential();
      }
   }

   public static Credential getEncryptionCredential() throws TechnicalConnectorException {
      SessionItem session = checkAndRetrieveSession();
      if (session.getEncryptionCredential() == null) {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_CRYPTO, new Object[]{"there was no encryption credential found in the session"});
      } else {
         return session.getEncryptionCredential();
      }
   }

   public static SessionItem checkAndRetrieveSession() throws TechnicalConnectorException {
      SessionItem session = Session.getInstance().getSession();
      if (!Session.getInstance().hasValidSession()) {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.NO_VALID_SESSION, new Object[0]);
      } else {
         return session;
      }
   }

   public static String getNihii11() throws TechnicalConnectorException {
      String nihii = null;
      if (config.hasProperty("user.nihii")) {
         nihii = config.getProperty("user.nihii");
         validateToken(nihii, ".*nihii11$");
         return nihii;
      } else {
         LOG.debug("Could not find propertyuser.nihii in the configuration.");
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.INVALID_PROPERTY, new Object[]{"user.nihii"});
      }
   }

   public static String getNihii() throws TechnicalConnectorException {
      String nihii = null;
      if (config.hasProperty("user.nihii")) {
         nihii = config.getProperty("user.nihii");
         validateToken(nihii, ".*nihii11$");
         return nihii.substring(0, 8);
      } else {
         LOG.debug("Could not find property user.nihii in the configuration.");
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.INVALID_PROPERTY, new Object[]{"user.nihii"});
      }
   }

   public static String getNiss() throws TechnicalConnectorException {
      String niss = null;
      if (config.hasProperty("user.inss")) {
         niss = config.getProperty("user.inss");
         return niss;
      } else {
         LOG.debug("Could not find property user.inss  in the configuration.");
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.INVALID_PROPERTY, new Object[]{"user.inss"});
      }
   }

   public static be.fgov.ehealth.commons.core.v1.IdentifierType createIdentifierType(String identifier, String type) {
      be.fgov.ehealth.commons.core.v1.IdentifierType ident = new be.fgov.ehealth.commons.core.v1.IdentifierType();
      ident.setId(identifier);
      ident.setType(type);
      return ident;
   }

   public static String getFullName() {
      StringBuilder sb = new StringBuilder();
      sb.append(config.getProperty("user.firstname"));
      sb.append(" ");
      sb.append(config.getProperty("user.lastname"));
      return sb.toString();
   }

   public static String getFirstname() {
      return config.getProperty("user.firstname");
   }

   public static String getLastname() {
      return config.getProperty("user.lastname");
   }

   private static void validateToken(String configValue, String attributeNameRegex) throws TechnicalConnectorException {
      SessionManager sessionMgr = Session.getInstance();
      if (sessionMgr.hasValidSession()) {
         Element token = sessionMgr.getSession().getSAMLToken().getAssertion();
         List<String> tokenValueList = getAttributeValue(token, attributeNameRegex);
         if (!containsCaseInsensitive(configValue, tokenValueList)) {
            LOG.warn("Inconsisting configuration, expecting value [" + configValue + "] but gets [" + ArrayUtils.toString(tokenValueList.toArray(new String[0])) + "]");
         }
      }

   }

   public static boolean containsCaseInsensitive(String strToCompare, List<String> list) {
      Iterator i$ = list.iterator();

      String str;
      do {
         if (!i$.hasNext()) {
            return false;
         }

         str = (String)i$.next();
      } while(!str.equalsIgnoreCase(strToCompare));

      return true;
   }

   public static Map<String, List<String>> getMatchingAttributes(String attributeNamePattern) throws TechnicalConnectorException {
      SessionManager sessionMgr = Session.getInstance();
      if (!sessionMgr.hasValidSession()) {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.NO_VALID_SESSION, new Object[0]);
      } else {
         Element token = sessionMgr.getSession().getSAMLToken().getAssertion();
         NodeList attributes = extractAttributes(token);
         Map<String, List<String>> result = new HashMap();
         if (attributes != null) {
            for(int i = 0; i < attributes.getLength(); ++i) {
               Node node = attributes.item(i);
               String attributeName = node.getAttributes().getNamedItem("AttributeName").getTextContent();
               if (attributeName.matches(attributeNamePattern)) {
                  if (!node.hasChildNodes()) {
                     result.put(attributeName, Arrays.asList(node.getTextContent().trim()));
                  } else {
                     NodeList attributeValueNodeList = node.getChildNodes();
                     List<String> values = new ArrayList();

                     for(int index = 0; index < attributeValueNodeList.getLength(); ++index) {
                        values.add(attributeValueNodeList.item(index).getTextContent().trim());
                     }

                     result.put(attributeName, values);
                  }
               }
            }
         }

         return result;
      }
   }

   private static List<String> getAttributeValue(Element token, String attributeNameSuffix) throws TechnicalConnectorException {
      NodeList attributes = extractAttributes(token);
      List<String> result = new ArrayList();
      if (attributes != null) {
         for(int i = 0; i < attributes.getLength(); ++i) {
            Node node = attributes.item(i);
            String attributeName = node.getAttributes().getNamedItem("AttributeName").getTextContent();
            String attributeNamespace = node.getAttributes().getNamedItem("AttributeNamespace").getTextContent();
            if (attributeName.matches(attributeNameSuffix) && attributeNamespace.equals("urn:be:fgov:certified-namespace:ehealth")) {
               if (node.hasChildNodes()) {
                  NodeList attributeValueNodeList = node.getChildNodes();

                  for(int index = 0; index < attributeValueNodeList.getLength(); ++index) {
                     result.add(attributeValueNodeList.item(index).getTextContent().trim());
                  }
               } else {
                  result.add(node.getTextContent().trim());
               }
            }
         }
      }

      if (result.isEmpty()) {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.INVALID_TOKEN, new Object[]{"Token doesn't contain an attribute with " + attributeNameSuffix + " in namespace " + "urn:be:fgov:certified-namespace:ehealth"});
      } else {
         return result;
      }
   }

   private static NodeList extractAttributes(Element element) {
      NodeList attributes = element.getElementsByTagName("Attribute");
      if (attributes.getLength() == 0) {
         attributes = element.getElementsByTagNameNS("urn:oasis:names:tc:SAML:1.0:assertion", "Attribute");
         if (attributes.getLength() == 0) {
            return null;
         }
      }

      return attributes;
   }

   // $FF: synthetic class
   static class SyntheticClass_1 {
      // $FF: synthetic field
      static final int[] $SwitchMap$be$ehealth$technicalconnector$enumeration$CryptoType = new int[CryptoType.values().length];

      static {
         try {
            $SwitchMap$be$ehealth$technicalconnector$enumeration$CryptoType[CryptoType.HOLDER_OF_KEY.ordinal()] = 1;
         } catch (NoSuchFieldError var2) {
            ;
         }

         try {
            $SwitchMap$be$ehealth$technicalconnector$enumeration$CryptoType[CryptoType.ENCRYPTION.ordinal()] = 2;
         } catch (NoSuchFieldError var1) {
            ;
         }

      }
   }
}
