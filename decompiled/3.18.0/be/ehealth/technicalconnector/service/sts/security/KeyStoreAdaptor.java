package be.ehealth.technicalconnector.service.sts.security;

import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import java.security.KeyStore;
import java.security.KeyStoreException;

public interface KeyStoreAdaptor {
   KeyStore getKeyStore() throws KeyStoreException, TechnicalConnectorException;
}
