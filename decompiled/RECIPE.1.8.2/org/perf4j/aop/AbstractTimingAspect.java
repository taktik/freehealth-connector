package org.perf4j.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.perf4j.LoggingStopWatch;

@Aspect
public abstract class AbstractTimingAspect extends AgnosticTimingAspect {
   @Around(
      value = "execution(* *(..)) && @annotation(profiled)",
      argNames = "pjp,profiled"
   )
   public Object doPerfLogging(final ProceedingJoinPoint pjp, Profiled profiled) throws Throwable {
      return this.runProfiledMethod(new AbstractJoinPoint() {
         public Object proceed() throws Throwable {
            return pjp.proceed();
         }

         public Object getExecutingObject() {
            return pjp.getThis();
         }

         public Object[] getParameters() {
            return pjp.getArgs();
         }

         public String getMethodName() {
            return pjp.getSignature().getName();
         }
      }, profiled, this.newStopWatch(profiled.logger() + "", profiled.level()));
   }

   protected abstract LoggingStopWatch newStopWatch(String var1, String var2);
}
