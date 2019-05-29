package be.ehealth.businessconnector.ehbox.v3.builders.impl;

import be.ehealth.businessconnector.ehbox.v3.builders.RequestBuilder;
import be.fgov.ehealth.ehbox.consultation.protocol.v3.DeleteMessageRequest;
import be.fgov.ehealth.ehbox.consultation.protocol.v3.DeleteOoORequest;
import be.fgov.ehealth.ehbox.consultation.protocol.v3.GetAllEhboxesMessagesListRequest;
import be.fgov.ehealth.ehbox.consultation.protocol.v3.GetBoxInfoRequest;
import be.fgov.ehealth.ehbox.consultation.protocol.v3.GetFullMessageRequest;
import be.fgov.ehealth.ehbox.consultation.protocol.v3.GetHistoryRequest;
import be.fgov.ehealth.ehbox.consultation.protocol.v3.GetMessageAcknowledgmentsStatusRequest;
import be.fgov.ehealth.ehbox.consultation.protocol.v3.GetMessagesListRequest;
import be.fgov.ehealth.ehbox.consultation.protocol.v3.GetOoOListRequest;
import be.fgov.ehealth.ehbox.consultation.protocol.v3.InsertOoORequest;
import be.fgov.ehealth.ehbox.consultation.protocol.v3.MessageRequestType;
import be.fgov.ehealth.ehbox.consultation.protocol.v3.MoveMessageRequest;
import be.fgov.ehealth.ehbox.core.v3.BoxIdType;
import java.util.List;
import org.joda.time.DateTime;

public class RequestBuilderImpl implements RequestBuilder {
   private static String[] stringTemplate = new String[0];
   private static BoxIdType[] boxIdTemplate = new BoxIdType[0];

   public DeleteMessageRequest createDeleteMessageRequest(String source, String... messageIds) {
      return this.createDeleteMessageRequest(source, (BoxIdType)null, (String[])messageIds);
   }

   public GetAllEhboxesMessagesListRequest createGetAllEhboxesMessagesListRequest(String source, Integer startIndex, Integer endIndex) {
      GetAllEhboxesMessagesListRequest request = new GetAllEhboxesMessagesListRequest();
      request.setSource(source);
      request.setStartIndex(startIndex);
      request.setEndIndex(endIndex);
      return request;
   }

   public GetMessagesListRequest createGetMessagesListRequest(String source, int startIndex, int endIndex, BoxIdType boxId) {
      GetMessagesListRequest request = new GetMessagesListRequest();
      request.setSource(source);
      request.setStartIndex(startIndex);
      request.setEndIndex(endIndex);
      request.setBoxId(boxId);
      return request;
   }

   public GetMessagesListRequest createGetMessagesListRequest(String source) {
      GetMessagesListRequest request = new GetMessagesListRequest();
      request.setSource(source);
      return request;
   }

   public DeleteMessageRequest createDeleteMessageRequest(String source, BoxIdType boxId, String... messageIds) {
      DeleteMessageRequest deleteMessageRequest = new DeleteMessageRequest();
      deleteMessageRequest.setBoxId(boxId);
      deleteMessageRequest.setSource(source);
      String[] arr$ = messageIds;
      int len$ = messageIds.length;

      for(int i$ = 0; i$ < len$; ++i$) {
         String messageId = arr$[i$];
         deleteMessageRequest.getMessageIds().add(messageId);
      }

      return deleteMessageRequest;
   }

   public GetBoxInfoRequest createBoxInfoRequestForDefaultBox() {
      return new GetBoxInfoRequest();
   }

   public GetBoxInfoRequest createBoxInfoRequest(BoxIdType boxId) {
      GetBoxInfoRequest getBoxInfoRequest = new GetBoxInfoRequest();
      getBoxInfoRequest.setBoxId(boxId);
      return getBoxInfoRequest;
   }

   public GetAllEhboxesMessagesListRequest createAllEhboxesMessagesListRequest(String source) {
      return this.createAllEhboxesMessagesListRequest(source, (Integer)null, (Integer)null);
   }

