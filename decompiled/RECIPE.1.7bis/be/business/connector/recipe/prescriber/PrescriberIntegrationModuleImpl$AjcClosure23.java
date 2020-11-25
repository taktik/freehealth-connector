package be.business.connector.recipe.prescriber;

import org.aspectj.lang.JoinPoint;
import org.aspectj.runtime.internal.AroundClosure;

public class PrescriberIntegrationModuleImpl$AjcClosure23 extends AroundClosure {
   public PrescriberIntegrationModuleImpl$AjcClosure23(Object[] var1) {
      super(var1);
   }

   public Object run(Object[] var1) {
      Object[] var2 = super.state;
      return PrescriberIntegrationModuleImpl.getNewKeyFromKgss_aroundBody22((PrescriberIntegrationModuleImpl)var2[0], (String)var2[1], (String)var2[2], (String)var2[3], (String)var2[4], (byte[])var2[5], (JoinPoint)var2[6]);
   }
}
