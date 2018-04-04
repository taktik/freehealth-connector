package be.ehealth.technicalconnector.ws.impl.strategy;

import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.ws.domain.GenericRequest;
import be.ehealth.technicalconnector.ws.domain.GenericResponse;

public interface InvokeStrategy {
   GenericResponse invoke(GenericRequest var1) throws TechnicalConnectorException;
}
