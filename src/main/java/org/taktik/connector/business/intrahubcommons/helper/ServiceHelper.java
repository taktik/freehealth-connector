package org.taktik.connector.business.intrahubcommons.helper;

import org.taktik.connector.business.intrahubcommons.exception.FolderDecryptionRuntimeException;
import org.taktik.connector.technical.exception.TechnicalConnectorException;
import org.taktik.connector.technical.exception.TechnicalConnectorExceptionValues;
import javax.xml.ws.WebServiceException;

public class ServiceHelper {
   public static TechnicalConnectorException handleWebServiceException(WebServiceException e) {
      if (e.getCause() instanceof FolderDecryptionRuntimeException) {
         FolderDecryptionRuntimeException decryptException = (FolderDecryptionRuntimeException)e.getCause();
         return decryptException.getCause() instanceof TechnicalConnectorException ? (TechnicalConnectorException)decryptException.getCause() : new TechnicalConnectorException(TechnicalConnectorExceptionValues.UNKNOWN_ERROR, e, new Object[]{""});
      } else {
         return new TechnicalConnectorException(TechnicalConnectorExceptionValues.UNKNOWN_ERROR, e.getCause(), new Object[]{e.getMessage()});
      }
   }
}
