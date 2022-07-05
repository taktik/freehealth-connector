package be.fgov.ehealth.etkra.protocol.v2;

import javax.xml.bind.annotation.XmlRegistry;

@XmlRegistry
public class ObjectFactory {
   public ObjectFactory() {
   }

   public StartETKRegistrationRequest createStartETKRegistrationRequest() {
      return new StartETKRegistrationRequest();
   }

   public StartETKRegistrationResponse createStartETKRegistrationResponse() {
      return new StartETKRegistrationResponse();
   }

   public CompleteETKRegistrationRequest createCompleteETKRegistrationRequest() {
      return new CompleteETKRegistrationRequest();
   }

   public CompleteETKRegistrationResponse createCompleteETKRegistrationResponse() {
      return new CompleteETKRegistrationResponse();
   }

   public ActivateETKRequest createActivateETKRequest() {
      return new ActivateETKRequest();
   }

   public ActivateETKResponse createActivateETKResponse() {
      return new ActivateETKResponse();
   }
}
