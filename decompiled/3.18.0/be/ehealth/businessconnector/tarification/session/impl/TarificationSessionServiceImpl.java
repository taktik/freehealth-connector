package be.ehealth.businessconnector.tarification.session.impl;

import be.ehealth.businessconnector.tarification.service.ServiceFactory;
import be.ehealth.businessconnector.tarification.session.TarificationSessionService;
import be.ehealth.technicalconnector.config.impl.ConfigurationModuleBootstrap;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.exception.TechnicalConnectorExceptionValues;
import be.ehealth.technicalconnector.session.Session;
import be.ehealth.technicalconnector.utils.impl.JaxbContextFactory;
import be.ehealth.technicalconnector.validator.EhealthReplyValidator;
import be.ehealth.technicalconnector.validator.SessionValidator;
import be.ehealth.technicalconnector.ws.domain.GenericRequest;
import be.ehealth.technicalconnector.ws.domain.GenericResponse;
import be.fgov.ehealth.mycarenet.commons.protocol.v2.TarificationConsultationRequest;
import be.fgov.ehealth.mycarenet.commons.protocol.v2.TarificationConsultationResponse;
import javax.xml.soap.SOAPException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TarificationSessionServiceImpl implements TarificationSessionService, ConfigurationModuleBootstrap.ModuleBootstrapHook {
   private SessionValidator sessionValidator;
   private static final Logger LOG = LoggerFactory.getLogger(TarificationSessionServiceImpl.class);

   public TarificationConsultationResponse consultTarification(TarificationConsultationRequest request) throws TechnicalConnectorException {
      try {
         this.sessionValidator.validateSession();
         GenericRequest service = ServiceFactory.getTarificationService(Session.getInstance().getSession().getSAMLToken());
         service.setPayload((Object)request);
         GenericResponse xmlResponse = be.ehealth.technicalconnector.ws.ServiceFactory.getGenericWsSender().send(service);
         TarificationConsultationResponse response = (TarificationConsultationResponse)xmlResponse.asObject(TarificationConsultationResponse.class);
         return response;
      } catch (SOAPException var5) {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_WS, var5, new Object[]{var5.getMessage()});
      }
   }

   public TarificationSessionServiceImpl(SessionValidator sessionValidator, EhealthReplyValidator replyValidator) throws TechnicalConnectorException {
      this.sessionValidator = sessionValidator;
      if (!Session.getInstance().hasValidSession()) {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.NO_VALID_SESSION, new Object[0]);
      }
   }

   public TarificationSessionServiceImpl() {
      LOG.debug("creating TarificationSessionServiceImpl for bootstrap purposes");
   }

   public void bootstrap() {
      JaxbContextFactory.initJaxbContext(TarificationConsultationRequest.class);
      JaxbContextFactory.initJaxbContext(TarificationConsultationResponse.class);
      JaxbContextFactory.initJaxbContext(TarificationConsultationResponse.class);
   }
}
