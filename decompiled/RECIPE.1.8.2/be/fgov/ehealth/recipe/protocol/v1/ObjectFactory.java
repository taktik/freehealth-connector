package be.fgov.ehealth.recipe.protocol.v1;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

@XmlRegistry
public class ObjectFactory {
   private static final QName _UploadFilePatientRequest_QNAME = new QName("urn:be:fgov:ehealth:recipe:protocol:v1", "UploadFilePatientRequest");
   private static final QName _UploadFilePrescriberResponse_QNAME = new QName("urn:be:fgov:ehealth:recipe:protocol:v1", "UploadFilePrescriberResponse");
   private static final QName _UploadFilePrescriberRequest_QNAME = new QName("urn:be:fgov:ehealth:recipe:protocol:v1", "UploadFilePrescriberRequest");
   private static final QName _UploadFileExecutorResponse_QNAME = new QName("urn:be:fgov:ehealth:recipe:protocol:v1", "UploadFileExecutorResponse");
   private static final QName _UploadFilePatientResponse_QNAME = new QName("urn:be:fgov:ehealth:recipe:protocol:v1", "UploadFilePatientResponse");
   private static final QName _UploadFileExecutorRequest_QNAME = new QName("urn:be:fgov:ehealth:recipe:protocol:v1", "UploadFileExecutorRequest");

   public CreatePrescriptionResponse createCreatePrescriptionResponse() {
      return new CreatePrescriptionResponse();
   }

   public CreatePrescriptionRequest createCreatePrescriptionRequest() {
      return new CreatePrescriptionRequest();
   }

   public UploadFileResponseType createUploadFileResponseType() {
      return new UploadFileResponseType();
   }

   public RevokePrescriptionForExecutorRequest createRevokePrescriptionForExecutorRequest() {
      return new RevokePrescriptionForExecutorRequest();
   }

   public ListFeedbacksResponse createListFeedbacksResponse() {
      return new ListFeedbacksResponse();
   }

   public MarkAsUnDeliveredRequest createMarkAsUnDeliveredRequest() {
      return new MarkAsUnDeliveredRequest();
   }

   public RevokePatientPrescriptionRequest createRevokePatientPrescriptionRequest() {
      return new RevokePatientPrescriptionRequest();
   }

   public UploadFileRequestType createUploadFileRequestType() {
      return new UploadFileRequestType();
   }

   public SendNotificationResponse createSendNotificationResponse() {
      return new SendNotificationResponse();
   }

   public UpdateFeedbackFlagRequest createUpdateFeedbackFlagRequest() {
      return new UpdateFeedbackFlagRequest();
   }

   public AliveCheckRequest createAliveCheckRequest() {
      return new AliveCheckRequest();
   }

   public ListPatientPrescriptionsRequest createListPatientPrescriptionsRequest() {
      return new ListPatientPrescriptionsRequest();
   }

   public GetPrescriptionForPatientResponse createGetPrescriptionForPatientResponse() {
      return new GetPrescriptionForPatientResponse();
   }

   public ListOpenPrescriptionsResponse createListOpenPrescriptionsResponse() {
      return new ListOpenPrescriptionsResponse();
   }

   public RevokePrescriptionRequest createRevokePrescriptionRequest() {
      return new RevokePrescriptionRequest();
   }

   public RevokePrescriptionForExecutorResponse createRevokePrescriptionForExecutorResponse() {
      return new RevokePrescriptionForExecutorResponse();
   }

   public ListNotificationsRequest createListNotificationsRequest() {
      return new ListNotificationsRequest();
   }

   public MarkAsDeliveredResponse createMarkAsDeliveredResponse() {
      return new MarkAsDeliveredResponse();
   }

   public GetPrescriptionForPrescriberRequest createGetPrescriptionForPrescriberRequest() {
      return new GetPrescriptionForPrescriberRequest();
   }

   public MarkAsDeliveredRequest createMarkAsDeliveredRequest() {
      return new MarkAsDeliveredRequest();
   }

   public GetPrescriptionForPatientRequest createGetPrescriptionForPatientRequest() {
      return new GetPrescriptionForPatientRequest();
   }

   public CreateFeedbackResponse createCreateFeedbackResponse() {
      return new CreateFeedbackResponse();
   }

   public MarkAsArchivedRequest createMarkAsArchivedRequest() {
      return new MarkAsArchivedRequest();
   }

   public RevokePrescriptionResponse createRevokePrescriptionResponse() {
      return new RevokePrescriptionResponse();
   }

   public AliveCheckResponse createAliveCheckResponse() {
      return new AliveCheckResponse();
   }

   public GetPrescriptionForExecutorRequest createGetPrescriptionForExecutorRequest() {
      return new GetPrescriptionForExecutorRequest();
   }

   public RevokePatientPrescriptionResponse createRevokePatientPrescriptionResponse() {
      return new RevokePatientPrescriptionResponse();
   }

   public GetPrescriptionForExecutorResponse createGetPrescriptionForExecutorResponse() {
      return new GetPrescriptionForExecutorResponse();
   }

   public UpdatePatientFeedbackFlagRequest createUpdatePatientFeedbackFlagRequest() {
      return new UpdatePatientFeedbackFlagRequest();
   }

   public ListPatientPrescriptionsResponse createListPatientPrescriptionsResponse() {
      return new ListPatientPrescriptionsResponse();
   }

   public CreateFeedbackRequest createCreateFeedbackRequest() {
      return new CreateFeedbackRequest();
   }

   public UpdatePatientFeedbackFlagResponse createUpdatePatientFeedbackFlagResponse() {
      return new UpdatePatientFeedbackFlagResponse();
   }

   public ListNotificationsResponse createListNotificationsResponse() {
      return new ListNotificationsResponse();
   }

   public ListFeedbacksRequest createListFeedbacksRequest() {
      return new ListFeedbacksRequest();
   }

   public ListOpenPrescriptionsRequest createListOpenPrescriptionsRequest() {
      return new ListOpenPrescriptionsRequest();
   }

   public GetPrescriptionForPrescriberResponse createGetPrescriptionForPrescriberResponse() {
      return new GetPrescriptionForPrescriberResponse();
   }

   public SendNotificationRequest createSendNotificationRequest() {
      return new SendNotificationRequest();
   }

   public UpdateFeedbackFlagResponse createUpdateFeedbackFlagResponse() {
      return new UpdateFeedbackFlagResponse();
   }

   public MarkAsUnDeliveredResponse createMarkAsUnDeliveredResponse() {
      return new MarkAsUnDeliveredResponse();
   }

   public MarkAsArchivedResponse createMarkAsArchivedResponse() {
      return new MarkAsArchivedResponse();
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
      name = "UploadFilePrescriberResponse"
   )
   public JAXBElement<UploadFileResponseType> createUploadFilePrescriberResponse(UploadFileResponseType value) {
      return new JAXBElement(_UploadFilePrescriberResponse_QNAME, UploadFileResponseType.class, (Class)null, value);
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
      name = "UploadFileExecutorResponse"
   )
   public JAXBElement<UploadFileResponseType> createUploadFileExecutorResponse(UploadFileResponseType value) {
      return new JAXBElement(_UploadFileExecutorResponse_QNAME, UploadFileResponseType.class, (Class)null, value);
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
      name = "UploadFileExecutorRequest"
   )
   public JAXBElement<UploadFileRequestType> createUploadFileExecutorRequest(UploadFileRequestType value) {
      return new JAXBElement(_UploadFileExecutorRequest_QNAME, UploadFileRequestType.class, (Class)null, value);
   }
}
