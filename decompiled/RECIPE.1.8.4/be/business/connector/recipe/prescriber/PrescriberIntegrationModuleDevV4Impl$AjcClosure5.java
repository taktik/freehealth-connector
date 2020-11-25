package be.business.connector.recipe.prescriber;

import org.aspectj.lang.JoinPoint;
import org.aspectj.runtime.internal.AroundClosure;

public class PrescriberIntegrationModuleDevV4Impl$AjcClosure5 extends AroundClosure {
   public PrescriberIntegrationModuleDevV4Impl$AjcClosure5(Object[] var1) {
      super(var1);
   }

   public Object run(Object[] var1) {
      Object[] var2 = super.state;
      PrescriberIntegrationModuleDevV4Impl.prepareCreatePrescription_aroundBody4((PrescriberIntegrationModuleDevV4Impl)var2[0], (String)var2[1], (String)var2[2], (JoinPoint)var2[3]);
      return null;
   }
}
