package be.business.connector.core.utils;

import be.business.connector.core.domain.KgssIdentifierType;
import be.business.connector.core.ehealth.services.KeyDepotService;
import be.business.connector.core.ehealth.services.KeyDepotServiceImpl;
import be.business.connector.core.exceptions.IntegrationModuleException;
import be.business.connector.core.exceptions.IntegrationModuleRuntimeException;
import be.fgov.ehealth.etee.crypto.encrypt.EncryptionToken;
import be.fgov.ehealth.etee.crypto.encrypt.EncryptionTokenFactory;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.security.cert.CertificateExpiredException;
import java.security.cert.CertificateNotYetValidException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.JoinPoint.StaticPart;
import org.aspectj.runtime.reflect.Factory;
import org.perf4j.aop.Profiled;
import org.perf4j.log4j.aop.TimingAspect;

public class ETKHelper {
   private static final Logger LOG;
   private static final String RECIPE_ID = "0823257311";
   private static final String KGSS_ID = "0809394427";
   private static String PCDH_ID;
   private static final String MY_ETK_PROPERTY = "MY_ETK";
   private PropertyHandler propertyHandler;
   private EncryptionUtils encryptionUtils;
   private Map<String, EncryptionToken> etkCache = new HashMap();
   private Map<String, List<EncryptionToken>> etksCache = new HashMap();
   private KeyDepotService keyDepotService = KeyDepotServiceImpl.getInstance();
   // $FF: synthetic field
   private static int[] $SWITCH_TABLE$be$business$connector$core$domain$KgssIdentifierType;
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

   static {
      ajc$preClinit();
      LOG = Logger.getLogger(ETKHelper.class);
      PCDH_ID = "0406753266";
   }

   public ETKHelper(PropertyHandler propertyHandler, EncryptionUtils encryptionUtils) {
      this.propertyHandler = propertyHandler;
      this.encryptionUtils = encryptionUtils;
      this.init();
   }

   @Profiled(
      logFailuresSeparately = true,
      tag = "0.ETKHelper#getKGSS_ETK",
      logger = "org.perf4j.TimingLogger_Common"
   )
   public List<EncryptionToken> getKGSS_ETK() throws IntegrationModuleException {
      JoinPoint var1 = Factory.makeJP(ajc$tjp_0, this, this);
      TimingAspect var10000 = TimingAspect.aspectOf();
      Object[] var2 = new Object[]{this, var1};
      ProceedingJoinPoint var10001 = (new ETKHelper$AjcClosure1(var2)).linkClosureAndJoinPoint(69648);
      Annotation var10002 = ajc$anno$0;
      if (var10002 == null) {
         var10002 = ajc$anno$0 = ETKHelper.class.getDeclaredMethod("getKGSS_ETK").getAnnotation(Profiled.class);
      }

      return (List)var10000.doPerfLogging(var10001, (Profiled)var10002);
   }

   @Profiled(
      logFailuresSeparately = true,
      tag = "0.ETKHelper#getRecipe_ETK",
      logger = "org.perf4j.TimingLogger_Common"
   )
   public List<EncryptionToken> getRecipe_ETK() throws IntegrationModuleException {
      JoinPoint var1 = Factory.makeJP(ajc$tjp_1, this, this);
      TimingAspect var10000 = TimingAspect.aspectOf();
      Object[] var2 = new Object[]{this, var1};
      ProceedingJoinPoint var10001 = (new ETKHelper$AjcClosure3(var2)).linkClosureAndJoinPoint(69648);
      Annotation var10002 = ajc$anno$1;
      if (var10002 == null) {
         var10002 = ajc$anno$1 = ETKHelper.class.getDeclaredMethod("getRecipe_ETK").getAnnotation(Profiled.class);
      }

      return (List)var10000.doPerfLogging(var10001, (Profiled)var10002);
   }

   @Profiled(
      logFailuresSeparately = true,
      tag = "0.ETKHelper#getTIPSystem_ETK",
      logger = "org.perf4j.TimingLogger_Common"
   )
   public List<EncryptionToken> getTIPSystem_ETK(String tipSystemId) throws IntegrationModuleException {
      JoinPoint var3 = Factory.makeJP(ajc$tjp_2, this, this, tipSystemId);
      TimingAspect var10000 = TimingAspect.aspectOf();
      Object[] var4 = new Object[]{this, tipSystemId, var3};
      ProceedingJoinPoint var10001 = (new ETKHelper$AjcClosure5(var4)).linkClosureAndJoinPoint(69648);
      Annotation var10002 = ajc$anno$2;
      if (var10002 == null) {
         var10002 = ajc$anno$2 = ETKHelper.class.getDeclaredMethod("getTIPSystem_ETK", String.class).getAnnotation(Profiled.class);
      }

      return (List)var10000.doPerfLogging(var10001, (Profiled)var10002);
   }

