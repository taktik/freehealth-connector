package be.business.connector.recipe.prescriber;

import be.business.connector.common.ApplicationConfig;
import be.business.connector.common.StandaloneRequestorProvider;
import be.business.connector.core.domain.IdentifierTypes;
import be.business.connector.core.domain.KgssIdentifierType;
import be.business.connector.core.exceptions.IntegrationModuleException;
import be.business.connector.core.utils.Exceptionutils;
import be.business.connector.core.utils.I18nHelper;
import be.business.connector.core.utils.IOUtils;
import be.business.connector.core.utils.MarshallerHelper;
import be.business.connector.projects.common.utils.ValidationUtils;
import be.business.connector.recipe.prescriber.services.RecipePrescriberServiceImpl;
import be.ehealth.technicalconnector.service.kgss.domain.KeyResult;
import be.fgov.ehealth.etee.crypto.encrypt.EncryptionToken;
import be.fgov.ehealth.recipe.core.v1.CreatePrescriptionAdministrativeInformationType;
import be.fgov.ehealth.recipe.core.v1.PrescriberServiceAdministrativeInformationType;
import be.fgov.ehealth.recipe.core.v1.SecuredContentType;
import be.fgov.ehealth.recipe.core.v1.SendNotificationAdministrativeInformationType;
import be.fgov.ehealth.recipe.protocol.v1.AliveCheckRequest;
import be.fgov.ehealth.recipe.protocol.v1.AliveCheckResponse;
import be.fgov.ehealth.recipe.protocol.v1.CreatePrescriptionRequest;
import be.fgov.ehealth.recipe.protocol.v1.CreatePrescriptionResponse;
import be.fgov.ehealth.recipe.protocol.v1.GetPrescriptionForPrescriberRequest;
import be.fgov.ehealth.recipe.protocol.v1.GetPrescriptionForPrescriberResponse;
import be.fgov.ehealth.recipe.protocol.v1.ListFeedbacksRequest;
import be.fgov.ehealth.recipe.protocol.v1.ListFeedbacksResponse;
import be.fgov.ehealth.recipe.protocol.v1.ListOpenPrescriptionsRequest;
import be.fgov.ehealth.recipe.protocol.v1.ListOpenPrescriptionsResponse;
import be.fgov.ehealth.recipe.protocol.v1.RevokePrescriptionRequest;
import be.fgov.ehealth.recipe.protocol.v1.SendNotificationRequest;
import be.fgov.ehealth.recipe.protocol.v1.UpdateFeedbackFlagRequest;
import be.recipe.services.prescriber.CreatePrescriptionParam;
import be.recipe.services.prescriber.CreatePrescriptionResult;
import be.recipe.services.prescriber.GetListOpenPrescriptionParam;
import be.recipe.services.prescriber.GetListOpenPrescriptionResult;
import be.recipe.services.prescriber.GetPrescriptionForPrescriberParam;
import be.recipe.services.prescriber.GetPrescriptionForPrescriberResult;
import be.recipe.services.prescriber.ListFeedbackItem;
import be.recipe.services.prescriber.ListFeedbacksParam;
import be.recipe.services.prescriber.ListFeedbacksResult;
import be.recipe.services.prescriber.RevokePrescriptionParam;
import be.recipe.services.prescriber.SendNotificationParam;
import be.recipe.services.prescriber.UpdateFeedbackFlagParam;
import com.sun.xml.ws.client.ClientTransportException;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.JoinPoint.StaticPart;
import org.aspectj.runtime.internal.Conversions;
import org.aspectj.runtime.reflect.Factory;
import org.bouncycastle.util.encoders.Base64;
import org.perf4j.aop.Profiled;
import org.perf4j.log4j.aop.TimingAspect;

public class PrescriberIntegrationModuleImpl extends AbstractPrescriberIntegrationModule implements PrescriberIntegrationModule {
   private static final Logger LOG;
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
   // $FF: synthetic field
   private static final StaticPart ajc$tjp_7;
   // $FF: synthetic field
   private static Annotation ajc$anno$7;
   // $FF: synthetic field
   private static final StaticPart ajc$tjp_8;
   // $FF: synthetic field
   private static Annotation ajc$anno$8;
   // $FF: synthetic field
   private static final StaticPart ajc$tjp_9;
   // $FF: synthetic field
   private static Annotation ajc$anno$9;

   static {
      ajc$preClinit();
      LOG = Logger.getLogger(PrescriberIntegrationModuleImpl.class);
   }

