package be.apb.standards.smoa.schema.model.v1;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

@XmlRegistry
public class ObjectFactory {
   private static final QName _AbstractEntity_QNAME = new QName("http://www.apb.be/standards/smoa/schema/model/v1", "abstract-Entity");
   private static final QName _AbstractTelecom_QNAME = new QName("http://www.apb.be/standards/smoa/schema/model/v1", "abstract-Telecom");
   private static final QName _AbstractPharmacy_QNAME = new QName("http://www.apb.be/standards/smoa/schema/model/v1", "abstract-Pharmacy");
   private static final QName _AbstractCareService_QNAME = new QName("http://www.apb.be/standards/smoa/schema/model/v1", "abstract-CareService");
   private static final QName _AbstractAddress_QNAME = new QName("http://www.apb.be/standards/smoa/schema/model/v1", "abstract-Address");
   private static final QName _AbstractPerson_QNAME = new QName("http://www.apb.be/standards/smoa/schema/model/v1", "abstract-Person");
   private static final QName _AbstractMedicinalProduct_QNAME = new QName("http://www.apb.be/standards/smoa/schema/model/v1", "abstract-MedicinalProduct");
   private static final QName _AbstractRawMaterial_QNAME = new QName("http://www.apb.be/standards/smoa/schema/model/v1", "abstract-RawMaterial");
   private static final QName _AbstractStatusMessage_QNAME = new QName("http://www.apb.be/standards/smoa/schema/model/v1", "abstract-StatusMessage");
   private static final QName _AbstractSubstanceProduct_QNAME = new QName("http://www.apb.be/standards/smoa/schema/model/v1", "abstract-SubstanceProduct");
   private static final QName _AbstractPreparation_QNAME = new QName("http://www.apb.be/standards/smoa/schema/model/v1", "abstract-Preparation");
   private static final QName _AbstractRecipeLine_QNAME = new QName("http://www.apb.be/standards/smoa/schema/model/v1", "abstract-RecipeLine");
   private static final QName _AbstractPosology_QNAME = new QName("http://www.apb.be/standards/smoa/schema/model/v1", "abstract-Posology");
   private static final QName _AbstractRegimen_QNAME = new QName("http://www.apb.be/standards/smoa/schema/model/v1", "abstract-Regimen");
   private static final QName _AbstractAttest_QNAME = new QName("http://www.apb.be/standards/smoa/schema/model/v1", "abstract-Attest");
   private static final QName _AbstractMagistralCertificate_QNAME = new QName("http://www.apb.be/standards/smoa/schema/model/v1", "abstract-MagistralCertificate");
   private static final QName _AbstractIncomingMedication_QNAME = new QName("http://www.apb.be/standards/smoa/schema/model/v1", "abstract-IncomingMedication");
   private static final QName _AbstractSupplier_QNAME = new QName("http://www.apb.be/standards/smoa/schema/model/v1", "abstract-Supplier");
   private static final QName _AbstractPatient_QNAME = new QName("http://www.apb.be/standards/smoa/schema/model/v1", "abstract-Patient");
   private static final QName _AbstractCareProvider_QNAME = new QName("http://www.apb.be/standards/smoa/schema/model/v1", "abstract-CareProvider");
   private static final QName _AbstractPharmacist_QNAME = new QName("http://www.apb.be/standards/smoa/schema/model/v1", "abstract-Pharmacist");
   private static final QName _AbstractDataLocation_QNAME = new QName("http://www.apb.be/standards/smoa/schema/model/v1", "abstract-DataLocation");
   private static final QName _AbstractDeliveredMedication_QNAME = new QName("http://www.apb.be/standards/smoa/schema/model/v1", "abstract-DeliveredMedication");
   private static final QName _AbstractPrescription_QNAME = new QName("http://www.apb.be/standards/smoa/schema/model/v1", "abstract-Prescription");
   private static final QName _AbstractVeterinaryPrescription_QNAME = new QName("http://www.apb.be/standards/smoa/schema/model/v1", "abstract-VeterinaryPrescription");
   private static final QName _AbstractHumanPrescription_QNAME = new QName("http://www.apb.be/standards/smoa/schema/model/v1", "abstract-HumanPrescription");
   private static final QName _AbstractMedicationConsumer_QNAME = new QName("http://www.apb.be/standards/smoa/schema/model/v1", "abstract-MedicationConsumer");
   private static final QName _Posology_QNAME = new QName("http://www.apb.be/standards/smoa/schema/model/v1", "posology");
   private static final QName _Regimen_QNAME = new QName("http://www.apb.be/standards/smoa/schema/model/v1", "regimen");
   private static final QName _MinPatient_QNAME = new QName("http://www.apb.be/standards/smoa/schema/model/v1", "min-Patient");
   private static final QName _MaxPatient_QNAME = new QName("http://www.apb.be/standards/smoa/schema/model/v1", "max-Patient");
   private static final QName _Identification_QNAME = new QName("http://www.apb.be/standards/smoa/schema/model/v1", "identification");
   private static final QName _ContactPerson_QNAME = new QName("http://www.apb.be/standards/smoa/schema/model/v1", "contactPerson");
   private static final QName _Hcparty_QNAME = new QName("http://www.apb.be/standards/smoa/schema/model/v1", "hcparty");
   private static final QName _MedicationHistoryEntity_QNAME = new QName("http://www.apb.be/standards/smoa/schema/model/v1", "medicationHistoryEntity");
   private static final QName _DeliveredMedicationHuman_QNAME = new QName("http://www.apb.be/standards/smoa/schema/model/v1", "deliveredMedicationHuman");
   private static final QName _StatusMessage_QNAME = new QName("http://www.apb.be/standards/smoa/schema/model/v1", "statusMessage");
   private static final QName _DataLocation_QNAME = new QName("http://www.apb.be/standards/smoa/schema/model/v1", "dataLocation");
   private static final QName _Attest_QNAME = new QName("http://www.apb.be/standards/smoa/schema/model/v1", "attest");
   private static final QName _MagistralCertificate_QNAME = new QName("http://www.apb.be/standards/smoa/schema/model/v1", "magistralCertificate");
   private static final QName _IncomingMedication_QNAME = new QName("http://www.apb.be/standards/smoa/schema/model/v1", "incomingMedication");
   private static final QName _MedicationConsumerPatient_QNAME = new QName("http://www.apb.be/standards/smoa/schema/model/v1", "medicationConsumerPatient");
   private static final QName _MedicationConsumerAnimalOwner_QNAME = new QName("http://www.apb.be/standards/smoa/schema/model/v1", "medicationConsumerAnimalOwner");
   private static final QName _MedicationConsumerUrgentieTrousse_QNAME = new QName("http://www.apb.be/standards/smoa/schema/model/v1", "medicationConsumerUrgentieTrousse");
   private static final QName _MedicationConsumerPatientGroup_QNAME = new QName("http://www.apb.be/standards/smoa/schema/model/v1", "medicationConsumerPatientGroup");
   private static final QName _DeliveredMedicationVeterinair_QNAME = new QName("http://www.apb.be/standards/smoa/schema/model/v1", "deliveredMedicationVeterinair");
   private static final QName _Person_QNAME = new QName("http://www.apb.be/standards/smoa/schema/model/v1", "person");
   private static final QName _MedicinalProduct_QNAME = new QName("http://www.apb.be/standards/smoa/schema/model/v1", "medicinalProduct");
   private static final QName _RawMaterial_QNAME = new QName("http://www.apb.be/standards/smoa/schema/model/v1", "rawMaterial");
   private static final QName _SubstanceProduct_QNAME = new QName("http://www.apb.be/standards/smoa/schema/model/v1", "substanceProduct");
   private static final QName _Preparation_QNAME = new QName("http://www.apb.be/standards/smoa/schema/model/v1", "preparation");
   private static final QName _VeterinaryPrescription_QNAME = new QName("http://www.apb.be/standards/smoa/schema/model/v1", "veterinaryPrescription");
   private static final QName _PatientPrescription_QNAME = new QName("http://www.apb.be/standards/smoa/schema/model/v1", "patientPrescription");
   private static final QName _SpecialPrescription_QNAME = new QName("http://www.apb.be/standards/smoa/schema/model/v1", "specialPrescription");
   private static final QName _DeferredPrescription_QNAME = new QName("http://www.apb.be/standards/smoa/schema/model/v1", "deferredPrescription");
   private static final QName _KmehrPrescription_QNAME = new QName("http://www.apb.be/standards/smoa/schema/model/v1", "kmehrPrescription");
   private static final QName _RecipeLine_QNAME = new QName("http://www.apb.be/standards/smoa/schema/model/v1", "recipeLine");
   private static final QName _RefPharmacy_QNAME = new QName("http://www.apb.be/standards/smoa/schema/model/v1", "refPharmacy");
   private static final QName _RefCareService_QNAME = new QName("http://www.apb.be/standards/smoa/schema/model/v1", "refCareService");
   private static final QName _RefPerson_QNAME = new QName("http://www.apb.be/standards/smoa/schema/model/v1", "refPerson");
   private static final QName _RefMedicinalProduct_QNAME = new QName("http://www.apb.be/standards/smoa/schema/model/v1", "refMedicinalProduct");
   private static final QName _RefPrescription_QNAME = new QName("http://www.apb.be/standards/smoa/schema/model/v1", "refPrescription");
   private static final QName _RefAttest_QNAME = new QName("http://www.apb.be/standards/smoa/schema/model/v1", "refAttest");
   private static final QName _RefMagistralCertificate_QNAME = new QName("http://www.apb.be/standards/smoa/schema/model/v1", "refMagistralCertificate");

