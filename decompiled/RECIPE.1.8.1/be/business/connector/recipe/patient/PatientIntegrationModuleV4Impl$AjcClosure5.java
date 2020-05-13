package be.business.connector.recipe.patient;

import be.recipe.services.patient.CreateReservationParam;
import org.aspectj.lang.JoinPoint;
import org.aspectj.runtime.internal.AroundClosure;

public class PatientIntegrationModuleV4Impl$AjcClosure5 extends AroundClosure {
   public PatientIntegrationModuleV4Impl$AjcClosure5(Object[] var1) {
      super(var1);
   }

   public Object run(Object[] var1) {
      Object[] var2 = super.state;
      return PatientIntegrationModuleV4Impl.putData_aroundBody4((PatientIntegrationModuleV4Impl)var2[0], (CreateReservationParam)var2[1], (JoinPoint)var2[2]);
   }
}
