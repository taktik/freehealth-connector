package be.business.connector.gfddpp.tipsystem;

import be.business.connector.gfddpp.domain.medicationscheme.protocol.StoreDataEntriesRequest;
import org.aspectj.lang.JoinPoint;
import org.aspectj.runtime.internal.AroundClosure;

public class TIPSystemIntegrationModuleImpl$AjcClosure7 extends AroundClosure {
   public TIPSystemIntegrationModuleImpl$AjcClosure7(Object[] var1) {
      super(var1);
   }

   public Object run(Object[] var1) {
      Object[] var2 = super.state;
      return TIPSystemIntegrationModuleImpl.registerMedicationSchemeEntries_aroundBody6((TIPSystemIntegrationModuleImpl)var2[0], (StoreDataEntriesRequest)var2[1], (JoinPoint)var2[2]);
   }
}
