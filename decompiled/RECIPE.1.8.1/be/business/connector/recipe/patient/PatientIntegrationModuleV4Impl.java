package be.business.connector.recipe.patient;

import be.business.connector.common.ApplicationConfig;
import be.business.connector.core.exceptions.IntegrationModuleException;
import be.business.connector.core.handlers.InsurabilityHandler;
import be.business.connector.core.utils.Exceptionutils;
import be.business.connector.core.utils.I18nHelper;
import be.business.connector.core.utils.MarshallerHelper;
import be.business.connector.core.utils.PropertyHandler;
import be.business.connector.recipe.patient.services.RecipePatientServiceV4Impl;
import be.business.connector.recipe.utils.RidValidator;
import be.fgov.ehealth.recipe.core.v4.SecuredContentType;
import be.fgov.ehealth.recipe.protocol.v4.CreateRelationRequest;
import be.fgov.ehealth.recipe.protocol.v4.CreateRelationResponse;
import be.fgov.ehealth.recipe.protocol.v4.CreateReservationRequest;
import be.fgov.ehealth.recipe.protocol.v4.CreateReservationResponse;
import be.fgov.ehealth.recipe.protocol.v4.GetPrescriptionRequest;
import be.fgov.ehealth.recipe.protocol.v4.GetPrescriptionResponse;
import be.fgov.ehealth.recipe.protocol.v4.GetPrescriptionStatusRequest;
import be.fgov.ehealth.recipe.protocol.v4.GetPrescriptionStatusResponse;
import be.fgov.ehealth.recipe.protocol.v4.GetVisionRequest;
import be.fgov.ehealth.recipe.protocol.v4.GetVisionResponse;
import be.fgov.ehealth.recipe.protocol.v4.ListOpenPrescriptionsRequest;
import be.fgov.ehealth.recipe.protocol.v4.ListOpenPrescriptionsResponse;
import be.fgov.ehealth.recipe.protocol.v4.ListOpenRidsRequest;
import be.fgov.ehealth.recipe.protocol.v4.ListOpenRidsResponse;
import be.fgov.ehealth.recipe.protocol.v4.ListRelationsRequest;
import be.fgov.ehealth.recipe.protocol.v4.ListRelationsResponse;
import be.fgov.ehealth.recipe.protocol.v4.ListRidsHistoryRequest;
import be.fgov.ehealth.recipe.protocol.v4.ListRidsHistoryResponse;
import be.fgov.ehealth.recipe.protocol.v4.PutFeedbackFlagRequest;
import be.fgov.ehealth.recipe.protocol.v4.PutFeedbackFlagResponse;
import be.fgov.ehealth.recipe.protocol.v4.PutVisionForPatientRequest;
import be.fgov.ehealth.recipe.protocol.v4.PutVisionForPatientResponse;
import be.fgov.ehealth.recipe.protocol.v4.RevokePrescriptionRequest;
import be.fgov.ehealth.recipe.protocol.v4.RevokePrescriptionResponse;
import be.fgov.ehealth.recipe.protocol.v4.RevokeRelationRequest;
import be.fgov.ehealth.recipe.protocol.v4.RevokeRelationResponse;
import be.recipe.services.patient.CreateRelationParam;
import be.recipe.services.patient.CreateRelationResult;
import be.recipe.services.patient.CreateReservationParam;
import be.recipe.services.patient.CreateReservationResult;
import be.recipe.services.patient.GetPrescriptionForPatientResult;
import be.recipe.services.patient.GetPrescriptionStatusParam;
import be.recipe.services.patient.GetPrescriptionStatusResult;
import be.recipe.services.patient.GetVisionParam;
import be.recipe.services.patient.GetVisionResult;
import be.recipe.services.patient.ListOpenRidsParam;
import be.recipe.services.patient.ListOpenRidsResult;
import be.recipe.services.patient.ListPatientPrescriptionsParam;
import be.recipe.services.patient.ListPatientPrescriptionsResult;
import be.recipe.services.patient.ListRelationsParam;
import be.recipe.services.patient.ListRelationsResult;
import be.recipe.services.patient.ListRidsHistoryParam;
import be.recipe.services.patient.ListRidsHistoryResult;
import be.recipe.services.patient.PutVisionParam;
import be.recipe.services.patient.PutVisionResult;
import be.recipe.services.patient.RevokePrescriptionResult;
import be.recipe.services.patient.RevokeRelationParam;
import be.recipe.services.patient.RevokeRelationResult;
import be.recipe.services.patient.UpdateFeedbackFlagParam;
import be.recipe.services.patient.UpdateFeedbackFlagResult;
import com.sun.xml.ws.client.ClientTransportException;
import java.lang.annotation.Annotation;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.JoinPoint.StaticPart;
import org.aspectj.runtime.reflect.Factory;
import org.joda.time.DateTime;
import org.perf4j.aop.Profiled;
import org.perf4j.log4j.aop.TimingAspect;

public class PatientIntegrationModuleV4Impl extends PatientIntegrationModuleImpl implements PatientIntegrationModuleV4 {
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

   public PatientIntegrationModuleV4Impl() throws IntegrationModuleException {
   }

