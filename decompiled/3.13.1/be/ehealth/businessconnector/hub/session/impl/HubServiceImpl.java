package be.ehealth.businessconnector.hub.session.impl;

import be.ehealth.businessconnector.hub.exception.IntraHubBusinessConnectorException;
import be.ehealth.businessconnector.hub.service.IntraHubAccessRightService;
import be.ehealth.businessconnector.hub.service.IntraHubHCPartyService;
import be.ehealth.businessconnector.hub.service.IntraHubPatientService;
import be.ehealth.businessconnector.hub.service.IntraHubService;
import be.ehealth.businessconnector.hub.service.IntraHubTherapeuticLinkService;
import be.ehealth.businessconnector.hub.service.IntraHubTransactionService;
import be.ehealth.businessconnector.hub.service.ServiceFactory;
import be.ehealth.businessconnector.hub.service.impl.IntraHubAccessRightServiceImpl;
import be.ehealth.businessconnector.hub.service.impl.IntraHubHCPartyServiceImpl;
import be.ehealth.businessconnector.hub.service.impl.IntraHubPatientServiceImpl;
import be.ehealth.businessconnector.hub.service.impl.IntraHubTherapeuticLinkServiceImpl;
import be.ehealth.businessconnector.hub.service.impl.IntraHubTransactionServiceImpl;
import be.ehealth.businessconnector.hub.session.HubService;
import be.ehealth.businessconnector.hub.validators.HubReplyValidator;
import be.ehealth.businessconnector.hub.validators.impl.HubReplyValidatorImpl;
import be.ehealth.technicalconnector.exception.ConnectorException;
import be.ehealth.technicalconnector.exception.SessionManagementException;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.validator.SessionValidator;
import be.fgov.ehealth.hubservices.core.v1.AccessRightListType;
import be.fgov.ehealth.hubservices.core.v1.AccessRightType;
import be.fgov.ehealth.hubservices.core.v1.ConsentHCPartyType;
import be.fgov.ehealth.hubservices.core.v1.ConsentType;
import be.fgov.ehealth.hubservices.core.v1.HCPartyAdaptedType;
import be.fgov.ehealth.hubservices.core.v1.HCPartyIdType;
import be.fgov.ehealth.hubservices.core.v1.KmehrHeaderDeclareTransaction;
import be.fgov.ehealth.hubservices.core.v1.KmehrHeaderGetTransactionList;
import be.fgov.ehealth.hubservices.core.v1.LocalSearchType;
import be.fgov.ehealth.hubservices.core.v1.PatientIdType;
import be.fgov.ehealth.hubservices.core.v1.SelectGetHCPartyPatientConsentType;
import be.fgov.ehealth.hubservices.core.v1.SelectGetPatientAuditTrailType;
import be.fgov.ehealth.hubservices.core.v1.SelectGetPatientConsentType;
import be.fgov.ehealth.hubservices.core.v1.SelectRevokeAccessRightType;
import be.fgov.ehealth.hubservices.core.v1.TherapeuticLinkType;
import be.fgov.ehealth.hubservices.core.v1.TransactionAccessListType;
import be.fgov.ehealth.hubservices.core.v1.TransactionBaseType;
import be.fgov.ehealth.hubservices.core.v1.TransactionIdType;
import be.fgov.ehealth.hubservices.core.v1.TransactionWithPeriodType;
import be.fgov.ehealth.standards.kmehr.id.v1.IDKMEHR;
import be.fgov.ehealth.standards.kmehr.schema.v1.Kmehrmessage;
import be.fgov.ehealth.standards.kmehr.schema.v1.PersonType;
import java.util.Collection;
import java.util.List;

public class HubServiceImpl implements HubService {
   private IntraHubTransactionService transactions;
   private IntraHubHCPartyService hcParties;
   private IntraHubPatientService patients;
   private IntraHubTherapeuticLinkService therapeuticLinks;
   private IntraHubAccessRightService accessRights;
   private SessionValidator sessionValidator;

   public HubServiceImpl(SessionValidator sessionValidator) throws TechnicalConnectorException, IntraHubBusinessConnectorException {
      IntraHubService hubService = ServiceFactory.getIntraHubService();
      HubReplyValidator validator = new HubReplyValidatorImpl();
      this.sessionValidator = sessionValidator;
      this.transactions = new IntraHubTransactionServiceImpl(hubService, validator);
      this.hcParties = new IntraHubHCPartyServiceImpl(hubService, validator);
      this.patients = new IntraHubPatientServiceImpl(hubService, validator);
      this.therapeuticLinks = new IntraHubTherapeuticLinkServiceImpl(hubService, validator);
      this.accessRights = new IntraHubAccessRightServiceImpl(hubService, validator);
   }

   public List<IDKMEHR> declareTransaction(KmehrHeaderDeclareTransaction kmehrHeader) throws TechnicalConnectorException, IntraHubBusinessConnectorException {
      this.sessionValidator.validateSession();
      return this.transactions.declareTransaction(kmehrHeader);
   }

   public void putTransaction(Kmehrmessage kmehrHeader) throws TechnicalConnectorException, IntraHubBusinessConnectorException {
      this.sessionValidator.validateSession();
      this.transactions.putTransaction(kmehrHeader);
   }