   public MinSetProductType createMinSetProductType() {
      return new MinSetProductType();
   }

   public MaxSetProductType createMaxSetProductType() {
      return new MaxSetProductType();
   }

   public MaxSetProductType.PharmaceuticalCare createMaxSetProductTypePharmaceuticalCare() {
      return new MaxSetProductType.PharmaceuticalCare();
   }

   public AdministrationType createAdministrationType() {
      return new AdministrationType();
   }

   public CompoundType createCompoundType() {
      return new CompoundType();
   }

   public DeferredPrescriptionType createDeferredPrescriptionType() {
      return new DeferredPrescriptionType();
   }

   public VeterinaryPrescriptionType createVeterinaryPrescriptionType() {
      return new VeterinaryPrescriptionType();
   }

   public PersonType createPersonType() {
      return new PersonType();
   }

   public DataLocationType createDataLocationType() {
      return new DataLocationType();
   }

   public MaxSetPatient createMaxSetPatient() {
      return new MaxSetPatient();
   }

   public MaxSetPatient.Physiology createMaxSetPatientPhysiology() {
      return new MaxSetPatient.Physiology();
   }

   public MaxSetPatient.Physiology.ChronicPathologies createMaxSetPatientPhysiologyChronicPathologies() {
      return new MaxSetPatient.Physiology.ChronicPathologies();
   }

   public MaxSetPatient.Physiology.Condition createMaxSetPatientPhysiologyCondition() {
      return new MaxSetPatient.Physiology.Condition();
   }

   public MaxSetPatient.Physiology.Condition.Female createMaxSetPatientPhysiologyConditionFemale() {
      return new MaxSetPatient.Physiology.Condition.Female();
   }

   public PosologyType createPosologyType() {
      return new PosologyType();
   }

   public RegimenType createRegimenType() {
      return new RegimenType();
   }

   public MinSetPatient createMinSetPatient() {
      return new MinSetPatient();
   }

   public MaxSetPersonType createMaxSetPersonType() {
      return new MaxSetPersonType();
   }

   public MaxSetContactPersonType createMaxSetContactPersonType() {
      return new MaxSetContactPersonType();
   }

   public MaxSetCareProviderType createMaxSetCareProviderType() {
      return new MaxSetCareProviderType();
   }

   public MedicationHistoryType createMedicationHistoryType() {
      return new MedicationHistoryType();
   }

   public DeliveredMedicationHumanType createDeliveredMedicationHumanType() {
      return new DeliveredMedicationHumanType();
   }

   public StatusMessageType createStatusMessageType() {
      return new StatusMessageType();
   }

   public AttestType createAttestType() {
      return new AttestType();
   }

   public MagistralCertificateType createMagistralCertificateType() {
      return new MagistralCertificateType();
   }

   public IncomingMedicationType createIncomingMedicationType() {
      return new IncomingMedicationType();
   }

   public MedicationConsumerPatientType createMedicationConsumerPatientType() {
      return new MedicationConsumerPatientType();
   }

   public MedicationConsumerAnimalOwnerType createMedicationConsumerAnimalOwnerType() {
      return new MedicationConsumerAnimalOwnerType();
   }

   public MedicationConsumerUrgentieTrousseType createMedicationConsumerUrgentieTrousseType() {
      return new MedicationConsumerUrgentieTrousseType();
   }

