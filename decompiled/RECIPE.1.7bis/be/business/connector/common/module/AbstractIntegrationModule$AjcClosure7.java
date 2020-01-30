package be.business.connector.common.module;

import org.aspectj.lang.JoinPoint;
import org.aspectj.runtime.internal.AroundClosure;

public class AbstractIntegrationModule$AjcClosure7 extends AroundClosure {
   public AbstractIntegrationModule$AjcClosure7(Object[] var1) {
      super(var1);
   }

   public Object run(Object[] var1) {
      Object[] var2 = super.state;
      return AbstractIntegrationModule.getKeyFromKgss_aroundBody6((AbstractIntegrationModule)var2[0], (String)var2[1], (byte[])var2[2], (JoinPoint)var2[3]);
   }
}
