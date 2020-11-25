package be.business.connector.core.technical.connector.utils;

import be.business.connector.core.exceptions.IntegrationModuleException;
import be.business.connector.core.utils.EncryptionUtils;
import be.ehealth.technicalconnector.service.kgss.domain.KeyResult;
import be.fgov.ehealth.etee.crypto.decrypt.DataUnsealer;
import be.fgov.ehealth.etee.crypto.encrypt.DataSealer;
import be.fgov.ehealth.etee.crypto.encrypt.DataSealerException;
import be.fgov.ehealth.etee.crypto.encrypt.EncryptionToken;
import java.io.IOException;
import java.security.Key;
import java.util.List;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;

public class Crypto {
   private static final String SYMM_KEY_ALGORITHM = "DESede";
   private static DataSealer dataSealer;
   private static DataUnsealer dataUnsealer;
   private static EncryptionUtils encryptionUtils;

   static {
      try {
         dataSealer = EncryptionUtils.getInstance().initSealing();
         dataUnsealer = EncryptionUtils.getInstance().initUnsealing();
         encryptionUtils = EncryptionUtils.getInstance();
      } catch (Exception var1) {
         var1.printStackTrace();
      }

   }

   public Crypto() throws Exception {
   }

   public static byte[] seal(EncryptionToken etk, String data) throws IntegrationModuleException {
      return seal(etk, data.getBytes());
   }

   public static byte[] seal(EncryptionToken etk, byte[] data) throws IntegrationModuleException {
      try {
         return dataSealer.seal(etk, data);
      } catch (DataSealerException var3) {
         throw new IntegrationModuleException("technical.connector.error.data.seal", var3);
      }
   }

   public static byte[] seal(byte[] data, SecretKey secretKey, String keyId) throws IntegrationModuleException {
      try {
         return dataSealer.seal(data, secretKey, keyId);
      } catch (DataSealerException var4) {
         throw new IntegrationModuleException("technical.connector.error.data.seal", var4);
      }
   }

   public static byte[] seal(List<EncryptionToken> etks, byte[] data) throws IntegrationModuleException {
      return seal((EncryptionToken)etks.get(0), data);
   }

   public static byte[] unseal(byte[] data) throws IntegrationModuleException {
      try {
         return encryptionUtils.unsealingData(dataUnsealer.unseal(data));
      } catch (IOException var2) {
         throw new IntegrationModuleException("technical.connector.error.data.seal", var2);
      }
   }

   public static byte[] unseal(SecretKey secretKey, byte[] data) throws IntegrationModuleException {
      try {
         return encryptionUtils.unsealingData(dataUnsealer.unseal(data, secretKey));
      } catch (Exception var3) {
         throw new IntegrationModuleException("technical.connector.error.data.seal", var3);
      }
   }

   public byte[] unsealWithSymmKey(Key symmKey, byte[] objectToUnseal) throws IntegrationModuleException {
      try {
         Cipher cipher = Cipher.getInstance("DESede");
         cipher.init(2, symmKey);
         return cipher.doFinal(objectToUnseal);
      } catch (Exception var4) {
         throw new IntegrationModuleException("technical.connector.error.data.seal", var4);
      }
   }

   public static byte[] unsealForUnknown(KeyResult keyResult, byte[] data) throws IntegrationModuleException {
      return unseal(keyResult.getSecretKey(), data);
   }
}
