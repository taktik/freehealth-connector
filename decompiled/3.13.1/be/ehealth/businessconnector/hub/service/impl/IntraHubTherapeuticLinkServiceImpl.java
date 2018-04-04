package be.ehealth.businessconnector.hub.service.impl;

import be.ehealth.businessconnector.hub.builders.BuilderFactory;
import be.ehealth.businessconnector.hub.builders.RequestBuilderComplete;
import be.ehealth.businessconnector.hub.exception.IntraHubBusinessConnectorException;
import be.ehealth.businessconnector.hub.service.IntraHubService;
import be.ehealth.businessconnector.hub.service.IntraHubTherapeuticLinkService;
import be.ehealth.businessconnector.hub.validators.HubReplyValidator;
import be.ehealth.technicalconnector.config.impl.ConfigurationModuleBootstrap;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.service.sts.security.SAMLToken;
import be.ehealth.technicalconnector.session.Session;
import be.ehealth.technicalconnector.utils.impl.JaxbContextFactory;
import be.fgov.ehealth.hubservices.core.v1.GetTherapeuticLinkRequest;
import be.fgov.ehealth.hubservices.core.v1.GetTherapeuticLinkResponse;
import be.fgov.ehealth.hubservices.core.v1.PutTherapeuticLinkRequest;
import be.fgov.ehealth.hubservices.core.v1.PutTherapeuticLinkResponse;
import be.fgov.ehealth.hubservices.core.v1.RevokeTherapeuticLinkRequest;
import be.fgov.ehealth.hubservices.core.v1.RevokeTherapeuticLinkResponse;
import be.fgov.ehealth.hubservices.core.v1.SelectGetHCPartyPatientConsentType;
import be.fgov.ehealth.hubservices.core.v1.TherapeuticLinkType;
import java.util.ArrayList;
import java.util.Collection;

public class IntraHubTherapeuticLinkServiceImpl extends IntraHubAbstract implements IntraHubTherapeuticLinkService, ConfigurationModuleBootstrap.ModuleBootstrapHook {
   private RequestBuilderComplete builder;

   public IntraHubTherapeuticLinkServiceImpl(IntraHubService hubService, HubReplyValidator validator) throws TechnicalConnectorException, IntraHubBusinessConnectorException {
      super(hubService, validator);
      this.builder = BuilderFactory.getInstance().getRequestBuilderComplete();
   }

   public IntraHubTherapeuticLinkServiceImpl() {
   }

   public void putTherapeuticLink(TherapeuticLinkType therapeuticLink) throws TechnicalConnectorException, IntraHubBusinessConnectorException {
      SAMLToken token = Session.getInstance().getSession().getSAMLToken();
      PutTherapeuticLinkRequest request = this.builder.buildPutTherapeuticLinkRequest(therapeuticLink);
      PutTherapeuticLinkResponse response = this.getService().putTherapeuticLink(token, request);
      this.getReplyValidator().validate(response.getAcknowledge());
   }

   public Collection<TherapeuticLinkType> getTherapeuticLink(SelectGetHCPartyPatientConsentType patientConsent) throws TechnicalConnectorException, IntraHubBusinessConnectorException {
      SAMLToken token = Session.getInstance().getSession().getSAMLToken();
      GetTherapeuticLinkRequest request = this.builder.buildGetTherapeuticLinkRequest(patientConsent);
      GetTherapeuticLinkResponse response = this.getService().getTherapeuticLink(token, request);
      this.getReplyValidator().validate(response.getAcknowledge());
      return (Collection)(response.getTherapeuticlinklist() == null ? new ArrayList() : response.getTherapeuticlinklist().getTherapeuticlinks());
   }

   public void revokeTherapeuticLink(TherapeuticLinkType therapeuticLink) throws TechnicalConnectorException, IntraHubBusinessConnectorException {
      SAMLToken token = Session.getInstance().getSession().getSAMLToken();
      RevokeTherapeuticLinkRequest request = this.builder.buildRevokeTherapeuticLinkRequest(therapeuticLink);
      RevokeTherapeuticLinkResponse response = this.getService().revokeTherapeuticLink(token, request);
      this.getReplyValidator().validate(response.getAcknowledge());
   }

   public void bootstrap() {
      JaxbContextFactory.initJaxbContext(GetTherapeuticLinkRequest.class);
      JaxbContextFactory.initJaxbContext(GetTherapeuticLinkResponse.class);
      JaxbContextFactory.initJaxbContext(PutTherapeuticLinkRequest.class);
      JaxbContextFactory.initJaxbContext(PutTherapeuticLinkResponse.class);
      JaxbContextFactory.initJaxbContext(RevokeTherapeuticLinkRequest.class);
      JaxbContextFactory.initJaxbContext(RevokeTherapeuticLinkResponse.class);
      JaxbContextFactory.initJaxbContext(SelectGetHCPartyPatientConsentType.class);
      JaxbContextFactory.initJaxbContext(TherapeuticLinkType.class);
   }
}
