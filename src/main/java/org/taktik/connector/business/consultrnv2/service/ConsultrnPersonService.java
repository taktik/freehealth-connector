package org.taktik.connector.business.consultrnv2.service;

import be.fgov.ehealth.rn.personservice.protocol.v1.SearchPersonBySsinRequest;
import be.fgov.ehealth.rn.personservice.protocol.v1.SearchPersonBySsinResponse;
import be.fgov.ehealth.rn.personservice.protocol.v1.SearchPersonPhoneticallyRequest;
import be.fgov.ehealth.rn.personservice.protocol.v1.SearchPersonPhoneticallyResponse;
import org.taktik.connector.technical.exception.TechnicalConnectorException;
import org.taktik.connector.technical.service.sts.security.SAMLToken;

public interface ConsultrnPersonService {
   SearchPersonBySsinResponse searchPersonBySsin(SAMLToken var1, SearchPersonBySsinRequest var2) throws TechnicalConnectorException;

   SearchPersonPhoneticallyResponse searchPersonPhonetically(SAMLToken var1, SearchPersonPhoneticallyRequest var2) throws TechnicalConnectorException;
}
