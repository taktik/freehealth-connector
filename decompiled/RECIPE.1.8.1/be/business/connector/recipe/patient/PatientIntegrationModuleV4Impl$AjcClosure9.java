package be.business.connector.recipe.patient;

import be.recipe.services.patient.ListRidsHistoryParam;
import org.aspectj.lang.JoinPoint;
import org.aspectj.runtime.internal.AroundClosure;

public class PatientIntegrationModuleV4Impl$AjcClosure9 extends AroundClosure {
   public PatientIntegrationModuleV4Impl$AjcClosure9(Object[] var1) {
      super(var1);
   }

   public Object run(Object[] var1) {
      Object[] var2 = super.state;
      return PatientIntegrationModuleV4Impl.getData_aroundBody8((PatientIntegrationModuleV4Impl)var2[0], (ListRidsHistoryParam)var2[1], (JoinPoint)var2[2]);
   }
}
