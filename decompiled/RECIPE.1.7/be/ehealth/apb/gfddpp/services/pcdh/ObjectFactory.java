package be.ehealth.apb.gfddpp.services.pcdh;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

@XmlRegistry
public class ObjectFactory {
   private static final QName _GetProductFilterRequest_QNAME = new QName("urn:be:fgov:ehealth:gfddpp:protocol:v1", "GetProductFilterRequest");
   private static final QName _GetDataTypesResponse_QNAME = new QName("urn:be:fgov:ehealth:gfddpp:protocol:v1", "GetDataTypesResponse");
   private static final QName _GetPharmacyDetailsRequest_QNAME = new QName("urn:be:fgov:ehealth:gfddpp:protocol:v1", "GetPharmacyDetailsRequest");
   private static final QName _GetSystemServicesRequest_QNAME = new QName("urn:be:fgov:ehealth:gfddpp:protocol:v1", "GetSystemServicesRequest");
   private static final QName _CheckAliveTIPRequest_QNAME = new QName("urn:be:fgov:ehealth:gfddpp:protocol:v1", "CheckAliveTIPRequest");
   private static final QName _SendStatusMessagesRequest_QNAME = new QName("urn:be:fgov:ehealth:gfddpp:protocol:v1", "SendStatusMessagesRequest");
   private static final QName _GetProductFilterResponse_QNAME = new QName("urn:be:fgov:ehealth:gfddpp:protocol:v1", "GetProductFilterResponse");
   private static final QName _GetDataRequest_QNAME = new QName("urn:be:fgov:ehealth:gfddpp:protocol:v1", "GetDataRequest");
   private static final QName _CheckAlivePCDHRequest_QNAME = new QName("urn:be:fgov:ehealth:gfddpp:protocol:v1", "CheckAlivePCDHRequest");
   private static final QName _RegisterDataResponse_QNAME = new QName("urn:be:fgov:ehealth:gfddpp:protocol:v1", "RegisterDataResponse");
   private static final QName _DeleteDataRequest_QNAME = new QName("urn:be:fgov:ehealth:gfddpp:protocol:v1", "DeleteDataRequest");
   private static final QName _DeleteDataResponse_QNAME = new QName("urn:be:fgov:ehealth:gfddpp:protocol:v1", "DeleteDataResponse");
   private static final QName _RegisterDataRequest_QNAME = new QName("urn:be:fgov:ehealth:gfddpp:protocol:v1", "RegisterDataRequest");
   private static final QName _CheckAliveTIPResponse_QNAME = new QName("urn:be:fgov:ehealth:gfddpp:protocol:v1", "CheckAliveTIPResponse");
   private static final QName _GetDataTypesRequest_QNAME = new QName("urn:be:fgov:ehealth:gfddpp:protocol:v1", "GetDataTypesRequest");
   private static final QName _UploadPerformanceMetricResponse_QNAME = new QName("urn:be:fgov:ehealth:gfddpp:protocol:v1", "UploadPerformanceMetricResponse");
   private static final QName _UploadPerformanceMetricRequest_QNAME = new QName("urn:be:fgov:ehealth:gfddpp:protocol:v1", "UploadPerformanceMetricRequest");
   private static final QName _UpdateDataRequest_QNAME = new QName("urn:be:fgov:ehealth:gfddpp:protocol:v1", "UpdateDataRequest");
   private static final QName _UpdateDataResponse_QNAME = new QName("urn:be:fgov:ehealth:gfddpp:protocol:v1", "UpdateDataResponse");
   private static final QName _RetrieveStatusMessagesResponse_QNAME = new QName("urn:be:fgov:ehealth:gfddpp:protocol:v1", "RetrieveStatusMessagesResponse");
   private static final QName _RetrieveStatusMessagesRequest_QNAME = new QName("urn:be:fgov:ehealth:gfddpp:protocol:v1", "RetrieveStatusMessagesRequest");
   private static final QName _SendStatusMessagesResponse_QNAME = new QName("urn:be:fgov:ehealth:gfddpp:protocol:v1", "SendStatusMessagesResponse");
   private static final QName _GetSystemServicesResponse_QNAME = new QName("urn:be:fgov:ehealth:gfddpp:protocol:v1", "GetSystemServicesResponse");
   private static final QName _GetDataResponse_QNAME = new QName("urn:be:fgov:ehealth:gfddpp:protocol:v1", "GetDataResponse");
   private static final QName _GetPharmacyDetailsResponse_QNAME = new QName("urn:be:fgov:ehealth:gfddpp:protocol:v1", "GetPharmacyDetailsResponse");
   private static final QName _CheckAlivePCDHResponse_QNAME = new QName("urn:be:fgov:ehealth:gfddpp:protocol:v1", "CheckAlivePCDHResponse");

