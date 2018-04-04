package org.taktik.connector.business.recipe.prescriber;

import org.aspectj.lang.JoinPoint;
import org.aspectj.runtime.internal.AroundClosure;
import org.aspectj.runtime.internal.Conversions;

public class PrescriberIntegrationModuleImpl$AjcClosure17 extends AroundClosure {
   public PrescriberIntegrationModuleImpl$AjcClosure17(Object[] var1) {
      super(var1);
   }

   public Object run(Object[] var1) {
      Object[] var2 = super.state;
      PrescriberIntegrationModuleImpl.updateFeedbackFlag_aroundBody16((PrescriberIntegrationModuleImpl)var2[0], (String)var2[1], Conversions.booleanValue(var2[2]), (JoinPoint)var2[3]);
      return null;
   }
}
