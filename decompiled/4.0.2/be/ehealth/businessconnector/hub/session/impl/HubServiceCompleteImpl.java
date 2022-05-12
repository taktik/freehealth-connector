package be.ehealth.businessconnector.hub.session.impl;

import be.ehealth.business.intrahubcommons.exception.IntraHubBusinessConnectorException;
import be.ehealth.businessconnector.hub.builders.RequestBuilderComplete;
import be.ehealth.businessconnector.hub.service.IntraHubService;
import be.ehealth.businessconnector.hub.service.ServiceFactory;
import be.ehealth.businessconnector.hub.session.HubServiceComplete;
import be.ehealth.businessconnector.hub.validators.HubReplyValidator;
import be.ehealth.businessconnector.hub.validators.impl.HubReplyValidatorImpl;
import be.ehealth.technicalconnector.exception.ConnectorException;
import be.ehealth.technicalconnector.exception.SessionManagementException;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.service.sts.security.SAMLToken;
import be.ehealth.technicalconnector.session.Session;
import be.ehealth.technicalconnector.validator.SessionValidator;
import be.fgov.ehealth.hubservices.core.v1.AccessRightType;
import be.fgov.ehealth.hubservices.core.v1.ConsentHCPartyType;
import be.fgov.ehealth.hubservices.core.v1.ConsentType;
import be.fgov.ehealth.hubservices.core.v1.DeclareTransactionRequest;
import be.fgov.ehealth.hubservices.core.v1.DeclareTransactionResponse;
import be.fgov.ehealth.hubservices.core.v1.GetAccessRightRequest;
import be.fgov.ehealth.hubservices.core.v1.GetAccessRightResponse;
import be.fgov.ehealth.hubservices.core.v1.GetHCPartyConsentRequest;
import be.fgov.ehealth.hubservices.core.v1.GetHCPartyConsentResponse;
import be.fgov.ehealth.hubservices.core.v1.GetHCPartyRequest;
import be.fgov.ehealth.hubservices.core.v1.GetHCPartyResponse;
import be.fgov.ehealth.hubservices.core.v1.GetPatientAuditTrailRequest;
import be.fgov.ehealth.hubservices.core.v1.GetPatientAuditTrailResponse;
import be.fgov.ehealth.hubservices.core.v1.GetPatientConsentRequest;
import be.fgov.ehealth.hubservices.core.v1.GetPatientConsentResponse;
import be.fgov.ehealth.hubservices.core.v1.GetPatientRequest;
import be.fgov.ehealth.hubservices.core.v1.GetPatientResponse;
import be.fgov.ehealth.hubservices.core.v1.GetTherapeuticLinkRequest;
import be.fgov.ehealth.hubservices.core.v1.GetTherapeuticLinkResponse;
import be.fgov.ehealth.hubservices.core.v1.GetTransactionListRequest;
import be.fgov.ehealth.hubservices.core.v1.GetTransactionListResponse;
import be.fgov.ehealth.hubservices.core.v1.GetTransactionRequest;
import be.fgov.ehealth.hubservices.core.v1.GetTransactionResponse;
import be.fgov.ehealth.hubservices.core.v1.HCPartyAdaptedType;
import be.fgov.ehealth.hubservices.core.v1.HCPartyIdType;
import be.fgov.ehealth.hubservices.core.v1.KmehrHeaderDeclareTransaction;
import be.fgov.ehealth.hubservices.core.v1.LocalSearchType;
import be.fgov.ehealth.hubservices.core.v1.PatientIdType;
import be.fgov.ehealth.hubservices.core.v1.PutAccessRightRequest;
import be.fgov.ehealth.hubservices.core.v1.PutAccessRightResponse;
import be.fgov.ehealth.hubservices.core.v1.PutHCPartyConsentRequest;
import be.fgov.ehealth.hubservices.core.v1.PutHCPartyConsentResponse;
import be.fgov.ehealth.hubservices.core.v1.PutHCPartyRequest;
import be.fgov.ehealth.hubservices.core.v1.PutHCPartyResponse;
import be.fgov.ehealth.hubservices.core.v1.PutPatientConsentRequest;
import be.fgov.ehealth.hubservices.core.v1.PutPatientConsentResponse;
import be.fgov.ehealth.hubservices.core.v1.PutPatientRequest;
import be.fgov.ehealth.hubservices.core.v1.PutPatientResponse;
import be.fgov.ehealth.hubservices.core.v1.PutTherapeuticLinkRequest;
import be.fgov.ehealth.hubservices.core.v1.PutTherapeuticLinkResponse;
import be.fgov.ehealth.hubservices.core.v1.PutTransactionRequest;
import be.fgov.ehealth.hubservices.core.v1.PutTransactionResponse;
import be.fgov.ehealth.hubservices.core.v1.RequestPublicationRequest;
import be.fgov.ehealth.hubservices.core.v1.RequestPublicationResponse;
import be.fgov.ehealth.hubservices.core.v1.RevokeAccessRightRequest;
import be.fgov.ehealth.hubservices.core.v1.RevokeAccessRightResponse;
import be.fgov.ehealth.hubservices.core.v1.RevokeHCPartyConsentRequest;
import be.fgov.ehealth.hubservices.core.v1.RevokeHCPartyConsentResponse;
import be.fgov.ehealth.hubservices.core.v1.RevokePatientConsentRequest;
import be.fgov.ehealth.hubservices.core.v1.RevokePatientConsentResponse;
import be.fgov.ehealth.hubservices.core.v1.RevokeTherapeuticLinkRequest;
import be.fgov.ehealth.hubservices.core.v1.RevokeTherapeuticLinkResponse;
import be.fgov.ehealth.hubservices.core.v1.RevokeTransactionRequest;
import be.fgov.ehealth.hubservices.core.v1.RevokeTransactionResponse;
import be.fgov.ehealth.hubservices.core.v1.SelectGetHCPartyPatientConsentType;
import be.fgov.ehealth.hubservices.core.v1.SelectGetPatientAuditTrailType;
import be.fgov.ehealth.hubservices.core.v1.SelectGetPatientConsentType;
import be.fgov.ehealth.hubservices.core.v1.SelectRevokeAccessRightType;
import be.fgov.ehealth.hubservices.core.v1.TherapeuticLinkType;
import be.fgov.ehealth.hubservices.core.v1.TransactionBaseType;
import be.fgov.ehealth.hubservices.core.v1.TransactionIdType;
import be.fgov.ehealth.hubservices.core.v1.TransactionWithPeriodType;
import be.fgov.ehealth.standards.kmehr.schema.v1.Kmehrmessage;
import be.fgov.ehealth.standards.kmehr.schema.v1.PersonType;

