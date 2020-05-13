package be.ehealth.businessconnector.mediprima.session.impl;

import be.ehealth.businessconnector.mediprima.service.ServiceFactory;
import be.ehealth.businessconnector.mediprima.session.MediprimaConsultationSessionService;
import be.ehealth.technicalconnector.config.impl.ConfigurationModuleBootstrap;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.exception.TechnicalConnectorExceptionValues;
import be.ehealth.technicalconnector.session.Session;
import be.ehealth.technicalconnector.utils.impl.JaxbContextFactory;
import be.ehealth.technicalconnector.validator.EhealthReplyValidator;
import be.ehealth.technicalconnector.validator.SessionValidator;
import be.ehealth.technicalconnector.ws.domain.GenericRequest;
import be.fgov.ehealth.commons.protocol.v2.StatusResponseType;
import be.fgov.ehealth.mediprima.protocol.v1.ConsultCarmedInterventionRequest;
import be.fgov.ehealth.mediprima.protocol.v1.ConsultCarmedInterventionResponse;
import javax.xml.soap.SOAPException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MediprimaConsultationSessionServiceImpl implements MediprimaConsultationSessionService, ConfigurationModuleBootstrap.ModuleBootstrapHook {
   private static final Logger LOG = LoggerFactory.getLogger(MediprimaConsultationSessionServiceImpl.class);
   private SessionValidator sessionValidator;
   private EhealthReplyValidator replyValidator;

   public MediprimaConsultationSessionServiceImpl(SessionValidator sessionValidator, EhealthReplyValidator replyValidator) throws TechnicalConnectorException {
      this.sessionValidator = sessionValidator;
      this.replyValidator = replyValidator;
      if (!Session.getInstance().hasValidSession()) {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.NO_VALID_SESSION, new Object[0]);
      }
   }

   public MediprimaConsultationSessionServiceImpl() {
      LOG.debug("creating MediprimaConsultationSessionServiceImpl for bootstrap purposes");
   }

   public void bootstrap() {
      JaxbContextFactory.initJaxbContext(ConsultCarmedInterventionRequest.class);
      JaxbContextFactory.initJaxbContext(ConsultCarmedInterventionResponse.class);
   }

   private <T extends StatusResponseType> T executeOperation(Object request, String operation, Class<T> clazz) throws TechnicalConnectorException {
      try {
         this.sessionValidator.validateSession();
         GenericRequest service = ServiceFactory.getMediprimaConsultationService(Session.getInstance().getSession().getSAMLToken(), operation);
         service.setPayload(request);
         T response = (StatusResponseType)be.ehealth.technicalconnector.ws.ServiceFactory.getGenericWsSender().send(service).asObject(clazz);
         this.replyValidator.validateReplyStatus(response);
         return response;
      } catch (SOAPException var6) {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_WS, var6, new Object[]{var6.getMessage()});
      }
   }

   public ConsultCarmedInterventionResponse consultCarmedIntervention(ConsultCarmedInterventionRequest request) throws TechnicalConnectorException {
      return (ConsultCarmedInterventionResponse)this.executeOperation(request, "urn:be:fgov:ehealth:mediprima:protocol:v1:consultCarmedIntervention", ConsultCarmedInterventionResponse.class);
   }
}
