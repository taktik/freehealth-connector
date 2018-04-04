package be.business.connector.mycarenet;

import org.aspectj.lang.JoinPoint;
import org.aspectj.runtime.internal.AroundClosure;

public class MyCareNetIntegrationModuleImpl$AjcClosure1 extends AroundClosure {
   public MyCareNetIntegrationModuleImpl$AjcClosure1(Object[] var1) {
      super(var1);
   }

   public Object run(Object[] var1) {
      Object[] var2 = super.state;
      return MyCareNetIntegrationModuleImpl.getInsurability_aroundBody0((MyCareNetIntegrationModuleImpl)var2[0], (String)var2[1], (String)var2[2], (String)var2[3], (String)var2[4], (String)var2[5], (String)var2[6], (String)var2[7], (String)var2[8], (String)var2[9], (String)var2[10], (String)var2[11], (String)var2[12], (String)var2[13], (JoinPoint)var2[14]);
   }
}
