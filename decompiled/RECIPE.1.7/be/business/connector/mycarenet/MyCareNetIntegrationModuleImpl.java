package be.business.connector.mycarenet;

import be.apb.gfddpp.common.utils.JaxContextCentralizer;
import be.business.connector.common.ApplicationConfig;
import be.business.connector.common.module.AbstractIntegrationModule;
import org.taktik.connector.business.recipeprojects.core.exceptions.IntegrationModuleException;
import org.taktik.connector.business.recipeprojects.core.handlers.InsurabilityHandler;
import org.taktik.connector.business.recipeprojects.core.utils.Exceptionutils;
import be.business.connector.mycarenet.domain.Insurability;
import be.business.connector.mycarenet.services.InsurabilityConsultationServiceImpl;
import be.fgov.ehealth.insurability.protocol.v1.GetInsurabilityForPharmacistResponse;
import java.lang.annotation.Annotation;
import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.runtime.reflect.Factory;
import org.perf4j.aop.Profiled;
import org.perf4j.log4j.aop.TimingAspect;

public class MyCareNetIntegrationModuleImpl extends AbstractIntegrationModule implements MyCareNetIntegrationModule {
   private static final Logger LOG;
   // $FF: synthetic field
   private static final JoinPoint.StaticPart ajc$tjp_0;
   // $FF: synthetic field
   private static Annotation ajc$anno$0;
   // $FF: synthetic field
   private static final JoinPoint.StaticPart ajc$tjp_1;
   // $FF: synthetic field
   private static Annotation ajc$anno$1;

   static {
      ajc$preClinit();
      LOG = Logger.getLogger(MyCareNetIntegrationModuleImpl.class);
   }

   public MyCareNetIntegrationModuleImpl() throws IntegrationModuleException {
   }

   /** @deprecated */
   @Deprecated
   @Profiled(
      logFailuresSeparately = true,
      tag = "0.MyCareNet#getInsurability"
   )
   public String getInsurability(String userName, String password, String pharmacyHolder, String pharmacySSIN, String pharmcayNihii, String date, String type, String careReceiverSSIN, String careReceiverMutuality, String careReceiverRegNrWithMut, String packageName, String reference, String userReference) throws IntegrationModuleException {
      JoinPoint.StaticPart var10000 = ajc$tjp_0;
      Object[] var31 = new Object[]{userName, password, pharmacyHolder, pharmacySSIN, pharmcayNihii, date, type, careReceiverSSIN, careReceiverMutuality, careReceiverRegNrWithMut, packageName, reference, userReference};
      JoinPoint var30 = Factory.makeJP(var10000, this, this, (Object[])var31);
      TimingAspect var33 = TimingAspect.aspectOf();
      Object[] var32 = new Object[]{this, userName, password, pharmacyHolder, pharmacySSIN, pharmcayNihii, date, type, careReceiverSSIN, careReceiverMutuality, careReceiverRegNrWithMut, packageName, reference, userReference, var30};
      ProceedingJoinPoint var10001 = (new MyCareNetIntegrationModuleImpl$AjcClosure1(var32)).linkClosureAndJoinPoint(69648);
      Annotation var10002 = ajc$anno$0;
      if (ajc$anno$0 == null) {
         var10002 = ajc$anno$0 = MyCareNetIntegrationModuleImpl.class.getDeclaredMethod("getInsurability", String.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class).getAnnotation(Profiled.class);
      }

      return (String)var33.doPerfLogging(var10001, (Profiled)var10002);
   }

   @Profiled(
      logFailuresSeparately = true,
      tag = "0.MyCareNet#getInsurability"
   )
   public Insurability getInsurabilityForPharmacist(String userName, String password, String pharmacyHolder, String pharmacySSIN, String pharmcayNihii, String date, String type, String careReceiverSSIN, String careReceiverMutuality, String careReceiverRegNrWithMut, String packageName, String reference, String userReference) throws IntegrationModuleException {
      JoinPoint.StaticPart var10000 = ajc$tjp_1;
      Object[] var31 = new Object[]{userName, password, pharmacyHolder, pharmacySSIN, pharmcayNihii, date, type, careReceiverSSIN, careReceiverMutuality, careReceiverRegNrWithMut, packageName, reference, userReference};
      JoinPoint var30 = Factory.makeJP(var10000, this, this, (Object[])var31);
      TimingAspect var33 = TimingAspect.aspectOf();
      Object[] var32 = new Object[]{this, userName, password, pharmacyHolder, pharmacySSIN, pharmcayNihii, date, type, careReceiverSSIN, careReceiverMutuality, careReceiverRegNrWithMut, packageName, reference, userReference, var30};
      ProceedingJoinPoint var10001 = (new MyCareNetIntegrationModuleImpl$AjcClosure3(var32)).linkClosureAndJoinPoint(69648);
      Annotation var10002 = ajc$anno$1;
      if (ajc$anno$1 == null) {
         var10002 = ajc$anno$1 = MyCareNetIntegrationModuleImpl.class.getDeclaredMethod("getInsurabilityForPharmacist", String.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class).getAnnotation(Profiled.class);
      }

      return (Insurability)var33.doPerfLogging(var10001, (Profiled)var10002);
   }

