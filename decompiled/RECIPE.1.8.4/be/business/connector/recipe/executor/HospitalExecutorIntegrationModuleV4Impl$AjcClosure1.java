package be.business.connector.recipe.executor;

import be.recipe.services.executor.GetPrescriptionStatusParam;
import org.aspectj.lang.JoinPoint;
import org.aspectj.runtime.internal.AroundClosure;

public class HospitalExecutorIntegrationModuleV4Impl$AjcClosure1 extends AroundClosure {
   public HospitalExecutorIntegrationModuleV4Impl$AjcClosure1(Object[] var1) {
      super(var1);
   }

   public Object run(Object[] var1) {
      Object[] var2 = super.state;
      return HospitalExecutorIntegrationModuleV4Impl.getData_aroundBody0((HospitalExecutorIntegrationModuleV4Impl)var2[0], (GetPrescriptionStatusParam)var2[1], (JoinPoint)var2[2]);
   }
}