   public PrescriberIntegrationModuleImpl() throws IntegrationModuleException {
   }

   @Profiled(
      logFailuresSeparately = true,
      tag = "PrescriberIntegrationModule#prepareCreatePrescription"
   )
   public void prepareCreatePrescription(String patientId, String prescriptionType) throws IntegrationModuleException {
      JoinPoint var6 = Factory.makeJP(ajc$tjp_0, this, this, patientId, prescriptionType);
      TimingAspect var10000 = TimingAspect.aspectOf();
      Object[] var7 = new Object[]{this, patientId, prescriptionType, var6};
      ProceedingJoinPoint var10001 = (new PrescriberIntegrationModuleImpl$AjcClosure1(var7)).linkClosureAndJoinPoint(69648);
      Annotation var10002 = ajc$anno$0;
      if (var10002 == null) {
         var10002 = ajc$anno$0 = PrescriberIntegrationModuleImpl.class.getDeclaredMethod("prepareCreatePrescription", String.class, String.class).getAnnotation(Profiled.class);
      }

      var10000.doPerfLogging(var10001, (Profiled)var10002);
   }

   @Profiled(
      logFailuresSeparately = true,
      tag = "0.PrescriberIntegrationModule#createPrescription"
   )
   public String createPrescription(boolean feedbackRequested, String patientId, byte[] prescription, String prescriptionType) throws IntegrationModuleException {
      StaticPart var10000 = ajc$tjp_1;
      Object[] var20 = new Object[]{Conversions.booleanObject(feedbackRequested), patientId, prescription, prescriptionType};
      JoinPoint var19 = Factory.makeJP(var10000, this, this, var20);
      TimingAspect var22 = TimingAspect.aspectOf();
      Object[] var21 = new Object[]{this, Conversions.booleanObject(feedbackRequested), patientId, prescription, prescriptionType, var19};
      ProceedingJoinPoint var10001 = (new PrescriberIntegrationModuleImpl$AjcClosure3(var21)).linkClosureAndJoinPoint(69648);
      Annotation var10002 = ajc$anno$1;
      if (var10002 == null) {
         var10002 = ajc$anno$1 = PrescriberIntegrationModuleImpl.class.getDeclaredMethod("createPrescription", Boolean.TYPE, String.class, byte[].class, String.class).getAnnotation(Profiled.class);
      }

      return (String)var22.doPerfLogging(var10001, (Profiled)var10002);
   }

   private SecuredContentType createSecuredContentType(byte[] content) {
      SecuredContentType secured = new SecuredContentType();
      secured.setSecuredContent(content);
      return secured;
   }

   @Profiled(
      logFailuresSeparately = true,
      tag = "0.PrescriberIntegrationModule#getPrescription"
   )
   public GetPrescriptionForPrescriberResult getPrescription(String rid) throws IntegrationModuleException {
      JoinPoint var12 = Factory.makeJP(ajc$tjp_2, this, this, rid);
      TimingAspect var10000 = TimingAspect.aspectOf();
      Object[] var13 = new Object[]{this, rid, var12};
      ProceedingJoinPoint var10001 = (new PrescriberIntegrationModuleImpl$AjcClosure5(var13)).linkClosureAndJoinPoint(69648);
      Annotation var10002 = ajc$anno$2;
      if (var10002 == null) {
         var10002 = ajc$anno$2 = PrescriberIntegrationModuleImpl.class.getDeclaredMethod("getPrescription", String.class).getAnnotation(Profiled.class);
      }

      return (GetPrescriptionForPrescriberResult)var10000.doPerfLogging(var10001, (Profiled)var10002);
   }

   @Profiled(
      logFailuresSeparately = true,
      tag = "PrescriberIntegrationModule#ping"
   )
   public void ping() throws IntegrationModuleException {
      JoinPoint var3 = Factory.makeJP(ajc$tjp_3, this, this);
      TimingAspect var10000 = TimingAspect.aspectOf();
      Object[] var4 = new Object[]{this, var3};
      ProceedingJoinPoint var10001 = (new PrescriberIntegrationModuleImpl$AjcClosure7(var4)).linkClosureAndJoinPoint(69648);
      Annotation var10002 = ajc$anno$3;
      if (var10002 == null) {
         var10002 = ajc$anno$3 = PrescriberIntegrationModuleImpl.class.getDeclaredMethod("ping").getAnnotation(Profiled.class);
      }

      var10000.doPerfLogging(var10001, (Profiled)var10002);
   }

