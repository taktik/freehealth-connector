package org.w3._2000._09.xmldsig;

import java.math.BigInteger;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

@XmlRegistry
public class ObjectFactory {

    private final static QName _KeyInfo_QNAME = new QName("http://www.w3.org/2000/09/xmldsig#", "KeyInfo");
    private final static QName _SignatureProperty_QNAME = new QName("http://www.w3.org/2000/09/xmldsig#", "SignatureProperty");
    private final static QName _RSAKeyValue_QNAME = new QName("http://www.w3.org/2000/09/xmldsig#", "RSAKeyValue");
    private final static QName _SignatureMethod_QNAME = new QName("http://www.w3.org/2000/09/xmldsig#", "SignatureMethod");
    private final static QName _Object_QNAME = new QName("http://www.w3.org/2000/09/xmldsig#", "Object");
    private final static QName _PGPData_QNAME = new QName("http://www.w3.org/2000/09/xmldsig#", "PGPData");
    private final static QName _RetrievalMethod_QNAME = new QName("http://www.w3.org/2000/09/xmldsig#", "RetrievalMethod");
    private final static QName _DSAKeyValue_QNAME = new QName("http://www.w3.org/2000/09/xmldsig#", "DSAKeyValue");
    private final static QName _SPKIData_QNAME = new QName("http://www.w3.org/2000/09/xmldsig#", "SPKIData");
    private final static QName _SignatureValue_QNAME = new QName("http://www.w3.org/2000/09/xmldsig#", "SignatureValue");
    private final static QName _KeyValue_QNAME = new QName("http://www.w3.org/2000/09/xmldsig#", "KeyValue");
    private final static QName _Transforms_QNAME = new QName("http://www.w3.org/2000/09/xmldsig#", "Transforms");
    private final static QName _DigestMethod_QNAME = new QName("http://www.w3.org/2000/09/xmldsig#", "DigestMethod");
    private final static QName _X509Data_QNAME = new QName("http://www.w3.org/2000/09/xmldsig#", "X509Data");
    private final static QName _KeyName_QNAME = new QName("http://www.w3.org/2000/09/xmldsig#", "KeyName");
    private final static QName _Signature_QNAME = new QName("http://www.w3.org/2000/09/xmldsig#", "Signature");
    private final static QName _MgmtData_QNAME = new QName("http://www.w3.org/2000/09/xmldsig#", "MgmtData");
    private final static QName _SignatureProperties_QNAME = new QName("http://www.w3.org/2000/09/xmldsig#", "SignatureProperties");
    private final static QName _Transform_QNAME = new QName("http://www.w3.org/2000/09/xmldsig#", "Transform");
    private final static QName _Reference_QNAME = new QName("http://www.w3.org/2000/09/xmldsig#", "Reference");
    private final static QName _DigestValue_QNAME = new QName("http://www.w3.org/2000/09/xmldsig#", "DigestValue");
    private final static QName _CanonicalizationMethod_QNAME = new QName("http://www.w3.org/2000/09/xmldsig#", "CanonicalizationMethod");
    private final static QName _SignedInfo_QNAME = new QName("http://www.w3.org/2000/09/xmldsig#", "SignedInfo");
    private final static QName _Manifest_QNAME = new QName("http://www.w3.org/2000/09/xmldsig#", "Manifest");
    private final static QName _X509DataX509CRL_QNAME = new QName("http://www.w3.org/2000/09/xmldsig#", "X509CRL");
    private final static QName _X509DataX509IssuerSerial_QNAME = new QName("http://www.w3.org/2000/09/xmldsig#", "X509IssuerSerial");
    private final static QName _X509DataX509SubjectName_QNAME = new QName("http://www.w3.org/2000/09/xmldsig#", "X509SubjectName");
    private final static QName _X509DataX509SKI_QNAME = new QName("http://www.w3.org/2000/09/xmldsig#", "X509SKI");
    private final static QName _X509DataX509Certificate_QNAME = new QName("http://www.w3.org/2000/09/xmldsig#", "X509Certificate");
    private final static QName _PGPDataPGPKeyID_QNAME = new QName("http://www.w3.org/2000/09/xmldsig#", "PGPKeyID");
    private final static QName _PGPDataPGPKeyPacket_QNAME = new QName("http://www.w3.org/2000/09/xmldsig#", "PGPKeyPacket");
    private final static QName _SignatureMethodHMACOutputLength_QNAME = new QName("http://www.w3.org/2000/09/xmldsig#", "HMACOutputLength");
    private final static QName _SPKIDataSPKISexp_QNAME = new QName("http://www.w3.org/2000/09/xmldsig#", "SPKISexp");
    private final static QName _TransformXPath_QNAME = new QName("http://www.w3.org/2000/09/xmldsig#", "XPath");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.w3._2000._09.xmldsig_
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link org.w3._2000._09.xmldsig.X509Data }
     * 
     */
    public org.w3._2000._09.xmldsig.X509Data createX509Data() {
        return new org.w3._2000._09.xmldsig.X509Data();
    }

    /**
     * Create an instance of {@link Transforms }
     * 
     */
    public Transforms createTransforms() {
        return new Transforms();
    }

    /**
     * Create an instance of {@link RetrievalMethod }
     * 
     */
    public RetrievalMethod createRetrievalMethod() {
        return new RetrievalMethod();
    }

    /**
     * Create an instance of {@link org.w3._2000._09.xmldsig.DigestMethod }
     * 
     */
    public org.w3._2000._09.xmldsig.DigestMethod createDigestMethod() {
        return new org.w3._2000._09.xmldsig.DigestMethod();
    }

    /**
     * Create an instance of {@link Object }
     * 
     */
    public Object createObject() {
        return new Object();
    }

    /**
     * Create an instance of {@link Transform }
     * 
     */
    public Transform createTransform() {
        return new Transform();
    }

    /**
     * Create an instance of {@link org.w3._2000._09.xmldsig.SignatureProperty }
     * 
     */
    public org.w3._2000._09.xmldsig.SignatureProperty createSignatureProperty() {
        return new org.w3._2000._09.xmldsig.SignatureProperty();
    }

    /**
     * Create an instance of {@link SignatureMethod }
     * 
     */
    public SignatureMethod createSignatureMethod() {
        return new SignatureMethod();
    }

    /**
     * Create an instance of {@link org.w3._2000._09.xmldsig.SignatureValue }
     * 
     */
    public org.w3._2000._09.xmldsig.SignatureValue createSignatureValue() {
        return new org.w3._2000._09.xmldsig.SignatureValue();
    }

    /**
     * Create an instance of {@link org.w3._2000._09.xmldsig.SPKIData }
     * 
     */
    public org.w3._2000._09.xmldsig.SPKIData createSPKIData() {
        return new org.w3._2000._09.xmldsig.SPKIData();
    }

    /**
     * Create an instance of {@link org.w3._2000._09.xmldsig.SignedInfo }
     * 
     */
    public org.w3._2000._09.xmldsig.SignedInfo createSignedInfo() {
        return new org.w3._2000._09.xmldsig.SignedInfo();
    }

    /**
     * Create an instance of {@link org.w3._2000._09.xmldsig.KeyInfo }
     * 
     */
    public org.w3._2000._09.xmldsig.KeyInfo createKeyInfo() {
        return new org.w3._2000._09.xmldsig.KeyInfo();
    }

    /**
     * Create an instance of {@link X509IssuerSerialType }
     * 
     */
    public X509IssuerSerialType createX509IssuerSerialType() {
        return new X509IssuerSerialType();
    }

    /**
     * Create an instance of {@link Manifest }
     * 
     */
    public Manifest createManifest() {
        return new Manifest();
    }

    /**
     * Create an instance of {@link org.w3._2000._09.xmldsig.CanonicalizationMethod }
     * 
     */
    public org.w3._2000._09.xmldsig.CanonicalizationMethod createCanonicalizationMethod() {
        return new org.w3._2000._09.xmldsig.CanonicalizationMethod();
    }

    /**
     * Create an instance of {@link DSAKeyValue }
     * 
     */
    public DSAKeyValue createDSAKeyValue() {
        return new DSAKeyValue();
    }

    /**
     * Create an instance of {@link org.w3._2000._09.xmldsig.Reference }
     * 
     */
    public org.w3._2000._09.xmldsig.Reference createReference() {
        return new org.w3._2000._09.xmldsig.Reference();
    }

    /**
     * Create an instance of {@link org.w3._2000._09.xmldsig.KeyValue }
     * 
     */
    public org.w3._2000._09.xmldsig.KeyValue createKeyValue() {
        return new org.w3._2000._09.xmldsig.KeyValue();
    }

    /**
     * Create an instance of {@link org.w3._2000._09.xmldsig.PGPData }
     * 
     */
    public org.w3._2000._09.xmldsig.PGPData createPGPData() {
        return new org.w3._2000._09.xmldsig.PGPData();
    }

    /**
     * Create an instance of {@link org.w3._2000._09.xmldsig.RSAKeyValue }
     * 
     */
    public org.w3._2000._09.xmldsig.RSAKeyValue createRSAKeyValue() {
        return new org.w3._2000._09.xmldsig.RSAKeyValue();
    }

    /**
     * Create an instance of {@link org.w3._2000._09.xmldsig.SignatureProperties }
     * 
     */
    public org.w3._2000._09.xmldsig.SignatureProperties createSignatureProperties() {
        return new org.w3._2000._09.xmldsig.SignatureProperties();
    }

    /**
     * Create an instance of {@link Signature }
     * 
     */
    public Signature createSignature() {
        return new Signature();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link org.w3._2000._09.xmldsig.KeyInfo }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.w3.org/2000/09/xmldsig#", name = "KeyInfo")
    public JAXBElement<org.w3._2000._09.xmldsig.KeyInfo> createKeyInfo(org.w3._2000._09.xmldsig.KeyInfo value) {
        return new JAXBElement<org.w3._2000._09.xmldsig.KeyInfo>(_KeyInfo_QNAME, org.w3._2000._09.xmldsig.KeyInfo.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link org.w3._2000._09.xmldsig.SignatureProperty }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.w3.org/2000/09/xmldsig#", name = "SignatureProperty")
    public JAXBElement<org.w3._2000._09.xmldsig.SignatureProperty> createSignatureProperty(org.w3._2000._09.xmldsig.SignatureProperty value) {
        return new JAXBElement<org.w3._2000._09.xmldsig.SignatureProperty>(_SignatureProperty_QNAME, org.w3._2000._09.xmldsig.SignatureProperty.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link org.w3._2000._09.xmldsig.RSAKeyValue }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.w3.org/2000/09/xmldsig#", name = "RSAKeyValue")
    public JAXBElement<org.w3._2000._09.xmldsig.RSAKeyValue> createRSAKeyValue(org.w3._2000._09.xmldsig.RSAKeyValue value) {
        return new JAXBElement<org.w3._2000._09.xmldsig.RSAKeyValue>(_RSAKeyValue_QNAME, org.w3._2000._09.xmldsig.RSAKeyValue.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SignatureMethod }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.w3.org/2000/09/xmldsig#", name = "SignatureMethod")
    public JAXBElement<SignatureMethod> createSignatureMethod(SignatureMethod value) {
        return new JAXBElement<SignatureMethod>(_SignatureMethod_QNAME, SignatureMethod.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.w3.org/2000/09/xmldsig#", name = "Object")
    public JAXBElement<Object> createObject(Object value) {
        return new JAXBElement<Object>(_Object_QNAME, Object.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link org.w3._2000._09.xmldsig.PGPData }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.w3.org/2000/09/xmldsig#", name = "PGPData")
    public JAXBElement<org.w3._2000._09.xmldsig.PGPData> createPGPData(org.w3._2000._09.xmldsig.PGPData value) {
        return new JAXBElement<org.w3._2000._09.xmldsig.PGPData>(_PGPData_QNAME, org.w3._2000._09.xmldsig.PGPData.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RetrievalMethod }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.w3.org/2000/09/xmldsig#", name = "RetrievalMethod")
    public JAXBElement<RetrievalMethod> createRetrievalMethod(RetrievalMethod value) {
        return new JAXBElement<RetrievalMethod>(_RetrievalMethod_QNAME, RetrievalMethod.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DSAKeyValue }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.w3.org/2000/09/xmldsig#", name = "DSAKeyValue")
    public JAXBElement<DSAKeyValue> createDSAKeyValue(DSAKeyValue value) {
        return new JAXBElement<DSAKeyValue>(_DSAKeyValue_QNAME, DSAKeyValue.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link org.w3._2000._09.xmldsig.SPKIData }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.w3.org/2000/09/xmldsig#", name = "SPKIData")
    public JAXBElement<org.w3._2000._09.xmldsig.SPKIData> createSPKIData(org.w3._2000._09.xmldsig.SPKIData value) {
        return new JAXBElement<org.w3._2000._09.xmldsig.SPKIData>(_SPKIData_QNAME, org.w3._2000._09.xmldsig.SPKIData.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link org.w3._2000._09.xmldsig.SignatureValue }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.w3.org/2000/09/xmldsig#", name = "SignatureValue")
    public JAXBElement<org.w3._2000._09.xmldsig.SignatureValue> createSignatureValue(org.w3._2000._09.xmldsig.SignatureValue value) {
        return new JAXBElement<org.w3._2000._09.xmldsig.SignatureValue>(_SignatureValue_QNAME, org.w3._2000._09.xmldsig.SignatureValue.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link org.w3._2000._09.xmldsig.KeyValue }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.w3.org/2000/09/xmldsig#", name = "KeyValue")
    public JAXBElement<org.w3._2000._09.xmldsig.KeyValue> createKeyValue(org.w3._2000._09.xmldsig.KeyValue value) {
        return new JAXBElement<org.w3._2000._09.xmldsig.KeyValue>(_KeyValue_QNAME, org.w3._2000._09.xmldsig.KeyValue.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Transforms }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.w3.org/2000/09/xmldsig#", name = "Transforms")
    public JAXBElement<Transforms> createTransforms(Transforms value) {
        return new JAXBElement<Transforms>(_Transforms_QNAME, Transforms.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link org.w3._2000._09.xmldsig.DigestMethod }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.w3.org/2000/09/xmldsig#", name = "DigestMethod")
    public JAXBElement<org.w3._2000._09.xmldsig.DigestMethod> createDigestMethod(org.w3._2000._09.xmldsig.DigestMethod value) {
        return new JAXBElement<org.w3._2000._09.xmldsig.DigestMethod>(_DigestMethod_QNAME, org.w3._2000._09.xmldsig.DigestMethod.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link org.w3._2000._09.xmldsig.X509Data }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.w3.org/2000/09/xmldsig#", name = "X509Data")
    public JAXBElement<org.w3._2000._09.xmldsig.X509Data> createX509Data(org.w3._2000._09.xmldsig.X509Data value) {
        return new JAXBElement<org.w3._2000._09.xmldsig.X509Data>(_X509Data_QNAME, org.w3._2000._09.xmldsig.X509Data.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.w3.org/2000/09/xmldsig#", name = "KeyName")
    public JAXBElement<String> createKeyName(String value) {
        return new JAXBElement<String>(_KeyName_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Signature }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.w3.org/2000/09/xmldsig#", name = "Signature")
    public JAXBElement<Signature> createSignature(Signature value) {
        return new JAXBElement<Signature>(_Signature_QNAME, Signature.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.w3.org/2000/09/xmldsig#", name = "MgmtData")
    public JAXBElement<String> createMgmtData(String value) {
        return new JAXBElement<String>(_MgmtData_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link org.w3._2000._09.xmldsig.SignatureProperties }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.w3.org/2000/09/xmldsig#", name = "SignatureProperties")
    public JAXBElement<org.w3._2000._09.xmldsig.SignatureProperties> createSignatureProperties(org.w3._2000._09.xmldsig.SignatureProperties value) {
        return new JAXBElement<org.w3._2000._09.xmldsig.SignatureProperties>(_SignatureProperties_QNAME, org.w3._2000._09.xmldsig.SignatureProperties.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Transform }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.w3.org/2000/09/xmldsig#", name = "Transform")
    public JAXBElement<Transform> createTransform(Transform value) {
        return new JAXBElement<Transform>(_Transform_QNAME, Transform.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link org.w3._2000._09.xmldsig.Reference }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.w3.org/2000/09/xmldsig#", name = "Reference")
    public JAXBElement<org.w3._2000._09.xmldsig.Reference> createReference(org.w3._2000._09.xmldsig.Reference value) {
        return new JAXBElement<org.w3._2000._09.xmldsig.Reference>(_Reference_QNAME, org.w3._2000._09.xmldsig.Reference.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link byte[]}{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.w3.org/2000/09/xmldsig#", name = "DigestValue")
    public JAXBElement<byte[]> createDigestValue(byte[] value) {
        return new JAXBElement<byte[]>(_DigestValue_QNAME, byte[].class, null, ((byte[]) value));
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link org.w3._2000._09.xmldsig.CanonicalizationMethod }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.w3.org/2000/09/xmldsig#", name = "CanonicalizationMethod")
    public JAXBElement<org.w3._2000._09.xmldsig.CanonicalizationMethod> createCanonicalizationMethod(org.w3._2000._09.xmldsig.CanonicalizationMethod value) {
        return new JAXBElement<org.w3._2000._09.xmldsig.CanonicalizationMethod>(_CanonicalizationMethod_QNAME, org.w3._2000._09.xmldsig.CanonicalizationMethod.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link org.w3._2000._09.xmldsig.SignedInfo }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.w3.org/2000/09/xmldsig#", name = "SignedInfo")
    public JAXBElement<org.w3._2000._09.xmldsig.SignedInfo> createSignedInfo(org.w3._2000._09.xmldsig.SignedInfo value) {
        return new JAXBElement<org.w3._2000._09.xmldsig.SignedInfo>(_SignedInfo_QNAME, org.w3._2000._09.xmldsig.SignedInfo.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Manifest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.w3.org/2000/09/xmldsig#", name = "Manifest")
    public JAXBElement<Manifest> createManifest(Manifest value) {
        return new JAXBElement<Manifest>(_Manifest_QNAME, Manifest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link byte[]}{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.w3.org/2000/09/xmldsig#", name = "X509CRL", scope = org.w3._2000._09.xmldsig.X509Data.class)
    public JAXBElement<byte[]> createX509DataX509CRL(byte[] value) {
        return new JAXBElement<byte[]>(_X509DataX509CRL_QNAME, byte[].class, org.w3._2000._09.xmldsig.X509Data.class, ((byte[]) value));
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link X509IssuerSerialType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.w3.org/2000/09/xmldsig#", name = "X509IssuerSerial", scope = org.w3._2000._09.xmldsig.X509Data.class)
    public JAXBElement<X509IssuerSerialType> createX509DataX509IssuerSerial(X509IssuerSerialType value) {
        return new JAXBElement<X509IssuerSerialType>(_X509DataX509IssuerSerial_QNAME, X509IssuerSerialType.class, org.w3._2000._09.xmldsig.X509Data.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.w3.org/2000/09/xmldsig#", name = "X509SubjectName", scope = org.w3._2000._09.xmldsig.X509Data.class)
    public JAXBElement<String> createX509DataX509SubjectName(String value) {
        return new JAXBElement<String>(_X509DataX509SubjectName_QNAME, String.class, org.w3._2000._09.xmldsig.X509Data.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link byte[]}{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.w3.org/2000/09/xmldsig#", name = "X509SKI", scope = org.w3._2000._09.xmldsig.X509Data.class)
    public JAXBElement<byte[]> createX509DataX509SKI(byte[] value) {
        return new JAXBElement<byte[]>(_X509DataX509SKI_QNAME, byte[].class, org.w3._2000._09.xmldsig.X509Data.class, ((byte[]) value));
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link byte[]}{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.w3.org/2000/09/xmldsig#", name = "X509Certificate", scope = org.w3._2000._09.xmldsig.X509Data.class)
    public JAXBElement<byte[]> createX509DataX509Certificate(byte[] value) {
        return new JAXBElement<byte[]>(_X509DataX509Certificate_QNAME, byte[].class, org.w3._2000._09.xmldsig.X509Data.class, ((byte[]) value));
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link byte[]}{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.w3.org/2000/09/xmldsig#", name = "PGPKeyID", scope = org.w3._2000._09.xmldsig.PGPData.class)
    public JAXBElement<byte[]> createPGPDataPGPKeyID(byte[] value) {
        return new JAXBElement<byte[]>(_PGPDataPGPKeyID_QNAME, byte[].class, org.w3._2000._09.xmldsig.PGPData.class, ((byte[]) value));
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link byte[]}{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.w3.org/2000/09/xmldsig#", name = "PGPKeyPacket", scope = org.w3._2000._09.xmldsig.PGPData.class)
    public JAXBElement<byte[]> createPGPDataPGPKeyPacket(byte[] value) {
        return new JAXBElement<byte[]>(_PGPDataPGPKeyPacket_QNAME, byte[].class, org.w3._2000._09.xmldsig.PGPData.class, ((byte[]) value));
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigInteger }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.w3.org/2000/09/xmldsig#", name = "HMACOutputLength", scope = SignatureMethod.class)
    public JAXBElement<BigInteger> createSignatureMethodHMACOutputLength(BigInteger value) {
        return new JAXBElement<BigInteger>(_SignatureMethodHMACOutputLength_QNAME, BigInteger.class, SignatureMethod.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link byte[]}{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.w3.org/2000/09/xmldsig#", name = "SPKISexp", scope = org.w3._2000._09.xmldsig.SPKIData.class)
    public JAXBElement<byte[]> createSPKIDataSPKISexp(byte[] value) {
        return new JAXBElement<byte[]>(_SPKIDataSPKISexp_QNAME, byte[].class, org.w3._2000._09.xmldsig.SPKIData.class, ((byte[]) value));
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.w3.org/2000/09/xmldsig#", name = "XPath", scope = Transform.class)
    public JAXBElement<String> createTransformXPath(String value) {
        return new JAXBElement<String>(_TransformXPath_QNAME, String.class, Transform.class, value);
    }

}