   public void updateFeedbackFlag(String rid, boolean feedbackAllowed) throws IntegrationModuleException {
      RidValidator.validateRid(rid);
      ApplicationConfig.getInstance().assertValidSession();

      try {
         UpdateFeedbackFlagParam param = new UpdateFeedbackFlagParam();
         param.setAllowFeedback(feedbackAllowed);
         param.setRid(rid);
         param.setSymmKey(this.getSymmKey().getEncoded());
         PutFeedbackFlagRequest request = this.getPutFeedbackFlagRequest(param);

         try {
            PutFeedbackFlagResponse response = RecipePatientServiceV4Impl.getInstance().putFeedbackFlag(request);
            MarshallerHelper<UpdateFeedbackFlagResult, UpdateFeedbackFlagResult> helper2 = new MarshallerHelper(UpdateFeedbackFlagResult.class, UpdateFeedbackFlagResult.class);
            UpdateFeedbackFlagResult result = (UpdateFeedbackFlagResult)helper2.unsealWithSymmKey(response.getSecuredPutFeedbackFlagResponse().getSecuredContent(), this.getSymmKey());
            this.checkStatus(result);
         } catch (ClientTransportException var8) {
            throw new IntegrationModuleException(I18nHelper.getLabel("error.connection.prescriber"), var8);
         }
      } catch (Throwable var9) {
         Exceptionutils.errorHandler(var9);
      }

   }

   protected PutFeedbackFlagRequest getPutFeedbackFlagRequest(UpdateFeedbackFlagParam param) throws IntegrationModuleException {
      param.setSymmKey(this.getSymmKey().getEncoded());
      PutFeedbackFlagRequest request = new PutFeedbackFlagRequest();
      request.setSecuredPutFeedbackFlagRequest(this.createSecuredContentTypeV4(this.getSealedData(param)));
      request.setProgramId(PropertyHandler.getInstance().getProperty("programIdentification"));
      request.setIssueInstant(new DateTime());
      request.setId(this.getId());
      return request;
   }

   private byte[] getSealedData(UpdateFeedbackFlagParam request) throws IntegrationModuleException {
      request.setSymmKey(this.getSymmKey().getEncoded());
      return this.sealForRecipe(request, UpdateFeedbackFlagParam.class);
   }

   private SecuredContentType createSecuredContentTypeV4(byte[] content) {
      SecuredContentType secured = new SecuredContentType();
      secured.setSecuredContent(content);
      return secured;
   }

   public void revokePrescription(String rid, String reason) throws IntegrationModuleException {
      RidValidator.validateRid(rid);
      ApplicationConfig.getInstance().assertValidSession();

      try {
         byte[] sealedRevokePrescriptionParam = this.getSealedRevokePrescriptionParam(rid, reason);
         RevokePrescriptionRequest request = new RevokePrescriptionRequest();
         request.setSecuredRevokePrescriptionRequest(this.createSecuredContentTypeV4(sealedRevokePrescriptionParam));
         request.setProgramId(PropertyHandler.getInstance().getProperty("programIdentification"));
         request.setIssueInstant(new DateTime());
         request.setIssueInstant(new DateTime());
         request.setId(this.getId());

         try {
            RevokePrescriptionResponse response = RecipePatientServiceV4Impl.getInstance().revokePrescription(request);
            MarshallerHelper<RevokePrescriptionResult, RevokePrescriptionResult> helper = new MarshallerHelper(RevokePrescriptionResult.class, RevokePrescriptionResult.class);
            RevokePrescriptionResult result = (RevokePrescriptionResult)helper.unsealWithSymmKey(response.getSecuredRevokePrescriptionResponse().getSecuredContent(), this.getSymmKey());
            this.checkStatus(result);
         } catch (ClientTransportException var8) {
            throw new IntegrationModuleException(I18nHelper.getLabel("error.connection.executor"), var8);
         }
      } catch (Throwable var9) {
         Exceptionutils.errorHandler(var9);
      }

   }

   public GetPrescriptionForPatientResult getPrescription(String rid) throws IntegrationModuleException {
      RidValidator.validateRid(rid);
      ApplicationConfig.getInstance().assertValidSession();
      InsurabilityHandler.setInsurability((String)null);
      InsurabilityHandler.setMessageId((String)null);

      try {
         byte[] sealedContent = this.getSealedGetPrescriptionForPatientParam(rid);
         GetPrescriptionRequest request = new GetPrescriptionRequest();
         request.setSecuredGetPrescriptionRequest(this.createSecuredContentTypeV4(sealedContent));
         request.setProgramId(PropertyHandler.getInstance().getProperty("programIdentification"));
         request.setIssueInstant(new DateTime());
         request.setId(this.getId());
         GetPrescriptionResponse response = null;

         try {
            response = RecipePatientServiceV4Impl.getInstance().getPrescriptionForPatient(request);
         } catch (ClientTransportException var6) {
            throw new IntegrationModuleException(I18nHelper.getLabel("error.connection.executor"), var6);
         }

         GetPrescriptionForPatientResult finalResult = this.unsealPrescription(response.getSecuredGetPrescriptionResponse().getSecuredContent());
         return finalResult;
      } catch (Throwable var7) {
         Exceptionutils.errorHandler(var7);
         return null;
      }
   }

