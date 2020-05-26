package be.ehealth.business.mycarenetdomaincommons.util;

import be.ehealth.business.mycarenetdomaincommons.domain.McnPackageInfo;
import be.ehealth.technicalconnector.config.ConfigFactory;
import be.ehealth.technicalconnector.config.ConfigValidator;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;

public final class McnConfigUtil {
   private static final String PACKAGE_LICENCE_USERNAME = "package.licence.username";
   private static final String PACKAGE_LICENCE_PASSWORD = "package.licence.password";
   private static final String PACKAGE_LICENCE_NAME = "package.name";

   private McnConfigUtil() {
      throw new UnsupportedOperationException();
   }

   public static McnPackageInfo retrievePackageInfo(String componentName) throws TechnicalConnectorException {
      ConfigValidator configValidator = ConfigFactory.getConfigValidator();
      String userName = configValidator.getProperty(componentName + "." + "package.licence.username", "${mycarenet.licence.username}");
      String password = configValidator.getProperty(componentName + "." + "package.licence.password", "${mycarenet.licence.password}");
      String name = configValidator.getProperty(componentName + "." + "package.name", "${package.name}");
      return new McnPackageInfo(userName, password, name);
   }
}
