package org.perf4j.aop;

import java.lang.reflect.Method;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;
import org.perf4j.LoggingStopWatch;

public abstract class AbstractEjbTimingAspect extends AgnosticTimingAspect {
   @AroundInvoke
   public Object doPerfLogging(final InvocationContext ctx) throws Throwable {
      final Method executingMethod = ctx.getMethod();
      Profiled profiled = executingMethod == null ? DefaultProfiled.INSTANCE : (Profiled)ctx.getMethod().getAnnotation(Profiled.class);
      if (profiled == null) {
         profiled = DefaultProfiled.INSTANCE;
      }

      return this.runProfiledMethod(new AbstractJoinPoint() {
         public Object proceed() throws Throwable {
            return ctx.proceed();
         }

         public Object getExecutingObject() {
            return ctx.getTarget();
         }

         public Object[] getParameters() {
            return ctx.getParameters();
         }

         public String getMethodName() {
            return executingMethod == null ? "null" : executingMethod.getName();
         }
      }, (Profiled)profiled, this.newStopWatch(((Profiled)profiled).logger(), ((Profiled)profiled).level()));
   }

   protected abstract LoggingStopWatch newStopWatch(String var1, String var2);
}
