package be.ehealth.businessconnector.registration.session.impl;

import be.cin.nip.sync.reg.v1.Registrations;
import be.ehealth.businessconnector.registration.service.ServiceFactory;
import be.ehealth.businessconnector.registration.session.RegistrationSession;
import be.ehealth.technicalconnector.config.impl.ConfigurationModuleBootstrap;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.exception.TechnicalConnectorExceptionValues;
import be.ehealth.technicalconnector.session.Session;
import be.ehealth.technicalconnector.utils.impl.JaxbContextFactory;
import be.ehealth.technicalconnector.validator.EhealthReplyValidator;
import be.ehealth.technicalconnector.validator.SessionValidator;
import be.ehealth.technicalconnector.ws.domain.GenericRequest;
import be.ehealth.technicalconnector.ws.domain.GenericResponse;
import be.fgov.ehealth.mycarenet.registration.protocol.v1.RegisterToMycarenetServiceRequest;
import be.fgov.ehealth.mycarenet.registration.protocol.v1.RegisterToMycarenetServiceResponse;
import javax.xml.soap.SOAPException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RegistrationSessionImpl implements RegistrationSession, ConfigurationModuleBootstrap.ModuleBootstrapHook {
   private SessionValidator sessionValidator;
   private static final Logger LOG = LoggerFactory.getLogger(RegistrationSessionImpl.class);

   public RegisterToMycarenetServiceResponse registerToMycarenetService(RegisterToMycarenetServiceRequest request) throws TechnicalConnectorException {
      try {
         this.sessionValidator.validateSession();
         GenericRequest service = ServiceFactory.getRegistrationService(Session.getInstance().getSession().getSAMLToken());
         service.setPayload((Object)request);
         service.setSoapAction("urn:be:fgov:ehealth:mycarenet:registration:protocol:v1:RegisterToMycarenetService");
         GenericResponse xmlResponse = be.ehealth.technicalconnector.ws.ServiceFactory.getGenericWsSender().send(service);
         RegisterToMycarenetServiceResponse response = (RegisterToMycarenetServiceResponse)xmlResponse.asObject(RegisterToMycarenetServiceResponse.class);
         return response;
      } catch (SOAPException var5) {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_WS, var5, new Object[]{var5.getMessage()});
      }
   }

   public RegistrationSessionImpl(SessionValidator sessionValidator, EhealthReplyValidator replyValidator) throws TechnicalConnectorException {
      this.sessionValidator = sessionValidator;
      if (!Session.getInstance().hasValidSession()) {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.NO_VALID_SESSION, new Object[0]);
      }
   }

   public RegistrationSessionImpl() {
      LOG.debug("creating RegistrationSessionImpl for ModuleBootstrapper ");
   }

   public void bootstrap() {
      JaxbContextFactory.initJaxbContext(RegisterToMycarenetServiceRequest.class);
      JaxbContextFactory.initJaxbContext(RegisterToMycarenetServiceResponse.class);
      JaxbContextFactory.initJaxbContext(Registrations.class);
   }
}
