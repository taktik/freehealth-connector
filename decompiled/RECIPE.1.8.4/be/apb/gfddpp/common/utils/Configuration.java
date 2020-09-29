package be.apb.gfddpp.common.utils;

import be.apb.gfddpp.common.log.Logger;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

public class Configuration {
   public static final String DEFAULT_CONFIG = "/batch.properties";
   public static final String PREFIX_ENV_CONFIG = "/batch-";
   public static final String SUFFIX_ENV_CONFIG = ".properties";
   private Properties properties = null;
   private String configFile = null;
   private static final Logger LOG = Logger.getLogger(Configuration.class);

   public Configuration() {
   }

   public Configuration(String configFile) {
      this.configFile = configFile;
   }

   public String getProperty(String key, String defaultValue) {
      return this.getPropertyFromConfig(key, defaultValue);
   }

   public Long getLongProperty(String key, Long defaultValue) {
      return Long.valueOf(this.getPropertyFromConfig(key, "" + defaultValue));
   }

   public String getProperty(String key) {
      return this.getPropertyFromConfig(key, (String)null);
   }

   public String getPropertyFromConfig(String key, String defaultValue) {
      if (key == null) {
         return defaultValue;
      } else {
         try {
            Properties p = this.getProperties();
            return p.getProperty(key, defaultValue);
         } catch (IOException var4) {
            return defaultValue;
         }
      }
   }

   public URL getURLProperty(String string) {
      try {
         return new URL(this.getProperty(string));
      } catch (MalformedURLException var3) {
         return null;
      }
   }

   public Integer getIntegerProperty(String string, String defaultValue) {
      return Integer.parseInt(this.getProperty(string, defaultValue));
   }

   public boolean propertyContainsKey(String key) {
      try {
         if (this.getProperties().containsKey(key)) {
            return true;
         }
      } catch (IOException var3) {
         var3.printStackTrace();
      }

      return false;
   }

   private Properties getProperties() throws IOException {
      if (this.properties == null) {
         this.properties = new Properties();
         if (this.configFile != null) {
            this.load(this.configFile);
         } else {
            this.load("/batch.properties");
         }

         if (System.getProperty("env") != null) {
            LOG.info("\n System.getProperty('env') != NULL: " + System.getProperty("env"));
            LOG.info("SO LOAD: /batch-" + System.getProperty("env").toLowerCase() + ".properties");
            this.load("/batch-" + System.getProperty("env").toLowerCase() + ".properties");
         } else {
            LOG.info("env System property not found, assuming dev.");
            this.load("/batch-dev.properties");
         }
      }

      return this.properties;
   }

   private void load(String file) throws IOException {
      InputStream is = Configuration.class.getResourceAsStream(file);
      LOG.info("Loading file " + file);
      if (is != null) {
         this.properties.load(is);
      }

   }
}
