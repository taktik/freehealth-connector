package be.business.connector.gfddpp.tipsystem;

import be.apb.gfddpp.common.exceptions.GFDDPPException;
import be.apb.gfddpp.common.log.LogsSMC;
import be.apb.gfddpp.common.medicationscheme.status.MSStatusCode;
import be.apb.gfddpp.common.medicationscheme.status.MSStatusResolver;
import be.apb.gfddpp.common.status.StatusCode;
import be.apb.gfddpp.common.status.StatusResolver;
import be.apb.gfddpp.common.utils.SingleMessageBuilder;
import be.apb.gfddpp.common.utils.SingleMessageWrapper;
import be.apb.gfddpp.common.utils.Utils;
import be.apb.gfddpp.helper.SingleMessageValidationHelper;
import be.apb.gfddpp.tipsys.errors.Error;
import be.apb.gfddpp.tipsys.errors.Errors;
import be.apb.standards.smoa.schema.model.v1.AbstractEntityType;
import be.apb.standards.smoa.schema.model.v1.StatusMessageType;
import be.apb.standards.smoa.schema.model.v1.SubjectReferenceType;
import be.apb.standards.smoa.schema.v1.ArchivePrescriptionCommentEventType;
import be.apb.standards.smoa.schema.v1.ArchivePrescriptionEventType;
import be.apb.standards.smoa.schema.v1.AvailabilityStatus;
import be.apb.standards.smoa.schema.v1.DataEntryRequest;
import be.apb.standards.smoa.schema.v1.EventFolderType;
import be.apb.standards.smoa.schema.v1.FormatCode;
import be.apb.standards.smoa.schema.v1.HeaderType;
import be.apb.standards.smoa.schema.v1.ID;
import be.apb.standards.smoa.schema.v1.MedicationSchemeEntriesRequest;
import be.apb.standards.smoa.schema.v1.MetaDataListType;
import be.apb.standards.smoa.schema.v1.MetaDataType;
import be.apb.standards.smoa.schema.v1.ObjectFactory;
import be.apb.standards.smoa.schema.v1.OrganizationIdType;
import be.apb.standards.smoa.schema.v1.OrganizationRoleType;
import be.apb.standards.smoa.schema.v1.SingleMessage;
import be.apb.standards.smoa.schema.v1.SmoaMessageType;
import be.business.connector.common.ApplicationConfig;
import be.business.connector.common.StandaloneRequestorProvider;
import be.business.connector.common.module.AbstractIntegrationModule;
import be.business.connector.core.exceptions.IntegrationModuleEhealthException;
import be.business.connector.core.exceptions.IntegrationModuleException;
import be.business.connector.core.exceptions.IntegrationModuleRuntimeException;
import be.business.connector.core.technical.connector.utils.Crypto;
import be.business.connector.core.utils.EncryptionUtils;
import be.business.connector.core.utils.Exceptionutils;
import be.business.connector.core.utils.I18nHelper;
import be.business.connector.core.utils.PropertyHandler;
import be.business.connector.core.utils.SealedProcessor;
import be.business.connector.gfddpp.domain.SealedMessageRequestTypeXml;
import be.business.connector.gfddpp.domain.ThreeStateBoolean;
import be.business.connector.gfddpp.domain.medicationscheme.DataEntry;
import be.business.connector.gfddpp.domain.medicationscheme.Status;
import be.business.connector.gfddpp.domain.medicationscheme.protocol.StoreDataEntriesRequest;
import be.business.connector.gfddpp.domain.medicationscheme.protocol.StoreDataEntriesResponse;
import be.business.connector.gfddpp.tipsystem.offline.queue.EHealthMessageQueueFacade;
import be.business.connector.gfddpp.tipsystem.offline.queue.QueueBehavior;
import be.business.connector.gfddpp.tipsystem.offline.queue.QueueControle;
import be.business.connector.gfddpp.tipsystem.status.offline.queue.StatusMessageQueue;
import be.business.connector.gfddpp.utils.DataEntryURI;
import be.business.connector.gfddpp.utils.GFDDPPValidationUtils;
import be.business.connector.gfddpp.utils.MappingUtils;
import be.business.connector.gfddpp.utils.RequestCreatorUtils;
import be.business.connector.gfddpp.utils.SingleMessageInfo;
import be.business.connector.projects.common.services.tipsystem.TipSystemServiceImpl;
import be.business.connector.projects.common.utils.ProductFilterSingleton;
import be.business.connector.projects.common.utils.ProductFilterUtils;
import be.business.connector.projects.common.utils.ValidationUtils;
import be.ehealth.apb.gfddpp.services.tipsystem.LangageType;
import be.ehealth.apb.gfddpp.services.tipsystem.LocalisedString;
import be.ehealth.apb.gfddpp.services.tipsystem.RoutedSealedRequestType;
import be.ehealth.apb.gfddpp.services.tipsystem.RoutedSealedResponseType;
import be.ehealth.apb.gfddpp.services.tipsystem.SealedMessageRequestType;
import be.ehealth.apb.gfddpp.services.tipsystem.SimpleResponseType;
import be.fgov.ehealth.etee.crypto.encrypt.EncryptionToken;
import java.io.UnsupportedEncodingException;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import java.util.Map.Entry;
import javax.xml.ws.soap.SOAPFaultException;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.JoinPoint.StaticPart;
import org.aspectj.runtime.internal.Conversions;
import org.aspectj.runtime.reflect.Factory;
import org.perf4j.aop.Profiled;
import org.perf4j.log4j.aop.TimingAspect;

public class TIPSystemIntegrationModuleImpl extends AbstractIntegrationModule implements TIPSystemIntegrationModule {
   private static final Logger LOG;
   private static final String MODULE = "TIPSystemIntegrationModuleImpl";
   private static final String SUCCESS;
   private static final String TIP_201 = "TIP_201";
   private static final String INVALID_MEDICATION_SCHEME;
   private QueueBehavior behavior;
   private static QueueControle controle;
   private static EHealthMessageQueueFacade facade;
   protected static StatusMessageQueue statusMessageQueue;
   private SingleMessageValidationHelper xmlValidationhelper;
   private ProductFilterUtils productFilterUtils;
   // $FF: synthetic field
   private static final StaticPart ajc$tjp_0;
   // $FF: synthetic field
   private static Annotation ajc$anno$0;
   // $FF: synthetic field
   private static final StaticPart ajc$tjp_1;
   // $FF: synthetic field
   private static Annotation ajc$anno$1;
   // $FF: synthetic field
   private static final StaticPart ajc$tjp_2;
   // $FF: synthetic field
   private static Annotation ajc$anno$2;
   // $FF: synthetic field
   private static final StaticPart ajc$tjp_3;
   // $FF: synthetic field
   private static Annotation ajc$anno$3;
   // $FF: synthetic field
   private static final StaticPart ajc$tjp_4;
   // $FF: synthetic field
   private static Annotation ajc$anno$4;
   // $FF: synthetic field
   private static final StaticPart ajc$tjp_5;
   // $FF: synthetic field
   private static Annotation ajc$anno$5;
   // $FF: synthetic field
   private static final StaticPart ajc$tjp_6;
   // $FF: synthetic field
   private static Annotation ajc$anno$6;

   static {
      ajc$preClinit();
      LOG = Logger.getLogger(TIPSystemIntegrationModuleImpl.class);
      SUCCESS = StatusCode.TIP_200.name();
      INVALID_MEDICATION_SCHEME = StatusCode.TIP_ERROR_INVALID_MEDICATION_SCHEME.name();
   }

   public ProductFilterUtils getProductFilterUtils() {
      return this.productFilterUtils;
   }

   public void setProductFilterUtils(ProductFilterUtils productFilterUtils) {
      this.productFilterUtils = productFilterUtils;
   }

   public TIPSystemIntegrationModuleImpl() throws IntegrationModuleException {
      this(true);
   }

   protected TIPSystemIntegrationModuleImpl(boolean initMessageQueues) throws IntegrationModuleException {
      ApplicationConfig.getInstance().checkLatestTIPConfiguration();
      this.xmlValidationhelper = new SingleMessageValidationHelper();
      this.setProductFilterUtils(new ProductFilterUtils());
      if (initMessageQueues) {
         this.initMessageQueues();
      }

      LOG.info("*************** TIP System module init correctly *******************");
   }

