package be.business.connector.gfddpp.tipsystem;

import org.aspectj.lang.JoinPoint;
import org.aspectj.runtime.internal.AroundClosure;

public class TIPSystemIntegrationModuleImpl$AjcClosure13 extends AroundClosure {
   public TIPSystemIntegrationModuleImpl$AjcClosure13(Object[] var1) {
      super(var1);
   }

   public Object run(Object[] var1) {
      Object[] var2 = super.state;
      return TIPSystemIntegrationModuleImpl.getStatusMessages_aroundBody12((TIPSystemIntegrationModuleImpl)var2[0], (String)var2[1], (String)var2[2], (JoinPoint)var2[3]);
   }
}
