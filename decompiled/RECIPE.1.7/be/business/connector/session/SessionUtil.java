package be.business.connector.session;

import org.taktik.connector.business.recipeprojects.core.utils.SAML10Converter;
import be.ehealth.technicalconnector.beid.BeIDInfo;
import be.ehealth.technicalconnector.config.ConfigFactory;
import be.ehealth.technicalconnector.service.sts.security.Credential;
import be.ehealth.technicalconnector.service.sts.security.SAMLToken;
import be.ehealth.technicalconnector.service.sts.security.impl.KeyStoreCredential;
import be.ehealth.technicalconnector.service.sts.security.impl.SAMLHolderOfKeyToken;
import be.ehealth.technicalconnector.session.Session;
import be.ehealth.technicalconnector.session.SessionItem;
import be.ehealth.technicalconnector.session.SessionManager;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Properties;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.w3c.dom.Element;

public class SessionUtil {
   private static final Logger LOG = Logger.getLogger(SessionUtil.class);
   public static final String SESSIONMANAGER_SAMLATTRIBUTE_MANDATE = "sessionmanager.samlattribute.mandate";
   public static final String SESSIONMANAGER_SAMLATTRIBUTEDESIGNATOR_MANDATE = "sessionmanager.samlattributedesignator.mandate";
   public static final String SESSIONMANAGER_SAMLATTRIBUTE = "sessionmanager.samlattribute";
   public static final String SESSIONMANAGER_SAMLATTRIBUTEDESIGNATOR = "sessionmanager.samlattributedesignator";
   public static final String SYSTEM_KEYSTORE_PASSWORD = "KEYSTORE_PASSWORD";
   public static final String USER_PASSWORD = "user.password";
   public static final String USER_INSS = "user.inss";
   public static final String SYSTEM_KEYSTORE_FILE = "sessionmanager.holderofkey.keystore";
   public static final String AUTHENTICATION_ALIAS = "authentication";

   public static SessionItem createSession(SessionType sessionType, Properties configuration, String inss, String password) throws Exception {
      if (StringUtils.isNotBlank(inss)) {
         configuration.put("user.inss", inss);
      }

      if (StringUtils.isNotBlank(password)) {
         configuration.put("user.password", password);
      }

      switch(sessionType) {
      case EID_SESSION:
         return createNewSession(configuration);
      case FALLBACK_SESSION:
         return createNewFallbackSession(configuration);
      case MANDATE_SESSION:
         return createNewMandateSession(configuration);
      default:
         throw new IllegalArgumentException("Unsupported sessionType " + sessionType);
      }
   }

   public static SessionItem createNewSession(Properties configuration) throws Exception {
      configuration.setProperty("user.inss", retrieveUserInss(configuration));
      String systemKeystorePassword = configuration.getProperty("KEYSTORE_PASSWORD");
      LOG.info("Creating a new session");
      SessionManager sessionManager = initEHealthSessionManager(configuration);
      SessionItem sessionItem = sessionManager.createSession(systemKeystorePassword, systemKeystorePassword);
      LOG.info("new session created");
      return sessionItem;
   }

   public static SessionItem createNewFallbackSession(Properties configuration) throws Exception {
      String systemKeystorePassword = configuration.getProperty("KEYSTORE_PASSWORD");
      String identificationPassword = configuration.getProperty("user.password");
      LOG.info("Creating a new fallback session");
      SessionManager sessionManager = initEHealthSessionManager(configuration);
      SessionItem sessionItem = sessionManager.createFallbackSession(identificationPassword, systemKeystorePassword, systemKeystorePassword);
      LOG.info("New fallback session created");
      return sessionItem;
   }