   private void initMessageQueues() throws IntegrationModuleException {
      if (this.getPropertyHandler().getProperty("MESSAGE_QUEUE_FOLDER").equals(this.getPropertyHandler().getProperty("STATUS_MESSAGE_QUEUE_FOLDER"))) {
         throw new IntegrationModuleException(I18nHelper.getLabel("error.path.msg.queue.equal.to.status.msg.queue"));
      } else {
         try {
            statusMessageQueue = new StatusMessageQueue(PropertyHandler.getInstance(), EncryptionUtils.getInstance());
         } catch (Exception var2) {
            throw new IntegrationModuleException(I18nHelper.getLabel("error.creation.statusMessageQueue"), var2);
         }

         setControle(new QueueControle());
         facade = new EHealthMessageQueueFacade(PropertyHandler.getInstance(), EncryptionUtils.getInstance());
         if (this.behavior == null) {
            this.behavior = new QueueBehavior(getFacade().getQueue(), statusMessageQueue);
         }

         getControle().processMessages(this.behavior);
      }
   }

   public String getNihiiPharmacyId(String info, boolean SyncEventType) throws IntegrationModuleException {
      String procedure = "getNihiiPharmacyId";
      String NihiiPharmacyId = "";

      try {
         String requestorId = StandaloneRequestorProvider.getRequestorIdInformation();
         String requestorType = StandaloneRequestorProvider.getRequestorTypeInformation();
         info = info + " RequestorId:" + requestorId + " " + "requestorType:" + requestorType + " ";
         LOG.debug("TIPSystemIntegrationModuleImpl=-----= ----- " + procedure + " =$=" + info);
         NihiiPharmacyId = requestorId;
      } catch (IntegrationModuleException var7) {
         LOG.info("TIPSystemIntegrationModuleImpl=-----= ----- " + procedure + " =$=" + info + "no valid session: offline operation" + var7.getMessage());
      }

      if (NihiiPharmacyId == null || NihiiPharmacyId.isEmpty()) {
         if (SyncEventType) {
            LOG.error("TIPSystemIntegrationModuleImpl=-----= ----- " + procedure + " =$=" + info + "error: error.validation.SyncEventType.nosession");
            throw new IntegrationModuleException(I18nHelper.getLabel("error.validation.SyncEventType.nosession"));
         }

         NihiiPharmacyId = this.getPropertyHandler().getProperty("pharmacy.nihii");
         LOG.error("TIPSystemIntegrationModuleImpl=-----= ----- " + procedure + " =$=" + info + "no valid session load from properties NihiiPharmacy:" + NihiiPharmacyId);
      }

      if (NihiiPharmacyId != null && !NihiiPharmacyId.isEmpty()) {
         return NihiiPharmacyId;
      } else {
         LOG.error("TIPSystemIntegrationModuleImpl=-----= ----- " + procedure + " =$=" + info + "error: error.pharmacy.nihii.not.found");
         throw new IntegrationModuleException(I18nHelper.getLabel("error.pharmacy.nihii.not.found"));
      }
   }

   public String getTipId(String info, String tipId) throws IntegrationModuleException {
      if (StringUtils.isEmpty(tipId) && this.getPropertyHandler().hasProperty("default.tip.id")) {
         tipId = this.getPropertyHandler().getProperty("default.tip.id");
      }

      ValidationUtils.validateExistingTipId(tipId, this.getPropertyHandler());
      return tipId;
   }

   @Profiled(
      logFailuresSeparately = true,
      tag = "{$1}.TIPSystemIntegrationModuleImpl#archivePrescription",
      logger = "org.perf4j.TimingLogger_Executor"
   )
   public String archivePrescription(String rid) throws IntegrationModuleException {
      JoinPoint var18 = Factory.makeJP(ajc$tjp_0, this, this, rid);
      TimingAspect var10000 = TimingAspect.aspectOf();
      Object[] var19 = new Object[]{this, rid, var18};
      ProceedingJoinPoint var10001 = (new TIPSystemIntegrationModuleImpl$AjcClosure1(var19)).linkClosureAndJoinPoint(69648);
      Annotation var10002 = ajc$anno$0;
      if (var10002 == null) {
         var10002 = ajc$anno$0 = TIPSystemIntegrationModuleImpl.class.getDeclaredMethod("archivePrescription", String.class).getAnnotation(Profiled.class);
      }

      return (String)var10000.doPerfLogging(var10001, (Profiled)var10002);
   }

   private ID getId(String value, String type, String typeId) throws IntegrationModuleException {
      ID id = new ID();
      id.setValue(value);
      id.setType(type);
      id.setIdType(typeId);
      return id;
   }

   @Profiled(
      logFailuresSeparately = true,
      tag = "{$1}.TIPSystemIntegrationModuleImpl#archivePrescriptionComment",
      logger = "org.perf4j.TimingLogger_Executor"
   )
   public String archivePrescriptionComment(String rid, String comment) throws IntegrationModuleException {
      JoinPoint var18 = Factory.makeJP(ajc$tjp_1, this, this, rid, comment);
      TimingAspect var10000 = TimingAspect.aspectOf();
      Object[] var19 = new Object[]{this, rid, comment, var18};
      ProceedingJoinPoint var10001 = (new TIPSystemIntegrationModuleImpl$AjcClosure3(var19)).linkClosureAndJoinPoint(69648);
      Annotation var10002 = ajc$anno$1;
      if (var10002 == null) {
         var10002 = ajc$anno$1 = TIPSystemIntegrationModuleImpl.class.getDeclaredMethod("archivePrescriptionComment", String.class, String.class).getAnnotation(Profiled.class);
      }

      return (String)var10000.doPerfLogging(var10001, (Profiled)var10002);
   }

   @Profiled(
      logFailuresSeparately = true,
      tag = "{$1}.TIPSystemIntegrationModuleImpl#registerData",
      logger = "org.perf4j.TimingLogger_Executor"
   )
   public String registerData(byte[] singleMessage, String tipId, boolean requestPatientSignature, ThreeStateBoolean patientConsent) throws IntegrationModuleException {
      StaticPart var10000 = ajc$tjp_2;
      Object[] var18 = new Object[]{singleMessage, tipId, Conversions.booleanObject(requestPatientSignature), patientConsent};
      JoinPoint var17 = Factory.makeJP(var10000, this, this, var18);
      TimingAspect var20 = TimingAspect.aspectOf();
      Object[] var19 = new Object[]{this, singleMessage, tipId, Conversions.booleanObject(requestPatientSignature), patientConsent, var17};
      ProceedingJoinPoint var10001 = (new TIPSystemIntegrationModuleImpl$AjcClosure5(var19)).linkClosureAndJoinPoint(69648);
      Annotation var10002 = ajc$anno$2;
      if (var10002 == null) {
         var10002 = ajc$anno$2 = TIPSystemIntegrationModuleImpl.class.getDeclaredMethod("registerData", byte[].class, String.class, Boolean.TYPE, ThreeStateBoolean.class).getAnnotation(Profiled.class);
      }

      return (String)var20.doPerfLogging(var10001, (Profiled)var10002);
   }

