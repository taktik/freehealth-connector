package be.business.connector.gfddpp.tipsystem.offline.queue;

import be.apb.gfddpp.common.exceptions.GFDDPPException;
import be.apb.gfddpp.common.utils.JaxContextCentralizer;
import be.apb.gfddpp.helper.SingleMessageValidationHelper;
import be.apb.gfddpp.rtrn.registerdata.ArchivePrescriptionCommentEventType;
import be.apb.gfddpp.rtrn.registerdata.ArchivePrescriptionEventType;
import be.apb.gfddpp.rtrn.registerdata.Dispensation;
import be.apb.gfddpp.rtrn.registerdata.Events;
import be.apb.gfddpp.validation.exception.SingleMessageValidationException;
import be.apb.standards.smoa.schema.model.v1.MessageInformation;
import be.apb.standards.smoa.schema.model.v1.ObjectFactory;
import be.apb.standards.smoa.schema.model.v1.OriginatorType;
import be.apb.standards.smoa.schema.model.v1.StatusMessageType;
import be.apb.standards.smoa.schema.model.v1.SubjectReferenceType;
import be.apb.standards.smoa.schema.model.v1.TargetType;
import be.apb.standards.smoa.schema.v1.MessageType;
import be.apb.standards.smoa.schema.v1.ServiceType;
import be.apb.standards.smoa.schema.v1.SingleMessage;
import be.apb.standards.smoa.schema.v1.TextType;
import be.business.connector.common.StandaloneRequestorProvider;
import be.business.connector.core.exceptions.IntegrationModuleException;
import be.business.connector.core.exceptions.IntegrationModuleRuntimeException;
import be.business.connector.core.utils.ETKHelper;
import be.business.connector.core.utils.EncryptionUtils;
import be.business.connector.core.utils.I18nHelper;
import be.business.connector.core.utils.PropertyHandler;
import be.business.connector.core.utils.SealedProcessor;
import be.business.connector.core.utils.SessionValidator;
import be.business.connector.gfddpp.domain.SealedMessageRequestTypeXml;
import be.business.connector.gfddpp.tipsystem.status.offline.queue.StatusMessageQueue;
import be.business.connector.gfddpp.utils.SingleMessageInfo;
import be.business.connector.projects.common.services.tipsystem.TipSystemServiceImpl;
import be.business.connector.projects.common.utils.SystemServicesUtils;
import be.business.connector.projects.common.utils.ValidationUtils;
import be.ehealth.apb.gfddpp.services.tipsystem.LocalisedString;
import be.ehealth.apb.gfddpp.services.tipsystem.SealedMessageRequestType;
import be.ehealth.apb.gfddpp.services.tipsystem.SimpleResponseType;
import be.ehealth.apb.gfddpp.services.tipsystem.SystemError_Exception;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.session.Session;
import be.fgov.ehealth.etee.crypto.encrypt.EncryptionToken;
import com.sun.xml.ws.client.ClientTransportException;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.ws.soap.SOAPFaultException;
import org.apache.commons.io.Charsets;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

public class QueueBehavior implements Runnable {
   private static Logger LOG = Logger.getLogger(QueueBehavior.class);
   private static final String UNENCRYPTED_FOLDER_PROPERTY_NAME = "UNENCRYPTED_FOLDER";
   private static final String INVALID_MESSAGES_FOLDER_PROPERTY_NAME = "INVALID_FOLDER";
   private String unencrypted_folder_name;
   private String invalid_folder_name;
   private final MessageQueue<EHealthMessage> queue;
   private final StatusMessageQueue statusQueue;
   private final ETKHelper etkHelper;
   private final PropertyHandler propertyHandler;
   private final JaxContextCentralizer jaxContextCentralizer = JaxContextCentralizer.getInstance();
   private final Set<String> retransmissionErrors = new HashSet(Arrays.asList("SOA-00001", "SOA-01001", "SOA-01002", "SOA-02001"));
   private final SingleMessageValidationHelper xmlValidationhelper = new SingleMessageValidationHelper();
   private static final String SUCCESS = "TIP_200";
   private static final String TIP_201 = "TIP_201";
   private static final String ERROR = "500";
   private int sendRetryCount = 1;
   private final int rescheduledTime;
   public static boolean shouldStopProcessing = false;

