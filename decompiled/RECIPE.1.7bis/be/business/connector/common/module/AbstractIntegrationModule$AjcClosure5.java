package be.business.connector.common.module;

import be.ehealth.technicalconnector.service.kgss.domain.KeyResult;
import org.aspectj.lang.JoinPoint;
import org.aspectj.runtime.internal.AroundClosure;

public class AbstractIntegrationModule$AjcClosure5 extends AroundClosure {
   public AbstractIntegrationModule$AjcClosure5(Object[] var1) {
      super(var1);
   }

   public Object run(Object[] var1) {
      Object[] var2 = super.state;
      return AbstractIntegrationModule.unsealPrescriptionForUnknown_aroundBody4((AbstractIntegrationModule)var2[0], (KeyResult)var2[1], (byte[])var2[2], (JoinPoint)var2[3]);
   }
}
