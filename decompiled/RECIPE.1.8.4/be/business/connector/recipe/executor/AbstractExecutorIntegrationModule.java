package be.business.connector.recipe.executor;

import be.business.connector.common.StandaloneRequestorProvider;
import be.business.connector.common.module.AbstractIntegrationModule;
import be.business.connector.core.domain.KgssIdentifierType;
import be.business.connector.core.exceptions.IntegrationModuleException;
import be.business.connector.core.handlers.InsurabilityHandler;
import be.business.connector.core.utils.I18nHelper;
import be.business.connector.core.utils.IOUtils;
import be.business.connector.core.utils.MarshallerHelper;
import be.business.connector.recipe.executor.domain.GetPrescriptionForExecutorResult;
import be.business.connector.recipe.utils.KmehrHelper;
import be.ehealth.technicalconnector.service.kgss.domain.KeyResult;
import be.fgov.ehealth.commons.core.v1.IdentifierType;
import be.fgov.ehealth.commons.core.v1.LocalisedString;
import be.fgov.ehealth.commons.core.v1.StatusType;
import be.fgov.ehealth.commons.protocol.v1.ResponseType;
import be.fgov.ehealth.commons.protocol.v2.ObjectFactory;
import be.fgov.ehealth.commons.protocol.v2.StatusResponseType;
import be.fgov.ehealth.etee.crypto.encrypt.EncryptionToken;
import be.fgov.ehealth.recipe.core.v4.SecuredContentType;
import be.recipe.services.executor.CreateFeedbackParam;
import be.recipe.services.executor.GetPrescriptionForExecutorParam;
import be.recipe.services.executor.GetPrescriptionForExecutorResultSealed;
import be.recipe.services.executor.GetPrescriptionStatusParam;
import be.recipe.services.executor.ListNotificationsItem;
import be.recipe.services.executor.ListNotificationsParam;
import be.recipe.services.executor.ListNotificationsResult;
import be.recipe.services.executor.ListReservations;
import be.recipe.services.executor.ListReservationsParam;
import be.recipe.services.executor.ListReservationsResult;
import be.recipe.services.executor.ListReservationsResultItem;
import be.recipe.services.executor.MarkAsArchivedParam;
import be.recipe.services.executor.MarkAsDeliveredParam;
import be.recipe.services.executor.MarkAsUndeliveredParam;
import be.recipe.services.executor.PutRidsInProcess;
import be.recipe.services.executor.RevokePrescriptionParam;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;

public abstract class AbstractExecutorIntegrationModule extends AbstractIntegrationModule {
   private static final Logger LOG = Logger.getLogger(AbstractExecutorIntegrationModule.class);
   private static Map<String, GetPrescriptionForExecutorResult> prescriptionCache;

   public AbstractExecutorIntegrationModule() throws IntegrationModuleException {
   }

   public static Map<String, GetPrescriptionForExecutorResult> getPrescriptionCache() {
      if (prescriptionCache == null) {
         prescriptionCache = new HashMap();
      }

      return prescriptionCache;
   }

   protected KmehrHelper getKmehrHelper() {
      return new KmehrHelper();
   }

   protected byte[] getSealedRevokePrescriptionParam(String rid, String reason) throws IntegrationModuleException {
      RevokePrescriptionParam param = new RevokePrescriptionParam();
      param.setRid(rid);
      param.setReason(reason);
      param.setExecutorId(StandaloneRequestorProvider.getRequestorIdInformation());
      param.setSymmKey(this.getSymmKey().getEncoded());
      return this.sealForRecipe(param, RevokePrescriptionParam.class);
   }

   protected byte[] getSealedMarkAsArchivedParam(String rid) throws IntegrationModuleException {
      MarkAsArchivedParam param = new MarkAsArchivedParam();
      param.setRid(rid);
      param.setExecutorId(StandaloneRequestorProvider.getRequestorIdInformation());
      param.setSymmKey(this.getSymmKey().getEncoded());
      return this.sealForRecipe(param, MarkAsArchivedParam.class);
   }

   protected byte[] getSealedMarkAsDeliveredParam(String rid) throws IntegrationModuleException {
      MarkAsDeliveredParam param = new MarkAsDeliveredParam();
      param.setRid(rid);
      param.setExecutorId(StandaloneRequestorProvider.getRequestorIdInformation());
      param.setSymmKey(this.getSymmKey().getEncoded());
      return this.sealForRecipe(param, MarkAsDeliveredParam.class);
   }

