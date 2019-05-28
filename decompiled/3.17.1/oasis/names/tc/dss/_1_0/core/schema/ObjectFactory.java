package oasis.names.tc.dss._1_0.core.schema;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.namespace.QName;
import oasis.names.tc.saml._1_0.assertion.NameIdentifierType;

@XmlRegistry
public class ObjectFactory {
   private static final QName _Document_QNAME = new QName("urn:oasis:names:tc:dss:1.0:core:schema", "Document");
   private static final QName _OptionalInputs_QNAME = new QName("urn:oasis:names:tc:dss:1.0:core:schema", "OptionalInputs");
   private static final QName _OptionalOutputs_QNAME = new QName("urn:oasis:names:tc:dss:1.0:core:schema", "OptionalOutputs");
   private static final QName _ServicePolicy_QNAME = new QName("urn:oasis:names:tc:dss:1.0:core:schema", "ServicePolicy");
   private static final QName _Language_QNAME = new QName("urn:oasis:names:tc:dss:1.0:core:schema", "Language");
   private static final QName _AdditionalProfile_QNAME = new QName("urn:oasis:names:tc:dss:1.0:core:schema", "AdditionalProfile");
   private static final QName _Schema_QNAME = new QName("urn:oasis:names:tc:dss:1.0:core:schema", "Schema");
   private static final QName _Response_QNAME = new QName("urn:oasis:names:tc:dss:1.0:core:schema", "Response");
   private static final QName _SignatureType_QNAME = new QName("urn:oasis:names:tc:dss:1.0:core:schema", "SignatureType");
   private static final QName _AddTimestamp_QNAME = new QName("urn:oasis:names:tc:dss:1.0:core:schema", "AddTimestamp");
   private static final QName _VerifyResponse_QNAME = new QName("urn:oasis:names:tc:dss:1.0:core:schema", "VerifyResponse");
   private static final QName _UseVerificationTime_QNAME = new QName("urn:oasis:names:tc:dss:1.0:core:schema", "UseVerificationTime");
   private static final QName _ReturnVerificationTimeInfo_QNAME = new QName("urn:oasis:names:tc:dss:1.0:core:schema", "ReturnVerificationTimeInfo");
   private static final QName _ReturnProcessingDetails_QNAME = new QName("urn:oasis:names:tc:dss:1.0:core:schema", "ReturnProcessingDetails");
   private static final QName _ReturnSigningTimeInfo_QNAME = new QName("urn:oasis:names:tc:dss:1.0:core:schema", "ReturnSigningTimeInfo");
   private static final QName _ReturnSignerIdentity_QNAME = new QName("urn:oasis:names:tc:dss:1.0:core:schema", "ReturnSignerIdentity");
   private static final QName _SignerIdentity_QNAME = new QName("urn:oasis:names:tc:dss:1.0:core:schema", "SignerIdentity");
   private static final QName _UpdatedSignature_QNAME = new QName("urn:oasis:names:tc:dss:1.0:core:schema", "UpdatedSignature");
   private static final QName _ReturnTimestampedSignature_QNAME = new QName("urn:oasis:names:tc:dss:1.0:core:schema", "ReturnTimestampedSignature");
   private static final QName _TimestampedSignature_QNAME = new QName("urn:oasis:names:tc:dss:1.0:core:schema", "TimestampedSignature");

   public InputDocuments createInputDocuments() {
      return new InputDocuments();
   }

   public DocumentType createDocumentType() {
      return new DocumentType();
   }

   public TransformedData createTransformedData() {
      return new TransformedData();
   }

   public Base64Data createBase64Data() {
      return new Base64Data();
   }

   public DocumentHash createDocumentHash() {
      return new DocumentHash();
   }

   public AnyType createAnyType() {
      return new AnyType();
   }

   public SignatureObject createSignatureObject() {
      return new SignatureObject();
   }

   public SignaturePtr createSignaturePtr() {
      return new SignaturePtr();
   }

   public Base64Signature createBase64Signature() {
      return new Base64Signature();
   }

   public Timestamp createTimestamp() {
      return new Timestamp();
   }

   public Result createResult() {
      return new Result();
   }

   public InternationalStringType createInternationalStringType() {
      return new InternationalStringType();
   }

   public ClaimedIdentity createClaimedIdentity() {
      return new ClaimedIdentity();
   }

   public Schemas createSchemas() {
      return new Schemas();
   }

   public ResponseBaseType createResponseBaseType() {
      return new ResponseBaseType();
   }

   public SignRequest createSignRequest() {
      return new SignRequest();
   }

   public RequestBaseType createRequestBaseType() {
      return new RequestBaseType();
   }

   public SignResponse createSignResponse() {
      return new SignResponse();
   }

   public UpdateSignatureInstructionType createUpdateSignatureInstructionType() {
      return new UpdateSignatureInstructionType();
   }

