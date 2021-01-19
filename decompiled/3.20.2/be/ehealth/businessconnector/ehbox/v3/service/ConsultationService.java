package be.ehealth.businessconnector.ehbox.v3.service;

import be.ehealth.technicalconnector.exception.ConnectorException;
import be.ehealth.technicalconnector.service.sts.security.SAMLToken;
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

public interface ConsultationService {
   GetBoxInfoResponse getBoxInfo(SAMLToken var1, GetBoxInfoRequest var2) throws ConnectorException;

   /** @deprecated */
   @Deprecated
   GetFullMessageResponse getFullMessage(SAMLToken var1, MessageRequestType var2) throws ConnectorException;

   GetFullMessageResponse getFullMessage(SAMLToken var1, GetFullMessageRequest var2) throws ConnectorException;

   /** @deprecated */
   @Deprecated
   GetHistoryResponse getMessageHistory(SAMLToken var1, MessageRequestType var2) throws ConnectorException;

   GetHistoryResponse getMessageHistory(SAMLToken var1, GetHistoryRequest var2) throws ConnectorException;

   GetMessagesListResponse getMessageList(SAMLToken var1, GetMessagesListRequest var2) throws ConnectorException;

   MoveMessageResponse moveMessage(SAMLToken var1, MoveMessageRequest var2) throws ConnectorException;

   GetMessageAcknowledgmentsStatusResponse getMessageAcknowledgmentsStatusResponse(SAMLToken var1, GetMessageAcknowledgmentsStatusRequest var2) throws ConnectorException;

   DeleteMessageResponse deleteMessage(SAMLToken var1, DeleteMessageRequest var2) throws ConnectorException;

   InsertOoOResponse insertOoO(SAMLToken var1, InsertOoORequest var2) throws ConnectorException;

   DeleteOoOResponse deleteOoO(SAMLToken var1, DeleteOoORequest var2) throws ConnectorException;

   GetOoOListResponse getOoOList(SAMLToken var1, GetOoOListRequest var2) throws ConnectorException;

   GetAllEhboxesMessagesListResponse getAllEhboxesMessagesList(SAMLToken var1, GetAllEhboxesMessagesListRequest var2) throws ConnectorException;
}
