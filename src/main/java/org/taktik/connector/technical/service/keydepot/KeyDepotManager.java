package org.taktik.connector.technical.service.keydepot;

import org.taktik.connector.technical.exception.TechnicalConnectorException;
import org.taktik.connector.technical.service.etee.domain.EncryptionToken;
import org.taktik.connector.technical.service.sts.security.Credential;
import org.taktik.connector.technical.utils.IdentifierType;
import java.util.Set;

public interface KeyDepotManager {

   EncryptionToken getETK(Credential cred) throws TechnicalConnectorException;
   EncryptionToken getEtk(IdentifierType identifierType, Long identifierValue, String application) throws TechnicalConnectorException;
   Set<EncryptionToken> getEtkSet(IdentifierType identifierType, Long identifierValue, String application) throws TechnicalConnectorException;


   enum EncryptionTokenType {
      HOLDER_OF_KEY,
      ENCRYPTION;
   }
}
