package be.cin.mycarenet._1_0.carenet.types;

import be.ehealth.technicalconnector.adapter.XmlDateNoTzAdapter;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;
import org.joda.time.DateTime;

@XmlRegistry
public class ObjectFactory {
   private static final QName _InsurabilityRequestType_QNAME = new QName("urn:be:cin:mycarenet:1.0:carenet:types", "InsurabilityRequestType");
   private static final QName _InsurabilityContactType_QNAME = new QName("urn:be:cin:mycarenet:1.0:carenet:types", "InsurabilityContactType");
   private static final QName _InsurabilityReference_QNAME = new QName("urn:be:cin:mycarenet:1.0:carenet:types", "InsurabilityReference");
   private static final QName _CareReceiverDetail_QNAME = new QName("urn:be:cin:mycarenet:1.0:carenet:types", "CareReceiverDetail");
   private static final QName _InsuranceOrg_QNAME = new QName("urn:be:cin:mycarenet:1.0:carenet:types", "InsuranceOrg");
   private static final QName _TransferDate_QNAME = new QName("urn:be:cin:mycarenet:1.0:carenet:types", "TransferDate");
   private static final QName _PaymentByIo_QNAME = new QName("urn:be:cin:mycarenet:1.0:carenet:types", "PaymentByIo");
   private static final QName _MaxInvoiced_QNAME = new QName("urn:be:cin:mycarenet:1.0:carenet:types", "MaxInvoiced");
   private static final QName _SpecialSocialCategory_QNAME = new QName("urn:be:cin:mycarenet:1.0:carenet:types", "SpecialSocialCategory");
   private static final QName _Hospital_QNAME = new QName("urn:be:cin:mycarenet:1.0:carenet:types", "Hospital");
   private static final QName _AdmissionDate_QNAME = new QName("urn:be:cin:mycarenet:1.0:carenet:types", "AdmissionDate");
   private static final QName _AdmissionService_QNAME = new QName("urn:be:cin:mycarenet:1.0:carenet:types", "AdmissionService");
   private static final QName _CT1_QNAME = new QName("urn:be:cin:mycarenet:1.0:carenet:types", "CT1");
   private static final QName _CT2_QNAME = new QName("urn:be:cin:mycarenet:1.0:carenet:types", "CT2");
   private static final QName _PaymentApproval_QNAME = new QName("urn:be:cin:mycarenet:1.0:carenet:types", "PaymentApproval");
   private static final QName _InsurabilityDate_QNAME = new QName("urn:be:cin:mycarenet:1.0:carenet:types", "InsurabilityDate");
   private static final QName _MedicalContract_QNAME = new QName("urn:be:cin:mycarenet:1.0:carenet:types", "MedicalContract");
   private static final QName _KineContract_QNAME = new QName("urn:be:cin:mycarenet:1.0:carenet:types", "KineContract");
   private static final QName _NurseContract_QNAME = new QName("urn:be:cin:mycarenet:1.0:carenet:types", "NurseContract");
   private static final QName _OriginalDetailFile_QNAME = new QName("urn:be:cin:mycarenet:1.0:carenet:types", "OriginalDetailFile");

   public ObjectFactory() {
   }

   public InsurabilityRequestList createInsurabilityRequestList() {
      return new InsurabilityRequestList();
   }

   public SingleInsurabilityRequest createSingleInsurabilityRequest() {
      return new SingleInsurabilityRequest();
   }

   public CareReceiverId createCareReceiverId() {
      return new CareReceiverId();
   }

   public InsurabilityRequestDetail createInsurabilityRequestDetail() {
      return new InsurabilityRequestDetail();
   }

   public PeriodType createPeriodType() {
      return new PeriodType();
   }

   public InsurabilityResponseList createInsurabilityResponseList() {
      return new InsurabilityResponseList();
   }

   public SingleInsurabilityResponse createSingleInsurabilityResponse() {
      return new SingleInsurabilityResponse();
   }

