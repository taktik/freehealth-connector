package be.business.connector.recipe.executor;

import be.business.connector.common.ApplicationConfig;
import be.business.connector.common.StandaloneRequestorProvider;
import be.business.connector.core.domain.KgssIdentifierType;
import be.business.connector.core.exceptions.IntegrationModuleException;
import be.business.connector.core.handlers.InsurabilityHandler;
import be.business.connector.core.utils.Exceptionutils;
import be.business.connector.core.utils.I18nHelper;
import be.business.connector.core.utils.IOUtils;
import be.business.connector.core.utils.MarshallerHelper;
import be.business.connector.core.utils.PropertyHandler;
import be.business.connector.projects.common.utils.ValidationUtils;
import be.business.connector.recipe.executor.domain.GetPrescriptionForExecutorResult;
import be.business.connector.recipe.executor.services.RecipeExecutorServiceDevV4Impl;
import be.business.connector.recipe.utils.RidValidator;
import be.ehealth.technicalconnector.service.kgss.domain.KeyResult;
import be.fgov.ehealth.etee.crypto.encrypt.EncryptionToken;
import be.recipe.services.core.Page;
import be.recipe.services.executor.CreateFeedback;
import be.recipe.services.executor.CreateFeedbackResponse;
import be.recipe.services.executor.CreateFeedbackResult;
import be.recipe.services.executor.GetOpenPrescriptionForExecutor;
import be.recipe.services.executor.GetPrescriptionForExecutor;
import be.recipe.services.executor.GetPrescriptionForExecutorResponse;
import be.recipe.services.executor.GetPrescriptionForExecutorResultSealed;
import be.recipe.services.executor.GetPrescriptionStatus;
import be.recipe.services.executor.GetPrescriptionStatusParam;
import be.recipe.services.executor.GetPrescriptionStatusResponse;
import be.recipe.services.executor.GetPrescriptionStatusResult;
import be.recipe.services.executor.ListNotifications;
import be.recipe.services.executor.ListNotificationsItem;
import be.recipe.services.executor.ListNotificationsResponse;
import be.recipe.services.executor.ListNotificationsResult;
import be.recipe.services.executor.ListOpenPrescriptions;
import be.recipe.services.executor.ListOpenPrescriptionsParam;
import be.recipe.services.executor.ListOpenPrescriptionsResponse;
import be.recipe.services.executor.ListOpenPrescriptionsResult;
import be.recipe.services.executor.ListRelations;
import be.recipe.services.executor.ListRelationsParam;
import be.recipe.services.executor.ListRelationsResponse;
import be.recipe.services.executor.ListRelationsResult;
import be.recipe.services.executor.ListReservations;
import be.recipe.services.executor.ListReservationsParam;
import be.recipe.services.executor.ListReservationsResponse;
import be.recipe.services.executor.ListReservationsResult;
import be.recipe.services.executor.ListRidsHistory;
import be.recipe.services.executor.ListRidsHistoryParam;
import be.recipe.services.executor.ListRidsHistoryResponse;
import be.recipe.services.executor.ListRidsHistoryResult;
import be.recipe.services.executor.ListRidsInProcess;
import be.recipe.services.executor.ListRidsInProcessParam;
import be.recipe.services.executor.ListRidsInProcessResponse;
import be.recipe.services.executor.ListRidsInProcessResult;
import be.recipe.services.executor.MarkAsArchived;
import be.recipe.services.executor.MarkAsArchivedResponse;
import be.recipe.services.executor.MarkAsArchivedResult;
import be.recipe.services.executor.MarkAsDelivered;
import be.recipe.services.executor.MarkAsDeliveredResponse;
import be.recipe.services.executor.MarkAsDeliveredResult;
import be.recipe.services.executor.MarkAsUnDelivered;
import be.recipe.services.executor.MarkAsUnDeliveredResponse;
import be.recipe.services.executor.MarkAsUndeliveredResult;
import be.recipe.services.executor.PutRidsInProcess;
import be.recipe.services.executor.PutRidsInProcessParam;
import be.recipe.services.executor.PutRidsInProcessResponse;
import be.recipe.services.executor.PutRidsInProcessResult;
import be.recipe.services.executor.RevokePrescription;
import be.recipe.services.executor.RevokePrescriptionResponse;
import be.recipe.services.executor.RevokePrescriptionResult;
import com.sun.xml.ws.client.ClientTransportException;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.math.BigInteger;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.apache.commons.collections.keyvalue.MultiKey;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.JoinPoint.StaticPart;
import org.aspectj.runtime.internal.Conversions;
import org.aspectj.runtime.reflect.Factory;
import org.perf4j.aop.Profiled;
import org.perf4j.log4j.aop.TimingAspect;

public class ExecutorIntegrationModuleDevV4Impl extends ExecutorIntegrationModuleImpl implements ExecutorIntegrationModuleDevV4 {
   private Map<MultiKey, byte[]> sessionMap = new HashMap();
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
   // $FF: synthetic field
   private static final StaticPart ajc$tjp_10;
   // $FF: synthetic field
   private static Annotation ajc$anno$10;
   // $FF: synthetic field
   private static final StaticPart ajc$tjp_11;
   // $FF: synthetic field
   private static Annotation ajc$anno$11;
   // $FF: synthetic field
   private static final StaticPart ajc$tjp_12;
   // $FF: synthetic field
   private static Annotation ajc$anno$12;
   // $FF: synthetic field
   private static final StaticPart ajc$tjp_13;
   // $FF: synthetic field
   private static Annotation ajc$anno$13;
   // $FF: synthetic field
   private static final StaticPart ajc$tjp_14;
   // $FF: synthetic field
   private static Annotation ajc$anno$14;

   public ExecutorIntegrationModuleDevV4Impl() throws IntegrationModuleException {
   }

   @Profiled(
      logFailuresSeparately = true,
      tag = "ExecutorIntegrationModuleV4#getData(GetOpenPrescriptionListParam)"
   )
   public ListOpenPrescriptionsResult getData(ListOpenPrescriptionsParam param) throws IntegrationModuleException {
      JoinPoint var6 = Factory.makeJP(ajc$tjp_0, this, this, param);
      TimingAspect var10000 = TimingAspect.aspectOf();
      Object[] var7 = new Object[]{this, param, var6};
      ProceedingJoinPoint var10001 = (new ExecutorIntegrationModuleDevV4Impl$AjcClosure1(var7)).linkClosureAndJoinPoint(69648);
      Annotation var10002 = ajc$anno$0;
      if (var10002 == null) {
         var10002 = ajc$anno$0 = ExecutorIntegrationModuleDevV4Impl.class.getDeclaredMethod("getData", ListOpenPrescriptionsParam.class).getAnnotation(Profiled.class);
      }

      return (ListOpenPrescriptionsResult)var10000.doPerfLogging(var10001, (Profiled)var10002);
   }

   private ListOpenPrescriptions getOpenPrescriptionList(ListOpenPrescriptionsParam listOpenPrescriptionsParam) throws IntegrationModuleException {
      listOpenPrescriptionsParam.setSession((byte[])this.sessionMap.get(new MultiKey(listOpenPrescriptionsParam.getExecutorId(), listOpenPrescriptionsParam.getPatientId())));
      ListOpenPrescriptions request = new ListOpenPrescriptions();
      request.setListOpenPrescriptionsParamSealed(this.getSealedData(listOpenPrescriptionsParam));
      request.setProgramIdentification(PropertyHandler.getInstance().getProperty("programIdentification"));
      request.setMguid(UUID.randomUUID().toString());
      return request;
   }

