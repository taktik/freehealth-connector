package be.ehealth.technicalconnector.beid;

import be.ehealth.technicalconnector.beid.domain.BeIDInfo;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.session.SessionServiceWithCache;
import java.security.KeyStore;

public interface BeIDInstantiator extends SessionServiceWithCache {
   BeIDInfo instantiateBeIDInfo(String var1, boolean var2) throws TechnicalConnectorException;

   KeyStore instantiateKeyStore(String var1, boolean var2) throws TechnicalConnectorException;
}
