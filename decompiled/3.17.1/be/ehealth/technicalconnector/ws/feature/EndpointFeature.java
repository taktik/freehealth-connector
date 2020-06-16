package be.ehealth.technicalconnector.ws.feature;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class EndpointFeature extends GenericFeature {
   private String url;

   public EndpointFeature(URL url) {
      super(true);
      this.url = url.toExternalForm();
   }

   public EndpointFeature(String endpoint) {
      super(true);

      try {
         new URL(endpoint);
         this.url = endpoint;
      } catch (MalformedURLException var3) {
         throw new IllegalArgumentException(var3.getMessage(), var3);
      }
   }

   public String getID() {
      return null;
   }

   public Map<String, Object> requestParamOptions() {
      Map<String, Object> result = new HashMap();
      result.put("javax.xml.ws.service.endpoint.address", this.url);
      return result;
   }
}
