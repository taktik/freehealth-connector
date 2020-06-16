package be.business.connector.recipe.prescriber;

import org.aspectj.lang.JoinPoint;
import org.aspectj.runtime.internal.AroundClosure;
import org.aspectj.runtime.internal.Conversions;

public class PrescriberIntegrationModuleV4Impl$AjcClosure13 extends AroundClosure {
   public PrescriberIntegrationModuleV4Impl$AjcClosure13(Object[] var1) {
      super(var1);
   }

   public Object run(Object[] var1) {
      Object[] var2 = super.state;
      PrescriberIntegrationModuleV4Impl.updateFeedbackFlag_aroundBody12((PrescriberIntegrationModuleV4Impl)var2[0], (String)var2[1], Conversions.booleanValue(var2[2]), (JoinPoint)var2[3]);
      return null;
   }
}
