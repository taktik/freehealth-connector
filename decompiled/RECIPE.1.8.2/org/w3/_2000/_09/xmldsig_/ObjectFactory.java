package org.w3._2000._09.xmldsig_;

import java.math.BigInteger;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

@XmlRegistry
public class ObjectFactory {
   private static final QName _Signature_QNAME = new QName("http://www.w3.org/2000/09/xmldsig#", "Signature");
   private static final QName _SignatureValue_QNAME = new QName("http://www.w3.org/2000/09/xmldsig#", "SignatureValue");
   private static final QName _SignedInfo_QNAME = new QName("http://www.w3.org/2000/09/xmldsig#", "SignedInfo");
   private static final QName _CanonicalizationMethod_QNAME = new QName("http://www.w3.org/2000/09/xmldsig#", "CanonicalizationMethod");
   private static final QName _SignatureMethod_QNAME = new QName("http://www.w3.org/2000/09/xmldsig#", "SignatureMethod");
   private static final QName _Reference_QNAME = new QName("http://www.w3.org/2000/09/xmldsig#", "Reference");
   private static final QName _Transforms_QNAME = new QName("http://www.w3.org/2000/09/xmldsig#", "Transforms");
   private static final QName _Transform_QNAME = new QName("http://www.w3.org/2000/09/xmldsig#", "Transform");
   private static final QName _DigestMethod_QNAME = new QName("http://www.w3.org/2000/09/xmldsig#", "DigestMethod");
   private static final QName _DigestValue_QNAME = new QName("http://www.w3.org/2000/09/xmldsig#", "DigestValue");
   private static final QName _KeyInfo_QNAME = new QName("http://www.w3.org/2000/09/xmldsig#", "KeyInfo");
   private static final QName _KeyName_QNAME = new QName("http://www.w3.org/2000/09/xmldsig#", "KeyName");
   private static final QName _MgmtData_QNAME = new QName("http://www.w3.org/2000/09/xmldsig#", "MgmtData");
   private static final QName _KeyValue_QNAME = new QName("http://www.w3.org/2000/09/xmldsig#", "KeyValue");
   private static final QName _RetrievalMethod_QNAME = new QName("http://www.w3.org/2000/09/xmldsig#", "RetrievalMethod");
   private static final QName _X509Data_QNAME = new QName("http://www.w3.org/2000/09/xmldsig#", "X509Data");
   private static final QName _PGPData_QNAME = new QName("http://www.w3.org/2000/09/xmldsig#", "PGPData");
   private static final QName _SPKIData_QNAME = new QName("http://www.w3.org/2000/09/xmldsig#", "SPKIData");
   private static final QName _Object_QNAME = new QName("http://www.w3.org/2000/09/xmldsig#", "Object");
   private static final QName _Manifest_QNAME = new QName("http://www.w3.org/2000/09/xmldsig#", "Manifest");
   private static final QName _SignatureProperties_QNAME = new QName("http://www.w3.org/2000/09/xmldsig#", "SignatureProperties");
   private static final QName _SignatureProperty_QNAME = new QName("http://www.w3.org/2000/09/xmldsig#", "SignatureProperty");
   private static final QName _DSAKeyValue_QNAME = new QName("http://www.w3.org/2000/09/xmldsig#", "DSAKeyValue");
   private static final QName _RSAKeyValue_QNAME = new QName("http://www.w3.org/2000/09/xmldsig#", "RSAKeyValue");
   private static final QName _SPKIDataTypeSPKISexp_QNAME = new QName("http://www.w3.org/2000/09/xmldsig#", "SPKISexp");
   private static final QName _PGPDataTypePGPKeyID_QNAME = new QName("http://www.w3.org/2000/09/xmldsig#", "PGPKeyID");
   private static final QName _PGPDataTypePGPKeyPacket_QNAME = new QName("http://www.w3.org/2000/09/xmldsig#", "PGPKeyPacket");
   private static final QName _X509DataTypeX509IssuerSerial_QNAME = new QName("http://www.w3.org/2000/09/xmldsig#", "X509IssuerSerial");
   private static final QName _X509DataTypeX509SKI_QNAME = new QName("http://www.w3.org/2000/09/xmldsig#", "X509SKI");
   private static final QName _X509DataTypeX509SubjectName_QNAME = new QName("http://www.w3.org/2000/09/xmldsig#", "X509SubjectName");
   private static final QName _X509DataTypeX509Certificate_QNAME = new QName("http://www.w3.org/2000/09/xmldsig#", "X509Certificate");
   private static final QName _X509DataTypeX509CRL_QNAME = new QName("http://www.w3.org/2000/09/xmldsig#", "X509CRL");
   private static final QName _TransformTypeXPath_QNAME = new QName("http://www.w3.org/2000/09/xmldsig#", "XPath");
   private static final QName _SignatureMethodTypeHMACOutputLength_QNAME = new QName("http://www.w3.org/2000/09/xmldsig#", "HMACOutputLength");

