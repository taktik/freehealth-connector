package be.business.connector.gfddpp.pcdh;

import be.business.connector.gfddpp.domain.medicationscheme.protocol.RetrieveTimestampsRequest;
import org.aspectj.lang.JoinPoint;
import org.aspectj.runtime.internal.AroundClosure;

public class PCDHIntegrationModuleImpl$AjcClosure1 extends AroundClosure {
   public PCDHIntegrationModuleImpl$AjcClosure1(Object[] var1) {
      super(var1);
   }

   public Object run(Object[] var1) {
      Object[] var2 = super.state;
      return PCDHIntegrationModuleImpl.getMedicationSchemeTimestamps_aroundBody0((PCDHIntegrationModuleImpl)var2[0], (RetrieveTimestampsRequest)var2[1], (JoinPoint)var2[2]);
   }
}
