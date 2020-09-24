package be.business.connector.recipe.prescriber;

import be.business.connector.common.StandaloneRequestorProvider;
import be.business.connector.common.module.AbstractIntegrationModule;
import be.business.connector.core.domain.KgssIdentifierType;
import be.business.connector.core.ehealth.services.KgssService;
import be.business.connector.core.ehealth.services.KgssServiceImpl;
import be.business.connector.core.exceptions.IntegrationModuleException;
import be.business.connector.core.technical.connector.utils.Crypto;
import be.business.connector.core.utils.EncryptionUtils;
import be.business.connector.core.utils.Exceptionutils;
import be.business.connector.core.utils.MarshallerHelper;
import be.business.connector.core.utils.STSHelper;
import be.business.connector.core.utils.SessionValidator;
import be.business.connector.recipe.utils.KmehrHelper;
import be.ehealth.technicalconnector.service.kgss.domain.KeyResult;
import be.ehealth.technicalconnector.session.Session;
import be.ehealth.technicalconnector.session.SessionItem;
import be.fgov.ehealth.commons.core.v1.IdentifierType;
import be.fgov.ehealth.commons.core.v1.LocalisedString;
import be.fgov.ehealth.commons.core.v1.StatusType;
import be.fgov.ehealth.commons.protocol.v1.ResponseType;
import be.fgov.ehealth.etee.crypto.encrypt.EncryptionToken;
import be.recipe.services.prescriber.GetPrescriptionStatusParam;
import be.recipe.services.prescriber.ListOpenRidsParam;
import be.recipe.services.prescriber.ListRidsHistoryParam;
import be.recipe.services.prescriber.PutVisionParam;
import be.recipe.services.prescriber.ValidationPropertiesParam;
import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.JoinPoint.StaticPart;
import org.aspectj.runtime.reflect.Factory;
import org.perf4j.aop.Profiled;
import org.perf4j.log4j.aop.TimingAspect;

public abstract class AbstractPrescriberIntegrationModule extends AbstractIntegrationModule {
   private static final Logger LOG;
   private final Map<String, String> prescriptionCache = new HashMap();
   private final KmehrHelper kmehrHelper = new KmehrHelper();
   protected Map<String, KeyResult> keyCache = new HashMap();
   protected KgssService kgssService = KgssServiceImpl.getInstance();
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

   static {
      ajc$preClinit();
      LOG = Logger.getLogger(AbstractPrescriberIntegrationModule.class);
   }

   public AbstractPrescriberIntegrationModule() throws IntegrationModuleException {
      LOG.info("*************** Prescriber System module init correctly *******************");
   }

   public void checkStatus(ResponseType response) throws IntegrationModuleException {
      if (!"100".equals(response.getStatus().getCode()) && !"200".equals(response.getStatus().getCode())) {
         LOG.error("Error Status received : " + response.getStatus().getCode());
         throw new IntegrationModuleException(this.getLocalisedMsg(response.getStatus()));
      }
   }