   private byte[] getSealedData(ListOpenPrescriptionsParam listOpenPrescriptionsParam) throws IntegrationModuleException {
      listOpenPrescriptionsParam.setSymmKey(this.getSymmKey().getEncoded());
      return this.sealForRecipe(listOpenPrescriptionsParam, ListOpenPrescriptionsParam.class);
   }

   private ListOpenPrescriptionsResult unsealGetDataResponse(ListOpenPrescriptionsResponse response) throws IntegrationModuleException {
      MarshallerHelper<ListOpenPrescriptionsResult, Object> marshaller = new MarshallerHelper(ListOpenPrescriptionsResult.class, Object.class);
      return (ListOpenPrescriptionsResult)marshaller.unsealWithSymmKey(response.getListOpenPrescriptionsResultSealed(), this.getSymmKey());
   }

   @Profiled(
      logFailuresSeparately = true,
      tag = "0.ExecutorIntegrationModuleV4#getData(GetPrescriptionStatusParam)"
   )
   public GetPrescriptionStatusResult getData(GetPrescriptionStatusParam param) throws IntegrationModuleException {
      JoinPoint var6 = Factory.makeJP(ajc$tjp_1, this, this, param);
      TimingAspect var10000 = TimingAspect.aspectOf();
      Object[] var7 = new Object[]{this, param, var6};
      ProceedingJoinPoint var10001 = (new ExecutorIntegrationModuleDevV4Impl$AjcClosure3(var7)).linkClosureAndJoinPoint(69648);
      Annotation var10002 = ajc$anno$1;
      if (var10002 == null) {
         var10002 = ajc$anno$1 = ExecutorIntegrationModuleDevV4Impl.class.getDeclaredMethod("getData", GetPrescriptionStatusParam.class).getAnnotation(Profiled.class);
      }

      return (GetPrescriptionStatusResult)var10000.doPerfLogging(var10001, (Profiled)var10002);
   }

   private GetPrescriptionStatus getPrescriptionStatus(GetPrescriptionStatusParam getPrescriptionStatusParam) throws IntegrationModuleException {
      GetPrescriptionStatus getPrescriptionStatus = new GetPrescriptionStatus();
      getPrescriptionStatus.setGetPrescriptionStatusParamSealed(this.getSealedData((GetPrescriptionStatusParam)getPrescriptionStatusParam));
      getPrescriptionStatus.setProgramIdentification(PropertyHandler.getInstance().getProperty("programIdentification"));
      getPrescriptionStatus.setMguid(UUID.randomUUID().toString());
      return getPrescriptionStatus;
   }

   private GetPrescriptionStatusResult unsealGetPrescriptionStatusResponse(GetPrescriptionStatusResponse response) throws IntegrationModuleException {
      MarshallerHelper<GetPrescriptionStatusResult, Object> marshaller = new MarshallerHelper(GetPrescriptionStatusResult.class, Object.class);
      return (GetPrescriptionStatusResult)marshaller.unsealWithSymmKey(response.getGetPrescriptionStatusResultSealed(), this.getSymmKey());
   }

   @Profiled(
      logFailuresSeparately = true,
      tag = "0.ExecutorIntegrationModuleV4#getData(ListRidsHistoryParam)"
   )
   public ListRidsHistoryResult getData(ListRidsHistoryParam param) throws IntegrationModuleException {
      JoinPoint var6 = Factory.makeJP(ajc$tjp_2, this, this, param);
      TimingAspect var10000 = TimingAspect.aspectOf();
      Object[] var7 = new Object[]{this, param, var6};
      ProceedingJoinPoint var10001 = (new ExecutorIntegrationModuleDevV4Impl$AjcClosure5(var7)).linkClosureAndJoinPoint(69648);
      Annotation var10002 = ajc$anno$2;
      if (var10002 == null) {
         var10002 = ajc$anno$2 = ExecutorIntegrationModuleDevV4Impl.class.getDeclaredMethod("getData", ListRidsHistoryParam.class).getAnnotation(Profiled.class);
      }

      return (ListRidsHistoryResult)var10000.doPerfLogging(var10001, (Profiled)var10002);
   }

   private ListRidsHistory getListRidsHistory(ListRidsHistoryParam listRidsHistoryParam) throws IntegrationModuleException {
      listRidsHistoryParam.setSession((byte[])this.sessionMap.get(new MultiKey(listRidsHistoryParam.getExecutorId(), listRidsHistoryParam.getPatientId())));
      ListRidsHistory listPrescriptionHistory = new ListRidsHistory();
      listPrescriptionHistory.setListRidsHistoryParamSealed(this.getSealedData(listRidsHistoryParam));
      listPrescriptionHistory.setProgramIdentification(PropertyHandler.getInstance().getProperty("programIdentification"));
      listPrescriptionHistory.setMguid(UUID.randomUUID().toString());
      return listPrescriptionHistory;
   }

   private byte[] getSealedData(ListRidsHistoryParam listRidsHistoryParam) throws IntegrationModuleException {
      listRidsHistoryParam.setSymmKey(this.getSymmKey().getEncoded());
      return this.sealForRecipe(listRidsHistoryParam, ListRidsHistoryParam.class);
   }

   private ListRidsHistoryResult unsealListRidsHistoryResponse(ListRidsHistoryResponse response) {
      MarshallerHelper<ListRidsHistoryResult, Object> marshaller = new MarshallerHelper(ListRidsHistoryResult.class, Object.class);
      return (ListRidsHistoryResult)marshaller.unsealWithSymmKey(response.getListRidsHistoryResultSealed(), this.getSymmKey());
   }

   @Profiled(
      logFailuresSeparately = true,
      tag = "ExecutorIntegrationModuleV4#getPrescription"
   )
   public GetPrescriptionForExecutorResult getPrescription(String rid) throws IntegrationModuleException {
      JoinPoint var9 = Factory.makeJP(ajc$tjp_3, this, this, rid);
      TimingAspect var10000 = TimingAspect.aspectOf();
      Object[] var10 = new Object[]{this, rid, var9};
      ProceedingJoinPoint var10001 = (new ExecutorIntegrationModuleDevV4Impl$AjcClosure7(var10)).linkClosureAndJoinPoint(69648);
      Annotation var10002 = ajc$anno$3;
      if (var10002 == null) {
         var10002 = ajc$anno$3 = ExecutorIntegrationModuleDevV4Impl.class.getDeclaredMethod("getPrescription", String.class).getAnnotation(Profiled.class);
      }

      return (GetPrescriptionForExecutorResult)var10000.doPerfLogging(var10001, (Profiled)var10002);
   }

   protected GetPrescriptionForExecutorResult createGetPrescriptionForExecutorResult(byte[] securedContent) throws IntegrationModuleException {
      MarshallerHelper<GetPrescriptionForExecutorResultSealed, Object> marshaller = new MarshallerHelper(GetPrescriptionForExecutorResultSealed.class, Object.class);
      String requestorIdInformation = StandaloneRequestorProvider.getRequestorIdInformation();
      String requestorTypeInformation = StandaloneRequestorProvider.getRequestorTypeInformation();
      GetPrescriptionForExecutorResultSealed sealedResult = (GetPrescriptionForExecutorResultSealed)marshaller.unsealWithSymmKey(securedContent, this.getSymmKey());
      GetPrescriptionForExecutorResult finalResult = new GetPrescriptionForExecutorResult(sealedResult);
      finalResult.setStatus(sealedResult.getStatus());
      this.checkStatus(finalResult);
      finalResult.setSealedContent(sealedResult.getPrescription());
      KeyResult key = this.getKeyFromKgss(sealedResult.getEncryptionKeyId(), ((EncryptionToken)this.getEtkHelper().getEtks(KgssIdentifierType.NIHII_PHARMACY, requestorIdInformation).get(0)).getEncoded());
      byte[] unsealedPrescription = this.unsealWithSymKey(sealedResult, key, requestorIdInformation, requestorTypeInformation);
      finalResult.setPrescription(unsealedPrescription);
      finalResult.setEncryptionKey(key.getSecretKey().getEncoded());
      finalResult.setInsurabilityResponse(InsurabilityHandler.getInsurability());
      finalResult.setMessageId(InsurabilityHandler.getMessageId());
      return finalResult;
   }

