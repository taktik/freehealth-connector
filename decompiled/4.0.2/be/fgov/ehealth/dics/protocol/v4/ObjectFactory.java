package be.fgov.ehealth.dics.protocol.v4;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

@XmlRegistry
public class ObjectFactory {
   private static final QName _GetListOfVirtualMedicinalProductsRequest_QNAME = new QName("urn:be:fgov:ehealth:dics:protocol:v4", "GetListOfVirtualMedicinalProductsRequest");
   private static final QName _GetListOfVmpGroupsRequest_QNAME = new QName("urn:be:fgov:ehealth:dics:protocol:v4", "GetListOfVmpGroupsRequest");
   private static final QName _GetListOfActualMedicinalProductsRequest_QNAME = new QName("urn:be:fgov:ehealth:dics:protocol:v4", "GetListOfActualMedicinalProductsRequest");
   private static final QName _GetListOfActualMedicinalProductPackagesRequest_QNAME = new QName("urn:be:fgov:ehealth:dics:protocol:v4", "GetListOfActualMedicinalProductPackagesRequest");

   public ObjectFactory() {
   }

   public FindAmpRequest createFindAmpRequest() {
      return new FindAmpRequest();
   }

   public FindByActualProductType createFindByActualProductType() {
      return new FindByActualProductType();
   }

   public FindByPackageType createFindByPackageType() {
      return new FindByPackageType();
   }

   public FindByIngredientType createFindByIngredientType() {
      return new FindByIngredientType();
   }

   public FindByVirtualProductType createFindByVirtualProductType() {
      return new FindByVirtualProductType();
   }

   public FindByGenericPrescriptionGroupType createFindByGenericPrescriptionGroupType() {
      return new FindByGenericPrescriptionGroupType();
   }

   public FindByCompanyType createFindByCompanyType() {
      return new FindByCompanyType();
   }

   public HasActualComponentWithType createHasActualComponentWithType() {
      return new HasActualComponentWithType();
   }

   public FindAmpResponse createFindAmpResponse() {
      return new FindAmpResponse();
   }

   public ConsultAmpType createConsultAmpType() {
      return new ConsultAmpType();
   }

