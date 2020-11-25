package org.perf4j.aop;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.commons.jexl.Expression;
import org.apache.commons.jexl.ExpressionFactory;
import org.apache.commons.jexl.JexlContext;
import org.apache.commons.jexl.context.HashMapContext;
import org.perf4j.LoggingStopWatch;

public class AgnosticTimingAspect {
   private Map<String, Expression> jexlExpressionCache = new ConcurrentHashMap(64, 0.75F, 16);

   public Object runProfiledMethod(AbstractJoinPoint joinPoint, Profiled profiled, LoggingStopWatch stopWatch) throws Throwable {
      if (!stopWatch.isLogging()) {
         return joinPoint.proceed();
      } else {
         stopWatch.setTimeThreshold(profiled.timeThreshold());
         Object retVal = null;
         Throwable exceptionThrown = null;
         boolean var14 = false;

         Object var6;
         try {
            var14 = true;
            var6 = retVal = joinPoint.proceed();
            var14 = false;
         } catch (Throwable var15) {
            exceptionThrown = var15;
            throw var15;
         } finally {
            if (var14) {
               String tag = this.getStopWatchTag(profiled, joinPoint, retVal, exceptionThrown);
               String message = this.getStopWatchMessage(profiled, joinPoint, retVal, exceptionThrown);
               if (profiled.logFailuresSeparately()) {
                  tag = exceptionThrown == null ? tag + ".success" : tag + ".failure";
               }

               stopWatch.stop(tag, message);
            }
         }

         String tag = this.getStopWatchTag(profiled, joinPoint, retVal, exceptionThrown);
         String message = this.getStopWatchMessage(profiled, joinPoint, retVal, exceptionThrown);
         if (profiled.logFailuresSeparately()) {
            tag = exceptionThrown == null ? tag + ".success" : tag + ".failure";
         }

         stopWatch.stop(tag, message);
         return var6;
      }
   }

   protected String getStopWatchTag(Profiled profiled, AbstractJoinPoint joinPoint, Object returnValue, Throwable exceptionThrown) {
      String tag;
      if ("@@USE_METHOD_NAME".equals(profiled.tag())) {
         tag = joinPoint.getMethodName();
      } else if (profiled.el() && profiled.tag().indexOf("{") >= 0) {
         tag = this.evaluateJexl(profiled.tag(), joinPoint.getParameters(), joinPoint.getExecutingObject(), returnValue, exceptionThrown);
      } else {
         tag = profiled.tag();
      }

      return tag;
   }

   protected String getStopWatchMessage(Profiled profiled, AbstractJoinPoint joinPoint, Object returnValue, Throwable exceptionThrown) {
      String message;
      if (profiled.el() && profiled.message().indexOf("{") >= 0) {
         message = this.evaluateJexl(profiled.message(), joinPoint.getParameters(), joinPoint.getExecutingObject(), returnValue, exceptionThrown);
         if ("".equals(message)) {
            message = null;
         }
      } else {
         message = "".equals(profiled.message()) ? null : profiled.message();
      }

      return message;
   }

   protected String evaluateJexl(String text, Object[] args, Object annotatedObject, Object returnValue, Throwable exceptionThrown) {
      StringBuilder retVal = new StringBuilder(text.length());
      JexlContext jexlContext = new HashMapContext();

      int bracketIndex;
      for(bracketIndex = 0; bracketIndex < args.length; ++bracketIndex) {
         jexlContext.getVars().put("$" + bracketIndex, args[bracketIndex]);
      }

      jexlContext.getVars().put("$this", annotatedObject);
      jexlContext.getVars().put("$return", returnValue);
      jexlContext.getVars().put("$exception", exceptionThrown);
      int lastCloseBracketIndex = -1;

      while((bracketIndex = text.indexOf(123, lastCloseBracketIndex + 1)) >= 0) {
         retVal.append(text.substring(lastCloseBracketIndex + 1, bracketIndex));
         lastCloseBracketIndex = text.indexOf(125, bracketIndex + 1);
         if (lastCloseBracketIndex == -1) {
            lastCloseBracketIndex = text.length();
         }

         String expressionText = text.substring(bracketIndex + 1, lastCloseBracketIndex);
         if (expressionText.length() > 0) {
            try {
               Object result = this.getJexlExpression(expressionText).evaluate(jexlContext);
               retVal.append(result);
            } catch (Exception var12) {
               retVal.append("_EL_ERROR_");
            }
         }
      }

      if (lastCloseBracketIndex < text.length()) {
         retVal.append(text.substring(lastCloseBracketIndex + 1, text.length()));
      }

      return retVal.toString();
   }

   protected Expression getJexlExpression(String expressionText) throws Exception {
      Expression retVal = (Expression)this.jexlExpressionCache.get(expressionText);
      if (retVal == null) {
         this.jexlExpressionCache.put(expressionText, retVal = ExpressionFactory.createExpression(expressionText));
      }

      return retVal;
   }
}
