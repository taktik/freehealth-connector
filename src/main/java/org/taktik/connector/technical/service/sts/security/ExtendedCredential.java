package org.taktik.connector.technical.service.sts.security;

import org.taktik.connector.technical.exception.TechnicalConnectorException;
import java.security.cert.CertPath;
import org.joda.time.DateTime;

public interface ExtendedCredential extends Credential {
   DateTime getExpirationDateTime() throws TechnicalConnectorException;

   CertPath getCertPath() throws TechnicalConnectorException;
}
