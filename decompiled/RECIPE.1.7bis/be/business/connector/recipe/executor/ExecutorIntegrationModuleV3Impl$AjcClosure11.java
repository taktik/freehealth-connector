package be.business.connector.recipe.executor;

import org.aspectj.lang.JoinPoint;
import org.aspectj.runtime.internal.AroundClosure;
import org.aspectj.runtime.internal.Conversions;

public class ExecutorIntegrationModuleV3Impl$AjcClosure11 extends AroundClosure {
   public ExecutorIntegrationModuleV3Impl$AjcClosure11(Object[] var1) {
      super(var1);
   }

   public Object run(Object[] var1) {
      Object[] var2 = super.state;
      return ExecutorIntegrationModuleV3Impl.listNotifications_aroundBody10((ExecutorIntegrationModuleV3Impl)var2[0], Conversions.booleanValue(var2[1]), (JoinPoint)var2[2]);
   }
}
