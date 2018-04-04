package org.taktik.connector.business.recipeprojects.core.utils;

import org.aspectj.lang.JoinPoint;
import org.aspectj.runtime.internal.AroundClosure;

public class ETKHelper$AjcClosure11 extends AroundClosure {
   public ETKHelper$AjcClosure11(Object[] var1) {
      super(var1);
   }

   public Object run(Object[] var1) {
      Object[] var2 = super.state;
      return ETKHelper.getSystemETK_aroundBody10((ETKHelper)var2[0], (JoinPoint)var2[1]);
   }
}
