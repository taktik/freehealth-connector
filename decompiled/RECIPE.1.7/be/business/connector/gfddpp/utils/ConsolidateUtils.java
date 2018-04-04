package be.business.connector.gfddpp.utils;

import be.apb.gfddpp.common.exceptions.GFDDPPException;
import be.apb.gfddpp.common.utils.JaxContextCentralizer;
import be.apb.gfddpp.common.utils.SingleMessageWrapper;
import be.apb.gfddpp.helper.SingleMessageValidationHelper;
import be.apb.gfddpp.validation.exception.SingleMessageValidationException;
import be.apb.standards.smoa.schema.id.v1.EntityIdType;
import be.apb.standards.smoa.schema.model.v1.MedicationHistoryType;
import be.apb.standards.smoa.schema.v1.AbstractEventType;
import be.apb.standards.smoa.schema.v1.MedicationHistoryEvent;
import be.apb.standards.smoa.schema.v1.SingleMessage;
import org.taktik.connector.business.recipeprojects.core.exceptions.IntegrationModuleException;
import org.taktik.connector.business.recipeprojects.core.utils.I18nHelper;
import org.taktik.connector.business.recipeprojects.common.utils.ValidationUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.xml.datatype.XMLGregorianCalendar;
import org.apache.log4j.Logger;

public class ConsolidateUtils {
   private static final Logger LOG = Logger.getLogger(ConsolidateUtils.class);
   private static JaxContextCentralizer jaxContextCentralizer = JaxContextCentralizer.getInstance();

   public static SingleMessage createConsolidatedGetDataResponse(byte[] response, byte[] local, String patientid) throws IntegrationModuleException, SingleMessageValidationException {
      SingleMessage singleMessageResponse = null;

      try {
         singleMessageResponse = (SingleMessage)jaxContextCentralizer.toObject(SingleMessage.class, response);
      } catch (GFDDPPException var20) {
         throw new IntegrationModuleException(var20.getLocalizedMessage(), var20);
      }

      SingleMessageWrapper singleMessageWrapperResponse = new SingleMessageWrapper(singleMessageResponse);
      SingleMessageInfo smilocal = new SingleMessageInfo(local);
      smilocal.validateEmptyHeader();
      smilocal.validateSGuidFilledOk();
      smilocal.validateDGuidFilledOk();
      smilocal.validateMedicationHistorySessionDateFilledOk();
      smilocal.validateMedicationHistoryPatient(patientid);
      if (!smilocal.getHasMedicationHistoryEvent()) {
         throw new IntegrationModuleException(I18nHelper.getLabel("error.consolidation.local.message.minimal.dataset"));
      } else {
         SingleMessageWrapper singleMessageWrapperLocal = smilocal.getWrapper();
         if (singleMessageWrapperLocal.getAllEventsOfType(MedicationHistoryEvent.class).size() == 0) {
            throw new IntegrationModuleException(I18nHelper.getLabel("error.consolidation.local.message.minimal.dataset"));
         } else {
            SingleMessage singleMessageLocal;
            try {
               singleMessageLocal = (SingleMessage)jaxContextCentralizer.toObject(SingleMessage.class, local);
            } catch (GFDDPPException var19) {
               throw new IntegrationModuleException(var19.getLocalizedMessage(), var19);
            }

            ValidationUtils.validateSingleMessagePatient(patientid, singleMessageLocal);
            if (singleMessageWrapperLocal.getAllEventsOfType(MedicationHistoryEvent.class).size() == 0) {
               throw new IntegrationModuleException(I18nHelper.getLabel("error.consolidation.local.message.minimal.dataset"));
            } else {
               LOG.debug("******************** Merging (dguid) **********************");
               Map<String, MedicationHistoryType> mergedMedicationHistoryType = merging(singleMessageWrapperResponse, singleMessageWrapperLocal);
               LOG.debug("******************** Remove all old getMedicationHistories & add new getMedicationHistories**********************");
               List<MedicationHistoryEvent> medicationHistoryEvents = singleMessageWrapperResponse.getAllEventsOfType(MedicationHistoryEvent.class);
               if (medicationHistoryEvents.size() != 1) {
                  if (medicationHistoryEvents.size() > 1) {
                     throw new IntegrationModuleException(I18nHelper.getLabel("to.much.medicalhistoryevents.found"));
                  }

                  List<MedicationHistoryEvent> localMedicationHistoryEvents = singleMessageWrapperLocal.getAllEventsOfType(MedicationHistoryEvent.class);
                  if (localMedicationHistoryEvents.size() != 1) {
                     if (localMedicationHistoryEvents.size() > 1) {
                        throw new IntegrationModuleException(I18nHelper.getLabel("too.much.localmedicalhistoryevents.found"));
                     }

                     throw new IntegrationModuleException(I18nHelper.getLabel("no.localmedicalhistoryevents.and.medicalhistoryevents.found"));
                  }
               }

               List<MedicationHistoryType> mergedMedicationHistoryTypes = new ArrayList(mergedMedicationHistoryType.values());
               Collections.sort(mergedMedicationHistoryTypes, createComparatorMedicationHistoryType());
               List<AbstractEventType> abstractEventTypes = singleMessageWrapperResponse.getEvents();
               boolean abstractEventTypesContainsMedicationHistoryEvent = false;
               Iterator var14 = abstractEventTypes.iterator();

               while(var14.hasNext()) {
                  AbstractEventType abstractEventType = (AbstractEventType)var14.next();
                  if (abstractEventType instanceof MedicationHistoryEvent) {
                     abstractEventTypesContainsMedicationHistoryEvent = true;
                     ((MedicationHistoryEvent)abstractEventType).getMedicationHistoryEntity().clear();
                     ((MedicationHistoryEvent)abstractEventType).getMedicationHistoryEntity().addAll(mergedMedicationHistoryTypes);
                  }
               }

               if (!abstractEventTypesContainsMedicationHistoryEvent) {
                  MedicationHistoryEvent medicationHistoryEvent = new MedicationHistoryEvent();
                  medicationHistoryEvent.getMedicationHistoryEntity().addAll(mergedMedicationHistoryTypes);
                  abstractEventTypes.add(medicationHistoryEvent);
               }

               SingleMessageValidationHelper helper = new SingleMessageValidationHelper();
               long startValidate = System.nanoTime();

               try {
                  helper.assertValidSingleMessage(jaxContextCentralizer.toXml(SingleMessage.class, singleMessageResponse).getBytes());
               } catch (GFDDPPException var18) {
                  throw new IntegrationModuleException(var18.getLocalizedMessage(), var18);
               }

               long endValidate = System.nanoTime();
               LOG.info("//PERF EXTERNAL - CONSOLIDATEUTILS - VALIDATE MERGE - START: " + startValidate + " - END: " + endValidate + " - DURATION: " + (endValidate - startValidate));
               return singleMessageResponse;
            }
         }
      }
   }

