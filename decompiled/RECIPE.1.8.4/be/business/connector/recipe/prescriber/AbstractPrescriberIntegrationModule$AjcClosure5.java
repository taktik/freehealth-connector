package be.business.connector.recipe.prescriber;

import org.aspectj.lang.JoinPoint;
import org.aspectj.runtime.internal.AroundClosure;

public class AbstractPrescriberIntegrationModule$AjcClosure5 extends AroundClosure {
   public AbstractPrescriberIntegrationModule$AjcClosure5(Object[] var1) {
      super(var1);
   }

   public Object run(Object[] var1) {
      Object[] var2 = super.state;
      return AbstractPrescriberIntegrationModule.unsealFeedback_aroundBody4((AbstractPrescriberIntegrationModule)var2[0], (byte[])var2[1], (JoinPoint)var2[2]);
   }
}
