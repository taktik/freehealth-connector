package be.ehealth.businessconnector.hub.service;

import be.ehealth.business.intrahubcommons.exception.IntraHubBusinessConnectorException;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.service.sts.security.SAMLToken;
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

public interface IntraHubService {
   DeclareTransactionResponse declareTransaction(SAMLToken var1, DeclareTransactionRequest var2) throws IntraHubBusinessConnectorException, TechnicalConnectorException;

   PutTransactionResponse putTransaction(SAMLToken var1, PutTransactionRequest var2) throws IntraHubBusinessConnectorException, TechnicalConnectorException;

   RevokeTransactionResponse revokeTransaction(SAMLToken var1, RevokeTransactionRequest var2) throws IntraHubBusinessConnectorException, TechnicalConnectorException;

   GetTransactionListResponse getTransactionList(SAMLToken var1, GetTransactionListRequest var2) throws IntraHubBusinessConnectorException, TechnicalConnectorException;

   GetTransactionResponse getTransaction(SAMLToken var1, GetTransactionRequest var2) throws IntraHubBusinessConnectorException, TechnicalConnectorException;

   RequestPublicationResponse requestPublication(SAMLToken var1, RequestPublicationRequest var2) throws IntraHubBusinessConnectorException, TechnicalConnectorException;

   PutHCPartyResponse putHCParty(SAMLToken var1, PutHCPartyRequest var2) throws IntraHubBusinessConnectorException, TechnicalConnectorException;

   GetHCPartyResponse getHCParty(SAMLToken var1, GetHCPartyRequest var2) throws IntraHubBusinessConnectorException, TechnicalConnectorException;

   PutPatientResponse putPatient(SAMLToken var1, PutPatientRequest var2) throws IntraHubBusinessConnectorException, TechnicalConnectorException;

   GetPatientResponse getPatient(SAMLToken var1, GetPatientRequest var2) throws IntraHubBusinessConnectorException, TechnicalConnectorException;

   PutHCPartyConsentResponse putHCPartyConsent(SAMLToken var1, PutHCPartyConsentRequest var2) throws IntraHubBusinessConnectorException, TechnicalConnectorException;

   GetHCPartyConsentResponse getHCPartyConsent(SAMLToken var1, GetHCPartyConsentRequest var2) throws IntraHubBusinessConnectorException, TechnicalConnectorException;

   RevokeHCPartyConsentResponse revokeHCPartyConsent(SAMLToken var1, RevokeHCPartyConsentRequest var2) throws IntraHubBusinessConnectorException, TechnicalConnectorException;

   PutPatientConsentResponse putPatientConsent(SAMLToken var1, PutPatientConsentRequest var2) throws IntraHubBusinessConnectorException, TechnicalConnectorException;

   GetPatientConsentResponse getPatientConsent(SAMLToken var1, GetPatientConsentRequest var2) throws IntraHubBusinessConnectorException, TechnicalConnectorException;

   RevokePatientConsentResponse revokePatientConsent(SAMLToken var1, RevokePatientConsentRequest var2) throws IntraHubBusinessConnectorException, TechnicalConnectorException;

   PutTherapeuticLinkResponse putTherapeuticLink(SAMLToken var1, PutTherapeuticLinkRequest var2) throws IntraHubBusinessConnectorException, TechnicalConnectorException;

   GetTherapeuticLinkResponse getTherapeuticLink(SAMLToken var1, GetTherapeuticLinkRequest var2) throws IntraHubBusinessConnectorException, TechnicalConnectorException;

   RevokeTherapeuticLinkResponse revokeTherapeuticLink(SAMLToken var1, RevokeTherapeuticLinkRequest var2) throws IntraHubBusinessConnectorException, TechnicalConnectorException;

   PutAccessRightResponse putAccessRight(SAMLToken var1, PutAccessRightRequest var2) throws IntraHubBusinessConnectorException, TechnicalConnectorException;

   GetAccessRightResponse getAccessRight(SAMLToken var1, GetAccessRightRequest var2) throws IntraHubBusinessConnectorException, TechnicalConnectorException;

   RevokeAccessRightResponse revokeAccessRight(SAMLToken var1, RevokeAccessRightRequest var2) throws IntraHubBusinessConnectorException, TechnicalConnectorException;

   GetPatientAuditTrailResponse getPatientAuditTrail(SAMLToken var1, GetPatientAuditTrailRequest var2) throws IntraHubBusinessConnectorException, TechnicalConnectorException;
}
