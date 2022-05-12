package be.ehealth.businessconnector.hubv3.builders.impl;

import be.ehealth.businessconnector.hubv3.builders.RequestBuilder;
import be.ehealth.technicalconnector.config.impl.ConfigurationModuleBootstrap;
import be.ehealth.technicalconnector.utils.impl.JaxbContextFactory;
import be.fgov.ehealth.hubservices.core.v3.AccessRightType;
import be.fgov.ehealth.hubservices.core.v3.ConsentHCPartyType;
import be.fgov.ehealth.hubservices.core.v3.ConsentType;
import be.fgov.ehealth.hubservices.core.v3.DeclareTransactionRequest;
import be.fgov.ehealth.hubservices.core.v3.GetAccessRightRequest;
import be.fgov.ehealth.hubservices.core.v3.GetHCPartyConsentRequest;
import be.fgov.ehealth.hubservices.core.v3.GetHCPartyRequest;
import be.fgov.ehealth.hubservices.core.v3.GetLatestUpdateRequest;
import be.fgov.ehealth.hubservices.core.v3.GetPatientAuditTrailRequest;
import be.fgov.ehealth.hubservices.core.v3.GetPatientConsentRequest;
import be.fgov.ehealth.hubservices.core.v3.GetPatientRequest;
import be.fgov.ehealth.hubservices.core.v3.GetTherapeuticLinkRequest;
import be.fgov.ehealth.hubservices.core.v3.GetTransactionListRequest;
import be.fgov.ehealth.hubservices.core.v3.GetTransactionRequest;
import be.fgov.ehealth.hubservices.core.v3.GetTransactionSetRequest;
import be.fgov.ehealth.hubservices.core.v3.HCPartyAdaptedType;
import be.fgov.ehealth.hubservices.core.v3.HCPartyIdType;
import be.fgov.ehealth.hubservices.core.v3.KmehrHeaderDeclareTransaction;
import be.fgov.ehealth.hubservices.core.v3.LocalSearchType;
import be.fgov.ehealth.hubservices.core.v3.PatientIdType;
import be.fgov.ehealth.hubservices.core.v3.PutAccessRightRequest;
import be.fgov.ehealth.hubservices.core.v3.PutHCPartyConsentRequest;
import be.fgov.ehealth.hubservices.core.v3.PutHCPartyRequest;
import be.fgov.ehealth.hubservices.core.v3.PutPatientConsentRequest;
import be.fgov.ehealth.hubservices.core.v3.PutPatientRequest;
import be.fgov.ehealth.hubservices.core.v3.PutTherapeuticLinkRequest;
import be.fgov.ehealth.hubservices.core.v3.PutTransactionRequest;
import be.fgov.ehealth.hubservices.core.v3.PutTransactionSetRequest;
import be.fgov.ehealth.hubservices.core.v3.RequestPublicationRequest;
import be.fgov.ehealth.hubservices.core.v3.RevokeAccessRightRequest;
import be.fgov.ehealth.hubservices.core.v3.RevokeHCPartyConsentRequest;
import be.fgov.ehealth.hubservices.core.v3.RevokePatientConsentRequest;
import be.fgov.ehealth.hubservices.core.v3.RevokeTherapeuticLinkRequest;
import be.fgov.ehealth.hubservices.core.v3.RevokeTransactionRequest;
import be.fgov.ehealth.hubservices.core.v3.SelectGetAccessRightType;
import be.fgov.ehealth.hubservices.core.v3.SelectGetHCPartyConsentType;
import be.fgov.ehealth.hubservices.core.v3.SelectGetHCPartyPatientConsentType;
import be.fgov.ehealth.hubservices.core.v3.SelectGetHCPartyType;
import be.fgov.ehealth.hubservices.core.v3.SelectGetLatestUpdateType;
import be.fgov.ehealth.hubservices.core.v3.SelectGetPatientAuditTrailType;
import be.fgov.ehealth.hubservices.core.v3.SelectGetPatientConsentType;
import be.fgov.ehealth.hubservices.core.v3.SelectGetPatientType;
import be.fgov.ehealth.hubservices.core.v3.SelectGetTransactionListType;
import be.fgov.ehealth.hubservices.core.v3.SelectGetTransactionType;
import be.fgov.ehealth.hubservices.core.v3.SelectRequestPublicationType;
import be.fgov.ehealth.hubservices.core.v3.SelectRevokeAccessRightType;
import be.fgov.ehealth.hubservices.core.v3.SelectRevokeTransactionType;
import be.fgov.ehealth.hubservices.core.v3.TherapeuticLinkType;
import be.fgov.ehealth.hubservices.core.v3.TransactionBaseType;
import be.fgov.ehealth.hubservices.core.v3.TransactionIdType;
import be.fgov.ehealth.hubservices.core.v3.TransactionWithPeriodType;
import be.fgov.ehealth.standards.kmehr.schema.v1.Kmehrmessage;
import be.fgov.ehealth.standards.kmehr.schema.v1.PersonType;

