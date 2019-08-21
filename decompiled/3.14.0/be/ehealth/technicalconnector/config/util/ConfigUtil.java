package be.ehealth.technicalconnector.config.util;

import be.ehealth.technicalconnector.config.ConfigFactory;
import be.ehealth.technicalconnector.config.ConfigValidator;
import be.ehealth.technicalconnector.config.util.domain.PackageInfo;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.exception.TechnicalConnectorExceptionValues;
import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** @deprecated */
@Deprecated
public final class ConfigUtil {
   private static final Logger LOG = LoggerFactory.getLogger(ConfigUtil.class);
   private static final String PACKAGE_LICENCE_USERNAME = "package.license.username";
   private static final String PACKAGE_LICENCE_PASSWORD = "package.license.password";
   private static final String PACKAGE_LICENCE_NAME = "package.name";
   public static final String USE_DEFAULT_PROPERTIES = ".usedefaultproperties";
   public static final String DEFAULT = "default";

   private ConfigUtil() {
      throw new UnsupportedOperationException();
   }

   public static PackageInfo retrievePackageInfo(String componentName) throws TechnicalConnectorException {
      ConfigValidator configValidator = ConfigFactory.getConfigValidator();
      String userName = configValidator.getProperty(componentName + "." + "package.license.username", "${mycarenet.license.username}");
      String password = configValidator.getProperty(componentName + "." + "package.license.password", "${mycarenet.license.password}");
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

      LOG.debug("retrieveBooleanProjectProperty for " + prefix + "." + projectName + "." + suffix + " : returning " + result);
      return result;
   }

   public static boolean isNet() throws TechnicalConnectorException {
      try {
         Enumeration resEnum = Thread.currentThread().getContextClassLoader().getResources("META-INF/MANIFEST.MF");
         if (resEnum.hasMoreElements()) {
            URL url = (URL)resEnum.nextElement();
            String path = url.getPath().toLowerCase();
            return StringUtils.contains(path, "ikvm");
         } else {
            return false;
         }
      } catch (IOException var3) {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_GENERAL, var3, new Object[]{var3.getMessage()});
      }
   }
}
