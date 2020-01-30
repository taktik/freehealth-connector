package be.apb.gfddpp.common.performance.logging;

import be.apb.gfddpp.common.exceptions.GFDDPPException;
import be.apb.gfddpp.common.utils.JaxContextCentralizer;
import be.apb.gfddpp.common.utils.SingleMessageWrapper;
import be.apb.standards.smoa.schema.model.v1.MedicationHistoryType;
import be.apb.standards.smoa.schema.v1.PharmaceuticalCareEventType;
import be.apb.standards.smoa.schema.v1.SingleMessage;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;

public class PerfLog {
   private static final String PERFORMANCE_TEST_JMETER_LOG_LINE_INDICATOR = "********** PERFLOG-JMETER: ";
   private static final String PERFORMANCE_TEST_INTERNAL_LOG_LINE_INDICATOR = "********** PERFLOG-INTERNAL: ";
   private static final String PERFORMANCE_TEST_EXTERNAL_LOG_LINE_INDICATOR = "********** PERFLOG-EXTERNAL: ";
   private static final String PERFORMANCE_TEST_MAPPING_INDICATOR = "MAPPING";
   private static final String PERFORMANCE_TEST_LOG_PARAMETER_SEPERATOR = " - ";
   private static JaxContextCentralizer jaxContextCentralizer = JaxContextCentralizer.getInstance();

   public static void logJmeter(Logger logger, String className, String methodName, String uniqueId) {
      log(logger, "********** PERFLOG-JMETER: ", className, methodName, (Long)null, (Long)null, (String)null, uniqueId);
   }

   public static void logMappingSguidAndUniqueId(Logger logger, byte[] payload, String uniqueId) {
      try {
         SingleMessage sm = (SingleMessage)jaxContextCentralizer.toObject(SingleMessage.class, payload);
         SingleMessageWrapper smw = new SingleMessageWrapper(sm);
         List<PharmaceuticalCareEventType> listEvent = smw.getAllEventsOfType(PharmaceuticalCareEventType.class);
         Iterator i$ = listEvent.iterator();

         while(i$.hasNext()) {
            PharmaceuticalCareEventType event = (PharmaceuticalCareEventType)i$.next();
            log(logger, "********** PERFLOG-INTERNAL: ", "MAPPING", (String)null, (Long)null, (Long)null, event.getId(), uniqueId);
         }
      } catch (GFDDPPException var8) {
         logger.warn("A problem occured when getting Payload for logging purpose", var8.getCause());
      }

   }

   public static void logMappingSguidAndUniqueId(Logger logger, SingleMessageWrapper singleMessageWrapper, String uniqueId) {
      List<PharmaceuticalCareEventType> listEvent = singleMessageWrapper.getAllEventsOfType(PharmaceuticalCareEventType.class);
      Iterator i$ = listEvent.iterator();

      while(i$.hasNext()) {
         PharmaceuticalCareEventType event = (PharmaceuticalCareEventType)i$.next();
         log(logger, "********** PERFLOG-INTERNAL: ", "MAPPING", (String)null, (Long)null, (Long)null, event.getId(), uniqueId);
      }

   }

   public static void logMappingSguidAndUniqueId(Logger logger, String sguid, String uniqueId) {
      log(logger, "********** PERFLOG-INTERNAL: ", "MAPPING", (String)null, (Long)null, (Long)null, sguid, uniqueId);
   }

   public static void logInternalSguid(Logger logger, String className, String method, Long startTimeStamp, Long endTimeStamp, byte[] payload) {
      logSguid(logger, "********** PERFLOG-INTERNAL: ", className, method, startTimeStamp, endTimeStamp, payload);
   }

   public static void logInternalSguid(Logger logger, String className, String method, Long startTimeStamp, Long endTimeStamp, SingleMessage singleMessage) {
      logSguid(logger, "********** PERFLOG-INTERNAL: ", className, method, startTimeStamp, endTimeStamp, singleMessage);
   }

   public static void logInternalSguid(Logger logger, String className, String method, Long startTimeStamp, Long endTimeStamp, SingleMessageWrapper singleMessageWrapper) {
      logSguid(logger, "********** PERFLOG-INTERNAL: ", className, method, startTimeStamp, endTimeStamp, singleMessageWrapper);
   }

   public static void logInternalSguid(Logger logger, String className, String method, Long startTimeStamp, Long endTimeStamp, String sguid) {
      log(logger, "********** PERFLOG-INTERNAL: ", className, method, startTimeStamp, endTimeStamp, sguid, (String)null);
   }

   public static void logInternalUniqueId(Logger logger, String className, String method, Long startTimeStamp, Long endTimeStamp, String uniqueId) {
      log(logger, "********** PERFLOG-INTERNAL: ", className, method, startTimeStamp, endTimeStamp, (String)null, uniqueId);
   }

