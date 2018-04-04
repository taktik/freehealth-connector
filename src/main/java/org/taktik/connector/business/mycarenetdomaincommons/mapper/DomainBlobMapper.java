package org.taktik.connector.business.mycarenetdomaincommons.mapper;

import org.taktik.connector.business.mycarenetdomaincommons.domain.Blob;
import org.taktik.connector.technical.exception.TechnicalConnectorException;
import org.taktik.connector.technical.exception.TechnicalConnectorExceptionValues;
import org.taktik.connector.technical.utils.ByteArrayDatasource;
import java.io.IOException;
import javax.activation.DataHandler;
import org.apache.commons.io.IOUtils;
import org.w3._2005._05.xmlmime.Base64Binary;

public final class DomainBlobMapper {
   public static Blob mapToBlob(be.cin.types.v1.Blob blob) throws TechnicalConnectorException {
      Blob result = new Blob();
      result.setContent(convertToByteArray(blob.getValue()));
      result.setId(blob.getId());
      result.setContentEncoding(blob.getContentEncoding());
      result.setHashValue(blob.getHashValue());
      result.setContentType(blob.getContentType());
      return result;
   }

   public static be.cin.types.v1.Blob mapBlobToCinBlob(Blob blob) {
      be.cin.types.v1.Blob result = new be.cin.types.v1.Blob();
      ByteArrayDatasource rawData = new ByteArrayDatasource(blob.getContent());
      DataHandler dh = new DataHandler(rawData);
      result.setValue(dh);
      result.setMessageName(blob.getMessageName());
      result.setId(blob.getId());
      result.setContentEncoding(blob.getContentEncoding());
      result.setHashValue(blob.getHashValue());
      result.setContentType(blob.getContentType());
      return result;
   }

   public static Base64Binary mapB64fromByte(byte[] param) {
      Base64Binary result = new Base64Binary();
      result.setValue(param);
      result.setContentType("text/xml");
      return result;
   }

   private static byte[] convertToByteArray(DataHandler value) throws TechnicalConnectorException {
      if (value == null) {
         return new byte[0];
      } else {
         try {
            return IOUtils.toByteArray(value.getInputStream());
         } catch (IOException var2) {
            throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.UNKNOWN_ERROR, new Object[]{"IoException while converting dataHandler to byteArray", var2});
         }
      }
   }
}
