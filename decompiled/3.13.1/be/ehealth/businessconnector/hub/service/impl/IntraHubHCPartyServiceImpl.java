package be.ehealth.businessconnector.hub.service.impl;

import be.ehealth.businessconnector.hub.builders.BuilderFactory;
import be.ehealth.businessconnector.hub.builders.RequestBuilderComplete;
import be.ehealth.businessconnector.hub.exception.IntraHubBusinessConnectorException;
import be.ehealth.businessconnector.hub.service.IntraHubHCPartyService;
import be.ehealth.businessconnector.hub.service.IntraHubService;
import be.ehealth.businessconnector.hub.validators.HubReplyValidator;
import be.ehealth.technicalconnector.config.impl.ConfigurationModuleBootstrap;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.service.sts.security.SAMLToken;
import be.ehealth.technicalconnector.session.Session;
import be.ehealth.technicalconnector.utils.impl.JaxbContextFactory;
import be.fgov.ehealth.hubservices.core.v1.ConsentHCPartyType;
import be.fgov.ehealth.hubservices.core.v1.GetHCPartyConsentRequest;
import be.fgov.ehealth.hubservices.core.v1.GetHCPartyConsentResponse;
import be.fgov.ehealth.hubservices.core.v1.GetHCPartyRequest;
import be.fgov.ehealth.hubservices.core.v1.GetHCPartyResponse;
import be.fgov.ehealth.hubservices.core.v1.HCPartyAdaptedType;
import be.fgov.ehealth.hubservices.core.v1.HCPartyIdType;
import be.fgov.ehealth.hubservices.core.v1.PutHCPartyConsentRequest;
import be.fgov.ehealth.hubservices.core.v1.PutHCPartyConsentResponse;
import be.fgov.ehealth.hubservices.core.v1.PutHCPartyRequest;
import be.fgov.ehealth.hubservices.core.v1.PutHCPartyResponse;
import be.fgov.ehealth.hubservices.core.v1.RevokeHCPartyConsentRequest;
import be.fgov.ehealth.hubservices.core.v1.RevokeHCPartyConsentResponse;

public class IntraHubHCPartyServiceImpl extends IntraHubAbstract implements IntraHubHCPartyService, ConfigurationModuleBootstrap.ModuleBootstrapHook {
   private RequestBuilderComplete builder;

   public IntraHubHCPartyServiceImpl(IntraHubService hubService, HubReplyValidator validator) throws TechnicalConnectorException, IntraHubBusinessConnectorException {
      super(hubService, validator);
      this.builder = BuilderFactory.getInstance().getRequestBuilderComplete();
   }

   public IntraHubHCPartyServiceImpl() {
   }

   public HCPartyAdaptedType getHCParty(HCPartyIdType hcParty) throws TechnicalConnectorException, IntraHubBusinessConnectorException {
      SAMLToken token = Session.getInstance().getSession().getSAMLToken();
      GetHCPartyRequest request = this.builder.buildGetHcPartyRequest(hcParty);
      GetHCPartyResponse response = this.getService().getHCParty(token, request);
      this.getReplyValidator().validate(response.getAcknowledge());
      return response.getHcparty();
   }

   public HCPartyAdaptedType putHCParty(HCPartyAdaptedType hcParty) throws TechnicalConnectorException, IntraHubBusinessConnectorException {
      SAMLToken token = Session.getInstance().getSession().getSAMLToken();
      PutHCPartyRequest request = this.builder.buildPutHcPartyRequest(hcParty);
      PutHCPartyResponse response = this.getService().putHCParty(token, request);
      this.getReplyValidator().validate(response.getAcknowledge());
      return response.getHcparty();
   }

   public void putHCPartyConsent(ConsentHCPartyType consent) throws TechnicalConnectorException, IntraHubBusinessConnectorException {
      SAMLToken token = Session.getInstance().getSession().getSAMLToken();
      PutHCPartyConsentRequest request = this.builder.buildPutHcPartyConsentRequest(consent);
      PutHCPartyConsentResponse response = this.getService().putHCPartyConsent(token, request);
      this.getReplyValidator().validate(response.getAcknowledge());
   }

   public ConsentHCPartyType getHCPartyConsent(HCPartyIdType hcPartyId) throws TechnicalConnectorException, IntraHubBusinessConnectorException {
      SAMLToken token = Session.getInstance().getSession().getSAMLToken();
      GetHCPartyConsentRequest request = this.builder.buildGetHcPartyConsent(hcPartyId);
      GetHCPartyConsentResponse response = this.getService().getHCPartyConsent(token, request);
      this.getReplyValidator().validate(response.getAcknowledge());
      return response.getConsent();
   }

   public void revokeHCPartyConsent(ConsentHCPartyType consent) throws TechnicalConnectorException, IntraHubBusinessConnectorException {
      SAMLToken token = Session.getInstance().getSession().getSAMLToken();
      RevokeHCPartyConsentRequest request = this.builder.buildRevokeHcPartyConsent(consent);
      RevokeHCPartyConsentResponse response = this.getService().revokeHCPartyConsent(token, request);
      this.getReplyValidator().validate(response.getAcknowledge());
   }

   public void bootstrap() {
      JaxbContextFactory.initJaxbContext(ConsentHCPartyType.class);
      JaxbContextFactory.initJaxbContext(GetHCPartyConsentRequest.class);
      JaxbContextFactory.initJaxbContext(GetHCPartyConsentResponse.class);
      JaxbContextFactory.initJaxbContext(GetHCPartyRequest.class);
      JaxbContextFactory.initJaxbContext(GetHCPartyResponse.class);
      JaxbContextFactory.initJaxbContext(HCPartyAdaptedType.class);
      JaxbContextFactory.initJaxbContext(PutHCPartyConsentRequest.class);
      JaxbContextFactory.initJaxbContext(PutHCPartyConsentResponse.class);
      JaxbContextFactory.initJaxbContext(PutHCPartyRequest.class);
      JaxbContextFactory.initJaxbContext(PutHCPartyResponse.class);
      JaxbContextFactory.initJaxbContext(RevokeHCPartyConsentRequest.class);
      JaxbContextFactory.initJaxbContext(RevokeHCPartyConsentResponse.class);
   }
}
