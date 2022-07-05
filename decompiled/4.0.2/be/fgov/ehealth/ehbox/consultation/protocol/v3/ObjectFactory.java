package be.fgov.ehealth.ehbox.consultation.protocol.v3;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

@XmlRegistry
public class ObjectFactory {
   private static final QName _GetFullMessageRequest_QNAME = new QName("urn:be:fgov:ehealth:ehbox:consultation:protocol:v3", "GetFullMessageRequest");
   private static final QName _GetHistoryRequest_QNAME = new QName("urn:be:fgov:ehealth:ehbox:consultation:protocol:v3", "GetHistoryRequest");
   private static final QName _GetMessagesListResponse_QNAME = new QName("urn:be:fgov:ehealth:ehbox:consultation:protocol:v3", "GetMessagesListResponse");
   private static final QName _GetAllEhboxesMessagesListResponse_QNAME = new QName("urn:be:fgov:ehealth:ehbox:consultation:protocol:v3", "GetAllEhboxesMessagesListResponse");

   public ObjectFactory() {
   }

   public GetBoxInfoRequest createGetBoxInfoRequest() {
      return new GetBoxInfoRequest();
   }

   public GetMessagesListRequest createGetMessagesListRequest() {
      return new GetMessagesListRequest();
   }

   public GetAllEhboxesMessagesListRequest createGetAllEhboxesMessagesListRequest() {
      return new GetAllEhboxesMessagesListRequest();
   }

   public MessageRequestType createMessageRequestType() {
      return new MessageRequestType();
   }

   public MoveMessageRequest createMoveMessageRequest() {
      return new MoveMessageRequest();
   }

   public DeleteMessageRequest createDeleteMessageRequest() {
      return new DeleteMessageRequest();
   }

   public GetMessageAcknowledgmentsStatusRequest createGetMessageAcknowledgmentsStatusRequest() {
      return new GetMessageAcknowledgmentsStatusRequest();
   }

   public InsertOoORequest createInsertOoORequest() {
      return new InsertOoORequest();
   }

   public DeleteOoORequest createDeleteOoORequest() {
      return new DeleteOoORequest();
   }

   public GetOoOListRequest createGetOoOListRequest() {
      return new GetOoOListRequest();
   }

   public GetBoxInfoResponse createGetBoxInfoResponse() {
      return new GetBoxInfoResponse();
   }

   public GetMessageListResponseType createGetMessageListResponseType() {
      return new GetMessageListResponseType();
   }

   public GetFullMessageResponse createGetFullMessageResponse() {
      return new GetFullMessageResponse();
   }

   public ConsultationMessageType createConsultationMessageType() {
      return new ConsultationMessageType();
   }

   public MoveMessageResponse createMoveMessageResponse() {
      return new MoveMessageResponse();
   }

   public DeleteMessageResponse createDeleteMessageResponse() {
      return new DeleteMessageResponse();
   }

   public GetHistoryResponse createGetHistoryResponse() {
      return new GetHistoryResponse();
   }

   public GetMessageAcknowledgmentsStatusResponse createGetMessageAcknowledgmentsStatusResponse() {
      return new GetMessageAcknowledgmentsStatusResponse();
   }

   public AcknowledgmentsStatus createAcknowledgmentsStatus() {
      return new AcknowledgmentsStatus();
   }

   public InsertOoOResponse createInsertOoOResponse() {
      return new InsertOoOResponse();
   }

   public SubstituteType createSubstituteType() {
      return new SubstituteType();
   }

   public DeleteOoOResponse createDeleteOoOResponse() {
      return new DeleteOoOResponse();
   }

   public GetOoOListResponse createGetOoOListResponse() {
      return new GetOoOListResponse();
   }

   public OoO createOoO() {
      return new OoO();
   }

   public DestinationContextType createDestinationContextType() {
      return new DestinationContextType();
   }

   public ConsultationContentType createConsultationContentType() {
      return new ConsultationContentType();
   }

   public ContentContextType createContentContextType() {
      return new ContentContextType();
   }

   public ConsultationAnnexType createConsultationAnnexType() {
      return new ConsultationAnnexType();
   }

   public ConsultationDocumentType createConsultationDocumentType() {
      return new ConsultationDocumentType();
   }

   public Row createRow() {
      return new Row();
   }

   public Message createMessage() {
      return new Message();
   }

   @XmlElementDecl(
      namespace = "urn:be:fgov:ehealth:ehbox:consultation:protocol:v3",
      name = "GetFullMessageRequest"
   )
   public JAXBElement<MessageRequestType> createGetFullMessageRequest(MessageRequestType value) {
      return new JAXBElement(_GetFullMessageRequest_QNAME, MessageRequestType.class, (Class)null, value);
   }

   @XmlElementDecl(
      namespace = "urn:be:fgov:ehealth:ehbox:consultation:protocol:v3",
      name = "GetHistoryRequest"
   )
   public JAXBElement<MessageRequestType> createGetHistoryRequest(MessageRequestType value) {
      return new JAXBElement(_GetHistoryRequest_QNAME, MessageRequestType.class, (Class)null, value);
   }

   @XmlElementDecl(
      namespace = "urn:be:fgov:ehealth:ehbox:consultation:protocol:v3",
      name = "GetMessagesListResponse"
   )
   public JAXBElement<GetMessageListResponseType> createGetMessagesListResponse(GetMessageListResponseType value) {
      return new JAXBElement(_GetMessagesListResponse_QNAME, GetMessageListResponseType.class, (Class)null, value);
   }

   @XmlElementDecl(
      namespace = "urn:be:fgov:ehealth:ehbox:consultation:protocol:v3",
      name = "GetAllEhboxesMessagesListResponse"
   )
   public JAXBElement<GetMessageListResponseType> createGetAllEhboxesMessagesListResponse(GetMessageListResponseType value) {
      return new JAXBElement(_GetAllEhboxesMessagesListResponse_QNAME, GetMessageListResponseType.class, (Class)null, value);
   }
}