   public void revokeTransaction(PatientIdType patient, TransactionIdType transaction) throws TechnicalConnectorException, IntraHubBusinessConnectorException {
      this.transactions.revokeTransaction(patient, transaction);
   }

   public KmehrHeaderGetTransactionList getTransactionList(PatientIdType patient, LocalSearchType searchType, TransactionWithPeriodType transaction) throws TechnicalConnectorException, IntraHubBusinessConnectorException {
      this.sessionValidator.validateSession();
      return this.transactions.getTransactionList(patient, searchType, transaction);
   }

   public Kmehrmessage getTransaction(PatientIdType patient, TransactionBaseType transaction) throws TechnicalConnectorException, IntraHubBusinessConnectorException {
      this.sessionValidator.validateSession();
      return this.transactions.getTransaction(patient, transaction);
   }

   public void requestPublication(PatientIdType patient, TransactionWithPeriodType transaction, String comment) throws TechnicalConnectorException, IntraHubBusinessConnectorException {
      this.sessionValidator.validateSession();
      this.transactions.requestPublication(patient, transaction, comment);
   }

   public HCPartyAdaptedType getHCParty(HCPartyIdType hcParty) throws TechnicalConnectorException, IntraHubBusinessConnectorException {
      this.sessionValidator.validateSession();
      return this.hcParties.getHCParty(hcParty);
   }

   public HCPartyAdaptedType putHCParty(HCPartyAdaptedType hcParty) throws TechnicalConnectorException, IntraHubBusinessConnectorException {
      this.sessionValidator.validateSession();
      return this.hcParties.putHCParty(hcParty);
   }

   public void putHCPartyConsent(ConsentHCPartyType consent) throws TechnicalConnectorException, IntraHubBusinessConnectorException {
      this.sessionValidator.validateSession();
      this.hcParties.putHCPartyConsent(consent);
   }

   public ConsentHCPartyType getHCPartyConsent(HCPartyIdType hcPartyId) throws TechnicalConnectorException, IntraHubBusinessConnectorException {
      this.sessionValidator.validateSession();
      return this.hcParties.getHCPartyConsent(hcPartyId);
   }

   public void revokeHCPartyConsent(ConsentHCPartyType consent) throws TechnicalConnectorException, IntraHubBusinessConnectorException {
      this.sessionValidator.validateSession();
      this.hcParties.revokeHCPartyConsent(consent);
   }

   public PersonType putPatient(PersonType patient) throws TechnicalConnectorException, IntraHubBusinessConnectorException {
      this.sessionValidator.validateSession();
      return this.patients.putPatient(patient);
   }

   public PersonType getPatient(PatientIdType patientId) throws TechnicalConnectorException, IntraHubBusinessConnectorException {
      this.sessionValidator.validateSession();
      return this.patients.getPatient(patientId);
   }

   public void putPatientConsent(ConsentType patientConsent) throws TechnicalConnectorException, IntraHubBusinessConnectorException {
      this.sessionValidator.validateSession();
      this.patients.putPatientConsent(patientConsent);
   }

   public ConsentType getPatientConsent(SelectGetPatientConsentType patientConsent) throws TechnicalConnectorException, IntraHubBusinessConnectorException {
      this.sessionValidator.validateSession();
      return this.patients.getPatientConsent(patientConsent);
   }

   public void revokePatientConsent(ConsentType patientConsent) throws TechnicalConnectorException, IntraHubBusinessConnectorException {
      this.sessionValidator.validateSession();
      this.patients.revokePatientConsent(patientConsent);
   }

   public void putTherapeuticLink(TherapeuticLinkType therapeuticLink) throws ConnectorException, SessionManagementException {
      this.sessionValidator.validateSession();
      this.therapeuticLinks.putTherapeuticLink(therapeuticLink);
   }

   public Collection<TherapeuticLinkType> getTherapeuticLink(SelectGetHCPartyPatientConsentType patientConsent) throws ConnectorException {
      this.sessionValidator.validateSession();
      return this.therapeuticLinks.getTherapeuticLink(patientConsent);
   }

   public void revokeTherapeuticLink(TherapeuticLinkType therapeuticLink) throws TechnicalConnectorException, IntraHubBusinessConnectorException {
      this.sessionValidator.validateSession();
      this.therapeuticLinks.revokeTherapeuticLink(therapeuticLink);
   }

   public void putAccessRight(AccessRightType accessRight) throws TechnicalConnectorException, IntraHubBusinessConnectorException {
      this.sessionValidator.validateSession();
      this.accessRights.putAccessRight(accessRight);
   }

   public AccessRightListType getAccessRight(TransactionIdType transaction) throws TechnicalConnectorException, IntraHubBusinessConnectorException {
      this.sessionValidator.validateSession();
      return this.accessRights.getAccessRight(transaction);
   }

   public void revokeAccessRight(SelectRevokeAccessRightType accessRight) throws TechnicalConnectorException, IntraHubBusinessConnectorException {
      this.sessionValidator.validateSession();
      this.accessRights.revokeAccessRight(accessRight);
   }

   public TransactionAccessListType getPatientAuditTrail(SelectGetPatientAuditTrailType patientAuditTrail) throws TechnicalConnectorException, IntraHubBusinessConnectorException {
      this.sessionValidator.validateSession();
      return this.accessRights.getPatientAuditTrail(patientAuditTrail);
   }
}
