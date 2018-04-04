package be.ehealth.technicalconnector.idgenerator.impl;

import be.ehealth.technicalconnector.idgenerator.IdGenerator;
import java.math.BigInteger;

public class TimeBasedUniqueKeyGenerator implements IdGenerator {
   private static char[] radixRange = " !\"#$%&'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_`abcdefghijklmnopqrstuvwxyz{|}~".toCharArray();

   public String generateId() {
      return radix(new BigInteger("" + System.currentTimeMillis() + System.nanoTime()));
   }

   private static String radix(BigInteger number) {
      StringBuilder sb = new StringBuilder();
      BigInteger left = number;
      BigInteger radix = BigInteger.valueOf((long)radixRange.length);

      do {
         int remainder = left.mod(radix).intValue();
         left = left.divide(radix);
         sb.append(radixRange[remainder]);
      } while(left.compareTo(BigInteger.valueOf(0L)) != 0);

      return sb.reverse().toString();
   }
}
