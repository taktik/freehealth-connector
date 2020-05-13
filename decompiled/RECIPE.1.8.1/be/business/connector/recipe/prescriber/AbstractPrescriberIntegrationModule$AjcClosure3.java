package be.business.connector.recipe.prescriber;

import be.ehealth.technicalconnector.service.kgss.domain.KeyResult;
import org.aspectj.lang.JoinPoint;
import org.aspectj.runtime.internal.AroundClosure;

public class AbstractPrescriberIntegrationModule$AjcClosure3 extends AroundClosure {
   public AbstractPrescriberIntegrationModule$AjcClosure3(Object[] var1) {
      super(var1);
   }

   public Object run(Object[] var1) {
      Object[] var2 = super.state;
      return AbstractPrescriberIntegrationModule.sealPrescriptionForUnknown_aroundBody2((AbstractPrescriberIntegrationModule)var2[0], (KeyResult)var2[1], (byte[])var2[2], (JoinPoint)var2[3]);
   }
}
