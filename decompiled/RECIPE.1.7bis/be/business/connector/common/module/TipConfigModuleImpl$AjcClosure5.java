package be.business.connector.common.module;

import org.aspectj.lang.JoinPoint;
import org.aspectj.runtime.internal.AroundClosure;

public class TipConfigModuleImpl$AjcClosure5 extends AroundClosure {
   public TipConfigModuleImpl$AjcClosure5(Object[] var1) {
      super(var1);
   }

   public Object run(Object[] var1) {
      Object[] var2 = super.state;
      TipConfigModuleImpl.getLatestSystemServices_aroundBody4((TipConfigModuleImpl)var2[0], (JoinPoint)var2[1]);
      return null;
   }
}
