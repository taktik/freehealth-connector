package be.ehealth.businessconnector.wsconsent.service.impl;

import be.ehealth.business.common.util.HandlerChainUtil;
import be.ehealth.businessconnector.wsconsent.exception.WsConsentBusinessConnectorException;
import be.ehealth.businessconnector.wsconsent.service.WsConsentService;
import be.ehealth.technicalconnector.config.ConfigFactory;
import be.ehealth.technicalconnector.config.Configuration;
import be.ehealth.technicalconnector.config.impl.ConfigurationModuleBootstrap;
import be.ehealth.technicalconnector.exception.SessionManagementException;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.exception.TechnicalConnectorExceptionValues;
import be.ehealth.technicalconnector.service.sts.security.SAMLToken;
import be.ehealth.technicalconnector.utils.impl.JaxbContextFactory;
import be.ehealth.technicalconnector.ws.ServiceFactory;
import be.ehealth.technicalconnector.ws.domain.GenericRequest;
import be.ehealth.technicalconnector.ws.domain.GenericResponse;
import be.ehealth.technicalconnector.ws.domain.TokenType;
import be.fgov.ehealth.hubservices.core.v2.GetPatientConsentRequest;
import be.fgov.ehealth.hubservices.core.v2.GetPatientConsentResponse;
import be.fgov.ehealth.hubservices.core.v2.GetPatientConsentStatusRequest;
import be.fgov.ehealth.hubservices.core.v2.GetPatientConsentStatusResponse;
import be.fgov.ehealth.hubservices.core.v2.PutPatientConsentRequest;
import be.fgov.ehealth.hubservices.core.v2.PutPatientConsentResponse;
import be.fgov.ehealth.hubservices.core.v2.RevokePatientConsentRequest;
import be.fgov.ehealth.hubservices.core.v2.RevokePatientConsentResponse;
import javax.xml.soap.SOAPException;

public class WsConsentServiceImpl implements WsConsentService, ConfigurationModuleBootstrap.ModuleBootstrapHook {
   private static final String SOAPACTION_PUT_CONSENT = "urn:be:fgov:ehealth:consent:protocol:v1:PutPatientConsent";
   private static final String SOAPACTION_GET_CONSENT = "urn:be:fgov:ehealth:consent:protocol:v1:GetPatientConsent";
   private static final String SOAPACTION_GET_CONSENT_STATUS = "urn:be:fgov:ehealth:consent:protocol:v1:GetPatientConsentStatus";
   private static final String SOAPACTION_REVOKE_CONSENT = "urn:be:fgov:ehealth:consent:protocol:v1:RevokePatientConsent";
   private static final String PROP_ENDPOINT_WSCONSENT_V1 = "endpoint.wsconsent";
   private static final String PROP_VALIDATION_INCOMING_WSCONSENT = "validation.incoming.wsconsent.message";
   private static final String WSCONSENT_PROT = "/ehealth-hubservices/XSD/hubservices_protocol-2_3.xsd";
   private static Configuration config = ConfigFactory.getConfigValidator();

   protected GenericRequest getPort(SAMLToken token) throws TechnicalConnectorException {
      GenericRequest req = new GenericRequest();
      req.setCredential(token, TokenType.SAML);
      req.setEndpoint(config.getProperty("endpoint.wsconsent", "$uddi{uddi:ehealth-fgov-be:business:consent:v1}"));
      req.setHandlerChain(HandlerChainUtil.buildChainWithValidator("validation.incoming.wsconsent.message", "/ehealth-hubservices/XSD/hubservices_protocol-2_3.xsd"));
      req.setDefaultHandlerChain();
      return req;
   }

   public PutPatientConsentResponse putPatientConsent(SAMLToken token, PutPatientConsentRequest request) throws WsConsentBusinessConnectorException, TechnicalConnectorException, SessionManagementException {
      return (PutPatientConsentResponse)this.handleRequest(token, request, PutPatientConsentResponse.class, "urn:be:fgov:ehealth:consent:protocol:v1:PutPatientConsent");
   }

   public GetPatientConsentResponse getPatientConsent(SAMLToken token, GetPatientConsentRequest request) throws WsConsentBusinessConnectorException, TechnicalConnectorException, SessionManagementException {
      return (GetPatientConsentResponse)this.handleRequest(token, request, GetPatientConsentResponse.class, "urn:be:fgov:ehealth:consent:protocol:v1:GetPatientConsent");
   }

   public GetPatientConsentStatusResponse getPatientConsentStatus(SAMLToken token, GetPatientConsentStatusRequest request) throws WsConsentBusinessConnectorException, TechnicalConnectorException, SessionManagementException {
      return (GetPatientConsentStatusResponse)this.handleRequest(token, request, GetPatientConsentStatusResponse.class, "urn:be:fgov:ehealth:consent:protocol:v1:GetPatientConsentStatus");
   }

   public RevokePatientConsentResponse revokePatientConsent(SAMLToken token, RevokePatientConsentRequest request) throws WsConsentBusinessConnectorException, TechnicalConnectorException, SessionManagementException {
      return (RevokePatientConsentResponse)this.handleRequest(token, request, RevokePatientConsentResponse.class, "urn:be:fgov:ehealth:consent:protocol:v1:RevokePatientConsent");
   }

   private <T, U> T handleRequest(SAMLToken token, U request, Class<T> responseClass, String soapAction) throws TechnicalConnectorException {
      GenericRequest req = this.getPort(token);
      req.setPayload(request);
      req.setSoapAction(soapAction);
      GenericResponse resp = ServiceFactory.getGenericWsSender().send(req);

      try {
         return resp.asObject(responseClass);
      } catch (SOAPException var8) {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_WS, new Object[]{var8.getMessage(), var8});
      }
   }

   public void bootstrap() {
      JaxbContextFactory.initJaxbContext(GetPatientConsentRequest.class);
      JaxbContextFactory.initJaxbContext(GetPatientConsentResponse.class);
      JaxbContextFactory.initJaxbContext(PutPatientConsentRequest.class);
      JaxbContextFactory.initJaxbContext(PutPatientConsentResponse.class);
      JaxbContextFactory.initJaxbContext(RevokePatientConsentRequest.class);
      JaxbContextFactory.initJaxbContext(RevokePatientConsentResponse.class);
   }
}
