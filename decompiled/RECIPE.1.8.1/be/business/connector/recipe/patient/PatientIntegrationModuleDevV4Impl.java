package be.business.connector.recipe.patient;

import be.business.connector.common.ApplicationConfig;
import be.business.connector.core.exceptions.IntegrationModuleException;
import be.business.connector.core.handlers.InsurabilityHandler;
import be.business.connector.core.utils.Exceptionutils;
import be.business.connector.core.utils.I18nHelper;
import be.business.connector.core.utils.MarshallerHelper;
import be.business.connector.core.utils.PropertyHandler;
import be.business.connector.recipe.patient.services.RecipePatientServiceDevV4Impl;
import be.business.connector.recipe.utils.RidValidator;
import be.fgov.ehealth.etee.crypto.encrypt.EncryptionToken;
import be.recipe.services.patient.CreateRelation;
import be.recipe.services.patient.CreateRelationParam;
import be.recipe.services.patient.CreateRelationResponse;
import be.recipe.services.patient.CreateRelationResult;
import be.recipe.services.patient.CreateReservation;
import be.recipe.services.patient.CreateReservationParam;
import be.recipe.services.patient.CreateReservationResponse;
import be.recipe.services.patient.CreateReservationResult;
import be.recipe.services.patient.GetPrescriptionForPatient;
import be.recipe.services.patient.GetPrescriptionForPatientResponse;
import be.recipe.services.patient.GetPrescriptionForPatientResult;
import be.recipe.services.patient.GetPrescriptionStatus;
import be.recipe.services.patient.GetPrescriptionStatusParam;
import be.recipe.services.patient.GetPrescriptionStatusResponse;
import be.recipe.services.patient.GetPrescriptionStatusResult;
import be.recipe.services.patient.GetVision;
import be.recipe.services.patient.GetVisionParam;
import be.recipe.services.patient.GetVisionResponse;
import be.recipe.services.patient.GetVisionResult;
import be.recipe.services.patient.ListOpenRids;
import be.recipe.services.patient.ListOpenRidsParam;
import be.recipe.services.patient.ListOpenRidsResponse;
import be.recipe.services.patient.ListOpenRidsResult;
import be.recipe.services.patient.ListPatientPrescription;
import be.recipe.services.patient.ListPatientPrescriptionResponse;
import be.recipe.services.patient.ListPatientPrescriptionsParam;
import be.recipe.services.patient.ListPatientPrescriptionsResult;
import be.recipe.services.patient.ListRelations;
import be.recipe.services.patient.ListRelationsParam;
import be.recipe.services.patient.ListRelationsResponse;
import be.recipe.services.patient.ListRelationsResult;
import be.recipe.services.patient.ListRidsHistory;
import be.recipe.services.patient.ListRidsHistoryParam;
import be.recipe.services.patient.ListRidsHistoryResponse;
import be.recipe.services.patient.ListRidsHistoryResult;
import be.recipe.services.patient.PutVision;
import be.recipe.services.patient.PutVisionParam;
import be.recipe.services.patient.PutVisionResponse;
import be.recipe.services.patient.PutVisionResult;
import be.recipe.services.patient.RevokePrescription;
import be.recipe.services.patient.RevokePrescriptionResponse;
import be.recipe.services.patient.RevokePrescriptionResult;
import be.recipe.services.patient.RevokeRelation;
import be.recipe.services.patient.RevokeRelationParam;
import be.recipe.services.patient.RevokeRelationResponse;
import be.recipe.services.patient.RevokeRelationResult;
import be.recipe.services.patient.UpdateFeedbackFlag;
import be.recipe.services.patient.UpdateFeedbackFlagParam;
import be.recipe.services.patient.UpdateFeedbackFlagResponse;
import be.recipe.services.patient.UpdateFeedbackFlagResult;
import com.sun.xml.ws.client.ClientTransportException;
import java.lang.annotation.Annotation;
import java.util.List;
import java.util.UUID;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.JoinPoint.StaticPart;
import org.aspectj.runtime.reflect.Factory;
import org.perf4j.aop.Profiled;
import org.perf4j.log4j.aop.TimingAspect;

