package be.ehealth.businessconnector.ehbox.v3.session.impl;

import be.ehealth.businessconnector.ehbox.api.domain.exception.EhboxBusinessConnectorException;
import be.ehealth.businessconnector.ehbox.v3.service.ConsultationService;
import be.ehealth.businessconnector.ehbox.v3.service.PublicationService;
import be.ehealth.businessconnector.ehbox.v3.service.impl.ConsultationServiceImpl;
import be.ehealth.businessconnector.ehbox.v3.service.impl.PublicationServiceImpl;
import be.ehealth.businessconnector.ehbox.v3.session.EhealthBoxServiceV3;
import be.ehealth.businessconnector.ehbox.v3.validator.EhboxReplyValidator;
import be.ehealth.technicalconnector.exception.ConnectorException;
import be.ehealth.technicalconnector.exception.SessionManagementException;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.service.sts.security.SAMLToken;
import be.ehealth.technicalconnector.session.Session;
import be.ehealth.technicalconnector.validator.SessionValidator;
import be.fgov.ehealth.ehbox.consultation.protocol.v3.DeleteMessageRequest;
import be.fgov.ehealth.ehbox.consultation.protocol.v3.DeleteMessageResponse;
import be.fgov.ehealth.ehbox.consultation.protocol.v3.DeleteOoORequest;
import be.fgov.ehealth.ehbox.consultation.protocol.v3.DeleteOoOResponse;
import be.fgov.ehealth.ehbox.consultation.protocol.v3.GetAllEhboxesMessagesListRequest;
import be.fgov.ehealth.ehbox.consultation.protocol.v3.GetAllEhboxesMessagesListResponse;
import be.fgov.ehealth.ehbox.consultation.protocol.v3.GetBoxInfoRequest;
import be.fgov.ehealth.ehbox.consultation.protocol.v3.GetBoxInfoResponse;
import be.fgov.ehealth.ehbox.consultation.protocol.v3.GetFullMessageRequest;
import be.fgov.ehealth.ehbox.consultation.protocol.v3.GetFullMessageResponse;
import be.fgov.ehealth.ehbox.consultation.protocol.v3.GetHistoryRequest;
import be.fgov.ehealth.ehbox.consultation.protocol.v3.GetHistoryResponse;
import be.fgov.ehealth.ehbox.consultation.protocol.v3.GetMessageAcknowledgmentsStatusRequest;
import be.fgov.ehealth.ehbox.consultation.protocol.v3.GetMessageAcknowledgmentsStatusResponse;
import be.fgov.ehealth.ehbox.consultation.protocol.v3.GetMessagesListRequest;
import be.fgov.ehealth.ehbox.consultation.protocol.v3.GetMessagesListResponse;
import be.fgov.ehealth.ehbox.consultation.protocol.v3.GetOoOListRequest;
import be.fgov.ehealth.ehbox.consultation.protocol.v3.GetOoOListResponse;
import be.fgov.ehealth.ehbox.consultation.protocol.v3.InsertOoORequest;
import be.fgov.ehealth.ehbox.consultation.protocol.v3.InsertOoOResponse;
import be.fgov.ehealth.ehbox.consultation.protocol.v3.MessageRequestType;
import be.fgov.ehealth.ehbox.consultation.protocol.v3.MoveMessageRequest;
import be.fgov.ehealth.ehbox.consultation.protocol.v3.MoveMessageResponse;
import be.fgov.ehealth.ehbox.publication.protocol.v3.SendMessageRequest;
import be.fgov.ehealth.ehbox.publication.protocol.v3.SendMessageResponse;

public class EhealthBoxServiceV3Impl implements EhealthBoxServiceV3 {
   private ConsultationService consultationService;
   private PublicationService publicationService;
   private SessionValidator sessionValidator;

   public EhealthBoxServiceV3Impl(SessionValidator sessionValidator, EhboxReplyValidator replyValidator) throws TechnicalConnectorException, EhboxBusinessConnectorException {
      this.consultationService = new ConsultationServiceImpl(sessionValidator, replyValidator);
      this.publicationService = new PublicationServiceImpl(sessionValidator, replyValidator);
      this.sessionValidator = sessionValidator;
   }

