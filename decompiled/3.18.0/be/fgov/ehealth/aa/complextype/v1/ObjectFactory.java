package be.fgov.ehealth.aa.complextype.v1;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

@XmlRegistry
public class ObjectFactory {
   private static final QName _GIAMIEntity_QNAME = new QName("urn:be:fgov:ehealth:aa:complextype:v1", "GIAMIEntity");
   private static final QName _GIAMIQuality_QNAME = new QName("urn:be:fgov:ehealth:aa:complextype:v1", "GIAMIQuality");
   private static final QName _HealthCareOrganizationV2_QNAME = new QName("urn:be:fgov:ehealth:aa:complextype:v1", "HealthCareOrganizationV2");
   private static final QName _Individual_QNAME = new QName("urn:be:fgov:ehealth:aa:complextype:v1", "Individual");
   private static final QName _MandateAttributeStatement_QNAME = new QName("urn:be:fgov:ehealth:aa:complextype:v1", "MandateAttributeStatement");
   private static final QName _Name_QNAME = new QName("urn:be:fgov:ehealth:aa:complextype:v1", "Name");
   private static final QName _OrganizationAttributeStatement_QNAME = new QName("urn:be:fgov:ehealth:aa:complextype:v1", "OrganizationAttributeStatement");
   private static final QName _OrganizationTypeCode_QNAME = new QName("urn:be:fgov:ehealth:aa:complextype:v1", "OrganizationTypeCode");
   private static final QName _OrganizationTypeFriendlyName_QNAME = new QName("urn:be:fgov:ehealth:aa:complextype:v1", "OrganizationTypeFriendlyName");
   private static final QName _Profession_QNAME = new QName("urn:be:fgov:ehealth:aa:complextype:v1", "Profession");
   private static final QName _ProfessionFriendlyName_QNAME = new QName("urn:be:fgov:ehealth:aa:complextype:v1", "ProfessionFriendlyName");
   private static final QName _ProfessionalAddressbook_QNAME = new QName("urn:be:fgov:ehealth:aa:complextype:v1", "ProfessionalAddressbook");
   private static final QName _QualityAttributeStatement_QNAME = new QName("urn:be:fgov:ehealth:aa:complextype:v1", "QualityAttributeStatement");
   private static final QName _QualityTypeCode_QNAME = new QName("urn:be:fgov:ehealth:aa:complextype:v1", "QualityTypeCode");
   private static final QName _QualityTypeFriendlyName_QNAME = new QName("urn:be:fgov:ehealth:aa:complextype:v1", "QualityTypeFriendlyName");

   public Address createAddress() {
      return new Address();
   }

   public StreetType createStreetType() {
      return new StreetType();
   }

   public Municipality createMunicipality() {
      return new Municipality();
   }

   public Country createCountry() {
      return new Country();
   }

   public Codification createCodification() {
      return new Codification();
   }

   public NameType createNameType() {
      return new NameType();
   }

   public Description createDescription() {
      return new Description();
   }

   public BaseNameType createBaseNameType() {
      return new BaseNameType();
   }

   public GIAMIAdministrator createGIAMIAdministrator() {
      return new GIAMIAdministrator();
   }

   public GIAMIEntityType createGIAMIEntityType() {
      return new GIAMIEntityType();
   }

   public GIAMIOrganization createGIAMIOrganization() {
      return new GIAMIOrganization();
   }

   public HealthCareAdditionalInformation createHealthCareAdditionalInformation() {
      return new HealthCareAdditionalInformation();
   }

   public HealthCareOrganization createHealthCareOrganization() {
      return new HealthCareOrganization();
   }

   public TypeCodeType createTypeCodeType() {
      return new TypeCodeType();
   }

   public HealthCareOrganizationTypeV2 createHealthCareOrganizationTypeV2() {
      return new HealthCareOrganizationTypeV2();
   }

   public HealthCarePerson createHealthCarePerson() {
      return new HealthCarePerson();
   }

   public ProfessionalType createProfessionalType() {
      return new ProfessionalType();
   }

