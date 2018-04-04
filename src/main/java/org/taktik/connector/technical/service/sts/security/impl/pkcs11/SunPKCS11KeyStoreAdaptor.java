package org.taktik.connector.technical.service.sts.security.impl.pkcs11;

import org.taktik.connector.technical.exception.TechnicalConnectorException;
import org.taktik.connector.technical.service.sts.security.KeyStoreAdaptor;
import org.taktik.connector.technical.service.sts.security.ProviderAdaptor;
import java.io.IOException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.Provider;
import java.security.Security;
import java.security.KeyStore.Builder;
import java.security.KeyStore.CallbackHandlerProtection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** @deprecated */
@Deprecated
public class SunPKCS11KeyStoreAdaptor implements KeyStoreAdaptor {
   private KeyStore keyStore;
   private static final Logger LOG = LoggerFactory.getLogger(SunPKCS11KeyStoreAdaptor.class);

   public SunPKCS11KeyStoreAdaptor() throws TechnicalConnectorException, IOException {
      ProviderAdaptor prov = new SunPKCS11ProviderAdaptor();
      Security.addProvider(prov.getProvider());
   }

   private void init() throws KeyStoreException {
      try {
         Builder ksBuilder = Builder.newInstance("PKCS11", (Provider)null, new CallbackHandlerProtection(EidPinCallBackHandlerFactory.getHandler()));
         this.keyStore = ksBuilder.getKeyStore();
      } catch (TechnicalConnectorException var3) {
         LOG.error("" + var3);
         throw new KeyStoreException(var3);
      }
   }

   public KeyStore getKeyStore() throws KeyStoreException {
      if (this.keyStore == null) {
         this.init();
      }

      return this.keyStore;
   }
}
