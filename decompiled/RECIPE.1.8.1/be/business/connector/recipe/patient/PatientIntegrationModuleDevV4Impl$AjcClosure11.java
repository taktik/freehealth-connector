package be.business.connector.recipe.patient;

import be.recipe.services.patient.ListOpenRidsParam;
import org.aspectj.lang.JoinPoint;
import org.aspectj.runtime.internal.AroundClosure;

public class PatientIntegrationModuleDevV4Impl$AjcClosure11 extends AroundClosure {
   public PatientIntegrationModuleDevV4Impl$AjcClosure11(Object[] var1) {
      super(var1);
   }

   public Object run(Object[] var1) {
      Object[] var2 = super.state;
      return PatientIntegrationModuleDevV4Impl.getData_aroundBody10((PatientIntegrationModuleDevV4Impl)var2[0], (ListOpenRidsParam)var2[1], (JoinPoint)var2[2]);
   }
}