   public HealthCareProfessional createHealthCareProfessional() {
      return new HealthCareProfessional();
   }

   public IndividualType createIndividualType() {
      return new IndividualType();
   }

   public ProfessionV3 createProfessionV3() {
      return new ProfessionV3();
   }

   public Mandate createMandate() {
      return new Mandate();
   }

   public PeriodType createPeriodType() {
      return new PeriodType();
   }

   public PrincipalType createPrincipalType() {
      return new PrincipalType();
   }

   public IAMAttributeStatement createIAMAttributeStatement() {
      return new IAMAttributeStatement();
   }

   public OrganizationInfo createOrganizationInfo() {
      return new OrganizationInfo();
   }

   public PersonWithFunction createPersonWithFunction() {
      return new PersonWithFunction();
   }

   public PersonType createPersonType() {
      return new PersonType();
   }

   public ProfessionType createProfessionType() {
      return new ProfessionType();
   }

   public ProfessionV2 createProfessionV2() {
      return new ProfessionV2();
   }

   public ProfessionCode createProfessionCode() {
      return new ProfessionCode();
   }

   public Speciality createSpeciality() {
      return new Speciality();
   }

   public ProfessionalAddressbookType createProfessionalAddressbookType() {
      return new ProfessionalAddressbookType();
   }

   public Responsible createResponsible() {
      return new Responsible();
   }

   public SupportedOrganization createSupportedOrganization() {
      return new SupportedOrganization();
   }

   public OrganizationDescriberType createOrganizationDescriberType() {
      return new OrganizationDescriberType();
   }

   public OrganizationIdentifierType createOrganizationIdentifierType() {
      return new OrganizationIdentifierType();
   }

   public BaseServiceListType createBaseServiceListType() {
      return new BaseServiceListType();
   }

   public SupportedOrganizationV2 createSupportedOrganizationV2() {
      return new SupportedOrganizationV2();
   }

   public OrganizationDescriberTypeV2 createOrganizationDescriberTypeV2() {
      return new OrganizationDescriberTypeV2();
   }

   public SupportedQuality createSupportedQuality() {
      return new SupportedQuality();
   }

   public QualityDescriberType createQualityDescriberType() {
      return new QualityDescriberType();
   }

   public BaseServiceType createBaseServiceType() {
      return new BaseServiceType();
   }

   public OrganizationAddressbookType createOrganizationAddressbookType() {
      return new OrganizationAddressbookType();
   }

   @XmlElementDecl(
      namespace = "urn:be:fgov:ehealth:aa:complextype:v1",
      name = "GIAMIEntity"
   )
   public JAXBElement<GIAMIEntityType> createGIAMIEntity(GIAMIEntityType value) {
      return new JAXBElement(_GIAMIEntity_QNAME, GIAMIEntityType.class, (Class)null, value);
   }

   @XmlElementDecl(
      namespace = "urn:be:fgov:ehealth:aa:complextype:v1",
      name = "GIAMIQuality"
   )
   public JAXBElement<GIAMIEntityType> createGIAMIQuality(GIAMIEntityType value) {
      return new JAXBElement(_GIAMIQuality_QNAME, GIAMIEntityType.class, (Class)null, value);
   }

   @XmlElementDecl(
      namespace = "urn:be:fgov:ehealth:aa:complextype:v1",
      name = "HealthCareOrganizationV2"
   )
   public JAXBElement<HealthCareOrganizationTypeV2> createHealthCareOrganizationV2(HealthCareOrganizationTypeV2 value) {
      return new JAXBElement(_HealthCareOrganizationV2_QNAME, HealthCareOrganizationTypeV2.class, (Class)null, value);
   }

   @XmlElementDecl(
      namespace = "urn:be:fgov:ehealth:aa:complextype:v1",
      name = "Individual"
   )
   public JAXBElement<IndividualType> createIndividual(IndividualType value) {
      return new JAXBElement(_Individual_QNAME, IndividualType.class, (Class)null, value);
   }