   protected TIPSystemIntegrationModuleImpl.RegisterEventResponse registerEvent(byte[] singleMessage, String info, String tipId, boolean requestPatientSignature, ThreeStateBoolean patientConsent, String procedure, long startTimeStamp) throws IntegrationModuleEhealthException, IntegrationModuleException, IntegrationModuleRuntimeException {
      TIPSystemIntegrationModuleImpl.RegisterEventResponse registerEventResponse = new TIPSystemIntegrationModuleImpl.RegisterEventResponse();

      double difftime;
      try {
         LOG.debug("===================== Validate single message UTF-8 bytes ==============================");
         if (this.getPropertyHandler().hasProperty("TIP_UTF_8_ONLY_INPUT") && this.getPropertyHandler().getProperty("TIP_UTF_8_ONLY_INPUT").equals("true")) {
            ValidationUtils.validateUTF_8Input(singleMessage);
         }

         SingleMessageInfo smi = new SingleMessageInfo(singleMessage);
         info = info + smi.getInfo();
         tipId = this.getTipId(info, tipId);
         info = info + " tipId:" + tipId + " ";
         smi.validateRegisterData();
         String NihiiPharmacyId = this.getNihiiPharmacyId(info, smi.getHasSyncEventType());
         info = info + " NihiiPharmacyId:" + NihiiPharmacyId + " ";
         smi.validateNihiiPharmacyId(NihiiPharmacyId);
         smi.setHeaderNihiiPharmacyId(NihiiPharmacyId);
         ProductFilterSingleton.getInstance(this.getPropertyHandler());
         smi.setFilterProducts(this.getProductFilterUtils(), this.getPropertyHandler());
         smi.setGuids(NihiiPharmacyId);
         smi.validateSingleMessageXsd();
         SealedMessageRequestType sealedMessageRequestType = smi.setCommonData(this.getEncryptionUtils(), tipId, requestPatientSignature, patientConsent);
         registerEventResponse.setSingleMessageInfo(smi);
         String sealedMsgXML;
         byte[] singleMessageBytes;
         if (!smi.getHasSyncEventType()) {
            LOG.info("TIPSystemIntegrationModuleImpl=-----= ----- " + procedure + " =$=" + info + "offline queue");
            SealedMessageRequestTypeXml sealedMessageRequestTypeXml = new SealedMessageRequestTypeXml();
            sealedMessageRequestTypeXml.setSealedMessageRequestType(sealedMessageRequestType);
            sealedMsgXML = this.getJaxContextCentralizer().toXml(SealedMessageRequestTypeXml.class, sealedMessageRequestTypeXml);
            singleMessageBytes = sealedMsgXML.getBytes("UTF-8");
            LOG.debug("TIPSystemIntegrationModuleImpl=-----= ----- " + procedure + " =$=" + info + "offline queue " + LogsSMC.IntermediateMessage.TO_OFFLINE_QUEUE + " " + smi.getLogInfoGuids());
            getFacade().registerData(singleMessageBytes, smi.getEvents(), smi.getMessageID(), smi.getHeaderPharmacyId());
            LOG.debug("TIPSystemIntegrationModuleImpl=-----= ----- " + procedure + " =$=" + info + "Register complete ( sync part )");
         } else {
            ApplicationConfig.getInstance().assertValidSession();
            LOG.info("TIPSystemIntegrationModuleImpl=-----= ----- " + procedure + " =$=" + info + "SyncEventType");
            String sealedMessageTipId = null;
            if (sealedMessageRequestType.getRoutingParameters() != null && !org.apache.commons.lang.StringUtils.isEmpty(sealedMessageRequestType.getRoutingParameters().getTIPId())) {
               sealedMessageTipId = sealedMessageRequestType.getRoutingParameters().getTIPId();
            } else {
               sealedMessageRequestType.getRoutingParameters().setTIPId(this.getPropertyHandler().getProperty("default.tip.id"));
            }

            sealedMsgXML = null;

            EncryptionToken etk;
            try {
               if ("false".equals(this.getPropertyHandler().getProperty("multitenancy.disabled", "false"))) {
                  etk = (EncryptionToken)this.getEtkHelper().getTIP_ETK(sealedMessageTipId).get(0);
               } else {
                  etk = (EncryptionToken)this.getEtkHelper().getTIPSystem_ETK(this.getPropertyHandler().getProperty("tipsystem.id")).get(0);
               }
            } catch (IntegrationModuleException var20) {
               LOG.error("TIPSystemIntegrationModuleImpl=-----= ----- " + procedure + " =$=" + info + "error: Couldn't retrieve ETK", var20);
               throw var20;
            }

            singleMessageBytes = sealedMessageRequestType.getSingleMessageSealed();
            byte[] SingleMessageSealed = SealedProcessor.createSealedSync(etk, singleMessageBytes, "SingleMessage");
            sealedMessageRequestType.setSingleMessageSealed(SingleMessageSealed);
            SimpleResponseType response = TipSystemServiceImpl.getInstance().registerData(sealedMessageRequestType);
            registerEventResponse.setTipResponse(response);
            String eventtype = smi.getEventType();
            if (response == null) {
               LOG.error("TIPSystemIntegrationModuleImpl=-----= ----- " + procedure + " =$=" + info + "Response from the tip/eHealth is null, something went wrong while trying to send a message to the tip");
               throw new IntegrationModuleException("error.no.response.tip");
            }

            String statusCode = response.getStatus().getCode();
            if (!SUCCESS.equals(statusCode) && !INVALID_MEDICATION_SCHEME.equals(statusCode)) {
               if (StringUtils.equals(response.getStatus().getCode(), "TIP_201")) {
                  LOG.info("TIPSystemIntegrationModuleImpl=-----= ----- " + procedure + " =$=" + info + "TIP_201" + " TIP_201");
                  throw new IntegrationModuleException("Sending " + eventtype + " message to tip failed");
               }

               LOG.error("TIPSystemIntegrationModuleImpl=-----= ----- " + procedure + " =$=" + info + response.getStatus().getCode());
               throw new IntegrationModuleException("Sending " + eventtype + " message to tip failed");
            }

            LOG.info("TIPSystemIntegrationModuleImpl=-----= ----- " + procedure + " =$=" + info + statusCode + " " + statusCode);
         }
      } catch (GFDDPPException var21) {
         difftime = (double)(System.nanoTime() - startTimeStamp) / 1.0E9D;
         LOG.info("TIPSystemIntegrationModuleImpl=-----= einde " + procedure + " =$=" + info + " => " + " (dt:" + difftime + ") " + "error GFDDPPException: " + var21.getMessage());
         throw new IntegrationModuleException(StatusResolver.resolveMessage(StatusResolver.getLocalResourceBundle(), var21.getStatusCode(), var21.getPlaceHolders()), var21);
      } catch (Throwable var22) {
         difftime = (double)(System.nanoTime() - startTimeStamp) / 1.0E9D;
         LOG.info("TIPSystemIntegrationModuleImpl=-----= einde " + procedure + " =$=" + info + " => " + " (dt:" + difftime + ") " + "error Throwable: " + var22.getMessage());
         Exceptionutils.errorHandler(var22);
      }

      return registerEventResponse;
   }

   @Profiled(
      logFailuresSeparately = true,
      tag = "{$1}.TIPSystemIntegrationModuleImpl#registerMedicationSchemeEntries",
      logger = "org.perf4j.TimingLogger_Executor"
   )
   public StoreDataEntriesResponse registerMedicationSchemeEntries(StoreDataEntriesRequest storeRequest) throws IntegrationModuleException {
      JoinPoint var30 = Factory.makeJP(ajc$tjp_3, this, this, storeRequest);
      TimingAspect var10000 = TimingAspect.aspectOf();
      Object[] var31 = new Object[]{this, storeRequest, var30};
      ProceedingJoinPoint var10001 = (new TIPSystemIntegrationModuleImpl$AjcClosure7(var31)).linkClosureAndJoinPoint(69648);
      Annotation var10002 = ajc$anno$3;
      if (var10002 == null) {
         var10002 = ajc$anno$3 = TIPSystemIntegrationModuleImpl.class.getDeclaredMethod("registerMedicationSchemeEntries", StoreDataEntriesRequest.class).getAnnotation(Profiled.class);
      }

      return (StoreDataEntriesResponse)var10000.doPerfLogging(var10001, (Profiled)var10002);
   }

   @Profiled(
      logFailuresSeparately = true,
      tag = "{$1}.TIPSystemIntegrationModuleImpl#revokeData",
      logger = "org.perf4j.TimingLogger_Executor"
   )
   public void revokeData(byte[] singleMessage, String tipId, boolean requestPatientSignature, ThreeStateBoolean patientConsent) throws IntegrationModuleException {
      StaticPart var10000 = ajc$tjp_4;
      Object[] var20 = new Object[]{singleMessage, tipId, Conversions.booleanObject(requestPatientSignature), patientConsent};
      JoinPoint var19 = Factory.makeJP(var10000, this, this, var20);
      TimingAspect var22 = TimingAspect.aspectOf();
      Object[] var21 = new Object[]{this, singleMessage, tipId, Conversions.booleanObject(requestPatientSignature), patientConsent, var19};
      ProceedingJoinPoint var10001 = (new TIPSystemIntegrationModuleImpl$AjcClosure9(var21)).linkClosureAndJoinPoint(69648);
      Annotation var10002 = ajc$anno$4;
      if (var10002 == null) {
         var10002 = ajc$anno$4 = TIPSystemIntegrationModuleImpl.class.getDeclaredMethod("revokeData", byte[].class, String.class, Boolean.TYPE, ThreeStateBoolean.class).getAnnotation(Profiled.class);
      }

      var22.doPerfLogging(var10001, (Profiled)var10002);
   }

