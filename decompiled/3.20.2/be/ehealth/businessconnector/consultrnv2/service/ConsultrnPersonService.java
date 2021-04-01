package be.ehealth.businessconnector.consultrnv2.service;

import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.service.sts.security.SAMLToken;
import be.fgov.ehealth.rn.personservice.protocol.v1.SearchPersonBySsinRequest;
import be.fgov.ehealth.rn.personservice.protocol.v1.SearchPersonBySsinResponse;
import be.fgov.ehealth.rn.personservice.protocol.v1.SearchPersonPhoneticallyRequest;
import be.fgov.ehealth.rn.personservice.protocol.v1.SearchPersonPhoneticallyResponse;

public interface ConsultrnPersonService {
   SearchPersonBySsinResponse searchPersonBySsin(SAMLToken var1, SearchPersonBySsinRequest var2) throws TechnicalConnectorException;

   SearchPersonPhoneticallyResponse searchPersonPhonetically(SAMLToken var1, SearchPersonPhoneticallyRequest var2) throws TechnicalConnectorException;
}
