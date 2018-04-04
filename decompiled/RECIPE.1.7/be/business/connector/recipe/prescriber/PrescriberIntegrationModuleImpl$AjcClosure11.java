package org.taktik.connector.business.recipe.prescriber;

import org.aspectj.lang.JoinPoint;
import org.aspectj.runtime.internal.AroundClosure;

public class PrescriberIntegrationModuleImpl$AjcClosure11 extends AroundClosure {
   public PrescriberIntegrationModuleImpl$AjcClosure11(Object[] var1) {
      super(var1);
   }

   public Object run(Object[] var1) {
      Object[] var2 = super.state;
      return PrescriberIntegrationModuleImpl.listOpenPrescription_aroundBody10((PrescriberIntegrationModuleImpl)var2[0], (String)var2[1], (JoinPoint)var2[2]);
   }
}
