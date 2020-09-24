package be.business.connector.recipe.prescriber;

import org.aspectj.lang.JoinPoint;
import org.aspectj.runtime.internal.AroundClosure;

public class HospitalPrescriberIntegrationModuleV4Impl$AjcClosure11 extends AroundClosure {
   public HospitalPrescriberIntegrationModuleV4Impl$AjcClosure11(Object[] var1) {
      super(var1);
   }

   public Object run(Object[] var1) {
      Object[] var2 = super.state;
      HospitalPrescriberIntegrationModuleV4Impl.sendNotification_aroundBody10((HospitalPrescriberIntegrationModuleV4Impl)var2[0], (byte[])var2[1], (String)var2[2], (String)var2[3], (JoinPoint)var2[4]);
      return null;
   }
}
