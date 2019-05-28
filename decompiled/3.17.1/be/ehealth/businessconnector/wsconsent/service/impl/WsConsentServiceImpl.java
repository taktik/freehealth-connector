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
import be.fgov.ehealth.hubservices.core.v2.PutPatientConsentRequest;
import be.fgov.ehealth.hubservices.core.v2.PutPatientConsentResponse;
import be.fgov.ehealth.hubservices.core.v2.RevokePatientConsentRequest;
import be.fgov.ehealth.hubservices.core.v2.RevokePatientConsentResponse;
import javax.xml.soap.SOAPException;

public class WsConsentServiceImpl implements WsConsentService, ConfigurationModuleBootstrap.ModuleBootstrapHook {
   private static final String SOAPACTION_PUT_CONSENT = "urn:be:fgov:ehealth:consent:protocol:v1:PutPatientConsent";
   private static final String SOAPACTION_GET_CONSENT = "urn:be:fgov:ehealth:consent:protocol:v1:GetPatientConsent";
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
      GenericRequest req = this.getPort(token);
      req.setPayload((Object)request);
      req.setSoapAction("urn:be:fgov:ehealth:consent:protocol:v1:PutPatientConsent");
      GenericResponse resp = ServiceFactory.getGenericWsSender().send(req);

      try {
         return (PutPatientConsentResponse)resp.asObject(PutPatientConsentResponse.class);
      } catch (SOAPException var6) {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_WS, new Object[]{var6.getMessage(), var6});
      }
   }

   public GetPatientConsentResponse getPatientConsent(SAMLToken token, GetPatientConsentRequest request) throws WsConsentBusinessConnectorException, TechnicalConnectorException, SessionManagementException {
      GenericRequest req = this.getPort(token);
      req.setPayload((Object)request);
      req.setSoapAction("urn:be:fgov:ehealth:consent:protocol:v1:GetPatientConsent");
      GenericResponse resp = ServiceFactory.getGenericWsSender().send(req);

      try {
         return (GetPatientConsentResponse)resp.asObject(GetPatientConsentResponse.class);
      } catch (SOAPException var6) {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_WS, new Object[]{var6.getMessage(), var6});
      }
   }

   public RevokePatientConsentResponse revokePatientConsent(SAMLToken token, RevokePatientConsentRequest request) throws WsConsentBusinessConnectorException, TechnicalConnectorException, SessionManagementException {
      GenericRequest req = this.getPort(token);
      req.setPayload((Object)request);
      req.setSoapAction("urn:be:fgov:ehealth:consent:protocol:v1:RevokePatientConsent");
      GenericResponse resp = ServiceFactory.getGenericWsSender().send(req);

      try {
         return (RevokePatientConsentResponse)resp.asObject(RevokePatientConsentResponse.class);
      } catch (SOAPException var6) {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_WS, new Object[]{var6.getMessage(), var6});
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
