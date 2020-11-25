package org.perf4j.log4j.aop;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.aspectj.lang.NoAspectBoundException;
import org.aspectj.lang.annotation.Aspect;
import org.perf4j.aop.AbstractTimingAspect;
import org.perf4j.log4j.Log4JStopWatch;

@Aspect
public class TimingAspect extends AbstractTimingAspect {
   // $FF: synthetic field
   private static Throwable ajc$initFailureCause;
   // $FF: synthetic field
   public static final TimingAspect ajc$perSingletonInstance;

   protected Log4JStopWatch newStopWatch(String loggerName, String levelName) {
      Level level = Level.toLevel(levelName, Level.INFO);
      return new Log4JStopWatch(Logger.getLogger(loggerName), level, level);
   }

   public static TimingAspect aspectOf() {
      if (ajc$perSingletonInstance == null) {
         throw new NoAspectBoundException("org.perf4j.log4j.aop.TimingAspect", ajc$initFailureCause);
      } else {
         return ajc$perSingletonInstance;
      }
   }

   public static boolean hasAspect() {
      return ajc$perSingletonInstance != null;
   }

   // $FF: synthetic method
   private static void ajc$postClinit() {
      ajc$perSingletonInstance = new TimingAspect();
   }

   static {
      try {
         ajc$postClinit();
      } catch (Throwable var1) {
         ajc$initFailureCause = var1;
      }

   }
}
