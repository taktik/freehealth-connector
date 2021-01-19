package be.ehealth.businessconnector.ssinhistory.service.impl;

import be.ehealth.businessconnector.ssinhistory.service.SsinHistoryTokenService;
import be.ehealth.businessconnector.ssinhistory.service.TokenServiceFactory;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.exception.TechnicalConnectorExceptionValues;
import be.ehealth.technicalconnector.service.sts.security.SAMLToken;
import be.ehealth.technicalconnector.validator.EhealthReplyValidator;
import be.ehealth.technicalconnector.validator.SessionValidator;
import be.ehealth.technicalconnector.ws.ServiceFactory;
import be.ehealth.technicalconnector.ws.domain.GenericRequest;
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
         service.setPayload((Object)request);
         service.setSoapAction((String)soapActions.get(clazz));
         T response = (StatusResponseType)ServiceFactory.getGenericWsSender().send(service).asObject(clazz);
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
