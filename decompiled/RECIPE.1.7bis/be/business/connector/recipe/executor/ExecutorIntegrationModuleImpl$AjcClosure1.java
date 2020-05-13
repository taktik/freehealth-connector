package be.business.connector.recipe.executor;

import org.aspectj.lang.JoinPoint;
import org.aspectj.runtime.internal.AroundClosure;

public class ExecutorIntegrationModuleImpl$AjcClosure1 extends AroundClosure {
   public ExecutorIntegrationModuleImpl$AjcClosure1(Object[] var1) {
      super(var1);
   }

   public Object run(Object[] var1) {
      Object[] var2 = super.state;
      return ExecutorIntegrationModuleImpl.getPrescription_aroundBody0((ExecutorIntegrationModuleImpl)var2[0], (String)var2[1], (JoinPoint)var2[2]);
   }
}
