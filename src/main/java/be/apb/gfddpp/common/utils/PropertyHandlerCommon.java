package be.apb.gfddpp.common.utils;

import be.apb.gfddpp.common.exceptions.GFDDPPException;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.Map.Entry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PropertyHandlerCommon {
   private static final Logger LOG = LogManager.getLogger(PropertyHandlerCommon.class);
   private static PropertyHandlerCommon instance = null;
   private Properties properties;
   private Properties originalProperties;

   public PropertyHandlerCommon(String propertyfile) throws GFDDPPException {
      try {
         this.properties = new Properties();
         LOG.debug("Loading the custom File");
         this.properties.load(CommonIOUtils.getResourceAsStream(propertyfile));
         if (LOG.isDebugEnabled()) {
            LOG.debug("Current folder is : " + (new File(".")).getCanonicalPath());
            LOG.debug("Current properties are : ");
            Iterator i$ = this.properties.keySet().iterator();

            while(i$.hasNext()) {
               Object key = i$.next();
               LOG.debug(key + " = " + this.properties.getProperty((String)key));
            }
         }

         instance = this;
      } catch (IOException var4) {
         throw new GFDDPPException(var4.getMessage());
      }
   }

   public PropertyHandlerCommon(Properties properties) {
      this.properties = properties;
      instance = this;
   }

   public static PropertyHandlerCommon getInstance() {
      return instance;
   }

   public String getProperty(String string) {
      return this.getProperty(string, (String)null);
   }

   public Integer getIntegerProperty(String string) {
      return Integer.parseInt(this.getProperty(string));
   }

   public URL getURLProperty(String string) throws GFDDPPException {
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
                  URL url = PropertyHandlerCommon.class.getResource(wsdl);
                  if (url != null) {
                     return url;
                  } else {
                     url = new URL(wsdl);

                     try {
                        LOG.debug("Checking connection with " + wsdl);
                        url.openStream().close();
                     } catch (IOException var7) {
                        throw new GFDDPPException(var7.getMessage());
                     }

                     return url;
                  }
               }
            }
         }
      } catch (Throwable var8) {
         var8.printStackTrace();
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

         return this.properties.getProperty(string, defaultValue);
      }
   }

   public boolean hasProperty(String key) {
      return this.properties != null && this.properties.containsKey(key);
   }

   public Properties getProperties() {
      return this.properties;
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

   public void setDefaultSessionProperties(String niss) {
      if (this.originalProperties != null) {
         Iterator i$ = this.originalProperties.entrySet().iterator();

         while(i$.hasNext()) {
            Entry<Object, Object> entry = (Entry)i$.next();
            this.properties.setProperty(entry.getKey().toString(), entry.getValue().toString());
         }

         this.originalProperties = null;
      }

   }

   public Set<String> getAllPropertyKeys() {
      return this.properties.stringPropertyNames();
   }
}
