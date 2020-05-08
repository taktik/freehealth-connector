package org.taktik.connector.technical.utils;

import org.taktik.connector.technical.enumeration.MimeType;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.activation.DataSource;
import org.apache.commons.lang.ArrayUtils;

public class ByteArrayDatasource implements DataSource {
   private byte[] byteArray;
   private String contentType;
   protected static final String DEFAULT_CONTENT_TYPE;

   public ByteArrayDatasource(byte[] byteArray) {
      this.contentType = DEFAULT_CONTENT_TYPE;
      this.byteArray = ArrayUtils.clone(byteArray);
      this.contentType = DEFAULT_CONTENT_TYPE;
   }

   public ByteArrayDatasource(byte[] byteArray, String contentType) {
      this.contentType = DEFAULT_CONTENT_TYPE;
      this.byteArray = ArrayUtils.clone(byteArray);
      this.contentType = contentType;
   }

   public InputStream getInputStream() throws IOException {
      return new ByteArrayInputStream(this.byteArray);
   }

   public OutputStream getOutputStream() throws IOException {
      throw new UnsupportedOperationException("This is a read-only datasource");
   }

   public String getContentType() {
      return this.contentType;
   }

   public String getName() {
      throw new UnsupportedOperationException("This is a read-only datasource");
   }

   public byte[] getByteArray() {
      return ArrayUtils.clone(this.byteArray);
   }

   static {
      DEFAULT_CONTENT_TYPE = MimeType.OCTET_STREAM.getValue();
   }
}
