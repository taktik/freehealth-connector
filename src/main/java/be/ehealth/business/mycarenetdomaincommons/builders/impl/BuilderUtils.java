package be.ehealth.business.mycarenetdomaincommons.builders.impl;

import be.ehealth.business.mycarenetdomaincommons.domain.Blob;
import be.ehealth.business.mycarenetdomaincommons.exception.InvalidBlobContentConnectorException;
import be.ehealth.business.mycarenetdomaincommons.exception.InvalidBlobContentConnectorExceptionValues;
import org.taktik.connector.technical.exception.TechnicalConnectorException;
import org.taktik.connector.technical.exception.TechnicalConnectorExceptionValues;
import org.taktik.connector.technical.utils.ConnectorIOUtils;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.apache.commons.lang.ArrayUtils;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.encoders.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class BuilderUtils {
   private static Logger logger = LoggerFactory.getLogger(BuilderUtils.class);

   public static byte[] checkAndDecompress(byte[] content, String contentEncoding, byte[] blobHashValue, boolean hashTagRequired) throws InvalidBlobContentConnectorException, TechnicalConnectorException {
      if (content != null && !ArrayUtils.isEmpty(content)) {
         byte[] decompressedBlob = decompressBlob(content, contentEncoding);
         if (blobHashValue != null && blobHashValue.length > 0) {
            checkHash(blobHashValue, decompressedBlob);
         } else if (hashTagRequired) {
            throw new InvalidBlobContentConnectorException(InvalidBlobContentConnectorExceptionValues.HASHVALUE_NULL, decompressedBlob);
         }

         return decompressedBlob;
      } else {
         throw new InvalidBlobContentConnectorException(InvalidBlobContentConnectorExceptionValues.PARAMETER_NULL, (Blob)null, new Object[]{"The content of the blob"});
      }
   }

   public static void checkHash(byte[] blobHashValue, byte[] decompressedBlob) throws InvalidBlobContentConnectorException, TechnicalConnectorException {
      try {
         byte[] calculatedHashValue = buildHash(decompressedBlob);
         if (!Arrays.areEqual(blobHashValue, calculatedHashValue)) {
            String blobHashAsString = blobHashValue != null ? new String(Base64.encode(blobHashValue)) : "";
            String calculatedHashAsString = calculatedHashValue != null ? new String(Base64.encode(calculatedHashValue)) : "";
            throw new InvalidBlobContentConnectorException(InvalidBlobContentConnectorExceptionValues.HASH_VALUES_DIFFERENT, (Blob)null, decompressedBlob, new Object[]{blobHashAsString, calculatedHashAsString});
         }
      } catch (NoSuchAlgorithmException var5) {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_GENERAL, var5, new Object[]{var5.getMessage()});
      }
   }

   public static byte[] decompressBlob(byte[] decompressedBlob, String contentEncoding) {
      if ("none".equals(contentEncoding)) {
         logger.warn("decompressBlob called with on blob with contentEncoding " + contentEncoding + " : decompress will be skipped!");
      } else {
         try {
            decompressedBlob = ConnectorIOUtils.decompress(decompressedBlob);
            if (!contentEncoding.equals("deflate")) {
               logger.warn("Blob was flagged as not deflated but was.");
            }
         } catch (TechnicalConnectorException var3) {
            if (contentEncoding.equals("deflate")) {
               logger.warn("Blob was flagged as deflated but wasn't.");
            }
         }
      }

      return decompressedBlob;
   }

   public static byte[] buildHash(byte[] decompressedBlob) throws NoSuchAlgorithmException {
      MessageDigest md = MessageDigest.getInstance("SHA-256");
      md.update(decompressedBlob);
      return md.digest();
   }
}
