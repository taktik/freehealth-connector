package be.apb.gfddpp.common.utils;

import be.apb.gfddpp.common.exceptions.GFDDPPException;
import be.apb.gfddpp.common.status.StatusCode;
import be.apb.gfddpp.common.status.StatusResolver;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

public class Utils {
   private static Random rand = new Random();

   public static String commaSeparated(List<?> objectList) {
      StringBuilder builder = new StringBuilder();
      Iterator<?> iterator = objectList.iterator();
      if (iterator.hasNext()) {
         builder.append(iterator.next().toString());

         while(iterator.hasNext()) {
            builder.append(", ");
            builder.append(iterator.next().toString());
         }
      }

      return builder.toString();
   }

   public static Date transformToDate(XMLGregorianCalendar date) {
      return date == null ? null : new Date(date.toGregorianCalendar().getTimeInMillis());
   }

   public static XMLGregorianCalendar transformToXMLGregorianCalendar(Date date) throws GFDDPPException {
      if (date == null) {
         return null;
      } else {
         DatatypeFactory dataTypeFactory;
         try {
            dataTypeFactory = DatatypeFactory.newInstance();
         } catch (DatatypeConfigurationException var3) {
            throw new GFDDPPException(StatusCode.COMMON_ERROR_DATATYPECONFIGURATION);
         }

         GregorianCalendar gregorianCalendar = new GregorianCalendar();
         gregorianCalendar.setTimeInMillis(date.getTime());
         return dataTypeFactory.newXMLGregorianCalendar(gregorianCalendar);
      }
   }

   public static String formatId(String id) {
      String formated = null;
      if (id != null) {
         formated = id.replaceAll("[\\W_]", "");
      }

      return formated;
   }

   public static String formatLogMessage(StatusCode statusCode, String message) {
      return "---> CODE: " + statusCode + "\n---> TYPE: " + StatusResolver.resolveType(StatusResolver.TYPE_BUNDLE, statusCode) + "\n---> MESSAGE: " + message + "\n";
   }

   public static String formatLogMessage(StatusCode statusCode) {
      return "---> CODE: " + statusCode + "\n---> TYPE: " + StatusResolver.resolveType(StatusResolver.TYPE_BUNDLE, statusCode) + "\n---> MESSAGE: " + StatusResolver.resolveMessage(StatusResolver.MESSAGE_DEFAULT_BUNDLE, statusCode) + "\n";
   }

   public static String formatLogMessage(StatusCode statusCode, String[] placeHolders) {
      return "---> CODE: " + statusCode + "\n---> TYPE: " + StatusResolver.resolveType(StatusResolver.TYPE_BUNDLE, statusCode) + "\n---> MESSAGE: " + StatusResolver.resolveMessage(StatusResolver.MESSAGE_DEFAULT_BUNDLE, statusCode, placeHolders) + "\n";
   }

   public static String generateGUID(String token) {
      return hash(token + rand.nextLong() + System.nanoTime());
   }

   public static String generateDocumentUID(String token) {
      return hashDocumentUID(token + rand.nextLong() + System.nanoTime());
   }

   private static String hashDocumentUID(String content) {
      MessageDigest digest;
      try {
         digest = MessageDigest.getInstance("SHA-256");
      } catch (NoSuchAlgorithmException var3) {
         throw new IllegalStateException(var3);
      }

      digest.update(content.getBytes());
      String hash = (new BigInteger(1, digest.digest())).toString(16);
      if (hash.length() > 25) {
         hash = hash.substring(hash.length() - 25, hash.length());
      }

      while(hash.length() < 25) {
         hash = "0" + hash;
      }

      return hash.toString();
   }

   private static String hash(String content) {
      MessageDigest digest;
      try {
         digest = MessageDigest.getInstance("SHA-256");
      } catch (NoSuchAlgorithmException var3) {
         throw new IllegalStateException(var3);
      }

      digest.update(content.getBytes());
      String hash = (new BigInteger(1, digest.digest())).toString(16);
      if (hash.length() > 32) {
         hash = hash.substring(hash.length() - 32, hash.length());
      }

      while(hash.length() < 32) {
         hash = "0" + hash;
      }

      return hash.substring(0, 8) + "-" + hash.substring(8, 12) + "-" + hash.substring(12, 16) + "-" + hash.substring(16, 20) + "-" + hash.substring(20, 32);
   }
}
