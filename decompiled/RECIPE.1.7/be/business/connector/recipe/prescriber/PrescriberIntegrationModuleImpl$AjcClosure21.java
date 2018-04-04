package org.taktik.connector.business.recipe.prescriber;

import org.aspectj.lang.JoinPoint;
import org.aspectj.runtime.internal.AroundClosure;

public class PrescriberIntegrationModuleImpl$AjcClosure21 extends AroundClosure {
   public PrescriberIntegrationModuleImpl$AjcClosure21(Object[] var1) {
      super(var1);
   }

   public Object run(Object[] var1) {
      Object[] var2 = super.state;
      return PrescriberIntegrationModuleImpl.unsealFeedback_aroundBody20((PrescriberIntegrationModuleImpl)var2[0], (byte[])var2[1], (JoinPoint)var2[2]);
   }
}