   public QueueBehavior(MessageQueue<EHealthMessage> queue, StatusMessageQueue statusQueue) throws IntegrationModuleException {
      this.queue = queue;
      this.etkHelper = new ETKHelper(PropertyHandler.getInstance(), EncryptionUtils.getInstance());
      this.propertyHandler = PropertyHandler.getInstance();
      this.statusQueue = statusQueue;
      this.jaxContextCentralizer.addContext(Events.class);
      this.jaxContextCentralizer.addContext(SealedMessageRequestTypeXml.class);
      this.initFailedMsgFolders(this.propertyHandler);
      this.rescheduledTime = this.propertyHandler.getIntegerProperty("MESSAGE_QUEUE_TIMER");
   }

   private void proceedResponse(SimpleResponseType response, EHealthMessage eHealthMessage, MessageQueue<EHealthMessage> q) {
      if (response != null) {
         LOG.info("The response code: " + response.getStatus().getCode() + " is returned from TIP System.");
         if (response.getStatus().getCode().equals("TIP_200")) {
            q.remove();
            LOG.info("Send to tip success, Removed from local queue " + eHealthMessage.getdGuidSGuid() + " for operation " + eHealthMessage.getMethodName());
         } else if (StringUtils.equals(response.getStatus().getCode(), "TIP_201")) {
            LOG.info("Send to tip success with TIP_201. Message is removed an put back on queue for retransmission.");
            q.remove();
            q.add((Serializable)eHealthMessage);
         } else {
            q.remove();
            LOG.info("Send to tip failed, Removed from local queue " + eHealthMessage.getdGuidSGuid());
            this.createPutStatusMessage(eHealthMessage, response, (IntegrationModuleException)null);
            LOG.info("Send to tip success, Removed from local queue " + eHealthMessage.getdGuidSGuid());
         }
      } else {
         LOG.info("Response from the tip is null, something went wrong while trying to send a message to the tip");
      }

   }