   @Profiled(
      logFailuresSeparately = true,
      tag = "{$1}.TIPSystemIntegrationModuleImpl#updateData",
      logger = "org.perf4j.TimingLogger_Executor"
   )
   public String updateData(byte[] singleMessage, String tipId, boolean requestPatientSignature, ThreeStateBoolean patientConsent) throws IntegrationModuleException {
      StaticPart var10000 = ajc$tjp_5;
      Object[] var21 = new Object[]{singleMessage, tipId, Conversions.booleanObject(requestPatientSignature), patientConsent};
      JoinPoint var20 = Factory.makeJP(var10000, this, this, var21);
      TimingAspect var23 = TimingAspect.aspectOf();
      Object[] var22 = new Object[]{this, singleMessage, tipId, Conversions.booleanObject(requestPatientSignature), patientConsent, var20};
      ProceedingJoinPoint var10001 = (new TIPSystemIntegrationModuleImpl$AjcClosure11(var22)).linkClosureAndJoinPoint(69648);
      Annotation var10002 = ajc$anno$5;
      if (var10002 == null) {
         var10002 = ajc$anno$5 = TIPSystemIntegrationModuleImpl.class.getDeclaredMethod("updateData", byte[].class, String.class, Boolean.TYPE, ThreeStateBoolean.class).getAnnotation(Profiled.class);
      }

      return (String)var23.doPerfLogging(var10001, (Profiled)var10002);
   }

   @Profiled(
      logFailuresSeparately = true,
      tag = "0.TIPSystemIntegrationModuleImpl#getStatusMessages",
      logger = "org.perf4j.TimingLogger_Executor"
   )
   public String getStatusMessages(String sGuid, String dGuid) throws IntegrationModuleException {
      JoinPoint var14 = Factory.makeJP(ajc$tjp_6, this, this, sGuid, dGuid);
      TimingAspect var10000 = TimingAspect.aspectOf();
      Object[] var15 = new Object[]{this, sGuid, dGuid, var14};
      ProceedingJoinPoint var10001 = (new TIPSystemIntegrationModuleImpl$AjcClosure13(var15)).linkClosureAndJoinPoint(69648);
      Annotation var10002 = ajc$anno$6;
      if (var10002 == null) {
         var10002 = ajc$anno$6 = TIPSystemIntegrationModuleImpl.class.getDeclaredMethod("getStatusMessages", String.class, String.class).getAnnotation(Profiled.class);
      }

      return (String)var10000.doPerfLogging(var10001, (Profiled)var10002);
   }

   public void shutdownGracefully() throws IntegrationModuleException {
      LOG.debug("******************** Shutdown application gracefully **********************");
      if (getControle() != null) {
         getControle().shutdown();
      }

   }

   public static QueueControle getControle() {
      return controle;
   }

   public static void setControle(QueueControle controle) {
      TIPSystemIntegrationModuleImpl.controle = controle;
   }

   public static EHealthMessageQueueFacade getFacade() {
      return facade;
   }

   public static void setFacade(EHealthMessageQueueFacade facade) {
      TIPSystemIntegrationModuleImpl.facade = facade;
   }

   private byte[] consolidationStatusResponse(String sGuid, String dGuid, byte[] response) throws IntegrationModuleException, GFDDPPException {
      LOG.debug("******************** Consolidation status message **********************");
      SingleMessageBuilder singleMessageBuilder = new SingleMessageBuilder();
      SingleMessage singleMessage = null;
      if (response != null) {
         try {
            singleMessage = (SingleMessage)this.getJaxContextCentralizer().toObject(SingleMessage.class, response);
            this.addMessageToLocalQueue(singleMessage);
         } catch (GFDDPPException var9) {
            LOG.debug("******************** The response was not unmarshable **********************");
            singleMessage = singleMessageBuilder.createSingleMessageUnsigned();
         }
      } else {
         singleMessage = singleMessageBuilder.createSingleMessageUnsigned();
      }

      LOG.debug("******************** Retrieving status message from queue **********************");
      SingleMessage singleMessageResponse = singleMessageBuilder.createSingleMessageUnsigned();
      singleMessageResponse.getUnsigned().setHeader(singleMessage.getUnsigned().getHeader());
      singleMessageResponse = this.retrieveStatusMessageFromLocalQueue(dGuid, sGuid, singleMessageBuilder, singleMessageResponse);
      LOG.debug("******************** Transform response to array **********************");

      try {
         return this.getJaxContextCentralizer().toXml(SingleMessage.class, singleMessageResponse).getBytes("UTF-8");
      } catch (UnsupportedEncodingException var8) {
         LOG.error("", var8);
         return null;
      }
   }

   private SingleMessage retrieveStatusMessageFromLocalQueue(String dGuid, String sGuid, SingleMessageBuilder singleMessageBuilder, SingleMessage singleMessageResponse) throws IntegrationModuleException {
      List<StatusMessageType> statusMessageTypes = new ArrayList();
      if (statusMessageQueue != null && !statusMessageQueue.isEmpty()) {
         StatusMessageType localStatus = null;

         try {
            localStatus = statusMessageQueue.poll();
         } catch (Exception var9) {
            throw new IntegrationModuleException(I18nHelper.getLabel("error.technical.status.queue"));
         }

         while(localStatus != null) {
            statusMessageTypes.add(localStatus);
            localStatus = statusMessageQueue.poll();
         }

         Iterator var8 = statusMessageTypes.iterator();

         while(var8.hasNext()) {
            StatusMessageType statusMessageType = (StatusMessageType)var8.next();
            if (this.shouldStatusMessageBeAdded(statusMessageType, sGuid, dGuid)) {
               singleMessageBuilder.addEntity(statusMessageType, singleMessageResponse);
            } else {
               statusMessageQueue.add(statusMessageType);
            }
         }
      }

      return singleMessageResponse;
   }

   private boolean shouldStatusMessageBeAdded(StatusMessageType statusMessageType, String sGuid, String dGuid) {
      String s = null;
      String d = null;
      SubjectReferenceType subjectReference = statusMessageType.getSubjectReference();
      if (subjectReference != null) {
         s = statusMessageType.getSubjectReference().getSessionGUID();
         d = statusMessageType.getSubjectReference().getDispensationGUID();
      }

      return !StringUtils.isEmpty(sGuid) && !StringUtils.isEmpty(dGuid) && StringUtils.equals(sGuid, s) && StringUtils.equals(dGuid, d) || !StringUtils.isEmpty(sGuid) && StringUtils.isEmpty(dGuid) && StringUtils.equals(sGuid, s) || StringUtils.isEmpty(sGuid) && StringUtils.isEmpty(dGuid);
   }

   public StatusMessageQueue getStatusMessageQueue() {
      return statusMessageQueue;
   }

   private void addMessageToLocalQueue(SingleMessage singleMessage) {
      LOG.debug("******************** Copying message receive from tip **********************");
      SingleMessageWrapper smw = new SingleMessageWrapper(singleMessage);
      Iterator var4 = smw.getEntities().iterator();

      while(var4.hasNext()) {
         AbstractEntityType entity = (AbstractEntityType)var4.next();
         if (entity instanceof StatusMessageType) {
            statusMessageQueue.add((StatusMessageType)entity);
         }
      }

      LOG.debug("******************** Copying message receive from tip (DONE) **********************");
   }

