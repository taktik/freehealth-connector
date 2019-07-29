package be.ehealth.businessconnector.dmg.mappers;

import be.ehealth.business.mycarenetdomaincommons.domain.Blob;
import be.fgov.ehealth.globalmedicalfile.core.v1.BlobType;

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
      return result;
   }

   public static Blob mapBlobfromBlobType(BlobType blob) {
      Blob result = new Blob();
      result.setContent(blob.getValue());
      result.setId(blob.getId());
      result.setContentEncoding(blob.getContentEncoding());
      result.setHashValue(blob.getHashValue());
      result.setContentType(blob.getContentType());
      return result;
   }
}
