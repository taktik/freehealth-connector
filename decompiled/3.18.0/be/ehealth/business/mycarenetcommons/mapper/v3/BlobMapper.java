package be.ehealth.business.mycarenetcommons.mapper.v3;

import be.ehealth.business.mycarenetdomaincommons.domain.Blob;
import be.fgov.ehealth.mycarenet.commons.core.v3.BlobType;

public final class BlobMapper {
   private BlobMapper() {
      throw new UnsupportedOperationException("only static methods may be used for BlobMapper");
   }

   public static BlobType mapBlobTypefromBlob(Blob blob) {
      BlobType result = new BlobType();
      result.setValue(blob.getContent());
      result.setId(blob.getId());
      result.setContentEncoding(blob.getContentEncoding());
      result.setHashValue(blob.getHashValue());
      result.setContentType(blob.getContentType());
      result.setContentEncryption(blob.getContentEncryption());
      return result;
   }

   public static Blob mapBlobfromBlobType(BlobType blob) {
      Blob result = new Blob();
      result.setContent(blob.getValue());
      result.setId(blob.getId());
      result.setContentEncoding(blob.getContentEncoding());
      result.setContentEncryption(blob.getContentEncryption());
      result.setHashValue(blob.getHashValue());
      result.setContentType(blob.getContentType());
      return result;
   }
}
