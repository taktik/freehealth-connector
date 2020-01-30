package be.business.connector.gfddpp.tipsystem;

import org.aspectj.lang.JoinPoint;
import org.aspectj.runtime.internal.AroundClosure;

public class TIPSystemIntegrationModuleImpl$AjcClosure1 extends AroundClosure {
   public TIPSystemIntegrationModuleImpl$AjcClosure1(Object[] var1) {
      super(var1);
   }

   public Object run(Object[] var1) {
      Object[] var2 = super.state;
      return TIPSystemIntegrationModuleImpl.archivePrescription_aroundBody0((TIPSystemIntegrationModuleImpl)var2[0], (String)var2[1], (JoinPoint)var2[2]);
   }
}
