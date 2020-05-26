package org.perf4j.aop;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.CONSTRUCTOR})
public @interface Profiled {
   String DEFAULT_TAG_NAME = "@@USE_METHOD_NAME";

   String tag() default "@@USE_METHOD_NAME";

   String message() default "";

   String logger() default "org.perf4j.TimingLogger";

   String level() default "INFO";

   boolean el() default true;

   boolean logFailuresSeparately() default false;

   long timeThreshold() default 0L;
}
