package org.taktik.connector.business.registration.session.impl;

import be.cin.nip.sync.reg.v1.Registrations;
import org.taktik.connector.business.registration.service.ServiceFactory;
import org.taktik.connector.business.registration.session.RegistrationSession;
import org.taktik.connector.technical.config.impl.ConfigurationModuleBootstrap;
import org.taktik.connector.technical.exception.TechnicalConnectorException;
import org.taktik.connector.technical.exception.TechnicalConnectorExceptionValues;
import org.taktik.connector.technical.session.Session;
import org.taktik.connector.technical.utils.impl.JaxbContextFactory;
import org.taktik.connector.technical.validator.EhealthReplyValidator;
import org.taktik.connector.technical.validator.SessionValidator;
import org.taktik.connector.technical.ws.domain.GenericRequest;
import org.taktik.connector.technical.ws.domain.GenericResponse;
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
         GenericResponse xmlResponse = org.taktik.connector.technical.ws.ServiceFactory.getGenericWsSender().send(service);
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
