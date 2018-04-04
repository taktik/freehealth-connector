package org.taktik.connector.business.intrahubcommons.exception;

import be.fgov.ehealth.standards.kmehr.schema.v1.ErrorType;
import java.util.ArrayList;
import java.util.List;

public class KmehrBusinessConnectorException extends IntraHubBusinessConnectorException {
   private static final long serialVersionUID = 1L;
   private List<ErrorType> listOfErrorType;

   public KmehrBusinessConnectorException(IntraHubBusinessConnectorExceptionValues errorCodeValue, Object... params) {
      super(errorCodeValue, params);
   }

   public KmehrBusinessConnectorException(IntraHubBusinessConnectorExceptionValues errorCodeValue, Throwable e, Object... params) {
      super(errorCodeValue, e, params);
   }

   public KmehrBusinessConnectorException(IntraHubBusinessConnectorExceptionValues errorCodeValue, List<ErrorType> listOfErrorType, Object... params) {
      super(errorCodeValue, params);
      this.listOfErrorType = new ArrayList(listOfErrorType);
   }

   public List<ErrorType> getListOfErrorType() {
      return this.listOfErrorType;
   }
}