   public RoutedCheckAliveResponseType createRoutedCheckAliveResponseType() {
      return new RoutedCheckAliveResponseType();
   }

   public AddressType.Country createAddressTypeCountry() {
      return new AddressType.Country();
   }

   public LocalisedString createLocalisedString() {
      return new LocalisedString();
   }

   public RequestType createRequestType() {
      return new RequestType();
   }

   public AddressType.Municipality createAddressTypeMunicipality() {
      return new AddressType.Municipality();
   }

   public IdentifierType createIdentifierType() {
      return new IdentifierType();
   }

   public SealedResponseType createSealedResponseType() {
      return new SealedResponseType();
   }

   public SealedRequestType createSealedRequestType() {
      return new SealedRequestType();
   }

   public SimpleResponseType createSimpleResponseType() {
      return new SimpleResponseType();
   }

   public TherapeuticalRelationShipType createTherapeuticalRelationShipType() {
      return new TherapeuticalRelationShipType();
   }

   public CheckAliveRequestType createCheckAliveRequestType() {
      return new CheckAliveRequestType();
   }

   public ErrorType createErrorType() {
      return new ErrorType();
   }

   public RoutingParametersType createRoutingParametersType() {
      return new RoutingParametersType();
   }

   public StatusTypeType createStatusTypeType() {
      return new StatusTypeType();
   }

   public PeriodType createPeriodType() {
      return new PeriodType();
   }

   public SOAErrorType createSOAErrorType() {
      return new SOAErrorType();
   }

   public SealedMessageRequestType createSealedMessageRequestType() {
      return new SealedMessageRequestType();
   }

   public PatientConsentType createPatientConsentType() {
      return new PatientConsentType();
   }

   public StatusType createStatusType() {
      return new StatusType();
   }

   public PatientSignatureType createPatientSignatureType() {
      return new PatientSignatureType();
   }

   public RoutedSealedResponseType createRoutedSealedResponseType() {
      return new RoutedSealedResponseType();
   }

   public AuthorizationParametersType createAuthorizationParametersType() {
      return new AuthorizationParametersType();
   }

   public RoutedSealedRequestType createRoutedSealedRequestType() {
      return new RoutedSealedRequestType();
   }

   public CheckAliveResponseType createCheckAliveResponseType() {
      return new CheckAliveResponseType();
   }

   public AddressType createAddressType() {
      return new AddressType();
   }

   public LocalisedStringType createLocalisedStringType() {
      return new LocalisedStringType();
   }

   public UploadPerformanceMetricRequestType createUploadPerformanceMetricRequestType() {
      return new UploadPerformanceMetricRequestType();
   }

   public BusinessError createBusinessError() {
      return new BusinessError();
   }

   public AddressType.Street createAddressTypeStreet() {
      return new AddressType.Street();
   }

   public SystemError createSystemError() {
      return new SystemError();
   }

   public ResponseType createResponseType() {
      return new ResponseType();
   }

   @XmlElementDecl(
      namespace = "urn:be:fgov:ehealth:gfddpp:protocol:v1",
      name = "GetProductFilterRequest"
   )
   public JAXBElement<RoutedSealedRequestType> createGetProductFilterRequest(RoutedSealedRequestType var1) {
      return new JAXBElement(_GetProductFilterRequest_QNAME, RoutedSealedRequestType.class, (Class)null, var1);
   }

   @XmlElementDecl(
      namespace = "urn:be:fgov:ehealth:gfddpp:protocol:v1",
      name = "GetDataTypesResponse"
   )
   public JAXBElement<SealedResponseType> createGetDataTypesResponse(SealedResponseType var1) {
      return new JAXBElement(_GetDataTypesResponse_QNAME, SealedResponseType.class, (Class)null, var1);
   }

   @XmlElementDecl(
      namespace = "urn:be:fgov:ehealth:gfddpp:protocol:v1",
      name = "GetPharmacyDetailsRequest"
   )
   public JAXBElement<SealedRequestType> createGetPharmacyDetailsRequest(SealedRequestType var1) {
      return new JAXBElement(_GetPharmacyDetailsRequest_QNAME, SealedRequestType.class, (Class)null, var1);
   }

   @XmlElementDecl(
      namespace = "urn:be:fgov:ehealth:gfddpp:protocol:v1",
      name = "GetSystemServicesRequest"
   )
   public JAXBElement<RoutedSealedRequestType> createGetSystemServicesRequest(RoutedSealedRequestType var1) {
      return new JAXBElement(_GetSystemServicesRequest_QNAME, RoutedSealedRequestType.class, (Class)null, var1);
   }

