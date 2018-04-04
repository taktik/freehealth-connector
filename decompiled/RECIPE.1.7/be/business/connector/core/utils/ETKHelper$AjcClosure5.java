package org.taktik.connector.business.recipeprojects.core.utils;

import org.aspectj.lang.JoinPoint;
import org.aspectj.runtime.internal.AroundClosure;

public class ETKHelper$AjcClosure5 extends AroundClosure {
   public ETKHelper$AjcClosure5(Object[] var1) {
      super(var1);
   }

   public Object run(Object[] var1) {
      Object[] var2 = super.state;
      return ETKHelper.getTIPSystem_ETK_aroundBody4((ETKHelper)var2[0], (String)var2[1], (JoinPoint)var2[2]);
   }
}