   public void run() {
      LOG.debug(">>> daemon process");
      OfflineQueueAssitant offlineQueueAssitant = new OfflineQueueAssitant();
      EHealthMessage eHealthMessage = null;
      SimpleResponseType response = null;

      while(!this.queue.isEmpty() && !offlineQueueAssitant.isStopOfflineQueue() && !this.queue.isFirstItemLocked()) {
         LOG.debug("***** Retrieve message from queue ....****");

         try {
            offlineQueueAssitant = new OfflineQueueAssitant();
            SealedMessageRequestType sealedMessageRequestType = null;
            EncryptionToken etk = null;

            try {
               eHealthMessage = (EHealthMessage)this.queue.elementAndLockItem();
               if (eHealthMessage == null) {
                  offlineQueueAssitant.setMoveToUnencryptable(true);
                  throw new IntegrationModuleException("unable to decrypt message");
               }

               String methodName = eHealthMessage.getMethodName();
               LOG.info("Message picked from offline queue for " + methodName + " operation. Contain : " + eHealthMessage.getdGuidSGuid());
               if (SessionValidator.isValidSession(Session.getInstance().getSession())) {
                  LOG.info("Has a Valid session to ehealth");

                  try {
                     sealedMessageRequestType = this.extractSealedMessageRequest(eHealthMessage);
                     SingleMessage singleMessageObject = this.extractSingleMessage(sealedMessageRequestType);
                     LOG.debug("******************** Validate nihii pharmacy  and set Headers in single message **********************");
                     SingleMessageInfo smi = new SingleMessageInfo();
                     smi.initSingleMessageInfo(singleMessageObject);
                     String NihiiPharmacyId = smi.validateNihiiPharmacyId(StandaloneRequestorProvider.getRequestorIdInformation());
                     smi.validateSingleMessageHeaderFilled(NihiiPharmacyId);
                     sealedMessageRequestType.setSingleMessageSealed(smi.getXmlBytes());
                     this.xmlValidationhelper.assertValidSingleMessage(sealedMessageRequestType.getSingleMessageSealed());
                  } catch (IntegrationModuleException var15) {
                     LOG.error("The message couldn't be extracted. Incorrect message in queue");
                     offlineQueueAssitant.setMoveToInvalid(true);
                     throw var15;
                  } catch (GFDDPPException var16) {
                     offlineQueueAssitant.setMoveToInvalid(true);
                     LOG.error("Error transforming SingleMessage to XML ");
                     throw new IntegrationModuleException("Error transforming SingleMessage to XML", var16);
                  } catch (SingleMessageValidationException var17) {
                     LOG.error("single message not valid to XSD");
                     offlineQueueAssitant.setMoveToInvalid(true);
                     throw new IntegrationModuleException("single message content INVALID TO xsd", var17);
                  }

                  if (sealedMessageRequestType.getRoutingParameters() != null || this.isTipIDEmpty(sealedMessageRequestType)) {
                     sealedMessageRequestType.getRoutingParameters().setTIPId(this.propertyHandler.getProperty("default.tip.id"));
                  }

                  String tipid = sealedMessageRequestType.getRoutingParameters().getTIPId();

                  try {
                     ValidationUtils.validateExistingTipId(sealedMessageRequestType.getRoutingParameters().getTIPId(), this.propertyHandler);
                  } catch (IntegrationModuleException var14) {
                     LOG.info("TIP ID validation exception :" + sealedMessageRequestType.getRoutingParameters().getTIPId() + " invalid");
                     offlineQueueAssitant.setMoveToInvalid(true);
                     offlineQueueAssitant.setCreateStatusMessage(true);
                     throw var14;
                  }

                  try {
                     etk = this.extractEncryptionToken(tipid);
                  } catch (IntegrationModuleException var13) {
                     LOG.info("====================Couldn't retrieve ETK==============================");
                     offlineQueueAssitant.setStopOfflineQueue(true);
                     throw var13;
                  }

                  byte[] tmpSingleMsgBytes = sealedMessageRequestType.getSingleMessageSealed();
                  byte[] sealedMessage = sealedMessageRequestType.getSingleMessageSealed();

                  try {
                     sealedMessage = SealedProcessor.createSealedAsync(etk, tmpSingleMsgBytes, "SingleMessage");
                  } catch (IntegrationModuleException var12) {
                     LOG.info("Can not seal the request for the tip for the following dispensation: " + eHealthMessage.getdGuidSGuid());
                     offlineQueueAssitant.setStopOfflineQueue(true);
                     throw new IntegrationModuleException(I18nHelper.getLabel("error.data.seal"));
                  }

                  sealedMessageRequestType.setSingleMessageSealed(sealedMessage);
                  LOG.debug("***** Send retrieved message from queue start ....****");

                  try {
                     LOG.info("Try to send message with id: " + eHealthMessage.getdGuidSGuid());
                     response = this.sendMessage(methodName, sealedMessageRequestType, sealedMessageRequestType.getRoutingParameters().getTIPId(), offlineQueueAssitant);
                  } catch (IntegrationModuleException var18) {
                     if (var18.getCause() instanceof TechnicalConnectorException) {
                        this.sendRetryCount *= 2;
                        this.queue.unlockFirstFile();
                     }

                     throw var18;
                  } catch (SystemError_Exception var19) {
                     if (var19.getFaultInfo() != null && this.retransmissionErrors.contains(var19.getFaultInfo().getCode())) {
                        LOG.info("Send to tip failed with  error: " + var19.getFaultInfo().getCode() + ". File not removed from queue. Will try to send the message again the next time");
                     } else {
                        LOG.info("Send to tip failed, invalid request, Removed from local queue " + eHealthMessage.getdGuidSGuid());
                        this.sendRetryCount *= 2;
                        this.queue.unlockFirstFile();
                     }

                     throw new IntegrationModuleException(var19.getMessage());
                  } catch (SOAPFaultException var20) {
                     if (this.retransmissionErrors.contains(var20.getMessage())) {
                        LOG.info("Send to tip failed but was error: " + var20.getMessage() + ". File not removed from queue. Will try to send the message again the next time");
                     } else {
                        LOG.info("Send to tip failed, invalid request, Removed from local queue " + eHealthMessage.getdGuidSGuid());
                        this.queue.unlockFirstFile();
                        this.sendRetryCount *= 2;
                     }

                     throw new IntegrationModuleException(var20.getMessage());
                  } catch (ClientTransportException var21) {
                     LOG.error("No connection could be made with the TIP System. Retry after: " + this.propertyHandler.getProperty("MESSAGE_QUEUE_TIMER") + " for the following sguid dguid : " + eHealthMessage.getdGuidSGuid());
                     LOG.error(var21);
                     this.queue.unlockFirstFile();
                     this.sendRetryCount *= 2;
                     throw new IntegrationModuleException(I18nHelper.getLabel("error.connection.tip"));
                  }

                  LOG.debug("***** Check response message send from TIP system....****");
                  this.sendRetryCount = 1;
                  this.proceedResponse(response, eHealthMessage, this.queue);
               } else {
                  LOG.info("No valid session with eHealth. Retry after: " + this.propertyHandler.getProperty("MESSAGE_QUEUE_TIMER"));
                  offlineQueueAssitant.setStopOfflineQueue(true);
                  this.queue.unlockFirstFile();
               }
            } catch (IntegrationModuleException var22) {
               this.proceedIntegrationModuleException(var22, eHealthMessage, offlineQueueAssitant);
            }
         } catch (Exception var23) {
            LOG.error("Unexpected exception occured. Will try to unlock first file on queue.", var23);
            this.queue.unlockFirstFile();
         }
      }

      LOG.debug("<<< daemon process\n");
      if (shouldStopProcessing) {
         throw new RuntimeException();
      }
   }