public class PatientIntegrationModuleDevV4Impl extends PatientIntegrationModuleImpl implements PatientIntegrationModuleDevV4 {
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

   public PatientIntegrationModuleDevV4Impl() throws IntegrationModuleException {
   }

   public void updateFeedbackFlag(String rid, boolean feedbackAllowed) throws IntegrationModuleException {
      RidValidator.validateRid(rid);
      ApplicationConfig.getInstance().assertValidSession();

      try {
         MarshallerHelper<Object, UpdateFeedbackFlagParam> helper = new MarshallerHelper(Object.class, UpdateFeedbackFlagParam.class);
         List<EncryptionToken> etkRecipes = this.getEtkHelper().getRecipe_ETK();
         UpdateFeedbackFlagParam param = new UpdateFeedbackFlagParam();
         param.setAllowFeedback(feedbackAllowed);
         param.setRid(rid);
         param.setSymmKey(this.getSymmKey().getEncoded());
         UpdateFeedbackFlag request = new UpdateFeedbackFlag();
         request.setUpdateFeedbackFlagParamSealed(this.sealRequest((EncryptionToken)etkRecipes.get(0), helper.toXMLByteArray(param)));
         request.setProgramIdentification(PropertyHandler.getInstance().getProperty("programIdentification"));
         request.setMguid(UUID.randomUUID().toString());

         try {
            UpdateFeedbackFlagResponse response = RecipePatientServiceDevV4Impl.getInstance().updateFeedbackFlag(request);
            MarshallerHelper<UpdateFeedbackFlagResult, UpdateFeedbackFlagResult> helper1 = new MarshallerHelper(UpdateFeedbackFlagResult.class, UpdateFeedbackFlagResult.class);
            UpdateFeedbackFlagResult result = (UpdateFeedbackFlagResult)helper1.unsealWithSymmKey(response.getUpdateFeedbackFlagResultSealed(), this.getSymmKey());
            this.checkStatus(result);
         } catch (ClientTransportException var10) {
            throw new IntegrationModuleException(I18nHelper.getLabel("error.connection.prescriber"), var10);
         }
      } catch (Throwable var11) {
         Exceptionutils.errorHandler(var11);
      }

   }

