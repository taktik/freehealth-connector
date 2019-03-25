package be.fgov.ehealth.bcp.protocol.v2;

import javax.xml.bind.annotation.XmlRegistry;

@XmlRegistry
public class ObjectFactory {
   public ServiceList createServiceList() {
      return new ServiceList();
   }

   public Service createService() {
      return new Service();
   }

   public Cache createCache() {
      return new Cache();
   }

   public Key createKey() {
      return new Key();
   }

   public Expiry createExpiry() {
      return new Expiry();
   }

   public Endpoint createEndpoint() {
      return new Endpoint();
   }

   public EndpointList createEndpointList() {
      return new EndpointList();
   }
}
