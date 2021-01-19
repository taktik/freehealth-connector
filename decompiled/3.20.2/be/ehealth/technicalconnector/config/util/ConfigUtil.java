package be.ehealth.technicalconnector.config.util;

import be.ehealth.technicalconnector.config.ConfigFactory;
import be.ehealth.technicalconnector.config.ConfigValidator;
import be.ehealth.technicalconnector.config.impl.ConfigUtils;
import be.ehealth.technicalconnector.config.util.domain.PackageInfo;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** @deprecated */
@Deprecated
public final class ConfigUtil {
   private static final Logger LOG = LoggerFactory.getLogger(ConfigUtil.class);
   private static final String PACKAGE_LICENCE_USERNAME = "package.licence.username";
   private static final String PACKAGE_LICENCE_PASSWORD = "package.licence.password";
   private static final String PACKAGE_LICENCE_NAME = "package.name";
   public static final String USE_DEFAULT_PROPERTIES = ".usedefaultproperties";
   public static final String DEFAULT = "default";

   private ConfigUtil() {
      throw new UnsupportedOperationException();
   }

   public static PackageInfo retrievePackageInfo(String componentName) {
      ConfigValidator configValidator = ConfigFactory.getConfigValidator();
      String userName = configValidator.getProperty(componentName + "." + "package.licence.username", "${mycarenet.licence.username}");
      String password = configValidator.getProperty(componentName + "." + "package.licence.password", "${mycarenet.licence.password}");
      String name = configValidator.getProperty(componentName + "." + "package.name", "${package.name}");
      return new PackageInfo(userName, password, name);
   }

   public static boolean retrieveBooleanProjectProperty(String prefix, String projectName, String suffix, boolean defaultValue) {
      ConfigValidator props = ConfigFactory.getConfigValidator();
      boolean result;
      if (props.getBooleanProperty(prefix + projectName + ".usedefaultproperties", true)) {
         result = props.getBooleanProperty(prefix + "default" + suffix, defaultValue);
      } else {
         result = props.getBooleanProperty(prefix + projectName + suffix, defaultValue);
      }

      LOG.debug("retrieveBooleanProjectProperty for {}.{}.{} : returning {}", new Object[]{prefix, projectName, suffix, result});
      return result;
   }

   public static boolean isNet() throws TechnicalConnectorException {
      return ConfigUtils.isNet();
   }
}
