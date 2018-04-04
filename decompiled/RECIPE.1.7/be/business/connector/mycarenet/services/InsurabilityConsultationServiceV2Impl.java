package be.business.connector.mycarenet.services;

import org.taktik.connector.business.recipeprojects.core.exceptions.IntegrationModuleException;
import org.taktik.connector.business.recipeprojects.core.services.GenericWebserviceCaller;
import org.taktik.connector.business.recipeprojects.common.utils.EndpointResolver;
import be.fgov.ehealth.insurability.protocol.v2.GetInsurabilityForPharmacistRequest;
import be.fgov.ehealth.insurability.protocol.v2.GetInsurabilityForPharmacistResponse;
import be.fgov.ehealth.recipe.protocol.v3.AliveCheckRequest;
import be.fgov.ehealth.recipe.protocol.v3.AliveCheckResponse;
import org.apache.log4j.Logger;

public class InsurabilityConsultationServiceV2Impl implements InsurabilityConsultationServiceV2 {
   private static final Logger LOG = Logger.getLogger(InsurabilityConsultationServiceV2Impl.class);
   private static final String ENDPOINT_NAME = "endpoint.insurability.v2";
   private static InsurabilityConsultationServiceV2 insurabilityConsultationService;

   public static InsurabilityConsultationServiceV2 getInstance() {
      if (insurabilityConsultationService == null) {
         insurabilityConsultationService = new InsurabilityConsultationServiceV2Impl();
      }

      return insurabilityConsultationService;
   }

   public AliveCheckResponse aliveCheck(AliveCheckRequest aliveCheckRequest) throws IntegrationModuleException {
      return (AliveCheckResponse)GenericWebserviceCaller.callGenericWebservice(aliveCheckRequest, AliveCheckResponse.class, EndpointResolver.getEndpointUrlString("endpoint.insurability.v2"), this.getClass().getName(), true, true, true, true);
   }

   public GetInsurabilityForPharmacistResponse getInsurabilityForPharmacist(GetInsurabilityForPharmacistRequest getInsurabilityForPharmacistRequest) throws IntegrationModuleException {
      return (GetInsurabilityForPharmacistResponse)GenericWebserviceCaller.callGenericWebservice(getInsurabilityForPharmacistRequest, GetInsurabilityForPharmacistResponse.class, EndpointResolver.getEndpointUrlString("endpoint.insurability.v2"), this.getClass().getName(), true, true, true, true);
   }
}
