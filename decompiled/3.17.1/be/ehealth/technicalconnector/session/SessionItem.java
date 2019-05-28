package be.ehealth.technicalconnector.session;

import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.service.etee.Crypto;
import be.ehealth.technicalconnector.service.sts.security.Credential;
import be.ehealth.technicalconnector.service.sts.security.SAMLToken;
import java.security.PrivateKey;
import java.util.Map;

public interface SessionItem {
   void setSAMLToken(SAMLToken var1);

   SAMLToken getSAMLToken();

   void setHolderOfKeyCredential(Credential var1);

   Credential getHolderOfKeyCredential();

   void setHolderOfKeyPrivateKeys(Map<String, PrivateKey> var1);

   Map<String, PrivateKey> getHolderOfKeyPrivateKeys();

   void setEncryptionCredential(Credential var1);

   Credential getEncryptionCredential();

   void setEncryptionPrivateKeys(Map<String, PrivateKey> var1);

   Map<String, PrivateKey> getEncryptionPrivateKeys();

   Crypto getHolderOfKeyCrypto() throws TechnicalConnectorException;

   Crypto getEncryptionCrypto() throws TechnicalConnectorException;

   void setHeaderCredential(Credential var1) throws TechnicalConnectorException;

   Credential getHeaderCredential() throws TechnicalConnectorException;
}
