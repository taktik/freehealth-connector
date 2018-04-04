package org.taktik.connector.business.recipeprojects.core.utils;

import org.taktik.connector.business.recipeprojects.core.exceptions.IntegrationModuleException;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

public class PropertyHandler {
   private static final Logger LOG = Logger.getLogger(PropertyHandler.class);
   private static PropertyHandler instance = null;
   private Properties properties;

   public PropertyHandler() throws IntegrationModuleException {
      this((String)null, (String)null, (String)null);
   }

   public PropertyHandler(String propertyfile, String validationFile) throws IntegrationModuleException {
      this(propertyfile, validationFile, (String)null);
   }

   public PropertyHandler(String propertyfile, String validationFile, String urlConf) throws IntegrationModuleException {
      Boolean onlyLoadDefaultProperties = StringUtils.isBlank(propertyfile);

      try {
         this.properties = new Properties();
         LOG.debug("Loading the default File");
         this.properties.load(IOUtils.getResourceAsStream("/connector-default.properties"));
         Path propertyFilePath = Paths.get(propertyfile);
         Path validationFilePath = Paths.get(validationFile);
         if (!onlyLoadDefaultProperties.booleanValue() && Files.exists(propertyFilePath, new LinkOption[0]) && Files.exists(validationFilePath, new LinkOption[0])) {
            LOG.debug("Loading the custom File");
            Path output = Paths.get(System.getProperty("java.io.tmpdir"), "connector-generated-properies.properties");
            if (Files.notExists(output, new LinkOption[0])) {
               Files.createFile(output);
            }

            Charset charset = StandardCharsets.UTF_8;
            Files.write(output, Files.readAllLines(propertyFilePath, charset), charset, StandardOpenOption.TRUNCATE_EXISTING);
            Files.write(output, Files.readAllLines(validationFilePath, charset), charset, StandardOpenOption.APPEND);

            try {
               String content = new String(Files.readAllBytes(output), charset);
               String pathToFile = propertyFilePath.getParent().toAbsolutePath().toString().replace("\\", "/");
               LOG.debug("%CONF% will be replaced by [" + pathToFile + "].");
               content = content.replaceAll("%CONF%", pathToFile);
               Files.write(output, content.getBytes(charset), new OpenOption[0]);
            } finally {
               this.properties.load(Files.newInputStream(output));
            }
         }

         instance = this;
         LoggingUtil.initLog4J(this);
         LOG.info("Properties updated.....");
         if (LOG.isDebugEnabled()) {
            LOG.debug("Current folder is : " + (new File(".")).getCanonicalPath());
            LOG.debug("Current properties are : ");
            Iterator var17 = this.properties.keySet().iterator();

            while(var17.hasNext()) {
               Object key = var17.next();
               LOG.debug(key + " = " + this.properties.getProperty((String)key));
            }
         }

         LOG.info("Log4j initialized.....");
      } catch (Exception var15) {
         throw new IntegrationModuleException(I18nHelper.getLabel("error.propertyfile"), var15);
      }
   }

   public PropertyHandler(Properties properties) {
      this.properties = properties;
      instance = this;
   }

   public static PropertyHandler getInstance() {
      return instance;
   }

   public String getProperty(String string) {
      return this.getProperty(string, (String)null);
   }

   public Integer getIntegerProperty(String string) {
      return Integer.parseInt(this.getProperty(string));
   }

   public URL getURLProperty(String string) throws IntegrationModuleException {
      try {
         String prop = this.getProperty(string);
         if (prop != null && prop.contains("META-INF")) {
            return this.getClass().getResource(prop);
         } else {
            String wsdl = this.getProperty(string);
            if (wsdl == null) {
               return null;
            } else {
               File f = new File(wsdl);
               if (f.exists()) {
                  return f.toURI().toURL();
               } else {
                  URL url = null;

                  try {
                     url = new URL(wsdl);
                  } catch (MalformedURLException var8) {
                     throw new IntegrationModuleException(I18nHelper.getLabel("wsdl.not.found", new String[]{prop}));
                  }

                  try {
                     LOG.debug("Checking connection with " + wsdl);
                     url.openStream().close();
                  } catch (IOException var7) {
                     throw new IntegrationModuleException(I18nHelper.getLabel("error.could.not.reach.url", new Object[]{wsdl}), var7);
                  }

                  return url;
               }
            }
         }
      } catch (Throwable var9) {
         Exceptionutils.errorHandler(var9);
         return null;
      }
   }

   public Integer getIntegerProperty(String string, String defaultValue) {
      return Integer.parseInt(this.getProperty(string, defaultValue));
   }

   public String getProperty(String string, String defaultValue) {
      if (this.properties == null) {
         LOG.warn("Properties are not initialized");
         return defaultValue;
      } else {
         if (!this.properties.containsKey(string)) {
            LOG.warn("Undefined property : " + string);
         }

         String propertyValue = this.properties.getProperty(string, defaultValue);
         if (!StringUtils.contains(string.toLowerCase(), "password")) {
            LOG.info("loading property " + string + " DefaultValue : " + defaultValue + " value returned : " + propertyValue);
         }

         return propertyValue != null ? propertyValue.trim() : propertyValue;
      }
   }

   public boolean hasProperty(String key) {
      return this.properties != null && this.properties.containsKey(key);
   }

   public Properties getProperties() {
      return this.properties;
   }

   public Properties getPropertiesCopy() {
      Properties copy = new Properties();
      copy.putAll(this.properties);
      return copy;
   }

   public List<String> getMatchingProperties(String rootKey) {
      int i = 1;
      ArrayList ret = new ArrayList();

      while(true) {
         String key = rootKey + "." + i;
         if (this.properties.getProperty(key) == null) {
            return ret;
         }

         ret.add(this.getProperty(key));
         ++i;
      }
   }

   public void setProperty(String key, String value) {
      this.properties.setProperty(key, value);
   }
}
