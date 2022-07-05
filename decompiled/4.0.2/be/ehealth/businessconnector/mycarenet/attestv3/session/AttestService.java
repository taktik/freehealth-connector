package be.ehealth.businessconnector.mycarenet.attestv3.session;

import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.fgov.ehealth.mycarenet.attest.protocol.v3.CancelAttestationResponse;
import be.fgov.ehealth.mycarenet.attest.protocol.v3.SendAttestationResponse;
import be.fgov.ehealth.mycarenet.commons.protocol.v4.SendRequestType;

public interface AttestService {
   SendAttestationResponse sendAttestation(SendRequestType var1) throws TechnicalConnectorException;

   CancelAttestationResponse cancelAttestation(SendRequestType var1) throws TechnicalConnectorException;
}
