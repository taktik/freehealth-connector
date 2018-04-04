package org.taktik.connector.business.recipe.prescriber;

import org.aspectj.lang.JoinPoint;
import org.aspectj.runtime.internal.AroundClosure;
import org.aspectj.runtime.internal.Conversions;

public class PrescriberIntegrationModuleImpl$AjcClosure19 extends AroundClosure {
   public PrescriberIntegrationModuleImpl$AjcClosure19(Object[] var1) {
      super(var1);
   }

   public Object run(Object[] var1) {
      Object[] var2 = super.state;
      return PrescriberIntegrationModuleImpl.listFeedback_aroundBody18((PrescriberIntegrationModuleImpl)var2[0], Conversions.booleanValue(var2[1]), (JoinPoint)var2[2]);
   }
}
