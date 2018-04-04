package org.taktik.connector.technical.service.sts.security;

import org.taktik.connector.technical.exception.TechnicalConnectorException;
import java.security.Provider;

public interface ProviderAdaptor {
   Provider getProvider() throws TechnicalConnectorException;
}
