package org.taktik.connector.business.recipeprojects.core.ehealth.services;

import org.taktik.connector.business.recipeprojects.core.domain.KgssIdentifierType;
import org.taktik.connector.business.recipeprojects.core.exceptions.IntegrationModuleException;
import be.fgov.ehealth.etee.crypto.encrypt.EncryptionToken;
import java.security.GeneralSecurityException;
import java.util.List;

public interface KeyDepotService {
   List<EncryptionToken> retrieveEtk(KgssIdentifierType var1, String var2, String var3) throws IntegrationModuleException, GeneralSecurityException;
}
