package be.business.connector.recipe.executor;

import org.aspectj.lang.JoinPoint;
import org.aspectj.runtime.internal.AroundClosure;

public class ExecutorIntegrationModuleDevV4Impl$AjcClosure17 extends AroundClosure {
   public ExecutorIntegrationModuleDevV4Impl$AjcClosure17(Object[] var1) {
      super(var1);
   }

   public Object run(Object[] var1) {
      Object[] var2 = super.state;
      ExecutorIntegrationModuleDevV4Impl.revokePrescription_aroundBody16((ExecutorIntegrationModuleDevV4Impl)var2[0], (String)var2[1], (String)var2[2], (JoinPoint)var2[3]);
      return null;
   }
}
