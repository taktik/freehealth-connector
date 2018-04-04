package be.ehealth.technicalconnector.utils;

import java.io.UnsupportedEncodingException;
import java.lang.management.ManagementFactory;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.security.GeneralSecurityException;
import java.util.Random;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;
import org.apache.commons.lang.Validate;

public class SecureString {
   private static final String ALGO_NAME = "PBEWithMD5AndDES";
   private byte[] salt;
   private byte[] cipherBytes;

   public SecureString(char[] value) throws UnsupportedEncodingException, GeneralSecurityException {
      Validate.notNull(value);
      this.salt = new byte[8];
      (new Random(System.currentTimeMillis())).nextBytes(this.salt);
      this.encrypt(charToByte(value));
   }

   public char[] getValue() throws GeneralSecurityException {
      return byteToChar(this.decrypt());
   }

   public void append(char[] chars) throws GeneralSecurityException {
      Validate.notNull(chars);
      if (chars.length != 0) {
         char[] value = this.getValue();
         char[] result = new char[value.length + chars.length];

         int i;
         for(i = 0; i < value.length; ++i) {
            result[i] = value[i];
            value[i] = 0;
         }

         for(i = 0; i < chars.length; ++i) {
            result[value.length + i] = chars[i];
         }

         this.encrypt(charToByte(result));

         for(i = 0; i < result.length; ++i) {
            result[i] = 0;
         }

      }
   }

   private static char[] getMetaPassword() {
      long jvmStartMillis = ManagementFactory.getRuntimeMXBean().getStartTime();
      byte[] bytes = ByteBuffer.allocate(8).putLong(jvmStartMillis).array();
      StringBuilder sb = new StringBuilder();
      byte[] arr$ = bytes;
      int len$ = bytes.length;

      for(int i$ = 0; i$ < len$; ++i$) {
         byte b = arr$[i$];
         sb.append(String.format("%02X ", b));
      }

      return sb.toString().toCharArray();
   }

   private static byte[] charToByte(char[] chars) {
      byte[] b = new byte[chars.length << 1];
      CharBuffer cBuffer = ByteBuffer.wrap(b).asCharBuffer();

      for(int i = 0; i < chars.length; ++i) {
         cBuffer.put(chars[i]);
      }

      return b;
   }

   private static char[] byteToChar(byte[] bytes) {
      CharBuffer cBuffer = ByteBuffer.wrap(bytes).asCharBuffer();
      return cBuffer.toString().toCharArray();
   }

   private void encrypt(byte[] cleartext) throws GeneralSecurityException {
      SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("PBEWithMD5AndDES");
      SecretKey key = keyFactory.generateSecret(new PBEKeySpec(getMetaPassword()));
      Cipher pbeCipher = Cipher.getInstance("PBEWithMD5AndDES");
      pbeCipher.init(1, key, new PBEParameterSpec(this.salt, 20));
      this.cipherBytes = pbeCipher.doFinal(cleartext);
   }

   private byte[] decrypt() throws GeneralSecurityException {
      SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("PBEWithMD5AndDES");
      SecretKey key = keyFactory.generateSecret(new PBEKeySpec(getMetaPassword()));
      Cipher pbeCipher = Cipher.getInstance("PBEWithMD5AndDES");
      pbeCipher.init(2, key, new PBEParameterSpec(this.salt, 20));
      return pbeCipher.doFinal(this.cipherBytes);
   }
}
