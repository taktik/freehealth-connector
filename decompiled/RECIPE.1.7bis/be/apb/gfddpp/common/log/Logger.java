package be.apb.gfddpp.common.log;

import java.net.MalformedURLException;
import java.net.URL;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.extras.DOMConfigurator;

public class Logger {
   org.apache.log4j.Logger rootLogger = null;
   private static Boolean initialized;
   private Class clazz = null;

   public Logger(Class clazz) {
      this.clazz = clazz;
   }

   public org.apache.log4j.Logger getRootLogger() {
      if (this.rootLogger == null) {
         synchronized(initialized) {
            if (!initialized) {
               URL url = null;
               if (System.getProperty("log4j.config.path") != null) {
                  try {
                     url = new URL(System.getProperty("log4j.config.path"));
                  } catch (MalformedURLException var5) {
                     var5.printStackTrace();
                  }
               } else {
                  url = Logger.class.getResource("/log4j.xml");
               }

               if (url == null) {
                  System.out.println("No log4j config file found...");
                  BasicConfigurator.configure();
               } else {
                  System.out.println("Initalizing log4j from " + url);
                  DOMConfigurator.configure(url);
               }

               initialized = true;
            }
         }

         this.rootLogger = org.apache.log4j.Logger.getLogger(this.clazz);
      }

      return this.rootLogger;
   }

   public void debug(Object message, Throwable t) {
      this.getRootLogger().debug(message, t);
   }

   public void debug(Object message) {
      this.getRootLogger().debug(message);
   }

   public void error(Object message, Throwable t) {
      this.getRootLogger().error(message, t);
   }

   public void error(Object message) {
      this.getRootLogger().error(message);
   }

   public void info(Object message, Throwable t) {
      this.getRootLogger().info(message, t);
   }

   public void info(Object message) {
      this.getRootLogger().info(message);
   }

   public boolean isDebugEnabled() {
      return this.getRootLogger().isDebugEnabled();
   }

   public void warn(Object message, Throwable t) {
      this.getRootLogger().warn(message, t);
   }

   public void warn(Object message) {
      this.getRootLogger().warn(message);
   }

   public static Logger getLogger(Class clazz) {
      return new Logger(clazz);
   }

   static {
      initialized = Boolean.FALSE;
   }
}
