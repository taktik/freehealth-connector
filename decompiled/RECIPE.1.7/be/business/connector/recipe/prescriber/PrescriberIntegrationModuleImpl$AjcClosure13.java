package org.taktik.connector.business.recipe.prescriber;

import org.aspectj.lang.JoinPoint;
import org.aspectj.runtime.internal.AroundClosure;

public class PrescriberIntegrationModuleImpl$AjcClosure13 extends AroundClosure {
   public PrescriberIntegrationModuleImpl$AjcClosure13(Object[] var1) {
      super(var1);
   }

   public Object run(Object[] var1) {
      Object[] var2 = super.state;
      return PrescriberIntegrationModuleImpl.listOpenPrescription_aroundBody12((PrescriberIntegrationModuleImpl)var2[0], (JoinPoint)var2[1]);
   }
}