   public final SendMessageResponse sendMessage(SendMessageRequest sendMessageRequest) throws ConnectorException {
      SAMLToken token = Session.getInstance().getSession().getSAMLToken();
      SendMessageResponse response = this.publicationService.sendMessage(token, sendMessageRequest);
      return response;
   }

   public final GetBoxInfoResponse getBoxInfo(GetBoxInfoRequest getBoxInfoRequest) throws ConnectorException {
      SAMLToken token = this.initSession();
      GetBoxInfoResponse response = this.consultationService.getBoxInfo(token, getBoxInfoRequest);
      return response;
   }

   public final GetMessagesListResponse getMessageList(GetMessagesListRequest request) throws ConnectorException {
      SAMLToken token = this.initSession();
      GetMessagesListResponse response = this.consultationService.getMessageList(token, request);
      return response;
   }

   /** @deprecated */
   @Deprecated
   public final GetFullMessageResponse getFullMessage(MessageRequestType request) throws ConnectorException {
      return this.getFullMessageLocal(request);
   }

   private GetFullMessageResponse getFullMessageLocal(MessageRequestType request) throws SessionManagementException, ConnectorException {
      SAMLToken token = this.initSession();
      GetFullMessageRequest requestToUse = (GetFullMessageRequest)this.mapToCorrectType(request, new GetFullMessageRequest());
      GetFullMessageResponse response = this.consultationService.getFullMessage(token, requestToUse);
      return response;
   }

   private <T extends MessageRequestType> T mapToCorrectType(MessageRequestType request, T emptyNewType) {
      emptyNewType.setBoxId(request.getBoxId());
      emptyNewType.setMessageId(request.getMessageId());
      emptyNewType.setSource(request.getSource());
      return emptyNewType;
   }

   public GetFullMessageResponse getFullMessage(GetFullMessageRequest request) throws ConnectorException {
      return this.getFullMessageLocal(request);
   }

   public final MoveMessageResponse moveMessage(MoveMessageRequest request) throws ConnectorException {
      SAMLToken token = this.initSession();
      MoveMessageResponse response = this.consultationService.moveMessage(token, request);
      return response;
   }

   public final GetHistoryResponse getMessageHistory(MessageRequestType request) throws ConnectorException {
      return this.getMessageHistoryLocal(request);
   }

   private GetHistoryResponse getMessageHistoryLocal(MessageRequestType request) throws SessionManagementException, ConnectorException {
      SAMLToken token = this.initSession();
      GetHistoryResponse response = this.consultationService.getMessageHistory(token, (GetHistoryRequest)this.mapToCorrectType(request, new GetHistoryRequest()));
      return response;
   }

   public GetHistoryResponse getMessageHistory(GetHistoryRequest request) throws ConnectorException {
      return this.getMessageHistoryLocal(request);
   }

   public final GetMessageAcknowledgmentsStatusResponse getMessageAcknowledgmentsStatusRequest(GetMessageAcknowledgmentsStatusRequest request) throws ConnectorException {
      SAMLToken token = this.initSession();
      GetMessageAcknowledgmentsStatusResponse response = this.consultationService.getMessageAcknowledgmentsStatusResponse(token, request);
      return response;
   }

   public final DeleteMessageResponse deleteMessage(DeleteMessageRequest request) throws ConnectorException {
      return this.consultationService.deleteMessage(this.initSession(), request);
   }

   private SAMLToken initSession() throws SessionManagementException {
      this.sessionValidator.validateSession();
      return Session.getInstance().getSession().getSAMLToken();
   }

   public final InsertOoOResponse insertOoO(InsertOoORequest request) throws ConnectorException {
      return this.consultationService.insertOoO(this.initSession(), request);
   }

   public final GetOoOListResponse getOoOList(GetOoOListRequest request) throws ConnectorException {
      return this.consultationService.getOoOList(this.initSession(), request);
   }

   public final DeleteOoOResponse deleteOoO(DeleteOoORequest request) throws ConnectorException {
      return this.consultationService.deleteOoO(this.initSession(), request);
   }

   public final GetAllEhboxesMessagesListResponse getAllEhboxesMessagesList(GetAllEhboxesMessagesListRequest request) throws TechnicalConnectorException, EhboxBusinessConnectorException, ConnectorException {
      return this.consultationService.getAllEhboxesMessagesList(this.initSession(), request);
   }
}
