package org.taktik.connector.business.mycarenet.attestv2.session;

import be.fgov.ehealth.mycarenet.attest.protocol.v2.CancelAttestationResponse;
import be.fgov.ehealth.mycarenet.attest.protocol.v2.SendAttestationResponse;
import be.fgov.ehealth.mycarenet.commons.protocol.v3.SendRequestType;
import org.taktik.connector.business.mycarenet.attestv2.exception.AttestBusinessConnectorException;
import org.taktik.connector.technical.exception.TechnicalConnectorException;

public interface AttestService {
   SendAttestationResponse sendAttestation(SendRequestType var1) throws AttestBusinessConnectorException, TechnicalConnectorException;

   CancelAttestationResponse cancelAttestation(SendRequestType var1) throws AttestBusinessConnectorException, TechnicalConnectorException;
}
