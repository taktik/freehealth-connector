package org.taktik.connector.business.consultrnv2.session;

import be.fgov.ehealth.rn.cbsspersonservice.protocol.v1.RegisterPersonRequest;
import be.fgov.ehealth.rn.cbsspersonservice.protocol.v1.RegisterPersonResponse;
import org.taktik.connector.technical.exception.TechnicalConnectorException;

public interface ConsultrnCBSSPersonService {
   RegisterPersonResponse registerPerson(RegisterPersonRequest var1) throws TechnicalConnectorException;
}
