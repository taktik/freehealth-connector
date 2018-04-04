package org.taktik.connector.business.recipe.prescriber;

import be.business.connector.common.ApplicationConfig;
import be.business.connector.common.StandaloneRequestorProvider;
import be.business.connector.common.module.AbstractIntegrationModule;
import org.taktik.connector.business.recipeprojects.core.domain.IdentifierTypes;
import org.taktik.connector.business.recipeprojects.core.domain.KgssIdentifierType;
import org.taktik.connector.business.recipeprojects.core.ehealth.services.KgssService;
import org.taktik.connector.business.recipeprojects.core.ehealth.services.KgssServiceImpl;
import org.taktik.connector.business.recipeprojects.core.exceptions.IntegrationModuleException;
import org.taktik.connector.business.recipeprojects.core.technical.connector.utils.Crypto;
import org.taktik.connector.business.recipeprojects.core.utils.EncryptionUtils;
import org.taktik.connector.business.recipeprojects.core.utils.Exceptionutils;
import org.taktik.connector.business.recipeprojects.core.utils.I18nHelper;
import org.taktik.connector.business.recipeprojects.core.utils.IOUtils;
import org.taktik.connector.business.recipeprojects.core.utils.MarshallerHelper;
import org.taktik.connector.business.recipeprojects.core.utils.PropertyHandler;
import org.taktik.connector.business.recipeprojects.core.utils.STSHelper;
import org.taktik.connector.business.recipeprojects.core.utils.SessionValidator;
import org.taktik.connector.business.recipeprojects.common.utils.ValidationUtils;
import org.taktik.connector.business.recipe.prescriber.services.RecipePrescriberServiceImpl;
import org.taktik.connector.business.recipe.utils.KmehrHelper;
import be.ehealth.technicalconnector.service.kgss.domain.KeyResult;
import be.ehealth.technicalconnector.session.Session;
import be.ehealth.technicalconnector.session.SessionItem;
import be.fgov.ehealth.commons.core.v1.IdentifierType;
import be.fgov.ehealth.commons.core.v1.LocalisedString;
import be.fgov.ehealth.commons.core.v1.StatusType;
import be.fgov.ehealth.commons.protocol.v1.ResponseType;
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
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.runtime.internal.Conversions;
import org.aspectj.runtime.reflect.Factory;
import org.bouncycastle.util.encoders.Base64;
import org.perf4j.aop.Profiled;
import org.perf4j.log4j.aop.TimingAspect;

public class PrescriberIntegrationModuleImpl extends AbstractIntegrationModule implements PrescriberIntegrationModule {
   private static final Logger LOG;
   private Map<String, KeyResult> keyCache = new HashMap();
   private Map<String, String> prescriptionCache = new HashMap();
   private KgssService kgssService = KgssServiceImpl.getInstance();
   private KmehrHelper kmehrHelper = new KmehrHelper(PropertyHandler.getInstance().getProperties());
   // $FF: synthetic field
   private static final JoinPoint.StaticPart ajc$tjp_0;
   // $FF: synthetic field
   private static Annotation ajc$anno$0;
   // $FF: synthetic field
   private static final JoinPoint.StaticPart ajc$tjp_1;
   // $FF: synthetic field
   private static Annotation ajc$anno$1;
   // $FF: synthetic field
   private static final JoinPoint.StaticPart ajc$tjp_2;
   // $FF: synthetic field
   private static Annotation ajc$anno$2;
   // $FF: synthetic field
   private static final JoinPoint.StaticPart ajc$tjp_3;
   // $FF: synthetic field
   private static Annotation ajc$anno$3;
   // $FF: synthetic field
   private static final JoinPoint.StaticPart ajc$tjp_4;
   // $FF: synthetic field
   private static Annotation ajc$anno$4;
   // $FF: synthetic field
   private static final JoinPoint.StaticPart ajc$tjp_5;
   // $FF: synthetic field
   private static Annotation ajc$anno$5;
   // $FF: synthetic field
   private static final JoinPoint.StaticPart ajc$tjp_6;
   // $FF: synthetic field
   private static Annotation ajc$anno$6;
   // $FF: synthetic field
   private static final JoinPoint.StaticPart ajc$tjp_7;
   // $FF: synthetic field
   private static Annotation ajc$anno$7;
   // $FF: synthetic field
   private static final JoinPoint.StaticPart ajc$tjp_8;
   // $FF: synthetic field
   private static Annotation ajc$anno$8;
   // $FF: synthetic field
   private static final JoinPoint.StaticPart ajc$tjp_9;
   // $FF: synthetic field
   private static Annotation ajc$anno$9;
   // $FF: synthetic field
   private static final JoinPoint.StaticPart ajc$tjp_10;
   // $FF: synthetic field
   private static Annotation ajc$anno$10;
   // $FF: synthetic field
   private static final JoinPoint.StaticPart ajc$tjp_11;
   // $FF: synthetic field
   private static Annotation ajc$anno$11;
   // $FF: synthetic field
   private static final JoinPoint.StaticPart ajc$tjp_12;
   // $FF: synthetic field
   private static Annotation ajc$anno$12;
   // $FF: synthetic field
   private static final JoinPoint.StaticPart ajc$tjp_13;
   // $FF: synthetic field
   private static Annotation ajc$anno$13;

