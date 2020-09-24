package be.business.connector.recipe.patient;

import be.business.connector.common.ApplicationConfig;
import be.business.connector.core.exceptions.IntegrationModuleException;
import be.business.connector.core.handlers.InsurabilityHandler;
import be.business.connector.core.utils.Exceptionutils;
import be.business.connector.core.utils.I18nHelper;
import be.business.connector.core.utils.MarshallerHelper;
import be.business.connector.recipe.patient.services.RecipePatientServiceImpl;
import be.business.connector.recipe.utils.RidValidator;
import be.fgov.ehealth.etee.crypto.encrypt.EncryptionToken;
import be.fgov.ehealth.recipe.core.v1.SecuredContentType;
import be.fgov.ehealth.recipe.protocol.v1.GetPrescriptionForPatientRequest;
import be.fgov.ehealth.recipe.protocol.v1.GetPrescriptionForPatientResponse;
import be.fgov.ehealth.recipe.protocol.v1.ListPatientPrescriptionsRequest;
import be.fgov.ehealth.recipe.protocol.v1.ListPatientPrescriptionsResponse;
import be.fgov.ehealth.recipe.protocol.v1.RevokePatientPrescriptionRequest;
import be.fgov.ehealth.recipe.protocol.v1.UpdatePatientFeedbackFlagRequest;
import be.recipe.services.patient.GetPrescriptionForPatientResult;
import be.recipe.services.patient.ListPatientPrescriptionsParam;
import be.recipe.services.patient.ListPatientPrescriptionsResult;
import be.recipe.services.patient.UpdateFeedbackFlagParam;
import com.sun.xml.ws.client.ClientTransportException;
import java.util.List;

public class PatientIntegrationModuleImpl extends AbstractPatientIntegrationModule implements PatientIntegrationModule {
   public PatientIntegrationModuleImpl() throws IntegrationModuleException {
      ApplicationConfig.getInstance().assertInitialized();
      this.init();
   }

   public ListPatientPrescriptionsResult listOpenPrescriptions() throws IntegrationModuleException {
      ApplicationConfig.getInstance().assertValidSession();

      try {
         MarshallerHelper<ListPatientPrescriptionsResult, ListPatientPrescriptionsParam> helper = new MarshallerHelper(ListPatientPrescriptionsResult.class, ListPatientPrescriptionsParam.class);
         List<EncryptionToken> etkRecipes = this.getEtkHelper().getRecipe_ETK();
         ListPatientPrescriptionsParam param = new ListPatientPrescriptionsParam();
         param.setSymmKey(this.getSymmKey().getEncoded());
         ListPatientPrescriptionsRequest request = new ListPatientPrescriptionsRequest();
         request.setSecuredListPatientPrescriptionsRequest(this.createSecuredContentType(this.sealRequest((EncryptionToken)etkRecipes.get(0), helper.toXMLByteArray(param))));
         ListPatientPrescriptionsResponse response = null;

         try {
            response = RecipePatientServiceImpl.getInstance().listOpenPrescriptions(request);
         } catch (ClientTransportException var7) {
            throw new IntegrationModuleException(I18nHelper.getLabel("error.connection.prescriber"), var7);
         }

         this.checkStatus(response);
         ListPatientPrescriptionsResult result = (ListPatientPrescriptionsResult)helper.unsealWithSymmKey(response.getSecuredListPatientPrescriptionsResponse().getSecuredContent(), this.getSymmKey());
         return result;
      } catch (Throwable var8) {
         Exceptionutils.errorHandler(var8);
         return null;
      }
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
         UpdatePatientFeedbackFlagRequest request = new UpdatePatientFeedbackFlagRequest();
         request.setSecuredUpdatePatientFeedbackFlagRequest(this.createSecuredContentType(this.sealRequest((EncryptionToken)etkRecipes.get(0), helper.toXMLByteArray(param))));

         try {
            this.checkStatus(RecipePatientServiceImpl.getInstance().updateFeedbackFlag(request));
         } catch (ClientTransportException var8) {
            throw new IntegrationModuleException(I18nHelper.getLabel("error.connection.prescriber"), var8);
         }
      } catch (Throwable var9) {
         Exceptionutils.errorHandler(var9);
      }

   }

   public void revokePrescription(String rid, String reason) throws IntegrationModuleException {
      RidValidator.validateRid(rid);
      ApplicationConfig.getInstance().assertValidSession();

      try {
         byte[] sealedRevokePrescriptionParam = this.getSealedRevokePrescriptionParam(rid, reason);
         RevokePatientPrescriptionRequest request = new RevokePatientPrescriptionRequest();
         request.setSecuredRevokePatientPrescriptionRequest(this.createSecuredContentType(sealedRevokePrescriptionParam));

         try {
            this.checkStatus(RecipePatientServiceImpl.getInstance().revokePrescription(request));
         } catch (ClientTransportException var6) {
            throw new IntegrationModuleException(I18nHelper.getLabel("error.connection.executor"), var6);
         }
      } catch (Throwable var7) {
         Exceptionutils.errorHandler(var7);
      }

   }

   public GetPrescriptionForPatientResult getPrescription(String rid) throws IntegrationModuleException {
      RidValidator.validateRid(rid);
      ApplicationConfig.getInstance().assertValidSession();
      InsurabilityHandler.setInsurability((String)null);
      InsurabilityHandler.setMessageId((String)null);

      try {
         byte[] sealedContent = this.getSealedGetPrescriptionForPatientParam(rid);
         GetPrescriptionForPatientRequest request = new GetPrescriptionForPatientRequest();
         SecuredContentType securedContent = new SecuredContentType();
         securedContent.setSecuredContent(sealedContent);
         request.setSecuredGetPrescriptionForPatientRequest(securedContent);
         GetPrescriptionForPatientResponse response = null;

         try {
            response = RecipePatientServiceImpl.getInstance().getPrescriptionForPatient(request);
         } catch (ClientTransportException var8) {
            throw new IntegrationModuleException(I18nHelper.getLabel("error.connection.executor"), var8);
         }

         this.checkStatus(response);
         byte[] securedContentResponse = response.getSecuredGetPrescriptionForPatientResponse().getSecuredContent();
         GetPrescriptionForPatientResult finalResult = this.unsealPrescription(securedContentResponse);
         return finalResult;
      } catch (Throwable var9) {
         Exceptionutils.errorHandler(var9);
         return null;
      }
   }
}
