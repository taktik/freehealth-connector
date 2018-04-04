package be.business.connector.mycarenet.services;

import org.taktik.connector.business.recipeprojects.core.exceptions.IntegrationModuleException;
import be.fgov.ehealth.insurability.protocol.v1.AliveCheckRequest;
import be.fgov.ehealth.insurability.protocol.v1.AliveCheckResponse;
import be.fgov.ehealth.insurability.protocol.v1.GetInsurabilityForPharmacistRequest;
import be.fgov.ehealth.insurability.protocol.v1.GetInsurabilityForPharmacistResponse;

public interface InsurabilityConsultationService {
   AliveCheckResponse aliveCheck(AliveCheckRequest var1) throws IntegrationModuleException;

   GetInsurabilityForPharmacistResponse getInsurabilityForPharmacist(GetInsurabilityForPharmacistRequest var1) throws IntegrationModuleException;
}
