package be.business.connector.recipe.prescriber;

import org.aspectj.lang.JoinPoint;
import org.aspectj.runtime.internal.AroundClosure;
import org.aspectj.runtime.internal.Conversions;

public class PrescriberIntegrationModuleDevV4Impl$AjcClosure13 extends AroundClosure {
   public PrescriberIntegrationModuleDevV4Impl$AjcClosure13(Object[] var1) {
      super(var1);
   }

   public Object run(Object[] var1) {
      Object[] var2 = super.state;
      PrescriberIntegrationModuleDevV4Impl.updateFeedbackFlag_aroundBody12((PrescriberIntegrationModuleDevV4Impl)var2[0], (String)var2[1], Conversions.booleanValue(var2[2]), (JoinPoint)var2[3]);
      return null;
   }
}
