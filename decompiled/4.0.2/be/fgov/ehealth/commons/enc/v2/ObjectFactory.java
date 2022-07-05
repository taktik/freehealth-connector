package be.fgov.ehealth.commons.enc.v2;

import javax.activation.DataHandler;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAttachmentRef;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

@XmlRegistry
public class ObjectFactory {
   private static final QName _URI_QNAME = new QName("urn:be:fgov:ehealth:commons:enc:v2", "URI");
   private static final QName _EncryptedData_QNAME = new QName("urn:be:fgov:ehealth:commons:enc:v2", "EncryptedData");
   private static final QName _EncryptedKey_QNAME = new QName("urn:be:fgov:ehealth:commons:enc:v2", "EncryptedKey");

   public ObjectFactory() {
   }

   public CipherData createCipherData() {
      return new CipherData();
   }

   public CipherReference createCipherReference() {
      return new CipherReference();
   }

   public Digest createDigest() {
      return new Digest();
   }

   public CipherValue createCipherValue() {
      return new CipherValue();
   }

   public EncryptedDataType createEncryptedDataType() {
      return new EncryptedDataType();
   }

   public EncryptionMethod createEncryptionMethod() {
      return new EncryptionMethod();
   }

   public EncryptionPolicy createEncryptionPolicy() {
      return new EncryptionPolicy();
   }

   public KeyInfo createKeyInfo() {
      return new KeyInfo();
   }

   public Key createKey() {
      return new Key();
   }

   public ETKSearchCriteria createETKSearchCriteria() {
      return new ETKSearchCriteria();
   }

   @XmlElementDecl(
      namespace = "urn:be:fgov:ehealth:commons:enc:v2",
      name = "URI"
   )
   @XmlAttachmentRef
   public JAXBElement<DataHandler> createURI(DataHandler value) {
      return new JAXBElement(_URI_QNAME, DataHandler.class, (Class)null, value);
   }

   @XmlElementDecl(
      namespace = "urn:be:fgov:ehealth:commons:enc:v2",
      name = "EncryptedData"
   )
   public JAXBElement<EncryptedDataType> createEncryptedData(EncryptedDataType value) {
      return new JAXBElement(_EncryptedData_QNAME, EncryptedDataType.class, (Class)null, value);
   }

   @XmlElementDecl(
      namespace = "urn:be:fgov:ehealth:commons:enc:v2",
      name = "EncryptedKey"
   )
   public JAXBElement<EncryptedDataType> createEncryptedKey(EncryptedDataType value) {
      return new JAXBElement(_EncryptedKey_QNAME, EncryptedDataType.class, (Class)null, value);
   }
}
