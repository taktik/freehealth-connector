package be.ehealth.businessconnector.consultrnv2.session;

import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.fgov.ehealth.rn.cbsspersonservice.protocol.v1.RegisterPersonRequest;
import be.fgov.ehealth.rn.cbsspersonservice.protocol.v1.RegisterPersonResponse;

public interface ConsultrnCBSSPersonService {
   RegisterPersonResponse registerPerson(RegisterPersonRequest var1) throws TechnicalConnectorException;
}
