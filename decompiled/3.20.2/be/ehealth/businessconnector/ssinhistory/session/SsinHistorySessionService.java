package be.ehealth.businessconnector.ssinhistory.session;

import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.fgov.ehealth.consultrn.ssinhistory.protocol.v1.ConsultCurrentSsinRequest;
import be.fgov.ehealth.consultrn.ssinhistory.protocol.v1.ConsultCurrentSsinResponse;
import be.fgov.ehealth.consultrn.ssinhistory.protocol.v1.ConsultRelatedSsinsRequest;
import be.fgov.ehealth.consultrn.ssinhistory.protocol.v1.ConsultRelatedSsinsResponse;

public interface SsinHistorySessionService {
   ConsultRelatedSsinsResponse consultRelatedSsins(ConsultRelatedSsinsRequest var1) throws TechnicalConnectorException;

   ConsultCurrentSsinResponse consultCurrentSsinResponse(ConsultCurrentSsinRequest var1) throws TechnicalConnectorException;
}
