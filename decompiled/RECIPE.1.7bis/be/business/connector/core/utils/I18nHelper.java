package be.business.connector.core.utils;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import org.apache.log4j.Logger;

public class I18nHelper {
   private static final Logger LOG = Logger.getLogger(I18nHelper.class);
   private static ResourceBundle bundle;
   private static ResourceBundle bundleNL;
   private static ResourceBundle bundleFR;
   private static ResourceBundle bundleENG;

   public static String getLabel(String key) {
      if (bundle == null) {
         bundle = ResourceBundle.getBundle("label", Locale.getDefault());
         LOG.info("Loading resource bundle for locale " + bundle.getLocale());
      }

      return bundle.getString(key);
   }

   public static String getLabel(String key, Object[] context) {
      if (bundle == null) {
         bundle = ResourceBundle.getBundle("label", Locale.getDefault());
         LOG.info("Loading resource bundle for locale " + bundle.getLocale());
      }

      String message = bundle.getString(key);
      return MessageFormat.format(message, context);
   }

   public static Map<String, String> getAllLanguagesLabels(String key) {
      Map<String, String> labels = new HashMap();
      if (bundleNL == null) {
         bundleNL = ResourceBundle.getBundle("label", new Locale("NL"));
         LOG.info("Loading resource bundle for locale " + bundleNL.getLocale());
      }

      if (bundleFR == null) {
         bundleFR = ResourceBundle.getBundle("label", Locale.FRENCH);
         LOG.info("Loading resource bundle for locale " + bundleFR.getLocale());
      }

      if (bundleENG == null) {
         bundleENG = ResourceBundle.getBundle("label", Locale.ROOT);
         LOG.info("Loading resource bundle for locale " + bundleENG.getLocale());
      }

      labels.put("NL", bundleNL.getString(key));
      labels.put("FR", bundleFR.getString(key));
      labels.put("ENG", bundleENG.getString(key));
      return labels;
   }

   public static Map<String, String> getAllLanguagesLabels(String key, Object[] context) {
      LOG.info("get value for key " + key);
      Map<String, String> labels = new HashMap();
      if (bundleNL == null) {
         bundleNL = ResourceBundle.getBundle("label", new Locale("NL"));
         LOG.info("Loading resource bundle for locale " + bundleNL.getLocale());
      }

      if (bundleFR == null) {
         bundleFR = ResourceBundle.getBundle("label", Locale.FRENCH);
         LOG.info("Loading resource bundle for locale " + bundleFR.getLocale());
      }

      if (bundleENG == null) {
         bundleENG = ResourceBundle.getBundle("label", Locale.ROOT);
         LOG.info("Loading resource bundle for locale " + bundleENG.getLocale());
      }

      labels.put("NL", MessageFormat.format(bundleNL.getString(key), context));
      labels.put("FR", MessageFormat.format(bundleFR.getString(key), context));
      labels.put("ENG", MessageFormat.format(bundleENG.getString(key), context));
      return labels;
   }
}
