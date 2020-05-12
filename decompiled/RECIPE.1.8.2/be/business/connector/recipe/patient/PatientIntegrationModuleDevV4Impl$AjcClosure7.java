package be.business.connector.recipe.patient;

import be.recipe.services.patient.GetPrescriptionStatusParam;
import org.aspectj.lang.JoinPoint;
import org.aspectj.runtime.internal.AroundClosure;

public class PatientIntegrationModuleDevV4Impl$AjcClosure7 extends AroundClosure {
   public PatientIntegrationModuleDevV4Impl$AjcClosure7(Object[] var1) {
      super(var1);
   }

   public Object run(Object[] var1) {
      Object[] var2 = super.state;
      return PatientIntegrationModuleDevV4Impl.getData_aroundBody6((PatientIntegrationModuleDevV4Impl)var2[0], (GetPrescriptionStatusParam)var2[1], (JoinPoint)var2[2]);
   }
}