   @Profiled(
      logFailuresSeparately = true,
      tag = "0.PrescriberIntegrationModule#revokePrescription"
   )
   public void revokePrescription(String rid, String reason) throws IntegrationModuleException {
      JoinPoint var11 = Factory.makeJP(ajc$tjp_4, this, this, rid, reason);
      TimingAspect var10000 = TimingAspect.aspectOf();
      Object[] var12 = new Object[]{this, rid, reason, var11};
      ProceedingJoinPoint var10001 = (new PrescriberIntegrationModuleImpl$AjcClosure9(var12)).linkClosureAndJoinPoint(69648);
      Annotation var10002 = ajc$anno$4;
      if (var10002 == null) {
         var10002 = ajc$anno$4 = PrescriberIntegrationModuleImpl.class.getDeclaredMethod("revokePrescription", String.class, String.class).getAnnotation(Profiled.class);
      }

      var10000.doPerfLogging(var10001, (Profiled)var10002);
   }

   @Profiled(
      logFailuresSeparately = true,
      tag = "0.PrescriberIntegrationModule#listOpenPrescription"
   )
   public List<String> listOpenPrescription(String patientId) throws IntegrationModuleException {
      JoinPoint var9 = Factory.makeJP(ajc$tjp_5, this, this, patientId);
      TimingAspect var10000 = TimingAspect.aspectOf();
      Object[] var10 = new Object[]{this, patientId, var9};
      ProceedingJoinPoint var10001 = (new PrescriberIntegrationModuleImpl$AjcClosure11(var10)).linkClosureAndJoinPoint(69648);
      Annotation var10002 = ajc$anno$5;
      if (var10002 == null) {
         var10002 = ajc$anno$5 = PrescriberIntegrationModuleImpl.class.getDeclaredMethod("listOpenPrescription", String.class).getAnnotation(Profiled.class);
      }

      return (List)var10000.doPerfLogging(var10001, (Profiled)var10002);
   }

   @Profiled(
      logFailuresSeparately = true,
      tag = "0.PrescriberIntegrationModule#listOpenPrescription"
   )
   public List<String> listOpenPrescription() throws IntegrationModuleException {
      JoinPoint var1 = Factory.makeJP(ajc$tjp_6, this, this);
      TimingAspect var10000 = TimingAspect.aspectOf();
      Object[] var2 = new Object[]{this, var1};
      ProceedingJoinPoint var10001 = (new PrescriberIntegrationModuleImpl$AjcClosure13(var2)).linkClosureAndJoinPoint(69648);
      Annotation var10002 = ajc$anno$6;
      if (var10002 == null) {
         var10002 = ajc$anno$6 = PrescriberIntegrationModuleImpl.class.getDeclaredMethod("listOpenPrescription").getAnnotation(Profiled.class);
      }

      return (List)var10000.doPerfLogging(var10001, (Profiled)var10002);
   }

   @Profiled(
      logFailuresSeparately = true,
      tag = "PrescriberIntegrationModule#sendNotification"
   )
   public void sendNotification(byte[] notificationText, String patientId, String executorId) throws IntegrationModuleException {
      StaticPart var10000 = ajc$tjp_7;
      Object[] var19 = new Object[]{notificationText, patientId, executorId};
      JoinPoint var18 = Factory.makeJP(var10000, this, this, var19);
      TimingAspect var21 = TimingAspect.aspectOf();
      Object[] var20 = new Object[]{this, notificationText, patientId, executorId, var18};
      ProceedingJoinPoint var10001 = (new PrescriberIntegrationModuleImpl$AjcClosure15(var20)).linkClosureAndJoinPoint(69648);
      Annotation var10002 = ajc$anno$7;
      if (var10002 == null) {
         var10002 = ajc$anno$7 = PrescriberIntegrationModuleImpl.class.getDeclaredMethod("sendNotification", byte[].class, String.class, String.class).getAnnotation(Profiled.class);
      }

      var21.doPerfLogging(var10001, (Profiled)var10002);
   }

