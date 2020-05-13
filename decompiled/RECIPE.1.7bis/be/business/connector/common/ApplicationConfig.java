package be.business.connector.common;

import be.business.connector.common.module.TipConfigModule;
import be.business.connector.common.module.TipConfigModuleImpl;
import be.business.connector.core.domain.CareProviderType;
import be.business.connector.core.exceptions.IntegrationModuleException;
import be.business.connector.core.utils.EncryptionUtils;
import be.business.connector.core.utils.I18nHelper;
import be.business.connector.core.utils.MessageDumper;
import be.business.connector.core.utils.MessageQueueHelper;
import be.business.connector.core.utils.PropertyHandler;
import be.business.connector.core.utils.SessionValidator;
import be.business.connector.projects.common.utils.ProductFilterSingleton;
import be.ehealth.technicalconnector.session.Session;
import org.apache.log4j.Logger;

public class ApplicationConfig {
   private static final Logger LOG = Logger.getLogger(ApplicationConfig.class);
   private static ApplicationConfig instance;

   private ApplicationConfig() {
   }

   public static ApplicationConfig getInstance() {
      if (instance == null) {
         instance = new ApplicationConfig();
      }

      return instance;
   }

   public void assertValidSession() throws IntegrationModuleException {
      SessionValidator.assertValidSession(Session.getInstance().getSession());
   }

   public void assertInitialized() throws IntegrationModuleException {
      if (PropertyHandler.getInstance() == null || EncryptionUtils.getInstance() == null) {
         throw new IntegrationModuleException(I18nHelper.getLabel("error.system.not.initialized"));
      }
   }

   public void setSystemKeystoreProperties(String systemKeystorePassword, String systemKeystorePath, String systemKeystoreDirectory, String systemKeystoreNIHIIPHARMACYCBE) throws IntegrationModuleException {
      this.assertInitialized();
      LOG.info("Setting key store : path " + systemKeystorePath + " directory : " + systemKeystoreDirectory + " Nihii : " + systemKeystoreNIHIIPHARMACYCBE);
      EncryptionUtils.getInstance().setSystemKeystorePassword(systemKeystorePassword);
      EncryptionUtils.getInstance().setSystemKeystorePath(systemKeystorePath);
      EncryptionUtils.getInstance().setSystemKeystoreDirectory(systemKeystoreDirectory);
      EncryptionUtils.getInstance().setSystemKeystoreRiziv(systemKeystoreNIHIIPHARMACYCBE);
      LOG.info("Setting key store - completed");
   }

   public void setOldSystemKeystoreProperties(String systemKeystorePassword, String systemKeystorePath, String systemKeystoreDirectory, String systemKeystoreNIHIIPHARMACYCBE) throws IntegrationModuleException {
      this.assertInitialized();
      EncryptionUtils.getInstance().setOldSystemKeystorePassword(systemKeystorePassword);
      EncryptionUtils.getInstance().setOldSystemKeystorePath(systemKeystorePath);
      EncryptionUtils.getInstance().setOldSystemKeystoreDirectory(systemKeystoreDirectory);
      EncryptionUtils.getInstance().setOldSystemKeystoreRiziv(systemKeystoreNIHIIPHARMACYCBE);
   }

   public void initialize() throws IntegrationModuleException {
      this.initialize((String)null, (String)null);
   }

   public void initialize(String propertyfile, String vslidationFile) throws IntegrationModuleException {
      this.initialize(propertyfile, vslidationFile, (String)null);
   }

   public void initialize(String propertyfile, String vslidationFile, String urlConf) throws IntegrationModuleException {
      LOG.info("Initializing applicationConfig for propertyFile [" + propertyfile + "], validationFile [" + vslidationFile + "] and urlConfig [" + urlConf + "].");
      Thread.currentThread().setContextClassLoader(this.getClass().getClassLoader());
      System.setProperty("javax.xml.soap.SOAPFactory", "com.sun.xml.messaging.saaj.soap.ver1_1.SOAPFactory1_1Impl");
      this.initPropertyHandlerSingleton(propertyfile, vslidationFile, urlConf);
      this.initEncryptionUtilsSingleton();
      PropertyHandler propertyHandler = PropertyHandler.getInstance();
      if (propertyHandler.hasProperty("care.provider.type") && propertyHandler.getProperty("care.provider.type").toUpperCase().equals(CareProviderType.PHARMACIST.toString())) {
         this.initProductFilterSingleton();
         this.getLatestTIPConfiguration();
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
         TipConfigModule tipConfigModule = new TipConfigModuleImpl();
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
