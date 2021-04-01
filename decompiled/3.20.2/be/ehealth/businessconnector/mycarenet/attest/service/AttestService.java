package be.ehealth.businessconnector.mycarenet.attest.service;

import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.service.sts.security.SAMLToken;
import be.fgov.ehealth.mycarenet.attest.protocol.v1.SendAttestationRequest;
import be.fgov.ehealth.mycarenet.attest.protocol.v1.SendAttestationResponse;

public interface AttestService {
   SendAttestationResponse sendAttestion(SAMLToken var1, SendAttestationRequest var2) throws TechnicalConnectorException;
}
