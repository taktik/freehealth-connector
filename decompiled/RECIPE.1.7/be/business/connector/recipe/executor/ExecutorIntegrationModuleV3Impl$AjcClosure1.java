package org.taktik.connector.business.recipe.executor;

import org.aspectj.lang.JoinPoint;
import org.aspectj.runtime.internal.AroundClosure;

public class ExecutorIntegrationModuleV3Impl$AjcClosure1 extends AroundClosure {
   public ExecutorIntegrationModuleV3Impl$AjcClosure1(Object[] var1) {
      super(var1);
   }

   public Object run(Object[] var1) {
      Object[] var2 = super.state;
      return ExecutorIntegrationModuleV3Impl.getPrescription_aroundBody0((ExecutorIntegrationModuleV3Impl)var2[0], (String)var2[1], (JoinPoint)var2[2]);
   }
}
