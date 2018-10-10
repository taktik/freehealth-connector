package org.taktik.connector.business.dmg.mappers;

import org.taktik.connector.business.mycarenetdomaincommons.domain.Blob;
import be.fgov.ehealth.globalmedicalfile.core.v1.BlobType;

public final class BlobMapper {
   private BlobMapper() {
      throw new UnsupportedOperationException("only static methods may be used for BlobMapper");
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
