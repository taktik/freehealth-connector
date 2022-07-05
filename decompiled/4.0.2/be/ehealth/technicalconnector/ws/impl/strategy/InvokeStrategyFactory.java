package be.ehealth.technicalconnector.ws.impl.strategy;

import be.fgov.ehealth.technicalconnector.bootstrap.bcp.EndpointDistributor;
import be.fgov.ehealth.technicalconnector.bootstrap.bcp.domain.CacheInformation;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;

public final class InvokeStrategyFactory {
   private static EndpointDistributor distributor = EndpointDistributor.getInstance();

   private InvokeStrategyFactory() {
   }

   public static final List<InvokeStrategy> getList(String endpoint) {
      ArrayList<InvokeStrategy> strategies = new ArrayList();
      String serviceName = distributor.getService(endpoint);
      CacheInformation cacheInformation = distributor.getCacheInformation(endpoint);
      int alternatives = distributor.getAmountOfAlternatives(endpoint);
      if (!StringUtils.isBlank(serviceName) && alternatives != 1) {
         if (alternatives > 1) {
            strategies.add(new RetryStrategy());
         }
      } else {
         strategies.add(new NoRetryInvokeStrategy());
      }

      if (cacheInformation != null && cacheInformation.isActivated()) {
         strategies.add(new CacheBasedInvokeStrategy(cacheInformation));
      }

      return strategies;
   }
}