   public GetAllEhboxesMessagesListRequest createAllEhboxesMessagesListRequest(String source, Integer startIndex, Integer endIndex) {
      GetAllEhboxesMessagesListRequest getAllEhboxesMessagesListRequest = new GetAllEhboxesMessagesListRequest();
      getAllEhboxesMessagesListRequest.setStartIndex(startIndex);
      getAllEhboxesMessagesListRequest.setEndIndex(endIndex);
      getAllEhboxesMessagesListRequest.setSource(source);
      return getAllEhboxesMessagesListRequest;
   }

   public MessageRequestType createMessageRequestType(String messageId) {
      return this.createMessageRequestType(messageId, "INBOX");
   }

   public MessageRequestType createMessageRequestType(String messageId, String source) {
      return this.createMessageRequestType(messageId, source, (BoxIdType)null);
   }

   public MessageRequestType createMessageRequestType(String messageId, String source, BoxIdType boxId) {
      MessageRequestType messageRequestType = new MessageRequestType();
      messageRequestType.setBoxId(boxId);
      messageRequestType.setMessageId(messageId);
      messageRequestType.setSource(source);
      return messageRequestType;
   }

   public GetFullMessageRequest createGetFullMessageRequest(String messageId) {
      return this.createGetFullMessageRequest(messageId, "INBOX");
   }

   public GetFullMessageRequest createGetFullMessageRequest(String messageId, String source) {
      return this.createGetFullMessageRequest(messageId, source, (BoxIdType)null);
   }

   public GetFullMessageRequest createGetFullMessageRequest(String messageId, String source, BoxIdType boxId) {
      GetFullMessageRequest messageRequestType = new GetFullMessageRequest();
      messageRequestType.setBoxId(boxId);
      messageRequestType.setMessageId(messageId);
      messageRequestType.setSource(source);
      return messageRequestType;
   }

   public GetHistoryRequest createGetHistoryRequest(String messageId) {
      return this.createGetHistoryRequest(messageId, (String)null);
   }

   public GetHistoryRequest createGetHistoryRequest(String messageId, String source) {
      return this.createGetHistoryRequest(messageId, source, (BoxIdType)null);
   }

   public GetHistoryRequest createGetHistoryRequest(String messageId, String source, BoxIdType boxId) {
      GetHistoryRequest messageRequestType = new GetHistoryRequest();
      messageRequestType.setBoxId(boxId);
      messageRequestType.setMessageId(messageId);
      messageRequestType.setSource(source);
      return messageRequestType;
   }

   public MoveMessageRequest createMoveMessageRequest(String source, String destination, String... messageIds) {
      return this.createMoveMessageRequest(source, destination, (BoxIdType)null, (String[])messageIds);
   }

   public MoveMessageRequest createMoveMessageRequest(String source, String destination, BoxIdType boxId, String... messageIds) {
      MoveMessageRequest moveMessageRequest = new MoveMessageRequest();
      moveMessageRequest.setBoxId(boxId);
      moveMessageRequest.setDestination(destination);
      moveMessageRequest.setSource(source);
      String[] arr$ = messageIds;
      int len$ = messageIds.length;

      for(int i$ = 0; i$ < len$; ++i$) {
         String messageId = arr$[i$];
         moveMessageRequest.getMessageIds().add(messageId);
      }

      return moveMessageRequest;
   }

   public GetMessageAcknowledgmentsStatusRequest createGetMessageAcknowledgmentsStatusRequest(String messageId) {
      return this.createGetMessageAcknowledgmentsStatusRequest(messageId, (Integer)null, (Integer)null, (BoxIdType)null);
   }

   public GetMessageAcknowledgmentsStatusRequest createGetMessageAcknowledgmentsStatusRequest(String messageId, Integer startIndex, Integer endIndex, BoxIdType boxId) {
      GetMessageAcknowledgmentsStatusRequest getMessageAcknowledgmentsStatusRequest = new GetMessageAcknowledgmentsStatusRequest();
      getMessageAcknowledgmentsStatusRequest.setBoxId(boxId);
      getMessageAcknowledgmentsStatusRequest.setEndIndex(endIndex);
      getMessageAcknowledgmentsStatusRequest.setMessageId(messageId);
      getMessageAcknowledgmentsStatusRequest.setStartIndex(startIndex);
      return getMessageAcknowledgmentsStatusRequest;
   }

