package be.fgov.ehealth.idsupport.protocol.v2;

import javax.xml.bind.annotation.XmlRegistry;

@XmlRegistry
public class ObjectFactory {
   public ObjectFactory() {
   }

   public VerifyIdRequest createVerifyIdRequest() {
      return new VerifyIdRequest();
   }

   public VerifyIdResponse createVerifyIdResponse() {
      return new VerifyIdResponse();
   }
}
