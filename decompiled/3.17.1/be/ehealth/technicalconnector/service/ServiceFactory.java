package be.ehealth.technicalconnector.service;

import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.service.idsupport.IdSupportService;
import be.ehealth.technicalconnector.service.idsupport.impl.IdSupportServiceImpl;
import be.ehealth.technicalconnector.service.keydepot.KeyDepotService;
import be.ehealth.technicalconnector.service.keydepot.impl.KeyDepotServiceImpl;
import be.ehealth.technicalconnector.service.kgss.KgssService;
import be.ehealth.technicalconnector.service.kgss.impl.KgssServiceImpl;
import be.ehealth.technicalconnector.service.seals.SealsService;
import be.ehealth.technicalconnector.service.seals.impl.SealsServiceImpl;
import be.ehealth.technicalconnector.service.sso.SingleSignOnService;
import be.ehealth.technicalconnector.service.sso.impl.SingleSignOnServiceImpl;
import be.ehealth.technicalconnector.service.sts.STSService;
import be.ehealth.technicalconnector.service.sts.STSServiceFactory;
import be.ehealth.technicalconnector.service.timestamp.AuthorityService;
import be.ehealth.technicalconnector.service.timestamp.ConsultService;
import be.ehealth.technicalconnector.service.timestamp.ConsultServiceV2;
import be.ehealth.technicalconnector.service.timestamp.impl.AuthorityServiceImpl;
import be.ehealth.technicalconnector.service.timestamp.impl.ConsultServiceImpl;
import be.ehealth.technicalconnector.service.timestamp.impl.ConsultServiceV2Impl;
import be.ehealth.technicalconnector.utils.ConfigurableFactoryHelper;
import be.ehealth.technicalconnector.validator.impl.EhealthReplyValidatorImpl;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class ServiceFactory {
   private static final Logger LOG = LoggerFactory.getLogger(ServiceFactory.class);
   private static Map<Class<?>, Object> serviceCache = new HashMap();

   private ServiceFactory() {
      throw new UnsupportedOperationException();
   }

   public static SealsService getSealsService() throws TechnicalConnectorException {
      return (SealsService)getService(SealsService.class);
   }

   public static KeyDepotService getKeyDepotService() throws TechnicalConnectorException {
      return (KeyDepotService)getService(KeyDepotService.class);
   }

   public static KgssService getKgssService() throws TechnicalConnectorException {
      return (KgssService)getService(KgssService.class);
   }

   public static STSService getStsService() throws TechnicalConnectorException {
      return STSServiceFactory.getInstance();
   }

   public static AuthorityService getAuthorityService() throws TechnicalConnectorException {
      return (AuthorityService)getService(AuthorityService.class);
   }

   /** @deprecated */
   @Deprecated
   public static ConsultService getConsultService() throws TechnicalConnectorException {
      return (ConsultService)getService(ConsultService.class);
   }

   public static ConsultServiceV2 getConsultServiceV2() throws TechnicalConnectorException {
      return (ConsultServiceV2)getService(ConsultServiceV2.class);
   }

   public static IdSupportService getIdSupportService() throws TechnicalConnectorException {
      return (IdSupportService)getService(IdSupportService.class);
   }

   public static SingleSignOnService getSingleSignOnService() throws TechnicalConnectorException {
      return (SingleSignOnService)getService(SingleSignOnService.class);
   }

   private static <T> T getService(Class<T> clazz) throws TechnicalConnectorException {
      if (!serviceCache.containsKey(clazz)) {
         createService(clazz);
      }

      return serviceCache.get(clazz);
   }

   private static <T> void createService(Class<T> clazz) throws TechnicalConnectorException {
      LOG.debug("Creating new Service for :" + clazz.getCanonicalName());
      if (clazz.equals(KeyDepotService.class)) {
         serviceCache.put(clazz, create(clazz, KeyDepotServiceImpl.class));
      } else if (clazz.equals(KgssService.class)) {
         serviceCache.put(clazz, create(clazz, KgssServiceImpl.class));
      } else if (clazz.equals(AuthorityService.class)) {
         serviceCache.put(clazz, create(clazz, AuthorityServiceImpl.class));
      } else if (clazz.equals(ConsultService.class)) {
         serviceCache.put(clazz, create(clazz, ConsultServiceImpl.class));
      } else if (clazz.equals(SealsService.class)) {
         serviceCache.put(clazz, new SealsServiceImpl(new EhealthReplyValidatorImpl()));
      } else if (clazz.equals(IdSupportService.class)) {
         serviceCache.put(clazz, new IdSupportServiceImpl(new EhealthReplyValidatorImpl()));
      } else if (clazz.equals(SingleSignOnService.class)) {
         serviceCache.put(clazz, create(clazz, SingleSignOnServiceImpl.class));
      } else if (clazz.equals(ConsultServiceV2.class)) {
         serviceCache.put(clazz, create(clazz, ConsultServiceV2Impl.class));
      }

   }

   private static <T> T create(Class<T> clazz, Class<?> impl) throws TechnicalConnectorException {
      return (new ConfigurableFactoryHelper(clazz.getName(), impl.getName())).getImplementation();
   }

   public static void flushCache() {
      serviceCache.clear();
   }
}
