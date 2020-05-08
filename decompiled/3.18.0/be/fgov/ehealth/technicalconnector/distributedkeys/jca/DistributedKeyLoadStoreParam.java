package be.fgov.ehealth.technicalconnector.distributedkeys.jca;

import be.fgov.ehealth.technicalconnector.distributedkeys.DistributedSignerProxy;
import java.security.KeyStore.LoadStoreParameter;
import java.security.KeyStore.ProtectionParameter;

public class DistributedKeyLoadStoreParam implements LoadStoreParameter {
   private DistributedSignerProxy proxy;

   public DistributedKeyLoadStoreParam(DistributedSignerProxy proxy) {
      this.proxy = proxy;
   }

   public ProtectionParameter getProtectionParameter() {
      return null;
   }

   public DistributedSignerProxy getProxy() {
      return this.proxy;
   }
}