   private static Comparator<MedicationHistoryType> createComparatorMedicationHistoryType() {
      return new Comparator<MedicationHistoryType>() {
         public int compare(MedicationHistoryType o1, MedicationHistoryType o2) {
            XMLGregorianCalendar date1 = this.extractDate(o1);
            XMLGregorianCalendar date2 = this.extractDate(o2);
            return date1.compare(date2);
         }

         private XMLGregorianCalendar extractDate(MedicationHistoryType medicationHistoryType) {
            return medicationHistoryType.getDeliveryDate();
         }
      };
   }

   private static Map<String, MedicationHistoryType> merging(SingleMessageWrapper singleMessageWrapperResponse, SingleMessageWrapper singleMessageWrapperLocal) throws IntegrationModuleException {
      Map<String, MedicationHistoryType> result = new HashMap();
      Iterator var4 = singleMessageWrapperResponse.getAllMedicationHistoryEntries().iterator();

      MedicationHistoryType medicationHistoryType;
      String dGuid;
      while(var4.hasNext()) {
         medicationHistoryType = (MedicationHistoryType)var4.next();
         dGuid = ((EntityIdType)medicationHistoryType.getEntityId()).getId();
         result.put(dGuid, medicationHistoryType);
      }

      if (singleMessageWrapperLocal.getWrappedMessage() != null) {
         var4 = singleMessageWrapperLocal.getAllMedicationHistoryEntries().iterator();

         while(var4.hasNext()) {
            medicationHistoryType = (MedicationHistoryType)var4.next();
            dGuid = ((EntityIdType)medicationHistoryType.getEntityId()).getId();
            result.put(dGuid, medicationHistoryType);
         }
      }

      return result;
   }
}
