package be.business.connector.recipe.prescriber;

import be.recipe.services.prescriber.ValidationPropertiesParam;
import org.aspectj.lang.JoinPoint;
import org.aspectj.runtime.internal.AroundClosure;

public class HospitalPrescriberIntegrationModuleV4Impl$AjcClosure17 extends AroundClosure {
   public HospitalPrescriberIntegrationModuleV4Impl$AjcClosure17(Object[] var1) {
      super(var1);
   }

   public Object run(Object[] var1) {
      Object[] var2 = super.state;
      return HospitalPrescriberIntegrationModuleV4Impl.getData_aroundBody16((HospitalPrescriberIntegrationModuleV4Impl)var2[0], (ValidationPropertiesParam)var2[1], (JoinPoint)var2[2]);
   }
}
