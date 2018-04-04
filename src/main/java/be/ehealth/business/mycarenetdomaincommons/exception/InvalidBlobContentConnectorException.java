package be.ehealth.business.mycarenetdomaincommons.exception;

import be.ehealth.business.mycarenetdomaincommons.domain.Blob;
import org.taktik.connector.technical.exception.TechnicalConnectorException;
import java.text.MessageFormat;
import org.bouncycastle.util.Arrays;

public class InvalidBlobContentConnectorException extends TechnicalConnectorException {
   private Blob blob;
   private byte[] decompressedBlob;
   private static final long serialVersionUID = 3015002994386182489L;

   public InvalidBlobContentConnectorException(InvalidBlobContentConnectorExceptionValues errorCodeValue, Blob blob, byte[] decompressedBlob, Object... params) {
      super(MessageFormat.format(errorCodeValue.getMessage(), params), errorCodeValue.getErrorCode());
      this.blob = blob;
      this.decompressedBlob = Arrays.clone(decompressedBlob);
   }

   public InvalidBlobContentConnectorException(InvalidBlobContentConnectorExceptionValues errorCodeValue, Blob blob, Object... params) {
      super(MessageFormat.format(errorCodeValue.getMessage(), params), errorCodeValue.getErrorCode());
      this.blob = blob;
   }

   public InvalidBlobContentConnectorException(InvalidBlobContentConnectorExceptionValues errorCodeValue, Blob blob) {
      super(errorCodeValue.getMessage(), errorCodeValue.getErrorCode());
      this.blob = blob;
   }

   public InvalidBlobContentConnectorException(InvalidBlobContentConnectorExceptionValues errorCodeValue, byte[] decompressedBlob) {
      super(errorCodeValue.getMessage(), errorCodeValue.getErrorCode());
      this.decompressedBlob = Arrays.clone(decompressedBlob);
   }

   public InvalidBlobContentConnectorException(InvalidBlobContentConnectorExceptionValues errorCodeValue, Blob blob, Throwable e, Object... params) {
      super(MessageFormat.format(errorCodeValue.getMessage(), params), errorCodeValue.getErrorCode(), e);
      this.blob = blob;
   }

   public byte[] getDecompressedBlob() {
      return Arrays.clone(this.decompressedBlob);
   }

   public Blob getBlob() {
      return this.blob;
   }
}
