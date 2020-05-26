package be.business.connector.recipe.executor;

import org.aspectj.lang.JoinPoint;
import org.aspectj.runtime.internal.AroundClosure;

public class ExecutorIntegrationModuleDevV4Impl$AjcClosure29 extends AroundClosure {
   public ExecutorIntegrationModuleDevV4Impl$AjcClosure29(Object[] var1) {
      super(var1);
   }

   public Object run(Object[] var1) {
      Object[] var2 = super.state;
      return ExecutorIntegrationModuleDevV4Impl.getAndMarkAsDelivered_aroundBody28((ExecutorIntegrationModuleDevV4Impl)var2[0], (String)var2[1], (JoinPoint)var2[2]);
   }
}
