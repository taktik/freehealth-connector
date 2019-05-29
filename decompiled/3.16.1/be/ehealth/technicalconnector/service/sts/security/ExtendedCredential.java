package be.ehealth.technicalconnector.service.sts.security;

import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import java.security.cert.CertPath;
import org.joda.time.DateTime;

public interface ExtendedCredential extends Credential {
   DateTime getExpirationDateTime() throws TechnicalConnectorException;

   CertPath getCertPath() throws TechnicalConnectorException;
}
