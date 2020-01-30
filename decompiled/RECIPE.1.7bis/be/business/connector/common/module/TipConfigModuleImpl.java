package be.business.connector.common.module;

import be.apb.gfddpp.common.exceptions.GFDDPPException;
import be.apb.gfddpp.common.utils.JaxContextCentralizer;
import be.apb.gfddpp.productfilter.ProductFilter;
import be.apb.standards.gfddpp.request.configuration.GetConfigurationRequestParameters;
import be.business.connector.common.ApplicationConfig;
import be.business.connector.common.StandaloneRequestorProvider;
import be.business.connector.core.exceptions.IntegrationModuleException;
import be.business.connector.core.technical.connector.utils.Crypto;
import be.business.connector.core.utils.ConfigUtils;
import be.business.connector.core.utils.ETKHelper;
import be.business.connector.core.utils.EncryptionUtils;
import be.business.connector.core.utils.I18nHelper;
import be.business.connector.core.utils.PropertyHandler;
import be.business.connector.core.utils.SealedProcessor;
import be.business.connector.projects.common.services.tipsystem.TipSystemServiceImpl;
import be.business.connector.projects.common.utils.ProductFilterSingleton;
import be.business.connector.projects.common.utils.SystemServicesUtils;
import be.ehealth.apb.gfddpp.services.tipsystem.RoutedSealedRequestType;
import be.ehealth.apb.gfddpp.services.tipsystem.RoutedSealedResponseType;
import be.fgov.ehealth.etee.crypto.encrypt.EncryptionToken;
import be.gfddpp.services.systemservices.v2.SystemServices;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.lang.annotation.Annotation;
import javax.xml.datatype.XMLGregorianCalendar;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.JoinPoint.StaticPart;
import org.aspectj.runtime.reflect.Factory;
import org.perf4j.aop.Profiled;
import org.perf4j.log4j.aop.TimingAspect;

public class TipConfigModuleImpl implements TipConfigModule {
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

   static {
      ajc$preClinit();
      LOG = Logger.getLogger(TipConfigModuleImpl.class);
   }

   public TipConfigModuleImpl() throws IntegrationModuleException {
      ApplicationConfig.getInstance().assertInitialized();
   }

   @Profiled(
      logFailuresSeparately = true,
      tag = "TipConfigModuleImpl#getLatestProductFilter",
      logger = "org.perf4j.TimingLogger_Executor"
   )
   public void getLatestProductFilter() throws IntegrationModuleException {
      JoinPoint var16 = Factory.makeJP(ajc$tjp_0, this, this);
      TimingAspect var10000 = TimingAspect.aspectOf();
      Object[] var17 = new Object[]{this, var16};
      ProceedingJoinPoint var10001 = (new TipConfigModuleImpl$AjcClosure1(var17)).linkClosureAndJoinPoint(69648);
      Annotation var10002 = ajc$anno$0;
      if (var10002 == null) {
         var10002 = ajc$anno$0 = TipConfigModuleImpl.class.getDeclaredMethod("getLatestProductFilter").getAnnotation(Profiled.class);
      }

      var10000.doPerfLogging(var10001, (Profiled)var10002);
   }

   @Profiled(
      logFailuresSeparately = true,
      tag = "TipConfigModuleImpl#getLatestTipConfig",
      logger = "org.perf4j.TimingLogger_Executor"
   )
   public void getLatestTIPConfig() throws IntegrationModuleException {
      JoinPoint var1 = Factory.makeJP(ajc$tjp_1, this, this);
      TimingAspect var10000 = TimingAspect.aspectOf();
      Object[] var2 = new Object[]{this, var1};
      ProceedingJoinPoint var10001 = (new TipConfigModuleImpl$AjcClosure3(var2)).linkClosureAndJoinPoint(69648);
      Annotation var10002 = ajc$anno$1;
      if (var10002 == null) {
         var10002 = ajc$anno$1 = TipConfigModuleImpl.class.getDeclaredMethod("getLatestTIPConfig").getAnnotation(Profiled.class);
      }

      var10000.doPerfLogging(var10001, (Profiled)var10002);
   }

   @Profiled(
      logFailuresSeparately = true,
      tag = "TipConfigModuleImpl#getLatestSystemServices",
      logger = "org.perf4j.TimingLogger_Executor"
   )
   public void getLatestSystemServices() throws IntegrationModuleException {
      JoinPoint var16 = Factory.makeJP(ajc$tjp_2, this, this);
      TimingAspect var10000 = TimingAspect.aspectOf();
      Object[] var17 = new Object[]{this, var16};
      ProceedingJoinPoint var10001 = (new TipConfigModuleImpl$AjcClosure5(var17)).linkClosureAndJoinPoint(69648);
      Annotation var10002 = ajc$anno$2;
      if (var10002 == null) {
         var10002 = ajc$anno$2 = TipConfigModuleImpl.class.getDeclaredMethod("getLatestSystemServices").getAnnotation(Profiled.class);
      }

      var10000.doPerfLogging(var10001, (Profiled)var10002);
   }

