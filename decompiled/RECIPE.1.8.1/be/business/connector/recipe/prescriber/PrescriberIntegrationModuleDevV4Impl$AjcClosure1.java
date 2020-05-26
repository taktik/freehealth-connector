package be.business.connector.recipe.prescriber;

import org.aspectj.lang.JoinPoint;
import org.aspectj.runtime.internal.AroundClosure;
import org.aspectj.runtime.internal.Conversions;

public class PrescriberIntegrationModuleDevV4Impl$AjcClosure1 extends AroundClosure {
   public PrescriberIntegrationModuleDevV4Impl$AjcClosure1(Object[] var1) {
      super(var1);
   }

   public Object run(Object[] var1) {
      Object[] var2 = super.state;
      return PrescriberIntegrationModuleDevV4Impl.createPrescription_aroundBody0((PrescriberIntegrationModuleDevV4Impl)var2[0], Conversions.booleanValue(var2[1]), (String)var2[2], (byte[])var2[3], (String)var2[4], (JoinPoint)var2[5]);
   }
}