   @Profiled(
      logFailuresSeparately = true,
      tag = "0.PatientIntegrationModuleV4#getData(GetVisionParam)"
   )
   public GetVisionResult getData(GetVisionParam data) throws IntegrationModuleException {
      JoinPoint var7 = Factory.makeJP(ajc$tjp_0, this, this, data);
      TimingAspect var10000 = TimingAspect.aspectOf();
      Object[] var8 = new Object[]{this, data, var7};
      ProceedingJoinPoint var10001 = (new PatientIntegrationModuleV4Impl$AjcClosure1(var8)).linkClosureAndJoinPoint(69648);
      Annotation var10002 = ajc$anno$0;
      if (var10002 == null) {
         var10002 = ajc$anno$0 = PatientIntegrationModuleV4Impl.class.getDeclaredMethod("getData", GetVisionParam.class).getAnnotation(Profiled.class);
      }

      return (GetVisionResult)var10000.doPerfLogging(var10001, (Profiled)var10002);
   }

   private GetVisionResult unsealGetVisionResponse(GetVisionResponse getVisionResponse) {
      MarshallerHelper<GetVisionResult, Object> marshaller = new MarshallerHelper(GetVisionResult.class, Object.class);
      GetVisionResult result = (GetVisionResult)marshaller.unsealWithSymmKey(getVisionResponse.getSecuredGetVisionResponse().getSecuredContent(), this.getSymmKey());
      return result;
   }

   private PutVisionResult unsealPutVisionResponse(PutVisionForPatientResponse putVisionResponse) {
      MarshallerHelper<PutVisionResult, Object> marshaller = new MarshallerHelper(PutVisionResult.class, Object.class);
      PutVisionResult result = (PutVisionResult)marshaller.unsealWithSymmKey(putVisionResponse.getSecuredPutVisionForPatientResponse().getSecuredContent(), this.getSymmKey());
      return result;
   }

   private CreateRelationResult unsealCreateResponse(CreateRelationResponse response) {
      MarshallerHelper<CreateRelationResult, Object> marshaller = new MarshallerHelper(CreateRelationResult.class, Object.class);
      CreateRelationResult result = (CreateRelationResult)marshaller.unsealWithSymmKey(response.getSecuredCreateRelationResponse().getSecuredContent(), this.getSymmKey());
      return result;
   }

   private ListRelationsResult unsealListRelationsResponse(ListRelationsResponse response) {
      MarshallerHelper<ListRelationsResult, Object> marshaller = new MarshallerHelper(ListRelationsResult.class, Object.class);
      ListRelationsResult result = (ListRelationsResult)marshaller.unsealWithSymmKey(response.getSecuredListRelationsResponse().getSecuredContent(), this.getSymmKey());
      return result;
   }

   private RevokeRelationResult unsealRevokeRelationResponse(RevokeRelationResponse response) {
      MarshallerHelper<RevokeRelationResult, Object> marshaller = new MarshallerHelper(RevokeRelationResult.class, Object.class);
      RevokeRelationResult result = (RevokeRelationResult)marshaller.unsealWithSymmKey(response.getSecuredRevokeRelationResponse().getSecuredContent(), this.getSymmKey());
      return result;
   }

   private CreateReservationResult unsealCreateReservationResponse(CreateReservationResponse dataResponse) {
      MarshallerHelper<CreateReservationResult, Object> marshaller = new MarshallerHelper(CreateReservationResult.class, Object.class);
      CreateReservationResult result = (CreateReservationResult)marshaller.unsealWithSymmKey(dataResponse.getSecuredCreateReservationResponse().getSecuredContent(), this.getSymmKey());
      return result;
   }

   @Profiled(
      logFailuresSeparately = true,
      tag = "0.PatientIntegrationModuleV4#putVision"
   )
   public PutVisionResult putData(PutVisionParam putVisionParam) throws IntegrationModuleException {
      JoinPoint var7 = Factory.makeJP(ajc$tjp_1, this, this, putVisionParam);
      TimingAspect var10000 = TimingAspect.aspectOf();
      Object[] var8 = new Object[]{this, putVisionParam, var7};
      ProceedingJoinPoint var10001 = (new PatientIntegrationModuleV4Impl$AjcClosure3(var8)).linkClosureAndJoinPoint(69648);
      Annotation var10002 = ajc$anno$1;
      if (var10002 == null) {
         var10002 = ajc$anno$1 = PatientIntegrationModuleV4Impl.class.getDeclaredMethod("putData", PutVisionParam.class).getAnnotation(Profiled.class);
      }

      return (PutVisionResult)var10000.doPerfLogging(var10001, (Profiled)var10002);
   }

