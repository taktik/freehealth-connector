package be.business.connector.recipe.prescriber;

import org.aspectj.lang.JoinPoint;
import org.aspectj.runtime.internal.AroundClosure;

public class AbstractPrescriberIntegrationModule$AjcClosure1 extends AroundClosure {
   public AbstractPrescriberIntegrationModule$AjcClosure1(Object[] var1) {
      super(var1);
   }

   public Object run(Object[] var1) {
      Object[] var2 = super.state;
      return AbstractPrescriberIntegrationModule.getNewKeyFromKgss_aroundBody0((AbstractPrescriberIntegrationModule)var2[0], (String)var2[1], (String)var2[2], (String)var2[3], (String)var2[4], (byte[])var2[5], (JoinPoint)var2[6]);
   }
}
