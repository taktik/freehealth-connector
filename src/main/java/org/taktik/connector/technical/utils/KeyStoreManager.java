package org.taktik.connector.technical.utils;

import org.apache.commons.lang3.StringUtils;
import org.taktik.connector.technical.exception.TechnicalConnectorException;
import org.taktik.connector.technical.exception.TechnicalConnectorExceptionValues;
import be.fgov.ehealth.etee.crypto.utils.KeyManager;
import be.fgov.ehealth.etee.crypto.utils.KeyManager.KeyStoreOpeningException;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.cert.CertificateException;
import java.text.MessageFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.taktik.connector.technical.service.sts.security.impl.KeyStoreInfo;

public class KeyStoreManager {
   private KeyStoreInfo keyStoreInfo;
   private KeyStore keyStore;
   private static final Logger LOG = LoggerFactory.getLogger(KeyStoreManager.class);

   /** @deprecated */
   @Deprecated
   public KeyStoreManager(KeyStore keyStore) {
      this.keyStore = keyStore;
   }

   public KeyStoreManager(KeyStore keyStore, KeyStoreInfo keyStoreInfo) {
      this.keyStore = keyStore;
      this.keyStoreInfo = keyStoreInfo;
   }

   public KeyStoreManager(KeyStoreInfo keyStoreInfo) throws TechnicalConnectorException {
      this.keyStoreInfo = keyStoreInfo;
      this.keyStore = this.getKeyStore(keyStoreInfo.getKeystorePath(), keyStoreInfo.getKeystorePassword());
   }

   public KeyStoreManager(String pathKeystore, char[] keyStorePassword) throws TechnicalConnectorException {
      this.keyStore = this.getKeyStore(pathKeystore, keyStorePassword);
   }

   private KeyStore getKeyStore(String pathKeystore, char[] keyStorePassword) throws TechnicalConnectorException {
      InputStream in = null;
      InputStream fallbackIn = null;

      KeyStore var7;
      try {
         TechnicalConnectorExceptionValues errorValue;
         try {
            if (pathKeystore == null) {
               errorValue = TechnicalConnectorExceptionValues.ERROR_KEYSTORE_LOAD;
               if (LOG.isDebugEnabled()) {
                  LOG.debug(MessageFormat.format(errorValue.getMessage(), "<empty>"));
               }

               throw new TechnicalConnectorException(errorValue, "<empty>");
            }

            String keystoreType = "PKCS12";
            if (StringUtils.endsWithIgnoreCase(pathKeystore, ".jks")) {
               keystoreType = "JKS";
            }

            try {
               in = ConnectorIOUtils.getResourceAsStream(pathKeystore);
               KeyStore var18 = KeyManager.getKeyStore(in, keystoreType, keyStorePassword);
               return var18;
            } catch (KeyStoreOpeningException var13) {
               LOG.error("Trying to load keystore with ./");
               fallbackIn = ConnectorIOUtils.getResourceAsStream("./" + pathKeystore);
               var7 = KeyManager.getKeyStore(in, keystoreType, keyStorePassword);
            }
         } catch (KeyStoreOpeningException var14) {
            errorValue = TechnicalConnectorExceptionValues.ERROR_KEYSTORE_PASSWORD;
            LOG.debug(MessageFormat.format(errorValue.getMessage(), pathKeystore));
            throw new TechnicalConnectorException(errorValue, var14, pathKeystore);
         } catch (Exception var15) {
            errorValue = TechnicalConnectorExceptionValues.ERROR_KEYSTORE_LOAD;
            LOG.debug(MessageFormat.format(errorValue.getMessage(), pathKeystore));
            throw new TechnicalConnectorException(errorValue, var15, pathKeystore);
         }
      } finally {
         ConnectorIOUtils.closeQuietly(in, fallbackIn);
      }

      return var7;
   }

   public final KeyStore getKeyStore() {
      return this.keyStore;
   }

   public final KeyStoreInfo getKeyStoreInfo() {
      return this.keyStoreInfo;
   }
}