   public IntendedAudience createIntendedAudience() {
      return new IntendedAudience();
   }

   public KeySelector createKeySelector() {
      return new KeySelector();
   }

   public Properties createProperties() {
      return new Properties();
   }

   public PropertiesType createPropertiesType() {
      return new PropertiesType();
   }

   public Property createProperty() {
      return new Property();
   }

   public IncludeObject createIncludeObject() {
      return new IncludeObject();
   }

   public SignaturePlacement createSignaturePlacement() {
      return new SignaturePlacement();
   }

   public DocumentWithSignature createDocumentWithSignature() {
      return new DocumentWithSignature();
   }

   public SignedReferences createSignedReferences() {
      return new SignedReferences();
   }

   public SignedReference createSignedReference() {
      return new SignedReference();
   }

   public VerifyRequest createVerifyRequest() {
      return new VerifyRequest();
   }

   public VerifyManifestResults createVerifyManifestResults() {
      return new VerifyManifestResults();
   }

   public ManifestResult createManifestResult() {
      return new ManifestResult();
   }

   public AdditionalTimeInfo createAdditionalTimeInfo() {
      return new AdditionalTimeInfo();
   }

   public VerificationTimeInfo createVerificationTimeInfo() {
      return new VerificationTimeInfo();
   }

   public AdditionalKeyInfo createAdditionalKeyInfo() {
      return new AdditionalKeyInfo();
   }

   public ProcessingDetails createProcessingDetails() {
      return new ProcessingDetails();
   }

   public DetailType createDetailType() {
      return new DetailType();
   }

   public SigningTimeInfo createSigningTimeInfo() {
      return new SigningTimeInfo();
   }

   public SigningTimeBoundaries createSigningTimeBoundaries() {
      return new SigningTimeBoundaries();
   }

   public ReturnUpdatedSignature createReturnUpdatedSignature() {
      return new ReturnUpdatedSignature();
   }

   public UpdatedSignatureType createUpdatedSignatureType() {
      return new UpdatedSignatureType();
   }

   public ReturnTransformedDocument createReturnTransformedDocument() {
      return new ReturnTransformedDocument();
   }

   public TransformedDocument createTransformedDocument() {
      return new TransformedDocument();
   }

   public TstInfo createTstInfo() {
      return new TstInfo();
   }

   public RequesterIdentity createRequesterIdentity() {
      return new RequesterIdentity();
   }

   public AttachmentReference createAttachmentReference() {
      return new AttachmentReference();
   }

   public InlineXMLType createInlineXMLType() {
      return new InlineXMLType();
   }

   public TimeSignatureInstructionType createTimeSignatureInstructionType() {
      return new TimeSignatureInstructionType();
   }

   public UseVerificationTimeType createUseVerificationTimeType() {
      return new UseVerificationTimeType();
   }

   @XmlElementDecl(
      namespace = "urn:oasis:names:tc:dss:1.0:core:schema",
      name = "Document"
   )
   public JAXBElement<DocumentType> createDocument(DocumentType value) {
      return new JAXBElement(_Document_QNAME, DocumentType.class, (Class)null, value);
   }

   @XmlElementDecl(
      namespace = "urn:oasis:names:tc:dss:1.0:core:schema",
      name = "OptionalInputs"
   )
   public JAXBElement<AnyType> createOptionalInputs(AnyType value) {
      return new JAXBElement(_OptionalInputs_QNAME, AnyType.class, (Class)null, value);
   }

   @XmlElementDecl(
      namespace = "urn:oasis:names:tc:dss:1.0:core:schema",
      name = "OptionalOutputs"
   )
   public JAXBElement<AnyType> createOptionalOutputs(AnyType value) {
      return new JAXBElement(_OptionalOutputs_QNAME, AnyType.class, (Class)null, value);
   }

   @XmlElementDecl(
      namespace = "urn:oasis:names:tc:dss:1.0:core:schema",
      name = "ServicePolicy"
   )
   public JAXBElement<String> createServicePolicy(String value) {
      return new JAXBElement(_ServicePolicy_QNAME, String.class, (Class)null, value);
   }

   @XmlElementDecl(
      namespace = "urn:oasis:names:tc:dss:1.0:core:schema",
      name = "Language"
   )
   @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
   public JAXBElement<String> createLanguage(String value) {
      return new JAXBElement(_Language_QNAME, String.class, (Class)null, value);
   }

   @XmlElementDecl(
      namespace = "urn:oasis:names:tc:dss:1.0:core:schema",
      name = "AdditionalProfile"
   )
   public JAXBElement<String> createAdditionalProfile(String value) {
      return new JAXBElement(_AdditionalProfile_QNAME, String.class, (Class)null, value);
   }

   @XmlElementDecl(
      namespace = "urn:oasis:names:tc:dss:1.0:core:schema",
      name = "Schema"
   )
   public JAXBElement<DocumentType> createSchema(DocumentType value) {
      return new JAXBElement(_Schema_QNAME, DocumentType.class, (Class)null, value);
   }

