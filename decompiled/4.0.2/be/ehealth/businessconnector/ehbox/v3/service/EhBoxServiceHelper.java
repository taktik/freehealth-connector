package be.ehealth.businessconnector.ehbox.v3.service;

import be.ehealth.businessconnector.ehbox.v3.exception.OoOPublicationException;
import be.ehealth.businessconnector.ehbox.v3.validator.EhboxReplyValidator;
import be.ehealth.technicalconnector.exception.ConnectorException;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.exception.TechnicalConnectorExceptionValues;
import be.ehealth.technicalconnector.service.sts.security.SAMLToken;
import be.ehealth.technicalconnector.validator.SessionValidator;
import be.ehealth.technicalconnector.ws.domain.GenericRequest;
import be.ehealth.technicalconnector.ws.domain.GenericResponse;
import be.fgov.ehealth.commons.protocol.v1.ResponseType;

public class EhBoxServiceHelper {
   public EhBoxServiceHelper() {
   }

   public static <T extends ResponseType> T callEhBoxService(SAMLToken token, GenericRequest service, String soapAction, Object request, Class<T> responseClass, SessionValidator sessValidator, EhboxReplyValidator replyValidator) throws ConnectorException {
      try {
         sessValidator.validateToken(token);
         service.setPayload(request);
         service.setSoapAction(soapAction);
         GenericResponse xmlResponse = be.ehealth.technicalconnector.ws.ServiceFactory.getGenericWsSender().send(service);
         T response = (ResponseType)xmlResponse.asObject(responseClass);
         replyValidator.validateReplyStatus(response);
         return response;
      } catch (Exception var9) {
         if (!(var9 instanceof OoOPublicationException) && !(var9 instanceof TechnicalConnectorException)) {
            throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_WS, var9, new Object[]{var9.getMessage()});
         } else {
            throw (ConnectorException)var9;
         }
      }
   }
}
