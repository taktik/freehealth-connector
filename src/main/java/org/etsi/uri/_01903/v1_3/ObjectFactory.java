package org.etsi.uri._01903.v1_3;

import org.taktik.connector.technical.adapter.XmlDateTimeAdapter;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.namespace.QName;
import org.joda.time.DateTime;

@XmlRegistry
public class ObjectFactory {
   private static final QName _XAdESTimeStamp_QNAME = new QName("http://uri.etsi.org/01903/v1.3.2#", "XAdESTimeStamp");
   private static final QName _SigningTime_QNAME = new QName("http://uri.etsi.org/01903/v1.3.2#", "SigningTime");
   private static final QName _SPURI_QNAME = new QName("http://uri.etsi.org/01903/v1.3.2#", "SPURI");
   private static final QName _AllDataObjectsTimeStamp_QNAME = new QName("http://uri.etsi.org/01903/v1.3.2#", "AllDataObjectsTimeStamp");
   private static final QName _IndividualDataObjectsTimeStamp_QNAME = new QName("http://uri.etsi.org/01903/v1.3.2#", "IndividualDataObjectsTimeStamp");
   private static final QName _SignatureTimeStamp_QNAME = new QName("http://uri.etsi.org/01903/v1.3.2#", "SignatureTimeStamp");
   private static final QName _CompleteCertificateRefs_QNAME = new QName("http://uri.etsi.org/01903/v1.3.2#", "CompleteCertificateRefs");
   private static final QName _CompleteRevocationRefs_QNAME = new QName("http://uri.etsi.org/01903/v1.3.2#", "CompleteRevocationRefs");
   private static final QName _AttributeCertificateRefs_QNAME = new QName("http://uri.etsi.org/01903/v1.3.2#", "AttributeCertificateRefs");
   private static final QName _AttributeRevocationRefs_QNAME = new QName("http://uri.etsi.org/01903/v1.3.2#", "AttributeRevocationRefs");
   private static final QName _SigAndRefsTimeStamp_QNAME = new QName("http://uri.etsi.org/01903/v1.3.2#", "SigAndRefsTimeStamp");
   private static final QName _RefsOnlyTimeStamp_QNAME = new QName("http://uri.etsi.org/01903/v1.3.2#", "RefsOnlyTimeStamp");
   private static final QName _CertificateValues_QNAME = new QName("http://uri.etsi.org/01903/v1.3.2#", "CertificateValues");
   private static final QName _RevocationValues_QNAME = new QName("http://uri.etsi.org/01903/v1.3.2#", "RevocationValues");
   private static final QName _AttrAuthoritiesCertValues_QNAME = new QName("http://uri.etsi.org/01903/v1.3.2#", "AttrAuthoritiesCertValues");
   private static final QName _AttributeRevocationValues_QNAME = new QName("http://uri.etsi.org/01903/v1.3.2#", "AttributeRevocationValues");
   private static final QName _ArchiveTimeStamp_QNAME = new QName("http://uri.etsi.org/01903/v1.3.2#", "ArchiveTimeStamp");

   public Any createAny() {
      return new Any();
   }

   public ObjectIdentifier createObjectIdentifier() {
      return new ObjectIdentifier();
   }

   public IdentifierType createIdentifierType() {
      return new IdentifierType();
   }

   public DocumentationReferencesType createDocumentationReferencesType() {
      return new DocumentationReferencesType();
   }

   public EncapsulatedPKIData createEncapsulatedPKIData() {
      return new EncapsulatedPKIData();
   }

   public Include createInclude() {
      return new Include();
   }

   public ReferenceInfo createReferenceInfo() {
      return new ReferenceInfo();
   }

   public XAdESTimeStampType createXAdESTimeStampType() {
      return new XAdESTimeStampType();
   }

   public OtherTimeStamp createOtherTimeStamp() {
      return new OtherTimeStamp();
   }

   public QualifyingProperties createQualifyingProperties() {
      return new QualifyingProperties();
   }

   public SignedProperties createSignedProperties() {
      return new SignedProperties();
   }

   public UnsignedProperties createUnsignedProperties() {
      return new UnsignedProperties();
   }

   public SignedSignatureProperties createSignedSignatureProperties() {
      return new SignedSignatureProperties();
   }

   public SignedDataObjectProperties createSignedDataObjectProperties() {
      return new SignedDataObjectProperties();
   }

   public UnsignedSignatureProperties createUnsignedSignatureProperties() {
      return new UnsignedSignatureProperties();
   }

   public UnsignedDataObjectProperties createUnsignedDataObjectProperties() {
      return new UnsignedDataObjectProperties();
   }

   public SigningCertificate createSigningCertificate() {
      return new SigningCertificate();
   }

   public SignaturePolicyIdentifier createSignaturePolicyIdentifier() {
      return new SignaturePolicyIdentifier();
   }

   public SignatureProductionPlace createSignatureProductionPlace() {
      return new SignatureProductionPlace();
   }

