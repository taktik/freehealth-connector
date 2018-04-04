package be.fgov.ehealth.seals.protocol.v1;

import javax.xml.bind.annotation.XmlRegistry;

@XmlRegistry
public class ObjectFactory {
   public EncodeRequest createEncodeRequest() {
      return new EncodeRequest();
   }

   public EncodeResponse createEncodeResponse() {
      return new EncodeResponse();
   }

   public DecodeRequest createDecodeRequest() {
      return new DecodeRequest();
   }

   public DecodeResponse createDecodeResponse() {
      return new DecodeResponse();
   }
}
