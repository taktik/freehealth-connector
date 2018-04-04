package be.ehealth.business.mycarenetdomaincommons.util;

import be.ehealth.business.mycarenetdomaincommons.domain.McnPackageInfo;
import org.taktik.connector.technical.config.ConfigFactory;
import org.taktik.connector.technical.config.ConfigValidator;
import org.taktik.connector.technical.exception.TechnicalConnectorException;

public final class McnConfigUtil {
   private static final String PACKAGE_LICENCE_USERNAME = "package.license.username";
   private static final String PACKAGE_LICENCE_PASSWORD = "package.license.password";
   private static final String PACKAGE_LICENCE_NAME = "package.name";

   private McnConfigUtil() {
      throw new UnsupportedOperationException();
   }

   public static McnPackageInfo retrievePackageInfo(String componentName) throws TechnicalConnectorException {
      ConfigValidator configValidator = ConfigFactory.getConfigValidator();
      String userName = configValidator.getProperty(componentName + "." + "package.license.username", "${mycarenet.license.username}");
      String password = configValidator.getProperty(componentName + "." + "package.license.password", "${mycarenet.license.password}");
      String name = configValidator.getProperty(componentName + "." + "package.name", "${package.name}");
      return new McnPackageInfo(userName, password, name);
   }
}
