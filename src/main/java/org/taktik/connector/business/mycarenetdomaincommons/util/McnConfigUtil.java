package org.taktik.connector.business.mycarenetdomaincommons.util;

import org.taktik.connector.business.mycarenetdomaincommons.domain.McnPackageInfo;
import org.taktik.connector.technical.config.ConfigFactory;
import org.taktik.connector.technical.config.ConfigValidator;

public final class McnConfigUtil {
   private static final String PACKAGE_LICENSE_USERNAME = "package.license.username";
   private static final String PACKAGE_LICENSE_PASSWORD = "package.license.password";
   private static final String PACKAGE_LICENSE_NAME = "package.name";

   private static final ConfigValidator configValidator = ConfigFactory.getConfigValidator();

   private McnConfigUtil() {
      throw new UnsupportedOperationException();
   }

   public static McnPackageInfo retrievePackageInfo(String componentName, String licenseUsername, String licensePassword) {
      String userName = licenseUsername != null ? licenseUsername : configValidator.getProperty(componentName + "." + PACKAGE_LICENSE_USERNAME, "${mycarenet.license.username}");
      String password = licensePassword != null ? licensePassword : configValidator.getProperty(componentName + "." + PACKAGE_LICENSE_PASSWORD, "${mycarenet.license.password}");
      String name = configValidator.getProperty(componentName + "." + PACKAGE_LICENSE_NAME, "${package.name}");
      return new McnPackageInfo(userName.trim(), password.trim(), name);
   }
}