   public SignatureType createSignatureType() {
      return new SignatureType();
   }

   public SignatureValueType createSignatureValueType() {
      return new SignatureValueType();
   }

   public SignedInfoType createSignedInfoType() {
      return new SignedInfoType();
   }

   public CanonicalizationMethodType createCanonicalizationMethodType() {
      return new CanonicalizationMethodType();
   }

   public SignatureMethodType createSignatureMethodType() {
      return new SignatureMethodType();
   }

   public ReferenceType createReferenceType() {
      return new ReferenceType();
   }

   public TransformsType createTransformsType() {
      return new TransformsType();
   }

   public TransformType createTransformType() {
      return new TransformType();
   }

   public DigestMethodType createDigestMethodType() {
      return new DigestMethodType();
   }

   public KeyInfoType createKeyInfoType() {
      return new KeyInfoType();
   }

   public KeyValueType createKeyValueType() {
      return new KeyValueType();
   }

   public RetrievalMethodType createRetrievalMethodType() {
      return new RetrievalMethodType();
   }

   public X509DataType createX509DataType() {
      return new X509DataType();
   }

   public PGPDataType createPGPDataType() {
      return new PGPDataType();
   }

   public SPKIDataType createSPKIDataType() {
      return new SPKIDataType();
   }

   public ObjectType createObjectType() {
      return new ObjectType();
   }

   public ManifestType createManifestType() {
      return new ManifestType();
   }

   public SignaturePropertiesType createSignaturePropertiesType() {
      return new SignaturePropertiesType();
   }

   public SignaturePropertyType createSignaturePropertyType() {
      return new SignaturePropertyType();
   }

   public DSAKeyValueType createDSAKeyValueType() {
      return new DSAKeyValueType();
   }

   public RSAKeyValueType createRSAKeyValueType() {
      return new RSAKeyValueType();
   }

   public X509IssuerSerialType createX509IssuerSerialType() {
      return new X509IssuerSerialType();
   }

   @XmlElementDecl(
      namespace = "http://www.w3.org/2000/09/xmldsig#",
      name = "Signature"
   )
   public JAXBElement<SignatureType> createSignature(SignatureType value) {
      return new JAXBElement(_Signature_QNAME, SignatureType.class, (Class)null, value);
   }

   @XmlElementDecl(
      namespace = "http://www.w3.org/2000/09/xmldsig#",
      name = "SignatureValue"
   )
   public JAXBElement<SignatureValueType> createSignatureValue(SignatureValueType value) {
      return new JAXBElement(_SignatureValue_QNAME, SignatureValueType.class, (Class)null, value);
   }

   @XmlElementDecl(
      namespace = "http://www.w3.org/2000/09/xmldsig#",
      name = "SignedInfo"
   )
   public JAXBElement<SignedInfoType> createSignedInfo(SignedInfoType value) {
      return new JAXBElement(_SignedInfo_QNAME, SignedInfoType.class, (Class)null, value);
   }

   @XmlElementDecl(
      namespace = "http://www.w3.org/2000/09/xmldsig#",
      name = "CanonicalizationMethod"
   )
   public JAXBElement<CanonicalizationMethodType> createCanonicalizationMethod(CanonicalizationMethodType value) {
      return new JAXBElement(_CanonicalizationMethod_QNAME, CanonicalizationMethodType.class, (Class)null, value);
   }

   @XmlElementDecl(
      namespace = "http://www.w3.org/2000/09/xmldsig#",
      name = "SignatureMethod"
   )
   public JAXBElement<SignatureMethodType> createSignatureMethod(SignatureMethodType value) {
      return new JAXBElement(_SignatureMethod_QNAME, SignatureMethodType.class, (Class)null, value);
   }

   @XmlElementDecl(
      namespace = "http://www.w3.org/2000/09/xmldsig#",
      name = "Reference"
   )
   public JAXBElement<ReferenceType> createReference(ReferenceType value) {
      return new JAXBElement(_Reference_QNAME, ReferenceType.class, (Class)null, value);
   }

   @XmlElementDecl(
      namespace = "http://www.w3.org/2000/09/xmldsig#",
      name = "Transforms"
   )
   public JAXBElement<TransformsType> createTransforms(TransformsType value) {
      return new JAXBElement(_Transforms_QNAME, TransformsType.class, (Class)null, value);
   }

   @XmlElementDecl(
      namespace = "http://www.w3.org/2000/09/xmldsig#",
      name = "Transform"
   )
   public JAXBElement<TransformType> createTransform(TransformType value) {
      return new JAXBElement(_Transform_QNAME, TransformType.class, (Class)null, value);
   }