   @XmlElementDecl(
      namespace = "urn:oasis:names:tc:dss:1.0:core:schema",
      name = "Response"
   )
   public JAXBElement<ResponseBaseType> createResponse(ResponseBaseType value) {
      return new JAXBElement(_Response_QNAME, ResponseBaseType.class, (Class)null, value);
   }

   @XmlElementDecl(
      namespace = "urn:oasis:names:tc:dss:1.0:core:schema",
      name = "SignatureType"
   )
   public JAXBElement<String> createSignatureType(String value) {
      return new JAXBElement(_SignatureType_QNAME, String.class, (Class)null, value);
   }

   @XmlElementDecl(
      namespace = "urn:oasis:names:tc:dss:1.0:core:schema",
      name = "AddTimestamp"
   )
   public JAXBElement<UpdateSignatureInstructionType> createAddTimestamp(UpdateSignatureInstructionType value) {
      return new JAXBElement(_AddTimestamp_QNAME, UpdateSignatureInstructionType.class, (Class)null, value);
   }

   @XmlElementDecl(
      namespace = "urn:oasis:names:tc:dss:1.0:core:schema",
      name = "VerifyResponse"
   )
   public JAXBElement<ResponseBaseType> createVerifyResponse(ResponseBaseType value) {
      return new JAXBElement(_VerifyResponse_QNAME, ResponseBaseType.class, (Class)null, value);
   }

   @XmlElementDecl(
      namespace = "urn:oasis:names:tc:dss:1.0:core:schema",
      name = "UseVerificationTime"
   )
   public JAXBElement<Object> createUseVerificationTime(Object value) {
      return new JAXBElement(_UseVerificationTime_QNAME, Object.class, (Class)null, value);
   }

   @XmlElementDecl(
      namespace = "urn:oasis:names:tc:dss:1.0:core:schema",
      name = "ReturnVerificationTimeInfo"
   )
   public JAXBElement<Object> createReturnVerificationTimeInfo(Object value) {
      return new JAXBElement(_ReturnVerificationTimeInfo_QNAME, Object.class, (Class)null, value);
   }

   @XmlElementDecl(
      namespace = "urn:oasis:names:tc:dss:1.0:core:schema",
      name = "ReturnProcessingDetails"
   )
   public JAXBElement<Object> createReturnProcessingDetails(Object value) {
      return new JAXBElement(_ReturnProcessingDetails_QNAME, Object.class, (Class)null, value);
   }

   @XmlElementDecl(
      namespace = "urn:oasis:names:tc:dss:1.0:core:schema",
      name = "ReturnSigningTimeInfo"
   )
   public JAXBElement<Object> createReturnSigningTimeInfo(Object value) {
      return new JAXBElement(_ReturnSigningTimeInfo_QNAME, Object.class, (Class)null, value);
   }

   @XmlElementDecl(
      namespace = "urn:oasis:names:tc:dss:1.0:core:schema",
      name = "ReturnSignerIdentity"
   )
   public JAXBElement<Object> createReturnSignerIdentity(Object value) {
      return new JAXBElement(_ReturnSignerIdentity_QNAME, Object.class, (Class)null, value);
   }

   @XmlElementDecl(
      namespace = "urn:oasis:names:tc:dss:1.0:core:schema",
      name = "SignerIdentity"
   )
   public JAXBElement<NameIdentifierType> createSignerIdentity(NameIdentifierType value) {
      return new JAXBElement(_SignerIdentity_QNAME, NameIdentifierType.class, (Class)null, value);
   }

   @XmlElementDecl(
      namespace = "urn:oasis:names:tc:dss:1.0:core:schema",
      name = "UpdatedSignature"
   )
   public JAXBElement<UpdatedSignatureType> createUpdatedSignature(UpdatedSignatureType value) {
      return new JAXBElement(_UpdatedSignature_QNAME, UpdatedSignatureType.class, (Class)null, value);
   }

   @XmlElementDecl(
      namespace = "urn:oasis:names:tc:dss:1.0:core:schema",
      name = "ReturnTimestampedSignature"
   )
   public JAXBElement<UpdateSignatureInstructionType> createReturnTimestampedSignature(UpdateSignatureInstructionType value) {
      return new JAXBElement(_ReturnTimestampedSignature_QNAME, UpdateSignatureInstructionType.class, (Class)null, value);
   }

   @XmlElementDecl(
      namespace = "urn:oasis:names:tc:dss:1.0:core:schema",
      name = "TimestampedSignature"
   )
   public JAXBElement<UpdatedSignatureType> createTimestampedSignature(UpdatedSignatureType value) {
      return new JAXBElement(_TimestampedSignature_QNAME, UpdatedSignatureType.class, (Class)null, value);
   }
}
