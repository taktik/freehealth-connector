package be.ehealth.technicalconnector.utils;

import be.ehealth.technicalconnector.exception.SoaErrorException;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.exception.UnsealConnectorException;
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

   private ConnectorExceptionUtils() {
   }

   public static void processSOAPFault(SOAPFaultException e) throws SoaErrorException {
      if (e.getFault() == null) {
         throw e;
      } else {
         Node detail = ConnectorXmlUtils.getFirstChildElement(e.getFault().getDetail());
         if (detail == null) {
            throw e;
         } else {
            MarshallerHelper helper;
            if ("BusinessError".equals(detail.getLocalName())) {
               helper = new MarshallerHelper(BusinessError.class, BusinessError.class);
               throw new SoaErrorException(e.getFault().getFaultCode() + ": " + e.getFault().getFaultString(), (ErrorType)helper.toObject((Node)detail));
            } else if ("SystemError".equals(detail.getLocalName())) {
               helper = new MarshallerHelper(SystemError.class, SystemError.class);
               throw new SoaErrorException(e.getFault().getFaultCode() + ": " + e.getFault().getFaultString(), (ErrorType)helper.toObject((Node)detail));
            } else {
               throw e;
            }
         }
      }
   }

   public static byte[] processUnsealConnectorException(UnsealConnectorException e, String... allowedErrors) throws TechnicalConnectorException {
      CryptoResult<UnsealedData> result = e.getUnsealResult();
      Set<NotificationError> errors = new HashSet();
      errors.addAll(result.getErrors());
      Iterator var4 = errors.iterator();

      while(var4.hasNext()) {
         NotificationError error = (NotificationError)var4.next();
         LOG.error("NotificationError: {}", error);
      }

      if (allowedErrors != null && allowedErrors.length != 0) {
         String[] var9 = allowedErrors;
         int var11 = allowedErrors.length;

         for(int var6 = 0; var6 < var11; ++var6) {
            String allowedError = var9[var6];
            NotificationError error = NotificationError.valueOf(allowedError);
            errors.remove(error);
         }
      } else {
         LOG.warn("Empty error list to ignore. Ignoring all errors.");
         errors.clear();
      }

      if (errors.isEmpty() && result.getFatal() == null) {
         InputStream unsealedDataStream = ((UnsealedData)result.getData()).getContent();
         return ConnectorIOUtils.getBytes(unsealedDataStream);
      } else {
         throw e;
      }
   }
}
