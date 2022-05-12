package be.fgov.ehealth.dics.protocol.v5;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

@XmlRegistry
public class ObjectFactory {
   private static final QName _FindAmpRequest_QNAME = new QName("urn:be:fgov:ehealth:dics:protocol:v5", "FindAmpRequest");
   private static final QName _FindListOfAmpRequest_QNAME = new QName("urn:be:fgov:ehealth:dics:protocol:v5", "FindListOfAmpRequest");

   public ObjectFactory() {
   }

   public FindAmpRequestType createFindAmpRequestType() {
      return new FindAmpRequestType();
   }

   public FindAmpResponse createFindAmpResponse() {
      return new FindAmpResponse();
   }

   public DicsResponseType createDicsResponseType() {
      return new DicsResponseType();
   }

   public ConsultAmpType createConsultAmpType() {
      return new ConsultAmpType();
   }

   public FindListOfAmpResponse createFindListOfAmpResponse() {
      return new FindListOfAmpResponse();
   }

   public ListAmpType createListAmpType() {
      return new ListAmpType();
   }

   public FindCompanyRequest createFindCompanyRequest() {
      return new FindCompanyRequest();
   }

   public DicsRequestType createDicsRequestType() {
      return new DicsRequestType();
   }

   public VatNrPerCountryType createVatNrPerCountryType() {
      return new VatNrPerCountryType();
   }

   public FindCompanyResponse createFindCompanyResponse() {
      return new FindCompanyResponse();
   }

   public ConsultCompanyType createConsultCompanyType() {
      return new ConsultCompanyType();
   }

   public FindVmpRequest createFindVmpRequest() {
      return new FindVmpRequest();
   }

   public FindByGenericPrescriptionGroupType createFindByGenericPrescriptionGroupType() {
      return new FindByGenericPrescriptionGroupType();
   }

   public FindByTherapeuticMoietyType createFindByTherapeuticMoietyType() {
      return new FindByTherapeuticMoietyType();
   }

   public FindByVirtualProductType createFindByVirtualProductType() {
      return new FindByVirtualProductType();
   }

   public FindByIngredientType createFindByIngredientType() {
      return new FindByIngredientType();
   }

   public HasVirtualComponentWithType createHasVirtualComponentWithType() {
      return new HasVirtualComponentWithType();
   }

   public HasWadaClassificationType createHasWadaClassificationType() {
      return new HasWadaClassificationType();
   }

   public FindVmpResponse createFindVmpResponse() {
      return new FindVmpResponse();
   }

   public ConsultVmpType createConsultVmpType() {
      return new ConsultVmpType();
   }

   public FindLegislationTextRequest createFindLegislationTextRequest() {
      return new FindLegislationTextRequest();
   }

   public FindByDmppType createFindByDmppType() {
      return new FindByDmppType();
   }

   public FindLegalBasesType createFindLegalBasesType() {
      return new FindLegalBasesType();
   }

   public FindLegislationTextResponse createFindLegislationTextResponse() {
      return new FindLegislationTextResponse();
   }

   public ConsultLegalBasisType createConsultLegalBasisType() {
      return new ConsultLegalBasisType();
   }

   public FindReimbursementRequest createFindReimbursementRequest() {
      return new FindReimbursementRequest();
   }

   public FindByPackageType createFindByPackageType() {
      return new FindByPackageType();
   }

   public FindReimbursementResponse createFindReimbursementResponse() {
      return new FindReimbursementResponse();
   }

   public ConsultReimbursementContextType createConsultReimbursementContextType() {
      return new ConsultReimbursementContextType();
   }

   public FindReferencesRequest createFindReferencesRequest() {
      return new FindReferencesRequest();
   }

   public FindReferencesResponse createFindReferencesResponse() {
      return new FindReferencesResponse();
   }

   public AtcClassification createAtcClassification() {
      return new AtcClassification();
   }

   public DeliveryModus createDeliveryModus() {
      return new DeliveryModus();
   }

   public DeliveryModusSpecification createDeliveryModusSpecification() {
      return new DeliveryModusSpecification();
   }

   public DeviceType createDeviceType() {
      return new DeviceType();
   }

   public PackagingClosure createPackagingClosure() {
      return new PackagingClosure();
   }

   public PackagingMaterial createPackagingMaterial() {
      return new PackagingMaterial();
   }

   public PackagingType createPackagingType() {
      return new PackagingType();
   }

   public PharmaceuticalForm createPharmaceuticalForm() {
      return new PharmaceuticalForm();
   }

   public RouteOfAdministration createRouteOfAdministration() {
      return new RouteOfAdministration();
   }