   @Profiled(
      logFailuresSeparately = true,
      tag = "0.PrescriberIntegrationModule#updateFeedbackFlag"
   )
   public void updateFeedbackFlag(String rid, boolean feedbackAllowed) throws IntegrationModuleException {
      JoinPoint var11 = Factory.makeJP(ajc$tjp_8, this, this, rid, Conversions.booleanObject(feedbackAllowed));
      TimingAspect var10000 = TimingAspect.aspectOf();
      Object[] var12 = new Object[]{this, rid, Conversions.booleanObject(feedbackAllowed), var11};
      ProceedingJoinPoint var10001 = (new PrescriberIntegrationModuleImpl$AjcClosure17(var12)).linkClosureAndJoinPoint(69648);
      Annotation var10002 = ajc$anno$8;
      if (var10002 == null) {
         var10002 = ajc$anno$8 = PrescriberIntegrationModuleImpl.class.getDeclaredMethod("updateFeedbackFlag", String.class, Boolean.TYPE).getAnnotation(Profiled.class);
      }

      var10000.doPerfLogging(var10001, (Profiled)var10002);
   }

   @Profiled(
      logFailuresSeparately = true,
      tag = "0.PrescriberIntegrationModule#listFeedback"
   )
   public List<ListFeedbackItem> listFeedback(boolean readFlag) throws IntegrationModuleException {
      JoinPoint var14 = Factory.makeJP(ajc$tjp_9, this, this, Conversions.booleanObject(readFlag));
      TimingAspect var10000 = TimingAspect.aspectOf();
      Object[] var15 = new Object[]{this, Conversions.booleanObject(readFlag), var14};
      ProceedingJoinPoint var10001 = (new PrescriberIntegrationModuleImpl$AjcClosure19(var15)).linkClosureAndJoinPoint(69648);
      Annotation var10002 = ajc$anno$9;
      if (var10002 == null) {
         var10002 = ajc$anno$9 = PrescriberIntegrationModuleImpl.class.getDeclaredMethod("listFeedback", Boolean.TYPE).getAnnotation(Profiled.class);
      }

      return (List)var10000.doPerfLogging(var10001, (Profiled)var10002);
   }

   // $FF: synthetic method
   static final void prepareCreatePrescription_aroundBody0(PrescriberIntegrationModuleImpl ajc$this, String patientId, String prescriptionType, JoinPoint var3) {
      ApplicationConfig.getInstance().assertValidSession();
      String cacheId = "(" + patientId + "#" + prescriptionType + ")";
      ajc$this.keyCache.put(cacheId, ajc$this.getNewKeyFromKgss(prescriptionType, StandaloneRequestorProvider.getRequestorIdInformation(), (String)null, patientId, ((EncryptionToken)ajc$this.getEtkHelper().getSystemETK().get(0)).getEncoded()));
   }

   // $FF: synthetic method
   static final String createPrescription_aroundBody2(PrescriberIntegrationModuleImpl ajc$this, boolean feedbackRequested, String patientId, byte[] prescription, String prescriptionType, JoinPoint var5) {
      ApplicationConfig.getInstance().assertValidSession();
      if (StringUtils.isBlank(patientId)) {
         throw new IntegrationModuleException("Patient ID is 0.");
      } else {
         ValidationUtils.validatePatientId(patientId);

         try {
            ajc$this.getKmehrHelper().assertValidKmehrPrescription(prescription, prescriptionType);
            MarshallerHelper helper = new MarshallerHelper(CreatePrescriptionResult.class, CreatePrescriptionParam.class);
            List etkRecipes = ajc$this.getEtkHelper().getRecipe_ETK();
            byte[] message = IOUtils.compress(prescription);
            KeyResult key = ajc$this.getNewKey(patientId, prescriptionType);
            message = ajc$this.sealPrescriptionForUnknown(key, message);
            String requestorIdInformation = StandaloneRequestorProvider.getRequestorIdInformation();
            CreatePrescriptionParam params = new CreatePrescriptionParam();
            params.setPrescription(message);
            params.setPrescriptionType(prescriptionType);
            params.setFeedbackRequested(feedbackRequested);
            params.setKeyId(key.getKeyId());
            params.setSymmKey(ajc$this.getSymmKey().getEncoded());
            params.setPatientId(patientId);
            params.setPrescriberId(requestorIdInformation);
            CreatePrescriptionRequest request = new CreatePrescriptionRequest();
            request.setSecuredCreatePrescriptionRequest(ajc$this.createSecuredContentType(ajc$this.sealRequest((EncryptionToken)etkRecipes.get(0), helper.toXMLByteArray(params))));
            CreatePrescriptionAdministrativeInformationType info = new CreatePrescriptionAdministrativeInformationType();
            request.setAdministrativeInformation(info);
            info.setKeyIdentifier(Base64.decode(key.getKeyId()));
            info.setPrescriptionType(prescriptionType);
            info.setPatientIdentifier(ajc$this.createIdentifierType(patientId, IdentifierTypes.SSIN.name()));
            info.setPrescriberIdentifier(ajc$this.createIdentifierType(requestorIdInformation, IdentifierTypes.NIHII11.name()));
            CreatePrescriptionResponse response = RecipePrescriberServiceImpl.getInstance().createPrescription(request);
            ajc$this.checkStatus(response);
            CreatePrescriptionResult result = (CreatePrescriptionResult)helper.unsealWithSymmKey(response.getSecuredCreatePrescriptionResponse().getSecuredContent(), ajc$this.getSymmKey());
            ajc$this.setPatientId(result.getRid(), patientId);
            return result.getRid();
         } catch (Throwable var25) {
            Exceptionutils.errorHandler(var25);
            return null;
         }
      }
   }