   static {
      ajc$preClinit();
      LOG = Logger.getLogger(PrescriberIntegrationModuleImpl.class);
   }

   public PrescriberIntegrationModuleImpl() throws IntegrationModuleException {
      LOG.info("*************** Prescriber System module init correctly *******************");
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
      if (ajc$anno$0 == null) {
         var10002 = ajc$anno$0 = PrescriberIntegrationModuleImpl.class.getDeclaredMethod("prepareCreatePrescription", String.class, String.class).getAnnotation(Profiled.class);
      }

      var10000.doPerfLogging(var10001, (Profiled)var10002);
   }

   private KeyResult getNewKey(String patientId, String prescriptionType) throws IntegrationModuleException {
      KeyResult key = null;
      String cacheId = "(" + patientId + "#" + prescriptionType + ")";
      if (this.keyCache.containsKey(cacheId)) {
         key = (KeyResult)this.keyCache.get(cacheId);
         this.keyCache.remove(cacheId);
      } else {
         key = this.getNewKeyFromKgss(prescriptionType, StandaloneRequestorProvider.getRequestorIdInformation(), (String)null, patientId, ((EncryptionToken)this.getEtkHelper().getSystemETK().get(0)).getEncoded());
      }

      return key;
   }

   @Profiled(
      logFailuresSeparately = true,
      tag = "PrescriberIntegrationModule#ping"
   )
   public void ping() throws IntegrationModuleException {
      JoinPoint var3 = Factory.makeJP(ajc$tjp_1, this, this);
      TimingAspect var10000 = TimingAspect.aspectOf();
      Object[] var4 = new Object[]{this, var3};
      ProceedingJoinPoint var10001 = (new PrescriberIntegrationModuleImpl$AjcClosure3(var4)).linkClosureAndJoinPoint(69648);
      Annotation var10002 = ajc$anno$1;
      if (ajc$anno$1 == null) {
         var10002 = ajc$anno$1 = PrescriberIntegrationModuleImpl.class.getDeclaredMethod("ping").getAnnotation(Profiled.class);
      }

      var10000.doPerfLogging(var10001, (Profiled)var10002);
   }

   @Profiled(
      logFailuresSeparately = true,
      tag = "0.PrescriberIntegrationModule#createPrescription"
   )
   public String createPrescription(boolean feedbackRequested, String patientId, byte[] prescription, String prescriptionType) throws IntegrationModuleException {
      JoinPoint.StaticPart var10000 = ajc$tjp_2;
      Object[] var21 = new Object[]{Conversions.booleanObject(feedbackRequested), patientId, prescription, prescriptionType};
      JoinPoint var20 = Factory.makeJP(var10000, this, this, (Object[])var21);
      TimingAspect var23 = TimingAspect.aspectOf();
      Object[] var22 = new Object[]{this, Conversions.booleanObject(feedbackRequested), patientId, prescription, prescriptionType, var20};
      ProceedingJoinPoint var10001 = (new PrescriberIntegrationModuleImpl$AjcClosure5(var22)).linkClosureAndJoinPoint(69648);
      Annotation var10002 = ajc$anno$2;
      if (ajc$anno$2 == null) {
         var10002 = ajc$anno$2 = PrescriberIntegrationModuleImpl.class.getDeclaredMethod("createPrescription", Boolean.TYPE, String.class, byte[].class, String.class).getAnnotation(Profiled.class);
      }

      return (String)var23.doPerfLogging(var10001, (Profiled)var10002);
   }

