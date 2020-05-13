package be.business.connector.common;

import be.business.connector.common.module.TipConfigModule;
import be.business.connector.common.module.TipConfigModuleImpl;
import org.taktik.connector.business.recipeprojects.core.domain.CareProviderType;
import org.taktik.connector.business.recipeprojects.core.exceptions.IntegrationModuleException;
import org.taktik.connector.business.recipeprojects.core.utils.*;
import org.taktik.connector.business.recipeprojects.common.utils.ProductFilterSingleton;
import be.ehealth.technicalconnector.session.Session;
import org.apache.log4j.Logger;

/**
 * @author Liesje Demuynck.
 */
public class ApplicationConfig {

    private final static Logger LOG = Logger.getLogger(ApplicationConfig.class);

    private static ApplicationConfig instance;


    private ApplicationConfig() {
    }

    public static ApplicationConfig getInstance() {
        if (instance == null) {
            instance = new ApplicationConfig();
        }
        return instance;
    }


    /**
     * Checks if the currently loaded  sessionItem is valid and if not: throws a error
     */
    public void assertValidSession() throws IntegrationModuleException {
        SessionValidator.assertValidSession(Session.getInstance().getSession());
    }

    /**
     * Checks if the system is initialized and if not: throws a error
     */
    public void assertInitialized() throws IntegrationModuleException {
        if (PropertyHandler.getInstance() == null || EncryptionUtils.getInstance() == null) {
            throw new IntegrationModuleException(I18nHelper.getLabel("error.system.not.initialized"));
        }
    }

    public void setSystemKeystoreProperties(String systemKeystorePassword, String systemKeystorePath, String systemKeystoreDirectory, String systemKeystoreNIHIIPHARMACYCBE) throws IntegrationModuleException {
        assertInitialized();
        LOG.info("Setting key store : path " + systemKeystorePath + " directory : " + systemKeystoreDirectory + " Nihii : " + systemKeystoreNIHIIPHARMACYCBE);

        EncryptionUtils.getInstance().setSystemKeystorePassword(systemKeystorePassword);
        EncryptionUtils.getInstance().setSystemKeystorePath(systemKeystorePath);
        EncryptionUtils.getInstance().setSystemKeystoreDirectory(systemKeystoreDirectory);
        EncryptionUtils.getInstance().setSystemKeystoreRiziv(systemKeystoreNIHIIPHARMACYCBE);
        LOG.info("Setting key store - completed");
    }

    public void setOldSystemKeystoreProperties(String systemKeystorePassword, String systemKeystorePath, String systemKeystoreDirectory, String systemKeystoreNIHIIPHARMACYCBE) throws IntegrationModuleException {
        assertInitialized();
        EncryptionUtils.getInstance().setOldSystemKeystorePassword(systemKeystorePassword);
        EncryptionUtils.getInstance().setOldSystemKeystorePath(systemKeystorePath);
        EncryptionUtils.getInstance().setOldSystemKeystoreDirectory(systemKeystoreDirectory);
        EncryptionUtils.getInstance().setOldSystemKeystoreRiziv(systemKeystoreNIHIIPHARMACYCBE);
    }


    public void initialize() throws IntegrationModuleException {
        initialize(null, null);
    }

    public void initialize(String propertyfile, String vslidationFile) throws IntegrationModuleException {
        initialize(propertyfile, vslidationFile, null);
    }


    /**
     * Initializes the system with the given propertyFile and urlConfig
     *
     * @param propertyfile the property file to use
     * @param urlConf      the configuration directory to use
     * @throws IntegrationModuleException
     */
    public void initialize(String propertyfile, String vslidationFile, String urlConf) throws IntegrationModuleException {
        LOG.info("Initializing applicationConfig for propertyFile [" + propertyfile + "], validationFile [" + vslidationFile + "] and urlConfig [" + urlConf + "].");

        // When running in DOTNET, the current context class loader must be
        // overriden to avoid class not found exceptions!!!
        Thread.currentThread().setContextClassLoader(getClass().getClassLoader());
        System.setProperty("javax.xml.soap.SOAPFactory", "com.sun.xml.messaging.saaj.soap.ver1_1.SOAPFactory1_1Impl");

        initPropertyHandlerSingleton(propertyfile, vslidationFile, urlConf);
        initEncryptionUtilsSingleton();

        final PropertyHandler propertyHandler = PropertyHandler.getInstance();
        if (propertyHandler.hasProperty("care.provider.type")
                && propertyHandler.getProperty("care.provider.type").toUpperCase().equals(CareProviderType.PHARMACIST.toString())) {

            initProductFilterSingleton();
            getLatestTIPConfiguration();
        }

        MessageQueueHelper.unlockLockedFilesOnQueue();
        LOG.info("Application config successfully initialized.");
    }

    public void setProperty(String key, String value) {
        PropertyHandler.getInstance().setProperty(key, value);
    }

    public void setSystemProperty(String key, String value) {
        System.setProperty(key, value);
        LOG.info("System property: " + key + " is set with value: " + value);
    }

    private void getLatestTIPConfiguration() throws IntegrationModuleException {
        if (SessionValidator.isValidSession(Session.getInstance().getSession())) {
            final TipConfigModule tipConfigModule = new TipConfigModuleImpl();
            tipConfigModule.getLatestTIPConfig();
        } else {
            LOG.warn("Could not retrieve latest tip configuration, no valid session found");
        }
    }

    private PropertyHandler initPropertyHandlerSingleton(String propertyfile, String vslidationFile, String urlConf) throws IntegrationModuleException {
        PropertyHandler propertyHandler = new PropertyHandler(propertyfile, vslidationFile, urlConf);
        MessageDumper.getInstance().init(propertyHandler);
        return propertyHandler;
    }

    private void initProductFilterSingleton() throws IntegrationModuleException {
        ProductFilterSingleton.getInstance(PropertyHandler.getInstance());
    }

    private void initEncryptionUtilsSingleton() {
        new EncryptionUtils(PropertyHandler.getInstance());
    }

}