   // $FF: synthetic method
   static final GetPrescriptionForPrescriberResult getPrescription_aroundBody4(PrescriberIntegrationModuleImpl ajc$this, String rid, JoinPoint var2) {
      ApplicationConfig.getInstance().assertValidSession();

      try {
         MarshallerHelper helper = new MarshallerHelper(GetPrescriptionForPrescriberResult.class, GetPrescriptionForPrescriberParam.class);
         List etkRecipes = ajc$this.getEtkHelper().getRecipe_ETK();
         GetPrescriptionForPrescriberParam param = new GetPrescriptionForPrescriberParam();
         param.setRid(rid);
         param.setSymmKey(ajc$this.getSymmKey().getEncoded());
         param.setPrescriberId(StandaloneRequestorProvider.getRequestorIdInformation());
         GetPrescriptionForPrescriberRequest request = new GetPrescriptionForPrescriberRequest();
         request.setSecuredGetPrescriptionForPrescriberRequest(ajc$this.createSecuredContentType(ajc$this.sealRequest((EncryptionToken)etkRecipes.get(0), helper.toXMLByteArray(param))));
         PrescriberServiceAdministrativeInformationType info = new PrescriberServiceAdministrativeInformationType();
         info.setPatientIdentifier(ajc$this.createIdentifierType(ajc$this.getPatientId(rid), IdentifierTypes.SSIN.name()));
         request.setAdministrativeInformation(info);
         GetPrescriptionForPrescriberResponse response = null;

         try {
            response = RecipePrescriberServiceImpl.getInstance().getPrescriptionForPrescriber(request);
         } catch (ClientTransportException var20) {
            throw new IntegrationModuleException(I18nHelper.getLabel("error.connection.prescriber"), var20);
         }

         ajc$this.checkStatus(response);
         GetPrescriptionForPrescriberResult result = (GetPrescriptionForPrescriberResult)helper.unsealWithSymmKey(response.getSecuredGetPrescriptionForPrescriberResponse().getSecuredContent(), ajc$this.getSymmKey());
         KeyResult key = ajc$this.getKeyFromKgss(result.getEncryptionKeyId(), ((EncryptionToken)ajc$this.getEtkHelper().getSystemETK().get(0)).getEncoded());
         byte[] unsealedPrescription = IOUtils.decompress(ajc$this.unsealPrescriptionForUnknown(key, result.getPrescription()));
         result.setPrescription(unsealedPrescription);
         ajc$this.setPatientId(result.getRid(), result.getPatientId());
         return result;
      } catch (Throwable var21) {
         Exceptionutils.errorHandler(var21);
         return null;
      }
   }

   // $FF: synthetic method
   static final void ping_aroundBody6(PrescriberIntegrationModuleImpl ajc$this, JoinPoint var1) {
      AliveCheckResponse response = null;

      try {
         response = RecipePrescriberServiceImpl.getInstance().aliveCheck(new AliveCheckRequest());
      } catch (ClientTransportException var5) {
         throw new IntegrationModuleException(I18nHelper.getLabel("error.connection.prescriber"), var5);
      }

      LOG.info("Ping response : " + response.getAliveCheckResult());
      ajc$this.checkStatus(response);
   }

