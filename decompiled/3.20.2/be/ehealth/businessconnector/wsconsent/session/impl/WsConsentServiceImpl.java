package be.ehealth.businessconnector.wsconsent.session.impl;

import be.ehealth.businessconnector.wsconsent.exception.WsConsentBusinessConnectorException;
import be.ehealth.businessconnector.wsconsent.service.ServiceFactory;
import be.ehealth.businessconnector.wsconsent.session.WsConsentService;
import be.ehealth.technicalconnector.exception.SessionManagementException;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.session.Session;
import be.fgov.ehealth.hubservices.core.v2.GetPatientConsentRequest;
import be.fgov.ehealth.hubservices.core.v2.GetPatientConsentResponse;
import be.fgov.ehealth.hubservices.core.v2.GetPatientConsentStatusRequest;
import be.fgov.ehealth.hubservices.core.v2.GetPatientConsentStatusResponse;
import be.fgov.ehealth.hubservices.core.v2.PutPatientConsentRequest;
import be.fgov.ehealth.hubservices.core.v2.PutPatientConsentResponse;
import be.fgov.ehealth.hubservices.core.v2.RevokePatientConsentRequest;
import be.fgov.ehealth.hubservices.core.v2.RevokePatientConsentResponse;

public class WsConsentServiceImpl implements WsConsentService {
   public PutPatientConsentResponse putPatientConsent(PutPatientConsentRequest request) throws WsConsentBusinessConnectorException, TechnicalConnectorException, SessionManagementException {
      be.ehealth.businessconnector.wsconsent.service.WsConsentService service = ServiceFactory.getWsConsentService();
      return service.putPatientConsent(Session.getInstance().getSession().getSAMLToken(), request);
   }

   public GetPatientConsentResponse getPatientConsent(GetPatientConsentRequest request) throws WsConsentBusinessConnectorException, TechnicalConnectorException, SessionManagementException {
      be.ehealth.businessconnector.wsconsent.service.WsConsentService service = ServiceFactory.getWsConsentService();
      return service.getPatientConsent(Session.getInstance().getSession().getSAMLToken(), request);
   }

   public GetPatientConsentStatusResponse getPatientConsentStatus(GetPatientConsentStatusRequest request) throws WsConsentBusinessConnectorException, TechnicalConnectorException, SessionManagementException {
      be.ehealth.businessconnector.wsconsent.service.WsConsentService service = ServiceFactory.getWsConsentService();
      return service.getPatientConsentStatus(Session.getInstance().getSession().getSAMLToken(), request);
   }

   public RevokePatientConsentResponse revokePatientConsent(RevokePatientConsentRequest request) throws WsConsentBusinessConnectorException, TechnicalConnectorException, SessionManagementException {
      be.ehealth.businessconnector.wsconsent.service.WsConsentService service = ServiceFactory.getWsConsentService();
      return service.revokePatientConsent(Session.getInstance().getSession().getSAMLToken(), request);
   }
}
