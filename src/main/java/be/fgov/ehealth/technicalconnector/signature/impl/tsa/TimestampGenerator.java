package be.fgov.ehealth.technicalconnector.signature.impl.tsa;

import org.taktik.connector.technical.exception.TechnicalConnectorException;
import org.taktik.connector.technical.service.sts.security.Credential;

public interface TimestampGenerator {
   byte[] generate(String requestId, Credential credential, String digestAlgoUri, byte[] digest) throws TechnicalConnectorException;
}
