package org.taktik.connector.business.mycarenetdomaincommons.util;

import org.taktik.connector.business.mycarenetdomaincommons.domain.McnPackageInfo;
import org.taktik.connector.technical.config.ConfigFactory;
import org.taktik.connector.technical.config.ConfigValidator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class McnConfigUtil {
   private static final String PACKAGE_LICENSE_USERNAME = "package.license.username";
   private static final String PACKAGE_LICENSE_PASSWORD = "package.license.password";
   private static final String PACKAGE_LICENSE_NAME = "package.name";

   private static final ConfigValidator configValidator = ConfigFactory.getConfigValidator();

   private static final Logger LOG = LoggerFactory.getLogger(McnConfigUtil.class);

   private McnConfigUtil() {
      throw new UnsupportedOperationException();
   }

   public static McnPackageInfo retrievePackageInfo(String componentName, String licenseUsername, String licensePassword, String packageName) {
      String userName = licenseUsername != null ? licenseUsername : configValidator.getProperty(componentName + "." + PACKAGE_LICENSE_USERNAME, "${mycarenet.license.username}");
      String password = licensePassword != null ? licensePassword : configValidator.getProperty(componentName + "." + PACKAGE_LICENSE_PASSWORD, "${mycarenet.license.password}");
      String name = packageName != null ? packageName : configValidator.getProperty(componentName + "." + PACKAGE_LICENSE_NAME, "${package.name}");
      return new McnPackageInfo(userName.trim(), password.trim(), name);
   }

   public static McnPackageInfo retrievePackageInfo(String componentName, String packageName, String hcpQuality) {
      String userNameKey = "mycarenet.licence." + hcpQuality + ".username";
      String passwordKey = "mycarenet.licence." + hcpQuality + ".password";

      LOG.info("retrievePackageInfo retrieving info using keys: " + userNameKey + " " + passwordKey);

      String userName = hcpQuality != null ? configValidator.getProperty(userNameKey) : null;
      String password = hcpQuality != null ? configValidator.getProperty(passwordKey) : null;
      String name = packageName != null ? packageName : configValidator.getProperty(componentName + "." + PACKAGE_LICENSE_NAME, "${package.name}");

      return new McnPackageInfo(userName.trim(), password.trim(), name);
   }
}
