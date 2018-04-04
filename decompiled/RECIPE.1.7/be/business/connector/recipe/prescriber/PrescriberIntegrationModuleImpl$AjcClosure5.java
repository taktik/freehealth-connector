package org.taktik.connector.business.recipe.prescriber;

import org.aspectj.lang.JoinPoint;
import org.aspectj.runtime.internal.AroundClosure;
import org.aspectj.runtime.internal.Conversions;

public class PrescriberIntegrationModuleImpl$AjcClosure5 extends AroundClosure {
   public PrescriberIntegrationModuleImpl$AjcClosure5(Object[] var1) {
      super(var1);
   }

   public Object run(Object[] var1) {
      Object[] var2 = super.state;
      return PrescriberIntegrationModuleImpl.createPrescription_aroundBody4((PrescriberIntegrationModuleImpl)var2[0], Conversions.booleanValue(var2[1]), (String)var2[2], (byte[])var2[3], (String)var2[4], (JoinPoint)var2[5]);
   }
}