   private boolean isTipIDEmpty(SealedMessageRequestType sealedMessageRequestType) {
      return org.apache.commons.lang.StringUtils.isEmpty(sealedMessageRequestType.getRoutingParameters().getTIPId());
   }

   private void proceedIntegrationModuleException(IntegrationModuleException e, EHealthMessage eHealthMessage, OfflineQueueAssitant offlineQueueAssitant) {
      if (offlineQueueAssitant.isCreateStatusMessage()) {
         this.putStatusMessageOnQueue(this.createStatusMessage(eHealthMessage, (SimpleResponseType)null, e));
      }

      if (offlineQueueAssitant.isRemoveFromQueue()) {
         if (eHealthMessage != null) {
            LOG.info("Message containing : " + eHealthMessage.getdGuidSGuid() + " will be removed from queue.");
         }

         this.queue.remove();
         LOG.info("Message removed");
      }

      if (offlineQueueAssitant.isAddBackToQueue()) {
         this.queue.remove();
         this.queue.add((Serializable)eHealthMessage);
         LOG.info("Message containing : " + eHealthMessage.getdGuidSGuid() + " added back to queue");
      }

      if (offlineQueueAssitant.isMoveToInvalid()) {
         this.moveQueueMsgPlainText(eHealthMessage);
         this.queue.moveAbnormal(this.invalid_folder_name);
      }

      if (offlineQueueAssitant.isMoveToUnencryptable()) {
         this.queue.moveAbnormal(this.unencrypted_folder_name);
      }

      try {
         Thread.sleep(1000L * (long)this.rescheduledTime * (long)this.sendRetryCount);
      } catch (InterruptedException var5) {
         LOG.warn("Error occurs: ", var5);
      }

   }

