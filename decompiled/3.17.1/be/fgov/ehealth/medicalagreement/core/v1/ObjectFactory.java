package be.fgov.ehealth.medicalagreement.core.v1;

import javax.xml.bind.annotation.XmlRegistry;

@XmlRegistry
public class ObjectFactory {
   public Kmehrrequest createKmehrrequest() {
      return new Kmehrrequest();
   }

   public Kmehrresponse createKmehrresponse() {
      return new Kmehrresponse();
   }

   public Request createRequest() {
      return new Request();
   }

   public Acknowledgetype createAcknowledgetype() {
      return new Acknowledgetype();
   }
}
