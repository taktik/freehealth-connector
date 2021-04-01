package be.fgov.ehealth.rn.personservice.protocol.v1;

import javax.xml.bind.annotation.XmlRegistry;

@XmlRegistry
public class ObjectFactory {
   public SearchPersonBySsinRequest createSearchPersonBySsinRequest() {
      return new SearchPersonBySsinRequest();
   }

   public SearchPersonPhoneticallyRequest createSearchPersonPhoneticallyRequest() {
      return new SearchPersonPhoneticallyRequest();
   }

   public SearchPersonBySsinResponse createSearchPersonBySsinResponse() {
      return new SearchPersonBySsinResponse();
   }

   public SearchPersonPhoneticallyResponse createSearchPersonPhoneticallyResponse() {
      return new SearchPersonPhoneticallyResponse();
   }
}
