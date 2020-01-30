package be.business.connector.core.utils;

import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import org.apache.log4j.Logger;

public class OnlinePropertiesHolder {
   private static final Logger LOG = Logger.getLogger(OnlinePropertiesHolder.class);
   private static OnlineProperties onlineProperties;
   private static boolean isXsdSet = false;
   private static PropertyHandler propertyHandler;

   private OnlinePropertiesHolder() {
   }

   public static OnlineProperties getInstance() {
      if (onlineProperties == null) {
         onlineProperties = new OnlineProperties();
      }

      return onlineProperties;
   }

   public static void reloadProperties() {
      if (onlineProperties != null) {
         Properties properties = getPropertyHandler().getProperties();
         if (onlineProperties.getProperties() != null && !onlineProperties.getProperties().isEmpty()) {
            properties.putAll(onlineProperties.getProperties());
         }

         log("After Reloading online properties: ", onlineProperties.getProperties());
         log("After Reloading online system properties: ", properties);
         getPropertyHandler().setProperties(properties);
         log("After setting the system properties: ", getPropertyHandler().getProperties());
      }

   }

   public static PropertyHandler getPropertyHandler() {
      if (propertyHandler == null) {
         propertyHandler = PropertyHandler.getInstance();
      }

      return propertyHandler;
   }

   private static void log(String message, Map<String, String> properties) {
      LOG.debug("**************************************************");
      LOG.debug("***********" + message + "**********");
      LOG.debug("**************************************************");
      if (properties != null && !properties.keySet().isEmpty()) {
         Iterator var3 = properties.keySet().iterator();

         while(var3.hasNext()) {
            String clientKey = (String)var3.next();
            LOG.debug(clientKey + " " + (String)properties.get(clientKey));
         }
      }

   }

   private static void log(String message, Properties properties) {
      LOG.debug("**************************************************");
      LOG.debug("***********" + message + "**********");
      LOG.debug("**************************************************");
      Iterator var3 = properties.stringPropertyNames().iterator();

      while(var3.hasNext()) {
         String clientKey = (String)var3.next();
         LOG.debug(clientKey + " " + properties.getProperty(clientKey));
      }

   }

   public static boolean isXsdSet() {
      return isXsdSet;
   }

   public static void setXsdSet(boolean isXsdSet) {
      OnlinePropertiesHolder.isXsdSet = isXsdSet;
   }
}
