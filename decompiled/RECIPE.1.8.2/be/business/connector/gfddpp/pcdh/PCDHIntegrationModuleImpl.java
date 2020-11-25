package be.business.connector.gfddpp.pcdh;

import be.apb.gfddpp.common.log.LogsSMC;
import be.apb.gfddpp.common.medicationscheme.status.MSStatusCode;
import be.apb.gfddpp.common.medicationscheme.status.MSStatusResolver;
import be.apb.gfddpp.common.status.StatusCode;
import be.apb.gfddpp.common.utils.SingleMessageWrapper;
import be.apb.gfddpp.domain.PharmacyIdType;
import be.apb.gfddpp.helper.SingleMessageValidationHelper;
import be.apb.standards.gfddpp.request.PatientType;
import be.apb.standards.smoa.schema.model.v1.HistoryProductType;
import be.apb.standards.smoa.schema.model.v1.MedicationHistoryType;
import be.apb.standards.smoa.schema.model.v1.ReferencePharmacyType;
import be.apb.standards.smoa.schema.v1.DataEntryResponse;
import be.apb.standards.smoa.schema.v1.MedicationSchemeEntriesResponse;
import be.apb.standards.smoa.schema.v1.MedicationSchemeTimestampsResponse;
import be.apb.standards.smoa.schema.v1.SingleMessage;
import be.business.connector.common.ApplicationConfig;
import be.business.connector.common.StandaloneRequestorProvider;
import be.business.connector.common.module.AbstractIntegrationModule;
import be.business.connector.core.exceptions.IntegrationModuleException;
import be.business.connector.core.technical.connector.utils.Crypto;
import be.business.connector.core.utils.Exceptionutils;
import be.business.connector.core.utils.I18nHelper;
import be.business.connector.core.utils.PropertyHandler;
import be.business.connector.core.utils.SealedProcessor;
import be.business.connector.gfddpp.domain.ThreeStateBoolean;
import be.business.connector.gfddpp.domain.medicationscheme.Status;
import be.business.connector.gfddpp.domain.medicationscheme.protocol.FetchDataEntriesRequest;
import be.business.connector.gfddpp.domain.medicationscheme.protocol.FetchDataEntriesResponse;
import be.business.connector.gfddpp.domain.medicationscheme.protocol.RetrieveTimestampsRequest;
import be.business.connector.gfddpp.domain.medicationscheme.protocol.RetrieveTimestampsResponse;
import be.business.connector.gfddpp.tipsystem.threading.KGSSThread;
import be.business.connector.gfddpp.tipsystem.threading.ProductDecryptorThread;
import be.business.connector.gfddpp.utils.ConsolidateUtils;
import be.business.connector.gfddpp.utils.GFDDPPValidationUtils;
import be.business.connector.gfddpp.utils.MappingUtils;
import be.business.connector.gfddpp.utils.RequestCreatorUtils;
import be.business.connector.projects.common.services.pcdh.PcdhServiceImpl;
import be.business.connector.projects.common.utils.ValidationUtils;
import be.ehealth.apb.gfddpp.services.pcdh.LocalisedString;
import be.ehealth.apb.gfddpp.services.pcdh.SealedRequestType;
import be.ehealth.apb.gfddpp.services.pcdh.SealedResponseType;
import be.ehealth.apb.gfddpp.services.pcdh.StatusType;
import be.fgov.ehealth.etee.crypto.encrypt.EncryptionToken;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.Semaphore;
import net.sf.ehcache.Element;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.JoinPoint.StaticPart;
import org.aspectj.runtime.internal.Conversions;
import org.aspectj.runtime.reflect.Factory;
import org.perf4j.aop.Profiled;
import org.perf4j.log4j.aop.TimingAspect;

public class PCDHIntegrationModuleImpl extends AbstractIntegrationModule implements PCDHIntegrationModule {
   private static final Logger LOG;
   private SingleMessageValidationHelper xmlValidationhelper = new SingleMessageValidationHelper();
   private final int MAX_THREAD = 30;
   private int threadLimit = 10;
   private final String THREAD_PROPERTY = "thread.number.limit";
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

   static {
      ajc$preClinit();
      LOG = Logger.getLogger(PCDHIntegrationModuleImpl.class);
   }

   public PCDHIntegrationModuleImpl() throws IntegrationModuleException {
      this.initThread();
      this.getJaxContextCentralizer().addContext(HistoryProductType.class);
      LOG.debug("******************** PCDH module init **********************");
   }

   @Profiled(
      logFailuresSeparately = true,
      tag = "0.PCDHIntegrationModuleImpl#getMedicationSchemeTimestamps",
      logger = "org.perf4j.TimingLogger_Executor"
   )
   public RetrieveTimestampsResponse getMedicationSchemeTimestamps(RetrieveTimestampsRequest retrieveTimestampsRequest) throws IntegrationModuleException {
      JoinPoint var18 = Factory.makeJP(ajc$tjp_0, this, this, retrieveTimestampsRequest);
      TimingAspect var10000 = TimingAspect.aspectOf();
      Object[] var19 = new Object[]{this, retrieveTimestampsRequest, var18};
      ProceedingJoinPoint var10001 = (new PCDHIntegrationModuleImpl$AjcClosure1(var19)).linkClosureAndJoinPoint(69648);
      Annotation var10002 = ajc$anno$0;
      if (var10002 == null) {
         var10002 = ajc$anno$0 = PCDHIntegrationModuleImpl.class.getDeclaredMethod("getMedicationSchemeTimestamps", RetrieveTimestampsRequest.class).getAnnotation(Profiled.class);
      }

      return (RetrieveTimestampsResponse)var10000.doPerfLogging(var10001, (Profiled)var10002);
   }

