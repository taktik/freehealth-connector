package be.ehealth.businessconnector.consultrnv2.service.impl.support;

import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.exception.TechnicalConnectorExceptionValues;
import be.ehealth.technicalconnector.service.sts.security.SAMLToken;
import be.ehealth.technicalconnector.validator.EhealthReplyValidator;
import be.ehealth.technicalconnector.validator.SessionValidator;
import be.ehealth.technicalconnector.ws.ServiceFactory;
import be.ehealth.technicalconnector.ws.domain.GenericRequest;
import be.ehealth.technicalconnector.ws.domain.GenericResponse;
import be.fgov.ehealth.commons.protocol.v2.StatusResponseType;
import javax.xml.soap.SOAPException;

public class ConsultrnService {
   private SessionValidator sessionValidator;
   private EhealthReplyValidator replyValidator;

   public ConsultrnService(SessionValidator sessionValidator, EhealthReplyValidator replyValidator) {
      this.sessionValidator = sessionValidator;
      this.replyValidator = replyValidator;
   }

   public <T extends StatusResponseType> T doOperation(GenericRequest service, SAMLToken token, Object request, String soapAction, Class<T> clazz) throws TechnicalConnectorException {
      try {
         this.sessionValidator.validateToken(token);
         service.setPayload(request);
         GenericResponse wsResponse = ServiceFactory.getGenericWsSender().send(service);
         T response = (StatusResponseType)wsResponse.asObject(clazz);
         this.replyValidator.validateReplyStatus(response);
         return response;
      } catch (SOAPException var8) {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_WS, var8, new Object[]{var8.getMessage()});
      }
   }
}
