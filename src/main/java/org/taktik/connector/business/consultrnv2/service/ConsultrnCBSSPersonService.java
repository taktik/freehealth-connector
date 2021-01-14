package org.taktik.connector.business.consultrnv2.service;

import be.fgov.ehealth.rn.cbsspersonservice.protocol.v1.RegisterPersonRequest;
import be.fgov.ehealth.rn.cbsspersonservice.protocol.v1.RegisterPersonResponse;
import org.taktik.connector.technical.exception.TechnicalConnectorException;
import org.taktik.connector.technical.service.sts.security.SAMLToken;

public interface ConsultrnCBSSPersonService {
   RegisterPersonResponse registerPerson(SAMLToken var1, RegisterPersonRequest var2) throws TechnicalConnectorException;
}
