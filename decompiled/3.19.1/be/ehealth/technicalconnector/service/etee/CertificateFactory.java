package be.ehealth.technicalconnector.service.etee;

import be.ehealth.technicalconnector.exception.TechnicalConnectorException;

/** @deprecated */
@Deprecated
public final class CertificateFactory {
   private CertificateFactory() {
   }

   public static CertificateChecker getCertificateChecker() throws TechnicalConnectorException {
      return CertificateCheckerFactory.getCertificateChecker();
   }
}
