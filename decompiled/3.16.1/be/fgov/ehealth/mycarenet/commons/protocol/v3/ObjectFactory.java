package be.fgov.ehealth.mycarenet.commons.protocol.v3;

import javax.xml.bind.annotation.XmlRegistry;

@XmlRegistry
public class ObjectFactory {
   public SendRequestType createSendRequestType() {
      return new SendRequestType();
   }

   public SendResponseType createSendResponseType() {
      return new SendResponseType();
   }

   public ResponseReturnType createResponseReturnType() {
      return new ResponseReturnType();
   }
}