public class RequestBuilderImpl implements RequestBuilder, ConfigurationModuleBootstrap.ModuleBootstrapHook {
   public RequestBuilderImpl() {
   }

   public GetTransactionListRequest buildGetTransactionListRequest(PatientIdType patient, LocalSearchType searchType, TransactionWithPeriodType transaction) {
      GetTransactionListRequest request = new GetTransactionListRequest();
      SelectGetTransactionListType select = new SelectGetTransactionListType();
      select.setPatient(patient);
      select.setSearchtype(searchType);
      select.setTransaction(transaction);
      request.setSelect(select);
      return request;
   }

   public DeclareTransactionRequest buildDeclareTransactionRequest(KmehrHeaderDeclareTransaction kmehrHeader) {
      DeclareTransactionRequest request = new DeclareTransactionRequest();
      request.setKmehrheader(kmehrHeader);
      return request;
   }

   public PutTransactionRequest buildPutTransactionRequest(Kmehrmessage kmehrHeader) {
      PutTransactionRequest request = new PutTransactionRequest();
      request.setKmehrmessage(kmehrHeader);
      return request;
   }

   public PutTransactionSetRequest buildPutTransactionSetRequest(Kmehrmessage kmehrHeader) {
      PutTransactionSetRequest request = new PutTransactionSetRequest();
      request.setKmehrmessage(kmehrHeader);
      return request;
   }

   public RevokeTransactionRequest buildRevokeTransactionRequest(PatientIdType patient, TransactionBaseType transaction) {
      RevokeTransactionRequest request = new RevokeTransactionRequest();
      SelectRevokeTransactionType select = new SelectRevokeTransactionType();
      select.setPatient(patient);
      select.setTransaction(transaction);
      request.setSelect(select);
      return request;
   }

   public GetTransactionRequest buildGetTransactionRequest(PatientIdType patient, TransactionBaseType transaction) {
      GetTransactionRequest request = new GetTransactionRequest();
      SelectGetTransactionType selectTransaction = new SelectGetTransactionType();
      selectTransaction.setPatient(patient);
      selectTransaction.setTransaction(transaction);
      request.setSelect(selectTransaction);
      return request;
   }

   public GetTransactionSetRequest buildGetTransactionSetRequest(PatientIdType patient, TransactionBaseType transaction) {
      GetTransactionSetRequest request = new GetTransactionSetRequest();
      SelectGetTransactionType selectTransaction = new SelectGetTransactionType();
      selectTransaction.setPatient(patient);
      selectTransaction.setTransaction(transaction);
      request.setSelect(selectTransaction);
      return request;
   }

   public RequestPublicationRequest buildRequestPublicationRequest(PatientIdType patient, TransactionWithPeriodType transaction, String comment) {
      RequestPublicationRequest request = new RequestPublicationRequest();
      SelectRequestPublicationType select = new SelectRequestPublicationType();
      select.setPatient(patient);
      select.setTransaction(transaction);
      select.setComment(comment);
      request.setSelect(select);
      return request;
   }

   public GetHCPartyRequest buildGetHcPartyRequest(HCPartyIdType hcParty) {
      GetHCPartyRequest request = new GetHCPartyRequest();
      SelectGetHCPartyType hcPartyType = new SelectGetHCPartyType();
      hcPartyType.setHcparty(hcParty);
      request.setSelect(hcPartyType);
      return request;
   }

   public PutHCPartyRequest buildPutHcPartyRequest(HCPartyAdaptedType hcParty) {
      PutHCPartyRequest request = new PutHCPartyRequest();
      request.setHcparty(hcParty);
      return request;
   }

   public PutHCPartyConsentRequest buildPutHcPartyConsentRequest(ConsentHCPartyType consent) {
      PutHCPartyConsentRequest request = new PutHCPartyConsentRequest();
      request.setConsent(consent);
      return request;
   }

   public GetHCPartyConsentRequest buildGetHcPartyConsent(HCPartyIdType hcPartyId) {
      GetHCPartyConsentRequest request = new GetHCPartyConsentRequest();
      SelectGetHCPartyConsentType hcPartyConsent = new SelectGetHCPartyConsentType();
      hcPartyConsent.setHcparty(hcPartyId);
      request.setSelect(hcPartyConsent);
      return request;
   }

   public RevokeHCPartyConsentRequest buildRevokeHcPartyConsent(ConsentHCPartyType consent) {
      RevokeHCPartyConsentRequest request = new RevokeHCPartyConsentRequest();
      request.setConsent(consent);
      return request;
   }

   public PutPatientRequest buildPutPatientRequest(PersonType patient) {
      PutPatientRequest request = new PutPatientRequest();
      request.setPatient(patient);
      return request;
   }

   public GetPatientRequest buildGetPatientRequest(PatientIdType patientId) {
      GetPatientRequest request = new GetPatientRequest();
      SelectGetPatientType selectGetPatient = new SelectGetPatientType();
      selectGetPatient.setPatient(patientId);
      request.setSelect(selectGetPatient);
      return request;
   }

