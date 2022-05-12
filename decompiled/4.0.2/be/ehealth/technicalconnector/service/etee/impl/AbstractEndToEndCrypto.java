package be.ehealth.technicalconnector.service.etee.impl;

import be.ehealth.technicalconnector.config.ConfigFactory;
import be.ehealth.technicalconnector.config.Configuration;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.service.etee.Crypto;
import be.ehealth.technicalconnector.service.etee.domain.EncryptionToken;
import be.ehealth.technicalconnector.service.kgss.domain.KeyResult;
import java.util.HashSet;
import java.util.Set;
import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractEndToEndCrypto implements Crypto {
   private Configuration config = ConfigFactory.getConfigValidator();
   private static final Logger LOG = LoggerFactory.getLogger(AbstractEndToEndCrypto.class);
   public static final String PROP_DUMP_MESSAGES = "be.ehealth.technicalconnector.service.etee.cryptoimpl.dump_messages";
   public static final String PROP_DUMP_FIRST_CHARS = "be.ehealth.technicalconnector.service.etee.cryptoimpl.dump_first_chars";

   public AbstractEndToEndCrypto() {
   }

   public byte[] seal(Crypto.SigningPolicySelector type, KeyResult symmKey, byte[] content) throws TechnicalConnectorException {
      return this.seal(type, (Set)null, symmKey, content);
   }

   public byte[] seal(Crypto.SigningPolicySelector type, EncryptionToken encryptionToken, byte[] content) throws TechnicalConnectorException {
      Set<EncryptionToken> etkSet = new HashSet();
      etkSet.add(encryptionToken);
      return this.seal(type, (Set)etkSet, content);
   }

   public byte[] seal(Crypto.SigningPolicySelector type, Set<EncryptionToken> paramEncryptionTokenSet, byte[] content) throws TechnicalConnectorException {
      return this.seal(type, paramEncryptionTokenSet, (KeyResult)null, content);
   }

   protected void dumpMessage(byte[] paramArrayOfByte, String msg) {
      if (Boolean.TRUE.equals(this.config.getBooleanProperty("be.ehealth.technicalconnector.service.etee.cryptoimpl.dump_messages", Boolean.FALSE))) {
         int maxNumberOfBytesToLog = this.config.getIntegerProperty("be.ehealth.technicalconnector.service.etee.cryptoimpl.dump_first_chars", 50);
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