   public SignerRole createSignerRole() {
      return new SignerRole();
   }

   public DataObjectFormat createDataObjectFormat() {
      return new DataObjectFormat();
   }

   public CommitmentTypeIndication createCommitmentTypeIndication() {
      return new CommitmentTypeIndication();
   }

   public CounterSignature createCounterSignature() {
      return new CounterSignature();
   }

   public CompleteCertificateRefsType createCompleteCertificateRefsType() {
      return new CompleteCertificateRefsType();
   }

   public CompleteRevocationRefsType createCompleteRevocationRefsType() {
      return new CompleteRevocationRefsType();
   }

   public CertificateValuesType createCertificateValuesType() {
      return new CertificateValuesType();
   }

   public RevocationValuesType createRevocationValuesType() {
      return new RevocationValuesType();
   }

   public QualifyingPropertiesReference createQualifyingPropertiesReference() {
      return new QualifyingPropertiesReference();
   }

   public CertIDType createCertIDType() {
      return new CertIDType();
   }

   public SignaturePolicyIdType createSignaturePolicyIdType() {
      return new SignaturePolicyIdType();
   }

   public SPUserNotice createSPUserNotice() {
      return new SPUserNotice();
   }

   public NoticeReferenceType createNoticeReferenceType() {
      return new NoticeReferenceType();
   }

   public CommitmentTypeQualifiersListType createCommitmentTypeQualifiersListType() {
      return new CommitmentTypeQualifiersListType();
   }

   public ClaimedRolesListType createClaimedRolesListType() {
      return new ClaimedRolesListType();
   }

   public CertifiedRolesListType createCertifiedRolesListType() {
      return new CertifiedRolesListType();
   }

   public DigestAlgAndValueType createDigestAlgAndValueType() {
      return new DigestAlgAndValueType();
   }

   public SigPolicyQualifiersListType createSigPolicyQualifiersListType() {
      return new SigPolicyQualifiersListType();
   }

   public IntegerListType createIntegerListType() {
      return new IntegerListType();
   }

   public CRLRefsType createCRLRefsType() {
      return new CRLRefsType();
   }

   public CRLRefType createCRLRefType() {
      return new CRLRefType();
   }

   public CRLIdentifierType createCRLIdentifierType() {
      return new CRLIdentifierType();
   }

   public OCSPRefsType createOCSPRefsType() {
      return new OCSPRefsType();
   }

   public OCSPRefType createOCSPRefType() {
      return new OCSPRefType();
   }

   public ResponderIDType createResponderIDType() {
      return new ResponderIDType();
   }

   public OCSPIdentifierType createOCSPIdentifierType() {
      return new OCSPIdentifierType();
   }

   public OtherCertStatusRefsType createOtherCertStatusRefsType() {
      return new OtherCertStatusRefsType();
   }

   public CRLValuesType createCRLValuesType() {
      return new CRLValuesType();
   }

   public OCSPValuesType createOCSPValuesType() {
      return new OCSPValuesType();
   }

   public OtherCertStatusValuesType createOtherCertStatusValuesType() {
      return new OtherCertStatusValuesType();
   }

   @XmlElementDecl(
      namespace = "http://uri.etsi.org/01903/v1.3.2#",
      name = "XAdESTimeStamp"
   )
   public JAXBElement<XAdESTimeStampType> createXAdESTimeStamp(XAdESTimeStampType value) {
      return new JAXBElement(_XAdESTimeStamp_QNAME, XAdESTimeStampType.class, (Class)null, value);
   }

   @XmlElementDecl(
      namespace = "http://uri.etsi.org/01903/v1.3.2#",
      name = "SigningTime"
   )
   @XmlJavaTypeAdapter(XmlDateTimeAdapter.class)
   public JAXBElement<DateTime> createSigningTime(DateTime value) {
      return new JAXBElement(_SigningTime_QNAME, DateTime.class, (Class)null, value);
   }

   @XmlElementDecl(
      namespace = "http://uri.etsi.org/01903/v1.3.2#",
      name = "SPURI"
   )
   public JAXBElement<String> createSPURI(String value) {
      return new JAXBElement(_SPURI_QNAME, String.class, (Class)null, value);
   }

   @XmlElementDecl(
      namespace = "http://uri.etsi.org/01903/v1.3.2#",
      name = "AllDataObjectsTimeStamp"
   )
   public JAXBElement<XAdESTimeStampType> createAllDataObjectsTimeStamp(XAdESTimeStampType value) {
      return new JAXBElement(_AllDataObjectsTimeStamp_QNAME, XAdESTimeStampType.class, (Class)null, value);
   }

   @XmlElementDecl(
      namespace = "http://uri.etsi.org/01903/v1.3.2#",
      name = "IndividualDataObjectsTimeStamp"
   )
   public JAXBElement<XAdESTimeStampType> createIndividualDataObjectsTimeStamp(XAdESTimeStampType value) {
      return new JAXBElement(_IndividualDataObjectsTimeStamp_QNAME, XAdESTimeStampType.class, (Class)null, value);
   }