   public Substance createSubstance() {
      return new Substance();
   }

   public NoSwitchReason createNoSwitchReason() {
      return new NoSwitchReason();
   }

   public VirtualForm createVirtualForm() {
      return new VirtualForm();
   }

   public Wada createWada() {
      return new Wada();
   }

   public NoGenericPrescriptionReason createNoGenericPrescriptionReason() {
      return new NoGenericPrescriptionReason();
   }

   public Appendix createAppendix() {
      return new Appendix();
   }

   public FormCategory createFormCategory() {
      return new FormCategory();
   }

   public Parameter createParameter() {
      return new Parameter();
   }

   public ReimbursementCriterion createReimbursementCriterion() {
      return new ReimbursementCriterion();
   }

   public ConsultStandardFormAndPhFrmAndVtlFrmType createConsultStandardFormAndPhFrmAndVtlFrmType() {
      return new ConsultStandardFormAndPhFrmAndVtlFrmType();
   }

   public ConsultStandardRouteAndRouteOfAdministrationType createConsultStandardRouteAndRouteOfAdministrationType() {
      return new ConsultStandardRouteAndRouteOfAdministrationType();
   }

   public ConsultStandardSubstanceAndSubstanceType createConsultStandardSubstanceAndSubstanceType() {
      return new ConsultStandardSubstanceAndSubstanceType();
   }

   public StandardUnitFamhpType createStandardUnitFamhpType() {
      return new StandardUnitFamhpType();
   }

   public GenericReferenceTableType createGenericReferenceTableType() {
      return new GenericReferenceTableType();
   }

   public FindVmpGroupRequest createFindVmpGroupRequest() {
      return new FindVmpGroupRequest();
   }

   public FindVmpGroupResponse createFindVmpGroupResponse() {
      return new FindVmpGroupResponse();
   }

   public ConsultVmpGroupType createConsultVmpGroupType() {
      return new ConsultVmpGroupType();
   }

   public FindVtmRequest createFindVtmRequest() {
      return new FindVtmRequest();
   }

   public FindVtmResponse createFindVtmResponse() {
      return new FindVtmResponse();
   }

   public ConsultVtmType createConsultVtmType() {
      return new ConsultVtmType();
   }

   public FindCommentedClassificationRequest createFindCommentedClassificationRequest() {
      return new FindCommentedClassificationRequest();
   }

   public FindByCommentedClassificationType createFindByCommentedClassificationType() {
      return new FindByCommentedClassificationType();
   }

   public FindCommentedClassificationResponse createFindCommentedClassificationResponse() {
      return new FindCommentedClassificationResponse();
   }

   public ConsultCommentedClassificationTreeType createConsultCommentedClassificationTreeType() {
      return new ConsultCommentedClassificationTreeType();
   }

   public FindCompoundingIngredientRequest createFindCompoundingIngredientRequest() {
      return new FindCompoundingIngredientRequest();
   }

   public FindCompoundingIngredientResponse createFindCompoundingIngredientResponse() {
      return new FindCompoundingIngredientResponse();
   }

   public ConsultCompoundingIngredientType createConsultCompoundingIngredientType() {
      return new ConsultCompoundingIngredientType();
   }

   public FindCompoundingFormulaRequest createFindCompoundingFormulaRequest() {
      return new FindCompoundingFormulaRequest();
   }

   public FindCompoundingFormulaResponse createFindCompoundingFormulaResponse() {
      return new FindCompoundingFormulaResponse();
   }

   public ConsultCompoundingFormulaType createConsultCompoundingFormulaType() {
      return new ConsultCompoundingFormulaType();
   }

   public FindAmppRequest createFindAmppRequest() {
      return new FindAmppRequest();
   }

   public FindAmppResponse createFindAmppResponse() {
      return new FindAmppResponse();
   }

   public ConsultAmppType createConsultAmppType() {
      return new ConsultAmppType();
   }

   public FindNonMedicinalProductRequest createFindNonMedicinalProductRequest() {
      return new FindNonMedicinalProductRequest();
   }

   public FindNonMedicinalProductResponse createFindNonMedicinalProductResponse() {
      return new FindNonMedicinalProductResponse();
   }

   public ConsultNonMedicinalProductType createConsultNonMedicinalProductType() {
      return new ConsultNonMedicinalProductType();
   }

   public ValidateSamIdRequest createValidateSamIdRequest() {
      return new ValidateSamIdRequest();
   }

   public ValidateSamIdResponse createValidateSamIdResponse() {
      return new ValidateSamIdResponse();
   }

   public ValidateProductIdRequest createValidateProductIdRequest() {
      return new ValidateProductIdRequest();
   }

