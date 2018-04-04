package be.ehealth.businessconnector.ehbox.v3.service.impl;

import be.ehealth.businessconnector.ehbox.v3.service.ConsultationService;
import be.ehealth.businessconnector.ehbox.v3.service.ServiceFactory;
import be.ehealth.businessconnector.ehbox.v3.validator.EhboxReplyValidator;
import be.ehealth.technicalconnector.config.impl.ConfigurationModuleBootstrap;
import be.ehealth.technicalconnector.exception.ConnectorException;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.exception.TechnicalConnectorExceptionValues;
import be.ehealth.technicalconnector.service.sts.security.SAMLToken;
import be.ehealth.technicalconnector.utils.impl.JaxbContextFactory;
import be.ehealth.technicalconnector.validator.SessionValidator;
import be.ehealth.technicalconnector.ws.domain.GenericRequest;
import be.ehealth.technicalconnector.ws.domain.GenericResponse;
import be.fgov.ehealth.commons.protocol.v1.ResponseType;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConsultationServiceImpl implements ConsultationService, ConfigurationModuleBootstrap.ModuleBootstrapHook {
   private static final Logger LOG = LoggerFactory.getLogger(ConsultationServiceImpl.class);
   private EhboxReplyValidator replyValidator;
   private SessionValidator sessionValidator;

   public ConsultationServiceImpl(SessionValidator sessionValidator, EhboxReplyValidator replyValidator) {
      this.replyValidator = replyValidator;
      this.sessionValidator = sessionValidator;
   }

   public ConsultationServiceImpl() {
      LOG.debug("creating ConsultationServiceImpl for bootstrapping purposes");
   }

   private <T extends ResponseType> T callService(SAMLToken token, Object request, String soapAction, Class<T> clazz) throws ConnectorException {
      try {
         this.sessionValidator.validateToken(token);
         GenericRequest service = ServiceFactory.getConsultationService(token);
         service.setPayload(request);
         service.setSoapAction(soapAction);
         GenericResponse xmlResponse = be.ehealth.technicalconnector.ws.ServiceFactory.getGenericWsSender().send(service);
         T response = (ResponseType)xmlResponse.asObject(clazz);
         this.replyValidator.validateReplyStatus(response);
         return response;
      } catch (Exception var8) {
         if (var8 instanceof TechnicalConnectorException) {
            throw (TechnicalConnectorException)var8;
         } else {
            throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_WS, var8, new Object[]{var8.getMessage()});
         }
      }
   }

   public final GetBoxInfoResponse getBoxInfo(SAMLToken token, GetBoxInfoRequest getBoxInfoRequest) throws ConnectorException {
      return (GetBoxInfoResponse)this.callService(token, getBoxInfoRequest, "urn:be:fgov:ehealth:ehbox:consultation:protocol:v3:getBoxInfo", GetBoxInfoResponse.class);
   }

   public GetFullMessageResponse getFullMessage(SAMLToken token, GetFullMessageRequest request) throws ConnectorException {
      return (GetFullMessageResponse)this.callService(token, request, "urn:be:fgov:ehealth:ehbox:consultation:protocol:v3:getFullMessage", GetFullMessageResponse.class);
   }

   /** @deprecated */
   @Deprecated
   public GetFullMessageResponse getFullMessage(SAMLToken token, MessageRequestType request) throws ConnectorException {
      GetFullMessageRequest requestToUse = (GetFullMessageRequest)this.mapToCorrectType(request, new GetFullMessageRequest());
      return (GetFullMessageResponse)this.callService(token, requestToUse, "urn:be:fgov:ehealth:ehbox:consultation:protocol:v3:getFullMessage", GetFullMessageResponse.class);
   }

   /** @deprecated */
   @Deprecated
   public final GetHistoryResponse getMessageHistory(SAMLToken token, MessageRequestType request) throws ConnectorException {
      GetHistoryRequest requestToUse = (GetHistoryRequest)this.mapToCorrectType(request, new GetHistoryRequest());
      return (GetHistoryResponse)this.callService(token, requestToUse, "urn:be:fgov:ehealth:ehbox:consultation:protocol:v3:getMessageHistory", GetHistoryResponse.class);
   }

   private <T extends MessageRequestType> T mapToCorrectType(MessageRequestType originalRequest, T newInstanceOfCorrectType) {
      newInstanceOfCorrectType.setBoxId(originalRequest.getBoxId());
      newInstanceOfCorrectType.setMessageId(originalRequest.getMessageId());
      newInstanceOfCorrectType.setSource(originalRequest.getSource());
      return newInstanceOfCorrectType;
   }

   public GetHistoryResponse getMessageHistory(SAMLToken token, GetHistoryRequest request) throws ConnectorException {
      return (GetHistoryResponse)this.callService(token, request, "urn:be:fgov:ehealth:ehbox:consultation:protocol:v3:getMessageHistory", GetHistoryResponse.class);
   }

   public final GetMessagesListResponse getMessageList(SAMLToken token, GetMessagesListRequest request) throws ConnectorException {
      return (GetMessagesListResponse)this.callService(token, request, "urn:be:fgov:ehealth:ehbox:consultation:protocol:v3:getMessagesList", GetMessagesListResponse.class);
   }

   public final GetMessageAcknowledgmentsStatusResponse getMessageAcknowledgmentsStatusResponse(SAMLToken token, GetMessageAcknowledgmentsStatusRequest request) throws ConnectorException {
      return (GetMessageAcknowledgmentsStatusResponse)this.callService(token, request, "urn:be:fgov:ehealth:ehbox:consultation:protocol:v3:getMessageAcknowledgmentsStatus", GetMessageAcknowledgmentsStatusResponse.class);
   }

   public final DeleteMessageResponse deleteMessage(SAMLToken token, DeleteMessageRequest request) throws ConnectorException {
      return (DeleteMessageResponse)this.callService(token, request, "urn:be:fgov:ehealth:ehbox:consultation:protocol:v3:deleteMessage", DeleteMessageResponse.class);
   }

   public final MoveMessageResponse moveMessage(SAMLToken token, MoveMessageRequest request) throws ConnectorException {
      return (MoveMessageResponse)this.callService(token, request, "urn:be:fgov:ehealth:ehbox:consultation:protocol:v3:moveMessage", MoveMessageResponse.class);
   }

   public InsertOoOResponse insertOoO(SAMLToken token, InsertOoORequest request) throws ConnectorException {
      return (InsertOoOResponse)this.callService(token, request, "urn:be:fgov:ehealth:ehbox:consultation:protocol:v3:insertOoO", InsertOoOResponse.class);
   }

   public final DeleteOoOResponse deleteOoO(SAMLToken token, DeleteOoORequest request) throws ConnectorException {
      return (DeleteOoOResponse)this.callService(token, request, "urn:be:fgov:ehealth:ehbox:consultation:protocol:v3:deleteOoO", DeleteOoOResponse.class);
   }

   public final GetOoOListResponse getOoOList(SAMLToken token, GetOoOListRequest request) throws ConnectorException {
      return (GetOoOListResponse)this.callService(token, request, "urn:be:fgov:ehealth:ehbox:consultation:protocol:v3:getOoOList", GetOoOListResponse.class);
   }

   public GetAllEhboxesMessagesListResponse getAllEhboxesMessagesList(SAMLToken token, GetAllEhboxesMessagesListRequest request) throws ConnectorException {
      return (GetAllEhboxesMessagesListResponse)this.callService(token, request, "urn:be:fgov:ehealth:ehbox:consultation:protocol:v3:getAllEhboxesMessagesList", GetAllEhboxesMessagesListResponse.class);
   }

   public void bootstrap() {
      JaxbContextFactory.initJaxbContext(DeleteMessageRequest.class);
      JaxbContextFactory.initJaxbContext(DeleteMessageResponse.class);
      JaxbContextFactory.initJaxbContext(DeleteOoORequest.class);
      JaxbContextFactory.initJaxbContext(DeleteOoOResponse.class);
      JaxbContextFactory.initJaxbContext(GetAllEhboxesMessagesListRequest.class);
      JaxbContextFactory.initJaxbContext(GetAllEhboxesMessagesListResponse.class);
      JaxbContextFactory.initJaxbContext(GetBoxInfoRequest.class);
      JaxbContextFactory.initJaxbContext(GetBoxInfoResponse.class);
      JaxbContextFactory.initJaxbContext(GetFullMessageRequest.class);
      JaxbContextFactory.initJaxbContext(GetFullMessageResponse.class);
      JaxbContextFactory.initJaxbContext(GetHistoryRequest.class);
      JaxbContextFactory.initJaxbContext(GetHistoryResponse.class);
      JaxbContextFactory.initJaxbContext(GetMessageAcknowledgmentsStatusRequest.class);
      JaxbContextFactory.initJaxbContext(GetMessageAcknowledgmentsStatusResponse.class);
      JaxbContextFactory.initJaxbContext(GetMessagesListRequest.class);
      JaxbContextFactory.initJaxbContext(GetMessagesListResponse.class);
      JaxbContextFactory.initJaxbContext(GetOoOListRequest.class);
      JaxbContextFactory.initJaxbContext(GetOoOListResponse.class);
      JaxbContextFactory.initJaxbContext(InsertOoORequest.class);
      JaxbContextFactory.initJaxbContext(InsertOoOResponse.class);
      JaxbContextFactory.initJaxbContext(MessageRequestType.class);
      JaxbContextFactory.initJaxbContext(MoveMessageRequest.class);
      JaxbContextFactory.initJaxbContext(MoveMessageResponse.class);
   }
}