   @Profiled(
      logFailuresSeparately = true,
      tag = "0.ExecutorIntegrationModuleV4#decryptGetOpenPrescriptionForExecutor"
   )
   public GetOpenPrescriptionForExecutor decryptGetOpenPrescriptionForExecutor(GetOpenPrescriptionForExecutor gopfe) throws IntegrationModuleException {
      JoinPoint var7 = Factory.makeJP(ajc$tjp_4, this, this, gopfe);
      TimingAspect var10000 = TimingAspect.aspectOf();
      Object[] var8 = new Object[]{this, gopfe, var7};
      ProceedingJoinPoint var10001 = (new ExecutorIntegrationModuleDevV4Impl$AjcClosure9(var8)).linkClosureAndJoinPoint(69648);
      Annotation var10002 = ajc$anno$4;
      if (var10002 == null) {
         var10002 = ajc$anno$4 = ExecutorIntegrationModuleDevV4Impl.class.getDeclaredMethod("decryptGetOpenPrescriptionForExecutor", GetOpenPrescriptionForExecutor.class).getAnnotation(Profiled.class);
      }

      return (GetOpenPrescriptionForExecutor)var10000.doPerfLogging(var10001, (Profiled)var10002);
   }

   @Profiled(
      logFailuresSeparately = true,
      tag = "0.ExecutorIntegrationModuleV4#markAsArchived"
   )
   public void markAsArchived(String rid) throws IntegrationModuleException {
      JoinPoint var8 = Factory.makeJP(ajc$tjp_5, this, this, rid);
      TimingAspect var10000 = TimingAspect.aspectOf();
      Object[] var9 = new Object[]{this, rid, var8};
      ProceedingJoinPoint var10001 = (new ExecutorIntegrationModuleDevV4Impl$AjcClosure11(var9)).linkClosureAndJoinPoint(69648);
      Annotation var10002 = ajc$anno$5;
      if (var10002 == null) {
         var10002 = ajc$anno$5 = ExecutorIntegrationModuleDevV4Impl.class.getDeclaredMethod("markAsArchived", String.class).getAnnotation(Profiled.class);
      }

      var10000.doPerfLogging(var10001, (Profiled)var10002);
   }

   @Profiled(
      logFailuresSeparately = true,
      tag = "0.ExecutorIntegrationModuleV4#markAsDelivered"
   )
   public void markAsDelivered(String rid) throws IntegrationModuleException {
      JoinPoint var8 = Factory.makeJP(ajc$tjp_6, this, this, rid);
      TimingAspect var10000 = TimingAspect.aspectOf();
      Object[] var9 = new Object[]{this, rid, var8};
      ProceedingJoinPoint var10001 = (new ExecutorIntegrationModuleDevV4Impl$AjcClosure13(var9)).linkClosureAndJoinPoint(69648);
      Annotation var10002 = ajc$anno$6;
      if (var10002 == null) {
         var10002 = ajc$anno$6 = ExecutorIntegrationModuleDevV4Impl.class.getDeclaredMethod("markAsDelivered", String.class).getAnnotation(Profiled.class);
      }

      var10000.doPerfLogging(var10001, (Profiled)var10002);
   }

   @Profiled(
      logFailuresSeparately = true,
      tag = "0.ExecutorIntegrationModuleV4#markAsUnDelivered"
   )
   public void markAsUndelivered(String rid) throws IntegrationModuleException {
      JoinPoint var8 = Factory.makeJP(ajc$tjp_7, this, this, rid);
      TimingAspect var10000 = TimingAspect.aspectOf();
      Object[] var9 = new Object[]{this, rid, var8};
      ProceedingJoinPoint var10001 = (new ExecutorIntegrationModuleDevV4Impl$AjcClosure15(var9)).linkClosureAndJoinPoint(69648);
      Annotation var10002 = ajc$anno$7;
      if (var10002 == null) {
         var10002 = ajc$anno$7 = ExecutorIntegrationModuleDevV4Impl.class.getDeclaredMethod("markAsUndelivered", String.class).getAnnotation(Profiled.class);
      }

      var10000.doPerfLogging(var10001, (Profiled)var10002);
   }

   @Profiled(
      logFailuresSeparately = true,
      tag = "0.ExecutorIntegrationModuleV4#revokePrescription"
   )
   public void revokePrescription(String rid, String reason) throws IntegrationModuleException {
      JoinPoint var10 = Factory.makeJP(ajc$tjp_8, this, this, rid, reason);
      TimingAspect var10000 = TimingAspect.aspectOf();
      Object[] var11 = new Object[]{this, rid, reason, var10};
      ProceedingJoinPoint var10001 = (new ExecutorIntegrationModuleDevV4Impl$AjcClosure17(var11)).linkClosureAndJoinPoint(69648);
      Annotation var10002 = ajc$anno$8;
      if (var10002 == null) {
         var10002 = ajc$anno$8 = ExecutorIntegrationModuleDevV4Impl.class.getDeclaredMethod("revokePrescription", String.class, String.class).getAnnotation(Profiled.class);
      }

      var10000.doPerfLogging(var10001, (Profiled)var10002);
   }

   @Profiled(
      logFailuresSeparately = true,
      tag = "0.ExecutorIntegrationModuleV4#listNotifications"
   )
   public List<ListNotificationsItem> listNotifications(boolean readFlag) throws IntegrationModuleException {
      JoinPoint var8 = Factory.makeJP(ajc$tjp_9, this, this, Conversions.booleanObject(readFlag));
      TimingAspect var10000 = TimingAspect.aspectOf();
      Object[] var9 = new Object[]{this, Conversions.booleanObject(readFlag), var8};
      ProceedingJoinPoint var10001 = (new ExecutorIntegrationModuleDevV4Impl$AjcClosure19(var9)).linkClosureAndJoinPoint(69648);
      Annotation var10002 = ajc$anno$9;
      if (var10002 == null) {
         var10002 = ajc$anno$9 = ExecutorIntegrationModuleDevV4Impl.class.getDeclaredMethod("listNotifications", Boolean.TYPE).getAnnotation(Profiled.class);
      }

      return (List)var10000.doPerfLogging(var10001, (Profiled)var10002);
   }

   @Profiled(
      logFailuresSeparately = true,
      tag = "0.ExecutorIntegrationModuleV4#createFeedback"
   )
   public void createFeedback(String prescriberId, String rid, byte[] feedbackText) throws IntegrationModuleException {
      StaticPart var10000 = ajc$tjp_10;
      Object[] var16 = new Object[]{prescriberId, rid, feedbackText};
      JoinPoint var15 = Factory.makeJP(var10000, this, this, var16);
      TimingAspect var18 = TimingAspect.aspectOf();
      Object[] var17 = new Object[]{this, prescriberId, rid, feedbackText, var15};
      ProceedingJoinPoint var10001 = (new ExecutorIntegrationModuleDevV4Impl$AjcClosure21(var17)).linkClosureAndJoinPoint(69648);
      Annotation var10002 = ajc$anno$10;
      if (var10002 == null) {
         var10002 = ajc$anno$10 = ExecutorIntegrationModuleDevV4Impl.class.getDeclaredMethod("createFeedback", String.class, String.class, byte[].class).getAnnotation(Profiled.class);
      }

      var18.doPerfLogging(var10001, (Profiled)var10002);
   }

