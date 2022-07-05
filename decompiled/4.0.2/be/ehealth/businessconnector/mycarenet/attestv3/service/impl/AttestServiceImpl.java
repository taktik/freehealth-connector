package be.ehealth.businessconnector.mycarenet.attestv3.service.impl;

import be.ehealth.businessconnector.mycarenet.attestv3.service.AttestService;
import be.ehealth.businessconnector.mycarenet.attestv3.service.ServiceFactory;
import be.ehealth.technicalconnector.config.impl.ConfigurationModuleBootstrap;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.exception.TechnicalConnectorExceptionValues;
import be.ehealth.technicalconnector.service.sts.security.SAMLToken;
import be.ehealth.technicalconnector.utils.impl.JaxbContextFactory;
import be.ehealth.technicalconnector.validator.SessionValidator;
import be.ehealth.technicalconnector.ws.domain.GenericRequest;
import be.ehealth.technicalconnector.ws.domain.GenericResponse;
import be.fgov.ehealth.mycarenet.attest.protocol.v3.CancelAttestationRequest;
import be.fgov.ehealth.mycarenet.attest.protocol.v3.CancelAttestationResponse;
import be.fgov.ehealth.mycarenet.attest.protocol.v3.SendAttestationRequest;
import be.fgov.ehealth.mycarenet.attest.protocol.v3.SendAttestationResponse;
import be.fgov.ehealth.mycarenet.commons.protocol.v4.SendRequestType;
import be.fgov.ehealth.mycarenet.commons.protocol.v4.SendResponseType;
import javax.xml.soap.SOAPException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AttestServiceImpl implements AttestService, ConfigurationModuleBootstrap.ModuleBootstrapHook {
   private static final Logger LOG = LoggerFactory.getLogger(AttestServiceImpl.class);
   private static final String SOAP_ACTION_SEND_ATTESTATION = "urn:be:fgov:ehealth:mycarenet:attest:protocol:v3:SendAttestation";
   private static final String SOAP_ACTION_CANCEl_ATTESTATION = "urn:be:fgov:ehealth:mycarenet:attest:protocol:v3:CancelAttestation";
   private SessionValidator sessionValidator;

   public AttestServiceImpl(SessionValidator sessionValidator) {
      this.sessionValidator = sessionValidator;
   }

   public AttestServiceImpl() {
      LOG.debug("creating AttestServiceImpl for bootstrapping purposes");
   }

   public final SendAttestationResponse sendAttestion(SAMLToken token, SendRequestType request) throws TechnicalConnectorException {
      return (SendAttestationResponse)this.callAttestService(token, request, "urn:be:fgov:ehealth:mycarenet:attest:protocol:v3:SendAttestation", SendAttestationResponse.class);
   }

   public CancelAttestationResponse cancelAttestion(SAMLToken token, SendRequestType request) throws TechnicalConnectorException {
      return (CancelAttestationResponse)this.callAttestService(token, request, "urn:be:fgov:ehealth:mycarenet:attest:protocol:v3:CancelAttestation", CancelAttestationResponse.class);
   }

   private <T extends SendRequestType, K extends SendResponseType> K callAttestService(SAMLToken token, T request, String soapAction, Class<K> responseClass) throws TechnicalConnectorException {
      try {
         this.sessionValidator.validateToken(token);
         GenericRequest service = ServiceFactory.getAttestPort(token);
         service.setSoapAction(soapAction);
         service.setPayload((Object)request);
         GenericResponse xmlResponse = be.ehealth.technicalconnector.ws.ServiceFactory.getGenericWsSender().send(service);
         return (SendResponseType)xmlResponse.asObject(responseClass);
      } catch (SOAPException var7) {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_WS, var7, new Object[]{var7.getMessage()});
      }
   }

   public void bootstrap() {
      JaxbContextFactory.initJaxbContext(SendAttestationRequest.class);
      JaxbContextFactory.initJaxbContext(SendAttestationResponse.class);
      JaxbContextFactory.initJaxbContext(CancelAttestationRequest.class);
      JaxbContextFactory.initJaxbContext(CancelAttestationResponse.class);
   }
}
