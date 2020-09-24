package org.perf4j.aop;

import java.lang.annotation.Annotation;

public class DefaultProfiled implements Profiled {
   public static final DefaultProfiled INSTANCE = new DefaultProfiled();

   private DefaultProfiled() {
   }

   public String tag() {
      return "@@USE_METHOD_NAME";
   }

   public String message() {
      return "";
   }

   public String logger() {
      return "org.perf4j.TimingLogger";
   }

   public String level() {
      return "INFO";
   }

   public boolean el() {
      return true;
   }

   public boolean logFailuresSeparately() {
      return false;
   }

   public long timeThreshold() {
      return 0L;
   }

   public Class<? extends Annotation> annotationType() {
      return this.getClass();
   }
}
