package be.business.connector.mycarenet.services;

import be.business.connector.core.exceptions.IntegrationModuleException;
import be.fgov.ehealth.insurability.protocol.v2.GetInsurabilityForPharmacistRequest;
import be.fgov.ehealth.insurability.protocol.v2.GetInsurabilityForPharmacistResponse;
import be.fgov.ehealth.recipe.protocol.v3.AliveCheckRequest;
import be.fgov.ehealth.recipe.protocol.v3.AliveCheckResponse;

public interface InsurabilityConsultationServiceV2 {
   AliveCheckResponse aliveCheck(AliveCheckRequest var1) throws IntegrationModuleException;

   GetInsurabilityForPharmacistResponse getInsurabilityForPharmacist(GetInsurabilityForPharmacistRequest var1) throws IntegrationModuleException;
}
