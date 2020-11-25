package be.fgov.ehealth.recipe.protocol.v2;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

@XmlRegistry
public class ObjectFactory {
   private static final QName _UploadFilePatientRequest_QNAME = new QName("urn:be:fgov:ehealth:recipe:protocol:v2", "UploadFilePatientRequest");
   private static final QName _UploadFilePrescriberResponse_QNAME = new QName("urn:be:fgov:ehealth:recipe:protocol:v2", "UploadFilePrescriberResponse");
   private static final QName _UploadFilePrescriberRequest_QNAME = new QName("urn:be:fgov:ehealth:recipe:protocol:v2", "UploadFilePrescriberRequest");
   private static final QName _UploadFileExecutorResponse_QNAME = new QName("urn:be:fgov:ehealth:recipe:protocol:v2", "UploadFileExecutorResponse");
   private static final QName _UploadFilePatientResponse_QNAME = new QName("urn:be:fgov:ehealth:recipe:protocol:v2", "UploadFilePatientResponse");
   private static final QName _UploadFileExecutorRequest_QNAME = new QName("urn:be:fgov:ehealth:recipe:protocol:v2", "UploadFileExecutorRequest");

   public GetPrescriptionForPrescriberRequest createGetPrescriptionForPrescriberRequest() {
      return new GetPrescriptionForPrescriberRequest();
   }

   public ListOpenPrescriptionsRequest createListOpenPrescriptionsRequest() {
      return new ListOpenPrescriptionsRequest();
   }

   public ListNotificationsRequest createListNotificationsRequest() {
      return new ListNotificationsRequest();
   }

   public CreatePrescriptionResponse createCreatePrescriptionResponse() {
      return new CreatePrescriptionResponse();
   }

   public GetPrescriptionForPatientResponse createGetPrescriptionForPatientResponse() {
      return new GetPrescriptionForPatientResponse();
   }

   public ListNotificationsResponse createListNotificationsResponse() {
      return new ListNotificationsResponse();
   }

   public ListOpenPrescriptionsResponse createListOpenPrescriptionsResponse() {
      return new ListOpenPrescriptionsResponse();
   }

   public GetPrescriptionForPrescriberResponse createGetPrescriptionForPrescriberResponse() {
      return new GetPrescriptionForPrescriberResponse();
   }

   public ListFeedbacksResponse createListFeedbacksResponse() {
      return new ListFeedbacksResponse();
   }

   public CreateFeedbackRequest createCreateFeedbackRequest() {
      return new CreateFeedbackRequest();
   }

   public AliveCheckResponse createAliveCheckResponse() {
      return new AliveCheckResponse();
   }

   public RevokePrescriptionForExecutorRequest createRevokePrescriptionForExecutorRequest() {
      return new RevokePrescriptionForExecutorRequest();
   }

   public UploadFileResponseType createUploadFileResponseType() {
      return new UploadFileResponseType();
   }

   public UpdateFeedbackFlagRequest createUpdateFeedbackFlagRequest() {
      return new UpdateFeedbackFlagRequest();
   }

   public AliveCheckRequest createAliveCheckRequest() {
      return new AliveCheckRequest();
   }

   public RevokePatientPrescriptionRequest createRevokePatientPrescriptionRequest() {
      return new RevokePatientPrescriptionRequest();
   }

   public ListPatientPrescriptionsRequest createListPatientPrescriptionsRequest() {
      return new ListPatientPrescriptionsRequest();
   }

   public MarkAsUnDeliveredResponse createMarkAsUnDeliveredResponse() {
      return new MarkAsUnDeliveredResponse();
   }

   public MarkAsArchivedRequest createMarkAsArchivedRequest() {
      return new MarkAsArchivedRequest();
   }

   public RevokePatientPrescriptionResponse createRevokePatientPrescriptionResponse() {
      return new RevokePatientPrescriptionResponse();
   }

   public GetPrescriptionForExecutorRequest createGetPrescriptionForExecutorRequest() {
      return new GetPrescriptionForExecutorRequest();
   }

   public RevokePrescriptionRequest createRevokePrescriptionRequest() {
      return new RevokePrescriptionRequest();
   }

   public ListFeedbacksRequest createListFeedbacksRequest() {
      return new ListFeedbacksRequest();
   }

   public CreatePrescriptionRequest createCreatePrescriptionRequest() {
      return new CreatePrescriptionRequest();
   }

   public UpdatePatientFeedbackFlagRequest createUpdatePatientFeedbackFlagRequest() {
      return new UpdatePatientFeedbackFlagRequest();
   }