   public CareReceiverDetailType createCareReceiverDetailType() {
      return new CareReceiverDetailType();
   }

   public InsurabilityResponseDetail createInsurabilityResponseDetail() {
      return new InsurabilityResponseDetail();
   }

   public GeneralSituation createGeneralSituation() {
      return new GeneralSituation();
   }

   public Transfer createTransfer() {
      return new Transfer();
   }

   public Payment createPayment() {
      return new Payment();
   }

   public Hospitalized createHospitalized() {
      return new Hospitalized();
   }

   public MedicalHouseType createMedicalHouseType() {
      return new MedicalHouseType();
   }

   public InsurabilityList createInsurabilityList() {
      return new InsurabilityList();
   }

   public InsurabilityItem createInsurabilityItem() {
      return new InsurabilityItem();
   }

   public MessageFaultType createMessageFaultType() {
      return new MessageFaultType();
   }

   public MedAdminRequestList createMedAdminRequestList() {
      return new MedAdminRequestList();
   }

   public SingleNurseContractualCareRequest createSingleNurseContractualCareRequest() {
      return new SingleNurseContractualCareRequest();
   }

   public ExtCareReceiverStrictIdType createExtCareReceiverStrictIdType() {
      return new ExtCareReceiverStrictIdType();
   }

   public NurseContractualCareRequestDetail createNurseContractualCareRequestDetail() {
      return new NurseContractualCareRequestDetail();
   }

   public NurseContractualCareDetailType createNurseContractualCareDetailType() {
      return new NurseContractualCareDetailType();
   }

   public SinglePalliativeCareRequest createSinglePalliativeCareRequest() {
      return new SinglePalliativeCareRequest();
   }

   public PalliativeCareDetail createPalliativeCareDetail() {
      return new PalliativeCareDetail();
   }

   public PalliativeCareGroupType createPalliativeCareGroupType() {
      return new PalliativeCareGroupType();
   }

   public PalliativeCareCareProviderType createPalliativeCareCareProviderType() {
      return new PalliativeCareCareProviderType();
   }

   public SingleSpecificTechnicalCareRequest createSingleSpecificTechnicalCareRequest() {
      return new SingleSpecificTechnicalCareRequest();
   }

   public SpecificTechnicalCareDetail createSpecificTechnicalCareDetail() {
      return new SpecificTechnicalCareDetail();
   }

   public MedAdminResponseList createMedAdminResponseList() {
      return new MedAdminResponseList();
   }

   public SingleNurseContractualCareResponse createSingleNurseContractualCareResponse() {
      return new SingleNurseContractualCareResponse();
   }

   public ExtCareReceiverDetailType createExtCareReceiverDetailType() {
      return new ExtCareReceiverDetailType();
   }

   public NurseContractualCareResponseDetail createNurseContractualCareResponseDetail() {
      return new NurseContractualCareResponseDetail();
   }

   public DecisionType createDecisionType() {
      return new DecisionType();
   }

   public SingleNurseContractualCareUpdate createSingleNurseContractualCareUpdate() {
      return new SingleNurseContractualCareUpdate();
   }

   public NurseContractualCareUpdateDetail createNurseContractualCareUpdateDetail() {
      return new NurseContractualCareUpdateDetail();
   }

   public SinglePalliativeCareResponse createSinglePalliativeCareResponse() {
      return new SinglePalliativeCareResponse();
   }

   public PalliativeCareResponseDetail createPalliativeCareResponseDetail() {
      return new PalliativeCareResponseDetail();
   }

   public SingleSpecificTechnicalCareResponse createSingleSpecificTechnicalCareResponse() {
      return new SingleSpecificTechnicalCareResponse();
   }

   public SpecificTechnicalResponseCareDetail createSpecificTechnicalResponseCareDetail() {
      return new SpecificTechnicalResponseCareDetail();
   }

   public CareReceiverStrictIdType createCareReceiverStrictIdType() {
      return new CareReceiverStrictIdType();
   }

   public PeriodLengthType createPeriodLengthType() {
      return new PeriodLengthType();
   }