   @Profiled(
      logFailuresSeparately = true,
      tag = "0.PCDHIntegrationModuleImpl#getMedicationSchemeEntries",
      logger = "org.perf4j.TimingLogger_Executor"
   )
   public FetchDataEntriesResponse getMedicationSchemeEntries(FetchDataEntriesRequest getMedicationEntriesRequest) throws IntegrationModuleException {
      JoinPoint var23 = Factory.makeJP(ajc$tjp_1, this, this, getMedicationEntriesRequest);
      TimingAspect var10000 = TimingAspect.aspectOf();
      Object[] var24 = new Object[]{this, getMedicationEntriesRequest, var23};
      ProceedingJoinPoint var10001 = (new PCDHIntegrationModuleImpl$AjcClosure3(var24)).linkClosureAndJoinPoint(69648);
      Annotation var10002 = ajc$anno$1;
      if (var10002 == null) {
         var10002 = ajc$anno$1 = PCDHIntegrationModuleImpl.class.getDeclaredMethod("getMedicationSchemeEntries", FetchDataEntriesRequest.class).getAnnotation(Profiled.class);
      }

      return (FetchDataEntriesResponse)var10000.doPerfLogging(var10001, (Profiled)var10002);
   }

   @Profiled(
      logFailuresSeparately = true,
      tag = "0.PCDHIntegrationModuleImpl#getPharmacyDetails",
      logger = "org.perf4j.TimingLogger_Executor"
   )
   public String getPharmacyDetails(String patientIdType, String patientId, String dGuid, String motivationType, String motivationText, boolean requestPatientSignature, ThreeStateBoolean therapeuticRelationShip) throws IntegrationModuleException {
      StaticPart var10000 = ajc$tjp_2;
      Object[] var30 = new Object[]{patientIdType, patientId, dGuid, motivationType, motivationText, Conversions.booleanObject(requestPatientSignature), therapeuticRelationShip};
      JoinPoint var29 = Factory.makeJP(var10000, this, this, var30);
      TimingAspect var32 = TimingAspect.aspectOf();
      Object[] var31 = new Object[]{this, patientIdType, patientId, dGuid, motivationType, motivationText, Conversions.booleanObject(requestPatientSignature), therapeuticRelationShip, var29};
      ProceedingJoinPoint var10001 = (new PCDHIntegrationModuleImpl$AjcClosure5(var31)).linkClosureAndJoinPoint(69648);
      Annotation var10002 = ajc$anno$2;
      if (var10002 == null) {
         var10002 = ajc$anno$2 = PCDHIntegrationModuleImpl.class.getDeclaredMethod("getPharmacyDetails", String.class, String.class, String.class, String.class, String.class, Boolean.TYPE, ThreeStateBoolean.class).getAnnotation(Profiled.class);
      }

      return (String)var32.doPerfLogging(var10001, (Profiled)var10002);
   }

   @Profiled(
      logFailuresSeparately = true,
      tag = "0.PCDHIntegrationModuleImpl#getData",
      logger = "org.perf4j.TimingLogger_Executor"
   )
   public String getData(String patientIdType, String patientId, String dataType, String dateRange, boolean ExcludeOwnData, byte[] localData, boolean requestPatientSignature, ThreeStateBoolean therapeuticRelationShip) throws IntegrationModuleException {
      StaticPart var10000 = ajc$tjp_3;
      Object[] var33 = new Object[]{patientIdType, patientId, dataType, dateRange, Conversions.booleanObject(ExcludeOwnData), localData, Conversions.booleanObject(requestPatientSignature), therapeuticRelationShip};
      JoinPoint var32 = Factory.makeJP(var10000, this, this, var33);
      TimingAspect var35 = TimingAspect.aspectOf();
      Object[] var34 = new Object[]{this, patientIdType, patientId, dataType, dateRange, Conversions.booleanObject(ExcludeOwnData), localData, Conversions.booleanObject(requestPatientSignature), therapeuticRelationShip, var32};
      ProceedingJoinPoint var10001 = (new PCDHIntegrationModuleImpl$AjcClosure7(var34)).linkClosureAndJoinPoint(69648);
      Annotation var10002 = ajc$anno$3;
      if (var10002 == null) {
         var10002 = ajc$anno$3 = PCDHIntegrationModuleImpl.class.getDeclaredMethod("getData", String.class, String.class, String.class, String.class, Boolean.TYPE, byte[].class, Boolean.TYPE, ThreeStateBoolean.class).getAnnotation(Profiled.class);
      }

      return (String)var35.doPerfLogging(var10001, (Profiled)var10002);
   }

