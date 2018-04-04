package be.ehealth.technicalconnector.service.etee;

import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import java.io.File;
import java.security.cert.X509Certificate;
import java.util.List;
import org.joda.time.DateTime;

public interface CertificateChecker {
   boolean isCertificateRevoked(File var1) throws TechnicalConnectorException;

   boolean isCertificateRevoked(X509Certificate var1) throws TechnicalConnectorException;

   boolean isCertificateRevoked(File var1, DateTime var2) throws TechnicalConnectorException;

   boolean isCertificateRevoked(X509Certificate var1, DateTime var2) throws TechnicalConnectorException;

   boolean isValidCertificateChain(List<X509Certificate> var1) throws TechnicalConnectorException;
}
