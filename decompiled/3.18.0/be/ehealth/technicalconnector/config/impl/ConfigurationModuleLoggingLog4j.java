package be.ehealth.technicalconnector.config.impl;

import be.ehealth.technicalconnector.enumeration.Charset;
import be.ehealth.technicalconnector.exception.InstantiationException;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.utils.ConnectorIOUtils;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Enumeration;
import java.util.Properties;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Appender;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.spi.Configurator;

public final class ConfigurationModuleLoggingLog4j {
   private static final String TRUE;
   private static final String FALSE;
   private static final String CONNECTOR_LOGGER_CONFIG = "connector.logger.through.config";
   private static final String CONNECTOR_LOGGER_FILE_CONFIG_LOCATION = "connector.logger.fileconfig.location";
   public static final String CONNECTOR_LOGGER_FILE_CONFIG_TYPE = "connector.logger.fileconfig.type";
   private static final String CONNECTOR_LOGGER_FILE_FILEPATH = "connector.logger.file.filepath";
   private static final String CONNECTOR_LOGGER_FILE_ENABLED = "connector.logger.file.enabled";
   private static final String CONNECTOR_LOGGER_PATTERNLAYOUT = "connector.logger.patternlayout";
   private static final String CONNECTOR_LOGGER_LEVEL = "connector.logger.level";
   private static final String CONNECTOR_LOGGER_CONSOLE_ENABLED = "connector.logger.console.enabled";
   private Enumeration<?> oldAppenders;
   private static String defaultImplementation;
   private static String type;

   public void init(Properties config) {
      String executed = System.getProperty(this.getClass().getName(), FALSE);
      if (FALSE.equalsIgnoreCase(executed)) {
         InputStream is = null;
         this.oldAppenders = Logger.getRootLogger().getAllAppenders();
         boolean configLogger = Boolean.parseBoolean(config.getProperty("connector.logger.through.config", TRUE));
         if (configLogger) {
            boolean consoleLogger = Boolean.parseBoolean(config.getProperty("connector.logger.console.enabled", TRUE));
            boolean fileLogger = Boolean.parseBoolean(config.getProperty("connector.logger.file.enabled", FALSE));
            String configLocation = this.determineLocation(consoleLogger, fileLogger);
            if (configLocation == null) {
               return;
            }

            byte[] configuration = this.getConfiguration(config, configLocation);
            is = new ByteArrayInputStream(configuration);
         } else {
            String configLocation = config.getProperty("connector.logger.fileconfig.location");
            if (StringUtils.isBlank(configLocation)) {
               return;
            }

            try {
               is = ConnectorIOUtils.getResourceAsStream(configLocation);
            } catch (TechnicalConnectorException var10) {
               throw new InstantiationException(var10);
            }
         }

         try {
            Class<Configurator> clazz = Class.forName(config.getProperty("connector.logger.fileconfig.type", defaultImplementation));
            Configurator configurator = (Configurator)clazz.newInstance();
            configurator.doConfigure((InputStream)is, LogManager.getLoggerRepository());
         } catch (Exception var9) {
            throw new InstantiationException(var9);
         }

         System.setProperty(this.getClass().getName(), TRUE);
      }

   }

   private byte[] getConfiguration(Properties config, String configLocation) {
      String configuration = "";

      try {
         configuration = IOUtils.toString(ConnectorIOUtils.getResourceAsStream(configLocation), Charset.UTF_8.getName());
      } catch (TechnicalConnectorException var6) {
         throw new InstantiationException(var6);
      } catch (IOException var7) {
         throw new InstantiationException(var7);
      }

      configuration = configuration.replaceAll("%%LEVEL%%", config.getProperty("connector.logger.level", "DEBUG"));
      configuration = configuration.replaceAll("%%PATTERN%%", config.getProperty("connector.logger.patternlayout", "%d{dd-MM-yyyy | HH:mm:ss} | %-5p | %c{1}:%L | %m%n"));
      if ("xml".equals(type)) {
         String value = config.getProperty("connector.logger.file.filepath") + "";
         value = StringUtils.replace(value, "./", System.getProperty("user.dir") + "/").replace("\\", "/");
         configuration = configuration.replaceAll("%%FILE_NAME%%", value);
      } else {
         configuration = configuration.replaceAll("%%FILE_NAME%%", config.getProperty("connector.logger.file.filepath"));
      }

      try {
         return configuration.getBytes(Charset.UTF_8.getName());
      } catch (UnsupportedEncodingException var5) {
         throw new InstantiationException(var5);
      }
   }

   private String determineLocation(boolean consoleLogger, boolean fileLogger) {
      String configLocation;
      if (consoleLogger && fileLogger) {
         configLocation = "/log4j/" + type + "/console.file";
      } else if (consoleLogger) {
         configLocation = "/log4j/" + type + "/console.only";
      } else {
         if (!fileLogger) {
            return null;
         }

         configLocation = "/log4j/" + type + "/file.only";
      }

      return configLocation;
   }

   public void unload() {
      Logger.getRootLogger().removeAllAppenders();

      while(this.oldAppenders.hasMoreElements()) {
         Appender appender = (Appender)this.oldAppenders.nextElement();
         Logger.getRootLogger().addAppender(appender);
      }

      System.setProperty(this.getClass().getName(), FALSE);
   }

   static {
      TRUE = Boolean.TRUE.toString();
      FALSE = Boolean.FALSE.toString();
      defaultImplementation = "org.apache.log4j.xml.DOMConfigurator";
      type = "xml";
   }
}
