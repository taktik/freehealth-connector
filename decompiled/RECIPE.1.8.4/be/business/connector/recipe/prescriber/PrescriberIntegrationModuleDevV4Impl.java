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
import be.business.connector.core.utils.SessionValidator;
import be.business.connector.projects.common.utils.ValidationUtils;
import be.business.connector.recipe.prescriber.services.RecipePrescriberServiceDevV4Impl;
import be.business.connector.recipe.utils.RidValidator;
import be.ehealth.technicalconnector.service.kgss.domain.KeyResult;
import be.ehealth.technicalconnector.session.Session;
import be.ehealth.technicalconnector.session.SessionItem;
import be.fgov.ehealth.etee.crypto.encrypt.EncryptionToken;
import be.recipe.services.core.PartyIdentification;
import be.recipe.services.prescriber.CreatePrescription;
import be.recipe.services.prescriber.CreatePrescriptionParam;
import be.recipe.services.prescriber.CreatePrescriptionResponse;
import be.recipe.services.prescriber.CreatePrescriptionResult;
import be.recipe.services.prescriber.GetPrescriptionForPrescriber;
import be.recipe.services.prescriber.GetPrescriptionForPrescriberParam;
import be.recipe.services.prescriber.GetPrescriptionForPrescriberResponse;
import be.recipe.services.prescriber.GetPrescriptionForPrescriberResult;
import be.recipe.services.prescriber.GetPrescriptionStatus;
import be.recipe.services.prescriber.GetPrescriptionStatusParam;
import be.recipe.services.prescriber.GetPrescriptionStatusResponse;
import be.recipe.services.prescriber.GetPrescriptionStatusResult;
import be.recipe.services.prescriber.ListFeedbackItem;
import be.recipe.services.prescriber.ListFeedbacks;
import be.recipe.services.prescriber.ListFeedbacksParam;
import be.recipe.services.prescriber.ListFeedbacksResponse;
import be.recipe.services.prescriber.ListFeedbacksResult;
import be.recipe.services.prescriber.ListOpenRids;
import be.recipe.services.prescriber.ListOpenRidsParam;
import be.recipe.services.prescriber.ListOpenRidsResponse;
import be.recipe.services.prescriber.ListOpenRidsResult;
import be.recipe.services.prescriber.ListRidsHistory;
import be.recipe.services.prescriber.ListRidsHistoryParam;
import be.recipe.services.prescriber.ListRidsHistoryResponse;
import be.recipe.services.prescriber.ListRidsHistoryResult;
import be.recipe.services.prescriber.PutVision;
import be.recipe.services.prescriber.PutVisionParam;
import be.recipe.services.prescriber.PutVisionResponse;
import be.recipe.services.prescriber.PutVisionResult;
import be.recipe.services.prescriber.RevokePrescription;
import be.recipe.services.prescriber.RevokePrescriptionParam;
import be.recipe.services.prescriber.RevokePrescriptionResponse;
import be.recipe.services.prescriber.RevokePrescriptionResult;
import be.recipe.services.prescriber.SendNotification;
import be.recipe.services.prescriber.SendNotificationParam;
import be.recipe.services.prescriber.SendNotificationResponse;
import be.recipe.services.prescriber.SendNotificationResult;
import be.recipe.services.prescriber.UpdateFeedbackFlag;
import be.recipe.services.prescriber.UpdateFeedbackFlagParam;
import be.recipe.services.prescriber.UpdateFeedbackFlagResponse;
import be.recipe.services.prescriber.UpdateFeedbackFlagResult;
import be.recipe.services.prescriber.ValidationProperties;
import be.recipe.services.prescriber.ValidationPropertiesParam;
import be.recipe.services.prescriber.ValidationPropertiesResponse;
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
import java.util.UUID;
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
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.JoinPoint.StaticPart;
import org.aspectj.runtime.internal.Conversions;
import org.aspectj.runtime.reflect.Factory;
import org.perf4j.aop.Profiled;
import org.perf4j.log4j.aop.TimingAspect;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class PrescriberIntegrationModuleDevV4Impl extends PrescriberIntegrationModuleImpl implements PrescriberIntegrationModuleDevV4 {
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
      LOG = Logger.getLogger(PrescriberIntegrationModuleDevV4Impl.class);
   }

   public PrescriberIntegrationModuleDevV4Impl() throws IntegrationModuleException {
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
      ProceedingJoinPoint var10001 = (new PrescriberIntegrationModuleDevV4Impl$AjcClosure1(var11)).linkClosureAndJoinPoint(69648);
      Annotation var10002 = ajc$anno$0;
      if (var10002 == null) {
         var10002 = ajc$anno$0 = PrescriberIntegrationModuleDevV4Impl.class.getDeclaredMethod("createPrescription", Boolean.TYPE, String.class, byte[].class, String.class).getAnnotation(Profiled.class);
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
      ProceedingJoinPoint var10001 = (new PrescriberIntegrationModuleDevV4Impl$AjcClosure3(var25)).linkClosureAndJoinPoint(69648);
      Annotation var10002 = ajc$anno$1;
      if (var10002 == null) {
         var10002 = ajc$anno$1 = PrescriberIntegrationModuleDevV4Impl.class.getDeclaredMethod("createPrescription", Boolean.TYPE, String.class, byte[].class, String.class, String.class, String.class).getAnnotation(Profiled.class);
      }

      return (String)var26.doPerfLogging(var10001, (Profiled)var10002);
   }

   private String getDefaultExpirationDate() {
      return this.getDefaultExpirationDate((String)null);
   }

   private String getDefaultExpirationDate(String expirationDate) {
      if (StringUtils.isBlank(expirationDate)) {
         Calendar defaultExpirationDate = Calendar.getInstance();
         defaultExpirationDate.add(2, 3);
         String var10000 = "yyyy-MM-dd";
         SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
         return sdf.format(defaultExpirationDate.getTime());
      } else {
         return expirationDate;
      }
   }

   private void performValidation(byte[] prescription, String prescriptionType, String expirationDate) throws IntegrationModuleException {
      ArrayList errors = new ArrayList();

      try {
         this.getKmehrHelper().assertValidKmehrPrescription(prescription, prescriptionType);
      } catch (IntegrationModuleValidationException var7) {
         errors.addAll(var7.getValidationErrors());
      }

      this.validateExpirationDateFromKmehr(prescription, errors, expirationDate);
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

   private String generateRid(String prescriptionType) {
      String rid = "BE" + prescriptionType + "JNT" + RandomStringUtils.random(5, true, false).toUpperCase();
      rid = rid.replace('I', 'J').replace('O', 'A').replace('U', 'V');
      return rid;
   }

   @Profiled(
      logFailuresSeparately = true,
      tag = "PrescriberIntegrationModule#prepareCreatePrescription"
   )
   public void prepareCreatePrescription(String patientId, String prescriptionType) throws IntegrationModuleException {
      JoinPoint var6 = Factory.makeJP(ajc$tjp_2, this, this, patientId, prescriptionType);
      TimingAspect var10000 = TimingAspect.aspectOf();
      Object[] var7 = new Object[]{this, patientId, prescriptionType, var6};
      ProceedingJoinPoint var10001 = (new PrescriberIntegrationModuleDevV4Impl$AjcClosure5(var7)).linkClosureAndJoinPoint(69648);
      Annotation var10002 = ajc$anno$2;
      if (var10002 == null) {
         var10002 = ajc$anno$2 = PrescriberIntegrationModuleDevV4Impl.class.getDeclaredMethod("prepareCreatePrescription", String.class, String.class).getAnnotation(Profiled.class);
      }

      var10000.doPerfLogging(var10001, (Profiled)var10002);
   }

   @Profiled(
      logFailuresSeparately = true,
      tag = "0.PrescriberIntegrationModule#getPrescription"
   )
   public GetPrescriptionForPrescriberResult getPrescription(String rid) throws IntegrationModuleException {
      JoinPoint var11 = Factory.makeJP(ajc$tjp_3, this, this, rid);
      TimingAspect var10000 = TimingAspect.aspectOf();
      Object[] var12 = new Object[]{this, rid, var11};
      ProceedingJoinPoint var10001 = (new PrescriberIntegrationModuleDevV4Impl$AjcClosure7(var12)).linkClosureAndJoinPoint(69648);
      Annotation var10002 = ajc$anno$3;
      if (var10002 == null) {
         var10002 = ajc$anno$3 = PrescriberIntegrationModuleDevV4Impl.class.getDeclaredMethod("getPrescription", String.class).getAnnotation(Profiled.class);
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
      ProceedingJoinPoint var10001 = (new PrescriberIntegrationModuleDevV4Impl$AjcClosure9(var13)).linkClosureAndJoinPoint(69648);
      Annotation var10002 = ajc$anno$4;
      if (var10002 == null) {
         var10002 = ajc$anno$4 = PrescriberIntegrationModuleDevV4Impl.class.getDeclaredMethod("revokePrescription", String.class, String.class).getAnnotation(Profiled.class);
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
      ProceedingJoinPoint var10001 = (new PrescriberIntegrationModuleDevV4Impl$AjcClosure11(var21)).linkClosureAndJoinPoint(69648);
      Annotation var10002 = ajc$anno$5;
      if (var10002 == null) {
         var10002 = ajc$anno$5 = PrescriberIntegrationModuleDevV4Impl.class.getDeclaredMethod("sendNotification", byte[].class, String.class, String.class).getAnnotation(Profiled.class);
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
      ProceedingJoinPoint var10001 = (new PrescriberIntegrationModuleDevV4Impl$AjcClosure13(var14)).linkClosureAndJoinPoint(69648);
      Annotation var10002 = ajc$anno$6;
      if (var10002 == null) {
         var10002 = ajc$anno$6 = PrescriberIntegrationModuleDevV4Impl.class.getDeclaredMethod("updateFeedbackFlag", String.class, Boolean.TYPE).getAnnotation(Profiled.class);
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
      ProceedingJoinPoint var10001 = (new PrescriberIntegrationModuleDevV4Impl$AjcClosure15(var15)).linkClosureAndJoinPoint(69648);
      Annotation var10002 = ajc$anno$7;
      if (var10002 == null) {
         var10002 = ajc$anno$7 = PrescriberIntegrationModuleDevV4Impl.class.getDeclaredMethod("listFeedback", Boolean.TYPE).getAnnotation(Profiled.class);
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
      ProceedingJoinPoint var10001 = (new PrescriberIntegrationModuleDevV4Impl$AjcClosure17(var17)).linkClosureAndJoinPoint(69648);
      Annotation var10002 = ajc$anno$8;
      if (var10002 == null) {
         var10002 = ajc$anno$8 = PrescriberIntegrationModuleDevV4Impl.class.getDeclaredMethod("getData", ValidationPropertiesParam.class).getAnnotation(Profiled.class);
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

   protected ValidationProperties getValidationProperties(ValidationPropertiesParam param) throws IntegrationModuleException {
      param.setSymmKey(this.getSymmKey().getEncoded());
      ValidationProperties request = new ValidationProperties();
      request.setValidationPropertiesParamSealed(this.getSealedData(param));
      request.setProgramIdentification(PropertyHandler.getInstance().getProperty("programIdentification"));
      request.setMguid(UUID.randomUUID().toString());
      return request;
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
      ProceedingJoinPoint var10001 = (new PrescriberIntegrationModuleDevV4Impl$AjcClosure19(var7)).linkClosureAndJoinPoint(69648);
      Annotation var10002 = ajc$anno$9;
      if (var10002 == null) {
         var10002 = ajc$anno$9 = PrescriberIntegrationModuleDevV4Impl.class.getDeclaredMethod("getData", GetPrescriptionStatusParam.class).getAnnotation(Profiled.class);
      }

      return (GetPrescriptionStatusResult)var10000.doPerfLogging(var10001, (Profiled)var10002);
   }

   protected GetPrescriptionStatus getGetPrescriptionStatus(GetPrescriptionStatusParam param) throws IntegrationModuleException {
      param.setSymmKey(this.getSymmKey().getEncoded());
      GetPrescriptionStatus request = new GetPrescriptionStatus();
      request.setGetPrescriptionStatusParamSealed(this.getSealedData(param));
      request.setProgramIdentification(PropertyHandler.getInstance().getProperty("programIdentification"));
      request.setMguid(UUID.randomUUID().toString());
      return request;
   }

   private GetPrescriptionStatusResult unsealGetPrescriptionStatusResponse(GetPrescriptionStatusResponse response) {
      MarshallerHelper<GetPrescriptionStatusResult, Object> marshaller = new MarshallerHelper(GetPrescriptionStatusResult.class, Object.class);
      return (GetPrescriptionStatusResult)marshaller.unsealWithSymmKey(response.getGetPrescriptionStatusResultSealed(), this.getSymmKey());
   }

   private ValidationPropertiesResult unsealValidationPropertiesResponse(ValidationPropertiesResponse response) {
      MarshallerHelper<ValidationPropertiesResult, Schema> marshaller = new MarshallerHelper(ValidationPropertiesResult.class, Schema.class);
      return (ValidationPropertiesResult)marshaller.unsealWithSymmKey(response.getValidationPropertiesResultSealed(), this.getSymmKey());
   }

   @Profiled(
      logFailuresSeparately = true,
      tag = "0.PrescriberIntegrationModuleV4#getData(ListPrescriptionHistoryParam)"
   )
   public ListRidsHistoryResult getData(ListRidsHistoryParam param) throws IntegrationModuleException {
      JoinPoint var6 = Factory.makeJP(ajc$tjp_10, this, this, param);
      TimingAspect var10000 = TimingAspect.aspectOf();
      Object[] var7 = new Object[]{this, param, var6};
      ProceedingJoinPoint var10001 = (new PrescriberIntegrationModuleDevV4Impl$AjcClosure21(var7)).linkClosureAndJoinPoint(69648);
      Annotation var10002 = ajc$anno$10;
      if (var10002 == null) {
         var10002 = ajc$anno$10 = PrescriberIntegrationModuleDevV4Impl.class.getDeclaredMethod("getData", ListRidsHistoryParam.class).getAnnotation(Profiled.class);
      }

      return (ListRidsHistoryResult)var10000.doPerfLogging(var10001, (Profiled)var10002);
   }

   private ListRidsHistory getListPrescriptionHistoryRequest(ListRidsHistoryParam listRidHistoryParam) throws IntegrationModuleException {
      listRidHistoryParam.setSymmKey(this.getSymmKey().getEncoded());
      ListRidsHistory listRidsHistory = new ListRidsHistory();
      listRidsHistory.setListRidsHistoryParamSealed(this.getSealedData(listRidHistoryParam));
      listRidsHistory.setProgramIdentification(PropertyHandler.getInstance().getProperty("programIdentification"));
      listRidsHistory.setMguid(UUID.randomUUID().toString());
      return listRidsHistory;
   }

   private ListRidsHistoryResult unsealListPrescriptionHistoryResponse(ListRidsHistoryResponse response) {
      MarshallerHelper<ListRidsHistoryResult, Object> marshaller = new MarshallerHelper(ListRidsHistoryResult.class, Object.class);
      return (ListRidsHistoryResult)marshaller.unsealWithSymmKey(response.getListRidsHistoryResultSealed(), this.getSymmKey());
   }

   @Profiled(
      logFailuresSeparately = true,
      tag = "0.PrescriberIntegrationModuleV4#putData(PutVisionParam)"
   )
   public PutVisionResult putData(PutVisionParam param) throws IntegrationModuleException {
      JoinPoint var6 = Factory.makeJP(ajc$tjp_11, this, this, param);
      TimingAspect var10000 = TimingAspect.aspectOf();
      Object[] var7 = new Object[]{this, param, var6};
      ProceedingJoinPoint var10001 = (new PrescriberIntegrationModuleDevV4Impl$AjcClosure23(var7)).linkClosureAndJoinPoint(69648);
      Annotation var10002 = ajc$anno$11;
      if (var10002 == null) {
         var10002 = ajc$anno$11 = PrescriberIntegrationModuleDevV4Impl.class.getDeclaredMethod("putData", PutVisionParam.class).getAnnotation(Profiled.class);
      }

      return (PutVisionResult)var10000.doPerfLogging(var10001, (Profiled)var10002);
   }

   protected PutVision putVisionRequest(PutVisionParam putVisionParam) throws IntegrationModuleException {
      putVisionParam.setSymmKey(this.getSymmKey().getEncoded());
      PutVision putVision = new PutVision();
      putVision.setPutVisionParamSealed(this.getSealedData(putVisionParam));
      putVision.setProgramIdentification(PropertyHandler.getInstance().getProperty("programIdentification"));
      putVision.setMguid(UUID.randomUUID().toString());
      return putVision;
   }

   private PutVisionResult unsealPutVisionResponse(PutVisionResponse response) {
      MarshallerHelper<PutVisionResult, Object> marshaller = new MarshallerHelper(PutVisionResult.class, Object.class);
      PutVisionResult result = (PutVisionResult)marshaller.unsealWithSymmKey(response.getPutVisionResultSealed(), this.getSymmKey());
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
      ProceedingJoinPoint var10001 = (new PrescriberIntegrationModuleDevV4Impl$AjcClosure25(var7)).linkClosureAndJoinPoint(69648);
      Annotation var10002 = ajc$anno$12;
      if (var10002 == null) {
         var10002 = ajc$anno$12 = PrescriberIntegrationModuleDevV4Impl.class.getDeclaredMethod("getData", ListOpenRidsParam.class).getAnnotation(Profiled.class);
      }

      return (ListOpenRidsResult)var10000.doPerfLogging(var10001, (Profiled)var10002);
   }

   protected ListOpenRids getListOpenRids(ListOpenRidsParam param) throws IntegrationModuleException {
      param.setSymmKey(this.getSymmKey().getEncoded());
      ListOpenRids request = new ListOpenRids();
      request.setListOpenRidsParamSealed(this.getSealedData(param));
      request.setProgramIdentification(PropertyHandler.getInstance().getProperty("programIdentification"));
      request.setMguid(UUID.randomUUID().toString());
      return request;
   }

   private ListOpenRidsResult unsealListOpenRidsResponse(ListOpenRidsResponse response) {
      MarshallerHelper<ListOpenRidsResult, Object> marshaller = new MarshallerHelper(ListOpenRidsResult.class, Object.class);
      return (ListOpenRidsResult)marshaller.unsealWithSymmKey(response.getListOpenRidsResultSealed(), this.getSymmKey());
   }

   // $FF: synthetic method
   static final String createPrescription_aroundBody0(PrescriberIntegrationModuleDevV4Impl ajc$this, boolean feedbackRequested, String patientId, byte[] prescription, String prescriptionType, JoinPoint var5) {
      return ajc$this.createPrescription(feedbackRequested, patientId, prescription, prescriptionType, (String)null, ajc$this.getDefaultExpirationDate());
   }

   // $FF: synthetic method
   static final String createPrescription_aroundBody2(PrescriberIntegrationModuleDevV4Impl ajc$this, boolean feedbackRequested, String patientId, byte[] prescription, String prescriptionType, String visi, String expirationDate, JoinPoint var7) {
      ApplicationConfig.getInstance().assertValidSession();
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
         params.setExpirationDate(ajc$this.getDefaultExpirationDate(expirationDate));
         params.setVision(visi);
         CreatePrescription request = new CreatePrescription();
         request.setCreatePrescriptionParamSealed(ajc$this.sealRequest((EncryptionToken)etkRecipes.get(0), helper.toXMLByteArray(params)));
         request.setKeyId(key.getKeyId());
         request.setPrescriptionType(prescriptionType);
         request.setDocumentId(ajc$this.generateRid(prescriptionType));
         request.setPrescriptionVersion(PropertyHandler.getInstance().getProperty("prescription.version"));
         request.setReferenceSourceVersion(ajc$this.extractReferenceSourceVersionFromKmehr(prescription));
         request.setProgramIdentification(propertyHandler.getProperty("programIdentification"));
         request.setMguid(UUID.randomUUID().toString());
         SessionItem sessionItem = Session.getInstance().getSession();
         SessionValidator.assertValidSession(sessionItem);
         request.setSecurityToken(sessionItem.getSAMLToken().getAssertion());
         CreatePrescriptionResponse response = RecipePrescriberServiceDevV4Impl.getInstance().createPrescription(request);
         CreatePrescriptionResult result = (CreatePrescriptionResult)helper.unsealWithSymmKey(response.getCreatePrescriptionResultSealed(), ajc$this.getSymmKey());
         ajc$this.checkStatus(result);
         ajc$this.setPatientId(result.getRid(), patientId);
         return result.getRid();
      } catch (Throwable var27) {
         Exceptionutils.errorHandler(var27);
         return null;
      }
   }

   // $FF: synthetic method
   static final void prepareCreatePrescription_aroundBody4(PrescriberIntegrationModuleDevV4Impl ajc$this, String patientId, String prescriptionType, JoinPoint var3) {
      ApplicationConfig.getInstance().assertValidSession();
      String cacheId = "(" + patientId + "#" + prescriptionType + ")";
      ajc$this.keyCache.put(cacheId, ajc$this.getNewKeyFromKgss(prescriptionType, StandaloneRequestorProvider.getRequestorIdInformation(), (String)null, patientId, ((EncryptionToken)ajc$this.getEtkHelper().getSystemETK().get(0)).getEncoded()));
   }

   // $FF: synthetic method
   static final GetPrescriptionForPrescriberResult getPrescription_aroundBody6(PrescriberIntegrationModuleDevV4Impl ajc$this, String rid, JoinPoint var2) {
      RidValidator.validateRid(rid);
      ApplicationConfig.getInstance().assertValidSession();

      try {
         MarshallerHelper helper = new MarshallerHelper(GetPrescriptionForPrescriberResult.class, GetPrescriptionForPrescriberParam.class);
         List etkRecipes = ajc$this.getEtkHelper().getRecipe_ETK();
         GetPrescriptionForPrescriberParam param = new GetPrescriptionForPrescriberParam();
         param.setRid(rid);
         param.setSymmKey(ajc$this.getSymmKey().getEncoded());
         GetPrescriptionForPrescriber request = new GetPrescriptionForPrescriber();
         request.setGetPrescriptionForPrescriberParamSealed(ajc$this.sealRequest((EncryptionToken)etkRecipes.get(0), helper.toXMLByteArray(param)));
         request.setProgramIdentification(PropertyHandler.getInstance().getProperty("programIdentification"));
         request.setMguid(UUID.randomUUID().toString());
         GetPrescriptionForPrescriberResponse response = null;

         try {
            response = RecipePrescriberServiceDevV4Impl.getInstance().getPrescriptionForPrescriber(request);
         } catch (ClientTransportException var18) {
            throw new IntegrationModuleException(I18nHelper.getLabel("error.connection.prescriber"), var18);
         }

         GetPrescriptionForPrescriberResult result = (GetPrescriptionForPrescriberResult)helper.unsealWithSymmKey(response.getGetPrescriptionForPrescriberResultSealed(), ajc$this.getSymmKey());
         ajc$this.checkStatus(result);
         KeyResult key = ajc$this.getKeyFromKgss(result.getEncryptionKeyId(), ((EncryptionToken)ajc$this.getEtkHelper().getSystemETK().get(0)).getEncoded());
         byte[] unsealedPrescription = IOUtils.decompress(ajc$this.unsealPrescriptionForUnknown(key, result.getPrescription()));
         result.setPrescription(unsealedPrescription);
         ajc$this.setPatientId(result.getRid(), result.getPatientId());
         return result;
      } catch (Throwable var19) {
         Exceptionutils.errorHandler(var19);
         return null;
      }
   }

   // $FF: synthetic method
   static final void revokePrescription_aroundBody8(PrescriberIntegrationModuleDevV4Impl ajc$this, String rid, String reason, JoinPoint var3) {
      RidValidator.validateRid(rid);
      ApplicationConfig.getInstance().assertValidSession();

      try {
         MarshallerHelper helper = new MarshallerHelper(Object.class, RevokePrescriptionParam.class);
         List etkRecipes = ajc$this.getEtkHelper().getRecipe_ETK();
         RevokePrescriptionParam params = new RevokePrescriptionParam();
         params.setRid(rid);
         params.setReason(reason);
         params.setSymmKey(ajc$this.getSymmKey().getEncoded());
         RevokePrescription request = new RevokePrescription();
         request.setRevokePrescriptionParamSealed(ajc$this.sealRequest((EncryptionToken)etkRecipes.get(0), helper.toXMLByteArray(params)));
         request.setProgramIdentification(PropertyHandler.getInstance().getProperty("programIdentification"));
         request.setMguid(UUID.randomUUID().toString());

         try {
            RevokePrescriptionResponse response = RecipePrescriberServiceDevV4Impl.getInstance().revokePrescription(request);
            MarshallerHelper marshaller = new MarshallerHelper(RevokePrescriptionResult.class, Object.class);
            RevokePrescriptionResult result = (RevokePrescriptionResult)marshaller.unsealWithSymmKey(response.getRevokePrescriptionResultSealed(), ajc$this.getSymmKey());
            ajc$this.checkStatus(result);
         } catch (ClientTransportException var17) {
            throw new IntegrationModuleException(I18nHelper.getLabel("error.connection.prescriber"), var17);
         }
      } catch (Throwable var18) {
         Exceptionutils.errorHandler(var18);
      }

   }

   // $FF: synthetic method
   static final void sendNotification_aroundBody10(PrescriberIntegrationModuleDevV4Impl ajc$this, byte[] notificationText, String patientId, String executorId, JoinPoint var4) {
      ValidationUtils.validatePatientId(patientId);
      ApplicationConfig.getInstance().assertValidSession();

      try {
         ajc$this.getKmehrHelper().assertValidNotification(notificationText);
         ValidationUtils.validatePatientId(patientId);
         MarshallerHelper helper = new MarshallerHelper(Object.class, SendNotificationParam.class);
         List etkRecipes = ajc$this.getEtkHelper().getRecipe_ETK();
         List etkRecipients = ajc$this.getEtkHelper().getEtks(KgssIdentifierType.NIHII_PHARMACY, executorId);
         byte[] notificationZip = IOUtils.compress(notificationText);

         for(int i = 0; i < etkRecipients.size(); ++i) {
            EncryptionToken etkRecipient = (EncryptionToken)etkRecipients.get(0);
            byte[] notificationSealed = ajc$this.sealNotification(etkRecipient, notificationZip);
            SendNotificationParam param = new SendNotificationParam();
            param.setContent(notificationSealed);
            param.setExecutorId(executorId);
            param.setPatientId(patientId);
            param.setSymmKey(ajc$this.getSymmKey().getEncoded());
            SendNotification request = new SendNotification();
            request.setSendNotificationParamSealed(ajc$this.sealRequest((EncryptionToken)etkRecipes.get(0), helper.toXMLByteArray(param)));
            request.setProgramIdentification(PropertyHandler.getInstance().getProperty("programIdentification"));
            request.setMguid(UUID.randomUUID().toString());

            try {
               SendNotificationResponse response = RecipePrescriberServiceDevV4Impl.getInstance().sendNotification(request);
               MarshallerHelper helper1 = new MarshallerHelper(SendNotificationResult.class, SendNotificationResult.class);
               SendNotificationResult result = (SendNotificationResult)helper1.unsealWithSymmKey(response.getSendNotificationResultSealed(), ajc$this.getSymmKey());
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
   static final void updateFeedbackFlag_aroundBody12(PrescriberIntegrationModuleDevV4Impl ajc$this, String rid, boolean feedbackAllowed, JoinPoint var3) {
      RidValidator.validateRid(rid);
      ApplicationConfig.getInstance().assertValidSession();

      try {
         MarshallerHelper helper = new MarshallerHelper(Object.class, UpdateFeedbackFlagParam.class);
         List etkRecipes = ajc$this.getEtkHelper().getRecipe_ETK();
         UpdateFeedbackFlagParam param = new UpdateFeedbackFlagParam();
         param.setAllowFeedback(feedbackAllowed);
         param.setRid(rid);
         param.setSymmKey(ajc$this.getSymmKey().getEncoded());
         UpdateFeedbackFlag request = new UpdateFeedbackFlag();
         request.setUpdateFeedbackFlagParamSealed(ajc$this.sealRequest((EncryptionToken)etkRecipes.get(0), helper.toXMLByteArray(param)));
         request.setProgramIdentification(PropertyHandler.getInstance().getProperty("programIdentification"));
         request.setMguid(UUID.randomUUID().toString());
         PartyIdentification value = new PartyIdentification();
         value.setPatientId(ajc$this.getPatientId(rid));

         try {
            UpdateFeedbackFlagResponse response = RecipePrescriberServiceDevV4Impl.getInstance().putFeedbackFlag(request);
            MarshallerHelper helper1 = new MarshallerHelper(UpdateFeedbackFlagResult.class, UpdateFeedbackFlagResult.class);
            UpdateFeedbackFlagResult result = (UpdateFeedbackFlagResult)helper1.unsealWithSymmKey(response.getUpdateFeedbackFlagResultSealed(), ajc$this.getSymmKey());
            ajc$this.checkStatus(result);
         } catch (ClientTransportException var19) {
            throw new IntegrationModuleException(I18nHelper.getLabel("error.connection.prescriber"), var19);
         }
      } catch (Throwable var20) {
         Exceptionutils.errorHandler(var20);
      }

   }

   // $FF: synthetic method
   static final List listFeedback_aroundBody14(PrescriberIntegrationModuleDevV4Impl ajc$this, boolean readFlag, JoinPoint var2) {
      ApplicationConfig.getInstance().assertValidSession();

      try {
         MarshallerHelper helper = new MarshallerHelper(ListFeedbacksResult.class, ListFeedbacksParam.class);
         List etkRecipes = ajc$this.getEtkHelper().getRecipe_ETK();
         ListFeedbacksParam param = new ListFeedbacksParam();
         param.setReadFlag(readFlag);
         param.setSymmKey(ajc$this.getSymmKey().getEncoded());
         ListFeedbacks request = new ListFeedbacks();
         request.setListFeedbacksParamSealed(ajc$this.sealRequest((EncryptionToken)etkRecipes.get(0), helper.toXMLByteArray(param)));
         request.setProgramIdentification(PropertyHandler.getInstance().getProperty("programIdentification"));
         request.setMguid(UUID.randomUUID().toString());
         ListFeedbacksResponse response = null;

         try {
            response = RecipePrescriberServiceDevV4Impl.getInstance().listFeedbacks(request);
         } catch (ClientTransportException var25) {
            throw new IntegrationModuleException(I18nHelper.getLabel("error.connection.prescriber"), var25);
         }

         ListFeedbacksResult result = (ListFeedbacksResult)helper.unsealWithSymmKey(response.getListFeedbacksResultSealed(), ajc$this.getSymmKey());
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
   static final ValidationPropertiesResult getData_aroundBody16(PrescriberIntegrationModuleDevV4Impl ajc$this, ValidationPropertiesParam param, JoinPoint var2) {
      if (!OnlineProperties.isLoaded()) {
         ApplicationConfig.getInstance().assertValidSession();

         ValidationPropertiesResult var25;
         try {
            try {
               ValidationProperties request = ajc$this.getValidationProperties(param);
               ValidationPropertiesResponse response = RecipePrescriberServiceDevV4Impl.getInstance().getValidationProperties(request);
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
   static final GetPrescriptionStatusResult getData_aroundBody18(PrescriberIntegrationModuleDevV4Impl ajc$this, GetPrescriptionStatusParam param, JoinPoint var2) {
      RidValidator.validateRid(param.getRid());
      ApplicationConfig.getInstance().assertValidSession();

      try {
         GetPrescriptionStatus getPrescriptionStatus = ajc$this.getGetPrescriptionStatus(param);

         try {
            GetPrescriptionStatusResponse response = RecipePrescriberServiceDevV4Impl.getInstance().getPrescriptionStatus(getPrescriptionStatus);
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
   static final ListRidsHistoryResult getData_aroundBody20(PrescriberIntegrationModuleDevV4Impl ajc$this, ListRidsHistoryParam param, JoinPoint var2) {
      ApplicationConfig.getInstance().assertValidSession();
      ValidationUtils.validatePatientId(param.getPatientId());

      try {
         ListRidsHistory listPrescriptionHistory = ajc$this.getListPrescriptionHistoryRequest(param);

         try {
            ListRidsHistoryResponse response = RecipePrescriberServiceDevV4Impl.getInstance().listRidsHistory(listPrescriptionHistory);
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
   static final PutVisionResult putData_aroundBody22(PrescriberIntegrationModuleDevV4Impl ajc$this, PutVisionParam param, JoinPoint var2) {
      RidValidator.validateRid(param.getRid());
      ValidationUtils.validateVisi(param.getVision(), false);
      ApplicationConfig.getInstance().assertValidSession();

      try {
         try {
            PutVision putVision = ajc$this.putVisionRequest(param);
            PutVisionResponse response = RecipePrescriberServiceDevV4Impl.getInstance().putVisionForPrescriber(putVision);
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
   static final ListOpenRidsResult getData_aroundBody24(PrescriberIntegrationModuleDevV4Impl ajc$this, ListOpenRidsParam param, JoinPoint var2) {
      ApplicationConfig.getInstance().assertValidSession();
      ValidationUtils.validatePatientId(param.getPatientId());

      try {
         ListOpenRids listOpenPrescriptions = ajc$this.getListOpenRids(param);

         try {
            ListOpenRidsResponse response = RecipePrescriberServiceDevV4Impl.getInstance().listOpenRids(listOpenPrescriptions);
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
      Factory var0 = new Factory("PrescriberIntegrationModuleDevV4Impl.java", PrescriberIntegrationModuleDevV4Impl.class);
      ajc$tjp_0 = var0.makeSJP("method-execution", var0.makeMethodSig("1", "createPrescription", "be.business.connector.recipe.prescriber.PrescriberIntegrationModuleDevV4Impl", "boolean:java.lang.String:[B:java.lang.String", "feedbackRequested:patientId:prescription:prescriptionType", "be.business.connector.core.exceptions.IntegrationModuleException", "java.lang.String"), 143);
      ajc$tjp_1 = var0.makeSJP("method-execution", var0.makeMethodSig("1", "createPrescription", "be.business.connector.recipe.prescriber.PrescriberIntegrationModuleDevV4Impl", "boolean:java.lang.String:[B:java.lang.String:java.lang.String:java.lang.String", "feedbackRequested:patientId:prescription:prescriptionType:visi:expirationDate", "be.business.connector.core.exceptions.IntegrationModuleException", "java.lang.String"), 154);
      ajc$tjp_10 = var0.makeSJP("method-execution", var0.makeMethodSig("1", "getData", "be.business.connector.recipe.prescriber.PrescriberIntegrationModuleDevV4Impl", "be.recipe.services.prescriber.ListRidsHistoryParam", "param", "be.business.connector.core.exceptions.IntegrationModuleException", "be.recipe.services.prescriber.ListRidsHistoryResult"), 953);
      ajc$tjp_11 = var0.makeSJP("method-execution", var0.makeMethodSig("1", "putData", "be.business.connector.recipe.prescriber.PrescriberIntegrationModuleDevV4Impl", "be.recipe.services.prescriber.PutVisionParam", "param", "be.business.connector.core.exceptions.IntegrationModuleException", "be.recipe.services.prescriber.PutVisionResult"), 1013);
      ajc$tjp_12 = var0.makeSJP("method-execution", var0.makeMethodSig("1", "getData", "be.business.connector.recipe.prescriber.PrescriberIntegrationModuleDevV4Impl", "be.recipe.services.prescriber.ListOpenRidsParam", "param", "be.business.connector.core.exceptions.IntegrationModuleException", "be.recipe.services.prescriber.ListOpenRidsResult"), 1071);
      ajc$tjp_2 = var0.makeSJP("method-execution", var0.makeMethodSig("1", "prepareCreatePrescription", "be.business.connector.recipe.prescriber.PrescriberIntegrationModuleDevV4Impl", "java.lang.String:java.lang.String", "patientId:prescriptionType", "be.business.connector.core.exceptions.IntegrationModuleException", "void"), 335);
      ajc$tjp_3 = var0.makeSJP("method-execution", var0.makeMethodSig("1", "getPrescription", "be.business.connector.recipe.prescriber.PrescriberIntegrationModuleDevV4Impl", "java.lang.String", "rid", "be.business.connector.core.exceptions.IntegrationModuleException", "be.recipe.services.prescriber.GetPrescriptionForPrescriberResult"), 354);
      ajc$tjp_4 = var0.makeSJP("method-execution", var0.makeMethodSig("1", "revokePrescription", "be.business.connector.recipe.prescriber.PrescriberIntegrationModuleDevV4Impl", "java.lang.String:java.lang.String", "rid:reason", "be.business.connector.core.exceptions.IntegrationModuleException", "void"), 409);
      ajc$tjp_5 = var0.makeSJP("method-execution", var0.makeMethodSig("1", "sendNotification", "be.business.connector.recipe.prescriber.PrescriberIntegrationModuleDevV4Impl", "[B:java.lang.String:java.lang.String", "notificationText:patientId:executorId", "be.business.connector.core.exceptions.IntegrationModuleException", "void"), 465);
      ajc$tjp_6 = var0.makeSJP("method-execution", var0.makeMethodSig("1", "updateFeedbackFlag", "be.business.connector.recipe.prescriber.PrescriberIntegrationModuleDevV4Impl", "java.lang.String:boolean", "rid:feedbackAllowed", "be.business.connector.core.exceptions.IntegrationModuleException", "void"), 532);
      ajc$tjp_7 = var0.makeSJP("method-execution", var0.makeMethodSig("1", "listFeedback", "be.business.connector.recipe.prescriber.PrescriberIntegrationModuleDevV4Impl", "boolean", "readFlag", "be.business.connector.core.exceptions.IntegrationModuleException", "java.util.List"), 579);
      ajc$tjp_8 = var0.makeSJP("method-execution", var0.makeMethodSig("1", "getData", "be.business.connector.recipe.prescriber.PrescriberIntegrationModuleDevV4Impl", "be.recipe.services.prescriber.ValidationPropertiesParam", "param", "be.business.connector.core.exceptions.IntegrationModuleException", "be.recipe.services.prescriber.ValidationPropertiesResult"), 641);
      ajc$tjp_9 = var0.makeSJP("method-execution", var0.makeMethodSig("1", "getData", "be.business.connector.recipe.prescriber.PrescriberIntegrationModuleDevV4Impl", "be.recipe.services.prescriber.GetPrescriptionStatusParam", "param", "be.business.connector.core.exceptions.IntegrationModuleException", "be.recipe.services.prescriber.GetPrescriptionStatusResult"), 880);
   }
}