   // $FF: synthetic method
   static final String archivePrescription_aroundBody0(TIPSystemIntegrationModuleImpl ajc$this, String rid, JoinPoint var2) {
      String procedure = "archivePrescription";
      String info = " ";
      LOG.info("TIPSystemIntegrationModuleImpl=-----= start " + procedure + " =$=" + info);
      long startTimeStamp = System.nanoTime();
      String events = "";

      double difftime;
      try {
         String archivedFilePath = ajc$this.getArchivedFilePath(rid);
         ObjectFactory sof = new ObjectFactory();
         byte[] archivedPrescription = ajc$this.getArchivedPrescription(archivedFilePath, new String[]{rid, archivedFilePath.substring(0, archivedFilePath.indexOf(rid) - 1)});
         ArchivePrescriptionEventType apet = new ArchivePrescriptionEventType();
         apet.setRid(rid);
         apet.setArchiveStandard(archivedPrescription);
         apet.setArchiveStandardVersion("V2");
         apet.setMarkAsDeliverTime(ajc$this.getCurrentXMLGregorianCalendar());

         try {
            apet.setExecutorId(ajc$this.getId(StandaloneRequestorProvider.getRequestorIdInformation(), "PHARMACY", "NIHII"));
         } catch (IntegrationModuleException var30) {
            LOG.info("TIPSystemIntegrationModuleImpl=-----= ----- " + procedure + " =$=" + info + "no valid session: offline operation" + var30.getMessage());
            apet.setExecutorId(ajc$this.getId(ajc$this.getPropertyHandler().getProperty("pharmacy.nihii"), "PHARMACY", "NIHII"));
         }

         apet.setDguid((String)null);
         apet.setSguid((String)null);
         SingleMessage sm = new SingleMessage();
         SmoaMessageType um = new SmoaMessageType();
         EventFolderType eft = sof.createEventFolderType();
         EventFolderType.Events eventFolderTypeEvents = new EventFolderType.Events();
         eventFolderTypeEvents.getEvent().add(apet);
         eft.setEvents(eventFolderTypeEvents);
         um.setAbstractFolder(sof.createEventFolder(eft));
         sm.setUnsigned(um);
         String tip_id = PropertyHandler.getInstance().getProperty("tipsystem.id");
         LOG.debug("Single Message [" + ajc$this.getJaxContextCentralizer().toXml(SingleMessage.class, sm) + "].");
         TIPSystemIntegrationModuleImpl.RegisterEventResponse registerEventResponse = ajc$this.registerEvent(ajc$this.getJaxContextCentralizer().toXml(SingleMessage.class, sm).getBytes("UTF-8"), info, tip_id, false, (ThreeStateBoolean)null, procedure, startTimeStamp);
         events = registerEventResponse.getSingleMessageInfo().getEvents();
      } catch (GFDDPPException var31) {
         difftime = (double)(System.nanoTime() - startTimeStamp) / 1.0E9D;
         LOG.info("TIPSystemIntegrationModuleImpl=-----= einde " + procedure + " =$=" + info + " => " + " (dt:" + difftime + ") " + "error GFDDPPException: " + var31.getMessage());
         throw new IntegrationModuleException(StatusResolver.resolveMessage(StatusResolver.getLocalResourceBundle(), var31.getStatusCode(), var31.getPlaceHolders()), var31);
      } catch (Throwable var32) {
         difftime = (double)(System.nanoTime() - startTimeStamp) / 1.0E9D;
         LOG.info("TIPSystemIntegrationModuleImpl=-----= einde " + procedure + " =$=" + info + " => " + " (dt:" + difftime + ") " + "error Throwable: " + var32.getMessage());
         Exceptionutils.errorHandler(var32);
      }

      return events;
   }

   // $FF: synthetic method
   static final String archivePrescriptionComment_aroundBody2(TIPSystemIntegrationModuleImpl ajc$this, String rid, String comment, JoinPoint var3) {
      String procedure = "archivePrescriptionComment";
      String info = " ";
      LOG.info("TIPSystemIntegrationModuleImpl=-----= start " + procedure + " =$=" + info);
      long startTimeStamp = System.nanoTime();
      String events = "";

      double difftime;
      try {
         ObjectFactory sof = new ObjectFactory();
         ArchivePrescriptionCommentEventType apet = new ArchivePrescriptionCommentEventType();
         apet.setRid(rid);
         apet.setPrescriptionComment(comment.getBytes("UTF-8"));
         apet.setPrescriptionCommentVersion("V2");
         apet.setPrescriptionCommentTime(ajc$this.getCurrentXMLGregorianCalendar());

         try {
            apet.setExecutorId(ajc$this.getId(StandaloneRequestorProvider.getRequestorIdInformation(), "PHARMACY", "NIHII"));
         } catch (IntegrationModuleException var27) {
            LOG.info("TIPSystemIntegrationModuleImpl=-----= ----- " + procedure + " =$=" + info + "no valid session: offline operation" + var27.getMessage());
            apet.setExecutorId(ajc$this.getId(ajc$this.getPropertyHandler().getProperty("pharmacy.nihii"), "PHARMACY", "NIHII"));
         }

         apet.setDguid((String)null);
         apet.setSguid((String)null);
         SingleMessage sm = new SingleMessage();
         SmoaMessageType um = new SmoaMessageType();
         EventFolderType eft = sof.createEventFolderType();
         EventFolderType.Events eventFolderTypeEvents = new EventFolderType.Events();
         eventFolderTypeEvents.getEvent().add(apet);
         eft.setEvents(eventFolderTypeEvents);
         um.setAbstractFolder(sof.createEventFolder(eft));
         sm.setUnsigned(um);
         String tip_id = PropertyHandler.getInstance().getProperty("tipsystem.id");
         LOG.debug("Single Message [" + ajc$this.getJaxContextCentralizer().toXml(SingleMessage.class, sm) + "].");
         TIPSystemIntegrationModuleImpl.RegisterEventResponse response = ajc$this.registerEvent(ajc$this.getJaxContextCentralizer().toXml(SingleMessage.class, sm).getBytes("UTF-8"), info, tip_id, false, (ThreeStateBoolean)null, procedure, startTimeStamp);
         events = response.getSingleMessageInfo().getEvents();
      } catch (GFDDPPException var28) {
         difftime = (double)(System.nanoTime() - startTimeStamp) / 1.0E9D;
         LOG.info("TIPSystemIntegrationModuleImpl=-----= einde " + procedure + " =$=" + info + " => " + " (dt:" + difftime + ") " + "error GFDDPPException: " + var28.getMessage());
         throw new IntegrationModuleException(StatusResolver.resolveMessage(StatusResolver.getLocalResourceBundle(), var28.getStatusCode(), var28.getPlaceHolders()), var28);
      } catch (Throwable var29) {
         difftime = (double)(System.nanoTime() - startTimeStamp) / 1.0E9D;
         LOG.info("TIPSystemIntegrationModuleImpl=-----= einde " + procedure + " =$=" + info + " => " + " (dt:" + difftime + ") " + "error Throwable: " + var29.getMessage());
         Exceptionutils.errorHandler(var29);
      }

      return events;
   }

   // $FF: synthetic method
   static final String registerData_aroundBody4(TIPSystemIntegrationModuleImpl ajc$this, byte[] singleMessage, String tipId, boolean requestPatientSignature, ThreeStateBoolean patientConsent, JoinPoint var5) {
      String procedure = "registerData";
      String info = " ";
      LOG.info("TIPSystemIntegrationModuleImpl=-----= start " + procedure + " =$=" + info);
      long startTimeStamp = System.nanoTime();
      String events = "";

      double difftime;
      try {
         TIPSystemIntegrationModuleImpl.RegisterEventResponse response = ajc$this.registerEvent(singleMessage, info, tipId, requestPatientSignature, patientConsent, procedure, startTimeStamp);
         events = response.getSingleMessageInfo().getEvents();
      } catch (GFDDPPException var18) {
         difftime = (double)(System.nanoTime() - startTimeStamp) / 1.0E9D;
         LOG.info("TIPSystemIntegrationModuleImpl=-----= einde " + procedure + " =$=" + info + " => " + " (dt:" + difftime + ") " + "error GFDDPPException: " + var18.getMessage());
         throw new IntegrationModuleException(StatusResolver.resolveMessage(StatusResolver.getLocalResourceBundle(), var18.getStatusCode(), var18.getPlaceHolders()), var18);
      } catch (Throwable var19) {
         difftime = (double)(System.nanoTime() - startTimeStamp) / 1.0E9D;
         LOG.info("TIPSystemIntegrationModuleImpl=-----= einde " + procedure + " =$=" + info + " => " + " (dt:" + difftime + ") " + "error Throwable: " + var19.getMessage());
         Exceptionutils.errorHandler(var19);
      }

      return events;
   }