   protected byte[] getSealedMarkAsUnDeliveredParam(String rid) throws IntegrationModuleException {
      MarkAsUndeliveredParam param = new MarkAsUndeliveredParam();
      param.setRid(rid);
      param.setExecutorId(StandaloneRequestorProvider.getRequestorIdInformation());
      param.setSymmKey(this.getSymmKey().getEncoded());
      return this.sealForRecipe(param, MarkAsUndeliveredParam.class);
   }

   protected byte[] getSealedGetPrescriptionForExecutorParam(String rid) throws IntegrationModuleException {
      return this.getSealedGetPrescriptionForExecutorParam(rid, (String)null);
   }

   protected byte[] getSealedGetPrescriptionForExecutorParam(String rid, String patientId) throws IntegrationModuleException {
      GetPrescriptionForExecutorParam param = new GetPrescriptionForExecutorParam();
      param.setRid(rid);
      param.setPatientId(patientId);
      param.setSymmKey(this.getSymmKey().getEncoded());
      param.setVersion(this.getPropertyHandler().getProperty("connector.version", "v2"));
      param.setExecutorId(StandaloneRequestorProvider.getRequestorIdInformation());
      return this.sealForRecipe(param, GetPrescriptionForExecutorParam.class);
   }

   protected byte[] getSealedCreateFeedbackParam(byte[] feedbackText, EncryptionToken etkRecipient, String rid, String prescriberId) throws Exception {
      byte[] message = IOUtils.compress(feedbackText);
      message = this.sealRequest(etkRecipient, message);
      CreateFeedbackParam param = new CreateFeedbackParam();
      param.setRid(rid);
      param.setContent(message);
      param.setSymmKey(this.getSymmKey().getEncoded());
      param.setPrescriberId(prescriberId);
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

   protected byte[] getSealedData(GetPrescriptionStatusParam getPrescriptionStatusParam) throws IntegrationModuleException {
      getPrescriptionStatusParam.setSymmKey(this.getSymmKey().getEncoded());
      return this.sealForRecipe(getPrescriptionStatusParam, GetPrescriptionStatusParam.class);
   }

   protected <T> byte[] sealForRecipe(T data, Class<T> type) throws IntegrationModuleException {
      MarshallerHelper<Object, T> helper = new MarshallerHelper(Object.class, type);
      EncryptionToken etkRecipe = (EncryptionToken)this.getEtkHelper().getRecipe_ETK().get(0);
      return this.sealRequest(etkRecipe, helper.toXMLByteArray(data));
   }

   protected GetPrescriptionForExecutorResult createGetPrescriptionForExecutorResult(GetPrescriptionForExecutorResultSealed getPrescriptionForExecutorResultSealed) throws IntegrationModuleException {
      String requestorIdInformation = StandaloneRequestorProvider.getRequestorIdInformation();
      String requestorTypeInformation = StandaloneRequestorProvider.getRequestorTypeInformation();
      KeyResult key = this.getKeyFromKgss(getPrescriptionForExecutorResultSealed.getEncryptionKeyId(), ((EncryptionToken)this.getEtkHelper().getEtks(KgssIdentifierType.NIHII_PHARMACY, requestorIdInformation).get(0)).getEncoded());
      byte[] unsealedPrescription = this.unsealWithSymKey(getPrescriptionForExecutorResultSealed, key, requestorIdInformation, requestorTypeInformation);
      GetPrescriptionForExecutorResult finalResult = new GetPrescriptionForExecutorResult(getPrescriptionForExecutorResultSealed);
      finalResult.setSealedContent(getPrescriptionForExecutorResultSealed.getPrescription());
      finalResult.setPrescription(unsealedPrescription);
      finalResult.setEncryptionKey(key.getSecretKey().getEncoded());
      finalResult.setInsurabilityResponse(InsurabilityHandler.getInsurability());
      finalResult.setMessageId(InsurabilityHandler.getMessageId());
      return finalResult;
   }

   protected List<ListNotificationsItem> createListNotificationItems(byte[] sealedExecutorResponse) throws IntegrationModuleException {
      MarshallerHelper<ListNotificationsResult, Object> marshaller = new MarshallerHelper(ListNotificationsResult.class, Object.class);
      ListNotificationsResult result = (ListNotificationsResult)marshaller.unsealWithSymmKey(sealedExecutorResponse, this.getSymmKey());
      List<ListNotificationsItem> items = result.getNotifications();

      for(int i = 0; i < items.size(); ++i) {
         be.business.connector.recipe.executor.domain.ListNotificationsItem item = new be.business.connector.recipe.executor.domain.ListNotificationsItem((ListNotificationsItem)items.get(i));
         if (item != null && item.getContent() != null) {
            try {
               byte[] bytes = this.unsealNotiffeed(item.getContent());
               if (bytes != null) {
                  item.setContent(bytes != null ? IOUtils.decompress(bytes) : null);
               }
            } catch (IOException var8) {
               item.setLinkedException(var8);
            }
         }

         items.set(i, item);
      }

      return items;
   }

   protected void checkStatus(ResponseType responseType) throws IntegrationModuleException {
      if (!"100".equals(responseType.getStatus().getCode()) && !"200".equals(responseType.getStatus().getCode())) {
         LOG.error("Error Status received : " + responseType.getStatus().getCode());
         throw new IntegrationModuleException(this.getLocalisedMsg(responseType.getStatus()));
      }
   }

   protected void checkResponseStatus(StatusResponseType statusType) {
      new ObjectFactory();
      if (!"urn:be:fgov:ehealth:2.0:status:Success".equals(statusType.getStatus().getStatusCode())) {
         LOG.error("Error Status received : " + statusType.getStatus().getStatusCode());
         throw new IntegrationModuleException(statusType.getStatus().getStatusMessage());
      }
   }

   protected void checkStatus(be.recipe.services.core.ResponseType responseType) throws IntegrationModuleException {
      if (!"100".equals(responseType.getStatus().getCode()) && !"200".equals(responseType.getStatus().getCode())) {
         LOG.error("Error Status received : " + responseType.getStatus().getCode());
         throw new IntegrationModuleException(this.getLocalisedMsg(responseType.getStatus()), responseType);
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

   protected IdentifierType createIdentifierType(String id, String type) {
      IdentifierType ident = new IdentifierType();
      ident.setId(id);
      ident.setType(type);
      return ident;
   }

   private SecuredContentType getSealedData(ListReservations listReservations) {
      SecuredContentType sct = new SecuredContentType();
      sct.setSecuredContent(this.sealForRecipe(listReservations, ListReservations.class));
      return sct;
   }

   protected void writeReservationsOnDisk(ListReservationsParam listReservationsParam, ListReservationsResult listReservationsResult, Calendar lastSyncDate) throws IntegrationModuleException {
      this.writeLastDateToDisk(listReservationsParam, lastSyncDate);
      this.writeRidsToDisk(listReservationsResult);
   }

   private void writeRidsToDisk(ListReservationsResult listReservationsResult) throws IntegrationModuleException {
      String path = this.getPropertyHandler().getProperty("reservation.path");
      File folder = new File(path);
      Iterator var5 = listReservationsResult.getItems().iterator();

      while(var5.hasNext()) {
         ListReservationsResultItem item = (ListReservationsResultItem)var5.next();

         try {
            DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
            String creationDate = df.format(item.getCreationDate().getTime());
            File file = new File(folder + "/" + item.getRid() + ".txt");
            if (!file.exists()) {
               file.createNewFile();
            }

            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(creationDate);
            bw.close();
         } catch (IOException var12) {
            throw new IntegrationModuleException(I18nHelper.getLabel("error.connection.executor"), var12);
         }
      }

   }

   protected void writeLastDateToDisk(ListReservationsParam listReservationsParam, Calendar lastSyncDate) throws IntegrationModuleException {
      try {
         String path = this.getPropertyHandler().getProperty("reservation.path");
         File filePath = new File(path);
         if (!filePath.exists()) {
            filePath.mkdirs();
         }

         File file = new File(path + "/lastReservationSyncDate.txt");
         file.createNewFile();
         Throwable var6 = null;
         Object var7 = null;

         try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(file.getAbsoluteFile()));

            try {
               DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
               String date = df.format(lastSyncDate.getTime());
               bw.write(date);
               bw.flush();
            } finally {
               if (bw != null) {
                  bw.close();
               }

            }

         } catch (Throwable var18) {
            if (var6 == null) {
               var6 = var18;
            } else if (var6 != var18) {
               var6.addSuppressed(var18);
            }

            throw var6;
         }
      } catch (IOException var19) {
         throw new IntegrationModuleException(I18nHelper.getLabel("error.connection.executor"), var19);
      }
   }

   protected Calendar readLastDateToDisk() throws IntegrationModuleException {
      // $FF: Couldn't be decompiled
   }

   private <T> byte[] marshall(T data, Class<T> type) {
      MarshallerHelper<Object, T> helper = new MarshallerHelper(Object.class, type);
      return helper.toXMLByteArray(data);
   }

   private SecuredContentType getSealedData(PutRidsInProcess putAllRidInProcess) {
      SecuredContentType sct = new SecuredContentType();
      sct.setSecuredContent(this.sealForRecipe(putAllRidInProcess, PutRidsInProcess.class));
      return sct;
   }
}
