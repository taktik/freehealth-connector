package org.taktik.connector.business.recipe.prescriber;

import be.fgov.ehealth.etee.crypto.encrypt.EncryptionToken;
import org.aspectj.lang.JoinPoint;
import org.aspectj.runtime.internal.AroundClosure;

public class PrescriberIntegrationModuleImpl$AjcClosure25 extends AroundClosure {
   public PrescriberIntegrationModuleImpl$AjcClosure25(Object[] var1) {
      super(var1);
   }

   public Object run(Object[] var1) {
      Object[] var2 = super.state;
      return PrescriberIntegrationModuleImpl.sealNotification_aroundBody24((PrescriberIntegrationModuleImpl)var2[0], (EncryptionToken)var2[1], (byte[])var2[2], (JoinPoint)var2[3]);
   }
}
