package org.taktik.connector.technical.session;

import org.taktik.connector.technical.exception.TechnicalConnectorException;
import org.taktik.connector.technical.service.etee.Crypto;
import org.taktik.connector.technical.service.sts.security.Credential;
import org.taktik.connector.technical.service.sts.security.SAMLToken;
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