   @Profiled(
      logFailuresSeparately = true,
      tag = "0.PatientIntegrationModuleV4#putReservation"
   )
   public CreateReservationResult putData(CreateReservationParam param) throws IntegrationModuleException {
      JoinPoint var6 = Factory.makeJP(ajc$tjp_2, this, this, param);
      TimingAspect var10000 = TimingAspect.aspectOf();
      Object[] var7 = new Object[]{this, param, var6};
      ProceedingJoinPoint var10001 = (new PatientIntegrationModuleV4Impl$AjcClosure5(var7)).linkClosureAndJoinPoint(69648);
      Annotation var10002 = ajc$anno$2;
      if (var10002 == null) {
         var10002 = ajc$anno$2 = PatientIntegrationModuleV4Impl.class.getDeclaredMethod("putData", CreateReservationParam.class).getAnnotation(Profiled.class);
      }

      return (CreateReservationResult)var10000.doPerfLogging(var10001, (Profiled)var10002);
   }

   public ListPatientPrescriptionsResult listOpenPrescriptions() throws IntegrationModuleException {
      ApplicationConfig.getInstance().assertValidSession();

      try {
         ListPatientPrescriptionsParam param = new ListPatientPrescriptionsParam();
         param.setSymmKey(this.getSymmKey().getEncoded());
         byte[] sealedContent = this.getSealedData(param);
         ListOpenPrescriptionsRequest request = new ListOpenPrescriptionsRequest();
         request.setSecuredListOpenPrescriptionsRequest(this.createSecuredContentTypeV4(sealedContent));
         request.setProgramId(PropertyHandler.getInstance().getProperty("programIdentification"));
         request.setIssueInstant(new DateTime());
         request.setId(this.getId());
         ListOpenPrescriptionsResponse response = null;

         try {
            response = RecipePatientServiceV4Impl.getInstance().listOpenPrescriptions(request);
         } catch (ClientTransportException var7) {
            throw new IntegrationModuleException(I18nHelper.getLabel("error.connection.prescriber"), var7);
         }

         MarshallerHelper<ListPatientPrescriptionsResult, ListPatientPrescriptionsParam> helper = new MarshallerHelper(ListPatientPrescriptionsResult.class, ListPatientPrescriptionsParam.class);
         ListPatientPrescriptionsResult result = (ListPatientPrescriptionsResult)helper.unsealWithSymmKey(response.getSecuredListOpenPrescriptionsResponse().getSecuredContent(), this.getSymmKey());
         this.checkStatus(result);
         return result;
      } catch (Throwable var8) {
         Exceptionutils.errorHandler(var8);
         return null;
      }
   }

   private byte[] getSealedData(ListPatientPrescriptionsParam request) throws IntegrationModuleException {
      request.setSymmKey(this.getSymmKey().getEncoded());
      return this.sealForRecipe(request, ListPatientPrescriptionsParam.class);
   }

   @Profiled(
      logFailuresSeparately = true,
      tag = "0.PatientIntegrationModuleV4#getData(GetPrescriptionStatusParam)"
   )
   public GetPrescriptionStatusResult getData(GetPrescriptionStatusParam data) throws IntegrationModuleException {
      JoinPoint var7 = Factory.makeJP(ajc$tjp_3, this, this, data);
      TimingAspect var10000 = TimingAspect.aspectOf();
      Object[] var8 = new Object[]{this, data, var7};
      ProceedingJoinPoint var10001 = (new PatientIntegrationModuleV4Impl$AjcClosure7(var8)).linkClosureAndJoinPoint(69648);
      Annotation var10002 = ajc$anno$3;
      if (var10002 == null) {
         var10002 = ajc$anno$3 = PatientIntegrationModuleV4Impl.class.getDeclaredMethod("getData", GetPrescriptionStatusParam.class).getAnnotation(Profiled.class);
      }

      return (GetPrescriptionStatusResult)var10000.doPerfLogging(var10001, (Profiled)var10002);
   }

   private GetPrescriptionStatusResult unsealGetPrescriptionStatusResponse(GetPrescriptionStatusResponse response) {
      MarshallerHelper<GetPrescriptionStatusResult, Object> marshaller = new MarshallerHelper(GetPrescriptionStatusResult.class, Object.class);
      return (GetPrescriptionStatusResult)marshaller.unsealWithSymmKey(response.getSecuredGetPrescriptionStatusResponse().getSecuredContent(), this.getSymmKey());
   }

   @Profiled(
      logFailuresSeparately = true,
      tag = "0.PatientIntegrationModuleV4#getData(ListRidsHistoryParam)"
   )
   public ListRidsHistoryResult getData(ListRidsHistoryParam param) throws IntegrationModuleException {
      JoinPoint var6 = Factory.makeJP(ajc$tjp_4, this, this, param);
      TimingAspect var10000 = TimingAspect.aspectOf();
      Object[] var7 = new Object[]{this, param, var6};
      ProceedingJoinPoint var10001 = (new PatientIntegrationModuleV4Impl$AjcClosure9(var7)).linkClosureAndJoinPoint(69648);
      Annotation var10002 = ajc$anno$4;
      if (var10002 == null) {
         var10002 = ajc$anno$4 = PatientIntegrationModuleV4Impl.class.getDeclaredMethod("getData", ListRidsHistoryParam.class).getAnnotation(Profiled.class);
      }

      return (ListRidsHistoryResult)var10000.doPerfLogging(var10001, (Profiled)var10002);
   }

