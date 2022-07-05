package be.ehealth.technicalconnector.service.etee.impl;

import be.ehealth.technicalconnector.exception.CertificateVerificationException;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.exception.TechnicalConnectorExceptionValues;
import be.ehealth.technicalconnector.service.etee.CertificateChecker;
import be.ehealth.technicalconnector.service.etee.CryptoFactory;
import be.ehealth.technicalconnector.service.etee.RevocationStatusCheckerFactory;
import be.fgov.ehealth.etee.crypto.cert.CertPathCheckerBuilder;
import be.fgov.ehealth.etee.crypto.cert.CertificateStatus;
import be.fgov.ehealth.etee.crypto.status.CryptoResult;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.List;
import org.joda.time.DateTime;

public class ConnectorCertificateChecker implements CertificateChecker {
   public ConnectorCertificateChecker() {
   }

   public boolean isCertificateRevoked(File certFile) throws TechnicalConnectorException {
      return this.isCertificateRevoked(certFile, new DateTime());
   }

   public boolean isCertificateRevoked(File certFile, DateTime validOn) throws TechnicalConnectorException {
      try {
         CertificateFactory certFactory = CertificateFactory.getInstance("X.509");
         X509Certificate cert = (X509Certificate)certFactory.generateCertificate(new FileInputStream(certFile));
         return this.isCertificateRevoked(cert, validOn);
      } catch (FileNotFoundException var5) {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_GENERAL, var5, new Object[]{var5.getMessage()});
      } catch (CertificateException var6) {
         throw new CertificateVerificationException(var6.getMessage(), var6);
      }
   }

   public boolean isCertificateRevoked(X509Certificate cert) throws TechnicalConnectorException {
      return this.isCertificateRevoked(cert, new DateTime());
   }

   public boolean isCertificateRevoked(X509Certificate cert, DateTime validOn) throws TechnicalConnectorException {
      try {
         return RevocationStatusCheckerFactory.getStatusChecker().isRevoked(cert, validOn);
      } catch (CertificateException var4) {
         throw new CertificateVerificationException(var4.getMessage(), var4);
      }
   }

   public boolean isValidCertificateChain(List<X509Certificate> certificateChain) throws TechnicalConnectorException {
      CryptoResult<CertificateStatus> result = CertPathCheckerBuilder.newBuilder().addTrustStore(CryptoFactory.getCaCertificateStore()).build().validate(certificateChain);
      return !result.hasErrors();
   }
}
