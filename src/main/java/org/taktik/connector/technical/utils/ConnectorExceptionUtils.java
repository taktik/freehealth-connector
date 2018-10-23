package org.taktik.connector.technical.utils;

import org.taktik.connector.technical.exception.SoaErrorException;
import org.taktik.connector.technical.exception.TechnicalConnectorException;
import org.taktik.connector.technical.exception.UnsealConnectorException;
import be.fgov.ehealth.errors.core.v1.ErrorType;
import be.fgov.ehealth.errors.soa.v1.BusinessError;
import be.fgov.ehealth.errors.soa.v1.SystemError;
import be.fgov.ehealth.etee.crypto.decrypt.UnsealedData;
import be.fgov.ehealth.etee.crypto.status.CryptoResult;
import be.fgov.ehealth.etee.crypto.status.NotificationError;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import javax.xml.ws.soap.SOAPFaultException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Node;

public final class ConnectorExceptionUtils {
   private static final Logger LOG = LoggerFactory.getLogger(ConnectorExceptionUtils.class);

   public static byte[] processUnsealConnectorException(UnsealConnectorException e, String... allowedErrors) throws UnsealConnectorException, TechnicalConnectorException {
      CryptoResult<UnsealedData> result = (CryptoResult<UnsealedData>) e.getUnsealResult();
      Set<NotificationError> errors = new HashSet();
      errors.addAll(result.getErrors());
      Iterator i$ = errors.iterator();

      while(i$.hasNext()) {
         NotificationError error = (NotificationError)i$.next();
         LOG.error("NotificationError: " + error.toString());
      }

      if (allowedErrors != null && allowedErrors.length != 0) {
         String[] arr$ = allowedErrors;
         int len$ = allowedErrors.length;

         for(int ii$ = 0; ii$ < len$; ++ii$) {
            String allowedError = arr$[ii$];
            NotificationError error = NotificationError.valueOf(allowedError);
            errors.remove(error);
         }
      } else {
         LOG.warn("Empty error list to ignore. Ignoring all errors.");
         errors.clear();
      }

      if (errors.size() <= 0 && result.getFatal() == null) {
         InputStream unsealedDataStream = ((UnsealedData)result.getData()).getContent();
         return ConnectorIOUtils.getBytes(unsealedDataStream);
      } else {
         throw e;
      }
   }
}
