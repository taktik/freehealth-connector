package org.taktik.connector.business.recipe.executor;

import be.business.connector.common.StandaloneRequestorProvider;
import be.business.connector.common.module.AbstractIntegrationModule;
import org.taktik.connector.business.recipeprojects.core.domain.KgssIdentifierType;
import org.taktik.connector.business.recipeprojects.core.exceptions.IntegrationModuleException;
import org.taktik.connector.business.recipeprojects.core.handlers.InsurabilityHandler;
import org.taktik.connector.business.recipeprojects.core.utils.IOUtils;
import org.taktik.connector.business.recipeprojects.core.utils.MarshallerHelper;
import org.taktik.connector.business.recipeprojects.core.utils.PropertyHandler;
import org.taktik.connector.business.recipe.executor.domain.GetPrescriptionForExecutorResult;
import org.taktik.connector.business.recipe.utils.KmehrHelper;
import be.ehealth.technicalconnector.service.kgss.domain.KeyResult;
import be.fgov.ehealth.commons.core.v1.IdentifierType;
import be.fgov.ehealth.commons.core.v1.LocalisedString;
import be.fgov.ehealth.commons.core.v1.StatusType;
import be.fgov.ehealth.commons.protocol.v1.ResponseType;
import be.fgov.ehealth.etee.crypto.encrypt.EncryptionToken;
import be.recipe.services.executor.CreateFeedbackParam;
import be.recipe.services.executor.GetPrescriptionForExecutorParam;
import be.recipe.services.executor.GetPrescriptionForExecutorResultSealed;
import be.recipe.services.executor.ListNotificationsItem;
import be.recipe.services.executor.ListNotificationsParam;
import be.recipe.services.executor.ListNotificationsResult;
import be.recipe.services.executor.MarkAsArchivedParam;
import be.recipe.services.executor.MarkAsDeliveredParam;
import be.recipe.services.executor.MarkAsUndeliveredParam;
import be.recipe.services.executor.RevokePrescriptionParam;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;

public abstract class AbstractExecutorIntegrationModule extends AbstractIntegrationModule {
   private static final Logger LOG = Logger.getLogger(AbstractExecutorIntegrationModule.class);
   private Map<String, GetPrescriptionForExecutorResult> prescriptionCache = new HashMap();
   private KmehrHelper kmehrHelper = new KmehrHelper(PropertyHandler.getInstance().getProperties());

   public AbstractExecutorIntegrationModule() throws IntegrationModuleException {
   }

   public Map<String, GetPrescriptionForExecutorResult> getPrescriptionCache() {
      return this.prescriptionCache;
   }

   public KmehrHelper getKmehrHelper() {
      return this.kmehrHelper;
   }

   protected byte[] getSealedRevokePrescriptionParam(String rid, String reason) throws IntegrationModuleException {
      RevokePrescriptionParam param = new RevokePrescriptionParam();
      param.setRid(rid);
      param.setReason(reason);
      param.setExecutorId(StandaloneRequestorProvider.getRequestorIdInformation());
      return this.sealForRecipe(param, RevokePrescriptionParam.class);
   }

   protected byte[] getSealedMarkAsArchivedParam(String rid) throws IntegrationModuleException {
      MarkAsArchivedParam param = new MarkAsArchivedParam();
      param.setRid(rid);
      param.setExecutorId(StandaloneRequestorProvider.getRequestorIdInformation());
      return this.sealForRecipe(param, MarkAsArchivedParam.class);
   }

   protected byte[] getSealedMarkAsDeliveredParam(String rid) throws IntegrationModuleException {
      MarkAsDeliveredParam param = new MarkAsDeliveredParam();
      param.setRid(rid);
      param.setExecutorId(StandaloneRequestorProvider.getRequestorIdInformation());
      return this.sealForRecipe(param, MarkAsDeliveredParam.class);
   }

   protected byte[] getSealedMarkAsUndeliveredParam(String rid) throws IntegrationModuleException {
      MarkAsUndeliveredParam param = new MarkAsUndeliveredParam();
      param.setRid(rid);
      param.setExecutorId(StandaloneRequestorProvider.getRequestorIdInformation());
      return this.sealForRecipe(param, MarkAsUndeliveredParam.class);
   }

   protected byte[] getSealedGetPrescriptionForExecutorParam(String rid) throws IntegrationModuleException {
      GetPrescriptionForExecutorParam param = new GetPrescriptionForExecutorParam();
      param.setRid(rid);
      param.setSymmKey(this.getSymmKey().getEncoded());
      param.setVersion(this.getPropertyHandler().getProperty("connector.version"));
      param.setExecutorId(StandaloneRequestorProvider.getRequestorIdInformation());
      return this.sealForRecipe(param, GetPrescriptionForExecutorParam.class);
   }

