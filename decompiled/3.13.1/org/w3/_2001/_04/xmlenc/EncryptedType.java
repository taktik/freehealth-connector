package org.w3._2001._04.xmlenc;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.w3._2000._09.xmldsig.KeyInfo;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "EncryptedType",
   propOrder = {"encryptionMethod", "keyInfo", "cipherData", "encryptionProperties"}
)
@XmlSeeAlso({EncryptedKey.class, EncryptedData.class})
public abstract class EncryptedType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "EncryptionMethod"
   )
   protected EncryptionMethodType encryptionMethod;
   @XmlElement(
      name = "KeyInfo",
      namespace = "http://www.w3.org/2000/09/xmldsig#"
   )
   protected KeyInfo keyInfo;
   @XmlElement(
      name = "CipherData",
      required = true
   )
   protected CipherData cipherData;
   @XmlElement(
      name = "EncryptionProperties"
   )
   protected EncryptionProperties encryptionProperties;
   @XmlAttribute(
      name = "Id"
   )
   @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
   @XmlID
   @XmlSchemaType(
      name = "ID"
   )
   protected String id;
   @XmlAttribute(
      name = "Type"
   )
   @XmlSchemaType(
      name = "anyURI"
   )
   protected String type;
   @XmlAttribute(
      name = "MimeType"
   )
   protected String mimeType;
   @XmlAttribute(
      name = "Encoding"
   )
   @XmlSchemaType(
      name = "anyURI"
   )
   protected String encoding;

   public EncryptionMethodType getEncryptionMethod() {
      return this.encryptionMethod;
   }

   public void setEncryptionMethod(EncryptionMethodType value) {
      this.encryptionMethod = value;
   }

   public KeyInfo getKeyInfo() {
      return this.keyInfo;
   }

   public void setKeyInfo(KeyInfo value) {
      this.keyInfo = value;
   }

   public CipherData getCipherData() {
      return this.cipherData;
   }

   public void setCipherData(CipherData value) {
      this.cipherData = value;
   }

   public EncryptionProperties getEncryptionProperties() {
      return this.encryptionProperties;
   }

   public void setEncryptionProperties(EncryptionProperties value) {
      this.encryptionProperties = value;
   }

   public String getId() {
      return this.id;
   }

   public void setId(String value) {
      this.id = value;
   }

   public String getType() {
      return this.type;
   }

   public void setType(String value) {
      this.type = value;
   }

   public String getMimeType() {
      return this.mimeType;
   }

   public void setMimeType(String value) {
      this.mimeType = value;
   }

   public String getEncoding() {
      return this.encoding;
   }

   public void setEncoding(String value) {
      this.encoding = value;
   }
}
