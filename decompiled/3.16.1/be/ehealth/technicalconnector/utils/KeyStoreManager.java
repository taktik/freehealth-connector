package be.ehealth.technicalconnector.utils;

import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.exception.TechnicalConnectorExceptionValues;
import be.ehealth.technicalconnector.service.sts.security.KeyStoreInfo;
import be.fgov.ehealth.etee.crypto.utils.KeyManager;
import be.fgov.ehealth.etee.crypto.utils.KeyManager.KeyStoreOpeningException;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.cert.CertificateException;
import java.text.MessageFormat;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
               TechnicalConnectorExceptionValues errorValue = TechnicalConnectorExceptionValues.ERROR_KEYSTORE_LOAD;
               LOG.debug(MessageFormat.format(errorValue.getMessage(), "<empty>"));
               throw new TechnicalConnectorException(errorValue, new Object[]{"<empty>"});
            }

            String keystoreType = "PKCS12";
            if (pathKeystore.toLowerCase().contains(".jks")) {
               keystoreType = "JKS";
            }

            try {
               in = ConnectorIOUtils.getResourceAsStream(pathKeystore);
               KeyStore var20 = KeyManager.getKeyStore(in, keystoreType, keyStorePassword);
               return var20;
            } catch (KeyStoreOpeningException var14) {
               LOG.error("Trying to load keystore with ./");
               fallbackIn = ConnectorIOUtils.getResourceAsStream("./" + pathKeystore);
               var7 = KeyManager.getKeyStore(in, keystoreType, keyStorePassword);
            }
         } catch (KeyStoreOpeningException var15) {
            errorValue = TechnicalConnectorExceptionValues.ERROR_KEYSTORE_PASSWORD;
            LOG.debug(MessageFormat.format(errorValue.getMessage(), pathKeystore));
            throw new TechnicalConnectorException(errorValue, var15, new Object[]{pathKeystore});
         } catch (CertificateException var16) {
            errorValue = TechnicalConnectorExceptionValues.ERROR_KEYSTORE_LOAD;
            LOG.debug(MessageFormat.format(errorValue.getMessage(), pathKeystore));
            throw new TechnicalConnectorException(errorValue, var16, new Object[]{pathKeystore});
         } catch (IOException var17) {
            errorValue = TechnicalConnectorExceptionValues.ERROR_KEYSTORE_LOAD;
            LOG.debug(MessageFormat.format(errorValue.getMessage(), pathKeystore));
            throw new TechnicalConnectorException(errorValue, var17, new Object[]{pathKeystore});
         }
      } finally {
         IOUtils.closeQuietly(in);
         IOUtils.closeQuietly(fallbackIn);
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
