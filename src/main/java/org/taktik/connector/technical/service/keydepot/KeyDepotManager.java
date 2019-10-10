package org.taktik.connector.technical.service.keydepot;

import org.taktik.connector.technical.exception.TechnicalConnectorException;
import org.taktik.connector.technical.service.etee.domain.EncryptionToken;
import org.taktik.connector.technical.service.sts.security.Credential;
import org.taktik.connector.technical.utils.IdentifierType;

import javax.annotation.Nullable;
import java.util.Set;
import java.util.UUID;

public interface KeyDepotManager {
   EncryptionToken getETK(Credential cred, UUID keystoreId) throws TechnicalConnectorException;

   EncryptionToken getEtk(IdentifierType identifierType, Long identifierValue, String application, UUID keystoreId, boolean isOwnEtk) throws TechnicalConnectorException;

   Set<EncryptionToken> getEtkSet(IdentifierType identifierType, Long identifierValue, String application, UUID keystoreId, boolean isOwnEtk) throws TechnicalConnectorException;

   void setKeyDepotService(KeyDepotService var1);

   void flushCache();

   public static enum EncryptionTokenType {
      HOLDER_OF_KEY,
      ENCRYPTION;
   }
}
