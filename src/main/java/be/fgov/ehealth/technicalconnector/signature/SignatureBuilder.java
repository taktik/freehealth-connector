package be.fgov.ehealth.technicalconnector.signature;

import org.taktik.connector.technical.exception.TechnicalConnectorException;
import org.taktik.connector.technical.service.sts.security.Credential;
import be.fgov.ehealth.technicalconnector.signature.domain.SignatureVerificationResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.util.Map;

public interface SignatureBuilder {
   byte[] sign(Credential var1, byte[] var2) throws TechnicalConnectorException;

   byte[] sign(Credential var1, byte[] var2, Map<String, Object> var3) throws TechnicalConnectorException;

   SignatureVerificationResult verify(byte[] var1, byte[] var2, Map<String, Object> var3) throws TechnicalConnectorException;

   SignatureVerificationResult verify(byte[] var1, Map<String, Object> var2) throws TechnicalConnectorException;

   SignatureVerificationResult verify(Document var1, Element var2, Map<String, Object> var3) throws TechnicalConnectorException;

   AdvancedElectronicSignatureEnumeration getSupportedAES();
}
