package be.fgov.ehealth.technicalconnector.bootstrap.bcp.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EndPointInformation {
   private static final Logger LOG = LoggerFactory.getLogger(EndPointInformation.class);
   private Map<String, String> url2Service = new HashMap();
   private Map<String, String> service2DefaultEndpoint = new HashMap();
   private Map<String, List<String>> service2AllEndpoints = new HashMap();
   private Map<String, String> service2ActiveEndpoint = new HashMap();
   private Map<String, CacheInformation> service2cache = new HashMap();

   public EndPointInformation() {
   }

   public Map<String, String> getService2ActiveEndpoint() {
      return Collections.unmodifiableMap(this.service2ActiveEndpoint);
   }

   public Map<String, String> getUrl2Service() {
      return Collections.unmodifiableMap(this.url2Service);
   }

   public Map<String, List<String>> getService2AllEndpoints() {
      return Collections.unmodifiableMap(this.service2AllEndpoints);
   }

   public Map<String, String> getService2DefaultEndpoint() {
      return Collections.unmodifiableMap(this.service2DefaultEndpoint);
   }

   public Map<String, CacheInformation> getService2CacheInformation() {
      return Collections.unmodifiableMap(this.service2cache);
   }

   public void register(String serviceName, String activeEndpoint, String defaultEndpoint, Collection<String> endpoints, CacheInformation cacheInformation) {
      Validate.isTrue(StringUtils.isNotBlank(defaultEndpoint));
      LOG.debug("Adding info to register {} activeURL {}, defaultURL {}, cache {}", new Object[]{serviceName, activeEndpoint, defaultEndpoint, cacheInformation});
      this.service2DefaultEndpoint.put(serviceName, defaultEndpoint);
      if (StringUtils.isNotBlank(activeEndpoint)) {
         this.service2ActiveEndpoint.put(serviceName, activeEndpoint);
      }

      this.service2AllEndpoints.put(serviceName, new ArrayList(endpoints));
      Iterator var6 = endpoints.iterator();

      while(var6.hasNext()) {
         String endpoint = (String)var6.next();
         this.url2Service.put(endpoint, serviceName);
      }

      if (cacheInformation != null) {
         this.service2cache.put(serviceName, cacheInformation);
      }

   }
}
