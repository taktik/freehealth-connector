package be.ehealth.technicalconnector.service.etee.impl;

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
import org.joda.time.DateTime;

public final class ConnectorOCSPRevocationStatusChecker extends AbstractRevocationStatusChecker {
   boolean delegateRevoke(X509Certificate cert, DateTime validOn) throws CertificateException {
      OCSPChecker ocspChecker = OCSPCheckerBuilder.newBuilder().addOCSPPolicy(OCSPPolicy.RECEIVER_MANDATORY, CryptoFactory.getOCSPOptions()).build();
      CryptoResult<OCSPData> result = ocspChecker.validate(cert, validOn.toDate(), new RevocationValues());
      if (result.getFatal() == null && result.getData() != null) {
         CertificateStatus certStatus = ((OCSPData)result.getData()).getCertStatus();
         return !certStatus.equals(CertificateStatus.VALID);
      } else {
         throw new CertificateException(result.toString());
      }
   }
}