   public MedicationConsumerPatientGroupType createMedicationConsumerPatientGroupType() {
      return new MedicationConsumerPatientGroupType();
   }

   public DeliveredMedicationVeterinairType createDeliveredMedicationVeterinairType() {
      return new DeliveredMedicationVeterinairType();
   }

   public MedicinalProduct createMedicinalProduct() {
      return new MedicinalProduct();
   }

   public RawMaterialType createRawMaterialType() {
      return new RawMaterialType();
   }

   public SubstanceProductType createSubstanceProductType() {
      return new SubstanceProductType();
   }

   public PreparationType createPreparationType() {
      return new PreparationType();
   }

   public PatientPrescriptionType createPatientPrescriptionType() {
      return new PatientPrescriptionType();
   }

   public SpecialPrescriptionType createSpecialPrescriptionType() {
      return new SpecialPrescriptionType();
   }

   public KmehrPrescriptionType createKmehrPrescriptionType() {
      return new KmehrPrescriptionType();
   }

   public RecipeLineType createRecipeLineType() {
      return new RecipeLineType();
   }

   public ReferencePharmacyType createReferencePharmacyType() {
      return new ReferencePharmacyType();
   }

   public ReferenceCareServiceType createReferenceCareServiceType() {
      return new ReferenceCareServiceType();
   }

   public ReferencePersonType createReferencePersonType() {
      return new ReferencePersonType();
   }

   public ReferenceMedicinalProductType createReferenceMedicinalProductType() {
      return new ReferenceMedicinalProductType();
   }

   public ReferencePrescriptionType createReferencePrescriptionType() {
      return new ReferencePrescriptionType();
   }

   public ReferenceAttestType createReferenceAttestType() {
      return new ReferenceAttestType();
   }

   public MagistralPreparationType createMagistralPreparationType() {
      return new MagistralPreparationType();
   }

   public FormularyReferenceType createFormularyReferenceType() {
      return new FormularyReferenceType();
   }

   public GalenicformType createGalenicformType() {
      return new GalenicformType();
   }

   public AdministrationInstructionsFrequencyType createAdministrationInstructionsFrequencyType() {
      return new AdministrationInstructionsFrequencyType();
   }

   public PosologyTakesType createPosologyTakesType() {
      return new PosologyTakesType();
   }

   public RegimenAdministrationQuantityType createRegimenAdministrationQuantityType() {
      return new RegimenAdministrationQuantityType();
   }

   public RegimenDayTimeType createRegimenDayTimeType() {
      return new RegimenDayTimeType();
   }

   public GenericExternalReference createGenericExternalReference() {
      return new GenericExternalReference();
   }

   public EuropeanAddressType createEuropeanAddressType() {
      return new EuropeanAddressType();
   }

   public GenericTelecomType createGenericTelecomType() {
      return new GenericTelecomType();
   }

   public PharmaceuticalInformationType createPharmaceuticalInformationType() {
      return new PharmaceuticalInformationType();
   }

   public EncryptedContentType createEncryptedContentType() {
      return new EncryptedContentType();
   }

   public EncryptedDataType createEncryptedDataType() {
      return new EncryptedDataType();
   }

   public DrugRelatedProblemType createDrugRelatedProblemType() {
      return new DrugRelatedProblemType();
   }

   public OriginatorType createOriginatorType() {
      return new OriginatorType();
   }

   public TargetType createTargetType() {
      return new TargetType();
   }

   public SubjectReferenceType createSubjectReferenceType() {
      return new SubjectReferenceType();
   }

   public MessageInformation createMessageInformation() {
      return new MessageInformation();
   }

   public ActiveSubstanceQuantityType createActiveSubstanceQuantityType() {
      return new ActiveSubstanceQuantityType();
   }

   public PreparationSubstanceType createPreparationSubstanceType() {
      return new PreparationSubstanceType();
   }

   public MagistralType createMagistralType() {
      return new MagistralType();
   }

   public RecipeLinesComplexType createRecipeLinesComplexType() {
      return new RecipeLinesComplexType();
   }

   public ReferenceMagistralCertificateType createReferenceMagistralCertificateType() {
      return new ReferenceMagistralCertificateType();
   }

   public HistoryProductType createHistoryProductType() {
      return new HistoryProductType();
   }

   public PharmacyList createPharmacyList() {
      return new PharmacyList();
   }

   public Pharmacy createPharmacy() {
      return new Pharmacy();
   }

   public Identification createIdentification() {
      return new Identification();
   }

   public Identity createIdentity() {
      return new Identity();
   }

   public BvacList createBvacList() {
      return new BvacList();
   }

   public BvacDocument createBvacDocument() {
      return new BvacDocument();
   }

   public IdentificationBvacDocument createIdentificationBvacDocument() {
      return new IdentificationBvacDocument();
   }

   public BarCode createBarCode() {
      return new BarCode();
   }

   public BvacPatient createBvacPatient() {
      return new BvacPatient();
   }

   public IdentificationBvacPatient createIdentificationBvacPatient() {
      return new IdentificationBvacPatient();
   }

   public IdentityBvac createIdentityBvac() {
      return new IdentityBvac();
   }

   public NhiData createNhiData() {
      return new NhiData();
   }

   public IdentificationBvacNhid createIdentificationBvacNhid() {
      return new IdentificationBvacNhid();
   }

   public InsuredStatus createInsuredStatus() {
      return new InsuredStatus();
   }

   public BvacDoctor createBvacDoctor() {
      return new BvacDoctor();
   }

   public IdentificationBvacDoctor createIdentificationBvacDoctor() {
      return new IdentificationBvacDoctor();
   }

   public ProductList createProductList() {
      return new ProductList();
   }

   public ProductBvac createProductBvac() {
      return new ProductBvac();
   }

   public IdentificationBvacProduct createIdentificationBvacProduct() {
      return new IdentificationBvacProduct();
   }

   public DescriptionBvacProduct createDescriptionBvacProduct() {
      return new DescriptionBvacProduct();
   }

   public AmountsBvacProduct createAmountsBvacProduct() {
      return new AmountsBvacProduct();
   }

   public TotalAmounts createTotalAmounts() {
      return new TotalAmounts();
   }

   public MinSetProductType.Description createMinSetProductTypeDescription() {
      return new MinSetProductType.Description();
   }

   public MinSetProductType.Dispensation createMinSetProductTypeDispensation() {
      return new MinSetProductType.Dispensation();
   }

   public MaxSetProductType.Motivation createMaxSetProductTypeMotivation() {
      return new MaxSetProductType.Motivation();
   }

