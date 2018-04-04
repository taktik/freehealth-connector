package org.taktik.connector.business.recipeprojects.core.technical.connector.utils;

import org.taktik.connector.business.recipeprojects.core.exceptions.IntegrationModuleException;
import org.taktik.connector.business.recipeprojects.core.utils.EncryptionUtils;
import be.ehealth.technicalconnector.service.kgss.domain.KeyResult;
import be.fgov.ehealth.etee.crypto.decrypt.DataUnsealer;
import be.fgov.ehealth.etee.crypto.encrypt.DataSealer;
import be.fgov.ehealth.etee.crypto.encrypt.DataSealerException;
import be.fgov.ehealth.etee.crypto.encrypt.EncryptionToken;
import java.io.IOException;
import java.security.Key;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.List;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import org.apache.log4j.Logger;

public class Crypto {
   private static final Logger LOG = Logger.getLogger(Crypto.class);
   private static final String SYMM_KEY_ALGORITHM = "DESede";

   public static byte[] seal(EncryptionToken etk, String data) throws IntegrationModuleException {
      return seal(etk, data.getBytes());
   }

   public static byte[] seal(EncryptionToken etk, byte[] data) throws IntegrationModuleException {
      try {
         DataSealer dataSealer = EncryptionUtils.getInstance().initSealing();
         return dataSealer.seal(etk, data);
      } catch (KeyStoreException var3) {
         throw new IntegrationModuleException("technical.connector.error.data.seal", var3);
      } catch (UnrecoverableKeyException var4) {
         throw new IntegrationModuleException("technical.connector.error.data.seal", var4);
      } catch (NoSuchAlgorithmException var5) {
         throw new IntegrationModuleException("technical.connector.error.data.seal", var5);
      } catch (CertificateException var6) {
         throw new IntegrationModuleException("technical.connector.error.data.seal", var6);
      } catch (IOException var7) {
         throw new IntegrationModuleException("technical.connector.error.data.seal", var7);
      } catch (DataSealerException var8) {
         throw new IntegrationModuleException("technical.connector.error.data.seal", var8);
      }
   }

   public static byte[] seal(byte[] data, SecretKey secretKey, String keyId) throws IntegrationModuleException {
      try {
         DataSealer dataSealer = EncryptionUtils.getInstance().initSealing();
         return dataSealer.seal(data, secretKey, keyId);
      } catch (UnrecoverableKeyException | NoSuchAlgorithmException | CertificateException | IOException | DataSealerException | KeyStoreException var4) {
         throw new IntegrationModuleException("technical.connector.error.data.seal", var4);
      }
   }

   public static byte[] seal(List<EncryptionToken> etks, byte[] data) throws IntegrationModuleException {
      return seal((EncryptionToken)etks.get(0), data);
   }

   public static byte[] unseal(byte[] data) throws IntegrationModuleException {
      try {
         EncryptionUtils encryptionUtils = EncryptionUtils.getInstance();
         DataUnsealer dataUnsealer = encryptionUtils.initUnsealing();
         return encryptionUtils.unsealingData(dataUnsealer.unseal(data));
      } catch (KeyStoreException var3) {
         throw new IntegrationModuleException("technical.connector.error.data.seal", var3);
      } catch (UnrecoverableKeyException var4) {
         throw new IntegrationModuleException("technical.connector.error.data.seal", var4);
      } catch (NoSuchAlgorithmException var5) {
         throw new IntegrationModuleException("technical.connector.error.data.seal", var5);
      } catch (CertificateException var6) {
         throw new IntegrationModuleException("technical.connector.error.data.seal", var6);
      } catch (IOException var7) {
         throw new IntegrationModuleException("technical.connector.error.data.seal", var7);
      }
   }

   public static byte[] unseal(SecretKey secretKey, byte[] data) throws IntegrationModuleException {
      try {
         EncryptionUtils encryptionUtils = EncryptionUtils.getInstance();
         DataUnsealer dataUnsealer = encryptionUtils.initUnsealing();
         return encryptionUtils.unsealingData(dataUnsealer.unseal(data, secretKey));
      } catch (Exception var4) {
         throw new IntegrationModuleException("technical.connector.error.data.seal", var4);
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