   @Profiled(
      logFailuresSeparately = true,
      tag = "0.ExecutorIntegrationModuleV4#getData(ListReservationsParam)"
   )
   public ListReservationsResult getData(ListReservationsParam param) throws IntegrationModuleException {
      JoinPoint var9 = Factory.makeJP(ajc$tjp_11, this, this, param);
      TimingAspect var10000 = TimingAspect.aspectOf();
      Object[] var10 = new Object[]{this, param, var9};
      ProceedingJoinPoint var10001 = (new ExecutorIntegrationModuleDevV4Impl$AjcClosure23(var10)).linkClosureAndJoinPoint(69648);
      Annotation var10002 = ajc$anno$11;
      if (var10002 == null) {
         var10002 = ajc$anno$11 = ExecutorIntegrationModuleDevV4Impl.class.getDeclaredMethod("getData", ListReservationsParam.class).getAnnotation(Profiled.class);
      }

      return (ListReservationsResult)var10000.doPerfLogging(var10001, (Profiled)var10002);
   }

   private ListReservations getListReservations(ListReservationsParam param) throws IntegrationModuleException {
      param.setSymmKey(this.getSymmKey().getEncoded());
      param.setStartDate(param.getStartDate() == null ? this.readLastDateToDisk() : param.getStartDate());
      ListReservations listReservations = new ListReservations();
      listReservations.setListReservationsParamSealed(this.getSealedData(param));
      listReservations.setProgramIdentification(PropertyHandler.getInstance().getProperty("programIdentification"));
      listReservations.setMguid(UUID.randomUUID().toString());
      return listReservations;
   }

   private byte[] getSealedData(ListReservationsParam listReservationsParam) throws IntegrationModuleException {
      return this.sealForRecipe(listReservationsParam, ListReservationsParam.class);
   }

   private ListReservationsResult unsealListReservationsResponse(ListReservationsResponse response) {
      MarshallerHelper<ListReservationsResult, Object> marshaller = new MarshallerHelper(ListReservationsResult.class, Object.class);
      ListReservationsResult result = (ListReservationsResult)marshaller.unsealWithSymmKey(response.getListReservationsResultSealed(), this.getSymmKey());
      return result;
   }

   @Profiled(
      logFailuresSeparately = true,
      tag = "0.ExecutorIntegrationModuleV4#getData(ListRidsInProcessParam)"
   )
   public ListRidsInProcessResult getData(ListRidsInProcessParam param) throws IntegrationModuleException {
      JoinPoint var6 = Factory.makeJP(ajc$tjp_12, this, this, param);
      TimingAspect var10000 = TimingAspect.aspectOf();
      Object[] var7 = new Object[]{this, param, var6};
      ProceedingJoinPoint var10001 = (new ExecutorIntegrationModuleDevV4Impl$AjcClosure25(var7)).linkClosureAndJoinPoint(69648);
      Annotation var10002 = ajc$anno$12;
      if (var10002 == null) {
         var10002 = ajc$anno$12 = ExecutorIntegrationModuleDevV4Impl.class.getDeclaredMethod("getData", ListRidsInProcessParam.class).getAnnotation(Profiled.class);
      }

      return (ListRidsInProcessResult)var10000.doPerfLogging(var10001, (Profiled)var10002);
   }

   private ListRidsInProcess getListRidsInProcess(ListRidsInProcessParam listRidsInProcessParam) throws IntegrationModuleException {
      listRidsInProcessParam.setSymmKey(this.getSymmKey().getEncoded());
      ListRidsInProcess getAllRidInProcess = new ListRidsInProcess();
      getAllRidInProcess.setListRidsInProcessParamSealed(this.getSealedData(listRidsInProcessParam));
      getAllRidInProcess.setProgramIdentification(PropertyHandler.getInstance().getProperty("programIdentification"));
      getAllRidInProcess.setMguid(UUID.randomUUID().toString());
      return getAllRidInProcess;
   }

   @Profiled(
      logFailuresSeparately = true,
      tag = "0.ExecutorIntegrationModuleV4#putData(PutAllRidInProcessParam)"
   )
   public PutRidsInProcessResult putData(PutRidsInProcessParam param) throws IntegrationModuleException {
      JoinPoint var6 = Factory.makeJP(ajc$tjp_13, this, this, param);
      TimingAspect var10000 = TimingAspect.aspectOf();
      Object[] var7 = new Object[]{this, param, var6};
      ProceedingJoinPoint var10001 = (new ExecutorIntegrationModuleDevV4Impl$AjcClosure27(var7)).linkClosureAndJoinPoint(69648);
      Annotation var10002 = ajc$anno$13;
      if (var10002 == null) {
         var10002 = ajc$anno$13 = ExecutorIntegrationModuleDevV4Impl.class.getDeclaredMethod("putData", PutRidsInProcessParam.class).getAnnotation(Profiled.class);
      }

      return (PutRidsInProcessResult)var10000.doPerfLogging(var10001, (Profiled)var10002);
   }

   private PutRidsInProcess getPutAllRidInProcess(PutRidsInProcessParam putRidsInProcessParam) throws IntegrationModuleException {
      putRidsInProcessParam.setSymmKey(this.getSymmKey().getEncoded());
      PutRidsInProcess putAllRidInProcess = new PutRidsInProcess();
      putAllRidInProcess.setPutRidsInProcessParamSealed(this.getSealedData(putRidsInProcessParam));
      putAllRidInProcess.setProgramIdentification(PropertyHandler.getInstance().getProperty("programIdentification"));
      putAllRidInProcess.setMguid(UUID.randomUUID().toString());
      return putAllRidInProcess;
   }

   private byte[] getSealedData(PutRidsInProcessParam putRidsInProcessParam) throws IntegrationModuleException {
      return this.sealForRecipe(putRidsInProcessParam, PutRidsInProcessParam.class);
   }

   private byte[] getSealedData(ListRidsInProcessParam litRidsInProcessParam) throws IntegrationModuleException {
      return this.sealForRecipe(litRidsInProcessParam, ListRidsInProcessParam.class);
   }

   private PutRidsInProcessResult unsealPutRidsInProcessResponse(PutRidsInProcessResponse response) {
      MarshallerHelper<PutRidsInProcessResult, Object> marshaller = new MarshallerHelper(PutRidsInProcessResult.class, Object.class);
      PutRidsInProcessResult result = (PutRidsInProcessResult)marshaller.unsealWithSymmKey(response.getPutRidsInProcessResultSealed(), this.getSymmKey());
      return result;
   }

   private ListRidsInProcessResult unsealListRidsInProcessResponse(ListRidsInProcessResponse response) {
      MarshallerHelper<ListRidsInProcessResult, Object> marshaller = new MarshallerHelper(ListRidsInProcessResult.class, Object.class);
      ListRidsInProcessResult result = (ListRidsInProcessResult)marshaller.unsealWithSymmKey(response.getListRidsInProcessResultSealed(), this.getSymmKey());
      return result;
   }