   public MaxSetProductType.PharmaceuticalCare.ProductInformation createMaxSetProductTypePharmaceuticalCareProductInformation() {
      return new MaxSetProductType.PharmaceuticalCare.ProductInformation();
   }

   public AdministrationType.Temporality createAdministrationTypeTemporality() {
      return new AdministrationType.Temporality();
   }

   public AdministrationType.Site createAdministrationTypeSite() {
      return new AdministrationType.Site();
   }

   public CompoundType.Medicinalproduct createCompoundTypeMedicinalproduct() {
      return new CompoundType.Medicinalproduct();
   }

   public CompoundType.Substance createCompoundTypeSubstance() {
      return new CompoundType.Substance();
   }

   public DeferredPrescriptionType.OriginalPrescription createDeferredPrescriptionTypeOriginalPrescription() {
      return new DeferredPrescriptionType.OriginalPrescription();
   }

   public VeterinaryPrescriptionType.Responsible createVeterinaryPrescriptionTypeResponsible() {
      return new VeterinaryPrescriptionType.Responsible();
   }

   public PersonType.FirstNames createPersonTypeFirstNames() {
      return new PersonType.FirstNames();
   }

   public DataLocationType.Location createDataLocationTypeLocation() {
      return new DataLocationType.Location();
   }

   public MaxSetPatient.Contact createMaxSetPatientContact() {
      return new MaxSetPatient.Contact();
   }

   public MaxSetPatient.Entourage createMaxSetPatientEntourage() {
      return new MaxSetPatient.Entourage();
   }

   public MaxSetPatient.CareProviders createMaxSetPatientCareProviders() {
      return new MaxSetPatient.CareProviders();
   }

   public MaxSetPatient.Physiology.ChronicPathologies.HomeOxygenTherapy createMaxSetPatientPhysiologyChronicPathologiesHomeOxygenTherapy() {
      return new MaxSetPatient.Physiology.ChronicPathologies.HomeOxygenTherapy();
   }

   public MaxSetPatient.Physiology.Condition.Female.Pregnant createMaxSetPatientPhysiologyConditionFemalePregnant() {
      return new MaxSetPatient.Physiology.Condition.Female.Pregnant();
   }

   @XmlElementDecl(
      namespace = "http://www.apb.be/standards/smoa/schema/model/v1",
      name = "abstract-Entity"
   )
   public JAXBElement<AbstractEntityType> createAbstractEntity(AbstractEntityType value) {
      return new JAXBElement(_AbstractEntity_QNAME, AbstractEntityType.class, (Class)null, value);
   }

   @XmlElementDecl(
      namespace = "http://www.apb.be/standards/smoa/schema/model/v1",
      name = "abstract-Telecom"
   )
   public JAXBElement<AbstractTelecomType> createAbstractTelecom(AbstractTelecomType value) {
      return new JAXBElement(_AbstractTelecom_QNAME, AbstractTelecomType.class, (Class)null, value);
   }

   @XmlElementDecl(
      namespace = "http://www.apb.be/standards/smoa/schema/model/v1",
      name = "abstract-Pharmacy"
   )
   public JAXBElement<AbstractPharmacyType> createAbstractPharmacy(AbstractPharmacyType value) {
      return new JAXBElement(_AbstractPharmacy_QNAME, AbstractPharmacyType.class, (Class)null, value);
   }

   @XmlElementDecl(
      namespace = "http://www.apb.be/standards/smoa/schema/model/v1",
      name = "abstract-CareService"
   )
   public JAXBElement<AbstractCareServiceType> createAbstractCareService(AbstractCareServiceType value) {
      return new JAXBElement(_AbstractCareService_QNAME, AbstractCareServiceType.class, (Class)null, value);
   }

   @XmlElementDecl(
      namespace = "http://www.apb.be/standards/smoa/schema/model/v1",
      name = "abstract-Address"
   )
   public JAXBElement<AbstractAddressType> createAbstractAddress(AbstractAddressType value) {
      return new JAXBElement(_AbstractAddress_QNAME, AbstractAddressType.class, (Class)null, value);
   }

   @XmlElementDecl(
      namespace = "http://www.apb.be/standards/smoa/schema/model/v1",
      name = "abstract-Person"
   )
   public JAXBElement<AbstractPersonType> createAbstractPerson(AbstractPersonType value) {
      return new JAXBElement(_AbstractPerson_QNAME, AbstractPersonType.class, (Class)null, value);
   }

   @XmlElementDecl(
      namespace = "http://www.apb.be/standards/smoa/schema/model/v1",
      name = "abstract-MedicinalProduct"
   )
   public JAXBElement<AbstractMedicinalProductType> createAbstractMedicinalProduct(AbstractMedicinalProductType value) {
      return new JAXBElement(_AbstractMedicinalProduct_QNAME, AbstractMedicinalProductType.class, (Class)null, value);
   }

   @XmlElementDecl(
      namespace = "http://www.apb.be/standards/smoa/schema/model/v1",
      name = "abstract-RawMaterial"
   )
   public JAXBElement<AbstractRawMaterialType> createAbstractRawMaterial(AbstractRawMaterialType value) {
      return new JAXBElement(_AbstractRawMaterial_QNAME, AbstractRawMaterialType.class, (Class)null, value);
   }

   @XmlElementDecl(
      namespace = "http://www.apb.be/standards/smoa/schema/model/v1",
      name = "abstract-StatusMessage"
   )
   public JAXBElement<AbstractStatusMessageType> createAbstractStatusMessage(AbstractStatusMessageType value) {
      return new JAXBElement(_AbstractStatusMessage_QNAME, AbstractStatusMessageType.class, (Class)null, value);
   }

   @XmlElementDecl(
      namespace = "http://www.apb.be/standards/smoa/schema/model/v1",
      name = "abstract-SubstanceProduct"
   )
   public JAXBElement<AbstractSubstanceProductType> createAbstractSubstanceProduct(AbstractSubstanceProductType value) {
      return new JAXBElement(_AbstractSubstanceProduct_QNAME, AbstractSubstanceProductType.class, (Class)null, value);
   }

   @XmlElementDecl(
      namespace = "http://www.apb.be/standards/smoa/schema/model/v1",
      name = "abstract-Preparation"
   )
   public JAXBElement<AbstractPreparationType> createAbstractPreparation(AbstractPreparationType value) {
      return new JAXBElement(_AbstractPreparation_QNAME, AbstractPreparationType.class, (Class)null, value);
   }

