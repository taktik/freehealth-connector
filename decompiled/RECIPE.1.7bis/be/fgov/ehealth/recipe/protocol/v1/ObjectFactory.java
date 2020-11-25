package be.fgov.ehealth.recipe.protocol.v1;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

@XmlRegistry
public class ObjectFactory {
   private static final QName _UploadFilePatientResponse_QNAME = new QName("urn:be:fgov:ehealth:recipe:protocol:v1", "UploadFilePatientResponse");
   private static final QName _UploadFileExecutorResponse_QNAME = new QName("urn:be:fgov:ehealth:recipe:protocol:v1", "UploadFileExecutorResponse");
   private static final QName _UploadFilePatientRequest_QNAME = new QName("urn:be:fgov:ehealth:recipe:protocol:v1", "UploadFilePatientRequest");
   private static final QName _UploadFileExecutorRequest_QNAME = new QName("urn:be:fgov:ehealth:recipe:protocol:v1", "UploadFileExecutorRequest");
   private static final QName _UploadFilePrescriberRequest_QNAME = new QName("urn:be:fgov:ehealth:recipe:protocol:v1", "UploadFilePrescriberRequest");
   private static final QName _UploadFilePrescriberResponse_QNAME = new QName("urn:be:fgov:ehealth:recipe:protocol:v1", "UploadFilePrescriberResponse");

   public SendNotificationResponse createSendNotificationResponse() {
      return new SendNotificationResponse();
   }

   public CreateFeedbackRequest createCreateFeedbackRequest() {
      return new CreateFeedbackRequest();
   }

   public RevokePrescriptionForExecutorRequest createRevokePrescriptionForExecutorRequest() {
      return new RevokePrescriptionForExecutorRequest();
   }

   public CreatePrescriptionResponse createCreatePrescriptionResponse() {
      return new CreatePrescriptionResponse();
   }

   public AliveCheckRequest createAliveCheckRequest() {
      return new AliveCheckRequest();
   }

   public RevokePrescriptionForExecutorResponse createRevokePrescriptionForExecutorResponse() {
      return new RevokePrescriptionForExecutorResponse();
   }

   public UpdatePatientFeedbackFlagResponse createUpdatePatientFeedbackFlagResponse() {
      return new UpdatePatientFeedbackFlagResponse();
   }

   public MarkAsArchivedResponse createMarkAsArchivedResponse() {
      return new MarkAsArchivedResponse();
   }

   public UploadFileResponseType createUploadFileResponseType() {
      return new UploadFileResponseType();
   }

   public CreatePrescriptionRequest createCreatePrescriptionRequest() {
      return new CreatePrescriptionRequest();
   }

   public UpdateFeedbackFlagResponse createUpdateFeedbackFlagResponse() {
      return new UpdateFeedbackFlagResponse();
   }

   public GetPrescriptionForPrescriberRequest createGetPrescriptionForPrescriberRequest() {
      return new GetPrescriptionForPrescriberRequest();
   }

   public GetPrescriptionForExecutorRequest createGetPrescriptionForExecutorRequest() {
      return new GetPrescriptionForExecutorRequest();
   }

   public RevokePatientPrescriptionResponse createRevokePatientPrescriptionResponse() {
      return new RevokePatientPrescriptionResponse();
   }

   public RevokePrescriptionRequest createRevokePrescriptionRequest() {
      return new RevokePrescriptionRequest();
   }

   public ListOpenPrescriptionsResponse createListOpenPrescriptionsResponse() {
      return new ListOpenPrescriptionsResponse();
   }

   public ListNotificationsRequest createListNotificationsRequest() {
      return new ListNotificationsRequest();
   }

   public ListFeedbacksRequest createListFeedbacksRequest() {
      return new ListFeedbacksRequest();
   }

   public MarkAsDeliveredResponse createMarkAsDeliveredResponse() {
      return new MarkAsDeliveredResponse();
   }

   public GetPrescriptionForPatientRequest createGetPrescriptionForPatientRequest() {
      return new GetPrescriptionForPatientRequest();
   }

   public MarkAsDeliveredRequest createMarkAsDeliveredRequest() {
      return new MarkAsDeliveredRequest();
   }

   public CreateFeedbackResponse createCreateFeedbackResponse() {
      return new CreateFeedbackResponse();
   }

   public ListPatientPrescriptionsResponse createListPatientPrescriptionsResponse() {
      return new ListPatientPrescriptionsResponse();
   }

   public GetPrescriptionForPrescriberResponse createGetPrescriptionForPrescriberResponse() {
      return new GetPrescriptionForPrescriberResponse();
   }