   @Profiled(
      logFailuresSeparately = true,
      tag = "0.ExecutorIntegrationModuleV4#getAndMarkAsDelivered"
   )
   public GetPrescriptionForExecutorResult getAndMarkAsDelivered(String rid) throws IntegrationModuleException {
      JoinPoint var4 = Factory.makeJP(ajc$tjp_14, this, this, rid);
      TimingAspect var10000 = TimingAspect.aspectOf();
      Object[] var5 = new Object[]{this, rid, var4};
      ProceedingJoinPoint var10001 = (new ExecutorIntegrationModuleDevV4Impl$AjcClosure29(var5)).linkClosureAndJoinPoint(69648);
      Annotation var10002 = ajc$anno$14;
      if (var10002 == null) {
         var10002 = ajc$anno$14 = ExecutorIntegrationModuleDevV4Impl.class.getDeclaredMethod("getAndMarkAsDelivered", String.class).getAnnotation(Profiled.class);
      }

      return (GetPrescriptionForExecutorResult)var10000.doPerfLogging(var10001, (Profiled)var10002);
   }

   public ListRelationsResult getData(ListRelationsParam patientRelationParam) throws IntegrationModuleException {
      ApplicationConfig.getInstance().assertValidPharmacySession();
      ListRelations request = this.getListPatientRelation(patientRelationParam);

      try {
         try {
            ListRelationsResponse dataResponse = RecipeExecutorServiceDevV4Impl.getInstance().listRelations(request);
            ListRelationsResult unsealedResponse = this.unsealListRelationsResponse(dataResponse);
            this.checkStatus(unsealedResponse);
            this.sessionMap.put(new MultiKey(patientRelationParam.getExecutorId(), patientRelationParam.getPatientId()), unsealedResponse.getSession());
            return unsealedResponse;
         } catch (ClientTransportException var5) {
            throw new IntegrationModuleException(I18nHelper.getLabel("error.connection.executor"), var5);
         }
      } catch (Throwable var6) {
         Exceptionutils.errorHandler(var6, request.getMguid());
         return null;
      }
   }

   private ListRelations getListPatientRelation(ListRelationsParam data) throws IntegrationModuleException {
      data.setSymmKey(this.getSymmKey().getEncoded());
      ListRelations patientRelation = new ListRelations();
      patientRelation.setListRelationsParamSealed(this.getSealedData(data));
      patientRelation.setProgramIdentification(PropertyHandler.getInstance().getProperty("programIdentification"));
      patientRelation.setMguid(UUID.randomUUID().toString());
      return patientRelation;
   }

   private byte[] getSealedData(ListRelationsParam data) throws IntegrationModuleException {
      data.setSymmKey(this.getSymmKey().getEncoded());
      return this.sealForRecipe(data, ListRelationsParam.class);
   }

   private ListRelationsResult unsealListRelationsResponse(ListRelationsResponse response) {
      MarshallerHelper<ListRelationsResult, Object> marshaller = new MarshallerHelper(ListRelationsResult.class, Object.class);
      ListRelationsResult result = (ListRelationsResult)marshaller.unsealWithSymmKey(response.getListRelationsResultSealed(), this.getSymmKey());
      return result;
   }

   private void markAsDeliveredAsync(final String rid) {
      (new Thread(new Runnable() {
         public void run() {
            try {
               ExecutorIntegrationModuleDevV4Impl.this.markAsDelivered(rid);
            } catch (IntegrationModuleException var2) {
               throw new RuntimeException(var2);
            }
         }
      })).start();
   }

   private void markAsArchivedAsync(final String rid) {
      (new Thread(new Runnable() {
         public void run() {
            try {
               ExecutorIntegrationModuleDevV4Impl.this.markAsArchived(rid);
            } catch (IntegrationModuleException var2) {
               throw new RuntimeException(var2);
            }
         }
      })).start();
   }

   static {
      ajc$preClinit();
   }

   // $FF: synthetic method
   static final ListOpenPrescriptionsResult getData_aroundBody0(ExecutorIntegrationModuleDevV4Impl ajc$this, ListOpenPrescriptionsParam param, JoinPoint var2) {
      ApplicationConfig.getInstance().assertValidPharmacySession();
      ValidationUtils.validatePatientId(param.getPatientId());
      ValidationUtils.validateMandateHolderId(param.getMandateHolderId(), true);
      ListOpenPrescriptions getOpenPrescriptionList = ajc$this.getOpenPrescriptionList(param);

      try {
         try {
            ListOpenPrescriptionsResponse response = RecipeExecutorServiceDevV4Impl.getInstance().listOpenPrescriptions(getOpenPrescriptionList);
            ListOpenPrescriptionsResult result = ajc$this.unsealGetDataResponse(response);
            ajc$this.checkStatus(result);
            ajc$this.sessionMap.put(new MultiKey(param.getExecutorId(), param.getPatientId()), result.getSession());
            return result;
         } catch (ClientTransportException var8) {
            throw new IntegrationModuleException(I18nHelper.getLabel("error.connection.executor"), var8);
         }
      } catch (Throwable var9) {
         Exceptionutils.errorHandler(var9, getOpenPrescriptionList.getMguid());
         return null;
      }
   }

   // $FF: synthetic method
   static final GetPrescriptionStatusResult getData_aroundBody2(ExecutorIntegrationModuleDevV4Impl ajc$this, GetPrescriptionStatusParam param, JoinPoint var2) {
      RidValidator.validateRid(param.getRid());
      ApplicationConfig.getInstance().assertValidPharmacySession();
      GetPrescriptionStatus getPrescriptionStatus = ajc$this.getPrescriptionStatus(param);

      try {
         try {
            GetPrescriptionStatusResponse response = RecipeExecutorServiceDevV4Impl.getInstance().getPrescriptionStatus(getPrescriptionStatus);
            GetPrescriptionStatusResult result = ajc$this.unsealGetPrescriptionStatusResponse(response);
            ajc$this.checkStatus(result);
            return result;
         } catch (ClientTransportException var8) {
            throw new IntegrationModuleException(I18nHelper.getLabel("error.connection.executor"), var8);
         }
      } catch (Throwable var9) {
         Exceptionutils.errorHandler(var9, getPrescriptionStatus.getMguid());
         return null;
      }
   }

   // $FF: synthetic method
   static final ListRidsHistoryResult getData_aroundBody4(ExecutorIntegrationModuleDevV4Impl ajc$this, ListRidsHistoryParam param, JoinPoint var2) {
      ApplicationConfig.getInstance().assertValidPharmacySession();
      ValidationUtils.validatePatientId(param.getPatientId());
      ListRidsHistory listPrescriptionHistory = ajc$this.getListRidsHistory(param);

      try {
         try {
            ListRidsHistoryResponse response = RecipeExecutorServiceDevV4Impl.getInstance().listRidsHistory(listPrescriptionHistory);
            ListRidsHistoryResult result = ajc$this.unsealListRidsHistoryResponse(response);
            ajc$this.checkStatus(result);
            ajc$this.sessionMap.put(new MultiKey(param.getExecutorId(), param.getPatientId()), result.getSession());
            return result;
         } catch (ClientTransportException var8) {
            throw new IntegrationModuleException(I18nHelper.getLabel("error.connection.executor"), var8);
         }
      } catch (Throwable var9) {
         Exceptionutils.errorHandler(var9, listPrescriptionHistory.getMguid());
         return null;
      }
   }

