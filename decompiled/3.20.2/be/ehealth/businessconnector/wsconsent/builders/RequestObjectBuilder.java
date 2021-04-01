package be.ehealth.businessconnector.wsconsent.builders;

import be.ehealth.businessconnector.wsconsent.exception.WsConsentBusinessConnectorException;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.fgov.ehealth.hubservices.core.v2.AuthorWithPatientAndPersonType;
import be.fgov.ehealth.hubservices.core.v2.ConsentType;
import be.fgov.ehealth.hubservices.core.v2.GetPatientConsentRequest;
import be.fgov.ehealth.hubservices.core.v2.GetPatientConsentStatusRequest;
import be.fgov.ehealth.hubservices.core.v2.PutPatientConsentRequest;
import be.fgov.ehealth.hubservices.core.v2.RevokePatientConsentRequest;
import be.fgov.ehealth.hubservices.core.v2.SelectGetPatientConsentType;

public interface RequestObjectBuilder {
   PutPatientConsentRequest createPutRequest(AuthorWithPatientAndPersonType var1, ConsentType var2) throws TechnicalConnectorException, WsConsentBusinessConnectorException, InstantiationException;

   RevokePatientConsentRequest createRevokeRequest(AuthorWithPatientAndPersonType var1, ConsentType var2) throws TechnicalConnectorException, WsConsentBusinessConnectorException, InstantiationException;

   GetPatientConsentRequest createGetRequest(AuthorWithPatientAndPersonType var1, SelectGetPatientConsentType var2) throws TechnicalConnectorException, WsConsentBusinessConnectorException, InstantiationException;

   GetPatientConsentStatusRequest createGetStatusRequest(AuthorWithPatientAndPersonType var1, SelectGetPatientConsentType var2) throws TechnicalConnectorException, WsConsentBusinessConnectorException, InstantiationException;
}
