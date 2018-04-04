package be.ehealth.business.kmehrcommons.validator;

import org.taktik.connector.technical.exception.TechnicalConnectorException;
import be.fgov.ehealth.standards.kmehr.schema.v1.Kmehrmessage;

public interface KmehrValidator {
   void validateKmehrMessage(Kmehrmessage var1) throws TechnicalConnectorException;
}
