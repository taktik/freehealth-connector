package be.business.connector.mycarenet;

import be.apb.gfddpp.common.utils.JaxContextCentralizer;
import be.business.connector.common.ApplicationConfig;
import be.business.connector.common.module.AbstractIntegrationModule;
import be.business.connector.core.exceptions.IntegrationModuleException;
import be.business.connector.core.handlers.InsurabilityHandler;
import be.business.connector.core.utils.Exceptionutils;
import be.business.connector.mycarenet.domain.Insurability;
import be.business.connector.mycarenet.services.InsurabilityConsultationServiceV2Impl;
import be.fgov.ehealth.insurability.protocol.v2.GetInsurabilityForPharmacistResponse;
import java.lang.annotation.Annotation;
import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.JoinPoint.StaticPart;
import org.aspectj.runtime.reflect.Factory;
import org.perf4j.aop.Profiled;
import org.perf4j.log4j.aop.TimingAspect;

public class MyCareNetIntegrationModuleV2Impl extends AbstractIntegrationModule implements MyCareNetIntegrationModule {
   private static final Logger LOG;
   // $FF: synthetic field
   private static final StaticPart ajc$tjp_0;
   // $FF: synthetic field
   private static Annotation ajc$anno$0;
   // $FF: synthetic field
   private static final StaticPart ajc$tjp_1;
   // $FF: synthetic field
   private static Annotation ajc$anno$1;

   static {
      ajc$preClinit();
      LOG = Logger.getLogger(MyCareNetIntegrationModuleV2Impl.class);
   }

   public MyCareNetIntegrationModuleV2Impl() throws IntegrationModuleException {
      LOG.debug("MyCareNetIntegrationModule V2 initialized.");
   }

   /** @deprecated */
   @Deprecated
   @Profiled(
      logFailuresSeparately = true,
      tag = "0.MyCareNet#getInsurability"
   )
   public String getInsurability(String userName, String password, String pharmacyHolder, String pharmacySSIN, String pharmcayNihii, String date, String type, String careReceiverSSIN, String careReceiverMutuality, String careReceiverRegNrWithMut, String packageName, String reference, String userReference) throws IntegrationModuleException {
      StaticPart var10000 = ajc$tjp_0;
      Object[] var31 = new Object[]{userName, password, pharmacyHolder, pharmacySSIN, pharmcayNihii, date, type, careReceiverSSIN, careReceiverMutuality, careReceiverRegNrWithMut, packageName, reference, userReference};
      JoinPoint var30 = Factory.makeJP(var10000, this, this, var31);
      TimingAspect var33 = TimingAspect.aspectOf();
      Object[] var32 = new Object[]{this, userName, password, pharmacyHolder, pharmacySSIN, pharmcayNihii, date, type, careReceiverSSIN, careReceiverMutuality, careReceiverRegNrWithMut, packageName, reference, userReference, var30};
      ProceedingJoinPoint var10001 = (new MyCareNetIntegrationModuleV2Impl$AjcClosure1(var32)).linkClosureAndJoinPoint(69648);
      Annotation var10002 = ajc$anno$0;
      if (var10002 == null) {
         var10002 = ajc$anno$0 = MyCareNetIntegrationModuleV2Impl.class.getDeclaredMethod("getInsurability", String.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class).getAnnotation(Profiled.class);
      }

      return (String)var33.doPerfLogging(var10001, (Profiled)var10002);
   }

   @Profiled(
      logFailuresSeparately = true,
      tag = "0.MyCareNet#getInsurability"
   )
   public Insurability getInsurabilityForPharmacist(String userName, String password, String pharmacyHolder, String pharmacySSIN, String pharmcayNihii, String date, String type, String careReceiverSSIN, String careReceiverMutuality, String careReceiverRegNrWithMut, String packageName, String reference, String userReference) throws IntegrationModuleException {
      StaticPart var10000 = ajc$tjp_1;
      Object[] var31 = new Object[]{userName, password, pharmacyHolder, pharmacySSIN, pharmcayNihii, date, type, careReceiverSSIN, careReceiverMutuality, careReceiverRegNrWithMut, packageName, reference, userReference};
      JoinPoint var30 = Factory.makeJP(var10000, this, this, var31);
      TimingAspect var33 = TimingAspect.aspectOf();
      Object[] var32 = new Object[]{this, userName, password, pharmacyHolder, pharmacySSIN, pharmcayNihii, date, type, careReceiverSSIN, careReceiverMutuality, careReceiverRegNrWithMut, packageName, reference, userReference, var30};
      ProceedingJoinPoint var10001 = (new MyCareNetIntegrationModuleV2Impl$AjcClosure3(var32)).linkClosureAndJoinPoint(69648);
      Annotation var10002 = ajc$anno$1;
      if (var10002 == null) {
         var10002 = ajc$anno$1 = MyCareNetIntegrationModuleV2Impl.class.getDeclaredMethod("getInsurabilityForPharmacist", String.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class).getAnnotation(Profiled.class);
      }

      return (Insurability)var33.doPerfLogging(var10001, (Profiled)var10002);
   }

