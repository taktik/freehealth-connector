package be.ehealth.businessconnector.hubv3.session.impl;

import be.ehealth.business.intrahubcommons.exception.IntraHubBusinessConnectorException;
import be.ehealth.businessconnector.hubv3.builders.RequestBuilderFactory;
import be.ehealth.businessconnector.hubv3.builders.impl.RequestBuilderImpl;
import be.ehealth.businessconnector.hubv3.service.HubTokenService;
import be.ehealth.businessconnector.hubv3.service.ServiceFactory;
import be.ehealth.businessconnector.hubv3.session.HubService;
import be.ehealth.businessconnector.hubv3.validators.HubReplyValidator;
import be.ehealth.businessconnector.hubv3.validators.impl.HubReplyValidatorImpl;
import be.ehealth.technicalconnector.exception.SessionManagementException;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.session.Session;
import be.ehealth.technicalconnector.validator.SessionValidator;
import be.fgov.ehealth.hubservices.core.v3.AccessRightType;
import be.fgov.ehealth.hubservices.core.v3.ConsentHCPartyType;
import be.fgov.ehealth.hubservices.core.v3.ConsentType;
import be.fgov.ehealth.hubservices.core.v3.DeclareTransactionRequest;
import be.fgov.ehealth.hubservices.core.v3.DeclareTransactionResponse;
import be.fgov.ehealth.hubservices.core.v3.GetAccessRightRequest;
import be.fgov.ehealth.hubservices.core.v3.GetAccessRightResponse;
import be.fgov.ehealth.hubservices.core.v3.GetHCPartyConsentRequest;
import be.fgov.ehealth.hubservices.core.v3.GetHCPartyConsentResponse;
import be.fgov.ehealth.hubservices.core.v3.GetHCPartyRequest;
import be.fgov.ehealth.hubservices.core.v3.GetHCPartyResponse;
import be.fgov.ehealth.hubservices.core.v3.GetLatestUpdateRequest;
import be.fgov.ehealth.hubservices.core.v3.GetLatestUpdateResponse;
import be.fgov.ehealth.hubservices.core.v3.GetPatientAuditTrailRequest;
import be.fgov.ehealth.hubservices.core.v3.GetPatientAuditTrailResponse;
import be.fgov.ehealth.hubservices.core.v3.GetPatientConsentRequest;
import be.fgov.ehealth.hubservices.core.v3.GetPatientConsentResponse;
import be.fgov.ehealth.hubservices.core.v3.GetPatientRequest;
import be.fgov.ehealth.hubservices.core.v3.GetPatientResponse;
import be.fgov.ehealth.hubservices.core.v3.GetTherapeuticLinkRequest;
import be.fgov.ehealth.hubservices.core.v3.GetTherapeuticLinkResponse;
import be.fgov.ehealth.hubservices.core.v3.GetTransactionListRequest;
import be.fgov.ehealth.hubservices.core.v3.GetTransactionListResponse;
import be.fgov.ehealth.hubservices.core.v3.GetTransactionRequest;
import be.fgov.ehealth.hubservices.core.v3.GetTransactionResponse;
import be.fgov.ehealth.hubservices.core.v3.GetTransactionSetRequest;
import be.fgov.ehealth.hubservices.core.v3.GetTransactionSetResponse;
import be.fgov.ehealth.hubservices.core.v3.HCPartyAdaptedType;
import be.fgov.ehealth.hubservices.core.v3.HCPartyIdType;
import be.fgov.ehealth.hubservices.core.v3.KmehrHeaderDeclareTransaction;
import be.fgov.ehealth.hubservices.core.v3.LocalSearchType;
import be.fgov.ehealth.hubservices.core.v3.Paginationrequestinfo;
import be.fgov.ehealth.hubservices.core.v3.PatientIdType;
import be.fgov.ehealth.hubservices.core.v3.PutAccessRightRequest;
import be.fgov.ehealth.hubservices.core.v3.PutAccessRightResponse;
import be.fgov.ehealth.hubservices.core.v3.PutHCPartyConsentRequest;
import be.fgov.ehealth.hubservices.core.v3.PutHCPartyConsentResponse;
import be.fgov.ehealth.hubservices.core.v3.PutHCPartyRequest;
import be.fgov.ehealth.hubservices.core.v3.PutHCPartyResponse;
import be.fgov.ehealth.hubservices.core.v3.PutPatientConsentRequest;
import be.fgov.ehealth.hubservices.core.v3.PutPatientConsentResponse;
import be.fgov.ehealth.hubservices.core.v3.PutPatientRequest;
import be.fgov.ehealth.hubservices.core.v3.PutPatientResponse;
import be.fgov.ehealth.hubservices.core.v3.PutTherapeuticLinkRequest;
import be.fgov.ehealth.hubservices.core.v3.PutTherapeuticLinkResponse;
import be.fgov.ehealth.hubservices.core.v3.PutTransactionRequest;
import be.fgov.ehealth.hubservices.core.v3.PutTransactionResponse;
import be.fgov.ehealth.hubservices.core.v3.PutTransactionSetRequest;
import be.fgov.ehealth.hubservices.core.v3.PutTransactionSetResponse;
import be.fgov.ehealth.hubservices.core.v3.RequestPublicationRequest;
import be.fgov.ehealth.hubservices.core.v3.RequestPublicationResponse;
import be.fgov.ehealth.hubservices.core.v3.RevokeAccessRightRequest;
import be.fgov.ehealth.hubservices.core.v3.RevokeAccessRightResponse;
import be.fgov.ehealth.hubservices.core.v3.RevokeHCPartyConsentRequest;
import be.fgov.ehealth.hubservices.core.v3.RevokeHCPartyConsentResponse;
import be.fgov.ehealth.hubservices.core.v3.RevokePatientConsentRequest;
import be.fgov.ehealth.hubservices.core.v3.RevokePatientConsentResponse;
import be.fgov.ehealth.hubservices.core.v3.RevokeTherapeuticLinkRequest;
import be.fgov.ehealth.hubservices.core.v3.RevokeTherapeuticLinkResponse;
import be.fgov.ehealth.hubservices.core.v3.RevokeTransactionRequest;
import be.fgov.ehealth.hubservices.core.v3.RevokeTransactionResponse;
import be.fgov.ehealth.hubservices.core.v3.SelectGetHCPartyPatientConsentType;
import be.fgov.ehealth.hubservices.core.v3.SelectGetLatestUpdateType;
import be.fgov.ehealth.hubservices.core.v3.SelectGetPatientAuditTrailType;
import be.fgov.ehealth.hubservices.core.v3.SelectGetPatientConsentType;
import be.fgov.ehealth.hubservices.core.v3.SelectRevokeAccessRightType;
import be.fgov.ehealth.hubservices.core.v3.TherapeuticLinkType;
import be.fgov.ehealth.hubservices.core.v3.TransactionBaseType;
import be.fgov.ehealth.hubservices.core.v3.TransactionIdType;
import be.fgov.ehealth.hubservices.core.v3.TransactionWithPeriodType;
import be.fgov.ehealth.standards.kmehr.schema.v1.Kmehrmessage;
import be.fgov.ehealth.standards.kmehr.schema.v1.PersonType;

