package be.ehealth.businessconnector.ehbox.v3.builders;

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

public interface RequestBuilder {
   DeleteMessageRequest createDeleteMessageRequest(String var1, String... var2);

   DeleteMessageRequest createDeleteMessageRequest(String var1, List<String> var2);

   DeleteMessageRequest createDeleteMessageRequest(String var1, BoxIdType var2, String... var3);

   DeleteMessageRequest createDeleteMessageRequest(String var1, BoxIdType var2, List<String> var3);

   GetAllEhboxesMessagesListRequest createGetAllEhboxesMessagesListRequest(String var1, Integer var2, Integer var3);

   GetMessagesListRequest createGetMessagesListRequest(String var1, int var2, int var3, BoxIdType var4);

   GetMessagesListRequest createGetMessagesListRequest(String var1);

   GetBoxInfoRequest createBoxInfoRequestForDefaultBox();

   GetBoxInfoRequest createBoxInfoRequest(BoxIdType var1);

   GetAllEhboxesMessagesListRequest createAllEhboxesMessagesListRequest(String var1);

   GetAllEhboxesMessagesListRequest createAllEhboxesMessagesListRequest(String var1, Integer var2, Integer var3);

   /** @deprecated */
   @Deprecated
   MessageRequestType createMessageRequestType(String var1);

   /** @deprecated */
   @Deprecated
   MessageRequestType createMessageRequestType(String var1, String var2);

   /** @deprecated */
   @Deprecated
   MessageRequestType createMessageRequestType(String var1, String var2, BoxIdType var3);

   GetFullMessageRequest createGetFullMessageRequest(String var1);

   GetFullMessageRequest createGetFullMessageRequest(String var1, String var2);

   GetFullMessageRequest createGetFullMessageRequest(String var1, String var2, BoxIdType var3);

   GetHistoryRequest createGetHistoryRequest(String var1);

   GetHistoryRequest createGetHistoryRequest(String var1, String var2);

   GetHistoryRequest createGetHistoryRequest(String var1, String var2, BoxIdType var3);

   MoveMessageRequest createMoveMessageRequest(String var1, String var2, String... var3);

   MoveMessageRequest createMoveMessageRequest(String var1, String var2, List<String> var3);

   MoveMessageRequest createMoveMessageRequest(String var1, String var2, BoxIdType var3, String... var4);

   MoveMessageRequest createMoveMessageRequest(String var1, String var2, BoxIdType var3, List<String> var4);

   GetMessageAcknowledgmentsStatusRequest createGetMessageAcknowledgmentsStatusRequest(String var1);

   GetMessageAcknowledgmentsStatusRequest createGetMessageAcknowledgmentsStatusRequest(String var1, Integer var2, Integer var3, BoxIdType var4);

   DeleteOoORequest createDeleteOoORequest(String... var1);

   DeleteOoORequest createDeleteOoORequest(List<String> var1);

   DeleteOoORequest createDeleteOoORequest(BoxIdType var1, String... var2);

   DeleteOoORequest createDeleteOoORequest(BoxIdType var1, List<String> var2);

   GetOoOListRequest createGetOoOListRequest();

   GetOoOListRequest createGetOoOListRequest(BoxIdType var1);

   InsertOoORequest createInsertOoORequest(DateTime var1, DateTime var2, BoxIdType... var3);

   InsertOoORequest createInsertOoORequest(DateTime var1, DateTime var2, List<BoxIdType> var3);

   InsertOoORequest createInsertOoORequest(BoxIdType var1, DateTime var2, DateTime var3, BoxIdType... var4);

   InsertOoORequest createInsertOoORequest(BoxIdType var1, DateTime var2, DateTime var3, List<BoxIdType> var4);
}
