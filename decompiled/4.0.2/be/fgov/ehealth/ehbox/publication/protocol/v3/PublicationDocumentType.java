package be.fgov.ehealth.ehbox.publication.protocol.v3;

import java.io.Serializable;
import javax.activation.DataHandler;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttachmentRef;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "PublicationDocumentType",
   propOrder = {"title", "encryptableTextContent", "encryptableBinaryContent", "downloadFileName", "mimeType", "digest"}
)
public class PublicationDocumentType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Title",
      required = true
   )
   protected String title;
   @XmlElement(
      name = "EncryptableTextContent"
   )
   protected byte[] encryptableTextContent;
   @XmlElement(
      name = "EncryptableBinaryContent",
      type = String.class
   )
   @XmlAttachmentRef
   @XmlSchemaType(
      name = "anyURI"
   )
   protected DataHandler encryptableBinaryContent;
   @XmlElement(
      name = "DownloadFileName",
      required = true
   )
   protected String downloadFileName;
   @XmlElement(
      name = "MimeType",
      required = true
   )
   protected String mimeType;
   @XmlElement(
      name = "Digest",
      required = true
   )
   protected String digest;

   public PublicationDocumentType() {
   }

   public String getTitle() {
      return this.title;
   }

   public void setTitle(String value) {
      this.title = value;
   }

   public byte[] getEncryptableTextContent() {
      return this.encryptableTextContent;
   }

   public void setEncryptableTextContent(byte[] value) {
      this.encryptableTextContent = value;
   }

   public DataHandler getEncryptableBinaryContent() {
      return this.encryptableBinaryContent;
   }

   public void setEncryptableBinaryContent(DataHandler value) {
      this.encryptableBinaryContent = value;
   }

   public String getDownloadFileName() {
      return this.downloadFileName;
   }

   public void setDownloadFileName(String value) {
      this.downloadFileName = value;
   }

   public String getMimeType() {
      return this.mimeType;
   }

   public void setMimeType(String value) {
      this.mimeType = value;
   }

   public String getDigest() {
      return this.digest;
   }

   public void setDigest(String value) {
      this.digest = value;
   }
}
