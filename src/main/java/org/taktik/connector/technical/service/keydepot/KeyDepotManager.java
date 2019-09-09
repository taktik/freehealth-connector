package org.taktik.connector.technical.service.keydepot;

import org.taktik.connector.technical.exception.TechnicalConnectorException;
import org.taktik.connector.technical.service.etee.domain.EncryptionToken;
import org.taktik.connector.technical.service.sts.security.Credential;
import org.taktik.connector.technical.utils.IdentifierType;

import javax.annotation.Nullable;
import java.util.Set;
import java.util.UUID;

public interface KeyDepotManager {
   EncryptionToken getETK(Credential cred, @Nullable UUID keystoreId) throws TechnicalConnectorException;

   EncryptionToken getEtk(IdentifierType identifierType, Long identifierValue, String application, @Nullable UUID keystoreId) throws TechnicalConnectorException;

   Set<EncryptionToken> getEtkSet(IdentifierType identifierType, Long identifierValue, String application, @Nullable UUID keystoreId) throws TechnicalConnectorException;

   void setKeyDepotService(KeyDepotService var1);

   public static enum EncryptionTokenType {
      HOLDER_OF_KEY,
      ENCRYPTION;
   }
}
