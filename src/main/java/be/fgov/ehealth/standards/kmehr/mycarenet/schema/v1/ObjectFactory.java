package be.fgov.ehealth.standards.kmehr.mycarenet.schema.v1;

import org.taktik.connector.technical.adapter.XmlDateNoTzAdapter;
import be.fgov.ehealth.standards.kmehr.mycarenet.dt.v1.TextType;
import java.math.BigInteger;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.namespace.QName;
import org.joda.time.DateTime;
import org.w3._2000._09.xmldsig.Signature;

@XmlRegistry
public class ObjectFactory {
   private static final QName _KmehrmessageSignature_QNAME = new QName("http://www.ehealth.fgov.be/standards/kmehr/schema/v1", "Signature");
   private static final QName _RegimenDaynumber_QNAME = new QName("http://www.ehealth.fgov.be/standards/kmehr/schema/v1", "daynumber");
   private static final QName _RegimenQuantity_QNAME = new QName("http://www.ehealth.fgov.be/standards/kmehr/schema/v1", "quantity");
   private static final QName _RegimenDate_QNAME = new QName("http://www.ehealth.fgov.be/standards/kmehr/schema/v1", "date");
   private static final QName _RegimenWeekday_QNAME = new QName("http://www.ehealth.fgov.be/standards/kmehr/schema/v1", "weekday");
   private static final QName _RegimenDaytime_QNAME = new QName("http://www.ehealth.fgov.be/standards/kmehr/schema/v1", "daytime");
   private static final QName _CompoundprescriptionTypeCompound_QNAME = new QName("http://www.ehealth.fgov.be/standards/kmehr/schema/v1", "compound");
   private static final QName _CompoundprescriptionTypeFormularyreference_QNAME = new QName("http://www.ehealth.fgov.be/standards/kmehr/schema/v1", "formularyreference");
   private static final QName _CompoundprescriptionTypeMagistraltext_QNAME = new QName("http://www.ehealth.fgov.be/standards/kmehr/schema/v1", "magistraltext");
   private static final QName _CompoundprescriptionTypeGalenicform_QNAME = new QName("http://www.ehealth.fgov.be/standards/kmehr/schema/v1", "galenicform");

   public Kmehrmessage createKmehrmessage() {
      return new Kmehrmessage();
   }

   public ConfidentialityType createConfidentialityType() {
      return new ConfidentialityType();
   }

   public HeaderType createHeaderType() {
      return new HeaderType();
   }

   public Base64EncryptedDataType createBase64EncryptedDataType() {
      return new Base64EncryptedDataType();
   }

   public FolderType createFolderType() {
      return new FolderType();
   }

   public PersonType createPersonType() {
      return new PersonType();
   }

   public DateType createDateType() {
      return new DateType();
   }

   public AddressTypeBase createAddressTypeBase() {
      return new AddressTypeBase();
   }

   public SexType createSexType() {
      return new SexType();
   }

   public Nationality createNationality() {
      return new Nationality();
   }

   public AddressType createAddressType() {
      return new AddressType();
   }

   public TelecomType createTelecomType() {
      return new TelecomType();
   }

   public ProfessionType createProfessionType() {
      return new ProfessionType();
   }

   public InsuranceType createInsuranceType() {
      return new InsuranceType();
   }

   public MemberinsuranceType createMemberinsuranceType() {
      return new MemberinsuranceType();
   }

   public Civilstate createCivilstate() {
      return new Civilstate();
   }

   public TransactionType createTransactionType() {
      return new TransactionType();
   }

   public AcknowledgmentType createAcknowledgmentType() {
      return new AcknowledgmentType();
   }

   public AdministrationquantityType createAdministrationquantityType() {
      return new AdministrationquantityType();
   }

   public AdministrationunitType createAdministrationunitType() {
      return new AdministrationunitType();
   }

   public AuthorType createAuthorType() {
      return new AuthorType();
   }

   public Base64EncryptedValueType createBase64EncryptedValueType() {
      return new Base64EncryptedValueType();
   }

   public CertaintyType createCertaintyType() {
      return new CertaintyType();
   }

   public CompoundType createCompoundType() {
      return new CompoundType();
   }

   public CompoundprescriptionType createCompoundprescriptionType() {
      return new CompoundprescriptionType();
   }

   public ContentlocalitemattributeType createContentlocalitemattributeType() {
      return new ContentlocalitemattributeType();
   }

   public ContentType createContentType() {
      return new ContentType();
   }

   public CostType createCostType() {
      return new CostType();
   }

   public CountryType createCountryType() {
      return new CountryType();
   }

   public DayperiodType createDayperiodType() {
      return new DayperiodType();
   }

   public DurationType createDurationType() {
      return new DurationType();
   }

