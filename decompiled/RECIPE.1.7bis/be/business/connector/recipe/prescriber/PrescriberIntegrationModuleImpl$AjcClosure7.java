package be.business.connector.recipe.prescriber;

import org.aspectj.lang.JoinPoint;
import org.aspectj.runtime.internal.AroundClosure;

public class PrescriberIntegrationModuleImpl$AjcClosure7 extends AroundClosure {
   public PrescriberIntegrationModuleImpl$AjcClosure7(Object[] var1) {
      super(var1);
   }

   public Object run(Object[] var1) {
      Object[] var2 = super.state;
      PrescriberIntegrationModuleImpl.revokePrescription_aroundBody6((PrescriberIntegrationModuleImpl)var2[0], (String)var2[1], (String)var2[2], (JoinPoint)var2[3]);
      return null;
   }
}
