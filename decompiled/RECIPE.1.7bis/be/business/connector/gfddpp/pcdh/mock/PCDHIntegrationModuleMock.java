package be.business.connector.gfddpp.pcdh.mock;

import be.apb.gfddpp.common.utils.SingleMessageWrapper;
import be.apb.gfddpp.validation.exception.SingleMessageValidationException;
import be.apb.standards.smoa.schema.id.v1.InssIdType;
import be.apb.standards.smoa.schema.model.v1.DataLocationType;
import be.apb.standards.smoa.schema.model.v1.MedicationHistoryType;
import be.apb.standards.smoa.schema.model.v1.MinSetPatient;
import be.apb.standards.smoa.schema.model.v1.ObjectFactory;
import be.apb.standards.smoa.schema.v1.MedicationHistoryEvent;
import be.apb.standards.smoa.schema.v1.MedicationSchemeEntriesResponse;
import be.apb.standards.smoa.schema.v1.MedicationSchemeTimestampsResponse;
import be.apb.standards.smoa.schema.v1.SingleMessage;
import be.business.connector.core.exceptions.IntegrationModuleException;
import be.business.connector.core.utils.Exceptionutils;
import be.business.connector.core.utils.I18nHelper;
import be.business.connector.core.utils.IOUtils;
import be.business.connector.core.utils.MarshallerHelper;
import be.business.connector.gfddpp.domain.ThreeStateBoolean;
import be.business.connector.gfddpp.domain.medicationscheme.protocol.FetchDataEntriesRequest;
import be.business.connector.gfddpp.domain.medicationscheme.protocol.FetchDataEntriesResponse;
import be.business.connector.gfddpp.domain.medicationscheme.protocol.RetrieveTimestampsRequest;
import be.business.connector.gfddpp.domain.medicationscheme.protocol.RetrieveTimestampsResponse;
import be.business.connector.gfddpp.pcdh.PCDHIntegrationModuleImpl;
import be.business.connector.gfddpp.utils.ConsolidateUtils;
import be.business.connector.gfddpp.utils.MappingUtils;
import be.business.connector.projects.common.utils.ValidationUtils;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;
import net.sf.ehcache.Cache;
import org.apache.log4j.Logger;

public class PCDHIntegrationModuleMock extends PCDHIntegrationModuleImpl {
   private static final Logger LOG = Logger.getLogger(PCDHIntegrationModuleMock.class);

   public PCDHIntegrationModuleMock() throws IntegrationModuleException {
   }

   public String getData(String patientIdType, String patientId, String dataType, String dateRange, boolean excludeOwnData, byte[] localData, boolean requestPatientSignature, ThreeStateBoolean therapeuticRelationShip) throws IntegrationModuleException {
      ValidationUtils.validateIncomingFieldsGetData(patientIdType, patientId, dataType, dateRange);
      byte[] singleMessage = this.getResponse("MOCK_GETDATA");
      SingleMessageWrapper smw = null;
      SingleMessage sm = null;
      MarshallerHelper<SingleMessage, SingleMessage> helper = new MarshallerHelper(SingleMessage.class, SingleMessage.class);
      if (localData != null && localData.length > 0) {
         LOG.debug("******************** Consolidation **********************");

         try {
            sm = ConsolidateUtils.createConsolidatedGetDataResponse(singleMessage, localData, patientId);
         } catch (SingleMessageValidationException var18) {
            LOG.error("", var18);
            throw new IntegrationModuleException(I18nHelper.getLabel("error.xml.invalid.singlemessage"));
         }

         smw = new SingleMessageWrapper(sm);
      } else {
         smw = new SingleMessageWrapper((SingleMessage)helper.toObject(singleMessage));
      }

      ObjectFactory o = new ObjectFactory();
      MinSetPatient patient = o.createMinSetPatient();
      InssIdType inssIdType = new InssIdType();
      inssIdType.setId(patientId);
      patient.setPersonId(inssIdType);
      Iterator var17 = smw.getAllMedicationHistoryEntries().iterator();

      while(var17.hasNext()) {
         MedicationHistoryType medicationHistoryType = (MedicationHistoryType)var17.next();
         medicationHistoryType.setMinPatient(patient);
      }

      return new String(helper.toXMLByteArray(smw.getWrappedMessage()));
   }