   @XmlElementDecl(
      namespace = "urn:be:fgov:ehealth:gfddpp:protocol:v1",
      name = "CheckAliveTIPRequest"
   )
   public JAXBElement<CheckAliveRequestType> createCheckAliveTIPRequest(CheckAliveRequestType var1) {
      return new JAXBElement(_CheckAliveTIPRequest_QNAME, CheckAliveRequestType.class, (Class)null, var1);
   }

   @XmlElementDecl(
      namespace = "urn:be:fgov:ehealth:gfddpp:protocol:v1",
      name = "SendStatusMessagesRequest"
   )
   public JAXBElement<SealedMessageRequestType> createSendStatusMessagesRequest(SealedMessageRequestType var1) {
      return new JAXBElement(_SendStatusMessagesRequest_QNAME, SealedMessageRequestType.class, (Class)null, var1);
   }

   @XmlElementDecl(
      namespace = "urn:be:fgov:ehealth:gfddpp:protocol:v1",
      name = "GetProductFilterResponse"
   )
   public JAXBElement<RoutedSealedResponseType> createGetProductFilterResponse(RoutedSealedResponseType var1) {
      return new JAXBElement(_GetProductFilterResponse_QNAME, RoutedSealedResponseType.class, (Class)null, var1);
   }

   @XmlElementDecl(
      namespace = "urn:be:fgov:ehealth:gfddpp:protocol:v1",
      name = "GetDataRequest"
   )
   public JAXBElement<SealedRequestType> createGetDataRequest(SealedRequestType var1) {
      return new JAXBElement(_GetDataRequest_QNAME, SealedRequestType.class, (Class)null, var1);
   }

   @XmlElementDecl(
      namespace = "urn:be:fgov:ehealth:gfddpp:protocol:v1",
      name = "CheckAlivePCDHRequest"
   )
   public JAXBElement<CheckAliveRequestType> createCheckAlivePCDHRequest(CheckAliveRequestType var1) {
      return new JAXBElement(_CheckAlivePCDHRequest_QNAME, CheckAliveRequestType.class, (Class)null, var1);
   }

   @XmlElementDecl(
      namespace = "urn:be:fgov:ehealth:gfddpp:protocol:v1",
      name = "RegisterDataResponse"
   )
   public JAXBElement<SimpleResponseType> createRegisterDataResponse(SimpleResponseType var1) {
      return new JAXBElement(_RegisterDataResponse_QNAME, SimpleResponseType.class, (Class)null, var1);
   }

   @XmlElementDecl(
      namespace = "urn:be:fgov:ehealth:gfddpp:protocol:v1",
      name = "DeleteDataRequest"
   )
   public JAXBElement<SealedMessageRequestType> createDeleteDataRequest(SealedMessageRequestType var1) {
      return new JAXBElement(_DeleteDataRequest_QNAME, SealedMessageRequestType.class, (Class)null, var1);
   }

   @XmlElementDecl(
      namespace = "urn:be:fgov:ehealth:gfddpp:protocol:v1",
      name = "DeleteDataResponse"
   )
   public JAXBElement<SimpleResponseType> createDeleteDataResponse(SimpleResponseType var1) {
      return new JAXBElement(_DeleteDataResponse_QNAME, SimpleResponseType.class, (Class)null, var1);
   }

   @XmlElementDecl(
      namespace = "urn:be:fgov:ehealth:gfddpp:protocol:v1",
      name = "RegisterDataRequest"
   )
   public JAXBElement<SealedMessageRequestType> createRegisterDataRequest(SealedMessageRequestType var1) {
      return new JAXBElement(_RegisterDataRequest_QNAME, SealedMessageRequestType.class, (Class)null, var1);
   }

   @XmlElementDecl(
      namespace = "urn:be:fgov:ehealth:gfddpp:protocol:v1",
      name = "CheckAliveTIPResponse"
   )
   public JAXBElement<RoutedCheckAliveResponseType> createCheckAliveTIPResponse(RoutedCheckAliveResponseType var1) {
      return new JAXBElement(_CheckAliveTIPResponse_QNAME, RoutedCheckAliveResponseType.class, (Class)null, var1);
   }

   @XmlElementDecl(
      namespace = "urn:be:fgov:ehealth:gfddpp:protocol:v1",
      name = "GetDataTypesRequest"
   )
   public JAXBElement<SealedRequestType> createGetDataTypesRequest(SealedRequestType var1) {
      return new JAXBElement(_GetDataTypesRequest_QNAME, SealedRequestType.class, (Class)null, var1);
   }

