package be.business.connector.recipe.executor;

import org.aspectj.lang.JoinPoint;
import org.aspectj.runtime.internal.AroundClosure;

public class HospitalExecutorIntegrationModuleV4Impl$AjcClosure15 extends AroundClosure {
   public HospitalExecutorIntegrationModuleV4Impl$AjcClosure15(Object[] var1) {
      super(var1);
   }

   public Object run(Object[] var1) {
      Object[] var2 = super.state;
      HospitalExecutorIntegrationModuleV4Impl.revokePrescription_aroundBody14((HospitalExecutorIntegrationModuleV4Impl)var2[0], (String)var2[1], (String)var2[2], (JoinPoint)var2[3]);
      return null;
   }
}
