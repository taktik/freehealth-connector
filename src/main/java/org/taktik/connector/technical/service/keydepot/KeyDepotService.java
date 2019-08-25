package org.taktik.connector.technical.service.keydepot;

import org.taktik.connector.technical.exception.TechnicalConnectorException;
import org.taktik.connector.technical.service.etee.domain.EncryptionToken;

import javax.annotation.Nullable;
import java.io.Serializable;
import java.util.Set;
import java.util.UUID;

public interface KeyDepotService extends Serializable {
   /**
    *
    * @param identifierType Used in searchCriteria
    * @param identifierValue Used in searchCriteria
    * @param applicationId Used in searchCriteria
    * @param keystoreId keystore of the HCP making the request. Allows for long lived cache of own etks
    * @param isOwnEtk used to cache etks in the long lived cache. Must be false except if we are looking for the holder of key etk
    * @return
    * @throws TechnicalConnectorException
    */
   Set<EncryptionToken> getETKSet(org.taktik.connector.technical.utils.IdentifierType identifierType, String identifierValue, String applicationId, @Nullable UUID keystoreId, boolean isOwnEtk) throws TechnicalConnectorException;
}