public class HubServiceImpl implements HubService {
   private final HubTokenService intrahubTokenService = ServiceFactory.getIntraHubService();
   private final SessionValidator sessionValidator;
   private final RequestBuilderImpl builder;
   private final HubReplyValidator validator;

   public HubServiceImpl(SessionValidator sessionValidator) throws TechnicalConnectorException {
      this.sessionValidator = sessionValidator;
      this.validator = new HubReplyValidatorImpl();
      this.builder = RequestBuilderFactory.getRequestBuilder();
   }

   public DeclareTransactionResponse declareTransaction(KmehrHeaderDeclareTransaction kmehrHeader, String breakTheGlass) throws TechnicalConnectorException, IntraHubBusinessConnectorException {
      this.validateSession();
      DeclareTransactionRequest request = this.builder.buildDeclareTransactionRequest(kmehrHeader);
      DeclareTransactionResponse declareTransaction = this.intrahubTokenService.declareTransaction(Session.getSAMLToken(), request, breakTheGlass);
      this.validator.validate(declareTransaction.getAcknowledge());
      return declareTransaction;
   }

   public DeclareTransactionResponse declareTransaction(KmehrHeaderDeclareTransaction kmehrHeader) throws TechnicalConnectorException, IntraHubBusinessConnectorException {
      return this.declareTransaction(kmehrHeader, (String)null);
   }