   // $FF: synthetic method
   static final StoreDataEntriesResponse registerMedicationSchemeEntries_aroundBody6(TIPSystemIntegrationModuleImpl ajc$this, StoreDataEntriesRequest storeRequest, JoinPoint var2) {
      String procedure = "registerMedicationSchemeEntries";
      String info = " ";
      LOG.info("TIPSystemIntegrationModuleImpl=-----= start " + procedure + " =$=" + info);
      long startTimeStamp = System.nanoTime();
      LOG.debug("******************** REGISTER_MEDICATIONSCHEME_ENTRIES - Validate incoming fields **********************");
      List errors = GFDDPPValidationUtils.validate(storeRequest);
      if (CollectionUtils.isNotEmpty(errors)) {
         StoreDataEntriesResponse response = new StoreDataEntriesResponse();
         Status status = new Status(MSStatusCode.MEDICATION_SCHEME_VALIDATION_ERRORS.getCode(), MSStatusResolver.getStatusMessage(MSStatusCode.MEDICATION_SCHEME_VALIDATION_ERRORS));
         status.getErrors().addAll(errors);
         response.setStatus(status);
         response.setServerMessageID(UUID.randomUUID().toString());
         if (storeRequest != null) {
            response.setClientMessageID(storeRequest.getClientMessageID());
            response.setSubjectID(storeRequest.getSubjectID());
         }

         return response;
      } else {
         double difftime;
         try {
            LOG.debug("******************** REGISTER_MEDICATIONSCHEME_ENTRIES -  Create single message  **********************");
            MedicationSchemeEntriesRequest medicationSchemeEntriesRequest = new MedicationSchemeEntriesRequest();
            medicationSchemeEntriesRequest.setClientMessageId(storeRequest.getClientMessageID());
            medicationSchemeEntriesRequest.setSubjectId(storeRequest.getSubjectID());
            medicationSchemeEntriesRequest.setVersion(((DataEntry)storeRequest.getDataEntries().get(0)).getNodeVersion());
            medicationSchemeEntriesRequest.setCurrentDateTime(Utils.transformToXMLGregorianCalendar(new Date()));
            Iterator var15 = storeRequest.getDataEntries().iterator();

            while(var15.hasNext()) {
               DataEntry dataEntry = (DataEntry)var15.next();
               DataEntryURI dataEntryURI = new DataEntryURI(dataEntry.getDataEntryURI());
               DataEntryRequest smcDataEntry = new DataEntryRequest();
               if (dataEntryURI.isCreateURI()) {
                  smcDataEntry.setDataEntryId(UUID.randomUUID().toString());
               } else {
                  smcDataEntry.setDataEntryId(dataEntryURI.getDataEntryId());
                  smcDataEntry.setDataEntryVersion(dataEntryURI.getDataEntryVersion());
               }

               smcDataEntry.setAvailabilityStatus(AvailabilityStatus.valueOf(((String)dataEntry.getMetadata().get("availabilityStatus")).toUpperCase()));
               smcDataEntry.setFormatCode(FormatCode.valueOf(((String)dataEntry.getMetadata().get("formatCode")).toUpperCase()));
               List keysToIgnore = Arrays.asList("availabilityStatus", "formatCode");
               MetaDataListType smcMetaDataList = new MetaDataListType();
               Iterator var25 = dataEntry.getMetadata().entrySet().iterator();

               while(var25.hasNext()) {
                  Entry metaData = (Entry)var25.next();
                  if (!keysToIgnore.contains(metaData.getKey())) {
                     MetaDataType smcMetaData = new MetaDataType();
                     smcMetaData.setKey((String)metaData.getKey());
                     smcMetaData.setValue((String)metaData.getValue());
                     smcMetaDataList.getMetaData().add(smcMetaData);
                  }
               }

               if (CollectionUtils.isNotEmpty(smcMetaDataList.getMetaData())) {
                  smcDataEntry.setMetaDataList(smcMetaDataList);
               }

               smcDataEntry.setBusinessData(dataEntry.getBusinessData());
               smcDataEntry.setReference(dataEntry.getReference());
               medicationSchemeEntriesRequest.getDataEntry().add(smcDataEntry);
            }

            SingleMessage singleMessage = (new SingleMessageBuilder()).createSingleMessageUnsigned();
            singleMessage.getUnsigned().setHeader((HeaderType)null);
            SingleMessageWrapper wrapper = new SingleMessageWrapper(singleMessage);
            wrapper.getEvents().add(medicationSchemeEntriesRequest);
            String tip_id = PropertyHandler.getInstance().getProperty("tipsystem.id");
            String singleMessageString = ajc$this.getJaxContextCentralizer().toXml(SingleMessage.class, singleMessage);
            LOG.debug("Single Message [" + singleMessageString + "].");
            TIPSystemIntegrationModuleImpl.RegisterEventResponse registerEventResponse = ajc$this.registerEvent(singleMessageString.getBytes("UTF-8"), info, tip_id, false, (ThreeStateBoolean)null, procedure, startTimeStamp);
            SimpleResponseType tipResponse = registerEventResponse.getTipResponse();
            String tipStatusCode = tipResponse.getStatus().getCode();
            SingleMessageInfo finalSingleMessageInfo = registerEventResponse.getSingleMessageInfo();
            SingleMessageWrapper finalSingleMessageWrapper = finalSingleMessageInfo.getWrapper();
            List finalMedicationSchemeEntryRequests = finalSingleMessageWrapper.getAllMedicationSchemeEntryRequests();
            MedicationSchemeEntriesRequest finalMedicationSchemeEntriesRequest = (MedicationSchemeEntriesRequest)finalMedicationSchemeEntryRequests.get(0);
            StoreDataEntriesResponse storeResponse = new StoreDataEntriesResponse();
            storeResponse.setClientMessageID(storeRequest.getClientMessageID());
            storeResponse.setServerMessageID(finalSingleMessageInfo.getMessageID());
            storeResponse.setSubjectID(finalMedicationSchemeEntriesRequest.getSubjectId());
            storeResponse.setStatus(new Status());
            if (!SUCCESS.equals(tipStatusCode)) {
               storeResponse.getStatus().setCode(MSStatusCode.MEDICATION_SCHEME_VALIDATION_ERRORS.getCode());
               storeResponse.getStatus().setMessage(MSStatusResolver.getStatusMessage(MSStatusCode.MEDICATION_SCHEME_VALIDATION_ERRORS));
               Iterator var74 = tipResponse.getStatus().getMessage().iterator();

               while(true) {
                  LocalisedString message;
                  byte[] messageBytes;
                  do {
                     do {
                        if (!var74.hasNext()) {
                           return storeResponse;
                        }

                        message = (LocalisedString)var74.next();
                        messageBytes = message.getValue().getBytes("UTF-8");
                     } while(!LangageType.NA.equals(message.getLang()));
                  } while(!Base64.isBase64(messageBytes));

                  byte[] decodedData = Base64.decodeBase64(messageBytes);
                  Errors detailedErrorList = (Errors)ajc$this.getJaxContextCentralizer().toObject(Errors.class, decodedData);
                  Iterator var59 = detailedErrorList.getError().iterator();

                  while(var59.hasNext()) {
                     Error tipSysError = (Error)var59.next();
                     MSStatusCode tipErrorCode = MappingUtils.mapStatusCode(tipSysError.getCode());
                     be.business.connector.gfddpp.domain.medicationscheme.Error newError = new be.business.connector.gfddpp.domain.medicationscheme.Error();
                     newError.setReference(tipSysError.getReference());
                     newError.setReferenceType(tipSysError.getReferenceType());
                     newError.setCode(tipErrorCode.getCode());
                     newError.setMessage(MSStatusResolver.getStatusMessage(tipErrorCode));
                     storeResponse.getStatus().getErrors().add(newError);
                  }
               }
            }

            storeResponse.getStatus().setCode(MSStatusCode.MEDICATION_SCHEME_SUCCESS.getCode());
            storeResponse.getStatus().setMessage(MSStatusResolver.getStatusMessage(MSStatusCode.MEDICATION_SCHEME_SUCCESS));
            Integer oldMedicationSchemeVersion = finalMedicationSchemeEntriesRequest.getVersion();
            storeResponse.setVersion(oldMedicationSchemeVersion == null ? 1 : oldMedicationSchemeVersion + 1);
            storeResponse.setLastUpdated(finalMedicationSchemeEntriesRequest.getCurrentDateTime().toGregorianCalendar());
            Iterator var41 = finalMedicationSchemeEntriesRequest.getDataEntry().iterator();
            if (var41.hasNext()) {
               DataEntryRequest dataEntryRequest = (DataEntryRequest)var41.next();
               DataEntry dataEntry = new DataEntry();
               dataEntry.setReference(dataEntryRequest.getReference());
               if (dataEntryRequest.getMetaDataList() != null) {
                  Iterator var49 = dataEntryRequest.getMetaDataList().getMetaData().iterator();

                  while(var49.hasNext()) {
                     MetaDataType metaDataType = (MetaDataType)var49.next();
                     dataEntry.getMetadata().put(metaDataType.getKey(), metaDataType.getValue());
                  }
               }

               dataEntry.getMetadata().put("sguid", finalMedicationSchemeEntriesRequest.getId());
               dataEntry.getMetadata().put("dguid", dataEntryRequest.getDguid());
               dataEntry.getMetadata().put("tguid", dataEntryRequest.getDataEntryId());
               dataEntry.getMetadata().put("formatCode", dataEntryRequest.getFormatCode().name());
               dataEntry.getMetadata().put("availabilityStatus", dataEntryRequest.getAvailabilityStatus().name());
               dataEntry.getMetadata().put("authorOrganizationId", finalSingleMessageInfo.getWrapper().getHeaderPharmacyId());
               dataEntry.getMetadata().put("authorOrganizationIdSource", OrganizationIdType.NIHII.name());
               dataEntry.getMetadata().put("authorRole", OrganizationRoleType.ORG_PHARMACY.name());
               dataEntry.setBusinessData(dataEntryRequest.getBusinessData());
               Integer oldDataEntryVersion = dataEntryRequest.getDataEntryVersion();
               int newDataEntryVersion = oldDataEntryVersion == null ? 1 : oldDataEntryVersion + 1;
               DataEntryURI uri = DataEntryURI.readURI(finalMedicationSchemeEntriesRequest.getSubjectId(), dataEntryRequest.getDataEntryId(), newDataEntryVersion);
               dataEntry.setDataEntryURI(uri.toString());
               dataEntry.setNodeVersion(storeResponse.getVersion());
               storeResponse.getDataEntries().add(dataEntry);
               return storeResponse;
            }
         } catch (GFDDPPException var54) {
            difftime = (double)(System.nanoTime() - startTimeStamp) / 1.0E9D;
            LOG.info("TIPSystemIntegrationModuleImpl=-----= einde " + procedure + " =$=" + info + " => " + " (dt:" + difftime + ") " + "error GFDDPPException: " + var54.getMessage());
            throw new IntegrationModuleException(StatusResolver.resolveMessage(StatusResolver.getLocalResourceBundle(), var54.getStatusCode(), var54.getPlaceHolders()), var54);
         } catch (Throwable var55) {
            difftime = (double)(System.nanoTime() - startTimeStamp) / 1.0E9D;
            LOG.info("TIPSystemIntegrationModuleImpl=-----= einde " + procedure + " =$=" + info + " => " + " (dt:" + difftime + ") " + "error Throwable: " + var55.getMessage());
            Exceptionutils.errorHandler(var55);
         }

         return null;
      }
   }

