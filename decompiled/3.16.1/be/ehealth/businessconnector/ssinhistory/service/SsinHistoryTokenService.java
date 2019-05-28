package be.ehealth.businessconnector.ssinhistory.service;

import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.service.sts.security.SAMLToken;
import be.fgov.ehealth.consultrn.ssinhistory.protocol.v1.ConsultCurrentSsinRequest;
import be.fgov.ehealth.consultrn.ssinhistory.protocol.v1.ConsultCurrentSsinResponse;
import be.fgov.ehealth.consultrn.ssinhistory.protocol.v1.ConsultRelatedSsinsRequest;
import be.fgov.ehealth.consultrn.ssinhistory.protocol.v1.ConsultRelatedSsinsResponse;

public interface SsinHistoryTokenService {
   ConsultRelatedSsinsResponse consultRelatedSsins(SAMLToken var1, ConsultRelatedSsinsRequest var2) throws TechnicalConnectorException;

   ConsultCurrentSsinResponse consultCurrentSsin(SAMLToken var1, ConsultCurrentSsinRequest var2) throws TechnicalConnectorException;
}
