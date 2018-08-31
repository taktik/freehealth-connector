package org.taktik.connector.technical.service.etee;

import org.taktik.connector.technical.exception.TechnicalConnectorException;
import org.taktik.connector.technical.exception.UnsealConnectorException;
import org.taktik.connector.technical.service.etee.domain.EncryptionToken;
import org.taktik.connector.technical.service.etee.domain.UnsealedData;
import org.taktik.connector.technical.service.kgss.domain.KeyResult;
import org.taktik.connector.technical.utils.ConfigurableImplementation;
import java.security.Key;
import java.util.Set;
import javax.crypto.SecretKey;

public interface Crypto extends ConfigurableImplementation {
   String DATAUNSEALER_PKMAP = "dataunsealer.pkmap";
   String DATASEALER_CREDENTIAL = "datasealer.credential";
   String OCSP_OPTIONMAP = "cryptolib.ocsp.optionmap";
   String SIGNING_OPTIONMAP = "cryptolib.signing.optionmap";
   String OCSP_POLICY = "cryptolib.ocsp.policy";


   @Deprecated
   byte[] seal(EncryptionToken paramEncryptionToken, byte[] paramArrayOfByte) throws TechnicalConnectorException;

   @Deprecated
   byte[] seal(Set<EncryptionToken> paramEncryptionTokenSet, byte[] paramArrayOfByte) throws TechnicalConnectorException;

   @Deprecated
   byte[] seal(byte[] paramArrayOfByte, SecretKey secretKey, String base64encodedSymKekId) throws TechnicalConnectorException;

   @Deprecated
   byte[] seal(Set<EncryptionToken> paramEncryptionTokenSet, byte[] content, SecretKey secretKey, String base64encodedSymKekId) throws TechnicalConnectorException;

   byte[] seal(SigningPolicySelector type, KeyResult symmKey, byte[] content) throws TechnicalConnectorException;

   byte[] seal(SigningPolicySelector type, EncryptionToken encryptionToken, byte[] content) throws TechnicalConnectorException;

   byte[] seal(SigningPolicySelector type, Set<EncryptionToken> paramEncryptionTokenSet, byte[] content) throws TechnicalConnectorException;

   byte[] seal(SigningPolicySelector type, Set<EncryptionToken> paramEncryptionTokenSet, KeyResult symmKey, byte[] content) throws TechnicalConnectorException;

   @Deprecated
   byte[] unseal(byte[] protectedMessage) throws UnsealConnectorException, TechnicalConnectorException;

   @Deprecated
   byte[] unsealForUnknown(SecretKey key, byte[] protectedMessage) throws TechnicalConnectorException;

   UnsealedData unseal(Crypto.SigningPolicySelector type, byte[] protectedMessage) throws TechnicalConnectorException;

   UnsealedData unseal(Crypto.SigningPolicySelector type, KeyResult symmKey, byte[] protectedMessage) throws TechnicalConnectorException;

   @Deprecated
   Key generateSecretKey() throws TechnicalConnectorException;

   @Deprecated
   Key getSymmKey();

   public static enum SigningPolicySelector {
      WITH_NON_REPUDIATION,
      WITHOUT_NON_REPUDIATION;
   }
}
