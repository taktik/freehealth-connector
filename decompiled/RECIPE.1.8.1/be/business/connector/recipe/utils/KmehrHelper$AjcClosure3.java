package be.business.connector.recipe.utils;

import org.aspectj.lang.JoinPoint;
import org.aspectj.runtime.internal.AroundClosure;

public class KmehrHelper$AjcClosure3 extends AroundClosure {
   public KmehrHelper$AjcClosure3(Object[] var1) {
      super(var1);
   }

   public Object run(Object[] var1) {
      Object[] var2 = super.state;
      KmehrHelper.assertValidFeedback_aroundBody2((KmehrHelper)var2[0], (byte[])var2[1], (JoinPoint)var2[2]);
      return null;
   }
}
