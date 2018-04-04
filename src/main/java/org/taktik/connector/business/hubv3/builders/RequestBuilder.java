package org.taktik.connector.business.hubv3.builders;

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

public interface RequestBuilder {
   GetTransactionListRequest buildGetTransactionListRequest(PatientIdType var1, LocalSearchType var2, TransactionWithPeriodType var3);

   DeclareTransactionRequest buildDeclareTransactionRequest(KmehrHeaderDeclareTransaction var1);

   PutTransactionRequest buildPutTransactionRequest(Kmehrmessage var1);

   PutTransactionSetRequest buildPutTransactionSetRequest(Kmehrmessage var1);

   RevokeTransactionRequest buildRevokeTransactionRequest(PatientIdType var1, TransactionBaseType var2);

   GetTransactionRequest buildGetTransactionRequest(PatientIdType var1, TransactionBaseType var2);

   GetTransactionSetRequest buildGetTransactionSetRequest(PatientIdType var1, TransactionBaseType var2);

   RequestPublicationRequest buildRequestPublicationRequest(PatientIdType var1, TransactionWithPeriodType var2, String var3);

   GetHCPartyRequest buildGetHcPartyRequest(HCPartyIdType var1);

   PutHCPartyRequest buildPutHcPartyRequest(HCPartyAdaptedType var1);

   PutHCPartyConsentRequest buildPutHcPartyConsentRequest(ConsentHCPartyType var1);

   GetHCPartyConsentRequest buildGetHcPartyConsent(HCPartyIdType var1);

   RevokeHCPartyConsentRequest buildRevokeHcPartyConsent(ConsentHCPartyType var1);

   PutPatientRequest buildPutPatientRequest(PersonType var1);

   GetPatientRequest buildGetPatientRequest(PatientIdType var1);

   PutPatientConsentRequest buildPutPatientConsentRequest(ConsentType var1);

   GetPatientConsentRequest buildGetPatientConsent(SelectGetPatientConsentType var1);

   RevokePatientConsentRequest buildRevokePatientConsentRequest(ConsentType var1);

   PutTherapeuticLinkRequest buildPutTherapeuticLinkRequest(TherapeuticLinkType var1);

   GetTherapeuticLinkRequest buildGetTherapeuticLinkRequest(SelectGetHCPartyPatientConsentType var1);

   RevokeTherapeuticLinkRequest buildRevokeTherapeuticLinkRequest(TherapeuticLinkType var1);

   PutAccessRightRequest buildPutAccessRightRequest(AccessRightType var1);

   GetAccessRightRequest buildGetAccessRight(TransactionIdType var1);

   RevokeAccessRightRequest buildRevokeAccessRight(SelectRevokeAccessRightType var1);

   GetPatientAuditTrailRequest buildGetPatientAudiTrail(SelectGetPatientAuditTrailType var1);

   GetLatestUpdateRequest buildGetLatestUpdateRequest(SelectGetLatestUpdateType var1);
}