   @Profiled(
      logFailuresSeparately = true,
      tag = "0.PrescriberIntegrationModule#revokePrescription"
   )
   public void revokePrescription(String rid, String reason) throws IntegrationModuleException {
      JoinPoint var11 = Factory.makeJP(ajc$tjp_3, this, this, rid, reason);
      TimingAspect var10000 = TimingAspect.aspectOf();
      Object[] var12 = new Object[]{this, rid, reason, var11};
      ProceedingJoinPoint var10001 = (new PrescriberIntegrationModuleImpl$AjcClosure7(var12)).linkClosureAndJoinPoint(69648);
      Annotation var10002 = ajc$anno$3;
      if (ajc$anno$3 == null) {
         var10002 = ajc$anno$3 = PrescriberIntegrationModuleImpl.class.getDeclaredMethod("revokePrescription", String.class, String.class).getAnnotation(Profiled.class);
      }

      var10000.doPerfLogging(var10001, (Profiled)var10002);
   }

   @Profiled(
      logFailuresSeparately = true,
      tag = "0.PrescriberIntegrationModule#getPrescription"
   )
   public GetPrescriptionForPrescriberResult getPrescription(String rid) throws IntegrationModuleException {
      JoinPoint var12 = Factory.makeJP(ajc$tjp_4, this, this, (Object)rid);
      TimingAspect var10000 = TimingAspect.aspectOf();
      Object[] var13 = new Object[]{this, rid, var12};
      ProceedingJoinPoint var10001 = (new PrescriberIntegrationModuleImpl$AjcClosure9(var13)).linkClosureAndJoinPoint(69648);
      Annotation var10002 = ajc$anno$4;
      if (ajc$anno$4 == null) {
         var10002 = ajc$anno$4 = PrescriberIntegrationModuleImpl.class.getDeclaredMethod("getPrescription", String.class).getAnnotation(Profiled.class);
      }

      return (GetPrescriptionForPrescriberResult)var10000.doPerfLogging(var10001, (Profiled)var10002);
   }