   public FindCompanyRequest createFindCompanyRequest() {
      return new FindCompanyRequest();
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

   public FindByTherapeuticMoietyType createFindByTherapeuticMoietyType() {
      return new FindByTherapeuticMoietyType();
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

   public ListConsultationRequestType createListConsultationRequestType() {
      return new ListConsultationRequestType();
   }

   public GetListOfVirtualMedicinalProductsResponse createGetListOfVirtualMedicinalProductsResponse() {
      return new GetListOfVirtualMedicinalProductsResponse();
   }

   public ListConsultationResponseType createListConsultationResponseType() {
      return new ListConsultationResponseType();
   }

   public VmpListType createVmpListType() {
      return new VmpListType();
   }

   public GetListOfVmpGroupsResponse createGetListOfVmpGroupsResponse() {
      return new GetListOfVmpGroupsResponse();
   }

   public VmpGroupListType createVmpGroupListType() {
      return new VmpGroupListType();
   }

   public GetListOfActualMedicinalProductsResponse createGetListOfActualMedicinalProductsResponse() {
      return new GetListOfActualMedicinalProductsResponse();
   }

   public AmpListType createAmpListType() {
      return new AmpListType();
   }

   public GetListOfActualMedicinalProductPackagesResponse createGetListOfActualMedicinalProductPackagesResponse() {
      return new GetListOfActualMedicinalProductPackagesResponse();
   }

   public AmppListType createAmppListType() {
      return new AmppListType();
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

   public Anomaly createAnomaly() {
      return new Anomaly();
   }

   public ConsultPricingUnitType createConsultPricingUnitType() {
      return new ConsultPricingUnitType();
   }

   public ConsultTextType createConsultTextType() {
      return new ConsultTextType();
   }

   public WadaType createWadaType() {
      return new WadaType();
   }

   public ConsultCommentedClassificationType createConsultCommentedClassificationType() {
      return new ConsultCommentedClassificationType();
   }

   public ConsultVmpComponentType createConsultVmpComponentType() {
      return new ConsultVmpComponentType();
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

   public ReimbursementCriterionType createReimbursementCriterionType() {
      return new ReimbursementCriterionType();
   }

   public ConsultCopaymentType createConsultCopaymentType() {
      return new ConsultCopaymentType();
   }

   public ComponentEquivalentType createComponentEquivalentType() {
      return new ComponentEquivalentType();
   }

   public ConsultRecursiveLegalReferenceType createConsultRecursiveLegalReferenceType() {
      return new ConsultRecursiveLegalReferenceType();
   }

   public StandardSubstanceCodeCriterionType createStandardSubstanceCodeCriterionType() {
      return new StandardSubstanceCodeCriterionType();
   }

   public StandardSubstanceNameCriterionType createStandardSubstanceNameCriterionType() {
      return new StandardSubstanceNameCriterionType();
   }

   public ConsultAmpComponentType createConsultAmpComponentType() {
      return new ConsultAmpComponentType();
   }

   public NoGenericPrescriptionReasonType createNoGenericPrescriptionReasonType() {
      return new NoGenericPrescriptionReasonType();
   }

   public NoSwitchReasonType createNoSwitchReasonType() {
      return new NoSwitchReasonType();
   }

   public VirtualFormWithStandardsType createVirtualFormWithStandardsType() {
      return new VirtualFormWithStandardsType();
   }

   public ConsultStandardFormType createConsultStandardFormType() {
      return new ConsultStandardFormType();
   }

   public RouteOfAdministrationWithStandardsType createRouteOfAdministrationWithStandardsType() {
      return new RouteOfAdministrationWithStandardsType();
   }

   public ConsultVirtualIngredientType createConsultVirtualIngredientType() {
      return new ConsultVirtualIngredientType();
   }

   public ConsultLegalReferenceType createConsultLegalReferenceType() {
      return new ConsultLegalReferenceType();
   }

   public ConsultFormalInterpretationType createConsultFormalInterpretationType() {
      return new ConsultFormalInterpretationType();
   }

   public ConsultReimbursementConditionType createConsultReimbursementConditionType() {
      return new ConsultReimbursementConditionType();
   }

   public ConsultReimbursementTermType createConsultReimbursementTermType() {
      return new ConsultReimbursementTermType();
   }

   public ConsultRecursiveLegalTextType createConsultRecursiveLegalTextType() {
      return new ConsultRecursiveLegalTextType();
   }

   public PharmaceuticalFormWithStandardsType createPharmaceuticalFormWithStandardsType() {
      return new PharmaceuticalFormWithStandardsType();
   }

   public ConsultRealActualIngredientType createConsultRealActualIngredientType() {
      return new ConsultRealActualIngredientType();
   }

   public SubstanceWithStandardsType createSubstanceWithStandardsType() {
      return new SubstanceWithStandardsType();
   }

   public ConsultRealActualIngredientEquivalentType createConsultRealActualIngredientEquivalentType() {
      return new ConsultRealActualIngredientEquivalentType();
   }

   public AtcClassificationType createAtcClassificationType() {
      return new AtcClassificationType();
   }

   public DeliveryModusType createDeliveryModusType() {
      return new DeliveryModusType();
   }

   public DeliveryModusSpecificationType createDeliveryModusSpecificationType() {
      return new DeliveryModusSpecificationType();
   }

   public ConsultAmppComponentType createConsultAmppComponentType() {
      return new ConsultAmppComponentType();
   }

   public ConsultCommercializationType createConsultCommercializationType() {
      return new ConsultCommercializationType();
   }

   public ConsultSupplyProblemType createConsultSupplyProblemType() {
      return new ConsultSupplyProblemType();
   }

   public ConsultDmppType createConsultDmppType() {
      return new ConsultDmppType();
   }

   public ConsultStandardRouteType createConsultStandardRouteType() {
      return new ConsultStandardRouteType();
   }

   public ConsultRealVirtualIngredientType createConsultRealVirtualIngredientType() {
      return new ConsultRealVirtualIngredientType();
   }

   public ConsultLegalReferenceTraceType createConsultLegalReferenceTraceType() {
      return new ConsultLegalReferenceTraceType();
   }

   public ConsultAttachmentType createConsultAttachmentType() {
      return new ConsultAttachmentType();
   }

   public ConsultStandardSubstanceType createConsultStandardSubstanceType() {
      return new ConsultStandardSubstanceType();
   }

   public DeviceTypeType createDeviceTypeType() {
      return new DeviceTypeType();
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

   public ConsultAmppComponentEquivalentType createConsultAmppComponentEquivalentType() {
      return new ConsultAmppComponentEquivalentType();
   }

   public ConsultDerogationImportType createConsultDerogationImportType() {
      return new ConsultDerogationImportType();
   }

   public AppendixType createAppendixType() {
      return new AppendixType();
   }

   public FormCategoryType createFormCategoryType() {
      return new FormCategoryType();
   }

   public DomainType createDomainType() {
      return new DomainType();
   }

   public VmpListDataType createVmpListDataType() {
      return new VmpListDataType();
   }

   public VmpGroupListDataType createVmpGroupListDataType() {
      return new VmpGroupListDataType();
   }

   public AmpListDataType createAmpListDataType() {
      return new AmpListDataType();
   }

   public AmppListDataType createAmppListDataType() {
      return new AmppListDataType();
   }

   public Text createText() {
      return new Text();
   }

   @XmlElementDecl(
      namespace = "urn:be:fgov:ehealth:dics:protocol:v4",
      name = "GetListOfVirtualMedicinalProductsRequest"
   )
   public JAXBElement<ListConsultationRequestType> createGetListOfVirtualMedicinalProductsRequest(ListConsultationRequestType value) {
      return new JAXBElement(_GetListOfVirtualMedicinalProductsRequest_QNAME, ListConsultationRequestType.class, (Class)null, value);
   }

   @XmlElementDecl(
      namespace = "urn:be:fgov:ehealth:dics:protocol:v4",
      name = "GetListOfVmpGroupsRequest"
   )
   public JAXBElement<ListConsultationRequestType> createGetListOfVmpGroupsRequest(ListConsultationRequestType value) {
      return new JAXBElement(_GetListOfVmpGroupsRequest_QNAME, ListConsultationRequestType.class, (Class)null, value);
   }

   @XmlElementDecl(
      namespace = "urn:be:fgov:ehealth:dics:protocol:v4",
      name = "GetListOfActualMedicinalProductsRequest"
   )
   public JAXBElement<ListConsultationRequestType> createGetListOfActualMedicinalProductsRequest(ListConsultationRequestType value) {
      return new JAXBElement(_GetListOfActualMedicinalProductsRequest_QNAME, ListConsultationRequestType.class, (Class)null, value);
   }

   @XmlElementDecl(
      namespace = "urn:be:fgov:ehealth:dics:protocol:v4",
      name = "GetListOfActualMedicinalProductPackagesRequest"
   )
   public JAXBElement<ListConsultationRequestType> createGetListOfActualMedicinalProductPackagesRequest(ListConsultationRequestType value) {
      return new JAXBElement(_GetListOfActualMedicinalProductPackagesRequest_QNAME, ListConsultationRequestType.class, (Class)null, value);
   }
}
