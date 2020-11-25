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
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.w3c.dom.Element;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Properties;

/**
 * @author Liesje Demuynck.
 */
public class SessionUtil {

    private final static Logger LOG = Logger.getLogger(SessionUtil.class);
    public final static String SESSIONMANAGER_SAMLATTRIBUTE_MANDATE = "sessionmanager.samlattribute.mandate";
    public final static String SESSIONMANAGER_SAMLATTRIBUTEDESIGNATOR_MANDATE = "sessionmanager.samlattributedesignator.mandate";
    public final static String SESSIONMANAGER_SAMLATTRIBUTE = "sessionmanager.samlattribute";
    public final static String SESSIONMANAGER_SAMLATTRIBUTEDESIGNATOR = "sessionmanager.samlattributedesignator";
    public static final String SYSTEM_KEYSTORE_PASSWORD = "KEYSTORE_PASSWORD";
    public static final String USER_PASSWORD = "user.password";
    public static final String USER_INSS = "user.inss";
    public final static String SYSTEM_KEYSTORE_FILE = "sessionmanager.holderofkey.keystore";
    public static final String AUTHENTICATION_ALIAS = "authentication";


    public static SessionItem createSession(SessionType sessionType, Properties configuration, String inss, String password) throws Exception {
        if (StringUtils.isNotBlank(inss)) {
            configuration.put(SessionUtil.USER_INSS, inss);
        }
        if (StringUtils.isNotBlank(password)) {
            configuration.put(SessionUtil.USER_PASSWORD, password);
        }
        switch (sessionType) {
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
        // for a regular session we determine the user inss based on the EID card
        configuration.setProperty(USER_INSS, retrieveUserInss(configuration));

        // using the propertyhandler makes sure that some initial filtering is taken care of
        final String systemKeystorePassword = configuration.getProperty(SYSTEM_KEYSTORE_PASSWORD);

        LOG.info("Creating a new session");
        SessionManager sessionManager = initEHealthSessionManager(configuration);
        SessionItem sessionItem = sessionManager.createSession(systemKeystorePassword, systemKeystorePassword);
        LOG.info("new session created");
        return sessionItem;
    }

    public static SessionItem createNewFallbackSession(Properties configuration) throws Exception {

        // using the propertyhandler makes sure that some initial filtering is taken care of
        final String systemKeystorePassword = configuration.getProperty(SYSTEM_KEYSTORE_PASSWORD);
        final String identificationPassword = configuration.getProperty(USER_PASSWORD);

        LOG.info("Creating a new fallback session");
        SessionManager sessionManager = initEHealthSessionManager(configuration);
        SessionItem sessionItem = sessionManager.createFallbackSession(identificationPassword, systemKeystorePassword, systemKeystorePassword);
        LOG.info("New fallback session created");
        return sessionItem;
    }

    public static SessionItem createNewMandateSession(Properties configuration) throws Exception {
        // for a regular session we determine the user inss based on the EID card
        configuration.setProperty(USER_INSS, retrieveUserInss(configuration));

        LOG.info("Creating a new mandated session");
        // remove the original SAML attributes and designators
        for (int i = 1; configuration.containsKey(SESSIONMANAGER_SAMLATTRIBUTE + "." + i); i++) {
            configuration.remove(SESSIONMANAGER_SAMLATTRIBUTE + "." + i);
        }
        for (int i = 1; configuration.containsKey(SESSIONMANAGER_SAMLATTRIBUTEDESIGNATOR + "." + i); i++) {
            configuration.remove(SESSIONMANAGER_SAMLATTRIBUTEDESIGNATOR + "." + i);
        }

        // set the SAML attribute and designaters to the mandated properties in the config file
        for (int i = 1; configuration.containsKey(SESSIONMANAGER_SAMLATTRIBUTE_MANDATE + "." + i); i++) {
            final String mandateAttributeValue = configuration.getProperty(SESSIONMANAGER_SAMLATTRIBUTE_MANDATE + "." + i);
            configuration.setProperty(SESSIONMANAGER_SAMLATTRIBUTE + "." + i, mandateAttributeValue);
            LOG.info("using samlAttribute: " + mandateAttributeValue);
        }

        for (int i = 1; configuration.containsKey(SESSIONMANAGER_SAMLATTRIBUTEDESIGNATOR_MANDATE + "." + i); i++) {
            final String mandateAttributeValue = configuration.getProperty(SESSIONMANAGER_SAMLATTRIBUTEDESIGNATOR_MANDATE + "." + i);
            configuration.setProperty(SESSIONMANAGER_SAMLATTRIBUTEDESIGNATOR + "." + i, mandateAttributeValue);
            LOG.info("using samlAttributeDesignator: " + mandateAttributeValue);
        }

        final String systemKeystorePassword = configuration.getProperty(SYSTEM_KEYSTORE_PASSWORD);

        SessionManager sessionManager = initEHealthSessionManager(configuration);
        SessionItem sessionItem = sessionManager.createSession(systemKeystorePassword, systemKeystorePassword);
        LOG.info("New fallback session created");
        return sessionItem;
    }

    public static SAMLToken loadExistingSession(Properties configuration, SAMLToken samlToken) throws Exception {
        final String systemKeystorePassword = configuration.getProperty(SYSTEM_KEYSTORE_PASSWORD);

        LOG.info(String.format("Loading session [%s] from cache", samlToken.getAssertionID()));
        SessionManager sessionManager = initEHealthSessionManager(configuration);
        sessionManager.loadSession(samlToken, systemKeystorePassword, systemKeystorePassword);
        if (!sessionManager.hasValidSession()) {
            throw new IllegalArgumentException("Invalid session loaded from " + System.getProperty("session"));
        }
        LOG.info(String.format("Session [%s] loaded from cache", samlToken.getAssertionID()));
        return samlToken;
    }


    public static SAMLToken createSAMLToken(Properties configuration, String samlTokenString) throws Exception{
        final String systemKeystoreFile = configuration.getProperty(SYSTEM_KEYSTORE_FILE);
        final String systemKeystorePassword = configuration.getProperty(SYSTEM_KEYSTORE_PASSWORD);


        loadEHealthConfig(configuration);
        final Element sessionElement = SAML10Converter.toElement(samlTokenString);
        final Credential credential = new KeyStoreCredential(systemKeystoreFile, systemKeystorePassword, AUTHENTICATION_ALIAS, systemKeystorePassword);
        return new SAMLHolderOfKeyToken(sessionElement, credential);
    }
    /**
     * Utility method to retrieve the user inss
     *
     * @param configuration
     * @return
     * @throws Exception
     */
    private static String retrieveUserInss(Properties configuration) throws Exception {
        LOG.debug("retrieving user INSS for session creation");
        // Shall we use the EID to identify the user ? if the property user
        // (inami) is defined, the EID is not used.
        final String userInss;
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

}
