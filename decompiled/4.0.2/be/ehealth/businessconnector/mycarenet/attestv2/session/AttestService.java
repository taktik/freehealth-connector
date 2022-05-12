package be.ehealth.businessconnector.mycarenet.attestv2.session;

import be.ehealth.businessconnector.mycarenet.attestv2.exception.AttestBusinessConnectorException;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.fgov.ehealth.mycarenet.attest.protocol.v2.CancelAttestationResponse;
import be.fgov.ehealth.mycarenet.attest.protocol.v2.SendAttestationResponse;
import be.fgov.ehealth.mycarenet.commons.protocol.v3.SendRequestType;

public interface AttestService {
   SendAttestationResponse sendAttestation(SendRequestType var1) throws AttestBusinessConnectorException, TechnicalConnectorException;

   CancelAttestationResponse cancelAttestation(SendRequestType var1) throws AttestBusinessConnectorException, TechnicalConnectorException;
}
