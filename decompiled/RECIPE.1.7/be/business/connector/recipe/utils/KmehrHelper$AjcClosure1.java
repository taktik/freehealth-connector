package org.taktik.connector.business.recipe.utils;

import org.aspectj.lang.JoinPoint;
import org.aspectj.runtime.internal.AroundClosure;

public class KmehrHelper$AjcClosure1 extends AroundClosure {
   public KmehrHelper$AjcClosure1(Object[] var1) {
      super(var1);
   }

   public Object run(Object[] var1) {
      Object[] var2 = super.state;
      KmehrHelper.assertValidNotification_aroundBody0((KmehrHelper)var2[0], (byte[])var2[1], (JoinPoint)var2[2]);
      return null;
   }
}