   public PutTransactionResponse putTransaction(Kmehrmessage kmehrHeader, String breakTheGlass) throws TechnicalConnectorException, IntraHubBusinessConnectorException {
      this.validateSession();
      PutTransactionRequest request = this.builder.buildPutTransactionRequest(kmehrHeader);
      PutTransactionResponse putTransaction = this.intrahubTokenService.putTransaction(Session.getSAMLToken(), request, breakTheGlass);
      this.validator.validate(putTransaction.getAcknowledge());
      return putTransaction;
   }

   public PutTransactionResponse putTransaction(Kmehrmessage kmehrHeader) throws TechnicalConnectorException, IntraHubBusinessConnectorException {
      return this.putTransaction(kmehrHeader, (String)null);
   }

   public RevokeTransactionResponse revokeTransaction(PatientIdType patient, TransactionBaseType transaction, String breakTheGlass) throws TechnicalConnectorException, IntraHubBusinessConnectorException {
      this.validateSession();
      RevokeTransactionRequest request = this.builder.buildRevokeTransactionRequest(patient, transaction);
      RevokeTransactionResponse revokeTransaction = this.intrahubTokenService.revokeTransaction(Session.getSAMLToken(), request, breakTheGlass);
      this.validator.validate(revokeTransaction.getAcknowledge());
      return revokeTransaction;
   }

   public RevokeTransactionResponse revokeTransaction(PatientIdType patient, TransactionBaseType transaction) throws TechnicalConnectorException, IntraHubBusinessConnectorException {
      return this.revokeTransaction(patient, transaction, (String)null);
   }

   public GetTransactionListResponse getTransactionList(PatientIdType patient, LocalSearchType searchType, TransactionWithPeriodType transaction, String breakTheGlass, Integer maxRows) throws TechnicalConnectorException, IntraHubBusinessConnectorException {
      this.validateSession();
      GetTransactionListRequest request = this.builder.buildGetTransactionListRequest(patient, searchType, transaction);
      GetTransactionListResponse transactionList = this.intrahubTokenService.getTransactionList(Session.getSAMLToken(), request, breakTheGlass);
      this.validator.validate(transactionList.getAcknowledge());
      return transactionList;
   }

   public GetTransactionListResponse getTransactionList(PatientIdType patient, LocalSearchType searchType, TransactionWithPeriodType transaction, Integer maxRows) throws TechnicalConnectorException, IntraHubBusinessConnectorException {
      return this.getTransactionList(patient, searchType, transaction, (String)null, (Integer)maxRows);
   }

   public GetTransactionListResponse getTransactionList(PatientIdType patient, LocalSearchType searchType, TransactionWithPeriodType transaction, String breakTheGlass) throws TechnicalConnectorException, IntraHubBusinessConnectorException {
      return this.getTransactionList(patient, searchType, transaction, (String)breakTheGlass, (Integer)null);
   }

   public GetTransactionListResponse getTransactionList(PatientIdType patient, LocalSearchType searchType, TransactionWithPeriodType transaction) throws TechnicalConnectorException, IntraHubBusinessConnectorException {
      return this.getTransactionList(patient, searchType, transaction, (String)null, (Integer)null);
   }

   public GetTransactionListResponse getTransactionList(PatientIdType patient, LocalSearchType searchType, TransactionWithPeriodType transaction, Paginationrequestinfo paginationrequestinfo, String breakTheGlass) throws TechnicalConnectorException, IntraHubBusinessConnectorException {
      this.validateSession();
      GetTransactionListRequest request = this.builder.buildGetTransactionListRequest(patient, searchType, transaction);
      GetTransactionListResponse transactionList = this.intrahubTokenService.getTransactionList(Session.getSAMLToken(), request, paginationrequestinfo, breakTheGlass);
      this.validator.validate(transactionList.getAcknowledge());
      return transactionList;
   }

