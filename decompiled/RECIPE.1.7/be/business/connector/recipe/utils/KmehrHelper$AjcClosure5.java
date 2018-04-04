package org.taktik.connector.business.recipe.utils;

import org.aspectj.lang.JoinPoint;
import org.aspectj.runtime.internal.AroundClosure;

public class KmehrHelper$AjcClosure5 extends AroundClosure {
   public KmehrHelper$AjcClosure5(Object[] var1) {
      super(var1);
   }

   public Object run(Object[] var1) {
      Object[] var2 = super.state;
      KmehrHelper.assertValidKmehrPrescription_aroundBody4((KmehrHelper)var2[0], (byte[])var2[1], (String)var2[2], (JoinPoint)var2[3]);
      return null;
   }
}
