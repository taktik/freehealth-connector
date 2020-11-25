package be.business.connector.recipe.patient;

import be.recipe.services.patient.GetVisionParam;
import org.aspectj.lang.JoinPoint;
import org.aspectj.runtime.internal.AroundClosure;

public class PatientIntegrationModuleV4Impl$AjcClosure1 extends AroundClosure {
   public PatientIntegrationModuleV4Impl$AjcClosure1(Object[] var1) {
      super(var1);
   }

   public Object run(Object[] var1) {
      Object[] var2 = super.state;
      return PatientIntegrationModuleV4Impl.getData_aroundBody0((PatientIntegrationModuleV4Impl)var2[0], (GetVisionParam)var2[1], (JoinPoint)var2[2]);
   }
}