   @Profiled(
      logFailuresSeparately = true,
      tag = "0.ETKHelper#getTIP_ETK",
      logger = "org.perf4j.TimingLogger_Common"
   )
   public List<EncryptionToken> getTIP_ETK(String tipId) throws IntegrationModuleException {
      JoinPoint var3 = Factory.makeJP(ajc$tjp_3, this, this, tipId);
      TimingAspect var10000 = TimingAspect.aspectOf();
      Object[] var4 = new Object[]{this, tipId, var3};
      ProceedingJoinPoint var10001 = (new ETKHelper$AjcClosure7(var4)).linkClosureAndJoinPoint(69648);
      Annotation var10002 = ajc$anno$3;
      if (var10002 == null) {
         var10002 = ajc$anno$3 = ETKHelper.class.getDeclaredMethod("getTIP_ETK", String.class).getAnnotation(Profiled.class);
      }

      return (List)var10000.doPerfLogging(var10001, (Profiled)var10002);
   }

   @Profiled(
      logFailuresSeparately = true,
      tag = "0.ETKHelper#getPCDH_ETK",
      logger = "org.perf4j.TimingLogger_Common"
   )
   public List<EncryptionToken> getPCDH_ETK() throws IntegrationModuleException {
      JoinPoint var1 = Factory.makeJP(ajc$tjp_4, this, this);
      TimingAspect var10000 = TimingAspect.aspectOf();
      Object[] var2 = new Object[]{this, var1};
      ProceedingJoinPoint var10001 = (new ETKHelper$AjcClosure9(var2)).linkClosureAndJoinPoint(69648);
      Annotation var10002 = ajc$anno$4;
      if (var10002 == null) {
         var10002 = ajc$anno$4 = ETKHelper.class.getDeclaredMethod("getPCDH_ETK").getAnnotation(Profiled.class);
      }

      return (List)var10000.doPerfLogging(var10001, (Profiled)var10002);
   }

   @Profiled(
      logFailuresSeparately = true,
      tag = "0.ETKHelper#getSystemETK",
      logger = "org.perf4j.TimingLogger_Common"
   )
   public List<EncryptionToken> getSystemETK() throws IntegrationModuleException {
      JoinPoint var9 = Factory.makeJP(ajc$tjp_5, this, this);
      TimingAspect var10000 = TimingAspect.aspectOf();
      Object[] var10 = new Object[]{this, var9};
      ProceedingJoinPoint var10001 = (new ETKHelper$AjcClosure11(var10)).linkClosureAndJoinPoint(69648);
      Annotation var10002 = ajc$anno$5;
      if (var10002 == null) {
         var10002 = ajc$anno$5 = ETKHelper.class.getDeclaredMethod("getSystemETK").getAnnotation(Profiled.class);
      }

      return (List)var10000.doPerfLogging(var10001, (Profiled)var10002);
   }

   public List<EncryptionToken> getEtks(KgssIdentifierType identifierType, String identifierValue) throws IntegrationModuleException {
      if (StringUtils.isBlank(identifierValue)) {
         throw new IntegrationModuleException(I18nHelper.getLabel("error.invalid.etk"));
      } else {
         return this.getEtks(identifierType, identifierValue, "");
      }
   }

   public List<EncryptionToken> getEtks(KgssIdentifierType identifierType, Long identifierValue, String applicationid) throws IntegrationModuleException {
      if (identifierType != null) {
         switch($SWITCH_TABLE$be$business$connector$core$domain$KgssIdentifierType()[identifierType.ordinal()]) {
         case 1:
            return this.getEtks(KgssIdentifierType.CBE, longToString(identifierValue, 10), applicationid);
         case 2:
            return this.getEtks(KgssIdentifierType.SSIN, longToString(identifierValue, 11), applicationid);
         case 3:
         default:
            break;
         case 4:
            return this.getEtks(KgssIdentifierType.NIHII_PHARMACY, longToString(identifierValue, 8), applicationid);
         case 5:
            return this.getEtks(KgssIdentifierType.NIHII_HOSPITAL, longToString(identifierValue, 8), applicationid);
         }
      }

      return this.getEtks(KgssIdentifierType.NIHII, longToString(identifierValue / 1000L, 8), applicationid);
   }

