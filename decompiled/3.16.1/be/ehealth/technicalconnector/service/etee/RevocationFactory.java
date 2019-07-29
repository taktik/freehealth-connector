package be.ehealth.technicalconnector.service.etee;

import be.ehealth.technicalconnector.exception.TechnicalConnectorException;

/** @deprecated */
@Deprecated
public final class RevocationFactory {
   public static RevocationStatusChecker getStatusChecker() throws TechnicalConnectorException {
      return RevocationStatusCheckerFactory.getStatusChecker();
   }
}