   private ETKHelper getETKHelper() {
      return new ETKHelper(PropertyHandler.getInstance(), EncryptionUtils.getInstance());
   }

   // $FF: synthetic method
   static final void getLatestProductFilter_aroundBody0(TipConfigModuleImpl ajc$this, JoinPoint var1) {
      ApplicationConfig.getInstance().assertValidSession();
      LOG.info("Loading latest productFilter");
      PropertyHandler propertyHandler = PropertyHandler.getInstance();
      if (!propertyHandler.hasProperty("PRODUCT_FILTER_PATH")) {
         throw new IntegrationModuleException(I18nHelper.getLabel("error.get.product.filter.failed"));
      } else {
         String productFilterPath = propertyHandler.getProperty("PRODUCT_FILTER_PATH");
         String tipSystemId = null;
         if (propertyHandler.hasProperty("tipsystem.id")) {
            tipSystemId = propertyHandler.getProperty("tipsystem.id");
            XMLGregorianCalendar latestVersion = ConfigUtils.getLatestProductFilterVersion(productFilterPath);
            GetConfigurationRequestParameters productFilterReq = new GetConfigurationRequestParameters();
            productFilterReq.setVersion(latestVersion);
            productFilterReq.setNihiiPharmacyNumber(StandaloneRequestorProvider.getRequestorIdInformation());

            String productFilterReqXml;
            try {
               productFilterReqXml = JaxContextCentralizer.getInstance().toXml(GetConfigurationRequestParameters.class, productFilterReq);
            } catch (GFDDPPException var34) {
               throw new IntegrationModuleException(I18nHelper.getLabel("error.get.product.filter.failed"), var34);
            }

            LOG.debug("PRODUCTFILTER: " + productFilterReqXml);
            RoutedSealedRequestType productFilterSealedReq = new RoutedSealedRequestType();
            EncryptionToken etk = (EncryptionToken)ajc$this.getETKHelper().getTIPSystem_ETK(tipSystemId).get(0);
            Object var18 = null;

            byte[] Request;
            try {
               Request = SealedProcessor.createSealedSync(etk, productFilterReqXml, "productFilterReq");
            } catch (UnsupportedEncodingException var33) {
               throw new IntegrationModuleException(I18nHelper.getLabel("error.get.product.filter.failed"), var33);
            }

            productFilterSealedReq.setRequestParametersSealed(Request);
            byte[] xml = null;

            try {
               LOG.debug("************* Retrieve latest version of the product filter configuration ********************* ");
               RoutedSealedResponseType routedSealedResponseType = TipSystemServiceImpl.getInstance().getProductFilter(productFilterSealedReq);
               LOG.debug("************* Latest version retrieved of the product filter configuration ********************* ");
               xml = routedSealedResponseType.getSingleMessageSealed();
            } catch (Exception var32) {
               LOG.error(I18nHelper.getLabel("error.get.product.filter.failed"), var32);
            }

            if (xml != null && xml.length > 0) {
               String xmlDecrypted = new String(Crypto.unseal(xml));

               XMLGregorianCalendar currentVersion;
               File newFile;
               try {
                  ProductFilter productFilter = (ProductFilter)JaxContextCentralizer.getInstance().toObject(ProductFilter.class, xmlDecrypted);
                  currentVersion = productFilter.getVersion();
                  newFile = new File(productFilterPath + "/productfilter_v" + currentVersion.toXMLFormat().replace(':', '-') + ".xml");
                  FileUtils.writeByteArrayToFile(newFile, xmlDecrypted.getBytes());
               } catch (Exception var31) {
                  throw new IntegrationModuleException(I18nHelper.getLabel("error.get.product.filter.failed"));
               }

               if (latestVersion != null && latestVersion.toGregorianCalendar().after(currentVersion.toGregorianCalendar())) {
                  throw new IntegrationModuleException(I18nHelper.getLabel("error.validation.productfilter.version", new Object[]{newFile.getAbsolutePath()}));
               }
            } else {
               LOG.debug("Received null payload from product filter.");
            }

            ProductFilterSingleton pfs = ProductFilterSingleton.getInstance();
            pfs.setProductFilterXmlFile(ConfigUtils.getLatestProductFilterFile(productFilterPath));
            pfs.reloadCache();
            LOG.info("productFilter loaded");
         } else {
            throw new IntegrationModuleException(I18nHelper.getLabel("error.validation.tipsystem.id"));
         }
      }
   }

   // $FF: synthetic method
   static final void getLatestTIPConfig_aroundBody2(TipConfigModuleImpl ajc$this, JoinPoint var1) {
      ajc$this.getLatestProductFilter();
      ajc$this.getLatestSystemServices();
   }

