package be.apb.gfddpp.common.log;

import be.apb.gfddpp.common.exceptions.GFDDPPException;
import be.apb.gfddpp.common.utils.JaxContextCentralizer;
import be.apb.gfddpp.common.utils.SingleMessageWrapper;
import be.apb.standards.smoa.schema.model.v1.MessageInformation;
import be.apb.standards.smoa.schema.model.v1.StatusMessageType;
import be.apb.standards.smoa.schema.model.v1.SubjectReferenceType;
import be.apb.standards.smoa.schema.v1.PharmaceuticalCareEventType;
import be.apb.standards.smoa.schema.v1.SingleMessage;
import java.util.Iterator;
import java.util.List;

public class LogsSMC {
   private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(LogsSMC.class);
   private static String SGUID = " SGUID : ";
   private static String DGUID = " DGUID : ";
   private static String SEPARATOR = " : ";
   private static JaxContextCentralizer jaxContextCentralizer = JaxContextCentralizer.getInstance();

   public static void logDguidSguid(byte[] payload, LogsSMC.IntermediateMessage intermediateMessage, Class clazz) {
      try {
         logDguidSguid((SingleMessage)jaxContextCentralizer.toObject(SingleMessage.class, payload), intermediateMessage, clazz);
      } catch (GFDDPPException var4) {
         LOG.error("error while marshalling the xml for logging", var4.getCause());
      }

   }

   public static void logDguidSguid(SingleMessage singleMessage, LogsSMC.IntermediateMessage intermediateMessage, Class clazz) {
      logDguidSguid(new SingleMessageWrapper(singleMessage), intermediateMessage, clazz);
   }

   public static void logDguidSguid(SingleMessageWrapper wrapper, LogsSMC.IntermediateMessage intermediateMessage, Class clazz) {
      logDguid(wrapper, intermediateMessage, clazz);
      logSguid(wrapper, intermediateMessage, clazz);
   }

   public static synchronized void logDguid(SingleMessageWrapper wrapper, LogsSMC.IntermediateMessage intermediateMessage, Class clazz) {
      List<String> listDguid = wrapper.getAllDGUIDs();
      Iterator i$ = listDguid.iterator();

      while(i$.hasNext()) {
         String dguid = (String)i$.next();
         LOG.info("The dguid " + dguid + intermediateMessage.getMessage() + clazz.getSimpleName());
      }

   }

   public static synchronized void logSguid(SingleMessageWrapper wrapper, LogsSMC.IntermediateMessage intermediateMessage, Class clazz) {
      List<PharmaceuticalCareEventType> listEvent = wrapper.getAllEventsOfType(PharmaceuticalCareEventType.class);
      Iterator i$ = listEvent.iterator();

      while(i$.hasNext()) {
         PharmaceuticalCareEventType event = (PharmaceuticalCareEventType)i$.next();
         LOG.info("The sguid " + event.getId() + intermediateMessage.getMessage() + clazz.getSimpleName());
      }

   }

   public static synchronized void logDguidSguid(List<StatusMessageType> statusMessages, LogsSMC.IntermediateMessageStatus intermediateMessage, Class clazz) {
      Iterator i$ = statusMessages.iterator();

      while(i$.hasNext()) {
         StatusMessageType statusMessage = (StatusMessageType)i$.next();
         logDguidSguid(statusMessage, intermediateMessage, clazz);
      }

   }

   public static void logDguidSguid(StatusMessageType statusMessage, LogsSMC.IntermediateMessageStatus intermediateMessage, Class clazz) {
      if (statusMessage != null) {
         logDguidSguid(statusMessage.getSubjectReference(), statusMessage.getMessageInformation(), intermediateMessage, clazz);
      }

   }

   public static synchronized void logDguidSguid(SubjectReferenceType subjectRef, MessageInformation messageInformation, LogsSMC.IntermediateMessageStatus intermediateMessage, Class clazz) {
      String dguid = null;
      String sguid = null;
      String messageDetails = null;
      if (subjectRef != null) {
         dguid = subjectRef.getDispensationGUID();
         sguid = subjectRef.getSessionGUID();
      }

      if (messageInformation != null) {
         messageDetails = messageInformation.getMessageSubType();
      }

      logDguid(dguid, messageDetails, intermediateMessage, clazz);
      logSguid(sguid, messageDetails, intermediateMessage, clazz);
   }

   public static void logSguid(String sguid, String messageDetails, LogsSMC.IntermediateMessageStatus intermediateMessage, Class clazz) {
      LOG.info(SGUID + sguid + SEPARATOR + intermediateMessage.getMessage() + SEPARATOR + messageDetails + SEPARATOR + clazz.getSimpleName());
   }

   public static void logDguid(String dguid, String messageDetails, LogsSMC.IntermediateMessageStatus intermediateMessage, Class clazz) {
      LOG.info(DGUID + dguid + SEPARATOR + intermediateMessage.getMessage() + SEPARATOR + messageDetails + SEPARATOR + clazz.getSimpleName());
   }

   public static void logDguid(String dguid, LogsSMC.IntermediateMessage intermediateMessage, String operation) {
      LOG.info(DGUID + dguid + SEPARATOR + intermediateMessage.getMessage() + operation);
   }

   public static enum IntermediateMessageStatus {
      STATUS_CREATED(" : status Message created : "),
      SEND_TIP_BEFORE(" : A status Message will be send to the tip : "),
      SEND_TIP_AFTER(" : A status Message has been sent to the tip : "),
      SEND_TIP_ISSUE(" : Can not send status message to the tip : ");

      private String message;

      private IntermediateMessageStatus(String message) {
         this.message = message;
      }

      public String getMessage() {
         return this.message;
      }
   }

   public static enum IntermediateMessage {
      IN_INTERMEDIATE(" : ENTER : "),
      OUT_INTERMEDIATE(" : COMPLETED : "),
      UNSEAL(" : UNSEAL : "),
      AUTHENTICATED(" : Message authenticated : "),
      SEAL(" : SEAL : "),
      CONSOLIDATED(" : CONSOLIDATED : "),
      TO_TIP(" : SEND TO TIP :"),
      TO_PCDH(" :S END TO PCDH : "),
      TO_OFFLINE_QUEUE(": PUTTED ON QUEUE :");

      private final String message;

      private IntermediateMessage(String message) {
         this.message = message;
      }

      public String getMessage() {
         return this.message;
      }
   }
}