public class HubServiceCompleteImpl implements HubServiceComplete {
   private IntraHubService intrahubService = ServiceFactory.getIntraHubService();
   private SessionValidator sessionValidator;
   private RequestBuilderComplete builder;
   private HubReplyValidator validator;

   public HubServiceCompleteImpl(SessionValidator sessionValidator) throws TechnicalConnectorException, IntraHubBusinessConnectorException {
      this.sessionValidator = sessionValidator;
      this.validator = new HubReplyValidatorImpl();
      this.builder = new RequestBuilderComplete();
   }

   public DeclareTransactionResponse declareTransaction(KmehrHeaderDeclareTransaction kmehrHeader) throws TechnicalConnectorException, IntraHubBusinessConnectorException {
      this.sessionValidator.validateSession();
      SAMLToken token = Session.getInstance().getSession().getSAMLToken();
      DeclareTransactionRequest request = this.builder.buildDeclareTransactionRequest(kmehrHeader);
      DeclareTransactionResponse declareTransaction = this.intrahubService.declareTransaction(token, request);
      this.validator.validate(declareTransaction.getAcknowledge());
      return declareTransaction;
   }

   public PutTransactionResponse putTransaction(Kmehrmessage kmehrHeader) throws TechnicalConnectorException, IntraHubBusinessConnectorException {
      this.sessionValidator.validateSession();
      SAMLToken token = Session.getInstance().getSession().getSAMLToken();
      PutTransactionRequest request = this.builder.buildPutTransactionRequest(kmehrHeader);
      PutTransactionResponse putTransaction = this.intrahubService.putTransaction(token, request);
      this.validator.validate(putTransaction.getAcknowledge());
      return putTransaction;
   }

   public RevokeTransactionResponse revokeTransaction(PatientIdType patient, TransactionIdType transaction) throws TechnicalConnectorException, IntraHubBusinessConnectorException {
      this.sessionValidator.validateSession();
      SAMLToken token = Session.getInstance().getSession().getSAMLToken();
      RevokeTransactionRequest request = this.builder.buildRevokeTransactionRequest(patient, transaction);
      RevokeTransactionResponse revokeTransaction = this.intrahubService.revokeTransaction(token, request);
      this.validator.validate(revokeTransaction.getAcknowledge());
      return revokeTransaction;
   }