   private SimpleResponseType sendMessage(String methodName, SealedMessageRequestType sealedMessageRequestType, String tipId, OfflineQueueAssitant offlineQueueAssitant) throws SystemError_Exception, IntegrationModuleException {
      String endpoint = SystemServicesUtils.getInstance(this.propertyHandler).getEndpointOutOfSystemConfiguration(this.propertyHandler.getProperty("tipsystem.id"), "TIP", "TIPService");
      if (endpoint == null) {
         LOG.error("***** No endpoint found in system services configuration for TIP: " + tipId + " - Not going to send message to TIP! ****");
         offlineQueueAssitant.removeAndCreateStatusMessage();
         offlineQueueAssitant.setStopOfflineQueue(true);
         throw new IntegrationModuleException(I18nHelper.getLabel("error.technical.endpoint.not.found"));
      } else {
         LOG.debug("***** Send message to endpoint: " + endpoint + "****");
         LOG.debug("***** Send message from queue: " + methodName + "****");
         if (methodName.equals(MethodName.REGISTERDATA.toString())) {
            return TipSystemServiceImpl.getInstance().registerData(sealedMessageRequestType);
         } else if (methodName.equals(MethodName.UPDATEDATA.toString())) {
            return TipSystemServiceImpl.getInstance().updateData(sealedMessageRequestType);
         } else {
            return methodName.equals(MethodName.REVOKEDATA.toString()) ? TipSystemServiceImpl.getInstance().deleteData(sealedMessageRequestType) : null;
         }
      }
   }

   private void createPutStatusMessage(EHealthMessage eHealthMessage, SimpleResponseType response, IntegrationModuleException e) {
      this.putStatusMessageOnQueue(this.createStatusMessage(eHealthMessage, response, e));
      LOG.info("Local status message created locally for : " + eHealthMessage.getdGuidSGuid());
   }

   private List<StatusMessageType> createStatusMessage(EHealthMessage messageOnQueue, SimpleResponseType response, IntegrationModuleException ex) {
      LOG.debug("***** CREATE STATUS MESSAGE ****");
      ObjectFactory modelObjectFactory = new ObjectFactory();
      Events events = null;

      try {
         events = (Events)this.jaxContextCentralizer.toObject(Events.class, messageOnQueue.getdGuidSGuid().getBytes());
      } catch (GFDDPPException var14) {
         LOG.error("A problem occur while trying to unmarshall DeliveryIds" + var14.getMessage());
      }

      List<StatusMessageType> statusMessageTypes = new ArrayList();
      StatusMessageType statusMessage;
      String xml;
      if (events != null && events.getPharmaceuticalCareEventType() != null) {
         Iterator iterator = events.getPharmaceuticalCareEventType().getDispensation().iterator();

         while(iterator.hasNext()) {
            Dispensation products = (Dispensation)iterator.next();
            statusMessage = modelObjectFactory.createStatusMessageType();
            statusMessage.setSubjectReference(this.createSubjectReferenceType(products.getDguid(), events.getPharmaceuticalCareEventType().getSguid(), messageOnQueue.getMessageId()));
            statusMessage.setOriginator(this.createOriginator(messageOnQueue.getPharmacyId()));
            statusMessage.setTarget(this.createTargetType(messageOnQueue.getPharmacyId()));
            statusMessage.setMessageInformation(this.createMessageInformation(response, ex));
            statusMessageTypes.add(statusMessage);

            try {
               xml = this.jaxContextCentralizer.toXml(StatusMessageType.class, statusMessage);
               LOG.debug("***** STATUS MESSAGE : " + xml + " ****");
            } catch (GFDDPPException var13) {
               LOG.error("A problem occur while trying to marshal PharmaceuticalCareIds" + var13.getMessage());
            }
         }
      }

      if (events != null && events.getArchivePrescriptionEventType() != null) {
         ArchivePrescriptionEventType apet = events.getArchivePrescriptionEventType();
         statusMessage = modelObjectFactory.createStatusMessageType();
         statusMessage.setSubjectReference(this.createSubjectReferenceType(apet.getDguid(), apet.getSguid(), messageOnQueue.getMessageId()));
         statusMessage.setOriginator(this.createOriginator(messageOnQueue.getPharmacyId()));
         statusMessage.setTarget(this.createTargetType(messageOnQueue.getPharmacyId()));
         statusMessage.setMessageInformation(this.createMessageInformation(response, ex));
         statusMessageTypes.add(statusMessage);

         try {
            xml = this.jaxContextCentralizer.toXml(StatusMessageType.class, statusMessage);
            LOG.debug("***** STATUS MESSAGE : " + xml + " ****");
         } catch (GFDDPPException var12) {
            LOG.error("A problem occur while trying to marshal PharmaceuticalCareIds" + var12.getMessage());
         }
      }

      if (events != null && events.getArchivePrescriptionCommentEventType() != null) {
         ArchivePrescriptionCommentEventType apet = events.getArchivePrescriptionCommentEventType();
         statusMessage = modelObjectFactory.createStatusMessageType();
         statusMessage.setSubjectReference(this.createSubjectReferenceType(apet.getDguid(), apet.getSguid(), messageOnQueue.getMessageId()));
         statusMessage.setOriginator(this.createOriginator(messageOnQueue.getPharmacyId()));
         statusMessage.setTarget(this.createTargetType(messageOnQueue.getPharmacyId()));
         statusMessage.setMessageInformation(this.createMessageInformation(response, ex));
         statusMessageTypes.add(statusMessage);

         try {
            xml = this.jaxContextCentralizer.toXml(StatusMessageType.class, statusMessage);
            LOG.debug("***** STATUS MESSAGE : " + xml + " ****");
         } catch (GFDDPPException var11) {
            LOG.error("A problem occur while trying to marshal PharmaceuticalCareIds" + var11.getMessage());
         }
      }

      return statusMessageTypes;
   }