   private ListRidsHistoryResult unsealListRidsHistoryResponse(ListRidsHistoryResponse response) {
      MarshallerHelper<ListRidsHistoryResult, Object> marshaller1 = new MarshallerHelper(ListRidsHistoryResult.class, Object.class);
      return (ListRidsHistoryResult)marshaller1.unsealWithSymmKey(response.getSecuredListRidsHistoryResponse().getSecuredContent(), this.getSymmKey());
   }

   @Profiled(
      logFailuresSeparately = true,
      tag = "0.PatientIntegrationModuleV4#getData(ListOpenPrescriptionsParam)"
   )
   public ListOpenRidsResult getData(ListOpenRidsParam data) throws IntegrationModuleException {
      JoinPoint var6 = Factory.makeJP(ajc$tjp_5, this, this, data);
      TimingAspect var10000 = TimingAspect.aspectOf();
      Object[] var7 = new Object[]{this, data, var6};
      ProceedingJoinPoint var10001 = (new PatientIntegrationModuleV4Impl$AjcClosure11(var7)).linkClosureAndJoinPoint(69648);
      Annotation var10002 = ajc$anno$5;
      if (var10002 == null) {
         var10002 = ajc$anno$5 = PatientIntegrationModuleV4Impl.class.getDeclaredMethod("getData", ListOpenRidsParam.class).getAnnotation(Profiled.class);
      }

      return (ListOpenRidsResult)var10000.doPerfLogging(var10001, (Profiled)var10002);
   }

   private ListOpenRidsResult unsealListOpenPrescriptionResponse(ListOpenRidsResponse response) {
      MarshallerHelper<ListOpenRidsResult, Object> marshaller = new MarshallerHelper(ListOpenRidsResult.class, Object.class);
      return (ListOpenRidsResult)marshaller.unsealWithSymmKey(response.getSecuredListOpenRidsResponse().getSecuredContent(), this.getSymmKey());
   }

   protected GetVisionRequest getVisionRequest(GetVisionParam data) throws IntegrationModuleException {
      data.setSymmKey(this.getSymmKey().getEncoded());
      GetVisionRequest request = new GetVisionRequest();
      request.setSecuredGetVisionRequest(this.createSecuredContentTypeV4(this.getSealedData(data)));
      request.setProgramId(PropertyHandler.getInstance().getProperty("programIdentification"));
      request.setIssueInstant(new DateTime());
      request.setId(this.getId());
      return request;
   }

   protected PutVisionForPatientRequest putVision(PutVisionParam data) throws IntegrationModuleException {
      data.setSymmKey(this.getSymmKey().getEncoded());
      PutVisionForPatientRequest request = new PutVisionForPatientRequest();
      request.setSecuredPutVisionForPatientRequest(this.createSecuredContentTypeV4(this.getSealedData(data)));
      request.setProgramId(PropertyHandler.getInstance().getProperty("programIdentification"));
      request.setIssueInstant(new DateTime());
      request.setId(this.getId());
      return request;
   }

   protected CreateReservationRequest getCreateReservationRequest(CreateReservationParam data) throws IntegrationModuleException {
      data.setSymmKey(this.getSymmKey().getEncoded());
      CreateReservationRequest request = new CreateReservationRequest();
      request.setSecuredCreateReservationRequest(this.createSecuredContentTypeV4(this.getSealedData(data)));
      request.setProgramId(PropertyHandler.getInstance().getProperty("programIdentification"));
      request.setIssueInstant(new DateTime());
      request.setId(this.getId());
      return request;
   }

   protected ListRidsHistoryRequest getListRidsHistory(ListRidsHistoryParam data) throws IntegrationModuleException {
      data.setSymmKey(this.getSymmKey().getEncoded());
      ListRidsHistoryRequest request = new ListRidsHistoryRequest();
      request.setSecuredListRidsHistoryRequest(this.createSecuredContentTypeV4(this.getSealedData(data)));
      request.setProgramId(PropertyHandler.getInstance().getProperty("programIdentification"));
      request.setIssueInstant(new DateTime());
      request.setId(this.getId());
      return request;
   }

   protected ListOpenRidsRequest getListOpenRids(ListOpenRidsParam data) throws IntegrationModuleException {
      data.setSymmKey(this.getSymmKey().getEncoded());
      ListOpenRidsRequest request = new ListOpenRidsRequest();
      request.setSecuredListOpenRidsRequest(this.createSecuredContentTypeV4(this.getSealedData(data)));
      request.setProgramId(PropertyHandler.getInstance().getProperty("programIdentification"));
      request.setIssueInstant(new DateTime());
      request.setId(this.getId());
      return request;
   }

   private byte[] getSealedData(GetPrescriptionStatusParam request) throws IntegrationModuleException {
      request.setSymmKey(this.getSymmKey().getEncoded());
      return this.sealForRecipe(request, GetPrescriptionStatusParam.class);
   }

   private byte[] getSealedData(ListRidsHistoryParam request) throws IntegrationModuleException {
      request.setSymmKey(this.getSymmKey().getEncoded());
      return this.sealForRecipe(request, ListRidsHistoryParam.class);
   }

   private byte[] getSealedData(ListOpenRidsParam request) throws IntegrationModuleException {
      request.setSymmKey(this.getSymmKey().getEncoded());
      return this.sealForRecipe(request, ListOpenRidsParam.class);
   }

