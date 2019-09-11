package org.taktik.connector.technical.utils;

import org.taktik.connector.technical.exception.TechnicalConnectorException;
import org.taktik.connector.technical.exception.TechnicalConnectorExceptionValues;
import be.fgov.ehealth.etee.crypto.utils.KeyManager;
import be.fgov.ehealth.etee.crypto.utils.KeyManager.KeyStoreOpeningException;
import java.io.IOException;
import java.security.KeyStore;
import java.security.cert.CertificateException;
import java.text.MessageFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class KeyStoreManager {
   private KeyStore keyStore;
   private static final Logger LOG = LoggerFactory.getLogger(KeyStoreManager.class);

   /** @deprecated */
   @Deprecated
   public KeyStoreManager(KeyStore keyStore) {
      this.keyStore = keyStore;
   }

   public KeyStoreManager(String pathKeystore, char[] keyStorePassword) throws TechnicalConnectorException {
      this.keyStore = this.getKeyStore(pathKeystore, keyStorePassword);
   }

   private KeyStore getKeyStore(String pathKeystore, char[] keyStorePassword) throws TechnicalConnectorException {
      TechnicalConnectorExceptionValues errorValue;
      try {
         if (pathKeystore != null) {
            String keystoreType = "PKCS12";
            if (pathKeystore.toLowerCase().contains(".jks")) {
               keystoreType = "JKS";
            }

            try {
               return KeyManager.getKeyStore(ConnectorIOUtils.getResourceAsStream(pathKeystore), keystoreType, keyStorePassword);
            } catch (KeyStoreOpeningException var5) {
               LOG.error("Trying to load keystore with ./");
               return KeyManager.getKeyStore(ConnectorIOUtils.getResourceAsStream("./" + pathKeystore), keystoreType, keyStorePassword);
            }
         } else {
            errorValue = TechnicalConnectorExceptionValues.ERROR_KEYSTORE_LOAD;
            LOG.debug(MessageFormat.format(errorValue.getMessage(), "<empty>"));
            throw new TechnicalConnectorException(errorValue, "<empty>");
         }
      } catch (KeyStoreOpeningException var6) {
         errorValue = TechnicalConnectorExceptionValues.ERROR_KEYSTORE_PASSWORD;
         LOG.debug(MessageFormat.format(errorValue.getMessage(), pathKeystore));
         throw new TechnicalConnectorException(errorValue, var6, pathKeystore);
      } catch (CertificateException | IOException var7) {
         errorValue = TechnicalConnectorExceptionValues.ERROR_KEYSTORE_LOAD;
         LOG.debug(MessageFormat.format(errorValue.getMessage(), pathKeystore));
         throw new TechnicalConnectorException(errorValue, var7, pathKeystore);
      }
   }

   public final KeyStore getKeyStore() {
      return this.keyStore;
   }

}
