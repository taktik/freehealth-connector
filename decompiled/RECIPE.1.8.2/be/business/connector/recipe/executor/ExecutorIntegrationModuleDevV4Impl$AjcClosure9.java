package be.business.connector.recipe.executor;

import be.recipe.services.executor.GetOpenPrescriptionForExecutor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.runtime.internal.AroundClosure;

public class ExecutorIntegrationModuleDevV4Impl$AjcClosure9 extends AroundClosure {
   public ExecutorIntegrationModuleDevV4Impl$AjcClosure9(Object[] var1) {
      super(var1);
   }

   public Object run(Object[] var1) {
      Object[] var2 = super.state;
      return ExecutorIntegrationModuleDevV4Impl.decryptGetOpenPrescriptionForExecutor_aroundBody8((ExecutorIntegrationModuleDevV4Impl)var2[0], (GetOpenPrescriptionForExecutor)var2[1], (JoinPoint)var2[2]);
   }
}
