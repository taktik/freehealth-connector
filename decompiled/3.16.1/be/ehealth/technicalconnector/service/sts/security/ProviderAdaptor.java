package be.ehealth.technicalconnector.service.sts.security;

import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import java.security.Provider;

public interface ProviderAdaptor {
   Provider getProvider() throws TechnicalConnectorException;
}
