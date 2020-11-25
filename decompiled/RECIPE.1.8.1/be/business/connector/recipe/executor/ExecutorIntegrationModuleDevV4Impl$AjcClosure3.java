package be.business.connector.recipe.executor;

import be.recipe.services.executor.GetPrescriptionStatusParam;
import org.aspectj.lang.JoinPoint;
import org.aspectj.runtime.internal.AroundClosure;

public class ExecutorIntegrationModuleDevV4Impl$AjcClosure3 extends AroundClosure {
   public ExecutorIntegrationModuleDevV4Impl$AjcClosure3(Object[] var1) {
      super(var1);
   }

   public Object run(Object[] var1) {
      Object[] var2 = super.state;
      return ExecutorIntegrationModuleDevV4Impl.getData_aroundBody2((ExecutorIntegrationModuleDevV4Impl)var2[0], (GetPrescriptionStatusParam)var2[1], (JoinPoint)var2[2]);
   }
}
