package be.ehealth.businessconnector.consultrnv2.service;

import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.service.sts.security.SAMLToken;
import be.fgov.ehealth.rn.cbsspersonservice.protocol.v1.RegisterPersonRequest;
import be.fgov.ehealth.rn.cbsspersonservice.protocol.v1.RegisterPersonResponse;

public interface ConsultrnCBSSPersonService {
   RegisterPersonResponse registerPerson(SAMLToken var1, RegisterPersonRequest var2) throws TechnicalConnectorException;
}