   // $FF: synthetic method
   static final String getInsurability_aroundBody0(MyCareNetIntegrationModuleV2Impl ajc$this, String userName, String password, String pharmacyHolder, String pharmacySSIN, String pharmcayNihii, String date, String type, String careReceiverSSIN, String careReceiverMutuality, String careReceiverRegNrWithMut, String packageName, String reference, String userReference, JoinPoint var14) {
      ApplicationConfig.getInstance().assertValidSession();

      try {
         MyCareNetRequestV2 myCareNet = new MyCareNetRequestV2();
         LOG.debug("******************** Call MyCarenet for insurability ******************** ");
         GetInsurabilityForPharmacistResponse ins = InsurabilityConsultationServiceV2Impl.getInstance().getInsurabilityForPharmacist(myCareNet.createRequest(userName, password, pharmacyHolder, pharmacySSIN, pharmcayNihii, date, type, careReceiverSSIN, careReceiverMutuality, careReceiverRegNrWithMut, packageName, reference, userReference));
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
   static final Insurability getInsurabilityForPharmacist_aroundBody2(MyCareNetIntegrationModuleV2Impl ajc$this, String userName, String password, String pharmacyHolder, String pharmacySSIN, String pharmcayNihii, String date, String type, String careReceiverSSIN, String careReceiverMutuality, String careReceiverRegNrWithMut, String packageName, String reference, String userReference, JoinPoint var14) {
      ApplicationConfig.getInstance().assertValidSession();

      try {
         MyCareNetRequestV2 myCareNet = new MyCareNetRequestV2();
         InsurabilityHandler.setInsurability((String)null);
         InsurabilityHandler.setMessageId((String)null);
         LOG.debug("******************** Call MyCarenet for insurability ******************** ");
         GetInsurabilityForPharmacistResponse ins = InsurabilityConsultationServiceV2Impl.getInstance().getInsurabilityForPharmacist(myCareNet.createRequest(userName, password, pharmacyHolder, pharmacySSIN, pharmcayNihii, date, type, careReceiverSSIN, careReceiverMutuality, careReceiverRegNrWithMut, packageName, reference, userReference));
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
      Factory var0 = new Factory("MyCareNetIntegrationModuleV2Impl.java", MyCareNetIntegrationModuleV2Impl.class);
      ajc$tjp_0 = var0.makeSJP("method-execution", var0.makeMethodSig("1", "getInsurability", "be.business.connector.mycarenet.MyCareNetIntegrationModuleV2Impl", "java.lang.String:java.lang.String:java.lang.String:java.lang.String:java.lang.String:java.lang.String:java.lang.String:java.lang.String:java.lang.String:java.lang.String:java.lang.String:java.lang.String:java.lang.String", "userName:password:pharmacyHolder:pharmacySSIN:pharmcayNihii:date:type:careReceiverSSIN:careReceiverMutuality:careReceiverRegNrWithMut:packageName:reference:userReference", "be.business.connector.core.exceptions.IntegrationModuleException", "java.lang.String"), 28);
      ajc$tjp_1 = var0.makeSJP("method-execution", var0.makeMethodSig("1", "getInsurabilityForPharmacist", "be.business.connector.mycarenet.MyCareNetIntegrationModuleV2Impl", "java.lang.String:java.lang.String:java.lang.String:java.lang.String:java.lang.String:java.lang.String:java.lang.String:java.lang.String:java.lang.String:java.lang.String:java.lang.String:java.lang.String:java.lang.String", "userName:password:pharmacyHolder:pharmacySSIN:pharmcayNihii:date:type:careReceiverSSIN:careReceiverMutuality:careReceiverRegNrWithMut:packageName:reference:userReference", "be.business.connector.core.exceptions.IntegrationModuleException", "be.business.connector.mycarenet.domain.Insurability"), 48);
   }
}
