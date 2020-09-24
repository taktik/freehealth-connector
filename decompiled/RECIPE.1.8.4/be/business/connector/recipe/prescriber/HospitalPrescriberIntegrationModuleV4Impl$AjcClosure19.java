package be.business.connector.recipe.prescriber;

import be.recipe.services.prescriber.GetPrescriptionStatusParam;
import org.aspectj.lang.JoinPoint;
import org.aspectj.runtime.internal.AroundClosure;

public class HospitalPrescriberIntegrationModuleV4Impl$AjcClosure19 extends AroundClosure {
   public HospitalPrescriberIntegrationModuleV4Impl$AjcClosure19(Object[] var1) {
      super(var1);
   }

   public Object run(Object[] var1) {
      Object[] var2 = super.state;
      return HospitalPrescriberIntegrationModuleV4Impl.getData_aroundBody18((HospitalPrescriberIntegrationModuleV4Impl)var2[0], (GetPrescriptionStatusParam)var2[1], (JoinPoint)var2[2]);
   }
}