   // $FF: synthetic method
   static final void revokePrescription_aroundBody8(PrescriberIntegrationModuleImpl ajc$this, String rid, String reason, JoinPoint var3) {
      ApplicationConfig.getInstance().assertValidSession();

      try {
         MarshallerHelper helper = new MarshallerHelper(Object.class, RevokePrescriptionParam.class);
         List etkRecipes = ajc$this.getEtkHelper().getRecipe_ETK();
         RevokePrescriptionParam params = new RevokePrescriptionParam();
         params.setRid(rid);
         params.setReason(reason);
         params.setPrescriberId(StandaloneRequestorProvider.getRequestorIdInformation());
         RevokePrescriptionRequest request = new RevokePrescriptionRequest();
         request.setSecuredRevokePrescriptionRequest(ajc$this.createSecuredContentType(ajc$this.sealRequest((EncryptionToken)etkRecipes.get(0), helper.toXMLByteArray(params))));
         PrescriberServiceAdministrativeInformationType info = new PrescriberServiceAdministrativeInformationType();
         info.setPatientIdentifier(ajc$this.createIdentifierType(ajc$this.getPatientId(rid), IdentifierTypes.SSIN.name()));
         request.setAdministrativeInformation(info);

         try {
            ajc$this.checkStatus(RecipePrescriberServiceImpl.getInstance().revokePrescription(request));
         } catch (ClientTransportException var15) {
            throw new IntegrationModuleException(I18nHelper.getLabel("error.connection.prescriber"), var15);
         }
      } catch (Throwable var16) {
         Exceptionutils.errorHandler(var16);
      }

   }

   // $FF: synthetic method
   static final List listOpenPrescription_aroundBody10(PrescriberIntegrationModuleImpl ajc$this, String patientId, JoinPoint var2) {
      ApplicationConfig.getInstance().assertValidSession();

      try {
         MarshallerHelper helper = new MarshallerHelper(GetListOpenPrescriptionResult.class, GetListOpenPrescriptionParam.class);
         List etkRecipes = ajc$this.getEtkHelper().getRecipe_ETK();
         GetListOpenPrescriptionParam param = new GetListOpenPrescriptionParam();
         param.setSymmKey(ajc$this.getSymmKey().getEncoded());
         param.setPrescriberId(StandaloneRequestorProvider.getRequestorIdInformation());
         param.setPatientId(patientId);
         ListOpenPrescriptionsRequest request = new ListOpenPrescriptionsRequest();
         request.setSecuredListOpenPrescriptionsRequest(ajc$this.createSecuredContentType(ajc$this.sealRequest((EncryptionToken)etkRecipes.get(0), helper.toXMLByteArray(param))));
         ListOpenPrescriptionsResponse response = null;

         try {
            response = RecipePrescriberServiceImpl.getInstance().listOpenPrescriptions(request);
         } catch (ClientTransportException var14) {
            throw new IntegrationModuleException(I18nHelper.getLabel("error.connection.prescriber"), var14);
         }

         ajc$this.checkStatus(response);
         GetListOpenPrescriptionResult result = (GetListOpenPrescriptionResult)helper.unsealWithSymmKey(response.getSecuredListOpenPrescriptionsResponse().getSecuredContent(), ajc$this.getSymmKey());
         return (List)(result.getPrescriptions() == null ? new ArrayList() : result.getPrescriptions());
      } catch (Throwable var15) {
         Exceptionutils.errorHandler(var15);
         return null;
      }
   }

   // $FF: synthetic method
   static final List listOpenPrescription_aroundBody12(PrescriberIntegrationModuleImpl ajc$this, JoinPoint var1) {
      return ajc$this.listOpenPrescription((String)null);
   }

   // $FF: synthetic method
   static final void sendNotification_aroundBody14(PrescriberIntegrationModuleImpl ajc$this, byte[] notificationText, String patientId, String executorId, JoinPoint var4) {
      ApplicationConfig.getInstance().assertValidSession();
      ValidationUtils.validatePatientId(patientId);

      try {
         ajc$this.getKmehrHelper().assertValidNotification(notificationText);
         ValidationUtils.validatePatientId(patientId);
         MarshallerHelper helper = new MarshallerHelper(Object.class, SendNotificationParam.class);
         List etkRecipes = ajc$this.getEtkHelper().getRecipe_ETK();
         List etkRecipients = ajc$this.getEtkHelper().getEtks(KgssIdentifierType.NIHII_PHARMACY, executorId);
         byte[] notificationZip = IOUtils.compress(notificationText);

         for(int i = 0; i < etkRecipients.size(); ++i) {
            EncryptionToken etkRecipient = (EncryptionToken)etkRecipients.get(0);
            byte[] notificationSealed = ajc$this.sealNotification(etkRecipient, notificationZip);
            SendNotificationParam param = new SendNotificationParam();
            param.setContent(notificationSealed);
            param.setExecutorId(executorId);
            param.setPrescriberId(StandaloneRequestorProvider.getRequestorIdInformation());
            param.setPatientId(patientId);
            SendNotificationRequest request = new SendNotificationRequest();
            request.setSecuredSendNotificationRequest(ajc$this.createSecuredContentType(ajc$this.sealRequest((EncryptionToken)etkRecipes.get(0), helper.toXMLByteArray(param))));
            SendNotificationAdministrativeInformationType info = new SendNotificationAdministrativeInformationType();
            info.setExecutorIdentifier(ajc$this.createIdentifierType(executorId, IdentifierTypes.SSIN.name()));
            info.setPatientIdentifier(ajc$this.createIdentifierType(patientId, IdentifierTypes.SSIN.name()));
            request.setAdministrativeInformation(info);

            try {
               ajc$this.checkStatus(RecipePrescriberServiceImpl.getInstance().sendNotification(request));
            } catch (ClientTransportException var26) {
               throw new IntegrationModuleException(I18nHelper.getLabel("error.connection.prescriber"), var26);
            }
         }
      } catch (Throwable var27) {
         Exceptionutils.errorHandler(var27);
      }

   }

