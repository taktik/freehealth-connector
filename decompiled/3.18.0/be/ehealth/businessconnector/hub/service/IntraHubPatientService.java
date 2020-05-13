package be.ehealth.businessconnector.hub.service;

import be.ehealth.business.intrahubcommons.exception.IntraHubBusinessConnectorException;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.fgov.ehealth.hubservices.core.v1.ConsentType;
import be.fgov.ehealth.hubservices.core.v1.PatientIdType;
import be.fgov.ehealth.hubservices.core.v1.SelectGetPatientConsentType;
import be.fgov.ehealth.standards.kmehr.schema.v1.PersonType;

public interface IntraHubPatientService {
   PersonType putPatient(PersonType var1) throws TechnicalConnectorException, IntraHubBusinessConnectorException;

   PersonType getPatient(PatientIdType var1) throws TechnicalConnectorException, IntraHubBusinessConnectorException;

   void putPatientConsent(ConsentType var1) throws TechnicalConnectorException, IntraHubBusinessConnectorException;

   ConsentType getPatientConsent(SelectGetPatientConsentType var1) throws TechnicalConnectorException, IntraHubBusinessConnectorException;

   void revokePatientConsent(ConsentType var1) throws TechnicalConnectorException, IntraHubBusinessConnectorException;
}
