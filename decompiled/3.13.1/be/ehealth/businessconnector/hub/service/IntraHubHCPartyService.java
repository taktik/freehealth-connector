package be.ehealth.businessconnector.hub.service;

import be.ehealth.businessconnector.hub.exception.IntraHubBusinessConnectorException;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.fgov.ehealth.hubservices.core.v1.ConsentHCPartyType;
import be.fgov.ehealth.hubservices.core.v1.HCPartyAdaptedType;
import be.fgov.ehealth.hubservices.core.v1.HCPartyIdType;

public interface IntraHubHCPartyService {
   HCPartyAdaptedType getHCParty(HCPartyIdType var1) throws TechnicalConnectorException, IntraHubBusinessConnectorException;

   HCPartyAdaptedType putHCParty(HCPartyAdaptedType var1) throws TechnicalConnectorException, IntraHubBusinessConnectorException;

   void putHCPartyConsent(ConsentHCPartyType var1) throws TechnicalConnectorException, IntraHubBusinessConnectorException;

   ConsentHCPartyType getHCPartyConsent(HCPartyIdType var1) throws TechnicalConnectorException, IntraHubBusinessConnectorException;

   void revokeHCPartyConsent(ConsentHCPartyType var1) throws TechnicalConnectorException, IntraHubBusinessConnectorException;
}
