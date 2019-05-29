package be.ehealth.businessconnector.wsconsent.builders;

import be.ehealth.businessconnector.wsconsent.exception.WsConsentBusinessConnectorException;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.fgov.ehealth.hubservices.core.v2.AuthorWithPatientAndPersonType;
import be.fgov.ehealth.hubservices.core.v2.ConsentType;
import be.fgov.ehealth.hubservices.core.v2.PatientIdType;
import be.fgov.ehealth.hubservices.core.v2.SelectGetPatientConsentType;
import be.fgov.ehealth.standards.kmehr.cd.v1.CDCONSENT;
import java.util.List;
import org.joda.time.DateTime;

public interface ConsentBuilder {
   SelectGetPatientConsentType createSelectGetPatientConsent(PatientIdType var1, List<CDCONSENT> var2) throws TechnicalConnectorException, WsConsentBusinessConnectorException, InstantiationException;

   ConsentType createNewConsent(PatientIdType var1, List<CDCONSENT> var2, DateTime var3, AuthorWithPatientAndPersonType var4) throws TechnicalConnectorException, WsConsentBusinessConnectorException, InstantiationException;
}