   public GetTransactionListResponse getTransactionList(PatientIdType patient, LocalSearchType searchType, TransactionWithPeriodType transaction) throws TechnicalConnectorException, IntraHubBusinessConnectorException {
      this.sessionValidator.validateSession();
      SAMLToken token = Session.getInstance().getSession().getSAMLToken();
      GetTransactionListRequest request = this.builder.buildGetTransactionListRequest(patient, searchType, transaction);
      GetTransactionListResponse transactionList = this.intrahubService.getTransactionList(token, request);
      this.validator.validate(transactionList.getAcknowledge());
      return transactionList;
   }

   public GetTransactionResponse getTransaction(PatientIdType patient, TransactionBaseType transaction) throws TechnicalConnectorException, IntraHubBusinessConnectorException {
      this.sessionValidator.validateSession();
      SAMLToken token = Session.getInstance().getSession().getSAMLToken();
      GetTransactionRequest request = this.builder.buildGetTransactionRequest(patient, transaction);
      GetTransactionResponse getTransactionResponse = this.intrahubService.getTransaction(token, request);
      this.validator.validate(getTransactionResponse.getAcknowledge());
      return getTransactionResponse;
   }

   public RequestPublicationResponse requestPublication(PatientIdType patient, TransactionWithPeriodType transaction, String comment) throws TechnicalConnectorException, IntraHubBusinessConnectorException {
      this.sessionValidator.validateSession();
      SAMLToken token = Session.getInstance().getSession().getSAMLToken();
      RequestPublicationRequest request = this.builder.buildRequestPublicationRequest(patient, transaction, comment);
      return this.intrahubService.requestPublication(token, request);
   }

   public GetHCPartyResponse getHCParty(HCPartyIdType hcParty) throws TechnicalConnectorException, IntraHubBusinessConnectorException {
      this.sessionValidator.validateSession();
      SAMLToken token = Session.getInstance().getSession().getSAMLToken();
      GetHCPartyRequest request = this.builder.buildGetHcPartyRequest(hcParty);
      return this.intrahubService.getHCParty(token, request);
   }

   public PutHCPartyResponse putHCParty(HCPartyAdaptedType hcParty) throws TechnicalConnectorException, IntraHubBusinessConnectorException {
      this.sessionValidator.validateSession();
      SAMLToken token = Session.getInstance().getSession().getSAMLToken();
      PutHCPartyRequest request = this.builder.buildPutHcPartyRequest(hcParty);
      return this.intrahubService.putHCParty(token, request);
   }

   public PutHCPartyConsentResponse putHCPartyConsent(ConsentHCPartyType consent) throws TechnicalConnectorException, IntraHubBusinessConnectorException {
      this.sessionValidator.validateSession();
      SAMLToken token = Session.getInstance().getSession().getSAMLToken();
      PutHCPartyConsentRequest request = this.builder.buildPutHcPartyConsentRequest(consent);
      return this.intrahubService.putHCPartyConsent(token, request);
   }

   public GetHCPartyConsentResponse getHCPartyConsent(HCPartyIdType hcPartyId) throws TechnicalConnectorException, IntraHubBusinessConnectorException {
      this.sessionValidator.validateSession();
      SAMLToken token = Session.getInstance().getSession().getSAMLToken();
      GetHCPartyConsentRequest request = this.builder.buildGetHcPartyConsent(hcPartyId);
      return this.intrahubService.getHCPartyConsent(token, request);
   }

   public RevokeHCPartyConsentResponse revokeHCPartyConsent(ConsentHCPartyType consent) throws TechnicalConnectorException, IntraHubBusinessConnectorException {
      this.sessionValidator.validateSession();
      SAMLToken token = Session.getInstance().getSession().getSAMLToken();
      RevokeHCPartyConsentRequest request = this.builder.buildRevokeHcPartyConsent(consent);
      return this.intrahubService.revokeHCPartyConsent(token, request);
   }

   public PutPatientResponse putPatient(PersonType patient) throws TechnicalConnectorException, IntraHubBusinessConnectorException {
      this.sessionValidator.validateSession();
      SAMLToken token = Session.getInstance().getSession().getSAMLToken();
      PutPatientRequest request = this.builder.buildPutPatientRequest(patient);
      return this.intrahubService.putPatient(token, request);
   }

   public GetPatientResponse getPatient(PatientIdType patientId) throws TechnicalConnectorException, IntraHubBusinessConnectorException {
      this.sessionValidator.validateSession();
      SAMLToken token = Session.getInstance().getSession().getSAMLToken();
      GetPatientRequest request = this.builder.buildGetPatientRequest(patientId);
      return this.intrahubService.getPatient(token, request);
   }

