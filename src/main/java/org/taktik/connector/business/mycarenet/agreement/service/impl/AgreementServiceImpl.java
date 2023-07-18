package org.taktik.connector.business.mycarenet.agreement.service.impl;

import be.ehealth.businessconnector.mycarenet.agreement.service.AgreementService;
import be.ehealth.businessconnector.mycarenet.agreement.service.ServiceFactory;
import be.ehealth.technicalconnector.config.impl.ConfigurationModuleBootstrap;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.exception.TechnicalConnectorExceptionValues;
import be.ehealth.technicalconnector.service.sts.security.SAMLToken;
import be.ehealth.technicalconnector.utils.impl.JaxbContextFactory;
import be.ehealth.technicalconnector.validator.SessionValidator;
import be.ehealth.technicalconnector.ws.domain.GenericRequest;
import be.ehealth.technicalconnector.ws.domain.GenericResponse;
import be.fgov.ehealth.mycarenet.agreement.protocol.v1.AskAgreementRequest;
import be.fgov.ehealth.mycarenet.agreement.protocol.v1.AskAgreementResponse;
import be.fgov.ehealth.mycarenet.agreement.protocol.v1.ConsultAgreementRequest;
import be.fgov.ehealth.mycarenet.agreement.protocol.v1.ConsultAgreementResponse;
import be.fgov.ehealth.mycarenet.commons.protocol.v3.SendRequestType;
import be.fgov.ehealth.mycarenet.commons.protocol.v3.SendResponseType;
import javax.xml.soap.SOAPException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AgreementServiceImpl implements AgreementService, ConfigurationModuleBootstrap.ModuleBootstrapHook {
   private static final Logger LOG = LoggerFactory.getLogger(AgreementServiceImpl.class);
   public static final String SOAP_ACTION_ASK_AGREEMENT = "urn:be:fgov:ehealth:mycarenet:agreement:protocol:v1:AskAgreement";
   public static final String SOAP_ACTION_CONSULT_AGREEMENT = "urn:be:fgov:ehealth:mycarenet:agreement:protocol:v1:ConsultAgreement";
   private SessionValidator sessionValidator;

   public AgreementServiceImpl(SessionValidator sessionValidator) {
      this.sessionValidator = sessionValidator;
   }

   public AgreementServiceImpl() {
      LOG.debug("creating AgreementServiceImpl for bootstrapping purposes");
   }

   public AskAgreementResponse askAgreement(SAMLToken token, AskAgreementRequest request) throws TechnicalConnectorException {
      return (AskAgreementResponse)this.callAgreementService(token, request, "urn:be:fgov:ehealth:mycarenet:agreement:protocol:v1:AskAgreement", AskAgreementResponse.class);
   }

   public ConsultAgreementResponse consultAgreement(SAMLToken token, ConsultAgreementRequest request) throws TechnicalConnectorException {
      return (ConsultAgreementResponse)this.callAgreementService(token, request, "urn:be:fgov:ehealth:mycarenet:agreement:protocol:v1:ConsultAgreement", ConsultAgreementResponse.class);
   }

   private <T extends SendRequestType, K extends SendResponseType> K callAgreementService(SAMLToken token, T request, String soapAction, Class<K> responseClass) throws TechnicalConnectorException {
      try {
         this.sessionValidator.validateToken(token);
         GenericRequest service = ServiceFactory.getAgreementPort(token);
         service.setSoapAction(soapAction);
         service.setPayload(request);
         GenericResponse xmlResponse = be.ehealth.technicalconnector.ws.ServiceFactory.getGenericWsSender().send(service);
         return (SendResponseType)xmlResponse.asObject(responseClass);
      } catch (SOAPException var7) {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_WS, var7, new Object[]{var7.getMessage()});
      }
   }

   public void bootstrap() {
      JaxbContextFactory.initJaxbContext(new Class[]{AskAgreementRequest.class});
      JaxbContextFactory.initJaxbContext(new Class[]{AskAgreementResponse.class});
      JaxbContextFactory.initJaxbContext(new Class[]{ConsultAgreementRequest.class});
      JaxbContextFactory.initJaxbContext(new Class[]{ConsultAgreementResponse.class});
   }
}
