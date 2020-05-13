package be.business.connector.gfddpp.tipsystem;

import be.business.connector.gfddpp.domain.ThreeStateBoolean;
import org.aspectj.lang.JoinPoint;
import org.aspectj.runtime.internal.AroundClosure;
import org.aspectj.runtime.internal.Conversions;

public class TIPSystemIntegrationModuleImpl$AjcClosure11 extends AroundClosure {
   public TIPSystemIntegrationModuleImpl$AjcClosure11(Object[] var1) {
      super(var1);
   }

   public Object run(Object[] var1) {
      Object[] var2 = super.state;
      return TIPSystemIntegrationModuleImpl.updateData_aroundBody10((TIPSystemIntegrationModuleImpl)var2[0], (byte[])var2[1], (String)var2[2], Conversions.booleanValue(var2[3]), (ThreeStateBoolean)var2[4], (JoinPoint)var2[5]);
   }
}
