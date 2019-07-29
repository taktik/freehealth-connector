package org.w3._2001._04.xmlenc;

import java.math.BigInteger;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;
import org.w3._2000._09.xmldsig.KeyInfo;

@XmlRegistry
public class ObjectFactory {
   private static final QName _ReferenceListDataReference_QNAME = new QName("http://www.w3.org/2001/04/xmlenc#", "DataReference");
   private static final QName _ReferenceListKeyReference_QNAME = new QName("http://www.w3.org/2001/04/xmlenc#", "KeyReference");
   private static final QName _AgreementMethodKANonce_QNAME = new QName("http://www.w3.org/2001/04/xmlenc#", "KA-Nonce");
   private static final QName _AgreementMethodOriginatorKeyInfo_QNAME = new QName("http://www.w3.org/2001/04/xmlenc#", "OriginatorKeyInfo");
   private static final QName _AgreementMethodRecipientKeyInfo_QNAME = new QName("http://www.w3.org/2001/04/xmlenc#", "RecipientKeyInfo");
   private static final QName _EncryptionMethodTypeKeySize_QNAME = new QName("http://www.w3.org/2001/04/xmlenc#", "KeySize");
   private static final QName _EncryptionMethodTypeOAEPparams_QNAME = new QName("http://www.w3.org/2001/04/xmlenc#", "OAEPparams");

   public CipherData createCipherData() {
      return new CipherData();
   }

   public CipherReference createCipherReference() {
      return new CipherReference();
   }

   public TransformsType createTransformsType() {
      return new TransformsType();
   }

   public EncryptedData createEncryptedData() {
      return new EncryptedData();
   }

   public EncryptionMethodType createEncryptionMethodType() {
      return new EncryptionMethodType();
   }

   public EncryptionProperties createEncryptionProperties() {
      return new EncryptionProperties();
   }

   public EncryptionProperty createEncryptionProperty() {
      return new EncryptionProperty();
   }

   public EncryptedKey createEncryptedKey() {
      return new EncryptedKey();
   }

   public ReferenceList createReferenceList() {
      return new ReferenceList();
   }

   public ReferenceType createReferenceType() {
      return new ReferenceType();
   }

   public AgreementMethod createAgreementMethod() {
      return new AgreementMethod();
   }

   @XmlElementDecl(
      namespace = "http://www.w3.org/2001/04/xmlenc#",
      name = "DataReference",
      scope = ReferenceList.class
   )
   public JAXBElement<ReferenceType> createReferenceListDataReference(ReferenceType value) {
      return new JAXBElement(_ReferenceListDataReference_QNAME, ReferenceType.class, ReferenceList.class, value);
   }

   @XmlElementDecl(
      namespace = "http://www.w3.org/2001/04/xmlenc#",
      name = "KeyReference",
      scope = ReferenceList.class
   )
   public JAXBElement<ReferenceType> createReferenceListKeyReference(ReferenceType value) {
      return new JAXBElement(_ReferenceListKeyReference_QNAME, ReferenceType.class, ReferenceList.class, value);
   }

   @XmlElementDecl(
      namespace = "http://www.w3.org/2001/04/xmlenc#",
      name = "KA-Nonce",
      scope = AgreementMethod.class
   )
   public JAXBElement<byte[]> createAgreementMethodKANonce(byte[] value) {
      return new JAXBElement(_AgreementMethodKANonce_QNAME, byte[].class, AgreementMethod.class, (byte[])value);
   }

   @XmlElementDecl(
      namespace = "http://www.w3.org/2001/04/xmlenc#",
      name = "OriginatorKeyInfo",
      scope = AgreementMethod.class
   )
   public JAXBElement<KeyInfo> createAgreementMethodOriginatorKeyInfo(KeyInfo value) {
      return new JAXBElement(_AgreementMethodOriginatorKeyInfo_QNAME, KeyInfo.class, AgreementMethod.class, value);
   }

   @XmlElementDecl(
      namespace = "http://www.w3.org/2001/04/xmlenc#",
      name = "RecipientKeyInfo",
      scope = AgreementMethod.class
   )
   public JAXBElement<KeyInfo> createAgreementMethodRecipientKeyInfo(KeyInfo value) {
      return new JAXBElement(_AgreementMethodRecipientKeyInfo_QNAME, KeyInfo.class, AgreementMethod.class, value);
   }

   @XmlElementDecl(
      namespace = "http://www.w3.org/2001/04/xmlenc#",
      name = "KeySize",
      scope = EncryptionMethodType.class
   )
   public JAXBElement<BigInteger> createEncryptionMethodTypeKeySize(BigInteger value) {
      return new JAXBElement(_EncryptionMethodTypeKeySize_QNAME, BigInteger.class, EncryptionMethodType.class, value);
   }

   @XmlElementDecl(
      namespace = "http://www.w3.org/2001/04/xmlenc#",
      name = "OAEPparams",
      scope = EncryptionMethodType.class
   )
   public JAXBElement<byte[]> createEncryptionMethodTypeOAEPparams(byte[] value) {
      return new JAXBElement(_EncryptionMethodTypeOAEPparams_QNAME, byte[].class, EncryptionMethodType.class, (byte[])value);
   }
}
