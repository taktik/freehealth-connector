package be.ehealth.businessconnector.hub.session;

import be.ehealth.businessconnector.hub.exception.IntraHubBusinessConnectorException;
import be.ehealth.technicalconnector.exception.ConnectorException;
import be.ehealth.technicalconnector.exception.SessionManagementException;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.fgov.ehealth.hubservices.core.v1.AccessRightType;
import be.fgov.ehealth.hubservices.core.v1.ConsentHCPartyType;
import be.fgov.ehealth.hubservices.core.v1.ConsentType;
import be.fgov.ehealth.hubservices.core.v1.DeclareTransactionResponse;
import be.fgov.ehealth.hubservices.core.v1.GetAccessRightResponse;
import be.fgov.ehealth.hubservices.core.v1.GetHCPartyConsentResponse;
import be.fgov.ehealth.hubservices.core.v1.GetHCPartyResponse;
import be.fgov.ehealth.hubservices.core.v1.GetPatientAuditTrailResponse;
import be.fgov.ehealth.hubservices.core.v1.GetPatientConsentResponse;
import be.fgov.ehealth.hubservices.core.v1.GetPatientResponse;
import be.fgov.ehealth.hubservices.core.v1.GetTherapeuticLinkResponse;
import be.fgov.ehealth.hubservices.core.v1.GetTransactionListResponse;
import be.fgov.ehealth.hubservices.core.v1.GetTransactionResponse;
import be.fgov.ehealth.hubservices.core.v1.HCPartyAdaptedType;
import be.fgov.ehealth.hubservices.core.v1.HCPartyIdType;
import be.fgov.ehealth.hubservices.core.v1.KmehrHeaderDeclareTransaction;
import be.fgov.ehealth.hubservices.core.v1.LocalSearchType;
import be.fgov.ehealth.hubservices.core.v1.PatientIdType;
import be.fgov.ehealth.hubservices.core.v1.PutAccessRightResponse;
import be.fgov.ehealth.hubservices.core.v1.PutHCPartyConsentResponse;
import be.fgov.ehealth.hubservices.core.v1.PutHCPartyResponse;
import be.fgov.ehealth.hubservices.core.v1.PutPatientConsentResponse;
import be.fgov.ehealth.hubservices.core.v1.PutPatientResponse;
import be.fgov.ehealth.hubservices.core.v1.PutTherapeuticLinkResponse;
import be.fgov.ehealth.hubservices.core.v1.PutTransactionResponse;
import be.fgov.ehealth.hubservices.core.v1.RequestPublicationResponse;
import be.fgov.ehealth.hubservices.core.v1.RevokeAccessRightResponse;
import be.fgov.ehealth.hubservices.core.v1.RevokeHCPartyConsentResponse;
import be.fgov.ehealth.hubservices.core.v1.RevokePatientConsentResponse;
import be.fgov.ehealth.hubservices.core.v1.RevokeTherapeuticLinkResponse;
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

public interface HubServiceComplete {
   DeclareTransactionResponse declareTransaction(KmehrHeaderDeclareTransaction var1) throws TechnicalConnectorException, SessionManagementException, IntraHubBusinessConnectorException;

   PutTransactionResponse putTransaction(Kmehrmessage var1) throws TechnicalConnectorException, IntraHubBusinessConnectorException;

   RevokeTransactionResponse revokeTransaction(PatientIdType var1, TransactionIdType var2) throws TechnicalConnectorException, IntraHubBusinessConnectorException;

   GetTransactionListResponse getTransactionList(PatientIdType var1, LocalSearchType var2, TransactionWithPeriodType var3) throws TechnicalConnectorException, IntraHubBusinessConnectorException;

   GetTransactionResponse getTransaction(PatientIdType var1, TransactionBaseType var2) throws TechnicalConnectorException, IntraHubBusinessConnectorException;

   RequestPublicationResponse requestPublication(PatientIdType var1, TransactionWithPeriodType var2, String var3) throws TechnicalConnectorException, IntraHubBusinessConnectorException;

   PutHCPartyResponse putHCParty(HCPartyAdaptedType var1) throws TechnicalConnectorException, IntraHubBusinessConnectorException;

   GetHCPartyResponse getHCParty(HCPartyIdType var1) throws TechnicalConnectorException, IntraHubBusinessConnectorException;

   PutPatientResponse putPatient(PersonType var1) throws TechnicalConnectorException, IntraHubBusinessConnectorException;

   GetPatientResponse getPatient(PatientIdType var1) throws TechnicalConnectorException, IntraHubBusinessConnectorException;

   PutHCPartyConsentResponse putHCPartyConsent(ConsentHCPartyType var1) throws TechnicalConnectorException, IntraHubBusinessConnectorException;

   GetHCPartyConsentResponse getHCPartyConsent(HCPartyIdType var1) throws TechnicalConnectorException, IntraHubBusinessConnectorException;

   RevokeHCPartyConsentResponse revokeHCPartyConsent(ConsentHCPartyType var1) throws TechnicalConnectorException, IntraHubBusinessConnectorException;

   PutPatientConsentResponse putPatientConsent(ConsentType var1) throws TechnicalConnectorException, IntraHubBusinessConnectorException;

   GetPatientConsentResponse getPatientConsent(SelectGetPatientConsentType var1) throws TechnicalConnectorException, IntraHubBusinessConnectorException;

   RevokePatientConsentResponse revokePatientConsent(ConsentType var1) throws TechnicalConnectorException, IntraHubBusinessConnectorException;

   PutTherapeuticLinkResponse putTherapeuticLink(TherapeuticLinkType var1) throws ConnectorException, SessionManagementException;

   GetTherapeuticLinkResponse getTherapeuticLink(SelectGetHCPartyPatientConsentType var1) throws SessionManagementException, ConnectorException;

   RevokeTherapeuticLinkResponse revokeTherapeuticLink(TherapeuticLinkType var1) throws TechnicalConnectorException, IntraHubBusinessConnectorException;

   PutAccessRightResponse putAccessRight(AccessRightType var1) throws TechnicalConnectorException, IntraHubBusinessConnectorException;

   GetAccessRightResponse getAccessRight(TransactionIdType var1) throws TechnicalConnectorException, IntraHubBusinessConnectorException;

   RevokeAccessRightResponse revokeAccessRight(SelectRevokeAccessRightType var1) throws TechnicalConnectorException, IntraHubBusinessConnectorException;

   GetPatientAuditTrailResponse getPatientAuditTrail(SelectGetPatientAuditTrailType var1) throws TechnicalConnectorException, IntraHubBusinessConnectorException;
}