   @XmlElementDecl(
      namespace = "http://www.apb.be/standards/smoa/schema/model/v1",
      name = "abstract-RecipeLine"
   )
   public JAXBElement<AbstractRecipeLineType> createAbstractRecipeLine(AbstractRecipeLineType value) {
      return new JAXBElement(_AbstractRecipeLine_QNAME, AbstractRecipeLineType.class, (Class)null, value);
   }

   @XmlElementDecl(
      namespace = "http://www.apb.be/standards/smoa/schema/model/v1",
      name = "abstract-Posology"
   )
   public JAXBElement<AbstractPosologyType> createAbstractPosology(AbstractPosologyType value) {
      return new JAXBElement(_AbstractPosology_QNAME, AbstractPosologyType.class, (Class)null, value);
   }

   @XmlElementDecl(
      namespace = "http://www.apb.be/standards/smoa/schema/model/v1",
      name = "abstract-Regimen"
   )
   public JAXBElement<AbstractRegimenType> createAbstractRegimen(AbstractRegimenType value) {
      return new JAXBElement(_AbstractRegimen_QNAME, AbstractRegimenType.class, (Class)null, value);
   }

   @XmlElementDecl(
      namespace = "http://www.apb.be/standards/smoa/schema/model/v1",
      name = "abstract-Attest"
   )
   public JAXBElement<AbstractAttestType> createAbstractAttest(AbstractAttestType value) {
      return new JAXBElement(_AbstractAttest_QNAME, AbstractAttestType.class, (Class)null, value);
   }

   @XmlElementDecl(
      namespace = "http://www.apb.be/standards/smoa/schema/model/v1",
      name = "abstract-MagistralCertificate"
   )
   public JAXBElement<AbstractMagistralCertificateType> createAbstractMagistralCertificate(AbstractMagistralCertificateType value) {
      return new JAXBElement(_AbstractMagistralCertificate_QNAME, AbstractMagistralCertificateType.class, (Class)null, value);
   }

   @XmlElementDecl(
      namespace = "http://www.apb.be/standards/smoa/schema/model/v1",
      name = "abstract-IncomingMedication"
   )
   public JAXBElement<AbstractIncomingMedicationType> createAbstractIncomingMedication(AbstractIncomingMedicationType value) {
      return new JAXBElement(_AbstractIncomingMedication_QNAME, AbstractIncomingMedicationType.class, (Class)null, value);
   }

   @XmlElementDecl(
      namespace = "http://www.apb.be/standards/smoa/schema/model/v1",
      name = "abstract-Supplier"
   )
   public JAXBElement<AbstractSupplierType> createAbstractSupplier(AbstractSupplierType value) {
      return new JAXBElement(_AbstractSupplier_QNAME, AbstractSupplierType.class, (Class)null, value);
   }

   @XmlElementDecl(
      namespace = "http://www.apb.be/standards/smoa/schema/model/v1",
      name = "abstract-Patient"
   )
   public JAXBElement<AbstractPatientType> createAbstractPatient(AbstractPatientType value) {
      return new JAXBElement(_AbstractPatient_QNAME, AbstractPatientType.class, (Class)null, value);
   }

   @XmlElementDecl(
      namespace = "http://www.apb.be/standards/smoa/schema/model/v1",
      name = "abstract-CareProvider"
   )
   public JAXBElement<AbstractCareProviderType> createAbstractCareProvider(AbstractCareProviderType value) {
      return new JAXBElement(_AbstractCareProvider_QNAME, AbstractCareProviderType.class, (Class)null, value);
   }

   @XmlElementDecl(
      namespace = "http://www.apb.be/standards/smoa/schema/model/v1",
      name = "abstract-Pharmacist"
   )
   public JAXBElement<AbstractPharmacistType> createAbstractPharmacist(AbstractPharmacistType value) {
      return new JAXBElement(_AbstractPharmacist_QNAME, AbstractPharmacistType.class, (Class)null, value);
   }

   @XmlElementDecl(
      namespace = "http://www.apb.be/standards/smoa/schema/model/v1",
      name = "abstract-DataLocation"
   )
   public JAXBElement<AbstractDataLocationType> createAbstractDataLocation(AbstractDataLocationType value) {
      return new JAXBElement(_AbstractDataLocation_QNAME, AbstractDataLocationType.class, (Class)null, value);
   }

   @XmlElementDecl(
      namespace = "http://www.apb.be/standards/smoa/schema/model/v1",
      name = "abstract-DeliveredMedication"
   )
   public JAXBElement<AbstractDeliveredMedicationType> createAbstractDeliveredMedication(AbstractDeliveredMedicationType value) {
      return new JAXBElement(_AbstractDeliveredMedication_QNAME, AbstractDeliveredMedicationType.class, (Class)null, value);
   }

   @XmlElementDecl(
      namespace = "http://www.apb.be/standards/smoa/schema/model/v1",
      name = "abstract-Prescription"
   )
   public JAXBElement<AbstractPrescriptionType> createAbstractPrescription(AbstractPrescriptionType value) {
      return new JAXBElement(_AbstractPrescription_QNAME, AbstractPrescriptionType.class, (Class)null, value);
   }

   @XmlElementDecl(
      namespace = "http://www.apb.be/standards/smoa/schema/model/v1",
      name = "abstract-VeterinaryPrescription"
   )
   public JAXBElement<AbstractVeterinaryPrescriptionType> createAbstractVeterinaryPrescription(AbstractVeterinaryPrescriptionType value) {
      return new JAXBElement(_AbstractVeterinaryPrescription_QNAME, AbstractVeterinaryPrescriptionType.class, (Class)null, value);
   }

   @XmlElementDecl(
      namespace = "http://www.apb.be/standards/smoa/schema/model/v1",
      name = "abstract-HumanPrescription"
   )
   public JAXBElement<AbstractHumanPrescriptionType> createAbstractHumanPrescription(AbstractHumanPrescriptionType value) {
      return new JAXBElement(_AbstractHumanPrescription_QNAME, AbstractHumanPrescriptionType.class, (Class)null, value);
   }

   @XmlElementDecl(
      namespace = "http://www.apb.be/standards/smoa/schema/model/v1",
      name = "abstract-MedicationConsumer"
   )
   public JAXBElement<AbstractMedicationConsumerType> createAbstractMedicationConsumer(AbstractMedicationConsumerType value) {
      return new JAXBElement(_AbstractMedicationConsumer_QNAME, AbstractMedicationConsumerType.class, (Class)null, value);
   }

