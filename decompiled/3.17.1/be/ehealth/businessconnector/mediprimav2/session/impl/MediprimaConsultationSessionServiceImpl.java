package be.ehealth.businessconnector.mediprimav2.session.impl;

import be.ehealth.businessconnector.mediprimav2.service.ServiceFactory;
import be.ehealth.businessconnector.mediprimav2.session.MediprimaConsultationSessionService;
import be.ehealth.technicalconnector.config.impl.ConfigurationModuleBootstrap;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.exception.TechnicalConnectorExceptionValues;
import be.ehealth.technicalconnector.session.Session;
import be.ehealth.technicalconnector.utils.impl.JaxbContextFactory;
import be.ehealth.technicalconnector.validator.EhealthReplyValidator;
import be.ehealth.technicalconnector.validator.SessionValidator;
import be.ehealth.technicalconnector.ws.domain.GenericRequest;
import be.ehealth.technicalconnector.ws.domain.GenericResponse;
import be.fgov.ehealth.commons.protocol.v2.StatusResponseType;
import be.fgov.ehealth.mediprima.protocol.v2.ConsultCarmedInterventionRequest;
import be.fgov.ehealth.mediprima.protocol.v2.ConsultCarmedInterventionResponse;
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
         GenericResponse resp = be.ehealth.technicalconnector.ws.ServiceFactory.getGenericWsSender().send(service);
         T response = (StatusResponseType)resp.asObject(clazz);
         this.replyValidator.validateReplyStatus(response);
         return response;
      } catch (SOAPException var7) {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_WS, var7, new Object[]{var7.getMessage()});
      }
   }

   public ConsultCarmedInterventionResponse consultCarmedIntervention(ConsultCarmedInterventionRequest request) throws TechnicalConnectorException {
      return (ConsultCarmedInterventionResponse)this.executeOperation(request, "urn:be:fgov:ehealth:mediprima:protocol:v2:consultCarmedIntervention", ConsultCarmedInterventionResponse.class);
   }
}
