package be.cin.types.v1;

import java.io.Serializable;
import javax.activation.DataHandler;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlMimeType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
        name = "Blob",
        propOrder = {"value"}
)
public class Blob implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlValue
   @XmlMimeType("application/octet-stream")
   protected DataHandler value;
   @XmlAttribute(
           name = "ContentType",
           required = true
   )
   protected String contentType;
   @XmlAttribute(
           name = "ContentEncoding"
   )
   protected String contentEncoding;
   @XmlAttribute(
           name = "ContentEncryption"
   )
   protected String contentEncryption;
   @XmlAttribute(
           name = "ContentEncryption"
   )
   protected byte[] etk;
   @XmlAttribute(
           name = "MessageName",
           required = true
   )
   protected String messageName;
   @XmlAttribute(
           name = "HashValue"
   )
   protected byte[] hashValue;
   @XmlAttribute(
           name = "Id"
   )
   @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
   @XmlSchemaType(
           name = "NCName"
   )
   protected String id;
   @XmlAttribute(
           name = "Reference"
   )
   protected String reference;
   @XmlAttribute(
           name = "Issuer"
   )
   @XmlSchemaType(
           name = "anyURI"
   )
   protected String issuer;

   public DataHandler getValue() {
      return this.value;
   }

   public void setValue(DataHandler value) {
      this.value = value;
   }

   public String getContentType() {
      return this.contentType;
   }

   public void setContentType(String value) {
      this.contentType = value;
   }

   public String getContentEncoding() {
      return this.contentEncoding == null ? "deflate" : this.contentEncoding;
   }

   public void setContentEncoding(String value) {
      this.contentEncoding = value;
   }

   public String getContentEncryption() { return contentEncryption; }

   public void setContentEncryption(String contentEncryption) { this.contentEncryption = contentEncryption; }

   public byte[] getEtk() {
      return this.etk;
   }

   public void setEtk(byte[] value) {
      this.etk = value;
   }

   public String getMessageName() {
      return this.messageName;
   }

   public void setMessageName(String value) {
      this.messageName = value;
   }

   public byte[] getHashValue() {
      return this.hashValue;
   }

   public void setHashValue(byte[] value) {
      this.hashValue = value;
   }

   public String getId() {
      return this.id;
   }

   public void setId(String value) {
      this.id = value;
   }

   public String getReference() {
      return this.reference;
   }

   public void setReference(String value) {
      this.reference = value;
   }

   public String getIssuer() {
      return this.issuer;
   }

   public void setIssuer(String value) {
      this.issuer = value;
   }
}
