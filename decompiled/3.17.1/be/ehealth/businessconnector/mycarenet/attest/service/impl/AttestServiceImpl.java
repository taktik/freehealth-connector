package be.ehealth.businessconnector.mycarenet.attest.service.impl;

import be.ehealth.businessconnector.mycarenet.attest.service.AttestService;
import be.ehealth.businessconnector.mycarenet.attest.service.ServiceFactory;
import be.ehealth.technicalconnector.config.impl.ConfigurationModuleBootstrap;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.exception.TechnicalConnectorExceptionValues;
import be.ehealth.technicalconnector.service.sts.security.SAMLToken;
import be.ehealth.technicalconnector.utils.impl.JaxbContextFactory;
import be.ehealth.technicalconnector.validator.EhealthReplyValidator;
import be.ehealth.technicalconnector.validator.SessionValidator;
import be.ehealth.technicalconnector.ws.domain.GenericRequest;
import be.ehealth.technicalconnector.ws.domain.GenericResponse;
import be.fgov.ehealth.mycarenet.attest.protocol.v1.SendAttestationRequest;
import be.fgov.ehealth.mycarenet.attest.protocol.v1.SendAttestationResponse;
import javax.xml.soap.SOAPException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AttestServiceImpl implements AttestService, ConfigurationModuleBootstrap.ModuleBootstrapHook {
   private static final Logger LOG = LoggerFactory.getLogger(AttestServiceImpl.class);
   private SessionValidator sessionValidator;
   private EhealthReplyValidator replyValidator;

   public AttestServiceImpl(SessionValidator sessionValidator, EhealthReplyValidator replyValidator) {
      this.sessionValidator = sessionValidator;
      this.replyValidator = replyValidator;
   }

   public AttestServiceImpl() {
      LOG.debug("creating AttestServiceImpl for bootstrapping purposes");
   }

   public final SendAttestationResponse sendAttestion(SAMLToken token, SendAttestationRequest request) throws TechnicalConnectorException {
      try {
         this.sessionValidator.validateSession();
         GenericRequest service = ServiceFactory.getAttestPort(token);
         service.setPayload((Object)request);
         GenericResponse xmlResponse = be.ehealth.technicalconnector.ws.ServiceFactory.getGenericWsSender().send(service);
         return (SendAttestationResponse)xmlResponse.asObject(SendAttestationResponse.class);
      } catch (SOAPException var5) {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_WS, var5, new Object[]{var5.getMessage()});
      }
   }

   public void bootstrap() {
      JaxbContextFactory.initJaxbContext(SendAttestationRequest.class);
      JaxbContextFactory.initJaxbContext(SendAttestationResponse.class);
   }
}
