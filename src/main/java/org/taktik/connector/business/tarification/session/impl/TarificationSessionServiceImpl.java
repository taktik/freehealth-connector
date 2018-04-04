package org.taktik.connector.business.tarification.session.impl;

import org.taktik.connector.business.tarification.service.ServiceFactory;
import org.taktik.connector.business.tarification.session.TarificationSessionService;
import org.taktik.connector.technical.config.impl.ConfigurationModuleBootstrap;
import org.taktik.connector.technical.exception.TechnicalConnectorException;
import org.taktik.connector.technical.exception.TechnicalConnectorExceptionValues;
import org.taktik.connector.technical.session.Session;
import org.taktik.connector.technical.utils.impl.JaxbContextFactory;
import org.taktik.connector.technical.validator.EhealthReplyValidator;
import org.taktik.connector.technical.validator.SessionValidator;
import org.taktik.connector.technical.ws.domain.GenericRequest;
import org.taktik.connector.technical.ws.domain.GenericResponse;
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
         GenericResponse xmlResponse = org.taktik.connector.technical.ws.ServiceFactory.getGenericWsSender().send(service);
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