   public ValidateProductIdResponse createValidateProductIdResponse() {
      return new ValidateProductIdResponse();
   }

   public FindByActualProductType createFindByActualProductType() {
      return new FindByActualProductType();
   }

   public FindByCompanyType createFindByCompanyType() {
      return new FindByCompanyType();
   }

   public HasActualComponentWithType createHasActualComponentWithType() {
      return new HasActualComponentWithType();
   }

   public AdditionalFieldsType createAdditionalFieldsType() {
      return new AdditionalFieldsType();
   }

   public AmpListDataType createAmpListDataType() {
      return new AmpListDataType();
   }

   public AppendixType createAppendixType() {
      return new AppendixType();
   }

   public AtcClassificationType createAtcClassificationType() {
      return new AtcClassificationType();
   }

   public ComponentEquivalentType createComponentEquivalentType() {
      return new ComponentEquivalentType();
   }

   public ConsultAdministrationFrequencyType createConsultAdministrationFrequencyType() {
      return new ConsultAdministrationFrequencyType();
   }

   public ConsultAmpComponentType createConsultAmpComponentType() {
      return new ConsultAmpComponentType();
   }

   public ConsultAmppComponentEquivalentType createConsultAmppComponentEquivalentType() {
      return new ConsultAmppComponentEquivalentType();
   }

   public ConsultAmppComponentType createConsultAmppComponentType() {
      return new ConsultAmppComponentType();
   }

   public ConsultAttachmentType createConsultAttachmentType() {
      return new ConsultAttachmentType();
   }

   public ConsultBoundedParameterType createConsultBoundedParameterType() {
      return new ConsultBoundedParameterType();
   }

   public ConsultCommentedClassificationType createConsultCommentedClassificationType() {
      return new ConsultCommentedClassificationType();
   }

   public ConsultCommercializationType createConsultCommercializationType() {
      return new ConsultCommercializationType();
   }

   public ConsultCopaymentType createConsultCopaymentType() {
      return new ConsultCopaymentType();
   }

   public ConsultDerogationImportType createConsultDerogationImportType() {
      return new ConsultDerogationImportType();
   }

   public ConsultDmppType createConsultDmppType() {
      return new ConsultDmppType();
   }

   public ConsultDosageParameterType createConsultDosageParameterType() {
      return new ConsultDosageParameterType();
   }

   public ConsultFormalInterpretationType createConsultFormalInterpretationType() {
      return new ConsultFormalInterpretationType();
   }

   public ConsultIndicationType createConsultIndicationType() {
      return new ConsultIndicationType();
   }

   public ConsultLegalReferenceTraceType createConsultLegalReferenceTraceType() {
      return new ConsultLegalReferenceTraceType();
   }

   public ConsultLegalReferenceType createConsultLegalReferenceType() {
      return new ConsultLegalReferenceType();
   }

   public ConsultLegalTextType createConsultLegalTextType() {
      return new ConsultLegalTextType();
   }

   public ConsultParameterizedQuantityType createConsultParameterizedQuantityType() {
      return new ConsultParameterizedQuantityType();
   }

   public ConsultPricingUnitType createConsultPricingUnitType() {
      return new ConsultPricingUnitType();
   }

   public ConsultRealActualIngredientEquivalentType createConsultRealActualIngredientEquivalentType() {
      return new ConsultRealActualIngredientEquivalentType();
   }

   public ConsultRealActualIngredientType createConsultRealActualIngredientType() {
      return new ConsultRealActualIngredientType();
   }

   public ConsultRealVirtualIngredientType createConsultRealVirtualIngredientType() {
      return new ConsultRealVirtualIngredientType();
   }

   public ConsultReimbursementConditionType createConsultReimbursementConditionType() {
      return new ConsultReimbursementConditionType();
   }

   public ConsultReimbursementTermType createConsultReimbursementTermType() {
      return new ConsultReimbursementTermType();
   }

   public ConsultStandardDosageType createConsultStandardDosageType() {
      return new ConsultStandardDosageType();
   }

   public ConsultStandardFormType createConsultStandardFormType() {
      return new ConsultStandardFormType();
   }

   public ConsultStandardRouteType createConsultStandardRouteType() {
      return new ConsultStandardRouteType();
   }

   public ConsultStandardSubstanceType createConsultStandardSubstanceType() {
      return new ConsultStandardSubstanceType();
   }

   public ConsultSupplyProblemType createConsultSupplyProblemType() {
      return new ConsultSupplyProblemType();
   }

   public ConsultTemporaryDurationDetailsType createConsultTemporaryDurationDetailsType() {
      return new ConsultTemporaryDurationDetailsType();
   }

