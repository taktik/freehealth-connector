package be.ehealth.business.mycarenetcommons.security;

import be.ehealth.business.mycarenetcommons.builders.util.RequestBuilderUtil;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.service.etee.Crypto;

public class EncryptionHelper {
   public EncryptionHelper() {
   }

   public byte[] encrypt(byte[] request, Crypto crypto, String projectIdentifier) throws TechnicalConnectorException {
      return crypto.seal(Crypto.SigningPolicySelector.WITH_NON_REPUDIATION, RequestBuilderUtil.getEtk(projectIdentifier), request);
   }
}