   @XmlElementDecl(
      namespace = "urn:be:fgov:ehealth:gfddpp:protocol:v1",
      name = "UploadPerformanceMetricResponse"
   )
   public JAXBElement<ResponseType> createUploadPerformanceMetricResponse(ResponseType var1) {
      return new JAXBElement(_UploadPerformanceMetricResponse_QNAME, ResponseType.class, (Class)null, var1);
   }

   @XmlElementDecl(
      namespace = "urn:be:fgov:ehealth:gfddpp:protocol:v1",
      name = "UploadPerformanceMetricRequest"
   )
   public JAXBElement<UploadPerformanceMetricRequestType> createUploadPerformanceMetricRequest(UploadPerformanceMetricRequestType var1) {
      return new JAXBElement(_UploadPerformanceMetricRequest_QNAME, UploadPerformanceMetricRequestType.class, (Class)null, var1);
   }

   @XmlElementDecl(
      namespace = "urn:be:fgov:ehealth:gfddpp:protocol:v1",
      name = "UpdateDataRequest"
   )
   public JAXBElement<SealedMessageRequestType> createUpdateDataRequest(SealedMessageRequestType var1) {
      return new JAXBElement(_UpdateDataRequest_QNAME, SealedMessageRequestType.class, (Class)null, var1);
   }

   @XmlElementDecl(
      namespace = "urn:be:fgov:ehealth:gfddpp:protocol:v1",
      name = "UpdateDataResponse"
   )
   public JAXBElement<SimpleResponseType> createUpdateDataResponse(SimpleResponseType var1) {
      return new JAXBElement(_UpdateDataResponse_QNAME, SimpleResponseType.class, (Class)null, var1);
   }

   @XmlElementDecl(
      namespace = "urn:be:fgov:ehealth:gfddpp:protocol:v1",
      name = "RetrieveStatusMessagesResponse"
   )
   public JAXBElement<RoutedSealedResponseType> createRetrieveStatusMessagesResponse(RoutedSealedResponseType var1) {
      return new JAXBElement(_RetrieveStatusMessagesResponse_QNAME, RoutedSealedResponseType.class, (Class)null, var1);
   }

   @XmlElementDecl(
      namespace = "urn:be:fgov:ehealth:gfddpp:protocol:v1",
      name = "RetrieveStatusMessagesRequest"
   )
   public JAXBElement<RoutedSealedRequestType> createRetrieveStatusMessagesRequest(RoutedSealedRequestType var1) {
      return new JAXBElement(_RetrieveStatusMessagesRequest_QNAME, RoutedSealedRequestType.class, (Class)null, var1);
   }

   @XmlElementDecl(
      namespace = "urn:be:fgov:ehealth:gfddpp:protocol:v1",
      name = "SendStatusMessagesResponse"
   )
   public JAXBElement<SimpleResponseType> createSendStatusMessagesResponse(SimpleResponseType var1) {
      return new JAXBElement(_SendStatusMessagesResponse_QNAME, SimpleResponseType.class, (Class)null, var1);
   }

   @XmlElementDecl(
      namespace = "urn:be:fgov:ehealth:gfddpp:protocol:v1",
      name = "GetSystemServicesResponse"
   )
   public JAXBElement<RoutedSealedResponseType> createGetSystemServicesResponse(RoutedSealedResponseType var1) {
      return new JAXBElement(_GetSystemServicesResponse_QNAME, RoutedSealedResponseType.class, (Class)null, var1);
   }

   @XmlElementDecl(
      namespace = "urn:be:fgov:ehealth:gfddpp:protocol:v1",
      name = "GetDataResponse"
   )
   public JAXBElement<SealedResponseType> createGetDataResponse(SealedResponseType var1) {
      return new JAXBElement(_GetDataResponse_QNAME, SealedResponseType.class, (Class)null, var1);
   }

   @XmlElementDecl(
      namespace = "urn:be:fgov:ehealth:gfddpp:protocol:v1",
      name = "GetPharmacyDetailsResponse"
   )
   public JAXBElement<SealedResponseType> createGetPharmacyDetailsResponse(SealedResponseType var1) {
      return new JAXBElement(_GetPharmacyDetailsResponse_QNAME, SealedResponseType.class, (Class)null, var1);
   }

   @XmlElementDecl(
      namespace = "urn:be:fgov:ehealth:gfddpp:protocol:v1",
      name = "CheckAlivePCDHResponse"
   )
   public JAXBElement<CheckAliveResponseType> createCheckAlivePCDHResponse(CheckAliveResponseType var1) {
      return new JAXBElement(_CheckAlivePCDHResponse_QNAME, CheckAliveResponseType.class, (Class)null, var1);
   }
}
