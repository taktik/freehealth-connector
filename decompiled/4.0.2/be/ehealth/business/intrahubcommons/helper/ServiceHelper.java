package be.ehealth.business.intrahubcommons.helper;

import be.ehealth.business.intrahubcommons.exception.FolderDecryptionRuntimeException;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.exception.TechnicalConnectorExceptionValues;
import javax.xml.ws.WebServiceException;

public class ServiceHelper {
   public ServiceHelper() {
   }

   public static TechnicalConnectorException handleWebServiceException(WebServiceException e) {
      if (e.getCause() instanceof FolderDecryptionRuntimeException) {
         FolderDecryptionRuntimeException decryptException = (FolderDecryptionRuntimeException)e.getCause();
         return decryptException.getCause() instanceof TechnicalConnectorException ? (TechnicalConnectorException)decryptException.getCause() : new TechnicalConnectorException(TechnicalConnectorExceptionValues.UNKNOWN_ERROR, e, new Object[]{""});
      } else {
         return new TechnicalConnectorException(TechnicalConnectorExceptionValues.UNKNOWN_ERROR, e.getCause(), new Object[]{e.getMessage()});
      }
   }
}
