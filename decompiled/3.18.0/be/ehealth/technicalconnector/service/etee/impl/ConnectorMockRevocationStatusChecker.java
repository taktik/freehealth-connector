package be.ehealth.technicalconnector.service.etee.impl;

import be.ehealth.technicalconnector.service.etee.RevocationStatusChecker;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** @deprecated */
@Deprecated
public final class ConnectorMockRevocationStatusChecker implements RevocationStatusChecker {
   private static final Logger LOG = LoggerFactory.getLogger(ConnectorMockRevocationStatusChecker.class);

   public boolean isRevoked(X509Certificate x509certificate) throws CertificateException {
      LOG.warn("Revokation check is disabled.");
      return false;
   }

   public boolean isRevoked(X509Certificate cert, DateTime validOn) throws CertificateException {
      LOG.warn("Revokation check is disabled.");
      return false;
   }
}
