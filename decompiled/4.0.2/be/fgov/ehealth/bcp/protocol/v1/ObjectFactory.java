package be.fgov.ehealth.bcp.protocol.v1;

import javax.xml.bind.annotation.XmlRegistry;

@XmlRegistry
public class ObjectFactory {
   public ObjectFactory() {
   }

   public ServiceList createServiceList() {
      return new ServiceList();
   }

   public ServiceType createServiceType() {
      return new ServiceType();
   }

   public Endpoint createEndpoint() {
      return new Endpoint();
   }
}
