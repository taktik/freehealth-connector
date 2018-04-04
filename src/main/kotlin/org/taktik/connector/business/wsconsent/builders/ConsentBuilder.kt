package org.taktik.connector.business.wsconsent.builders

import org.taktik.connector.business.wsconsent.exception.WsConsentBusinessConnectorException
import org.taktik.connector.technical.exception.TechnicalConnectorException
import be.fgov.ehealth.hubservices.core.v2.AuthorWithPatientAndPersonType
import be.fgov.ehealth.hubservices.core.v2.ConsentType
import be.fgov.ehealth.hubservices.core.v2.PatientIdType
import be.fgov.ehealth.hubservices.core.v2.SelectGetPatientConsentType
import be.fgov.ehealth.standards.kmehr.cd.v1.CDCONSENT
import org.joda.time.DateTime

interface ConsentBuilder {
	fun createSelectGetPatientConsent(patient: PatientIdType, consent: List<CDCONSENT>): SelectGetPatientConsentType
	fun createNewConsent(patient: PatientIdType, consent: List<CDCONSENT>, signdate: DateTime, author: AuthorWithPatientAndPersonType): ConsentType
}