   @XmlElementDecl(
      namespace = "http://www.apb.be/standards/smoa/schema/model/v1",
      name = "posology",
      substitutionHeadNamespace = "http://www.apb.be/standards/smoa/schema/model/v1",
      substitutionHeadName = "abstract-Posology"
   )
   public JAXBElement<PosologyType> createPosology(PosologyType value) {
      return new JAXBElement(_Posology_QNAME, PosologyType.class, (Class)null, value);
   }

   @XmlElementDecl(
      namespace = "http://www.apb.be/standards/smoa/schema/model/v1",
      name = "regimen",
      substitutionHeadNamespace = "http://www.apb.be/standards/smoa/schema/model/v1",
      substitutionHeadName = "abstract-Regimen"
   )
   public JAXBElement<RegimenType> createRegimen(RegimenType value) {
      return new JAXBElement(_Regimen_QNAME, RegimenType.class, (Class)null, value);
   }

   @XmlElementDecl(
      namespace = "http://www.apb.be/standards/smoa/schema/model/v1",
      name = "min-Patient",
      substitutionHeadNamespace = "http://www.apb.be/standards/smoa/schema/model/v1",
      substitutionHeadName = "abstract-Patient"
   )
   public JAXBElement<MinSetPatient> createMinPatient(MinSetPatient value) {
      return new JAXBElement(_MinPatient_QNAME, MinSetPatient.class, (Class)null, value);
   }

   @XmlElementDecl(
      namespace = "http://www.apb.be/standards/smoa/schema/model/v1",
      name = "max-Patient",
      substitutionHeadNamespace = "http://www.apb.be/standards/smoa/schema/model/v1",
      substitutionHeadName = "abstract-Patient"
   )
   public JAXBElement<MaxSetPatient> createMaxPatient(MaxSetPatient value) {
      return new JAXBElement(_MaxPatient_QNAME, MaxSetPatient.class, (Class)null, value);
   }

   @XmlElementDecl(
      namespace = "http://www.apb.be/standards/smoa/schema/model/v1",
      name = "identification",
      substitutionHeadNamespace = "http://www.apb.be/standards/smoa/schema/model/v1",
      substitutionHeadName = "abstract-Person"
   )
   public JAXBElement<MaxSetPersonType> createIdentification(MaxSetPersonType value) {
      return new JAXBElement(_Identification_QNAME, MaxSetPersonType.class, (Class)null, value);
   }

   @XmlElementDecl(
      namespace = "http://www.apb.be/standards/smoa/schema/model/v1",
      name = "contactPerson"
   )
   public JAXBElement<MaxSetContactPersonType> createContactPerson(MaxSetContactPersonType value) {
      return new JAXBElement(_ContactPerson_QNAME, MaxSetContactPersonType.class, (Class)null, value);
   }

   @XmlElementDecl(
      namespace = "http://www.apb.be/standards/smoa/schema/model/v1",
      name = "hcparty",
      substitutionHeadNamespace = "http://www.apb.be/standards/smoa/schema/model/v1",
      substitutionHeadName = "abstract-CareProvider"
   )
   public JAXBElement<MaxSetCareProviderType> createHcparty(MaxSetCareProviderType value) {
      return new JAXBElement(_Hcparty_QNAME, MaxSetCareProviderType.class, (Class)null, value);
   }

   @XmlElementDecl(
      namespace = "http://www.apb.be/standards/smoa/schema/model/v1",
      name = "medicationHistoryEntity",
      substitutionHeadNamespace = "http://www.apb.be/standards/smoa/schema/model/v1",
      substitutionHeadName = "abstract-DeliveredMedication"
   )
   public JAXBElement<MedicationHistoryType> createMedicationHistoryEntity(MedicationHistoryType value) {
      return new JAXBElement(_MedicationHistoryEntity_QNAME, MedicationHistoryType.class, (Class)null, value);
   }

   @XmlElementDecl(
      namespace = "http://www.apb.be/standards/smoa/schema/model/v1",
      name = "deliveredMedicationHuman",
      substitutionHeadNamespace = "http://www.apb.be/standards/smoa/schema/model/v1",
      substitutionHeadName = "abstract-DeliveredMedication"
   )
   public JAXBElement<DeliveredMedicationHumanType> createDeliveredMedicationHuman(DeliveredMedicationHumanType value) {
      return new JAXBElement(_DeliveredMedicationHuman_QNAME, DeliveredMedicationHumanType.class, (Class)null, value);
   }

   @XmlElementDecl(
      namespace = "http://www.apb.be/standards/smoa/schema/model/v1",
      name = "statusMessage",
      substitutionHeadNamespace = "http://www.apb.be/standards/smoa/schema/model/v1",
      substitutionHeadName = "abstract-StatusMessage"
   )
   public JAXBElement<StatusMessageType> createStatusMessage(StatusMessageType value) {
      return new JAXBElement(_StatusMessage_QNAME, StatusMessageType.class, (Class)null, value);
   }

   @XmlElementDecl(
      namespace = "http://www.apb.be/standards/smoa/schema/model/v1",
      name = "dataLocation",
      substitutionHeadNamespace = "http://www.apb.be/standards/smoa/schema/model/v1",
      substitutionHeadName = "abstract-DataLocation"
   )
   public JAXBElement<DataLocationType> createDataLocation(DataLocationType value) {
      return new JAXBElement(_DataLocation_QNAME, DataLocationType.class, (Class)null, value);
   }

   @XmlElementDecl(
      namespace = "http://www.apb.be/standards/smoa/schema/model/v1",
      name = "attest",
      substitutionHeadNamespace = "http://www.apb.be/standards/smoa/schema/model/v1",
      substitutionHeadName = "abstract-Attest"
   )
   public JAXBElement<AttestType> createAttest(AttestType value) {
      return new JAXBElement(_Attest_QNAME, AttestType.class, (Class)null, value);
   }

   @XmlElementDecl(
      namespace = "http://www.apb.be/standards/smoa/schema/model/v1",
      name = "magistralCertificate",
      substitutionHeadNamespace = "http://www.apb.be/standards/smoa/schema/model/v1",
      substitutionHeadName = "abstract-MagistralCertificate"
   )
   public JAXBElement<MagistralCertificateType> createMagistralCertificate(MagistralCertificateType value) {
      return new JAXBElement(_MagistralCertificate_QNAME, MagistralCertificateType.class, (Class)null, value);
   }

