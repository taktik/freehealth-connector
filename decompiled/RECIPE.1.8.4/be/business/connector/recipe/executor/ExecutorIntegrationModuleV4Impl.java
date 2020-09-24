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
import be.business.connector.recipe.executor.services.RecipeExecutorServiceV4Impl;
import be.business.connector.recipe.utils.RidValidator;
import be.ehealth.technicalconnector.service.kgss.domain.KeyResult;
import be.fgov.ehealth.etee.crypto.encrypt.EncryptionToken;
import be.fgov.ehealth.recipe.core.v4.SecuredContentType;
import be.fgov.ehealth.recipe.protocol.v4.CreateFeedbackRequest;
import be.fgov.ehealth.recipe.protocol.v4.CreateFeedbackResponse;
import be.fgov.ehealth.recipe.protocol.v4.GetPrescriptionForExecutorRequest;
import be.fgov.ehealth.recipe.protocol.v4.GetPrescriptionForExecutorResponse;
import be.fgov.ehealth.recipe.protocol.v4.GetPrescriptionStatusRequest;
import be.fgov.ehealth.recipe.protocol.v4.GetPrescriptionStatusResponse;
import be.fgov.ehealth.recipe.protocol.v4.ListNotificationsRequest;
import be.fgov.ehealth.recipe.protocol.v4.ListNotificationsResponse;
import be.fgov.ehealth.recipe.protocol.v4.ListOpenPrescriptionsRequest;
import be.fgov.ehealth.recipe.protocol.v4.ListOpenPrescriptionsResponse;
import be.fgov.ehealth.recipe.protocol.v4.ListRelationsRequest;
import be.fgov.ehealth.recipe.protocol.v4.ListRelationsResponse;
import be.fgov.ehealth.recipe.protocol.v4.ListReservationsRequest;
import be.fgov.ehealth.recipe.protocol.v4.ListReservationsResponse;
import be.fgov.ehealth.recipe.protocol.v4.ListRidsHistoryRequest;
import be.fgov.ehealth.recipe.protocol.v4.ListRidsHistoryResponse;
import be.fgov.ehealth.recipe.protocol.v4.ListRidsInProcessRequest;
import be.fgov.ehealth.recipe.protocol.v4.ListRidsInProcessResponse;
import be.fgov.ehealth.recipe.protocol.v4.MarkAsArchivedRequest;
import be.fgov.ehealth.recipe.protocol.v4.MarkAsArchivedResponse;
import be.fgov.ehealth.recipe.protocol.v4.MarkAsDeliveredRequest;
import be.fgov.ehealth.recipe.protocol.v4.MarkAsDeliveredResponse;
import be.fgov.ehealth.recipe.protocol.v4.MarkAsUnDeliveredRequest;
import be.fgov.ehealth.recipe.protocol.v4.MarkAsUnDeliveredResponse;
import be.fgov.ehealth.recipe.protocol.v4.PutRidsInProcessRequest;
import be.fgov.ehealth.recipe.protocol.v4.PutRidsInProcessResponse;
import be.fgov.ehealth.recipe.protocol.v4.RevokePrescriptionRequest;
import be.fgov.ehealth.recipe.protocol.v4.RevokePrescriptionResponse;
import be.recipe.services.core.Page;
import be.recipe.services.executor.CreateFeedbackResult;
import be.recipe.services.executor.GetOpenPrescriptionForExecutor;
import be.recipe.services.executor.GetPrescriptionForExecutorResultSealed;
import be.recipe.services.executor.GetPrescriptionStatusParam;
import be.recipe.services.executor.GetPrescriptionStatusResult;
import be.recipe.services.executor.ListNotificationsItem;
import be.recipe.services.executor.ListNotificationsResult;
import be.recipe.services.executor.ListOpenPrescriptionsParam;
import be.recipe.services.executor.ListOpenPrescriptionsResult;
import be.recipe.services.executor.ListRelationsParam;
import be.recipe.services.executor.ListRelationsResult;
import be.recipe.services.executor.ListReservationsParam;
import be.recipe.services.executor.ListReservationsResult;
import be.recipe.services.executor.ListRidsHistoryParam;
import be.recipe.services.executor.ListRidsHistoryResult;
import be.recipe.services.executor.ListRidsInProcessParam;
import be.recipe.services.executor.ListRidsInProcessResult;
import be.recipe.services.executor.MarkAsArchivedResult;
import be.recipe.services.executor.MarkAsDeliveredResult;
import be.recipe.services.executor.MarkAsUndeliveredResult;
import be.recipe.services.executor.PutRidsInProcessParam;
import be.recipe.services.executor.PutRidsInProcessResult;
import be.recipe.services.executor.RevokePrescriptionResult;
import com.sun.xml.ws.client.ClientTransportException;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.collections.keyvalue.MultiKey;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.JoinPoint.StaticPart;
import org.aspectj.runtime.internal.Conversions;
import org.aspectj.runtime.reflect.Factory;
import org.joda.time.DateTime;
import org.perf4j.aop.Profiled;
import org.perf4j.log4j.aop.TimingAspect;

public class ExecutorIntegrationModuleV4Impl extends ExecutorIntegrationModuleImpl implements ExecutorIntegrationModuleV4 {
   private static final Map<MultiKey, byte[]> sessionMap;
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

   static {
      ajc$preClinit();
      sessionMap = new HashMap();
   }

   public ExecutorIntegrationModuleV4Impl() throws IntegrationModuleException {
   }

   public ListOpenPrescriptionsResult getData(ListOpenPrescriptionsParam param) throws IntegrationModuleException {
      ApplicationConfig.getInstance().assertValidPharmacySession();
      ValidationUtils.validatePatientId(param.getPatientId());
      ValidationUtils.validateMandateHolderId(param.getMandateHolderId(), true);
      ListOpenPrescriptionsRequest listOpenPrescriptionsRequest = this.getOpenPrescriptionList(param);

      try {
         try {
            ListOpenPrescriptionsResponse response = RecipeExecutorServiceV4Impl.getInstance().listOpenPrescriptions(listOpenPrescriptionsRequest);
            ListOpenPrescriptionsResult result = this.unsealGetDataResponse(response);
            this.checkStatus(result);
            sessionMap.put(new MultiKey(StandaloneRequestorProvider.getRequestorIdInformation(), param.getPatientId()), result.getSession());
            return result;
         } catch (ClientTransportException var5) {
            throw new IntegrationModuleException(I18nHelper.getLabel("error.connection.executor"), var5);
         }
      } catch (Throwable var6) {
         Exceptionutils.errorHandler(var6, listOpenPrescriptionsRequest.getId());
         return null;
      }
   }

   private ListOpenPrescriptionsResult unsealGetDataResponse(ListOpenPrescriptionsResponse response) throws IntegrationModuleException {
      MarshallerHelper<ListOpenPrescriptionsResult, Object> marshaller = new MarshallerHelper(ListOpenPrescriptionsResult.class, Object.class);
      return (ListOpenPrescriptionsResult)marshaller.unsealWithSymmKey(response.getSecuredListOpenPrescriptionsResponse().getSecuredContent(), this.getSymmKey());
   }

