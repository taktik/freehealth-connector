package be.ehealth.technicalconnector.utils;

import be.ehealth.technicalconnector.config.ConfigFactory;
import be.ehealth.technicalconnector.config.ConfigValidator;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.exception.TechnicalConnectorExceptionValues;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.Security;
import java.text.MessageFormat;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class ConnectorCryptoUtils {
   private static final Logger LOG = LoggerFactory.getLogger(ConnectorCryptoUtils.class);
   public static final String CONNECTORCRYPTO_ALGO_NAME_KEY = "be.ehealth.technicalconnector.utils.connectorcryptoutils.default_algo_name";
   public static final String CONNECTORCRYPTO_KEYSIZE_KEY = "be.ehealth.technicalconnector.utils.connectorcryptoutils.default_keysize";
   public static final String CONNECTORCRYPTO_MOCK_KEY = "be.ehealth.technicalconnector.utils.connectorcryptoutils.mock.desede";
   private static final String DEFAULT_ALGO_NAME = "AES";
   private static final int DEFAULT_KEYSIZE = 128;
   private static ConfigValidator conf = ConfigFactory.getConfigValidator();
   private static KeyGenerator keyGen;

   private ConnectorCryptoUtils() {
   }

   public static SecretKey generateKey() throws TechnicalConnectorException {
      Integer keySize = conf.getIntegerProperty("be.ehealth.technicalconnector.utils.connectorcryptoutils.default_keysize", 128);
      return generateKey(keySize);
   }

   public static SecretKey generateKey(int keySize) throws TechnicalConnectorException {
      String algo = conf.getProperty("be.ehealth.technicalconnector.utils.connectorcryptoutils.default_algo_name", "AES");
      return generateKey(algo, keySize);
   }

   public static SecretKey generateKey(String algo, int keySize) throws TechnicalConnectorException {
      try {
         if (keyGen == null) {
            keyGen = KeyGenerator.getInstance(algo);
         }

         keyGen.init(keySize, new SecureRandom());
         return keyGen.generateKey();
      } catch (NoSuchAlgorithmException var3) {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_GENERAL, var3, new Object[]{var3.getMessage()});
      }
   }

   public static byte[] decrypt(Key key, byte[] encryptedBytes) throws TechnicalConnectorException {
      return decrypt(key, key.getAlgorithm(), encryptedBytes);
   }

   public static byte[] decrypt(Key key, String algo, byte[] encryptedBytes) throws TechnicalConnectorException {
      ByteArrayOutputStream baos = null;

      byte[] var7;
      try {
         Cipher cipher = Cipher.getInstance(algo, "BC");
         cipher.init(2, key);
         int index = 0;
         int blockSize = cipher.getBlockSize();
         baos = new ByteArrayOutputStream();
         decrypt(encryptedBytes, baos, cipher, index, blockSize);
         var7 = baos.toByteArray();
      } catch (Exception var11) {
         TechnicalConnectorExceptionValues errorValue = TechnicalConnectorExceptionValues.ERROR_CRYPTO;
         String param = "Decrypt failed.";
         LOG.debug(MessageFormat.format(errorValue.getMessage(), param));
         throw new TechnicalConnectorException(errorValue, var11, new Object[]{param});
      } finally {
         ConnectorIOUtils.closeQuietly((Object)baos);
      }

      return var7;
   }

   private static void decrypt(byte[] encryptedBytes, ByteArrayOutputStream baos, Cipher cipher, int index, int blockSize) throws IOException, IllegalBlockSizeException, BadPaddingException {
      try {
         decrypt(encryptedBytes, baos, index, blockSize, new SinglePartOperation(cipher));
      } catch (Exception var6) {
         LOG.debug("Not a SinglePart operation cipher. Trying MultiPartOperation. Reason [{}]", ExceptionUtils.getRootCauseMessage(var6), var6);
         baos.reset();
         decrypt(encryptedBytes, baos, index, blockSize, new MultiPartOperationDecryptor(cipher));
      }

   }

   private static void decrypt(byte[] encryptedBytes, ByteArrayOutputStream baos, int index, int blockSize, Decryptor decryptor) throws IOException, IllegalBlockSizeException, BadPaddingException {
      if (blockSize == 0) {
         baos.write(decryptor.doFinal(encryptedBytes, 0, encryptedBytes.length));
      } else {
         for(; index < encryptedBytes.length; index += blockSize) {
            if (index + blockSize >= encryptedBytes.length) {
               baos.write(decryptor.doFinal(encryptedBytes, index, blockSize));
            } else {
               byte[] blockResult = decryptor.update(encryptedBytes, index, blockSize);
               if (blockResult != null) {
                  baos.write(blockResult);
               }
            }
         }
      }

   }

   public static void setKeyGenerator(KeyGenerator keyGenerator) {
      keyGen = keyGenerator;
   }

   public static byte[] calculateDigest(String digestAlgo, byte[] content) throws TechnicalConnectorException {
      TechnicalConnectorExceptionValues errorValue = TechnicalConnectorExceptionValues.ERROR_GENERAL;
      String param = "Digest calculation failed for " + digestAlgo + ".";

      try {
         MessageDigest md = MessageDigest.getInstance(digestAlgo);
         InputStream fis = new ByteArrayInputStream(content);
         byte[] dataBytes = new byte[1024];
         int nread = false;

         int nread;
         while((nread = fis.read(dataBytes)) != -1) {
            md.update(dataBytes, 0, nread);
         }

         return md.digest();
      } catch (Exception var8) {
         LOG.debug(MessageFormat.format(errorValue.getMessage(), param));
         throw new TechnicalConnectorException(errorValue, var8, new Object[]{param});
      }
   }

   static {
      Security.addProvider(new BouncyCastleProvider());
   }

   private static class MultiPartOperationDecryptor implements Decryptor {
      private Cipher cipher;

      public MultiPartOperationDecryptor(Cipher cipher) {
         this.cipher = cipher;
      }

      public byte[] update(byte[] input, int inputOffset, int inputLen) throws IllegalBlockSizeException, BadPaddingException {
         return this.cipher.update(input, inputOffset, inputLen);
      }

      public byte[] doFinal(byte[] input, int inputOffset, int inputLen) throws IllegalBlockSizeException, BadPaddingException {
         return this.cipher.doFinal(input, inputOffset, inputLen);
      }
   }

   private static class SinglePartOperation implements Decryptor {
      private Cipher cipher;

      public SinglePartOperation(Cipher cipher) {
         this.cipher = cipher;
      }

      public byte[] update(byte[] input, int inputOffset, int inputLen) throws IllegalBlockSizeException, BadPaddingException {
         return this.cipher.doFinal(input, inputOffset, inputLen);
      }

      public byte[] doFinal(byte[] input, int inputOffset, int inputLen) throws IllegalBlockSizeException, BadPaddingException {
         return this.cipher.doFinal(input, inputOffset, inputLen);
      }
   }

   private interface Decryptor {
      byte[] update(byte[] var1, int var2, int var3) throws IllegalBlockSizeException, BadPaddingException;

      byte[] doFinal(byte[] var1, int var2, int var3) throws IllegalBlockSizeException, BadPaddingException;
   }
}