   public void convertEncryptedContentToProduct(SingleMessageWrapper wrapper) throws IntegrationModuleException {
      Semaphore availableThread = new Semaphore(this.threadLimit, true);
      EncryptionToken systemETK = this.getSystemETKFromCache();
      List<String> uniqueEncryptionKeys = wrapper.getAllUniqueMedicationHistoryEntriesEncryptionIds();
      LOG.info("uniqueEncryptionKeys.size = " + uniqueEncryptionKeys.size());
      this.loadEncryptionKeysToCache(uniqueEncryptionKeys, systemETK);
      LOG.debug("******************* convertEncryptedContentToProduct ***********************");
      if (uniqueEncryptionKeys.size() > 0) {
         Iterator var6 = wrapper.getAllMedicationHistoryEntries().iterator();

         while(var6.hasNext()) {
            MedicationHistoryType medicationHistoryType = (MedicationHistoryType)var6.next();
            ProductDecryptorThread productDecryptorThread = new ProductDecryptorThread(availableThread, medicationHistoryType, systemETK.getEncoded(), this, this.getKgssCache());
            availableThread.acquireUninterruptibly();
            productDecryptorThread.start();
         }

         try {
            availableThread.acquireUninterruptibly(this.threadLimit);
         } catch (IllegalArgumentException var8) {
            LOG.debug("Incorrect Thread configuration : " + var8);
         }
      }

      LOG.debug("******************* convertEncryptedContentToProduct DONE ***********************");
   }

   private EncryptionToken getSystemETKFromCache() throws IntegrationModuleException {
      EncryptionToken systemETK = null;
      if (this.getEtkCache().get("SystemETK") == null) {
         systemETK = (EncryptionToken)this.getEtkHelper().getSystemETK().get(0);
         Element element = new Element("SystemETK", systemETK);
         this.getEtkCache().put(element);
      } else {
         systemETK = (EncryptionToken)this.getEtkCache().get("SystemETK").getObjectValue();
      }

      return systemETK;
   }

   private void loadEncryptionKeysToCache(List<String> encryptionKeyIds, EncryptionToken systemETK) throws IntegrationModuleException {
      LOG.info(String.format("loading [{0}] encryption keys to cache.", encryptionKeyIds.size()));
      int maxNumberOfThreads = Math.min(this.threadLimit, encryptionKeyIds.size());
      Semaphore availableKGSSThread = new Semaphore(maxNumberOfThreads, true);
      boolean errorsOccured = false;
      Iterator var7 = encryptionKeyIds.iterator();

      while(var7.hasNext()) {
         String encryptionId = (String)var7.next();
         if (this.getKgssCache().get(encryptionId) == null) {
            KGSSThread kgssThread = new KGSSThread(availableKGSSThread, this.getKgssCache(), encryptionId, systemETK.getEncoded(), this);
            availableKGSSThread.acquireUninterruptibly();
            kgssThread.start();
            if (kgssThread.isErrorOccured()) {
               errorsOccured = true;
            }
         }
      }

      try {
         availableKGSSThread.acquireUninterruptibly(maxNumberOfThreads);
         if (errorsOccured) {
            throw new IntegrationModuleException(I18nHelper.getLabel("error.validation.kgss.encryption.keys.not.equal"));
         }
      } catch (IllegalArgumentException var9) {
         LOG.debug("Incorrect Thread configuration : " + var9);
      }

   }

   @Profiled(
      logFailuresSeparately = true,
      tag = "0.PCDHIntegrationModuleImpl#getDataTypes",
      logger = "org.perf4j.TimingLogger_Executor"
   )
   public String getDataTypes(String patientIdType, String patientId, boolean requestPatientSignature, ThreeStateBoolean therapeuticRelationShip) throws IntegrationModuleException {
      StaticPart var10000 = ajc$tjp_4;
      Object[] var21 = new Object[]{patientIdType, patientId, Conversions.booleanObject(requestPatientSignature), therapeuticRelationShip};
      JoinPoint var20 = Factory.makeJP(var10000, this, this, var21);
      TimingAspect var23 = TimingAspect.aspectOf();
      Object[] var22 = new Object[]{this, patientIdType, patientId, Conversions.booleanObject(requestPatientSignature), therapeuticRelationShip, var20};
      ProceedingJoinPoint var10001 = (new PCDHIntegrationModuleImpl$AjcClosure9(var22)).linkClosureAndJoinPoint(69648);
      Annotation var10002 = ajc$anno$4;
      if (var10002 == null) {
         var10002 = ajc$anno$4 = PCDHIntegrationModuleImpl.class.getDeclaredMethod("getDataTypes", String.class, String.class, Boolean.TYPE, ThreeStateBoolean.class).getAnnotation(Profiled.class);
      }

      return (String)var23.doPerfLogging(var10001, (Profiled)var10002);
   }

   private void validateResponse(SealedResponseType sealedResponseType) throws IntegrationModuleException {
      if (sealedResponseType != null) {
         StatusType status = sealedResponseType.getStatus();
         if (status == null) {
            throw new IntegrationModuleException(I18nHelper.getLabel("error.technical.status.null"));
         } else {
            String message = null;
            if (!status.getCode().equals("200") && !status.getCode().equals(StatusCode.PCDH_SUCCESS.toString())) {
               Iterator var5 = status.getMessage().iterator();

               while(var5.hasNext()) {
                  LocalisedString localisedString = (LocalisedString)var5.next();
                  LOG.debug("******************** STATUS MESSAGE: " + message + " **********************");
                  String lang = localisedString.getLang().toString();
                  System.out.println("Language default :" + Locale.getDefault().toString());
                  if (Locale.getDefault().toString().contains(lang.toLowerCase())) {
                     throw new IntegrationModuleException("[" + status.getCode() + "] " + localisedString.getValue());
                  }
               }
            }

            LOG.debug("******************** STATUS CODE " + status.getCode() + " **********************");
         }
      } else {
         throw new IntegrationModuleException(I18nHelper.getLabel("error.technical.response.null"));
      }
   }

