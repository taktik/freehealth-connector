package be.ehealth.technicalconnector.utils;

public final class ConnectorReflectionUtils {
   private ConnectorReflectionUtils() {
   }

   public static String getClassName(Class<?> clazz) {
      return clazz.getName();
   }
}
