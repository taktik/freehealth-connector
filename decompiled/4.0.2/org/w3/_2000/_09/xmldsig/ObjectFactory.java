package org.w3._2000._09.xmldsig;

import java.math.BigInteger;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

@XmlRegistry
public class ObjectFactory {
   private static final QName _DigestValue_QNAME = new QName("http://www.w3.org/2000/09/xmldsig#", "DigestValue");
   private static final QName _KeyName_QNAME = new QName("http://www.w3.org/2000/09/xmldsig#", "KeyName");
   private static final QName _MgmtData_QNAME = new QName("http://www.w3.org/2000/09/xmldsig#", "MgmtData");
   private static final QName _TransformXPath_QNAME = new QName("http://www.w3.org/2000/09/xmldsig#", "XPath");
   private static final QName _SignatureMethodHMACOutputLength_QNAME = new QName("http://www.w3.org/2000/09/xmldsig#", "HMACOutputLength");
   private static final QName _X509DataX509IssuerSerial_QNAME = new QName("http://www.w3.org/2000/09/xmldsig#", "X509IssuerSerial");
   private static final QName _X509DataX509SKI_QNAME = new QName("http://www.w3.org/2000/09/xmldsig#", "X509SKI");
   private static final QName _X509DataX509SubjectName_QNAME = new QName("http://www.w3.org/2000/09/xmldsig#", "X509SubjectName");
   private static final QName _X509DataX509Certificate_QNAME = new QName("http://www.w3.org/2000/09/xmldsig#", "X509Certificate");
   private static final QName _X509DataX509CRL_QNAME = new QName("http://www.w3.org/2000/09/xmldsig#", "X509CRL");
   private static final QName _SPKIDataSPKISexp_QNAME = new QName("http://www.w3.org/2000/09/xmldsig#", "SPKISexp");

   public ObjectFactory() {
   }

   public Transforms createTransforms() {
      return new Transforms();
   }

   public Transform createTransform() {
      return new Transform();
   }

   public DigestMethod createDigestMethod() {
      return new DigestMethod();
   }

   public Signature createSignature() {
      return new Signature();
   }

   public SignedInfo createSignedInfo() {
      return new SignedInfo();
   }

   public CanonicalizationMethod createCanonicalizationMethod() {
      return new CanonicalizationMethod();
   }

   public SignatureMethod createSignatureMethod() {
      return new SignatureMethod();
   }

   public Reference createReference() {
      return new Reference();
   }

   public SignatureValue createSignatureValue() {
      return new SignatureValue();
   }

   public KeyInfo createKeyInfo() {
      return new KeyInfo();
   }

   public KeyValue createKeyValue() {
      return new KeyValue();
   }

   public DSAKeyValue createDSAKeyValue() {
      return new DSAKeyValue();
   }

   public RSAKeyValue createRSAKeyValue() {
      return new RSAKeyValue();
   }

   public RetrievalMethod createRetrievalMethod() {
      return new RetrievalMethod();
   }

   public X509Data createX509Data() {
      return new X509Data();
   }

   public X509IssuerSerialType createX509IssuerSerialType() {
      return new X509IssuerSerialType();
   }

   public PGPData createPGPData() {
      return new PGPData();
   }

   public SPKIData createSPKIData() {
      return new SPKIData();
   }

   public Object createObject() {
      return new Object();
   }

   public Manifest createManifest() {
      return new Manifest();
   }

   public SignatureProperties createSignatureProperties() {
      return new SignatureProperties();
   }

   public SignatureProperty createSignatureProperty() {
      return new SignatureProperty();
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
      name = "XPath",
      scope = Transform.class
   )
   public JAXBElement<String> createTransformXPath(String value) {
      return new JAXBElement(_TransformXPath_QNAME, String.class, Transform.class, value);
   }

   @XmlElementDecl(
      namespace = "http://www.w3.org/2000/09/xmldsig#",
      name = "HMACOutputLength",
      scope = SignatureMethod.class
   )
   public JAXBElement<BigInteger> createSignatureMethodHMACOutputLength(BigInteger value) {
      return new JAXBElement(_SignatureMethodHMACOutputLength_QNAME, BigInteger.class, SignatureMethod.class, value);
   }

   @XmlElementDecl(
      namespace = "http://www.w3.org/2000/09/xmldsig#",
      name = "X509IssuerSerial",
      scope = X509Data.class
   )
   public JAXBElement<X509IssuerSerialType> createX509DataX509IssuerSerial(X509IssuerSerialType value) {
      return new JAXBElement(_X509DataX509IssuerSerial_QNAME, X509IssuerSerialType.class, X509Data.class, value);
   }

   @XmlElementDecl(
      namespace = "http://www.w3.org/2000/09/xmldsig#",
      name = "X509SKI",
      scope = X509Data.class
   )
   public JAXBElement<byte[]> createX509DataX509SKI(byte[] value) {
      return new JAXBElement(_X509DataX509SKI_QNAME, byte[].class, X509Data.class, (byte[])value);
   }

   @XmlElementDecl(
      namespace = "http://www.w3.org/2000/09/xmldsig#",
      name = "X509SubjectName",
      scope = X509Data.class
   )
   public JAXBElement<String> createX509DataX509SubjectName(String value) {
      return new JAXBElement(_X509DataX509SubjectName_QNAME, String.class, X509Data.class, value);
   }

   @XmlElementDecl(
      namespace = "http://www.w3.org/2000/09/xmldsig#",
      name = "X509Certificate",
      scope = X509Data.class
   )
   public JAXBElement<byte[]> createX509DataX509Certificate(byte[] value) {
      return new JAXBElement(_X509DataX509Certificate_QNAME, byte[].class, X509Data.class, (byte[])value);
   }

   @XmlElementDecl(
      namespace = "http://www.w3.org/2000/09/xmldsig#",
      name = "X509CRL",
      scope = X509Data.class
   )
   public JAXBElement<byte[]> createX509DataX509CRL(byte[] value) {
      return new JAXBElement(_X509DataX509CRL_QNAME, byte[].class, X509Data.class, (byte[])value);
   }

   @XmlElementDecl(
      namespace = "http://www.w3.org/2000/09/xmldsig#",
      name = "SPKISexp",
      scope = SPKIData.class
   )
   public JAXBElement<byte[]> createSPKIDataSPKISexp(byte[] value) {
      return new JAXBElement(_SPKIDataSPKISexp_QNAME, byte[].class, SPKIData.class, (byte[])value);
   }
}