   public GetTransactionListResponse getTransactionList(PatientIdType patient, LocalSearchType searchType, TransactionWithPeriodType transaction, Paginationrequestinfo paginationrequestinfo) throws TechnicalConnectorException, IntraHubBusinessConnectorException {
      return this.getTransactionList(patient, searchType, transaction, (Paginationrequestinfo)paginationrequestinfo, (String)null);
   }

   public GetTransactionResponse getTransaction(PatientIdType patient, TransactionBaseType transaction, String breakTheGlass) throws TechnicalConnectorException, IntraHubBusinessConnectorException {
      this.validateSession();
      GetTransactionRequest request = this.builder.buildGetTransactionRequest(patient, transaction);
      GetTransactionResponse getTransactionResponse = this.intrahubTokenService.getTransaction(Session.getSAMLToken(), request, breakTheGlass);
      this.validator.validate(getTransactionResponse.getAcknowledge());
      return getTransactionResponse;
   }

   public GetTransactionResponse getTransaction(PatientIdType patient, TransactionBaseType transaction) throws TechnicalConnectorException, IntraHubBusinessConnectorException {
      return this.getTransaction(patient, transaction, (String)null);
   }

   public RequestPublicationResponse requestPublication(PatientIdType patient, TransactionWithPeriodType transaction, String comment, String breakTheGlass) throws TechnicalConnectorException, IntraHubBusinessConnectorException {
      this.validateSession();
      RequestPublicationRequest request = this.builder.buildRequestPublicationRequest(patient, transaction, comment);
      return this.intrahubTokenService.requestPublication(Session.getSAMLToken(), request, breakTheGlass);
   }

   public RequestPublicationResponse requestPublication(PatientIdType patient, TransactionWithPeriodType transaction, String comment) throws TechnicalConnectorException, IntraHubBusinessConnectorException {
      return this.requestPublication(patient, transaction, comment, (String)null);
   }

   public PutHCPartyResponse putHCParty(HCPartyAdaptedType hcParty, String breakTheGlass) throws TechnicalConnectorException, IntraHubBusinessConnectorException {
      this.validateSession();
      PutHCPartyRequest request = this.builder.buildPutHcPartyRequest(hcParty);
      return this.intrahubTokenService.putHCParty(Session.getSAMLToken(), request, breakTheGlass);
   }

   public PutHCPartyResponse putHCParty(HCPartyAdaptedType hcParty) throws TechnicalConnectorException, IntraHubBusinessConnectorException {
      return this.putHCParty(hcParty, (String)null);
   }

   public GetHCPartyResponse getHCParty(HCPartyIdType hcParty, String breakTheGlass) throws TechnicalConnectorException, IntraHubBusinessConnectorException {
      this.validateSession();
      GetHCPartyRequest request = this.builder.buildGetHcPartyRequest(hcParty);
      return this.intrahubTokenService.getHCParty(Session.getSAMLToken(), request, breakTheGlass);
   }

   public GetHCPartyResponse getHCParty(HCPartyIdType hcParty) throws TechnicalConnectorException, IntraHubBusinessConnectorException {
      return this.getHCParty(hcParty, (String)null);
   }

   public PutHCPartyConsentResponse putHCPartyConsent(ConsentHCPartyType consent, String breakTheGlass) throws TechnicalConnectorException, IntraHubBusinessConnectorException {
      this.validateSession();
      PutHCPartyConsentRequest request = this.builder.buildPutHcPartyConsentRequest(consent);
      return this.intrahubTokenService.putHCPartyConsent(Session.getSAMLToken(), request, breakTheGlass);
   }

   public PutHCPartyConsentResponse putHCPartyConsent(ConsentHCPartyType consent) throws TechnicalConnectorException, IntraHubBusinessConnectorException {
      return this.putHCPartyConsent(consent, (String)null);
   }

   public GetHCPartyConsentResponse getHCPartyConsent(HCPartyIdType hcPartyId, String breakTheGlass) throws TechnicalConnectorException, IntraHubBusinessConnectorException {
      this.validateSession();
      GetHCPartyConsentRequest request = this.builder.buildGetHcPartyConsent(hcPartyId);
      return this.intrahubTokenService.getHCPartyConsent(Session.getSAMLToken(), request, breakTheGlass);
   }

