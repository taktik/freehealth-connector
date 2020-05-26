package be.business.connector.recipe.prescriber;

import org.aspectj.lang.JoinPoint;
import org.aspectj.runtime.internal.AroundClosure;

public class PrescriberIntegrationModuleImpl$AjcClosure15 extends AroundClosure {
   public PrescriberIntegrationModuleImpl$AjcClosure15(Object[] var1) {
      super(var1);
   }

   public Object run(Object[] var1) {
      Object[] var2 = super.state;
      PrescriberIntegrationModuleImpl.sendNotification_aroundBody14((PrescriberIntegrationModuleImpl)var2[0], (byte[])var2[1], (String)var2[2], (String)var2[3], (JoinPoint)var2[4]);
      return null;
   }
}