   @XmlElementDecl(
      namespace = "http://uri.etsi.org/01903/v1.3.2#",
      name = "SignatureTimeStamp"
   )
   public JAXBElement<XAdESTimeStampType> createSignatureTimeStamp(XAdESTimeStampType value) {
      return new JAXBElement(_SignatureTimeStamp_QNAME, XAdESTimeStampType.class, (Class)null, value);
   }

   @XmlElementDecl(
      namespace = "http://uri.etsi.org/01903/v1.3.2#",
      name = "CompleteCertificateRefs"
   )
   public JAXBElement<CompleteCertificateRefsType> createCompleteCertificateRefs(CompleteCertificateRefsType value) {
      return new JAXBElement(_CompleteCertificateRefs_QNAME, CompleteCertificateRefsType.class, (Class)null, value);
   }

   @XmlElementDecl(
      namespace = "http://uri.etsi.org/01903/v1.3.2#",
      name = "CompleteRevocationRefs"
   )
   public JAXBElement<CompleteRevocationRefsType> createCompleteRevocationRefs(CompleteRevocationRefsType value) {
      return new JAXBElement(_CompleteRevocationRefs_QNAME, CompleteRevocationRefsType.class, (Class)null, value);
   }

   @XmlElementDecl(
      namespace = "http://uri.etsi.org/01903/v1.3.2#",
      name = "AttributeCertificateRefs"
   )
   public JAXBElement<CompleteCertificateRefsType> createAttributeCertificateRefs(CompleteCertificateRefsType value) {
      return new JAXBElement(_AttributeCertificateRefs_QNAME, CompleteCertificateRefsType.class, (Class)null, value);
   }

   @XmlElementDecl(
      namespace = "http://uri.etsi.org/01903/v1.3.2#",
      name = "AttributeRevocationRefs"
   )
   public JAXBElement<CompleteRevocationRefsType> createAttributeRevocationRefs(CompleteRevocationRefsType value) {
      return new JAXBElement(_AttributeRevocationRefs_QNAME, CompleteRevocationRefsType.class, (Class)null, value);
   }

   @XmlElementDecl(
      namespace = "http://uri.etsi.org/01903/v1.3.2#",
      name = "SigAndRefsTimeStamp"
   )
   public JAXBElement<XAdESTimeStampType> createSigAndRefsTimeStamp(XAdESTimeStampType value) {
      return new JAXBElement(_SigAndRefsTimeStamp_QNAME, XAdESTimeStampType.class, (Class)null, value);
   }

   @XmlElementDecl(
      namespace = "http://uri.etsi.org/01903/v1.3.2#",
      name = "RefsOnlyTimeStamp"
   )
   public JAXBElement<XAdESTimeStampType> createRefsOnlyTimeStamp(XAdESTimeStampType value) {
      return new JAXBElement(_RefsOnlyTimeStamp_QNAME, XAdESTimeStampType.class, (Class)null, value);
   }

   @XmlElementDecl(
      namespace = "http://uri.etsi.org/01903/v1.3.2#",
      name = "CertificateValues"
   )
   public JAXBElement<CertificateValuesType> createCertificateValues(CertificateValuesType value) {
      return new JAXBElement(_CertificateValues_QNAME, CertificateValuesType.class, (Class)null, value);
   }

   @XmlElementDecl(
      namespace = "http://uri.etsi.org/01903/v1.3.2#",
      name = "RevocationValues"
   )
   public JAXBElement<RevocationValuesType> createRevocationValues(RevocationValuesType value) {
      return new JAXBElement(_RevocationValues_QNAME, RevocationValuesType.class, (Class)null, value);
   }

   @XmlElementDecl(
      namespace = "http://uri.etsi.org/01903/v1.3.2#",
      name = "AttrAuthoritiesCertValues"
   )
   public JAXBElement<CertificateValuesType> createAttrAuthoritiesCertValues(CertificateValuesType value) {
      return new JAXBElement(_AttrAuthoritiesCertValues_QNAME, CertificateValuesType.class, (Class)null, value);
   }

   @XmlElementDecl(
      namespace = "http://uri.etsi.org/01903/v1.3.2#",
      name = "AttributeRevocationValues"
   )
   public JAXBElement<RevocationValuesType> createAttributeRevocationValues(RevocationValuesType value) {
      return new JAXBElement(_AttributeRevocationValues_QNAME, RevocationValuesType.class, (Class)null, value);
   }

   @XmlElementDecl(
      namespace = "http://uri.etsi.org/01903/v1.3.2#",
      name = "ArchiveTimeStamp"
   )
   public JAXBElement<XAdESTimeStampType> createArchiveTimeStamp(XAdESTimeStampType value) {
      return new JAXBElement(_ArchiveTimeStamp_QNAME, XAdESTimeStampType.class, (Class)null, value);
   }
}
