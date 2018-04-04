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

   /** @deprecated */
   @Deprecated
   byte[] seal(EncryptionToken var1, byte[] var2) throws TechnicalConnectorException;

   /** @deprecated */
   @Deprecated
   byte[] seal(Set<EncryptionToken> var1, byte[] var2) throws TechnicalConnectorException;

   /** @deprecated */
   @Deprecated
   byte[] seal(byte[] var1, SecretKey var2, String var3) throws TechnicalConnectorException;

   /** @deprecated */
   @Deprecated
   byte[] seal(Set<EncryptionToken> var1, byte[] var2, SecretKey var3, String var4) throws TechnicalConnectorException;

   byte[] seal(Crypto.SigningPolicySelector var1, EncryptionToken var2, byte[] var3) throws TechnicalConnectorException;

   byte[] seal(Crypto.SigningPolicySelector var1, Set<EncryptionToken> var2, byte[] var3) throws TechnicalConnectorException;

   byte[] seal(Crypto.SigningPolicySelector var1, KeyResult var2, byte[] var3) throws TechnicalConnectorException;

   byte[] seal(Crypto.SigningPolicySelector var1, Set<EncryptionToken> var2, KeyResult var3, byte[] var4) throws TechnicalConnectorException;

   /** @deprecated */
   @Deprecated
   byte[] unseal(byte[] var1) throws UnsealConnectorException, TechnicalConnectorException;

   /** @deprecated */
   @Deprecated
   byte[] unsealForUnknown(SecretKey var1, byte[] var2) throws TechnicalConnectorException;

   UnsealedData unseal(Crypto.SigningPolicySelector var1, byte[] var2) throws TechnicalConnectorException;

   UnsealedData unseal(Crypto.SigningPolicySelector var1, KeyResult var2, byte[] var3) throws TechnicalConnectorException;

   /** @deprecated */
   @Deprecated
   Key generateSecretKey() throws TechnicalConnectorException;

   /** @deprecated */
   @Deprecated
   Key getSymmKey();

   public static enum SigningPolicySelector {
      WITH_NON_REPUDIATION,
      WITHOUT_NON_REPUDIATION;
   }
}
