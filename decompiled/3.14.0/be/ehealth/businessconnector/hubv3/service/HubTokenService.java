package be.ehealth.businessconnector.hubv3.service;

import be.ehealth.business.intrahubcommons.exception.IntraHubBusinessConnectorException;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.service.sts.security.SAMLToken;
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

public interface HubTokenService {
   DeclareTransactionResponse declareTransaction(SAMLToken var1, DeclareTransactionRequest var2) throws TechnicalConnectorException;

   PutTransactionResponse putTransaction(SAMLToken var1, PutTransactionRequest var2) throws IntraHubBusinessConnectorException, TechnicalConnectorException;

   RevokeTransactionResponse revokeTransaction(SAMLToken var1, RevokeTransactionRequest var2) throws TechnicalConnectorException;

   GetTransactionListResponse getTransactionList(SAMLToken var1, GetTransactionListRequest var2) throws TechnicalConnectorException;

   GetTransactionResponse getTransaction(SAMLToken var1, GetTransactionRequest var2) throws TechnicalConnectorException, IntraHubBusinessConnectorException;

   RequestPublicationResponse requestPublication(SAMLToken var1, RequestPublicationRequest var2) throws TechnicalConnectorException;

   PutHCPartyResponse putHCParty(SAMLToken var1, PutHCPartyRequest var2) throws TechnicalConnectorException;

   GetHCPartyResponse getHCParty(SAMLToken var1, GetHCPartyRequest var2) throws TechnicalConnectorException;

   PutPatientResponse putPatient(SAMLToken var1, PutPatientRequest var2) throws TechnicalConnectorException;

   GetPatientResponse getPatient(SAMLToken var1, GetPatientRequest var2) throws TechnicalConnectorException;

   PutHCPartyConsentResponse putHCPartyConsent(SAMLToken var1, PutHCPartyConsentRequest var2) throws TechnicalConnectorException;

   GetHCPartyConsentResponse getHCPartyConsent(SAMLToken var1, GetHCPartyConsentRequest var2) throws TechnicalConnectorException;

   RevokeHCPartyConsentResponse revokeHCPartyConsent(SAMLToken var1, RevokeHCPartyConsentRequest var2) throws TechnicalConnectorException;

   PutPatientConsentResponse putPatientConsent(SAMLToken var1, PutPatientConsentRequest var2) throws TechnicalConnectorException;

   GetPatientConsentResponse getPatientConsent(SAMLToken var1, GetPatientConsentRequest var2) throws TechnicalConnectorException;

   RevokePatientConsentResponse revokePatientConsent(SAMLToken var1, RevokePatientConsentRequest var2) throws TechnicalConnectorException;

   PutTherapeuticLinkResponse putTherapeuticLink(SAMLToken var1, PutTherapeuticLinkRequest var2) throws TechnicalConnectorException;

   GetTherapeuticLinkResponse getTherapeuticLink(SAMLToken var1, GetTherapeuticLinkRequest var2) throws TechnicalConnectorException;

   RevokeTherapeuticLinkResponse revokeTherapeuticLink(SAMLToken var1, RevokeTherapeuticLinkRequest var2) throws TechnicalConnectorException;

   PutAccessRightResponse putAccessRight(SAMLToken var1, PutAccessRightRequest var2) throws TechnicalConnectorException;

   GetAccessRightResponse getAccessRight(SAMLToken var1, GetAccessRightRequest var2) throws TechnicalConnectorException;

   RevokeAccessRightResponse revokeAccessRight(SAMLToken var1, RevokeAccessRightRequest var2) throws TechnicalConnectorException;

   GetPatientAuditTrailResponse getPatientAuditTrail(SAMLToken var1, GetPatientAuditTrailRequest var2) throws TechnicalConnectorException;

   PutTransactionSetResponse putTransactionSet(SAMLToken var1, PutTransactionSetRequest var2) throws TechnicalConnectorException, IntraHubBusinessConnectorException;

   GetTransactionSetResponse getTransactionSet(SAMLToken var1, GetTransactionSetRequest var2) throws TechnicalConnectorException;

   GetLatestUpdateResponse getLatestUpdate(SAMLToken var1, GetLatestUpdateRequest var2) throws TechnicalConnectorException;
}
