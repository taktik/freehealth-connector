package be.business.connector.recipe.patient;

import be.business.connector.common.StandaloneRequestorProvider;
import be.business.connector.common.module.AbstractIntegrationModule;
import be.business.connector.core.domain.KgssIdentifierType;
import be.business.connector.core.exceptions.IntegrationModuleException;
import be.business.connector.core.utils.IOUtils;
import be.business.connector.core.utils.MarshallerHelper;
import be.business.connector.recipe.patient.domain.GetVisionParam;
import be.business.connector.recipe.patient.domain.PutReservationParam;
import be.business.connector.recipe.patient.domain.PutVisionParam;
import be.ehealth.technicalconnector.service.kgss.domain.KeyResult;
import be.fgov.ehealth.commons.core.v1.LocalisedString;
import be.fgov.ehealth.commons.core.v1.StatusType;
import be.fgov.ehealth.etee.crypto.encrypt.EncryptionToken;
import be.fgov.ehealth.recipe.core.v1.SecuredContentType;
import be.recipe.services.core.ResponseType;
import be.recipe.services.patient.GetPrescriptionForPatientParam;
import be.recipe.services.patient.GetPrescriptionForPatientResult;
import be.recipe.services.patient.GetPrescriptionForPatientResultSealed;
import be.recipe.services.patient.RevokePrescriptionParam;
import java.io.IOException;
import java.util.Iterator;
import org.apache.log4j.Logger;

public abstract class AbstractPatientIntegrationModule extends AbstractIntegrationModule {
   private static final Logger LOG = Logger.getLogger(AbstractPatientIntegrationModule.class);

   public AbstractPatientIntegrationModule() throws IntegrationModuleException {
   }

   protected GetPrescriptionForPatientResult unsealPrescription(byte[] result) throws IntegrationModuleException, IOException {
      MarshallerHelper<GetPrescriptionForPatientResult, Object> marshaller = new MarshallerHelper(GetPrescriptionForPatientResult.class, Object.class);
      GetPrescriptionForPatientResult unsealedResult = (GetPrescriptionForPatientResult)marshaller.unsealWithSymmKey(result, this.getSymmKey());
      this.checkStatus((ResponseType)unsealedResult);
      this.unsealPrescriptionBytes(unsealedResult);
      return unsealedResult;
   }

   private void unsealPrescriptionBytes(GetPrescriptionForPatientResult result) throws IntegrationModuleException, IOException {
      KeyResult key = this.getKeyFromKgss(result.getEncryptionKeyId(), ((EncryptionToken)this.getEtkHelper().getSystemETK().get(0)).getEncoded());
      byte[] unsealedPrescription = IOUtils.decompress(this.unsealPrescriptionForUnknown(key, result.getPrescription()));
      result.setPrescription(unsealedPrescription);
   }

   protected GetPrescriptionForPatientResult createGetPrescriptionForPatientResult(byte[] sealedExecutorResponse) throws IntegrationModuleException {
      MarshallerHelper<GetPrescriptionForPatientResultSealed, Object> marshaller = new MarshallerHelper(GetPrescriptionForPatientResultSealed.class, Object.class);
      String requestorIdInformation = StandaloneRequestorProvider.getRequestorIdInformation();
      String requestorTypeInformation = StandaloneRequestorProvider.getRequestorTypeInformation();
      GetPrescriptionForPatientResultSealed sealedResult = (GetPrescriptionForPatientResultSealed)marshaller.unsealWithSymmKey(sealedExecutorResponse, this.getSymmKey());
      KeyResult key = this.getKeyFromKgss(sealedResult.getEncryptionKeyId(), ((EncryptionToken)this.getEtkHelper().getEtks(KgssIdentifierType.NIHII_PHARMACY, requestorIdInformation).get(0)).getEncoded());
      byte[] unsealedPrescription = this.unsealWithSymKey(sealedResult, key, requestorIdInformation, requestorTypeInformation);
      GetPrescriptionForPatientResult finalResult = new GetPrescriptionForPatientResult();
      finalResult.setPrescription(unsealedPrescription);
      return finalResult;
   }

   protected byte[] getSealedGetPrescriptionForPatientParam(String rid) throws IntegrationModuleException {
      GetPrescriptionForPatientParam param = new GetPrescriptionForPatientParam();
      param.setRid(rid);
      param.setSymmKey(this.getSymmKey().getEncoded());
      return this.sealForRecipe(param, GetPrescriptionForPatientParam.class);
   }

   protected <T> byte[] sealForRecipe(T data, Class<T> type) throws IntegrationModuleException {
      MarshallerHelper<Object, T> helper = new MarshallerHelper(Object.class, type);
      EncryptionToken etkRecipe = (EncryptionToken)this.getEtkHelper().getRecipe_ETK().get(0);
      return this.sealRequest(etkRecipe, helper.toXMLByteArray(data));
   }

   protected <T> byte[] marshall(T data, Class<T> type) throws IntegrationModuleException {
      MarshallerHelper<Object, T> helper = new MarshallerHelper(Object.class, type);
      return helper.toXMLByteArray(data);
   }

   protected SecuredContentType createSecuredContentType(byte[] content) {
      SecuredContentType secured = new SecuredContentType();
      secured.setSecuredContent(content);
      return secured;
   }

   protected void checkStatus(be.fgov.ehealth.commons.protocol.v1.ResponseType response) throws IntegrationModuleException {
      if (!"100".equals(response.getStatus().getCode()) && !"200".equals(response.getStatus().getCode())) {
         LOG.error("Error Status received : " + response.getStatus().getCode());
         throw new IntegrationModuleException(this.getLocalisedMsg(response.getStatus()));
      }
   }

   protected void checkStatus(ResponseType response) throws IntegrationModuleException {
      if (response != null && response.getStatus() != null && !"100".equals(response.getStatus().getCode()) && !"200".equals(response.getStatus().getCode())) {
         LOG.error("Error Status received : " + response.getStatus().getCode());
         throw new IntegrationModuleException(this.getLocalisedMsg(response.getStatus()), response);
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

   private String getLocalisedMsg(be.recipe.services.core.StatusType status) {
      String locale = IntegrationModuleException.getUserLocale();
      Iterator var4 = status.getMessages().iterator();

      be.recipe.services.core.LocalisedString msg;
      do {
         if (!var4.hasNext()) {
            if (status.getMessages().size() > 0) {
               return ((be.recipe.services.core.LocalisedString)status.getMessages().get(0)).getValue();
            }

            return status.getCode();
         }

         msg = (be.recipe.services.core.LocalisedString)var4.next();
      } while(msg.getLang() == null || !locale.equalsIgnoreCase(msg.getLang().value()));

      return msg.getValue();
   }

   protected byte[] getSealedRevokePrescriptionParam(String rid, String reason) throws IntegrationModuleException {
      RevokePrescriptionParam param = new RevokePrescriptionParam();
      param.setRid(rid);
      param.setReason(reason);
      param.setSymmKey(this.getSymmKey().getEncoded());
      return this.sealForRecipe(param, RevokePrescriptionParam.class);
   }

   protected byte[] getSealedData(GetVisionParam getVisionParam) throws IntegrationModuleException {
      return this.sealForRecipe(getVisionParam, GetVisionParam.class);
   }

   protected byte[] getSealedData(PutReservationParam putReservationParam) throws IntegrationModuleException {
      return this.sealForRecipe(putReservationParam, PutReservationParam.class);
   }

   protected byte[] getSealedData(PutVisionParam putVisionParam) throws IntegrationModuleException {
      return this.sealForRecipe(putVisionParam, PutVisionParam.class);
   }
}