   // $FF: synthetic method
   static final GetPrescriptionForExecutorResult getPrescription_aroundBody6(ExecutorIntegrationModuleDevV4Impl ajc$this, String rid, JoinPoint var2) {
      RidValidator.validateRid(rid);
      ApplicationConfig.getInstance().assertValidPharmacySession();
      InsurabilityHandler.setInsurability((String)null);
      InsurabilityHandler.setMessageId((String)null);
      String guid = UUID.randomUUID().toString();

      try {
         byte[] sealedGetPrescriptionForExecutorParam = ajc$this.getSealedGetPrescriptionForExecutorParam(rid);
         GetPrescriptionForExecutor request = new GetPrescriptionForExecutor();
         request.setDisablePatientInsurabilityCheckParam(Boolean.parseBoolean(ajc$this.getPropertyHandler().getProperty("patient.insurability.disable")));
         request.setGetPrescriptionForExecutorParamSealed(sealedGetPrescriptionForExecutorParam);
         request.setProgramIdentification(PropertyHandler.getInstance().getProperty("programIdentification"));
         request.setMguid(guid);
         GetPrescriptionForExecutorResponse response = null;

         try {
            response = RecipeExecutorServiceDevV4Impl.getInstance().getPrescriptionForExecutor(request);
         } catch (ClientTransportException var14) {
            throw new IntegrationModuleException(I18nHelper.getLabel("error.connection.executor"), var14);
         }

         be.recipe.services.executor.GetPrescriptionForExecutorResult executorResult = response.getGetPrescriptionForExecutorResult();
         GetPrescriptionForExecutorResult finalResult = ajc$this.createGetPrescriptionForExecutorResult(executorResult.getGetPrescriptionForExecutorResultSealed());
         ajc$this.checkStatus(finalResult);
         getPrescriptionCache().put(rid, finalResult);
         return finalResult;
      } catch (Throwable var15) {
         Exceptionutils.errorHandler(var15, guid);
         return null;
      }
   }

   // $FF: synthetic method
   static final GetOpenPrescriptionForExecutor decryptGetOpenPrescriptionForExecutor_aroundBody8(ExecutorIntegrationModuleDevV4Impl ajc$this, GetOpenPrescriptionForExecutor gopfe, JoinPoint var2) {
      try {
         String requestorIdInformation = StandaloneRequestorProvider.getRequestorIdInformation();
         KeyResult key = ajc$this.getKeyFromKgss(gopfe.getEncryptionKeyId(), ((EncryptionToken)ajc$this.getEtkHelper().getEtks(KgssIdentifierType.NIHII_PHARMACY, requestorIdInformation).get(0)).getEncoded());
         byte[] unsealedPrescription = ajc$this.unsealPrescriptionForUnknown(key, gopfe.getPrescription());
         GetOpenPrescriptionForExecutor finalResult = new GetOpenPrescriptionForExecutor();
         finalResult.setCreationDate(gopfe.getCreationDate());
         finalResult.setEncryptionKeyId(gopfe.getEncryptionKeyId());
         finalResult.setFeedbackAllowed(gopfe.isFeedbackAllowed());
         finalResult.setId(gopfe.getId());
         finalResult.setPatientId(gopfe.getPatientId());
         finalResult.setPrescriberId(gopfe.getPrescriberId());
         finalResult.setPrescription(IOUtils.decompress(unsealedPrescription));
         finalResult.setPrescriptionType(gopfe.getPrescriptionType());
         finalResult.setRid(gopfe.getRid());
         finalResult.setStatus(gopfe.getStatus());
         finalResult.setExpirationDate(gopfe.getExpirationDate());
         return finalResult;
      } catch (IOException var10) {
         Exceptionutils.errorHandler(var10, "error.data.uncompression");
         return null;
      }
   }

   // $FF: synthetic method
   static final void markAsArchived_aroundBody10(ExecutorIntegrationModuleDevV4Impl ajc$this, String rid, JoinPoint var2) {
      RidValidator.validateRid(rid);
      ApplicationConfig.getInstance().assertValidPharmacySession();
      MarkAsArchived request = new MarkAsArchived();
      request.setMguid(UUID.randomUUID().toString());

      try {
         byte[] sealedMarkAsArchivedParam = ajc$this.getSealedMarkAsArchivedParam(rid);
         request.setMarkAsArchivedParamSealed(sealedMarkAsArchivedParam);
         request.setProgramIdentification(PropertyHandler.getInstance().getProperty("programIdentification"));

         try {
            MarkAsArchivedResponse response = RecipeExecutorServiceDevV4Impl.getInstance().markAsArchived(request);
            MarshallerHelper marshaller = new MarshallerHelper(MarkAsArchivedResult.class, Object.class);
            MarkAsArchivedResult result = (MarkAsArchivedResult)marshaller.unsealWithSymmKey(response.getMarkAsArchivedResultSealed(), ajc$this.getSymmKey());
            ajc$this.checkStatus(result);
         } catch (ClientTransportException var12) {
            throw new IntegrationModuleException(I18nHelper.getLabel("error.connection.executor"), var12);
         }
      } catch (Throwable var13) {
         Exceptionutils.errorHandler(var13, request.getMguid());
      }

   }

   // $FF: synthetic method
   static final void markAsDelivered_aroundBody12(ExecutorIntegrationModuleDevV4Impl ajc$this, String rid, JoinPoint var2) {
      RidValidator.validateRid(rid);
      ApplicationConfig.getInstance().assertValidPharmacySession();
      MarkAsDelivered request = new MarkAsDelivered();
      request.setMguid(UUID.randomUUID().toString());

      try {
         byte[] sealedMarkAsDeliveredParam = ajc$this.getSealedMarkAsDeliveredParam(rid);
         request.setMarkAsDeliveredParamSealed(sealedMarkAsDeliveredParam);
         request.setProgramIdentification(PropertyHandler.getInstance().getProperty("programIdentification"));
         MarkAsDeliveredResponse response = null;

         try {
            response = RecipeExecutorServiceDevV4Impl.getInstance().markAsDelivered(request);
            MarshallerHelper marshaller = new MarshallerHelper(MarkAsDeliveredResult.class, Object.class);
            MarkAsDeliveredResult result = (MarkAsDeliveredResult)marshaller.unsealWithSymmKey(response.getMarkAsDeliveredResultSealed(), ajc$this.getSymmKey());
            ajc$this.checkStatus(result);
         } catch (ClientTransportException var12) {
            throw new IntegrationModuleException(I18nHelper.getLabel("error.connection.executor"), var12);
         }
      } catch (Throwable var13) {
         Exceptionutils.errorHandler(var13, request.getMguid());
      }

   }

   // $FF: synthetic method
   static final void markAsUndelivered_aroundBody14(ExecutorIntegrationModuleDevV4Impl ajc$this, String rid, JoinPoint var2) {
      RidValidator.validateRid(rid);
      ApplicationConfig.getInstance().assertValidPharmacySession();
      MarkAsUnDelivered request = new MarkAsUnDelivered();
      request.setProgramIdentification(PropertyHandler.getInstance().getProperty("programIdentification"));
      request.setMguid(UUID.randomUUID().toString());

      try {
         byte[] sealedMarkAsUnDeliveredParam = ajc$this.getSealedMarkAsUnDeliveredParam(rid);
         request.setMarkAsUndeliveredParamSealed(sealedMarkAsUnDeliveredParam);
         MarkAsUnDeliveredResponse response = null;

         try {
            response = RecipeExecutorServiceDevV4Impl.getInstance().markAsUnDelivered(request);
            MarshallerHelper marshaller = new MarshallerHelper(MarkAsUndeliveredResult.class, Object.class);
            MarkAsUndeliveredResult result = (MarkAsUndeliveredResult)marshaller.unsealWithSymmKey(response.getMarkAsUnDeliveredResultSealed(), ajc$this.getSymmKey());
            ajc$this.checkStatus(result);
         } catch (ClientTransportException var12) {
            throw new IntegrationModuleException(I18nHelper.getLabel("error.connection.executor"), var12);
         }
      } catch (Throwable var13) {
         Exceptionutils.errorHandler(var13, request.getMguid());
      }

   }