   private ListOpenPrescriptionsRequest getOpenPrescriptionList(ListOpenPrescriptionsParam listOpenPrescriptionsParam) throws IntegrationModuleException {
      listOpenPrescriptionsParam.setSession((byte[])sessionMap.get(new MultiKey(StandaloneRequestorProvider.getRequestorIdInformation(), listOpenPrescriptionsParam.getPatientId())));
      ListOpenPrescriptionsRequest request = new ListOpenPrescriptionsRequest();
      request.setSecuredListOpenPrescriptionsRequest(this.createSecuredContentType(this.getSealedData(listOpenPrescriptionsParam)));
      request.setIssueInstant(new DateTime());
      request.setProgramId(PropertyHandler.getInstance().getProperty("programIdentification"));
      request.setId(this.getId());
      return request;
   }

   private GetPrescriptionStatusRequest getPrescriptionStatus(GetPrescriptionStatusParam getPrescriptionStatusParam) throws IntegrationModuleException {
      GetPrescriptionStatusRequest request = new GetPrescriptionStatusRequest();
      request.setSecuredGetPrescriptionStatusRequest(this.createSecuredContentType(this.getSealedData((GetPrescriptionStatusParam)getPrescriptionStatusParam)));
      request.setIssueInstant(new DateTime());
      request.setProgramId(PropertyHandler.getInstance().getProperty("programIdentification"));
      request.setId(this.getId());
      return request;
   }

   private byte[] getSealedData(ListOpenPrescriptionsParam listOpenPrescriptionsParam) throws IntegrationModuleException {
      listOpenPrescriptionsParam.setSymmKey(this.getSymmKey().getEncoded());
      return this.sealForRecipe(listOpenPrescriptionsParam, ListOpenPrescriptionsParam.class);
   }

   private SecuredContentType createSecuredContentType(byte[] content) {
      SecuredContentType secured = new SecuredContentType();
      secured.setSecuredContent(content);
      return secured;
   }

   @Profiled(
      logFailuresSeparately = true,
      tag = "0.ExecutorIntegrationModuleV4#getData(GetPrescriptionStatusParam)"
   )
   public GetPrescriptionStatusResult getData(GetPrescriptionStatusParam param) throws IntegrationModuleException {
      JoinPoint var6 = Factory.makeJP(ajc$tjp_0, this, this, param);
      TimingAspect var10000 = TimingAspect.aspectOf();
      Object[] var7 = new Object[]{this, param, var6};
      ProceedingJoinPoint var10001 = (new ExecutorIntegrationModuleV4Impl$AjcClosure1(var7)).linkClosureAndJoinPoint(69648);
      Annotation var10002 = ajc$anno$0;
      if (var10002 == null) {
         var10002 = ajc$anno$0 = ExecutorIntegrationModuleV4Impl.class.getDeclaredMethod("getData", GetPrescriptionStatusParam.class).getAnnotation(Profiled.class);
      }

      return (GetPrescriptionStatusResult)var10000.doPerfLogging(var10001, (Profiled)var10002);
   }

   private GetPrescriptionStatusResult unsealGetPrescriptionStatusResponse(GetPrescriptionStatusResponse response) throws IntegrationModuleException {
      MarshallerHelper<GetPrescriptionStatusResult, Object> marshaller = new MarshallerHelper(GetPrescriptionStatusResult.class, Object.class);
      return (GetPrescriptionStatusResult)marshaller.unsealWithSymmKey(response.getSecuredGetPrescriptionStatusResponse().getSecuredContent(), this.getSymmKey());
   }

   @Profiled(
      logFailuresSeparately = true,
      tag = "0.ExecutorIntegrationModuleV4#getData(ListRidsHistoryParam)"
   )
   public ListRidsHistoryResult getData(ListRidsHistoryParam param) throws IntegrationModuleException {
      JoinPoint var6 = Factory.makeJP(ajc$tjp_1, this, this, param);
      TimingAspect var10000 = TimingAspect.aspectOf();
      Object[] var7 = new Object[]{this, param, var6};
      ProceedingJoinPoint var10001 = (new ExecutorIntegrationModuleV4Impl$AjcClosure3(var7)).linkClosureAndJoinPoint(69648);
      Annotation var10002 = ajc$anno$1;
      if (var10002 == null) {
         var10002 = ajc$anno$1 = ExecutorIntegrationModuleV4Impl.class.getDeclaredMethod("getData", ListRidsHistoryParam.class).getAnnotation(Profiled.class);
      }

      return (ListRidsHistoryResult)var10000.doPerfLogging(var10001, (Profiled)var10002);
   }

   private ListRidsHistoryRequest getListRidsHistory(ListRidsHistoryParam listRidsHistoryParam) throws IntegrationModuleException {
      listRidsHistoryParam.setSession((byte[])sessionMap.get(new MultiKey(listRidsHistoryParam.getExecutorId(), listRidsHistoryParam.getPatientId())));
      ListRidsHistoryRequest request = new ListRidsHistoryRequest();
      request.setSecuredListRidsHistoryRequest(this.createSecuredContentType(this.getSealedData(listRidsHistoryParam)));
      request.setProgramId(PropertyHandler.getInstance().getProperty("programIdentification"));
      request.setIssueInstant(new DateTime());
      request.setId(this.getId());
      return request;
   }

   private byte[] getSealedData(ListRidsHistoryParam listRidsHistoryParam) throws IntegrationModuleException {
      listRidsHistoryParam.setSymmKey(this.getSymmKey().getEncoded());
      return this.sealForRecipe(listRidsHistoryParam, ListRidsHistoryParam.class);
   }

   private ListRidsHistoryResult unsealListRidsHistoryResponse(ListRidsHistoryResponse response) {
      MarshallerHelper<ListRidsHistoryResult, Object> marshaller = new MarshallerHelper(ListRidsHistoryResult.class, Object.class);
      return (ListRidsHistoryResult)marshaller.unsealWithSymmKey(response.getSecuredListRidsHistoryResponse().getSecuredContent(), this.getSymmKey());
   }

