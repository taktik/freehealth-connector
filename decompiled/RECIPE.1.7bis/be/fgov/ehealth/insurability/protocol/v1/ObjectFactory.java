package be.fgov.ehealth.insurability.protocol.v1;

import javax.xml.bind.annotation.XmlRegistry;

@XmlRegistry
public class ObjectFactory {
   public AliveCheckResponse createAliveCheckResponse() {
      return new AliveCheckResponse();
   }

   public AliveCheckRequest createAliveCheckRequest() {
      return new AliveCheckRequest();
   }

   public GetInsurabilityForPharmacistRequest createGetInsurabilityForPharmacistRequest() {
      return new GetInsurabilityForPharmacistRequest();
   }

   public GetInsurabilityForPharmacistResponse createGetInsurabilityForPharmacistResponse() {
      return new GetInsurabilityForPharmacistResponse();
   }
}
