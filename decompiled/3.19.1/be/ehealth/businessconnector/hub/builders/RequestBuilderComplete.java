package be.ehealth.businessconnector.hub.builders;

import be.fgov.ehealth.hubservices.core.v1.AccessRightType;
import be.fgov.ehealth.hubservices.core.v1.ConsentHCPartyType;
import be.fgov.ehealth.hubservices.core.v1.ConsentType;
import be.fgov.ehealth.hubservices.core.v1.DeclareTransactionRequest;
import be.fgov.ehealth.hubservices.core.v1.GetAccessRightRequest;
import be.fgov.ehealth.hubservices.core.v1.GetHCPartyConsentRequest;
import be.fgov.ehealth.hubservices.core.v1.GetHCPartyRequest;
import be.fgov.ehealth.hubservices.core.v1.GetPatientAuditTrailRequest;
import be.fgov.ehealth.hubservices.core.v1.GetPatientConsentRequest;
import be.fgov.ehealth.hubservices.core.v1.GetPatientRequest;
import be.fgov.ehealth.hubservices.core.v1.GetTherapeuticLinkRequest;
import be.fgov.ehealth.hubservices.core.v1.GetTransactionListRequest;
import be.fgov.ehealth.hubservices.core.v1.GetTransactionRequest;
import be.fgov.ehealth.hubservices.core.v1.HCPartyAdaptedType;
import be.fgov.ehealth.hubservices.core.v1.HCPartyIdType;
import be.fgov.ehealth.hubservices.core.v1.KmehrHeaderDeclareTransaction;
import be.fgov.ehealth.hubservices.core.v1.LocalSearchType;
import be.fgov.ehealth.hubservices.core.v1.PatientIdType;
import be.fgov.ehealth.hubservices.core.v1.PutAccessRightRequest;
import be.fgov.ehealth.hubservices.core.v1.PutHCPartyConsentRequest;
import be.fgov.ehealth.hubservices.core.v1.PutHCPartyRequest;
import be.fgov.ehealth.hubservices.core.v1.PutPatientConsentRequest;
import be.fgov.ehealth.hubservices.core.v1.PutPatientRequest;
import be.fgov.ehealth.hubservices.core.v1.PutTherapeuticLinkRequest;
import be.fgov.ehealth.hubservices.core.v1.PutTransactionRequest;
import be.fgov.ehealth.hubservices.core.v1.RequestPublicationRequest;
import be.fgov.ehealth.hubservices.core.v1.RevokeAccessRightRequest;
import be.fgov.ehealth.hubservices.core.v1.RevokeHCPartyConsentRequest;
import be.fgov.ehealth.hubservices.core.v1.RevokePatientConsentRequest;
import be.fgov.ehealth.hubservices.core.v1.RevokeTherapeuticLinkRequest;
import be.fgov.ehealth.hubservices.core.v1.RevokeTransactionRequest;
import be.fgov.ehealth.hubservices.core.v1.SelectGetAccessRightType;
import be.fgov.ehealth.hubservices.core.v1.SelectGetHCPartyConsentType;
import be.fgov.ehealth.hubservices.core.v1.SelectGetHCPartyPatientConsentType;
import be.fgov.ehealth.hubservices.core.v1.SelectGetHCPartyType;
import be.fgov.ehealth.hubservices.core.v1.SelectGetPatientAuditTrailType;
import be.fgov.ehealth.hubservices.core.v1.SelectGetPatientConsentType;
import be.fgov.ehealth.hubservices.core.v1.SelectGetPatientType;
import be.fgov.ehealth.hubservices.core.v1.SelectGetTransactionListType;
import be.fgov.ehealth.hubservices.core.v1.SelectGetTransactionType;
import be.fgov.ehealth.hubservices.core.v1.SelectRequestPublicationType;
import be.fgov.ehealth.hubservices.core.v1.SelectRevokeAccessRightType;
import be.fgov.ehealth.hubservices.core.v1.SelectRevokeTransactionType;
import be.fgov.ehealth.hubservices.core.v1.TherapeuticLinkType;
import be.fgov.ehealth.hubservices.core.v1.TransactionBaseType;
import be.fgov.ehealth.hubservices.core.v1.TransactionIdType;
import be.fgov.ehealth.hubservices.core.v1.TransactionWithPeriodType;
import be.fgov.ehealth.standards.kmehr.schema.v1.Kmehrmessage;
import be.fgov.ehealth.standards.kmehr.schema.v1.PersonType;

public class RequestBuilderComplete {
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

   public RevokeTransactionRequest buildRevokeTransactionRequest(PatientIdType patient, TransactionIdType transaction) {
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
}
