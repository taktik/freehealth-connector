package be.fgov.ehealth.standards.kmehr.schema.v1;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import org.w3._2000._09.xmldsig.Signature;
import org.w3._2001._04.xmlenc.EncryptedType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "kmehrmessageType",
   propOrder = {"confidentiality", "header", "base64EncryptedData", "encryptedData", "folders", "signature"}
)
@XmlRootElement(
   name = "kmehrmessage"
)
public class Kmehrmessage implements Serializable {
   private static final long serialVersionUID = 1L;
   protected ConfidentialityType confidentiality;
   @XmlElement(
      required = true
   )
   protected HeaderType header;
   @XmlElement(
      name = "Base64EncryptedData"
   )
   protected Base64EncryptedDataType base64EncryptedData;
   @XmlElement(
      name = "EncryptedData"
   )
   protected EncryptedType encryptedData;
   @XmlElement(
      name = "folder"
   )
   protected List<FolderType> folders;
   @XmlElementRef(
      name = "Signature",
      namespace = "http://www.ehealth.fgov.be/standards/kmehr/schema/v1",
      type = JAXBElement.class
   )
   protected JAXBElement<Signature> signature;

   public ConfidentialityType getConfidentiality() {
      return this.confidentiality;
   }

   public void setConfidentiality(ConfidentialityType value) {
      this.confidentiality = value;
   }

   public HeaderType getHeader() {
      return this.header;
   }

   public void setHeader(HeaderType value) {
      this.header = value;
   }

   public Base64EncryptedDataType getBase64EncryptedData() {
      return this.base64EncryptedData;
   }

   public void setBase64EncryptedData(Base64EncryptedDataType value) {
      this.base64EncryptedData = value;
   }

   public EncryptedType getEncryptedData() {
      return this.encryptedData;
   }

   public void setEncryptedData(EncryptedType value) {
      this.encryptedData = value;
   }

   public List<FolderType> getFolders() {
      if (this.folders == null) {
         this.folders = new ArrayList();
      }

      return this.folders;
   }

   public JAXBElement<Signature> getSignature() {
      return this.signature;
   }

   public void setSignature(JAXBElement<Signature> value) {
      this.signature = value;
   }
}
