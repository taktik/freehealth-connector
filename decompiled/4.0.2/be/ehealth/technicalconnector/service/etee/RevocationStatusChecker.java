package be.ehealth.technicalconnector.service.etee;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import org.joda.time.DateTime;

public interface RevocationStatusChecker {
   boolean isRevoked(X509Certificate var1) throws CertificateException;

   boolean isRevoked(X509Certificate var1, DateTime var2) throws CertificateException;
}
