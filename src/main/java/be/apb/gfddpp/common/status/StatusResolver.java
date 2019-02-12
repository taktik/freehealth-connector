package be.apb.gfddpp.common.status;

import java.text.MessageFormat;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class StatusResolver {
   private static final Logger LOG = LogManager.getLogger(StatusResolver.class);
   public static List<ResourceBundle> MESSAGE_BUNDLES;
   public static ResourceBundle MESSAGE_DEFAULT_BUNDLE;
   public static ResourceBundle TYPE_BUNDLE;
   static String DEFAULT_TYPE;

   public static String resolveMessage(ResourceBundle bundle, StatusCode statusCode) {
      String message = null;
      if (bundle != null && statusCode != null) {
         try {
            message = bundle.getString(statusCode.toString());
         } catch (MissingResourceException var4) {
            message = getDefaultMessage(bundle, statusCode);
         }
      } else {
         message = getDefaultMessage(bundle, statusCode);
      }

      LOG.info("StatusResolver - RESOURSEBUNDLE - Status message: " + message);
      return message;
   }

   public static String resolveMessage(ResourceBundle bundle, StatusCode statusCode, Object[] placeHolders) {
      String message = null;
      if (bundle != null && statusCode != null) {
         try {
            message = bundle.getString(statusCode.toString());
            message = MessageFormat.format(message, placeHolders);
         } catch (MissingResourceException var5) {
            message = getDefaultMessage(bundle, statusCode);
         }
      } else {
         message = getDefaultMessage(bundle, statusCode);
      }

      LOG.info("StatusResolver - RESOURSEBUNDLE - Status message: " + message);
      return message;
   }

   public static String resolveType(ResourceBundle bundle, StatusCode statusCode) {
      String type = null;
      if (bundle != null && statusCode != null) {
         try {
            type = bundle.getString(statusCode.toString());
         } catch (MissingResourceException var4) {
            type = DEFAULT_TYPE;
         }
      } else {
         type = DEFAULT_TYPE;
      }

      return type;
   }

   protected static String getDefaultMessage(ResourceBundle bundle, StatusCode statusCode) {
      return "Error message for error code " + statusCode + " could not be found in " + bundle.getLocale().getLanguage();
   }

   public static String getLanguage(ResourceBundle bundle) {
      return bundle != null ? bundle.getLocale().getLanguage().toUpperCase() : null;
   }

   public static ResourceBundle getLocalResourceBundle() {
      return getResourceBundle(Locale.getDefault().getLanguage());
   }

   public static ResourceBundle getResourceBundle(String local) {
      Iterator i$ = MESSAGE_BUNDLES.iterator();

      ResourceBundle resourceBundle;
      do {
         if (!i$.hasNext()) {
            return MESSAGE_DEFAULT_BUNDLE;
         }

         resourceBundle = (ResourceBundle)i$.next();
      } while(!getLanguage(resourceBundle).toLowerCase().equals(local));

      return resourceBundle;
   }

   static {
      MESSAGE_BUNDLES = Arrays.asList(ResourceBundle.getBundle("messages", Locale.ENGLISH, new UTF8Control()), ResourceBundle.getBundle("messages", Locale.FRENCH, new UTF8Control()), ResourceBundle.getBundle("messages", new Locale("NL"), new UTF8Control()));
      MESSAGE_DEFAULT_BUNDLE = (ResourceBundle)MESSAGE_BUNDLES.get(0);
      TYPE_BUNDLE = ResourceBundle.getBundle("types", new UTF8Control());
      DEFAULT_TYPE = "UNKNOWN";
   }
}