   private List<EncryptionToken> getEtks(KgssIdentifierType identifierType, String identifierValue, String application) throws IntegrationModuleException {
      String etkCacheId = identifierType + "/" + identifierValue + "/" + application;
      if (this.etkCache.containsKey(etkCacheId)) {
         LOG.info("ETK retrieved from the cache : " + etkCacheId);
         return (List)this.etksCache.get(etkCacheId);
      } else {
         List<EncryptionToken> encryptionTokens = this.getEtksFromDepot(identifierType, identifierValue, application);
         this.etksCache.put(etkCacheId, encryptionTokens);
         return encryptionTokens;
      }
   }

   private List<EncryptionToken> getEtksFromDepot(KgssIdentifierType identifierType, String identifierValue, String application) throws IntegrationModuleException {
      try {
         List<EncryptionToken> encryptiontokens = this.keyDepotService.retrieveEtk(identifierType, identifierValue, application);
         return encryptiontokens;
      } catch (Throwable var5) {
         Exceptionutils.errorHandler(var5);
         return null;
      }
   }

   public static String longToString(Long id, int numberOfDigits) {
      if (id == null) {
         return null;
      } else {
         StringBuilder buffer = new StringBuilder(Long.toString(id));
         int delta = numberOfDigits - buffer.length();
         if (delta == 0) {
            return buffer.toString();
         } else if (delta < 0) {
            throw new IllegalArgumentException("numberOfDigits < input length");
         } else {
            while(delta > 0) {
               buffer.insert(0, "0");
               --delta;
            }

            return buffer.toString();
         }
      }
   }

   public static String subString(String id, int numberOfDigits) {
      if (StringUtils.isBlank(id)) {
         return null;
      } else {
         StringBuilder buffer = new StringBuilder(id);
         int delta = numberOfDigits - buffer.length();
         if (delta == 0) {
            return buffer.toString();
         } else if (delta < 0) {
            throw new IllegalArgumentException("numberOfDigits < input length");
         } else {
            while(delta > 0) {
               buffer.insert(0, "0");
               --delta;
            }

            return buffer.toString();
         }
      }
   }

   private void init() {
      if (this.propertyHandler.hasProperty("default.pcdh.id")) {
         String tmp = this.propertyHandler.getProperty("default.pcdh.id");
         if (!StringUtils.equals("", tmp)) {
            PCDH_ID = tmp;
         }
      }

   }

   // $FF: synthetic method
   static int[] $SWITCH_TABLE$be$business$connector$core$domain$KgssIdentifierType() {
      int[] var10000 = $SWITCH_TABLE$be$business$connector$core$domain$KgssIdentifierType;
      if (var10000 != null) {
         return var10000;
      } else {
         int[] var0 = new int[KgssIdentifierType.values().length];

         try {
            var0[KgssIdentifierType.CBE.ordinal()] = 1;
         } catch (NoSuchFieldError var5) {
         }

         try {
            var0[KgssIdentifierType.NIHII.ordinal()] = 3;
         } catch (NoSuchFieldError var4) {
         }

         try {
            var0[KgssIdentifierType.NIHII_HOSPITAL.ordinal()] = 5;
         } catch (NoSuchFieldError var3) {
         }

         try {
            var0[KgssIdentifierType.NIHII_PHARMACY.ordinal()] = 4;
         } catch (NoSuchFieldError var2) {
         }

         try {
            var0[KgssIdentifierType.SSIN.ordinal()] = 2;
         } catch (NoSuchFieldError var1) {
         }

         $SWITCH_TABLE$be$business$connector$core$domain$KgssIdentifierType = var0;
         return var0;
      }
   }

   // $FF: synthetic method
   static final List getKGSS_ETK_aroundBody0(ETKHelper ajc$this, JoinPoint var1) {
      return ajc$this.getEtks(KgssIdentifierType.CBE, "0809394427", "KGSS");
   }

   // $FF: synthetic method
   static final List getRecipe_ETK_aroundBody2(ETKHelper ajc$this, JoinPoint var1) {
      return ajc$this.getEtks(KgssIdentifierType.CBE, "0823257311", "");
   }

   // $FF: synthetic method
   static final List getTIPSystem_ETK_aroundBody4(ETKHelper ajc$this, String tipSystemId, JoinPoint var2) {
      return ajc$this.getEtks(KgssIdentifierType.CBE, tipSystemId, "TIPSYSTEM");
   }

   // $FF: synthetic method
   static final List getTIP_ETK_aroundBody6(ETKHelper ajc$this, String tipId, JoinPoint var2) {
      return ajc$this.getEtks(KgssIdentifierType.CBE, tipId, "");
   }