   @Profiled(
      logFailuresSeparately = true,
      tag = "0.PrescriberIntegrationModule#listOpenPrescription"
   )
   public List<String> listOpenPrescription(String patientId) throws IntegrationModuleException {
      JoinPoint var9 = Factory.makeJP(ajc$tjp_5, this, this, (Object)patientId);
      TimingAspect var10000 = TimingAspect.aspectOf();
      Object[] var10 = new Object[]{this, patientId, var9};
      ProceedingJoinPoint var10001 = (new PrescriberIntegrationModuleImpl$AjcClosure11(var10)).linkClosureAndJoinPoint(69648);
      Annotation var10002 = ajc$anno$5;
      if (ajc$anno$5 == null) {
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
      if (ajc$anno$6 == null) {
         var10002 = ajc$anno$6 = PrescriberIntegrationModuleImpl.class.getDeclaredMethod("listOpenPrescription").getAnnotation(Profiled.class);
      }

      return (List)var10000.doPerfLogging(var10001, (Profiled)var10002);
   }

   @Profiled(
      logFailuresSeparately = true,
      tag = "PrescriberIntegrationModule#sendNotification"
   )
   public void sendNotification(byte[] notificationText, String patientId, String executorId) throws IntegrationModuleException {
      JoinPoint.StaticPart var10000 = ajc$tjp_7;
      Object[] var19 = new Object[]{notificationText, patientId, executorId};
      JoinPoint var18 = Factory.makeJP(var10000, this, this, (Object[])var19);
      TimingAspect var21 = TimingAspect.aspectOf();
      Object[] var20 = new Object[]{this, notificationText, patientId, executorId, var18};
      ProceedingJoinPoint var10001 = (new PrescriberIntegrationModuleImpl$AjcClosure15(var20)).linkClosureAndJoinPoint(69648);
      Annotation var10002 = ajc$anno$7;
      if (ajc$anno$7 == null) {
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
      if (ajc$anno$8 == null) {
         var10002 = ajc$anno$8 = PrescriberIntegrationModuleImpl.class.getDeclaredMethod("updateFeedbackFlag", String.class, Boolean.TYPE).getAnnotation(Profiled.class);
      }

      var10000.doPerfLogging(var10001, (Profiled)var10002);
   }

   @Profiled(
      logFailuresSeparately = true,
      tag = "0.PrescriberIntegrationModule#listFeedback"
   )
   public List<ListFeedbackItem> listFeedback(boolean readFlag) throws IntegrationModuleException {
      JoinPoint var15 = Factory.makeJP(ajc$tjp_9, this, this, (Object)Conversions.booleanObject(readFlag));
      TimingAspect var10000 = TimingAspect.aspectOf();
      Object[] var16 = new Object[]{this, Conversions.booleanObject(readFlag), var15};
      ProceedingJoinPoint var10001 = (new PrescriberIntegrationModuleImpl$AjcClosure19(var16)).linkClosureAndJoinPoint(69648);
      Annotation var10002 = ajc$anno$9;
      if (ajc$anno$9 == null) {
         var10002 = ajc$anno$9 = PrescriberIntegrationModuleImpl.class.getDeclaredMethod("listFeedback", Boolean.TYPE).getAnnotation(Profiled.class);
      }

      return (List)var10000.doPerfLogging(var10001, (Profiled)var10002);
   }

   private SecuredContentType createSecuredContentType(byte[] content) {
      SecuredContentType secured = new SecuredContentType();
      secured.setSecuredContent(content);
      return secured;
   }

   private void checkStatus(ResponseType response) throws IntegrationModuleException {
      if (!"100".equals(response.getStatus().getCode()) && !"200".equals(response.getStatus().getCode())) {
         LOG.error("Error Status received : " + response.getStatus().getCode());
         throw new IntegrationModuleException(this.getLocalisedMsg(response.getStatus()));
      }
   }

   private String getLocalisedMsg(StatusType status) {
      String locale = IntegrationModuleException.getUserLocale();
      Iterator var4 = status.getMessages().iterator();

      LocalisedString msg;
      do {
         if (!var4.hasNext()) {
            if (status.getMessages().size() > 0) {
               return ((LocalisedString)status.getMessages().get(0)).getValue();
            }

            return status.getCode();
         }

         msg = (LocalisedString)var4.next();
      } while(msg.getLang() == null || !locale.equalsIgnoreCase(msg.getLang().value()));

      return msg.getValue();
   }

   private IdentifierType createIdentifierType(String id, String type) {
      IdentifierType ident = new IdentifierType();
      ident.setId(id);
      ident.setType(type);
      return ident;
   }

   private String getPatientId(String rid) {
      return this.prescriptionCache.containsKey(rid) ? (String)this.prescriptionCache.get(rid) : "72081061175";
   }

   private void setPatientId(String rid, String patientId) {
      this.prescriptionCache.put(rid, patientId);
   }

   public void setPersonalPassword(String personalPassword) throws IntegrationModuleException {
      SessionItem sessionItem = Session.getInstance().getSession();
      SessionValidator.assertValidSession(sessionItem);

      try {
         String niss = STSHelper.getNiss(sessionItem.getSAMLToken().getAssertion());
         EncryptionUtils encryptionUtils = EncryptionUtils.getInstance();
         encryptionUtils.unlockPersonalKey(niss, personalPassword);
         this.dataUnsealer = encryptionUtils.initUnsealing();
         List<EncryptionToken> tokens = this.getEtkHelper().getEtks(KgssIdentifierType.NIHII, StandaloneRequestorProvider.getRequestorIdInformation());
         encryptionUtils.verifyDecryption((EncryptionToken)tokens.get(0));
      } catch (Exception var6) {
         throw new IntegrationModuleException(var6);
      }
   }

   @Profiled(
      logFailuresSeparately = true,
      tag = "0.PrescriberIntegrationModule#unsealFeedback"
   )
   protected byte[] unsealFeedback(byte[] message) throws IntegrationModuleException {
      JoinPoint var3 = Factory.makeJP(ajc$tjp_10, this, this, (Object)message);
      TimingAspect var10000 = TimingAspect.aspectOf();
      Object[] var4 = new Object[]{this, message, var3};
      ProceedingJoinPoint var10001 = (new PrescriberIntegrationModuleImpl$AjcClosure21(var4)).linkClosureAndJoinPoint(69648);
      Annotation var10002 = ajc$anno$10;
      if (ajc$anno$10 == null) {
         var10002 = ajc$anno$10 = PrescriberIntegrationModuleImpl.class.getDeclaredMethod("unsealFeedback", byte[].class).getAnnotation(Profiled.class);
      }

      return (byte[])var10000.doPerfLogging(var10001, (Profiled)var10002);
   }

   @Profiled(
      logFailuresSeparately = true,
      tag = "0.PrescriberIntegrationModule#getNewKeyFromKgss"
   )
   protected KeyResult getNewKeyFromKgss(String prescriptionType, String prescriberId, String executorId, String patientId, byte[] myEtk) throws IntegrationModuleException {
      JoinPoint.StaticPart var10000 = ajc$tjp_11;
      Object[] var16 = new Object[]{prescriptionType, prescriberId, executorId, patientId, myEtk};
      JoinPoint var15 = Factory.makeJP(var10000, this, this, (Object[])var16);
      TimingAspect var18 = TimingAspect.aspectOf();
      Object[] var17 = new Object[]{this, prescriptionType, prescriberId, executorId, patientId, myEtk, var15};
      ProceedingJoinPoint var10001 = (new PrescriberIntegrationModuleImpl$AjcClosure23(var17)).linkClosureAndJoinPoint(69648);
      Annotation var10002 = ajc$anno$11;
      if (ajc$anno$11 == null) {
         var10002 = ajc$anno$11 = PrescriberIntegrationModuleImpl.class.getDeclaredMethod("getNewKeyFromKgss", String.class, String.class, String.class, String.class, byte[].class).getAnnotation(Profiled.class);
      }

      return (KeyResult)var18.doPerfLogging(var10001, (Profiled)var10002);
   }

   @Profiled(
      logFailuresSeparately = true,
      tag = "0.PrescriberIntegrationModule#sealNotification"
   )
   protected byte[] sealNotification(EncryptionToken paramEncryptionToken, byte[] paramArrayOfByte) throws IntegrationModuleException {
      JoinPoint var5 = Factory.makeJP(ajc$tjp_12, this, this, paramEncryptionToken, paramArrayOfByte);
      TimingAspect var10000 = TimingAspect.aspectOf();
      Object[] var6 = new Object[]{this, paramEncryptionToken, paramArrayOfByte, var5};
      ProceedingJoinPoint var10001 = (new PrescriberIntegrationModuleImpl$AjcClosure25(var6)).linkClosureAndJoinPoint(69648);
      Annotation var10002 = ajc$anno$12;
      if (ajc$anno$12 == null) {
         var10002 = ajc$anno$12 = PrescriberIntegrationModuleImpl.class.getDeclaredMethod("sealNotification", EncryptionToken.class, byte[].class).getAnnotation(Profiled.class);
      }

      return (byte[])var10000.doPerfLogging(var10001, (Profiled)var10002);
   }

   @Profiled(
      logFailuresSeparately = true,
      tag = "0.PrescriberIntegrationModule#sealPrescriptionForUnknown"
   )
   protected byte[] sealPrescriptionForUnknown(KeyResult key, byte[] messageToProtect) throws IntegrationModuleException {
      JoinPoint var5 = Factory.makeJP(ajc$tjp_13, this, this, key, messageToProtect);
      TimingAspect var10000 = TimingAspect.aspectOf();
      Object[] var6 = new Object[]{this, key, messageToProtect, var5};
      ProceedingJoinPoint var10001 = (new PrescriberIntegrationModuleImpl$AjcClosure27(var6)).linkClosureAndJoinPoint(69648);
      Annotation var10002 = ajc$anno$13;
      if (ajc$anno$13 == null) {
         var10002 = ajc$anno$13 = PrescriberIntegrationModuleImpl.class.getDeclaredMethod("sealPrescriptionForUnknown", KeyResult.class, byte[].class).getAnnotation(Profiled.class);
      }

      return (byte[])var10000.doPerfLogging(var10001, (Profiled)var10002);
   }

   private KmehrHelper getKmehrHelper() {
      return this.kmehrHelper;
   }

   // $FF: synthetic method
   static final void prepareCreatePrescription_aroundBody0(PrescriberIntegrationModuleImpl ajc$this, String patientId, String prescriptionType, JoinPoint var3) {
      ApplicationConfig.getInstance().assertValidSession();
      String cacheId = "(" + patientId + "#" + prescriptionType + ")";
      ajc$this.keyCache.put(cacheId, ajc$this.getNewKeyFromKgss(prescriptionType, StandaloneRequestorProvider.getRequestorIdInformation(), (String)null, patientId, ((EncryptionToken)ajc$this.getEtkHelper().getSystemETK().get(0)).getEncoded()));
   }

   // $FF: synthetic method
   static final void ping_aroundBody2(PrescriberIntegrationModuleImpl ajc$this, JoinPoint var1) {
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
   static final String createPrescription_aroundBody4(PrescriberIntegrationModuleImpl ajc$this, boolean feedbackRequested, String patientId, byte[] prescription, String prescriptionType, JoinPoint var5) {
      ApplicationConfig.getInstance().assertValidSession();
      if (StringUtils.isBlank(patientId)) {
         throw new IntegrationModuleException("Patient ID is 0.");
      } else {
         String pid = patientId;
         ValidationUtils.validatePatientId(patientId);

         try {
            ajc$this.getKmehrHelper().assertValidKmehrPrescription(prescription, prescriptionType);
            MarshallerHelper helper = new MarshallerHelper(CreatePrescriptionResult.class, CreatePrescriptionParam.class);
            List etkRecipes = ajc$this.getEtkHelper().getRecipe_ETK();
            byte[] message = IOUtils.compress(prescription);
            KeyResult key = ajc$this.getNewKey(pid, prescriptionType);
            message = ajc$this.sealPrescriptionForUnknown(key, message);
            String requestorIdInformation = StandaloneRequestorProvider.getRequestorIdInformation();
            CreatePrescriptionParam params = new CreatePrescriptionParam();
            params.setPatientId(pid);
            params.setFeedbackRequested(feedbackRequested);
            params.setPrescription(message);
            params.setPrescriptionType(prescriptionType);
            params.setSymmKey(ajc$this.getSymmKey().getEncoded());
            params.setKeyId(key.getKeyId());
            params.setPrescriberId(requestorIdInformation);
            CreatePrescriptionRequest request = new CreatePrescriptionRequest();
            request.setSecuredCreatePrescriptionRequest(ajc$this.createSecuredContentType(ajc$this.sealRequest((EncryptionToken)etkRecipes.get(0), helper.toXMLByteArray(params))));
            CreatePrescriptionAdministrativeInformationType info = new CreatePrescriptionAdministrativeInformationType();
            request.setAdministrativeInformation(info);
            info.setKeyIdentifier(Base64.decode(key.getKeyId()));
            info.setPrescriptionType(prescriptionType);
            info.setPatientIdentifier(ajc$this.createIdentifierType(pid, IdentifierTypes.SSIN.name()));
            info.setPrescriberIdentifier(ajc$this.createIdentifierType(requestorIdInformation, IdentifierTypes.NIHII11.name()));
            CreatePrescriptionResponse response = RecipePrescriberServiceImpl.getInstance().createPrescription(request);
            ajc$this.checkStatus(response);
            CreatePrescriptionResult result = (CreatePrescriptionResult)helper.unsealWithSymmKey(response.getSecuredCreatePrescriptionResponse().getSecuredContent(), ajc$this.getSymmKey());
            ajc$this.setPatientId(result.getRid(), pid);
            return result.getRid();
         } catch (Throwable var27) {
            Exceptionutils.errorHandler(var27);
            return null;
         }
      }
   }

   // $FF: synthetic method
   static final void revokePrescription_aroundBody6(PrescriberIntegrationModuleImpl ajc$this, String rid, String reason, JoinPoint var3) {
      ajc$this.validateRid(rid);
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
   static final GetPrescriptionForPrescriberResult getPrescription_aroundBody8(PrescriberIntegrationModuleImpl ajc$this, String rid, JoinPoint var2) {
      ajc$this.validateRid(rid);
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
      ajc$this.validateRid(rid);
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
         List personalETKs = ajc$this.getEtkHelper().getEtks(KgssIdentifierType.NIHII, requestorIdInformation);
         ajc$this.getEncryptionUtils().verifyDecryption((EncryptionToken)personalETKs.get(0));
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
         } catch (ClientTransportException var27) {
            throw new IntegrationModuleException(I18nHelper.getLabel("error.connection.prescriber"), var27);
         }

         ajc$this.checkStatus(response);
         List feedbacks = ((ListFeedbacksResult)helper.unsealWithSymmKey(response.getSecuredListFeedbacksResponse().getSecuredContent(), ajc$this.getSymmKey())).getFeedbacks();

         for(int i = 0; i < feedbacks.size(); ++i) {
            org.taktik.connector.business.recipe.prescriber.domain.ListFeedbackItem item = new org.taktik.connector.business.recipe.prescriber.domain.ListFeedbackItem((ListFeedbackItem)feedbacks.get(i));
            byte[] content = item.getContent();

            try {
               content = ajc$this.unsealFeedback(content);
               content = content == null ? content : IOUtils.decompress(content);
               item.setContent(content);
            } catch (Throwable var26) {
               item.setLinkedException(var26);
            }

            feedbacks.set(i, item);
         }

         return feedbacks;
      } catch (Throwable var28) {
         Exceptionutils.errorHandler(var28);
         return null;
      }
   }

   // $FF: synthetic method
   static final byte[] unsealFeedback_aroundBody20(PrescriberIntegrationModuleImpl ajc$this, byte[] message, JoinPoint var2) {
      return ajc$this.unsealNotiffeed(message);
   }

   // $FF: synthetic method
   static final KeyResult getNewKeyFromKgss_aroundBody22(PrescriberIntegrationModuleImpl ajc$this, String prescriptionType, String prescriberId, String executorId, String patientId, byte[] myEtk, JoinPoint var6) {
      if (ajc$this.getPropertyHandler().hasProperty("test_kgss_key")) {
         return ajc$this.getKeyFromKgss((String)null, (byte[])null);
      } else {
         EncryptionToken etkKgss = (EncryptionToken)ajc$this.getEtkHelper().getKGSS_ETK().get(0);
         List credentialTypes = ajc$this.getPropertyHandler().getMatchingProperties("kgss.createPrescription.ACL." + prescriptionType);
         KeyResult keyResult = null;

         try {
            keyResult = ajc$this.kgssService.retrieveNewKey(etkKgss.getEncoded(), credentialTypes, prescriberId, executorId, patientId, myEtk);
         } catch (Throwable var14) {
            Exceptionutils.errorHandler(var14);
         }

         return keyResult;
      }
   }

   // $FF: synthetic method
   static final byte[] sealNotification_aroundBody24(PrescriberIntegrationModuleImpl ajc$this, EncryptionToken paramEncryptionToken, byte[] paramArrayOfByte, JoinPoint var3) {
      return Crypto.seal(paramEncryptionToken, paramArrayOfByte);
   }

   // $FF: synthetic method
   static final byte[] sealPrescriptionForUnknown_aroundBody26(PrescriberIntegrationModuleImpl ajc$this, KeyResult key, byte[] messageToProtect, JoinPoint var3) {
      return Crypto.seal(messageToProtect, key.getSecretKey(), key.getKeyId());
   }

   // $FF: synthetic method
   private static void ajc$preClinit() {
      Factory var0 = new Factory("PrescriberIntegrationModuleImpl.java", PrescriberIntegrationModuleImpl.class);
      ajc$tjp_0 = var0.makeSJP("method-execution", var0.makeMethodSig("1", "prepareCreatePrescription", "be.business.connector.recipe.prescriber.PrescriberIntegrationModuleImpl", "java.lang.String:java.lang.String", "patientId:prescriptionType", "be.business.connector.core.exceptions.IntegrationModuleException", "void"), 68);
      ajc$tjp_1 = var0.makeSJP("method-execution", var0.makeMethodSig("1", "ping", "be.business.connector.recipe.prescriber.PrescriberIntegrationModuleImpl", "", "", "be.business.connector.core.exceptions.IntegrationModuleException", "void"), 97);
      ajc$tjp_10 = var0.makeSJP("method-execution", var0.makeMethodSig("4", "unsealFeedback", "be.business.connector.recipe.prescriber.PrescriberIntegrationModuleImpl", "[B", "message", "be.business.connector.core.exceptions.IntegrationModuleException", "[B"), 594);
      ajc$tjp_11 = var0.makeSJP("method-execution", var0.makeMethodSig("4", "getNewKeyFromKgss", "be.business.connector.recipe.prescriber.PrescriberIntegrationModuleImpl", "java.lang.String:java.lang.String:java.lang.String:java.lang.String:[B", "prescriptionType:prescriberId:executorId:patientId:myEtk", "be.business.connector.core.exceptions.IntegrationModuleException", "be.ehealth.technicalconnector.service.kgss.domain.KeyResult"), 599);
      ajc$tjp_12 = var0.makeSJP("method-execution", var0.makeMethodSig("4", "sealNotification", "be.business.connector.recipe.prescriber.PrescriberIntegrationModuleImpl", "be.fgov.ehealth.etee.crypto.encrypt.EncryptionToken:[B", "paramEncryptionToken:paramArrayOfByte", "be.business.connector.core.exceptions.IntegrationModuleException", "[B"), 618);
      ajc$tjp_13 = var0.makeSJP("method-execution", var0.makeMethodSig("4", "sealPrescriptionForUnknown", "be.business.connector.recipe.prescriber.PrescriberIntegrationModuleImpl", "be.ehealth.technicalconnector.service.kgss.domain.KeyResult:[B", "key:messageToProtect", "be.business.connector.core.exceptions.IntegrationModuleException", "[B"), 623);
      ajc$tjp_2 = var0.makeSJP("method-execution", var0.makeMethodSig("1", "createPrescription", "be.business.connector.recipe.prescriber.PrescriberIntegrationModuleImpl", "boolean:java.lang.String:[B:java.lang.String", "feedbackRequested:patientId:prescription:prescriptionType", "be.business.connector.core.exceptions.IntegrationModuleException", "java.lang.String"), 122);
      ajc$tjp_3 = var0.makeSJP("method-execution", var0.makeMethodSig("1", "revokePrescription", "be.business.connector.recipe.prescriber.PrescriberIntegrationModuleImpl", "java.lang.String:java.lang.String", "rid:reason", "be.business.connector.core.exceptions.IntegrationModuleException", "void"), 200);
      ajc$tjp_4 = var0.makeSJP("method-execution", var0.makeMethodSig("1", "getPrescription", "be.business.connector.recipe.prescriber.PrescriberIntegrationModuleImpl", "java.lang.String", "rid", "be.business.connector.core.exceptions.IntegrationModuleException", "be.recipe.services.prescriber.GetPrescriptionForPrescriberResult"), 249);
      ajc$tjp_5 = var0.makeSJP("method-execution", var0.makeMethodSig("1", "listOpenPrescription", "be.business.connector.recipe.prescriber.PrescriberIntegrationModuleImpl", "java.lang.String", "patientId", "be.business.connector.core.exceptions.IntegrationModuleException", "java.util.List"), 313);
      ajc$tjp_6 = var0.makeSJP("method-execution", var0.makeMethodSig("1", "listOpenPrescription", "be.business.connector.recipe.prescriber.PrescriberIntegrationModuleImpl", "", "", "be.business.connector.core.exceptions.IntegrationModuleException", "java.util.List"), 362);
      ajc$tjp_7 = var0.makeSJP("method-execution", var0.makeMethodSig("1", "sendNotification", "be.business.connector.recipe.prescriber.PrescriberIntegrationModuleImpl", "[B:java.lang.String:java.lang.String", "notificationText:patientId:executorId", "be.business.connector.core.exceptions.IntegrationModuleException", "void"), 376);
      ajc$tjp_8 = var0.makeSJP("method-execution", var0.makeMethodSig("1", "updateFeedbackFlag", "be.business.connector.recipe.prescriber.PrescriberIntegrationModuleImpl", "java.lang.String:boolean", "rid:feedbackAllowed", "be.business.connector.core.exceptions.IntegrationModuleException", "void"), 434);
      ajc$tjp_9 = var0.makeSJP("method-execution", var0.makeMethodSig("1", "listFeedback", "be.business.connector.recipe.prescriber.PrescriberIntegrationModuleImpl", "boolean", "readFlag", "be.business.connector.core.exceptions.IntegrationModuleException", "java.util.List"), 473);
   }
}
