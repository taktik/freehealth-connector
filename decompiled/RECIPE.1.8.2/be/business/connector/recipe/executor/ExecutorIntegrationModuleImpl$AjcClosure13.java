package be.business.connector.recipe.executor;

import org.aspectj.lang.JoinPoint;
import org.aspectj.runtime.internal.AroundClosure;

public class ExecutorIntegrationModuleImpl$AjcClosure13 extends AroundClosure {
   public ExecutorIntegrationModuleImpl$AjcClosure13(Object[] var1) {
      super(var1);
   }

   public Object run(Object[] var1) {
      Object[] var2 = super.state;
      ExecutorIntegrationModuleImpl.createFeedback_aroundBody12((ExecutorIntegrationModuleImpl)var2[0], (String)var2[1], (String)var2[2], (byte[])var2[3], (JoinPoint)var2[4]);
      return null;
   }
}
