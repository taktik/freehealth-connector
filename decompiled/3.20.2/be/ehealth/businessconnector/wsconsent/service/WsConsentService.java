package be.ehealth.businessconnector.wsconsent.service;

import be.ehealth.businessconnector.wsconsent.exception.WsConsentBusinessConnectorException;
import be.ehealth.technicalconnector.exception.SessionManagementException;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.service.sts.security.SAMLToken;
import be.fgov.ehealth.hubservices.core.v2.GetPatientConsentRequest;
import be.fgov.ehealth.hubservices.core.v2.GetPatientConsentResponse;
import be.fgov.ehealth.hubservices.core.v2.GetPatientConsentStatusRequest;
import be.fgov.ehealth.hubservices.core.v2.GetPatientConsentStatusResponse;
import be.fgov.ehealth.hubservices.core.v2.PutPatientConsentRequest;
import be.fgov.ehealth.hubservices.core.v2.PutPatientConsentResponse;
import be.fgov.ehealth.hubservices.core.v2.RevokePatientConsentRequest;
import be.fgov.ehealth.hubservices.core.v2.RevokePatientConsentResponse;

public interface WsConsentService {
   PutPatientConsentResponse putPatientConsent(SAMLToken var1, PutPatientConsentRequest var2) throws WsConsentBusinessConnectorException, TechnicalConnectorException, SessionManagementException;

   GetPatientConsentResponse getPatientConsent(SAMLToken var1, GetPatientConsentRequest var2) throws WsConsentBusinessConnectorException, TechnicalConnectorException, SessionManagementException;

   GetPatientConsentStatusResponse getPatientConsentStatus(SAMLToken var1, GetPatientConsentStatusRequest var2) throws WsConsentBusinessConnectorException, TechnicalConnectorException, SessionManagementException;

   RevokePatientConsentResponse revokePatientConsent(SAMLToken var1, RevokePatientConsentRequest var2) throws WsConsentBusinessConnectorException, TechnicalConnectorException, SessionManagementException;
}
