package org.perf4j.aop;

public interface AbstractJoinPoint {
   Object proceed() throws Throwable;

   Object getExecutingObject();

   Object[] getParameters();

   String getMethodName();
}