   private void initThread() {
      PropertyHandler propertyHandler = this.getPropertyHandler();
      if (propertyHandler != null && propertyHandler.hasProperty("thread.number.limit")) {
         int threadLimitTmp = propertyHandler.getIntegerProperty("thread.number.limit");
         if (threadLimitTmp > 30) {
            this.threadLimit = 30;
            LOG.debug("The thread.number.limit is higher than what is tolerated, the maximum value will be used instead. The maximum value is :30");
         } else {
            this.threadLimit = threadLimitTmp;
         }
      }

      LOG.debug("A maximum of " + this.threadLimit + "has been defined ");
   }

   // $FF: synthetic method
   static final RetrieveTimestampsResponse getMedicationSchemeTimestamps_aroundBody0(PCDHIntegrationModuleImpl ajc$this, RetrieveTimestampsRequest retrieveTimestampsRequest, JoinPoint var2) {
      ApplicationConfig.getInstance().assertValidSession();
      LOG.debug("******************** GET_MEDICATIONSCHEME_TIMESTAMPS - Validate incoming fields **********************");
      List errors = GFDDPPValidationUtils.validate(retrieveTimestampsRequest);
      if (CollectionUtils.isNotEmpty(errors)) {
         RetrieveTimestampsResponse response = new RetrieveTimestampsResponse();
         Status status = new Status(MSStatusCode.MEDICATION_SCHEME_VALIDATION_ERRORS.getCode(), MSStatusResolver.getStatusMessage(MSStatusCode.MEDICATION_SCHEME_VALIDATION_ERRORS));
         status.getErrors().addAll(errors);
         response.setStatus(status);
         response.setClientMessageID(retrieveTimestampsRequest == null ? null : retrieveTimestampsRequest.getClientMessageID());
         response.setServerMessageID(UUID.randomUUID().toString());
         response.setCurrentDateTime(Calendar.getInstance());
         return response;
      } else {
         String clientMessageId = retrieveTimestampsRequest.getClientMessageID();

         try {
            LOG.debug("******************** GET_MEDICATIONSCHEME_TIMESTAMPS -  Create request  **********************");
            SealedRequestType sealedRequestType = new SealedRequestType();
            String requestorId = StandaloneRequestorProvider.getRequestorIdInformation();
            String requestorType = StandaloneRequestorProvider.getRequestorTypeInformation();
            String xml = RequestCreatorUtils.createRequestMedicationSchemeTimestamps(requestorId, requestorType, retrieveTimestampsRequest.getSubjectIDs(), clientMessageId);
            LOG.debug("******************** GET_MEDICATIONSCHEME_TIMESTAMPS - UNSEALED request: " + xml + " ************************");
            LOG.debug("******************** GET_MEDICATIONSCHEME_TIMESTAMPS -  Seal request xml  **********************");
            List etks = ajc$this.getEtkHelper().getPCDH_ETK();
            byte[] Request = SealedProcessor.createSealedSync(etks, xml, "GetTimestamps");
            sealedRequestType.setRequestParametersSealed(Request);
            LOG.info("******************** GET_MEDICATIONSCHEME_TIMESTAMPS - CALL PCDH **********************");
            SealedResponseType sealedResponseType = PcdhServiceImpl.getInstance().getData(sealedRequestType);
            LOG.info("******************** GET_MEDICATIONSCHEME_TIMESTAMPS - VALIDATE RESPONSE PCDH **********************");
            ajc$this.validateResponse(sealedResponseType);
            LOG.info("******************** GET_MEDICATIONSCHEME_TIMESTAMPS - UNSEAL SingleMessage response **********************");
            byte[] singleMessageSealed = sealedResponseType.getSingleMessageSealed();
            byte[] singleMessage = Crypto.unseal(singleMessageSealed);
            ajc$this.xmlValidationhelper.assertValidSingleMessage(singleMessage);
            SingleMessage smg = (SingleMessage)ajc$this.getJaxContextCentralizer().toObject(SingleMessage.class, singleMessage);
            SingleMessageWrapper wrapper = new SingleMessageWrapper(smg);
            List events = wrapper.getEvents();
            if (!CollectionUtils.isEmpty(events) && events.size() <= 1 && events.get(0) instanceof MedicationSchemeTimestampsResponse) {
               MedicationSchemeTimestampsResponse pcdhResponse = (MedicationSchemeTimestampsResponse)events.get(0);
               if (!StringUtils.defaultIfBlank(clientMessageId, "").equals(StringUtils.defaultIfBlank(pcdhResponse.getClientMessageId(), ""))) {
                  throw new IntegrationModuleException(I18nHelper.getLabel("error.clientmessageids.not.equal"));
               } else {
                  LOG.info("******************** GETTIMESTAMPS - Create response **********************");
                  return MappingUtils.mapMedicationSchemeTimestampsResponse(pcdhResponse);
               }
            } else {
               throw new IntegrationModuleException(I18nHelper.getLabel("error.number.of.timestamp.responses"));
            }
         } catch (Throwable var32) {
            Exceptionutils.errorHandler(var32);
            return null;
         }
      }
   }