   @XmlElementDecl(
      namespace = "urn:be:fgov:ehealth:aa:complextype:v1",
      name = "MandateAttributeStatement"
   )
   public JAXBElement<IAMAttributeStatement> createMandateAttributeStatement(IAMAttributeStatement value) {
      return new JAXBElement(_MandateAttributeStatement_QNAME, IAMAttributeStatement.class, (Class)null, value);
   }

   @XmlElementDecl(
      namespace = "urn:be:fgov:ehealth:aa:complextype:v1",
      name = "Name"
   )
   public JAXBElement<NameType> createName(NameType value) {
      return new JAXBElement(_Name_QNAME, NameType.class, (Class)null, value);
   }

   @XmlElementDecl(
      namespace = "urn:be:fgov:ehealth:aa:complextype:v1",
      name = "OrganizationAttributeStatement"
   )
   public JAXBElement<IAMAttributeStatement> createOrganizationAttributeStatement(IAMAttributeStatement value) {
      return new JAXBElement(_OrganizationAttributeStatement_QNAME, IAMAttributeStatement.class, (Class)null, value);
   }

   @XmlElementDecl(
      namespace = "urn:be:fgov:ehealth:aa:complextype:v1",
      name = "OrganizationTypeCode"
   )
   public JAXBElement<TypeCodeType> createOrganizationTypeCode(TypeCodeType value) {
      return new JAXBElement(_OrganizationTypeCode_QNAME, TypeCodeType.class, (Class)null, value);
   }

   @XmlElementDecl(
      namespace = "urn:be:fgov:ehealth:aa:complextype:v1",
      name = "OrganizationTypeFriendlyName"
   )
   public JAXBElement<NameType> createOrganizationTypeFriendlyName(NameType value) {
      return new JAXBElement(_OrganizationTypeFriendlyName_QNAME, NameType.class, (Class)null, value);
   }

   @XmlElementDecl(
      namespace = "urn:be:fgov:ehealth:aa:complextype:v1",
      name = "Profession"
   )
   public JAXBElement<ProfessionType> createProfession(ProfessionType value) {
      return new JAXBElement(_Profession_QNAME, ProfessionType.class, (Class)null, value);
   }

   @XmlElementDecl(
      namespace = "urn:be:fgov:ehealth:aa:complextype:v1",
      name = "ProfessionFriendlyName"
   )
   public JAXBElement<NameType> createProfessionFriendlyName(NameType value) {
      return new JAXBElement(_ProfessionFriendlyName_QNAME, NameType.class, (Class)null, value);
   }

   @XmlElementDecl(
      namespace = "urn:be:fgov:ehealth:aa:complextype:v1",
      name = "ProfessionalAddressbook"
   )
   public JAXBElement<ProfessionalAddressbookType> createProfessionalAddressbook(ProfessionalAddressbookType value) {
      return new JAXBElement(_ProfessionalAddressbook_QNAME, ProfessionalAddressbookType.class, (Class)null, value);
   }

   @XmlElementDecl(
      namespace = "urn:be:fgov:ehealth:aa:complextype:v1",
      name = "QualityAttributeStatement"
   )
   public JAXBElement<IAMAttributeStatement> createQualityAttributeStatement(IAMAttributeStatement value) {
      return new JAXBElement(_QualityAttributeStatement_QNAME, IAMAttributeStatement.class, (Class)null, value);
   }

   @XmlElementDecl(
      namespace = "urn:be:fgov:ehealth:aa:complextype:v1",
      name = "QualityTypeCode"
   )
   public JAXBElement<TypeCodeType> createQualityTypeCode(TypeCodeType value) {
      return new JAXBElement(_QualityTypeCode_QNAME, TypeCodeType.class, (Class)null, value);
   }

   @XmlElementDecl(
      namespace = "urn:be:fgov:ehealth:aa:complextype:v1",
      name = "QualityTypeFriendlyName"
   )
   public JAXBElement<NameType> createQualityTypeFriendlyName(NameType value) {
      return new JAXBElement(_QualityTypeFriendlyName_QNAME, NameType.class, (Class)null, value);
   }
}
