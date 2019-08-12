package be.fgov.ehealth.technicalconnector.signature.impl;

import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.exception.TechnicalConnectorExceptionValues;
import be.ehealth.technicalconnector.service.etee.CertificateChecker;
import be.ehealth.technicalconnector.service.etee.CertificateCheckerFactory;
import be.ehealth.technicalconnector.service.sts.security.Credential;
import be.fgov.ehealth.etee.crypto.utils.SecurityConfiguration;
import be.fgov.ehealth.technicalconnector.signature.domain.SignatureVerificationError;
import be.fgov.ehealth.technicalconnector.signature.domain.SignatureVerificationResult;
import java.security.NoSuchProviderException;
import java.security.cert.CertPath;
import java.security.cert.CertificateException;
import java.security.cert.CertificateExpiredException;
import java.security.cert.CertificateFactory;
import java.security.cert.CertificateNotYetValidException;
import java.security.cert.X509Certificate;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AbstractSignatureBuilder {
   private static final Logger LOG = LoggerFactory.getLogger(AbstractSignatureBuilder.class);
   private static final CertificateFactory CF;

   protected void validateInput(Credential signatureCredential, byte[] byteArrayToSign) throws TechnicalConnectorException {
      if (byteArrayToSign != null && byteArrayToSign.length != 0) {
         if (signatureCredential == null) {
            throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_SIGNATURE, new Object[]{"invalid parameter : signatureCredential was null"});
         }
      } else {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_SIGNATURE, new Object[]{"invalid parameter : byteArrayToSign was null or empty"});
      }
   }

   protected void validateChain(SignatureVerificationResult result, Map<String, Object> options) throws TechnicalConnectorException {
      Integer duration = (Integer)SignatureUtils.getOption("SigningTimeClockSkewDuration", options, 5);
      TimeUnit timeUnit = (TimeUnit)SignatureUtils.getOption("SigningTimeClockSkewTimeUnit", options, TimeUnit.MINUTES);
      CertificateChecker certChecker = CertificateCheckerFactory.getCertificateChecker();
      Iterator i$ = result.getCertChain().iterator();

      while(i$.hasNext()) {
         X509Certificate cert = (X509Certificate)i$.next();

         try {
            cert.checkValidity(result.getVerifiedSigningTime(duration, timeUnit).toDate());
         } catch (CertificateExpiredException var10) {
            result.getErrors().add(SignatureVerificationError.CERTIFICATE_EXPIRED);
         } catch (CertificateNotYetValidException var11) {
            result.getErrors().add(SignatureVerificationError.CERTIFICATE_NOT_YET_VALID);
         }
      }

      try {
         if (!certChecker.isValidCertificateChain(result.getCertChain())) {
            result.getErrors().add(SignatureVerificationError.CERTIFICATE_CHAIN_NOT_TRUSTED);
         }

         this.validateEndCertificate(result, certChecker, duration, timeUnit);
      } catch (TechnicalConnectorException var9) {
         result.getErrors().add(SignatureVerificationError.CERTIFICATE_CHAIN_COULD_NOT_BE_VERIFIED);
      }

   }

   protected X509Certificate extractEndCertificate(List<X509Certificate> chain) throws CertificateException {
      CertPath certChain = CF.generateCertPath(chain);
      return (X509Certificate)certChain.getCertificates().get(0);
   }

   private void validateEndCertificate(SignatureVerificationResult result, CertificateChecker certChecker, Integer duration, TimeUnit timeUnit) throws TechnicalConnectorException {
      try {
         X509Certificate cert = this.extractEndCertificate(result.getCertChain());
         if (certChecker.isCertificateRevoked(cert, result.getVerifiedSigningTime(duration, timeUnit))) {
            result.getErrors().add(SignatureVerificationError.CERTIFICATE_REVOKED);
         }

         result.setSigningCert(cert);
      } catch (CertificateException var6) {
         LOG.error("EndCertificate invalid.", var6);
         result.getErrors().add(SignatureVerificationError.CERTIFICATE_COULD_NOT_BE_VERIFIED);
      }

   }

   static {
      try {
         SecurityConfiguration.configure();
         CF = CertificateFactory.getInstance("X.509", "BC");
      } catch (NoSuchProviderException var1) {
         throw new IllegalArgumentException(var1);
      } catch (CertificateException var2) {
         throw new IllegalArgumentException(var2);
      }
   }
}
