package be.business.connector.mycarenet.services;

import org.taktik.connector.business.recipeprojects.core.exceptions.IntegrationModuleException;
import org.taktik.connector.business.recipeprojects.core.services.GenericWebserviceCaller;
import org.taktik.connector.business.recipeprojects.common.utils.EndpointResolver;
import be.fgov.ehealth.insurability.protocol.v1.AliveCheckRequest;
import be.fgov.ehealth.insurability.protocol.v1.AliveCheckResponse;
import be.fgov.ehealth.insurability.protocol.v1.GetInsurabilityForPharmacistRequest;
import be.fgov.ehealth.insurability.protocol.v1.GetInsurabilityForPharmacistResponse;
import org.apache.log4j.Logger;

public class InsurabilityConsultationServiceImpl implements InsurabilityConsultationService {
   private static final Logger LOG = Logger.getLogger(InsurabilityConsultationServiceImpl.class);
   private static final String ENDPOINT_NAME = "endpoint.insurability";
   private static InsurabilityConsultationService insurabilityConsultationService;

   public static InsurabilityConsultationService getInstance() {
      if (insurabilityConsultationService == null) {
         insurabilityConsultationService = new InsurabilityConsultationServiceImpl();
      }

      return insurabilityConsultationService;
   }

   public AliveCheckResponse aliveCheck(AliveCheckRequest aliveCheckRequest) throws IntegrationModuleException {
      return (AliveCheckResponse)GenericWebserviceCaller.callGenericWebservice(aliveCheckRequest, AliveCheckResponse.class, EndpointResolver.getEndpointUrlString("endpoint.insurability"), this.getClass().getName(), true, true, true, true);
   }

   public GetInsurabilityForPharmacistResponse getInsurabilityForPharmacist(GetInsurabilityForPharmacistRequest getInsurabilityForPharmacistRequest) throws IntegrationModuleException {
      return (GetInsurabilityForPharmacistResponse)GenericWebserviceCaller.callGenericWebservice(getInsurabilityForPharmacistRequest, GetInsurabilityForPharmacistResponse.class, EndpointResolver.getEndpointUrlString("endpoint.insurability"), this.getClass().getName(), true, true, true, true);
   }
}