   // $FF: synthetic method
   static final void revokeData_aroundBody8(TIPSystemIntegrationModuleImpl ajc$this, byte[] singleMessage, String tipId, boolean requestPatientSignature, ThreeStateBoolean patientConsent, JoinPoint var5) {
      String procedure = "revokeData";
      String info = " ";
      LOG.info("TIPSystemIntegrationModuleImpl=-----= start " + procedure + " =$=" + info);
      long startTimeStamp = System.nanoTime();

      try {
         if (ajc$this.getPropertyHandler().hasProperty("TIP_UTF_8_ONLY_INPUT") && ajc$this.getPropertyHandler().getProperty("TIP_UTF_8_ONLY_INPUT").equals("true")) {
            ValidationUtils.validateUTF_8Input(singleMessage);
         }

         SingleMessageInfo smi = new SingleMessageInfo(singleMessage);
         info = info + smi.getInfo();
         tipId = ajc$this.getTipId(info, tipId);
         info = info + " tipId:" + tipId + " ";
         smi.validateRevokeData();
         String NihiiPharmacyId = ajc$this.getNihiiPharmacyId(info, smi.getHasSyncEventType());
         info = info + " NihiiPharmacyId:" + NihiiPharmacyId + " ";
         smi.validateNihiiPharmacyId(NihiiPharmacyId);
         smi.setHeaderNihiiPharmacyId(NihiiPharmacyId);
         smi.validateSingleMessageXsd();
         SealedMessageRequestType sealedMessageRequestType = smi.setCommonData(ajc$this.getEncryptionUtils(), tipId, requestPatientSignature, patientConsent);
         SealedMessageRequestTypeXml sealedMessageRequestTypeXml = new SealedMessageRequestTypeXml();
         sealedMessageRequestTypeXml.setSealedMessageRequestType(sealedMessageRequestType);
         LOG.debug("TIPSystemIntegrationModuleImpl=-----= ----- " + procedure + " =$=" + info + "=> Encrypt message for queue **********************");
         String s = ajc$this.getJaxContextCentralizer().toXml(SealedMessageRequestTypeXml.class, sealedMessageRequestTypeXml);
         String ids = smi.getEvents();
         ajc$this.xmlValidationhelper.assertValidSingleMessage(sealedMessageRequestType.getSingleMessageSealed());
         LOG.debug("TIPSystemIntegrationModuleImpl=-----= ----- " + procedure + " =$=" + info + "=> Add revoke message on queue **********************");
         getFacade().deleteData(s.getBytes("UTF-8"), ids, (String)null, smi.getHeaderPharmacyId());
      } catch (Throwable var23) {
         double difftime = (double)(System.nanoTime() - startTimeStamp) / 1.0E9D;
         LOG.info("TIPSystemIntegrationModuleImpl=-----= einde " + procedure + " =$=" + info + " => " + " (dt:" + difftime + ") " + "error Throwable: " + var23.getMessage());
         Exceptionutils.errorHandler(var23);
      }

   }

   // $FF: synthetic method
   static final String updateData_aroundBody10(TIPSystemIntegrationModuleImpl ajc$this, byte[] singleMessage, String tipId, boolean requestPatientSignature, ThreeStateBoolean patientConsent, JoinPoint var5) {
      String procedure = "updateData";
      String info = " ";
      LOG.info("TIPSystemIntegrationModuleImpl=-----= start " + procedure + " =$=" + info);
      long startTimeStamp = System.nanoTime();
      String ids = null;

      try {
         LOG.debug("===================== Validate single message UTF-8 bytes ==============================");
         if (ajc$this.getPropertyHandler().hasProperty("TIP_UTF_8_ONLY_INPUT") && ajc$this.getPropertyHandler().getProperty("TIP_UTF_8_ONLY_INPUT").equals("true")) {
            ValidationUtils.validateUTF_8Input(singleMessage);
         }

         SingleMessageInfo smi = new SingleMessageInfo(singleMessage);
         info = info + smi.getInfo();
         tipId = ajc$this.getTipId(info, tipId);
         info = info + " tipId:" + tipId + " ";
         smi.validateUpdateData();
         String NihiiPharmacyId = ajc$this.getNihiiPharmacyId(info, smi.getHasSyncEventType());
         info = info + " NihiiPharmacyId:" + NihiiPharmacyId + " ";
         smi.validateNihiiPharmacyId(NihiiPharmacyId);
         smi.setHeaderNihiiPharmacyId(NihiiPharmacyId);
         ProductFilterSingleton.getInstance(ajc$this.getPropertyHandler());
         smi.setFilterProducts(ajc$this.getProductFilterUtils(), ajc$this.getPropertyHandler());
         smi.validateSingleMessageXsd();
         SealedMessageRequestType sealedMessageRequestType = smi.setCommonData(ajc$this.getEncryptionUtils(), tipId, requestPatientSignature, patientConsent);
         SealedMessageRequestTypeXml sealedMessageRequestTypeXml = new SealedMessageRequestTypeXml();
         sealedMessageRequestTypeXml.setSealedMessageRequestType(sealedMessageRequestType);
         LOG.debug("TIPSystemIntegrationModuleImpl=-----= ----- " + procedure + " =$=" + info + "=> Encrypt message for queue **********************");
         String s = ajc$this.getJaxContextCentralizer().toXml(SealedMessageRequestTypeXml.class, sealedMessageRequestTypeXml);
         byte[] sealedSMCForQueue = s.getBytes();
         ids = smi.getEvents();
         ajc$this.xmlValidationhelper.assertValidSingleMessage(sealedMessageRequestType.getSingleMessageSealed());
         LOG.debug("TIPSystemIntegrationModuleImpl=-----= ----- " + procedure + " =$=" + info + "=> Add update message on queue **********************");
         getFacade().updateData(sealedSMCForQueue, ids, smi.getMessageID(), smi.getHeaderPharmacyId());
      } catch (Throwable var25) {
         double difftime = (double)(System.nanoTime() - startTimeStamp) / 1.0E9D;
         LOG.info("TIPSystemIntegrationModuleImpl=-----= einde " + procedure + " =$=" + info + " => " + " (dt:" + difftime + ") " + "error Throwable: " + var25.getMessage());
         Exceptionutils.errorHandler(var25);
      }

      return ids;
   }