   public MessageType createMessageType() {
      return new MessageType();
   }

   public DetailsType createDetailsType() {
      return new DetailsType();
   }

   public DetailType createDetailType() {
      return new DetailType();
   }

   public MultiIOType createMultiIOType() {
      return new MultiIOType();
   }

   public ToiletsType createToiletsType() {
      return new ToiletsType();
   }

   public DementiaType createDementiaType() {
      return new DementiaType();
   }

   public KatzType createKatzType() {
      return new KatzType();
   }

   public CarePlaceType createCarePlaceType() {
      return new CarePlaceType();
   }

   public SpecificTechnicalCareTreatmentType createSpecificTechnicalCareTreatmentType() {
      return new SpecificTechnicalCareTreatmentType();
   }

   public ResponseDetailType createResponseDetailType() {
      return new ResponseDetailType();
   }

   public PeriodLength createPeriodLength() {
      return new PeriodLength();
   }

   @XmlElementDecl(
      namespace = "urn:be:cin:mycarenet:1.0:carenet:types",
      name = "InsurabilityRequestType"
   )
   public JAXBElement<InsurabilityRequestTypeType> createInsurabilityRequestType(InsurabilityRequestTypeType value) {
      return new JAXBElement(_InsurabilityRequestType_QNAME, InsurabilityRequestTypeType.class, (Class)null, value);
   }

   @XmlElementDecl(
      namespace = "urn:be:cin:mycarenet:1.0:carenet:types",
      name = "InsurabilityContactType"
   )
   public JAXBElement<InsurabilityContactTypeType> createInsurabilityContactType(InsurabilityContactTypeType value) {
      return new JAXBElement(_InsurabilityContactType_QNAME, InsurabilityContactTypeType.class, (Class)null, value);
   }

   @XmlElementDecl(
      namespace = "urn:be:cin:mycarenet:1.0:carenet:types",
      name = "InsurabilityReference"
   )
   public JAXBElement<String> createInsurabilityReference(String value) {
      return new JAXBElement(_InsurabilityReference_QNAME, String.class, (Class)null, value);
   }

   @XmlElementDecl(
      namespace = "urn:be:cin:mycarenet:1.0:carenet:types",
      name = "CareReceiverDetail"
   )
   public JAXBElement<CareReceiverDetailType> createCareReceiverDetail(CareReceiverDetailType value) {
      return new JAXBElement(_CareReceiverDetail_QNAME, CareReceiverDetailType.class, (Class)null, value);
   }

   @XmlElementDecl(
      namespace = "urn:be:cin:mycarenet:1.0:carenet:types",
      name = "InsuranceOrg"
   )
   public JAXBElement<String> createInsuranceOrg(String value) {
      return new JAXBElement(_InsuranceOrg_QNAME, String.class, (Class)null, value);
   }

   @XmlElementDecl(
      namespace = "urn:be:cin:mycarenet:1.0:carenet:types",
      name = "TransferDate"
   )
   @XmlJavaTypeAdapter(XmlDateNoTzAdapter.class)
   public JAXBElement<DateTime> createTransferDate(DateTime value) {
      return new JAXBElement(_TransferDate_QNAME, DateTime.class, (Class)null, value);
   }

   @XmlElementDecl(
      namespace = "urn:be:cin:mycarenet:1.0:carenet:types",
      name = "PaymentByIo"
   )
   public JAXBElement<Boolean> createPaymentByIo(Boolean value) {
      return new JAXBElement(_PaymentByIo_QNAME, Boolean.class, (Class)null, value);
   }

   @XmlElementDecl(
      namespace = "urn:be:cin:mycarenet:1.0:carenet:types",
      name = "MaxInvoiced"
   )
   public JAXBElement<XMLGregorianCalendar> createMaxInvoiced(XMLGregorianCalendar value) {
      return new JAXBElement(_MaxInvoiced_QNAME, XMLGregorianCalendar.class, (Class)null, value);
   }

