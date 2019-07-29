package org.taktik.connector.business.ssinhistory.service.impl;

import org.taktik.connector.business.ssinhistory.service.SsinHistoryTokenService;
import org.taktik.connector.business.ssinhistory.service.TokenServiceFactory;
import org.taktik.connector.technical.exception.TechnicalConnectorException;
import org.taktik.connector.technical.exception.TechnicalConnectorExceptionValues;
import org.taktik.connector.technical.service.sts.security.SAMLToken;
import org.taktik.connector.technical.validator.EhealthReplyValidator;
import org.taktik.connector.technical.validator.SessionValidator;
import org.taktik.connector.technical.ws.ServiceFactory;
import org.taktik.connector.technical.ws.domain.GenericRequest;
import be.fgov.ehealth.commons.protocol.v2.RequestType;
import be.fgov.ehealth.commons.protocol.v2.StatusResponseType;
import be.fgov.ehealth.consultrn.ssinhistory.protocol.v1.ConsultCurrentSsinRequest;
import be.fgov.ehealth.consultrn.ssinhistory.protocol.v1.ConsultCurrentSsinResponse;
import be.fgov.ehealth.consultrn.ssinhistory.protocol.v1.ConsultRelatedSsinsRequest;
import be.fgov.ehealth.consultrn.ssinhistory.protocol.v1.ConsultRelatedSsinsResponse;
import java.util.HashMap;
import java.util.Map;
import javax.xml.soap.SOAPException;

public class SsinHistoryTokenServiceImpl implements SsinHistoryTokenService {
   private SessionValidator sessionValidator;
   private EhealthReplyValidator ehealthReplyValidator;
   private static Map<Class<? extends StatusResponseType>, String> soapActions = new HashMap();

   public SsinHistoryTokenServiceImpl(SessionValidator sessVal, EhealthReplyValidator ehRepVal) throws TechnicalConnectorException {
      this.sessionValidator = sessVal;
      this.ehealthReplyValidator = ehRepVal;
   }

   public ConsultRelatedSsinsResponse consultRelatedSsins(SAMLToken token, ConsultRelatedSsinsRequest request) throws TechnicalConnectorException {
      return (ConsultRelatedSsinsResponse)this.invoke(token, request, ConsultRelatedSsinsResponse.class);
   }

   public ConsultCurrentSsinResponse consultCurrentSsin(SAMLToken token, ConsultCurrentSsinRequest request) throws TechnicalConnectorException {
      return (ConsultCurrentSsinResponse)this.invoke(token, request, ConsultCurrentSsinResponse.class);
   }

   private <T extends StatusResponseType> T invoke(SAMLToken token, RequestType request, Class<T> clazz) throws TechnicalConnectorException {
      try {
         this.sessionValidator.validateToken(token);
         GenericRequest service = TokenServiceFactory.getService(token);
         service.setPayload(request);
         service.setSoapAction(soapActions.get(clazz));
         T response = ServiceFactory.getGenericWsSender().send(service).asObject(clazz);
         this.ehealthReplyValidator.validateReplyStatus(response);
         return response;
      } catch (SOAPException var6) {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_WS, var6, new Object[]{var6.getMessage()});
      }
   }

   static {
      soapActions.put(ConsultRelatedSsinsResponse.class, "urn:be:fgov:ehealth:consultrn:ssinhistory:protocol:v1:consultRelatedSsins");
      soapActions.put(ConsultCurrentSsinResponse.class, "urn:be:fgov:ehealth:consultrn:ssinhistory:protocol:v1:consultCurrentSsin");
   }
}