   @XmlElementDecl(
      namespace = "http://www.apb.be/standards/smoa/schema/model/v1",
      name = "incomingMedication",
      substitutionHeadNamespace = "http://www.apb.be/standards/smoa/schema/model/v1",
      substitutionHeadName = "abstract-IncomingMedication"
   )
   public JAXBElement<IncomingMedicationType> createIncomingMedication(IncomingMedicationType value) {
      return new JAXBElement(_IncomingMedication_QNAME, IncomingMedicationType.class, (Class)null, value);
   }

   @XmlElementDecl(
      namespace = "http://www.apb.be/standards/smoa/schema/model/v1",
      name = "medicationConsumerPatient",
      substitutionHeadNamespace = "http://www.apb.be/standards/smoa/schema/model/v1",
      substitutionHeadName = "abstract-MedicationConsumer"
   )
   public JAXBElement<MedicationConsumerPatientType> createMedicationConsumerPatient(MedicationConsumerPatientType value) {
      return new JAXBElement(_MedicationConsumerPatient_QNAME, MedicationConsumerPatientType.class, (Class)null, value);
   }

   @XmlElementDecl(
      namespace = "http://www.apb.be/standards/smoa/schema/model/v1",
      name = "medicationConsumerAnimalOwner",
      substitutionHeadNamespace = "http://www.apb.be/standards/smoa/schema/model/v1",
      substitutionHeadName = "abstract-MedicationConsumer"
   )
   public JAXBElement<MedicationConsumerAnimalOwnerType> createMedicationConsumerAnimalOwner(MedicationConsumerAnimalOwnerType value) {
      return new JAXBElement(_MedicationConsumerAnimalOwner_QNAME, MedicationConsumerAnimalOwnerType.class, (Class)null, value);
   }

   @XmlElementDecl(
      namespace = "http://www.apb.be/standards/smoa/schema/model/v1",
      name = "medicationConsumerUrgentieTrousse",
      substitutionHeadNamespace = "http://www.apb.be/standards/smoa/schema/model/v1",
      substitutionHeadName = "abstract-MedicationConsumer"
   )
   public JAXBElement<MedicationConsumerUrgentieTrousseType> createMedicationConsumerUrgentieTrousse(MedicationConsumerUrgentieTrousseType value) {
      return new JAXBElement(_MedicationConsumerUrgentieTrousse_QNAME, MedicationConsumerUrgentieTrousseType.class, (Class)null, value);
   }

   @XmlElementDecl(
      namespace = "http://www.apb.be/standards/smoa/schema/model/v1",
      name = "medicationConsumerPatientGroup",
      substitutionHeadNamespace = "http://www.apb.be/standards/smoa/schema/model/v1",
      substitutionHeadName = "abstract-MedicationConsumer"
   )
   public JAXBElement<MedicationConsumerPatientGroupType> createMedicationConsumerPatientGroup(MedicationConsumerPatientGroupType value) {
      return new JAXBElement(_MedicationConsumerPatientGroup_QNAME, MedicationConsumerPatientGroupType.class, (Class)null, value);
   }

   @XmlElementDecl(
      namespace = "http://www.apb.be/standards/smoa/schema/model/v1",
      name = "deliveredMedicationVeterinair",
      substitutionHeadNamespace = "http://www.apb.be/standards/smoa/schema/model/v1",
      substitutionHeadName = "abstract-DeliveredMedication"
   )
   public JAXBElement<DeliveredMedicationVeterinairType> createDeliveredMedicationVeterinair(DeliveredMedicationVeterinairType value) {
      return new JAXBElement(_DeliveredMedicationVeterinair_QNAME, DeliveredMedicationVeterinairType.class, (Class)null, value);
   }

   @XmlElementDecl(
      namespace = "http://www.apb.be/standards/smoa/schema/model/v1",
      name = "person",
      substitutionHeadNamespace = "http://www.apb.be/standards/smoa/schema/model/v1",
      substitutionHeadName = "abstract-Person"
   )
   public JAXBElement<PersonType> createPerson(PersonType value) {
      return new JAXBElement(_Person_QNAME, PersonType.class, (Class)null, value);
   }

   @XmlElementDecl(
      namespace = "http://www.apb.be/standards/smoa/schema/model/v1",
      name = "medicinalProduct",
      substitutionHeadNamespace = "http://www.apb.be/standards/smoa/schema/model/v1",
      substitutionHeadName = "abstract-MedicinalProduct"
   )
   public JAXBElement<MedicinalProduct> createMedicinalProduct(MedicinalProduct value) {
      return new JAXBElement(_MedicinalProduct_QNAME, MedicinalProduct.class, (Class)null, value);
   }

   @XmlElementDecl(
      namespace = "http://www.apb.be/standards/smoa/schema/model/v1",
      name = "rawMaterial",
      substitutionHeadNamespace = "http://www.apb.be/standards/smoa/schema/model/v1",
      substitutionHeadName = "abstract-RawMaterial"
   )
   public JAXBElement<RawMaterialType> createRawMaterial(RawMaterialType value) {
      return new JAXBElement(_RawMaterial_QNAME, RawMaterialType.class, (Class)null, value);
   }

   @XmlElementDecl(
      namespace = "http://www.apb.be/standards/smoa/schema/model/v1",
      name = "substanceProduct",
      substitutionHeadNamespace = "http://www.apb.be/standards/smoa/schema/model/v1",
      substitutionHeadName = "abstract-SubstanceProduct"
   )
   public JAXBElement<SubstanceProductType> createSubstanceProduct(SubstanceProductType value) {
      return new JAXBElement(_SubstanceProduct_QNAME, SubstanceProductType.class, (Class)null, value);
   }

   @XmlElementDecl(
      namespace = "http://www.apb.be/standards/smoa/schema/model/v1",
      name = "preparation",
      substitutionHeadNamespace = "http://www.apb.be/standards/smoa/schema/model/v1",
      substitutionHeadName = "abstract-Preparation"
   )
   public JAXBElement<PreparationType> createPreparation(PreparationType value) {
      return new JAXBElement(_Preparation_QNAME, PreparationType.class, (Class)null, value);
   }

   @XmlElementDecl(
      namespace = "http://www.apb.be/standards/smoa/schema/model/v1",
      name = "veterinaryPrescription",
      substitutionHeadNamespace = "http://www.apb.be/standards/smoa/schema/model/v1",
      substitutionHeadName = "abstract-VeterinaryPrescription"
   )
   public JAXBElement<VeterinaryPrescriptionType> createVeterinaryPrescription(VeterinaryPrescriptionType value) {
      return new JAXBElement(_VeterinaryPrescription_QNAME, VeterinaryPrescriptionType.class, (Class)null, value);
   }

