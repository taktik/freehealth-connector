package be.ehealth.technicalconnector.service.etee.impl;

import be.ehealth.technicalconnector.config.ConfigFactory;
import be.ehealth.technicalconnector.config.Configuration;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.service.etee.Crypto;
import be.ehealth.technicalconnector.service.etee.domain.EncryptionToken;
import be.ehealth.technicalconnector.service.kgss.domain.KeyResult;
import java.util.HashSet;
import java.util.Set;
import javax.crypto.SecretKey;
import org.apache.commons.lang.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractEndToEndCrypto extends AbstractCrypto {
   private static Configuration config = ConfigFactory.getConfigValidator();
   private static final Logger LOG = LoggerFactory.getLogger(AbstractEndToEndCrypto.class);
   public static final String PROP_DUMP_MESSAGES = "be.ehealth.technicalconnector.service.etee.cryptoimpl.dump_messages";
   public static final String PROP_DUMP_FIRST_CHARS = "be.ehealth.technicalconnector.service.etee.cryptoimpl.dump_first_chars";

   public byte[] seal(EncryptionToken paramEncryptionToken, byte[] paramArrayOfByte) throws TechnicalConnectorException {
      Set<EncryptionToken> etkSet = new HashSet();
      etkSet.add(paramEncryptionToken);
      return this.seal((Set)etkSet, paramArrayOfByte);
   }

   public byte[] seal(Set<EncryptionToken> paramEncryptionTokenSet, byte[] paramArrayOfByte) throws TechnicalConnectorException {
      return this.seal(paramEncryptionTokenSet, paramArrayOfByte, (SecretKey)null, (String)null);
   }

   public byte[] seal(byte[] paramArrayOfByte, SecretKey secretKey, String base64encodedSymKekId) throws TechnicalConnectorException {
      return this.seal((Set)null, paramArrayOfByte, secretKey, base64encodedSymKekId);
   }

   public byte[] seal(Set<EncryptionToken> paramEncryptionTokenSet, byte[] content, SecretKey secretKey, String base64encodedSymKekId) throws TechnicalConnectorException {
      return this.seal(Crypto.SigningPolicySelector.WITH_NON_REPUDIATION, paramEncryptionTokenSet, new KeyResult(secretKey, base64encodedSymKekId), content);
   }

   public byte[] seal(Crypto.SigningPolicySelector type, KeyResult symmKey, byte[] content) throws TechnicalConnectorException {
      return this.seal(type, (Set)null, symmKey, content);
   }

   public byte[] seal(Crypto.SigningPolicySelector type, EncryptionToken encryptionToken, byte[] content) throws TechnicalConnectorException {
      Set<EncryptionToken> etkSet = new HashSet();
      etkSet.add(encryptionToken);
      return this.seal((Crypto.SigningPolicySelector)type, (Set)etkSet, (byte[])content);
   }

   public byte[] seal(Crypto.SigningPolicySelector type, Set<EncryptionToken> paramEncryptionTokenSet, byte[] content) throws TechnicalConnectorException {
      return this.seal(type, paramEncryptionTokenSet, (KeyResult)null, content);
   }

   public byte[] unseal(byte[] protectedMessage) throws TechnicalConnectorException {
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
      if (Boolean.TRUE.equals(config.getBooleanProperty("be.ehealth.technicalconnector.service.etee.cryptoimpl.dump_messages", Boolean.FALSE))) {
         int maxNumberOfBytesToLog = config.getIntegerProperty("be.ehealth.technicalconnector.service.etee.cryptoimpl.dump_first_chars", 50);
         String stringToLog;
         if (paramArrayOfByte.length < maxNumberOfBytesToLog) {
            stringToLog = new String(paramArrayOfByte);
         } else {
            stringToLog = new String(ArrayUtils.subarray(paramArrayOfByte, 0, maxNumberOfBytesToLog - 1));
         }

         LOG.debug("{}: {}", msg, stringToLog);
      }

   }
}