   public DeleteOoORequest createDeleteOoORequest(String... oOoIds) {
      return this.createDeleteOoORequest((BoxIdType)null, (String[])oOoIds);
   }

   public DeleteOoORequest createDeleteOoORequest(List<String> oOoIds) {
      return this.createDeleteOoORequest((String[])oOoIds.toArray(stringTemplate));
   }

   public DeleteOoORequest createDeleteOoORequest(BoxIdType boxId, String... oOoIds) {
      DeleteOoORequest deleteOoORequest = new DeleteOoORequest();
      deleteOoORequest.setBoxId(boxId);
      String[] arr$ = oOoIds;
      int len$ = oOoIds.length;

      for(int i$ = 0; i$ < len$; ++i$) {
         String oOoId = arr$[i$];
         deleteOoORequest.getOoOIds().add(oOoId);
      }

      return deleteOoORequest;
   }

   public DeleteOoORequest createDeleteOoORequest(BoxIdType boxId, List<String> oOoIds) {
      return this.createDeleteOoORequest(boxId, (String[])oOoIds.toArray(stringTemplate));
   }

   public GetOoOListRequest createGetOoOListRequest() {
      return this.createGetOoOListRequest((BoxIdType)null);
   }

   public GetOoOListRequest createGetOoOListRequest(BoxIdType boxId) {
      GetOoOListRequest getOoOListRequest = new GetOoOListRequest();
      getOoOListRequest.setBoxId(boxId);
      return getOoOListRequest;
   }

   public InsertOoORequest createInsertOoORequest(DateTime startDate, DateTime endDate, BoxIdType... substitutes) {
      return this.createInsertOoORequest((BoxIdType)null, startDate, endDate, (BoxIdType[])substitutes);
   }

   public InsertOoORequest createInsertOoORequest(BoxIdType boxId, DateTime startDate, DateTime endDate, BoxIdType... substitutes) {
      InsertOoORequest insertOoORequest = new InsertOoORequest();
      insertOoORequest.setBoxId(boxId);
      insertOoORequest.setStartDate(startDate);
      insertOoORequest.setEndDate(endDate);
      BoxIdType[] arr$ = substitutes;
      int len$ = substitutes.length;

      for(int i$ = 0; i$ < len$; ++i$) {
         BoxIdType subtitute = arr$[i$];
         insertOoORequest.getSubstitutes().add(subtitute);
      }

      return insertOoORequest;
   }

   public DeleteMessageRequest createDeleteMessageRequest(String source, List<String> messageIds) {
      return this.createDeleteMessageRequest(source, (String[])messageIds.toArray(stringTemplate));
   }

   public DeleteMessageRequest createDeleteMessageRequest(String source, BoxIdType boxId, List<String> messageIds) {
      return this.createDeleteMessageRequest(source, boxId, (String[])messageIds.toArray(stringTemplate));
   }

   public MoveMessageRequest createMoveMessageRequest(String source, String destination, List<String> messageIds) {
      return this.createMoveMessageRequest(source, destination, (String[])messageIds.toArray(stringTemplate));
   }

   public MoveMessageRequest createMoveMessageRequest(String source, String destination, BoxIdType boxId, List<String> messageIds) {
      return this.createMoveMessageRequest(source, destination, boxId, (String[])messageIds.toArray(stringTemplate));
   }

   public InsertOoORequest createInsertOoORequest(DateTime startDate, DateTime endDate, List<BoxIdType> substitutes) {
      return this.createInsertOoORequest(startDate, endDate, (BoxIdType[])substitutes.toArray(boxIdTemplate));
   }

   public InsertOoORequest createInsertOoORequest(BoxIdType boxId, DateTime startDate, DateTime endDate, List<BoxIdType> substitutes) {
      return this.createInsertOoORequest(boxId, startDate, endDate, (BoxIdType[])substitutes.toArray(boxIdTemplate));
   }
}