   public static void logExternalSguid(Logger logger, String className, String method, Long startTimeStamp, Long endTimeStamp, byte[] payload) {
      logSguid(logger, "********** PERFLOG-EXTERNAL: ", className, method, startTimeStamp, endTimeStamp, payload);
   }

   public static void logExternalSguid(Logger logger, String className, String method, Long startTimeStamp, Long endTimeStamp, SingleMessage singleMessage) {
      logSguid(logger, "********** PERFLOG-EXTERNAL: ", className, method, startTimeStamp, endTimeStamp, singleMessage);
   }

   public static void logExternalSguid(Logger logger, String className, String method, Long startTimeStamp, Long endTimeStamp, SingleMessageWrapper singleMessageWrapper) {
      logSguid(logger, "********** PERFLOG-EXTERNAL: ", className, method, startTimeStamp, endTimeStamp, singleMessageWrapper);
   }

   public static void logExternalSguid(Logger logger, String className, String method, Long startTimeStamp, Long endTimeStamp, String sguid) {
      log(logger, "********** PERFLOG-EXTERNAL: ", className, method, startTimeStamp, endTimeStamp, sguid, (String)null);
   }

   public static void logExternalUniqueId(Logger logger, String className, String method, Long startTimeStamp, Long endTimeStamp, String uniqueId) {
      log(logger, "********** PERFLOG-EXTERNAL: ", className, method, startTimeStamp, endTimeStamp, (String)null, uniqueId);
   }

   private static void logSguid(Logger logger, String logIndicator, String className, String method, Long startTimeStamp, Long endTimeStamp, byte[] payload) {
      try {
         logSguid(logger, logIndicator, className, method, startTimeStamp, endTimeStamp, (SingleMessage)jaxContextCentralizer.toObject(SingleMessage.class, payload));
      } catch (GFDDPPException var8) {
         logger.warn("A problem occured when getting Payload for logging purpose", var8.getCause());
      }

   }

   private static void logSguid(Logger logger, String logIndicator, String className, String method, Long startTimeStamp, Long endTimeStamp, SingleMessage singleMessage) {
      logSguid(logger, logIndicator, className, method, startTimeStamp, endTimeStamp, new SingleMessageWrapper(singleMessage));
   }

   private static void logSguid(Logger logger, String logIndicator, String className, String method, Long startTimeStamp, Long endTimeStamp, SingleMessageWrapper singleMessageWrapper) {
      List<PharmaceuticalCareEventType> listEvent = singleMessageWrapper.getAllEventsOfType(PharmaceuticalCareEventType.class);
      List<MedicationHistoryType> medicationHistoryTypes = singleMessageWrapper.getAllMedicationHistoryEntries();
      if (!CollectionUtils.isNotEmpty(listEvent) && !CollectionUtils.isNotEmpty(medicationHistoryTypes)) {
         logger.warn("NO SGUID FOUND TO LOG");
      } else {
         Iterator i$ = listEvent.iterator();
         if (i$.hasNext()) {
            PharmaceuticalCareEventType event = (PharmaceuticalCareEventType)i$.next();
            log(logger, logIndicator, className, method, startTimeStamp, endTimeStamp, event.getId(), (String)null);
         }

         i$ = medicationHistoryTypes.iterator();
         if (i$.hasNext()) {
            MedicationHistoryType medicationHistoryType = (MedicationHistoryType)i$.next();
            log(logger, logIndicator, className, method, startTimeStamp, endTimeStamp, medicationHistoryType.getSessionID(), (String)null);
         }
      }

   }

   private static void log(Logger logger, String logIndicator, String className, String method, Long startTimeStamp, Long endTimeStamp, String sguid, String uniqueId) {
      StringBuilder sb = new StringBuilder(logIndicator);
      sb.append(PerfLogParameters.CLASS.getValue()).append("=");
      if (className != null) {
         sb.append(className);
      }

      sb.append(" - ");
      sb.append(PerfLogParameters.METHOD.getValue()).append("=");
      if (method != null) {
         sb.append(method);
      }

      sb.append(" - ");
      sb.append(PerfLogParameters.START_TIMESTAMP.getValue()).append("=");
      if (startTimeStamp != null) {
         sb.append(startTimeStamp);
      }

      sb.append(" - ");
      sb.append(PerfLogParameters.END_TIMESTAMP.getValue()).append("=");
      if (endTimeStamp != null) {
         sb.append(endTimeStamp);
      }

      sb.append(" - ");
      sb.append(PerfLogParameters.DURATION.getValue()).append("=");
      if (startTimeStamp != null && endTimeStamp != null) {
         sb.append((endTimeStamp - startTimeStamp) / 1000000L);
      }

      sb.append(" - ");
      sb.append(PerfLogParameters.SGUID.getValue()).append("=");
      if (sguid != null) {
         sb.append(sguid);
      }

      sb.append(" - ");
      sb.append(PerfLogParameters.UNIQUE_ID.getValue()).append("=");
      if (uniqueId != null) {
         sb.append(uniqueId);
      }

      logger.info(sb.toString());
   }
}
