package be.business.connector.recipe.executor;

import org.aspectj.lang.JoinPoint;
import org.aspectj.runtime.internal.AroundClosure;

public class HospitalExecutorIntegrationModuleV4Impl$AjcClosure27 extends AroundClosure {
   public HospitalExecutorIntegrationModuleV4Impl$AjcClosure27(Object[] var1) {
      super(var1);
   }

   public Object run(Object[] var1) {
      Object[] var2 = super.state;
      return HospitalExecutorIntegrationModuleV4Impl.getAndMarkAsDelivered_aroundBody26((HospitalExecutorIntegrationModuleV4Impl)var2[0], (String)var2[1], (JoinPoint)var2[2]);
   }
}
