package be.business.connector.core.technical.connector.utils;

import be.business.connector.core.exceptions.IntegrationModuleException;
import be.business.connector.core.utils.EncryptionUtils;
import be.ehealth.technicalconnector.service.kgss.domain.KeyResult;
import be.fgov.ehealth.etee.crypto.decrypt.DataUnsealer;
import be.fgov.ehealth.etee.crypto.encrypt.DataSealer;
import be.fgov.ehealth.etee.crypto.encrypt.DataSealerException;
import be.fgov.ehealth.etee.crypto.encrypt.EncryptionToken;
import java.io.IOException;
import java.util.List;
import javax.crypto.SecretKey;

public class Crypto {
   private static final String TECHNICAL_CONNECTOR_ERROR_DATA_SEAL = "technical.connector.error.data.seal";
   private static DataSealer dataSealer;
   private static DataUnsealer dataUnsealer;
   private static EncryptionUtils encryptionUtils;

   static {
      try {
         dataSealer = EncryptionUtils.getInstance().initSealing();
         dataUnsealer = EncryptionUtils.getInstance().initUnsealing();
         encryptionUtils = EncryptionUtils.getInstance();
      } catch (Exception var1) {
         throw new IntegrationModuleException("technical.connector.error.data.seal", var1);
      }
   }

   private Crypto() {
   }

   public static byte[] seal(EncryptionToken etk, byte[] data) {
      try {
         return dataSealer.seal(etk, data);
      } catch (DataSealerException var3) {
         throw new IntegrationModuleException("technical.connector.error.data.seal", var3);
      }
   }

   public static byte[] seal(byte[] data, SecretKey secretKey, String keyId) {
      try {
         return dataSealer.seal(data, secretKey, keyId);
      } catch (DataSealerException var4) {
         throw new IntegrationModuleException("technical.connector.error.data.seal", var4);
      }
   }

   public static byte[] seal(List<EncryptionToken> etks, byte[] data) {
      return seal((EncryptionToken)etks.get(0), data);
   }

   public static byte[] unseal(byte[] data) throws IntegrationModuleException {
      try {
         return encryptionUtils.unsealingData(dataUnsealer.unseal(data));
      } catch (IOException var2) {
         throw new IntegrationModuleException("technical.connector.error.data.seal", var2);
      }
   }

   public static byte[] unsealForUnknown(KeyResult keyResult, byte[] data) {
      return unseal(keyResult.getSecretKey(), data);
   }

   private static byte[] unseal(SecretKey secretKey, byte[] data) {
      try {
         return encryptionUtils.unsealingData(dataUnsealer.unseal(data, secretKey));
      } catch (Exception var3) {
         throw new IntegrationModuleException("technical.connector.error.data.seal", var3);
      }
   }
}