   public PutPatientConsentRequest buildPutPatientConsentRequest(ConsentType patientConsent) {
      PutPatientConsentRequest request = new PutPatientConsentRequest();
      request.setConsent(patientConsent);
      return request;
   }

   public GetPatientConsentRequest buildGetPatientConsent(SelectGetPatientConsentType patientConsent) {
      GetPatientConsentRequest request = new GetPatientConsentRequest();
      request.setSelect(patientConsent);
      return request;
   }

   public RevokePatientConsentRequest buildRevokePatientConsentRequest(ConsentType patientConsent) {
      RevokePatientConsentRequest request = new RevokePatientConsentRequest();
      request.setConsent(patientConsent);
      return request;
   }

   public PutTherapeuticLinkRequest buildPutTherapeuticLinkRequest(TherapeuticLinkType therapeuticLink) {
      PutTherapeuticLinkRequest request = new PutTherapeuticLinkRequest();
      request.setTherapeuticlink(therapeuticLink);
      return request;
   }

   public GetTherapeuticLinkRequest buildGetTherapeuticLinkRequest(SelectGetHCPartyPatientConsentType patientConsent) {
      GetTherapeuticLinkRequest request = new GetTherapeuticLinkRequest();
      request.setSelect(patientConsent);
      return request;
   }

   public RevokeTherapeuticLinkRequest buildRevokeTherapeuticLinkRequest(TherapeuticLinkType therapeuticLink) {
      RevokeTherapeuticLinkRequest request = new RevokeTherapeuticLinkRequest();
      request.setTherapeuticlink(therapeuticLink);
      return request;
   }

   public PutAccessRightRequest buildPutAccessRightRequest(AccessRightType accessRight) {
      PutAccessRightRequest request = new PutAccessRightRequest();
      request.setAccessright(accessRight);
      return request;
   }

   public GetAccessRightRequest buildGetAccessRight(TransactionIdType transaction) {
      GetAccessRightRequest request = new GetAccessRightRequest();
      SelectGetAccessRightType accessRight = new SelectGetAccessRightType();
      accessRight.setTransaction(transaction);
      request.setSelect(accessRight);
      return request;
   }

   public RevokeAccessRightRequest buildRevokeAccessRight(SelectRevokeAccessRightType accessRight) {
      RevokeAccessRightRequest request = new RevokeAccessRightRequest();
      request.setAccessright(accessRight);
      return request;
   }

   public GetPatientAuditTrailRequest buildGetPatientAudiTrail(SelectGetPatientAuditTrailType patientAuditTrail) {
      GetPatientAuditTrailRequest request = new GetPatientAuditTrailRequest();
      request.setSelect(patientAuditTrail);
      return request;
   }

   public GetLatestUpdateRequest buildGetLatestUpdateRequest(SelectGetLatestUpdateType latestUpdate) {
      GetLatestUpdateRequest request = new GetLatestUpdateRequest();
      request.setSelect(latestUpdate);
      return request;
   }

   public void bootstrap() {
      JaxbContextFactory.initJaxbContext(DeclareTransactionRequest.class);
      JaxbContextFactory.initJaxbContext(GetAccessRightRequest.class);
      JaxbContextFactory.initJaxbContext(GetHCPartyConsentRequest.class);
      JaxbContextFactory.initJaxbContext(GetHCPartyRequest.class);
      JaxbContextFactory.initJaxbContext(GetPatientAuditTrailRequest.class);
      JaxbContextFactory.initJaxbContext(GetPatientConsentRequest.class);
      JaxbContextFactory.initJaxbContext(GetPatientRequest.class);
      JaxbContextFactory.initJaxbContext(GetTherapeuticLinkRequest.class);
      JaxbContextFactory.initJaxbContext(GetTransactionListRequest.class);
      JaxbContextFactory.initJaxbContext(GetTransactionRequest.class);
      JaxbContextFactory.initJaxbContext(PutAccessRightRequest.class);
      JaxbContextFactory.initJaxbContext(PutHCPartyConsentRequest.class);
      JaxbContextFactory.initJaxbContext(PutHCPartyRequest.class);
      JaxbContextFactory.initJaxbContext(PutPatientConsentRequest.class);
      JaxbContextFactory.initJaxbContext(PutPatientRequest.class);
      JaxbContextFactory.initJaxbContext(PutTherapeuticLinkRequest.class);
      JaxbContextFactory.initJaxbContext(PutTransactionRequest.class);
      JaxbContextFactory.initJaxbContext(RequestPublicationRequest.class);
      JaxbContextFactory.initJaxbContext(RevokeAccessRightRequest.class);
      JaxbContextFactory.initJaxbContext(RevokeHCPartyConsentRequest.class);
      JaxbContextFactory.initJaxbContext(RevokePatientConsentRequest.class);
      JaxbContextFactory.initJaxbContext(RevokeTherapeuticLinkRequest.class);
      JaxbContextFactory.initJaxbContext(RevokeTransactionRequest.class);
      JaxbContextFactory.initJaxbContext(GetLatestUpdateRequest.class);
   }
}
