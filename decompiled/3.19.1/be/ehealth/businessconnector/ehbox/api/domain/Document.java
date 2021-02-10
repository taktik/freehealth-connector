package be.ehealth.businessconnector.ehbox.api.domain;

import be.ehealth.businessconnector.ehbox.api.domain.exception.EhboxBusinessConnectorException;
import be.ehealth.businessconnector.ehbox.api.domain.exception.EhboxBusinessConnectorExceptionValues;
import be.ehealth.businessconnector.ehbox.api.utils.SigningValue;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.exception.UnsealConnectorException;
import be.ehealth.technicalconnector.utils.ConnectorIOUtils;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.text.MessageFormat;
import org.apache.commons.lang.ArrayUtils;
import org.bouncycastle.util.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Document implements Serializable {
   private static final long serialVersionUID = 1L;
   private static final Logger LOG = LoggerFactory.getLogger(Document.class);
   private String title;
   private byte[] content;
   private String filename;
   private String mimeType;
   private transient SigningValue signing;
   private UnsealConnectorException expection;

   public final void getDocument(String fullpath) throws EhboxBusinessConnectorException {
      File file = new File(fullpath);
      if (file.isDirectory()) {
         file = new File(fullpath + System.getProperty("file.separator") + this.filename);
      }

      FileOutputStream fos = null;

      try {
         fos = new FileOutputStream(file);
         fos.write(this.content);
         fos.flush();
      } catch (IOException var8) {
         LOG.debug("\t## " + MessageFormat.format(EhboxBusinessConnectorExceptionValues.ERROR_EHBOX_DOCUMENT_OUTPUTSTREAM.getMessage(), file.toURI()));
         throw new EhboxBusinessConnectorException(EhboxBusinessConnectorExceptionValues.ERROR_EHBOX_DOCUMENT_OUTPUTSTREAM, var8, new Object[]{file.toURI()});
      } finally {
         ConnectorIOUtils.closeQuietly((Object)fos);
      }

   }

   public final byte[] getContent() throws UnsealConnectorException {
      if (this.content == null && this.expection != null) {
         throw this.expection;
      } else {
         return Arrays.clone(this.content);
      }
   }

   public final void setContent(byte[] content) {
      this.content = Arrays.clone(content);
   }

   /** @deprecated */
   @Deprecated
   public final void setContent(Byte[] content) {
      this.content = ArrayUtils.toPrimitive(content);
   }

   public final void setContent(InputStream inputStream) throws TechnicalConnectorException {
      this.content = ConnectorIOUtils.getBytes(inputStream);
   }

   public final String getFilename() {
      return this.filename;
   }

   public final void setFilename(String filename) {
      this.filename = filename;
   }

   public final SigningValue getSigning() {
      return this.signing;
   }

   public final void setSigning(SigningValue signing) {
      this.signing = signing;
   }

   public final String getMimeType() {
      return this.mimeType;
   }

   public final void setMimeType(String mimeType) {
      this.mimeType = mimeType;
   }

   public final String getTitle() {
      return this.title;
   }

   public final void setTitle(String title) {
      this.title = title;
   }

   public void setException(UnsealConnectorException expection) {
      this.expection = expection;
   }
}
