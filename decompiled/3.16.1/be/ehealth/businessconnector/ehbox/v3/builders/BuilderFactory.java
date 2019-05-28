package be.ehealth.businessconnector.ehbox.v3.builders;

import be.ehealth.businessconnector.ehbox.v3.builders.impl.ConsultationMessageBuilderImpl;
import be.ehealth.businessconnector.ehbox.v3.builders.impl.RequestBuilderImpl;
import be.ehealth.businessconnector.ehbox.v3.builders.impl.SendMessageBuilderImpl;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.service.keydepot.KeyDepotManagerFactory;
import be.ehealth.technicalconnector.session.Session;
import be.ehealth.technicalconnector.session.SessionServiceWithCache;

public final class BuilderFactory implements SessionServiceWithCache {
   private static volatile SendMessageBuilder sendMessageBuilder;
   private static volatile ConsultationMessageBuilder consultationMessageBuilder;
   private static volatile RequestBuilder requestBuilder;
   private static volatile BuilderFactory instance;
   private static Object mutexSendBuilder = new Object();
   private static Object mutexConsBuilder = new Object();
   private static Object mutexReqBuilder = new Object();
   private static Object mutexBuilderFactory = new Object();

   public static SendMessageBuilder getSendMessageBuilder() throws TechnicalConnectorException {
      init();
      if (sendMessageBuilder == null) {
         Object var0 = mutexSendBuilder;
         synchronized(mutexSendBuilder) {
            if (sendMessageBuilder == null) {
               sendMessageBuilder = new SendMessageBuilderImpl(KeyDepotManagerFactory.getKeyDepotManager());
            }
         }
      }

      return sendMessageBuilder;
   }

   public static ConsultationMessageBuilder getConsultationMessageBuilder() throws TechnicalConnectorException {
      init();
      if (consultationMessageBuilder == null) {
         Object var0 = mutexConsBuilder;
         synchronized(mutexConsBuilder) {
            if (consultationMessageBuilder == null) {
               consultationMessageBuilder = new ConsultationMessageBuilderImpl();
            }
         }
      }

      return consultationMessageBuilder;
   }

   public static RequestBuilder getRequestBuilder() throws TechnicalConnectorException {
      init();
      if (requestBuilder == null) {
         Object var0 = mutexReqBuilder;
         synchronized(mutexReqBuilder) {
            if (requestBuilder == null) {
               requestBuilder = new RequestBuilderImpl();
            }
         }
      }

      return requestBuilder;
   }

   private static void init() {
      if (instance == null) {
         Object var0 = mutexBuilderFactory;
         synchronized(mutexBuilderFactory) {
            if (instance == null) {
               instance = new BuilderFactory();
               Session.getInstance().registerSessionService(instance);
            }
         }
      }

   }

   public void flushCache() {
      instance = null;
      sendMessageBuilder = null;
      consultationMessageBuilder = null;
   }
}
