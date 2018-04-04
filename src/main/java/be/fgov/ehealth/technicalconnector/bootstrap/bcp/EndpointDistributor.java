package be.fgov.ehealth.technicalconnector.bootstrap.bcp;

import org.taktik.connector.technical.config.ConfigFactory;
import org.taktik.connector.technical.config.ConfigValidator;
import org.taktik.connector.technical.exception.NoNextEndpointException;
import be.fgov.ehealth.technicalconnector.bootstrap.bcp.domain.EndPointInformation;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.Validate;
import org.apache.commons.lang.reflect.MethodUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class EndpointDistributor {
   public static final String PROP_POLLING_INTERVAL = "be.fgov.ehealth.technicalconnector.bootstrap.bcp.polling.interval.minutes";
   public static final String PROP_POLLING_ACTIVATED = "be.fgov.ehealth.technicalconnector.bootstrap.bcp.polling.activated";
   private static final long DEFAULT_POLLING_INTERVAL = 15L;
   private static final Logger LOG = LoggerFactory.getLogger(EndpointDistributor.class);
   private static ConfigValidator config = ConfigFactory.getConfigValidator();
   private boolean polling;
   private Timer timer;
   private Map<String, String> url2Service;
   private Map<String, List<String>> service2AllEndpoints;
   private Map<String, String> service2ActiveEndpoint;
   private Map<String, String> service2DefaultEndpoint;

   public static EndpointDistributor getInstance() {
      return EndpointDistributor.EndpointDistributorSingleton.INSTANCE.getEndpointDistributor();
   }

   private EndpointDistributor() {
      this.url2Service = new HashMap();
      this.service2AllEndpoints = new HashMap();
      this.service2ActiveEndpoint = new HashMap();
      this.service2DefaultEndpoint = new HashMap();
   }

   public String getActiveEndpoint(String currentEndpoint) {
      return this.url2Service.containsKey(currentEndpoint) ? (String)this.service2ActiveEndpoint.get(this.url2Service.get(currentEndpoint)) : currentEndpoint;
   }

   public void activatePolling() {
      if (!this.polling && this.isBCPMode() && config.getBooleanProperty("be.fgov.ehealth.technicalconnector.bootstrap.bcp.polling.activated", Boolean.TRUE).booleanValue()) {
         this.timer = new Timer(true);
         this.timer.schedule(new EndpointDistributor.StatusPollingTimerTask(), new Date(), TimeUnit.MILLISECONDS.convert(config.getLongProperty("be.fgov.ehealth.technicalconnector.bootstrap.bcp.polling.interval.minutes", 15L).longValue(), TimeUnit.MINUTES));
      }

      this.polling = true;
   }

   public boolean mustPoll() {
      return this.polling;
   }

   public void activateNextEndPoint(String currentEndpoint) throws NoNextEndpointException {
      LOG.debug("Trying to activate next endpoint for [{}]", currentEndpoint);
      if (this.url2Service.containsKey(currentEndpoint)) {
         String serviceKey = (String)this.url2Service.get(currentEndpoint);
         String nextEndpoint = next(currentEndpoint, (List)this.service2AllEndpoints.get(serviceKey));
         LOG.info("Activating new endpoint [{}] for [{}]", nextEndpoint, serviceKey);
         this.service2ActiveEndpoint.put(serviceKey, nextEndpoint);
      } else {
         throw new NoNextEndpointException("Unable to activate alternative for [" + currentEndpoint + "]");
      }
   }

   public int getAmountOfAlternatives(String currentEndpoint) {
      if (this.url2Service.containsKey(currentEndpoint)) {
         String serviceKey = (String)this.url2Service.get(currentEndpoint);
         return ((List)this.service2AllEndpoints.get(serviceKey)).size();
      } else {
         return 1;
      }
   }

   private static String next(String currentEndpoint, List<String> endpoints) throws NoNextEndpointException {
      Validate.notEmpty(endpoints);
      if (endpoints.size() == 1) {
         throw new NoNextEndpointException("Unable to activate alternative for [" + currentEndpoint + "]");
      } else {
         Validate.isTrue(endpoints.size() > 1);
         int pos = endpoints.indexOf(currentEndpoint);
         if (pos == -1) {
            throw new IllegalArgumentException("Unable to find currentValue [" + currentEndpoint + "] in list " + StringUtils.join(endpoints, ","));
         } else {
            ++pos;
            return endpoints.size() == pos ? (String)endpoints.get(0) : (String)endpoints.get(pos);
         }
      }
   }

   void update(EndPointInformation info) {
      Validate.notNull(info);
      if (!isBCPMode(info)) {
         this.polling = false;
         if (this.timer != null) {
            this.timer.cancel();
         }
      }

      this.url2Service = info.getUrl2Service();
      this.service2ActiveEndpoint = info.getService2ActiveEndpoint();
      this.service2AllEndpoints = info.getService2AllEndpoints();
      this.service2DefaultEndpoint = info.getService2DefaultEndpoint();
   }

   void reset() {
      this.url2Service = new HashMap();
      this.service2AllEndpoints = new HashMap();
      this.service2ActiveEndpoint = new HashMap();
      this.service2DefaultEndpoint = new HashMap();
   }

   private static boolean isBCPMode(EndPointInformation info) {
      return !info.getService2ActiveEndpoint().equals(info.getService2DefaultEndpoint());
   }

   private boolean isBCPMode() {
      return !this.service2ActiveEndpoint.equals(this.service2DefaultEndpoint);
   }

   public static boolean update() {
      try {
         return (Boolean) MethodUtils.invokeStaticMethod(Class.forName("be.fgov.ehealth.technicalconnector.bootstrap.bcp.EndpointUpdater"), "update", new Object[0]);
      } catch (Exception ex) {
         LOG.error("Unable to update endpoints", ex);
         return false;
      }
   }

   // $FF: synthetic method
   EndpointDistributor(EndpointDistributor.SyntheticClass_1 x0) {
      this();
   }

   // $FF: synthetic class
   static class SyntheticClass_1 {
   }

   private static class StatusPollingTimerTask extends TimerTask {
      private static final Logger LOG = LoggerFactory.getLogger(EndpointDistributor.StatusPollingTimerTask.class);

      private StatusPollingTimerTask() {
      }

      public void run() {
         LOG.debug("Update endpoints through Timer");
         EndpointDistributor.update();
      }

      // $FF: synthetic method
      StatusPollingTimerTask(EndpointDistributor.SyntheticClass_1 x0) {
         this();
      }
   }

   private static enum EndpointDistributorSingleton {
      INSTANCE;

      private volatile EndpointDistributor distributor = new EndpointDistributor();

      public EndpointDistributor getEndpointDistributor() {
         return this.distributor;
      }
   }
}
