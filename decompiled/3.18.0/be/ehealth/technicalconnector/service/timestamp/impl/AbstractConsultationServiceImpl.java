package be.ehealth.technicalconnector.service.timestamp.impl;

import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.exception.TechnicalConnectorExceptionValues;
import be.ehealth.technicalconnector.service.ws.ServiceFactory;
import be.ehealth.technicalconnector.ws.domain.GenericRequest;
import java.security.PrivateKey;
import java.security.cert.X509Certificate;
import javax.xml.soap.SOAPException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AbstractConsultationServiceImpl<A, B, C, D> {
   private static final Logger LOG = LoggerFactory.getLogger(AbstractConsultationServiceImpl.class);
   private Class<B> clazzB;
   private Class<D> clazzD;

   AbstractConsultationServiceImpl(Class<B> clazzB, Class<D> clazzD) {
      this.clazzB = clazzB;
      this.clazzD = clazzD;
   }

   protected B obtainTimestamp(X509Certificate certificate, PrivateKey privateKey, A consultRequest) throws TechnicalConnectorException {
      if (certificate != null && privateKey != null) {
         GenericRequest request = ServiceFactory.getTSConsultService(certificate, privateKey);
         request.setPayload(consultRequest);

         try {
            return be.ehealth.technicalconnector.ws.ServiceFactory.getGenericWsSender().send(request).asObject(this.clazzB);
         } catch (SOAPException var6) {
            throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_WS, new Object[]{var6.getMessage(), var6});
         }
      } else {
         TechnicalConnectorExceptionValues errorValue = TechnicalConnectorExceptionValues.SECURITY_NO_CERTIFICATE;
         LOG.debug("\t## {}", errorValue.getMessage());
         throw new TechnicalConnectorException(errorValue, (Throwable)null, new Object[0]);
      }
   }

   protected D obtainCompleteness(X509Certificate certificate, PrivateKey privateKey, C consultRequest) throws TechnicalConnectorException {
      if (certificate != null && privateKey != null) {
         GenericRequest request = ServiceFactory.getTSConsultService(certificate, privateKey);
         request.setPayload(consultRequest);

         try {
            return be.ehealth.technicalconnector.ws.ServiceFactory.getGenericWsSender().send(request).asObject(this.clazzD);
         } catch (SOAPException var6) {
            throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_WS, new Object[]{var6.getMessage(), var6});
         }
      } else {
         TechnicalConnectorExceptionValues errorValue = TechnicalConnectorExceptionValues.SECURITY_NO_CERTIFICATE;
         LOG.debug("\t## {}", errorValue.getMessage());
         throw new TechnicalConnectorException(errorValue, (Throwable)null, new Object[0]);
      }
   }
}
