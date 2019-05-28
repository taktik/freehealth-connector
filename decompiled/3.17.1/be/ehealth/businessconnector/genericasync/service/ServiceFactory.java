package be.ehealth.businessconnector.genericasync.service;

import be.ehealth.businessconnector.genericasync.service.impl.GenAsyncServiceImpl;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.service.sts.security.SAMLToken;
import be.ehealth.technicalconnector.utils.ConfigurableFactoryHelper;
import be.ehealth.technicalconnector.validator.SessionValidator;
import be.ehealth.technicalconnector.ws.domain.GenericRequest;
import java.util.HashMap;
import java.util.Map;

public final class ServiceFactory extends GenAsyncServiceImpl {
   private ServiceFactory() {
      throw new UnsupportedOperationException();
   }

   public static GenAsyncService getGenAsyncService(String serviceName, SessionValidator sessionValidator) throws TechnicalConnectorException {
      Map<String, Object> paramMap = new HashMap();
      paramMap.put("serviceName", serviceName);
      paramMap.put("sessionValidator", sessionValidator);
      return getGenAsyncService(paramMap);
   }

   public static GenAsyncService getGenAsyncService(Map<String, Object> paramMap) throws TechnicalConnectorException {
      return (GenAsyncService)(new ConfigurableFactoryHelper(GenAsyncService.class.getName(), GenAsyncServiceImpl.class.getName())).getImplementation(paramMap);
   }

   /** @deprecated */
   @Deprecated
   public static GenericRequest getService(SAMLToken token, String serviceName) throws TechnicalConnectorException {
      return build(token, serviceName);
   }
}