   // $FF: synthetic method
   static final void revokePrescription_aroundBody16(ExecutorIntegrationModuleDevV4Impl ajc$this, String rid, String reason, JoinPoint var3) {
      RidValidator.validateRid(rid);
      ApplicationConfig.getInstance().assertValidPharmacySession();
      RevokePrescription request = new RevokePrescription();

      try {
         byte[] sealedRevokePrescriptionParam = ajc$this.getSealedRevokePrescriptionParam(rid, reason);
         request.setRevokePrescriptionParamSealed(sealedRevokePrescriptionParam);
         request.setProgramIdentification(PropertyHandler.getInstance().getProperty("programIdentification"));
         request.setMguid(UUID.randomUUID().toString());
         RevokePrescriptionResponse response = null;

         try {
            response = RecipeExecutorServiceDevV4Impl.getInstance().revokePrescriptionForExecutor(request);
            MarshallerHelper marshaller = new MarshallerHelper(RevokePrescriptionResult.class, Object.class);
            RevokePrescriptionResult result = (RevokePrescriptionResult)marshaller.unsealWithSymmKey(response.getRevokePrescriptionResultSealed(), ajc$this.getSymmKey());
            ajc$this.checkStatus(result);
         } catch (ClientTransportException var13) {
            throw new IntegrationModuleException(I18nHelper.getLabel("error.connection.executor"), var13);
         }
      } catch (Throwable var14) {
         Exceptionutils.errorHandler(var14, request.getMguid());
      }

   }

   // $FF: synthetic method
   static final List listNotifications_aroundBody18(ExecutorIntegrationModuleDevV4Impl ajc$this, boolean readFlag, JoinPoint var2) {
      ApplicationConfig.getInstance().assertValidPharmacySession();
      ListNotifications request = new ListNotifications();
      request.setMguid(UUID.randomUUID().toString());

      try {
         byte[] sealedListNotificationsParam = ajc$this.getSealedListNotificationsParam(readFlag);
         request.setListNotificationsParamSealed(sealedListNotificationsParam);
         request.setProgramIdentification(PropertyHandler.getInstance().getProperty("programIdentification"));
         ListNotificationsResponse response = null;

         try {
            response = RecipeExecutorServiceDevV4Impl.getInstance().listNotifications(request);
            MarshallerHelper marshaller = new MarshallerHelper(ListNotificationsResult.class, Object.class);
            ListNotificationsResult result = (ListNotificationsResult)marshaller.unsealWithSymmKey(response.getListNotificationsResultSealed(), ajc$this.getSymmKey());
            ajc$this.checkStatus(result);
         } catch (ClientTransportException var12) {
            throw new IntegrationModuleException(I18nHelper.getLabel("error.connection.executor"), var12);
         }

         byte[] securedContent = response.getListNotificationsResultSealed();
         return ajc$this.createListNotificationItems(securedContent);
      } catch (Throwable var13) {
         Exceptionutils.errorHandler(var13, request.getMguid());
         return null;
      }
   }

   // $FF: synthetic method
   static final void createFeedback_aroundBody20(ExecutorIntegrationModuleDevV4Impl ajc$this, String prescriberId, String rid, byte[] feedbackText, JoinPoint var4) {
      RidValidator.validateRid(rid);
      ApplicationConfig.getInstance().assertValidPharmacySession();
      CreateFeedback request = new CreateFeedback();
      request.setMguid(UUID.randomUUID().toString());

      try {
         ajc$this.getKmehrHelper().assertValidFeedback(feedbackText);
         List etkRecipients = ajc$this.getEtkHelper().getEtks(KgssIdentifierType.NIHII, prescriberId);

         for(int i = 0; i < etkRecipients.size(); ++i) {
            EncryptionToken etkRecipient = (EncryptionToken)etkRecipients.get(i);
            byte[] sealedCreateFeedbackParam = ajc$this.getSealedCreateFeedbackParam(feedbackText, etkRecipient, rid, prescriberId);
            request.setCreateFeedbackParamSealed(sealedCreateFeedbackParam);
            request.setProgramIdentification(PropertyHandler.getInstance().getProperty("programIdentification"));

            try {
               CreateFeedbackResponse response = RecipeExecutorServiceDevV4Impl.getInstance().createFeedback(request);
               MarshallerHelper marshaller1 = new MarshallerHelper(CreateFeedbackResult.class, Object.class);
               CreateFeedbackResult result = (CreateFeedbackResult)marshaller1.unsealWithSymmKey(response.getCreateFeedbackResultSealed(), ajc$this.getSymmKey());
               ajc$this.checkStatus(result);
            } catch (ClientTransportException var20) {
               throw new IntegrationModuleException(I18nHelper.getLabel("error.connection.executor"), var20);
            }
         }
      } catch (Throwable var21) {
         Exceptionutils.errorHandler(var21, request.getMguid());
      }

   }

   // $FF: synthetic method
   static final ListReservationsResult getData_aroundBody22(ExecutorIntegrationModuleDevV4Impl ajc$this, ListReservationsParam param, JoinPoint var2) {
      ApplicationConfig.getInstance().assertValidPharmacySession();
      Calendar lastSyncDate = Calendar.getInstance();

      try {
         try {
            ListReservationsResult completeResult = new ListReservationsResult();

            ListReservationsResult result;
            do {
               if (param.getPage() == null) {
                  param.setPage(new Page());
               }

               BigInteger currentPage;
               if (param.getPage() != null && param.getPage().getPageNumber() == null) {
                  currentPage = BigInteger.valueOf(0L);
               } else {
                  currentPage = param.getPage().getPageNumber();
               }

               param.getPage().setPageNumber(currentPage);
               ListReservations request = ajc$this.getListReservations(param);
               ListReservationsResponse response = RecipeExecutorServiceDevV4Impl.getInstance().listReservations(request);
               result = ajc$this.unsealListReservationsResponse(response);
               ajc$this.checkStatus(result);
               ajc$this.writeReservationsOnDisk(param, result, lastSyncDate);
               completeResult.getItems().addAll(result.getItems());
               param.getPage().setPageNumber(BigInteger.valueOf((long)(currentPage.intValue() + 1)));
            } while(result != null && result.isHasMoreResults() != null && result.isHasMoreResults());

            return completeResult;
         } catch (ClientTransportException var14) {
            throw new IntegrationModuleException(I18nHelper.getLabel("error.connection.executor"), var14);
         }
      } catch (Throwable var15) {
         Exceptionutils.errorHandler(var15);
         return null;
      }
   }

   // $FF: synthetic method
   static final ListRidsInProcessResult getData_aroundBody24(ExecutorIntegrationModuleDevV4Impl ajc$this, ListRidsInProcessParam param, JoinPoint var2) {
      ApplicationConfig.getInstance().assertValidPharmacySession();
      ListRidsInProcess request = ajc$this.getListRidsInProcess(param);

      try {
         try {
            ListRidsInProcessResponse response = RecipeExecutorServiceDevV4Impl.getInstance().listRidsInProcess(request);
            ListRidsInProcessResult result = ajc$this.unsealListRidsInProcessResponse(response);
            ajc$this.checkStatus(result);
            return result;
         } catch (ClientTransportException var8) {
            throw new IntegrationModuleException(I18nHelper.getLabel("error.connection.executor"), var8);
         }
      } catch (Throwable var9) {
         Exceptionutils.errorHandler(var9, request.getMguid());
         return null;
      }
   }

