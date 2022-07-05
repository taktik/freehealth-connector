package be.fgov.ehealth.commons.protocol.v1;

import javax.xml.bind.annotation.XmlRegistry;

@XmlRegistry
public class ObjectFactory {
   public ObjectFactory() {
   }

   public ResponseType createResponseType() {
      return new ResponseType();
   }

   public RequestType createRequestType() {
      return new RequestType();
   }
}
