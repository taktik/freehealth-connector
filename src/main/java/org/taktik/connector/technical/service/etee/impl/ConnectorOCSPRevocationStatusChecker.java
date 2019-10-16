package org.taktik.connector.technical.service.etee.impl;

import org.taktik.connector.technical.exception.TechnicalConnectorException;
import org.taktik.connector.technical.service.etee.CryptoFactory;
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
   boolean delegateRevoke(X509Certificate cert, DateTime validOn) throws CertificateException {
      CryptoResult<OCSPData> result = OCSPCheckerBuilder.newBuilder().addOCSPPolicy(OCSPPolicy.RECEIVER_MANDATORY, CryptoFactory.getOCSPOptions()).build().validate(cert, validOn.toDate(), new RevocationValues());
      if (result.getFatal() == null && result.getData() != null) {
         return !result.getData().getCertStatus().equals(CertificateStatus.VALID);
      } else {
         throw new CertificateException(result.toString());
      }
   }
}
