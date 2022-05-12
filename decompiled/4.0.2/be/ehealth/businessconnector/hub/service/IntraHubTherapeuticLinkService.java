package be.ehealth.businessconnector.hub.service;

import be.ehealth.business.intrahubcommons.exception.IntraHubBusinessConnectorException;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.fgov.ehealth.hubservices.core.v1.SelectGetHCPartyPatientConsentType;
import be.fgov.ehealth.hubservices.core.v1.TherapeuticLinkType;
import java.util.Collection;

public interface IntraHubTherapeuticLinkService {
   void putTherapeuticLink(TherapeuticLinkType var1) throws TechnicalConnectorException, IntraHubBusinessConnectorException;

   Collection<TherapeuticLinkType> getTherapeuticLink(SelectGetHCPartyPatientConsentType var1) throws TechnicalConnectorException, IntraHubBusinessConnectorException;

   void revokeTherapeuticLink(TherapeuticLinkType var1) throws TechnicalConnectorException, IntraHubBusinessConnectorException;
}
