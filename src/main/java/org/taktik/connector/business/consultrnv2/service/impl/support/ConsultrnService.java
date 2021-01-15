package org.taktik.connector.business.consultrnv2.service.impl.support;


import be.fgov.ehealth.commons.protocol.v2.StatusResponseType;
import org.taktik.connector.technical.exception.TechnicalConnectorException;
import org.taktik.connector.technical.exception.TechnicalConnectorExceptionValues;
import org.taktik.connector.technical.service.sts.security.SAMLToken;
import org.taktik.connector.technical.validator.EhealthReplyValidator;
import org.taktik.connector.technical.ws.ServiceFactory;
import org.taktik.connector.technical.ws.domain.GenericRequest;
import org.taktik.connector.technical.ws.domain.GenericResponse;

import javax.xml.soap.SOAPException;

public class ConsultrnService {
   private EhealthReplyValidator replyValidator;

   public ConsultrnService(EhealthReplyValidator replyValidator) {
      this.replyValidator = replyValidator;
   }

   public <T extends StatusResponseType> T doOperation(GenericRequest service, SAMLToken token, Object request, String soapAction, Class<T> clazz) throws TechnicalConnectorException {
      try {
         service.setPayload(request);
         GenericResponse wsResponse = ServiceFactory.getGenericWsSender().send(service);
         T response = wsResponse.asObject(clazz);
         this.replyValidator.validateReplyStatus(response);
         return response;
      } catch (SOAPException var8) {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_WS, var8, new Object[]{var8.getMessage()});
      }
   }
}
