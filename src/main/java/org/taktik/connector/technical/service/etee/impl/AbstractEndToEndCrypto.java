package org.taktik.connector.technical.service.etee.impl;

import org.taktik.connector.technical.config.ConfigFactory;
import org.taktik.connector.technical.config.Configuration;
import org.taktik.connector.technical.exception.TechnicalConnectorException;
import org.taktik.connector.technical.exception.UnsealConnectorException;
import org.taktik.connector.technical.service.etee.Crypto;
import org.taktik.connector.technical.service.etee.domain.EncryptionToken;
import org.taktik.connector.technical.service.kgss.domain.KeyResult;
import java.util.HashSet;
import java.util.Set;
import javax.crypto.SecretKey;
import org.apache.commons.lang.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractEndToEndCrypto extends AbstractCrypto {
   private static Configuration config = ConfigFactory.getConfigValidator();
   private static final Logger LOG = LoggerFactory.getLogger(AbstractEndToEndCrypto.class);
   public static final String PROP_DUMP_MESSAGES = "org.taktik.connector.technical.service.etee.cryptoimpl.dump_messages";
   public static final String PROP_DUMP_FIRST_CHARS = "org.taktik.connector.technical.service.etee.cryptoimpl.dump_first_chars";

   public byte[] seal(EncryptionToken paramEncryptionToken, byte[] paramArrayOfByte) throws TechnicalConnectorException {
      Set<EncryptionToken> etkSet = new HashSet();
      etkSet.add(paramEncryptionToken);
      return this.seal(etkSet, paramArrayOfByte);
   }

   public byte[] seal(Set<EncryptionToken> paramEncryptionTokenSet, byte[] paramArrayOfByte) throws TechnicalConnectorException {
      return this.seal(paramEncryptionTokenSet, paramArrayOfByte, null, null);
   }

   public byte[] seal(byte[] paramArrayOfByte, SecretKey secretKey, String base64encodedSymKekId) throws TechnicalConnectorException {
      return this.seal(null, paramArrayOfByte, secretKey, base64encodedSymKekId);
   }

   public byte[] seal(Set<EncryptionToken> paramEncryptionTokenSet, byte[] content, SecretKey secretKey, String base64encodedSymKekId) throws TechnicalConnectorException {
      return this.seal(Crypto.SigningPolicySelector.WITH_NON_REPUDIATION, paramEncryptionTokenSet, new KeyResult(secretKey, base64encodedSymKekId), content);
   }

   public byte[] seal(Crypto.SigningPolicySelector type, KeyResult symmKey, byte[] content) throws TechnicalConnectorException {
      return this.seal(type, null, symmKey, content);
   }

   public byte[] seal(Crypto.SigningPolicySelector type, EncryptionToken encryptionToken, byte[] content) throws TechnicalConnectorException {
      Set<EncryptionToken> etkSet = new HashSet();
      etkSet.add(encryptionToken);
      return this.seal(type, etkSet, content);
   }

   public byte[] seal(Crypto.SigningPolicySelector type, Set<EncryptionToken> paramEncryptionTokenSet, byte[] content) throws TechnicalConnectorException {
      return this.seal(type, paramEncryptionTokenSet, null, content);
   }

   public byte[] unseal(byte[] protectedMessage) throws UnsealConnectorException, TechnicalConnectorException {
      byte[] result = this.unseal(Crypto.SigningPolicySelector.WITH_NON_REPUDIATION, protectedMessage).getContentAsByte();
      this.dumpMessage(result, "Message unsealed");
      return result;
   }

   public byte[] unsealForUnknown(SecretKey key, byte[] protectedMessage) throws TechnicalConnectorException {
      byte[] result = this.unseal(Crypto.SigningPolicySelector.WITH_NON_REPUDIATION, new KeyResult(key, "dummy"), protectedMessage).getContentAsByte();
      this.dumpMessage(result, "Message unsealed");
      return result;
   }

   protected void dumpMessage(byte[] paramArrayOfByte, String msg) {
      if (config.getBooleanProperty("org.taktik.connector.technical.service.etee.cryptoimpl.dump_messages", Boolean.FALSE).booleanValue()) {
         int maxNumberOfBytesToLog = config.getIntegerProperty("org.taktik.connector.technical.service.etee.cryptoimpl.dump_first_chars", Integer.valueOf(50)).intValue();
         String stringToLog;
         if (paramArrayOfByte.length < maxNumberOfBytesToLog) {
            stringToLog = new String(paramArrayOfByte);
         } else {
            stringToLog = new String(ArrayUtils.subarray(paramArrayOfByte, 0, maxNumberOfBytesToLog - 1));
         }

         LOG.debug(msg + ": " + stringToLog);
      }

   }
}
