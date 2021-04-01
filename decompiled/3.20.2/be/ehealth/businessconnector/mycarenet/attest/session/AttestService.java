package be.ehealth.businessconnector.mycarenet.attest.session;

import be.ehealth.businessconnector.mycarenet.attest.exception.AttestBusinessConnectorException;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.fgov.ehealth.mycarenet.attest.protocol.v1.SendAttestationRequest;
import be.fgov.ehealth.mycarenet.attest.protocol.v1.SendAttestationResponse;

public interface AttestService {
   SendAttestationResponse sendAttestation(SendAttestationRequest var1) throws AttestBusinessConnectorException, TechnicalConnectorException;
}