   // $FF: synthetic method
   static final FetchDataEntriesResponse getMedicationSchemeEntries_aroundBody2(PCDHIntegrationModuleImpl ajc$this, FetchDataEntriesRequest getMedicationEntriesRequest, JoinPoint var2) {
      ApplicationConfig.getInstance().assertValidSession();
      LOG.debug("******************** GET_MEDICATIONSCHEME_ENTRIES - Validate incoming fields **********************");
      List errors = GFDDPPValidationUtils.validate(getMedicationEntriesRequest);
      if (CollectionUtils.isNotEmpty(errors)) {
         FetchDataEntriesResponse response = new FetchDataEntriesResponse();
         Status status = new Status(MSStatusCode.MEDICATION_SCHEME_VALIDATION_ERRORS.getCode(), MSStatusResolver.getStatusMessage(MSStatusCode.MEDICATION_SCHEME_VALIDATION_ERRORS));
         status.getErrors().addAll(errors);
         response.setStatus(status);
         response.setServerMessageID(UUID.randomUUID().toString());
         response.setClientMessageID(getMedicationEntriesRequest == null ? null : getMedicationEntriesRequest.getClientMessageID());
         return response;
      } else {
         String subjectId = getMedicationEntriesRequest.getSubjectID();
         String clientMessageId = getMedicationEntriesRequest.getClientMessageID();

         try {
            LOG.debug("******************** GET_MEDICATIONSCHEME_ENTRIES -  Create request  **********************");
            SealedRequestType sealedRequestType = new SealedRequestType();
            String requestorId = StandaloneRequestorProvider.getRequestorIdInformation();
            String requestorType = StandaloneRequestorProvider.getRequestorTypeInformation();
            Integer paginationIndex = 1;
            if (getMedicationEntriesRequest.getPagination() != null) {
               paginationIndex = getMedicationEntriesRequest.getPagination().getPaginationIndex();
            }

            String xml = RequestCreatorUtils.createRequestMedicationSchemeEntries(requestorId, requestorType, subjectId, paginationIndex, clientMessageId);
            LOG.debug("******************** GET_MEDICATIONSCHEME_ENTRIES - UNSEALED request: " + xml + " ************************");
            LOG.debug("******************** GET_MEDICATIONSCHEME_ENTRIES -  Seal request xml  **********************");
            List etks = ajc$this.getEtkHelper().getPCDH_ETK();
            byte[] Request = SealedProcessor.createSealedSync(etks, xml, "GetMedicationScheme");
            sealedRequestType.setRequestParametersSealed(Request);
            LOG.info("******************** GET_MEDICATIONSCHEME_ENTRIES - CALL PCDH **********************");
            SealedResponseType sealedResponseType = PcdhServiceImpl.getInstance().getData(sealedRequestType);
            LOG.info("******************** GET_MEDICATIONSCHEME_ENTRIES - VALIDATE RESPONSE PCDH **********************");
            ajc$this.validateResponse(sealedResponseType);
            LOG.info("******************** GET_MEDICATIONSCHEME_ENTRIES - UNSEAL SingleMessage response **********************");
            byte[] singleMessageSealed = sealedResponseType.getSingleMessageSealed();
            byte[] singleMessage = Crypto.unseal(singleMessageSealed);
            ajc$this.xmlValidationhelper.assertValidSingleMessage(singleMessage);
            SingleMessage smg = (SingleMessage)ajc$this.getJaxContextCentralizer().toObject(SingleMessage.class, singleMessage);
            SingleMessageWrapper wrapper = new SingleMessageWrapper(smg);
            List events = wrapper.getEvents();
            if (!CollectionUtils.isEmpty(events) && events.size() <= 1 && events.get(0) instanceof MedicationSchemeEntriesResponse) {
               MedicationSchemeEntriesResponse pcdhResponse = (MedicationSchemeEntriesResponse)events.get(0);
               if (!StringUtils.defaultIfBlank(clientMessageId, "").equals(StringUtils.defaultIfBlank(pcdhResponse.getClientMessageId(), ""))) {
                  throw new IntegrationModuleException(I18nHelper.getLabel("error.clientmessageids.not.equal"));
               } else if (!StringUtils.defaultIfBlank(subjectId, "").equals(StringUtils.defaultIfBlank(pcdhResponse.getSubjectId(), ""))) {
                  throw new IntegrationModuleException(I18nHelper.getLabel("error.subjectids.not.equal"));
               } else {
                  LOG.info("******************** GET_MEDICATIONSCHEME_ENTRIES - load encryption keys **********************");
                  Set encryptionKeyIds = new HashSet();
                  if (pcdhResponse.getMedicationScheme() != null) {
                     Iterator var39 = pcdhResponse.getMedicationScheme().getDataEntry().iterator();

                     while(var39.hasNext()) {
                        DataEntryResponse pcdhDataEntry = (DataEntryResponse)var39.next();
                        if (pcdhDataEntry.getEncryptedData() != null) {
                           encryptionKeyIds.add(pcdhDataEntry.getEncryptedData().getEncryptionKeyId());
                        }
                     }
                  }

                  ajc$this.loadEncryptionKeysToCache(new ArrayList(encryptionKeyIds), ajc$this.getSystemETKFromCache());
                  LOG.info("******************** GET_MEDICATIONSCHEME_ENTRIES - create Response **********************");
                  FetchDataEntriesResponse response = MappingUtils.mapMedicationSchemeEntriesResponse(pcdhResponse, ajc$this.getKgssCache(), ajc$this.threadLimit);
                  return response;
               }
            } else {
               throw new IntegrationModuleException(I18nHelper.getLabel("error.number.of.fetchmedicationentries.responses"));
            }
         } catch (Throwable var42) {
            Exceptionutils.errorHandler(var42);
            return null;
         }
      }
   }