   public GetHCPartyConsentResponse getHCPartyConsent(HCPartyIdType hcPartyId) throws TechnicalConnectorException, IntraHubBusinessConnectorException {
      return this.getHCPartyConsent(hcPartyId, (String)null);
   }

   public RevokeHCPartyConsentResponse revokeHCPartyConsent(ConsentHCPartyType consent, String breakTheGlass) throws TechnicalConnectorException, IntraHubBusinessConnectorException {
      this.validateSession();
      RevokeHCPartyConsentRequest request = this.builder.buildRevokeHcPartyConsent(consent);
      return this.intrahubTokenService.revokeHCPartyConsent(Session.getSAMLToken(), request, breakTheGlass);
   }

   public RevokeHCPartyConsentResponse revokeHCPartyConsent(ConsentHCPartyType consent) throws TechnicalConnectorException, IntraHubBusinessConnectorException {
      return this.revokeHCPartyConsent(consent, (String)null);
   }

   public PutPatientResponse putPatient(PersonType patient, String breakTheGlass) throws TechnicalConnectorException, IntraHubBusinessConnectorException {
      this.validateSession();
      PutPatientRequest request = this.builder.buildPutPatientRequest(patient);
      return this.intrahubTokenService.putPatient(Session.getSAMLToken(), request, breakTheGlass);
   }

   public PutPatientResponse putPatient(PersonType patient) throws TechnicalConnectorException, IntraHubBusinessConnectorException {
      return this.putPatient(patient, (String)null);
   }

   public GetPatientResponse getPatient(PatientIdType patientId, String breakTheGlass) throws TechnicalConnectorException, IntraHubBusinessConnectorException {
      this.validateSession();
      GetPatientRequest request = this.builder.buildGetPatientRequest(patientId);
      return this.intrahubTokenService.getPatient(Session.getSAMLToken(), request, breakTheGlass);
   }

   public GetPatientResponse getPatient(PatientIdType patientId) throws TechnicalConnectorException, IntraHubBusinessConnectorException {
      return this.getPatient(patientId, (String)null);
   }

   public PutPatientConsentResponse putPatientConsent(ConsentType patientConsent, String breakTheGlass) throws TechnicalConnectorException, IntraHubBusinessConnectorException {
      this.validateSession();
      PutPatientConsentRequest request = this.builder.buildPutPatientConsentRequest(patientConsent);
      return this.intrahubTokenService.putPatientConsent(Session.getSAMLToken(), request, breakTheGlass);
   }

   public PutPatientConsentResponse putPatientConsent(ConsentType patientConsent) throws TechnicalConnectorException, IntraHubBusinessConnectorException {
      return this.putPatientConsent(patientConsent, (String)null);
   }

   public GetPatientConsentResponse getPatientConsent(SelectGetPatientConsentType patientConsent, String breakTheGlass) throws TechnicalConnectorException, IntraHubBusinessConnectorException {
      this.validateSession();
      GetPatientConsentRequest request = this.builder.buildGetPatientConsent(patientConsent);
      return this.intrahubTokenService.getPatientConsent(Session.getSAMLToken(), request, breakTheGlass);
   }

   public GetPatientConsentResponse getPatientConsent(SelectGetPatientConsentType patientConsent) throws TechnicalConnectorException, IntraHubBusinessConnectorException {
      return this.getPatientConsent(patientConsent, (String)null);
   }

   public RevokePatientConsentResponse revokePatientConsent(ConsentType patientConsent, String breakTheGlass) throws TechnicalConnectorException, IntraHubBusinessConnectorException {
      this.validateSession();
      RevokePatientConsentRequest request = this.builder.buildRevokePatientConsentRequest(patientConsent);
      return this.intrahubTokenService.revokePatientConsent(Session.getSAMLToken(), request, breakTheGlass);
   }

   public RevokePatientConsentResponse revokePatientConsent(ConsentType patientConsent) throws TechnicalConnectorException, IntraHubBusinessConnectorException {
      return this.revokePatientConsent(patientConsent, (String)null);
   }

