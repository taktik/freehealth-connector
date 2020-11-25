package be.ehealth.technicalconnector.utils;

/** @deprecated */
@Deprecated
public final class ConnectorJaxbUtils {
   private ConnectorJaxbUtils() {
   }

   /** @deprecated */
   @Deprecated
   public static String marshal(Object obj) {
      return ConnectorXmlUtils.toString(obj);
   }
}
