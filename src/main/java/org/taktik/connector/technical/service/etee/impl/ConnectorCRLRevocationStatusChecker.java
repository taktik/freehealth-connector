package org.taktik.connector.technical.service.etee.impl;

import org.taktik.connector.technical.exception.TechnicalConnectorException;
import org.taktik.connector.technical.service.etee.CryptoFactory;
import be.fgov.ehealth.etee.crypto.cert.CertificateStatus;
import be.fgov.ehealth.etee.crypto.crl.CRLChecker;
import be.fgov.ehealth.etee.crypto.crl.CRLCheckerBuilder;
import be.fgov.ehealth.etee.crypto.crl.CRLData;
import be.fgov.ehealth.etee.crypto.policies.OCSPOption;
import be.fgov.ehealth.etee.crypto.status.CryptoResult;
import java.security.cert.CertStore;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class ConnectorCRLRevocationStatusChecker extends AbstractRevocationStatusChecker {
   private static final Logger LOG = LoggerFactory.getLogger(ConnectorCRLRevocationStatusChecker.class);
   private CRLChecker crlChecker;

   public ConnectorCRLRevocationStatusChecker() {
      this.crlChecker = CRLCheckerBuilder.newBuilder().addCertStore((CertStore)CryptoFactory.getOCSPOptions().get(OCSPOption.CERT_STORE)).build();
   }

   boolean delegateRevoke(X509Certificate cert, DateTime validOn) throws CertificateException {
      CryptoResult<CRLData> crlData = this.crlChecker.validate(cert);
      if (crlData.getFatal() == null) {
         return ((CRLData)crlData.getData()).getCertStatus() == CertificateStatus.REVOKED;
      } else {
         throw new CertificateException(crlData.getFatal().getErrorMessage());
      }
   }
}
