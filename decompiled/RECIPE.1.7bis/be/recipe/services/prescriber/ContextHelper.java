package be.recipe.services.prescriber;

import javax.annotation.Resource;
import javax.naming.Context;
import javax.naming.NamingException;

public class ContextHelper {
   @Resource
   Context context;
   private static ContextHelper instance;

   private ContextHelper() {
   }

   public static ContextHelper getInstance() {
      if (instance == null) {
         instance = new ContextHelper();
      }

      return instance;
   }

   public String getProperty(String property) {
      try {
         return (String)this.context.getEnvironment().get(property);
      } catch (NamingException var3) {
         throw new IllegalArgumentException("Invalid property '" + property + "'", var3);
      }
   }
}