   public PutTherapeuticLinkResponse putTherapeuticLink(TherapeuticLinkType therapeuticLink, String breakTheGlass) throws TechnicalConnectorException, IntraHubBusinessConnectorException {
      this.validateSession();
      PutTherapeuticLinkRequest request = this.builder.buildPutTherapeuticLinkRequest(therapeuticLink);
      return this.intrahubTokenService.putTherapeuticLink(Session.getSAMLToken(), request, breakTheGlass);
   }

   public PutTherapeuticLinkResponse putTherapeuticLink(TherapeuticLinkType therapeuticLink) throws TechnicalConnectorException, IntraHubBusinessConnectorException {
      return this.putTherapeuticLink(therapeuticLink, (String)null);
   }

   public GetTherapeuticLinkResponse getTherapeuticLink(SelectGetHCPartyPatientConsentType patientConsent, String breakTheGlass) throws TechnicalConnectorException, IntraHubBusinessConnectorException {
      this.validateSession();
      GetTherapeuticLinkRequest request = this.builder.buildGetTherapeuticLinkRequest(patientConsent);
      return this.intrahubTokenService.getTherapeuticLink(Session.getSAMLToken(), request, breakTheGlass);
   }

   public GetTherapeuticLinkResponse getTherapeuticLink(SelectGetHCPartyPatientConsentType patientConsent) throws TechnicalConnectorException, IntraHubBusinessConnectorException {
      return this.getTherapeuticLink(patientConsent, (String)null);
   }

   public RevokeTherapeuticLinkResponse revokeTherapeuticLink(TherapeuticLinkType therapeuticLink, String breakTheGlass) throws TechnicalConnectorException, IntraHubBusinessConnectorException {
      this.validateSession();
      RevokeTherapeuticLinkRequest request = this.builder.buildRevokeTherapeuticLinkRequest(therapeuticLink);
      return this.intrahubTokenService.revokeTherapeuticLink(Session.getSAMLToken(), request, breakTheGlass);
   }

   public RevokeTherapeuticLinkResponse revokeTherapeuticLink(TherapeuticLinkType therapeuticLink) throws TechnicalConnectorException, IntraHubBusinessConnectorException {
      return this.revokeTherapeuticLink(therapeuticLink, (String)null);
   }

   public PutAccessRightResponse putAccessRight(AccessRightType accessRight, String breakTheGlass) throws TechnicalConnectorException, IntraHubBusinessConnectorException {
      this.validateSession();
      PutAccessRightRequest request = this.builder.buildPutAccessRightRequest(accessRight);
      return this.intrahubTokenService.putAccessRight(Session.getSAMLToken(), request, breakTheGlass);
   }

   public PutAccessRightResponse putAccessRight(AccessRightType accessRight) throws TechnicalConnectorException, IntraHubBusinessConnectorException {
      return this.putAccessRight(accessRight, (String)null);
   }

   public GetAccessRightResponse getAccessRight(TransactionIdType transaction, String breakTheGlass) throws TechnicalConnectorException, IntraHubBusinessConnectorException {
      this.validateSession();
      GetAccessRightRequest request = this.builder.buildGetAccessRight(transaction);
      return this.intrahubTokenService.getAccessRight(Session.getSAMLToken(), request, breakTheGlass);
   }

   public GetAccessRightResponse getAccessRight(TransactionIdType transaction) throws TechnicalConnectorException, IntraHubBusinessConnectorException {
      return this.getAccessRight(transaction, (String)null);
   }

   public RevokeAccessRightResponse revokeAccessRight(SelectRevokeAccessRightType accessRight, String breakTheGlass) throws TechnicalConnectorException, IntraHubBusinessConnectorException {
      this.validateSession();
      RevokeAccessRightRequest request = this.builder.buildRevokeAccessRight(accessRight);
      return this.intrahubTokenService.revokeAccessRight(Session.getSAMLToken(), request, breakTheGlass);
   }

   public RevokeAccessRightResponse revokeAccessRight(SelectRevokeAccessRightType accessRight) throws TechnicalConnectorException, IntraHubBusinessConnectorException {
      return this.revokeAccessRight(accessRight, (String)null);
   }

