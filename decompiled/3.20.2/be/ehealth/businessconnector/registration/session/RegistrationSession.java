package be.ehealth.businessconnector.registration.session;

import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.fgov.ehealth.mycarenet.registration.protocol.v1.RegisterToMycarenetServiceRequest;
import be.fgov.ehealth.mycarenet.registration.protocol.v1.RegisterToMycarenetServiceResponse;

public interface RegistrationSession {
   RegisterToMycarenetServiceResponse registerToMycarenetService(RegisterToMycarenetServiceRequest var1) throws TechnicalConnectorException;
}
