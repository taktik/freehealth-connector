package be.ehealth.technicalconnector.service.keydepot;

import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.service.etee.domain.EncryptionToken;
import be.ehealth.technicalconnector.utils.IdentifierType;
import java.util.Set;

public interface KeyDepotManager {
   /** @deprecated */
   @Deprecated
   EncryptionToken getHolderOfKeyETK() throws TechnicalConnectorException;

   /** @deprecated */
   @Deprecated
   EncryptionToken getEncryptionETK() throws TechnicalConnectorException;

   EncryptionToken getETK(KeyDepotManager.EncryptionTokenType var1) throws TechnicalConnectorException;

   EncryptionToken getETK(IdentifierType var1, Long var2, String var3) throws TechnicalConnectorException;

   /** @deprecated */
   @Deprecated
   EncryptionToken getEtk(IdentifierType var1, Long var2, String var3) throws TechnicalConnectorException;

   Set<EncryptionToken> getETKs(IdentifierType var1, Long var2, String var3) throws TechnicalConnectorException;

   /** @deprecated */
   @Deprecated
   Set<EncryptionToken> getEtkSet(IdentifierType var1, Long var2, String var3) throws TechnicalConnectorException;

   void setKeyDepotService(KeyDepotService var1);

   public static enum EncryptionTokenType {
      HOLDER_OF_KEY,
      ENCRYPTION;
   }
}
