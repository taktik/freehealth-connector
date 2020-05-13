package be.business.connector.recipe.patient;

import be.recipe.services.patient.CreateRelationParam;
import org.aspectj.lang.JoinPoint;
import org.aspectj.runtime.internal.AroundClosure;

public class PatientIntegrationModuleV4Impl$AjcClosure13 extends AroundClosure {
   public PatientIntegrationModuleV4Impl$AjcClosure13(Object[] var1) {
      super(var1);
   }

   public Object run(Object[] var1) {
      Object[] var2 = super.state;
      return PatientIntegrationModuleV4Impl.putData_aroundBody12((PatientIntegrationModuleV4Impl)var2[0], (CreateRelationParam)var2[1], (JoinPoint)var2[2]);
   }
}
