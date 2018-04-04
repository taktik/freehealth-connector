package be.apb.gfddpp.common.log;

import be.apb.gfddpp.common.utils.Operation;
import be.apb.standards.gfddpp.request.DataSpecificParametersGetData;
import be.apb.standards.gfddpp.request.DataSpecificParametersGetDataTypes;
import be.apb.standards.gfddpp.request.DataSpecificParametersGetStatus;
import be.apb.standards.gfddpp.request.DataSpecificParametersPharmacyDetails;
import be.apb.standards.gfddpp.request.GetDataRequestParameters;
import be.apb.standards.gfddpp.request.Motivation;
import be.apb.standards.gfddpp.request.PatientType;
import be.apb.standards.gfddpp.request.RequestorType;

public class LogsGetDataParameters {
   private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(LogsSMC.class);
   private static final String SEPARATOR = " : ";

   public static synchronized void logs(GetDataRequestParameters requestParam, LogsGetDataParameters.IntermediateMessage intermediateMessage, Operation operation) {
      if (requestParam != null) {
         logRequestor(requestParam.getRequestor(), intermediateMessage, operation);
         logPatient(requestParam.getPatient(), intermediateMessage, operation);
         switch(operation) {
         case GETDATA:
            logSpecificParam(requestParam.getDataSpecificParametersGetData(), intermediateMessage, operation);
            break;
         case GETDATATYPES:
            logSpecificParam(requestParam.getDataSpecificParametersGetDataTypes(), intermediateMessage, operation);
            break;
         case GETPHARMACYDETAILS:
            logSpecificParam(requestParam.getDataSpecificParametersPharmacyDetails(), intermediateMessage, operation);
            break;
         case GETSTATUS:
            logSpecificParam(requestParam.getDataSpecificParametersGetStatus(), intermediateMessage, operation);
         }
      }

   }

   public static void logRequestor(RequestorType requestor, LogsGetDataParameters.IntermediateMessage intermediateMessage, Operation operation) {
      String requestorId = null;
      String requestorType = null;
      if (requestor != null) {
         requestorId = requestor.getRequestorId();
         requestorType = requestor.getRequestorType();
      }

      LOG.info(operation.name() + " : " + "RequestorId" + " : " + requestorId + " : " + "RequestorType" + " : " + requestorType + " : " + intermediateMessage.getMessage());
   }

   public static void logPatient(PatientType patient, LogsGetDataParameters.IntermediateMessage intermediateMessage, Operation operation) {
      String patientId = null;
      String patientType = null;
      if (patient != null) {
         patientId = patient.getPatientId();
         patientType = patient.getPatientIdType();
      }

      LOG.info(operation.name() + " : " + "PatientId" + " : " + patientId + "PatientIdType" + " : " + patientType + " : " + intermediateMessage.getMessage());
   }

   public static void logMotivation(Motivation motivation, LogsGetDataParameters.IntermediateMessage intermediateMessage, Operation operation) {
      String motivationText = null;
      String motivationType = null;
      if (motivation != null) {
         motivationText = motivation.getMotivationtext();
         motivationType = motivation.getMotivationtype();
      }

      LOG.info(operation.name() + " : " + "MotivationText" + " : " + motivationText + " : " + "motivationType" + " : " + motivationType + " : " + intermediateMessage.getMessage());
   }

   public static void logSpecificParam(DataSpecificParametersGetData specificParam, LogsGetDataParameters.IntermediateMessage intermediateMessage, Operation operation) {
      if (specificParam != null) {
         LOG.info(operation.name() + " : " + "date range type" + " : " + specificParam.getDateRangeType() + " : " + "startDate" + " : " + specificParam.getStartDate() + " : " + specificParam.getEndDate() + " : " + intermediateMessage.getMessage());
      }

   }

   public static void logSpecificParam(DataSpecificParametersGetDataTypes specificParam, LogsGetDataParameters.IntermediateMessage intermediateMessage, Operation operation) {
      if (specificParam != null) {
         LOG.info(operation.name() + " : " + "start Date" + " : " + specificParam.getStartDate() + " : " + "end date" + " : " + specificParam.getEndDate() + " : " + intermediateMessage.getMessage());
      }

   }

   public static synchronized void logSpecificParam(DataSpecificParametersPharmacyDetails specificParam, LogsGetDataParameters.IntermediateMessage intermediateMessage, Operation operation) {
      if (specificParam != null) {
         LOG.info(operation.name() + " : " + "DGUID" + " : " + specificParam.getDGuid() + " : " + intermediateMessage.getMessage());
      }

   }

   public static void logSpecificParam(DataSpecificParametersGetStatus specificParam, LogsGetDataParameters.IntermediateMessage intermediateMessage, Operation operation) {
      if (specificParam != null) {
         LOG.info(operation.name() + " : " + "DGUID" + " : " + specificParam.getDGUID() + " : " + specificParam.getSGUID() + " : " + intermediateMessage.getMessage());
      }

   }

   // $FF: synthetic class
   static class SyntheticClass_1 {
      // $FF: synthetic field
      static final int[] $SwitchMap$be$apb$gfddpp$common$utils$Operation = new int[Operation.values().length];

      static {
         try {
            $SwitchMap$be$apb$gfddpp$common$utils$Operation[Operation.GETDATA.ordinal()] = 1;
         } catch (NoSuchFieldError var4) {
            ;
         }

         try {
            $SwitchMap$be$apb$gfddpp$common$utils$Operation[Operation.GETDATATYPES.ordinal()] = 2;
         } catch (NoSuchFieldError var3) {
            ;
         }

         try {
            $SwitchMap$be$apb$gfddpp$common$utils$Operation[Operation.GETPHARMACYDETAILS.ordinal()] = 3;
         } catch (NoSuchFieldError var2) {
            ;
         }

         try {
            $SwitchMap$be$apb$gfddpp$common$utils$Operation[Operation.GETSTATUS.ordinal()] = 4;
         } catch (NoSuchFieldError var1) {
            ;
         }

      }
   }

   public static enum IntermediateMessage {
      UNSEAL("message unsealed"),
      SEAL("message sealed"),
      PHARMACY("look up for pharmacist"),
      PATIENT("look up for patient"),
      THERAPEUTICAL_IN("therapeutical relation lookup required"),
      THERAPEUTICAL_NO_LOG("therapeutical relation - no log created"),
      THERAPEUTICAL_LOG("therapeutical relation - log will becreated"),
      OPERATION_COMPLETE("operation completed"),
      REQUEST_CREATED("request created");

      private final String message;

      private IntermediateMessage(String message) {
         this.message = message;
      }

      public String getMessage() {
         return this.message;
      }
   }
}