   private byte[] getSealedData(CreateReservationParam request) throws IntegrationModuleException {
      request.setSymmKey(this.getSymmKey().getEncoded());
      return this.sealForRecipe(request, CreateReservationParam.class);
   }

   private byte[] getSealedData(GetVisionParam request) throws IntegrationModuleException {
      request.setSymmKey(this.getSymmKey().getEncoded());
      return this.sealForRecipe(request, GetVisionParam.class);
   }

   private byte[] getSealedData(PutVisionParam request) throws IntegrationModuleException {
      request.setSymmKey(this.getSymmKey().getEncoded());
      return this.sealForRecipe(request, PutVisionParam.class);
   }

   public ListRelationsResult getData(ListRelationsParam patientRelationParam) throws IntegrationModuleException {
      ApplicationConfig.getInstance().assertValidSession();

      try {
         ListRelationsRequest request = this.getListPatientRelation(patientRelationParam);

         try {
            ListRelationsResponse dataResponse = RecipePatientServiceV4Impl.getInstance().listRelations(request);
            ListRelationsResult unsealedResponse = this.unsealListRelationsResponse(dataResponse);
            this.checkStatus(unsealedResponse);
            return unsealedResponse;
         } catch (ClientTransportException var5) {
            throw new IntegrationModuleException(I18nHelper.getLabel("error.connection.executor"), var5);
         }
      } catch (Throwable var6) {
         Exceptionutils.errorHandler(var6);
         return null;
      }
   }

   @Profiled(
      logFailuresSeparately = true,
      tag = "0.PatientIntegrationModuleV4#putData(PatientRelationParam)"
   )
   public CreateRelationResult putData(CreateRelationParam patientRelationParam) throws IntegrationModuleException {
      JoinPoint var6 = Factory.makeJP(ajc$tjp_6, this, this, patientRelationParam);
      TimingAspect var10000 = TimingAspect.aspectOf();
      Object[] var7 = new Object[]{this, patientRelationParam, var6};
      ProceedingJoinPoint var10001 = (new PatientIntegrationModuleV4Impl$AjcClosure13(var7)).linkClosureAndJoinPoint(69648);
      Annotation var10002 = ajc$anno$6;
      if (var10002 == null) {
         var10002 = ajc$anno$6 = PatientIntegrationModuleV4Impl.class.getDeclaredMethod("putData", CreateRelationParam.class).getAnnotation(Profiled.class);
      }

      return (CreateRelationResult)var10000.doPerfLogging(var10001, (Profiled)var10002);
   }

   private ListRelationsRequest getListPatientRelation(ListRelationsParam data) throws IntegrationModuleException {
      data.setSymmKey(this.getSymmKey().getEncoded());
      ListRelationsRequest request = new ListRelationsRequest();
      request.setSecuredListRelationsRequest(this.createSecuredContentTypeV4(this.getSealedData(data)));
      request.setProgramId(PropertyHandler.getInstance().getProperty("programIdentification"));
      request.setIssueInstant(new DateTime());
      request.setId(this.getId());
      return request;
   }

   private CreateRelationRequest getCreateRelation(CreateRelationParam data) throws IntegrationModuleException {
      data.setSymmKey(this.getSymmKey().getEncoded());
      CreateRelationRequest request = new CreateRelationRequest();
      request.setSecuredCreateRelationRequest(this.createSecuredContentTypeV4(this.getSealedData(data)));
      request.setProgramId(PropertyHandler.getInstance().getProperty("programIdentification"));
      request.setIssueInstant(new DateTime());
      request.setId(this.getId());
      return request;
   }

   private RevokeRelationRequest getRevokeRelation(RevokeRelationParam data) throws IntegrationModuleException {
      data.setSymmKey(this.getSymmKey().getEncoded());
      RevokeRelationRequest request = new RevokeRelationRequest();
      request.setSecuredRevokeRelationRequest(this.createSecuredContentTypeV4(this.getSealedData(data)));
      request.setProgramId(PropertyHandler.getInstance().getProperty("programIdentification"));
      request.setIssueInstant(new DateTime());
      request.setId(this.getId());
      return request;
   }

   private byte[] getSealedData(ListRelationsParam data) throws IntegrationModuleException {
      data.setSymmKey(this.getSymmKey().getEncoded());
      return this.sealForRecipe(data, ListRelationsParam.class);
   }

   private byte[] getSealedData(CreateRelationParam data) throws IntegrationModuleException {
      data.setSymmKey(this.getSymmKey().getEncoded());
      return this.sealForRecipe(data, CreateRelationParam.class);
   }

   private byte[] getSealedData(RevokeRelationParam data) throws IntegrationModuleException {
      data.setSymmKey(this.getSymmKey().getEncoded());
      return this.sealForRecipe(data, RevokeRelationParam.class);
   }

