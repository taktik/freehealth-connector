package be.business.connector.gfddpp.pcdh;

import be.business.connector.gfddpp.domain.ThreeStateBoolean;
import org.aspectj.lang.JoinPoint;
import org.aspectj.runtime.internal.AroundClosure;
import org.aspectj.runtime.internal.Conversions;

public class PCDHIntegrationModuleImpl$AjcClosure9 extends AroundClosure {
   public PCDHIntegrationModuleImpl$AjcClosure9(Object[] var1) {
      super(var1);
   }

   public Object run(Object[] var1) {
      Object[] var2 = super.state;
      return PCDHIntegrationModuleImpl.getDataTypes_aroundBody8((PCDHIntegrationModuleImpl)var2[0], (String)var2[1], (String)var2[2], Conversions.booleanValue(var2[3]), (ThreeStateBoolean)var2[4], (JoinPoint)var2[5]);
   }
}
