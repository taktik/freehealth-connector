package be.business.connector.recipe.prescriber;

import org.aspectj.lang.JoinPoint;
import org.aspectj.runtime.internal.AroundClosure;

public class PrescriberIntegrationModuleImpl$AjcClosure5 extends AroundClosure {
   public PrescriberIntegrationModuleImpl$AjcClosure5(Object[] var1) {
      super(var1);
   }

   public Object run(Object[] var1) {
      Object[] var2 = super.state;
      return PrescriberIntegrationModuleImpl.getPrescription_aroundBody4((PrescriberIntegrationModuleImpl)var2[0], (String)var2[1], (JoinPoint)var2[2]);
   }
}
