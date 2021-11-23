package be.business.connector.common;

import org.apache.log4j.Logger;

import be.business.connector.core.exceptions.IntegrationModuleException;
import be.business.connector.core.utils.EncryptionUtils;
import be.business.connector.core.utils.I18nHelper;
import be.business.connector.core.utils.MessageDumper;
import be.business.connector.core.utils.PropertyHandler;
import be.business.connector.core.utils.SessionValidator;
import be.ehealth.technicalconnector.session.Session;

/**
 * The Class ApplicationConfig.
 *
 * @author Liesje Demuynck.
 */
public class ApplicationConfig {

	/** The Constant LOG. */
	private static final Logger LOG = Logger.getLogger(ApplicationConfig.class);

	/** The instance. */
    private static ApplicationConfig instance;

	/**
	 * Instantiates a new application config.
	 */
    private ApplicationConfig() {
    }

	/**
	 * Gets the single instance of ApplicationConfig.
	 *
	 * @return single instance of ApplicationConfig
	 */
    public static ApplicationConfig getInstance() {
        if (instance == null) {
            instance = new ApplicationConfig();
        }
        return instance;
    }

    /**
	 * Checks if the currently loaded sessionItem is valid and if not: throws a error.
	 */
	public void assertValidSession() {
		SessionValidator.assertValidSession(Session.getInstance().getSession());
    }
    
	/**
	 * Assert valid pharmacy session.
	 */
    public void assertValidPharmacySession() {
    	SessionValidator.assertValidPharmacySession(Session.getInstance().getSession());
	}

	/**
	 * Assert valid hospital pharmacy session.
	 */
	public void assertValidHospitalPharmacySession() {
		SessionValidator.assertValidHospitalPharmacySession(Session.getInstance().getSession());
	}

    /**
	 * Checks if the system is initialized and if not: throws a error.
	 */
	public void assertInitialized() {
		if (PropertyHandler.getInstance() == null || EncryptionUtils.getInstance() == null) {
			throw new IntegrationModuleException(I18nHelper.getLabel("error.system.not.initialized"));
		}
    }

	/**
	 * Sets the system keystore properties.
	 *
	 * @param systemKeystorePassword
	 *            the system keystore password
	 * @param systemKeystorePath
	 *            the system keystore path
	 * @param systemKeystoreDirectory
	 *            the system keystore directory
	 * @param systemKeystoreNIHIIPHARMACYCBE
	 *            the system keystore NIHIIPHARMACYCBE
	 */
	public void setSystemKeystoreProperties(String systemKeystorePassword, String systemKeystorePath, String systemKeystoreDirectory,
			String systemKeystoreNIHIIPHARMACYCBE) {
        assertInitialized();
        LOG.info("Setting key store : path " + systemKeystorePath + " directory : " + systemKeystoreDirectory + " Nihii : " + systemKeystoreNIHIIPHARMACYCBE);

        EncryptionUtils.getInstance().setSystemKeystorePassword(systemKeystorePassword);
        EncryptionUtils.getInstance().setSystemKeystorePath(systemKeystorePath);
        EncryptionUtils.getInstance().setSystemKeystoreDirectory(systemKeystoreDirectory);
        EncryptionUtils.getInstance().setSystemKeystoreRiziv(systemKeystoreNIHIIPHARMACYCBE);
        LOG.info("Setting key store - completed");
    }

	/**
	 * Sets the old system keystore properties.
	 *
	 * @param systemKeystorePassword
	 *            the system keystore password
	 * @param systemKeystorePath
	 *            the system keystore path
	 * @param systemKeystoreDirectory
	 *            the system keystore directory
	 * @param systemKeystoreNIHIIPHARMACYCBE
	 *            the system keystore NIHIIPHARMACYCBE
	 */
	public void setOldSystemKeystoreProperties(String systemKeystorePassword, String systemKeystorePath, String systemKeystoreDirectory,
			String systemKeystoreNIHIIPHARMACYCBE) {
        assertInitialized();
        EncryptionUtils.getInstance().setOldSystemKeystorePassword(systemKeystorePassword);
        EncryptionUtils.getInstance().setOldSystemKeystorePath(systemKeystorePath);
        EncryptionUtils.getInstance().setOldSystemKeystoreDirectory(systemKeystoreDirectory);
        EncryptionUtils.getInstance().setOldSystemKeystoreRiziv(systemKeystoreNIHIIPHARMACYCBE);
    }


	/**
	 * Initialize.
	 */
	public void initialize() {
        initialize(null, null);
    }

	/**
	 * Initialize.
	 *
	 * @param propertyfile
	 *            the propertyfile
	 * @param validationFile
	 *            the validation file
	 */
	public void initialize(String propertyfile, String validationFile) {
        initialize(propertyfile, validationFile, null);
    }

    /**
	 * Initializes the system with the given propertyFile and urlConfig.
	 *
	 * @param propertyfile
	 *            the property file to use
	 * @param vslidationFile
	 *            the vslidation file
	 * @param urlConf
	 *            the configuration directory to use @
	 */
	public void initialize(String propertyfile, String vslidationFile, String urlConf) {
        LOG.info("Initializing applicationConfig for propertyFile [" + propertyfile + "], validationFile [" + vslidationFile + "] and urlConfig [" + urlConf + "].");
        Thread.currentThread().setContextClassLoader(getClass().getClassLoader());
        System.setProperty("javax.xml.soap.SOAPFactory", "com.sun.xml.messaging.saaj.soap.ver1_1.SOAPFactory1_1Impl");
        initPropertyHandlerSingleton(propertyfile, vslidationFile, urlConf);
        initEncryptionUtilsSingleton();
        LOG.info("Application config successfully initialized.");
    }

	/**
	 * Sets the property.
	 *
	 * @param key
	 *            the key
	 * @param value
	 *            the value
	 */
    public void setProperty(String key, String value) {
        PropertyHandler.getInstance().setProperty(key, value);
    }

	/**
	 * Sets the system property.
	 *
	 * @param key
	 *            the key
	 * @param value
	 *            the value
	 */
    public void setSystemProperty(String key, String value) {
        System.setProperty(key, value);
        LOG.info("System property: " + key + " is set with value: " + value);
    }
    
	/**
	 * Inits the property handler singleton.
	 *
	 * @param propertyfile
	 *            the propertyfile
	 * @param vslidationFile
	 *            the vslidation file
	 * @param urlConf
	 *            the url conf
	 * @return the property handler
	 * @throws IntegrationModuleException
	 *             the integration module exception
	 */
    private PropertyHandler initPropertyHandlerSingleton(String propertyfile, String vslidationFile, String urlConf) throws IntegrationModuleException {
        PropertyHandler propertyHandler = new PropertyHandler(propertyfile, vslidationFile, urlConf);
        MessageDumper.getInstance().init(propertyHandler);
        return propertyHandler;
    }

	/**
	 * Inits the encryption utils singleton.
	 */
    private void initEncryptionUtilsSingleton() {
        new EncryptionUtils(PropertyHandler.getInstance());
    }
}