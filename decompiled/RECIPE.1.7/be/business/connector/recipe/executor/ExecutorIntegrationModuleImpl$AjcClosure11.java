package org.taktik.connector.business.recipe.executor;

import org.aspectj.lang.JoinPoint;
import org.aspectj.runtime.internal.AroundClosure;
import org.aspectj.runtime.internal.Conversions;

public class ExecutorIntegrationModuleImpl$AjcClosure11 extends AroundClosure {
   public ExecutorIntegrationModuleImpl$AjcClosure11(Object[] var1) {
      super(var1);
   }

   public Object run(Object[] var1) {
      Object[] var2 = super.state;
      return ExecutorIntegrationModuleImpl.listNotifications_aroundBody10((ExecutorIntegrationModuleImpl)var2[0], Conversions.booleanValue(var2[1]), (JoinPoint)var2[2]);
   }
}