   public void revokePrescription(String rid, String reason) throws IntegrationModuleException {
      RidValidator.validateRid(rid);
      ApplicationConfig.getInstance().assertValidSession();

      try {
         byte[] sealedRevokePrescriptionParam = this.getSealedRevokePrescriptionParam(rid, reason);
         RevokePrescription request = new RevokePrescription();
         request.setRevokePrescriptionParamSealed(sealedRevokePrescriptionParam);
         request.setProgramIdentification(PropertyHandler.getInstance().getProperty("programIdentification"));
         request.setMguid(UUID.randomUUID().toString());

         try {
            RevokePrescriptionResponse response = RecipePatientServiceDevV4Impl.getInstance().revokePrescription(request);
            MarshallerHelper<RevokePrescriptionResult, RevokePrescriptionResult> helper = new MarshallerHelper(RevokePrescriptionResult.class, RevokePrescriptionResult.class);
            RevokePrescriptionResult result = (RevokePrescriptionResult)helper.unsealWithSymmKey(response.getRevokePrescriptionResultSealed(), this.getSymmKey());
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
         GetPrescriptionForPatient request = new GetPrescriptionForPatient();
         request.setGetPrescriptionForPatientParamSealed(sealedContent);
         request.setProgramIdentification(PropertyHandler.getInstance().getProperty("programIdentification"));
         request.setMguid(UUID.randomUUID().toString());
         GetPrescriptionForPatientResponse response = null;

         try {
            response = RecipePatientServiceDevV4Impl.getInstance().getPrescriptionForPatient(request);
         } catch (ClientTransportException var6) {
            throw new IntegrationModuleException(I18nHelper.getLabel("error.connection.executor"), var6);
         }

         GetPrescriptionForPatientResult finalResult = this.unsealPrescription(response.getGetPrescriptionForPatientResultSealed());
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
      JoinPoint var6 = Factory.makeJP(ajc$tjp_0, this, this, data);
      TimingAspect var10000 = TimingAspect.aspectOf();
      Object[] var7 = new Object[]{this, data, var6};
      ProceedingJoinPoint var10001 = (new PatientIntegrationModuleDevV4Impl$AjcClosure1(var7)).linkClosureAndJoinPoint(69648);
      Annotation var10002 = ajc$anno$0;
      if (var10002 == null) {
         var10002 = ajc$anno$0 = PatientIntegrationModuleDevV4Impl.class.getDeclaredMethod("getData", GetVisionParam.class).getAnnotation(Profiled.class);
      }

      return (GetVisionResult)var10000.doPerfLogging(var10001, (Profiled)var10002);
   }

   private GetVisionResult unsealGetVisionResponse(GetVisionResponse getVisionResponse) {
      MarshallerHelper<GetVisionResult, Object> marshaller = new MarshallerHelper(GetVisionResult.class, Object.class);
      GetVisionResult result = (GetVisionResult)marshaller.unsealWithSymmKey(getVisionResponse.getGetVisionResultSealed(), this.getSymmKey());
      return result;
   }

   private PutVisionResult unsealPutVisionResponse(PutVisionResponse putVisionResponse) {
      MarshallerHelper<PutVisionResult, Object> marshaller = new MarshallerHelper(PutVisionResult.class, Object.class);
      PutVisionResult result = (PutVisionResult)marshaller.unsealWithSymmKey(putVisionResponse.getPutVisionResultSealed(), this.getSymmKey());
      return result;
   }

   private CreateRelationResult unsealCreateResponse(CreateRelationResponse response) {
      MarshallerHelper<CreateRelationResult, Object> marshaller = new MarshallerHelper(CreateRelationResult.class, Object.class);
      CreateRelationResult result = (CreateRelationResult)marshaller.unsealWithSymmKey(response.getCreateRelationResultSealed(), this.getSymmKey());
      return result;
   }

   private ListRelationsResult unsealListRelationsResponse(ListRelationsResponse response) {
      MarshallerHelper<ListRelationsResult, Object> marshaller = new MarshallerHelper(ListRelationsResult.class, Object.class);
      ListRelationsResult result = (ListRelationsResult)marshaller.unsealWithSymmKey(response.getListRelationsResultSealed(), this.getSymmKey());
      return result;
   }

   private RevokeRelationResult unsealRevokeRelationResponse(RevokeRelationResponse response) {
      MarshallerHelper<RevokeRelationResult, Object> marshaller = new MarshallerHelper(RevokeRelationResult.class, Object.class);
      RevokeRelationResult result = (RevokeRelationResult)marshaller.unsealWithSymmKey(response.getRevokeRelationResultSealed(), this.getSymmKey());
      return result;
   }

   private CreateReservationResult unsealCreateReservationResponse(CreateReservationResponse dataResponse) {
      MarshallerHelper<CreateReservationResult, Object> marshaller = new MarshallerHelper(CreateReservationResult.class, Object.class);
      CreateReservationResult result = (CreateReservationResult)marshaller.unsealWithSymmKey(dataResponse.getCreateReservationResultSealed(), this.getSymmKey());
      return result;
   }

   @Profiled(
      logFailuresSeparately = true,
      tag = "0.PatientIntegrationModuleV4#putVision"
   )
   public PutVisionResult putData(PutVisionParam putVisionParam) throws IntegrationModuleException {
      JoinPoint var6 = Factory.makeJP(ajc$tjp_1, this, this, putVisionParam);
      TimingAspect var10000 = TimingAspect.aspectOf();
      Object[] var7 = new Object[]{this, putVisionParam, var6};
      ProceedingJoinPoint var10001 = (new PatientIntegrationModuleDevV4Impl$AjcClosure3(var7)).linkClosureAndJoinPoint(69648);
      Annotation var10002 = ajc$anno$1;
      if (var10002 == null) {
         var10002 = ajc$anno$1 = PatientIntegrationModuleDevV4Impl.class.getDeclaredMethod("putData", PutVisionParam.class).getAnnotation(Profiled.class);
      }

      return (PutVisionResult)var10000.doPerfLogging(var10001, (Profiled)var10002);
   }

   @Profiled(
      logFailuresSeparately = true,
      tag = "0.PatientIntegrationModuleV4#putReservation"
   )
   public CreateReservationResult putData(CreateReservationParam data) throws IntegrationModuleException {
      JoinPoint var6 = Factory.makeJP(ajc$tjp_2, this, this, data);
      TimingAspect var10000 = TimingAspect.aspectOf();
      Object[] var7 = new Object[]{this, data, var6};
      ProceedingJoinPoint var10001 = (new PatientIntegrationModuleDevV4Impl$AjcClosure5(var7)).linkClosureAndJoinPoint(69648);
      Annotation var10002 = ajc$anno$2;
      if (var10002 == null) {
         var10002 = ajc$anno$2 = PatientIntegrationModuleDevV4Impl.class.getDeclaredMethod("putData", CreateReservationParam.class).getAnnotation(Profiled.class);
      }

      return (CreateReservationResult)var10000.doPerfLogging(var10001, (Profiled)var10002);
   }

   public ListPatientPrescriptionsResult listOpenPrescriptions() throws IntegrationModuleException {
      ApplicationConfig.getInstance().assertValidSession();

      try {
         MarshallerHelper<ListPatientPrescriptionsResult, ListPatientPrescriptionsParam> helper = new MarshallerHelper(ListPatientPrescriptionsResult.class, ListPatientPrescriptionsParam.class);
         List<EncryptionToken> etkRecipes = this.getEtkHelper().getRecipe_ETK();
         ListPatientPrescriptionsParam param = new ListPatientPrescriptionsParam();
         param.setSymmKey(this.getSymmKey().getEncoded());
         ListPatientPrescription request = new ListPatientPrescription();
         request.setListPatientPrescriptionsParamSealed(this.sealRequest((EncryptionToken)etkRecipes.get(0), helper.toXMLByteArray(param)));
         request.setProgramIdentification(PropertyHandler.getInstance().getProperty("programIdentification"));
         request.setMguid(UUID.randomUUID().toString());
         ListPatientPrescriptionResponse response = null;

         try {
            response = RecipePatientServiceDevV4Impl.getInstance().listOpenPrescriptions(request);
         } catch (ClientTransportException var7) {
            throw new IntegrationModuleException(I18nHelper.getLabel("error.connection.prescriber"), var7);
         }

         ListPatientPrescriptionsResult result = (ListPatientPrescriptionsResult)helper.unsealWithSymmKey(response.getListPatientPrescriptionsResultSealed(), this.getSymmKey());
         this.checkStatus(result);
         return result;
      } catch (Throwable var8) {
         Exceptionutils.errorHandler(var8);
         return null;
      }
   }

   @Profiled(
      logFailuresSeparately = true,
      tag = "0.PatientIntegrationModuleV4#getData(GetPrescriptionStatusParam)"
   )
   public GetPrescriptionStatusResult getData(GetPrescriptionStatusParam data) throws IntegrationModuleException {
      JoinPoint var6 = Factory.makeJP(ajc$tjp_3, this, this, data);
      TimingAspect var10000 = TimingAspect.aspectOf();
      Object[] var7 = new Object[]{this, data, var6};
      ProceedingJoinPoint var10001 = (new PatientIntegrationModuleDevV4Impl$AjcClosure7(var7)).linkClosureAndJoinPoint(69648);
      Annotation var10002 = ajc$anno$3;
      if (var10002 == null) {
         var10002 = ajc$anno$3 = PatientIntegrationModuleDevV4Impl.class.getDeclaredMethod("getData", GetPrescriptionStatusParam.class).getAnnotation(Profiled.class);
      }

      return (GetPrescriptionStatusResult)var10000.doPerfLogging(var10001, (Profiled)var10002);
   }

   private GetPrescriptionStatusResult unsealGetPrescriptionStatusResponse(GetPrescriptionStatusResponse response) {
      MarshallerHelper<GetPrescriptionStatusResult, Object> marshaller = new MarshallerHelper(GetPrescriptionStatusResult.class, Object.class);
      return (GetPrescriptionStatusResult)marshaller.unsealWithSymmKey(response.getGetPrescriptionStatusResultSealed(), this.getSymmKey());
   }

   @Profiled(
      logFailuresSeparately = true,
      tag = "0.PatientIntegrationModuleV4#getData(ListRidsHistoryParam)"
   )
   public ListRidsHistoryResult getData(ListRidsHistoryParam param) throws IntegrationModuleException {
      JoinPoint var6 = Factory.makeJP(ajc$tjp_4, this, this, param);
      TimingAspect var10000 = TimingAspect.aspectOf();
      Object[] var7 = new Object[]{this, param, var6};
      ProceedingJoinPoint var10001 = (new PatientIntegrationModuleDevV4Impl$AjcClosure9(var7)).linkClosureAndJoinPoint(69648);
      Annotation var10002 = ajc$anno$4;
      if (var10002 == null) {
         var10002 = ajc$anno$4 = PatientIntegrationModuleDevV4Impl.class.getDeclaredMethod("getData", ListRidsHistoryParam.class).getAnnotation(Profiled.class);
      }

      return (ListRidsHistoryResult)var10000.doPerfLogging(var10001, (Profiled)var10002);
   }

   private ListRidsHistoryResult unsealListRidsHistoryResponse(ListRidsHistoryResponse response) {
      MarshallerHelper<ListRidsHistoryResult, Object> marshaller1 = new MarshallerHelper(ListRidsHistoryResult.class, Object.class);
      return (ListRidsHistoryResult)marshaller1.unsealWithSymmKey(response.getListRidsHistoryResultSealed(), this.getSymmKey());
   }

   @Profiled(
      logFailuresSeparately = true,
      tag = "0.PatientIntegrationModuleV4#getData(ListOpenPrescriptionsParam)"
   )
   public ListOpenRidsResult getData(ListOpenRidsParam data) throws IntegrationModuleException {
      JoinPoint var6 = Factory.makeJP(ajc$tjp_5, this, this, data);
      TimingAspect var10000 = TimingAspect.aspectOf();
      Object[] var7 = new Object[]{this, data, var6};
      ProceedingJoinPoint var10001 = (new PatientIntegrationModuleDevV4Impl$AjcClosure11(var7)).linkClosureAndJoinPoint(69648);
      Annotation var10002 = ajc$anno$5;
      if (var10002 == null) {
         var10002 = ajc$anno$5 = PatientIntegrationModuleDevV4Impl.class.getDeclaredMethod("getData", ListOpenRidsParam.class).getAnnotation(Profiled.class);
      }

      return (ListOpenRidsResult)var10000.doPerfLogging(var10001, (Profiled)var10002);
   }

   private ListOpenRidsResult unsealListOpenPrescriptionResponse(ListOpenRidsResponse response) {
      MarshallerHelper<ListOpenRidsResult, Object> marshaller = new MarshallerHelper(ListOpenRidsResult.class, Object.class);
      return (ListOpenRidsResult)marshaller.unsealWithSymmKey(response.getListOpenRidsResultSealed(), this.getSymmKey());
   }

   protected GetVision getVisionRequest(GetVisionParam data) throws IntegrationModuleException {
      data.setSymmKey(this.getSymmKey().getEncoded());
      GetVision getVision = new GetVision();
      getVision.setGetVisionParamSealed(this.getSealedData(data));
      getVision.setProgramIdentification(PropertyHandler.getInstance().getProperty("programIdentification"));
      getVision.setMguid(UUID.randomUUID().toString());
      return getVision;
   }

   protected PutVision putVision(PutVisionParam data) throws IntegrationModuleException {
      data.setSymmKey(this.getSymmKey().getEncoded());
      PutVision putVision = new PutVision();
      putVision.setPutVisionParamSealed(this.getSealedData(data));
      putVision.setProgramIdentification(PropertyHandler.getInstance().getProperty("programIdentification"));
      putVision.setMguid(UUID.randomUUID().toString());
      return putVision;
   }

   protected CreateReservation putReservationRequest(CreateReservationParam data) throws IntegrationModuleException {
      data.setSymmKey(this.getSymmKey().getEncoded());
      CreateReservation putReservation = new CreateReservation();
      putReservation.setCreateReservationParamSealed(this.getSealedData(data));
      putReservation.setProgramIdentification(PropertyHandler.getInstance().getProperty("programIdentification"));
      putReservation.setMguid(UUID.randomUUID().toString());
      return putReservation;
   }

   protected GetPrescriptionStatus getGetPrescriptionStatusRequest(GetPrescriptionStatusParam data) throws IntegrationModuleException {
      data.setSymmKey(this.getSymmKey().getEncoded());
      GetPrescriptionStatus getPrescriptionStatus = new GetPrescriptionStatus();
      getPrescriptionStatus.setGetPrescriptionStatusParamSealed(this.getSealedData(data));
      getPrescriptionStatus.setProgramIdentification(PropertyHandler.getInstance().getProperty("programIdentification"));
      getPrescriptionStatus.setMguid(UUID.randomUUID().toString());
      return getPrescriptionStatus;
   }

   protected ListRidsHistory getListRidsHistory(ListRidsHistoryParam data) throws IntegrationModuleException {
      data.setSymmKey(this.getSymmKey().getEncoded());
      ListRidsHistory listPrescriptionHistory = new ListRidsHistory();
      listPrescriptionHistory.setListRidsHistoryParamSealed(this.getSealedData(data));
      listPrescriptionHistory.setProgramIdentification(PropertyHandler.getInstance().getProperty("programIdentification"));
      listPrescriptionHistory.setMguid(UUID.randomUUID().toString());
      return listPrescriptionHistory;
   }

   protected ListOpenRids getListOpenRids(ListOpenRidsParam data) throws IntegrationModuleException {
      data.setSymmKey(this.getSymmKey().getEncoded());
      ListOpenRids listOpenPrescription = new ListOpenRids();
      listOpenPrescription.setListOpenRidsParamSealed(this.getSealedData(data));
      listOpenPrescription.setProgramIdentification(PropertyHandler.getInstance().getProperty("programIdentification"));
      listOpenPrescription.setMguid(UUID.randomUUID().toString());
      return listOpenPrescription;
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
         ListRelations request = this.getListPatientRelation(patientRelationParam);

         try {
            ListRelationsResponse dataResponse = RecipePatientServiceDevV4Impl.getInstance().listRelations(request);
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
      ProceedingJoinPoint var10001 = (new PatientIntegrationModuleDevV4Impl$AjcClosure13(var7)).linkClosureAndJoinPoint(69648);
      Annotation var10002 = ajc$anno$6;
      if (var10002 == null) {
         var10002 = ajc$anno$6 = PatientIntegrationModuleDevV4Impl.class.getDeclaredMethod("putData", CreateRelationParam.class).getAnnotation(Profiled.class);
      }

      return (CreateRelationResult)var10000.doPerfLogging(var10001, (Profiled)var10002);
   }

   private ListRelations getListPatientRelation(ListRelationsParam data) throws IntegrationModuleException {
      data.setSymmKey(this.getSymmKey().getEncoded());
      ListRelations patientRelation = new ListRelations();
      patientRelation.setListRelationsParamSealed(this.getSealedData(data));
      patientRelation.setProgramIdentification(PropertyHandler.getInstance().getProperty("programIdentification"));
      patientRelation.setMguid(UUID.randomUUID().toString());
      return patientRelation;
   }

   private CreateRelation getCreateRelation(CreateRelationParam data) throws IntegrationModuleException {
      data.setSymmKey(this.getSymmKey().getEncoded());
      CreateRelation patientRelation = new CreateRelation();
      patientRelation.setCreateRelationParamSealed(this.getSealedData(data));
      patientRelation.setProgramIdentification(PropertyHandler.getInstance().getProperty("programIdentification"));
      patientRelation.setMguid(UUID.randomUUID().toString());
      return patientRelation;
   }

   private RevokeRelation getRevokeRelation(RevokeRelationParam data) throws IntegrationModuleException {
      data.setSymmKey(this.getSymmKey().getEncoded());
      RevokeRelation patientRelation = new RevokeRelation();
      patientRelation.setRevokeRelationParamSealed(this.getSealedData(data));
      patientRelation.setProgramIdentification(PropertyHandler.getInstance().getProperty("programIdentification"));
      patientRelation.setMguid(UUID.randomUUID().toString());
      return patientRelation;
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
         RevokeRelation request = this.getRevokeRelation(patientRelationParam);

         try {
            RevokeRelationResponse dataResponse = RecipePatientServiceDevV4Impl.getInstance().revokeRelation(request);
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
   static final GetVisionResult getData_aroundBody0(PatientIntegrationModuleDevV4Impl ajc$this, GetVisionParam data, JoinPoint var2) {
      RidValidator.validateRid(data.getRid());
      ApplicationConfig.getInstance().assertValidSession();

      try {
         GetVision request = ajc$this.getVisionRequest(data);

         try {
            GetVisionResponse getDataResponse = RecipePatientServiceDevV4Impl.getInstance().getVision(request);
            GetVisionResult getVisionResult = ajc$this.unsealGetVisionResponse(getDataResponse);
            ajc$this.checkStatus(getVisionResult);
            return getVisionResult;
         } catch (ClientTransportException var8) {
            throw new IntegrationModuleException(I18nHelper.getLabel("error.connection.executor"), var8);
         }
      } catch (Throwable var9) {
         Exceptionutils.errorHandler(var9);
         return null;
      }
   }

   // $FF: synthetic method
   static final PutVisionResult putData_aroundBody2(PatientIntegrationModuleDevV4Impl ajc$this, PutVisionParam putVisionParam, JoinPoint var2) {
      RidValidator.validateRid(putVisionParam.getRid());
      ApplicationConfig.getInstance().assertValidSession();

      try {
         PutVision request = ajc$this.putVision(putVisionParam);

         try {
            PutVisionResponse response = RecipePatientServiceDevV4Impl.getInstance().putVision(request);
            PutVisionResult result = ajc$this.unsealPutVisionResponse(response);
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
   static final CreateReservationResult putData_aroundBody4(PatientIntegrationModuleDevV4Impl ajc$this, CreateReservationParam data, JoinPoint var2) {
      RidValidator.validateRid(data.getRid());
      ApplicationConfig.getInstance().assertValidSession();

      try {
         CreateReservation putReservation = ajc$this.putReservationRequest(data);

         try {
            CreateReservationResponse response = RecipePatientServiceDevV4Impl.getInstance().createReservation(putReservation);
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
   static final GetPrescriptionStatusResult getData_aroundBody6(PatientIntegrationModuleDevV4Impl ajc$this, GetPrescriptionStatusParam data, JoinPoint var2) {
      RidValidator.validateRid(data.getRid());
      ApplicationConfig.getInstance().assertValidSession();

      try {
         GetPrescriptionStatus getPrescriptionStatus = ajc$this.getGetPrescriptionStatusRequest(data);

         try {
            GetPrescriptionStatusResponse response = RecipePatientServiceDevV4Impl.getInstance().getPrescriptionStatus(getPrescriptionStatus);
            GetPrescriptionStatusResult result = ajc$this.unsealGetPrescriptionStatusResponse(response);
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
   static final ListRidsHistoryResult getData_aroundBody8(PatientIntegrationModuleDevV4Impl ajc$this, ListRidsHistoryParam param, JoinPoint var2) {
      ApplicationConfig.getInstance().assertValidSession();

      try {
         ListRidsHistory request = ajc$this.getListRidsHistory(param);

         try {
            ListRidsHistoryResponse response = RecipePatientServiceDevV4Impl.getInstance().listRidsHistory(request);
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
   static final ListOpenRidsResult getData_aroundBody10(PatientIntegrationModuleDevV4Impl ajc$this, ListOpenRidsParam data, JoinPoint var2) {
      ApplicationConfig.getInstance().assertValidSession();

      try {
         ListOpenRids request = ajc$this.getListOpenRids(data);

         try {
            ListOpenRidsResponse getDataResponse = RecipePatientServiceDevV4Impl.getInstance().listOpenRids(request);
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
   static final CreateRelationResult putData_aroundBody12(PatientIntegrationModuleDevV4Impl ajc$this, CreateRelationParam patientRelationParam, JoinPoint var2) {
      ApplicationConfig.getInstance().assertValidSession();

      try {
         CreateRelation request = ajc$this.getCreateRelation(patientRelationParam);

         try {
            CreateRelationResponse dataResponse = RecipePatientServiceDevV4Impl.getInstance().createRelation(request);
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
      Factory var0 = new Factory("PatientIntegrationModuleDevV4Impl.java", PatientIntegrationModuleDevV4Impl.class);
      ajc$tjp_0 = var0.makeSJP("method-execution", var0.makeMethodSig("1", "getData", "be.business.connector.recipe.patient.PatientIntegrationModuleDevV4Impl", "be.recipe.services.patient.GetVisionParam", "data", "be.business.connector.core.exceptions.IntegrationModuleException", "be.recipe.services.patient.GetVisionResult"), 202);
      ajc$tjp_1 = var0.makeSJP("method-execution", var0.makeMethodSig("1", "putData", "be.business.connector.recipe.patient.PatientIntegrationModuleDevV4Impl", "be.recipe.services.patient.PutVisionParam", "putVisionParam", "be.business.connector.core.exceptions.IntegrationModuleException", "be.recipe.services.patient.PutVisionResult"), 312);
      ajc$tjp_2 = var0.makeSJP("method-execution", var0.makeMethodSig("1", "putData", "be.business.connector.recipe.patient.PatientIntegrationModuleDevV4Impl", "be.recipe.services.patient.CreateReservationParam", "data", "be.business.connector.core.exceptions.IntegrationModuleException", "be.recipe.services.patient.CreateReservationResult"), 336);
      ajc$tjp_3 = var0.makeSJP("method-execution", var0.makeMethodSig("1", "getData", "be.business.connector.recipe.patient.PatientIntegrationModuleDevV4Impl", "be.recipe.services.patient.GetPrescriptionStatusParam", "data", "be.business.connector.core.exceptions.IntegrationModuleException", "be.recipe.services.patient.GetPrescriptionStatusResult"), 406);
      ajc$tjp_4 = var0.makeSJP("method-execution", var0.makeMethodSig("1", "getData", "be.business.connector.recipe.patient.PatientIntegrationModuleDevV4Impl", "be.recipe.services.patient.ListRidsHistoryParam", "param", "be.business.connector.core.exceptions.IntegrationModuleException", "be.recipe.services.patient.ListRidsHistoryResult"), 446);
      ajc$tjp_5 = var0.makeSJP("method-execution", var0.makeMethodSig("1", "getData", "be.business.connector.recipe.patient.PatientIntegrationModuleDevV4Impl", "be.recipe.services.patient.ListOpenRidsParam", "data", "be.business.connector.core.exceptions.IntegrationModuleException", "be.recipe.services.patient.ListOpenRidsResult"), 483);
      ajc$tjp_6 = var0.makeSJP("method-execution", var0.makeMethodSig("1", "putData", "be.business.connector.recipe.patient.PatientIntegrationModuleDevV4Impl", "be.recipe.services.patient.CreateRelationParam", "patientRelationParam", "be.business.connector.core.exceptions.IntegrationModuleException", "be.recipe.services.patient.CreateRelationResult"), 736);
   }
}