   private MessageInformation createMessageInformation(SimpleResponseType response, IntegrationModuleException ex) {
      ObjectFactory modelObjectFactory = new ObjectFactory();
      MessageInformation messageInformation = modelObjectFactory.createMessageInformation();
      if (response != null) {
         LOG.debug("***** RESPONSE FROM TIP SYSTEM NOT NULL ****");
         if (response.getStatus() != null) {
            LOG.debug("***** STATUS IN RESPONSE NOT NULL ****");
            this.fillMessageInformationResponse(messageInformation, MessageType.INFO, response);
         } else {
            LOG.debug("***** STATUS IN RESPONSE NULL ****");
            this.fillMessageInformationLabel(messageInformation, MessageType.INFO, I18nHelper.getAllLanguagesLabels("statusmanagement.empty.status.response"));
         }
      } else if (ex != null) {
         LOG.debug("***** RESPONSE is NULL but got an exception: " + ex + "****");
         this.fillMessageInformationLabelLocal(messageInformation, MessageType.ERROR, ex.getLocalizedMessage());
      } else {
         LOG.debug("***** RESPONSE is NULL but got an exception: " + ex + "****");
         this.fillMessageInformationLabel(messageInformation, MessageType.ERROR, I18nHelper.getAllLanguagesLabels("statusmanagement.unknown.error"));
      }

      return messageInformation;
   }

   private void fillMessageInformationLabelLocal(MessageInformation messageInformation, MessageType messageType, String localizedMessage) {
      TextType textType = new TextType();
      textType.setLanguage(IntegrationModuleException.getUserLocale().toUpperCase());
      textType.setValue(localizedMessage);
      messageInformation.getMessageText().add(textType);
      this.fillMessageInformation(messageInformation, messageType, (String)null);
   }

   private void fillMessageInformationLabel(MessageInformation messageInformation, MessageType messageType, Map<String, String> labels) {
      String nlLabel = (String)labels.get("NL");
      String frLabel = (String)labels.get("FR");
      String engLabel = (String)labels.get("ENG");
      String[] splittedNL = nlLabel.split(",");
      String[] splittedFR = frLabel.split(",");
      String[] splittedENG = engLabel.split(",");
      TextType textTypeNL = new TextType();
      textTypeNL.setLanguage("NL");
      textTypeNL.setValue(splittedNL[1]);
      messageInformation.getMessageText().add(textTypeNL);
      TextType textTypeFR = new TextType();
      textTypeFR.setLanguage(Locale.FRENCH.toString().toUpperCase());
      textTypeFR.setValue(splittedFR[1]);
      messageInformation.getMessageText().add(textTypeFR);
      TextType textTypeENG = new TextType();
      textTypeENG.setLanguage(Locale.ENGLISH.toString().toUpperCase());
      textTypeENG.setValue(splittedENG[1]);
      messageInformation.getMessageText().add(textTypeENG);
      this.fillMessageInformation(messageInformation, messageType, splittedENG[0]);
   }

