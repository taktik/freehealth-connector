package be.ehealth.businessconnector.hub.session;

import be.ehealth.business.intrahubcommons.exception.IntraHubBusinessConnectorException;
import be.ehealth.technicalconnector.exception.ConnectorException;
import be.ehealth.technicalconnector.exception.SessionManagementException;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
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

public interface HubService {
   List<IDKMEHR> declareTransaction(KmehrHeaderDeclareTransaction var1) throws TechnicalConnectorException, SessionManagementException, IntraHubBusinessConnectorException;

   void putTransaction(Kmehrmessage var1) throws TechnicalConnectorException, IntraHubBusinessConnectorException;

   void revokeTransaction(PatientIdType var1, TransactionIdType var2) throws TechnicalConnectorException, IntraHubBusinessConnectorException;

   KmehrHeaderGetTransactionList getTransactionList(PatientIdType var1, LocalSearchType var2, TransactionWithPeriodType var3) throws TechnicalConnectorException, IntraHubBusinessConnectorException;

   Kmehrmessage getTransaction(PatientIdType var1, TransactionBaseType var2) throws TechnicalConnectorException, IntraHubBusinessConnectorException;

   void requestPublication(PatientIdType var1, TransactionWithPeriodType var2, String var3) throws TechnicalConnectorException, IntraHubBusinessConnectorException;

   HCPartyAdaptedType putHCParty(HCPartyAdaptedType var1) throws TechnicalConnectorException, IntraHubBusinessConnectorException;

   HCPartyAdaptedType getHCParty(HCPartyIdType var1) throws TechnicalConnectorException, IntraHubBusinessConnectorException;

   PersonType putPatient(PersonType var1) throws TechnicalConnectorException, IntraHubBusinessConnectorException;

   PersonType getPatient(PatientIdType var1) throws TechnicalConnectorException, IntraHubBusinessConnectorException;

   void putHCPartyConsent(ConsentHCPartyType var1) throws TechnicalConnectorException, IntraHubBusinessConnectorException;

   ConsentHCPartyType getHCPartyConsent(HCPartyIdType var1) throws TechnicalConnectorException, IntraHubBusinessConnectorException;

   void revokeHCPartyConsent(ConsentHCPartyType var1) throws TechnicalConnectorException, IntraHubBusinessConnectorException;

   void putPatientConsent(ConsentType var1) throws TechnicalConnectorException, IntraHubBusinessConnectorException;

   ConsentType getPatientConsent(SelectGetPatientConsentType var1) throws TechnicalConnectorException, IntraHubBusinessConnectorException;

   void revokePatientConsent(ConsentType var1) throws TechnicalConnectorException, IntraHubBusinessConnectorException;

   void putTherapeuticLink(TherapeuticLinkType var1) throws ConnectorException, SessionManagementException;

   Collection<TherapeuticLinkType> getTherapeuticLink(SelectGetHCPartyPatientConsentType var1) throws SessionManagementException, ConnectorException;

   void revokeTherapeuticLink(TherapeuticLinkType var1) throws TechnicalConnectorException, IntraHubBusinessConnectorException;

   void putAccessRight(AccessRightType var1) throws TechnicalConnectorException, IntraHubBusinessConnectorException;

   AccessRightListType getAccessRight(TransactionIdType var1) throws TechnicalConnectorException, IntraHubBusinessConnectorException;

   void revokeAccessRight(SelectRevokeAccessRightType var1) throws TechnicalConnectorException, IntraHubBusinessConnectorException;

   TransactionAccessListType getPatientAuditTrail(SelectGetPatientAuditTrailType var1) throws TechnicalConnectorException, IntraHubBusinessConnectorException;
}
