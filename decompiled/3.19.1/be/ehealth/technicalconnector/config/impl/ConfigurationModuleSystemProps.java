package be.ehealth.technicalconnector.config.impl;

import be.ehealth.technicalconnector.config.Configuration;
import be.ehealth.technicalconnector.config.ConfigurationModule;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConfigurationModuleSystemProps implements ConfigurationModule {
   private static final Logger LOG = LoggerFactory.getLogger(ConfigurationModuleSystemProps.class);
   public static final String SYSTEMPROP_ROOTKEY = "be.ehealth.technicalconnector.config.impl.ConfigurationModuleSystemProps.systemprop";
   private static final String DELIMITER = "=";
   private List<String> oldValues = new ArrayList();
   private static final Pattern PATTERN = Pattern.compile("(.+?)=(.*)");

   public void init(Configuration config) throws TechnicalConnectorException {
      LOG.debug("Initializing ConfigurationModule " + this.getClass().getName());
      List<String> systemProps = config.getMatchingProperties("be.ehealth.technicalconnector.config.impl.ConfigurationModuleSystemProps.systemprop");
      Iterator i$ = systemProps.iterator();

      while(i$.hasNext()) {
         String systemProp = (String)i$.next();
         Matcher m = PATTERN.matcher(systemProp);
         if (!m.find()) {
            throw new IllegalArgumentException("Invalid argument [" + systemProp + "] doesnot match pattern [" + PATTERN + "].");
         }

         String key = m.group(1);
         String value = m.group(2);
         StringBuilder keyBuilder = new StringBuilder();
         keyBuilder.append(key).append("=");
         String oldValue = System.getProperty(key);
         if (oldValue != null) {
            keyBuilder.append(oldValue);
         }

         LOG.debug("Saving Property [{}] changed with value [{}].", key, oldValue);
         this.oldValues.add(keyBuilder.toString());
         LOG.debug("System Property [{}] changed with value [{}].", key, value);
         System.setProperty(key, value);
      }

   }

   public void unload() throws TechnicalConnectorException {
      LOG.debug("Unloading ConfigurationModule " + this.getClass().getName());
      Iterator i$ = this.oldValues.iterator();

      while(i$.hasNext()) {
         String systemProp = (String)i$.next();
         String[] splittedProp = systemProp.split("=");
         String key = splittedProp[0];
         if (splittedProp.length == 2) {
            String value = splittedProp[1];
            if (!StringUtils.isEmpty(key) && !StringUtils.isEmpty(value)) {
               LOG.debug("System Property [" + key + "] changed with value [" + value + "].");
               System.setProperty(key, value);
            }
         } else {
            System.clearProperty(key);
         }
      }

      this.oldValues.clear();
   }
}
