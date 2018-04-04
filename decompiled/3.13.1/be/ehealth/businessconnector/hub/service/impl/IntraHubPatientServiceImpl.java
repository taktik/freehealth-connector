package be.ehealth.businessconnector.hub.service.impl;

import be.ehealth.businessconnector.hub.builders.BuilderFactory;
import be.ehealth.businessconnector.hub.builders.RequestBuilderComplete;
import be.ehealth.businessconnector.hub.exception.IntraHubBusinessConnectorException;
import be.ehealth.businessconnector.hub.service.IntraHubPatientService;
import be.ehealth.businessconnector.hub.service.IntraHubService;
import be.ehealth.businessconnector.hub.validators.HubReplyValidator;
import be.ehealth.technicalconnector.config.impl.ConfigurationModuleBootstrap;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.service.sts.security.SAMLToken;
import be.ehealth.technicalconnector.session.Session;
import be.ehealth.technicalconnector.utils.impl.JaxbContextFactory;
import be.fgov.ehealth.hubservices.core.v1.ConsentType;
import be.fgov.ehealth.hubservices.core.v1.GetPatientConsentRequest;
import be.fgov.ehealth.hubservices.core.v1.GetPatientConsentResponse;
import be.fgov.ehealth.hubservices.core.v1.GetPatientRequest;
import be.fgov.ehealth.hubservices.core.v1.GetPatientResponse;
import be.fgov.ehealth.hubservices.core.v1.PatientIdType;
import be.fgov.ehealth.hubservices.core.v1.PutPatientConsentRequest;
import be.fgov.ehealth.hubservices.core.v1.PutPatientConsentResponse;
import be.fgov.ehealth.hubservices.core.v1.PutPatientRequest;
import be.fgov.ehealth.hubservices.core.v1.PutPatientResponse;
import be.fgov.ehealth.hubservices.core.v1.RevokePatientConsentRequest;
import be.fgov.ehealth.hubservices.core.v1.RevokePatientConsentResponse;
import be.fgov.ehealth.hubservices.core.v1.SelectGetPatientConsentType;
import be.fgov.ehealth.standards.kmehr.schema.v1.PersonType;

public class IntraHubPatientServiceImpl extends IntraHubAbstract implements IntraHubPatientService, ConfigurationModuleBootstrap.ModuleBootstrapHook {
   private RequestBuilderComplete builder;

   public IntraHubPatientServiceImpl(IntraHubService hubService, HubReplyValidator validator) throws TechnicalConnectorException, IntraHubBusinessConnectorException {
      super(hubService, validator);
      this.builder = BuilderFactory.getInstance().getRequestBuilderComplete();
   }

   public IntraHubPatientServiceImpl() {
   }

   public PersonType putPatient(PersonType patient) throws TechnicalConnectorException, IntraHubBusinessConnectorException {
      SAMLToken token = Session.getInstance().getSession().getSAMLToken();
      PutPatientRequest request = this.builder.buildPutPatientRequest(patient);
      PutPatientResponse response = this.getService().putPatient(token, request);
      this.getReplyValidator().validate(response.getAcknowledge());
      return response.getPatient();
   }

   public PersonType getPatient(PatientIdType patientId) throws TechnicalConnectorException, IntraHubBusinessConnectorException {
      SAMLToken token = Session.getInstance().getSession().getSAMLToken();
      GetPatientRequest request = this.builder.buildGetPatientRequest(patientId);
      GetPatientResponse response = this.getService().getPatient(token, request);
      this.getReplyValidator().validate(response.getAcknowledge());
      return response.getPatient();
   }

   public void putPatientConsent(ConsentType patientConsent) throws TechnicalConnectorException, IntraHubBusinessConnectorException {
      SAMLToken token = Session.getInstance().getSession().getSAMLToken();
      PutPatientConsentRequest request = this.builder.buildPutPatientConsentRequest(patientConsent);
      PutPatientConsentResponse response = this.getService().putPatientConsent(token, request);
      this.getReplyValidator().validate(response.getAcknowledge());
   }

   public ConsentType getPatientConsent(SelectGetPatientConsentType patientConsent) throws TechnicalConnectorException, IntraHubBusinessConnectorException {
      SAMLToken token = Session.getInstance().getSession().getSAMLToken();
      GetPatientConsentRequest request = this.builder.buildGetPatientConsent(patientConsent);
      GetPatientConsentResponse response = this.getService().getPatientConsent(token, request);
      this.getReplyValidator().validate(response.getAcknowledge());
      return response.getConsent();
   }

   public void revokePatientConsent(ConsentType patientConsent) throws TechnicalConnectorException, IntraHubBusinessConnectorException {
      SAMLToken token = Session.getInstance().getSession().getSAMLToken();
      RevokePatientConsentRequest request = this.builder.buildRevokePatientConsentRequest(patientConsent);
      RevokePatientConsentResponse response = this.getService().revokePatientConsent(token, request);
      this.getReplyValidator().validate(response.getAcknowledge());
   }

   public void bootstrap() {
      JaxbContextFactory.initJaxbContext(ConsentType.class);
      JaxbContextFactory.initJaxbContext(GetPatientConsentRequest.class);
      JaxbContextFactory.initJaxbContext(GetPatientConsentResponse.class);
      JaxbContextFactory.initJaxbContext(GetPatientRequest.class);
      JaxbContextFactory.initJaxbContext(GetPatientResponse.class);
      JaxbContextFactory.initJaxbContext(PatientIdType.class);
      JaxbContextFactory.initJaxbContext(PutPatientConsentRequest.class);
      JaxbContextFactory.initJaxbContext(PutPatientConsentResponse.class);
      JaxbContextFactory.initJaxbContext(PutPatientRequest.class);
      JaxbContextFactory.initJaxbContext(PutPatientResponse.class);
      JaxbContextFactory.initJaxbContext(RevokePatientConsentRequest.class);
      JaxbContextFactory.initJaxbContext(RevokePatientConsentResponse.class);
      JaxbContextFactory.initJaxbContext(SelectGetPatientConsentType.class);
      JaxbContextFactory.initJaxbContext(PersonType.class);
   }
}
