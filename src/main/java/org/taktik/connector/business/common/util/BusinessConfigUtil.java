package org.taktik.connector.business.common.util;

import org.taktik.connector.technical.config.ConfigFactory;
import org.taktik.connector.technical.config.ConfigValidator;
import org.taktik.connector.technical.exception.TechnicalConnectorException;
import org.taktik.connector.technical.exception.TechnicalConnectorExceptionValues;
import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class BusinessConfigUtil {
   private static final Logger LOG = LoggerFactory.getLogger(BusinessConfigUtil.class);
   public static final String USE_DEFAULT_PROPERTIES = ".usedefaultproperties";
   public static final String DEFAULT = "default";

   private BusinessConfigUtil() {
      throw new UnsupportedOperationException();
   }

   public static boolean retrieveBooleanProjectProperty(String prefix, String projectName, String suffix, boolean defaultValue) {
      ConfigValidator props = ConfigFactory.getConfigValidator();
      boolean result;
      if (props.getBooleanProperty(prefix + projectName + ".usedefaultproperties", true).booleanValue()) {
         result = props.getBooleanProperty(prefix + "default" + suffix, defaultValue).booleanValue();
      } else {
         result = props.getBooleanProperty(prefix + projectName + suffix, defaultValue).booleanValue();
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