   public ConsultTextType createConsultTextType() {
      return new ConsultTextType();
   }

   public ConsultVirtualIngredientType createConsultVirtualIngredientType() {
      return new ConsultVirtualIngredientType();
   }

   public ConsultVmpComponentType createConsultVmpComponentType() {
      return new ConsultVmpComponentType();
   }

   public ConsultWadaType createConsultWadaType() {
      return new ConsultWadaType();
   }

   public DeliveryModusSpecificationType createDeliveryModusSpecificationType() {
      return new DeliveryModusSpecificationType();
   }

   public DeliveryModusType createDeliveryModusType() {
      return new DeliveryModusType();
   }

   public DeviceTypeType createDeviceTypeType() {
      return new DeviceTypeType();
   }

   public DomainType createDomainType() {
      return new DomainType();
   }

   public DoseUnitsType createDoseUnitsType() {
      return new DoseUnitsType();
   }

   public FormCategoryType createFormCategoryType() {
      return new FormCategoryType();
   }

   public NoGenericPrescriptionReasonType createNoGenericPrescriptionReasonType() {
      return new NoGenericPrescriptionReasonType();
   }

   public NoSwitchReasonType createNoSwitchReasonType() {
      return new NoSwitchReasonType();
   }

   public PackagingClosureType createPackagingClosureType() {
      return new PackagingClosureType();
   }

   public PackagingMaterialType createPackagingMaterialType() {
      return new PackagingMaterialType();
   }

   public PackagingTypeType createPackagingTypeType() {
      return new PackagingTypeType();
   }

   public PackAmountType createPackAmountType() {
      return new PackAmountType();
   }

   public ParameterValueType createParameterValueType() {
      return new ParameterValueType();
   }

   public PharmaceuticalFormWithStandardsType createPharmaceuticalFormWithStandardsType() {
      return new PharmaceuticalFormWithStandardsType();
   }

   public ReimbursementCriterionType createReimbursementCriterionType() {
      return new ReimbursementCriterionType();
   }

   public RouteOfAdministrationWithStandardsType createRouteOfAdministrationWithStandardsType() {
      return new RouteOfAdministrationWithStandardsType();
   }

   public StandardFormCodeCriterionType createStandardFormCodeCriterionType() {
      return new StandardFormCodeCriterionType();
   }

   public StandardFormNameCriterionType createStandardFormNameCriterionType() {
      return new StandardFormNameCriterionType();
   }

   public StandardRouteCodeCriterionType createStandardRouteCodeCriterionType() {
      return new StandardRouteCodeCriterionType();
   }

   public StandardRouteNameCriterionType createStandardRouteNameCriterionType() {
      return new StandardRouteNameCriterionType();
   }

   public StandardSubstanceCodeCriterionType createStandardSubstanceCodeCriterionType() {
      return new StandardSubstanceCodeCriterionType();
   }

   public StandardSubstanceNameCriterionType createStandardSubstanceNameCriterionType() {
      return new StandardSubstanceNameCriterionType();
   }

   public SubstanceWithStandardsType createSubstanceWithStandardsType() {
      return new SubstanceWithStandardsType();
   }

   public SynonymType createSynonymType() {
      return new SynonymType();
   }

   public VirtualFormWithStandardsType createVirtualFormWithStandardsType() {
      return new VirtualFormWithStandardsType();
   }

   public QuantityType createQuantityType() {
      return new QuantityType();
   }

   public RangeType createRangeType() {
      return new RangeType();
   }

   public StrengthRangeType createStrengthRangeType() {
      return new StrengthRangeType();
   }

   public StrengthType createStrengthType() {
      return new StrengthType();
   }

   public GenericReferenceEntryType createGenericReferenceEntryType() {
      return new GenericReferenceEntryType();
   }

   public Text createText() {
      return new Text();
   }

   @XmlElementDecl(
      namespace = "urn:be:fgov:ehealth:dics:protocol:v5",
      name = "FindAmpRequest"
   )
   public JAXBElement<FindAmpRequestType> createFindAmpRequest(FindAmpRequestType value) {
      return new JAXBElement(_FindAmpRequest_QNAME, FindAmpRequestType.class, (Class)null, value);
   }

   @XmlElementDecl(
      namespace = "urn:be:fgov:ehealth:dics:protocol:v5",
      name = "FindListOfAmpRequest"
   )
   public JAXBElement<FindAmpRequestType> createFindListOfAmpRequest(FindAmpRequestType value) {
      return new JAXBElement(_FindListOfAmpRequest_QNAME, FindAmpRequestType.class, (Class)null, value);
   }
}
