package be.fgov.ehealth.technicalconnector.services.daas;

import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.exception.TechnicalConnectorExceptionValues;

public class DaasServiceException extends TechnicalConnectorException {
   public DaasServiceException(String status, Object... params) {
      super(getCode(status), params);
   }

   private static TechnicalConnectorExceptionValues getCode(String status) {
      return "urn:oasis:names:tc:SAML:2.0:status:Requester".equals(status) ? TechnicalConnectorExceptionValues.ERROR_INPUT : TechnicalConnectorExceptionValues.ERROR_GENERAL;
   }
}
