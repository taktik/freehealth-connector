package be.business.connector.gfddpp.tipsystem.mock;

import be.apb.gfddpp.common.exceptions.GFDDPPException;
import be.apb.gfddpp.common.status.StatusCode;
import be.apb.standards.smoa.schema.id.v1.NihiiIdType;
import be.apb.standards.smoa.schema.model.v1.MessageInformation;
import be.apb.standards.smoa.schema.model.v1.OriginatorType;
import be.apb.standards.smoa.schema.model.v1.ReferencePharmacyType;
import be.apb.standards.smoa.schema.model.v1.StatusMessageType;
import be.apb.standards.smoa.schema.model.v1.SubjectReferenceType;
import be.apb.standards.smoa.schema.v1.EventFolderType;
import be.apb.standards.smoa.schema.v1.HeaderType;
import be.apb.standards.smoa.schema.v1.MessageType;
import be.apb.standards.smoa.schema.v1.ObjectFactory;
import be.apb.standards.smoa.schema.v1.SenderType;
import be.apb.standards.smoa.schema.v1.ServiceType;
import be.apb.standards.smoa.schema.v1.SingleMessage;
import be.apb.standards.smoa.schema.v1.SmoaMessageType;
import be.business.connector.core.exceptions.IntegrationModuleEhealthException;
import be.business.connector.core.exceptions.IntegrationModuleException;
import be.business.connector.core.exceptions.IntegrationModuleRuntimeException;
import be.business.connector.core.utils.Exceptionutils;
import be.business.connector.core.utils.I18nHelper;
import be.business.connector.gfddpp.domain.ThreeStateBoolean;
import be.business.connector.gfddpp.tipsystem.TIPSystemIntegrationModuleImpl;
import be.business.connector.gfddpp.tipsystem.status.offline.queue.StatusMessageQueueMock;
import be.business.connector.gfddpp.utils.SingleMessageInfo;
import be.ehealth.apb.gfddpp.services.tipsystem.SimpleResponseType;
import be.ehealth.apb.gfddpp.services.tipsystem.StatusType;
import java.util.GregorianCalendar;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import org.apache.commons.io.Charsets;
import org.apache.log4j.Logger;

public class TIPSystemIntegrationModuleMock extends TIPSystemIntegrationModuleImpl {
   private static final Logger LOG = Logger.getLogger(TIPSystemIntegrationModuleMock.class);
   private static final String STATUS_MESSAGE_QUEUE_FOLDER_PROPERTY_NAME = "STATUS_MESSAGE_QUEUE_FOLDER";
   private static final String FILE_NAME_SEPARATOR = "_";

   public TIPSystemIntegrationModuleMock() throws IntegrationModuleException {
      super(false);
      TIPSystemIntegrationModuleImpl.statusMessageQueue = new StatusMessageQueueMock();
   }

   public String registerData(byte[] singleMessage, String tipId, boolean requestPatientSignature, ThreeStateBoolean patientConsent) throws IntegrationModuleException {
      String ids = null;

      try {
         LOG.debug("******************** Marshal byte[] to SMOAMessagetype  **********************");
         String singleMessageString = new String(singleMessage, Charsets.UTF_8);
         SingleMessage lsingleMessageObject = (SingleMessage)this.getJaxContextCentralizer().toObject(SingleMessage.class, singleMessageString);
         SingleMessageInfo smi = new SingleMessageInfo();
         smi.initSingleMessageInfo(lsingleMessageObject);
         LOG.debug("******************** Validate event type **********************");
         smi.validateRegisterData();
         LOG.debug("******************** get all ids **********************");
         smi.setGuids();
         ids = smi.getEvents();
         LOG.info("Data is Registered!");
      } catch (Throwable var9) {
         Exceptionutils.errorHandler(var9);
      }

      return ids;
   }

   protected TIPSystemIntegrationModuleImpl.RegisterEventResponse registerEvent(byte[] singleMessage, String info, String tipId, boolean requestPatientSignature, ThreeStateBoolean patientConsent, String procedure, long startTimeStamp) throws IntegrationModuleEhealthException, IntegrationModuleException, IntegrationModuleRuntimeException {
      SingleMessageInfo singleMessageInfo = new SingleMessageInfo(singleMessage);
      if ("registerMedicationSchemeEntries".equals(procedure)) {
         try {
            singleMessageInfo.validateRegisterData();
            singleMessageInfo.setGuids();
            singleMessageInfo.setHeaderNihiiPharmacyId("62599147");
            SimpleResponseType tipSuccess = new SimpleResponseType();
            tipSuccess.setStatus(new StatusType());
            tipSuccess.getStatus().setCode(StatusCode.TIP_200.name());
            TIPSystemIntegrationModuleImpl.RegisterEventResponse response = new TIPSystemIntegrationModuleImpl.RegisterEventResponse();
            response.setSingleMessageInfo(singleMessageInfo);
            response.setTipResponse(tipSuccess);
            return response;
         } catch (Throwable var12) {
            Exceptionutils.errorHandler(var12);
            return null;
         }
      } else {
         return super.registerEvent(singleMessage, info, tipId, requestPatientSignature, patientConsent, procedure, startTimeStamp);
      }
   }

   public void revokeData(byte[] singleMessage, String tipId, boolean requestPatientSignature, ThreeStateBoolean patientConsent) throws IntegrationModuleException {
      try {
         LOG.debug("******************** Marshal byte[] to SMOAMessagetype  **********************");
         String singleMessageString = new String(singleMessage, Charsets.UTF_8);
         SingleMessage lsingleMessageObject = (SingleMessage)this.getJaxContextCentralizer().toObject(SingleMessage.class, singleMessageString);
         SingleMessageInfo smi = new SingleMessageInfo();
         smi.initSingleMessageInfo(lsingleMessageObject);
         LOG.debug("******************** Validate event type **********************");
         smi.validateRevokeData();
         LOG.info("Data is Revoked!");
      } catch (Throwable var8) {
         Exceptionutils.errorHandler(var8);
      }

   }

