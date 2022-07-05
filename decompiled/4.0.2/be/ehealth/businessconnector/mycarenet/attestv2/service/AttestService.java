package be.ehealth.businessconnector.mycarenet.attestv2.service;

import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.service.sts.security.SAMLToken;
import be.fgov.ehealth.mycarenet.attest.protocol.v2.CancelAttestationResponse;
import be.fgov.ehealth.mycarenet.attest.protocol.v2.SendAttestationResponse;
import be.fgov.ehealth.mycarenet.commons.protocol.v3.SendRequestType;

public interface AttestService {
   SendAttestationResponse sendAttestion(SAMLToken var1, SendRequestType var2) throws TechnicalConnectorException;

   CancelAttestationResponse cancelAttestion(SAMLToken var1, SendRequestType var2) throws TechnicalConnectorException;
}
