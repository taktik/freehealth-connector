package be.business.connector.recipe.patient;

import be.recipe.services.patient.GetVisionParam;
import org.aspectj.lang.JoinPoint;
import org.aspectj.runtime.internal.AroundClosure;

public class PatientIntegrationModuleDevV4Impl$AjcClosure1 extends AroundClosure {
   public PatientIntegrationModuleDevV4Impl$AjcClosure1(Object[] var1) {
      super(var1);
   }

   public Object run(Object[] var1) {
      Object[] var2 = super.state;
      return PatientIntegrationModuleDevV4Impl.getData_aroundBody0((PatientIntegrationModuleDevV4Impl)var2[0], (GetVisionParam)var2[1], (JoinPoint)var2[2]);
   }
}