   @XmlElementDecl(
      namespace = "http://www.w3.org/2000/09/xmldsig#",
      name = "DigestMethod"
   )
   public JAXBElement<DigestMethodType> createDigestMethod(DigestMethodType value) {
      return new JAXBElement(_DigestMethod_QNAME, DigestMethodType.class, (Class)null, value);
   }

   @XmlElementDecl(
      namespace = "http://www.w3.org/2000/09/xmldsig#",
      name = "DigestValue"
   )
   public JAXBElement<byte[]> createDigestValue(byte[] value) {
      return new JAXBElement(_DigestValue_QNAME, byte[].class, (Class)null, (byte[])value);
   }

   @XmlElementDecl(
      namespace = "http://www.w3.org/2000/09/xmldsig#",
      name = "KeyInfo"
   )
   public JAXBElement<KeyInfoType> createKeyInfo(KeyInfoType value) {
      return new JAXBElement(_KeyInfo_QNAME, KeyInfoType.class, (Class)null, value);
   }

   @XmlElementDecl(
      namespace = "http://www.w3.org/2000/09/xmldsig#",
      name = "KeyName"
   )
   public JAXBElement<String> createKeyName(String value) {
      return new JAXBElement(_KeyName_QNAME, String.class, (Class)null, value);
   }

   @XmlElementDecl(
      namespace = "http://www.w3.org/2000/09/xmldsig#",
      name = "MgmtData"
   )
   public JAXBElement<String> createMgmtData(String value) {
      return new JAXBElement(_MgmtData_QNAME, String.class, (Class)null, value);
   }

   @XmlElementDecl(
      namespace = "http://www.w3.org/2000/09/xmldsig#",
      name = "KeyValue"
   )
   public JAXBElement<KeyValueType> createKeyValue(KeyValueType value) {
      return new JAXBElement(_KeyValue_QNAME, KeyValueType.class, (Class)null, value);
   }

   @XmlElementDecl(
      namespace = "http://www.w3.org/2000/09/xmldsig#",
      name = "RetrievalMethod"
   )
   public JAXBElement<RetrievalMethodType> createRetrievalMethod(RetrievalMethodType value) {
      return new JAXBElement(_RetrievalMethod_QNAME, RetrievalMethodType.class, (Class)null, value);
   }

   @XmlElementDecl(
      namespace = "http://www.w3.org/2000/09/xmldsig#",
      name = "X509Data"
   )
   public JAXBElement<X509DataType> createX509Data(X509DataType value) {
      return new JAXBElement(_X509Data_QNAME, X509DataType.class, (Class)null, value);
   }

   @XmlElementDecl(
      namespace = "http://www.w3.org/2000/09/xmldsig#",
      name = "PGPData"
   )
   public JAXBElement<PGPDataType> createPGPData(PGPDataType value) {
      return new JAXBElement(_PGPData_QNAME, PGPDataType.class, (Class)null, value);
   }

   @XmlElementDecl(
      namespace = "http://www.w3.org/2000/09/xmldsig#",
      name = "SPKIData"
   )
   public JAXBElement<SPKIDataType> createSPKIData(SPKIDataType value) {
      return new JAXBElement(_SPKIData_QNAME, SPKIDataType.class, (Class)null, value);
   }

   @XmlElementDecl(
      namespace = "http://www.w3.org/2000/09/xmldsig#",
      name = "Object"
   )
   public JAXBElement<ObjectType> createObject(ObjectType value) {
      return new JAXBElement(_Object_QNAME, ObjectType.class, (Class)null, value);
   }

   @XmlElementDecl(
      namespace = "http://www.w3.org/2000/09/xmldsig#",
      name = "Manifest"
   )
   public JAXBElement<ManifestType> createManifest(ManifestType value) {
      return new JAXBElement(_Manifest_QNAME, ManifestType.class, (Class)null, value);
   }

   @XmlElementDecl(
      namespace = "http://www.w3.org/2000/09/xmldsig#",
      name = "SignatureProperties"
   )
   public JAXBElement<SignaturePropertiesType> createSignatureProperties(SignaturePropertiesType value) {
      return new JAXBElement(_SignatureProperties_QNAME, SignaturePropertiesType.class, (Class)null, value);
   }

   @XmlElementDecl(
      namespace = "http://www.w3.org/2000/09/xmldsig#",
      name = "SignatureProperty"
   )
   public JAXBElement<SignaturePropertyType> createSignatureProperty(SignaturePropertyType value) {
      return new JAXBElement(_SignatureProperty_QNAME, SignaturePropertyType.class, (Class)null, value);
   }

