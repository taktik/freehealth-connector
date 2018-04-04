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
      try {
         this.crlChecker = CRLCheckerBuilder.newBuilder().addCertStore((CertStore)CryptoFactory.getOCSPOptions().get(OCSPOption.CERT_STORE)).build();
      } catch (TechnicalConnectorException var2) {
         LOG.warn("Unable to obtain CertStore");
         this.crlChecker = CRLCheckerBuilder.newBuilder().build();
      }

   }

   boolean delegateRevoke(X509Certificate cert, DateTime validOn) throws CertificateException {
      CryptoResult<CRLData> crlData = this.crlChecker.validate(cert);
      if (crlData.getFatal() == null) {
         switch(((CRLData)crlData.getData()).getCertStatus()) {
         case REVOKED:
            return true;
         default:
            return false;
         }
      } else {
         throw new CertificateException(crlData.getFatal().getErrorMessage());
      }
   }

   // $FF: synthetic class
   static class SyntheticClass_1 {
      // $FF: synthetic field
      static final int[] $SwitchMap$be$fgov$ehealth$etee$crypto$cert$CertificateStatus = new int[CertificateStatus.values().length];

      static {
         try {
            $SwitchMap$be$fgov$ehealth$etee$crypto$cert$CertificateStatus[CertificateStatus.REVOKED.ordinal()] = 1;
         } catch (NoSuchFieldError var1) {
            ;
         }

      }
   }
}