   // $FF: synthetic method
   static final PutRidsInProcessResult putData_aroundBody26(ExecutorIntegrationModuleDevV4Impl ajc$this, PutRidsInProcessParam param, JoinPoint var2) {
      ApplicationConfig.getInstance().assertValidPharmacySession();
      PutRidsInProcess request = ajc$this.getPutAllRidInProcess(param);

      try {
         try {
            PutRidsInProcessResponse response = RecipeExecutorServiceDevV4Impl.getInstance().putRidsInProcess(request);
            PutRidsInProcessResult result = ajc$this.unsealPutRidsInProcessResponse(response);
            ajc$this.checkStatus(result);
            return result;
         } catch (ClientTransportException var8) {
            throw new IntegrationModuleException(I18nHelper.getLabel("error.connection.executor"), var8);
         }
      } catch (Throwable var9) {
         Exceptionutils.errorHandler(var9, request.getMguid());
         return null;
      }
   }

   // $FF: synthetic method
   static final GetPrescriptionForExecutorResult getAndMarkAsDelivered_aroundBody28(ExecutorIntegrationModuleDevV4Impl ajc$this, String rid, JoinPoint var2) {
      try {
         GetPrescriptionForExecutorResult getPrescriptionForExecutorResult;
         if (getPrescriptionCache().containsKey(rid)) {
            getPrescriptionForExecutorResult = (GetPrescriptionForExecutorResult)getPrescriptionCache().get(rid);
         } else {
            getPrescriptionForExecutorResult = ajc$this.getPrescription(rid);
         }

         ajc$this.markAsDelivered(rid);
         ajc$this.markAsArchived(rid);
         return getPrescriptionForExecutorResult;
      } catch (Exception var4) {
         Exceptionutils.errorHandler(var4);
         return null;
      }
   }

   // $FF: synthetic method
   private static void ajc$preClinit() {
      Factory var0 = new Factory("ExecutorIntegrationModuleDevV4Impl.java", ExecutorIntegrationModuleDevV4Impl.class);
      ajc$tjp_0 = var0.makeSJP("method-execution", var0.makeMethodSig("1", "getData", "be.business.connector.recipe.executor.ExecutorIntegrationModuleDevV4Impl", "be.recipe.services.executor.ListOpenPrescriptionsParam", "param", "be.business.connector.core.exceptions.IntegrationModuleException", "be.recipe.services.executor.ListOpenPrescriptionsResult"), 109);
      ajc$tjp_1 = var0.makeSJP("method-execution", var0.makeMethodSig("1", "getData", "be.business.connector.recipe.executor.ExecutorIntegrationModuleDevV4Impl", "be.recipe.services.executor.GetPrescriptionStatusParam", "param", "be.business.connector.core.exceptions.IntegrationModuleException", "be.recipe.services.executor.GetPrescriptionStatusResult"), 184);
      ajc$tjp_10 = var0.makeSJP("method-execution", var0.makeMethodSig("1", "createFeedback", "be.business.connector.recipe.executor.ExecutorIntegrationModuleDevV4Impl", "java.lang.String:java.lang.String:[B", "prescriberId:rid:feedbackText", "be.business.connector.core.exceptions.IntegrationModuleException", "void"), 574);
      ajc$tjp_11 = var0.makeSJP("method-execution", var0.makeMethodSig("1", "getData", "be.business.connector.recipe.executor.ExecutorIntegrationModuleDevV4Impl", "be.recipe.services.executor.ListReservationsParam", "param", "be.business.connector.core.exceptions.IntegrationModuleException", "be.recipe.services.executor.ListReservationsResult"), 614);
      ajc$tjp_12 = var0.makeSJP("method-execution", var0.makeMethodSig("1", "getData", "be.business.connector.recipe.executor.ExecutorIntegrationModuleDevV4Impl", "be.recipe.services.executor.ListRidsInProcessParam", "param", "be.business.connector.core.exceptions.IntegrationModuleException", "be.recipe.services.executor.ListRidsInProcessResult"), 706);
      ajc$tjp_13 = var0.makeSJP("method-execution", var0.makeMethodSig("1", "putData", "be.business.connector.recipe.executor.ExecutorIntegrationModuleDevV4Impl", "be.recipe.services.executor.PutRidsInProcessParam", "param", "be.business.connector.core.exceptions.IntegrationModuleException", "be.recipe.services.executor.PutRidsInProcessResult"), 752);
      ajc$tjp_14 = var0.makeSJP("method-execution", var0.makeMethodSig("1", "getAndMarkAsDelivered", "be.business.connector.recipe.executor.ExecutorIntegrationModuleDevV4Impl", "java.lang.String", "rid", "be.business.connector.core.exceptions.IntegrationModuleException", "be.business.connector.recipe.executor.domain.GetPrescriptionForExecutorResult"), 856);
      ajc$tjp_2 = var0.makeSJP("method-execution", var0.makeMethodSig("1", "getData", "be.business.connector.recipe.executor.ExecutorIntegrationModuleDevV4Impl", "be.recipe.services.executor.ListRidsHistoryParam", "param", "be.business.connector.core.exceptions.IntegrationModuleException", "be.recipe.services.executor.ListRidsHistoryResult"), 242);
      ajc$tjp_3 = var0.makeSJP("method-execution", var0.makeMethodSig("1", "getPrescription", "be.business.connector.recipe.executor.ExecutorIntegrationModuleDevV4Impl", "java.lang.String", "rid", "be.business.connector.core.exceptions.IntegrationModuleException", "be.business.connector.recipe.executor.domain.GetPrescriptionForExecutorResult"), 306);
      ajc$tjp_4 = var0.makeSJP("method-execution", var0.makeMethodSig("1", "decryptGetOpenPrescriptionForExecutor", "be.business.connector.recipe.executor.ExecutorIntegrationModuleDevV4Impl", "be.recipe.services.executor.GetOpenPrescriptionForExecutor", "gopfe", "be.business.connector.core.exceptions.IntegrationModuleException", "be.recipe.services.executor.GetOpenPrescriptionForExecutor"), 376);
      ajc$tjp_5 = var0.makeSJP("method-execution", var0.makeMethodSig("1", "markAsArchived", "be.business.connector.recipe.executor.ExecutorIntegrationModuleDevV4Impl", "java.lang.String", "rid", "be.business.connector.core.exceptions.IntegrationModuleException", "void"), 407);
      ajc$tjp_6 = var0.makeSJP("method-execution", var0.makeMethodSig("1", "markAsDelivered", "be.business.connector.recipe.executor.ExecutorIntegrationModuleDevV4Impl", "java.lang.String", "rid", "be.business.connector.core.exceptions.IntegrationModuleException", "void"), 442);
      ajc$tjp_7 = var0.makeSJP("method-execution", var0.makeMethodSig("1", "markAsUndelivered", "be.business.connector.recipe.executor.ExecutorIntegrationModuleDevV4Impl", "java.lang.String", "rid", "be.business.connector.core.exceptions.IntegrationModuleException", "void"), 473);
      ajc$tjp_8 = var0.makeSJP("method-execution", var0.makeMethodSig("1", "revokePrescription", "be.business.connector.recipe.executor.ExecutorIntegrationModuleDevV4Impl", "java.lang.String:java.lang.String", "rid:reason", "be.business.connector.core.exceptions.IntegrationModuleException", "void"), 505);
      ajc$tjp_9 = var0.makeSJP("method-execution", var0.makeMethodSig("1", "listNotifications", "be.business.connector.recipe.executor.ExecutorIntegrationModuleDevV4Impl", "boolean", "readFlag", "be.business.connector.core.exceptions.IntegrationModuleException", "java.util.List"), 538);
   }
}
