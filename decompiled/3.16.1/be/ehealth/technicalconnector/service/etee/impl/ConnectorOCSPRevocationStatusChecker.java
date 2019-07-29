package be.ehealth.technicalconnector.service.etee.impl;

import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.service.etee.CryptoFactory;
import be.fgov.ehealth.etee.crypto.cert.CertificateStatus;
import be.fgov.ehealth.etee.crypto.ocsp.OCSPChecker;
import be.fgov.ehealth.etee.crypto.ocsp.OCSPCheckerBuilder;
import be.fgov.ehealth.etee.crypto.ocsp.OCSPData;
import be.fgov.ehealth.etee.crypto.ocsp.RevocationValues;
import be.fgov.ehealth.etee.crypto.policies.OCSPPolicy;
import be.fgov.ehealth.etee.crypto.status.CryptoResult;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class ConnectorOCSPRevocationStatusChecker extends AbstractRevocationStatusChecker {
   private static final Logger LOG = LoggerFactory.getLogger(ConnectorOCSPRevocationStatusChecker.class);
   private OCSPChecker ocspchecker;

   public ConnectorOCSPRevocationStatusChecker() {
      HashMap options = new HashMap();

      try {
         options.putAll(CryptoFactory.getOCSPOptions());
      } catch (TechnicalConnectorException var3) {
         LOG.warn("Unable to load ocsp options.", var3);
      }

      this.ocspchecker = OCSPCheckerBuilder.newBuilder().addOCSPPolicy(OCSPPolicy.RECEIVER_MANDATORY, options).build();
   }

   boolean delegateRevoke(X509Certificate cert, DateTime validOn) throws CertificateException {
      CryptoResult<OCSPData> result = this.ocspchecker.validate(cert, validOn.toDate(), new RevocationValues());
      if (result.getFatal() == null && result.getData() != null) {
         return !((OCSPData)result.getData()).getCertStatus().equals(CertificateStatus.VALID);
      } else {
         throw new CertificateException(result.toString());
      }
   }
}