   @Profiled(
      logFailuresSeparately = true,
      tag = "ExecutorIntegrationModule#getPrescription"
   )
   public GetPrescriptionForExecutorResult getPrescription(String rid) throws IntegrationModuleException {
      JoinPoint var9 = Factory.makeJP(ajc$tjp_2, this, this, rid);
      TimingAspect var10000 = TimingAspect.aspectOf();
      Object[] var10 = new Object[]{this, rid, var9};
      ProceedingJoinPoint var10001 = (new ExecutorIntegrationModuleV4Impl$AjcClosure5(var10)).linkClosureAndJoinPoint(69648);
      Annotation var10002 = ajc$anno$2;
      if (var10002 == null) {
         var10002 = ajc$anno$2 = ExecutorIntegrationModuleV4Impl.class.getDeclaredMethod("getPrescription", String.class).getAnnotation(Profiled.class);
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
      JoinPoint var7 = Factory.makeJP(ajc$tjp_3, this, this, gopfe);
      TimingAspect var10000 = TimingAspect.aspectOf();
      Object[] var8 = new Object[]{this, gopfe, var7};
      ProceedingJoinPoint var10001 = (new ExecutorIntegrationModuleV4Impl$AjcClosure7(var8)).linkClosureAndJoinPoint(69648);
      Annotation var10002 = ajc$anno$3;
      if (var10002 == null) {
         var10002 = ajc$anno$3 = ExecutorIntegrationModuleV4Impl.class.getDeclaredMethod("decryptGetOpenPrescriptionForExecutor", GetOpenPrescriptionForExecutor.class).getAnnotation(Profiled.class);
      }

      return (GetOpenPrescriptionForExecutor)var10000.doPerfLogging(var10001, (Profiled)var10002);
   }

   @Profiled(
      logFailuresSeparately = true,
      tag = "0.ExecutorIntegrationModuleV4#markAsArchived"
   )
   public void markAsArchived(String rid) throws IntegrationModuleException {
      JoinPoint var8 = Factory.makeJP(ajc$tjp_4, this, this, rid);
      TimingAspect var10000 = TimingAspect.aspectOf();
      Object[] var9 = new Object[]{this, rid, var8};
      ProceedingJoinPoint var10001 = (new ExecutorIntegrationModuleV4Impl$AjcClosure9(var9)).linkClosureAndJoinPoint(69648);
      Annotation var10002 = ajc$anno$4;
      if (var10002 == null) {
         var10002 = ajc$anno$4 = ExecutorIntegrationModuleV4Impl.class.getDeclaredMethod("markAsArchived", String.class).getAnnotation(Profiled.class);
      }

      var10000.doPerfLogging(var10001, (Profiled)var10002);
   }

   @Profiled(
      logFailuresSeparately = true,
      tag = "0.ExecutorIntegrationModuleV4#markAsDelivered"
   )
   public void markAsDelivered(String rid) throws IntegrationModuleException {
      JoinPoint var8 = Factory.makeJP(ajc$tjp_5, this, this, rid);
      TimingAspect var10000 = TimingAspect.aspectOf();
      Object[] var9 = new Object[]{this, rid, var8};
      ProceedingJoinPoint var10001 = (new ExecutorIntegrationModuleV4Impl$AjcClosure11(var9)).linkClosureAndJoinPoint(69648);
      Annotation var10002 = ajc$anno$5;
      if (var10002 == null) {
         var10002 = ajc$anno$5 = ExecutorIntegrationModuleV4Impl.class.getDeclaredMethod("markAsDelivered", String.class).getAnnotation(Profiled.class);
      }

      var10000.doPerfLogging(var10001, (Profiled)var10002);
   }

   @Profiled(
      logFailuresSeparately = true,
      tag = "0.ExecutorIntegrationModuleV4#markAsUnDelivered"
   )
   public void markAsUndelivered(String rid) throws IntegrationModuleException {
      JoinPoint var8 = Factory.makeJP(ajc$tjp_6, this, this, rid);
      TimingAspect var10000 = TimingAspect.aspectOf();
      Object[] var9 = new Object[]{this, rid, var8};
      ProceedingJoinPoint var10001 = (new ExecutorIntegrationModuleV4Impl$AjcClosure13(var9)).linkClosureAndJoinPoint(69648);
      Annotation var10002 = ajc$anno$6;
      if (var10002 == null) {
         var10002 = ajc$anno$6 = ExecutorIntegrationModuleV4Impl.class.getDeclaredMethod("markAsUndelivered", String.class).getAnnotation(Profiled.class);
      }

      var10000.doPerfLogging(var10001, (Profiled)var10002);
   }

   @Profiled(
      logFailuresSeparately = true,
      tag = "0.ExecutorIntegrationModuleV4#revokePrescription"
   )
   public void revokePrescription(String rid, String reason) throws IntegrationModuleException {
      JoinPoint var10 = Factory.makeJP(ajc$tjp_7, this, this, rid, reason);
      TimingAspect var10000 = TimingAspect.aspectOf();
      Object[] var11 = new Object[]{this, rid, reason, var10};
      ProceedingJoinPoint var10001 = (new ExecutorIntegrationModuleV4Impl$AjcClosure15(var11)).linkClosureAndJoinPoint(69648);
      Annotation var10002 = ajc$anno$7;
      if (var10002 == null) {
         var10002 = ajc$anno$7 = ExecutorIntegrationModuleV4Impl.class.getDeclaredMethod("revokePrescription", String.class, String.class).getAnnotation(Profiled.class);
      }

      var10000.doPerfLogging(var10001, (Profiled)var10002);
   }

   @Profiled(
      logFailuresSeparately = true,
      tag = "0.ExecutorIntegrationModuleV4#listNotifications"
   )
   public List<ListNotificationsItem> listNotifications(boolean readFlag) throws IntegrationModuleException {
      JoinPoint var8 = Factory.makeJP(ajc$tjp_8, this, this, Conversions.booleanObject(readFlag));
      TimingAspect var10000 = TimingAspect.aspectOf();
      Object[] var9 = new Object[]{this, Conversions.booleanObject(readFlag), var8};
      ProceedingJoinPoint var10001 = (new ExecutorIntegrationModuleV4Impl$AjcClosure17(var9)).linkClosureAndJoinPoint(69648);
      Annotation var10002 = ajc$anno$8;
      if (var10002 == null) {
         var10002 = ajc$anno$8 = ExecutorIntegrationModuleV4Impl.class.getDeclaredMethod("listNotifications", Boolean.TYPE).getAnnotation(Profiled.class);
      }

      return (List)var10000.doPerfLogging(var10001, (Profiled)var10002);
   }

   @Profiled(
      logFailuresSeparately = true,
      tag = "0.ExecutorIntegrationModuleV4#createFeedback"
   )
   public void createFeedback(String prescriberId, String rid, byte[] feedbackText) throws IntegrationModuleException {
      StaticPart var10000 = ajc$tjp_9;
      Object[] var15 = new Object[]{prescriberId, rid, feedbackText};
      JoinPoint var14 = Factory.makeJP(var10000, this, this, var15);
      TimingAspect var17 = TimingAspect.aspectOf();
      Object[] var16 = new Object[]{this, prescriberId, rid, feedbackText, var14};
      ProceedingJoinPoint var10001 = (new ExecutorIntegrationModuleV4Impl$AjcClosure19(var16)).linkClosureAndJoinPoint(69648);
      Annotation var10002 = ajc$anno$9;
      if (var10002 == null) {
         var10002 = ajc$anno$9 = ExecutorIntegrationModuleV4Impl.class.getDeclaredMethod("createFeedback", String.class, String.class, byte[].class).getAnnotation(Profiled.class);
      }

      var17.doPerfLogging(var10001, (Profiled)var10002);
   }

   @Profiled(
      logFailuresSeparately = true,
      tag = "0.ExecutorIntegrationModuleV4#getData(ListReservationsParam)"
   )
   public ListReservationsResult getData(ListReservationsParam param) throws IntegrationModuleException {
      JoinPoint var9 = Factory.makeJP(ajc$tjp_10, this, this, param);
      TimingAspect var10000 = TimingAspect.aspectOf();
      Object[] var10 = new Object[]{this, param, var9};
      ProceedingJoinPoint var10001 = (new ExecutorIntegrationModuleV4Impl$AjcClosure21(var10)).linkClosureAndJoinPoint(69648);
      Annotation var10002 = ajc$anno$10;
      if (var10002 == null) {
         var10002 = ajc$anno$10 = ExecutorIntegrationModuleV4Impl.class.getDeclaredMethod("getData", ListReservationsParam.class).getAnnotation(Profiled.class);
      }

      return (ListReservationsResult)var10000.doPerfLogging(var10001, (Profiled)var10002);
   }

   protected ListReservationsRequest getListReservations(ListReservationsParam param) throws IntegrationModuleException {
      param.setSymmKey(this.getSymmKey().getEncoded());
      param.setStartDate(param.getStartDate() == null ? this.readLastDateToDisk() : param.getStartDate());
      ListReservationsRequest request = new ListReservationsRequest();
      request.setSecuredListReservationsRequest(this.createSecuredContentType(this.getSealedData(param)));
      request.setProgramId(PropertyHandler.getInstance().getProperty("programIdentification"));
      request.setIssueInstant(new DateTime());
      request.setId(this.getId());
      return request;
   }

   private byte[] getSealedData(ListReservationsParam listReservationsParam) throws IntegrationModuleException {
      listReservationsParam.setSymmKey(this.getSymmKey().getEncoded());
      return this.sealForRecipe(listReservationsParam, ListReservationsParam.class);
   }

   private ListReservationsResult unsealListReservationsResponse(ListReservationsResponse response) {
      MarshallerHelper<ListReservationsResult, Object> marshaller = new MarshallerHelper(ListReservationsResult.class, Object.class);
      ListReservationsResult result = (ListReservationsResult)marshaller.unsealWithSymmKey(response.getSecuredListReservationsResponse().getSecuredContent(), this.getSymmKey());
      return result;
   }

   private CreateFeedbackResult unsealCreateFeedbackResponse(CreateFeedbackResponse response) {
      MarshallerHelper<CreateFeedbackResult, Object> marshaller = new MarshallerHelper(CreateFeedbackResult.class, Object.class);
      CreateFeedbackResult result = (CreateFeedbackResult)marshaller.unsealWithSymmKey(response.getSecuredCreateFeedbackResponse().getSecuredContent(), this.getSymmKey());
      return result;
   }

   @Profiled(
      logFailuresSeparately = true,
      tag = "0.ExecutorIntegrationModuleV4#getData(ListRidsInProcessParam)"
   )
   public ListRidsInProcessResult getData(ListRidsInProcessParam param) throws IntegrationModuleException {
      JoinPoint var6 = Factory.makeJP(ajc$tjp_11, this, this, param);
      TimingAspect var10000 = TimingAspect.aspectOf();
      Object[] var7 = new Object[]{this, param, var6};
      ProceedingJoinPoint var10001 = (new ExecutorIntegrationModuleV4Impl$AjcClosure23(var7)).linkClosureAndJoinPoint(69648);
      Annotation var10002 = ajc$anno$11;
      if (var10002 == null) {
         var10002 = ajc$anno$11 = ExecutorIntegrationModuleV4Impl.class.getDeclaredMethod("getData", ListRidsInProcessParam.class).getAnnotation(Profiled.class);
      }

      return (ListRidsInProcessResult)var10000.doPerfLogging(var10001, (Profiled)var10002);
   }

   private ListRidsInProcessRequest getListRidsInProcess(ListRidsInProcessParam listRidsInProcessParam) throws IntegrationModuleException {
      listRidsInProcessParam.setSymmKey(this.getSymmKey().getEncoded());
      ListRidsInProcessRequest request = new ListRidsInProcessRequest();
      request.setSecuredListRidsInProcessRequest(this.createSecuredContentType(this.getSealedData(listRidsInProcessParam)));
      request.setProgramId(PropertyHandler.getInstance().getProperty("programIdentification"));
      request.setIssueInstant(new DateTime());
      request.setId(this.getId());
      return request;
   }

   private byte[] getSealedData(ListRidsInProcessParam listRidsInProcessParam) throws IntegrationModuleException {
      return this.sealForRecipe(listRidsInProcessParam, ListRidsInProcessParam.class);
   }

   @Profiled(
      logFailuresSeparately = true,
      tag = "0.ExecutorIntegrationModuleV4#putData(PutAllRidInProcessParam)"
   )
   public PutRidsInProcessResult putData(PutRidsInProcessParam param) throws IntegrationModuleException {
      JoinPoint var6 = Factory.makeJP(ajc$tjp_12, this, this, param);
      TimingAspect var10000 = TimingAspect.aspectOf();
      Object[] var7 = new Object[]{this, param, var6};
      ProceedingJoinPoint var10001 = (new ExecutorIntegrationModuleV4Impl$AjcClosure25(var7)).linkClosureAndJoinPoint(69648);
      Annotation var10002 = ajc$anno$12;
      if (var10002 == null) {
         var10002 = ajc$anno$12 = ExecutorIntegrationModuleV4Impl.class.getDeclaredMethod("putData", PutRidsInProcessParam.class).getAnnotation(Profiled.class);
      }

      return (PutRidsInProcessResult)var10000.doPerfLogging(var10001, (Profiled)var10002);
   }

   protected PutRidsInProcessRequest getPutAllRidInProcess(PutRidsInProcessParam putRidsInProcessParam) throws IntegrationModuleException {
      putRidsInProcessParam.setSymmKey(this.getSymmKey().getEncoded());
      PutRidsInProcessRequest request = new PutRidsInProcessRequest();
      request.setSecuredPutRidsInProcessRequest(this.createSecuredContentType(this.getSealedData(putRidsInProcessParam)));
      request.setProgramId(PropertyHandler.getInstance().getProperty("programIdentification"));
      request.setId(this.getId());
      request.setIssueInstant(new DateTime());
      return request;
   }

   private byte[] getSealedData(PutRidsInProcessParam putRidsInProcessParam) throws IntegrationModuleException {
      putRidsInProcessParam.setSymmKey(this.getSymmKey().getEncoded());
      return this.sealForRecipe(putRidsInProcessParam, PutRidsInProcessParam.class);
   }

   private PutRidsInProcessResult unsealPutRidsInProcessResponse(PutRidsInProcessResponse response) {
      MarshallerHelper<PutRidsInProcessResult, Object> marshaller = new MarshallerHelper(PutRidsInProcessResult.class, Object.class);
      PutRidsInProcessResult result = (PutRidsInProcessResult)marshaller.unsealWithSymmKey(response.getSecuredPutRidsInProcessResponse().getSecuredContent(), this.getSymmKey());
      return result;
   }

   private ListRidsInProcessResult unsealListRidsInProcessResponse(ListRidsInProcessResponse response) {
      MarshallerHelper<ListRidsInProcessResult, Object> marshaller = new MarshallerHelper(ListRidsInProcessResult.class, Object.class);
      ListRidsInProcessResult result = (ListRidsInProcessResult)marshaller.unsealWithSymmKey(response.getSecuredListRidsInProcessResponse().getSecuredContent(), this.getSymmKey());
      return result;
   }

   @Profiled(
      logFailuresSeparately = true,
      tag = "0.ExecutorIntegrationModuleV4#getAndMarkAsDelivered"
   )
   public GetPrescriptionForExecutorResult getAndMarkAsDelivered(String rid) throws IntegrationModuleException {
      JoinPoint var4 = Factory.makeJP(ajc$tjp_13, this, this, rid);
      TimingAspect var10000 = TimingAspect.aspectOf();
      Object[] var5 = new Object[]{this, rid, var4};
      ProceedingJoinPoint var10001 = (new ExecutorIntegrationModuleV4Impl$AjcClosure27(var5)).linkClosureAndJoinPoint(69648);
      Annotation var10002 = ajc$anno$13;
      if (var10002 == null) {
         var10002 = ajc$anno$13 = ExecutorIntegrationModuleV4Impl.class.getDeclaredMethod("getAndMarkAsDelivered", String.class).getAnnotation(Profiled.class);
      }

      return (GetPrescriptionForExecutorResult)var10000.doPerfLogging(var10001, (Profiled)var10002);
   }

   private void getPrescriptionAsync(final String rid) {
      (new Thread(new Runnable() {
         public void run() {
            try {
               ExecutorIntegrationModuleV4Impl.this.getPrescription(rid);
            } catch (IntegrationModuleException var2) {
               throw new RuntimeException(var2);
            }
         }
      })).start();
   }

   private void markAsDeliveredAsync(final String rid) {
      (new Thread(new Runnable() {
         public void run() {
            try {
               ExecutorIntegrationModuleV4Impl.this.markAsDelivered(rid);
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
               ExecutorIntegrationModuleV4Impl.this.markAsArchived(rid);
            } catch (IntegrationModuleException var2) {
               throw new RuntimeException(var2);
            }
         }
      })).start();
   }

   public ListRelationsResult getData(ListRelationsParam patientRelationParam) {
      ApplicationConfig.getInstance().assertValidPharmacySession();
      ValidationUtils.validatePatientId(patientRelationParam.getMandateHolderId());

      try {
         ListRelationsRequest request = this.getListPatientRelation(patientRelationParam);

         try {
            ListRelationsResponse dataResponse = RecipeExecutorServiceV4Impl.getInstance().listRelations(request);
            ListRelationsResult unsealedResponse = this.unsealListRelationsResponse(dataResponse);
            this.checkStatus(unsealedResponse);
            sessionMap.put(new MultiKey(patientRelationParam.getExecutorId(), patientRelationParam.getPatientId()), unsealedResponse.getSession());
            return unsealedResponse;
         } catch (ClientTransportException var5) {
            throw new IntegrationModuleException(I18nHelper.getLabel("error.connection.executor"), var5);
         }
      } catch (Throwable var6) {
         Exceptionutils.errorHandler(var6);
         return null;
      }
   }

   private ListRelationsRequest getListPatientRelation(ListRelationsParam data) throws IntegrationModuleException {
      data.setSymmKey(this.getSymmKey().getEncoded());
      data.setSession((byte[])sessionMap.get(new MultiKey(data.getExecutorId(), data.getPatientId())));
      ListRelationsRequest request = new ListRelationsRequest();
      request.setSecuredListRelationsRequest(this.createSecuredContentType(this.getSealedData(data)));
      request.setIssueInstant(new DateTime());
      request.setProgramId(PropertyHandler.getInstance().getProperty("programIdentification"));
      request.setId(this.getId());
      return request;
   }

   private byte[] getSealedData(ListRelationsParam data) throws IntegrationModuleException {
      data.setSymmKey(this.getSymmKey().getEncoded());
      return this.sealForRecipe(data, ListRelationsParam.class);
   }

   private ListRelationsResult unsealListRelationsResponse(ListRelationsResponse response) {
      MarshallerHelper<ListRelationsResult, Object> marshaller = new MarshallerHelper(ListRelationsResult.class, Object.class);
      ListRelationsResult result = (ListRelationsResult)marshaller.unsealWithSymmKey(response.getSecuredListRelationsResponse().getSecuredContent(), this.getSymmKey());
      return result;
   }

   // $FF: synthetic method
   static final GetPrescriptionStatusResult getData_aroundBody0(ExecutorIntegrationModuleV4Impl ajc$this, GetPrescriptionStatusParam param, JoinPoint var2) {
      RidValidator.validateRid(param.getRid());
      ApplicationConfig.getInstance().assertValidPharmacySession();
      GetPrescriptionStatusRequest getPrescriptionStatus = ajc$this.getPrescriptionStatus(param);

      try {
         try {
            GetPrescriptionStatusResponse response = RecipeExecutorServiceV4Impl.getInstance().getPrescriptionStatus(getPrescriptionStatus);
            GetPrescriptionStatusResult result = ajc$this.unsealGetPrescriptionStatusResponse(response);
            ajc$this.checkStatus(result);
            return result;
         } catch (ClientTransportException var8) {
            throw new IntegrationModuleException(I18nHelper.getLabel("error.connection.executor"), var8);
         }
      } catch (Throwable var9) {
         Exceptionutils.errorHandler(var9, getPrescriptionStatus.getId());
         return null;
      }
   }

   // $FF: synthetic method
   static final ListRidsHistoryResult getData_aroundBody2(ExecutorIntegrationModuleV4Impl ajc$this, ListRidsHistoryParam param, JoinPoint var2) {
      ApplicationConfig.getInstance().assertValidPharmacySession();
      ValidationUtils.validatePatientId(param.getPatientId());
      ListRidsHistoryRequest listPrescriptionHistory = ajc$this.getListRidsHistory(param);

      try {
         try {
            ListRidsHistoryResponse response = RecipeExecutorServiceV4Impl.getInstance().listRidsHistory(listPrescriptionHistory);
            ListRidsHistoryResult result = ajc$this.unsealListRidsHistoryResponse(response);
            ajc$this.checkStatus(result);
            sessionMap.put(new MultiKey(param.getExecutorId(), param.getPatientId()), result.getSession());
            return result;
         } catch (ClientTransportException var8) {
            throw new IntegrationModuleException(I18nHelper.getLabel("error.connection.executor"), var8);
         }
      } catch (Throwable var9) {
         Exceptionutils.errorHandler(var9, listPrescriptionHistory.getId());
         return null;
      }
   }

   // $FF: synthetic method
   static final GetPrescriptionForExecutorResult getPrescription_aroundBody4(ExecutorIntegrationModuleV4Impl ajc$this, String rid, JoinPoint var2) {
      RidValidator.validateRid(rid);
      ApplicationConfig.getInstance().assertValidPharmacySession();
      InsurabilityHandler.setInsurability((String)null);
      InsurabilityHandler.setMessageId((String)null);
      String guid = ajc$this.getId();

      try {
         byte[] sealedGetPrescriptionForExecutorParam = ajc$this.getSealedGetPrescriptionForExecutorParam(rid);
         GetPrescriptionForExecutorRequest request = new GetPrescriptionForExecutorRequest();
         request.setDisablePatientInsurabilityCheckParam(Boolean.parseBoolean(ajc$this.getPropertyHandler().getProperty("patient.insurability.disable")));
         request.setSecuredGetPrescriptionForExecutorRequest(ajc$this.createSecuredContentType(sealedGetPrescriptionForExecutorParam));
         request.setIssueInstant(new DateTime());
         request.setProgramId(PropertyHandler.getInstance().getProperty("programIdentification"));
         request.setId(guid);
         GetPrescriptionForExecutorResponse response = null;

         try {
            response = RecipeExecutorServiceV4Impl.getInstance().getPrescriptionForExecutor(request);
         } catch (ClientTransportException var14) {
            throw new IntegrationModuleException(I18nHelper.getLabel("error.connection.executor"), var14);
         }

         byte[] securedContent = response.getSecuredGetPrescriptionForExecutorResponse().getSecuredContent();
         GetPrescriptionForExecutorResult finalResult = ajc$this.createGetPrescriptionForExecutorResult(securedContent);
         ajc$this.checkStatus(finalResult);
         getPrescriptionCache().put(rid, finalResult);
         return finalResult;
      } catch (Throwable var15) {
         Exceptionutils.errorHandler(var15, guid);
         return null;
      }
   }

   // $FF: synthetic method
   static final GetOpenPrescriptionForExecutor decryptGetOpenPrescriptionForExecutor_aroundBody6(ExecutorIntegrationModuleV4Impl ajc$this, GetOpenPrescriptionForExecutor gopfe, JoinPoint var2) {
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
   static final void markAsArchived_aroundBody8(ExecutorIntegrationModuleV4Impl ajc$this, String rid, JoinPoint var2) {
      RidValidator.validateRid(rid);
      ApplicationConfig.getInstance().assertValidPharmacySession();
      MarkAsArchivedRequest request = new MarkAsArchivedRequest();

      try {
         byte[] sealedMarkAsArchivedParam = ajc$this.getSealedMarkAsArchivedParam(rid);
         request.setSecuredMarkAsArchivedRequest(ajc$this.createSecuredContentType(sealedMarkAsArchivedParam));
         request.setProgramId(PropertyHandler.getInstance().getProperty("programIdentification"));
         request.setIssueInstant(new DateTime());
         request.setId(ajc$this.getId());

         try {
            MarkAsArchivedResponse response = RecipeExecutorServiceV4Impl.getInstance().markAsArchived(request);
            MarshallerHelper marshaller = new MarshallerHelper(MarkAsArchivedResult.class, Object.class);
            MarkAsArchivedResult result = (MarkAsArchivedResult)marshaller.unsealWithSymmKey(response.getSecuredMarkAsArchivedResponse().getSecuredContent(), ajc$this.getSymmKey());
            ajc$this.checkStatus(result);
         } catch (ClientTransportException var12) {
            throw new IntegrationModuleException(I18nHelper.getLabel("error.connection.executor"), var12);
         }
      } catch (Throwable var13) {
         Exceptionutils.errorHandler(var13, request.getId());
      }

   }

   // $FF: synthetic method
   static final void markAsDelivered_aroundBody10(ExecutorIntegrationModuleV4Impl ajc$this, String rid, JoinPoint var2) {
      RidValidator.validateRid(rid);
      ApplicationConfig.getInstance().assertValidPharmacySession();
      MarkAsDeliveredRequest request = new MarkAsDeliveredRequest();

      try {
         byte[] sealedMarkAsDeliveredParam = ajc$this.getSealedMarkAsDeliveredParam(rid);
         request.setSecuredMarkAsDeliveredRequest(ajc$this.createSecuredContentType(sealedMarkAsDeliveredParam));
         request.setProgramId(PropertyHandler.getInstance().getProperty("programIdentification"));
         request.setIssueInstant(new DateTime());
         request.setId(ajc$this.getId());
         MarkAsDeliveredResponse response = null;

         try {
            response = RecipeExecutorServiceV4Impl.getInstance().markAsDelivered(request);
            MarshallerHelper marshaller = new MarshallerHelper(MarkAsDeliveredResult.class, Object.class);
            MarkAsDeliveredResult result = (MarkAsDeliveredResult)marshaller.unsealWithSymmKey(response.getSecuredMarkAsDeliveredResponse().getSecuredContent(), ajc$this.getSymmKey());
            ajc$this.checkStatus(result);
         } catch (ClientTransportException var12) {
            throw new IntegrationModuleException(I18nHelper.getLabel("error.connection.executor"), var12);
         }
      } catch (Throwable var13) {
         Exceptionutils.errorHandler(var13, request.getId());
      }

   }

   // $FF: synthetic method
   static final void markAsUndelivered_aroundBody12(ExecutorIntegrationModuleV4Impl ajc$this, String rid, JoinPoint var2) {
      RidValidator.validateRid(rid);
      ApplicationConfig.getInstance().assertValidPharmacySession();
      MarkAsUnDeliveredRequest request = new MarkAsUnDeliveredRequest();
      request.setProgramId(PropertyHandler.getInstance().getProperty("programIdentification"));
      request.setId(ajc$this.getId());

      try {
         byte[] sealedMarkAsUnDeliveredParam = ajc$this.getSealedMarkAsUnDeliveredParam(rid);
         request.setSecuredMarkAsUnDeliveredRequest(ajc$this.createSecuredContentType(sealedMarkAsUnDeliveredParam));
         request.setProgramId(PropertyHandler.getInstance().getProperty("programIdentification"));
         request.setIssueInstant(new DateTime());
         request.setId(ajc$this.getId());
         MarkAsUnDeliveredResponse response = null;

         try {
            response = RecipeExecutorServiceV4Impl.getInstance().markAsUnDelivered(request);
            MarshallerHelper marshaller = new MarshallerHelper(MarkAsUndeliveredResult.class, Object.class);
            MarkAsUndeliveredResult result = (MarkAsUndeliveredResult)marshaller.unsealWithSymmKey(response.getSecuredMarkAsUnDeliveredResponse().getSecuredContent(), ajc$this.getSymmKey());
            ajc$this.checkStatus(result);
            if (getPrescriptionCache().containsKey(rid)) {
               getPrescriptionCache().remove(rid);
            }
         } catch (ClientTransportException var12) {
            throw new IntegrationModuleException(I18nHelper.getLabel("error.connection.executor"), var12);
         }
      } catch (Throwable var13) {
         Exceptionutils.errorHandler(var13, request.getId());
      }

   }

   // $FF: synthetic method
   static final void revokePrescription_aroundBody14(ExecutorIntegrationModuleV4Impl ajc$this, String rid, String reason, JoinPoint var3) {
      RidValidator.validateRid(rid);
      ApplicationConfig.getInstance().assertValidPharmacySession();
      RevokePrescriptionRequest request = new RevokePrescriptionRequest();

      try {
         byte[] sealedRevokePrescriptionParam = ajc$this.getSealedRevokePrescriptionParam(rid, reason);
         request.setSecuredRevokePrescriptionRequest(ajc$this.createSecuredContentType(sealedRevokePrescriptionParam));
         request.setProgramId(PropertyHandler.getInstance().getProperty("programIdentification"));
         request.setIssueInstant(new DateTime());
         request.setId(ajc$this.getId());
         RevokePrescriptionResponse response = null;

         try {
            response = RecipeExecutorServiceV4Impl.getInstance().revokePrescriptionForExecutor(request);
            MarshallerHelper marshaller = new MarshallerHelper(RevokePrescriptionResult.class, Object.class);
            RevokePrescriptionResult result = (RevokePrescriptionResult)marshaller.unsealWithSymmKey(response.getSecuredRevokePrescriptionResponse().getSecuredContent(), ajc$this.getSymmKey());
            ajc$this.checkStatus(result);
         } catch (ClientTransportException var13) {
            throw new IntegrationModuleException(I18nHelper.getLabel("error.connection.executor"), var13);
         }
      } catch (Throwable var14) {
         Exceptionutils.errorHandler(var14, request.getId());
      }

   }

   // $FF: synthetic method
   static final List listNotifications_aroundBody16(ExecutorIntegrationModuleV4Impl ajc$this, boolean readFlag, JoinPoint var2) {
      ApplicationConfig.getInstance().assertValidPharmacySession();
      ListNotificationsRequest request = new ListNotificationsRequest();

      try {
         byte[] sealedListNotificationsParam = ajc$this.getSealedListNotificationsParam(readFlag);
         request.setSecuredListNotificationsRequest(ajc$this.createSecuredContentType(sealedListNotificationsParam));
         request.setProgramId(PropertyHandler.getInstance().getProperty("programIdentification"));
         request.setIssueInstant(new DateTime());
         request.setId(ajc$this.getId());
         ListNotificationsResponse response = null;

         try {
            response = RecipeExecutorServiceV4Impl.getInstance().listNotifications(request);
            MarshallerHelper marshaller = new MarshallerHelper(ListNotificationsResult.class, Object.class);
            ListNotificationsResult result = (ListNotificationsResult)marshaller.unsealWithSymmKey(response.getSecuredListNotificationsResponse().getSecuredContent(), ajc$this.getSymmKey());
            ajc$this.checkStatus(result);
         } catch (ClientTransportException var12) {
            throw new IntegrationModuleException(I18nHelper.getLabel("error.connection.executor"), var12);
         }

         byte[] securedContent = response.getSecuredListNotificationsResponse().getSecuredContent();
         return ajc$this.createListNotificationItems(securedContent);
      } catch (Throwable var13) {
         Exceptionutils.errorHandler(var13, request.getId());
         return null;
      }
   }

   // $FF: synthetic method
   static final void createFeedback_aroundBody18(ExecutorIntegrationModuleV4Impl ajc$this, String prescriberId, String rid, byte[] feedbackText, JoinPoint var4) {
      RidValidator.validateRid(rid);
      ApplicationConfig.getInstance().assertValidPharmacySession();
      CreateFeedbackRequest request = new CreateFeedbackRequest();

      try {
         ajc$this.getKmehrHelper().assertValidFeedback(feedbackText);
         ArrayList etkRecipients = new ArrayList();

         try {
            etkRecipients.addAll(ajc$this.getEtkHelper().getEtks(KgssIdentifierType.NIHII, prescriberId));
         } catch (Exception var20) {
            try {
               etkRecipients.addAll(ajc$this.getEtkHelper().getEtks(KgssIdentifierType.NIHII_HOSPITAL, prescriberId));
            } catch (Exception var19) {
               Exceptionutils.errorHandler(var19, var20.getMessage() + var19.getMessage());
            }
         }

         for(int i = 0; i < etkRecipients.size(); ++i) {
            EncryptionToken etkRecipient = (EncryptionToken)etkRecipients.get(i);
            byte[] sealedCreateFeedbackParam = ajc$this.getSealedCreateFeedbackParam(feedbackText, etkRecipient, rid, prescriberId);
            request.setSecuredCreateFeedbackRequest(ajc$this.createSecuredContentType(sealedCreateFeedbackParam));
            request.setProgramId(PropertyHandler.getInstance().getProperty("programIdentification"));
            request.setIssueInstant(new DateTime());
            request.setId(ajc$this.getId());

            try {
               CreateFeedbackResponse response = RecipeExecutorServiceV4Impl.getInstance().createFeedback(request);
               CreateFeedbackResult result = ajc$this.unsealCreateFeedbackResponse(response);
               ajc$this.checkStatus(result);
            } catch (ClientTransportException var18) {
               throw new IntegrationModuleException(I18nHelper.getLabel("error.connection.executor"), var18);
            }
         }
      } catch (Throwable var21) {
         Exceptionutils.errorHandler(var21, request.getId());
      }

   }

   // $FF: synthetic method
   static final ListReservationsResult getData_aroundBody20(ExecutorIntegrationModuleV4Impl ajc$this, ListReservationsParam param, JoinPoint var2) {
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
               ListReservationsRequest request = ajc$this.getListReservations(param);
               ListReservationsResponse response = RecipeExecutorServiceV4Impl.getInstance().listReservations(request);
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
   static final ListRidsInProcessResult getData_aroundBody22(ExecutorIntegrationModuleV4Impl ajc$this, ListRidsInProcessParam param, JoinPoint var2) {
      ApplicationConfig.getInstance().assertValidPharmacySession();

      try {
         ListRidsInProcessRequest request = ajc$this.getListRidsInProcess(param);

         try {
            ListRidsInProcessResponse response = RecipeExecutorServiceV4Impl.getInstance().listRidsInProcess(request);
            ListRidsInProcessResult result = ajc$this.unsealListRidsInProcessResponse(response);
            ajc$this.checkStatus(result);
            return result;
         } catch (ClientTransportException var8) {
            throw new IntegrationModuleException(I18nHelper.getLabel("error.connection.executor"), var8);
         }
      } catch (Throwable var9) {
         Exceptionutils.errorHandler(var9);
         return null;
      }
   }

   // $FF: synthetic method
   static final PutRidsInProcessResult putData_aroundBody24(ExecutorIntegrationModuleV4Impl ajc$this, PutRidsInProcessParam param, JoinPoint var2) {
      ApplicationConfig.getInstance().assertValidPharmacySession();
      PutRidsInProcessRequest request = ajc$this.getPutAllRidInProcess(param);

      try {
         try {
            PutRidsInProcessResponse response = RecipeExecutorServiceV4Impl.getInstance().putRidsInProcess(request);
            PutRidsInProcessResult result = ajc$this.unsealPutRidsInProcessResponse(response);
            ajc$this.checkStatus(result);
            return result;
         } catch (ClientTransportException var8) {
            throw new IntegrationModuleException(I18nHelper.getLabel("error.connection.executor"), var8);
         }
      } catch (Throwable var9) {
         Exceptionutils.errorHandler(var9, request.getId());
         return null;
      }
   }

   // $FF: synthetic method
   static final GetPrescriptionForExecutorResult getAndMarkAsDelivered_aroundBody26(ExecutorIntegrationModuleV4Impl ajc$this, String rid, JoinPoint var2) {
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
      Factory var0 = new Factory("ExecutorIntegrationModuleV4Impl.java", ExecutorIntegrationModuleV4Impl.class);
      ajc$tjp_0 = var0.makeSJP("method-execution", var0.makeMethodSig("1", "getData", "be.business.connector.recipe.executor.ExecutorIntegrationModuleV4Impl", "be.recipe.services.executor.GetPrescriptionStatusParam", "param", "be.business.connector.core.exceptions.IntegrationModuleException", "be.recipe.services.executor.GetPrescriptionStatusResult"), 209);
      ajc$tjp_1 = var0.makeSJP("method-execution", var0.makeMethodSig("1", "getData", "be.business.connector.recipe.executor.ExecutorIntegrationModuleV4Impl", "be.recipe.services.executor.ListRidsHistoryParam", "param", "be.business.connector.core.exceptions.IntegrationModuleException", "be.recipe.services.executor.ListRidsHistoryResult"), 251);
      ajc$tjp_10 = var0.makeSJP("method-execution", var0.makeMethodSig("1", "getData", "be.business.connector.recipe.executor.ExecutorIntegrationModuleV4Impl", "be.recipe.services.executor.ListReservationsParam", "param", "be.business.connector.core.exceptions.IntegrationModuleException", "be.recipe.services.executor.ListReservationsResult"), 632);
      ajc$tjp_11 = var0.makeSJP("method-execution", var0.makeMethodSig("1", "getData", "be.business.connector.recipe.executor.ExecutorIntegrationModuleV4Impl", "be.recipe.services.executor.ListRidsInProcessParam", "param", "be.business.connector.core.exceptions.IntegrationModuleException", "be.recipe.services.executor.ListRidsInProcessResult"), 725);
      ajc$tjp_12 = var0.makeSJP("method-execution", var0.makeMethodSig("1", "putData", "be.business.connector.recipe.executor.ExecutorIntegrationModuleV4Impl", "be.recipe.services.executor.PutRidsInProcessParam", "param", "be.business.connector.core.exceptions.IntegrationModuleException", "be.recipe.services.executor.PutRidsInProcessResult"), 782);
      ajc$tjp_13 = var0.makeSJP("method-execution", var0.makeMethodSig("1", "getAndMarkAsDelivered", "be.business.connector.recipe.executor.ExecutorIntegrationModuleV4Impl", "java.lang.String", "rid", "be.business.connector.core.exceptions.IntegrationModuleException", "be.business.connector.recipe.executor.domain.GetPrescriptionForExecutorResult"), 869);
      ajc$tjp_2 = var0.makeSJP("method-execution", var0.makeMethodSig("1", "getPrescription", "be.business.connector.recipe.executor.ExecutorIntegrationModuleV4Impl", "java.lang.String", "rid", "be.business.connector.core.exceptions.IntegrationModuleException", "be.business.connector.recipe.executor.domain.GetPrescriptionForExecutorResult"), 307);
      ajc$tjp_3 = var0.makeSJP("method-execution", var0.makeMethodSig("1", "decryptGetOpenPrescriptionForExecutor", "be.business.connector.recipe.executor.ExecutorIntegrationModuleV4Impl", "be.recipe.services.executor.GetOpenPrescriptionForExecutor", "gopfe", "be.business.connector.core.exceptions.IntegrationModuleException", "be.recipe.services.executor.GetOpenPrescriptionForExecutor"), 383);
      ajc$tjp_4 = var0.makeSJP("method-execution", var0.makeMethodSig("1", "markAsArchived", "be.business.connector.recipe.executor.ExecutorIntegrationModuleV4Impl", "java.lang.String", "rid", "be.business.connector.core.exceptions.IntegrationModuleException", "void"), 414);
      ajc$tjp_5 = var0.makeSJP("method-execution", var0.makeMethodSig("1", "markAsDelivered", "be.business.connector.recipe.executor.ExecutorIntegrationModuleV4Impl", "java.lang.String", "rid", "be.business.connector.core.exceptions.IntegrationModuleException", "void"), 448);
      ajc$tjp_6 = var0.makeSJP("method-execution", var0.makeMethodSig("1", "markAsUndelivered", "be.business.connector.recipe.executor.ExecutorIntegrationModuleV4Impl", "java.lang.String", "rid", "be.business.connector.core.exceptions.IntegrationModuleException", "void"), 481);
      ajc$tjp_7 = var0.makeSJP("method-execution", var0.makeMethodSig("1", "revokePrescription", "be.business.connector.recipe.executor.ExecutorIntegrationModuleV4Impl", "java.lang.String:java.lang.String", "rid:reason", "be.business.connector.core.exceptions.IntegrationModuleException", "void"), 518);
      ajc$tjp_8 = var0.makeSJP("method-execution", var0.makeMethodSig("1", "listNotifications", "be.business.connector.recipe.executor.ExecutorIntegrationModuleV4Impl", "boolean", "readFlag", "be.business.connector.core.exceptions.IntegrationModuleException", "java.util.List"), 552);
      ajc$tjp_9 = var0.makeSJP("method-execution", var0.makeMethodSig("1", "createFeedback", "be.business.connector.recipe.executor.ExecutorIntegrationModuleV4Impl", "java.lang.String:java.lang.String:[B", "prescriberId:rid:feedbackText", "be.business.connector.core.exceptions.IntegrationModuleException", "void"), 589);
   }
}
