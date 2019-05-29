package be.fgov.ehealth.technicalconnector.signature;

import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.service.sts.security.Credential;
import be.fgov.ehealth.technicalconnector.signature.domain.SignatureVerificationResult;
import java.util.Map;

public interface SignatureBuilder {
   byte[] sign(Credential var1, byte[] var2) throws TechnicalConnectorException;

   byte[] sign(Credential var1, byte[] var2, Map<String, Object> var3) throws TechnicalConnectorException;

   SignatureVerificationResult verify(byte[] var1, byte[] var2, Map<String, Object> var3) throws TechnicalConnectorException;

   SignatureVerificationResult verify(byte[] var1, Map<String, Object> var2) throws TechnicalConnectorException;

   AdvancedElectronicSignatureEnumeration getSupportedAES();
}
