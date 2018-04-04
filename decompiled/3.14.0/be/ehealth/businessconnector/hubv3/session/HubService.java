package be.ehealth.businessconnector.hubv3.session;

import be.ehealth.business.intrahubcommons.exception.IntraHubBusinessConnectorException;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.fgov.ehealth.hubservices.core.v3.AccessRightType;
import be.fgov.ehealth.hubservices.core.v3.ConsentHCPartyType;
import be.fgov.ehealth.hubservices.core.v3.ConsentType;
import be.fgov.ehealth.hubservices.core.v3.DeclareTransactionResponse;
import be.fgov.ehealth.hubservices.core.v3.GetAccessRightResponse;
import be.fgov.ehealth.hubservices.core.v3.GetHCPartyConsentResponse;
import be.fgov.ehealth.hubservices.core.v3.GetHCPartyResponse;
import be.fgov.ehealth.hubservices.core.v3.GetLatestUpdateResponse;
import be.fgov.ehealth.hubservices.core.v3.GetPatientAuditTrailResponse;
import be.fgov.ehealth.hubservices.core.v3.GetPatientConsentResponse;
import be.fgov.ehealth.hubservices.core.v3.GetPatientResponse;
import be.fgov.ehealth.hubservices.core.v3.GetTherapeuticLinkResponse;
import be.fgov.ehealth.hubservices.core.v3.GetTransactionListResponse;
import be.fgov.ehealth.hubservices.core.v3.GetTransactionResponse;
import be.fgov.ehealth.hubservices.core.v3.GetTransactionSetResponse;
import be.fgov.ehealth.hubservices.core.v3.HCPartyAdaptedType;
import be.fgov.ehealth.hubservices.core.v3.HCPartyIdType;
import be.fgov.ehealth.hubservices.core.v3.KmehrHeaderDeclareTransaction;
import be.fgov.ehealth.hubservices.core.v3.LocalSearchType;
import be.fgov.ehealth.hubservices.core.v3.PatientIdType;
import be.fgov.ehealth.hubservices.core.v3.PutAccessRightResponse;
import be.fgov.ehealth.hubservices.core.v3.PutHCPartyConsentResponse;
import be.fgov.ehealth.hubservices.core.v3.PutHCPartyResponse;
import be.fgov.ehealth.hubservices.core.v3.PutPatientConsentResponse;
import be.fgov.ehealth.hubservices.core.v3.PutPatientResponse;
import be.fgov.ehealth.hubservices.core.v3.PutTherapeuticLinkResponse;
import be.fgov.ehealth.hubservices.core.v3.PutTransactionResponse;
import be.fgov.ehealth.hubservices.core.v3.PutTransactionSetResponse;
import be.fgov.ehealth.hubservices.core.v3.RequestPublicationResponse;
import be.fgov.ehealth.hubservices.core.v3.RevokeAccessRightResponse;
import be.fgov.ehealth.hubservices.core.v3.RevokeHCPartyConsentResponse;
import be.fgov.ehealth.hubservices.core.v3.RevokePatientConsentResponse;
import be.fgov.ehealth.hubservices.core.v3.RevokeTherapeuticLinkResponse;
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

public interface HubService {
   DeclareTransactionResponse declareTransaction(KmehrHeaderDeclareTransaction var1) throws TechnicalConnectorException, IntraHubBusinessConnectorException;

   PutTransactionResponse putTransaction(Kmehrmessage var1) throws TechnicalConnectorException, IntraHubBusinessConnectorException;

   RevokeTransactionResponse revokeTransaction(PatientIdType var1, TransactionBaseType var2) throws TechnicalConnectorException, IntraHubBusinessConnectorException;

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

   PutTherapeuticLinkResponse putTherapeuticLink(TherapeuticLinkType var1) throws TechnicalConnectorException, IntraHubBusinessConnectorException;

   GetTherapeuticLinkResponse getTherapeuticLink(SelectGetHCPartyPatientConsentType var1) throws TechnicalConnectorException, IntraHubBusinessConnectorException;

   RevokeTherapeuticLinkResponse revokeTherapeuticLink(TherapeuticLinkType var1) throws TechnicalConnectorException, IntraHubBusinessConnectorException;

   PutAccessRightResponse putAccessRight(AccessRightType var1) throws TechnicalConnectorException, IntraHubBusinessConnectorException;

   GetAccessRightResponse getAccessRight(TransactionIdType var1) throws TechnicalConnectorException, IntraHubBusinessConnectorException;

   RevokeAccessRightResponse revokeAccessRight(SelectRevokeAccessRightType var1) throws TechnicalConnectorException, IntraHubBusinessConnectorException;

   GetPatientAuditTrailResponse getPatientAuditTrail(SelectGetPatientAuditTrailType var1) throws TechnicalConnectorException, IntraHubBusinessConnectorException;

   GetTransactionSetResponse getTransactionSet(PatientIdType var1, TransactionBaseType var2) throws TechnicalConnectorException, IntraHubBusinessConnectorException;

   PutTransactionSetResponse putTransactionSet(Kmehrmessage var1) throws TechnicalConnectorException, IntraHubBusinessConnectorException;

   GetLatestUpdateResponse getLatestUpdate(SelectGetLatestUpdateType var1) throws TechnicalConnectorException, IntraHubBusinessConnectorException;
}