   protected byte[] getSealedCreateFeedbackParam(byte[] feedbackText, EncryptionToken etkRecipient, String rid, String prescriberId) throws Exception {
      byte[] message = IOUtils.compress(feedbackText);
      message = this.sealRequest(etkRecipient, message);
      CreateFeedbackParam param = new CreateFeedbackParam();
      param.setContent(message);
      param.setPrescriberId(prescriberId);
      param.setRid(rid);
      param.setExecutorId(StandaloneRequestorProvider.getRequestorIdInformation());
      return this.sealForRecipe(param, CreateFeedbackParam.class);
   }

   protected byte[] getSealedListNotificationsParam(boolean readFlag) throws IntegrationModuleException {
      ListNotificationsParam param = new ListNotificationsParam();
      param.setSymmKey(this.getSymmKey().getEncoded());
      param.setReadFlag(readFlag);
      param.setExecutorId(StandaloneRequestorProvider.getRequestorIdInformation());
      return this.sealForRecipe(param, ListNotificationsParam.class);
   }

   private <T> byte[] sealForRecipe(T data, Class<T> type) throws IntegrationModuleException {
      MarshallerHelper<Object, T> helper = new MarshallerHelper(Object.class, type);
      EncryptionToken etkRecipe = (EncryptionToken)this.getEtkHelper().getRecipe_ETK().get(0);
      return this.sealRequest(etkRecipe, helper.toXMLByteArray(data));
   }

   protected GetPrescriptionForExecutorResult createGetPrescriptionForExecutorResult(byte[] sealedExecutorResponse) throws IntegrationModuleException {
      MarshallerHelper<GetPrescriptionForExecutorResultSealed, Object> marshaller = new MarshallerHelper(GetPrescriptionForExecutorResultSealed.class, Object.class);
      String requestorIdInformation = StandaloneRequestorProvider.getRequestorIdInformation();
      String requestorTypeInformation = StandaloneRequestorProvider.getRequestorTypeInformation();
      GetPrescriptionForExecutorResultSealed sealedResult = (GetPrescriptionForExecutorResultSealed)marshaller.unsealWithSymmKey(sealedExecutorResponse, this.getSymmKey());
      KeyResult key = this.getKeyFromKgss(sealedResult.getEncryptionKeyId(), ((EncryptionToken)this.getEtkHelper().getEtks(KgssIdentifierType.NIHII_PHARMACY, requestorIdInformation).get(0)).getEncoded());
      byte[] unsealedPrescription = this.unsealWithSymKey(sealedResult, key, requestorIdInformation, requestorTypeInformation);
      GetPrescriptionForExecutorResult finalResult = new GetPrescriptionForExecutorResult(sealedResult);
      finalResult.setSealedContent(sealedResult.getPrescription());
      finalResult.setPrescription(unsealedPrescription);
      finalResult.setEncryptionKey(key.getSecretKey().getEncoded());
      finalResult.setInsurabilityResponse(InsurabilityHandler.getInsurability());
      finalResult.setMessageId(InsurabilityHandler.getMessageId());
      return finalResult;
   }

   protected List<ListNotificationsItem> createListNotificationItems(byte[] sealedExecutorResponse) throws IntegrationModuleException {
      MarshallerHelper<ListNotificationsResult, Object> marshaller = new MarshallerHelper(ListNotificationsResult.class, Object.class);
      List<ListNotificationsItem> items = ((ListNotificationsResult)marshaller.unsealWithSymmKey(sealedExecutorResponse, this.getSymmKey())).getAddressedNotifications();

      for(int i = 0; i < items.size(); ++i) {
         org.taktik.connector.business.recipe.executor.domain.ListNotificationsItem item = new org.taktik.connector.business.recipe.executor.domain.ListNotificationsItem((ListNotificationsItem)items.get(i));

         try {
            item.setContent(IOUtils.decompress(this.unsealNotiffeed(item.getContent())));
         } catch (Throwable var7) {
            item.setLinkedException(var7);
         }

         items.set(i, item);
      }

      return items;
   }

   protected void checkStatus(ResponseType response) throws IntegrationModuleException {
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

   protected IdentifierType createIdentifierType(String id, String type) {
      IdentifierType ident = new IdentifierType();
      ident.setId(id);
      ident.setType(type);
      return ident;
   }
}
