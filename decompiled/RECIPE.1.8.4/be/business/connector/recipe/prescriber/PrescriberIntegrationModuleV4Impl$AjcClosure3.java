package be.business.connector.recipe.prescriber;

import org.aspectj.lang.JoinPoint;
import org.aspectj.runtime.internal.AroundClosure;
import org.aspectj.runtime.internal.Conversions;

public class PrescriberIntegrationModuleV4Impl$AjcClosure3 extends AroundClosure {
   public PrescriberIntegrationModuleV4Impl$AjcClosure3(Object[] var1) {
      super(var1);
   }

   public Object run(Object[] var1) {
      Object[] var2 = super.state;
      return PrescriberIntegrationModuleV4Impl.createPrescription_aroundBody2((PrescriberIntegrationModuleV4Impl)var2[0], Conversions.booleanValue(var2[1]), (String)var2[2], (byte[])var2[3], (String)var2[4], (String)var2[5], (String)var2[6], (JoinPoint)var2[7]);
   }
}
