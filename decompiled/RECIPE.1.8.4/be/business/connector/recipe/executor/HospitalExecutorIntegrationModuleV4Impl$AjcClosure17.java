package be.business.connector.recipe.executor;

import org.aspectj.lang.JoinPoint;
import org.aspectj.runtime.internal.AroundClosure;
import org.aspectj.runtime.internal.Conversions;

public class HospitalExecutorIntegrationModuleV4Impl$AjcClosure17 extends AroundClosure {
   public HospitalExecutorIntegrationModuleV4Impl$AjcClosure17(Object[] var1) {
      super(var1);
   }

   public Object run(Object[] var1) {
      Object[] var2 = super.state;
      return HospitalExecutorIntegrationModuleV4Impl.listNotifications_aroundBody16((HospitalExecutorIntegrationModuleV4Impl)var2[0], Conversions.booleanValue(var2[1]), (JoinPoint)var2[2]);
   }
}