   public ErrorType createErrorType() {
      return new ErrorType();
   }

   public ErrorMyCarenetType createErrorMyCarenetType() {
      return new ErrorMyCarenetType();
   }

   public FormularyreferenceType createFormularyreferenceType() {
      return new FormularyreferenceType();
   }

   public GalenicformType createGalenicformType() {
      return new GalenicformType();
   }

   public FCMAXType createFCMAXType() {
      return new FCMAXType();
   }

   public FCMINType createFCMINType() {
      return new FCMINType();
   }

   public FrequencyType createFrequencyType() {
      return new FrequencyType();
   }

   public HcpartyType createHcpartyType() {
      return new HcpartyType();
   }

   public HeadingType createHeadingType() {
      return new HeadingType();
   }

   public HolterType createHolterType() {
      return new HolterType();
   }

   public IncapacityreasonType createIncapacityreasonType() {
      return new IncapacityreasonType();
   }

   public IncapacityType createIncapacityType() {
      return new IncapacityType();
   }

   public ItemType createItemType() {
      return new ItemType();
   }

   public LifecycleType createLifecycleType() {
      return new LifecycleType();
   }

   public LocalitemattributeType createLocalitemattributeType() {
      return new LocalitemattributeType();
   }

   public LocationBirthPlaceType createLocationBirthPlaceType() {
      return new LocationBirthPlaceType();
   }

   public MaxrefType createMaxrefType() {
      return new MaxrefType();
   }

   public MedicationType createMedicationType() {
      return new MedicationType();
   }

   public MinrefType createMinrefType() {
      return new MinrefType();
   }

   public MomentType createMomentType() {
      return new MomentType();
   }

   public PackageType createPackageType() {
      return new PackageType();
   }

   public PeriodicityType createPeriodicityType() {
      return new PeriodicityType();
   }

   public PersonTypeLight createPersonTypeLight() {
      return new PersonTypeLight();
   }

   public PresentationType createPresentationType() {
      return new PresentationType();
   }

   public QuantityType createQuantityType() {
      return new QuantityType();
   }

   public RecipientType createRecipientType() {
      return new RecipientType();
   }

   public RefscopeType createRefscopeType() {
      return new RefscopeType();
   }

   public RenewalType createRenewalType() {
      return new RenewalType();
   }

   public RouteType createRouteType() {
      return new RouteType();
   }

   public RRMAXType createRRMAXType() {
      return new RRMAXType();
   }

   public RRMINType createRRMINType() {
      return new RRMINType();
   }

   public SenderType createSenderType() {
      return new SenderType();
   }

   public SeverityType createSeverityType() {
      return new SeverityType();
   }

   public SiteType createSiteType() {
      return new SiteType();
   }

   public StandardType createStandardType() {
      return new StandardType();
   }

   public StrengthType createStrengthType() {
      return new StrengthType();
   }

   public SubstanceType createSubstanceType() {
      return new SubstanceType();
   }

   public TemporalityType createTemporalityType() {
      return new TemporalityType();
   }

   public TextWithLayoutType createTextWithLayoutType() {
      return new TextWithLayoutType();
   }

   public TimequantityType createTimequantityType() {
      return new TimequantityType();
   }

   public TimeunitType createTimeunitType() {
      return new TimeunitType();
   }

   public UnitType createUnitType() {
      return new UnitType();
   }

   public UrgencyType createUrgencyType() {
      return new UrgencyType();
   }

   public WeekdayType createWeekdayType() {
      return new WeekdayType();
   }

   public MedicinalProductType createMedicinalProductType() {
      return new MedicinalProductType();
   }

   public Specialisation createSpecialisation() {
      return new Specialisation();
   }

   public Refvalue createRefvalue() {
      return new Refvalue();
   }

   public Posology createPosology() {
      return new Posology();
   }

   public McnRegimen createRegimen() {
      return new McnRegimen();
   }

   public Feedback createFeedback() {
      return new Feedback();
   }

   public Weekday createWeekday() {
      return new Weekday();
   }

   public Daytime createDaytime() {
      return new Daytime();
   }

   public Takes createTakes() {
      return new Takes();
   }

   public Nominator createNominator() {
      return new Nominator();
   }

   public Denominator createDenominator() {
      return new Denominator();
   }

   public Substanceproduct createSubstanceproduct() {
      return new Substanceproduct();
   }

   public Quantityprefix createQuantityprefix() {
      return new Quantityprefix();
   }

   public Personalpart createPersonalpart() {
      return new Personalpart();
   }

   public Thirdpayercontract createThirdpayercontract() {
      return new Thirdpayercontract();
   }

   public Externalsource createExternalsource() {
      return new Externalsource();
   }

   public Source createSource() {
      return new Source();
   }

