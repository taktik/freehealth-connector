package be.business.connector.core.utils;

import org.aspectj.lang.JoinPoint;
import org.aspectj.runtime.internal.AroundClosure;

public class ETKHelper$AjcClosure9 extends AroundClosure {
   public ETKHelper$AjcClosure9(Object[] var1) {
      super(var1);
   }

   public Object run(Object[] var1) {
      Object[] var2 = super.state;
      return ETKHelper.getPCDH_ETK_aroundBody8((ETKHelper)var2[0], (JoinPoint)var2[1]);
   }
}