   public PutPatientConsentResponse putPatientConsent(ConsentType patientConsent) throws TechnicalConnectorException, IntraHubBusinessConnectorException {
      this.sessionValidator.validateSession();
      SAMLToken token = Session.getInstance().getSession().getSAMLToken();
      PutPatientConsentRequest request = this.builder.buildPutPatientConsentRequest(patientConsent);
      return this.intrahubService.putPatientConsent(token, request);
   }

   public GetPatientConsentResponse getPatientConsent(SelectGetPatientConsentType patientConsent) throws TechnicalConnectorException, IntraHubBusinessConnectorException {
      this.sessionValidator.validateSession();
      SAMLToken token = Session.getInstance().getSession().getSAMLToken();
      GetPatientConsentRequest request = this.builder.buildGetPatientConsent(patientConsent);
      return this.intrahubService.getPatientConsent(token, request);
   }

   public RevokePatientConsentResponse revokePatientConsent(ConsentType patientConsent) throws TechnicalConnectorException, IntraHubBusinessConnectorException {
      this.sessionValidator.validateSession();
      SAMLToken token = Session.getInstance().getSession().getSAMLToken();
      RevokePatientConsentRequest request = this.builder.buildRevokePatientConsentRequest(patientConsent);
      return this.intrahubService.revokePatientConsent(token, request);
   }

   public PutTherapeuticLinkResponse putTherapeuticLink(TherapeuticLinkType therapeuticLink) throws ConnectorException, SessionManagementException {
      this.sessionValidator.validateSession();
      SAMLToken token = Session.getInstance().getSession().getSAMLToken();
      PutTherapeuticLinkRequest request = this.builder.buildPutTherapeuticLinkRequest(therapeuticLink);
      return this.intrahubService.putTherapeuticLink(token, request);
   }

   public GetTherapeuticLinkResponse getTherapeuticLink(SelectGetHCPartyPatientConsentType patientConsent) throws ConnectorException {
      this.sessionValidator.validateSession();
      SAMLToken token = Session.getInstance().getSession().getSAMLToken();
      GetTherapeuticLinkRequest request = this.builder.buildGetTherapeuticLinkRequest(patientConsent);
      return this.intrahubService.getTherapeuticLink(token, request);
   }

   public RevokeTherapeuticLinkResponse revokeTherapeuticLink(TherapeuticLinkType therapeuticLink) throws TechnicalConnectorException, IntraHubBusinessConnectorException {
      this.sessionValidator.validateSession();
      SAMLToken token = Session.getInstance().getSession().getSAMLToken();
      RevokeTherapeuticLinkRequest request = this.builder.buildRevokeTherapeuticLinkRequest(therapeuticLink);
      return this.intrahubService.revokeTherapeuticLink(token, request);
   }

   public PutAccessRightResponse putAccessRight(AccessRightType accessRight) throws TechnicalConnectorException, IntraHubBusinessConnectorException {
      this.sessionValidator.validateSession();
      SAMLToken token = Session.getInstance().getSession().getSAMLToken();
      PutAccessRightRequest request = this.builder.buildPutAccessRightRequest(accessRight);
      return this.intrahubService.putAccessRight(token, request);
   }

   public GetAccessRightResponse getAccessRight(TransactionIdType transaction) throws TechnicalConnectorException, IntraHubBusinessConnectorException {
      this.sessionValidator.validateSession();
      SAMLToken token = Session.getInstance().getSession().getSAMLToken();
      GetAccessRightRequest request = this.builder.buildGetAccessRight(transaction);
      return this.intrahubService.getAccessRight(token, request);
   }

   public RevokeAccessRightResponse revokeAccessRight(SelectRevokeAccessRightType accessRight) throws TechnicalConnectorException, IntraHubBusinessConnectorException {
      this.sessionValidator.validateSession();
      SAMLToken token = Session.getInstance().getSession().getSAMLToken();
      RevokeAccessRightRequest request = this.builder.buildRevokeAccessRight(accessRight);
      return this.intrahubService.revokeAccessRight(token, request);
   }

   public GetPatientAuditTrailResponse getPatientAuditTrail(SelectGetPatientAuditTrailType patientAuditTrail) throws TechnicalConnectorException, IntraHubBusinessConnectorException {
      this.sessionValidator.validateSession();
      SAMLToken token = Session.getInstance().getSession().getSAMLToken();
      GetPatientAuditTrailRequest request = this.builder.buildGetPatientAudiTrail(patientAuditTrail);
      return this.intrahubService.getPatientAuditTrail(token, request);
   }
}