   public MarkAsUnDeliveredResponse createMarkAsUnDeliveredResponse() {
      return new MarkAsUnDeliveredResponse();
   }

   public GetPrescriptionForPatientResponse createGetPrescriptionForPatientResponse() {
      return new GetPrescriptionForPatientResponse();
   }

   public UpdateFeedbackFlagRequest createUpdateFeedbackFlagRequest() {
      return new UpdateFeedbackFlagRequest();
   }

   public AliveCheckResponse createAliveCheckResponse() {
      return new AliveCheckResponse();
   }

   public ListPatientPrescriptionsRequest createListPatientPrescriptionsRequest() {
      return new ListPatientPrescriptionsRequest();
   }

   public GetPrescriptionForExecutorResponse createGetPrescriptionForExecutorResponse() {
      return new GetPrescriptionForExecutorResponse();
   }

   public RevokePatientPrescriptionRequest createRevokePatientPrescriptionRequest() {
      return new RevokePatientPrescriptionRequest();
   }

   public SendNotificationRequest createSendNotificationRequest() {
      return new SendNotificationRequest();
   }

   public UpdatePatientFeedbackFlagRequest createUpdatePatientFeedbackFlagRequest() {
      return new UpdatePatientFeedbackFlagRequest();
   }

   public ListOpenPrescriptionsRequest createListOpenPrescriptionsRequest() {
      return new ListOpenPrescriptionsRequest();
   }

   public MarkAsUnDeliveredRequest createMarkAsUnDeliveredRequest() {
      return new MarkAsUnDeliveredRequest();
   }

   public RevokePrescriptionResponse createRevokePrescriptionResponse() {
      return new RevokePrescriptionResponse();
   }

   public ListNotificationsResponse createListNotificationsResponse() {
      return new ListNotificationsResponse();
   }

   public MarkAsArchivedRequest createMarkAsArchivedRequest() {
      return new MarkAsArchivedRequest();
   }

   public ListFeedbacksResponse createListFeedbacksResponse() {
      return new ListFeedbacksResponse();
   }

   public UploadFileRequestType createUploadFileRequestType() {
      return new UploadFileRequestType();
   }

   @XmlElementDecl(
      namespace = "urn:be:fgov:ehealth:recipe:protocol:v1",
      name = "UploadFilePatientResponse"
   )
   public JAXBElement<UploadFileResponseType> createUploadFilePatientResponse(UploadFileResponseType value) {
      return new JAXBElement(_UploadFilePatientResponse_QNAME, UploadFileResponseType.class, (Class)null, value);
   }

   @XmlElementDecl(
      namespace = "urn:be:fgov:ehealth:recipe:protocol:v1",
      name = "UploadFileExecutorResponse"
   )
   public JAXBElement<UploadFileResponseType> createUploadFileExecutorResponse(UploadFileResponseType value) {
      return new JAXBElement(_UploadFileExecutorResponse_QNAME, UploadFileResponseType.class, (Class)null, value);
   }

   @XmlElementDecl(
      namespace = "urn:be:fgov:ehealth:recipe:protocol:v1",
      name = "UploadFilePatientRequest"
   )
   public JAXBElement<UploadFileRequestType> createUploadFilePatientRequest(UploadFileRequestType value) {
      return new JAXBElement(_UploadFilePatientRequest_QNAME, UploadFileRequestType.class, (Class)null, value);
   }

   @XmlElementDecl(
      namespace = "urn:be:fgov:ehealth:recipe:protocol:v1",
      name = "UploadFileExecutorRequest"
   )
   public JAXBElement<UploadFileRequestType> createUploadFileExecutorRequest(UploadFileRequestType value) {
      return new JAXBElement(_UploadFileExecutorRequest_QNAME, UploadFileRequestType.class, (Class)null, value);
   }

   @XmlElementDecl(
      namespace = "urn:be:fgov:ehealth:recipe:protocol:v1",
      name = "UploadFilePrescriberRequest"
   )
   public JAXBElement<UploadFileRequestType> createUploadFilePrescriberRequest(UploadFileRequestType value) {
      return new JAXBElement(_UploadFilePrescriberRequest_QNAME, UploadFileRequestType.class, (Class)null, value);
   }

   @XmlElementDecl(
      namespace = "urn:be:fgov:ehealth:recipe:protocol:v1",
      name = "UploadFilePrescriberResponse"
   )
   public JAXBElement<UploadFileResponseType> createUploadFilePrescriberResponse(UploadFileResponseType value) {
      return new JAXBElement(_UploadFilePrescriberResponse_QNAME, UploadFileResponseType.class, (Class)null, value);
   }
}