   public RevokePrescriptionForExecutorResponse createRevokePrescriptionForExecutorResponse() {
      return new RevokePrescriptionForExecutorResponse();
   }

   public MarkAsUnDeliveredRequest createMarkAsUnDeliveredRequest() {
      return new MarkAsUnDeliveredRequest();
   }

   public SendNotificationResponse createSendNotificationResponse() {
      return new SendNotificationResponse();
   }

   public UploadFileRequestType createUploadFileRequestType() {
      return new UploadFileRequestType();
   }

   public GetPrescriptionForExecutorResponse createGetPrescriptionForExecutorResponse() {
      return new GetPrescriptionForExecutorResponse();
   }

   public SendNotificationRequest createSendNotificationRequest() {
      return new SendNotificationRequest();
   }

   public MarkAsDeliveredResponse createMarkAsDeliveredResponse() {
      return new MarkAsDeliveredResponse();
   }

   public ListPatientPrescriptionsResponse createListPatientPrescriptionsResponse() {
      return new ListPatientPrescriptionsResponse();
   }

   public MarkAsArchivedResponse createMarkAsArchivedResponse() {
      return new MarkAsArchivedResponse();
   }

   public UpdateFeedbackFlagResponse createUpdateFeedbackFlagResponse() {
      return new UpdateFeedbackFlagResponse();
   }

   public GetPrescriptionForPatientRequest createGetPrescriptionForPatientRequest() {
      return new GetPrescriptionForPatientRequest();
   }

   public UpdatePatientFeedbackFlagResponse createUpdatePatientFeedbackFlagResponse() {
      return new UpdatePatientFeedbackFlagResponse();
   }

   public RevokePrescriptionResponse createRevokePrescriptionResponse() {
      return new RevokePrescriptionResponse();
   }

   public CreateFeedbackResponse createCreateFeedbackResponse() {
      return new CreateFeedbackResponse();
   }

   public MarkAsDeliveredRequest createMarkAsDeliveredRequest() {
      return new MarkAsDeliveredRequest();
   }

   @XmlElementDecl(
      namespace = "urn:be:fgov:ehealth:recipe:protocol:v2",
      name = "UploadFilePatientRequest"
   )
   public JAXBElement<UploadFileRequestType> createUploadFilePatientRequest(UploadFileRequestType value) {
      return new JAXBElement(_UploadFilePatientRequest_QNAME, UploadFileRequestType.class, (Class)null, value);
   }

   @XmlElementDecl(
      namespace = "urn:be:fgov:ehealth:recipe:protocol:v2",
      name = "UploadFilePrescriberResponse"
   )
   public JAXBElement<UploadFileResponseType> createUploadFilePrescriberResponse(UploadFileResponseType value) {
      return new JAXBElement(_UploadFilePrescriberResponse_QNAME, UploadFileResponseType.class, (Class)null, value);
   }

   @XmlElementDecl(
      namespace = "urn:be:fgov:ehealth:recipe:protocol:v2",
      name = "UploadFilePrescriberRequest"
   )
   public JAXBElement<UploadFileRequestType> createUploadFilePrescriberRequest(UploadFileRequestType value) {
      return new JAXBElement(_UploadFilePrescriberRequest_QNAME, UploadFileRequestType.class, (Class)null, value);
   }

   @XmlElementDecl(
      namespace = "urn:be:fgov:ehealth:recipe:protocol:v2",
      name = "UploadFileExecutorResponse"
   )
   public JAXBElement<UploadFileResponseType> createUploadFileExecutorResponse(UploadFileResponseType value) {
      return new JAXBElement(_UploadFileExecutorResponse_QNAME, UploadFileResponseType.class, (Class)null, value);
   }

   @XmlElementDecl(
      namespace = "urn:be:fgov:ehealth:recipe:protocol:v2",
      name = "UploadFilePatientResponse"
   )
   public JAXBElement<UploadFileResponseType> createUploadFilePatientResponse(UploadFileResponseType value) {
      return new JAXBElement(_UploadFilePatientResponse_QNAME, UploadFileResponseType.class, (Class)null, value);
   }

   @XmlElementDecl(
      namespace = "urn:be:fgov:ehealth:recipe:protocol:v2",
      name = "UploadFileExecutorRequest"
   )
   public JAXBElement<UploadFileRequestType> createUploadFileExecutorRequest(UploadFileRequestType value) {
      return new JAXBElement(_UploadFileExecutorRequest_QNAME, UploadFileRequestType.class, (Class)null, value);
   }
}