   // $FF: synthetic method
   static final String getPharmacyDetails_aroundBody4(PCDHIntegrationModuleImpl ajc$this, String patientIdType, String patientId, String dGuid, String motivationType, String motivationText, boolean requestPatientSignature, ThreeStateBoolean therapeuticRelationShip, JoinPoint var8) {
      ApplicationConfig.getInstance().assertValidSession();
      LOG.debug("******************** GETPHARMACYDETAILS - Validate incoming fields **********************");
      ValidationUtils.validateIncomingFieldsGetPharmacyDetails(patientIdType, patientId, dGuid, motivationText, motivationType);
      ValidationUtils.validatePatientId(patientId);
      String pharmacyId = null;

      try {
         LOG.debug("******************** GETPHARMACYDETAILS -  Create request  **********************");
         SealedRequestType sealedRequestType = new SealedRequestType();
         PatientType patient = RequestCreatorUtils.createPatientType(patientId, patientIdType);
         LOG.debug("******************** GETPHARMACYDETAILS - Get requestor information from SAML  **********************");
         String requestorId = StandaloneRequestorProvider.getRequestorIdInformation();
         String requestorType = StandaloneRequestorProvider.getRequestorTypeInformation();
         String xml = RequestCreatorUtils.createRequestPharmacyDetails(requestorId, requestorType, patient, dGuid, motivationType, motivationText);
         LOG.debug("******************** GETPHARMACYDETAILS - UNSEALED request: " + xml + " ************************");
         LOG.debug("******************** GETPHARMACYDETAILS -  Seal request xml  **********************");
         List etks = ajc$this.getEtkHelper().getPCDH_ETK();
         byte[] Request = SealedProcessor.createSealedSync(etks, xml, "RequestPharmacyDetails");
         sealedRequestType.setRequestParametersSealed(Request);
         LOG.info("Request sealed");
         sealedRequestType.setAuthorizationParameters(RequestCreatorUtils.createPCDHAuthorizationParameters(requestPatientSignature, therapeuticRelationShip, dGuid, patient.getPatientId(), ajc$this.getEncryptionUtils()));
         if (requestPatientSignature) {
            LOG.info("Signature required");
            sealedRequestType.getAuthorizationParameters().getPatientConsent().getPatientSignature().setSignature(Crypto.seal(etks, sealedRequestType.getAuthorizationParameters().getPatientConsent().getPatientSignature().getSignature()));
            LOG.info("Message signed");
         }

         LOG.info("******************** GETPHARMACYDETAILS - CALL PCDH **********************");
         SealedResponseType sealedResponseType = PcdhServiceImpl.getInstance().getPharmacyDetails(sealedRequestType);
         LOG.info("******************** GETPHARMACYDETAILS - VALIDATE RESPONSE PCDH **********************");
         ajc$this.validateResponse(sealedResponseType);
         LOG.info("******************** GETPHARMACYDETAILS - UNSEAL SingleMessage response **********************");
         byte[] singleMessageSealed = sealedResponseType.getSingleMessageSealed();
         byte[] singleMessage = Crypto.unseal(singleMessageSealed);
         ajc$this.xmlValidationhelper.assertValidSingleMessage(singleMessage);
         SingleMessage sng = (SingleMessage)ajc$this.getJaxContextCentralizer().toObject(SingleMessage.class, singleMessage);
         SingleMessageWrapper wrapper = new SingleMessageWrapper(sng);
         if (wrapper.getEntities() != null && wrapper.getEntities().size() > 0) {
            ReferencePharmacyType referencePharmacyType = (ReferencePharmacyType)wrapper.getEntities().get(0);
            pharmacyId = PharmacyIdType.valueOf(referencePharmacyType.getPharmacyId().getClass()).getIdFrom(referencePharmacyType.getPharmacyId());
         }

         LOG.debug("returned pharmacyId: " + pharmacyId);
      } catch (Throwable var36) {
         Exceptionutils.errorHandler(var36);
      }

      return pharmacyId;
   }

