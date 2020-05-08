package be.ehealth.businessconnector.wsconsent.builders;

import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.fgov.ehealth.hubservices.core.v2.PatientIdType;

public interface PatientInfoBuilder {
   PatientIdType readFromEidCard() throws TechnicalConnectorException;
}