   // $FF: synthetic method
   static final void updateFeedbackFlag_aroundBody16(PrescriberIntegrationModuleImpl ajc$this, String rid, boolean feedbackAllowed, JoinPoint var3) {
      ApplicationConfig.getInstance().assertValidSession();

      try {
         MarshallerHelper helper = new MarshallerHelper(Object.class, UpdateFeedbackFlagParam.class);
         List etkRecipes = ajc$this.getEtkHelper().getRecipe_ETK();
         UpdateFeedbackFlagParam param = new UpdateFeedbackFlagParam();
         param.setAllowFeedback(feedbackAllowed);
         param.setRid(rid);
         param.setPrescriberId(StandaloneRequestorProvider.getRequestorIdInformation());
         UpdateFeedbackFlagRequest request = new UpdateFeedbackFlagRequest();
         request.setSecuredUpdateFeedbackFlagRequest(ajc$this.createSecuredContentType(ajc$this.sealRequest((EncryptionToken)etkRecipes.get(0), helper.toXMLByteArray(param))));
         PrescriberServiceAdministrativeInformationType info = new PrescriberServiceAdministrativeInformationType();
         info.setPatientIdentifier(ajc$this.createIdentifierType(ajc$this.getPatientId(rid), IdentifierTypes.SSIN.name()));
         request.setAdministrativeInformation(info);

         try {
            ajc$this.checkStatus(RecipePrescriberServiceImpl.getInstance().updateFeedbackFlag(request));
         } catch (ClientTransportException var15) {
            throw new IntegrationModuleException(I18nHelper.getLabel("error.connection.prescriber"), var15);
         }
      } catch (Throwable var16) {
         Exceptionutils.errorHandler(var16);
      }

   }

   // $FF: synthetic method
   static final List listFeedback_aroundBody18(PrescriberIntegrationModuleImpl ajc$this, boolean readFlag, JoinPoint var2) {
      ApplicationConfig.getInstance().assertValidSession();
      String requestorIdInformation = StandaloneRequestorProvider.getRequestorIdInformation();

      try {
         ajc$this.getEtkHelper().getEtks(KgssIdentifierType.NIHII, requestorIdInformation);
         MarshallerHelper helper = new MarshallerHelper(ListFeedbacksResult.class, ListFeedbacksParam.class);
         List etkRecipes = ajc$this.getEtkHelper().getRecipe_ETK();
         ListFeedbacksParam param = new ListFeedbacksParam();
         param.setReadFlag(readFlag);
         param.setSymmKey(ajc$this.getSymmKey().getEncoded());
         param.setPrescriberId(requestorIdInformation);
         ListFeedbacksRequest request = new ListFeedbacksRequest();
         request.setSecuredListFeedbacksRequest(ajc$this.createSecuredContentType(ajc$this.sealRequest((EncryptionToken)etkRecipes.get(0), helper.toXMLByteArray(param))));
         ListFeedbacksResponse response = null;

         try {
            response = RecipePrescriberServiceImpl.getInstance().listFeedbacks(request);
         } catch (ClientTransportException var25) {
            throw new IntegrationModuleException(I18nHelper.getLabel("error.connection.prescriber"), var25);
         }

         ajc$this.checkStatus(response);
         List feedbacks = ((ListFeedbacksResult)helper.unsealWithSymmKey(response.getSecuredListFeedbacksResponse().getSecuredContent(), ajc$this.getSymmKey())).getFeedbacks();

         for(int i = 0; i < feedbacks.size(); ++i) {
            be.business.connector.recipe.prescriber.domain.ListFeedbackItem item = new be.business.connector.recipe.prescriber.domain.ListFeedbackItem((ListFeedbackItem)feedbacks.get(i));
            byte[] content = item.getContent();

            try {
               content = ajc$this.unsealFeedback(content);
               content = content == null ? content : IOUtils.decompress(content);
               item.setContent(content);
            } catch (Throwable var24) {
               item.setLinkedException(var24);
            }

            feedbacks.set(i, item);
         }

         return feedbacks;
      } catch (Throwable var26) {
         Exceptionutils.errorHandler(var26);
         return null;
      }
   }

