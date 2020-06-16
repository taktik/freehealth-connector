package be.business.connector.recipe.executor;

import be.recipe.services.executor.PutRidsInProcessParam;
import org.aspectj.lang.JoinPoint;
import org.aspectj.runtime.internal.AroundClosure;

public class ExecutorIntegrationModuleV4Impl$AjcClosure25 extends AroundClosure {
   public ExecutorIntegrationModuleV4Impl$AjcClosure25(Object[] var1) {
      super(var1);
   }

   public Object run(Object[] var1) {
      Object[] var2 = super.state;
      return ExecutorIntegrationModuleV4Impl.putData_aroundBody24((ExecutorIntegrationModuleV4Impl)var2[0], (PutRidsInProcessParam)var2[1], (JoinPoint)var2[2]);
   }
}
