package org.taktik.connector.technical.service.etee;

import org.taktik.connector.technical.exception.TechnicalConnectorException;

/** @deprecated */
@Deprecated
public final class RevocationFactory {
   public static RevocationStatusChecker getStatusChecker() throws TechnicalConnectorException {
      return RevocationStatusCheckerFactory.getStatusChecker();
   }
}
