package org.w3._2001._04.xmlenc_;

import java.math.BigInteger;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;
import org.w3._2000._09.xmldsig_.KeyInfoType;

@XmlRegistry
public class ObjectFactory {
   private static final QName _CipherData_QNAME = new QName("http://www.w3.org/2001/04/xmlenc#", "CipherData");
   private static final QName _CipherReference_QNAME = new QName("http://www.w3.org/2001/04/xmlenc#", "CipherReference");
   private static final QName _EncryptedData_QNAME = new QName("http://www.w3.org/2001/04/xmlenc#", "EncryptedData");
   private static final QName _EncryptedKey_QNAME = new QName("http://www.w3.org/2001/04/xmlenc#", "EncryptedKey");
   private static final QName _AgreementMethod_QNAME = new QName("http://www.w3.org/2001/04/xmlenc#", "AgreementMethod");
   private static final QName _EncryptionProperties_QNAME = new QName("http://www.w3.org/2001/04/xmlenc#", "EncryptionProperties");
   private static final QName _EncryptionProperty_QNAME = new QName("http://www.w3.org/2001/04/xmlenc#", "EncryptionProperty");
   private static final QName _ReferenceListDataReference_QNAME = new QName("http://www.w3.org/2001/04/xmlenc#", "DataReference");
   private static final QName _ReferenceListKeyReference_QNAME = new QName("http://www.w3.org/2001/04/xmlenc#", "KeyReference");
   private static final QName _EncryptionMethodTypeKeySize_QNAME = new QName("http://www.w3.org/2001/04/xmlenc#", "KeySize");
   private static final QName _EncryptionMethodTypeOAEPparams_QNAME = new QName("http://www.w3.org/2001/04/xmlenc#", "OAEPparams");
   private static final QName _AgreementMethodTypeKANonce_QNAME = new QName("http://www.w3.org/2001/04/xmlenc#", "KA-Nonce");
   private static final QName _AgreementMethodTypeOriginatorKeyInfo_QNAME = new QName("http://www.w3.org/2001/04/xmlenc#", "OriginatorKeyInfo");
   private static final QName _AgreementMethodTypeRecipientKeyInfo_QNAME = new QName("http://www.w3.org/2001/04/xmlenc#", "RecipientKeyInfo");

   public CipherDataType createCipherDataType() {
      return new CipherDataType();
   }

   public CipherReferenceType createCipherReferenceType() {
      return new CipherReferenceType();
   }

   public EncryptedDataType createEncryptedDataType() {
      return new EncryptedDataType();
   }

   public EncryptedKeyType createEncryptedKeyType() {
      return new EncryptedKeyType();
   }

   public AgreementMethodType createAgreementMethodType() {
      return new AgreementMethodType();
   }

   public ReferenceList createReferenceList() {
      return new ReferenceList();
   }

   public ReferenceType createReferenceType() {
      return new ReferenceType();
   }

   public EncryptionPropertiesType createEncryptionPropertiesType() {
      return new EncryptionPropertiesType();
   }

   public EncryptionPropertyType createEncryptionPropertyType() {
      return new EncryptionPropertyType();
   }

   public EncryptionMethodType createEncryptionMethodType() {
      return new EncryptionMethodType();
   }

   public TransformsType createTransformsType() {
      return new TransformsType();
   }

   @XmlElementDecl(
      namespace = "http://www.w3.org/2001/04/xmlenc#",
      name = "CipherData"
   )
   public JAXBElement<CipherDataType> createCipherData(CipherDataType value) {
      return new JAXBElement(_CipherData_QNAME, CipherDataType.class, (Class)null, value);
   }

   @XmlElementDecl(
      namespace = "http://www.w3.org/2001/04/xmlenc#",
      name = "CipherReference"
   )
   public JAXBElement<CipherReferenceType> createCipherReference(CipherReferenceType value) {
      return new JAXBElement(_CipherReference_QNAME, CipherReferenceType.class, (Class)null, value);
   }

   @XmlElementDecl(
      namespace = "http://www.w3.org/2001/04/xmlenc#",
      name = "EncryptedData"
   )
   public JAXBElement<EncryptedDataType> createEncryptedData(EncryptedDataType value) {
      return new JAXBElement(_EncryptedData_QNAME, EncryptedDataType.class, (Class)null, value);
   }

   @XmlElementDecl(
      namespace = "http://www.w3.org/2001/04/xmlenc#",
      name = "EncryptedKey"
   )
   public JAXBElement<EncryptedKeyType> createEncryptedKey(EncryptedKeyType value) {
      return new JAXBElement(_EncryptedKey_QNAME, EncryptedKeyType.class, (Class)null, value);
   }

   @XmlElementDecl(
      namespace = "http://www.w3.org/2001/04/xmlenc#",
      name = "AgreementMethod"
   )
   public JAXBElement<AgreementMethodType> createAgreementMethod(AgreementMethodType value) {
      return new JAXBElement(_AgreementMethod_QNAME, AgreementMethodType.class, (Class)null, value);
   }

   @XmlElementDecl(
      namespace = "http://www.w3.org/2001/04/xmlenc#",
      name = "EncryptionProperties"
   )
   public JAXBElement<EncryptionPropertiesType> createEncryptionProperties(EncryptionPropertiesType value) {
      return new JAXBElement(_EncryptionProperties_QNAME, EncryptionPropertiesType.class, (Class)null, value);
   }

   @XmlElementDecl(
      namespace = "http://www.w3.org/2001/04/xmlenc#",
      name = "EncryptionProperty"
   )
   public JAXBElement<EncryptionPropertyType> createEncryptionProperty(EncryptionPropertyType value) {
      return new JAXBElement(_EncryptionProperty_QNAME, EncryptionPropertyType.class, (Class)null, value);
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

   @XmlElementDecl(
      namespace = "http://www.w3.org/2001/04/xmlenc#",
      name = "KA-Nonce",
      scope = AgreementMethodType.class
   )
   public JAXBElement<byte[]> createAgreementMethodTypeKANonce(byte[] value) {
      return new JAXBElement(_AgreementMethodTypeKANonce_QNAME, byte[].class, AgreementMethodType.class, (byte[])value);
   }

   @XmlElementDecl(
      namespace = "http://www.w3.org/2001/04/xmlenc#",
      name = "OriginatorKeyInfo",
      scope = AgreementMethodType.class
   )
   public JAXBElement<KeyInfoType> createAgreementMethodTypeOriginatorKeyInfo(KeyInfoType value) {
      return new JAXBElement(_AgreementMethodTypeOriginatorKeyInfo_QNAME, KeyInfoType.class, AgreementMethodType.class, value);
   }

   @XmlElementDecl(
      namespace = "http://www.w3.org/2001/04/xmlenc#",
      name = "RecipientKeyInfo",
      scope = AgreementMethodType.class
   )
   public JAXBElement<KeyInfoType> createAgreementMethodTypeRecipientKeyInfo(KeyInfoType value) {
      return new JAXBElement(_AgreementMethodTypeRecipientKeyInfo_QNAME, KeyInfoType.class, AgreementMethodType.class, value);
   }
}
