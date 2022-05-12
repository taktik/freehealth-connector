package be.ehealth.technicalconnector.service.etee.impl;

import be.ehealth.technicalconnector.service.etee.RevocationStatusChecker;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class ConnectorRevocationStatusChecker implements RevocationStatusChecker {
   private static final Logger LOG = LoggerFactory.getLogger(ConnectorRevocationStatusChecker.class);
   private RevocationStatusChecker ocsp = new ConnectorOCSPRevocationStatusChecker();
   private RevocationStatusChecker crl = new ConnectorCRLRevocationStatusChecker();

   public ConnectorRevocationStatusChecker() {
   }

   public boolean isRevoked(X509Certificate cert) throws CertificateException {
      return this.isRevoked(cert, new DateTime());
   }

   public boolean isRevoked(X509Certificate cert, DateTime validOn) throws CertificateException {
      if (cert == null) {
         throw new CertificateException("X509Certificate is empty.");
      } else {
         try {
            LOG.debug("Using ConnectorOCSPRevocationStatusChecker for RevocationCheck");
            return this.ocsp.isRevoked(cert, validOn);
         } catch (CertificateException var4) {
            LOG.debug("Using ConnectorCRLRevocationStatusChecker for RevocationCheck, OCSP failed Reason:[{}]", var4.getMessage());
            return this.crl.isRevoked(cert);
         }
      }
   }
}
