package org.taktik.connector.business.mycarenet.agreement.service;

import be.fgov.ehealth.agreement.protocol.v1.AskAgreementRequest;
import be.fgov.ehealth.agreement.protocol.v1.AskAgreementResponse;
import be.fgov.ehealth.agreement.protocol.v1.ConsultAgreementRequest;
import be.fgov.ehealth.agreement.protocol.v1.ConsultAgreementResponse;
import org.taktik.connector.technical.exception.TechnicalConnectorException;
import org.taktik.connector.technical.service.sts.security.SAMLToken;

public interface AgreementService {
   AskAgreementResponse askAgreement(SAMLToken var1, AskAgreementRequest var2) throws TechnicalConnectorException;

   ConsultAgreementResponse consultAgreement(SAMLToken var1, ConsultAgreementRequest var2) throws TechnicalConnectorException;
}
