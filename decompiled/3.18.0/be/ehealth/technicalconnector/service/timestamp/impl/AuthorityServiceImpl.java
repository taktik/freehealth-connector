package be.ehealth.technicalconnector.service.timestamp.impl;

import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.exception.TechnicalConnectorExceptionValues;
import be.ehealth.technicalconnector.service.timestamp.AuthorityService;
import be.ehealth.technicalconnector.service.ws.ServiceFactory;
import be.ehealth.technicalconnector.ws.domain.GenericRequest;
import java.security.PrivateKey;
import java.security.cert.X509Certificate;
import javax.xml.soap.SOAPException;
import oasis.names.tc.dss._1_0.core.schema.SignRequest;
import oasis.names.tc.dss._1_0.core.schema.SignResponse;

public class AuthorityServiceImpl implements AuthorityService {
   public SignResponse signRequest(X509Certificate certificate, PrivateKey privateKey, SignRequest signRequest) throws TechnicalConnectorException {
      if (certificate != null && privateKey != null) {
         GenericRequest request = ServiceFactory.getTSAuthorityService(certificate, privateKey);
         request.setPayload((Object)signRequest);

         try {
            return (SignResponse)be.ehealth.technicalconnector.ws.ServiceFactory.getGenericWsSender().send(request).asObject(SignResponse.class);
         } catch (SOAPException var6) {
            throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_WS, new Object[]{var6.getMessage(), var6});
         }
      } else {
         TechnicalConnectorExceptionValues errorValue = TechnicalConnectorExceptionValues.SECURITY_NO_CERTIFICATE;
         throw new TechnicalConnectorException(errorValue, (Throwable)null, new Object[0]);
      }
   }
}