   @XmlElementDecl(
      namespace = "http://www.w3.org/2000/09/xmldsig#",
      name = "DSAKeyValue"
   )
   public JAXBElement<DSAKeyValueType> createDSAKeyValue(DSAKeyValueType value) {
      return new JAXBElement(_DSAKeyValue_QNAME, DSAKeyValueType.class, (Class)null, value);
   }

   @XmlElementDecl(
      namespace = "http://www.w3.org/2000/09/xmldsig#",
      name = "RSAKeyValue"
   )
   public JAXBElement<RSAKeyValueType> createRSAKeyValue(RSAKeyValueType value) {
      return new JAXBElement(_RSAKeyValue_QNAME, RSAKeyValueType.class, (Class)null, value);
   }

   @XmlElementDecl(
      namespace = "http://www.w3.org/2000/09/xmldsig#",
      name = "SPKISexp",
      scope = SPKIDataType.class
   )
   public JAXBElement<byte[]> createSPKIDataTypeSPKISexp(byte[] value) {
      return new JAXBElement(_SPKIDataTypeSPKISexp_QNAME, byte[].class, SPKIDataType.class, (byte[])value);
   }

   @XmlElementDecl(
      namespace = "http://www.w3.org/2000/09/xmldsig#",
      name = "PGPKeyID",
      scope = PGPDataType.class
   )
   public JAXBElement<byte[]> createPGPDataTypePGPKeyID(byte[] value) {
      return new JAXBElement(_PGPDataTypePGPKeyID_QNAME, byte[].class, PGPDataType.class, (byte[])value);
   }

   @XmlElementDecl(
      namespace = "http://www.w3.org/2000/09/xmldsig#",
      name = "PGPKeyPacket",
      scope = PGPDataType.class
   )
   public JAXBElement<byte[]> createPGPDataTypePGPKeyPacket(byte[] value) {
      return new JAXBElement(_PGPDataTypePGPKeyPacket_QNAME, byte[].class, PGPDataType.class, (byte[])value);
   }

   @XmlElementDecl(
      namespace = "http://www.w3.org/2000/09/xmldsig#",
      name = "X509IssuerSerial",
      scope = X509DataType.class
   )
   public JAXBElement<X509IssuerSerialType> createX509DataTypeX509IssuerSerial(X509IssuerSerialType value) {
      return new JAXBElement(_X509DataTypeX509IssuerSerial_QNAME, X509IssuerSerialType.class, X509DataType.class, value);
   }

   @XmlElementDecl(
      namespace = "http://www.w3.org/2000/09/xmldsig#",
      name = "X509SKI",
      scope = X509DataType.class
   )
   public JAXBElement<byte[]> createX509DataTypeX509SKI(byte[] value) {
      return new JAXBElement(_X509DataTypeX509SKI_QNAME, byte[].class, X509DataType.class, (byte[])value);
   }

   @XmlElementDecl(
      namespace = "http://www.w3.org/2000/09/xmldsig#",
      name = "X509SubjectName",
      scope = X509DataType.class
   )
   public JAXBElement<String> createX509DataTypeX509SubjectName(String value) {
      return new JAXBElement(_X509DataTypeX509SubjectName_QNAME, String.class, X509DataType.class, value);
   }

   @XmlElementDecl(
      namespace = "http://www.w3.org/2000/09/xmldsig#",
      name = "X509Certificate",
      scope = X509DataType.class
   )
   public JAXBElement<byte[]> createX509DataTypeX509Certificate(byte[] value) {
      return new JAXBElement(_X509DataTypeX509Certificate_QNAME, byte[].class, X509DataType.class, (byte[])value);
   }

   @XmlElementDecl(
      namespace = "http://www.w3.org/2000/09/xmldsig#",
      name = "X509CRL",
      scope = X509DataType.class
   )
   public JAXBElement<byte[]> createX509DataTypeX509CRL(byte[] value) {
      return new JAXBElement(_X509DataTypeX509CRL_QNAME, byte[].class, X509DataType.class, (byte[])value);
   }

   @XmlElementDecl(
      namespace = "http://www.w3.org/2000/09/xmldsig#",
      name = "XPath",
      scope = TransformType.class
   )
   public JAXBElement<String> createTransformTypeXPath(String value) {
      return new JAXBElement(_TransformTypeXPath_QNAME, String.class, TransformType.class, value);
   }

   @XmlElementDecl(
      namespace = "http://www.w3.org/2000/09/xmldsig#",
      name = "HMACOutputLength",
      scope = SignatureMethodType.class
   )
   public JAXBElement<BigInteger> createSignatureMethodTypeHMACOutputLength(BigInteger value) {
      return new JAXBElement(_SignatureMethodTypeHMACOutputLength_QNAME, BigInteger.class, SignatureMethodType.class, value);
   }
}
