package be.business.connector.common.module;

import org.aspectj.lang.JoinPoint;
import org.aspectj.runtime.internal.AroundClosure;

public class AbstractIntegrationModule$AjcClosure3 extends AroundClosure {
   public AbstractIntegrationModule$AjcClosure3(Object[] var1) {
      super(var1);
   }

   public Object run(Object[] var1) {
      Object[] var2 = super.state;
      return AbstractIntegrationModule.unsealRequest_aroundBody2((AbstractIntegrationModule)var2[0], (byte[])var2[1], (JoinPoint)var2[2]);
   }
}
