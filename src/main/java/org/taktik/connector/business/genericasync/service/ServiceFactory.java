package org.taktik.connector.business.genericasync.service;

import org.taktik.connector.business.genericasync.service.impl.GenAsyncServiceImpl;
import org.taktik.connector.technical.exception.TechnicalConnectorException;
import org.taktik.connector.technical.service.sts.security.SAMLToken;
import org.taktik.connector.technical.utils.ConfigurableFactoryHelper;
import org.taktik.connector.technical.validator.SessionValidator;
import org.taktik.connector.technical.ws.domain.GenericRequest;
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
