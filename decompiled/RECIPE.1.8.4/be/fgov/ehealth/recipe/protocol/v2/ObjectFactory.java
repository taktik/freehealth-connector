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

   public ListNotificationsRequest createListNotificationsRequest() {
      return new ListNotificationsRequest();
   }

   public MarkAsDeliveredRequest createMarkAsDeliveredRequest() {
      return new MarkAsDeliveredRequest();
   }

   public CreatePrescriptionRequest createCreatePrescriptionRequest() {
      return new CreatePrescriptionRequest();
   }

   public RevokePrescriptionResponse createRevokePrescriptionResponse() {
      return new RevokePrescriptionResponse();
   }

   public MarkAsArchivedResponse createMarkAsArchivedResponse() {
      return new MarkAsArchivedResponse();
   }

   public RevokePatientPrescriptionRequest createRevokePatientPrescriptionRequest() {
      return new RevokePatientPrescriptionRequest();
   }

   public SendNotificationResponse createSendNotificationResponse() {
      return new SendNotificationResponse();
   }

   public SendNotificationRequest createSendNotificationRequest() {
      return new SendNotificationRequest();
   }

   public ListNotificationsResponse createListNotificationsResponse() {
      return new ListNotificationsResponse();
   }

   public ListFeedbacksResponse createListFeedbacksResponse() {
      return new ListFeedbacksResponse();
   }

   public ListOpenPrescriptionsResponse createListOpenPrescriptionsResponse() {
      return new ListOpenPrescriptionsResponse();
   }

   public MarkAsUnDeliveredRequest createMarkAsUnDeliveredRequest() {
      return new MarkAsUnDeliveredRequest();
   }

   public UploadFileRequestType createUploadFileRequestType() {
      return new UploadFileRequestType();
   }

   public CreateFeedbackRequest createCreateFeedbackRequest() {
      return new CreateFeedbackRequest();
   }

   public UpdatePatientFeedbackFlagRequest createUpdatePatientFeedbackFlagRequest() {
      return new UpdatePatientFeedbackFlagRequest();
   }

   public GetPrescriptionForPrescriberRequest createGetPrescriptionForPrescriberRequest() {
      return new GetPrescriptionForPrescriberRequest();
   }

   public CreatePrescriptionResponse createCreatePrescriptionResponse() {
      return new CreatePrescriptionResponse();
   }

   public MarkAsUnDeliveredResponse createMarkAsUnDeliveredResponse() {
      return new MarkAsUnDeliveredResponse();
   }

   public UpdatePatientFeedbackFlagResponse createUpdatePatientFeedbackFlagResponse() {
      return new UpdatePatientFeedbackFlagResponse();
   }

   public GetPrescriptionForExecutorRequest createGetPrescriptionForExecutorRequest() {
      return new GetPrescriptionForExecutorRequest();
   }

   public MarkAsArchivedRequest createMarkAsArchivedRequest() {
      return new MarkAsArchivedRequest();
   }

   public ListFeedbacksRequest createListFeedbacksRequest() {
      return new ListFeedbacksRequest();
   }

   public UploadFileResponseType createUploadFileResponseType() {
      return new UploadFileResponseType();
   }

   public GetPrescriptionForPatientRequest createGetPrescriptionForPatientRequest() {
      return new GetPrescriptionForPatientRequest();
   }

   public AliveCheckRequest createAliveCheckRequest() {
      return new AliveCheckRequest();
   }

   public RevokePrescriptionForExecutorRequest createRevokePrescriptionForExecutorRequest() {
      return new RevokePrescriptionForExecutorRequest();
   }

   public AliveCheckResponse createAliveCheckResponse() {
      return new AliveCheckResponse();
   }

   public RevokePrescriptionForExecutorResponse createRevokePrescriptionForExecutorResponse() {
      return new RevokePrescriptionForExecutorResponse();
   }

   public ListPatientPrescriptionsResponse createListPatientPrescriptionsResponse() {
      return new ListPatientPrescriptionsResponse();
   }

   public MarkAsDeliveredResponse createMarkAsDeliveredResponse() {
      return new MarkAsDeliveredResponse();
   }

   public RevokePrescriptionRequest createRevokePrescriptionRequest() {
      return new RevokePrescriptionRequest();
   }

   public GetPrescriptionForExecutorResponse createGetPrescriptionForExecutorResponse() {
      return new GetPrescriptionForExecutorResponse();
   }

   public ListPatientPrescriptionsRequest createListPatientPrescriptionsRequest() {
      return new ListPatientPrescriptionsRequest();
   }

   public UpdateFeedbackFlagResponse createUpdateFeedbackFlagResponse() {
      return new UpdateFeedbackFlagResponse();
   }

   public RevokePatientPrescriptionResponse createRevokePatientPrescriptionResponse() {
      return new RevokePatientPrescriptionResponse();
   }

   public CreateFeedbackResponse createCreateFeedbackResponse() {
      return new CreateFeedbackResponse();
   }

   public GetPrescriptionForPrescriberResponse createGetPrescriptionForPrescriberResponse() {
      return new GetPrescriptionForPrescriberResponse();
   }

   public GetPrescriptionForPatientResponse createGetPrescriptionForPatientResponse() {
      return new GetPrescriptionForPatientResponse();
   }

   public ListOpenPrescriptionsRequest createListOpenPrescriptionsRequest() {
      return new ListOpenPrescriptionsRequest();
   }

   public UpdateFeedbackFlagRequest createUpdateFeedbackFlagRequest() {
      return new UpdateFeedbackFlagRequest();
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
