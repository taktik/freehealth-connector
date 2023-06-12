package be.ehealth.businessconnector.mycarenet.agreement.service;

import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.service.sts.security.SAMLToken;
import be.fgov.ehealth.mycarenet.agreement.protocol.v1.AskAgreementRequest;
import be.fgov.ehealth.mycarenet.agreement.protocol.v1.AskAgreementResponse;
import be.fgov.ehealth.mycarenet.agreement.protocol.v1.ConsultAgreementRequest;
import be.fgov.ehealth.mycarenet.agreement.protocol.v1.ConsultAgreementResponse;

public interface AgreementService {
   AskAgreementResponse askAgreement(SAMLToken var1, AskAgreementRequest var2) throws TechnicalConnectorException;

   ConsultAgreementResponse consultAgreement(SAMLToken var1, ConsultAgreementRequest var2) throws TechnicalConnectorException;
}