   @XmlElementDecl(
      namespace = "http://www.ehealth.fgov.be/standards/kmehr/schema/v1",
      name = "Signature",
      scope = Kmehrmessage.class
   )
   public JAXBElement<Signature> createKmehrmessageSignature(Signature value) {
      return new JAXBElement(_KmehrmessageSignature_QNAME, Signature.class, Kmehrmessage.class, value);
   }

   @XmlElementDecl(
      namespace = "http://www.ehealth.fgov.be/standards/kmehr/schema/v1",
      name = "daynumber",
      scope = McnRegimen.class
   )
   public JAXBElement<BigInteger> createRegimenDaynumber(BigInteger value) {
      return new JAXBElement(_RegimenDaynumber_QNAME, BigInteger.class, McnRegimen.class, value);
   }

   @XmlElementDecl(
      namespace = "http://www.ehealth.fgov.be/standards/kmehr/schema/v1",
      name = "quantity",
      scope = McnRegimen.class
   )
   public JAXBElement<AdministrationquantityType> createRegimenQuantity(AdministrationquantityType value) {
      return new JAXBElement(_RegimenQuantity_QNAME, AdministrationquantityType.class, McnRegimen.class, value);
   }

   @XmlElementDecl(
      namespace = "http://www.ehealth.fgov.be/standards/kmehr/schema/v1",
      name = "date",
      scope = McnRegimen.class
   )
   @XmlJavaTypeAdapter(XmlDateNoTzAdapter.class)
   public JAXBElement<DateTime> createRegimenDate(DateTime value) {
      return new JAXBElement(_RegimenDate_QNAME, DateTime.class, McnRegimen.class, value);
   }

   @XmlElementDecl(
      namespace = "http://www.ehealth.fgov.be/standards/kmehr/schema/v1",
      name = "weekday",
      scope = McnRegimen.class
   )
   public JAXBElement<Weekday> createRegimenWeekday(Weekday value) {
      return new JAXBElement(_RegimenWeekday_QNAME, Weekday.class, McnRegimen.class, value);
   }

   @XmlElementDecl(
      namespace = "http://www.ehealth.fgov.be/standards/kmehr/schema/v1",
      name = "daytime",
      scope = McnRegimen.class
   )
   public JAXBElement<Daytime> createRegimenDaytime(Daytime value) {
      return new JAXBElement(_RegimenDaytime_QNAME, Daytime.class, McnRegimen.class, value);
   }

   @XmlElementDecl(
      namespace = "http://www.ehealth.fgov.be/standards/kmehr/schema/v1",
      name = "compound",
      scope = CompoundprescriptionType.class
   )
   public JAXBElement<CompoundType> createCompoundprescriptionTypeCompound(CompoundType value) {
      return new JAXBElement(_CompoundprescriptionTypeCompound_QNAME, CompoundType.class, CompoundprescriptionType.class, value);
   }

   @XmlElementDecl(
      namespace = "http://www.ehealth.fgov.be/standards/kmehr/schema/v1",
      name = "formularyreference",
      scope = CompoundprescriptionType.class
   )
   public JAXBElement<FormularyreferenceType> createCompoundprescriptionTypeFormularyreference(FormularyreferenceType value) {
      return new JAXBElement(_CompoundprescriptionTypeFormularyreference_QNAME, FormularyreferenceType.class, CompoundprescriptionType.class, value);
   }

   @XmlElementDecl(
      namespace = "http://www.ehealth.fgov.be/standards/kmehr/schema/v1",
      name = "magistraltext",
      scope = CompoundprescriptionType.class
   )
   public JAXBElement<TextType> createCompoundprescriptionTypeMagistraltext(TextType value) {
      return new JAXBElement(_CompoundprescriptionTypeMagistraltext_QNAME, TextType.class, CompoundprescriptionType.class, value);
   }

   @XmlElementDecl(
      namespace = "http://www.ehealth.fgov.be/standards/kmehr/schema/v1",
      name = "galenicform",
      scope = CompoundprescriptionType.class
   )
   public JAXBElement<GalenicformType> createCompoundprescriptionTypeGalenicform(GalenicformType value) {
      return new JAXBElement(_CompoundprescriptionTypeGalenicform_QNAME, GalenicformType.class, CompoundprescriptionType.class, value);
   }

   @XmlElementDecl(
      namespace = "http://www.ehealth.fgov.be/standards/kmehr/schema/v1",
      name = "quantity",
      scope = CompoundprescriptionType.class
   )
   public JAXBElement<QuantityType> createCompoundprescriptionTypeQuantity(QuantityType value) {
      return new JAXBElement(_RegimenQuantity_QNAME, QuantityType.class, CompoundprescriptionType.class, value);
   }
}
