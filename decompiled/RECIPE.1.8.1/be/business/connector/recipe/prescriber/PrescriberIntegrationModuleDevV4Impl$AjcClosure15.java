package be.business.connector.recipe.prescriber;

import org.aspectj.lang.JoinPoint;
import org.aspectj.runtime.internal.AroundClosure;
import org.aspectj.runtime.internal.Conversions;

public class PrescriberIntegrationModuleDevV4Impl$AjcClosure15 extends AroundClosure {
   public PrescriberIntegrationModuleDevV4Impl$AjcClosure15(Object[] var1) {
      super(var1);
   }

   public Object run(Object[] var1) {
      Object[] var2 = super.state;
      return PrescriberIntegrationModuleDevV4Impl.listFeedback_aroundBody14((PrescriberIntegrationModuleDevV4Impl)var2[0], Conversions.booleanValue(var2[1]), (JoinPoint)var2[2]);
   }
}
