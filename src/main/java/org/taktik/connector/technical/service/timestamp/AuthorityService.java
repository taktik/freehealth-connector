package org.taktik.connector.technical.service.timestamp;

import org.taktik.connector.technical.exception.TechnicalConnectorException;
import java.security.PrivateKey;
import java.security.cert.X509Certificate;
import oasis.names.tc.dss._1_0.core.schema.SignRequest;
import oasis.names.tc.dss._1_0.core.schema.SignResponse;

public interface AuthorityService {
   SignResponse signRequest(X509Certificate var1, PrivateKey var2, SignRequest var3) throws TechnicalConnectorException;
}