   public RevokeRelationResult revokeData(RevokeRelationParam patientRelationParam) throws IntegrationModuleException {
      ApplicationConfig.getInstance().assertValidSession();

      try {
         RevokeRelationRequest request = this.getRevokeRelation(patientRelationParam);

         try {
            RevokeRelationResponse dataResponse = RecipePatientServiceV4Impl.getInstance().revokeRelation(request);
            RevokeRelationResult unsealedResponse = this.unsealRevokeRelationResponse(dataResponse);
            this.checkStatus(unsealedResponse);
            return unsealedResponse;
         } catch (ClientTransportException var5) {
            throw new IntegrationModuleException(I18nHelper.getLabel("error.connection.executor"), var5);
         }
      } catch (Throwable var6) {
         Exceptionutils.errorHandler(var6);
         return null;
      }
   }

   static {
      ajc$preClinit();
   }

   // $FF: synthetic method
   static final GetVisionResult getData_aroundBody0(PatientIntegrationModuleV4Impl ajc$this, GetVisionParam data, JoinPoint var2) {
      RidValidator.validateRid(data.getRid());
      ApplicationConfig.getInstance().assertValidSession();

      try {
         try {
            byte[] sealedContent = ajc$this.getSealedData(data);
            GetVisionRequest request = new GetVisionRequest();
            request.setSecuredGetVisionRequest(ajc$this.createSecuredContentTypeV4(sealedContent));
            request.setProgramId(PropertyHandler.getInstance().getProperty("programIdentification"));
            request.setId(ajc$this.getId());
            request.setIssueInstant(new DateTime());
            GetVisionResponse getDataResponse = RecipePatientServiceV4Impl.getInstance().getVision(request);
            GetVisionResult getVisionResult = ajc$this.unsealGetVisionResponse(getDataResponse);
            ajc$this.checkStatus(getVisionResult);
            return getVisionResult;
         } catch (ClientTransportException var10) {
            throw new IntegrationModuleException(I18nHelper.getLabel("error.connection.executor"), var10);
         }
      } catch (Throwable var11) {
         Exceptionutils.errorHandler(var11);
         return null;
      }
   }

   // $FF: synthetic method
   static final PutVisionResult putData_aroundBody2(PatientIntegrationModuleV4Impl ajc$this, PutVisionParam putVisionParam, JoinPoint var2) {
      RidValidator.validateRid(putVisionParam.getRid());
      ApplicationConfig.getInstance().assertValidSession();

      try {
         byte[] sealedContent = ajc$this.getSealedData(putVisionParam);
         PutVisionForPatientRequest request = new PutVisionForPatientRequest();
         request.setSecuredPutVisionForPatientRequest(ajc$this.createSecuredContentTypeV4(sealedContent));
         request.setProgramId(PropertyHandler.getInstance().getProperty("programIdentification"));
         request.setIssueInstant(new DateTime());
         request.setId(ajc$this.getId());

         try {
            PutVisionForPatientResponse response = RecipePatientServiceV4Impl.getInstance().putVision(request);
            PutVisionResult result = ajc$this.unsealPutVisionResponse(response);
            ajc$this.checkStatus(result);
            return result;
         } catch (ClientTransportException var10) {
            throw new IntegrationModuleException(I18nHelper.getLabel("error.connection.executor"), var10);
         }
      } catch (Throwable var11) {
         Exceptionutils.errorHandler(var11);
         return null;
      }
   }

