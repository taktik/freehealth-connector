package org.taktik.connector.technical.service.etee.impl;

import org.taktik.connector.technical.config.ConfigFactory;
import org.taktik.connector.technical.config.Configuration;
import org.taktik.connector.technical.exception.TechnicalConnectorException;
import org.taktik.connector.technical.exception.TechnicalConnectorExceptionValues;
import org.taktik.connector.technical.service.etee.Crypto;
import org.taktik.connector.technical.utils.ConnectorCryptoUtils;
import java.security.Key;
import java.text.MessageFormat;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import org.bouncycastle.util.encoders.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractCrypto implements Crypto {
   private static final Logger LOG = LoggerFactory.getLogger(AbstractCrypto.class);
   private static Configuration config = ConfigFactory.getConfigValidator();
   private Key symmKey;
   static final String PROP_SYMM_KEY_PROPERTY = "SYMM_KEY_PROPERTY";

   /** @deprecated */
   @Deprecated
   public static byte[] unsealWithSymmKey(Key symmKey, byte[] objectToUnseal) throws TechnicalConnectorException {
      return ConnectorCryptoUtils.decrypt(symmKey, "DESede", objectToUnseal);
   }

   public Key generateSecretKey() throws TechnicalConnectorException {
      TechnicalConnectorExceptionValues errorValue = TechnicalConnectorExceptionValues.ERROR_CRYPTO;
      String param = "Could not generate secret key (SymmKey)";

      try {
         if (config.hasProperty("SYMM_KEY_PROPERTY")) {
            String base64key = config.getProperty("SYMM_KEY_PROPERTY");
            DESedeKeySpec keyspec = new DESedeKeySpec(Base64.decode(base64key));
            SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("DESede");
            return keyfactory.generateSecret(keyspec);
         } else {
            KeyGenerator keyGen = KeyGenerator.getInstance("DESede");
            return keyGen.generateKey();
         }
      } catch (Exception var6) {
         LOG.debug(MessageFormat.format(errorValue.getMessage(), param));
         throw new TechnicalConnectorException(errorValue, var6, new Object[]{param});
      }
   }

   public Key getSymmKey() {
      if (this.symmKey == null) {
         try {
            this.symmKey = this.generateSecretKey();
         } catch (TechnicalConnectorException var2) {
            LOG.error("Could not generate symmetric encryption key", var2);
            return null;
         }
      }

      return this.symmKey;
   }
}
