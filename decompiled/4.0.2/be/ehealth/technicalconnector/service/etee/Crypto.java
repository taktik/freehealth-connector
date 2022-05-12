package be.ehealth.technicalconnector.service.etee;

import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.service.etee.domain.EncryptionToken;
import be.ehealth.technicalconnector.service.etee.domain.UnsealedData;
import be.ehealth.technicalconnector.service.kgss.domain.KeyResult;
import be.ehealth.technicalconnector.utils.ConfigurableImplementation;
import java.util.Set;

public interface Crypto extends ConfigurableImplementation {
   String DATAUNSEALER_PKMAP = "dataunsealer.pkmap";
   String DATASEALER_CREDENTIAL = "datasealer.credential";
   String OCSP_OPTIONMAP = "cryptolib.ocsp.optionmap";
   String SIGNING_OPTIONMAP = "cryptolib.signing.optionmap";
   String OCSP_POLICY = "cryptolib.ocsp.policy";

   byte[] seal(SigningPolicySelector var1, EncryptionToken var2, byte[] var3) throws TechnicalConnectorException;

   byte[] seal(SigningPolicySelector var1, Set<EncryptionToken> var2, byte[] var3) throws TechnicalConnectorException;

   byte[] seal(SigningPolicySelector var1, KeyResult var2, byte[] var3) throws TechnicalConnectorException;

   byte[] seal(SigningPolicySelector var1, Set<EncryptionToken> var2, KeyResult var3, byte[] var4) throws TechnicalConnectorException;

   UnsealedData unseal(SigningPolicySelector var1, byte[] var2) throws TechnicalConnectorException;

   UnsealedData unseal(SigningPolicySelector var1, KeyResult var2, byte[] var3) throws TechnicalConnectorException;

   public static enum SigningPolicySelector {
      WITH_NON_REPUDIATION,
      WITHOUT_NON_REPUDIATION;

      private SigningPolicySelector() {
      }
   }
}
