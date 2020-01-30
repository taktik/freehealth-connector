package be.apb.gfddpp.common.medicationscheme.status;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

public class MSStatusResolver {
   private static final String BUNDLE_NAME = "ms_messages";
   private static ResourceBundle resourceBundle;

   public static String getStatusMessage(MSStatusCode code) {
      return getResourceBundle().getString(code.name());
   }

   public static String getStatusMessage(MSStatusCode code, Object[] context) {
      return MessageFormat.format(getStatusMessage(code), context);
   }

   private static ResourceBundle getResourceBundle() {
      if (resourceBundle == null) {
         resourceBundle = ResourceBundle.getBundle("ms_messages", Locale.getDefault());
      }

      return resourceBundle;
   }
}