   private void fillMessageInformation(MessageInformation messageInformation, MessageType messageType, String messageSubType) {
      DatatypeFactory dataTypeFactory;
      try {
         dataTypeFactory = DatatypeFactory.newInstance();
      } catch (DatatypeConfigurationException var6) {
         throw new IntegrationModuleRuntimeException(var6.getCause());
      }

      GregorianCalendar gregorianCalendar = new GregorianCalendar();
      gregorianCalendar.setTimeInMillis(System.currentTimeMillis());
      messageInformation.setDateTime(dataTypeFactory.newXMLGregorianCalendar(gregorianCalendar));
      messageInformation.setMessageType(messageType);
      messageInformation.setMessageSubType(messageSubType);
   }

   private void fillMessageInformationResponse(MessageInformation messageInformation, MessageType messageType, SimpleResponseType response) {
      Iterator var5 = response.getStatus().getMessage().iterator();

      while(var5.hasNext()) {
         LocalisedString message = (LocalisedString)var5.next();
         TextType textType = new TextType();
         textType.setLanguage(message.getLang().toString());
         textType.setValue(message.getValue());
         messageInformation.getMessageText().add(textType);
      }

      DatatypeFactory dataTypeFactory;
      try {
         dataTypeFactory = DatatypeFactory.newInstance();
      } catch (DatatypeConfigurationException var7) {
         throw new IntegrationModuleRuntimeException(var7.getCause());
      }

      GregorianCalendar gregorianCalendar = new GregorianCalendar();
      gregorianCalendar.setTimeInMillis(System.currentTimeMillis());
      messageInformation.setDateTime(dataTypeFactory.newXMLGregorianCalendar(gregorianCalendar));
      messageInformation.setMessageType(messageType);
      messageInformation.setMessageSubType(response.getStatus().getCode());
   }

   private TargetType createTargetType(String pharmacyId) {
      ObjectFactory modelObjectFactory = new ObjectFactory();
      TargetType target = modelObjectFactory.createTargetType();
      target.setTargetId(pharmacyId);
      target.setTargetType("NIHII");
      return target;
   }

   private OriginatorType createOriginator(String pharmacyId) {
      ObjectFactory modelObjectFactory = new ObjectFactory();
      OriginatorType originator = modelObjectFactory.createOriginatorType();
      originator.setServiceType(ServiceType.CM.toString());
      originator.setOriginatorType("NIHII");
      originator.setOriginatorId(pharmacyId);
      return originator;
   }

   private SubjectReferenceType createSubjectReferenceType(String dguid, String sguid, String mguid) {
      ObjectFactory modelObjectFactory = new ObjectFactory();
      SubjectReferenceType subjectReferenceType = modelObjectFactory.createSubjectReferenceType();
      subjectReferenceType.setDispensationGUID(dguid);
      subjectReferenceType.setSessionGUID(sguid);
      subjectReferenceType.setMessageGUID(mguid);
      return subjectReferenceType;
   }

   private void putStatusMessageOnQueue(List<StatusMessageType> statusMessages) {
      LOG.debug("***** ADDING NEW STATUS MESSAGE ON QUEUE****");
      Iterator var3 = statusMessages.iterator();

      while(var3.hasNext()) {
         StatusMessageType statusMessageType = (StatusMessageType)var3.next();
         this.statusQueue.add(statusMessageType);
         LOG.debug("***** NEW STATUS MESSAGE ON QUEUE****");
      }

   }

