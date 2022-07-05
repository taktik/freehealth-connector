package be.fgov.ehealth.technicalconnector.signature.impl.xades.impl;

import be.fgov.ehealth.technicalconnector.signature.domain.SignatureVerificationError;
import be.fgov.ehealth.technicalconnector.signature.domain.SignatureVerificationResult;
import java.security.cert.CertificateExpiredException;
import java.security.cert.CertificateNotYetValidException;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class XadesVerificationHelper {
   private static final Logger LOG = LoggerFactory.getLogger(XadesSpecification.class);

   public XadesVerificationHelper() {
   }

   public static void verifyValiditySigningCert(DateTime signingTime, SignatureVerificationResult result) {
      try {
         result.getSigningCert().checkValidity(signingTime.toDate());
      } catch (CertificateExpiredException var3) {
         LOG.error("Signing certificate expired.", var3);
         result.getErrors().add(SignatureVerificationError.CERTIFICATE_EXPIRED);
      } catch (CertificateNotYetValidException var4) {
         LOG.error("Signing certificate not yet valid.", var4);
         result.getErrors().add(SignatureVerificationError.CERTIFICATE_NOT_YET_VALID);
      }

   }
}
