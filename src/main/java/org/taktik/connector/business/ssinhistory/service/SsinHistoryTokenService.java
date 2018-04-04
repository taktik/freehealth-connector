package org.taktik.connector.business.ssinhistory.service;

import org.taktik.connector.technical.exception.TechnicalConnectorException;
import org.taktik.connector.technical.service.sts.security.SAMLToken;
import be.fgov.ehealth.consultrn.ssinhistory.protocol.v1.ConsultCurrentSsinRequest;
import be.fgov.ehealth.consultrn.ssinhistory.protocol.v1.ConsultCurrentSsinResponse;
import be.fgov.ehealth.consultrn.ssinhistory.protocol.v1.ConsultRelatedSsinsRequest;
import be.fgov.ehealth.consultrn.ssinhistory.protocol.v1.ConsultRelatedSsinsResponse;

public interface SsinHistoryTokenService {
   ConsultRelatedSsinsResponse consultRelatedSsins(SAMLToken var1, ConsultRelatedSsinsRequest var2) throws TechnicalConnectorException;

   ConsultCurrentSsinResponse consultCurrentSsin(SAMLToken var1, ConsultCurrentSsinRequest var2) throws TechnicalConnectorException;
}
