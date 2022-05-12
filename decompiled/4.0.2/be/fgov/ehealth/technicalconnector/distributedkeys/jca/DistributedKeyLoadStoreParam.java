package be.fgov.ehealth.technicalconnector.distributedkeys.jca;

import be.fgov.ehealth.technicalconnector.distributedkeys.DistributedSignerProxy;
import java.security.KeyStore;

public class DistributedKeyLoadStoreParam implements KeyStore.LoadStoreParameter {
   private DistributedSignerProxy proxy;

   public DistributedKeyLoadStoreParam(DistributedSignerProxy proxy) {
      this.proxy = proxy;
   }

   public KeyStore.ProtectionParameter getProtectionParameter() {
      return null;
   }

   public DistributedSignerProxy getProxy() {
      return this.proxy;
   }
}