   public RetrieveTimestampsResponse getMedicationSchemeTimestamps(RetrieveTimestampsRequest retrieveTimestampsRequest) throws IntegrationModuleException {
      byte[] singleMessage = this.getResponse("MOCK_GET_MEDICATIONSCHEME_TIMESTAMPS");

      try {
         MarshallerHelper<SingleMessage, SingleMessage> helper = new MarshallerHelper(SingleMessage.class, SingleMessage.class);
         SingleMessageWrapper wrapper = new SingleMessageWrapper((SingleMessage)helper.toObject(singleMessage));
         MedicationSchemeTimestampsResponse pcdhResponse = (MedicationSchemeTimestampsResponse)wrapper.getEvents().get(0);
         return MappingUtils.mapMedicationSchemeTimestampsResponse(pcdhResponse);
      } catch (Throwable var6) {
         Exceptionutils.errorHandler(var6);
         return null;
      }
   }

   public FetchDataEntriesResponse getMedicationSchemeEntries(FetchDataEntriesRequest getMedicationEntriesRequest) throws IntegrationModuleException {
      byte[] singleMessage = this.getResponse("MOCK_GET_MEDICATIONSCHEME_ENTRIES");

      try {
         MarshallerHelper<SingleMessage, SingleMessage> helper = new MarshallerHelper(SingleMessage.class, SingleMessage.class);
         SingleMessageWrapper wrapper = new SingleMessageWrapper((SingleMessage)helper.toObject(singleMessage));
         MedicationSchemeEntriesResponse pcdhResponse = (MedicationSchemeEntriesResponse)wrapper.getEvents().get(0);
         return MappingUtils.mapMedicationSchemeEntriesResponse(pcdhResponse, (Cache)null, 10);
      } catch (Throwable var6) {
         Exceptionutils.errorHandler(var6);
         return null;
      }
   }

   public String getDataTypes(String patientIdType, String patientId, boolean requestPatientSignature, ThreeStateBoolean therapeuticRelationShip) throws IntegrationModuleException {
      ValidationUtils.validateIncomingFieldsGetDataTypes(patientId, patientIdType);
      byte[] singleMessage = this.getResponse("MOCK_GETDATATYPE");
      MarshallerHelper<SingleMessage, SingleMessage> helper = new MarshallerHelper(SingleMessage.class, SingleMessage.class);
      SingleMessage sM = (SingleMessage)helper.toObject(singleMessage);
      SingleMessageWrapper smw = new SingleMessageWrapper(sM);
      ObjectFactory o = new ObjectFactory();
      MinSetPatient patient = o.createMinSetPatient();
      InssIdType inssIdType = new InssIdType();
      inssIdType.setId(patientId);
      patient.setPersonId(inssIdType);
      List<MedicationHistoryEvent> medicationHistoryEvents = smw.getAllEventsOfType(MedicationHistoryEvent.class);
      List<DataLocationType> dataLocationTypes = ((MedicationHistoryEvent)medicationHistoryEvents.get(0)).getDataLocation();
      ObjectFactory objectFactory = new ObjectFactory();
      ((DataLocationType)dataLocationTypes.get(0)).setAbstractPatient(objectFactory.createAbstractPatient(patient));
      return new String(helper.toXMLByteArray(sM));
   }

   private byte[] getResponse(String propertyName) {
      try {
         InputStream fis = IOUtils.getResourceAsStream(this.getPropertyHandler().getProperty(propertyName));
         byte[] bytes = new byte[fis.available()];
         fis.read(bytes);
         return bytes;
      } catch (IOException var4) {
         throw new RuntimeException(var4);
      }
   }

   public String getPharmacyDetails(String patientIdType, String patientId, String dGuid, String motivationType, String motivationText, boolean requestPatientSignature, ThreeStateBoolean therapeuticRelationShip) throws IntegrationModuleException {
      return "62599147";
   }
}