   // $FF: synthetic method
   static final CreateReservationResult putData_aroundBody4(PatientIntegrationModuleV4Impl ajc$this, CreateReservationParam param, JoinPoint var2) {
      RidValidator.validateRid(param.getRid());
      ApplicationConfig.getInstance().assertValidSession();

      try {
         CreateReservationRequest createReservationRequest = ajc$this.getCreateReservationRequest(param);

         try {
            CreateReservationResponse response = RecipePatientServiceV4Impl.getInstance().createReservation(createReservationRequest);
            CreateReservationResult result = ajc$this.unsealCreateReservationResponse(response);
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
   static final GetPrescriptionStatusResult getData_aroundBody6(PatientIntegrationModuleV4Impl ajc$this, GetPrescriptionStatusParam data, JoinPoint var2) {
      RidValidator.validateRid(data.getRid());
      ApplicationConfig.getInstance().assertValidSession();

      try {
         byte[] sealedContent = ajc$this.getSealedData(data);
         GetPrescriptionStatusRequest request = new GetPrescriptionStatusRequest();
         request.setSecuredGetPrescriptionStatusRequest(ajc$this.createSecuredContentTypeV4(sealedContent));
         request.setProgramId(PropertyHandler.getInstance().getProperty("programIdentification"));
         request.setIssueInstant(new DateTime());
         request.setId(ajc$this.getId());

         try {
            GetPrescriptionStatusResponse response = RecipePatientServiceV4Impl.getInstance().getPrescriptionStatus(request);
            GetPrescriptionStatusResult result = ajc$this.unsealGetPrescriptionStatusResponse(response);
            ajc$this.checkStatus(result);
            return result;
         } catch (ClientTransportException var10) {
            throw new IntegrationModuleException(I18nHelper.getLabel("error.connection.executor"), var10);
         }
      } catch (Throwable var11) {
         Exceptionutils.errorHandler(var11);
         return null;
      }
   }

   // $FF: synthetic method
   static final ListRidsHistoryResult getData_aroundBody8(PatientIntegrationModuleV4Impl ajc$this, ListRidsHistoryParam param, JoinPoint var2) {
      ApplicationConfig.getInstance().assertValidSession();

      try {
         ListRidsHistoryRequest request = ajc$this.getListRidsHistory(param);

         try {
            ListRidsHistoryResponse response = RecipePatientServiceV4Impl.getInstance().listRidsHistory(request);
            ListRidsHistoryResult result = ajc$this.unsealListRidsHistoryResponse(response);
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
   static final ListOpenRidsResult getData_aroundBody10(PatientIntegrationModuleV4Impl ajc$this, ListOpenRidsParam data, JoinPoint var2) {
      ApplicationConfig.getInstance().assertValidSession();

      try {
         ListOpenRidsRequest request = ajc$this.getListOpenRids(data);

         try {
            ListOpenRidsResponse getDataResponse = RecipePatientServiceV4Impl.getInstance().listOpenRids(request);
            ListOpenRidsResult unsealedResponse = ajc$this.unsealListOpenPrescriptionResponse(getDataResponse);
            ajc$this.checkStatus(unsealedResponse);
            return unsealedResponse;
         } catch (ClientTransportException var8) {
            throw new IntegrationModuleException(I18nHelper.getLabel("error.connection.executor"), var8);
         }
      } catch (Throwable var9) {
         Exceptionutils.errorHandler(var9);
         return null;
      }
   }

   // $FF: synthetic method
   static final CreateRelationResult putData_aroundBody12(PatientIntegrationModuleV4Impl ajc$this, CreateRelationParam patientRelationParam, JoinPoint var2) {
      ApplicationConfig.getInstance().assertValidSession();

      try {
         CreateRelationRequest request = ajc$this.getCreateRelation(patientRelationParam);

         try {
            CreateRelationResponse dataResponse = RecipePatientServiceV4Impl.getInstance().createRelation(request);
            CreateRelationResult unsealedResponse = ajc$this.unsealCreateResponse(dataResponse);
            ajc$this.checkStatus(unsealedResponse);
            return unsealedResponse;
         } catch (ClientTransportException var8) {
            throw new IntegrationModuleException(I18nHelper.getLabel("error.connection.executor"), var8);
         }
      } catch (Throwable var9) {
         Exceptionutils.errorHandler(var9);
         return null;
      }
   }

   // $FF: synthetic method
   private static void ajc$preClinit() {
      Factory var0 = new Factory("PatientIntegrationModuleV4Impl.java", PatientIntegrationModuleV4Impl.class);
      ajc$tjp_0 = var0.makeSJP("method-execution", var0.makeMethodSig("1", "getData", "be.business.connector.recipe.patient.PatientIntegrationModuleV4Impl", "be.recipe.services.patient.GetVisionParam", "data", "be.business.connector.core.exceptions.IntegrationModuleException", "be.recipe.services.patient.GetVisionResult"), 229);
      ajc$tjp_1 = var0.makeSJP("method-execution", var0.makeMethodSig("1", "putData", "be.business.connector.recipe.patient.PatientIntegrationModuleV4Impl", "be.recipe.services.patient.PutVisionParam", "putVisionParam", "be.business.connector.core.exceptions.IntegrationModuleException", "be.recipe.services.patient.PutVisionResult"), 345);
      ajc$tjp_2 = var0.makeSJP("method-execution", var0.makeMethodSig("1", "putData", "be.business.connector.recipe.patient.PatientIntegrationModuleV4Impl", "be.recipe.services.patient.CreateReservationParam", "param", "be.business.connector.core.exceptions.IntegrationModuleException", "be.recipe.services.patient.CreateReservationResult"), 375);
      ajc$tjp_3 = var0.makeSJP("method-execution", var0.makeMethodSig("1", "getData", "be.business.connector.recipe.patient.PatientIntegrationModuleV4Impl", "be.recipe.services.patient.GetPrescriptionStatusParam", "data", "be.business.connector.core.exceptions.IntegrationModuleException", "be.recipe.services.patient.GetPrescriptionStatusResult"), 449);
      ajc$tjp_4 = var0.makeSJP("method-execution", var0.makeMethodSig("1", "getData", "be.business.connector.recipe.patient.PatientIntegrationModuleV4Impl", "be.recipe.services.patient.ListRidsHistoryParam", "param", "be.business.connector.core.exceptions.IntegrationModuleException", "be.recipe.services.patient.ListRidsHistoryResult"), 493);
      ajc$tjp_5 = var0.makeSJP("method-execution", var0.makeMethodSig("1", "getData", "be.business.connector.recipe.patient.PatientIntegrationModuleV4Impl", "be.recipe.services.patient.ListOpenRidsParam", "data", "be.business.connector.core.exceptions.IntegrationModuleException", "be.recipe.services.patient.ListOpenRidsResult"), 530);
      ajc$tjp_6 = var0.makeSJP("method-execution", var0.makeMethodSig("1", "putData", "be.business.connector.recipe.patient.PatientIntegrationModuleV4Impl", "be.recipe.services.patient.CreateRelationParam", "patientRelationParam", "be.business.connector.core.exceptions.IntegrationModuleException", "be.recipe.services.patient.CreateRelationResult"), 768);
   }
}
