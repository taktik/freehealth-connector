package org.taktik.connector.technical.service.seals;

import org.taktik.connector.technical.exception.TechnicalConnectorException;
import be.fgov.ehealth.seals.protocol.v1.DecodeRequest;
import be.fgov.ehealth.seals.protocol.v1.DecodeResponse;
import be.fgov.ehealth.seals.protocol.v1.EncodeRequest;
import be.fgov.ehealth.seals.protocol.v1.EncodeResponse;
import java.security.PrivateKey;
import java.security.cert.X509Certificate;

public interface SealsService {
   EncodeResponse encode(X509Certificate var1, PrivateKey var2, EncodeRequest var3) throws TechnicalConnectorException;

   DecodeResponse decode(X509Certificate var1, PrivateKey var2, DecodeRequest var3) throws TechnicalConnectorException;
}
