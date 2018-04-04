package org.taktik.connector.technical.service.sts.security;

import org.taktik.connector.technical.exception.TechnicalConnectorException;
import java.security.KeyStore;
import java.security.KeyStoreException;

public interface KeyStoreAdaptor {
   KeyStore getKeyStore() throws KeyStoreException, TechnicalConnectorException;
}
