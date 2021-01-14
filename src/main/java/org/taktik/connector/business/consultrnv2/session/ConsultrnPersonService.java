package org.taktik.connector.business.consultrnv2.session;

import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.fgov.ehealth.rn.personservice.protocol.v1.SearchPersonBySsinRequest;
import be.fgov.ehealth.rn.personservice.protocol.v1.SearchPersonBySsinResponse;
import be.fgov.ehealth.rn.personservice.protocol.v1.SearchPersonPhoneticallyRequest;
import be.fgov.ehealth.rn.personservice.protocol.v1.SearchPersonPhoneticallyResponse;

public interface ConsultrnPersonService {
   SearchPersonBySsinResponse searchPersonBySsin(SearchPersonBySsinRequest var1) throws TechnicalConnectorException;

   SearchPersonPhoneticallyResponse searchPersonPhonetically(SearchPersonPhoneticallyRequest var1) throws TechnicalConnectorException;
}