   private SealedMessageRequestType extractSealedMessageRequest(EHealthMessage eHealthMessage) throws IntegrationModuleException {
      LOG.info("extracting SealedMessagerRequestType from EHealthMessage");
      byte[] queueMessage = eHealthMessage.getMessage();
      SealedMessageRequestTypeXml sealXml = null;

      try {
         sealXml = (SealedMessageRequestTypeXml)this.jaxContextCentralizer.toObject(SealedMessageRequestTypeXml.class, queueMessage);
      } catch (GFDDPPException var5) {
         throw new IntegrationModuleException("Couldn't unmarshal Object SealedMessageRequestTypeXml", var5.getCause());
      }

      return sealXml.getSealedMessageRequestType();
   }

   private SingleMessage extractSingleMessage(SealedMessageRequestType sealedMessageRequestType) throws IntegrationModuleException {
      LOG.info("extracting SingleMessage from SealedMessagerRequestType");
      byte[] sealedMsgReqTypBytes = sealedMessageRequestType.getSingleMessageSealed();
      SingleMessage singleMessage = null;

      try {
         singleMessage = (SingleMessage)this.jaxContextCentralizer.toObject(SingleMessage.class, sealedMsgReqTypBytes);
         return singleMessage;
      } catch (GFDDPPException var5) {
         throw new IntegrationModuleException("Couldn't unmarshal Object SingleMessage ", var5.getCause());
      }
   }

   protected void initFailedMsgFolders(PropertyHandler propertyHandler) throws IntegrationModuleException {
      if (propertyHandler.hasProperty("UNENCRYPTED_FOLDER")) {
         this.unencrypted_folder_name = propertyHandler.getProperty("UNENCRYPTED_FOLDER");
         if (propertyHandler.hasProperty("INVALID_FOLDER")) {
            this.invalid_folder_name = propertyHandler.getProperty("INVALID_FOLDER");
            File unencryptedMsgFolder = new File(this.unencrypted_folder_name);
            File invalidMsgFolder = new File(this.invalid_folder_name);
            if (!unencryptedMsgFolder.exists() && !unencryptedMsgFolder.mkdirs()) {
               throw new RuntimeException("Not able to create folder " + this.unencrypted_folder_name);
            } else if (!invalidMsgFolder.exists() && !invalidMsgFolder.mkdirs()) {
               throw new RuntimeException("Not able to create folder " + this.invalid_folder_name);
            }
         } else {
            throw new RuntimeException("INVALID FOLDER not found in properties file !");
         }
      } else {
         throw new RuntimeException("UNENCRYPTED_FOLDER not found in properties file !");
      }
   }

   private EncryptionToken extractEncryptionToken(String tipid) throws IntegrationModuleException {
      EncryptionToken etk;
      if (this.propertyHandler.hasProperty("multitenancy.disabled")) {
         if ("false".equals(this.propertyHandler.getProperty("multitenancy.disabled"))) {
            etk = (EncryptionToken)this.etkHelper.getTIP_ETK(tipid).get(0);
         } else {
            etk = (EncryptionToken)this.etkHelper.getTIPSystem_ETK(this.propertyHandler.getProperty("tipsystem.id")).get(0);
         }
      } else {
         etk = (EncryptionToken)this.etkHelper.getTIP_ETK(tipid).get(0);
      }

      return etk;
   }

   private void moveQueueMsgPlainText(EHealthMessage ehealthMsg) {
      File dumpFile = null;

      try {
         SealedMessageRequestType sealedMessageRequestType = this.extractSealedMessageRequest(ehealthMsg);
         SingleMessage singleMsgObj = this.extractSingleMessage(sealedMessageRequestType);
         byte[] xmlSingleMsg = this.jaxContextCentralizer.toXml(SingleMessage.class, singleMsgObj).getBytes(Charsets.UTF_8);
         String currentFileName = this.queue.getFirstFileName();
         String dumpFileName = this.invalid_folder_name + File.separator + currentFileName.substring(0, currentFileName.lastIndexOf("_LOCK")) + ".xml";
         dumpFile = new File(dumpFileName);
         FileUtils.writeByteArrayToFile(dumpFile, xmlSingleMsg);
      } catch (GFDDPPException | IOException | IntegrationModuleException var8) {
         LOG.error(var8);
      }

   }

   public void shouldStopProcessing() {
      shouldStopProcessing = true;
   }
}
