package be.business.connector.recipe.prescriber;

import org.aspectj.lang.JoinPoint;
import org.aspectj.runtime.internal.AroundClosure;

public class HospitalPrescriberIntegrationModuleV4Impl$AjcClosure7 extends AroundClosure {
   public HospitalPrescriberIntegrationModuleV4Impl$AjcClosure7(Object[] var1) {
      super(var1);
   }

   public Object run(Object[] var1) {
      Object[] var2 = super.state;
      return HospitalPrescriberIntegrationModuleV4Impl.getPrescription_aroundBody6((HospitalPrescriberIntegrationModuleV4Impl)var2[0], (String)var2[1], (JoinPoint)var2[2]);
   }
}
