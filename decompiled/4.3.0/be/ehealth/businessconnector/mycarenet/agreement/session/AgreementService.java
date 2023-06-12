package be.ehealth.businessconnector.mycarenet.agreement.session;

import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.fgov.ehealth.mycarenet.agreement.protocol.v1.AskAgreementRequest;
import be.fgov.ehealth.mycarenet.agreement.protocol.v1.AskAgreementResponse;
import be.fgov.ehealth.mycarenet.agreement.protocol.v1.ConsultAgreementRequest;
import be.fgov.ehealth.mycarenet.agreement.protocol.v1.ConsultAgreementResponse;

public interface AgreementService {
   AskAgreementResponse askAgreement(AskAgreementRequest var1) throws TechnicalConnectorException;

   ConsultAgreementResponse consultAgreement(ConsultAgreementRequest var1) throws TechnicalConnectorException;
}
