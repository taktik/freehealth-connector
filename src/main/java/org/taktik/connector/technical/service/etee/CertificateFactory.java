package org.taktik.connector.technical.service.etee;

import org.taktik.connector.technical.exception.TechnicalConnectorException;

/** @deprecated */
@Deprecated
public final class CertificateFactory {
   public static CertificateChecker getCertificateChecker() throws TechnicalConnectorException {
      return CertificateCheckerFactory.getCertificateChecker();
   }
}
