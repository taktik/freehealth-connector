package be.business.connector.common.module;

import org.aspectj.lang.JoinPoint;
import org.aspectj.runtime.internal.AroundClosure;

public class TipConfigModuleImpl$AjcClosure3 extends AroundClosure {
   public TipConfigModuleImpl$AjcClosure3(Object[] var1) {
      super(var1);
   }

   public Object run(Object[] var1) {
      Object[] var2 = super.state;
      TipConfigModuleImpl.getLatestTIPConfig_aroundBody2((TipConfigModuleImpl)var2[0], (JoinPoint)var2[1]);
      return null;
   }
}
