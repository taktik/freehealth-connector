package be.ehealth.businessconnector.wsconsent.builders.impl;

import be.ehealth.businessconnector.wsconsent.builders.ConsentBuilder;
import be.ehealth.businessconnector.wsconsent.exception.WsConsentBusinessConnectorException;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.fgov.ehealth.hubservices.core.v2.AuthorWithPatientAndPersonType;
import be.fgov.ehealth.hubservices.core.v2.BasicConsentType;
import be.fgov.ehealth.hubservices.core.v2.ConsentType;
import be.fgov.ehealth.hubservices.core.v2.PatientIdType;
import be.fgov.ehealth.hubservices.core.v2.SelectGetPatientConsentType;
import be.fgov.ehealth.standards.kmehr.cd.v1.CDCONSENT;
import java.util.List;
import org.joda.time.DateTime;

public final class ConsentBuilderImpl implements ConsentBuilder {
   public ConsentBuilderImpl() {
   }

   private ConsentType createConsent(PatientIdType patient, List<CDCONSENT> consent, DateTime signdate, DateTime revokedate, AuthorWithPatientAndPersonType author) throws TechnicalConnectorException, WsConsentBusinessConnectorException, InstantiationException {
      ConsentType consentType = new ConsentType();
      consentType.setAuthor(author);
      consentType.setPatient(patient);
      consentType.setRevokedate(revokedate);
      consentType.setSigndate(signdate);
      consentType.getCds().addAll(consent);
      return consentType;
   }

   public SelectGetPatientConsentType createSelectGetPatientConsent(PatientIdType patient, List<CDCONSENT> consent) throws TechnicalConnectorException, WsConsentBusinessConnectorException, InstantiationException {
      SelectGetPatientConsentType result = new SelectGetPatientConsentType();
      result.setPatient(patient);
      BasicConsentType consentType = new BasicConsentType();
      if (consent != null) {
         consentType.getCds().addAll(consent);
      }

      result.setConsent(consentType);
      return result;
   }

   public ConsentType createNewConsent(PatientIdType patient, List<CDCONSENT> consent, DateTime signdate, AuthorWithPatientAndPersonType author) throws TechnicalConnectorException, WsConsentBusinessConnectorException, InstantiationException {
      return this.createConsent(patient, consent, signdate, (DateTime)null, author);
   }
}
