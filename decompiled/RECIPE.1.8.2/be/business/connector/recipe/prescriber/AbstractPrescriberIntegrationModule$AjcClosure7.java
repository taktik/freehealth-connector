package be.business.connector.recipe.prescriber;

import be.fgov.ehealth.etee.crypto.encrypt.EncryptionToken;
import org.aspectj.lang.JoinPoint;
import org.aspectj.runtime.internal.AroundClosure;

public class AbstractPrescriberIntegrationModule$AjcClosure7 extends AroundClosure {
   public AbstractPrescriberIntegrationModule$AjcClosure7(Object[] var1) {
      super(var1);
   }

   public Object run(Object[] var1) {
      Object[] var2 = super.state;
      return AbstractPrescriberIntegrationModule.sealNotification_aroundBody6((AbstractPrescriberIntegrationModule)var2[0], (EncryptionToken)var2[1], (byte[])var2[2], (JoinPoint)var2[3]);
   }
}