   // $FF: synthetic method
   static final List getPCDH_ETK_aroundBody8(ETKHelper ajc$this, JoinPoint var1) {
      return ajc$this.getEtks(KgssIdentifierType.CBE, PCDH_ID, "PCDH");
   }

   // $FF: synthetic method
   static final List getSystemETK_aroundBody10(ETKHelper ajc$this, JoinPoint var1) {
      String application = "";
      KgssIdentifierType identifierType = null;
      String identifierValue = "";
      if (ajc$this.propertyHandler.hasProperty("MY_ETK")) {
         String myETK = ajc$this.propertyHandler.getProperty("MY_ETK");
         if (myETK.indexOf(59) <= -1) {
            try {
               InputStream etkStream = IOUtils.getResourceAsStream(myETK);
               byte[] etk = IOUtils.getBytes(etkStream);
               List encryptionTokens = new ArrayList();
               EncryptionToken etkToken = EncryptionTokenFactory.getInstance().create(etk);
               encryptionTokens.add(etkToken);
               return encryptionTokens;
            } catch (Exception var17) {
               throw new IntegrationModuleException("Invalid ETK", var17);
            }
         }

         String[] etk = myETK.split(";");
         if (etk.length > 1) {
            identifierType = KgssIdentifierType.lookup(etk[0].toUpperCase());
            identifierValue = etk[1];
            if (etk.length >= 3) {
               application = etk[2];
            }
         }
      } else {
         X509Certificate certificate = ajc$this.encryptionUtils.getCertificate();
         if (certificate == null) {
            throw new IntegrationModuleRuntimeException(I18nHelper.getLabel("error.notfound.system.certificate"));
         }

         try {
            certificate.checkValidity();
         } catch (CertificateExpiredException var18) {
            throw new IntegrationModuleRuntimeException(I18nHelper.getLabel("error.expired.system.certificate"), var18);
         } catch (CertificateNotYetValidException var19) {
            throw new IntegrationModuleRuntimeException(I18nHelper.getLabel("error.invalid.system.certificate"), var19);
         }

         CertificateParser parser = new CertificateParser(certificate);
         identifierType = KgssIdentifierType.lookup(parser.getType());
         identifierValue = parser.getValue();
         application = parser.getApplication();
      }

      if (identifierType != null && identifierValue != null && !"".equals(identifierValue)) {
         return ajc$this.getEtks(identifierType, Long.valueOf(identifierValue), application);
      } else {
         throw new IntegrationModuleException(I18nHelper.getLabel("error.invalid.system.certificate"));
      }
   }

   // $FF: synthetic method
   private static void ajc$preClinit() {
      Factory var0 = new Factory("ETKHelper.java", ETKHelper.class);
      ajc$tjp_0 = var0.makeSJP("method-execution", var0.makeMethodSig("1", "getKGSS_ETK", "be.business.connector.core.utils.ETKHelper", "", "", "be.business.connector.core.exceptions.IntegrationModuleException", "java.util.List"), 48);
      ajc$tjp_1 = var0.makeSJP("method-execution", var0.makeMethodSig("1", "getRecipe_ETK", "be.business.connector.core.utils.ETKHelper", "", "", "be.business.connector.core.exceptions.IntegrationModuleException", "java.util.List"), 53);
      ajc$tjp_2 = var0.makeSJP("method-execution", var0.makeMethodSig("1", "getTIPSystem_ETK", "be.business.connector.core.utils.ETKHelper", "java.lang.String", "tipSystemId", "be.business.connector.core.exceptions.IntegrationModuleException", "java.util.List"), 58);
      ajc$tjp_3 = var0.makeSJP("method-execution", var0.makeMethodSig("1", "getTIP_ETK", "be.business.connector.core.utils.ETKHelper", "java.lang.String", "tipId", "be.business.connector.core.exceptions.IntegrationModuleException", "java.util.List"), 63);
      ajc$tjp_4 = var0.makeSJP("method-execution", var0.makeMethodSig("1", "getPCDH_ETK", "be.business.connector.core.utils.ETKHelper", "", "", "be.business.connector.core.exceptions.IntegrationModuleException", "java.util.List"), 68);
      ajc$tjp_5 = var0.makeSJP("method-execution", var0.makeMethodSig("1", "getSystemETK", "be.business.connector.core.utils.ETKHelper", "", "", "be.business.connector.core.exceptions.IntegrationModuleException", "java.util.List"), 73);
   }
}
