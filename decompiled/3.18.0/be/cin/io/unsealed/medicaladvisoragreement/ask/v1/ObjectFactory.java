package be.cin.io.unsealed.medicaladvisoragreement.ask.v1;

import javax.xml.bind.annotation.XmlRegistry;

@XmlRegistry
public class ObjectFactory {
   public Request createRequest() {
      return new Request();
   }

   public Response createResponse() {
      return new Response();
   }
}