   // $FF: synthetic method
   static final String getStatusMessages_aroundBody12(TIPSystemIntegrationModuleImpl ajc$this, String sGuid, String dGuid, JoinPoint var3) {
      ApplicationConfig.getInstance().assertValidSession();
      LOG.debug("******************** Create getStatusMessage Request **********************");
      String xml = null;
      String response = null;

      try {
         String tipId = null;
         if (ajc$this.getPropertyHandler().hasProperty("default.tip.id")) {
            tipId = ajc$this.getPropertyHandler().getProperty("default.tip.id");
         }

         LOG.debug("******************** Validate incoming parameters **********************");
         ValidationUtils.validateIncomingFieldsGetStatusMessage(sGuid, dGuid);
         ValidationUtils.validateExistingTipId(tipId, ajc$this.getPropertyHandler());
         LOG.debug("******************** Get requestor information out of SAML **********************");
         String requestorId = StandaloneRequestorProvider.getRequestorIdInformation();
         String requestorType = StandaloneRequestorProvider.getRequestorTypeInformation();
         LOG.debug("******************** Create request getStatus **********************");
         xml = RequestCreatorUtils.createRequestGetStatus(requestorId, requestorType, sGuid, dGuid);
         byte[] RequestParametersSealed;
         if (StringUtils.isNoneBlank(new CharSequence[]{sGuid})) {
            RequestParametersSealed = SealedProcessor.createSealedSync((EncryptionToken)ajc$this.getEtkHelper().getTIPSystem_ETK(tipId).get(0), xml, "RequestGetStatus");
         } else {
            RequestParametersSealed = SealedProcessor.createSealedAsync((EncryptionToken)ajc$this.getEtkHelper().getTIPSystem_ETK(tipId).get(0), xml, "RequestGetStatus");
         }

         RoutedSealedRequestType routedSealedRequest = new RoutedSealedRequestType();
         routedSealedRequest.setRequestParametersSealed(RequestParametersSealed);
         LOG.debug("******************** Retrieve Status **********************");
         RoutedSealedResponseType routedSealedResponse = null;

         try {
            routedSealedResponse = TipSystemServiceImpl.getInstance().retrieveStatusMessages(routedSealedRequest);
         } catch (SOAPFaultException var21) {
            throw new IntegrationModuleException(var21);
         }

         LOG.debug("******************** Building GetStatus Response **********************");
         byte[] singleMessageResponse = null;
         if (routedSealedResponse.getSingleMessageSealed() != null && routedSealedResponse.getSingleMessageSealed().length > 1) {
            singleMessageResponse = Crypto.unseal(routedSealedResponse.getSingleMessageSealed());
            ajc$this.xmlValidationhelper.assertValidSingleMessage(singleMessageResponse);
         }

         singleMessageResponse = ajc$this.consolidationStatusResponse(sGuid, dGuid, singleMessageResponse);
         response = new String(singleMessageResponse, "UTF-8");
      } catch (IntegrationModuleException var22) {
         LOG.error("Integration Module Exception " + var22.getMessage());
         throw var22;
      } catch (Throwable var23) {
         Exceptionutils.errorHandler(var23);
      }

      LOG.debug("******************** Return getStatus response **********************");
      LOG.debug(response);
      return response;
   }

   // $FF: synthetic method
   private static void ajc$preClinit() {
      Factory var0 = new Factory("TIPSystemIntegrationModuleImpl.java", TIPSystemIntegrationModuleImpl.class);
      ajc$tjp_0 = var0.makeSJP("method-execution", var0.makeMethodSig("1", "archivePrescription", "be.business.connector.gfddpp.tipsystem.TIPSystemIntegrationModuleImpl", "java.lang.String", "rid", "be.business.connector.core.exceptions.IntegrationModuleException", "java.lang.String"), 219);
      ajc$tjp_1 = var0.makeSJP("method-execution", var0.makeMethodSig("1", "archivePrescriptionComment", "be.business.connector.gfddpp.tipsystem.TIPSystemIntegrationModuleImpl", "java.lang.String:java.lang.String", "rid:comment", "be.business.connector.core.exceptions.IntegrationModuleException", "java.lang.String"), 277);
      ajc$tjp_2 = var0.makeSJP("method-execution", var0.makeMethodSig("1", "registerData", "be.business.connector.gfddpp.tipsystem.TIPSystemIntegrationModuleImpl", "[B:java.lang.String:boolean:be.business.connector.gfddpp.domain.ThreeStateBoolean", "singleMessage:tipId:requestPatientSignature:patientConsent", "be.business.connector.core.exceptions.IntegrationModuleException", "java.lang.String"), 328);
      ajc$tjp_3 = var0.makeSJP("method-execution", var0.makeMethodSig("1", "registerMedicationSchemeEntries", "be.business.connector.gfddpp.tipsystem.TIPSystemIntegrationModuleImpl", "be.business.connector.gfddpp.domain.medicationscheme.protocol.StoreDataEntriesRequest", "storeRequest", "be.business.connector.core.exceptions.IntegrationModuleException", "be.business.connector.gfddpp.domain.medicationscheme.protocol.StoreDataEntriesResponse"), 495);
      ajc$tjp_4 = var0.makeSJP("method-execution", var0.makeMethodSig("1", "revokeData", "be.business.connector.gfddpp.tipsystem.TIPSystemIntegrationModuleImpl", "[B:java.lang.String:boolean:be.business.connector.gfddpp.domain.ThreeStateBoolean", "singleMessage:tipId:requestPatientSignature:patientConsent", "be.business.connector.core.exceptions.IntegrationModuleException", "void"), 648);
      ajc$tjp_5 = var0.makeSJP("method-execution", var0.makeMethodSig("1", "updateData", "be.business.connector.gfddpp.tipsystem.TIPSystemIntegrationModuleImpl", "[B:java.lang.String:boolean:be.business.connector.gfddpp.domain.ThreeStateBoolean", "singleMessage:tipId:requestPatientSignature:patientConsent", "be.business.connector.core.exceptions.IntegrationModuleException", "java.lang.String"), 709);
      ajc$tjp_6 = var0.makeSJP("method-execution", var0.makeMethodSig("1", "getStatusMessages", "be.business.connector.gfddpp.tipsystem.TIPSystemIntegrationModuleImpl", "java.lang.String:java.lang.String", "sGuid:dGuid", "be.business.connector.core.exceptions.IntegrationModuleException", "java.lang.String"), 779);
   }

   protected class RegisterEventResponse {
      private SingleMessageInfo singleMessageInfo;
      private SimpleResponseType tipResponse;

      public RegisterEventResponse() {
      }

      public SingleMessageInfo getSingleMessageInfo() {
         return this.singleMessageInfo;
      }

      public void setSingleMessageInfo(SingleMessageInfo singleMessageInfo) {
         this.singleMessageInfo = singleMessageInfo;
      }

      public SimpleResponseType getTipResponse() {
         return this.tipResponse;
      }

      public void setTipResponse(SimpleResponseType tipResponse) {
         this.tipResponse = tipResponse;
      }
   }
}
