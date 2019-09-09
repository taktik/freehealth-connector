package org.taktik.connector.business.consultrn.service;

import org.taktik.connector.business.consultrn.exception.identifyperson.ConsultrnIdentifyPersonException;
import org.taktik.connector.business.consultrn.exception.manageperson.ConsultrnRegisterExistingPersonException;
import org.taktik.connector.business.consultrn.exception.manageperson.ConsultrnRegisterPersonException;
import org.taktik.connector.business.consultrn.exception.phoneticsearch.ConsultrnPhoneticSearchException;
import org.taktik.connector.technical.exception.TechnicalConnectorException;
import org.taktik.connector.technical.service.sts.security.SAMLToken;
import be.fgov.ehealth.consultrn._1_0.protocol.SearchBySSINReply;
import be.fgov.ehealth.consultrn._1_0.protocol.SearchBySSINRequest;
import be.fgov.ehealth.consultrn._1_0.protocol.SearchPhoneticReply;
import be.fgov.ehealth.consultrn._1_0.protocol.SearchPhoneticRequest;
import be.fgov.ehealth.consultrn.protocol.v2.RegisterPersonRequest;
import be.fgov.ehealth.consultrn.protocol.v2.RegisterPersonResponse;

public interface ConsultrnService {
   SearchBySSINReply search(SAMLToken samlToken, SearchBySSINRequest var2) throws TechnicalConnectorException, ConsultrnIdentifyPersonException;

   SearchPhoneticReply search(SAMLToken samlToken, SearchPhoneticRequest var2) throws TechnicalConnectorException, ConsultrnPhoneticSearchException;

   RegisterPersonResponse registerPerson(SAMLToken samlToken, RegisterPersonRequest var2) throws TechnicalConnectorException, ConsultrnRegisterPersonException, ConsultrnRegisterExistingPersonException;
}
