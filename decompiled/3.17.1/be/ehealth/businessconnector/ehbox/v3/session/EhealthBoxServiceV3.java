package be.ehealth.businessconnector.ehbox.v3.session;

import be.ehealth.technicalconnector.exception.ConnectorException;
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

public interface EhealthBoxServiceV3 {
   SendMessageResponse sendMessage(SendMessageRequest var1) throws ConnectorException;

   GetBoxInfoResponse getBoxInfo(GetBoxInfoRequest var1) throws ConnectorException;

   GetMessagesListResponse getMessageList(GetMessagesListRequest var1) throws ConnectorException;

   /** @deprecated */
   @Deprecated
   GetFullMessageResponse getFullMessage(MessageRequestType var1) throws ConnectorException;

   GetFullMessageResponse getFullMessage(GetFullMessageRequest var1) throws ConnectorException;

   MoveMessageResponse moveMessage(MoveMessageRequest var1) throws ConnectorException;

   /** @deprecated */
   @Deprecated
   GetHistoryResponse getMessageHistory(MessageRequestType var1) throws ConnectorException;

   GetHistoryResponse getMessageHistory(GetHistoryRequest var1) throws ConnectorException;

   GetMessageAcknowledgmentsStatusResponse getMessageAcknowledgmentsStatusRequest(GetMessageAcknowledgmentsStatusRequest var1) throws ConnectorException;

   DeleteMessageResponse deleteMessage(DeleteMessageRequest var1) throws ConnectorException;

   InsertOoOResponse insertOoO(InsertOoORequest var1) throws ConnectorException;

   GetOoOListResponse getOoOList(GetOoOListRequest var1) throws ConnectorException;

   DeleteOoOResponse deleteOoO(DeleteOoORequest var1) throws ConnectorException;

   GetAllEhboxesMessagesListResponse getAllEhboxesMessagesList(GetAllEhboxesMessagesListRequest var1) throws ConnectorException;
}
