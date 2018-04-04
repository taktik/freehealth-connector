package org.taktik.connector.technical.service.keydepot;

import org.taktik.connector.technical.exception.TechnicalConnectorException;
import org.taktik.connector.technical.service.etee.domain.EncryptionToken;
import org.taktik.connector.technical.utils.IdentifierType;
import java.util.Set;

public interface KeyDepotManager {
   /** @deprecated */
   @Deprecated
   EncryptionToken getHolderOfKeyETK() throws TechnicalConnectorException;

   /** @deprecated */
   @Deprecated
   EncryptionToken getEncryptionETK() throws TechnicalConnectorException;

   EncryptionToken getETK(KeyDepotManager.EncryptionTokenType var1) throws TechnicalConnectorException;

   EncryptionToken getEtk(IdentifierType var1, Long var2, String var3) throws TechnicalConnectorException;

   Set<EncryptionToken> getEtkSet(IdentifierType var1, Long var2, String var3) throws TechnicalConnectorException;

   void setKeyDepotService(KeyDepotService var1);

   public static enum EncryptionTokenType {
      HOLDER_OF_KEY,
      ENCRYPTION;
   }
}