   public void checkStatus(be.recipe.services.core.ResponseType response) throws IntegrationModuleException {
      if (!"100".equals(response.getStatus().getCode()) && !"200".equals(response.getStatus().getCode())) {
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
            if (!status.getMessages().isEmpty()) {
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
            if (!status.getMessages().isEmpty()) {
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

   protected String getPatientId(String rid) {
      return this.prescriptionCache.containsKey(rid) ? (String)this.prescriptionCache.get(rid) : "72081061175";
   }

   protected void setPatientId(String rid, String patientId) {
      this.prescriptionCache.put(rid, patientId);
   }

   public void setPersonalPassword(String personalPassword) throws IntegrationModuleException {
      SessionItem sessionItem = Session.getInstance().getSession();
      SessionValidator.assertValidSession(sessionItem);

      try {
         String niss = STSHelper.getNiss(sessionItem.getSAMLToken().getAssertion());
         String nihii = STSHelper.getNihii(sessionItem.getSAMLToken().getAssertion());
         EncryptionUtils encryptionUtils = EncryptionUtils.getInstance();
         encryptionUtils.unlockPersonalKey(StringUtils.isNotBlank(niss) ? niss : nihii, personalPassword);
         this.dataUnsealer = encryptionUtils.initUnsealing();
         List<EncryptionToken> tokens = this.getEtkHelper().getEtks(this.getIdentifierType(), StandaloneRequestorProvider.getRequestorIdInformation());
         encryptionUtils.verifyDecryption((EncryptionToken)tokens.get(0));
      } catch (Exception var7) {
         throw new IntegrationModuleException(var7);
      }
   }

   private KgssIdentifierType getIdentifierType() {
      String type = STSHelper.getType(Session.getInstance().getSession().getSAMLToken().getAssertion());
      return type.equals("HOSPITAL") ? KgssIdentifierType.NIHII_HOSPITAL : KgssIdentifierType.NIHII;
   }

   protected KmehrHelper getKmehrHelper() {
      return this.kmehrHelper;
   }

   protected byte[] getSealedData(ValidationPropertiesParam validationPropertiesParam) throws IntegrationModuleException {
      return this.sealForRecipe(validationPropertiesParam, ValidationPropertiesParam.class);
   }

   private <T> byte[] sealForRecipe(T data, Class<T> type) throws IntegrationModuleException {
      MarshallerHelper<Object, T> helper = new MarshallerHelper(Object.class, type);
      EncryptionToken etkRecipe = (EncryptionToken)this.getEtkHelper().getRecipe_ETK().get(0);
      return this.sealRequest(etkRecipe, helper.toXMLByteArray(data));
   }

   public KeyResult getNewKey(String patientId, String prescriptionType) throws IntegrationModuleException {
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
      tag = "0.PrescriberIntegrationModule#getNewKeyFromKgss"
   )
   protected KeyResult getNewKeyFromKgss(String prescriptionType, String prescriberId, String executorId, String patientId, byte[] myEtk) throws IntegrationModuleException {
      StaticPart var10000 = ajc$tjp_0;
      Object[] var15 = new Object[]{prescriptionType, prescriberId, executorId, patientId, myEtk};
      JoinPoint var14 = Factory.makeJP(var10000, this, this, var15);
      TimingAspect var17 = TimingAspect.aspectOf();
      Object[] var16 = new Object[]{this, prescriptionType, prescriberId, executorId, patientId, myEtk, var14};
      ProceedingJoinPoint var10001 = (new AbstractPrescriberIntegrationModule$AjcClosure1(var16)).linkClosureAndJoinPoint(69648);
      Annotation var10002 = ajc$anno$0;
      if (var10002 == null) {
         var10002 = ajc$anno$0 = AbstractPrescriberIntegrationModule.class.getDeclaredMethod("getNewKeyFromKgss", String.class, String.class, String.class, String.class, byte[].class).getAnnotation(Profiled.class);
      }

      return (KeyResult)var17.doPerfLogging(var10001, (Profiled)var10002);
   }

   @Profiled(
      logFailuresSeparately = true,
      tag = "0.PrescriberIntegrationModule#sealPrescriptionForUnknown"
   )
   public byte[] sealPrescriptionForUnknown(KeyResult key, byte[] messageToProtect) throws IntegrationModuleException {
      JoinPoint var5 = Factory.makeJP(ajc$tjp_1, this, this, key, messageToProtect);
      TimingAspect var10000 = TimingAspect.aspectOf();
      Object[] var6 = new Object[]{this, key, messageToProtect, var5};
      ProceedingJoinPoint var10001 = (new AbstractPrescriberIntegrationModule$AjcClosure3(var6)).linkClosureAndJoinPoint(69648);
      Annotation var10002 = ajc$anno$1;
      if (var10002 == null) {
         var10002 = ajc$anno$1 = AbstractPrescriberIntegrationModule.class.getDeclaredMethod("sealPrescriptionForUnknown", KeyResult.class, byte[].class).getAnnotation(Profiled.class);
      }

      return (byte[])var10000.doPerfLogging(var10001, (Profiled)var10002);
   }

   @Profiled(
      logFailuresSeparately = true,
      tag = "0.PrescriberIntegrationModule#unsealFeedback"
   )
   protected byte[] unsealFeedback(byte[] message) throws IntegrationModuleException {
      JoinPoint var3 = Factory.makeJP(ajc$tjp_2, this, this, message);
      TimingAspect var10000 = TimingAspect.aspectOf();
      Object[] var4 = new Object[]{this, message, var3};
      ProceedingJoinPoint var10001 = (new AbstractPrescriberIntegrationModule$AjcClosure5(var4)).linkClosureAndJoinPoint(69648);
      Annotation var10002 = ajc$anno$2;
      if (var10002 == null) {
         var10002 = ajc$anno$2 = AbstractPrescriberIntegrationModule.class.getDeclaredMethod("unsealFeedback", byte[].class).getAnnotation(Profiled.class);
      }

      return (byte[])var10000.doPerfLogging(var10001, (Profiled)var10002);
   }

   @Profiled(
      logFailuresSeparately = true,
      tag = "0.PrescriberIntegrationModule#sealNotification"
   )
   protected byte[] sealNotification(EncryptionToken paramEncryptionToken, byte[] paramArrayOfByte) throws IntegrationModuleException {
      JoinPoint var5 = Factory.makeJP(ajc$tjp_3, this, this, paramEncryptionToken, paramArrayOfByte);
      TimingAspect var10000 = TimingAspect.aspectOf();
      Object[] var6 = new Object[]{this, paramEncryptionToken, paramArrayOfByte, var5};
      ProceedingJoinPoint var10001 = (new AbstractPrescriberIntegrationModule$AjcClosure7(var6)).linkClosureAndJoinPoint(69648);
      Annotation var10002 = ajc$anno$3;
      if (var10002 == null) {
         var10002 = ajc$anno$3 = AbstractPrescriberIntegrationModule.class.getDeclaredMethod("sealNotification", EncryptionToken.class, byte[].class).getAnnotation(Profiled.class);
      }

      return (byte[])var10000.doPerfLogging(var10001, (Profiled)var10002);
   }

   private <T> byte[] marshall(T data, Class<T> type) {
      MarshallerHelper<Object, T> helper = new MarshallerHelper(Object.class, type);
      return helper.toXMLByteArray(data);
   }

   protected byte[] getSealedData(ListRidsHistoryParam listRidHistoryParam) throws IntegrationModuleException {
      return this.sealForRecipe(listRidHistoryParam, ListRidsHistoryParam.class);
   }

   protected byte[] getSealedData(GetPrescriptionStatusParam request) throws IntegrationModuleException {
      return this.sealForRecipe(request, GetPrescriptionStatusParam.class);
   }

   protected byte[] getSealedData(PutVisionParam putVisionParam) throws IntegrationModuleException {
      return this.sealForRecipe(putVisionParam, PutVisionParam.class);
   }

   protected byte[] getSealedData(ListOpenRidsParam listOpenRidsParam) throws IntegrationModuleException {
      return this.sealForRecipe(listOpenRidsParam, ListOpenRidsParam.class);
   }

   // $FF: synthetic method
   static final KeyResult getNewKeyFromKgss_aroundBody0(AbstractPrescriberIntegrationModule ajc$this, String prescriptionType, String prescriberId, String executorId, String patientId, byte[] myEtk, JoinPoint var6) {
      if (ajc$this.getPropertyHandler().hasProperty("test_kgss_key")) {
         return ajc$this.getKeyFromKgss((String)null, (byte[])null);
      } else {
         EncryptionToken etkKgss = (EncryptionToken)ajc$this.getEtkHelper().getKGSS_ETK().get(0);
         List credentialTypes = ajc$this.getPropertyHandler().getMatchingProperties("kgss.createPrescription.ACL." + prescriptionType);

         try {
            return ajc$this.kgssService.retrieveNewKey(etkKgss.getEncoded(), credentialTypes, prescriberId, executorId, patientId, myEtk);
         } catch (Throwable var12) {
            Exceptionutils.errorHandler(var12);
            return null;
         }
      }
   }

   // $FF: synthetic method
   static final byte[] sealPrescriptionForUnknown_aroundBody2(AbstractPrescriberIntegrationModule ajc$this, KeyResult key, byte[] messageToProtect, JoinPoint var3) {
      return Crypto.seal(messageToProtect, key.getSecretKey(), key.getKeyId());
   }

   // $FF: synthetic method
   static final byte[] unsealFeedback_aroundBody4(AbstractPrescriberIntegrationModule ajc$this, byte[] message, JoinPoint var2) {
      return ajc$this.unsealNotiffeed(message);
   }

   // $FF: synthetic method
   static final byte[] sealNotification_aroundBody6(AbstractPrescriberIntegrationModule ajc$this, EncryptionToken paramEncryptionToken, byte[] paramArrayOfByte, JoinPoint var3) {
      return Crypto.seal(paramEncryptionToken, paramArrayOfByte);
   }

   // $FF: synthetic method
   private static void ajc$preClinit() {
      Factory var0 = new Factory("AbstractPrescriberIntegrationModule.java", AbstractPrescriberIntegrationModule.class);
      ajc$tjp_0 = var0.makeSJP("method-execution", var0.makeMethodSig("4", "getNewKeyFromKgss", "be.business.connector.recipe.prescriber.AbstractPrescriberIntegrationModule", "java.lang.String:java.lang.String:java.lang.String:java.lang.String:[B", "prescriptionType:prescriberId:executorId:patientId:myEtk", "be.business.connector.core.exceptions.IntegrationModuleException", "be.ehealth.technicalconnector.service.kgss.domain.KeyResult"), 299);
      ajc$tjp_1 = var0.makeSJP("method-execution", var0.makeMethodSig("1", "sealPrescriptionForUnknown", "be.business.connector.recipe.prescriber.AbstractPrescriberIntegrationModule", "be.ehealth.technicalconnector.service.kgss.domain.KeyResult:[B", "key:messageToProtect", "be.business.connector.core.exceptions.IntegrationModuleException", "[B"), 329);
      ajc$tjp_2 = var0.makeSJP("method-execution", var0.makeMethodSig("4", "unsealFeedback", "be.business.connector.recipe.prescriber.AbstractPrescriberIntegrationModule", "[B", "message", "be.business.connector.core.exceptions.IntegrationModuleException", "[B"), 343);
      ajc$tjp_3 = var0.makeSJP("method-execution", var0.makeMethodSig("4", "sealNotification", "be.business.connector.recipe.prescriber.AbstractPrescriberIntegrationModule", "be.fgov.ehealth.etee.crypto.encrypt.EncryptionToken:[B", "paramEncryptionToken:paramArrayOfByte", "be.business.connector.core.exceptions.IntegrationModuleException", "[B"), 359);
   }
}
