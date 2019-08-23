package org.taktik.connector.technical.service.keydepot;

import org.taktik.connector.technical.exception.TechnicalConnectorException;
import org.taktik.connector.technical.service.etee.domain.EncryptionToken;
import org.taktik.connector.technical.utils.IdentifierType;
import be.fgov.ehealth.etkdepot._1_0.protocol.GetEtkRequest;
import be.fgov.ehealth.etkdepot._1_0.protocol.GetEtkResponse;
import java.io.Serializable;
import java.util.Set;

public interface KeyDepotService extends Serializable {
   Set<EncryptionToken> getETKSet(org.taktik.connector.technical.utils.IdentifierType identifierType, String identifierValue, String applicationId) throws TechnicalConnectorException;
}
