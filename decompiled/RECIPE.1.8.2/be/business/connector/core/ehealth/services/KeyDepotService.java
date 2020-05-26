package be.business.connector.core.ehealth.services;

import be.business.connector.core.domain.KgssIdentifierType;
import be.business.connector.core.exceptions.IntegrationModuleException;
import be.fgov.ehealth.etee.crypto.encrypt.EncryptionToken;
import java.security.GeneralSecurityException;
import java.util.List;

public interface KeyDepotService {
   List<EncryptionToken> retrieveEtk(KgssIdentifierType var1, String var2, String var3) throws IntegrationModuleException, GeneralSecurityException;
}
