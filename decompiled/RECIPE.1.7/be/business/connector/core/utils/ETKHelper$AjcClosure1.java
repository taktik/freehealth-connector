package org.taktik.connector.business.recipeprojects.core.utils;

import org.aspectj.lang.JoinPoint;
import org.aspectj.runtime.internal.AroundClosure;

public class ETKHelper$AjcClosure1 extends AroundClosure {
   public ETKHelper$AjcClosure1(Object[] var1) {
      super(var1);
   }

   public Object run(Object[] var1) {
      Object[] var2 = super.state;
      return ETKHelper.getKGSS_ETK_aroundBody0((ETKHelper)var2[0], (JoinPoint)var2[1]);
   }
}