   public GetTransactionSetResponse getTransactionSet(PatientIdType patient, TransactionBaseType transaction, String breakTheGlass) throws TechnicalConnectorException, IntraHubBusinessConnectorException {
      this.validateSession();
      GetTransactionSetRequest request = this.builder.buildGetTransactionSetRequest(patient, transaction);
      GetTransactionSetResponse transactionSetResponse = this.intrahubTokenService.getTransactionSet(Session.getSAMLToken(), request, breakTheGlass);
      this.validator.validate(transactionSetResponse.getAcknowledge());
      return transactionSetResponse;
   }

   public GetTransactionSetResponse getTransactionSet(PatientIdType patient, TransactionBaseType transaction) throws TechnicalConnectorException, IntraHubBusinessConnectorException {
      return this.getTransactionSet(patient, transaction, (String)null);
   }

   public PutTransactionSetResponse putTransactionSet(Kmehrmessage kmehrHeader, String breakTheGlass) throws TechnicalConnectorException, IntraHubBusinessConnectorException {
      this.validateSession();
      PutTransactionSetRequest request = this.builder.buildPutTransactionSetRequest(kmehrHeader);
      PutTransactionSetResponse putTransactionSet = this.intrahubTokenService.putTransactionSet(Session.getSAMLToken(), request, breakTheGlass);
      this.validator.validate(putTransactionSet.getAcknowledge());
      return putTransactionSet;
   }

   public PutTransactionSetResponse putTransactionSet(Kmehrmessage kmehrHeader) throws TechnicalConnectorException, IntraHubBusinessConnectorException {
      return this.putTransactionSet(kmehrHeader, (String)null);
   }

   public GetLatestUpdateResponse getLatestUpdate(SelectGetLatestUpdateType latestUpdate, String breakTheGlass) throws TechnicalConnectorException, IntraHubBusinessConnectorException {
      this.validateSession();
      GetLatestUpdateRequest request = this.builder.buildGetLatestUpdateRequest(latestUpdate);
      GetLatestUpdateResponse latestUpdateResponse = this.intrahubTokenService.getLatestUpdate(Session.getSAMLToken(), request, breakTheGlass);
      this.validator.validate(latestUpdateResponse.getAcknowledge());
      return latestUpdateResponse;
   }

   public GetLatestUpdateResponse getLatestUpdate(SelectGetLatestUpdateType latestUpdate) throws TechnicalConnectorException, IntraHubBusinessConnectorException {
      return this.getLatestUpdate(latestUpdate, (String)null);
   }

   public GetPatientAuditTrailResponse getPatientAuditTrail(SelectGetPatientAuditTrailType patientAuditTrail, String breakTheGlass) throws TechnicalConnectorException, IntraHubBusinessConnectorException {
      this.validateSession();
      GetPatientAuditTrailRequest request = this.builder.buildGetPatientAudiTrail(patientAuditTrail);
      return this.intrahubTokenService.getPatientAuditTrail(Session.getSAMLToken(), request, breakTheGlass);
   }

   public GetPatientAuditTrailResponse getPatientAuditTrail(SelectGetPatientAuditTrailType patientAuditTrail) throws TechnicalConnectorException, IntraHubBusinessConnectorException {
      return this.getPatientAuditTrail(patientAuditTrail, (String)null);
   }

   public GetPatientAuditTrailResponse getPatientAuditTrail(SelectGetPatientAuditTrailType patientAuditTrail, Paginationrequestinfo paginationrequestinfo, String breakTheGlass) throws TechnicalConnectorException, IntraHubBusinessConnectorException {
      this.validateSession();
      GetPatientAuditTrailRequest request = this.builder.buildGetPatientAudiTrail(patientAuditTrail);
      return this.intrahubTokenService.getPatientAuditTrail(Session.getSAMLToken(), request, paginationrequestinfo, breakTheGlass);
   }

   public GetPatientAuditTrailResponse getPatientAuditTrail(SelectGetPatientAuditTrailType patientAuditTrail, Paginationrequestinfo paginationrequestinfo) throws TechnicalConnectorException, IntraHubBusinessConnectorException {
      return this.getPatientAuditTrail(patientAuditTrail, paginationrequestinfo, (String)null);
   }

   private void validateSession() throws SessionManagementException {
      this.sessionValidator.validateSession();
   }
}
