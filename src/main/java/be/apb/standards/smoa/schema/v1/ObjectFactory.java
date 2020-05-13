package be.apb.standards.smoa.schema.v1;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

@XmlRegistry
public class ObjectFactory {
   private static final QName _AbstractFolder_QNAME = new QName("http://www.apb.be/standards/smoa/schema/v1", "abstract-Folder");
   private static final QName _EventFolder_QNAME = new QName("http://www.apb.be/standards/smoa/schema/v1", "eventFolder");

   public MedicationSchemeTimestampsResponse createMedicationSchemeTimestampsResponse() {
      return new MedicationSchemeTimestampsResponse();
   }

   public PharmaceuticalCareEventType createPharmaceuticalCareEventType() {
      return new PharmaceuticalCareEventType();
   }

   public PharmaceuticalCareEventType.DispensedForSamePrescription createPharmaceuticalCareEventTypeDispensedForSamePrescription() {
      return new PharmaceuticalCareEventType.DispensedForSamePrescription();
   }

   public RegisterExportEventType createRegisterExportEventType() {
      return new RegisterExportEventType();
   }

   public EventFolderType createEventFolderType() {
      return new EventFolderType();
   }

   public SingleMessage createSingleMessage() {
      return new SingleMessage();
   }

   public SmoaMessageType createSmoaMessageType() {
      return new SmoaMessageType();
   }

   public DigitalSignedSmoaMessageType createDigitalSignedSmoaMessageType() {
      return new DigitalSignedSmoaMessageType();
   }

   public FileType createFileType() {
      return new FileType();
   }

   public MetaDataListType createMetaDataListType() {
      return new MetaDataListType();
   }

   public InfoListType createInfoListType() {
      return new InfoListType();
   }

   public MetaDataType createMetaDataType() {
      return new MetaDataType();
   }

   public DurationType createDurationType() {
      return new DurationType();
   }

   public QuantityType createQuantityType() {
      return new QuantityType();
   }

   public TextType createTextType() {
      return new TextType();
   }

   public MomentType createMomentType() {
      return new MomentType();
   }

   public DeliveriesType createDeliveriesType() {
      return new DeliveriesType();
   }

   public Error createError() {
      return new Error();
   }

   public Status createStatus() {
      return new Status();
   }

   public PaginationInfo createPaginationInfo() {
      return new PaginationInfo();
   }

   public Organization createOrganization() {
      return new Organization();
   }

   public PersonIdentifier createPersonIdentifier() {
      return new PersonIdentifier();
   }

   public Person createPerson() {
      return new Person();
   }

   public Author createAuthor() {
      return new Author();
   }

   public AbstractDataEntry createAbstractDataEntry() {
      return new AbstractDataEntry();
   }

   public DataEntryRequest createDataEntryRequest() {
      return new DataEntryRequest();
   }

   public DataEntryResponse createDataEntryResponse() {
      return new DataEntryResponse();
   }

   public MedicationSchemeResponse createMedicationSchemeResponse() {
      return new MedicationSchemeResponse();
   }

   public ArchivePrescriptionEventType createArchivePrescriptionEventType() {
      return new ArchivePrescriptionEventType();
   }

   public ID createID() {
      return new ID();
   }

   public ArchivePrescriptionCommentEventType createArchivePrescriptionCommentEventType() {
      return new ArchivePrescriptionCommentEventType();
   }

   public MedicationDeliveryEventType createMedicationDeliveryEventType() {
      return new MedicationDeliveryEventType();
   }

   public BvacEventType createBvacEventType() {
      return new BvacEventType();
   }

   public MedicationHistoryEvent createMedicationHistoryEvent() {
      return new MedicationHistoryEvent();
   }

   public ContinuedPharmaceuticalCareDossierEvent createContinuedPharmaceuticalCareDossierEvent() {
      return new ContinuedPharmaceuticalCareDossierEvent();
   }

   public ContinuedPharmaceuticalCareEntity createContinuedPharmaceuticalCareEntity() {
      return new ContinuedPharmaceuticalCareEntity();
   }

   public ContinuedPharmaceuticalCareResponse createContinuedPharmaceuticalCareResponse() {
      return new ContinuedPharmaceuticalCareResponse();
   }

   public AbstractMedicationSchemeResponse createAbstractMedicationSchemeResponse() {
      return new AbstractMedicationSchemeResponse();
   }

   public MedicationSchemeEntriesResponse createMedicationSchemeEntriesResponse() {
      return new MedicationSchemeEntriesResponse();
   }

   public MedicationSchemeEntriesRequest createMedicationSchemeEntriesRequest() {
      return new MedicationSchemeEntriesRequest();
   }

   public DigitalSignatureType createDigitalSignatureType() {
      return new DigitalSignatureType();
   }

   public HeaderType createHeaderType() {
      return new HeaderType();
   }

   public SenderType createSenderType() {
      return new SenderType();
   }

   public MedicationSchemeTimestampsResponse.SubjectTimestamp createMedicationSchemeTimestampsResponseSubjectTimestamp() {
      return new MedicationSchemeTimestampsResponse.SubjectTimestamp();
   }

   public PharmaceuticalCareEventType.DispensedWithoutPrescription createPharmaceuticalCareEventTypeDispensedWithoutPrescription() {
      return new PharmaceuticalCareEventType.DispensedWithoutPrescription();
   }

   public PharmaceuticalCareEventType.PharmaceuticalCareActivities createPharmaceuticalCareEventTypePharmaceuticalCareActivities() {
      return new PharmaceuticalCareEventType.PharmaceuticalCareActivities();
   }

   public PharmaceuticalCareEventType.DispensedForSamePrescription.Product createPharmaceuticalCareEventTypeDispensedForSamePrescriptionProduct() {
      return new PharmaceuticalCareEventType.DispensedForSamePrescription.Product();
   }

   public RegisterExportEventType.Incoming createRegisterExportEventTypeIncoming() {
      return new RegisterExportEventType.Incoming();
   }

   public EventFolderType.Events createEventFolderTypeEvents() {
      return new EventFolderType.Events();
   }

   public EventFolderType.EntitySpace createEventFolderTypeEntitySpace() {
      return new EventFolderType.EntitySpace();
   }

   @XmlElementDecl(
           namespace = "http://www.apb.be/standards/smoa/schema/v1",
           name = "abstract-Folder"
   )
   public JAXBElement<AbstractFolderType> createAbstractFolder(AbstractFolderType value) {
      return new JAXBElement(_AbstractFolder_QNAME, AbstractFolderType.class, (Class)null, value);
   }

   @XmlElementDecl(
           namespace = "http://www.apb.be/standards/smoa/schema/v1",
           name = "eventFolder",
           substitutionHeadNamespace = "http://www.apb.be/standards/smoa/schema/v1",
           substitutionHeadName = "abstract-Folder"
   )
   public JAXBElement<EventFolderType> createEventFolder(EventFolderType value) {
      return new JAXBElement(_EventFolder_QNAME, EventFolderType.class, (Class)null, value);
   }
}
