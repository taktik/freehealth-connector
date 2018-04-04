package org.taktik.connector.technical.service.seals.impl;

import org.taktik.connector.technical.exception.TechnicalConnectorException;
import org.taktik.connector.technical.exception.TechnicalConnectorExceptionValues;
import org.taktik.connector.technical.service.seals.SealsService;
import org.taktik.connector.technical.service.ws.ServiceFactory;
import org.taktik.connector.technical.validator.EhealthReplyValidator;
import org.taktik.connector.technical.ws.domain.GenericRequest;
import be.fgov.ehealth.commons.protocol.v1.ResponseType;
import be.fgov.ehealth.seals.protocol.v1.DecodeRequest;
import be.fgov.ehealth.seals.protocol.v1.DecodeResponse;
import be.fgov.ehealth.seals.protocol.v1.EncodeRequest;
import be.fgov.ehealth.seals.protocol.v1.EncodeResponse;
import java.security.PrivateKey;
import java.security.cert.X509Certificate;
import javax.xml.soap.SOAPException;

public class SealsServiceImpl implements SealsService {
   private EhealthReplyValidator validator;

   public SealsServiceImpl(EhealthReplyValidator validator) {
      this.validator = validator;
   }

   public EncodeResponse encode(X509Certificate certificate, PrivateKey privateKey, EncodeRequest encodeRequest) throws TechnicalConnectorException {
      if (certificate != null && privateKey != null) {
         GenericRequest request = ServiceFactory.getSealsService(certificate, privateKey);
         request.setPayload((Object)encodeRequest);

         try {
            EncodeResponse response = (EncodeResponse)org.taktik.connector.technical.ws.ServiceFactory.getGenericWsSender().send(request).asObject(EncodeResponse.class);
            this.validator.validateReplyStatus((ResponseType)response);
            return response;
         } catch (SOAPException var6) {
            throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_WS, new Object[]{var6.getMessage(), var6});
         }
      } else {
         TechnicalConnectorExceptionValues errorValue = TechnicalConnectorExceptionValues.SECURITY_NO_CERTIFICATE;
         throw new TechnicalConnectorException(errorValue, (Throwable)null, new Object[0]);
      }
   }

   public DecodeResponse decode(X509Certificate certificate, PrivateKey privateKey, DecodeRequest decodeRequest) throws TechnicalConnectorException {
      if (certificate != null && privateKey != null) {
         GenericRequest request = ServiceFactory.getSealsService(certificate, privateKey);
         request.setPayload((Object)decodeRequest);

         try {
            DecodeResponse response = (DecodeResponse)org.taktik.connector.technical.ws.ServiceFactory.getGenericWsSender().send(request).asObject(DecodeResponse.class);
            this.validator.validateReplyStatus((ResponseType)response);
            return response;
         } catch (SOAPException var6) {
            throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_WS, new Object[]{var6.getMessage(), var6});
         }
      } else {
         TechnicalConnectorExceptionValues errorValue = TechnicalConnectorExceptionValues.SECURITY_NO_CERTIFICATE;
         throw new TechnicalConnectorException(errorValue, (Throwable)null, new Object[0]);
      }
   }
}