   public String updateData(byte[] singleMessage, String tipId, boolean requestPatientSignature, ThreeStateBoolean patientConsent) throws IntegrationModuleException {
      String ids = null;

      try {
         LOG.debug("******************** Marshal byte[] to SMOAMessagetype  **********************");
         String singleMessageString = new String(singleMessage, Charsets.UTF_8);
         SingleMessage lsingleMessageObject = (SingleMessage)this.getJaxContextCentralizer().toObject(SingleMessage.class, singleMessageString);
         SingleMessageInfo smi = new SingleMessageInfo();
         smi.initSingleMessageInfo(lsingleMessageObject);
         LOG.debug("******************** Validate event type **********************");
         smi.validateUpdateData();
         LOG.debug("******************** get all ids **********************");
         ids = smi.getEvents();
         LOG.info("Data is Updated!");
      } catch (Throwable var9) {
         Exceptionutils.errorHandler(var9);
      }

      return ids;
   }

   public String getStatusMessages(String sGuid, String dGuid) throws IntegrationModuleException {
      ObjectFactory schemaObjectFactory = new ObjectFactory();
      be.apb.standards.smoa.schema.model.v1.ObjectFactory modelObjectFactory = new be.apb.standards.smoa.schema.model.v1.ObjectFactory();
      new be.apb.standards.smoa.schema.id.v1.ObjectFactory();
      Object var6 = null;

      byte[] xml;
      try {
         DatatypeFactory dataTypeFactory = DatatypeFactory.newInstance();
         LOG.debug("********************** getStatusMessage - creating Single Message Structure ********************** ");
         SingleMessage singleMessage = schemaObjectFactory.createSingleMessage();
         SmoaMessageType message = schemaObjectFactory.createSmoaMessageType();
         String requestorId = "62599147";
         message.setHeader(this.createHeader(requestorId, dataTypeFactory, schemaObjectFactory));
         EventFolderType eventFolderType = schemaObjectFactory.createEventFolderType();
         EventFolderType.Events events = new EventFolderType.Events();
         eventFolderType.setEvents(events);
         EventFolderType.EntitySpace entitySpace = new EventFolderType.EntitySpace();
         eventFolderType.setEntitySpace(entitySpace);
         StatusMessageType statusMessageEntity = new StatusMessageType();
         OriginatorType originator = modelObjectFactory.createOriginatorType();
         originator.setOriginatorId("62599147");
         originator.setServiceType(ServiceType.CM.toString());
         statusMessageEntity.setOriginator(originator);
         MessageInformation messageInformation = modelObjectFactory.createMessageInformation();
         messageInformation.setMessageSubType("401000");
         messageInformation.setMessageType(MessageType.ERROR);
         messageInformation.setMessageDetails("Failed to write to audit log");
         GregorianCalendar gregorianCalendar = new GregorianCalendar();
         gregorianCalendar.setTimeInMillis(System.currentTimeMillis());
         messageInformation.setDateTime(dataTypeFactory.newXMLGregorianCalendar(gregorianCalendar));
         statusMessageEntity.setMessageInformation(messageInformation);
         SubjectReferenceType subjectReference = modelObjectFactory.createSubjectReferenceType();
         subjectReference.setMessageGUID("12345678-1234-1234-1234-0123456789AB");
         subjectReference.setDispensationGUID("12345678-1234-1234-1234-0123456789AB");
         subjectReference.setSessionGUID("12345678-5234-1244-1634-0123467789DB");
         statusMessageEntity.setSubjectReference(subjectReference);
         entitySpace.getEntity().add(statusMessageEntity);
         message.setAbstractFolder(schemaObjectFactory.createEventFolder(eventFolderType));
         singleMessage.setUnsigned(message);
         xml = this.getJaxContextCentralizer().toXml(SingleMessage.class, singleMessage).getBytes();
      } catch (DatatypeConfigurationException var18) {
         throw new IntegrationModuleException(I18nHelper.getLabel("error.technical"));
      } catch (GFDDPPException var19) {
         throw new IntegrationModuleException(var19);
      }

      return new String(xml);
   }

   private HeaderType createHeader(String nihiiPharmacyNumber, DatatypeFactory dataTypeFactory, ObjectFactory schemaObjectFactory) {
      HeaderType header = schemaObjectFactory.createHeaderType();
      GregorianCalendar gregorianCalendar = new GregorianCalendar();
      gregorianCalendar.setTimeInMillis(System.currentTimeMillis());
      header.setMessageCreateDate(dataTypeFactory.newXMLGregorianCalendar(gregorianCalendar));
      SenderType sender = schemaObjectFactory.createSenderType();
      ReferencePharmacyType referencePharmacyType = new ReferencePharmacyType();
      NihiiIdType nihiiIdType = new NihiiIdType();
      nihiiIdType.setNihiiPharmacyNumber(nihiiPharmacyNumber);
      referencePharmacyType.setPharmacyId(nihiiIdType);
      be.apb.standards.smoa.schema.model.v1.ObjectFactory objectFactory = new be.apb.standards.smoa.schema.model.v1.ObjectFactory();
      sender.setAbstractPharmacy(objectFactory.createAbstractPharmacy(referencePharmacyType));
      return header;
   }

   public void shutdownGracefully() {
   }
}