   // $FF: synthetic method
   static final void getLatestSystemServices_aroundBody4(TipConfigModuleImpl ajc$this, JoinPoint var1) {
      ApplicationConfig.getInstance().assertValidSession();
      LOG.info("Loading latest systemServices");
      PropertyHandler propertyHandler = PropertyHandler.getInstance();
      if (!propertyHandler.hasProperty("SYSTEM_SERVICES_PATH")) {
         throw new IntegrationModuleException(I18nHelper.getLabel("error.get.system.services.failed"));
      } else {
         String systemServicesPath = propertyHandler.getProperty("SYSTEM_SERVICES_PATH");
         String tipSystemId = null;
         if (propertyHandler.hasProperty("tipsystem.id")) {
            tipSystemId = propertyHandler.getProperty("tipsystem.id");
            XMLGregorianCalendar latestVersion = ConfigUtils.getLatestSystemServicesVersion(systemServicesPath);
            GetConfigurationRequestParameters systemServicesReq = new GetConfigurationRequestParameters();
            systemServicesReq.setVersion(latestVersion);
            systemServicesReq.setNihiiPharmacyNumber(StandaloneRequestorProvider.getRequestorIdInformation());

            String systemServicesReqXml;
            try {
               systemServicesReqXml = JaxContextCentralizer.getInstance().toXml(GetConfigurationRequestParameters.class, systemServicesReq);
            } catch (GFDDPPException var34) {
               throw new IntegrationModuleException(I18nHelper.getLabel("error.get.system.services.failed"), var34);
            }

            LOG.debug("CM UNSEALED OUTGOING MESSAGE: " + systemServicesReqXml);
            EncryptionToken etk = (EncryptionToken)ajc$this.getETKHelper().getTIPSystem_ETK(tipSystemId).get(0);
            Object var16 = null;

            byte[] Request;
            try {
               Request = SealedProcessor.createSealedSync(etk, systemServicesReqXml, "systemServicesReq");
            } catch (UnsupportedEncodingException var33) {
               throw new IntegrationModuleException(I18nHelper.getLabel("error.get.system.services.failed"), var33);
            }

            RoutedSealedRequestType systemServicesSealedReq = new RoutedSealedRequestType();
            systemServicesSealedReq.setRequestParametersSealed(Request);
            byte[] xml = null;

            try {
               LOG.debug("************* Retrieve latest version of the system services configuration ********************* ");
               RoutedSealedResponseType routedSealedResponseType = TipSystemServiceImpl.getInstance().getSystemServices(systemServicesSealedReq);
               LOG.debug("************* Latest version of the system services configuration retrieved ********************* ");
               xml = routedSealedResponseType.getSingleMessageSealed();
            } catch (Exception var32) {
               LOG.error(I18nHelper.getLabel("error.get.system.services.failed"), var32);
            }

            if (xml != null && xml.length > 0) {
               byte[] xmlDecrypted = Crypto.unseal(xml);

               XMLGregorianCalendar currentVersion;
               File newFile;
               try {
                  SystemServices systemServices = (SystemServices)JaxContextCentralizer.getInstance().toObject(SystemServices.class, xmlDecrypted);
                  currentVersion = systemServices.getVersion();
                  newFile = new File(systemServicesPath + "/systemservices_v" + currentVersion.toXMLFormat().replace(':', '-') + ".xml");
                  FileUtils.writeByteArrayToFile(newFile, xmlDecrypted);
               } catch (Exception var31) {
                  throw new IntegrationModuleException(I18nHelper.getLabel("error.get.system.services.failed"));
               }

               if (latestVersion != null && latestVersion.toGregorianCalendar().after(currentVersion.toGregorianCalendar())) {
                  throw new IntegrationModuleException(I18nHelper.getLabel("error.validation.systemconfiguration.version", new Object[]{newFile.getAbsolutePath()}));
               }
            } else {
               LOG.debug("Received null payload from system services.");
            }

            SystemServicesUtils ssu = SystemServicesUtils.getInstance();
            ssu.setSystemServicesXmlFile(ConfigUtils.getLatestSystemServicesFile(systemServicesPath));
            ssu.reloadCache();
            LOG.info("SystemService loaded");
         } else {
            throw new IntegrationModuleException(I18nHelper.getLabel("error.validation.tipsystem.id"));
         }
      }
   }

   // $FF: synthetic method
   private static void ajc$preClinit() {
      Factory var0 = new Factory("TipConfigModuleImpl.java", TipConfigModuleImpl.class);
      ajc$tjp_0 = var0.makeSJP("method-execution", var0.makeMethodSig("1", "getLatestProductFilter", "be.business.connector.common.module.TipConfigModuleImpl", "", "", "be.business.connector.core.exceptions.IntegrationModuleException", "void"), 44);
      ajc$tjp_1 = var0.makeSJP("method-execution", var0.makeMethodSig("1", "getLatestTIPConfig", "be.business.connector.common.module.TipConfigModuleImpl", "", "", "be.business.connector.core.exceptions.IntegrationModuleException", "void"), 135);
      ajc$tjp_2 = var0.makeSJP("method-execution", var0.makeMethodSig("1", "getLatestSystemServices", "be.business.connector.common.module.TipConfigModuleImpl", "", "", "be.business.connector.core.exceptions.IntegrationModuleException", "void"), 146);
   }
}