   @XmlElementDecl(
      namespace = "urn:be:cin:mycarenet:1.0:carenet:types",
      name = "SpecialSocialCategory"
   )
   public JAXBElement<Boolean> createSpecialSocialCategory(Boolean value) {
      return new JAXBElement(_SpecialSocialCategory_QNAME, Boolean.class, (Class)null, value);
   }

   @XmlElementDecl(
      namespace = "urn:be:cin:mycarenet:1.0:carenet:types",
      name = "Hospital"
   )
   public JAXBElement<String> createHospital(String value) {
      return new JAXBElement(_Hospital_QNAME, String.class, (Class)null, value);
   }

   @XmlElementDecl(
      namespace = "urn:be:cin:mycarenet:1.0:carenet:types",
      name = "AdmissionDate"
   )
   @XmlJavaTypeAdapter(XmlDateNoTzAdapter.class)
   public JAXBElement<DateTime> createAdmissionDate(DateTime value) {
      return new JAXBElement(_AdmissionDate_QNAME, DateTime.class, (Class)null, value);
   }

   @XmlElementDecl(
      namespace = "urn:be:cin:mycarenet:1.0:carenet:types",
      name = "AdmissionService"
   )
   public JAXBElement<String> createAdmissionService(String value) {
      return new JAXBElement(_AdmissionService_QNAME, String.class, (Class)null, value);
   }

   @XmlElementDecl(
      namespace = "urn:be:cin:mycarenet:1.0:carenet:types",
      name = "CT1"
   )
   public JAXBElement<String> createCT1(String value) {
      return new JAXBElement(_CT1_QNAME, String.class, (Class)null, value);
   }

   @XmlElementDecl(
      namespace = "urn:be:cin:mycarenet:1.0:carenet:types",
      name = "CT2"
   )
   public JAXBElement<String> createCT2(String value) {
      return new JAXBElement(_CT2_QNAME, String.class, (Class)null, value);
   }

   @XmlElementDecl(
      namespace = "urn:be:cin:mycarenet:1.0:carenet:types",
      name = "PaymentApproval"
   )
   public JAXBElement<String> createPaymentApproval(String value) {
      return new JAXBElement(_PaymentApproval_QNAME, String.class, (Class)null, value);
   }

   @XmlElementDecl(
      namespace = "urn:be:cin:mycarenet:1.0:carenet:types",
      name = "InsurabilityDate"
   )
   @XmlJavaTypeAdapter(XmlDateNoTzAdapter.class)
   public JAXBElement<DateTime> createInsurabilityDate(DateTime value) {
      return new JAXBElement(_InsurabilityDate_QNAME, DateTime.class, (Class)null, value);
   }

   @XmlElementDecl(
      namespace = "urn:be:cin:mycarenet:1.0:carenet:types",
      name = "MedicalContract"
   )
   public JAXBElement<PeriodType> createMedicalContract(PeriodType value) {
      return new JAXBElement(_MedicalContract_QNAME, PeriodType.class, (Class)null, value);
   }

   @XmlElementDecl(
      namespace = "urn:be:cin:mycarenet:1.0:carenet:types",
      name = "KineContract"
   )
   public JAXBElement<PeriodType> createKineContract(PeriodType value) {
      return new JAXBElement(_KineContract_QNAME, PeriodType.class, (Class)null, value);
   }

   @XmlElementDecl(
      namespace = "urn:be:cin:mycarenet:1.0:carenet:types",
      name = "NurseContract"
   )
   public JAXBElement<PeriodType> createNurseContract(PeriodType value) {
      return new JAXBElement(_NurseContract_QNAME, PeriodType.class, (Class)null, value);
   }

   @XmlElementDecl(
      namespace = "urn:be:cin:mycarenet:1.0:carenet:types",
      name = "OriginalDetailFile"
   )
   public JAXBElement<byte[]> createOriginalDetailFile(byte[] value) {
      return new JAXBElement(_OriginalDetailFile_QNAME, byte[].class, (Class)null, (byte[])value);
   }
}
