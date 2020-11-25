package be.business.connector.gfddpp.pcdh;

import be.business.connector.gfddpp.domain.ThreeStateBoolean;
import org.aspectj.lang.JoinPoint;
import org.aspectj.runtime.internal.AroundClosure;
import org.aspectj.runtime.internal.Conversions;

public class PCDHIntegrationModuleImpl$AjcClosure7 extends AroundClosure {
   public PCDHIntegrationModuleImpl$AjcClosure7(Object[] var1) {
      super(var1);
   }

   public Object run(Object[] var1) {
      Object[] var2 = super.state;
      return PCDHIntegrationModuleImpl.getData_aroundBody6((PCDHIntegrationModuleImpl)var2[0], (String)var2[1], (String)var2[2], (String)var2[3], (String)var2[4], Conversions.booleanValue(var2[5]), (byte[])var2[6], Conversions.booleanValue(var2[7]), (ThreeStateBoolean)var2[8], (JoinPoint)var2[9]);
   }
}
