package be.fgov.ehealth.monitoring.protocol.v2;

import javax.xml.bind.annotation.XmlRegistry;

@XmlRegistry
public class ObjectFactory {
   public AliveCheckRequest createAliveCheckRequest() {
      return new AliveCheckRequest();
   }

   public AliveCheckResponse createAliveCheckResponse() {
      return new AliveCheckResponse();
   }
}
