package be.fgov.ehealth.consultrn.protocol.v2;

import javax.xml.bind.annotation.XmlRegistry;

@XmlRegistry
public class ObjectFactory {
   public RegisterPersonRequest createRegisterPersonRequest() {
      return new RegisterPersonRequest();
   }

   public RegisterPersonResponse createRegisterPersonResponse() {
      return new RegisterPersonResponse();
   }
}
