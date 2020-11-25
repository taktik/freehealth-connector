package be.ehealth.businessconnector.wsconsent.session;

import be.ehealth.businessconnector.wsconsent.exception.WsConsentBusinessConnectorException;
import be.ehealth.technicalconnector.exception.SessionManagementException;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.fgov.ehealth.hubservices.core.v2.GetPatientConsentRequest;
import be.fgov.ehealth.hubservices.core.v2.GetPatientConsentResponse;
import be.fgov.ehealth.hubservices.core.v2.GetPatientConsentStatusRequest;
import be.fgov.ehealth.hubservices.core.v2.GetPatientConsentStatusResponse;
import be.fgov.ehealth.hubservices.core.v2.PutPatientConsentRequest;
import be.fgov.ehealth.hubservices.core.v2.PutPatientConsentResponse;
import be.fgov.ehealth.hubservices.core.v2.RevokePatientConsentRequest;
import be.fgov.ehealth.hubservices.core.v2.RevokePatientConsentResponse;

public interface WsConsentService {
   PutPatientConsentResponse putPatientConsent(PutPatientConsentRequest var1) throws WsConsentBusinessConnectorException, TechnicalConnectorException, SessionManagementException;

   GetPatientConsentResponse getPatientConsent(GetPatientConsentRequest var1) throws WsConsentBusinessConnectorException, TechnicalConnectorException, SessionManagementException;

   GetPatientConsentStatusResponse getPatientConsentStatus(GetPatientConsentStatusRequest var1) throws WsConsentBusinessConnectorException, TechnicalConnectorException, SessionManagementException;

   RevokePatientConsentResponse revokePatientConsent(RevokePatientConsentRequest var1) throws WsConsentBusinessConnectorException, TechnicalConnectorException, SessionManagementException;
}