   // $FF: synthetic method
   static final String getData_aroundBody6(PCDHIntegrationModuleImpl ajc$this, String patientIdType, String patientId, String dataType, String dateRange, boolean ExcludeOwnData, byte[] localData, boolean requestPatientSignature, ThreeStateBoolean therapeuticRelationShip, JoinPoint var9) {
      ApplicationConfig.getInstance().assertValidSession();
      LOG.debug("******************** GETDATA - Validate incoming fields **********************");
      ValidationUtils.validateIncomingFieldsGetData(patientIdType, patientId, dataType, dateRange);
      ValidationUtils.validatePatientId(patientId);
      Object response = null;

      try {
         LOG.debug("******************** GETDATA - Create request  **********************");
         SealedRequestType sealedRequestType = new SealedRequestType();
         LOG.debug("******************** GETDATA - Create Patient object  **********************");
         PatientType patient = RequestCreatorUtils.createPatientType(patientId, patientIdType);
         LOG.debug("******************** GETDATA - Get requestor information from SAML  **********************");
         String requestorId = StandaloneRequestorProvider.getRequestorIdInformation();
         String requestorType = StandaloneRequestorProvider.getRequestorTypeInformation();
         LOG.debug("******************** GETDATA - Create get data request **********************");
         String xml = RequestCreatorUtils.createGetDataParams(patient, requestorId, requestorType, ExcludeOwnData, dateRange, dataType);
         LOG.debug("******************** GETDATA - Request parameter sealed \n" + xml);
         LOG.info("******************** GETDATA - Seal request xml  **********************");
         List etks = ajc$this.getEtkHelper().getPCDH_ETK();
         byte[] Request = SealedProcessor.createSealedSync(etks, xml, "GetDataParams");
         sealedRequestType.setRequestParametersSealed(Request);
         LOG.debug("******************** GETDATA - add authorization parameters  **********************");
         sealedRequestType.setAuthorizationParameters(RequestCreatorUtils.createPCDHAuthorizationParameters(requestPatientSignature, therapeuticRelationShip, (String)null, patient.getPatientId(), ajc$this.getEncryptionUtils()));
         if (requestPatientSignature) {
            LOG.info("Signature required");
            sealedRequestType.getAuthorizationParameters().getPatientConsent().getPatientSignature().setSignature(Crypto.seal(etks, sealedRequestType.getAuthorizationParameters().getPatientConsent().getPatientSignature().getSignature()));
            LOG.info("Message signed");
         }

         LOG.info("******************** GETDATA - CALL PCDH **********************");
         SealedResponseType sealedResponseType = PcdhServiceImpl.getInstance().getData(sealedRequestType);
         LOG.info("******************** GETDATA - VALIDATE RESPONSE PCDH  **********************");
         ajc$this.validateResponse(sealedResponseType);
         LOG.info("******************** GETDATA - UNSEAL SingleMessage response **********************");
         byte[] singleMessageSealed = sealedResponseType.getSingleMessageSealed();
         byte[] singleMessage = Crypto.unseal(singleMessageSealed);
         LOG.info("******************** GETDATA - VALIDATE SingleMessage response **********************");
         ajc$this.xmlValidationhelper.assertValidSingleMessage(singleMessage);
         LOG.debug("******************** GETDATA - LENGTH singlemessagee SEALED client side: " + singleMessageSealed.length);
         LogsSMC.logDguidSguid(singleMessage, LogsSMC.IntermediateMessage.UNSEAL, ajc$this.getClass());
         StatusType status = sealedResponseType.getStatus();
         SingleMessage sm = null;
         if (status != null) {
            if (localData != null && localData.length > 0) {
               LOG.debug("****************** GETDATA - Consolidation **********************");
               LOG.debug("****************** GETDATA - LOCAL DATA: " + Arrays.toString(localData) + " *****************");
               sm = ConsolidateUtils.createConsolidatedGetDataResponse(singleMessage, localData, patientId);
            } else {
               LOG.debug("****************** GETDATA - NO LOCAL DATA: *****************");
               LOG.debug("****************** GETDATA - RETURN STRING REPRESENTATION OF RESPONSE BEFORE UNSEALING PRODUCTS: " + new String(singleMessage) + " *****************");
               sm = (SingleMessage)ajc$this.getJaxContextCentralizer().toObject(SingleMessage.class, singleMessage);
            }

            SingleMessageWrapper wrapper = new SingleMessageWrapper(sm);
            LogsSMC.logDguidSguid(wrapper, LogsSMC.IntermediateMessage.CONSOLIDATED, ajc$this.getClass());
            ajc$this.convertEncryptedContentToProduct(wrapper);
            String updatedSm = ajc$this.getJaxContextCentralizer().toXml(SingleMessage.class, wrapper.getWrappedMessage());
            return updatedSm;
         }
      } catch (Throwable var39) {
         Exceptionutils.errorHandler(var39);
      }

      return (String)response;
   }

