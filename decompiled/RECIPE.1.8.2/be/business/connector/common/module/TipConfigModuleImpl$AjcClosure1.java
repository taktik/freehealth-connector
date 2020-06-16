package be.business.connector.common.module;

import org.aspectj.lang.JoinPoint;
import org.aspectj.runtime.internal.AroundClosure;

public class TipConfigModuleImpl$AjcClosure1 extends AroundClosure {
   public TipConfigModuleImpl$AjcClosure1(Object[] var1) {
      super(var1);
   }

   public Object run(Object[] var1) {
      Object[] var2 = super.state;
      TipConfigModuleImpl.getLatestProductFilter_aroundBody0((TipConfigModuleImpl)var2[0], (JoinPoint)var2[1]);
      return null;
   }
}
