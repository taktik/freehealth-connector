package be.business.connector.gfddpp.pcdh;

import be.business.connector.gfddpp.domain.medicationscheme.protocol.FetchDataEntriesRequest;
import org.aspectj.lang.JoinPoint;
import org.aspectj.runtime.internal.AroundClosure;

public class PCDHIntegrationModuleImpl$AjcClosure3 extends AroundClosure {
   public PCDHIntegrationModuleImpl$AjcClosure3(Object[] var1) {
      super(var1);
   }

   public Object run(Object[] var1) {
      Object[] var2 = super.state;
      return PCDHIntegrationModuleImpl.getMedicationSchemeEntries_aroundBody2((PCDHIntegrationModuleImpl)var2[0], (FetchDataEntriesRequest)var2[1], (JoinPoint)var2[2]);
   }
}
