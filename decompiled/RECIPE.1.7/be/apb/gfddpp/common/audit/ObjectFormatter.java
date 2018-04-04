package be.apb.gfddpp.common.audit;

import be.apb.standards.smoa.schema.model.v1.EncryptedContentType;
import java.lang.reflect.Field;
import java.util.logging.Logger;

public class ObjectFormatter {
   public static String formatParameters(Object[] args) {
      StringBuilder result = new StringBuilder();
      int j = 0;
      Object[] arr$ = args;
      int len$ = args.length;

      for(int i$ = 0; i$ < len$; ++i$) {
         Object object = arr$[i$];
         if (j > 0) {
            result.append(",");
         }

         result.append("arg" + j + "=");
         if (object == null) {
            result.append("null");
         } else {
            if (isPrimitive(object)) {
               result.append(object);
            } else if (object instanceof byte[]) {
               result.append("[..]");
            } else {
               result.append(object.getClass().getSimpleName());
               result.append("={");
               Field[] fields = object.getClass().getDeclaredFields();
               int i = 0;
               Field[] arr$ = fields;
               int len$ = fields.length;

               for(int i$ = 0; i$ < len$; ++i$) {
                  Field field = arr$[i$];
                  if (field.getType() != Logger.class && field.getType() != EncryptedContentType.class) {
                     try {
                        result.append(i == 0 ? "" : "; ");
                        boolean isAccessible = field.isAccessible();
                        field.setAccessible(true);
                        Object value = field.get(object);
                        if (value != null) {
                           result.append(field.getName());
                           result.append(": ");
                           result.append(trim(field.get(object).toString()));
                           ++i;
                        }

                        field.setAccessible(isAccessible ? isAccessible : !isAccessible);
                     } catch (IllegalAccessException var15) {
                        ;
                     }
                  }
               }

               result.append("}");
            }

            ++j;
         }
      }

      return result.toString();
   }

   public static String trim(String s) {
      return s != null && s.length() > 64 ? s.substring(0, 61) + "..." : s;
   }

   public static String formatSource(Class clazz, String method) {
      String clazzName = clazz.getSimpleName();
      if (clazzName.indexOf("_") > 0) {
         clazzName = clazzName.substring(0, clazzName.indexOf("_"));
      }

      return trim(clazzName + "." + method);
   }

   private static boolean isPrimitive(Object value) {
      return value instanceof String || value instanceof Number || value instanceof Boolean || value instanceof Character;
   }
}
