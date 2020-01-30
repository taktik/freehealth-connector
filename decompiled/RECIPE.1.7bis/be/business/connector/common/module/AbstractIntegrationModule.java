package be.business.connector.common.module;

import be.apb.gfddpp.common.utils.JaxContextCentralizer;
import be.business.connector.common.ApplicationConfig;
import be.business.connector.core.ehealth.services.KgssService;
import be.business.connector.core.ehealth.services.KgssServiceImpl;
import be.business.connector.core.exceptions.IntegrationModuleException;
import be.business.connector.core.technical.connector.utils.Crypto;
import be.business.connector.core.utils.ETKHelper;
import be.business.connector.core.utils.EncryptionUtils;
import be.business.connector.core.utils.Exceptionutils;
import be.business.connector.core.utils.I18nHelper;
import be.business.connector.core.utils.IOUtils;
import be.business.connector.core.utils.LoggingUtil;
import be.business.connector.core.utils.MapNamespaceContext;
import be.business.connector.core.utils.MarshallerHelper;
import be.business.connector.core.utils.MessageDumper;
import be.business.connector.core.utils.PropertyHandler;
import be.business.connector.core.utils.Qualities;
import be.business.connector.core.utils.STSHelper;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.service.kgss.domain.KeyResult;
import be.ehealth.technicalconnector.utils.ConnectorCryptoUtils;
import be.fgov.ehealth.etee.crypto.decrypt.DataUnsealer;
import be.fgov.ehealth.etee.crypto.decrypt.UnsealedData;
import be.fgov.ehealth.etee.crypto.encrypt.DataSealer;
import be.fgov.ehealth.etee.crypto.encrypt.EncryptionToken;
import be.fgov.ehealth.etee.crypto.status.CryptoResult;
import be.fgov.ehealth.etee.crypto.status.CryptoResultException;
import be.fgov.ehealth.etee.crypto.status.NotificationError;
import be.fgov.ehealth.etee.crypto.status.NotificationWarning;
import be.fgov.ehealth.etee.kgss._1_0.protocol.GetKeyRequestContent;
import be.fgov.ehealth.etee.kgss._1_0.protocol.GetKeyResponseContent;
import be.fgov.ehealth.technicalconnector.signature.AdvancedElectronicSignatureEnumeration;
import be.fgov.ehealth.technicalconnector.signature.SignatureBuilder;
import be.fgov.ehealth.technicalconnector.signature.SignatureBuilderFactory;
import be.fgov.ehealth.technicalconnector.signature.domain.SignatureVerificationError;
import be.fgov.ehealth.technicalconnector.signature.domain.SignatureVerificationResult;
import be.recipe.services.executor.ArchiveStandard;
import be.recipe.services.executor.GetPrescriptionForExecutorResultSealed;
import be.recipe.services.executor.ID;
import be.recipe.services.executor.MessageText;
import be.recipe.services.executor.Properties;
import be.recipe.services.executor.Property;
import be.recipe.services.executor.RuleId;
import be.recipe.services.executor.RuleMessage;
import be.recipe.services.executor.TimestampedPrescription;
import be.recipe.services.executor.ValidationWarning;
import be.recipe.services.executor.ValidationWarnings;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.Key;
import java.security.KeyStore;
import java.security.MessageDigest;
import java.security.Security;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.NamespaceContext;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.JoinPoint.StaticPart;
import org.aspectj.runtime.reflect.Factory;
import org.bouncycastle.asn1.cms.Attribute;
import org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.cms.DefaultCMSSignatureAlgorithmNameGenerator;
import org.bouncycastle.cms.SignerInformationVerifier;
import org.bouncycastle.cms.bc.BcRSASignerInfoVerifierBuilder;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.operator.DefaultDigestAlgorithmIdentifierFinder;
import org.bouncycastle.operator.DefaultSignatureAlgorithmIdentifierFinder;
import org.bouncycastle.operator.bc.BcDigestCalculatorProvider;
import org.bouncycastle.tsp.TimeStampResponse;
import org.bouncycastle.tsp.TimeStampToken;
import org.bouncycastle.tsp.TimeStampTokenInfo;
import org.bouncycastle.util.encoders.Base64;
import org.perf4j.aop.Profiled;
import org.perf4j.log4j.aop.TimingAspect;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public abstract class AbstractIntegrationModule {
   private static final Logger LOG;
   public static long TIME_KGSS_CALL;
   public static final String EHEALTH_SUCCESS_CODE_100 = "100";
   public static final String EHEALTH_SUCCESS_CODE_200 = "200";
   public static final String EHEALTH_SUCCESS_CODE_300 = "300";
   public static final String EHEALTH_SUCCESS_CODE_400 = "400";
   public static final String EHEALTH_SUCCESS_CODE_500 = "500";
   public static final int PATTERN_LENGTH = 12;
   public static final String RID_PATTERN = "BE(P|K|N)(P|0|1|2|3|4|5|6|7|8|9)[(0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9 | A | B | C | D | E | F | G | H | J | K | L | M | N | P | Q | R | S | T | V | W | X | Y | Z)]{8}";
   private final Pattern ridPattern = Pattern.compile("BE(P|K|N)(P|0|1|2|3|4|5|6|7|8|9)[(0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9 | A | B | C | D | E | F | G | H | J | K | L | M | N | P | Q | R | S | T | V | W | X | Y | Z)]{8}");
   protected DataUnsealer dataUnsealer = null;
   private DataSealer oldDataSealer = null;
   private DataUnsealer oldDataUnsealer = null;
   private ETKHelper etkHelper;
   private Key symmKey = null;
   private CacheManager cacheManager;
   private Cache kgssCache;
   private Cache etkCache;
   private final KgssService kgssService = KgssServiceImpl.getInstance();
   private static final String VERSION_1 = "v1";
   private static final String VERSION_2 = "v2";
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
      LOG = Logger.getLogger(AbstractIntegrationModule.class);
      TIME_KGSS_CALL = 0L;
   }

   public AbstractIntegrationModule() throws IntegrationModuleException {
      ApplicationConfig.getInstance().assertInitialized();
      this.init();
   }

   protected void init() throws IntegrationModuleException {
      try {
         LOG.info("Init abstractIntegrationModule!");
         LoggingUtil.initLog4J(this.getPropertyHandler());
         this.getJaxContextCentralizer().addContext(GetKeyRequestContent.class);
         this.getJaxContextCentralizer().addContext(GetKeyResponseContent.class);
         Security.addProvider(new BouncyCastleProvider());
         MessageDumper.getInstance().init(this.getPropertyHandler());
         Thread.currentThread().setContextClassLoader(this.getClass().getClassLoader());
         System.setProperty("javax.xml.soap.SOAPFactory", "com.sun.xml.messaging.saaj.soap.ver1_1.SOAPFactory1_1Impl");
         if (LOG.isDebugEnabled()) {
            LOG.debug("Curdir : " + (new File(".")).getCanonicalPath());
            LOG.debug("Support P12 keystores : " + KeyStore.getInstance("PKCS12"));
         }

         this.initCaching();
         this.initEncryption();
         LOG.info("End Init abstractIntegrationModule!");
      } catch (Throwable var2) {
         LOG.error("Exception in init abstractIntegrationModule: ", var2);
         Exceptionutils.errorHandler(var2);
      }

   }

   private void initCaching() {
      LOG.info("INIT CACHE MANAGER");
      URL url = this.getClass().getResource("/cache/config/ehcache.xml");
      this.cacheManager = CacheManager.newInstance(url);
      LOG.info("DOES KGSS CACHE EXIST?");
      this.kgssCache = this.cacheManager.getCache("KGSS");
      if (this.kgssCache == null) {
         LOG.info("NEW KGSS CACHE");
         this.kgssCache = new Cache("KGSS", 0, false, false, 0L, 0L);
         this.cacheManager.addCache(this.kgssCache);
      }

      LOG.info("DOES ETK CACHE EXIST?");
      this.etkCache = this.cacheManager.getCache("ETK");
      if (this.etkCache == null) {
         LOG.info("NEW ETK CACHE");
         this.etkCache = new Cache("ETK", 0, false, false, 0L, 0L);
         this.cacheManager.addCache(this.etkCache);
      }

   }

   public void initEncryption() throws IntegrationModuleException {
      try {
         LOG.info("Init the encryption - create symmKey");
         this.symmKey = this.getEncryptionUtils().generateSecretKey();
         if (this.getEncryptionUtils().getOldKeyStore() != null) {
            this.oldDataSealer = this.getEncryptionUtils().initOldSealing();
            this.oldDataUnsealer = this.getEncryptionUtils().initOldUnSealing();
         }

         LOG.info("Init the encryption - init etkHelper");
         this.etkHelper = new ETKHelper(this.getPropertyHandler(), this.getEncryptionUtils());
      } catch (Throwable var2) {
         LOG.error("Exception occured when initializing the encryption util: ", var2);
         Exceptionutils.errorHandler(var2, "error.initialization");
      }

   }

   protected boolean hasPersonalEtk() {
      return true;
   }

   @Profiled(
      logFailuresSeparately = true,
      tag = "0.AbstractIntegrationModule#sealRequest",
      logger = "org.perf4j.TimingLogger_Common"
   )
   protected synchronized byte[] sealRequest(EncryptionToken paramEncryptionToken, byte[] paramArrayOfByte) throws IntegrationModuleException {
      JoinPoint var5 = Factory.makeJP(ajc$tjp_0, this, this, paramEncryptionToken, paramArrayOfByte);
      TimingAspect var10000 = TimingAspect.aspectOf();
      Object[] var6 = new Object[]{this, paramEncryptionToken, paramArrayOfByte, var5};
      ProceedingJoinPoint var10001 = (new AbstractIntegrationModule$AjcClosure1(var6)).linkClosureAndJoinPoint(69648);
      Annotation var10002 = ajc$anno$0;
      if (var10002 == null) {
         var10002 = ajc$anno$0 = AbstractIntegrationModule.class.getDeclaredMethod("sealRequest", EncryptionToken.class, byte[].class).getAnnotation(Profiled.class);
      }

      return (byte[])var10000.doPerfLogging(var10001, (Profiled)var10002);
   }

   @Profiled(
      logFailuresSeparately = true,
      tag = "0.AbstractIntegrationModule#unsealRequest",
      logger = "org.perf4j.TimingLogger_Common"
   )
   protected byte[] unsealRequest(byte[] message) throws IntegrationModuleException {
      JoinPoint var3 = Factory.makeJP(ajc$tjp_1, this, this, message);
      TimingAspect var10000 = TimingAspect.aspectOf();
      Object[] var4 = new Object[]{this, message, var3};
      ProceedingJoinPoint var10001 = (new AbstractIntegrationModule$AjcClosure3(var4)).linkClosureAndJoinPoint(69648);
      Annotation var10002 = ajc$anno$1;
      if (var10002 == null) {
         var10002 = ajc$anno$1 = AbstractIntegrationModule.class.getDeclaredMethod("unsealRequest", byte[].class).getAnnotation(Profiled.class);
      }

      return (byte[])var10000.doPerfLogging(var10001, (Profiled)var10002);
   }

   protected byte[] unsealNotif(byte[] message) throws IntegrationModuleException {
      return Crypto.unseal(message);
   }

   protected byte[] unsealNotifOld(byte[] message) {
      CryptoResult<UnsealedData> result = this.getOldDataUnsealer().unseal(message);
      if (result != null && result.hasData()) {
         if (result.hasErrors()) {
            Iterator var4 = result.getErrors().iterator();

            while(var4.hasNext()) {
               NotificationError error = (NotificationError)var4.next();
               LOG.error(error.name());
            }

            var4 = result.getWarnings().iterator();

            while(var4.hasNext()) {
               NotificationWarning warning = (NotificationWarning)var4.next();
               LOG.error(warning.name());
            }

            if (result.getFatal() != null) {
               LOG.error(result.getFatal().getErrorMessage());
            }
         }

         InputStream unsealedDataStream = ((UnsealedData)result.getData()).getContent();
         byte[] unsealedData = IOUtils.getBytes(unsealedDataStream);
         return unsealedData;
      } else {
         return null;
      }
   }

   protected byte[] unsealNotiffeed(byte[] message) throws IntegrationModuleException {
      byte[] unsealedNotification = null;
      boolean calledUnsealNotifOld = false;

      try {
         LOG.debug("Start unseal notification: " + org.apache.commons.io.IOUtils.toString(message, "UTF-8"));
         unsealedNotification = Crypto.unseal(message);
         if (unsealedNotification != null) {
            return unsealedNotification;
         }

         if (this.getOldDataUnsealer() != null) {
            LOG.debug("Unseal notification was null. Start unseal notification with old keystore: " + Arrays.toString(message));
            calledUnsealNotifOld = true;
            unsealedNotification = this.unsealNotifOld(message);
            if (unsealedNotification != null) {
               return this.unsealNotifOld(message);
            }
         } else {
            LOG.debug("OldDataUnsealer is null.");
         }
      } catch (Throwable var7) {
         LOG.error("Exception occured with unsealing notification: ", var7);
         if (calledUnsealNotifOld) {
            if (var7 instanceof CryptoResultException && var7.getMessage().contains("There is no data available")) {
               return null;
            }

            Exceptionutils.errorHandler(var7, "error.data.unseal");
         } else {
            try {
               LOG.debug("Exception occured with unsealing notification. Trying to unseal notification with old keystore: " + Arrays.toString(message));
               unsealedNotification = this.unsealNotifOld(message);
            } catch (Throwable var6) {
               if (var7 instanceof CryptoResultException && var7.getMessage().contains("There is no data available")) {
                  return null;
               }

               Exceptionutils.errorHandler(var6, "error.data.unseal");
            }
         }
      }

      if (unsealedNotification == null) {
         throw new IntegrationModuleException(I18nHelper.getLabel("error.data.unseal"));
      } else {
         return unsealedNotification;
      }
   }

   @Profiled(
      logFailuresSeparately = true,
      tag = "0.AbstractIntegrationModule#unsealPrescriptionForUnknown",
      logger = "org.perf4j.TimingLogger_Common"
   )
   protected byte[] unsealPrescriptionForUnknown(KeyResult key, byte[] protectedMessage) throws IntegrationModuleException {
      JoinPoint var5 = Factory.makeJP(ajc$tjp_2, this, this, key, protectedMessage);
      TimingAspect var10000 = TimingAspect.aspectOf();
      Object[] var6 = new Object[]{this, key, protectedMessage, var5};
      ProceedingJoinPoint var10001 = (new AbstractIntegrationModule$AjcClosure5(var6)).linkClosureAndJoinPoint(69648);
      Annotation var10002 = ajc$anno$2;
      if (var10002 == null) {
         var10002 = ajc$anno$2 = AbstractIntegrationModule.class.getDeclaredMethod("unsealPrescriptionForUnknown", KeyResult.class, byte[].class).getAnnotation(Profiled.class);
      }

      return (byte[])var10000.doPerfLogging(var10001, (Profiled)var10002);
   }

   protected KeyResult getKeyFromKgss(String keyId) throws IntegrationModuleException {
      return this.getKeyFromKgss(keyId, (byte[])null);
   }

   @Profiled(
      logFailuresSeparately = true,
      tag = "0.AbstractIntegrationModule#getKeyFromKgss",
      logger = "org.perf4j.TimingLogger_Common"
   )
   public KeyResult getKeyFromKgss(String keyId, byte[] myEtk) throws IntegrationModuleException {
      JoinPoint var9 = Factory.makeJP(ajc$tjp_3, this, this, keyId, myEtk);
      TimingAspect var10000 = TimingAspect.aspectOf();
      Object[] var10 = new Object[]{this, keyId, myEtk, var9};
      ProceedingJoinPoint var10001 = (new AbstractIntegrationModule$AjcClosure7(var10)).linkClosureAndJoinPoint(69648);
      Annotation var10002 = ajc$anno$3;
      if (var10002 == null) {
         var10002 = ajc$anno$3 = AbstractIntegrationModule.class.getDeclaredMethod("getKeyFromKgss", String.class, byte[].class).getAnnotation(Profiled.class);
      }

      return (KeyResult)var10000.doPerfLogging(var10001, (Profiled)var10002);
   }

   protected Key getSymmKey() {
      return this.symmKey;
   }

   public EncryptionUtils getEncryptionUtils() {
      return EncryptionUtils.getInstance();
   }

   public PropertyHandler getPropertyHandler() {
      return PropertyHandler.getInstance();
   }

   public void setOldDataUnsealer(DataUnsealer oldDataUnsealer) {
      this.oldDataUnsealer = oldDataUnsealer;
   }

   public DataUnsealer getOldDataUnsealer() {
      return this.oldDataUnsealer;
   }

   public void setOldDataSealer(DataSealer oldDataSealer) {
      this.oldDataSealer = oldDataSealer;
   }

   public DataSealer getOldDataSealer() {
      return this.oldDataSealer;
   }

   protected ETKHelper getEtkHelper() {
      return this.etkHelper;
   }

   public void setDataUnsealer(DataUnsealer dataUnsealer) {
      this.dataUnsealer = dataUnsealer;
   }

   public JaxContextCentralizer getJaxContextCentralizer() {
      return JaxContextCentralizer.getInstance();
   }

   public CacheManager getCacheManager() {
      return this.cacheManager;
   }

   public void setCacheManager(CacheManager cacheManager) {
      this.cacheManager = cacheManager;
   }

   public Cache getKgssCache() {
      return this.kgssCache;
   }

   public void setKgssCache(Cache kgssCache) {
      this.kgssCache = kgssCache;
   }

   public Cache getEtkCache() {
      return this.etkCache;
   }

   public void setEtkCache(Cache etkCache) {
      this.etkCache = etkCache;
   }

   protected byte[] unsealWithSymKey(GetPrescriptionForExecutorResultSealed result, KeyResult key, String identifier, String type) throws IntegrationModuleException {
      try {
         String timestampedPrescriptionVersion = "v2";
         TimestampedPrescription tp = null;

         byte[] sealedPrescription;
         try {
            MarshallerHelper<TimestampedPrescription, TimestampedPrescription> helper = new MarshallerHelper(TimestampedPrescription.class, TimestampedPrescription.class);
            tp = (TimestampedPrescription)helper.unmarsh(result.getPrescription());
            sealedPrescription = tp.getPrescriptionWithSecurityToken().getPrescriptionSealed();
         } catch (Exception var9) {
            LOG.debug("Prescription is version 1: ", var9);
            timestampedPrescriptionVersion = "v1";
            sealedPrescription = result.getPrescription();
         }

         byte[] unsealedPrescription = this.unsealPrescriptionForUnknown(key, sealedPrescription);
         unsealedPrescription = IOUtils.decompress(unsealedPrescription);
         this.archivePrescription(unsealedPrescription, result, sealedPrescription, timestampedPrescriptionVersion, tp, key, identifier, type);
         return unsealedPrescription;
      } catch (TechnicalConnectorException | XPathExpressionException | IOException var10) {
         Exceptionutils.errorHandler(var10, "error.data.uncompression");
         return null;
      }
   }

   private void archivePrescription(byte[] unsealedPrescription, GetPrescriptionForExecutorResultSealed result, byte[] sealedPrescription, String timestampedPrescriptionVersion, TimestampedPrescription tp, KeyResult key, String identifier, String type) throws IOException, IntegrationModuleException, TechnicalConnectorException, XPathExpressionException {
      boolean archivePrescription = Boolean.parseBoolean(this.getPropertyHandler().getProperty("ArchivePrescription-xml", "false"));
      ID prescriberFromKmehr = this.getPrescriberIdFromKmehr(new ByteArrayInputStream(unsealedPrescription));
      ArchiveStandard archiveStandard = new ArchiveStandard();
      if (Boolean.parseBoolean(this.getPropertyHandler().getProperty("validate.befor.archiving", "true"))) {
         this.validatePrescriber(tp, archiveStandard, prescriberFromKmehr);
         if ("v2".equalsIgnoreCase(timestampedPrescriptionVersion)) {
            this.validateXadesT(result, archiveStandard, SignatureVerificationError.CERTIFICATE_CHAIN_COULD_NOT_BE_VERIFIED);
         } else {
            this.validateTimeStamping(result, archiveStandard);
         }
      }

      if (archivePrescription) {
         String archivingPath = this.getArchivedFilePath(result.getRid());
         MarshallerHelper<ArchiveStandard, ArchiveStandard> helper = new MarshallerHelper(ArchiveStandard.class, ArchiveStandard.class);
         archiveStandard = this.getArchiveStandard(result, unsealedPrescription, sealedPrescription, timestampedPrescriptionVersion, tp, key, identifier, type, prescriberFromKmehr, archiveStandard);
         helper.wrtiePrescriptionToFile(helper.toXMLByteArray(archiveStandard), archivingPath);
      }

   }

   protected String getArchivedFilePath(String rid) {
      String archivingPath = this.getPropertyHandler().getProperty("ArchivePrescriptionDirectory");
      archivingPath = archivingPath == null ? null : archivingPath.trim();
      if (archivingPath != null) {
         if (archivingPath.endsWith("/")) {
            archivingPath = archivingPath.concat(rid);
         } else {
            archivingPath = archivingPath.concat("/").concat(rid);
         }

         archivingPath = archivingPath.concat(".xml");
      }

      return archivingPath;
   }

   public byte[] getArchivedPrescription(String archivedFilePath, String... options) throws IntegrationModuleException {
      try {
         return Files.readAllBytes(Paths.get(archivedFilePath));
      } catch (Throwable var4) {
         LOG.warn("Could not read file [" + archivedFilePath + "].", var4);
         throw new IntegrationModuleException(I18nHelper.getLabel("error.archive.file", options));
      }
   }

   public XMLGregorianCalendar getCurrentXMLGregorianCalendar() {
      XMLGregorianCalendar xgcal = null;

      try {
         GregorianCalendar gcal = new GregorianCalendar();
         gcal.setTimeInMillis(System.currentTimeMillis());
         xgcal = DatatypeFactory.newInstance().newXMLGregorianCalendar(gcal);
      } catch (DatatypeConfigurationException var3) {
         LOG.error("Error creating a xml gregorian calendat!! ", var3);
      }

      return xgcal;
   }

   private ArchiveStandard getArchiveStandard(GetPrescriptionForExecutorResultSealed result, byte[] unsealedPrescription, byte[] sealedPrescription, String timestampedPrescriptionVersion, TimestampedPrescription tp, KeyResult key, String identifier, String type, ID id, ArchiveStandard archiveStandard) {
      archiveStandard.setCreationDate(this.getCurrentXMLGregorianCalendar());
      if ("v1".equalsIgnoreCase(timestampedPrescriptionVersion)) {
         archiveStandard.setPrescriptionSealed(sealedPrescription);
         archiveStandard.setTimestampeId(result.getTimestampingId());
         archiveStandard.setPatientId(this.getID(result.getPatientId(), "SSIN", Qualities.CITIZEN.name()));
         archiveStandard.setPrescriberId(this.getID(result.getPrescriberId(), "NIHII", Qualities.DOCTOR.name()));
      } else {
         archiveStandard.setTimestampedPrescription(result.getPrescription());
         archiveStandard.setPatientId(tp.getPrescriptionWithSecurityToken().getPatientId());
         ID prescId = tp.getPrescriptionWithSecurityToken().getPrescriberId();
         prescId.setIdType(prescId.getIdType().replace("NIHHI", "NIHII"));
         archiveStandard.setPrescriberId(prescId);
      }

      archiveStandard.setExecutorId(this.getID(identifier, type, Qualities.PHARMACY.name()));
      archiveStandard.setPrescriptionPrescriberId(id);
      archiveStandard.setPrescriptionType(result.getPrescriptionType());
      archiveStandard.setRid(result.getRid());
      archiveStandard.setEncryptionKeyId(result.getEncryptionKeyId());
      archiveStandard.setEncryptionKey(key.getSecretKey().getEncoded());
      archiveStandard.setTimestampedPrescriptionVersion(timestampedPrescriptionVersion);
      archiveStandard.setUnsealedPrescription(unsealedPrescription);
      Properties properties = new Properties();
      properties.getProperty().add(this.getProperty("sdkVersion", this.getPropertyHandler().getProperty("sdk.version")));
      properties.getProperty().add(this.getProperty("SupportedPrescriptionVersion", this.getPropertyHandler().getProperty("supported.prescription.version")));
      properties.getProperty().add(this.getProperty("prescriptionVersion", this.getPropertyHandler().getProperty("prescription.version")));
      archiveStandard.setProperties(properties);
      return archiveStandard;
   }

   private Property getProperty(String key, String value) {
      Property property = new Property();
      property.setKey(key);
      property.setValue(value);
      return property;
   }

   private void validatePrescriber(TimestampedPrescription tp, ArchiveStandard archiveStandard, ID prescriberFromKmehr) throws IntegrationModuleException {
      if (tp != null) {
         try {
            byte[] samlToken = tp.getPrescriptionWithSecurityToken().getSecurityToken();
            Document doc = this.obtenerDocumentDeByte(samlToken);
            String nihiiInSamlToken = STSHelper.getNihii(doc.getDocumentElement());
            boolean ErrorOccurs = false;
            if (StringUtils.isNotBlank(prescriberFromKmehr.getValue()) && StringUtils.isNotBlank(nihiiInSamlToken)) {
               LOG.debug("NIHII found in the Saml Assertion is [" + nihiiInSamlToken + "], and the one in the kmehr message [" + prescriberFromKmehr.getValue() + "].");
               if (!prescriberFromKmehr.getValue().substring(0, 8).equals(nihiiInSamlToken.substring(0, 8))) {
                  ErrorOccurs = true;
               }
            } else {
               ErrorOccurs = true;
            }

            if (ErrorOccurs) {
               LOG.error("Prescriber mismach between kmehr and SAMLToken!! [" + prescriberFromKmehr.getValue() + "]-[" + nihiiInSamlToken + "]");
               archiveStandard.setValidationWarnings(this.getValidationWarnings(archiveStandard.getValidationWarnings(), "RECIPE", "1041", "ERROR", "error.validation.presciber.mismach", prescriberFromKmehr.getValue(), nihiiInSamlToken));
            }
         } catch (Throwable var8) {
            LOG.error("Problems validating the prescriber in the samlToken against the one in the Kmehr!!!", var8);
            archiveStandard.setValidationWarnings(this.getValidationWarnings(archiveStandard.getValidationWarnings(), "RECIPE", "1042", "ERROR", "error.validation.presciber"));
         }
      }

   }

   private Document obtenerDocumentDeByte(byte[] documentoXml) throws SAXException, ParserConfigurationException, IOException {
      DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
      factory.setNamespaceAware(true);
      DocumentBuilder builder = factory.newDocumentBuilder();
      return builder.parse(new ByteArrayInputStream(documentoXml));
   }

   private ID getPrescriberIdFromKmehr(InputStream is) throws XPathExpressionException {
      XPath xPath = XPathFactory.newInstance().newXPath();
      NamespaceContext nsCtx = new MapNamespaceContext(new String[]{"http://www.ehealth.fgov.be/standards/kmehr/schema/v1"});
      xPath.setNamespaceContext(nsCtx);
      InputSource inputSource = new InputSource(is);
      Document doc = (Document)xPath.evaluate("/", inputSource, XPathConstants.NODE);
      String idValue = xPath.evaluate("/ns1:kmehrmessage/ns1:header/ns1:sender/ns1:hcparty/ns1:id[@S='ID-HCPARTY' and @SV='1.0']/text()", doc);
      String type = xPath.evaluate("/ns1:kmehrmessage/ns1:header/ns1:sender/ns1:hcparty/ns1:cd[@S='CD-HCPARTY' and @SV='1.0']/text()", doc);
      ID id = new ID();
      id.setIdType("NIHII");
      id.setValue(idValue);
      id.setType(type);
      return id;
   }

   public SignatureVerificationResult validateXadesT(GetPrescriptionForExecutorResultSealed gpfers, ArchiveStandard archiveStandard, SignatureVerificationError... errors) throws TechnicalConnectorException {
      LOG.debug("Verify XadesT signature.");
      String toSignStringOrig = new String(gpfers.getPrescription());
      String toSignDoc = toSignStringOrig.substring(0, toSignStringOrig.indexOf("<ds:Signature")) + "</ns2:timestampedPrescription>";
      String signatureString = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" + toSignStringOrig.substring(toSignStringOrig.indexOf("<ds:Signature"), toSignStringOrig.indexOf("</ds:Signature>") + "</ds:Signature>".length());
      LOG.debug("To sign document [" + toSignDoc + "].");
      LOG.debug("Signature [" + signatureString + "].");
      SignatureBuilder builder1 = SignatureBuilderFactory.getSignatureBuilder(AdvancedElectronicSignatureEnumeration.XAdES_T);
      Map<String, Object> options = new HashMap();
      options.put("followNestedManifest", Boolean.TRUE);
      options.put("encapsulate", Boolean.TRUE);
      SignatureVerificationResult result = builder1.verify(toSignDoc.trim().getBytes(), signatureString.getBytes(Charset.forName("UTF-8")), options);
      SignatureVerificationError[] var13 = errors;
      int var12 = errors.length;

      SignatureVerificationError error;
      for(int var11 = 0; var11 < var12; ++var11) {
         error = var13[var11];
         result.getErrors().remove(error);
      }

      Iterator var14 = result.getErrors().iterator();

      while(var14.hasNext()) {
         error = (SignatureVerificationError)var14.next();
         LOG.warn("ERROR: " + error.getErrorName());
         ValidationWarnings warnings = this.getValidationWarnings(archiveStandard.getValidationWarnings(), "RECIPE", error.getErrorName(), "ERROR", "error.validation.signature", error.getMessage());
         archiveStandard.setValidationWarnings(warnings);
      }

      return result;
   }

   private ValidationWarnings getValidationWarnings(ValidationWarnings warnings, String ruleIDType, String ruleIdValue, String sevirity, String key, String... args) {
      List<ValidationWarning> validationWarnings = new ArrayList();
      ValidationWarning warning = new ValidationWarning();
      RuleId ruleID = new RuleId();
      ruleID.setIdType(ruleIDType);
      ruleID.setValue(ruleIdValue);
      warning.setRuleId(ruleID);
      RuleMessage ruleMessage = new RuleMessage();
      List<MessageText> messageTexts = new ArrayList();
      Map<String, String> labels = I18nHelper.getAllLanguagesLabels(key, args);
      Iterator var14 = labels.entrySet().iterator();

      while(var14.hasNext()) {
         Object entry = var14.next();
         Entry<String, String> pair = (Entry)entry;
         MessageText messageText = new MessageText();
         messageText.setLanguage((String)pair.getKey());
         messageText.setValue((String)pair.getValue());
         messageTexts.add(messageText);
      }

      ruleMessage.getMessageText().addAll(messageTexts);
      warning.setRuleMessage(ruleMessage);
      warning.setSeverity(sevirity);
      validationWarnings.add(warning);
      if (warnings == null) {
         warnings = new ValidationWarnings();
      }

      warnings.getValidationWarning().addAll(validationWarnings);
      return warnings;
   }

   private ID getID(String idValue, String... type) {
      ID id = new ID();
      id.setIdType(type[0]);
      if (type.length > 1) {
         id.setType(type[1]);
      }

      id.setValue(idValue);
      return id;
   }

   public void validateTimeStamping(GetPrescriptionForExecutorResultSealed result, ArchiveStandard aStandard) {
      try {
         TimeStampResponse response = new TimeStampResponse(Base64.decode(result.getTimestampingId().getBytes()));
         boolean isValid = true;
         TimeStampToken tsToken = response.getTimeStampToken();
         byte[] tokenDigestValue = tsToken.getTimeStampInfo().getMessageImprintDigest();
         String algo = tsToken.getTimeStampInfo().getHashAlgorithm().getAlgorithm().getId();
         byte[] calculatedDigest = ConnectorCryptoUtils.calculateDigest(algo, result.getPrescription());
         if (!MessageDigest.isEqual(calculatedDigest, tokenDigestValue)) {
            isValid = false;
         }

         Attribute scV1 = tsToken.getSignedAttributes().get(PKCSObjectIdentifiers.id_aa_signingCertificate);
         Attribute scV2 = tsToken.getSignedAttributes().get(PKCSObjectIdentifiers.id_aa_signingCertificateV2);
         ValidationWarnings warnings;
         if (scV1 == null && scV2 == null) {
            isValid = false;
            warnings = this.getValidationWarnings(aStandard.getValidationWarnings(), "RECIPE", "1038", "ERROR", "error.validation.missing.certificate");
            aStandard.setValidationWarnings(warnings);
         }

         if (scV1 != null && scV2 != null) {
            isValid = false;
            warnings = this.getValidationWarnings(aStandard.getValidationWarnings(), "RECIPE", "1039", "ERROR", "error.validation.conflicting.certificate.attribute");
            aStandard.setValidationWarnings(warnings);
         }

         if (isValid) {
            this.validateTimeStampToken(tsToken);
         }
      } catch (Throwable var12) {
         LOG.warn("Validation error: ", var12);
         ValidationWarnings warnings = this.getValidationWarnings(aStandard.getValidationWarnings(), "RECIPE", "1040", "ERROR", "error.validation.timestamp", var12.getMessage());
         aStandard.setValidationWarnings(warnings);
      }

   }

   private boolean validateTimeStampToken(TimeStampToken tsToken) throws Exception {
      boolean result = false;
      KeyStore keyStore = this.getEncryptionUtils().getTSAKeyStore();
      List<String> aliases = this.getEncryptionUtils().getTsaStoreAliases();
      if (aliases != null && keyStore != null) {
         TimeStampTokenInfo tsi = tsToken.getTimeStampInfo();
         LOG.info("GenTime:" + tsi.getGenTime());
         LOG.info("ImprintAlgOID:" + tsi.getMessageImprintAlgOID());
         LOG.info("Policy:" + tsi.getPolicy());
         LOG.info("HashAlgorithm:" + tsi.getHashAlgorithm().getAlgorithm().getId());
         boolean signatureValid = false;
         Exception lastException = null;
         Iterator var9 = aliases.iterator();

         while(var9.hasNext()) {
            String alias = (String)var9.next();

            try {
               X509Certificate ttsaCert = (X509Certificate)keyStore.getCertificate(alias);
               String t = ttsaCert.getSubjectX500Principal().getName("RFC1779");
               LOG.debug("Trying to validate timestamp against certificate with alias [" + alias + "] : [" + t + "]");
               X509CertificateHolder tokenSigner = new X509CertificateHolder(ttsaCert.getEncoded());
               SignerInformationVerifier verifier = (new BcRSASignerInfoVerifierBuilder(new DefaultCMSSignatureAlgorithmNameGenerator(), new DefaultSignatureAlgorithmIdentifierFinder(), new DefaultDigestAlgorithmIdentifierFinder(), new BcDigestCalculatorProvider())).build(tokenSigner);
               tsToken.validate(verifier);
               signatureValid = true;
               break;
            } catch (Exception var14) {
               lastException = var14;
            }
         }

         if (signatureValid) {
            result = true;
            LOG.debug("timestampToken is valid");
            return result;
         } else {
            result = false;
            throw new Exception("timestamp is not valid ", lastException);
         }
      } else {
         throw new IllegalStateException("keystore or aliases not initialised yet : aliases : [" + aliases + "] and keystore : [" + keyStore + "]");
      }
   }

   public void validateRid(String rid) throws IntegrationModuleException {
      Matcher matcher = this.ridPattern.matcher(rid);
      if (!matcher.find()) {
         LOG.error("Invalid RID was provided.");
         throw new IntegrationModuleException(I18nHelper.getLabel("error.rid.validation", new Object[]{rid}));
      }
   }

   // $FF: synthetic method
   static final byte[] sealRequest_aroundBody0(AbstractIntegrationModule ajc$this, EncryptionToken paramEncryptionToken, byte[] paramArrayOfByte, JoinPoint var3) {
      return Crypto.seal(paramEncryptionToken, paramArrayOfByte);
   }

   // $FF: synthetic method
   static final byte[] unsealRequest_aroundBody2(AbstractIntegrationModule ajc$this, byte[] message, JoinPoint var2) {
      return Crypto.unseal(message);
   }

   // $FF: synthetic method
   static final byte[] unsealPrescriptionForUnknown_aroundBody4(AbstractIntegrationModule ajc$this, KeyResult key, byte[] protectedMessage, JoinPoint var3) {
      return Crypto.unsealForUnknown(key, protectedMessage);
   }

   // $FF: synthetic method
   static final KeyResult getKeyFromKgss_aroundBody6(AbstractIntegrationModule ajc$this, String keyId, byte[] myEtk, JoinPoint var3) {
      KeyResult keyResult = null;

      try {
         if (ajc$this.getPropertyHandler().hasProperty("test_kgss_key")) {
            String part1 = ajc$this.getPropertyHandler().getProperty("test_kgss_key").split(";")[0];
            String part2 = ajc$this.getPropertyHandler().getProperty("test_kgss_key").split(";")[1];
            byte[] keyResponse = Base64.decode(part2);
            return new KeyResult(new SecretKeySpec(keyResponse, "AES"), part1);
         }

         keyResult = ajc$this.kgssService.retrieveKeyFromKgss(keyId.getBytes(), myEtk, ((EncryptionToken)ajc$this.etkHelper.getKGSS_ETK().get(0)).getEncoded());
      } catch (Throwable var11) {
         LOG.error("Exception in getKeyFromKgss abstractIntegrationModule: ", var11);
         Exceptionutils.errorHandler(var11);
      }

      return keyResult;
   }

   // $FF: synthetic method
   private static void ajc$preClinit() {
      Factory var0 = new Factory("AbstractIntegrationModule.java", AbstractIntegrationModule.class);
      ajc$tjp_0 = var0.makeSJP("method-execution", var0.makeMethodSig("24", "sealRequest", "be.business.connector.common.module.AbstractIntegrationModule", "be.fgov.ehealth.etee.crypto.encrypt.EncryptionToken:[B", "paramEncryptionToken:paramArrayOfByte", "be.business.connector.core.exceptions.IntegrationModuleException", "[B"), 205);
      ajc$tjp_1 = var0.makeSJP("method-execution", var0.makeMethodSig("4", "unsealRequest", "be.business.connector.common.module.AbstractIntegrationModule", "[B", "message", "be.business.connector.core.exceptions.IntegrationModuleException", "[B"), 217);
      ajc$tjp_2 = var0.makeSJP("method-execution", var0.makeMethodSig("4", "unsealPrescriptionForUnknown", "be.business.connector.common.module.AbstractIntegrationModule", "be.ehealth.technicalconnector.service.kgss.domain.KeyResult:[B", "key:protectedMessage", "be.business.connector.core.exceptions.IntegrationModuleException", "[B"), 303);
      ajc$tjp_3 = var0.makeSJP("method-execution", var0.makeMethodSig("1", "getKeyFromKgss", "be.business.connector.common.module.AbstractIntegrationModule", "java.lang.String:[B", "keyId:myEtk", "be.business.connector.core.exceptions.IntegrationModuleException", "be.ehealth.technicalconnector.service.kgss.domain.KeyResult"), 312);
   }
}
