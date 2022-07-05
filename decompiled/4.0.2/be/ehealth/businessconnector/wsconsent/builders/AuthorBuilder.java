package be.ehealth.businessconnector.wsconsent.builders;

import be.ehealth.businessconnector.wsconsent.exception.WsConsentBusinessConnectorException;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.fgov.ehealth.hubservices.core.v2.AuthorWithPatientAndPersonType;

public interface AuthorBuilder {
   AuthorWithPatientAndPersonType createAuthor() throws TechnicalConnectorException, WsConsentBusinessConnectorException, InstantiationException;

   AuthorWithPatientAndPersonType readFromEidCard() throws TechnicalConnectorException;
}