   // $FF: synthetic method
   static final String getInsurability_aroundBody0(MyCareNetIntegrationModuleImpl ajc$this, String userName, String password, String pharmacyHolder, String pharmacySSIN, String pharmcayNihii, String date, String type, String careReceiverSSIN, String careReceiverMutuality, String careReceiverRegNrWithMut, String packageName, String reference, String userReference, JoinPoint var14) {
      ApplicationConfig.getInstance().assertValidSession();

      try {
         MyCareNetRequest myCareNet = new MyCareNetRequest();
         LOG.debug("******************** Call MyCarenet for insurability ******************** ");
         GetInsurabilityForPharmacistResponse ins = InsurabilityConsultationServiceImpl.getInstance().getInsurabilityForPharmacist(myCareNet.createRequest(userName, password, pharmacyHolder, pharmacySSIN, pharmcayNihii, date, type, careReceiverSSIN, careReceiverMutuality, careReceiverRegNrWithMut, packageName, reference, userReference));
         String insXml = null;
         if (ins != null) {
            insXml = JaxContextCentralizer.getInstance().toXml(GetInsurabilityForPharmacistResponse.class, ins);
            LOG.debug("******************** Call MyCarenet for insurability response: " + insXml + " ******************** ");
         }

         return insXml;
      } catch (Throwable var20) {
         Exceptionutils.errorHandler(var20);
         return null;
      }
   }

   // $FF: synthetic method
   static final Insurability getInsurabilityForPharmacist_aroundBody2(MyCareNetIntegrationModuleImpl ajc$this, String userName, String password, String pharmacyHolder, String pharmacySSIN, String pharmcayNihii, String date, String type, String careReceiverSSIN, String careReceiverMutuality, String careReceiverRegNrWithMut, String packageName, String reference, String userReference, JoinPoint var14) {
      ApplicationConfig.getInstance().assertValidSession();

      try {
         MyCareNetRequest myCareNet = new MyCareNetRequest();
         InsurabilityHandler.setInsurability((String)null);
         InsurabilityHandler.setMessageId((String)null);
         LOG.debug("******************** Call MyCarenet for insurability ******************** ");
         GetInsurabilityForPharmacistResponse ins = InsurabilityConsultationServiceImpl.getInstance().getInsurabilityForPharmacist(myCareNet.createRequest(userName, password, pharmacyHolder, pharmacySSIN, pharmcayNihii, date, type, careReceiverSSIN, careReceiverMutuality, careReceiverRegNrWithMut, packageName, reference, userReference));
         String insXml = null;
         if (ins != null) {
            insXml = JaxContextCentralizer.getInstance().toXml(GetInsurabilityForPharmacistResponse.class, ins);
            LOG.debug("******************** Call MyCarenet for insurability response: " + insXml + " ******************** ");
         }

         return new Insurability(insXml, InsurabilityHandler.getMessageId());
      } catch (Throwable var20) {
         Exceptionutils.errorHandler(var20);
         return null;
      }
   }

   // $FF: synthetic method
   private static void ajc$preClinit() {
      Factory var0 = new Factory("MyCareNetIntegrationModuleImpl.java", MyCareNetIntegrationModuleImpl.class);
      ajc$tjp_0 = var0.makeSJP("method-execution", var0.makeMethodSig("1", "getInsurability", "be.business.connector.mycarenet.MyCareNetIntegrationModuleImpl", "java.lang.String:java.lang.String:java.lang.String:java.lang.String:java.lang.String:java.lang.String:java.lang.String:java.lang.String:java.lang.String:java.lang.String:java.lang.String:java.lang.String:java.lang.String", "userName:password:pharmacyHolder:pharmacySSIN:pharmcayNihii:date:type:careReceiverSSIN:careReceiverMutuality:careReceiverRegNrWithMut:packageName:reference:userReference", "be.business.connector.core.exceptions.IntegrationModuleException", "java.lang.String"), 25);
      ajc$tjp_1 = var0.makeSJP("method-execution", var0.makeMethodSig("1", "getInsurabilityForPharmacist", "be.business.connector.mycarenet.MyCareNetIntegrationModuleImpl", "java.lang.String:java.lang.String:java.lang.String:java.lang.String:java.lang.String:java.lang.String:java.lang.String:java.lang.String:java.lang.String:java.lang.String:java.lang.String:java.lang.String:java.lang.String", "userName:password:pharmacyHolder:pharmacySSIN:pharmcayNihii:date:type:careReceiverSSIN:careReceiverMutuality:careReceiverRegNrWithMut:packageName:reference:userReference", "be.business.connector.core.exceptions.IntegrationModuleException", "be.business.connector.mycarenet.domain.Insurability"), 46);
   }
}
