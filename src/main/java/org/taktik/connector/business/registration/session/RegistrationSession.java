package org.taktik.connector.business.registration.session;

import org.taktik.connector.technical.exception.TechnicalConnectorException;
import be.fgov.ehealth.mycarenet.registration.protocol.v1.RegisterToMycarenetServiceRequest;
import be.fgov.ehealth.mycarenet.registration.protocol.v1.RegisterToMycarenetServiceResponse;

public interface RegistrationSession {
   RegisterToMycarenetServiceResponse registerToMycarenetService(RegisterToMycarenetServiceRequest var1) throws TechnicalConnectorException;
}
