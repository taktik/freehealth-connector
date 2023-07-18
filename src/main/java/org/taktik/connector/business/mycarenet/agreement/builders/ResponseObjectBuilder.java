package org.taktik.connector.business.mycarenet.agreement.builders;

import be.ehealth.businessconnector.mycarenet.agreement.domain.AskAgreementBuilderRequest;
import be.ehealth.businessconnector.mycarenet.agreement.domain.ConsultAgreementBuilderRequest;
import be.ehealth.businessconnector.mycarenet.agreement.domain.SignedEncryptedBuilderResponse;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.fgov.ehealth.mycarenet.agreement.protocol.v1.AskAgreementResponse;
import be.fgov.ehealth.mycarenet.agreement.protocol.v1.ConsultAgreementResponse;

public interface ResponseObjectBuilder {
   SignedEncryptedBuilderResponse handleAskAgreementResponse(AskAgreementResponse var1, AskAgreementBuilderRequest var2) throws TechnicalConnectorException;

   SignedEncryptedBuilderResponse handleConsultAgreementResponse(ConsultAgreementResponse var1, ConsultAgreementBuilderRequest var2) throws TechnicalConnectorException;
}
