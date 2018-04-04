package org.taktik.connector.business.ssinhistory.session;

import org.taktik.connector.technical.exception.TechnicalConnectorException;
import be.fgov.ehealth.consultrn.ssinhistory.protocol.v1.ConsultCurrentSsinRequest;
import be.fgov.ehealth.consultrn.ssinhistory.protocol.v1.ConsultCurrentSsinResponse;
import be.fgov.ehealth.consultrn.ssinhistory.protocol.v1.ConsultRelatedSsinsRequest;
import be.fgov.ehealth.consultrn.ssinhistory.protocol.v1.ConsultRelatedSsinsResponse;

public interface SsinHistorySessionService {
   ConsultRelatedSsinsResponse consultRelatedSsins(ConsultRelatedSsinsRequest var1) throws TechnicalConnectorException;

   ConsultCurrentSsinResponse consultCurrentSsinResponse(ConsultCurrentSsinRequest var1) throws TechnicalConnectorException;
}
