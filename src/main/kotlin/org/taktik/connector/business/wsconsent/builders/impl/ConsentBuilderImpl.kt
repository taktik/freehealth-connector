package org.taktik.connector.business.wsconsent.builders.impl

import org.taktik.connector.business.wsconsent.builders.ConsentBuilder
import be.fgov.ehealth.hubservices.core.v2.AuthorWithPatientAndPersonType
import be.fgov.ehealth.hubservices.core.v2.BasicConsentType
import be.fgov.ehealth.hubservices.core.v2.ConsentType
import be.fgov.ehealth.hubservices.core.v2.PatientIdType
import be.fgov.ehealth.hubservices.core.v2.SelectGetPatientConsentType
import be.fgov.ehealth.standards.kmehr.cd.v1.CDCONSENT
import org.joda.time.DateTime

class ConsentBuilderImpl : ConsentBuilder {
    private fun createConsent(
        patient: PatientIdType,
        consent: List<CDCONSENT>,
        signdate: DateTime?,
        revokedate: DateTime?,
        author: AuthorWithPatientAndPersonType
                             ) = ConsentType().apply {
        this.author = author
        this.patient = patient
        this.revokedate = revokedate
        this.signdate = signdate
        cds.addAll(consent)
    }

    override fun createSelectGetPatientConsent(patient: PatientIdType, consent: List<CDCONSENT>) =
        SelectGetPatientConsentType().apply {
            this.patient = patient
            this.consent = BasicConsentType().apply {
                cds.addAll(consent)
            }
        }

    override fun createNewConsent(
        patient: PatientIdType,
        consent: List<CDCONSENT>,
        signdate: DateTime,
        author: AuthorWithPatientAndPersonType
                                 ) = this.createConsent(patient, consent, signdate, null, author)
}
