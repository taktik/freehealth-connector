package be.business.connector.core.utils;

import org.aspectj.lang.JoinPoint;
import org.aspectj.runtime.internal.AroundClosure;

public class ETKHelper$AjcClosure7 extends AroundClosure {
   public ETKHelper$AjcClosure7(Object[] var1) {
      super(var1);
   }

   public Object run(Object[] var1) {
      Object[] var2 = super.state;
      return ETKHelper.getTIP_ETK_aroundBody6((ETKHelper)var2[0], (String)var2[1], (JoinPoint)var2[2]);
   }
}
