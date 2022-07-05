package be.ehealth.businessconnector.mycarenet.attestv3.service;

import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.service.sts.security.SAMLToken;
import be.fgov.ehealth.mycarenet.attest.protocol.v3.CancelAttestationResponse;
import be.fgov.ehealth.mycarenet.attest.protocol.v3.SendAttestationResponse;
import be.fgov.ehealth.mycarenet.commons.protocol.v4.SendRequestType;

public interface AttestService {
   SendAttestationResponse sendAttestion(SAMLToken var1, SendRequestType var2) throws TechnicalConnectorException;

   CancelAttestationResponse cancelAttestion(SAMLToken var1, SendRequestType var2) throws TechnicalConnectorException;
}
