package be.ehealth.technicalconnector.service.keydepot;

import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.service.etee.domain.EncryptionToken;
import be.ehealth.technicalconnector.utils.IdentifierType;
import be.fgov.ehealth.etkdepot._1_0.protocol.GetEtkRequest;
import be.fgov.ehealth.etkdepot._1_0.protocol.GetEtkResponse;
import java.io.Serializable;
import java.util.Set;

public interface KeyDepotService extends Serializable {
   GetEtkResponse getETK(GetEtkRequest var1) throws TechnicalConnectorException;

   Set<EncryptionToken> getETKSet(IdentifierType var1, String var2, String var3) throws TechnicalConnectorException;
}