   // $FF: synthetic method
   static final String getDataTypes_aroundBody8(PCDHIntegrationModuleImpl ajc$this, String patientIdType, String patientId, boolean requestPatientSignature, ThreeStateBoolean therapeuticRelationShip, JoinPoint var5) {
      ApplicationConfig.getInstance().assertValidSession();
      LOG.debug("******************** GETDATATYPES - Validate incoming fields **********************");
      ValidationUtils.validateIncomingFieldsGetDataTypes(patientId, patientIdType);
      ValidationUtils.validatePatientId(patientId);
      byte[] singleMessage = null;

      try {
         LOG.debug("******************** GETDATATYPES -  Create GetDataTypes request  **********************");
         SealedRequestType sealedRequestType = new SealedRequestType();
         PatientType patient = RequestCreatorUtils.createPatientType(patientId, patientIdType);
         LOG.debug("******************** GETDATATYPES - Get requestor information from SAML  **********************");
         String requestorId = StandaloneRequestorProvider.getRequestorIdInformation();
         String requestorType = StandaloneRequestorProvider.getRequestorTypeInformation();
         String xml = RequestCreatorUtils.createGetDataTypesParams(patient, requestorId, requestorType);
         LOG.info("******************** GETDATATYPES -  Seal GetData request xml  **********************");
         List etks = ajc$this.getEtkHelper().getPCDH_ETK();
         byte[] Request = SealedProcessor.createSealedSync(etks, xml, "GetDataTypesParams");
         sealedRequestType.setRequestParametersSealed(Request);
         sealedRequestType.setAuthorizationParameters(RequestCreatorUtils.createPCDHAuthorizationParameters(requestPatientSignature, therapeuticRelationShip, (String)null, patient.getPatientId(), ajc$this.getEncryptionUtils()));
         if (requestPatientSignature) {
            sealedRequestType.getAuthorizationParameters().getPatientConsent().getPatientSignature().setSignature(Crypto.seal(etks, sealedRequestType.getAuthorizationParameters().getPatientConsent().getPatientSignature().getSignature()));
         }

         LOG.info("******************** GETDATATYPES - CALL PCDH  **********************");
         SealedResponseType sealedResponseType = null;
         sealedResponseType = PcdhServiceImpl.getInstance().getDataTypes(sealedRequestType);
         LOG.info("******************** GETDATATYPES - VALIDATE RESPONSE PCDH  **********************");
         ajc$this.validateResponse(sealedResponseType);
         LOG.info("******************** GETDATATYPES - UNSEAL SingleMessage response **********************");
         byte[] singleMessageSealed = sealedResponseType.getSingleMessageSealed();
         singleMessage = Crypto.unseal(singleMessageSealed);
         ajc$this.xmlValidationhelper.assertValidSingleMessage(singleMessage);
         SingleMessage smo = (SingleMessage)ajc$this.getJaxContextCentralizer().toObject(SingleMessage.class, singleMessage);
         ValidationUtils.validateDataTypesResult(smo);
      } catch (Throwable var27) {
         Exceptionutils.errorHandler(var27);
      }

      return new String(singleMessage);
   }

   // $FF: synthetic method
   private static void ajc$preClinit() {
      Factory var0 = new Factory("PCDHIntegrationModuleImpl.java", PCDHIntegrationModuleImpl.class);
      ajc$tjp_0 = var0.makeSJP("method-execution", var0.makeMethodSig("1", "getMedicationSchemeTimestamps", "be.business.connector.gfddpp.pcdh.PCDHIntegrationModuleImpl", "be.business.connector.gfddpp.domain.medicationscheme.protocol.RetrieveTimestampsRequest", "retrieveTimestampsRequest", "be.business.connector.core.exceptions.IntegrationModuleException", "be.business.connector.gfddpp.domain.medicationscheme.protocol.RetrieveTimestampsResponse"), 76);
      ajc$tjp_1 = var0.makeSJP("method-execution", var0.makeMethodSig("1", "getMedicationSchemeEntries", "be.business.connector.gfddpp.pcdh.PCDHIntegrationModuleImpl", "be.business.connector.gfddpp.domain.medicationscheme.protocol.FetchDataEntriesRequest", "getMedicationEntriesRequest", "be.business.connector.core.exceptions.IntegrationModuleException", "be.business.connector.gfddpp.domain.medicationscheme.protocol.FetchDataEntriesResponse"), 143);
      ajc$tjp_2 = var0.makeSJP("method-execution", var0.makeMethodSig("1", "getPharmacyDetails", "be.business.connector.gfddpp.pcdh.PCDHIntegrationModuleImpl", "java.lang.String:java.lang.String:java.lang.String:java.lang.String:java.lang.String:boolean:be.business.connector.gfddpp.domain.ThreeStateBoolean", "patientIdType:patientId:dGuid:motivationType:motivationText:requestPatientSignature:therapeuticRelationShip", "be.business.connector.core.exceptions.IntegrationModuleException", "java.lang.String"), 230);
      ajc$tjp_3 = var0.makeSJP("method-execution", var0.makeMethodSig("1", "getData", "be.business.connector.gfddpp.pcdh.PCDHIntegrationModuleImpl", "java.lang.String:java.lang.String:java.lang.String:java.lang.String:boolean:[B:boolean:be.business.connector.gfddpp.domain.ThreeStateBoolean", "patientIdType:patientId:dataType:dateRange:ExcludeOwnData:localData:requestPatientSignature:therapeuticRelationShip", "be.business.connector.core.exceptions.IntegrationModuleException", "java.lang.String"), 297);
      ajc$tjp_4 = var0.makeSJP("method-execution", var0.makeMethodSig("1", "getDataTypes", "be.business.connector.gfddpp.pcdh.PCDHIntegrationModuleImpl", "java.lang.String:java.lang.String:boolean:be.business.connector.gfddpp.domain.ThreeStateBoolean", "patientIdType:patientId:requestPatientSignature:therapeuticRelationShip", "be.business.connector.core.exceptions.IntegrationModuleException", "java.lang.String"), 451);
   }
}
