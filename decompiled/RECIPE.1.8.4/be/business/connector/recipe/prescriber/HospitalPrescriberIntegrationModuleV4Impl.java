package be.business.connector.recipe.prescriber;

import be.business.connector.common.ApplicationConfig;
import be.business.connector.common.StandaloneRequestorProvider;
import be.business.connector.core.domain.KgssIdentifierType;
import be.business.connector.core.exceptions.IntegrationModuleException;
import be.business.connector.core.exceptions.IntegrationModuleValidationException;
import be.business.connector.core.utils.Exceptionutils;
import be.business.connector.core.utils.I18nHelper;
import be.business.connector.core.utils.IOUtils;
import be.business.connector.core.utils.MarshallerHelper;
import be.business.connector.core.utils.OnlineProperties;
import be.business.connector.core.utils.OnlinePropertiesHolder;
import be.business.connector.core.utils.PropertyHandler;
import be.business.connector.projects.common.utils.ValidationUtils;
import be.business.connector.recipe.prescriber.services.RecipePrescriberServiceV4Impl;
import be.business.connector.recipe.utils.RidValidator;
import be.ehealth.technicalconnector.service.kgss.domain.KeyResult;
import be.fgov.ehealth.etee.crypto.encrypt.EncryptionToken;
import be.fgov.ehealth.recipe.core.v4.CreatePrescriptionAdministrativeInformationType;
import be.fgov.ehealth.recipe.core.v4.SecuredContentType;
import be.fgov.ehealth.recipe.protocol.v4.CreatePrescriptionRequest;
import be.fgov.ehealth.recipe.protocol.v4.CreatePrescriptionResponse;
import be.fgov.ehealth.recipe.protocol.v4.GetPrescriptionRequest;
import be.fgov.ehealth.recipe.protocol.v4.GetPrescriptionResponse;
import be.fgov.ehealth.recipe.protocol.v4.GetPrescriptionStatusRequest;
import be.fgov.ehealth.recipe.protocol.v4.GetPrescriptionStatusResponse;
import be.fgov.ehealth.recipe.protocol.v4.GetValidationPropertiesRequest;
import be.fgov.ehealth.recipe.protocol.v4.GetValidationPropertiesResponse;
import be.fgov.ehealth.recipe.protocol.v4.ListFeedbacksRequest;
import be.fgov.ehealth.recipe.protocol.v4.ListFeedbacksResponse;
import be.fgov.ehealth.recipe.protocol.v4.ListOpenRidsRequest;
import be.fgov.ehealth.recipe.protocol.v4.ListOpenRidsResponse;
import be.fgov.ehealth.recipe.protocol.v4.ListRidsHistoryRequest;
import be.fgov.ehealth.recipe.protocol.v4.ListRidsHistoryResponse;
import be.fgov.ehealth.recipe.protocol.v4.PutFeedbackFlagRequest;
import be.fgov.ehealth.recipe.protocol.v4.PutFeedbackFlagResponse;
import be.fgov.ehealth.recipe.protocol.v4.PutVisionForPrescriberRequest;
import be.fgov.ehealth.recipe.protocol.v4.PutVisionForPrescriberResponse;
import be.fgov.ehealth.recipe.protocol.v4.RevokePrescriptionRequest;
import be.fgov.ehealth.recipe.protocol.v4.RevokePrescriptionResponse;
import be.fgov.ehealth.recipe.protocol.v4.SendNotificationRequest;
import be.fgov.ehealth.recipe.protocol.v4.SendNotificationResponse;
import be.recipe.services.core.PartyIdentification;
import be.recipe.services.prescriber.CreatePrescriptionParam;
import be.recipe.services.prescriber.CreatePrescriptionResult;
import be.recipe.services.prescriber.GetPrescriptionForPrescriberParam;
import be.recipe.services.prescriber.GetPrescriptionForPrescriberResult;
import be.recipe.services.prescriber.GetPrescriptionStatusParam;
import be.recipe.services.prescriber.GetPrescriptionStatusResult;
import be.recipe.services.prescriber.ListFeedbackItem;
import be.recipe.services.prescriber.ListFeedbacksParam;
import be.recipe.services.prescriber.ListFeedbacksResult;
import be.recipe.services.prescriber.ListOpenRidsParam;
import be.recipe.services.prescriber.ListOpenRidsResult;
import be.recipe.services.prescriber.ListRidsHistoryParam;
import be.recipe.services.prescriber.ListRidsHistoryResult;
import be.recipe.services.prescriber.PutVisionParam;
import be.recipe.services.prescriber.PutVisionResult;
import be.recipe.services.prescriber.RevokePrescriptionParam;
import be.recipe.services.prescriber.RevokePrescriptionResult;
import be.recipe.services.prescriber.SendNotificationParam;
import be.recipe.services.prescriber.SendNotificationResult;
import be.recipe.services.prescriber.UpdateFeedbackFlagParam;
import be.recipe.services.prescriber.UpdateFeedbackFlagResult;
import be.recipe.services.prescriber.ValidationPropertiesParam;
import be.recipe.services.prescriber.ValidationPropertiesResult;
import com.sun.xml.ws.client.ClientTransportException;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.validation.Schema;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.JoinPoint.StaticPart;
import org.aspectj.runtime.internal.Conversions;
import org.aspectj.runtime.reflect.Factory;
import org.joda.time.DateTime;
import org.perf4j.aop.Profiled;
import org.perf4j.log4j.aop.TimingAspect;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class HospitalPrescriberIntegrationModuleV4Impl extends PrescriberIntegrationModuleImpl implements PrescriberIntegrationModuleV4 {
   private static final Logger LOG;
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

   static {
      ajc$preClinit();
      LOG = Logger.getLogger(HospitalPrescriberIntegrationModuleV4Impl.class);
   }

   public HospitalPrescriberIntegrationModuleV4Impl() throws IntegrationModuleException {
      try {
         ValidationPropertiesParam request = new ValidationPropertiesParam();
         request.setClientPropertiesVersion(this.readPropertiesVersionFromDisk());
         this.getData(request);
      } catch (Exception var2) {
         LOG.error("Load online properties failed.", var2);
      }

   }

   @Profiled(
      logFailuresSeparately = true,
      tag = "0.PrescriberIntegrationModuleV4#createPrescription"
   )
   public String createPrescription(boolean feedbackRequested, String patientId, byte[] prescription, String prescriptionType) throws IntegrationModuleException {
      StaticPart var10000 = ajc$tjp_0;
      Object[] var10 = new Object[]{Conversions.booleanObject(feedbackRequested), patientId, prescription, prescriptionType};
      JoinPoint var9 = Factory.makeJP(var10000, this, this, var10);
      TimingAspect var12 = TimingAspect.aspectOf();
      Object[] var11 = new Object[]{this, Conversions.booleanObject(feedbackRequested), patientId, prescription, prescriptionType, var9};
      ProceedingJoinPoint var10001 = (new HospitalPrescriberIntegrationModuleV4Impl$AjcClosure1(var11)).linkClosureAndJoinPoint(69648);
      Annotation var10002 = ajc$anno$0;
      if (var10002 == null) {
         var10002 = ajc$anno$0 = HospitalPrescriberIntegrationModuleV4Impl.class.getDeclaredMethod("createPrescription", Boolean.TYPE, String.class, byte[].class, String.class).getAnnotation(Profiled.class);
      }

      return (String)var12.doPerfLogging(var10001, (Profiled)var10002);
   }

   @Profiled(
      logFailuresSeparately = true,
      tag = "0.PrescriberIntegrationModuleV4#createPrescription"
   )
   public String createPrescription(boolean feedbackRequested, String patientId, byte[] prescription, String prescriptionType, String visi, String expirationDate) throws IntegrationModuleException {
      StaticPart var10000 = ajc$tjp_1;
      Object[] var24 = new Object[]{Conversions.booleanObject(feedbackRequested), patientId, prescription, prescriptionType, visi, expirationDate};
      JoinPoint var23 = Factory.makeJP(var10000, this, this, var24);
      TimingAspect var26 = TimingAspect.aspectOf();
      Object[] var25 = new Object[]{this, Conversions.booleanObject(feedbackRequested), patientId, prescription, prescriptionType, visi, expirationDate, var23};
      ProceedingJoinPoint var10001 = (new HospitalPrescriberIntegrationModuleV4Impl$AjcClosure3(var25)).linkClosureAndJoinPoint(69648);
      Annotation var10002 = ajc$anno$1;
      if (var10002 == null) {
         var10002 = ajc$anno$1 = HospitalPrescriberIntegrationModuleV4Impl.class.getDeclaredMethod("createPrescription", Boolean.TYPE, String.class, byte[].class, String.class, String.class, String.class).getAnnotation(Profiled.class);
      }

      return (String)var26.doPerfLogging(var10001, (Profiled)var10002);
   }

   private String getDefaultExpirationDate() {
      Calendar defaultExpirationDate = Calendar.getInstance();
      defaultExpirationDate.add(2, 3);
      String var10000 = "yyyy-MM-dd";
      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
      return sdf.format(defaultExpirationDate.getTime());
   }

   private SecuredContentType createSecuredContentType(byte[] content) {
      SecuredContentType secured = new SecuredContentType();
      secured.setSecuredContent(content);
      return secured;
   }

   private void performValidation(byte[] prescription, String prescriptionType, String expirationDateFromRequest) throws IntegrationModuleException {
      ArrayList errors = new ArrayList();

      try {
         this.getKmehrHelper().assertValidKmehrPrescription(prescription, prescriptionType);
      } catch (IntegrationModuleValidationException var7) {
         errors.addAll(var7.getValidationErrors());
      }

      this.validateExpirationDateFromKmehr(prescription, errors, expirationDateFromRequest);
      if (CollectionUtils.isNotEmpty(errors)) {
         LOG.info("******************************************************");
         Iterator var6 = errors.iterator();

         while(var6.hasNext()) {
            String error = (String)var6.next();
            LOG.info("Errors found in the kmehr:" + error);
         }

         LOG.info("******************************************************");
         throw new IntegrationModuleValidationException(I18nHelper.getLabel("error.xml.invalid"), errors);
      }
   }

   private void validateExpirationDateFromKmehr(byte[] xmlDocument, List<String> errors, String expirationDateFromRequest) throws IntegrationModuleException {
      try {
         DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
         factory.setNamespaceAware(false);
         DocumentBuilder builder = factory.newDocumentBuilder();
         Document kmehrDocument = builder.parse(new ByteArrayInputStream(xmlDocument));
         PropertyHandler propertyHandler = PropertyHandler.getInstance();
         XPath xpath = XPathFactory.newInstance().newXPath();
         String xpathStr = propertyHandler.getProperty("expirationdate.xpath");
         NodeList expirationDateNodeList = (NodeList)xpath.evaluate(xpathStr, kmehrDocument, XPathConstants.NODESET);
         if (expirationDateNodeList.item(0) != null) {
            String expirationDateFromKmehr = expirationDateNodeList.item(0).getTextContent();
            if (!expirationDateFromKmehr.contentEquals(expirationDateFromRequest)) {
               errors.add(I18nHelper.getLabel("error.validation.expirationdate.different.message", new Object[]{expirationDateFromRequest, expirationDateFromKmehr}));
            }
         } else {
            errors.add(I18nHelper.getLabel("error.validation.expirationdate.kmehr"));
         }
      } catch (ParserConfigurationException | SAXException | IOException | XPathExpressionException var12) {
         Exceptionutils.errorHandler(var12);
      }

   }

   private String extractPrescriptionVersionFromKmehr(byte[] xmlDocument) throws IntegrationModuleException {
      try {
         DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
         factory.setNamespaceAware(false);
         DocumentBuilder builder = factory.newDocumentBuilder();
         Document kmehrDocument = builder.parse(new ByteArrayInputStream(xmlDocument));
         PropertyHandler propertyHandler = PropertyHandler.getInstance();
         XPath xpath = XPathFactory.newInstance().newXPath();
         String xpathStr = propertyHandler.getProperty("prescriptionVersion.xpath");
         NodeList prescriptionVersionNodeList = (NodeList)xpath.evaluate(xpathStr, kmehrDocument, XPathConstants.NODESET);
         if (prescriptionVersionNodeList.item(0) != null) {
            String prescriptionVersion = prescriptionVersionNodeList.item(0).getTextContent();
            return "kmehr_" + prescriptionVersion;
         } else {
            return "Unknown";
         }
      } catch (ParserConfigurationException | SAXException | IOException | XPathExpressionException var10) {
         Exceptionutils.errorHandler(var10);
         return null;
      }
   }

   @Profiled(
      logFailuresSeparately = true,
      tag = "PrescriberIntegrationModule#prepareCreatePrescription"
   )
   public void prepareCreatePrescription(String patientId, String prescriptionType) throws IntegrationModuleException {
      JoinPoint var6 = Factory.makeJP(ajc$tjp_2, this, this, patientId, prescriptionType);
      TimingAspect var10000 = TimingAspect.aspectOf();
      Object[] var7 = new Object[]{this, patientId, prescriptionType, var6};
      ProceedingJoinPoint var10001 = (new HospitalPrescriberIntegrationModuleV4Impl$AjcClosure5(var7)).linkClosureAndJoinPoint(69648);
      Annotation var10002 = ajc$anno$2;
      if (var10002 == null) {
         var10002 = ajc$anno$2 = HospitalPrescriberIntegrationModuleV4Impl.class.getDeclaredMethod("prepareCreatePrescription", String.class, String.class).getAnnotation(Profiled.class);
      }

      var10000.doPerfLogging(var10001, (Profiled)var10002);
   }

   @Profiled(
      logFailuresSeparately = true,
      tag = "0.PrescriberIntegrationModule#getPrescription"
   )
   public GetPrescriptionForPrescriberResult getPrescription(String rid) throws IntegrationModuleException {
      JoinPoint var10 = Factory.makeJP(ajc$tjp_3, this, this, rid);
      TimingAspect var10000 = TimingAspect.aspectOf();
      Object[] var11 = new Object[]{this, rid, var10};
      ProceedingJoinPoint var10001 = (new HospitalPrescriberIntegrationModuleV4Impl$AjcClosure7(var11)).linkClosureAndJoinPoint(69648);
      Annotation var10002 = ajc$anno$3;
      if (var10002 == null) {
         var10002 = ajc$anno$3 = HospitalPrescriberIntegrationModuleV4Impl.class.getDeclaredMethod("getPrescription", String.class).getAnnotation(Profiled.class);
      }

      return (GetPrescriptionForPrescriberResult)var10000.doPerfLogging(var10001, (Profiled)var10002);
   }

   @Profiled(
      logFailuresSeparately = true,
      tag = "0.PrescriberIntegrationModule#revokePrescription"
   )
   public void revokePrescription(String rid, String reason) throws IntegrationModuleException {
      JoinPoint var12 = Factory.makeJP(ajc$tjp_4, this, this, rid, reason);
      TimingAspect var10000 = TimingAspect.aspectOf();
      Object[] var13 = new Object[]{this, rid, reason, var12};
      ProceedingJoinPoint var10001 = (new HospitalPrescriberIntegrationModuleV4Impl$AjcClosure9(var13)).linkClosureAndJoinPoint(69648);
      Annotation var10002 = ajc$anno$4;
      if (var10002 == null) {
         var10002 = ajc$anno$4 = HospitalPrescriberIntegrationModuleV4Impl.class.getDeclaredMethod("revokePrescription", String.class, String.class).getAnnotation(Profiled.class);
      }

      var10000.doPerfLogging(var10001, (Profiled)var10002);
   }

   @Profiled(
      logFailuresSeparately = true,
      tag = "PrescriberIntegrationModule#sendNotification"
   )
   public void sendNotification(byte[] notificationText, String patientId, String executorId) throws IntegrationModuleException {
      StaticPart var10000 = ajc$tjp_5;
      Object[] var20 = new Object[]{notificationText, patientId, executorId};
      JoinPoint var19 = Factory.makeJP(var10000, this, this, var20);
      TimingAspect var22 = TimingAspect.aspectOf();
      Object[] var21 = new Object[]{this, notificationText, patientId, executorId, var19};
      ProceedingJoinPoint var10001 = (new HospitalPrescriberIntegrationModuleV4Impl$AjcClosure11(var21)).linkClosureAndJoinPoint(69648);
      Annotation var10002 = ajc$anno$5;
      if (var10002 == null) {
         var10002 = ajc$anno$5 = HospitalPrescriberIntegrationModuleV4Impl.class.getDeclaredMethod("sendNotification", byte[].class, String.class, String.class).getAnnotation(Profiled.class);
      }

      var22.doPerfLogging(var10001, (Profiled)var10002);
   }

   @Profiled(
      logFailuresSeparately = true,
      tag = "0.PrescriberIntegrationModule#updateFeedbackFlag"
   )
   public void updateFeedbackFlag(String rid, boolean feedbackAllowed) throws IntegrationModuleException {
      JoinPoint var13 = Factory.makeJP(ajc$tjp_6, this, this, rid, Conversions.booleanObject(feedbackAllowed));
      TimingAspect var10000 = TimingAspect.aspectOf();
      Object[] var14 = new Object[]{this, rid, Conversions.booleanObject(feedbackAllowed), var13};
      ProceedingJoinPoint var10001 = (new HospitalPrescriberIntegrationModuleV4Impl$AjcClosure13(var14)).linkClosureAndJoinPoint(69648);
      Annotation var10002 = ajc$anno$6;
      if (var10002 == null) {
         var10002 = ajc$anno$6 = HospitalPrescriberIntegrationModuleV4Impl.class.getDeclaredMethod("updateFeedbackFlag", String.class, Boolean.TYPE).getAnnotation(Profiled.class);
      }

      var10000.doPerfLogging(var10001, (Profiled)var10002);
   }

   @Profiled(
      logFailuresSeparately = true,
      tag = "0.PrescriberIntegrationModule#listFeedback"
   )
   public List<ListFeedbackItem> listFeedback(boolean readFlag) throws IntegrationModuleException {
      JoinPoint var14 = Factory.makeJP(ajc$tjp_7, this, this, Conversions.booleanObject(readFlag));
      TimingAspect var10000 = TimingAspect.aspectOf();
      Object[] var15 = new Object[]{this, Conversions.booleanObject(readFlag), var14};
      ProceedingJoinPoint var10001 = (new HospitalPrescriberIntegrationModuleV4Impl$AjcClosure15(var15)).linkClosureAndJoinPoint(69648);
      Annotation var10002 = ajc$anno$7;
      if (var10002 == null) {
         var10002 = ajc$anno$7 = HospitalPrescriberIntegrationModuleV4Impl.class.getDeclaredMethod("listFeedback", Boolean.TYPE).getAnnotation(Profiled.class);
      }

      return (List)var10000.doPerfLogging(var10001, (Profiled)var10002);
   }

   @Profiled(
      logFailuresSeparately = true,
      tag = "0.PrescriberIntegrationModule#getData(ValidationPropertiesParam)"
   )
   public ValidationPropertiesResult getData(ValidationPropertiesParam param) throws IntegrationModuleException {
      JoinPoint var16 = Factory.makeJP(ajc$tjp_8, this, this, param);
      TimingAspect var10000 = TimingAspect.aspectOf();
      Object[] var17 = new Object[]{this, param, var16};
      ProceedingJoinPoint var10001 = (new HospitalPrescriberIntegrationModuleV4Impl$AjcClosure17(var17)).linkClosureAndJoinPoint(69648);
      Annotation var10002 = ajc$anno$8;
      if (var10002 == null) {
         var10002 = ajc$anno$8 = HospitalPrescriberIntegrationModuleV4Impl.class.getDeclaredMethod("getData", ValidationPropertiesParam.class).getAnnotation(Profiled.class);
      }

      return (ValidationPropertiesResult)var10000.doPerfLogging(var10001, (Profiled)var10002);
   }

   private void deleteOnlineProperties() {
      String directory = this.getPropertyHandler().getProperty("online.properties.file");

      try {
         FileUtils.deleteDirectory(new File(directory));
      } catch (IOException var3) {
         LOG.error("Failed to delete online properties from local cache", var3);
      }

   }

   protected GetValidationPropertiesRequest getValidationProperties(ValidationPropertiesParam param) throws IntegrationModuleException {
      param.setSymmKey(this.getSymmKey().getEncoded());
      GetValidationPropertiesRequest request = new GetValidationPropertiesRequest();
      request.setSecuredGetValidationPropertiesRequest(this.createSecuredContentType(this.getSealedData(param)));
      request.setProgramId(PropertyHandler.getInstance().getProperty("programIdentification"));
      request.setIssueInstant(new DateTime());
      request.setId(this.getId());
      return request;
   }

   protected byte[] getSealedData(ValidationPropertiesParam data) throws IntegrationModuleException {
      data.setSymmKey(this.getSymmKey().getEncoded());
      return this.sealForRecipe(data, ValidationPropertiesParam.class);
   }

   protected <T> byte[] sealForRecipe(T data, Class<T> type) throws IntegrationModuleException {
      MarshallerHelper<Object, T> helper = new MarshallerHelper(Object.class, type);
      EncryptionToken etkRecipe = (EncryptionToken)this.getEtkHelper().getRecipe_ETK().get(0);
      return this.sealRequest(etkRecipe, helper.toXMLByteArray(data));
   }

   private void storePropertiesOnDisk(Map<String, String> map) {
      if (CollectionUtils.isNotEmpty(map.values())) {
         try {
            Properties temp = new Properties();
            Iterator var4 = map.keySet().iterator();

            while(var4.hasNext()) {
               String key = (String)var4.next();
               temp.put(key, map.get(key));
            }

            temp.store(new FileOutputStream(this.getPropertyHandler().getProperty("online.properties.file") + "/online.properties.txt"), (String)null);
         } catch (IOException var5) {
            LOG.error("Failed to store the online properties to disk", var5);
         }
      }

   }

   private void storeXsdsOnDisk(Map<String, byte[]> xsdValidationFile) {
      if (CollectionUtils.isNotEmpty(xsdValidationFile.values())) {
         Iterator var3 = xsdValidationFile.keySet().iterator();

         while(var3.hasNext()) {
            String key = (String)var3.next();

            try {
               unzip((byte[])xsdValidationFile.get(key), this.getPropertyHandler().getProperty("online.xsd.path") + File.separator + key.split(":")[0]);
            } catch (Exception var5) {
               LOG.error("Failed to store the online xsds to disk", var5);
            }
         }
      }

   }

   private static void unzip(byte[] compressedData, String destDir) {
      File dir = new File(destDir);
      if (!dir.exists()) {
         dir.mkdirs();
      }

      byte[] buffer = new byte[1024];

      try {
         ByteArrayInputStream fis = new ByteArrayInputStream(compressedData);
         ZipInputStream zis = new ZipInputStream(fis);

         for(ZipEntry ze = zis.getNextEntry(); ze != null; ze = zis.getNextEntry()) {
            String fileName = ze.getName();
            File newFile = new File(destDir + File.separator + fileName);
            LOG.debug("Unzipping to " + newFile.getAbsolutePath());
            (new File(newFile.getParent())).mkdirs();
            FileOutputStream fos = new FileOutputStream(newFile);

            int len;
            while((len = zis.read(buffer)) > 0) {
               fos.write(buffer, 0, len);
            }

            fos.close();
            zis.closeEntry();
         }

         zis.closeEntry();
         zis.close();
         fis.close();
      } catch (IOException var11) {
         LOG.error("Error while unzipping the xsd files from the server: ", var11);
      }

   }

   private void readFromDisk() throws Exception {
      try {
         Properties prop = new Properties();
         File file = new File(this.getPropertyHandler().getProperty("online.properties.file") + "/online.properties.txt");
         if (file != null && file.exists()) {
            prop.load(new FileInputStream(file));
            OnlineProperties onlineProperties = OnlinePropertiesHolder.getInstance();
            Map<String, String> targetProperties = new HashMap();
            Enumeration iterator = prop.keys();

            while(iterator.hasMoreElements()) {
               String key = (String)iterator.nextElement();
               targetProperties.put(key, prop.getProperty(key));
            }

            onlineProperties.setProperties(targetProperties);
            OnlinePropertiesHolder.reloadProperties();
         }
      } catch (Exception var7) {
         LOG.info("Failed to read online properties from disk, using default properties as default.", var7);
      }

   }

   protected void storePropertiesVersionToDisk(String serverPropertiesVersion) throws IntegrationModuleException {
      try {
         String path = this.getPropertyHandler().getProperty("online.properties.version.path");
         File file = new File(path + "/online.properties.version.txt");
         FileUtils.writeStringToFile(file, serverPropertiesVersion);
      } catch (IOException var4) {
         throw new IntegrationModuleException(I18nHelper.getLabel("error.connection.executor"), var4);
      }
   }

   private String readPropertiesVersionFromDisk() throws IntegrationModuleException {
      // $FF: Couldn't be decompiled
   }

   @Profiled(
      logFailuresSeparately = true,
      tag = "0.PrescriberIntegrationModuleV4#getData(GetPrescriptionStatusParam)"
   )
   public GetPrescriptionStatusResult getData(GetPrescriptionStatusParam param) throws IntegrationModuleException {
      JoinPoint var6 = Factory.makeJP(ajc$tjp_9, this, this, param);
      TimingAspect var10000 = TimingAspect.aspectOf();
      Object[] var7 = new Object[]{this, param, var6};
      ProceedingJoinPoint var10001 = (new HospitalPrescriberIntegrationModuleV4Impl$AjcClosure19(var7)).linkClosureAndJoinPoint(69648);
      Annotation var10002 = ajc$anno$9;
      if (var10002 == null) {
         var10002 = ajc$anno$9 = HospitalPrescriberIntegrationModuleV4Impl.class.getDeclaredMethod("getData", GetPrescriptionStatusParam.class).getAnnotation(Profiled.class);
      }

      return (GetPrescriptionStatusResult)var10000.doPerfLogging(var10001, (Profiled)var10002);
   }

   protected GetPrescriptionStatusRequest getGetPrescriptionStatus(GetPrescriptionStatusParam param) throws IntegrationModuleException {
      param.setSymmKey(this.getSymmKey().getEncoded());
      GetPrescriptionStatusRequest request = new GetPrescriptionStatusRequest();
      request.setSecuredGetPrescriptionStatusRequest(this.createSecuredContentType(this.getSealedData(param)));
      request.setProgramId(PropertyHandler.getInstance().getProperty("programIdentification"));
      request.setIssueInstant(new DateTime());
      request.setId(this.getId());
      return request;
   }

   private GetPrescriptionStatusResult unsealGetPrescriptionStatusResponse(GetPrescriptionStatusResponse response) {
      MarshallerHelper<GetPrescriptionStatusResult, Object> marshaller = new MarshallerHelper(GetPrescriptionStatusResult.class, Object.class);
      return (GetPrescriptionStatusResult)marshaller.unsealWithSymmKey(response.getSecuredGetPrescriptionStatusResponse().getSecuredContent(), this.getSymmKey());
   }

   private ValidationPropertiesResult unsealValidationPropertiesResponse(GetValidationPropertiesResponse response) {
      MarshallerHelper<ValidationPropertiesResult, Schema> marshaller = new MarshallerHelper(ValidationPropertiesResult.class, Schema.class);
      return (ValidationPropertiesResult)marshaller.unsealWithSymmKey(response.getSecuredGetValidationPropertiesResponse().getSecuredContent(), this.getSymmKey());
   }

   @Profiled(
      logFailuresSeparately = true,
      tag = "0.PrescriberIntegrationModuleV4#getData(ListPrescriptionHistoryParam)"
   )
   public ListRidsHistoryResult getData(ListRidsHistoryParam param) throws IntegrationModuleException {
      JoinPoint var6 = Factory.makeJP(ajc$tjp_10, this, this, param);
      TimingAspect var10000 = TimingAspect.aspectOf();
      Object[] var7 = new Object[]{this, param, var6};
      ProceedingJoinPoint var10001 = (new HospitalPrescriberIntegrationModuleV4Impl$AjcClosure21(var7)).linkClosureAndJoinPoint(69648);
      Annotation var10002 = ajc$anno$10;
      if (var10002 == null) {
         var10002 = ajc$anno$10 = HospitalPrescriberIntegrationModuleV4Impl.class.getDeclaredMethod("getData", ListRidsHistoryParam.class).getAnnotation(Profiled.class);
      }

      return (ListRidsHistoryResult)var10000.doPerfLogging(var10001, (Profiled)var10002);
   }

   private ListRidsHistoryRequest getListPrescriptionHistoryRequest(ListRidsHistoryParam listRidHistoryParam) throws IntegrationModuleException {
      listRidHistoryParam.setSymmKey(this.getSymmKey().getEncoded());
      ListRidsHistoryRequest listRidsHistory = new ListRidsHistoryRequest();
      listRidsHistory.setSecuredListRidsHistoryRequest(this.createSecuredContentType(this.getSealedData(listRidHistoryParam)));
      listRidsHistory.setProgramId(PropertyHandler.getInstance().getProperty("programIdentification"));
      listRidsHistory.setId(this.getId());
      listRidsHistory.setIssueInstant(new DateTime());
      return listRidsHistory;
   }

   private ListRidsHistoryResult unsealListPrescriptionHistoryResponse(ListRidsHistoryResponse response) {
      MarshallerHelper<ListRidsHistoryResult, Object> marshaller = new MarshallerHelper(ListRidsHistoryResult.class, Object.class);
      return (ListRidsHistoryResult)marshaller.unsealWithSymmKey(response.getSecuredListRidsHistoryResponse().getSecuredContent(), this.getSymmKey());
   }

   @Profiled(
      logFailuresSeparately = true,
      tag = "0.PrescriberIntegrationModuleV4#putData(PutVisionParam)"
   )
   public PutVisionResult putData(PutVisionParam param) throws IntegrationModuleException {
      JoinPoint var6 = Factory.makeJP(ajc$tjp_11, this, this, param);
      TimingAspect var10000 = TimingAspect.aspectOf();
      Object[] var7 = new Object[]{this, param, var6};
      ProceedingJoinPoint var10001 = (new HospitalPrescriberIntegrationModuleV4Impl$AjcClosure23(var7)).linkClosureAndJoinPoint(69648);
      Annotation var10002 = ajc$anno$11;
      if (var10002 == null) {
         var10002 = ajc$anno$11 = HospitalPrescriberIntegrationModuleV4Impl.class.getDeclaredMethod("putData", PutVisionParam.class).getAnnotation(Profiled.class);
      }

      return (PutVisionResult)var10000.doPerfLogging(var10001, (Profiled)var10002);
   }

   protected PutVisionForPrescriberRequest putVisionRequest(PutVisionParam param) throws IntegrationModuleException {
      param.setSymmKey(this.getSymmKey().getEncoded());
      PutVisionForPrescriberRequest request = new PutVisionForPrescriberRequest();
      request.setSecuredPutVisionForPrescriberRequest(this.createSecuredContentType(this.getSealedData(param)));
      request.setProgramId(PropertyHandler.getInstance().getProperty("programIdentification"));
      request.setIssueInstant(new DateTime());
      request.setId(this.getId());
      return request;
   }

   private PutVisionResult unsealPutVisionResponse(PutVisionForPrescriberResponse response) {
      MarshallerHelper<PutVisionResult, Object> marshaller = new MarshallerHelper(PutVisionResult.class, Object.class);
      PutVisionResult result = (PutVisionResult)marshaller.unsealWithSymmKey(response.getSecuredPutVisionForPrescriberResponse().getSecuredContent(), this.getSymmKey());
      return result;
   }

   @Profiled(
      logFailuresSeparately = true,
      tag = "0.PrescriberIntegrationModuleV4#getData(ListOpenRidsParam)"
   )
   public ListOpenRidsResult getData(ListOpenRidsParam param) throws IntegrationModuleException {
      JoinPoint var6 = Factory.makeJP(ajc$tjp_12, this, this, param);
      TimingAspect var10000 = TimingAspect.aspectOf();
      Object[] var7 = new Object[]{this, param, var6};
      ProceedingJoinPoint var10001 = (new HospitalPrescriberIntegrationModuleV4Impl$AjcClosure25(var7)).linkClosureAndJoinPoint(69648);
      Annotation var10002 = ajc$anno$12;
      if (var10002 == null) {
         var10002 = ajc$anno$12 = HospitalPrescriberIntegrationModuleV4Impl.class.getDeclaredMethod("getData", ListOpenRidsParam.class).getAnnotation(Profiled.class);
      }

      return (ListOpenRidsResult)var10000.doPerfLogging(var10001, (Profiled)var10002);
   }

   protected ListOpenRidsRequest getListOpenRids(ListOpenRidsParam param) throws IntegrationModuleException {
      param.setSymmKey(this.getSymmKey().getEncoded());
      ListOpenRidsRequest request = new ListOpenRidsRequest();
      request.setSecuredListOpenRidsRequest(this.createSecuredContentType(this.getSealedData(param)));
      request.setProgramId(PropertyHandler.getInstance().getProperty("programIdentification"));
      request.setIssueInstant(new DateTime());
      request.setId(this.getId());
      return request;
   }

   private ListOpenRidsResult unsealListOpenRidsResponse(ListOpenRidsResponse response) {
      MarshallerHelper<ListOpenRidsResult, Object> marshaller = new MarshallerHelper(ListOpenRidsResult.class, Object.class);
      return (ListOpenRidsResult)marshaller.unsealWithSymmKey(response.getSecuredListOpenRidsResponse().getSecuredContent(), this.getSymmKey());
   }

   // $FF: synthetic method
   static final String createPrescription_aroundBody0(HospitalPrescriberIntegrationModuleV4Impl ajc$this, boolean feedbackRequested, String patientId, byte[] prescription, String prescriptionType, JoinPoint var5) {
      return ajc$this.createPrescription(feedbackRequested, patientId, prescription, prescriptionType, (String)null, ajc$this.getDefaultExpirationDate());
   }

   // $FF: synthetic method
   static final String createPrescription_aroundBody2(HospitalPrescriberIntegrationModuleV4Impl ajc$this, boolean feedbackRequested, String patientId, byte[] prescription, String prescriptionType, String visi, String expirationDate, JoinPoint var7) {
      ApplicationConfig.getInstance().assertValidSession();
      ValidationUtils.validatePatientIdNotBlank(patientId);
      ValidationUtils.validatePatientId(patientId);
      ValidationUtils.validateVisi(visi, false);

      try {
         PropertyHandler propertyHandler = PropertyHandler.getInstance();
         ValidationUtils.validateExpirationDate(expirationDate);
         ajc$this.performValidation(prescription, prescriptionType, expirationDate);
         MarshallerHelper helper = new MarshallerHelper(CreatePrescriptionResult.class, CreatePrescriptionParam.class);
         List etkRecipes = ajc$this.getEtkHelper().getRecipe_ETK();
         byte[] message = IOUtils.compress(prescription);
         KeyResult key = ajc$this.getNewKey(patientId, prescriptionType);
         message = ajc$this.sealPrescriptionForUnknown(key, message);
         CreatePrescriptionParam params = new CreatePrescriptionParam();
         params.setPrescription(message);
         params.setPrescriptionType(prescriptionType);
         params.setFeedbackRequested(feedbackRequested);
         params.setKeyId(key.getKeyId());
         params.setSymmKey(ajc$this.getSymmKey().getEncoded());
         params.setPatientId(patientId);
         params.setExpirationDate(expirationDate);
         params.setVision(visi);
         CreatePrescriptionRequest request = new CreatePrescriptionRequest();
         request.setSecuredCreatePrescriptionRequest(ajc$this.createSecuredContentType(ajc$this.sealRequest((EncryptionToken)etkRecipes.get(0), helper.toXMLByteArray(params))));
         request.setProgramId(propertyHandler.getProperty("programIdentification"));
         request.setId(ajc$this.getId());
         request.setIssueInstant(new DateTime());
         CreatePrescriptionAdministrativeInformationType adminValue = new CreatePrescriptionAdministrativeInformationType();
         adminValue.setKeyIdentifier(key.getKeyId().getBytes());
         adminValue.setPrescriptionVersion(PropertyHandler.getInstance().getProperty("prescription.version"));
         adminValue.setReferenceSourceVersion(ajc$this.extractReferenceSourceVersionFromKmehr(prescription));
         adminValue.setPrescriptionType(prescriptionType);
         request.setAdministrativeInformation(adminValue);
         CreatePrescriptionResponse response = RecipePrescriberServiceV4Impl.getInstance().createPrescription(request);
         CreatePrescriptionResult result = (CreatePrescriptionResult)helper.unsealWithSymmKey(response.getSecuredCreatePrescriptionResponse().getSecuredContent(), ajc$this.getSymmKey());
         ajc$this.checkStatus(result);
         ajc$this.setPatientId(result.getRid(), patientId);
         return result.getRid();
      } catch (Throwable var27) {
         Exceptionutils.errorHandler(var27);
         return null;
      }
   }

   // $FF: synthetic method
   static final void prepareCreatePrescription_aroundBody4(HospitalPrescriberIntegrationModuleV4Impl ajc$this, String patientId, String prescriptionType, JoinPoint var3) {
      ApplicationConfig.getInstance().assertValidSession();
      String cacheId = "(" + patientId + "#" + prescriptionType + ")";
      ajc$this.keyCache.put(cacheId, ajc$this.getNewKeyFromKgss(prescriptionType, StandaloneRequestorProvider.getRequestorIdInformation(), (String)null, patientId, ((EncryptionToken)ajc$this.getEtkHelper().getSystemETK().get(0)).getEncoded()));
   }

   // $FF: synthetic method
   static final GetPrescriptionForPrescriberResult getPrescription_aroundBody6(HospitalPrescriberIntegrationModuleV4Impl ajc$this, String rid, JoinPoint var2) {
      RidValidator.validateRid(rid);
      ApplicationConfig.getInstance().assertValidSession();

      try {
         MarshallerHelper helper = new MarshallerHelper(GetPrescriptionForPrescriberResult.class, GetPrescriptionForPrescriberParam.class);
         GetPrescriptionForPrescriberParam param = new GetPrescriptionForPrescriberParam();
         param.setRid(rid);
         param.setSymmKey(ajc$this.getSymmKey().getEncoded());
         GetPrescriptionRequest request = new GetPrescriptionRequest();
         request.setSecuredGetPrescriptionRequest(ajc$this.createSecuredContentType(ajc$this.sealForRecipe(param, GetPrescriptionForPrescriberParam.class)));
         request.setProgramId(PropertyHandler.getInstance().getProperty("programIdentification"));
         request.setIssueInstant(new DateTime());
         request.setId(ajc$this.getId());
         GetPrescriptionResponse response = null;

         try {
            response = RecipePrescriberServiceV4Impl.getInstance().getPrescriptionForPrescriber(request);
         } catch (ClientTransportException var16) {
            throw new IntegrationModuleException(I18nHelper.getLabel("error.connection.prescriber"), var16);
         }

         GetPrescriptionForPrescriberResult result = (GetPrescriptionForPrescriberResult)helper.unsealWithSymmKey(response.getSecuredGetPrescriptionResponse().getSecuredContent(), ajc$this.getSymmKey());
         ajc$this.checkStatus(result);
         KeyResult key = ajc$this.getKeyFromKgss(result.getEncryptionKeyId(), ((EncryptionToken)ajc$this.getEtkHelper().getEtks(KgssIdentifierType.NIHII_HOSPITAL, StandaloneRequestorProvider.getRequestorIdInformation()).get(0)).getEncoded());
         byte[] unsealedPrescription = IOUtils.decompress(ajc$this.unsealPrescriptionForUnknown(key, result.getPrescription()));
         result.setPrescription(unsealedPrescription);
         ajc$this.setPatientId(result.getRid(), result.getPatientId());
         return result;
      } catch (Throwable var17) {
         Exceptionutils.errorHandler(var17);
         return null;
      }
   }

   // $FF: synthetic method
   static final void revokePrescription_aroundBody8(HospitalPrescriberIntegrationModuleV4Impl ajc$this, String rid, String reason, JoinPoint var3) {
      RidValidator.validateRid(rid);
      ApplicationConfig.getInstance().assertValidSession();

      try {
         MarshallerHelper helper = new MarshallerHelper(Object.class, RevokePrescriptionParam.class);
         List etkRecipes = ajc$this.getEtkHelper().getRecipe_ETK();
         RevokePrescriptionParam params = new RevokePrescriptionParam();
         params.setRid(rid);
         params.setReason(reason);
         params.setSymmKey(ajc$this.getSymmKey().getEncoded());
         RevokePrescriptionRequest request = new RevokePrescriptionRequest();
         request.setSecuredRevokePrescriptionRequest(ajc$this.createSecuredContentType(ajc$this.sealRequest((EncryptionToken)etkRecipes.get(0), helper.toXMLByteArray(params))));
         request.setProgramId(PropertyHandler.getInstance().getProperty("programIdentification"));
         request.setIssueInstant(new DateTime());
         request.setId(ajc$this.getId());

         try {
            RevokePrescriptionResponse response = RecipePrescriberServiceV4Impl.getInstance().revokePrescription(request);
            MarshallerHelper marshaller = new MarshallerHelper(RevokePrescriptionResult.class, Object.class);
            RevokePrescriptionResult result = (RevokePrescriptionResult)marshaller.unsealWithSymmKey(response.getSecuredRevokePrescriptionResponse().getSecuredContent(), ajc$this.getSymmKey());
            ajc$this.checkStatus(result);
         } catch (ClientTransportException var17) {
            throw new IntegrationModuleException(I18nHelper.getLabel("error.connection.prescriber"), var17);
         }
      } catch (Throwable var18) {
         Exceptionutils.errorHandler(var18);
      }

   }

   // $FF: synthetic method
   static final void sendNotification_aroundBody10(HospitalPrescriberIntegrationModuleV4Impl ajc$this, byte[] notificationText, String patientId, String executorId, JoinPoint var4) {
      ApplicationConfig.getInstance().assertValidSession();

      try {
         ajc$this.getKmehrHelper().assertValidNotification(notificationText);
         ValidationUtils.validatePatientId(patientId);
         MarshallerHelper helper = new MarshallerHelper(Object.class, SendNotificationParam.class);
         List etkRecipes = ajc$this.getEtkHelper().getRecipe_ETK();
         List etkRecipients = ajc$this.getEtkHelper().getEtks(KgssIdentifierType.NIHII_HOSPITAL, executorId);
         byte[] notificationZip = IOUtils.compress(notificationText);

         for(int i = 0; i < etkRecipients.size(); ++i) {
            EncryptionToken etkRecipient = (EncryptionToken)etkRecipients.get(0);
            byte[] notificationSealed = ajc$this.sealNotification(etkRecipient, notificationZip);
            SendNotificationParam param = new SendNotificationParam();
            param.setContent(notificationSealed);
            param.setExecutorId(executorId);
            param.setPatientId(patientId);
            param.setSymmKey(ajc$this.getSymmKey().getEncoded());
            SendNotificationRequest request = new SendNotificationRequest();
            request.setSecuredSendNotificationRequest(ajc$this.createSecuredContentType(ajc$this.sealRequest((EncryptionToken)etkRecipes.get(0), helper.toXMLByteArray(param))));
            request.setProgramId(PropertyHandler.getInstance().getProperty("programIdentification"));
            request.setIssueInstant(new DateTime());
            request.setId(ajc$this.getId());

            try {
               SendNotificationResponse response = RecipePrescriberServiceV4Impl.getInstance().sendNotification(request);
               MarshallerHelper helper1 = new MarshallerHelper(SendNotificationResult.class, SendNotificationResult.class);
               SendNotificationResult result = (SendNotificationResult)helper1.unsealWithSymmKey(response.getSecuredSendNotificationResponse().getSecuredContent(), ajc$this.getSymmKey());
               ajc$this.checkStatus(result);
            } catch (ClientTransportException var28) {
               throw new IntegrationModuleException(I18nHelper.getLabel("error.connection.prescriber"), var28);
            }
         }
      } catch (Throwable var29) {
         Exceptionutils.errorHandler(var29);
      }

   }

   // $FF: synthetic method
   static final void updateFeedbackFlag_aroundBody12(HospitalPrescriberIntegrationModuleV4Impl ajc$this, String rid, boolean feedbackAllowed, JoinPoint var3) {
      RidValidator.validateRid(rid);
      ApplicationConfig.getInstance().assertValidSession();

      try {
         MarshallerHelper helper = new MarshallerHelper(Object.class, UpdateFeedbackFlagParam.class);
         List etkRecipes = ajc$this.getEtkHelper().getRecipe_ETK();
         UpdateFeedbackFlagParam param = new UpdateFeedbackFlagParam();
         param.setAllowFeedback(feedbackAllowed);
         param.setRid(rid);
         param.setPrescriberId(StandaloneRequestorProvider.getRequestorIdInformation());
         param.setSymmKey(ajc$this.getSymmKey().getEncoded());
         PutFeedbackFlagRequest request = new PutFeedbackFlagRequest();
         request.setSecuredPutFeedbackFlagRequest(ajc$this.createSecuredContentType(ajc$this.sealRequest((EncryptionToken)etkRecipes.get(0), helper.toXMLByteArray(param))));
         request.setProgramId(PropertyHandler.getInstance().getProperty("programIdentification"));
         request.setIssueInstant(new DateTime());
         request.setId(ajc$this.getId());
         PartyIdentification value = new PartyIdentification();
         value.setPatientId(ajc$this.getPatientId(rid));

         try {
            PutFeedbackFlagResponse response = RecipePrescriberServiceV4Impl.getInstance().putFeedbackFlag(request);
            MarshallerHelper helper2 = new MarshallerHelper(UpdateFeedbackFlagResult.class, UpdateFeedbackFlagResult.class);
            UpdateFeedbackFlagResult result = (UpdateFeedbackFlagResult)helper2.unsealWithSymmKey(response.getSecuredPutFeedbackFlagResponse().getSecuredContent(), ajc$this.getSymmKey());
            ajc$this.checkStatus(result);
         } catch (ClientTransportException var19) {
            throw new IntegrationModuleException(I18nHelper.getLabel("error.connection.prescriber"), var19);
         }
      } catch (Throwable var20) {
         Exceptionutils.errorHandler(var20);
      }

   }

   // $FF: synthetic method
   static final List listFeedback_aroundBody14(HospitalPrescriberIntegrationModuleV4Impl ajc$this, boolean readFlag, JoinPoint var2) {
      ApplicationConfig.getInstance().assertValidSession();

      try {
         MarshallerHelper helper = new MarshallerHelper(ListFeedbacksResult.class, ListFeedbacksParam.class);
         List etkRecipes = ajc$this.getEtkHelper().getRecipe_ETK();
         ListFeedbacksParam param = new ListFeedbacksParam();
         param.setReadFlag(readFlag);
         param.setSymmKey(ajc$this.getSymmKey().getEncoded());
         ListFeedbacksRequest request = new ListFeedbacksRequest();
         request.setSecuredListFeedbacksRequest(ajc$this.createSecuredContentType(ajc$this.sealRequest((EncryptionToken)etkRecipes.get(0), helper.toXMLByteArray(param))));
         request.setProgramId(PropertyHandler.getInstance().getProperty("programIdentification"));
         request.setIssueInstant(new DateTime());
         request.setId(ajc$this.getId());
         ListFeedbacksResponse response = null;

         try {
            response = RecipePrescriberServiceV4Impl.getInstance().listFeedbacks(request);
         } catch (ClientTransportException var25) {
            throw new IntegrationModuleException(I18nHelper.getLabel("error.connection.prescriber"), var25);
         }

         ListFeedbacksResult result = (ListFeedbacksResult)helper.unsealWithSymmKey(response.getSecuredListFeedbacksResponse().getSecuredContent(), ajc$this.getSymmKey());
         ajc$this.checkStatus(result);
         List feedbacks = result.getFeedbacks();

         for(int i = 0; i < feedbacks.size(); ++i) {
            be.business.connector.recipe.prescriber.domain.ListFeedbackItem item = new be.business.connector.recipe.prescriber.domain.ListFeedbackItem((ListFeedbackItem)feedbacks.get(i));
            byte[] content = item.getContent();

            try {
               content = ajc$this.unsealFeedback(content);
               content = content == null ? content : IOUtils.decompress(content);
               item.setContent(content);
            } catch (Throwable var24) {
               item.setLinkedException(var24);
            }

            feedbacks.set(i, item);
         }

         return feedbacks;
      } catch (Throwable var26) {
         Exceptionutils.errorHandler(var26);
         return null;
      }
   }

   // $FF: synthetic method
   static final ValidationPropertiesResult getData_aroundBody16(HospitalPrescriberIntegrationModuleV4Impl ajc$this, ValidationPropertiesParam param, JoinPoint var2) {
      ApplicationConfig.getInstance().assertValidSession();
      if (!OnlineProperties.isLoaded()) {
         ValidationPropertiesResult var25;
         try {
            try {
               GetValidationPropertiesRequest validationProperties = ajc$this.getValidationProperties(param);
               GetValidationPropertiesResponse response = RecipePrescriberServiceV4Impl.getInstance().getValidationProperties(validationProperties);
               ValidationPropertiesResult result = ajc$this.unsealValidationPropertiesResponse(response);
               ajc$this.checkStatus(result);
               OnlineProperties onlineProperties = OnlinePropertiesHolder.getInstance();
               ValidationPropertiesResult.Properties properties = result.getProperties();
               String clientVersion = ajc$this.readPropertiesVersionFromDisk();
               if (result.getServerPropertiesVersion() == null || result.getServerPropertiesVersion() != null && result.getServerPropertiesVersion().equals("")) {
                  ajc$this.deleteOnlineProperties();
               } else if (result.getServerPropertiesVersion() != null && clientVersion != null && !result.getServerPropertiesVersion().equals(clientVersion)) {
                  ajc$this.deleteOnlineProperties();
               }

               if (StringUtils.isNotBlank(result.getServerPropertiesVersion()) && !result.getServerPropertiesVersion().equals(clientVersion)) {
                  Map targetProperties = new HashMap();
                  if (properties != null && CollectionUtils.isNotEmpty(properties.getEntries())) {
                     Iterator var17 = properties.getEntries().iterator();

                     while(var17.hasNext()) {
                        ValidationPropertiesResult.Properties.Entry obj = (ValidationPropertiesResult.Properties.Entry)var17.next();
                        targetProperties.put(obj.getKey(), obj.getValue());
                     }

                     onlineProperties.setProperties(targetProperties);
                  }

                  if (result.getXsdValidationFiles() != null && CollectionUtils.isNotEmpty(result.getXsdValidationFiles().getEntries())) {
                     ValidationPropertiesResult.XsdValidationFiles xsdFiles = result.getXsdValidationFiles();
                     Map targetMap = new HashMap();
                     Iterator var21 = xsdFiles.getEntries().iterator();

                     ValidationPropertiesResult.XsdValidationFiles.Entry item;
                     while(var21.hasNext()) {
                        item = (ValidationPropertiesResult.XsdValidationFiles.Entry)var21.next();
                        targetMap.put(item.getKey(), item.getValue());
                     }

                     onlineProperties.setXsdValidationFiles(targetMap);
                     OnlinePropertiesHolder.setXsdSet(true);
                     ajc$this.storeXsdsOnDisk(targetMap);
                     var21 = xsdFiles.getEntries().iterator();

                     while(var21.hasNext()) {
                        item = (ValidationPropertiesResult.XsdValidationFiles.Entry)var21.next();
                        targetProperties.put(item.getKey().split(":")[0].replace("_", "."), ajc$this.getPropertyHandler().getProperty("online.xsd.path") + File.separator + item.getKey().split(":")[0] + File.separator + item.getKey().split(":")[1]);
                     }
                  }

                  ajc$this.storePropertiesVersionToDisk(result.getServerPropertiesVersion());
                  ajc$this.storePropertiesOnDisk(targetProperties);
                  OnlinePropertiesHolder.reloadProperties();
               } else {
                  ajc$this.readFromDisk();
               }

               var25 = result;
            } catch (Exception var31) {
               ajc$this.readFromDisk();
               return null;
            }
         } catch (Throwable var32) {
            Exceptionutils.errorHandler(var32);
            return null;
         } finally {
            OnlineProperties.setLoaded(true);
         }

         return var25;
      } else {
         return null;
      }
   }

   // $FF: synthetic method
   static final GetPrescriptionStatusResult getData_aroundBody18(HospitalPrescriberIntegrationModuleV4Impl ajc$this, GetPrescriptionStatusParam param, JoinPoint var2) {
      RidValidator.validateRid(param.getRid());
      ApplicationConfig.getInstance().assertValidSession();

      try {
         GetPrescriptionStatusRequest getPrescriptionStatusRequest = ajc$this.getGetPrescriptionStatus(param);

         try {
            GetPrescriptionStatusResponse response = RecipePrescriberServiceV4Impl.getInstance().getPrescriptionStatus(getPrescriptionStatusRequest);
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
   static final ListRidsHistoryResult getData_aroundBody20(HospitalPrescriberIntegrationModuleV4Impl ajc$this, ListRidsHistoryParam param, JoinPoint var2) {
      ApplicationConfig.getInstance().assertValidSession();
      ValidationUtils.validatePatientId(param.getPatientId());

      try {
         ListRidsHistoryRequest listRidsHistory = ajc$this.getListPrescriptionHistoryRequest(param);

         try {
            ListRidsHistoryResponse response = RecipePrescriberServiceV4Impl.getInstance().listRidsHistory(listRidsHistory);
            ListRidsHistoryResult result = ajc$this.unsealListPrescriptionHistoryResponse(response);
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
   static final PutVisionResult putData_aroundBody22(HospitalPrescriberIntegrationModuleV4Impl ajc$this, PutVisionParam param, JoinPoint var2) {
      RidValidator.validateRid(param.getRid());
      ValidationUtils.validateVisi(param.getVision(), false);
      ApplicationConfig.getInstance().assertValidSession();

      try {
         PutVisionForPrescriberRequest putVision = ajc$this.putVisionRequest(param);

         try {
            PutVisionForPrescriberResponse response = RecipePrescriberServiceV4Impl.getInstance().putVisionForPrescriber(putVision);
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
   static final ListOpenRidsResult getData_aroundBody24(HospitalPrescriberIntegrationModuleV4Impl ajc$this, ListOpenRidsParam param, JoinPoint var2) {
      ApplicationConfig.getInstance().assertValidSession();
      ValidationUtils.validatePatientId(param.getPatientId());

      try {
         ListOpenRidsRequest listOpenRids = ajc$this.getListOpenRids(param);

         try {
            ListOpenRidsResponse response = RecipePrescriberServiceV4Impl.getInstance().listOpenRids(listOpenRids);
            ListOpenRidsResult result = ajc$this.unsealListOpenRidsResponse(response);
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
   private static void ajc$preClinit() {
      Factory var0 = new Factory("HospitalPrescriberIntegrationModuleV4Impl.java", HospitalPrescriberIntegrationModuleV4Impl.class);
      ajc$tjp_0 = var0.makeSJP("method-execution", var0.makeMethodSig("1", "createPrescription", "be.business.connector.recipe.prescriber.HospitalPrescriberIntegrationModuleV4Impl", "boolean:java.lang.String:[B:java.lang.String", "feedbackRequested:patientId:prescription:prescriptionType", "be.business.connector.core.exceptions.IntegrationModuleException", "java.lang.String"), 144);
      ajc$tjp_1 = var0.makeSJP("method-execution", var0.makeMethodSig("1", "createPrescription", "be.business.connector.recipe.prescriber.HospitalPrescriberIntegrationModuleV4Impl", "boolean:java.lang.String:[B:java.lang.String:java.lang.String:java.lang.String", "feedbackRequested:patientId:prescription:prescriptionType:visi:expirationDate", "be.business.connector.core.exceptions.IntegrationModuleException", "java.lang.String"), 155);
      ajc$tjp_10 = var0.makeSJP("method-execution", var0.makeMethodSig("1", "getData", "be.business.connector.recipe.prescriber.HospitalPrescriberIntegrationModuleV4Impl", "be.recipe.services.prescriber.ListRidsHistoryParam", "param", "be.business.connector.core.exceptions.IntegrationModuleException", "be.recipe.services.prescriber.ListRidsHistoryResult"), 1009);
      ajc$tjp_11 = var0.makeSJP("method-execution", var0.makeMethodSig("1", "putData", "be.business.connector.recipe.prescriber.HospitalPrescriberIntegrationModuleV4Impl", "be.recipe.services.prescriber.PutVisionParam", "param", "be.business.connector.core.exceptions.IntegrationModuleException", "be.recipe.services.prescriber.PutVisionResult"), 1061);
      ajc$tjp_12 = var0.makeSJP("method-execution", var0.makeMethodSig("1", "getData", "be.business.connector.recipe.prescriber.HospitalPrescriberIntegrationModuleV4Impl", "be.recipe.services.prescriber.ListOpenRidsParam", "param", "be.business.connector.core.exceptions.IntegrationModuleException", "be.recipe.services.prescriber.ListOpenRidsResult"), 1120);
      ajc$tjp_2 = var0.makeSJP("method-execution", var0.makeMethodSig("1", "prepareCreatePrescription", "be.business.connector.recipe.prescriber.HospitalPrescriberIntegrationModuleV4Impl", "java.lang.String:java.lang.String", "patientId:prescriptionType", "be.business.connector.core.exceptions.IntegrationModuleException", "void"), 346);
      ajc$tjp_3 = var0.makeSJP("method-execution", var0.makeMethodSig("1", "getPrescription", "be.business.connector.recipe.prescriber.HospitalPrescriberIntegrationModuleV4Impl", "java.lang.String", "rid", "be.business.connector.core.exceptions.IntegrationModuleException", "be.recipe.services.prescriber.GetPrescriptionForPrescriberResult"), 365);
      ajc$tjp_4 = var0.makeSJP("method-execution", var0.makeMethodSig("1", "revokePrescription", "be.business.connector.recipe.prescriber.HospitalPrescriberIntegrationModuleV4Impl", "java.lang.String:java.lang.String", "rid:reason", "be.business.connector.core.exceptions.IntegrationModuleException", "void"), 422);
      ajc$tjp_5 = var0.makeSJP("method-execution", var0.makeMethodSig("1", "sendNotification", "be.business.connector.recipe.prescriber.HospitalPrescriberIntegrationModuleV4Impl", "[B:java.lang.String:java.lang.String", "notificationText:patientId:executorId", "be.business.connector.core.exceptions.IntegrationModuleException", "void"), 479);
      ajc$tjp_6 = var0.makeSJP("method-execution", var0.makeMethodSig("1", "updateFeedbackFlag", "be.business.connector.recipe.prescriber.HospitalPrescriberIntegrationModuleV4Impl", "java.lang.String:boolean", "rid:feedbackAllowed", "be.business.connector.core.exceptions.IntegrationModuleException", "void"), 546);
      ajc$tjp_7 = var0.makeSJP("method-execution", var0.makeMethodSig("1", "listFeedback", "be.business.connector.recipe.prescriber.HospitalPrescriberIntegrationModuleV4Impl", "boolean", "readFlag", "be.business.connector.core.exceptions.IntegrationModuleException", "java.util.List"), 596);
      ajc$tjp_8 = var0.makeSJP("method-execution", var0.makeMethodSig("1", "getData", "be.business.connector.recipe.prescriber.HospitalPrescriberIntegrationModuleV4Impl", "be.recipe.services.prescriber.ValidationPropertiesParam", "param", "be.business.connector.core.exceptions.IntegrationModuleException", "be.recipe.services.prescriber.ValidationPropertiesResult"), 659);
      ajc$tjp_9 = var0.makeSJP("method-execution", var0.makeMethodSig("1", "getData", "be.business.connector.recipe.prescriber.HospitalPrescriberIntegrationModuleV4Impl", "be.recipe.services.prescriber.GetPrescriptionStatusParam", "param", "be.business.connector.core.exceptions.IntegrationModuleException", "be.recipe.services.prescriber.GetPrescriptionStatusResult"), 932);
   }
}
