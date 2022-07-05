package be.ehealth.technicalconnector.ws;

import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.ws.domain.GenericRequest;
import be.ehealth.technicalconnector.ws.domain.GenericResponse;

public interface GenericWsSender {
   GenericResponse send(GenericRequest var1) throws TechnicalConnectorException;
}