   public static SessionItem createNewMandateSession(Properties configuration) throws Exception {
      configuration.setProperty("user.inss", retrieveUserInss(configuration));
      LOG.info("Creating a new mandated session");

      int i;
      for(i = 1; configuration.containsKey("sessionmanager.samlattribute." + i); ++i) {
         configuration.remove("sessionmanager.samlattribute." + i);
      }

      for(i = 1; configuration.containsKey("sessionmanager.samlattributedesignator." + i); ++i) {
         configuration.remove("sessionmanager.samlattributedesignator." + i);
      }

      String mandateAttributeValue;
      for(i = 1; configuration.containsKey("sessionmanager.samlattribute.mandate." + i); ++i) {
         mandateAttributeValue = configuration.getProperty("sessionmanager.samlattribute.mandate." + i);
         configuration.setProperty("sessionmanager.samlattribute." + i, mandateAttributeValue);
         LOG.info("using samlAttribute: " + mandateAttributeValue);
      }

      for(i = 1; configuration.containsKey("sessionmanager.samlattributedesignator.mandate." + i); ++i) {
         mandateAttributeValue = configuration.getProperty("sessionmanager.samlattributedesignator.mandate." + i);
         configuration.setProperty("sessionmanager.samlattributedesignator." + i, mandateAttributeValue);
         LOG.info("using samlAttributeDesignator: " + mandateAttributeValue);
      }

      String systemKeystorePassword = configuration.getProperty("KEYSTORE_PASSWORD");
      SessionManager sessionManager = initEHealthSessionManager(configuration);
      SessionItem sessionItem = sessionManager.createSession(systemKeystorePassword, systemKeystorePassword);
      LOG.info("New fallback session created");
      return sessionItem;
   }

   public static SAMLToken loadExistingSession(Properties configuration, SAMLToken samlToken) throws Exception {
      String systemKeystorePassword = configuration.getProperty("KEYSTORE_PASSWORD");
      LOG.info(String.format("Loading session [%s] from cache", samlToken.getAssertionID()));
      SessionManager sessionManager = initEHealthSessionManager(configuration);
      sessionManager.loadSession(samlToken, systemKeystorePassword, systemKeystorePassword);
      if (!sessionManager.hasValidSession()) {
         throw new IllegalArgumentException("Invalid session loaded from " + System.getProperty("session"));
      } else {
         LOG.info(String.format("Session [%s] loaded from cache", samlToken.getAssertionID()));
         return samlToken;
      }
   }

   public static SAMLToken createSAMLToken(Properties configuration, String samlTokenString) throws Exception {
      String systemKeystoreFile = configuration.getProperty("sessionmanager.holderofkey.keystore");
      String systemKeystorePassword = configuration.getProperty("KEYSTORE_PASSWORD");
      loadEHealthConfig(configuration);
      Element sessionElement = SAML10Converter.toElement(samlTokenString);
      Credential credential = new KeyStoreCredential(systemKeystoreFile, systemKeystorePassword, "authentication", systemKeystorePassword);
      return new SAMLHolderOfKeyToken(sessionElement, credential);
   }

   private static String retrieveUserInss(Properties configuration) throws Exception {
      LOG.debug("retrieving user INSS for session creation");
      String userInss;
      if (StringUtils.isNotBlank(configuration.getProperty("user"))) {
         LOG.debug("retrieving user INSS for session creation");
         userInss = configuration.getProperty("user");
      } else {
         LOG.debug("retrieving user INSS for session creation");
         userInss = BeIDInfo.getInstance("test").getIdentity().getNationalNumber();
      }

      LOG.debug(String.format("user INSS successfully retrieved: %s", userInss));
      return userInss;
   }

   private static SessionManager initEHealthSessionManager(Properties configuration) throws Exception {
      loadEHealthConfig(configuration);
      SessionManager sessionManager = Session.getInstance();
      sessionManager.unloadSession();
      return sessionManager;
   }

   private static void loadEHealthConfig(Properties configuration) throws Exception {
      File tempConfigurationFile = File.createTempFile("tempConfig", ".properties");
      configuration.store(new FileOutputStream(tempConfigurationFile), "configuration file in which the placeholders are processed");
      ConfigFactory.setConfigLocation(tempConfigurationFile.getAbsolutePath());
   }

   // $FF: synthetic class
   static class SyntheticClass_1 {
      // $FF: synthetic field
      static final int[] $SwitchMap$be$business$connector$session$SessionType = new int[SessionType.values().length];

      static {
         try {
            $SwitchMap$be$business$connector$session$SessionType[SessionType.EID_SESSION.ordinal()] = 1;
         } catch (NoSuchFieldError var3) {
            ;
         }

         try {
            $SwitchMap$be$business$connector$session$SessionType[SessionType.FALLBACK_SESSION.ordinal()] = 2;
         } catch (NoSuchFieldError var2) {
            ;
         }

         try {
            $SwitchMap$be$business$connector$session$SessionType[SessionType.MANDATE_SESSION.ordinal()] = 3;
         } catch (NoSuchFieldError var1) {
            ;
         }

      }
   }
}