   // $FF: synthetic method
   private static void ajc$preClinit() {
      Factory var0 = new Factory("PrescriberIntegrationModuleImpl.java", PrescriberIntegrationModuleImpl.class);
      ajc$tjp_0 = var0.makeSJP("method-execution", var0.makeMethodSig("1", "prepareCreatePrescription", "be.business.connector.recipe.prescriber.PrescriberIntegrationModuleImpl", "java.lang.String:java.lang.String", "patientId:prescriptionType", "be.business.connector.core.exceptions.IntegrationModuleException", "void"), 77);
      ajc$tjp_1 = var0.makeSJP("method-execution", var0.makeMethodSig("1", "createPrescription", "be.business.connector.recipe.prescriber.PrescriberIntegrationModuleImpl", "boolean:java.lang.String:[B:java.lang.String", "feedbackRequested:patientId:prescription:prescriptionType", "be.business.connector.core.exceptions.IntegrationModuleException", "java.lang.String"), 102);
      ajc$tjp_2 = var0.makeSJP("method-execution", var0.makeMethodSig("1", "getPrescription", "be.business.connector.recipe.prescriber.PrescriberIntegrationModuleImpl", "java.lang.String", "rid", "be.business.connector.core.exceptions.IntegrationModuleException", "be.recipe.services.prescriber.GetPrescriptionForPrescriberResult"), 196);
      ajc$tjp_3 = var0.makeSJP("method-execution", var0.makeMethodSig("1", "ping", "be.business.connector.recipe.prescriber.PrescriberIntegrationModuleImpl", "", "", "be.business.connector.core.exceptions.IntegrationModuleException", "void"), 256);
      ajc$tjp_4 = var0.makeSJP("method-execution", var0.makeMethodSig("1", "revokePrescription", "be.business.connector.recipe.prescriber.PrescriberIntegrationModuleImpl", "java.lang.String:java.lang.String", "rid:reason", "be.business.connector.core.exceptions.IntegrationModuleException", "void"), 281);
      ajc$tjp_5 = var0.makeSJP("method-execution", var0.makeMethodSig("1", "listOpenPrescription", "be.business.connector.recipe.prescriber.PrescriberIntegrationModuleImpl", "java.lang.String", "patientId", "be.business.connector.core.exceptions.IntegrationModuleException", "java.util.List"), 333);
      ajc$tjp_6 = var0.makeSJP("method-execution", var0.makeMethodSig("1", "listOpenPrescription", "be.business.connector.recipe.prescriber.PrescriberIntegrationModuleImpl", "", "", "be.business.connector.core.exceptions.IntegrationModuleException", "java.util.List"), 386);
      ajc$tjp_7 = var0.makeSJP("method-execution", var0.makeMethodSig("1", "sendNotification", "be.business.connector.recipe.prescriber.PrescriberIntegrationModuleImpl", "[B:java.lang.String:java.lang.String", "notificationText:patientId:executorId", "be.business.connector.core.exceptions.IntegrationModuleException", "void"), 404);
      ajc$tjp_8 = var0.makeSJP("method-execution", var0.makeMethodSig("1", "updateFeedbackFlag", "be.business.connector.recipe.prescriber.PrescriberIntegrationModuleImpl", "java.lang.String:boolean", "rid:feedbackAllowed", "be.business.connector.core.exceptions.IntegrationModuleException", "void"), 467);
      ajc$tjp_9 = var0.makeSJP("method-execution", var0.makeMethodSig("1", "listFeedback", "be.business.connector.recipe.prescriber.PrescriberIntegrationModuleImpl", "boolean", "readFlag", "be.business.connector.core.exceptions.IntegrationModuleException", "java.util.List"), 507);
   }
}
