package be.fgov.ehealth.mycarenet.commons.core.v3;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "BlobType",
   propOrder = {"value"}
)
public class BlobType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlValue
   protected byte[] value;
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
      name = "Etk"
   )
   protected byte[] etk;
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

   public BlobType() {
   }

   public byte[] getValue() {
      return this.value;
   }

   public void setValue(byte[] value) {
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

   public String getContentEncryption() {
      return this.contentEncryption;
   }

   public void setContentEncryption(String value) {
      this.contentEncryption = value;
   }

   public byte[] getEtk() {
      return this.etk;
   }

   public void setEtk(byte[] value) {
      this.etk = value;
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
}
