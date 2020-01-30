package be.business.connector.gfddpp.pcdh;

import be.business.connector.gfddpp.domain.ThreeStateBoolean;
import org.aspectj.lang.JoinPoint;
import org.aspectj.runtime.internal.AroundClosure;
import org.aspectj.runtime.internal.Conversions;

public class PCDHIntegrationModuleImpl$AjcClosure5 extends AroundClosure {
   public PCDHIntegrationModuleImpl$AjcClosure5(Object[] var1) {
      super(var1);
   }

   public Object run(Object[] var1) {
      Object[] var2 = super.state;
      return PCDHIntegrationModuleImpl.getPharmacyDetails_aroundBody4((PCDHIntegrationModuleImpl)var2[0], (String)var2[1], (String)var2[2], (String)var2[3], (String)var2[4], (String)var2[5], Conversions.booleanValue(var2[6]), (ThreeStateBoolean)var2[7], (JoinPoint)var2[8]);
   }
}