   @XmlElementDecl(
      namespace = "http://www.apb.be/standards/smoa/schema/model/v1",
      name = "patientPrescription",
      substitutionHeadNamespace = "http://www.apb.be/standards/smoa/schema/model/v1",
      substitutionHeadName = "abstract-HumanPrescription"
   )
   public JAXBElement<PatientPrescriptionType> createPatientPrescription(PatientPrescriptionType value) {
      return new JAXBElement(_PatientPrescription_QNAME, PatientPrescriptionType.class, (Class)null, value);
   }

   @XmlElementDecl(
      namespace = "http://www.apb.be/standards/smoa/schema/model/v1",
      name = "specialPrescription",
      substitutionHeadNamespace = "http://www.apb.be/standards/smoa/schema/model/v1",
      substitutionHeadName = "abstract-HumanPrescription"
   )
   public JAXBElement<SpecialPrescriptionType> createSpecialPrescription(SpecialPrescriptionType value) {
      return new JAXBElement(_SpecialPrescription_QNAME, SpecialPrescriptionType.class, (Class)null, value);
   }

   @XmlElementDecl(
      namespace = "http://www.apb.be/standards/smoa/schema/model/v1",
      name = "deferredPrescription",
      substitutionHeadNamespace = "http://www.apb.be/standards/smoa/schema/model/v1",
      substitutionHeadName = "abstract-HumanPrescription"
   )
   public JAXBElement<DeferredPrescriptionType> createDeferredPrescription(DeferredPrescriptionType value) {
      return new JAXBElement(_DeferredPrescription_QNAME, DeferredPrescriptionType.class, (Class)null, value);
   }

   @XmlElementDecl(
      namespace = "http://www.apb.be/standards/smoa/schema/model/v1",
      name = "kmehrPrescription",
      substitutionHeadNamespace = "http://www.apb.be/standards/smoa/schema/model/v1",
      substitutionHeadName = "abstract-Prescription"
   )
   public JAXBElement<KmehrPrescriptionType> createKmehrPrescription(KmehrPrescriptionType value) {
      return new JAXBElement(_KmehrPrescription_QNAME, KmehrPrescriptionType.class, (Class)null, value);
   }

   @XmlElementDecl(
      namespace = "http://www.apb.be/standards/smoa/schema/model/v1",
      name = "recipeLine",
      substitutionHeadNamespace = "http://www.apb.be/standards/smoa/schema/model/v1",
      substitutionHeadName = "abstract-RecipeLine"
   )
   public JAXBElement<RecipeLineType> createRecipeLine(RecipeLineType value) {
      return new JAXBElement(_RecipeLine_QNAME, RecipeLineType.class, (Class)null, value);
   }

   @XmlElementDecl(
      namespace = "http://www.apb.be/standards/smoa/schema/model/v1",
      name = "refPharmacy",
      substitutionHeadNamespace = "http://www.apb.be/standards/smoa/schema/model/v1",
      substitutionHeadName = "abstract-Pharmacy"
   )
   public JAXBElement<ReferencePharmacyType> createRefPharmacy(ReferencePharmacyType value) {
      return new JAXBElement(_RefPharmacy_QNAME, ReferencePharmacyType.class, (Class)null, value);
   }

   @XmlElementDecl(
      namespace = "http://www.apb.be/standards/smoa/schema/model/v1",
      name = "refCareService",
      substitutionHeadNamespace = "http://www.apb.be/standards/smoa/schema/model/v1",
      substitutionHeadName = "abstract-CareService"
   )
   public JAXBElement<ReferenceCareServiceType> createRefCareService(ReferenceCareServiceType value) {
      return new JAXBElement(_RefCareService_QNAME, ReferenceCareServiceType.class, (Class)null, value);
   }

   @XmlElementDecl(
      namespace = "http://www.apb.be/standards/smoa/schema/model/v1",
      name = "refPerson",
      substitutionHeadNamespace = "http://www.apb.be/standards/smoa/schema/model/v1",
      substitutionHeadName = "abstract-Person"
   )
   public JAXBElement<ReferencePersonType> createRefPerson(ReferencePersonType value) {
      return new JAXBElement(_RefPerson_QNAME, ReferencePersonType.class, (Class)null, value);
   }

   @XmlElementDecl(
      namespace = "http://www.apb.be/standards/smoa/schema/model/v1",
      name = "refMedicinalProduct",
      substitutionHeadNamespace = "http://www.apb.be/standards/smoa/schema/model/v1",
      substitutionHeadName = "abstract-MedicinalProduct"
   )
   public JAXBElement<ReferenceMedicinalProductType> createRefMedicinalProduct(ReferenceMedicinalProductType value) {
      return new JAXBElement(_RefMedicinalProduct_QNAME, ReferenceMedicinalProductType.class, (Class)null, value);
   }

   @XmlElementDecl(
      namespace = "http://www.apb.be/standards/smoa/schema/model/v1",
      name = "refPrescription",
      substitutionHeadNamespace = "http://www.apb.be/standards/smoa/schema/model/v1",
      substitutionHeadName = "abstract-Prescription"
   )
   public JAXBElement<ReferencePrescriptionType> createRefPrescription(ReferencePrescriptionType value) {
      return new JAXBElement(_RefPrescription_QNAME, ReferencePrescriptionType.class, (Class)null, value);
   }

   @XmlElementDecl(
      namespace = "http://www.apb.be/standards/smoa/schema/model/v1",
      name = "refAttest",
      substitutionHeadNamespace = "http://www.apb.be/standards/smoa/schema/model/v1",
      substitutionHeadName = "abstract-Attest"
   )
   public JAXBElement<ReferenceAttestType> createRefAttest(ReferenceAttestType value) {
      return new JAXBElement(_RefAttest_QNAME, ReferenceAttestType.class, (Class)null, value);
   }

   @XmlElementDecl(
      namespace = "http://www.apb.be/standards/smoa/schema/model/v1",
      name = "refMagistralCertificate",
      substitutionHeadNamespace = "http://www.apb.be/standards/smoa/schema/model/v1",
      substitutionHeadName = "abstract-MagistralCertificate"
   )
   public JAXBElement<AbstractMagistralCertificateType> createRefMagistralCertificate(AbstractMagistralCertificateType value) {
      return new JAXBElement(_RefMagistralCertificate_QNAME, AbstractMagistralCertificateType.class, (Class)null, value);
   }
}
