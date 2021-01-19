package be.ehealth.technicalconnector.utils;

/** @deprecated */
@Deprecated
public abstract class ConnectorLogUtils {
   private ConnectorLogUtils() {
   }

   /** @deprecated */
   @Deprecated
   public static void logXmlObject(Object obj) {
      ConnectorXmlUtils.logXmlObject(obj);
   }
}
