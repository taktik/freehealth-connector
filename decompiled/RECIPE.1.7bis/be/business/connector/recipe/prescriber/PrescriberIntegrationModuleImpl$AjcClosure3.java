package be.business.connector.recipe.prescriber;

import org.aspectj.lang.JoinPoint;
import org.aspectj.runtime.internal.AroundClosure;

public class PrescriberIntegrationModuleImpl$AjcClosure3 extends AroundClosure {
   public PrescriberIntegrationModuleImpl$AjcClosure3(Object[] var1) {
      super(var1);
   }

   public Object run(Object[] var1) {
      Object[] var2 = super.state;
      PrescriberIntegrationModuleImpl.ping_aroundBody2((PrescriberIntegrationModuleImpl)var2[0], (JoinPoint)var2[1]);
      return null;
   }
}
